package wpi.xojo.g2.project.http;

public class DeleteChoicesResponse {
	
	public final String error;
	public final int httpCode;
	
	public DeleteChoicesResponse(String error, int code) {
		this.error = error;
		this.httpCode = code;
	}
	
	public DeleteChoicesResponse() {
		this.error = "";
		this.httpCode = 200;
	}
	
	public String toString() {
		if (httpCode == 200) {
			return "Response(success)";
		}
		return "Error :" + error;
	}

}
