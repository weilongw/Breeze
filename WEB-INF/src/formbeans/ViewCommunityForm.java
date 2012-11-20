package formbeans;

import java.util.ArrayList;
import java.util.List;

public class ViewCommunityForm {
	
	private String name;
	
	public String getName() { return name; }
	
	public void setName(String x) { name = x; }
	
	public boolean isPresent() {
		if (name != null) return true;
		return false;
	}

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();
		if (name == null || name.trim().length() == 0) {
			errors.add("Community name is required");
		}
		return errors;
	}
}
