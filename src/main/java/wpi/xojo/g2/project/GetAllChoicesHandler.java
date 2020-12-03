package wpi.xojo.g2.project;

import java.util.List;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import wpi.xojo.g2.project.db.ChoiceDAO;
import wpi.xojo.g2.project.http.GetAllChoicesResponse;
import wpi.xojo.g2.project.model.Choice;

public class GetAllChoicesHandler implements RequestHandler<Object,GetAllChoicesResponse> {
	
	public LambdaLogger logger;
	
	List<Choice> getAllChoices() throws Exception {
		ChoiceDAO dao = new ChoiceDAO();
		return dao.getAllChoices();
	}

	@Override
	public GetAllChoicesResponse handleRequest(Object input, Context context) {
		
		logger = context.getLogger();
		logger.log("Loading Java Lambda handler to list alternatives");
		
		GetAllChoicesResponse response;
		
		try {
			List<Choice> list =  getAllChoices();
			response = new GetAllChoicesResponse(list);
		} catch (Exception e) {
			response = new GetAllChoicesResponse(e.getMessage(), 403);
		}
		
		return response;
	}
	
	
}
