package controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.ExchangeDAO;
import model.ItemDAO;
import model.Model;
import model.UserDAO;

import org.mybeans.dao.DAOException;

import databean.Exchange;
import databean.Item;
import databean.User;

public class ShowMyItemsAction extends Action{

	private ItemDAO itemDAO;
	private ExchangeDAO exchangeDAO;
	private UserDAO userDAO;
	
	public ShowMyItemsAction(Model model){
		itemDAO = model.getItemDAO();
		exchangeDAO = model.getExchangeDAO();
		userDAO = model.getUserDAO();
	}
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "showMyItems.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		// TODO Auto-generated method stub
		List<String> errors = prepareErrors(request);
        User curUser = (User) request.getSession(false).getAttribute("user");
        if (curUser == null) {
        	errors.add("You are not logged in");
        	return "browse.do";
        }
        try {
			Item[] myPostedItems = itemDAO.getMyPostedItems(curUser);
			request.setAttribute("myPostedItems",myPostedItems);
			Item[] myRequestedItems = itemDAO.getMyRequestedItems(curUser);
			request.setAttribute("myRequestedItems",myRequestedItems);
			Item[] myPendingItems = exchangeDAO.getPendingItems(curUser);
			request.setAttribute("myPendingItems", myPendingItems);
			Exchange[] myFinishedItems = exchangeDAO.getMyFinished(curUser);
			//Exchange[] myWonItems = exchangeDAO.getMyWon(curUser);
			Exchange[] myClosedItems = exchangeDAO.getMyClosed(curUser);
			request.setAttribute("myFinishedItems", myFinishedItems);
			//request.setAttribute("myWonItems", myWonItems);
			request.setAttribute("myClosedItems", myClosedItems);
			HashMap<Integer, String> map = new HashMap<Integer,String>();
			map.put(Exchange.ANSWER_POST_WITH_CREDIT, "Answer post with credit");
			map.put(Exchange.ANSWER_POST_WITH_EXCHANGE, "Answer post with exchange");
			map.put(Exchange.ANSWER_REQUEST_FOR_CREDIT, "Answer request for credit");
			map.put(Exchange.ANSWER_REQUEST_FOR_EXCHANGE, "Answer request for exchange");
			request.setAttribute("map", map);
			HashMap<Integer, String> item_map = new HashMap<Integer,String>();
			item_map.put(Item.POST, "Post");
			item_map.put(Item.REQUEST, "Request");
			request.setAttribute("closedMap", item_map);
			
		} catch (DAOException e) {
			errors.add(e.getMessage());
			return "browse.do";
		}
        
       
		
		try {
			curUser = userDAO.lookup(curUser.getUserName());
			request.getSession().setAttribute("user", curUser);
		} catch (DAOException e) {
		}
	
		
		return "my_items.jsp";
	}

}
