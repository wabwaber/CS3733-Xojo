package wpi.xojo.g2.project;

import java.util.ArrayList;
import java.util.List;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import wpi.xojo.g2.project.db.FeedbackDAO;
import wpi.xojo.g2.project.http.GetFeedbackRequest;
import wpi.xojo.g2.project.http.GetFeedbackResponse;
import wpi.xojo.g2.project.model.Feedback;
import wpi.xojo.g2.project.model.FeedbackName;

public class GetFeedbackHandler implements RequestHandler<GetFeedbackRequest,GetFeedbackResponse> {
	LambdaLogger logger;
	
	List<FeedbackName> getFeedback(String alternativeID) throws Exception {
		FeedbackDAO dao = new FeedbackDAO();
		return dao.getFeedback(alternativeID);
	}

	@Override
	public GetFeedbackResponse handleRequest(GetFeedbackRequest req, Context context) {
		
		logger = context.getLogger();
		logger.log(req.toString());
		
		GetFeedbackResponse response;
		try {
			List<FeedbackName> feedback = getFeedback(req.alternativeID);
			response = new GetFeedbackResponse(feedback);
			
		} catch (Exception e) {
			response = new GetFeedbackResponse("Unable to get feedback: " + req.alternativeID + " (" + e.getMessage() + ")", 400);
		}
	
		return response;
	}
	
	
}
