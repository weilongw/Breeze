package controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.ItemDAO;
import model.Model;

import org.mybeans.dao.DAOException;
import org.mybeans.forms.FormBeanFactory;

import databean.Item;
import formbeans.ShowItemForm;

public class ShowMovieAction extends Action {

	private FormBeanFactory<ShowItemForm> formBeanFactory = FormBeanFactory.getInstance(ShowItemForm.class, "<>\"");
	
	private ItemDAO itemDAO;
	
	public ShowMovieAction(Model model) {
		itemDAO = model.getItemDAO();
	}
	@Override
	public String getName() {
		return "showMovie.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		ShowItemForm form = formBeanFactory.create(request);
		List<String> errors = prepareErrors(request);
		
		if (!form.isPresent()) {
			return "browse.do";
		}
		errors.addAll(form.getValidationErrors());
		if (errors.size() != 0) return "browse.do";
		Item item = null;
		try {
			item = itemDAO.getItemById(form.getItemIdAsInt());
			if (item == null) {
				errors.add("Invalid item");
				return "browse.do";
			}
		} catch (DAOException e) {
			errors.add(e.getMessage());
			return "browse.do";
		}
		request.setAttribute("item", item);
		return "about_movie.jsp";
	}


}
