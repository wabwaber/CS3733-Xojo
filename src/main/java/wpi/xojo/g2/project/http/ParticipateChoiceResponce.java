package wpi.xojo.g2.project.http;

import wpi.xojo.g2.project.model.TeamMember;

public class ParticipateChoiceResponce {
	public final TeamMember member;
	public final String error;
	public final int httpCode;
	
	public ParticipateChoiceResponce (String error, int code) {
		this.member = null;
		this.error = error;
		this.httpCode = code;
	}
	
	// 200 means success
	public ParticipateChoiceResponce (TeamMember m) {
		this.member = m;
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
