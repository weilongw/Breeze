package formbeans;

import java.util.ArrayList;
import java.util.List;

public class SearchTopicForm {

	private String key;
	private String commName;
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getCommName() {
		return commName;
	}
	public void setCommName(String commName) {
		this.commName = commName;
	}
	
	public boolean isPresend() {
		if (key != null) return true;
		if (commName != null) return true;
		return false;
	}
	
	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();
		 
		if (commName == null || commName.trim().length() == 0){
			errors.add("Invalid community");
		}
		
		return errors;
	}
	
	
	
}
