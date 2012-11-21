package controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.CommunityDAO;
import model.Model;
import model.TopicDAO;

import org.mybeans.dao.DAOException;
import org.mybeans.forms.FormBeanFactory;

import databean.Community;
import databean.Topic;
import formbeans.ViewCommunityForm;

public class ViewCommunityAction extends Action {

	private FormBeanFactory<ViewCommunityForm> formBeanFactory = FormBeanFactory.getInstance(ViewCommunityForm.class, "<>\"");
	
	private CommunityDAO communityDAO;
	private TopicDAO topicDAO;
	
	public ViewCommunityAction (Model model) {
		communityDAO = model.getCommunityDAO();
		topicDAO = model.getTopicDAO();
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
		return "show_community.jsp";
	}
	

}
