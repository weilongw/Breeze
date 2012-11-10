package formbeans;

import java.util.ArrayList;
import java.util.List;

public class LoginForm {
	private String userName;
	private String password;
	
	public String getUserName()  { return userName; }
	public String getPassword()  { return password; }
	
	public void setUserName(String s)  { userName = s; }
	public void setPassword(String s)  { password = s; }

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		if (userName == null || userName.trim().length() == 0) {
			errors.add("User name is required");
		}

		if (password == null || password.trim().length() == 0) {
			errors.add("Password is required");
		}
		
		return errors;
	}
	
	public boolean isPresent() {
		if (userName != null) return true;
		if (password != null) return true;
		return false;
	}
}
