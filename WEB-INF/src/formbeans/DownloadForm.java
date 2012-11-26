package formbeans;

import java.util.ArrayList;
import java.util.List;

public class DownloadForm {

	private String url;
	private String id;
	
	public String getUrl() { return url; }
	public String getId() { return id; }
	
	public void setUrl(String x) { url = x; }
	public void setId(String x) { id = x; }
	
	public boolean isPresent() {
		if (url != null) return true;
		if (id != null) return true;
		return false;
	}
	
	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();
		if (url == null || url.trim().length() == 0) {
			errors.add("url is required.");
		}
		if (id == null || id.trim().length() == 0) {
			errors.add("movie id is required");
		}
		if (errors.size() != 0) return errors;
		
		if (!id.matches("tt\\d{7}")) {
			errors.add("Invalid imdb id");
			return errors;
		}
		
		if (!url.startsWith("http://ia.media-imdb.com/images")) {
			errors.add("Invalid image url");
		}
		
		return errors;
	}

}
