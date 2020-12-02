package wpi.xojo.g2.project.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import wpi.xojo.g2.project.model.Alternative;
import wpi.xojo.g2.project.model.Vote;

public class VoteDAO {
	
	java.sql.Connection conn;
	
	final String tblName = "Vote";   // Exact capitalization

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
    
    public boolean addVote(Vote vote) throws Exception {
    	try {
    		PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tblName + " WHERE alternativeID = ? and memberID = ?;");
            ps.setString(1, vote.alternativeID);
            ps.setString(2, vote.memberID);
            ResultSet resultSet = ps.executeQuery();
            
            // already present?
            if (resultSet.next()) {
                resultSet.close();
                return false;
            }
            
            resultSet.close();
            
            ps = conn.prepareStatement("INSERT INTO " + tblName + " (alternativeID,memberID,isUpvote) values(?,?,?);");
            ps.setString(1, vote.alternativeID);
            ps.setString(2, vote.memberID);
            ps.setObject(3, vote.isUpvote);
            ps.execute();
            return true;
            
    	} catch (Exception e) {
            throw new Exception("Failed to insert vote: " + e.getMessage());
        }
    }
    
    public boolean changeVote(String alternativeID, String memberID, Boolean isUpvote) throws Exception {
    	try {
    		PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tblName + " WHERE alternativeID = ? and memberID = ?;");
            ps.setString(1, alternativeID);
            ps.setString(2, memberID);
            ResultSet resultSet = ps.executeQuery();
            
            if (resultSet.next()) {
            	ps.close();
            	ps = conn.prepareStatement("UPDATE " + tblName + " SET isUpvote = ? WHERE alternativeID = ? and memberID = ?;");
            	ps.setBoolean(1, isUpvote);
            	ps.setString(2, alternativeID);
                ps.setString(3, memberID);
                return true;
            }
            
            ps.close();
            return false;
    	} catch (Exception e) {
            throw new Exception("Failed to update vote: " + e.getMessage());
        }
    }
    
    public boolean setVotes(String choiceID, String memberID) throws Exception {
    	try {
    		PreparedStatement ps = conn.prepareStatement("SELECT * FROM Choice WHERE choiceID = ?");
            ps.setString(1, choiceID);
            ResultSet resultSet = ps.executeQuery();
            
            if (!resultSet.next()) {
            	resultSet.close();
            	ps.close();
            	return false;
            }
            
            resultSet.close();
            ps.close();
            ps = conn.prepareStatement("SELECT * FROM TeamMember WHERE memberID = ?");
            ps.setString(1, memberID);
            resultSet = ps.executeQuery();
            
            if (!resultSet.next()) {
            	resultSet.close();
            	ps.close();
            	return false;
            }
            
            resultSet.close();
        	ps.close();
    		
    		ChoiceDAO dao = new ChoiceDAO();
    		List<Alternative> alternatives = dao.getChoiceAlternatives(choiceID);
    		for (Alternative a: alternatives) {
    			Vote v = new Vote(a.alternativeID, memberID, null);
    			addVote(v);
    		}
    		return true;
    	} catch (Exception e) {
    		throw new Exception("Failed to update vote: " + e.getMessage());
    	}
    }
    
    public static Vote generateVote(ResultSet resultSet) throws Exception {
    	String alternativeID = resultSet.getString("alternativeID");
    	String memberID = resultSet.getString("memberID");
    	Boolean isUpvote = resultSet.getBoolean("isUpvote");
    	
    	return new Vote(alternativeID, memberID, isUpvote);
    }

}