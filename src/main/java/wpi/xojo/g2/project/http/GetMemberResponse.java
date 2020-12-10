package wpi.xojo.g2.project.http;

import wpi.xojo.g2.project.model.TeamMember;

public class GetMemberResponse {
	public final TeamMember member;
	public final String error;
	public final int httpCode;
	
	public GetMemberResponse(String error, int code) {
		this.member = null;
		this.error = error;
		this.httpCode = code;
	}
	
	public GetMemberResponse(TeamMember member) {
		this.member = member;
		this.error = "";
		this.httpCode = 200;
	}
}
