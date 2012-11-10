package controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.mybeans.dao.DAOException;
import org.mybeans.forms.FormBeanFactory;

import databean.Item;
import databean.User;

import formbeans.PostItemForm;

import model.ItemDAO;
import model.Model;
import model.UserDAO;

public class PostItemAction extends Action{
	private FormBeanFactory<PostItemForm> formBeanFactory = FormBeanFactory.getInstance(PostItemForm.class,"<>\"");

	UserDAO userDAO;
	ItemDAO itemDAO;
	
	public PostItemAction(Model model){
		userDAO = model.getUserDAO();
		itemDAO = model.getItemDAO();
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
		List<String> errors = new ArrayList<String>();
		
		request.setAttribute("errors",errors);
        
        if (!form.isPresent()) {
            return "post_item.jsp";
        }
        
        if (form.getPostTypeAsInt() == Item.POST)
        	request.setAttribute("postForm",form);
        else
        	request.setAttribute("requestForm", form);
        
        errors.addAll(form.getValidationErrors());
        
        if (errors.size() != 0) {
            return "post_item.jsp";
        }
        
        User curUser = (User) request.getSession(false).getAttribute("user");
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
		newItem.setStatus(0);
		newItem.setClickCount(0);
		newItem.setCategory(form.getItemCategory());
		
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
		

    	return "upload_image.jsp";

		//return "showItem.do";
	}

}
