package formbeans;

import java.util.List;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class MessageForm {

	
	private String receiver;
	private String title;
	private String content;
	
	
	public String getReceiver() { return receiver; }
	public String getTitle()    { return title; }
	public String getContent()  { return content; }
	
	
	public void setReceiver(String x) { receiver = x; }
	public void setTitle(String x) { title = x; }
	public void setContent(String x) { content = x; }
	
	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();
		
		if (receiver == null || receiver.trim().length() == 0) {
			errors.add("receiver is required");
		}
		if (title == null || title.trim().length() == 0) {
			errors.add("title is required");
		}
		if (content == null || content.trim().length() == 0) {
			errors.add("content is required");
		}
		
		if (errors.size() != 0 ) return errors;
		
		if (title.length() > 50){
			errors.add("Message title is too long.");
		}
		/*Pattern p = Pattern.compile("[^a-zA-Z0-9 _'-:,.!?;()~=+@*]");
		boolean hasSpecialChar = p.matcher(title).find();
		if (hasSpecialChar) {
			errors.add("Title can only contain characters, digits, and regular symbols" );
		}*/
		
		return errors;
	}
	
	public boolean isPresent() {
		
		if (receiver != null) return true;
		if (title != null) return true;
		if (content != null) return true;
		return false;
	}

}
