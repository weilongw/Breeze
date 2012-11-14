package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.ItemDAO;
import model.Model;

import org.mybeans.dao.DAOException;
import org.mybeans.forms.FormBeanFactory;

import formbeans.CategoryForm;

public class ShowCategoryAction extends Action {

	private FormBeanFactory<CategoryForm> formBeanFactory = FormBeanFactory.getInstance(CategoryForm.class, "<>\"");
	private ItemDAO itemDAO;
	
	public ShowCategoryAction (Model model) {
		itemDAO = model.getItemDAO();
	}
	@Override
	public String getName() {
		return "showCategory.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		CategoryForm form = formBeanFactory.create(request);
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);
		if (!form.isPresent()) return "browse.do";
		errors.addAll(form.getValidationErrors());
		if (errors.size() != 0) return "index.jsp";
		
		try {
			request.setAttribute("allItemList", itemDAO.getItemsByCategory(form.getCategory()));

		} catch (DAOException e) {
			// TODO Auto-generated catch block
			errors.add(e.getMessage());
        	return "index.jsp";
		}
    	
		return "index.jsp";
		
	}
	
	

	

}
