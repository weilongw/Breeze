package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import model.CommunityDAO;
import model.Model;
import model.UserDAO;

import org.mybeans.dao.DAOException;
import org.mybeans.forms.FormBeanFactory;

import databean.Community;
import databean.User;
import formbeans.AjaxCheckForm;

public class CheckAction extends Action{

	private FormBeanFactory<AjaxCheckForm> formBeanFactory = FormBeanFactory.getInstance(AjaxCheckForm.class, "<>\"");
	
	private UserDAO userDAO;
	private CommunityDAO communityDAO;
	
	public CheckAction(Model model) {
		userDAO = model.getUserDAO();
		communityDAO = model.getCommunityDAO();
	}
	
	@Override
	public String getName() {
		return "check.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		AjaxCheckForm form = formBeanFactory.create(request);
		List<String> msgs = new ArrayList<String>();
		request.setAttribute("result", "False");
		request.setAttribute("msgs", msgs);
		if (!form.isPresent()) {
			msgs.add("Form not present");
			return "ajaxCheck.jsp";
		}
		msgs.addAll(form.getValidationErrors());
		if (msgs.size() != 0) return "ajaxCheck.jsp";
		
		int field = Integer.parseInt(form.getField());
		if (field == 1) {
			String userName = form.getValue();
			if (userName.trim().length() == 0)
				msgs.add("username cannot be blank");
			if (userName.length() > 15)
				msgs.add("username is too long, at most 15 characters");
			Pattern p = Pattern.compile("[^a-zA-Z0-9]");
			boolean hasSpecialChar = p.matcher(userName).find();
			//boolean hasNonAlpha = userName.matches("^.*[^a-zA-Z0-9 ].*$");
			if (hasSpecialChar) {
				
				msgs.add("username can only contain characters and digits");
			}
			if (msgs.size() != 0) return "ajaxCheck.jsp";
			try {
				User user = userDAO.lookup(userName);
				if (user != null) {
					msgs.add("username " + userName + " has already been taken.");
				} else {
					request.setAttribute("result", "True");
				}	
			} catch (DAOException e) {
				msgs.add(e.getMessage());
			}
			
		} else if (field == 2) {
			String comm = form.getValue();
			if (comm.trim().length() == 0)
				msgs.add("community name cannot be blank");
			
			Pattern p = Pattern.compile("[^a-zA-Z0-9 _':]");
			boolean hasSpecialChar = p.matcher(comm).find();
			if (hasSpecialChar) {
				msgs.add("community name can only contain characters, digits, spaces and underscore");
			}
			
			if (msgs.size() != 0) return "ajaxCheck.jsp";
			try {
				Community old_comm = communityDAO.lookup(comm);
				if (old_comm != null) {
					msgs.add("Community " + comm + " already exists.");
				} else {
					request.setAttribute("result", "True");
				}	
			} catch (DAOException e) {
				msgs.add(e.getMessage());
			}
			
		} else {
			msgs.add("Invalid field input");
		}
		return "ajaxCheck.jsp";
	}

	
}
