package wpi.xojo.g2.project.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import wpi.xojo.g2.project.model.Feedback;
import wpi.xojo.g2.project.model.FeedbackName;

public class FeedbackDAO {
	java.sql.Connection conn;
	
	public FeedbackDAO() {
		try {
    		conn = DatabaseUtil.connect();
    	} catch (Exception e) {
    		conn = null;
    	}
	}
	
	public List<FeedbackName> getFeedback(String alternativeID) throws Exception {
		
		List<FeedbackName> feedback = new ArrayList<FeedbackName>();
				
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM Feedback F join TeamMember M on F.memberID = M.memberID WHERE alternativeID = ?;");
			ps.setString(1, alternativeID);
    		ResultSet resultSet = ps.executeQuery();
    		
    		while (resultSet.next()) {
    			String name = resultSet.getString("memberName");
    			String desc = resultSet.getString("feedbackDesc");
    			Timestamp time = resultSet.getTimestamp("timeCreated");
    			feedback.add(new FeedbackName(name, desc, time));
    		}
    		
    		resultSet.close();
    		ps.close();
    		
    		return feedback;
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
		Timestamp time = resultSet.getTimestamp("timeCreated");
		
		return new Feedback(alternativeID, memberID, desc, time);
	}
}
