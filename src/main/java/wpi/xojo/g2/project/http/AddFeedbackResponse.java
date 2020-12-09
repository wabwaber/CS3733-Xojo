package wpi.xojo.g2.project.http;

import wpi.xojo.g2.project.model.Feedback;

public class AddFeedbackResponse {
	public final Feedback feedback;
	public final String error;
	public final int httpCode;
	
	public AddFeedbackResponse(String error, int code) {
		this.feedback = null;
		this.error = error;
		this.httpCode = code;
	}
	
	public AddFeedbackResponse(Feedback f) {
		this.feedback = f;
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
