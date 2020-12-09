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

public class GetFeedbackHandler implements RequestHandler<GetFeedbackRequest,GetFeedbackResponse> {
	LambdaLogger logger;
	
	void getFeedbackStuff(String alternativeID, List<Feedback> feedbackBodies, List<String> names) throws Exception {
		FeedbackDAO dao = new FeedbackDAO();
		dao.getFeedback(alternativeID, feedbackBodies, names);
	}

	@Override
	public GetFeedbackResponse handleRequest(GetFeedbackRequest req, Context context) {
		
		logger = context.getLogger();
		logger.log(req.toString());
		
		GetFeedbackResponse response;
		try {
			
			List<Feedback> feedbackBodies = new ArrayList<Feedback>();
			List<String> names = new ArrayList<String>();
			getFeedbackStuff(req.alternativeID, feedbackBodies, names);
			response = new GetFeedbackResponse(feedbackBodies, names);
			
		} catch (Exception e) {
			response = new GetFeedbackResponse("Unable to get feedback: " + req.alternativeID + " (" + e.getMessage() + ")", 400);
		}
	
		return response;
	}
	
	
}
