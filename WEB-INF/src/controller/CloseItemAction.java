package controller;


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
import databean.User;
import formbeans.ShowItemForm;

public class CloseItemAction extends Action {

	private FormBeanFactory<ShowItemForm> formBeanFactory = FormBeanFactory.getInstance(ShowItemForm.class, "<>\"");
	
	private ItemDAO itemDAO;
	private UserDAO userDAO;
	private ExchangeDAO exchangeDAO;
	private MessageDAO messageDAO;
	
	public CloseItemAction(Model model) {
		itemDAO = model.getItemDAO();
		userDAO = model.getUserDAO();
		exchangeDAO = model.getExchangeDAO();
		messageDAO = model.getMessageDAO();
	}
	@Override
	public String getName() {
		return "closeItem.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		ShowItemForm form = formBeanFactory.create(request);
		List<String> errors = prepareErrors(request);
		User curUser = (User)request.getSession().getAttribute("user");
		if (curUser == null) {
			errors.add("You are not logged in");
			return "browse.do";
		}
		if (!form.isPresent()) {
			return "showMyItems.do";
		}
		errors.addAll(form.getValidationErrors());
		if (errors.size() != 0) return "showMyItems.do";
		
		Item item = null;
		try {
			item = itemDAO.getItemById(form.getItemIdAsInt());
		} catch (DAOException e) {
			errors.add(e.getMessage());
			return "showMyItems.do";
		}
		if (item == null) {
			errors.add("Invalid item ID");
			return "showMyItems.do";
		}
		if (!item.getOwner().getUserName().equals(curUser.getUserName())) {
			errors.add("You are not the owner of this product");
			return "browse.do";
		}
		if (item.getStatus() == Item.CLOSED) {
			errors.add("Item is already closed");
			return "browse.do";
		}
		User admin = null;
		try {
			admin = userDAO.lookup("Admin");
		} catch (DAOException e) {
			errors.add(e.getMessage());
			return "browse.do";
		}
		try {
			itemDAO.closeItem(item.getId());
			Exchange[] pending = exchangeDAO.findItemPendingTransactions(item);
			messageDAO.send(admin, item.getOwner(), "Item closed", 
										"You have just canceled your item" +
										item.getItemName());
			for (Exchange xchg : pending) {
				messageDAO.send(admin, xchg.getResponder(), 
								"Item close notification", 
								"The item you have responded to(" + item.getItemName() +
								") is no longer available");
			}
			exchangeDAO.closeItemTransaction(item);
			exchangeDAO.createCancelTransaction(item);
		} catch(DAOException e) {
			errors.add(e.getMessage());
			return "showMyItems.do";
		}
		request.setAttribute("success", "Your item has just been cancelled");
		
		return "showMyItems.do";
		
	}

}
