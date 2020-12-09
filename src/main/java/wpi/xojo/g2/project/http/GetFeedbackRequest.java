package wpi.xojo.g2.project.http;

public class GetFeedbackRequest {
	public String alternativeID;
	
	public String toString() {
		return "Getting feedback for: " + alternativeID;
	}
	
	public GetFeedbackRequest(String alternativeID) {
		this.alternativeID = alternativeID;
	}
	
	public GetFeedbackRequest() {
	}
}
