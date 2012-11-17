package controller;


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
		List<String> errors = prepareErrors(request);
        
		User user = (User) request.getSession(false).getAttribute("user");
		
		if (user == null) {
			errors.add("You are not logged in");
			return "browse.do";
		}

		return "user_profile.jsp";
	}

}
