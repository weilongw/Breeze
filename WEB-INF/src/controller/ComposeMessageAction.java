package controller;



import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.MessageDAO;
import model.Model;
import model.UserDAO;

import org.mybeans.dao.DAOException;
import org.mybeans.forms.FormBeanFactory;


import databean.User;
import formbeans.MessageForm;

public class ComposeMessageAction extends Action{

	private FormBeanFactory<MessageForm> formBeanFactory = FormBeanFactory.getInstance(MessageForm.class, "<>\"'\\\n");
	private MessageDAO messageDAO;
	private UserDAO userDAO;
	
	public ComposeMessageAction(Model model) {
		messageDAO = model.getMessageDAO();
		userDAO = model.getUserDAO();
	}
	
	@Override
	public String getName() {
		return "composeMessage.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		MessageForm form = formBeanFactory.create(request);
		
		request.setAttribute("form", form);
		List<String> errors = prepareErrors(request);
		if (!form.isPresent()) {
			return "message.jsp";
		}
		User curUser = (User)request.getSession().getAttribute("user");
		if (curUser == null) {
			errors.add("You are not logged in");
			return "browse.do";
		}
		errors.addAll(form.getValidationErrors());
		if (errors.size() != 0) return "showMessage.do";
		
		User receiver = null;
		try {
			receiver = userDAO.lookup(form.getReceiver());
			if (receiver == null)
				errors.add("Cannot find user named " + form.getReceiver());
		} catch(DAOException e) {
			errors.add(e.getMessage());
		}
		if (errors.size() != 0) return "message.jsp";
		
		//Message msg = new Message();
//		msg.setContent(form.getContent().replace("&#39;", "&quot;"));
		
		//msg.setReceiver(receiver);
		//msg.setTitle(form.getTitle().replace("&#39;", "&quot;"));
		//msg.setSentDate(new Date());
		try {
			messageDAO.send(curUser, receiver, form.getTitle(),
											   form.getContent());
		} catch (DAOException e) {
			errors.add(e.getMessage());
			return "showMessage.do";
		}
		
		request.setAttribute("success", "Your message has been sent");
		return "showMessage.do";
			
	}



}
