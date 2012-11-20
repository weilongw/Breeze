package formbeans;

import java.util.ArrayList;
import java.util.List;

public class CreateCommunityForm {

	private String name;
	private String relatedMovie;
	private String info;
	
	public String getName() { return name; }
	public String getRelatedMovie() { return relatedMovie; }
	public String getInfo() { return info; }
	
	public void setName(String x) { name = x; }
	public void setRelatedMovie(String x) {relatedMovie = x; }
	public void setInfo(String x) { info = x; }
	
	public boolean isPresent() {
		if (name != null) return true;
		if (relatedMovie != null) return true;
		if (info != null) return true;
		return false;
	}
	
	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();
		if (name == null || name.trim().length() == 0) {
			errors.add("Community name is required");
		}
/*		if (relatedMovie == null || relatedMovie.trim().length() == 0) {
			errors.add("A related movie is required");
		}*/
		if (info == null || info.trim().length() == 0) {
			errors.add("Welcome info is required");
		}
		return errors;
	}
}