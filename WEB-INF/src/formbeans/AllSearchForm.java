package formbeans;

import java.util.ArrayList;
import java.util.List;

public class AllSearchForm {
	
	private String key;
	private String options;
	private String category;
	private String page;
	
	public String getKey() { return key; }
	public String getOptions() { return options; }
	public String getCategory() { return category; }
	public String getPage() { return page; }
	
	public void setKey(String x) { key = x; }
	public void setOptions(String x) { options = x; }
	public void setCategory(String x) { category = x; }
	public void setPage(String x) { page = x; }
	
	public boolean isPresent() {
		if (key != null) return true;
		if (options != null) return true;
		if (category != null) return true;
		if (page != null) return true;
		return false;
	}
	
	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();
		if (key == null) errors.add("key is required");
		if (options == null) errors.add("item type is required");
		if (category == null) errors.add("Category is required");
		if (page == null) errors.add("page index is required");
		if (errors.size() != 0) return errors;
		try {
			int opt = Integer.parseInt(options);
			if (opt < 0 || opt > 2) 
				errors.add("Invalid options");
		} catch (NumberFormatException e) {
			errors.add("Invalid options");
		}
		try {
			int cat = Integer.parseInt(category);
			if (cat < 0 || cat > 3) {
				errors.add("Invalid category");
			}
		} catch (NumberFormatException e) {
			errors.add("Invalid category");
		}
		try {
			Integer.parseInt(page);
		} catch (NumberFormatException e) {
			errors.add("Invalid page index");
		}
		return errors;
	}
}
