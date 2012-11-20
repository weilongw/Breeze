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
import formbeans.SearchForm;

public class CommunitySearchAction extends Action {

	private FormBeanFactory<SearchForm> formBeanFactory = FormBeanFactory.getInstance(SearchForm.class, "<>\"");
	
	private CommunityDAO communityDAO;
	private TopicDAO topicDAO;
	
	public CommunitySearchAction(Model model) {
		communityDAO = model.getCommunityDAO();
		topicDAO = model.getTopicDAO();
	}
			
	@Override
	public String getName() {
		return "communitySearch.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		SearchForm form = formBeanFactory.create(request);
		List<String> errors = prepareErrors(request);
		if (!form.isPresend()) return "browseCommunity.do";
		request.setAttribute("form", form);
		errors.addAll(form.getValidationErrors());
		if (errors.size() != 0) return "browseCommunity.do";
		int option = form.getOptionsAsInt();
		if (option == 2) {
			errors.add("Invalid search option");
			return "browseCommunity.do";
		}
		try {
			request.setAttribute("top_C", communityDAO.getPopular());
			if (option == 0) {
				Community[] comm = null;
				if(form.getKey().trim().length() == 0)
					comm = communityDAO.getAllCommunities();
				else
					comm = communityDAO.search(form.getKey());
				request.setAttribute("search_C", comm);
				request.setAttribute("title", "Community Search Results");
			}
			else {
				Topic[] topic = null;
				if (form.getKey().trim().length() == 0)
					topic = topicDAO.getAllTopics();
				else 
					topic = topicDAO.search(form.getKey());
				request.setAttribute("search_T", topic);
				request.setAttribute("title", "Topic Search Results");
			}
		} catch(DAOException e) {
			errors.add(e.getMessage());
		}
		return "community.jsp";
	}
	
	

}
