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

	@Override
	public AddAlternativeResponse handleRequest(AddAlternativeRequest input, Context context) {
		// TODO Auto-generated method stub
		return null;
	}
	
	boolean addAlternative(int ID, String choiceID, String desc) {
		if (logger != null) { logger.log("in createConstant"); }
		AlternativeDAO dao = new AlternativeDAO();
		Alternative exists = dao.getAlternative(ID);
		Alternative choice = new Alternative(ID,choiceID,desc);
		if (exists == null) {
			return dao.addAlternative(choice);
		} else {
			return false;
		}
	}
	}
}
