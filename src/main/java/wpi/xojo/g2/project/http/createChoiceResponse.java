package wpi.xojo.g2.project.http;

public class createChoiceResponse {
	public final String response;
	public final int httpCode;
	
	public createChoiceResponse (String s, int code) {
		this.response = s;
		this.httpCode = code;
	}
	
	// 200 means success
	public createChoiceResponse (String s) {
		this.response = s;
		this.httpCode = 200;
	}
	
	public String toString() {
		return "Response(" + response + ")";
	}
}
