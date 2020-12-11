package wpi.xojo.g2.project.http;

public class GetMemberRequest {
	public String memberID;
	
	public String toString() {
		return "Getting member : " + memberID; 
	}
	
	public GetMemberRequest(String memberID) {
		this.memberID = memberID;
	}
	
	public GetMemberRequest() {
	}
}
