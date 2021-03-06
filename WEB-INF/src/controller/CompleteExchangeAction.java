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
import formbeans.ExchangeForm;

public class CompleteExchangeAction extends Action {

	private FormBeanFactory<ExchangeForm> formBeanFactory = FormBeanFactory.getInstance(ExchangeForm.class, "<>\"");
	
	private ItemDAO itemDAO;
	private UserDAO userDAO;
	private MessageDAO messageDAO;
	private ExchangeDAO exchangeDAO;
	
	public CompleteExchangeAction(Model model) {
		itemDAO = model.getItemDAO();
		userDAO = model.getUserDAO();
		messageDAO = model.getMessageDAO();
		exchangeDAO = model.getExchangeDAO();
	}
	
	@Override
	public String getName() {
		return "complete.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		ExchangeForm form = formBeanFactory.create(request);
		List<String> errors = prepareErrors(request);
		User curUser = (User)request.getSession().getAttribute("user");
		if (curUser == null) {
			errors.add("You are not logged in");
			return "browse.do";
		}
		if (!form.isPresent()) {
			return "browse.do";
		}
		errors.addAll(form.getValidationErrors());
		if (errors.size() != 0) return "browse.do";
		
		Exchange xchg = null;
		User admin = null;
		try {
			xchg = exchangeDAO.lookup(form.getExchangeIdAsInt());
			admin = userDAO.lookup("Admin");
			curUser = userDAO.lookup(curUser.getUserName());
		} catch (DAOException e) {
			errors.add(e.getMessage());
			return "browse.do";
		}
		request.getSession().setAttribute("user", curUser);
		if (xchg == null) {
			errors.add("Transaction not found");
			return "browse.do";
		}
		Item item = null;
		try {
			item = itemDAO.getItemById(xchg.getItem().getId());
		} catch(DAOException e) {
			errors.add(e.getMessage());
			return "browse.do";
		}
		if (!xchg.getPoster().getUserName().equals(curUser.getUserName())) {
			errors.add("You are not the owner of the item");
			return "showMyItems.do";
		}
		if (xchg.getStatus() == Exchange.CLOSED || item.getStatus() == Item.CLOSED) {
			errors.add("Item already closed");
			return "showMyItems.do";
		}
		try {
			Transaction.begin();
			if (xchg.getRespondType() == Exchange.ANSWER_REQUEST_FOR_CREDIT) {
				userDAO.transferCredit(item.getCredit(), xchg.getPoster(), xchg.getResponder());
				curUser.setCredit(curUser.getCredit() - item.getCredit());
			}
			String url1 = "<a href=&quot;showItems.do?itemId=" + item.getId() +"&quot;>item</a>";
			itemDAO.closeItem(item.getId());
			exchangeDAO.setSuccessTransaction(xchg.getId());
			messageDAO.send(admin, curUser, "Transaction on (" + item.getItemName() + ") complete", 
							"You have accepted the request from (" + "<a href=&quot;redirectSend.do?receiver=" +xchg.getResponder().getUserName()
							+ "&quot;>" + xchg.getResponder().getUserName() + "</a>). Your " + url1 + " is now closed.");
			userDAO.updateNewMsgCount(curUser.getUserName(), 1);
			messageDAO.send(admin, xchg.getResponder(), "Transaction on (" + item.getItemName() + ") complete", 
							"The <a href=&quot;redirectSend.do?receiver=" + item.getOwner().getUserName() + "&quot;>owner</a> of " + url1 + " has accepted your request");
			
			userDAO.updateNewMsgCount(xchg.getResponder().getUserName(), 1);
			Exchange[] pending = exchangeDAO.findItemPendingTransactions(item);
			for (Exchange each : pending) {
				messageDAO.send(admin, each.getResponder(), "Transaction on (" + item.getItemName() + ") dismissed",
								"The item you have reponded to is now closed");
				userDAO.updateNewMsgCount(each.getResponder().getUserName(), 1);
			}
			exchangeDAO.closeItemTransaction(item);
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
		request.setAttribute("success", "Congrats, your transaction has been made");
		return "showMyItems.do";
	}	
	
	



}
