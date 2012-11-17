package controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.ItemDAO;
import model.Model;

import org.mybeans.dao.DAOException;
import org.mybeans.forms.FormBeanFactory;

import formbeans.UploadForm;

public class UploadImageAction extends Action{
	
	private ItemDAO itemDAO;
	private FormBeanFactory<UploadForm> formBeanFactory = FormBeanFactory.getInstance(UploadForm.class,"<>\"");
	
	public UploadImageAction(Model model) {
		itemDAO = model.getItemDAO();
	}

	@Override
	public String getName() {
		return "uploadImage.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		UploadForm form = formBeanFactory.create(request);
		List<String> errors = prepareErrors(request);
		if (!form.isPresent()) {
			return "post_item.jsp";
		}
		errors.addAll(form.getValidationErrors());

		if (errors.size() != 0 ) return "upload_image.jsp";
		
		
		int itemId = form.getItemIdAsInt();
		try {
			itemDAO.setImgName(itemId, form.getImgName());
		} catch (DAOException e) {
			errors.add(e.getMessage());
			return "upload_image.jsp";
		}
		request.getSession().setAttribute("newItem", null);
		return "showMyItems.do";
	}


}
