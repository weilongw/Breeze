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
		
		int inboxPageCount = inbox.length == 0 ? 1 : ((inbox.length - 1) / PAGE_COUNT + 1);
		int inboxPageEnd = inboxPageCount > (2 * PAGE + 1) ? (2 * PAGE + 1) : inboxPageCount;
		int sentPageCount = sent.length == 0 ? 1 : ((sent.length - 1) / PAGE_COUNT + 1);
		int sentPageEnd = sentPageCount > (2 * PAGE + 1) ? (2 * PAGE + 1) : sentPageCount;
		
		request.setAttribute("inboxPageCount", inboxPageCount);
		request.setAttribute("inboxPageStart", 1);
		request.setAttribute("inboxPageEnd", inboxPageEnd);
		request.setAttribute("inboxPageCurrent", 1);
		request.setAttribute("inboxStart", 0);
		request.setAttribute("inboxEnd", PAGE_COUNT - 1);
		
		request.setAttribute("sentPageCount", sentPageCount);
		request.setAttribute("sentPageStart", 1);
		request.setAttribute("sentPageEnd", sentPageEnd);
		request.setAttribute("sentPageCurrent", 1);
		request.setAttribute("sentStart", 0);
		request.setAttribute("sentEnd", PAGE_COUNT - 1);
		
		request.setAttribute("inbox", inbox);
		request.setAttribute("sent", sent);
		
		return "message.jsp";
	}

}
