package wpi.xojo.g2.project;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import wpi.xojo.g2.project.db.ChoiceDAO;
import wpi.xojo.g2.project.http.GetChoiceRequest;
import wpi.xojo.g2.project.http.GetChoiceResponse;
import wpi.xojo.g2.project.model.Choice;

public class GetChoiceHandler implements RequestHandler<GetChoiceRequest,GetChoiceResponse> {
	
	public LambdaLogger logger;

	public Choice getChoice(String ID) throws Exception {
		ChoiceDAO dao = new ChoiceDAO();
		return dao.getChoice(ID);
	}
	
	@Override
	public GetChoiceResponse handleRequest(GetChoiceRequest req, Context context) {
		logger = context.getLogger();
		logger.log("Loading Java Lambda handler to get choice");
		
		GetChoiceResponse response;
		
		try {
			Choice choice = getChoice(req.choiceID);
			if (choice != null) {
				response = new GetChoiceResponse(choice);
			} else {
				response = new GetChoiceResponse("Choice doesn't exist: " + req.choiceID, 403);
			}
		} catch (Exception e) {
			response = new GetChoiceResponse(e.getMessage(), 400);
		}
		
		return response;
	}

}
