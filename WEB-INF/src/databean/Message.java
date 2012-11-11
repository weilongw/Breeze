package databean;

import java.util.Date;

public class Message implements Comparable {
	private int id;		// primary key
	private User sender;
	private User receiver;
	private String content;
	private String title;
	private int type;
	private Date sentDate;
	
	public int compareTo(Object other){
		return -sentDate.compareTo(((Message)other).sentDate);
	}
	public int getId() {
		return id;
	}
	public void setId(int x) {
		id = x;
	}
	public User getSender() {
		return sender;
	}
	public void setSender(User sender) {
		this.sender = sender;
	}
	public User getReceiver() {
		return receiver;
	}
	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String x) {
		title = x;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public Date getSentDate() {
		return sentDate;
	}
	public void setSentDate(Date sentDate) {
		this.sentDate = sentDate;
	}
	
	
}
