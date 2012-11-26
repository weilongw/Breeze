package controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.ExchangeDAO;
import model.ItemDAO;
import model.Model;
import model.UserDAO;

import org.mybeans.dao.DAOException;
import org.mybeans.forms.FormBeanFactory;

import databean.Exchange;
import databean.Item;
import databean.User;
import formbeans.ShowItemForm;

public class ShowItemPageAction extends Action{

	private FormBeanFactory<ShowItemForm> formBeanFactory = FormBeanFactory.getInstance(ShowItemForm.class,"<>\"");
	
	private ItemDAO itemDAO;
	private ExchangeDAO exchangeDAO;
	private UserDAO userDAO;
	
	public ShowItemPageAction(Model model){
		itemDAO = model.getItemDAO();
		exchangeDAO = model.getExchangeDAO();
		userDAO = model.getUserDAO();
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
        
		if (user != null) {
			try {
				user = userDAO.lookup(user.getUserName());
				request.getSession().setAttribute("user", user);
			} catch (DAOException e) {
			}
		}
        
        errors.addAll(form.getValidationErrors());
        
        if (errors.size() != 0) {
            return "browse.do";
        }
        
        int itemId = form.getItemIdAsInt();
        
        try {
        	Item item = itemDAO.getItemById(itemId);
        	if (item == null) {
        		System.out.println("haha");
        		System.out.println(itemId);
        		errors.add("Invalid itemID");
        		return "browse.do";
        	}
        	else if(item.getStatus() == 0){	// open
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
        	else if(user != null){
        		int result = exchangeDAO.findExchangeResult(item);
        		if(result == 1){	// no one answer
        			if(item.getOwner().getUserName().equals(user.getUserName())){
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
        	    		request.setAttribute("isOwner",1);
        	    		return "item_page.jsp";
        			}      				
        		}
        		else{
        			if(item.getOwner().getUserName().equals(user.getUserName())){
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
        	    		request.setAttribute("isOwner",1);
        	    		return "item_page.jsp";
        			}     
        			Exchange xchg = exchangeDAO.findSuccessExchange(item);
        			if(xchg.getResponder().getUserName().equals(user.getUserName())){
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
        	    		request.setAttribute("isOwner",0);
        	    		return "item_page.jsp";
        			}      				
        		}
        	}
		} catch (DAOException e) {
			
			errors.add(e.getMessage());
			return "browse.do";
		}
        
		errors.add("Invalid operation.");
		return "browse.do";

        	
	}

}
