package formbeans;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

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
		
		if (errors.size() != 0 ) return errors;
		
		if (title.length() > 50){
			errors.add("Topic title is too long.");
		}
		
		/*Pattern p = Pattern.compile("[^a-zA-Z0-9 _'-:,.!?;()~=+@*]");
		boolean hasSpecialChar = p.matcher(title).find();
		if (hasSpecialChar) {
			errors.add("topic title can only contain characters, digits, and regular symbols");
		}*/
		return errors;
	}

}
