package wpi.xojo.g2.project.http;

import wpi.xojo.g2.project.model.TeamMember;

public class ParticipateChoiceResponse {
	public final TeamMember member;
	public final boolean firstTime;
	public final String error;
	public final int httpCode;
	
	public ParticipateChoiceResponse (String error, int code) {
		this.member = null;
		this.firstTime = false;
		this.error = error;
		this.httpCode = code;
	}
	
	// 200 means success
	public ParticipateChoiceResponse (TeamMember m, boolean firstTime) {
		this.member = m;
		this.firstTime = firstTime;
		this.error = "";
		this.httpCode = 200;
	}
	
	public String toString() {
		if (httpCode == 200) {
			return "Response(" + member.toString() + ")";
		}
		return "Error :" + error;
	}
}
