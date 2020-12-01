package wpi.xojo.g2.project.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import wpi.xojo.g2.project.model.Vote;

public class VoteDAO {
	
	java.sql.Connection conn;
	
	final String tblName = "TeamMember";   // Exact capitalization

    public VoteDAO() {
    	try  {
    		conn = DatabaseUtil.connect();
    	} catch (Exception e) {
    		conn = null;
    	}
    }
    
    public Vote getVote(String alternativeID, String memberID) throws Exception {
    	try {
    		Vote vote = null;
    		PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tblName + " WHERE alternativeID = ? and memberID = ?;");
            ps.setString(1, alternativeID);
            ps.setString(2, memberID);
            ResultSet resultSet = ps.executeQuery();
            
            if (resultSet.next()) {
            	vote = VoteDAO.generateVote(resultSet);
            }
            
            resultSet.close();
            ps.close();
            
            return vote;
    	} catch (Exception e) {
    		e.printStackTrace();
    		throw new Exception("Failed in getting vote: " + e.getMessage());
    	}
    }
    
    public static Vote generateVote(ResultSet resultSet) throws Exception {
    	String alternativeID = resultSet.getString("alternativeID");
    	String memberID = resultSet.getString("memberID");
    	boolean isUpvote = resultSet.getBoolean("isUpvote");
    	
    	return new Vote(alternativeID, memberID, isUpvote);
    }

}
