package formbeans;

import java.util.ArrayList;
import java.util.List;

public class SearchForm {
	private String key;
	private String options;// Will it be better if we can search it by select the range in searching, like in item name, item description, user name
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getOptions() {
		return options;
	}
	public void setOptions(String options) {
		this.options = options;
	}
	
	public int getOptionsAsInt() {
		return Integer.parseInt(options);
	}
	
	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();
		
		if (key == null) {
			errors.add("key is required");
		}
		 
		if (options == null || options.length() == 0){
			errors.add("Option is required");
			try{
				int option = Integer.parseInt(options);
				if(option > 2 && option < 0){
					errors.add("Invalid option choice for search.");
				}
			}catch (NumberFormatException e) {
				errors.add("Invalid option choice for search.");
			}
			
		}
		
		return errors;
	}
	
}
