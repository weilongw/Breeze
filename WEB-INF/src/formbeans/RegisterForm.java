package formbeans;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class RegisterForm {
	private String userName;
	private String address;
	private String email;
	private String emailConfirm;
	private String password;
	private String passwordConfirm;
	
	

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmailConfirm() {
		return emailConfirm;
	}

	public void setEmailConfirm(String emailConfirm) {
		this.emailConfirm = emailConfirm;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		if (userName == null || userName.trim().length() == 0) {
			errors.add("User name is required");
		}

		if (address == null || address.trim().length() == 0) {
			errors.add("Address is required");
		}

		if (email == null || email.trim().length() == 0) {
			errors.add("Email is required");
		}
		
		if (emailConfirm == null || emailConfirm.trim().length() == 0) {
			errors.add("Confirm Email is required");
		}
		
		if (password == null || password.trim().length() == 0) {
			errors.add("Password is required");
		}

		if (passwordConfirm == null || passwordConfirm.trim().length() == 0) {
			errors.add("Confirm password is required");
		}
		
		if (errors.size() > 0) {
			return errors;
		}
		
		if (!password.equals(passwordConfirm)) {
			errors.add("Passwords are not the same");
		}
		
		if (!email.equals(emailConfirm)) {
			errors.add("Emails are not the same");
		}
		
		if (userName.length() > 10) {
			errors.add("Username is too long");
		}
		
		Pattern p = Pattern.compile("[^a-zA-Z0-9]");
		boolean hasSpecialChar = p.matcher(userName).find();
		if (hasSpecialChar) {
			errors.add("username can only contain characters and digits");
		}
		
		return errors;
	}
	
	public boolean isPresent() {
		if (userName != null) return true;
		if (address  != null) return true;
		if (email  != null) return true;
		if (emailConfirm   != null) return true;
		if (password  != null) return true;
		if (passwordConfirm   != null) return true;
		return false;
	}
}
