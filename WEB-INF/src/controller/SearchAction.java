package controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.ItemDAO;
import model.Model;

import org.mybeans.dao.DAOException;
import org.mybeans.forms.FormBeanFactory;

import databean.Item;

import formbeans.SearchForm;

public class SearchAction extends Action{

	private FormBeanFactory<SearchForm> formBeanFactory = FormBeanFactory.getInstance(SearchForm.class,"<>\"");

	private ItemDAO itemDAO;
	
	public SearchAction(Model model){
		itemDAO = model.getItemDAO();
	}
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "search.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		// TODO Auto-generated method stub
		SearchForm form = formBeanFactory.create(request);
		List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        Item[] allItems;
		try {
			allItems = itemDAO.getAllItems();
			request.setAttribute("search_result", allItems);
		} catch (DAOException e) {
			errors.add(e.getMessage());
			return "index.jsp";
		}
        
        
        errors.addAll(form.getValidationErrors());
        
        if (errors.size() != 0) {
            return "index.jsp";
        }
        
        try {
        	int option = form.getOptionsAsInt();
        	if (option == 0)
        		allItems = itemDAO.getActiveItem();
        	else 
        		allItems = itemDAO.getItemsByType(option);
        	if (form.getKey().trim().length() == 0) {
        		request.setAttribute("search_result", allItems);
        		return "index.jsp";
        	}
			List<Item> searchResultList = new ArrayList<Item>();
			for(Item item:allItems){
				if(item.getItemName().toLowerCase().contains(form.getKey().toLowerCase()) || 
						item.getItemDescription().toLowerCase().contains(form.getKey().toLowerCase()))
					searchResultList.add(item);
			}
			Item[] searchResultArray = 
					searchResultList.toArray(new Item[searchResultList.size()]);
			Arrays.sort(searchResultArray);
			request.setAttribute("search_result", searchResultArray);			
			
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			errors.add(e.getMessage());
		}
		
		return "index.jsp";
	}

}
