package wpi.xojo.g2.project;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import wpi.xojo.g2.project.db.ChoiceDAO;
import wpi.xojo.g2.project.db.TeamMemberDAO;
import wpi.xojo.g2.project.http.ParticipateChoiceRequest;
import wpi.xojo.g2.project.http.ParticipateChoiceResponce;
import wpi.xojo.g2.project.model.Choice;
import wpi.xojo.g2.project.model.TeamMember;

public class ParticipateChoiceHandler implements RequestHandler<ParticipateChoiceRequest,ParticipateChoiceResponce> {
	
	LambdaLogger logger;
	
	TeamMember createGetMember(int ID, String name, int choiceID, String pass) throws Exception {
		TeamMemberDAO dao = new TeamMemberDAO();
		TeamMember exists = dao.getMember(name, choiceID);
		TeamMember member = new TeamMember(ID, name, choiceID, pass);
		boolean correctPass = (exists != null && exists.password.equals(pass));
		if (exists == null) {
			dao.addMember(member);
			return member;
		} else if (correctPass) {
			return exists;
		} else {
			return null;
		}
	}
	
	int getChoiceMax(int ID) throws Exception {
		ChoiceDAO dao = new ChoiceDAO();
		Choice choice = dao.getChoice(ID);
		if (choice == null) {
			return -1;
		} else {
			return choice.maxMembers;
		}
	}
	
	int getChoiceMemberCount(int ID) throws Exception {
		ChoiceDAO dao = new ChoiceDAO();
		Choice choice = dao.getChoice(ID);
		if (choice == null) {
			return -1;
		} else {
			int count = dao.getMemberCount(ID);
			return count;
		}
	}
	
	@Override
	public ParticipateChoiceResponce handleRequest(ParticipateChoiceRequest req, Context context) {
		
		logger = context.getLogger();
		logger.log(req.toString());
		
		ParticipateChoiceResponce response;
		try {
			int randID = (int) (Math.random() * 1000000000);
			TeamMember member = createGetMember(randID, req.name, req.choiceID, req.password);
			if (member != null) {
				response = new ParticipateChoiceResponce(member);
			} else {
				response = new ParticipateChoiceResponce("Wrong password", 401);
			}
			
		} catch (Exception e) {
			response = new ParticipateChoiceResponce("Unable to participate member: " + req.name + " (" + e.getMessage() + ")", 400);
		}
		
		return response;
	}
}
