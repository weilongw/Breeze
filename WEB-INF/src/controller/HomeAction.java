package controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import model.Model;
import model.UserDAO;

import org.mybeans.dao.DAOException;

import databean.User;

public class HomeAction extends Action {

	private UserDAO userDAO;
	
	public HomeAction(Model model) {
		userDAO = model.getUserDAO();
	}
	@Override
	public String getName() {
		return "home.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		User curUser = (User)request.getSession().getAttribute("user");
		if (curUser != null) {
			try {
				curUser = userDAO.lookup(curUser.getUserName());
				request.getSession().setAttribute("user", curUser);
			} catch (DAOException e) {
			}
		}
		
		String hello = "hello world";
		byte[] haha = null;
		try {
			haha = hello.getBytes("ISO-8859-1");
		} catch (UnsupportedEncodingException e) {
			
			e.printStackTrace();
		}
		String str = new String(haha); 
		return "home.jsp";
	}

	

}
