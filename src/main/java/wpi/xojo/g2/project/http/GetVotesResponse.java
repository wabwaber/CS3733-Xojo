package wpi.xojo.g2.project.http;

import java.util.List;

import wpi.xojo.g2.project.model.Vote;
import wpi.xojo.g2.project.model.VoteName;


public class GetVotesResponse {
	public final List<VoteName> approvals;
	public final List<VoteName> disapprovals;
	public final String error;
	public final int httpCode;
	
	public GetVotesResponse(String error, int code) {
		this.approvals = null;
		this.disapprovals = null;
		this.error = error;
		this.httpCode = code;
	}
	
	public GetVotesResponse(List<VoteName> approvals, List<VoteName> disapprovals) {
		this.approvals = approvals;
		this.disapprovals = disapprovals;
		this.error = "";
		this.httpCode = 200;
	}
	
	
}
