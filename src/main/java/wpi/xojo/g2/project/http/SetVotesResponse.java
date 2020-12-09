package wpi.xojo.g2.project.http;

public class SetVotesResponse {
	public final String error;
	public final int httpCode;
	
	public SetVotesResponse(String error, int code) {
		this.error = error;
		this.httpCode = code;
	}
	
	public SetVotesResponse() {
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
