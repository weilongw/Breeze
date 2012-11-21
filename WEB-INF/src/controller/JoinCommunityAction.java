package controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.mybeans.dao.DAOException;
import org.mybeans.forms.FormBeanFactory;

import databean.Community;
import databean.User;

import formbeans.ViewCommunityForm;

import model.CommunityDAO;
import model.Model;
import model.RelationDAO;

public class JoinCommunityAction extends Action{
	
	private FormBeanFactory<ViewCommunityForm> formBeanFactory = FormBeanFactory.getInstance(ViewCommunityForm.class, "<>\"");
	
	RelationDAO relationDAO;
	CommunityDAO communityDAO;
	
	public JoinCommunityAction(Model model){
		relationDAO = model.getRelationDAO();
		communityDAO = model.getCommunityDAO();
	}
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "joinCommunity.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		// TODO Auto-generated method stub
		ViewCommunityForm form = formBeanFactory.create(request);
		List<String> errors = prepareErrors(request);

		User curUser = (User) request.getSession(false).getAttribute("user");
		if (curUser == null) {
			errors.add("You are not logged in");
			return "browse.do";
		}
		
		if(!form.isPresent()) return "browseCommunity.do";
		errors.addAll(form.getValidationErrors());
		if (errors.size() != 0) return "browseCommunity.do";
		
		Community community;
		try {
			community = communityDAO.lookup(form.getName());
			if(community == null){
				errors.add("Unknown Community.");
				return "browseCommunity.do";
			}
			
			if(relationDAO.exist(curUser, community)){
				errors.add("You are already in this community: " + community.getName());
				return "viewCommunity.do?name=" + community.getName();
			}
				
			relationDAO.create(curUser, community);
			
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			errors.add(e.getMessage());
			return "browseCommunity.do";
		}
		
		String success = "You become one of them!";
		request.setAttribute("success",success);			
		
		return "viewCommunity.do?name=" + community.getName();
	}

}
