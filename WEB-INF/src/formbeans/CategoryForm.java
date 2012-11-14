package formbeans;

import java.util.ArrayList;
import java.util.List;

public class CategoryForm {
	private String category;
	
	public String getCategory() { return category; }
	
	public void setCategory(String x) { category = x; }
	
	public boolean isPresent() {
		if (category != null) return true;
		return false;
	}
	
	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();
		if (category == null) {
			errors.add("Category is required");
			return errors;
		}
		if ( (!category.equals("Poster")) && (!category.equals("DVD"))
				&& (!category.equals("Prop")))
			errors.add("Invalid category");
		return errors;
	}
}
