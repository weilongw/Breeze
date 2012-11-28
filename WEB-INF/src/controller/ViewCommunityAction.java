package controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.CommunityDAO;
import model.Model;
import model.RelationDAO;
import model.TopicDAO;
import model.UserDAO;

import org.mybeans.dao.DAOException;
import org.mybeans.forms.FormBeanFactory;

import databean.Community;
import databean.Topic;
import databean.User;
import formbeans.ViewCommunityForm;

public class ViewCommunityAction extends Action {

	private FormBeanFactory<ViewCommunityForm> formBeanFactory = FormBeanFactory.getInstance(ViewCommunityForm.class, "<>\"");
	
	private CommunityDAO communityDAO;
	private TopicDAO topicDAO;
	private RelationDAO relationDAO;
	private UserDAO userDAO;
	
	public ViewCommunityAction (Model model) {
		communityDAO = model.getCommunityDAO();
		topicDAO = model.getTopicDAO();
		relationDAO = model.getRelationDAO();
		userDAO = model.getUserDAO();
	}
	
	@Override
	public String getName() {
		return "viewCommunity.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		List<String> errors = prepareErrors(request);
		ViewCommunityForm form = formBeanFactory.create(request);
		if(!form.isPresent()) return "browseCommunity.do";
		
		Community comm = null;
		try {
			comm = communityDAO.lookup(form.getName());
		} catch (DAOException e) {
			errors.add(e.getMessage());
			return "browseCommunity.do";
		}
		System.out.println("haha" + form.getName());
		if (comm == null) {
			errors.add("Community named " + form.getName() + " not found");
			return "browseCommunity.do";
		}
		Topic[] topics = null;
		try {
			topics = topicDAO.getTopicsByCommunity(comm);
		} catch (DAOException e) {
			errors.add(e.getMessage());
			return "browseCommunity.do";
		}
		request.setAttribute("comm", comm);
		request.setAttribute("topics", topics);

		User curUser = (User) request.getSession(false).getAttribute("user");
	
		if (curUser != null) {
			try {
				curUser = userDAO.lookup(curUser.getUserName());
				request.getSession().setAttribute("user", curUser);
			} catch (DAOException e) {
			}
		}
		
		boolean joining;
		try {
			joining = relationDAO.exist(curUser, comm);
			if(joining)
				request.setAttribute("joining", 1);
			else
				request.setAttribute("joining", 0);
			return "show_community.jsp";
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			errors.add(e.getMessage());
			return "browseCommunity.do";
		}
		
		
	}
	

}
