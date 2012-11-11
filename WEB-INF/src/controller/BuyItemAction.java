package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.mybeans.dao.DAOException;
import org.mybeans.forms.FormBeanFactory;

import databean.Item;
import databean.User;

import formbeans.BuyItemForm;

import model.ItemDAO;
import model.Model;

public class BuyItemAction extends Action{
	
	private FormBeanFactory<BuyItemForm> formBeanFactory = FormBeanFactory.getInstance(BuyItemForm.class, "<>\"");
	
	ItemDAO itemDAO;
	
	public BuyItemAction(Model model){
		itemDAO = model.getItemDAO();
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
		try {
			if(itemId > itemDAO.getAllItems().length)
				return "index.jsp";
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int buyType = form.getBuyTypeAsInt();
        User curUser = (User) request.getSession(false).getAttribute("user");

		if(buyType == 0){
			try {
				Item item = itemDAO.getItemById(itemId);
				int credit = item.getCredit();
				if(curUser.getCredit() - credit < 0){
					errors.add("Not enough credits.");
					request.setAttribute("posted", item);
				}
				curUser.setCredit(curUser.getCredit() - credit);
				User owner = item.getOwner();
				owner.setCredit(owner.getCredit() + credit);
				item.setStatus(1);// set status as closed
			} catch (DAOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
		else{
			try {
				Item item = itemDAO.getItemById(itemId);
				String url = "http://localhost:8080/Breeze/complete.do?buyType=" + buyType + 
						"&buyerName=" + curUser.getUserName() + "&itemId=" + item.getId();
			} catch (DAOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
	
		
		return null;
	}

}
