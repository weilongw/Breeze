package controller;

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
import formbeans.ViewCommunityForm;

public class UnjoinCommunityAction extends Action{
	private FormBeanFactory<ViewCommunityForm> formBeanFactory = FormBeanFactory.getInstance(ViewCommunityForm.class, "<>\"");
	
	private RelationDAO relationDAO;
	private CommunityDAO communityDAO;
	private UserDAO userDAO;
	
	public UnjoinCommunityAction(Model model){
		relationDAO = model.getRelationDAO();
		communityDAO = model.getCommunityDAO();
		userDAO = model.getUserDAO();
	}
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "unjoinCommunity.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		// TODO Auto-generated method stub
		ViewCommunityForm form = formBeanFactory.create(request);
		List<String> errors = prepareErrors(request);
		//request.setAttribute("choice", "Unjoin!");
		User curUser = (User) request.getSession(false).getAttribute("user");
		
		
		if(!form.isPresent()) return "browseCommunity.do";
		errors.addAll(form.getValidationErrors());
		if (errors.size() != 0) return "browseCommunity.do";
		
		Community community;
		try {
			community = communityDAO.lookup(form.getName());
			if(community == null){
				errors.add("Cannot find community named " + form.getName());
				return "browseCommunity.do";
			}
			if (curUser == null) {
				errors.add("You are not logged in");
				return "viewCommunity.do?name=" + form.getName();
			}
			if(!relationDAO.exist(curUser, community)){
				errors.add("You are not in this community: " + community.getName());
				return "viewCommunity.do?name=" + form.getName();
			}
				
			relationDAO.destroy(curUser, community);
			communityDAO.updateUserCount(-1, community.getName());
			
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			errors.add(e.getMessage());
			return "viewCommunity.do?name=" + form.getName();
		}
		
		String success = "You are not in community " + community.getName() + " any more";
		request.setAttribute("success",success);	
		
		try {
			curUser = userDAO.lookup(curUser.getUserName());
			request.getSession().setAttribute("user", curUser);
		} catch (DAOException e) {
		}
		
		return "viewCommunity.do?name=" + form.getName();
	}
	
	
	
}
