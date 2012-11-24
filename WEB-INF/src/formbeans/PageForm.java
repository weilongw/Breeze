package formbeans;

import java.util.ArrayList;
import java.util.List;

public class PageForm {

	public static final int INBOX = 0;
	public static final int SENT = 1;
	
	private String inbox;
	private String sent;
	private String render;
	
	public String getInbox() { return inbox; }
	public String getSent() { return sent; }
	public String getRender() { return render; }
	
	public void setInbox(String x) { inbox = x; }
	public void setSent(String x) { sent = x; }
	public void setRender(String x) { render = x; }
	
	public boolean isPresent() {
		if (inbox != null) return true;
		if (sent != null) return true;
		if (render != null) return true;
		return false;
	}
	
	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();
		if (inbox == null || sent == null) {
			errors.add("page index is required");
		}
		if (render == null) {
			errors.add("render path is required");
		}
		if (errors.size() != 0) return errors;
		
		try {
			Integer.parseInt(inbox);
			Integer.parseInt(sent);
			int x = Integer.parseInt(render);
			if (x != PageForm.INBOX && x != PageForm.SENT)
				errors.add("Invalid render path");
		} catch (NumberFormatException e) {
			errors.add("Invalid page index");
		}
		return errors;
	}
}
