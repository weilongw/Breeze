package databean;

public class Relation {

	private int id;
	private User user;
	private Community community;
	
	public int getId() { return id; }
	public User getUser() { return user; }
	public Community getCommunity() { return community; }
	
	public void setId(int x) { id = x; }
	public void setUser(User x) { user = x; }
	public void setCommunity(Community x) { community = x; }
	

}
