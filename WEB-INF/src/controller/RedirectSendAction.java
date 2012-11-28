package controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.Model;
import model.UserDAO;

import org.mybeans.dao.DAOException;
import org.mybeans.forms.FormBeanFactory;

import databean.User;
import formbeans.MessageForm;
import formbeans.RedirectSendForm;


public class RedirectSendAction extends Action {

	private FormBeanFactory<RedirectSendForm> formBeanFactory = FormBeanFactory.getInstance(RedirectSendForm.class, "<>\"");
	
	private UserDAO userDAO;
	public RedirectSendAction(Model model) {
		userDAO = model.getUserDAO();
	}
	@Override
	public String getName() {
		return "redirectSend.do";
	}

	@Override
	public String perform(HttpServletRequest request){
		RedirectSendForm form = formBeanFactory.create(request);
		List<String> errors = prepareErrors(request);
		User curUser = (User)request.getSession().getAttribute("user");
		
		if (curUser == null) {
			errors.add("You are not logged in");
			return "browse.do";
		}
		MessageForm newForm = new MessageForm();
		request.setAttribute("form", newForm);
		if (!form.isPresent()) {
			return "showMessage.do";
		}

		newForm.setReceiver(form.getReceiver());
		
		errors.addAll(form.getValidationErrors());
		if (errors.size() != 0) return "showMessage.do";
		
		try {
			User user = userDAO.lookup(form.getReceiver());
			if (user == null) {
				errors.add("No such user present");
				return "showMessage.do";
			}
		} catch (DAOException e) {
			errors.add(e.getMessage());
			return "showMessage.do";
		}
		
		
		
		try {
			curUser = userDAO.lookup(curUser.getUserName());
			request.getSession().setAttribute("user", curUser);
		} catch (DAOException e) {
		}

		
		//request.setAttribute("form", newForm);
		return "showMessage.do";
		
		
	}

	

}
