package formbeans;

import java.util.ArrayList;
import java.util.List;

public class BuyItemForm {
	String buyType;
	String itemId;
	
	public boolean isPresent() {
		if (buyType != null) return true;
		if (itemId != null) return true;
		return false;
	}
	public String getBuyType() {
		return buyType;
	}
	public void setBuyType(String buyType) {
		this.buyType = buyType;
	}
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	
	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();
		
		if (buyType == null) {
			errors.add("Invalid operation.");
		}
		else{
			try{
				int type = Integer.parseInt(buyType);
				if(type > 4 && type < 1){
					errors.add("Invalid operation.");
				}
			}catch (NumberFormatException e) {
				errors.add("Invalid operation.");
			}
		}
		 
		if (itemId == null) {
			errors.add("Invalid operation.");
		}
		else{
			try{
				int id = Integer.parseInt(itemId);
				if(id < 1){
					errors.add("Invalid operation.");
				}
			}catch (NumberFormatException e) {
				errors.add("Invalid operation.");
			}
		}
		
		return errors;
	}
	
	public int getBuyTypeAsInt() {
		return Integer.parseInt(buyType);
	}

	public int getItemIdAsInt() {
		return Integer.parseInt(itemId);
	}
}
