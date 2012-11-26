package controller;

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
		return "home.jsp";
	}

	

}
