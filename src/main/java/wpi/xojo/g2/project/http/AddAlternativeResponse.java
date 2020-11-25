package wpi.xojo.g2.project.http;

import wpi.xojo.g2.project.model.Alternative;

public class AddAlternativeResponse {
	public final Alternative alternative;
	public final String error;
	public final int httpCode;
	
	public AddAlternativeResponse (String s, int code) {
		this.alternative = null;
		this.error = s;
		this.httpCode = code;
	}
	
	// 200 means success
	public AddAlternativeResponse (Alternative a) {
		this.alternative = a;
		this.error = "";
		this.httpCode = 200;
	}
	
	public String toString() {
		if (httpCode == 200) {
			return "Response(" + alternative.toString() + ")";
		}
		return "Error :" + error;
	}
}
