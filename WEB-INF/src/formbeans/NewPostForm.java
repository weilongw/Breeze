package formbeans;

import java.util.ArrayList;
import java.util.List;

public class NewPostForm {
	
	private String topicId;
	private String postContent;
	
	public String getTopicId() {
		return topicId;
	}
	public void setTopicId(String topicId) {
		this.topicId = topicId;
	}
	public String getPostContent() {
		return postContent;
	}
	public void setPostContent(String postContent) {
		this.postContent = postContent;
	}
	
	public int getTopicIdAsInt() {
		return Integer.parseInt(topicId);
	}
	
	public boolean isPresent() {
		if (topicId != null) return true;
		if(postContent != null) return true;
		return false;
	}
	
	public List<String> getIntegerValidationErrors() {
		List<String> errors = new ArrayList<String>();
		
		if(topicId == null)
			errors.add("Invalid topic.");
		else
			try {
				int idRange = Integer.parseInt(topicId);
				if(idRange < 1)
					errors.add("Invalid topic.");
			}catch (NumberFormatException e) {
				errors.add("Invalid topic.");
			}
		
		return errors;
	}
	
	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		if(postContent == null || postContent.trim().length() == 0)
			errors.add("Enter your post content");
		
		return errors;
	}

}
