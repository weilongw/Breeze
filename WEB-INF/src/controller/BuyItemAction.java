package controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.ExchangeDAO;
import model.ItemDAO;
import model.MessageDAO;
import model.Model;
import model.UserDAO;

import org.mybeans.dao.DAOException;
import org.mybeans.forms.FormBeanFactory;

import databean.Exchange;
import databean.Item;
import databean.Message;
import databean.User;
import formbeans.BuyItemForm;

public class BuyItemAction extends Action{
	
	private FormBeanFactory<BuyItemForm> formBeanFactory = FormBeanFactory.getInstance(BuyItemForm.class, "<>\"");
	
	ItemDAO itemDAO;
	MessageDAO messageDAO;
	UserDAO userDAO;
	ExchangeDAO exchangeDAO;
	
	public BuyItemAction(Model model){
		itemDAO = model.getItemDAO();
		messageDAO = model.getMessageDAO();
		userDAO = model.getUserDAO();
		exchangeDAO = model.getExchangeDAO();
	}
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "buyItem.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		// TODO Auto-generated method stub
		BuyItemForm form = formBeanFactory.create(request);
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);
		
		errors.addAll(form.getValidationErrors());
		if (errors.size() != 0) return "index.jsp";
		
		int itemId = form.getItemIdAsInt();
		int buyType = form.getBuyTypeAsInt();
        User curUser = (User) request.getSession(false).getAttribute("user");
        
        if (curUser == null) {
        	errors.add("You are not logged in");
        	return "index.jsp";
        }
        Item item = null;
        User admin = null;
        try {
        	item = itemDAO.getItemById(itemId);
        	admin = userDAO.lookup("Admin");
        } catch (DAOException e) {
        	errors.add(e.getMessage());
        	return "index.jsp";
        }
        
        if(item == null || admin == null || item.getStatus() == Item.CLOSED) {
        	errors.add("Item not available");
        	return "index.jsp";
        }
     
        
		if(buyType == 1 && item.getType() == Item.POST && item.getCredit() != -1){
			try {
				request.setAttribute("posted", item);
				int credit = item.getCredit();
				if(curUser.getCredit() - credit < 0){
					errors.add("Not enough credits.");			
					return "item_page.jsp";
				}
				//userDAO.setCredit(curUser.getCredit() - credit, curUser.getUserName());
				User owner = item.getOwner();
				//userDAO.setCredit(owner.getCredit() + credit, owner.getUserName());
				itemDAO.closeItem(itemId);
				curUser.setCredit(curUser.getCredit() - credit);
				userDAO.transferCredit(credit, curUser, owner);
				exchangeDAO.closeItemTransaction(item);
				messageDAO.send(admin, owner, "Your item has been sold", 
								"Your item: " + item.getItemName() +" has been bought by "
								+ curUser.getUserName() + " and transaction now is closed.");
				messageDAO.send(admin, curUser, "You won the item", 
								"You have just won the item " + item.getItemName()
								+ ". Congratulations.");
				request.setAttribute("success", "Transaction was successfully made. Your " +
						"remaining credits are " + curUser.getCredit());
				
			} catch (DAOException e) {
				errors.add(e.getMessage());
				return "item_page.jsp";
			}			
		}
		else if ((buyType == 2 && item.getType() == Item.POST && item.getExchangeItemDescription() != null) ||
				 (buyType == 3 && item.getType() == Item.REQUEST && item.getCredit() != -1) ||
				 (buyType == 4 && item.getType() == Item.REQUEST && item.getExchangeItemDescription() != null)) {
			try {
				if(item.getType() == Item.POST)
					request.setAttribute("posted", item);
				else 
					request.setAttribute("requested", item);
				User owner = item.getOwner();
				String url = "<a href=&quot;http://localhost:8080/Breeze/complete.do?buyType=" + buyType + 
						"&buyerName=" + curUser.getUserName() + "&itemId=" + item.getId() +"&quot;>link</a>";
				String[] buyTypeName = {"exchange with items", "exchange for credits", "exchange with items"};
				
				String content = "Your item (" + item.getItemName() + ") has been responded " +
						"by " + curUser.getUserName() +  
						", who agreed to " + buyTypeName[buyType - 2] + ". Click this " + url +
								" if you want to make a transaction with him.";
				System.out.println(content.length());
				
				
				String content2 = "The item: " + item.getItemName() + " you requested has been sent " +
						"to the user: " + item.getOwner().getUserName() + ", email: " + item.getOwner().getEmail() + 
						". You agreed to " + buyTypeName[buyType - 2] + ". You will get automatically message notification" +
								" if the item owner makes the transaction with you.";
				exchangeDAO.openPendingTransaction(item, curUser, buyType);
				messageDAO.send(admin, owner, "Your item has been responded", content);
				messageDAO.send(admin, curUser, "Your request is accepted", content2);
				
				
				request.setAttribute("success", "Your request has been sent.");
			} catch (DAOException e) {
				
			}			
		}
	
		return "showMessage.do";
	}

}
