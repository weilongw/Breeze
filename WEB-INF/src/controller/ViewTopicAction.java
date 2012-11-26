package controller;

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
import formbeans.ViewTopicForm;

public class ViewTopicAction extends Action{

	private FormBeanFactory<ViewTopicForm> formBeanFactory = FormBeanFactory.getInstance(ViewTopicForm.class, "<>\"");

	private TopicDAO topicDAO;
	private PostDAO postDAO;
	private UserDAO userDAO;
	
	public ViewTopicAction(Model model) {
		topicDAO = model.getTopicDAO();
		postDAO = model.getPostDAO();
		userDAO = model.getUserDAO();
	}
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "viewTopic.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		// TODO Auto-generated method stub
		List<String> errors = prepareErrors(request);
		ViewTopicForm form = formBeanFactory.create(request);
		
		if(!form.isPresent()) return "browseCommunity.do";
		
		try {
			try {
				Integer.parseInt(form.getTopicId());
				
				Topic topic = topicDAO.lookup(form.getTopicIdAsInt());
				if(topic == null){
					errors.add("No such topic!");
					return "browseCommunity.do";
				}
					
				request.setAttribute("topic", topic);
				Post[] list = postDAO.getPostsByTopic(topic);
				request.setAttribute("posts", list);
			}catch (NumberFormatException e) {
				errors.add("Invalid topic.");
				return "browseCommunity.do";
			}
			
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			errors.add(e.getMessage());
			return "browseCommunity.do";
		}
		User curUser = (User)request.getSession().getAttribute("user");
		if (curUser != null) {
			try {
				curUser = userDAO.lookup(curUser.getUserName());
				request.getSession().setAttribute("user", curUser);
			} catch (DAOException e) {
			}
		}
		
		
		return "topic.jsp";
	}

}
