package controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.ItemDAO;
import model.Model;

import org.mybeans.dao.DAOException;

import databean.Item;
import formbeans.AllSearchForm;

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
	public String perform(HttpServletRequest request)  {
		
		List<String> errors = prepareErrors(request);
	
		Item[] items = null;
		try {
			items = itemDAO.getActiveItem();
		} catch (DAOException e) {
			errors.add(e.getMessage());
			return "index.jsp";
		}
		
		request.setAttribute("allItemList", items);
		
		int itemPageCount = items.length == 0 ? 1 : ((items.length - 1) / PAGE_COUNT + 1);
		int itemPageEnd = itemPageCount > (2 * PAGE + 1) ? (2 * PAGE + 1) : itemPageCount;

		AllSearchForm form = new AllSearchForm();
		form.setCategory("0");
		form.setKey("");
		form.setOptions("0");
		form.setPage("1");
		
		request.setAttribute("itemPageCount", itemPageCount);
		request.setAttribute("itemPageStart", 1);
		request.setAttribute("itemPageEnd", itemPageEnd);
		request.setAttribute("itemPageCurrent", 1);
		request.setAttribute("itemStart", 0);
		request.setAttribute("itemEnd", PAGE_COUNT - 1);
		request.setAttribute("form", form);
		
		return "index.jsp";
	}

}
