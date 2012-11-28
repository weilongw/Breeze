package formbeans;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class UpdateProfileForm {
	private String address;
	private String email;
	private String confirmEmail;
	private String password;
	private String confirmPwd;
	
	private static final String EMAIL_PATTERN = 
			"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	 
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
	public String getConfirmEmail() {
		return confirmEmail;
	}
	public void setConfirmEmail(String confirm_email) {
		this.confirmEmail = confirm_email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmPwd() {
		return confirmPwd;
	}
	public void setConfirmPwd(String confirm_pwd) {
		this.confirmPwd = confirm_pwd;
	}
	
	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		
		if (address == null || address.trim().length() == 0) {
			errors.add("Adress is required");
		}

		if (email == null || email.trim().length() == 0) {
			errors.add("Email is required");
		}
		
		if (confirmEmail == null || confirmEmail.trim().length() == 0) {
			errors.add("Confirm Email is required");
		}
		
		if (password == null || password.trim().length() == 0) {
			errors.add("Password is required");
		}

		if (confirmPwd == null || confirmPwd.trim().length() == 0) {
			errors.add("Confirm password is required");
		}
		
		if (errors.size() > 0) {
			return errors;
		}
		
		if (!password.equals(confirmPwd)) {
			errors.add("Passwords are not the same");
		}
		
		if (!email.equals(confirmEmail)) {
			errors.add("Emails are not the same");
		}
		
		if(address.length() > 250){
			errors.add("Address is too long");
		}
		
		Pattern pForEmail = Pattern.compile(EMAIL_PATTERN);
		boolean emailValidate = pForEmail.matcher(email).find();
		if (!emailValidate) {
			errors.add("Invalid email address");
		}
		
		return errors;
	}
	
	public boolean isPresent() {
		if (address  != null) return true;
		if (email  != null) return true;
		if (confirmEmail   != null) return true;
		if (password  != null) return true;
		if (confirmPwd   != null) return true;
		return false;
	}
}
