package controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.MessageDAO;
import model.Model;
import model.UserDAO;

import org.mybeans.dao.DAOException;
import org.mybeans.forms.FormBeanFactory;

import databean.Message;
import databean.User;
import formbeans.MessageForm;
import formbeans.PageForm;

public class PaginateMsgAction extends Action{

	private FormBeanFactory<PageForm> formBeanFactory = FormBeanFactory.getInstance(PageForm.class, "<>\"");
	
	private MessageDAO messageDAO;
	private UserDAO userDAO;
	
	public PaginateMsgAction (Model model) {
		messageDAO = model.getMessageDAO();
		userDAO = model.getUserDAO();
	}

	@Override
	public String getName() {
		return "pageMsg.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		PageForm form = formBeanFactory.create(request);
		List<String> errors = prepareErrors(request);
		User curUser = (User)request.getSession().getAttribute("user");
		if (curUser == null) {
			errors.add("You are not logged in");
			return "browse.do";
		}
		if (!form.isPresent()) return "showMessage.do";
		errors.addAll(form.getValidationErrors());
		if (errors.size() != 0) return "showMessage.do";
		
		int inboxIdx = Integer.parseInt(form.getInbox());
		int sentIdx = Integer.parseInt(form.getSent());
		int render = Integer.parseInt(form.getRender());
		
		if (render == PageForm.SENT) {
			request.setAttribute("success", "1");
			request.setAttribute("form", new MessageForm());
		}
		
		Message[] inbox = null;
		Message[] sent = null;
		
		try {
			inbox = messageDAO.getNMessageByReceiver(curUser);
			sent = messageDAO.getNMessageBySender(curUser);
		} catch(DAOException e) {
			errors.add(e.getMessage());
			return "message.jsp";
		}
		
		int inboxPageCount = inbox.length == 0 ? 1 : ((inbox.length - 1) / PAGE_COUNT + 1);
		int inboxPageStart = getStart(inboxIdx, inboxPageCount);
		int inboxPageEnd = getEnd(inboxIdx, inboxPageCount);
		if (inboxPageStart == 0 || inboxPageEnd == 0) {
			errors.add("Invalid inbox page index");
			return "showMessage.do";
		}
		int inboxStart = (inboxIdx - 1) * PAGE_COUNT;
		int inboxEnd = inboxStart + PAGE_COUNT - 1;
		
		int sentPageCount = sent.length == 0 ? 1 : ((sent.length - 1) / PAGE_COUNT + 1);
		int sentPageStart = getStart(sentIdx, sentPageCount);
		int sentPageEnd = getEnd(sentIdx, sentPageCount);
		if (sentPageStart == 0 || sentPageEnd == 0) {
			errors.add("Invalid sent page index");
			return "showMessage.do";
		}
		int sentStart = (sentIdx - 1) * PAGE_COUNT;
		int sentEnd = sentStart + PAGE_COUNT - 1;
		
		request.setAttribute("inboxPageCount", inboxPageCount);
		request.setAttribute("inboxPageStart", inboxPageStart);
		request.setAttribute("inboxPageEnd", inboxPageEnd);
		request.setAttribute("inboxPageCurrent", inboxIdx);
		request.setAttribute("inboxStart", inboxStart);
		request.setAttribute("inboxEnd", inboxEnd);
		
		request.setAttribute("sentPageCount", sentPageCount);
		request.setAttribute("sentPageStart", sentPageStart);
		request.setAttribute("sentPageEnd", sentPageEnd);
		request.setAttribute("sentPageCurrent", sentIdx);
		request.setAttribute("sentStart", sentStart);
		request.setAttribute("sentEnd", sentEnd);
		
		request.setAttribute("inbox", inbox);
		request.setAttribute("sent", sent);
		
		

	    try {
			curUser = userDAO.lookup(curUser.getUserName());
			request.getSession().setAttribute("user", curUser);
		} catch (DAOException e) {
		}

		
		return "message.jsp";
	}
	
	public int getStart(int idx, int count) {
		if (idx < 1 || idx > count) return 0;
		if (count <= (2 * PAGE + 1)) return 1;
		
		if (idx - PAGE <= 1) return 1;
		else if ((idx - PAGE) <= (count - 2*PAGE)) return (idx - PAGE);
		else return (count - 2*PAGE);
	}
	
	public int getEnd(int idx, int count) {
		if (idx < 1 || idx > count) return 0;
		if (count <= (2*PAGE + 1)) return count;
		
		if ((idx + PAGE) <= (2 * PAGE + 1)) return (2 * PAGE + 1);
		else if (idx + PAGE <= count) return (idx + PAGE);
		else return count;
	}

}
