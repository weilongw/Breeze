package databean;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class User {

	private String userName;
	private String hashedPassword;
	private int salt;
	private String address = null;
	private int credit = 0;
	private String email;
	private String userPhoto="default_icon.jpg";
	private int newMsgCount = 0;

	public User(String userName) { this.userName = userName; }

	public boolean checkPassword(String password) {
		return hashedPassword.equals(hash(password));
	}
	
	public String getHashedPassword() { return hashedPassword; }
	public String getUserName()		  { return userName; }
	public int    getSalt()           { return salt; }
	public int    getCredit()         { return credit; }
	public String getAddress()        { return address; }
	public String getEmail()          { return email; }
	public String getUserPhoto()	  { return userPhoto; }
	public int getNewMsgCount()		  { return newMsgCount; }

	public int hashCode() 			  { return userName.hashCode(); }

	public void setHashedPassword(String x) { hashedPassword = x; }
	public void setPassword(String x) { salt = newSalt(); hashedPassword = hash(x); }
	public void setSalt(int x)   { salt = x; }
	public void setAddress(String x) { address = x; }
	public void setCredit(int x) { credit = x; }
	public void setEmail(String x)   { email =  x; }
	public void setUserPhoto(String x) { userPhoto = x; }
	public void setNewMsgCount(int x) { newMsgCount = x; }

	@Override
	public boolean equals(Object obj) {
		User usr = (User)obj;
		return userName.equals(usr.userName);
	}
	
	private String hash(String clearPassword) {
		if (salt == 0) return null;

		MessageDigest md = null;
		try {
		  md = MessageDigest.getInstance("SHA1");
		} catch (NoSuchAlgorithmException e) {
		  throw new AssertionError("Can't find the SHA1 algorithm in the java.security package");
		}

		String saltString = String.valueOf(salt);
		
		md.update(saltString.getBytes());
		md.update(clearPassword.getBytes());
		byte[] digestBytes = md.digest();

		// Format the digest as a String
		StringBuffer digestSB = new StringBuffer();
		for (int i=0; i<digestBytes.length; i++) {
		  int lowNibble = digestBytes[i] & 0x0f;
		  int highNibble = (digestBytes[i]>>4) & 0x0f;
		  digestSB.append(Integer.toHexString(highNibble));
		  digestSB.append(Integer.toHexString(lowNibble));
		}
		String digestStr = digestSB.toString();

		return digestStr;
	}

	private int newSalt() {
		Random random = new Random();
		return random.nextInt(8192)+1;  // salt cannot be zero
	}
	
}
