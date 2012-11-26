package controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.CommunityDAO;
import model.Model;
import model.RelationDAO;
import model.UserDAO;

import org.mybeans.dao.DAOException;
import org.mybeans.forms.FormBeanFactory;

import databean.Community;
import databean.User;
import formbeans.CreateCommunityForm;

public class CreateCommunityAction extends Action {

	private FormBeanFactory<CreateCommunityForm> formBeanFactory = FormBeanFactory.getInstance(CreateCommunityForm.class, "<>\"");
	
	private CommunityDAO communityDAO;
	private RelationDAO relationDAO;
	private UserDAO userDAO;
	
	public CreateCommunityAction (Model model) {
		communityDAO = model.getCommunityDAO();
		relationDAO = model.getRelationDAO();
		userDAO = model.getUserDAO();
	}
	@Override
	public String getName() {
		return "createCommunity.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		CreateCommunityForm form = formBeanFactory.create(request);
		List<String> errors = prepareErrors(request);
		User curUser = (User)request.getSession().getAttribute("user");
		if (curUser == null) {
			errors.add("You are not logged in");
			return "browseCommunity.do";
		}
		if (!form.isPresent()) return "create_grp.jsp";
		
		request.setAttribute("form", form);
		errors.addAll(form.getValidationErrors());
		if (errors.size() != 0) return "create_grp.jsp";
		
		Community newCommunity = new Community(form.getName());
		newCommunity.setRelatedMovie(form.getRelatedMovie());
		newCommunity.setInfo(form.getInfo());
		newCommunity.setCreater(curUser);
		newCommunity.setTopicCount(0);
		newCommunity.setUserCount(1);
		newCommunity.setCreatedAt(new Date());
		
		
		try {
			communityDAO.create(newCommunity);
			relationDAO.create(curUser, newCommunity);
		} catch (DAOException e) {
			errors.add(e.getMessage());
			return "create_grp.jsp";
		}
		
		request.setAttribute("success", "Your community has been created");
		request.setAttribute("form", null);
		
		
		try {
			curUser = userDAO.lookup(curUser.getUserName());
			request.getSession().setAttribute("user", curUser);
		} catch (DAOException e) {
		}
	
		return "browseCommunity.do";
	}

	
}
