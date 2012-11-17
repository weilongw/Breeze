package controller;


import java.util.List;

import model.ItemDAO;
import model.Model;

import javax.servlet.http.HttpServletRequest;

import org.mybeans.dao.DAOException;
import org.mybeans.forms.FormBeanFactory;

import databean.Item;
import databean.User;

import formbeans.ShowItemForm;

public class ShowItemPageAction extends Action{

	private FormBeanFactory<ShowItemForm> formBeanFactory = FormBeanFactory.getInstance(ShowItemForm.class,"<>\"");
	
	ItemDAO itemDAO;
	
	public ShowItemPageAction(Model model){
		itemDAO = model.getItemDAO();
	}
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "showItems.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		// TODO Auto-generated method stub
		ShowItemForm form = formBeanFactory.create(request);
		List<String> errors = prepareErrors(request);
        
        User user =  (User) request.getSession(false).getAttribute("user");
        
        
        errors.addAll(form.getValidationErrors());
        
        if (errors.size() != 0) {
            return "browse.do";
        }
        
        int itemId = form.getItemIdAsInt();
    
        try {
        	Item item = itemDAO.getItemById(itemId);
        	if (item == null) {
        		errors.add("Invalid itemID");
        		return "browse.do";
        	}
        	else if(item.getStatus() == 0){
	        	Item requested;
	        	Item posted;
	        	if(item.getType() == 1){
	        		posted = item;
	    			request.setAttribute("posted",posted);
	        	}
	        	else{
	        		requested = item;
	        		request.setAttribute("requested",requested);
	        	}
	        	int isOwner = 0;
	        	if(user != null && item.getOwner().getUserName().equals(user.getUserName()))
	        		isOwner = 1;
	    		request.setAttribute("isOwner",isOwner);
	    		return "item_page.jsp";
        	}
        	else{
        		errors.add("Invalid operation.");
        		return "browse.do";
        	}
		} catch (DAOException e) {
			
			errors.add(e.getMessage());
			return "browse.do";
		}

        	
	}

}
