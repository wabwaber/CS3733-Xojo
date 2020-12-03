package wpi.xojo.g2.project;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import wpi.xojo.g2.project.db.VoteDAO;
import wpi.xojo.g2.project.http.ChangeVoteRequest;
import wpi.xojo.g2.project.http.ChangeVoteResponse;
import wpi.xojo.g2.project.model.Vote;

public class ChangeVoteHandler implements RequestHandler<ChangeVoteRequest,ChangeVoteResponse> {
	
	LambdaLogger logger;
	
	public Vote changeVote(String alternativeID, String memberID, Boolean isUpvote) throws Exception {
		VoteDAO dao = new VoteDAO();
		Vote vote = dao.changeVote(alternativeID, memberID, isUpvote);
		return vote;
	}

	@Override
	public ChangeVoteResponse handleRequest(ChangeVoteRequest req, Context context) {
	
		logger = context.getLogger();
		logger.log(req.toString());
		
		ChangeVoteResponse response;
		
		try {
			Vote vote = changeVote(req.alternativeID, req.memberID, req.isUpvote);
			if (vote != null) {
				response = new ChangeVoteResponse(vote);
			} else {
				response = new ChangeVoteResponse("Vote doesn't exist", 404);
			}
		} catch (Exception e) {
			response = new ChangeVoteResponse("Unable to change vote", 400);
		}
		
		return response;
	}
}
