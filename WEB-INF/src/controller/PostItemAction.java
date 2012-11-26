package controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.mybeans.dao.DAOException;
import org.mybeans.forms.FormBeanFactory;

import databean.Community;
import databean.Item;
import databean.Post;
import databean.Topic;
import databean.User;

import formbeans.PostItemForm;

import model.CommunityDAO;
import model.ItemDAO;
import model.Model;
import model.PostDAO;
import model.TopicDAO;
import model.UserDAO;

public class PostItemAction extends Action{
	private FormBeanFactory<PostItemForm> formBeanFactory = FormBeanFactory.getInstance(PostItemForm.class,"<>\"");

	private UserDAO userDAO;
	private ItemDAO itemDAO;
    private	CommunityDAO communityDAO;
	private TopicDAO topicDAO;
	private PostDAO postDAO;
	
	public PostItemAction(Model model){
		userDAO = model.getUserDAO();
		itemDAO = model.getItemDAO();
		communityDAO = model.getCommunityDAO();
		topicDAO = model.getTopicDAO();
		postDAO = model.getPostDAO();
	}
	
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "postItem.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		// TODO Auto-generated method stub
		PostItemForm form = formBeanFactory.create(request);
		List<String> errors = prepareErrors(request);
        
		User curUser = (User) request.getSession(false).getAttribute("user");
		if (curUser == null) {
			errors.add("You are not logged in");
			return "browse.do";
		}
        if (!form.isPresent()) {
            return "post_item.jsp";
        }
        try {
        	curUser = userDAO.lookup(curUser.getUserName());
        	request.getSession().setAttribute("user", curUser);
        } catch (DAOException e) {
        	errors.add(e.getMessage());
        }
        
        if (form.getPostTypeAsInt() == Item.POST)
        	request.setAttribute("postForm",form);
        else 
        	request.setAttribute("requestForm", form);
        	
        
        
        errors.addAll(form.getValidationErrors());
        
        if (errors.size() != 0) {
            return "post_item.jsp";
        }
        
        
        if (form.getPostTypeAsInt() == Item.REQUEST && form.getForCredit() != null) {
        	int forCredit = Integer.parseInt(form.getCredit());
        	if (forCredit > curUser.getCredit()) {
        		errors.add("You have not enough credit for this request");
        		return "post_item.jsp";
        	}
        }
        Item newItem = new Item();
        newItem.setType(form.getPostTypeAsInt());
        newItem.setItemName(form.getItemName());
		newItem.setOwner(curUser);
		newItem.setItemDescription(form.getItemDescription());
		if(form.getForExchange() != null)
			newItem.setExchangeItemDescription(form.getExchangeDescription());
		if(form.getForCredit() != null)
			newItem.setCredit(Integer.parseInt(form.getCredit()));
		newItem.setPostDate(new Date());
		newItem.setStatus(Item.OPEN);
		newItem.setClickCount(0);
		newItem.setCategory(form.getItemCategoryAsInt());
		newItem.setRelatedMovie(form.getRelatedMovie());
		
		try {
			newItem.setId(itemDAO.create(newItem));
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			errors.add(e.getMessage());
        	return "post_item.jsp";
        }
		
		String success = "Your item was created successfully!";
		request.setAttribute("success",success);
		request.getSession().setAttribute("newItem", newItem);
	
		String relatedMovie = newItem.getRelatedMovie();
		if(relatedMovie.length() != 0){
			try {
				Community[] relatedCommunities = communityDAO.getCommunityByMovie(relatedMovie);
				for(Community c:relatedCommunities){
					Topic newTopic = new Topic();
					Post newPost = new Post();
					newTopic.setOwnerGroup(c);
					User poster = userDAO.lookup("Admin");
					newTopic.setPoster(poster);
					newTopic.setReplyCount(1);
					newTopic.setTitle("NOTICE: a related item was post.");
					newTopic.setViewCount(0);
					newTopic.setPostDate(new Date());
					newTopic = topicDAO.create(newTopic);
					String url = "<a href=showItems.do?itemId=" + newItem.getId() + ">link</a>";
					String content = "Item: " + newItem.getItemName() + " was just posted, click the " + url + " to view it.";
					newPost.setContent(content);
					newPost.setPoster(poster);
					newPost.setTopic(newTopic);
					newPost.setPostDate(new Date());
					postDAO.create(newPost);
					communityDAO.addTopic(c.getName());	
				}
			} catch (DAOException e) {
				// TODO Auto-generated catch block
				errors.add(e.getMessage());
			}
		}
		try {
			curUser = userDAO.lookup(curUser.getUserName());
			request.getSession().setAttribute("user", curUser);
		} catch (DAOException e) {
		}

		
    	return "upload_image.jsp";

	}

}
