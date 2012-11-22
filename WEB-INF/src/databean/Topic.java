package databean;

import java.util.Comparator;
import java.util.Date;

public class Topic implements Comparable<Topic>{

	private int id;
	private String title;
	private int viewCount;
	private int replyCount;
	private Community ownerGroup;
	private User poster;
	private Date postDate;
	
	public int getId() { return id; }
	public String getTitle() { return title; }
	public int getViewCount() { return viewCount; }
	public int getReplyCount() { return replyCount; }
	public Community getOwnerGroup() { return ownerGroup; }
	public User getPoster() { return poster; }
	public Date getPostDate() { return postDate; }
	
	public void setId (int x) { id = x; }
	public void setTitle(String x) { title = x; }
	public void setViewCount(int x) { viewCount = x; }
	public void setReplyCount(int x) { replyCount = x; }
	public void setOwnerGroup(Community x) { ownerGroup = x; }
	public void setPoster(User x) { poster = x; }
	public void setPostDate(Date x) { postDate = x; }
	
	@Override
	public int compareTo(Topic o) {
		if(replyCount > o.replyCount) return -1;
		if (replyCount < o.replyCount) return 1;
		return -postDate.compareTo(o.postDate);
	}
	
	public static final Comparator<Topic> REVERSE_TIMEORDER = 
            new Comparator<Topic>() {		
		@Override
		public int compare(Topic t1, Topic t2) {
			// TODO Auto-generated method stub
			return t2.postDate.compareTo(t1.postDate);
		}
	};

}
