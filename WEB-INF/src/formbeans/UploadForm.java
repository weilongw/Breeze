package formbeans;

import java.util.ArrayList;
import java.util.List;

public class UploadForm {
	private String id;
	private String imgName;
	
	public String getId() { return id; }
	public String getImgName() { return imgName; }
	
	public void setId(String x) { id = x; }
	public void setImgName(String x) { imgName = x; }
	
	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();
		
		if (id == null || id.trim().length() == 0) {
			errors.add("Illegal state, item not present");
		}
		if (imgName == null || imgName.trim().length() == 0) {
			errors.add("You didn't upload an image");
		}
		
		if (errors.size() != 0) return errors;
		
		try {
			Integer.parseInt(id);
		} catch (NumberFormatException e) {
			errors.add("Invalid item id");
		}
		
		return errors;
		
	}
	
	public int getItemIdAsInt() {
		return Integer.parseInt(id);
	}
	
	public boolean isPresent() {
		if (id != null) return true;
		if (imgName != null) return true;
		return false;
	}
	
 	
}
