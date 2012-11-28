package formbeans;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import databean.Item;

public class PostItemForm {
	
	private String itemName;
	private String itemDescription;
	private String itemCategory;
	private String forCredit;
	private String credit;
	private String forExchange;
	private String exchangeDescription;
	private String postType;
	private String relatedMovie;
	
	public boolean isPresent() {
		if (itemName != null) return true;
		if (itemDescription  != null) return true;
		if (forCredit  != null) return true;
		if (itemCategory != null) return true;
		if (credit   != null) return true;
		if (forExchange  != null) return true;
		if (exchangeDescription   != null) return true;
		if (postType   != null) return true;
		if (relatedMovie != null) return true;
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
		
		if (itemCategory == null || itemCategory.trim().length() == 0) {
			errors.add("Item Category is required");
		}
	/*	if (relatedMovie == null || relatedMovie.trim().length() == 0) {
			errors.add("Related Movie is required");
		}*/
		
		if (relatedMovie == null) {
			errors.add("related movie is required");
		}
		
		if (relatedMovie != null && relatedMovie.trim().length() != 0) {
			if (!relatedMovie.matches("tt\\d{7}")) {
				errors.add("Invalid imdb id");
			}
		}
		if (forCredit == null && forExchange == null) {
			errors.add("You must choose one from the checkbox.");
		}
		if (errors.size() != 0 ) return errors;
		
		try {
			int category = Integer.parseInt(itemCategory);
			if (category < 1 || category > 3) {
				errors.add("Invalid category");
				return errors;
			}
		} catch (NumberFormatException e) {
			errors.add("Invalid category");
			return errors;
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
			int type = Integer.parseInt(postType);
			if (type != Item.POST && type != Item.REQUEST) {
				errors.add("Invalid post type");
			}
		}catch (NumberFormatException e) {
			errors.add("Invalid post type");
		}
		if (errors.size() != 0) return errors;
		if (itemName.length() > 30) {
			errors.add("Item name is too long");
		}
		/*Pattern p = Pattern.compile("[^a-zA-Z0-9 _'-:,.!?;()~=+@*]");
		boolean hasSpecialChar = p.matcher(itemName).find();
		if (hasSpecialChar) {
			errors.add("Item name can only contain characters, digits, and regular symbols" );
		}*/
		
		if(itemDescription.length() > 250){
			errors.add("Item description is too long.");
		}
		
		if(forExchange != null && exchangeDescription.trim().length() > 250){
			errors.add("Exchange discription is too long.");
		}
		
		return errors;
	}
	
	public int getPostTypeAsInt() {
		return Integer.parseInt(postType);
	}
	
	public int getItemCategoryAsInt() {
		return Integer.parseInt(itemCategory);
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
	public String getItemCategory() {
		return itemCategory;
	}
	public void setItemCategory(String x) {
		itemCategory = x;
	}
	public String getRelatedMovie() {
		return relatedMovie;
	}
	public void setRelatedMovie(String x) {
		relatedMovie = x;
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
