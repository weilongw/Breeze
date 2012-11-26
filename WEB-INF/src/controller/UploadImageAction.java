package controller;


import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.ItemDAO;
import model.Model;
import model.UserDAO;

import org.mybeans.dao.DAOException;
import org.mybeans.forms.FormBeanFactory;

import databean.Item;
import databean.User;
import formbeans.UploadForm;

public class UploadImageAction extends Action{
	
	private ItemDAO itemDAO;
	private UserDAO userDAO;
	
	private FormBeanFactory<UploadForm> formBeanFactory = FormBeanFactory.getInstance(UploadForm.class,"<>\"");
	
	public UploadImageAction(Model model) {
		itemDAO = model.getItemDAO();
		userDAO = model.getUserDAO();
	}

	@Override
	public String getName() {
		return "uploadImage.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		
		UploadForm form = formBeanFactory.create(request);
		List<String> errors = prepareErrors(request);
		User curUser = (User)request.getSession().getAttribute("user");
		Item newItem = (Item)request.getSession().getAttribute("newItem");
		if (curUser == null) {
			errors.add("You are logged in");
			return "browse.do";
		}
		if (newItem == null) {
			errors.add("You haven't posted an item yet");
			return "post_item.jsp";
		}
		if (!form.isPresent()) {
			return "upload_image.jsp";
		}
		errors.addAll(form.getValidationErrors());
		if (errors.size() != 0 ) return "upload_image.jsp";
		StringBuffer buf = new StringBuffer();
		buf.append(curUser.getUserName());
		buf.append(newItem.getId());
		buf.append(".png");
		String fname = buf.toString();
		if (form.getImgName().equals(fname)) {
			File f = new File("webapps/Breeze/img/item/" + fname);
			if (!f.exists()) errors.add("No uploaded file is found");
		} else if (form.getImgName().equals("default.jpg")) {
			
		} else {
			errors.add("Invalid Image file name");
		}
		if (errors.size() != 0) return "upload_image.jsp"; 
		
		try {
			itemDAO.setImgName(newItem.getId(), form.getImgName());
		} catch (DAOException e) {
			errors.add(e.getMessage());
			return "upload_image.jsp";
		}
		request.getSession().setAttribute("newItem", null);
		
	

		try {
			curUser = userDAO.lookup(curUser.getUserName());
			request.getSession().setAttribute("user", curUser);
		} catch (DAOException e) {
		}
		
		return "showMyItems.do";
	}

}
