package wpi.xojo.g2.project.http;

public class createChoiceResponse {
	public int statusCode;
	public String error;
	
	public createChoiceResponse (int statusCode) {
		this.statusCode = statusCode;
		this.error = "";
	}
	
	public createChoiceResponse(int statusCode, String errorMessage) {
		this.statusCode = statusCode;
		this.error = errorMessage;
	}
	
	public String toString() {
		if (statusCode / 100 == 2) {
			return "Success creating responce";
		} else {
			return "ErrorResult(" + statusCode + ", err=" + error + ")";
		}
	}
}
