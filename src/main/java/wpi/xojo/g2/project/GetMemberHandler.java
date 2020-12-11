package wpi.xojo.g2.project;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import wpi.xojo.g2.project.db.MemberDAO;
import wpi.xojo.g2.project.http.GetChoiceRequest;
import wpi.xojo.g2.project.http.GetChoiceResponse;
import wpi.xojo.g2.project.http.GetMemberRequest;
import wpi.xojo.g2.project.http.GetMemberResponse;
import wpi.xojo.g2.project.model.Choice;
import wpi.xojo.g2.project.model.TeamMember;

public class GetMemberHandler implements RequestHandler<GetMemberRequest,GetMemberResponse> {
	
	LambdaLogger logger;
	
	public TeamMember getMember(String memberID) throws Exception {
		MemberDAO dao = new MemberDAO();
		return dao.getMember(memberID);
	}
	
	@Override
	public GetMemberResponse handleRequest(GetMemberRequest req, Context context) {
		
		logger = context.getLogger();
		logger.log("Loading Java Lambda handler to get member");
		
		GetMemberResponse response;
		
		try {
			TeamMember member = getMember(req.memberID);
			if (member != null) {
				response = new GetMemberResponse(member);
			} else {
				response = new GetMemberResponse("Member doesn't exist: " + req.memberID, 403);
			}
		} catch (Exception e) {
			response = new GetMemberResponse(e.getMessage(), 400);
		}
		
		return response;
	}
	

}
