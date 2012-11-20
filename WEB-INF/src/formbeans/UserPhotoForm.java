package formbeans;

import java.util.ArrayList;
import java.util.List;

public class UserPhotoForm {

	private String userPhoto;
	
	public String getUserPhoto() { return userPhoto; }
	public void setUserPhoto (String x) { userPhoto = x; }
	
	public boolean isPresent() {
		if (userPhoto != null) return true;
		return false;
	}
 
	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();
		if (userPhoto == null || userPhoto.trim().length() == 0) {
			errors.add("User photo name is required");
		}
		return errors;
	}
}
