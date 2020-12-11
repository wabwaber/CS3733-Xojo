package wpi.xojo.g2.project;

import java.sql.Timestamp;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import wpi.xojo.g2.project.db.ChoiceDAO;
import wpi.xojo.g2.project.http.DeleteChoicesRequest;
import wpi.xojo.g2.project.http.DeleteChoicesResponse;

public class DeleteChoicesHandler implements RequestHandler<DeleteChoicesRequest,DeleteChoicesResponse> {

	LambdaLogger logger;
	
	boolean deleteChoices(Timestamp time) throws Exception {
		ChoiceDAO dao = new ChoiceDAO();
		return dao.deleteChoices(time);
	}
	
	@Override
	public DeleteChoicesResponse handleRequest(DeleteChoicesRequest req, Context context) {
		
		logger = context.getLogger();
		logger.log(req.toString());
		
		DeleteChoicesResponse response;
		
		try {
			if (deleteChoices(req.time)) {
				response = new DeleteChoicesResponse();
			} else {
				response = new DeleteChoicesResponse("Failed to delete choices", 400);
			}
		} catch (Exception e) {
			response = new DeleteChoicesResponse("Unable to delete choices (" + e.getMessage() + ")", 400);
		}
		
		return response;
	}
	 
}
