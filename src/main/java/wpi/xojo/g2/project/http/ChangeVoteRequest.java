package wpi.xojo.g2.project.http;

public class ChangeVoteRequest {
	public String alternativeID;
	public String memberID;
	public Boolean isUpvote;
	
	public ChangeVoteRequest(String alternativeID, String memberID, Boolean isUpvote) {
		this.alternativeID = alternativeID;
		this.memberID = memberID;
		this.isUpvote = isUpvote;
	}
	
	public ChangeVoteRequest() {
	}
}
