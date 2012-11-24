package databean;

import java.util.Date;

public class Item implements Comparable<Item>{
	public static final int POST = 1;
	public static final int REQUEST = 2;
	
	public static final int OPEN = 0;
	public static final int CLOSED = 1;
	
	public static final int POSTER = 1;
	public static final int DVD = 2;
	public static final int PROP = 3;
	
	private int id;	// primary key
	private String itemName;
	private String relatedMovie;
	private User owner;		// user who posted this item
	private String itemDescription;
	private String exchangeItemDescription;	
	private int category;
	private String imgName;
	private Date postDate;
	private int credit = -1;
	private int type;	// whether the item is posted as on sale or being requested
	private int status;	// whether this transaction is complete
	// keep track of how many times an item's show page has been visited.
	private int clickCount;
	
	@Override
	public int compareTo(Item o) {
		int c = postDate.compareTo(o.postDate);
		if (c != 0) return -c;
		c = itemName.compareTo(o.itemName);
		return c;
	}		
	
	/*
	public int compareTo(Object other){
		Item item = (Item) other;
		int c = postDate.compareTo(item.postDate);
		if(c != 0)	return -c;
		c = itemName.compareTo(item.itemName);
		return c;
	}*/
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
	public int getCategory() {
		return category;
	}
	public void setCategory(int category) {
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
