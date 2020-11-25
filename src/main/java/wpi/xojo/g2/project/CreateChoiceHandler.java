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
	
	Choice createChoice(int ID, String name, String desc, int max) throws Exception {
		ChoiceDAO dao = new ChoiceDAO();
		Choice exists = dao.getChoice(ID);
		Choice choice = new Choice(ID,name,desc,max);
		if (exists == null) {
			dao.addChoice(choice);
			return choice;
		} else {
			return null;
		}
	}

    @Override
    public CreateChoiceResponse handleRequest(CreateChoiceRequest req, Context context) {

    	logger = context.getLogger();
		logger.log(req.toString());
		
		CreateChoiceResponse response;
		try {
			Choice choice = createChoice(req.choiceID, req.choiceName, req.choiceDesc, req.maxMembers);
			if (choice != null) {
				response = new CreateChoiceResponse(choice);
			} else {
				response = new CreateChoiceResponse("" + req.choiceID, 422);
			}
			
		} catch (Exception e) {
			response = new CreateChoiceResponse("Unable to create choice: " + req.choiceName + " (" + e.getMessage() + ")", 400);
		}
		
		return response;
    }

}
