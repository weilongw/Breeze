package controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.Model;
import model.UserDAO;

import org.mybeans.dao.DAOException;

import databean.User;

public class ShowProfileAction extends Action{
	
	private UserDAO userDAO;
	
	public ShowProfileAction(Model model) {
		userDAO = model.getUserDAO();
	}
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "showProfile.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		// TODO Auto-generated method stub
		List<String> errors = prepareErrors(request);
        
		User user = (User) request.getSession(false).getAttribute("user");
		
		if (user == null) {
			errors.add("You are not logged in");
			return "browse.do";
		}
		try {
			user = userDAO.lookup(user.getUserName());
			request.getSession().setAttribute("user", user);
		} catch(DAOException e) {
			errors.add(e.getMessage());
		}
		
		return "user_profile.jsp";
	}

}
