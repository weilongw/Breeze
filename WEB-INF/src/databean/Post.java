package databean;

import java.util.Date;

public class Post implements Comparable<Post>{
	private int id;
	private String content;
	private Date postDate;
	private User poster;
	private Topic topic;

	public int getId() { return id; }
	public String getContent() { return content; }
	public Date getPostDate() { return postDate; }
	public User getPoster() { return poster; }
	public Topic getTopic() { return topic; }
	
	public void setId(int x) { id = x; }
	public void setContent(String x) { content = x; }
	public void setPostDate(Date x) { postDate = x; }
	public void setPoster(User x) { poster = x; }
	public void setTopic(Topic x) { topic = x; }
	
	@Override
	public int compareTo(Post o) {
		return -this.postDate.compareTo(o.postDate);
	}
}
