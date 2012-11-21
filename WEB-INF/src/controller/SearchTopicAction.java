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

import formbeans.SearchTopicForm;

public class SearchTopicAction extends Action{

	private FormBeanFactory<SearchTopicForm> formBeanFactory = FormBeanFactory.getInstance(SearchTopicForm.class,"<>\"");
	
	private TopicDAO topicDAO;
	private CommunityDAO communityDAO;
	
	public SearchTopicAction(Model model){
		topicDAO = model.getTopicDAO();
		communityDAO = model.getCommunityDAO();
	}
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "searchTopic.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		// TODO Auto-generated method stub
		SearchTopicForm form = formBeanFactory.create(request);
		List<String> errors = prepareErrors(request);
		if (!form.isPresend()) return "browseCommunity.do";
		errors.addAll(form.getValidationErrors());
		if (errors.size() != 0) return "browseCommunity.do";
		
		Community community;
		try {
			community = communityDAO.lookup(form.getCommName());
			if(community == null){
				errors.add("Invalid community name");
				return "browseCommunity.do";
			}
						
			String key = form.getKey();
			if(key == null || key.trim().length() == 0)
				return "viewCommunity.do?name=" + community.getName();
			
			request.setAttribute("sForm", form);
			
			Topic[] list = topicDAO.searchInCommunity(key, community);
			
			if(list.length == 0)
				errors.add("No result found");
			request.setAttribute("search", list);
			request.setAttribute("comm", community);
			
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			errors.add(e.getMessage());
			return "browseCommunity.do";
		}
		


		return "show_community.jsp";
	}

}
