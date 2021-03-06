package wpi.xojo.g2.project;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import wpi.xojo.g2.project.db.ChoiceDAO;
import wpi.xojo.g2.project.db.MemberDAO;
import wpi.xojo.g2.project.http.ParticipateChoiceRequest;
import wpi.xojo.g2.project.http.ParticipateChoiceResponse;
import wpi.xojo.g2.project.model.Choice;
import wpi.xojo.g2.project.model.TeamMember;

public class ParticipateChoiceHandler implements RequestHandler<ParticipateChoiceRequest,ParticipateChoiceResponse> {
	
	LambdaLogger logger;
	
	TeamMember getMember(String choiceID, String name, String pass) throws Exception {
		MemberDAO dao = new MemberDAO();
		TeamMember member = dao.getMember(name, choiceID);
		boolean correctPass = (member != null && member.password.equals(pass));
		if (correctPass) {
			return member;
		}
		return null;
	}
	
	TeamMember createMember(String choiceID, String name, String pass) throws Exception {
		MemberDAO dao = new MemberDAO();
		TeamMember exists = dao.getMember(name, choiceID);
		TeamMember member = new TeamMember(choiceID, name, pass);
		if (exists == null && dao.addMember(member)) {
			return member;
		}
		return null;
	}
	
	
	@Override
	public ParticipateChoiceResponse handleRequest(ParticipateChoiceRequest req, Context context) {
		
		logger = context.getLogger();
		logger.log(req.toString());
		
		ParticipateChoiceResponse response;
		try {
			TeamMember member = getMember(req.choiceID, req.name, req.password);
			if (member != null) {
				response = new ParticipateChoiceResponse(member, false);
			} else {
				member = createMember(req.choiceID, req.name, req.password);
				if (member != null) {
					response = new ParticipateChoiceResponse(member, true);
				} else {
					response = new ParticipateChoiceResponse("Wrong password, choice full or choice does not exist", 400);
				}
			}
			
		} catch (Exception e) {
			response = new ParticipateChoiceResponse("Unable to participate member: " + req.name + " (" + e.getMessage() + ")", 400);
		}
		
		return response;
	}
}
