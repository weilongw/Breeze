package databean;

import java.util.Date;

public class FakeMessage implements Comparable<FakeMessage> {

	private int id;		// primary key
	private User sender;
	private User receiver;
	private byte[] content;
	private String title;
	private int hasRead = 0;
	private Date sentDate;
	
	@Override
	public int compareTo(FakeMessage o) {
		return -sentDate.compareTo(o.sentDate);
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
	public byte[] getContent() {
		return content;
	}
	public void setContent(byte[] content) {
		this.content = content;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String x) {
		title = x;
	}
	public int getHasRead() {
		return hasRead;
	}
	public void setHasRead(int hasRead) {
		this.hasRead = hasRead;
	}
	public Date getSentDate() {
		return sentDate;
	}
	public void setSentDate(Date sentDate) {
		this.sentDate = sentDate;
	}

}
