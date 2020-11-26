package wpi.xojo.g2.project.http;

public class GetChoiceRequest {
	public int choiceID;
	
	public String toString() {
		return "Getting choice: " + choiceID;
	}
	
	public GetChoiceRequest(int choiceID) {
		this.choiceID = choiceID;
	}
	
	public GetChoiceRequest() {
	}
}
