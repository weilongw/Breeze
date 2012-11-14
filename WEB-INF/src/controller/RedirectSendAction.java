package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.ItemDAO;
import model.Model;

import org.mybeans.dao.DAOException;
import org.mybeans.forms.FormBeanFactory;

import databean.Item;
import databean.User;
import formbeans.MessageForm;
import formbeans.RedirectSendForm;


public class RedirectSendAction extends Action {

	private FormBeanFactory<RedirectSendForm> formBeanFactory = FormBeanFactory.getInstance(RedirectSendForm.class, "<>\"");
	
	private ItemDAO itemDAO;
	
	public RedirectSendAction(Model model) {
		itemDAO = model.getItemDAO();
	}
	@Override
	public String getName() {
		return "redirectSend.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		RedirectSendForm form = formBeanFactory.create(request);
		List<String> errors = new ArrayList<String>();
		User curUser = (User)request.getSession().getAttribute("user");
		
		if (curUser == null) return "browse.do";
		if (!form.isPresent()) {
			return "browse.do";
		}
		
		errors.addAll(form.getValidationErrors());
		if (errors.size() != 0) return "browse.do";
		
		int itemId = form.getItemIdAsInt();
		Item item = null;
		try {
			item = itemDAO.getItemById(itemId);
			if (item == null) {
				errors.add("Cannot find such item");
				return "index.jsp";
			}
		} catch (DAOException e) {
			errors.add(e.getMessage());
			return "index.jsp";
		}
		
		MessageForm newForm = new MessageForm();
		
		newForm.setReceiver(form.getReceiver());
		newForm.setTitle("About " + item.getItemName());
		
		request.setAttribute("form", newForm);
		return "message.jsp";
		
		
	}

	

}
