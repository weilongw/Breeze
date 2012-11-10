package controller;

import javax.servlet.http.HttpServletRequest;

import org.mybeans.dao.DAOException;

import databean.Item;
import databean.User;

import model.ItemDAO;
import model.Model;

public class ShowMyItemsAction extends Action{

	ItemDAO itemDAO;
	
	public ShowMyItemsAction(Model model){
		itemDAO = model.getItemDAO();
	}
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "showMyItems.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		// TODO Auto-generated method stub
        User curUser = (User) request.getSession(false).getAttribute("user");
        try {
			Item[] myPostedItems = itemDAO.getMyPostedItems(curUser);
			request.setAttribute("myPostedItems",myPostedItems);
			Item[] myRequestedItems = itemDAO.getMyRequestedItems(curUser);
			request.setAttribute("myRequestedItems",myRequestedItems);

		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
		return "my_items.jsp";
	}

}
