package wpi.xojo.g2.project.http;

public class ChangeVoteRequest {
	public String alternativeID;
	public String memberID;
	
	public ChangeVoteRequest(String alternativeID, String memberID) {
		this.alternativeID = alternativeID;
		this.memberID = memberID;
	}
	
	public ChangeVoteRequest() {
	}
}
