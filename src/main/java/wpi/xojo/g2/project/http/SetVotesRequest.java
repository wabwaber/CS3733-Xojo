package wpi.xojo.g2.project.http;

public class SetVotesRequest {
	public String choiceID;
	public String memberID;
	
	public String toString() {
		return "Setting votes for: " + memberID;
	}
	
	
	public SetVotesRequest(String choiceID, String memberID) {
		this.choiceID = choiceID;
		this.memberID = memberID;
	}
	
	public SetVotesRequest() {
	}
}
