package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.MessageDAO;
import model.Model;
import model.UserDAO;

import org.mybeans.dao.DAOException;
import org.mybeans.factory.RollbackException;
import org.mybeans.factory.Transaction;
import org.mybeans.forms.FormBeanFactory;

import databean.Message;
import databean.User;
import formbeans.ReadMessageForm;

public class AjaxReadAction extends Action {

	private FormBeanFactory<ReadMessageForm> formBeanFactory = FormBeanFactory.getInstance(ReadMessageForm.class, "<>\"");
	
	private UserDAO userDAO;
	private MessageDAO messageDAO;
	
	public AjaxReadAction (Model model) {
		userDAO = model.getUserDAO();
		messageDAO = model.getMessageDAO();
		
	}
	@Override
	public String getName() {
		return "read.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		ReadMessageForm form = formBeanFactory.create(request);
		List<String> msgs = new ArrayList<String>();
		request.setAttribute("result", "False");
		request.setAttribute("msgs", msgs);
		if (!form.isPresent()) {
			msgs.add("Form not present");
			return "ajaxCheck.jsp";
		}
		msgs.addAll(form.getValidationErrors());
		if (msgs.size() != 0) return "ajaxCheck.jsp";
		User curUser = (User)request.getSession().getAttribute("user");
		if (curUser == null) {
			msgs.add("You are not logged in");
			return "ajaxCheck.jsp";
		}
		int messageID = Integer.parseInt(form.getMsgId());
		Message msg = null;
		try {
			msg = messageDAO.lookup(messageID);
			if (msg == null) {
				msgs.add("Cannot find message");
				return "ajaxCheck.jsp";
			}
		} catch (DAOException e){
			msgs.add(e.getMessage());
			return "ajaxCheck.jsp";
		}
		
		if (!msg.getReceiver().getUserName().equals(curUser.getUserName())) {
			msgs.add("You are not receiver of this message");
			return "ajaxCheck.jsp";
		}
		
		if (msg.getHasRead() == 1) {
			msgs.add("This message has already been marked read");
			return "ajaxCheck.jsp";
		}
		try {
			Transaction.begin();
			messageDAO.markRead(msg.getId());
			userDAO.updateNewMsgCount(curUser.getUserName(), -1);
			Transaction.commit();
			curUser = userDAO.lookup(curUser.getUserName());
		} catch (RollbackException e) {
			msgs.add(e.getMessage());
			return "ajaxCheck.jsp";
		} catch (DAOException e) { 
			msgs.add(e.getMessage());
			return "ajaxCheck.jsp";
		} finally {
			if(Transaction.isActive()) Transaction.rollback();
		}
		request.getSession().setAttribute("user", curUser);
		msgs.add(Integer.toString(curUser.getNewMsgCount()));
		request.setAttribute("result", "True");
		request.setAttribute("messageID", Integer.toString(msg.getId()));
		request.setAttribute("date", msg.getSentDate());
		return "ajaxCheck.jsp";
	}

}
