package wpi.xojo.g2.project.http;

public class GetAlternativesRequest {
	public int choiceID;
	
	public String toString() {
		return "Get alternatives from Choice: " + choiceID;
	}
	
	public GetAlternativesRequest(int choiceID) {
		this.choiceID = choiceID;
	}
	
	public GetAlternativesRequest() {
	}
}
