package wpi.xojo.g2.project.http;

import java.util.List;

public class GetVotesResponse {
	public final List<String> approvals;
	public final List<String> disapprovals;
	public final String error;
	public final int httpCode;
	
	public GetVotesResponse(String error, int code) {
		this.approvals = null;
		this.disapprovals = null;
		this.error = error;
		this.httpCode = code;
	}
	
	public GetVotesResponse(List<String> approvals, List<String> disapprovals) {
		this.approvals = approvals;
		this.disapprovals = disapprovals;
		this.error = "";
		this.httpCode = 200;
	}
	
	
}
