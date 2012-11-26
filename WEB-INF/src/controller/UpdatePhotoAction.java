package controller;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.Model;
import model.UserDAO;

import org.mybeans.dao.DAOException;
import org.mybeans.forms.FormBeanFactory;

import databean.User;
import formbeans.UserPhotoForm;

public class UpdatePhotoAction extends Action {

	private FormBeanFactory<UserPhotoForm> formBeanFactory = FormBeanFactory.getInstance(UserPhotoForm.class, "<>\"");
	
	private UserDAO userDAO;
	
	public UpdatePhotoAction (Model model) {
		userDAO = model.getUserDAO();
	}
	@Override
	public String getName() {
		return "updatePhoto.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		UserPhotoForm form = formBeanFactory.create(request);
		List<String> errors = prepareErrors(request);
		User curUser = (User)request.getSession().getAttribute("user");
		if (curUser == null) {
			errors.add("You are not logged in");
			return "browse.do";
		}
		if (!form.isPresent()) return "showProfile.do";
		
		errors.addAll(form.getValidationErrors());
		if (errors.size() != 0) return "showProfile.do";
		
		File f = new File("webapps/Breeze/img/user/" + form.getUserPhoto());
		if (!f.exists()) {
			errors.add("Cannot found uploaded file");
			return "showProfile.do";
		}
		
		try {
			userDAO.setPhoto(curUser, form.getUserPhoto());
			curUser.setUserPhoto(form.getUserPhoto());
		} catch(DAOException e) {
			errors.add(e.getMessage());
			return "showProfile.do";
		}
		request.setAttribute("success", "Your profile has been updated");
		
		try {
			curUser = userDAO.lookup(curUser.getUserName());
			request.getSession().setAttribute("user", curUser);
		} catch (DAOException e) {
		}

		return "showProfile.do";
	}

	
}
