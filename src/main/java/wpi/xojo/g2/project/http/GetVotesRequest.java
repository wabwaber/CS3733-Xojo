package wpi.xojo.g2.project.http;

public class GetVotesRequest {

	public String alternativeID;
	
	public String toString() {
		return "Getting votes for alternative: " + alternativeID;
	}
	
	public GetVotesRequest(String alternativeID) {
		this.alternativeID = alternativeID;
	}
	
	public GetVotesRequest() {
	}
	
}
