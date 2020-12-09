package wpi.xojo.g2.project.http;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import wpi.xojo.g2.project.model.Feedback;

public class GetFeedbackResponse {
	class FeedbackName {
		public String name;
		public String description;
		public Timestamp timeCreated;
		
		FeedbackName(String name, String description, Timestamp timeCreated) {
			this.name = name;
			this.description = description;
			this.timeCreated = timeCreated;
		}
	}
	
	public final List<FeedbackName> feedback;
	public final String error;
	public final int httpCode;
	
	public GetFeedbackResponse(String error, int code) {
		feedback = null;
		this.error = error;
		this.httpCode = code;
	}
	
	public GetFeedbackResponse(List<Feedback> feedbackBody, List<String> name) {
		feedback = new ArrayList<FeedbackName>();
		for (int i = 0; i < feedback.size(); i++) {
			String memberName = name.get(i);
			String description = feedbackBody.get(i).description;
			Timestamp time = feedbackBody.get(i).timeCreated;
			FeedbackName feedbackName = new FeedbackName(memberName, description, time);
			feedback.add(feedbackName);
		}
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
