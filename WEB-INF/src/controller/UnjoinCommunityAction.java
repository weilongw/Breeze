package controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.CommunityDAO;
import model.Model;
import model.RelationDAO;

import org.mybeans.dao.DAOException;
import org.mybeans.forms.FormBeanFactory;

import databean.Community;
import databean.User;

import formbeans.ViewCommunityForm;

public class UnjoinCommunityAction extends Action{
	private FormBeanFactory<ViewCommunityForm> formBeanFactory = FormBeanFactory.getInstance(ViewCommunityForm.class, "<>\"");
	
	RelationDAO relationDAO;
	CommunityDAO communityDAO;
	
	public UnjoinCommunityAction(Model model){
		relationDAO = model.getRelationDAO();
		communityDAO = model.getCommunityDAO();
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
		request.setAttribute("choice", "Unjoin!");
		User curUser = (User) request.getSession(false).getAttribute("user");
		if (curUser == null) {
			errors.add("You are not logged in");
			return "unjoin_comm.jsp";
		}
		
		if(!form.isPresent()) return "unjoin_comm.jsp";
		errors.addAll(form.getValidationErrors());
		if (errors.size() != 0) return "unjoin_comm.jsp";
		
		Community community;
		try {
			community = communityDAO.lookup(form.getName());
			if(community == null){
				errors.add("Unknown Community.");
				return "unjoin_comm.jsp";
			}
			
			if(!relationDAO.exist(curUser, community)){
				errors.add("You are not in this community: " + community.getName());
				request.setAttribute("choice", "Join!");
				request.setAttribute("commName", community.getName());

				return "unjoin_comm.jsp";
			}
				
			relationDAO.destroy(curUser, community);
			
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			errors.add(e.getMessage());
			return "unjoin_comm.jsp";
		}
		
		String success = "You are not in the community: " + community.getName() + " now";
		request.setAttribute("success",success);	
		request.setAttribute("choice", "Join!");
		request.setAttribute("commName", community.getName());

		
		return "unjoin_comm.jsp";
	}
	
	
	
}
