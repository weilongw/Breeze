package formbeans;

import java.util.ArrayList;
import java.util.List;

public class RedirectSendForm {

	private String receiver;
	private String itemId;
	
	public String getReceiver() { return receiver; }
	public String getItemId() { return itemId; }
	
	public void setReceiver(String x) { receiver = x; }
	public void setItemId(String x) { itemId = x; }
	
	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();
		
		if (receiver == null || receiver.trim().length() == 0) {
			errors.add("receiver is required");
		}
		if (itemId == null) {
			errors.add("itemID is required");
		}
		if (errors.size() != 0 ) return errors;
		
		try {
			Integer.parseInt(itemId);
		} catch (NumberFormatException e) {
			errors.add("Invalid ItemID");
		}
		
		return errors;
	}
	
	public int getItemIdAsInt() {
		return Integer.parseInt(itemId);
	}

	public boolean isPresent() {
		if (receiver != null) return true;
		if (itemId != null) return true;
		return false;
	}
}