package wpi.xojo.g2.project;


import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;


import wpi.xojo.g2.project.db.ChoiceDAO;
import wpi.xojo.g2.project.http.CreateChoiceRequest;
import wpi.xojo.g2.project.http.CreateChoiceResponse;
import wpi.xojo.g2.project.model.Choice;

public class CreateChoiceHandler implements RequestHandler<CreateChoiceRequest,CreateChoiceResponse> {
	
	LambdaLogger logger;
	
	boolean createChoice(int ID, String name, String desc) throws Exception {
		if (logger != null) { logger.log("in createConstant"); }
		ChoiceDAO dao = new ChoiceDAO();
		Choice exists = dao.getChoice(ID);
		Choice choice = new Choice(ID,name,desc);
		if (exists == null) {
			return dao.addChoice(choice);
		} else {
			return false;
		}
	}

    @Override
    public CreateChoiceResponse handleRequest(CreateChoiceRequest req, Context context) {

    	logger = context.getLogger();
		logger.log(req.toString());
		
		CreateChoiceResponse response;
		try {
			if (createChoice(req.choiceID, req.choiceName, req.choiceDesc)) {
				response = new CreateChoiceResponse("" + req.choiceID);
			} else {
				response = new CreateChoiceResponse("" + req.choiceID, 422);
			}
			
		} catch (Exception e) {
			response = new CreateChoiceResponse("Unable to create choice: " + req.choiceName + " (" + e.getMessage() + ")", 400);
		}
		
		return response;
    }

}
