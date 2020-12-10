package wpi.xojo.g2.project.http;

public class AddFeedbackRequest {
	public String alternativeID;
	public String memberID;
	public String feedbackDesc;
	
	public AddFeedbackRequest(String alternativeID, String memberID, String feedbackDesc) {
		this.alternativeID = alternativeID;
		this.memberID = memberID;
		this.feedbackDesc = feedbackDesc;
	}
	
	public AddFeedbackRequest() {
	}
	
	public String toString() {
		return "Feedback Desc: " + feedbackDesc;
	}
}
