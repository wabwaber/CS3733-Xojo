package wpi.xojo.g2.project;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import wpi.xojo.g2.project.db.TeamMemberDAO;
import wpi.xojo.g2.project.http.ParticipateChoiceRequest;
import wpi.xojo.g2.project.http.ParticipateChoiceResponce;
import wpi.xojo.g2.project.model.TeamMember;

public class ParticipateChoiceHandler implements RequestHandler<ParticipateChoiceRequest,ParticipateChoiceResponce> {
	
	LambdaLogger logger;
	
	TeamMember createGetMember(int ID, String name, int choiceID, String pass) throws Exception {
		TeamMemberDAO dao = new TeamMemberDAO();
		TeamMember exists = dao.getMember(ID);
		TeamMember member = new TeamMember(ID, name, choiceID, pass);
		if (exists == null) {
			return member;
		} else {
			return exists;
		}
	}
	
	@Override
	public ParticipateChoiceResponce handleRequest(ParticipateChoiceRequest req, Context context) {
		
		logger = context.getLogger();
		logger.log(req.toString());
		
		ParticipateChoiceResponce response;
		try {
			
			TeamMember member = createGetMember(req.memberID, req.name, req.choiceID, req.password);
			response = new ParticipateChoiceResponce(member);
			
		} catch (Exception e) {
			response = new ParticipateChoiceResponce("Unable to participate member: " + req.memberID + " (" + e.getMessage() + ")", 400);
		}
		
		return response;
	}
}
