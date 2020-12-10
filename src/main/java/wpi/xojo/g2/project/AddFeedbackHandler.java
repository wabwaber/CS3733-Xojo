package wpi.xojo.g2.project;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import wpi.xojo.g2.project.db.FeedbackDAO;
import wpi.xojo.g2.project.http.AddFeedbackRequest;
import wpi.xojo.g2.project.http.AddFeedbackResponse;
import wpi.xojo.g2.project.model.Feedback;

public class AddFeedbackHandler implements RequestHandler<AddFeedbackRequest,AddFeedbackResponse> {
	
	LambdaLogger logger;
	
	Feedback addFeedback(String alternativeID, String memberID, String desc) throws Exception {
		FeedbackDAO dao = new FeedbackDAO();
		Feedback feedback =  new Feedback(alternativeID, memberID, desc);
		if (dao.addFeedback(feedback)) {
			return feedback;
		}
		return null;
	}
	
	@Override
	public AddFeedbackResponse handleRequest(AddFeedbackRequest req, Context context) {
		
		logger = context.getLogger();
		logger.log(req.toString());
		
		AddFeedbackResponse response;
		try {
			Feedback feedback = addFeedback(req.alternativeID, req.memberID, req.feedbackDesc);
			if (feedback != null) {
				response = new AddFeedbackResponse(feedback);
			} else {
				response = new AddFeedbackResponse("Failed to add feedback: " + req.feedbackDesc, 422);
			}
		} catch (Exception e) {
			response = new AddFeedbackResponse("Unable to add feedback: " +  req.feedbackDesc + " (" + e.getMessage() + ")", 400);
		}
		
		return response;
		
	}
}
