package wpi.xojo.g2.project.http;

public class GetChoiceRequest {
	public String choiceID;
	
	public String toString() {
		return "Getting choice: " + choiceID;
	}
	
	public GetChoiceRequest(String choiceID) {
		this.choiceID = choiceID;
	}
	
	public GetChoiceRequest() {
	}
}
