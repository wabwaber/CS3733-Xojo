package wpi.xojo.g2.project.http;

public class GetAlternativesRequest {
	public String choiceID;
	
	public String toString() {
		return "Get alternatives from Choice: " + choiceID;
	}
	
	public GetAlternativesRequest(String choiceID) {
		this.choiceID = choiceID;
	}
	
	public GetAlternativesRequest() {
	}
}
