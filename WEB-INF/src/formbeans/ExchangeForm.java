package formbeans;

import java.util.ArrayList;
import java.util.List;

public class ExchangeForm {

	private String exchangeId;
	
	public String getExchangeId() { return exchangeId; }
	public void setExchangeId(String x) { exchangeId = x; }
	
	public boolean isPresent() {
		if (exchangeId != null) return true;
		return false;
	}
	
	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();
		if (exchangeId == null || exchangeId.trim().length() == 0) {
			errors.add("Invalid Transaction ID");
		}
		if (errors.size() != 0) return errors;
		try {
			Integer.parseInt(exchangeId);
		} catch(NumberFormatException e){
			errors.add("Invalid Transaction ID");
		}
		return errors;
	}
	
	public int getExchangeIdAsInt() {
		return Integer.parseInt(exchangeId);
	}
}
