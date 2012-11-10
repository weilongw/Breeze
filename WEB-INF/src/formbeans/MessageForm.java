package formbeans;

import java.util.List;
import java.util.ArrayList;

public class MessageForm {

	private String sender;
	private String receiver;
	private String title;
	private String content;
	
	public String getSender()   { return sender; }
	public String getReceiver() { return receiver; }
	public String getTitle()    { return title; }
	public String getContent()  { return content; }
	
	public void setSender(String x) { sender = x; }
	public void setReceiver(String x) { receiver = x; }
	public void setTitle(String x) { title = x; }
	public void setContent(String x) { content = x; }
	
	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();
		if (sender == null || sender.trim().length() == 0) {
			errors.add("sender is required");
		}
		if (receiver == null || receiver.trim().length() == 0) {
			errors.add("receiver is required");
		}
		if (title == null || title.trim().length() == 0) {
			errors.add("title is required");
		}
		if (content == null || content.trim().length() == 0) {
			errors.add("content is required");
		}
		return errors;
	}
	
	public boolean isPresent() {
		if (sender != null) return true;
		if (receiver != null) return true;
		if (title != null) return true;
		if (content != null) return true;
		return false;
	}

}
