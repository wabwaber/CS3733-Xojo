package wpi.xojo.g2.project;

import java.util.List;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import wpi.xojo.g2.project.db.AlternativeDAO;
import wpi.xojo.g2.project.http.GetVotesRequest;
import wpi.xojo.g2.project.http.GetVotesResponse;
import wpi.xojo.g2.project.model.Vote;

public class GetVotesHandler implements RequestHandler<GetVotesRequest, GetVotesResponse> {
	
	public LambdaLogger logger;

	List<Vote> getVotes(String ID, boolean isUpvote) throws Exception {
		AlternativeDAO dao = new AlternativeDAO();
		return dao.getAlternativeVotes(ID, isUpvote);
	}
	
	@Override
	public GetVotesResponse handleRequest(GetVotesRequest req, Context context) {
		logger = context.getLogger();
		logger.log("Loading Java Lambda handler to list alternatives");
		
		GetVotesResponse response;
		
		try {
			List<Vote> approvals = getVotes(req.alternativeID, true);
			List<Vote> disapprovals = getVotes(req.alternativeID, false);
			response = new GetVotesResponse(approvals, disapprovals);
		} catch (Exception e) {
			response = new GetVotesResponse(e.getMessage(), 403);
		}
		
		return response;
	}
	
}
