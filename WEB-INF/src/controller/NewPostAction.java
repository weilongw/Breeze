package controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.Model;
import model.PostDAO;
import model.TopicDAO;
import model.UserDAO;

import org.mybeans.dao.DAOException;
import org.mybeans.forms.FormBeanFactory;

import databean.Post;
import databean.Topic;
import databean.User;
import formbeans.NewPostForm;

public class NewPostAction extends Action{

	private FormBeanFactory<NewPostForm> formBeanFactory = FormBeanFactory.getInstance(NewPostForm.class, "<>\"");

	private PostDAO postDAO;
	private TopicDAO topicDAO;
	private UserDAO userDAO;
	
	public NewPostAction(Model model){
		postDAO = model.getPostDAO();
		topicDAO = model.getTopicDAO();
		userDAO = model.getUserDAO();
	}
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "newPost.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		// TODO Auto-generated method stub
		List<String> errors = prepareErrors(request);
		NewPostForm form = formBeanFactory.create(request);
		if (!form.isPresent()) return "browseCommunity.do";
		
		
		request.setAttribute("form", form);
		
		errors.addAll(form.getIntegerValidationErrors());
		if(errors.size() != 0)
			return "browseCommunity.do";
		
	
		
		try {
			Topic topic = topicDAO.lookup(form.getTopicIdAsInt());
			if(topic == null){
				errors.add("No such topic!");
				return "browseCommunity.do";
			}
			User curUser = (User)request.getSession().getAttribute("user");
			if (curUser == null) {
				errors.add("You are not logged in");
				return "viewTopic.do?topicId=" + topic.getId();
			}
			errors.addAll(form.getValidationErrors());
			
			if(errors.size() != 0)
				return "viewTopic.do?topicId=" + topic.getId();
			
			request.setAttribute("topic", topic);
			Post newPost = new Post();
			newPost.setContent(form.getPostContent());
			newPost.setPostDate(new Date());
			newPost.setPoster(curUser);
			newPost.setTopic(topic);
			postDAO.create(newPost);
			topicDAO.addReplyCount(topic.getId());
			Post[] list = postDAO.getPostsByTopic(topic);
			request.setAttribute("posts", list);
			String success = "Your post was created successfully";
			request.setAttribute("success", success);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			errors.add(e.getMessage());
			return "browseCommunity.do";
		}
		
		User curUser = (User)request.getSession().getAttribute("user");
		
		try {
			curUser = userDAO.lookup(curUser.getUserName());
			request.getSession().setAttribute("user", curUser);
		} catch (DAOException e) {
		}
		return "topic.jsp";
	}

}
