package formbeans;

import java.util.ArrayList;
import java.util.List;

public class ShowItemForm {
	String itemId;

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	
	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		if (itemId == null) 
			errors.add("Item name is required");
		else{
			try {
				int idRange = Integer.parseInt(itemId);
				if(idRange < 1)
					errors.add("Invalid item.");
			}catch (NumberFormatException e) {
				errors.add("Invalid item.");
			}
		}

		return errors;
	}
	
	public int getItemIdAsInt() {
		return Integer.parseInt(itemId);
	}
}
