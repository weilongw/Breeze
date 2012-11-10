package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import databean.User;

public class ShowProfileAction extends Action{
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "showProfile.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		// TODO Auto-generated method stub
		List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        
		User user = (User) request.getSession(false).getAttribute("user");
		
		if (user == null) {
			errors.add("You are not logged in");
			return "index.jsp";
		}

		return "user_profile.jsp";
	}

}
