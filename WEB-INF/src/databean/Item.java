package databean;

import java.util.Date;

public class Item implements Comparable{
	public static final int POST = 1;
	public static final int REQUEST = 2;
	
	private int id;	// primary key
	private String itemName;
	private String relatedMovie;
	private User owner;		// user who posted this item
	private String itemDescription;
	private String exchangeItemDescription;	
	private String category;
	private String imgName;
	private Date postDate;
	private int credit;
	private int type;	// whether the item is posted as on sale or being requested
	private int status;	// whether this transaction is complete
	// keep track of how many times an item's show page has been visited.
	private int clickCount;
	
	public int compareTo(Object other){
		Item item = (Item) other;
		int c = postDate.compareTo(item.postDate);
		if(c != 0)	return -c;
		c = itemName.compareTo(item.itemName);
		return c;
	}
	public int getId() {
		return id;
	}
	public void setId(int x) {
		id = x;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getRelatedMovie() {
		return relatedMovie;
	}
	public void setRelatedMovie(String relatedMovie) {
		this.relatedMovie = relatedMovie;
	}
	public User getOwner() {
		return owner;
	}
	public void setOwner(User owner) {
		this.owner = owner;
	}
	public String getImgName() {
		return imgName;
	}
	public void setImgName(String x) {
		imgName = x;
	}
	public String getItemDescription() {
		return itemDescription;
	}
	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}
	public String getExchangeItemDescription() {
		return exchangeItemDescription;
	}
	public void setExchangeItemDescription(String exchangeItemDescription) {
		this.exchangeItemDescription = exchangeItemDescription;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Date getPostDate() {
		return postDate;
	}
	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}
	public int getCredit() {
		return credit;
	}
	public void setCredit(int credit) {
		this.credit = credit;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getClickCount() {
		return clickCount;
	}
	public void setClickCount(int clickCount) {
		this.clickCount = clickCount;
	}		
	
	

}
