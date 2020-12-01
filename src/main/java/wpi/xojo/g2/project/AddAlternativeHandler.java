package wpi.xojo.g2.project;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import wpi.xojo.g2.project.db.AlternativeDAO;
import wpi.xojo.g2.project.http.AddAlternativeRequest;
import wpi.xojo.g2.project.http.AddAlternativeResponse;
import wpi.xojo.g2.project.model.Alternative;

public class AddAlternativeHandler implements RequestHandler<AddAlternativeRequest, AddAlternativeResponse> {
	LambdaLogger logger;
	
	Alternative addAlternative(String choiceID, String name, String desc) throws Exception {
		AlternativeDAO dao = new AlternativeDAO();
		Alternative alternative = new Alternative(choiceID, name, desc);
		if (dao.addAlternative(alternative)) {
			return alternative;
		}
		return null;
	}
	
	@Override
	public AddAlternativeResponse handleRequest(AddAlternativeRequest req, Context context) {

		logger = context.getLogger();
		logger.log(req.toString());
		
		AddAlternativeResponse response;
		try {
			Alternative alternative = addAlternative(req.choiceID, req.alternativeName,  req.alternativeDesc);
			if (alternative != null) {
				response = new AddAlternativeResponse(alternative);
			} else {
				response = new AddAlternativeResponse("Failed to add alternative: " + req.alternativeName, 422);
			}
		} catch (Exception e) {
			response = new AddAlternativeResponse("Unable to add alternative: " +  req.alternativeName + " (" + e.getMessage() + ")", 400);
		}
		
		return response;
	}
}
