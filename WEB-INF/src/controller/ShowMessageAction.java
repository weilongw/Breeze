package controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.MessageDAO;
import model.Model;

import org.mybeans.dao.DAOException;

import databean.Message;
import databean.User;

public class ShowMessageAction extends Action{

	private MessageDAO messageDAO;

	public ShowMessageAction(Model model) {
		messageDAO = model.getMessageDAO();
	}
	
	@Override
	public String getName() {
		return "showMessage.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		List<String> errors = prepareErrors(request);
		Message[] inbox = null;
		Message[] sent = null;
		User user = (User)request.getSession().getAttribute("user");
		if (user == null) {
			errors.add("You are not logged in");
			return "browse.do";
		}
		
		try {
			inbox = messageDAO.getNMessageByReceiver(user);
			sent = messageDAO.getNMessageBySender(user);
		} catch(DAOException e) {
			errors.add(e.getMessage());
			return "message.jsp";
		}
		System.out.println(inbox.length);
		int inboxPageCount = inbox.length == 0 ? 1 : ((inbox.length - 1) / 5 + 1);
		request.setAttribute("inboxPageCount", inboxPageCount);
		request.setAttribute("inbox", inbox);
		request.setAttribute("sent", sent);
		
		return "message.jsp";
	}

}
