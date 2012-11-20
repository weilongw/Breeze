package formbeans;

import java.util.ArrayList;
import java.util.List;

public class RedirectSendForm {

	private String receiver;
	private String title;
	
	public String getReceiver() { return receiver; }
	public String getTitle() { return title; }
	
	public void setReceiver(String x) { receiver = x; }
	public void setTitle(String x) { title = x; }
	
	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();
		
		if (receiver == null || receiver.trim().length() == 0) {
			errors.add("receiver is required");
		}
		if (title == null || title.trim().length() == 0) {
			errors.add("title is required");
		}
		
		return errors;
	}
	

	public boolean isPresent() {
		if (receiver != null) return true;
		if (title != null) return true;
		return false;
	}
}