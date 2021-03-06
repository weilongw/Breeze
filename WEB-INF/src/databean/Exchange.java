package databean;

import java.util.Date;

public class Exchange {

	private int id;
	private Item item;
	private User poster;
	private User responder;
	private int respondType;
	private int status;
	private Date endDate;
	
	public static final int PENDING = 1;
	public static final int CLOSED = 2;	// closed or success for responders
	public static final int NO_ONE_ANSWER = 0;	// for items closed by posters without buyer
	public static final int SUCCESS = 3;
	
	public static final int ANSWER_POST_WITH_CREDIT = 1;
	public static final int ANSWER_POST_WITH_EXCHANGE = 2;
	public static final int ANSWER_REQUEST_FOR_CREDIT = 3;
	public static final int ANSWER_REQUEST_FOR_EXCHANGE = 4;
	public static final int UNDEFINED = 5;
	
	
	public int getId()		{ return id; }
	public Item getItem()	{ return item; }
	public User getPoster() { return poster; }
	public User getResponder() { return responder; }
	public int getRespondType() { return respondType; }
	public int getStatus() { return status; }
	public Date getEndDate() { return endDate; }
	
	public void setId(int x) { id = x; }
	public void setItem(Item x) { item = x; }
	public void setPoster(User x) { poster = x; }
	public void setResponder(User x) { responder = x; }
	public void setRespondType(int x) { respondType = x; }
	public void setStatus(int x) { status = x; }
	public void setEndDate(Date x) { endDate = x; }
	
	
 
}
