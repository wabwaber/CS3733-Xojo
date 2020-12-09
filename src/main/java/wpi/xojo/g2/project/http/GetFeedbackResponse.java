package wpi.xojo.g2.project.http;

import java.util.List;

import wpi.xojo.g2.project.model.FeedbackName;

public class GetFeedbackResponse {
	public final List<FeedbackName> feedback;
	public final String error;
	public final int httpCode;
	
	public GetFeedbackResponse(String error, int code) {
		feedback = null;
		this.error = error;
		this.httpCode = code;
	}
	
	public GetFeedbackResponse(List<FeedbackName> feedback) {
		this.feedback = feedback;
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
