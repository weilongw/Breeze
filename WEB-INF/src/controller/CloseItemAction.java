package controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.ExchangeDAO;
import model.ItemDAO;
import model.MessageDAO;
import model.Model;
import model.UserDAO;

import org.mybeans.dao.DAOException;
import org.mybeans.factory.RollbackException;
import org.mybeans.factory.Transaction;
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
			Transaction.begin();
			itemDAO.closeItem(item.getId());
			Exchange[] pending = exchangeDAO.findItemPendingTransactions(item);
			String url1 = "<a href=&quot;showItems.do?itemId=" + item.getId() +"&quot;>item</a>";
			messageDAO.send(admin, item.getOwner(), "Item (" + item.getItemName() + ") closed", 
										"You have just canceled your " + url1);
			userDAO.updateNewMsgCount(item.getOwner().getUserName(), 1);
			for (Exchange xchg : pending) {
				messageDAO.send(admin, xchg.getResponder(), 
								"Item (" + item.getItemName() + ") close notification", 
								"This item you have responded to is no longer available");
				userDAO.updateNewMsgCount(xchg.getResponder().getUserName(), 1);
			}
			exchangeDAO.closeItemTransaction(item);
			exchangeDAO.createCancelTransaction(item);
			Transaction.commit();
		} catch(RollbackException e) {
			errors.add(e.getMessage());
			return "showMyItems.do";
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}
		
		try {
			curUser = userDAO.lookup(curUser.getUserName());
		} catch(DAOException e) {
			errors.add(e.getMessage());
			return "showMyItems.do";
		}
		request.getSession().setAttribute("user", curUser);
		request.setAttribute("success", "Your item has just been cancelled");
		return "showMyItems.do";
		
	}

}
