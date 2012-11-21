package formbeans;

import java.util.ArrayList;
import java.util.List;

public class NewTopicForm {
	
	private String title;
	private String content;
	private String communityName;
	
	public String getTitle() { return title; }
	public String getContent() { return content; }
	public String getCommunityName() { return communityName; }
	
	public void setTitle(String x) { title = x; }
	public void setContent(String x) { content = x; }
	public void setCommunityName(String x) { communityName = x; }
	
	public boolean isPresent() {
		if (title != null) return true;
		if (content != null) return true;
		if (communityName != null) return true;
		return false;
	}
	
	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();
		if (title == null || title.trim().length() == 0) {
			errors.add("Topic title is required");
		}
		if (content == null || content.trim().length() == 0) {
			errors.add("Content is required");
		}
		if (communityName == null || communityName.trim().length() == 0) {
			errors.add("Community Name is required");
		}
		return errors;
	}

}
