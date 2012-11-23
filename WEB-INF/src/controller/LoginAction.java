package controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.Model;
import model.UserDAO;

import org.mybeans.dao.DAOException;
import org.mybeans.forms.FormBeanFactory;

import databean.User;

import formbeans.LoginForm;

public class LoginAction extends Action{

	private FormBeanFactory<LoginForm> formBeanFactory = FormBeanFactory.getInstance(LoginForm.class,"<>\"");

	private UserDAO userDAO;
	//private ItemDAO itemDAO;
	
	public LoginAction(Model model) {
		// TODO Auto-generated constructor stub
		userDAO = model.getUserDAO();
		//itemDAO = model.getItemDAO();
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "login.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		
		LoginForm form = formBeanFactory.create(request);
    	
		List<String> errors = prepareErrors(request);
        
		
        if (!form.isPresent()) {
            return "browse.do";
        }
        request.setAttribute("form",form);
        errors.addAll(form.getValidationErrors());
        if (errors.size() != 0) {
            return "browse.do";
        }

        User user;
        try {
        	user = userDAO.lookup(form.getUserName());
        } catch (DAOException e) {
        	errors.add(e.getMessage());
        	return "browse.do";
        }
        
        if (user == null) {
            errors.add("Username not found");
            return "browse.do";
        }

        if (!user.checkPassword(form.getPassword())) {
            errors.add("Incorrect password");
            return "browse.do";
        }

        HttpSession session = request.getSession();
        session.setAttribute("user",user);
        
        
        request.setAttribute("success", "Welcome back! " + user.getUserName());
        return "browse.do";
        
	}


}
