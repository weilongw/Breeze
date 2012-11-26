package controller;



import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.MessageDAO;
import model.Model;
import model.UserDAO;

import org.mybeans.dao.DAOException;
import org.mybeans.factory.RollbackException;
import org.mybeans.factory.Transaction;
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
		List<String> errors = prepareErrors(request);
		User curUser = (User)request.getSession().getAttribute("user");
		if (curUser == null) {
			errors.add("You are not logged in");
			return "browse.do";
		}
		
		request.setAttribute("form", form);
		
		if (!form.isPresent()) {
			return "message.jsp";
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
			Transaction.begin();
			messageDAO.send(curUser, receiver, form.getTitle(),
											   form.getContent());
			userDAO.updateNewMsgCount(receiver.getUserName(), 1);
			Transaction.commit();
		} catch (RollbackException e) {
			errors.add(e.getMessage());
			return "showMessage.do";
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}
		try {
			curUser = userDAO.lookup(curUser.getUserName());
		} catch(DAOException e) {
			errors.add(e.getMessage());
			return "showMyItems.do";
		}
		System.out.println(curUser.getNewMsgCount());
		request.getSession().setAttribute("user", curUser);
		request.setAttribute("success", "Your message has been sent");
		return "showMessage.do";
			
	}



}
