package wpi.xojo.g2.project;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import wpi.xojo.g2.project.db.AlternativeDAO;
import wpi.xojo.g2.project.db.ChoiceDAO;
import wpi.xojo.g2.project.http.CompleteChoiceRequest;
import wpi.xojo.g2.project.http.CompleteChoiceResponse;

public class CompleteChoiceHandler implements RequestHandler<CompleteChoiceRequest, CompleteChoiceResponse> {
	
	LambdaLogger logger;

	boolean completeChoice(String choiceID, String alternativeID) throws Exception {
		ChoiceDAO cdao = new ChoiceDAO();
		AlternativeDAO adao = new AlternativeDAO();
		return (adao.selectAlternative(alternativeID) && cdao.completeChoice(choiceID));
	}
	
	@Override
	public CompleteChoiceResponse handleRequest(CompleteChoiceRequest req, Context context) {
		logger = context.getLogger();
		logger.log(req.toString());
		
		CompleteChoiceResponse response;
		
		try {
			if (completeChoice(req.choiceID, req.alternativeID)) {
				response = new CompleteChoiceResponse();
			} else {
				response = new CompleteChoiceResponse("Choice or Alternative does not exist", 422);
			}
		} catch (Exception e) {
			response = new CompleteChoiceResponse("Unable to complete choice: " + e.getMessage(), 400);
		}
		
		return response;
	}

}
