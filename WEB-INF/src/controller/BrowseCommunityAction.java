package controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.mybeans.dao.DAOException;

import model.CommunityDAO;
import model.Model;
import model.TopicDAO;
import databean.Community;
import databean.Topic;

public class BrowseCommunityAction extends Action {

	private CommunityDAO communityDAO;
	private TopicDAO topicDAO;
	
	public BrowseCommunityAction (Model model) {
		communityDAO = model.getCommunityDAO();
		topicDAO = model.getTopicDAO();
	}
	
	@Override
	public String getName() {
		return "browseCommunity.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		List<String> errors = prepareErrors(request);
		Community[] top_community = null;
		Topic[] top_topic = null;
		try {
			top_community = communityDAO.getPopular();
			request.setAttribute("top_C", top_community);
			top_topic = topicDAO.getPopular();
			request.setAttribute("top_T", top_topic);
		} catch (DAOException e) {
			errors.add(e.getMessage());
		}
		request.setAttribute("title", "Hot topics today");
		return "community.jsp";
		
	}

}
