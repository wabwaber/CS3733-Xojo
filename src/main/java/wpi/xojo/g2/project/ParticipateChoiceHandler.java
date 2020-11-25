package wpi.xojo.g2.project;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import wpi.xojo.g2.project.db.TeamMemberDAO;
import wpi.xojo.g2.project.http.CreateChoiceResponse;
import wpi.xojo.g2.project.http.ParticipateChoiceRequest;
import wpi.xojo.g2.project.http.ParticipateChoiceResponce;
import wpi.xojo.g2.project.model.TeamMember;

public class ParticipateChoiceHandler implements RequestHandler<ParticipateChoiceRequest,ParticipateChoiceResponce> {
	
	LambdaLogger logger;
	
	boolean createMember(int ID, String name, int teamID, String pass) throws Exception {
		TeamMemberDAO dao = new TeamMemberDAO();
		TeamMember exists = dao.getMember(ID);
		TeamMember member = new TeamMember(ID, name, teamID, pass);
		if (exists == null) {
			return dao.addMember(member);
		} else {
			return false;
		}
	}
	
	boolean hasMember(int ID) throws Exception {
		TeamMemberDAO dao = new TeamMemberDAO();
		TeamMember exists = dao.getMember(ID);
		return exists != null;
	}

	@Override
	public ParticipateChoiceResponce handleRequest(ParticipateChoiceRequest req, Context context) {
		
		logger = context.getLogger();
		logger.log(req.toString());
		
		ParticipateChoiceResponce response;
		try {
			if (createMember(req.memberID, req.name, req.choiceID, req.password)) {
				response = new ParticipateChoiceResponce("" + req.memberID);
			} else if (hasMember(req.memberID)){
				response = new ParticipateChoiceResponce("" + req.memberID);
			} else {
				response = new ParticipateChoiceResponce("" + req.memberID, 422);
			}
			
		} catch (Exception e) {
			response = new ParticipateChoiceResponce("Unable to participate member: " + req.memberID + " (" + e.getMessage() + ")", 400);
		}
		
		return response;
	}
}
