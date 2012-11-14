package databean;

public class Exchange {

	private int id;
	private int itemId;
	private User poster;
	private User responder;
	private int respondType;
	private int status;
	
	public static final int PENDING = 1;
	public static final int CLOSED = 2;
	public static final int NO_ONE_ANSWER = 0;
	public static final int ANSWER_POST_WITH_CREDIT = 1;
	public static final int ANSWER_POST_WITH_EXCHANGE = 2;
	public static final int ANSWER_REQUEST_FOR_CREDIT = 3;
	public static final int ANSWER_REQUEST_FOR_EXCHANGE = 4;
	
	
	public int getId()		{ return id; }
	public int getItemId()	{ return itemId; }
	public User getPoster() { return poster; }
	public User getResponder() { return responder; }
	public int getRespondType() { return respondType; }
	public int getStatus() { return status; }
	
	public void setId(int x) { id = x; }
	public void setItemId(int x) { itemId = x; }
	public void setPoster(User x) { poster = x; }
	public void setResponder(User x) { responder = x; }
	public void setRespondType(int x) { respondType = x; }
	public void setStatus(int x) { status = x; }
	
	
 
}
