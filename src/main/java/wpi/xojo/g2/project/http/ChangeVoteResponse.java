package wpi.xojo.g2.project.http;

import wpi.xojo.g2.project.model.Vote;

public class ChangeVoteResponse {
	public final Vote vote;
	public final String error;
	public final int httpCode;
	
	public ChangeVoteResponse (String s, int code) {
		this.vote = null;
		this.error = s;
		this.httpCode = code;
	}
	
	public ChangeVoteResponse (Vote v) {
		this.vote = v;
		this.error = "";
		this.httpCode = 200;
	}
	
	public String toString() {
		if (httpCode == 200) {
			return "Response(" + vote.toString() + ")";
		}
		return "Error :" + error;
	}
}
