package formbeans;

import java.util.ArrayList;
import java.util.List;

public class AjaxCheckForm {

	private String value;
	private String field;
	
	public String getValue() { return value; }
	public String getField() { return field; }
	
	public void setValue(String x) { value = x; }
	public void setField(String x) { field = x; }
	
	public boolean isPresent() {
		if (value != null) return true;
		if (field != null) return true;
		return false;
	}
	
	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();
		if(value == null) 
			errors.add("value is required");
		if (field == null)
			errors.add("field id required");
		
		if (errors.size() != 0) return errors;
		
		try {
			Integer.parseInt(field);
		} catch (NumberFormatException e) {
			errors.add("Invalid field input");
		}
		
		return errors;
		
	}

}
