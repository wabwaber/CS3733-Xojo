package wpi.xojo.g2.project.http;

public class ParticipateChoiceResponce {
	public final String response;
	public final int httpCode;
	
	public ParticipateChoiceResponce (String s, int code) {
		this.response = s;
		this.httpCode = code;
	}
	
	// 200 means success
	public ParticipateChoiceResponce (String s) {
		this.response = s;
		this.httpCode = 200;
	}
	
	public String toString() {
		return "Response(" + response + ")";
	}
}
