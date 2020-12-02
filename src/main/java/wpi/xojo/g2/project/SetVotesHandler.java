package wpi.xojo.g2.project;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import wpi.xojo.g2.project.db.VoteDAO;
import wpi.xojo.g2.project.http.SetVotesRequest;
import wpi.xojo.g2.project.http.SetVotesResponse;

public class SetVotesHandler implements RequestHandler<SetVotesRequest, SetVotesResponse> {
	
	LambdaLogger logger;
	
	boolean setVotes(String choiceID, String memberID) throws Exception {
		VoteDAO dao = new VoteDAO();
		return dao.setVotes(choiceID, memberID);
	}

	@Override
	public SetVotesResponse handleRequest(SetVotesRequest req, Context context) {
		
		logger = context.getLogger();
		logger.log(req.toString());
		
		SetVotesResponse response;
		
		try {
			if(setVotes(req.choiceID, req.memberID)) {
				response = new SetVotesResponse(200);
			} else {
				response = new SetVotesResponse("Failed to set votes for: " + req.memberID, 400);
			}
		} catch (Exception e) {
			response = new SetVotesResponse("Unable to set votes for: " + req.memberID, 400);
		}
		
		return response;
		
	}
	
	
}
