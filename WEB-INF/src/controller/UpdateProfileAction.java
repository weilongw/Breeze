package controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.Model;
import model.UserDAO;

import org.mybeans.dao.DAOException;
import org.mybeans.forms.FormBeanFactory;

import databean.User;
import formbeans.UpdateProfileForm;;

public class UpdateProfileAction extends Action{

	private FormBeanFactory<UpdateProfileForm> formBeanFactory = FormBeanFactory.getInstance(UpdateProfileForm.class,"<>\"");

	private UserDAO userDAO;
	
	public UpdateProfileAction(Model model) {
		userDAO = model.getUserDAO();
	}
	
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "updateProfile.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		// TODO Auto-generated method stub
		UpdateProfileForm form = formBeanFactory.create(request);
		List<String> errors = prepareErrors(request);
        User user =  (User) request.getSession(false).getAttribute("user");
        
        if (user == null) {
			errors.add("You are not logged in");
			return "browse.do";
		}
        
        if (!form.isPresent()) {
            return "showProfile.do";
        }
        
        errors.addAll(form.getValidationErrors());
        
        if (errors.size() != 0) {
            return "user_profile.jsp";
        }
        
        
        
        user.setEmail(form.getEmail());
        user.setAddress(form.getAddress());
        user.setPassword(form.getPassword());
        
        try {
			userDAO.update(user);
			
		} catch (DAOException e) {
			// TODO Auto-generated catch block
        	errors.add(e.getMessage());
        	return "user_profile.jsp";
		}
        
		String successMsg  = "Profile updated!";
        request.setAttribute("success",successMsg);
        
		try {
			user = userDAO.lookup(user.getUserName());
			request.getSession().setAttribute("user", user);
		} catch (DAOException e) {
		}
	
		return "user_profile.jsp";
	}

}
