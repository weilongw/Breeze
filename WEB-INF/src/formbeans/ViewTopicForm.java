package formbeans;

public class ViewTopicForm {
	private String topicId;
	
	
	public String getTopicId() {
		return topicId;
	}
	
	public int getTopicIdAsInt() {
		return Integer.parseInt(topicId);
	}

	public void setTopicId(String topicId) {
		this.topicId = topicId;
	}

	public boolean isPresent() {
		if (topicId != null) return true;
		return false;
	}

}
