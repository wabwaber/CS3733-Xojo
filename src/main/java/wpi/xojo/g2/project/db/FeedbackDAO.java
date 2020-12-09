package wpi.xojo.g2.project.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import wpi.xojo.g2.project.model.Feedback;

public class FeedbackDAO {
	java.sql.Connection conn;
	
	public FeedbackDAO() {
		try {
    		conn = DatabaseUtil.connect();
    	} catch (Exception e) {
    		conn = null;
    	}
	}
	
	public void getFeedback(String alternativeID, List<Feedback> feedback, List<String> names) throws Exception {
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT alternativeID, M.memberID, feedbackDesc, timeCreated, memberName FROM Feedback F join TeamMember M on F.memberID = M.memberID WHERE alternativeID = ?;");
			ps.setString(1, alternativeID);
    		ResultSet resultSet = ps.executeQuery();
    		
    		while (resultSet.next()) {
    			Feedback f = FeedbackDAO.generateFeedback(resultSet);
    			feedback.add(f);
    			names.add(resultSet.getString("memberName"));
    		}
    		
    		resultSet.close();
    		ps.close();
		} catch (Exception e) {
			throw new Exception("Failed in getting feedback: " + e.getMessage());
		}
	}
	
	
	public boolean addFeedback(Feedback feedback) throws Exception {
		try {
			PreparedStatement ps = conn.prepareStatement("INSERT INTO Feedback (alternativeID,memberID,feedbackDesc,timeCreated) values(?,?,?,?);");
			ps.setString(1, feedback.alternativeID);
			ps.setString(2, feedback.memberID);
			ps.setString(3, feedback.description);
			ps.setTimestamp(4, feedback.timeCreated);
			ps.execute();
			ps.close();
			return true;
		} catch (Exception e) {
			throw new Exception("Failed to add feedback: " + e.getMessage());
		}
	}
	
	public static Feedback generateFeedback(ResultSet resultSet) throws Exception {
		String alternativeID = resultSet.getString("alternativeID");
		String memberID = resultSet.getString("memberID");
		String desc = resultSet.getString("feedbackDesc");
		Timestamp date = resultSet.getTimestamp("timeCreated");
		
		return new Feedback(alternativeID, memberID, desc, date);
	}
}
