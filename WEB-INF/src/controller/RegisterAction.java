package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.Model;
import model.UserDAO;

import org.mybeans.dao.DAOException;
import org.mybeans.forms.FormBeanFactory;

import databean.User;

import formbeans.RegisterForm;

public class RegisterAction extends Action{
	private FormBeanFactory<RegisterForm> formBeanFactory = FormBeanFactory.getInstance(RegisterForm.class,"<>\"");

	private UserDAO userDAO;
	
	public RegisterAction(Model model) {
		userDAO = model.getUserDAO();
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "register.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		// TODO Auto-generated method stub
		RegisterForm form = formBeanFactory.create(request);
		List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        request.setAttribute("form",form);
        
        if (!form.isPresent()) {
            return "register.jsp";
        }
        
        errors.addAll(form.getValidationErrors());
        
        if (errors.size() != 0) {
            return "register.jsp";
        }
        
        User user = new User(form.getUserName());
        user.setEmail(form.getEmail());
        user.setAddress(form.getAddress());
        user.setPassword(form.getPassword());
        user.setCredit(20);		// initial credit should be defined as static final later
        
        try {
			userDAO.create(user);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
        	errors.add(e.getMessage());
        	return "register.jsp";
		}
        
        HttpSession session = request.getSession(false);
        session.setAttribute("user",user);
        
        return "browse.do";// add some welcome message or send welcome message to user
	}

}
