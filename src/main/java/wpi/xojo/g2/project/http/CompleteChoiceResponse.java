package wpi.xojo.g2.project.http;

public class CompleteChoiceResponse {
	
	public final String error;
	public final int httpCode;
	
	public CompleteChoiceResponse(String error, int code) {
		this.error = error;
		this.httpCode = code;
	}
	
	public CompleteChoiceResponse(int code) {
		this.error = "";
		this.httpCode = code;
	}
	
	public String toString() {
		if (httpCode == 200) {
			return "Response(success)";
		}
		return "Error :" + error;
	}

}
