package formbeans;

import java.util.ArrayList;
import java.util.List;

public class PostItemForm {
	
	private String itemName;
	private String itemDescription;
	private String forCredit;
	private String credit;
	private String forExchange;
	private String exchangeDescription;
	private String postType;
	
	public boolean isPresent() {
		if (itemName != null) return true;
		if (itemDescription  != null) return true;
		if (forCredit  != null) return true;
		if (credit   != null) return true;
		if (forExchange  != null) return true;
		if (exchangeDescription   != null) return true;
		if (postType   != null) return true;
		return false;
	}
	
	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		if (itemName == null || itemName.trim().length() == 0) {
			errors.add("Item name is required");
		}

		if (itemDescription == null || itemDescription.trim().length() == 0) {
			errors.add("Item description is required");
		}
		
		if (forCredit == null && forExchange == null) {
			errors.add("You must choose one from the checkbox.");
		}
		
		if (forCredit != null){
			if(credit == null || credit.trim().length() == 0) 
				errors.add("You must enter the credits.");
			else{
				try{
				int credits = Integer.parseInt(credit);
				if(credits < 0)// Is there a range for that input?
					errors.add("Invalid credits.");
				} catch(NumberFormatException e){
					errors.add("Invalid credits.");
				}
			}			
		}
		
		if(forExchange != null && 
				(exchangeDescription == null || exchangeDescription.trim().length() == 0))
			errors.add("Exchange discription is required.");
		
		try {
			Integer.parseInt(postType);
		}catch (NumberFormatException e) {
			errors.add("Invalid post type");
		}
		
		return errors;
	}
	
	public int getPostTypeAsInt() {
		return Integer.parseInt(postType);
	}
	
	
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName.trim();
	}
	public String getItemDescription() {
		return itemDescription;
	}
	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription.trim();
	}
	public String getForCredit() {
		return forCredit;
	}
	public void setForCredit(String forCredit) {
		this.forCredit = forCredit;
	}
	public String getCredit() {
		return credit;
	}
	public void setCredit(String credit) {
		this.credit = credit.trim();
	}
	public String getForExchange() {
		return forExchange;
	}
	public void setForExchange(String forExchange) {
		this.forExchange = forExchange;
	}
	public String getExchangeDescription() {
		return exchangeDescription;
	}
	public void setExchangeDescription(String exchangeDescription) {
		this.exchangeDescription = exchangeDescription.trim();
	}
	public String getPostType() {
		return postType;
	}
	public void setPostType(String postType) {
		this.postType = postType;
	}
	
	
	
	
	
	
}
