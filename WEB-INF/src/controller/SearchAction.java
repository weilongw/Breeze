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
import formbeans.AllSearchForm;

public class SearchAction extends Action{

	private FormBeanFactory<AllSearchForm> formBeanFactory = FormBeanFactory.getInstance(AllSearchForm.class,"<>\"");

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
		AllSearchForm form = formBeanFactory.create(request);
		List<String> errors = prepareErrors(request);
		if (!form.isPresent()) return "browse.do";
        Item[] allItems = null;
        
        errors.addAll(form.getValidationErrors());
        
        if (errors.size() != 0) {
            return "browse.do";
        }
        
        try {
        	allItems = itemDAO.getActiveItem();
		} catch (DAOException e) {
			errors.add(e.getMessage());
			return "browse.do";
		}
        int option = Integer.parseInt(form.getOptions());
        int category = Integer.parseInt(form.getCategory());
        
    	if (option != 0) {
    		allItems = filterOption(allItems, option);
    	}
    	if (category != 0) {
    		allItems = filterCategory(allItems, category);
    	}
    	
    	if (form.getKey().trim().length() != 0) {
    		List<Item> searchResultList = new ArrayList<Item>();
    		for(Item item:allItems){
    			if(item.getItemName().toLowerCase().contains(form.getKey().toLowerCase()) || 
    					item.getItemDescription().toLowerCase().contains(form.getKey().toLowerCase()))
    				searchResultList.add(item);
    		}
    		allItems = 
    				searchResultList.toArray(new Item[searchResultList.size()]);
    	}
    	Arrays.sort(allItems);
		request.setAttribute("allItemList", allItems);
		int page = Integer.parseInt(form.getPage());
		
		int itemPageCount = allItems.length == 0 ? 1 : ((allItems.length - 1) / PAGE_COUNT + 1);
		int itemPageStart = getStart(page, itemPageCount);
		int itemPageEnd = getEnd(page, itemPageCount);
		if (itemPageStart == 0 || itemPageEnd == 0) {
			errors.add("Invalid item page index");
			return "browse.do";
		}
		int itemStart = (page - 1) * PAGE_COUNT;
		int itemEnd = itemStart + PAGE_COUNT - 1;
		
		request.setAttribute("itemPageCount", itemPageCount);
		request.setAttribute("itemPageStart", itemPageStart);
		request.setAttribute("itemPageEnd", itemPageEnd);
		request.setAttribute("itemPageCurrent", page);
		request.setAttribute("itemStart", itemStart);
		request.setAttribute("itemEnd", itemEnd);
		request.setAttribute("form", form);
		
		return "index.jsp";
	}
	
	public Item[] filterOption(Item[] items, int option) {
		List<Item> result = new ArrayList<Item>();
		for (Item item : items) {
			if (item.getType() == option)
				result.add(item);
		}
		Item[] ret = result.toArray(new Item[result.size()]);
		return ret;
		
	}
	
	public Item[] filterCategory(Item[] items, int category) {
		List<Item> result = new ArrayList<Item>();
		for (Item item : items) {
			if (item.getCategory() == category)
				result.add(item);
		}
		Item[] ret = result.toArray(new Item[result.size()]);
		return ret;
		
	}
	
	public int getStart(int idx, int count) {
		if (idx < 1 || idx > count) return 0;
		if (count <= (2 * PAGE + 1)) return 1;
		
		if (idx - PAGE <= 1) return 1;
		else if ((idx - PAGE) <= (count - 2*PAGE)) return (idx - PAGE);
		else return (count - 2*PAGE);
	}
	
	public int getEnd(int idx, int count) {
		if (idx < 1 || idx > count) return 0;
		if (count <= (2*PAGE + 1)) return count;
		
		if ((idx + PAGE) <= (2 * PAGE + 1)) return (2 * PAGE + 1);
		else if (idx + PAGE <= count) return (idx + PAGE);
		else return count;
	}

}
