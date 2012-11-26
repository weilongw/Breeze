package formbeans;

import java.util.ArrayList;
import java.util.List;

public class ReadMessageForm {
	String msgId;

	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String MsgId) {
		this.msgId = MsgId;
	}
	
	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		if (msgId == null) 
			errors.add("Message id is required");
		else{
			try {
				int idRange = Integer.parseInt(msgId);
				if(idRange < 1)
					errors.add("Invalid message id.");
			}catch (NumberFormatException e) {
				errors.add("Invalid message id.");
			}
		}

		return errors;
	}

	public boolean isPresent() {
		if (msgId != null) return true;
		return false;
	}
}
