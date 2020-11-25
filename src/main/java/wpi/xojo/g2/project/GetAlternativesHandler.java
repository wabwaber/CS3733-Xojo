package wpi.xojo.g2.project;

import java.util.List;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import wpi.xojo.g2.project.db.ChoiceDAO;
import wpi.xojo.g2.project.http.GetAlternativesRequest;
import wpi.xojo.g2.project.http.GetAlternativesResponse;
import wpi.xojo.g2.project.model.Alternative;

public class GetAlternativesHandler implements RequestHandler<GetAlternativesRequest,GetAlternativesResponse>{
	
	public LambdaLogger logger;

	
	List<Alternative> getAlternatives(int ID) throws Exception {
		ChoiceDAO dao = new ChoiceDAO();
		return dao.getChoiceAlternatives(ID);
	}
	@Override
	public GetAlternativesResponse handleRequest(GetAlternativesRequest req, Context context) {
		logger = context.getLogger();
		logger.log("Loading Java Lambda handler to list alternatives");
		
		GetAlternativesResponse response;
		
		try {
			List<Alternative> list = getAlternatives(req.choiceID);
			response = new GetAlternativesResponse(list);
		} catch (Exception e) {
			response = new GetAlternativesResponse(e.getMessage(), 403);
		}
		
		return response;
	}

}
