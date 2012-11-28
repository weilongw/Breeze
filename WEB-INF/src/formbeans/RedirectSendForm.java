package formbeans;

import java.util.ArrayList;
import java.util.List;

public class RedirectSendForm {

	private String receiver;
	
	public String getReceiver() { return receiver; }
	
	public void setReceiver(String x) { receiver = x; }
	
	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();
		
		if (receiver == null || receiver.trim().length() == 0) {
			errors.add("receiver is required");
		}
		
		return errors;
	}
	

	public boolean isPresent() {
		if (receiver != null) return true;
		return false;
	}
}