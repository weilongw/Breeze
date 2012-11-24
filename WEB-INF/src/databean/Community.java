package databean;

import java.util.Comparator;
import java.util.Date;

public class Community implements Comparable<Community> {


	private String name;
	private String relatedMovie;
	private String info;
	private User creater;
	private int userCount;
	private int topicCount;
	private Date createdAt;
	
	public Community(String name) { this.name = name; }

	public String getName() { return name; }
	public String getRelatedMovie() { return relatedMovie; }
	public String getInfo() { return info; }
	public User getCreater() { return creater; }
	public int getUserCount() { return userCount; }
	public int getTopicCount() { return topicCount; }
	public Date getCreatedAt() { return createdAt; }
	

	//public void setName(String x) { name = x; }
	public void setRelatedMovie(String x) { relatedMovie = x; }
	public void setInfo(String x) { info = x; }
	public void setCreater(User x) { creater = x; }
	public void setUserCount(int x) { userCount = x; }
	public void setTopicCount(int x) { topicCount = x; }
	public void setCreatedAt(Date x) { createdAt = x; }
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Community)) return false;
		Community another_c = (Community)obj;
		return this.name.equals(another_c.name);
	}

	@Override
	public int compareTo(Community o) {
		if (userCount > o.userCount) return -1;
		if (userCount < o.userCount) return 1;
		return 0;
	}
	
	public static final Comparator<Community> REVERSE_TIMEORDER = 
            new Comparator<Community>() {		
		@Override
		public int compare(Community t1, Community t2) {
			// TODO Auto-generated method stub
			return t2.createdAt.compareTo(t1.createdAt);
		}
	};
}
