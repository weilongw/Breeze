package controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.CommunityDAO;
import model.Model;
import model.PostDAO;
import model.RelationDAO;
import model.TopicDAO;
import model.UserDAO;

import org.mybeans.dao.DAOException;
import org.mybeans.forms.FormBeanFactory;

import databean.Community;
import databean.Post;
import databean.Topic;
import databean.User;
import formbeans.NewTopicForm;

public class NewTopicAction extends Action {

	private FormBeanFactory<NewTopicForm> formBeanFactory = FormBeanFactory.getInstance(NewTopicForm.class, "<>\"");
	
	private CommunityDAO communityDAO;
	private TopicDAO topicDAO;
	private PostDAO postDAO;
	private RelationDAO relationDAO;
	private UserDAO userDAO;
	
	public NewTopicAction (Model model) {
		communityDAO = model.getCommunityDAO();
		topicDAO = model.getTopicDAO();
		postDAO = model.getPostDAO();
		relationDAO = model.getRelationDAO();
		userDAO = model.getUserDAO();
	}
	@Override
	public String getName() {
		return "newTopic.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		List<String> errors = prepareErrors(request);
		NewTopicForm form = formBeanFactory.create(request);
		if (!form.isPresent()) return "browseCommunity.do";
		
		if (form.getCommunityName() == null) {
			errors.add("Community Name is required");
			return "browseCommunity.do";
		}
		Community comm = null;
		try {
			comm = communityDAO.lookup(form.getCommunityName());
		} catch(DAOException e) {
			errors.add(e.getMessage());
			return "browseCommunity.do";
		}
		if (comm == null) {
			errors.add("Community named " + form.getCommunityName() + " does not exist");
			return "browseCommunity.do";
		}
		request.setAttribute("form", form);
		User curUser = (User)request.getSession().getAttribute("user");
		if (curUser == null) {
			errors.add("You are not logged in");
			return "viewCommunity.do?name=" + comm.getName();
		}
		errors.addAll(form.getValidationErrors());
		if (errors.size() != 0) return "viewCommunity.do?name=" + comm.getName();
		try {
			if (!relationDAO.exist(curUser, comm)) {
				errors.add("You are not a member of this community");
				
				return "viewCommunity.do?name=" + comm.getName();
			}
		} catch (DAOException e) {
			errors.add(e.getMessage());
			return "viewCommunity.do?name="+comm.getName();
		}
		Topic newTopic = new Topic();
		Post newPost = new Post();
		newTopic.setOwnerGroup(comm);
		newTopic.setPoster(curUser);
		newTopic.setReplyCount(1);
		newTopic.setTitle(form.getTitle());
		newTopic.setViewCount(0);
		newTopic.setPostDate(new Date());
		try {
			newTopic = topicDAO.create(newTopic);
			newPost.setContent(form.getContent());
			newPost.setPoster(curUser);
			newPost.setTopic(newTopic);
			newPost.setPostDate(new Date());
			postDAO.create(newPost);
			communityDAO.addTopic(comm.getName());
		} catch (DAOException e) {
			errors.add(e.getMessage());
			return "viewCommunity.do?name="+comm.getName();
		}
		request.setAttribute("success", "A new topic is posted!");
		request.setAttribute("form", null);
		
		

		try {
			curUser = userDAO.lookup(curUser.getUserName());
			request.getSession().setAttribute("user", curUser);
		} catch (DAOException e) {
		}

		return "viewCommunity.do?name=" + comm.getName();
		
	}


}
