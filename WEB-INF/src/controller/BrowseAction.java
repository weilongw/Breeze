package controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.mybeans.dao.DAOException;

import model.ItemDAO;
import model.Model;

public class BrowseAction extends Action{
	
	private ItemDAO itemDAO;
	
	public BrowseAction(Model model){
		itemDAO = model.getItemDAO();
	}
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "browse.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		// TODO Auto-generated method stub
		//List<String> errors = new ArrayList<String>();
        //request.setAttribute("errors",errors);
		List<String> errors = prepareErrors(request);
		
		try {
			request.setAttribute("allItemList", itemDAO.getActiveItem());

		} catch (DAOException e) {
			
			errors.add(e.getMessage());
        	
		}
    	
		return "index.jsp";
	}

}
