package wpi.xojo.g2.project.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import wpi.xojo.g2.project.model.TeamMember;


public class MemberDAO {
	java.sql.Connection conn;
	
    public MemberDAO() {
    	try  {
    		conn = DatabaseUtil.connect();
    	} catch (Exception e) {
    		conn = null;
    	}
    }
    public TeamMember getMember(String name, String choiceID) throws Exception {
    	try {
            TeamMember member = null;
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM TeamMember WHERE memberName = ? and choiceID = ?;");
            ps.setString(1,  name);
            ps.setString(2, choiceID);
            ResultSet resultSet = ps.executeQuery();
            
            if (resultSet.next()) {
                member = MemberDAO.generateMember(resultSet);
            }
            resultSet.close();
            ps.close();
            
            return member;

        } catch (Exception e) {
        	e.printStackTrace();
            throw new Exception("Failed in getting member: " + e.getMessage());
        }
    }
    
    public TeamMember getMember(String memberID) throws Exception {
    	try {
            TeamMember member = null;
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM TeamMember WHERE memberID = ?;");
            ps.setString(1, memberID);
            ResultSet resultSet = ps.executeQuery();
            
            if (resultSet.next()) {
                member = MemberDAO.generateMember(resultSet);
            }
            resultSet.close();
            ps.close();
            
            return member;

        } catch (Exception e) {
        	e.printStackTrace();
            throw new Exception("Failed in getting member: " + e.getMessage());
        }
    }
    
    public boolean addMember(TeamMember member) throws Exception {
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM TeamMember WHERE memberName = ? and choiceID = ?;");
            ps.setString(1, member.name);
            ps.setString(2, member.choiceID);
            ResultSet resultSet = ps.executeQuery();
            
            // already present?
            if (resultSet.next()) {
                resultSet.close();
                return false;
            }
            
            resultSet.close();
            
            if (countCurrMembers(member.choiceID) >= getMaxMembers(member.choiceID)) {
            	return false;
            }

            ps = conn.prepareStatement("INSERT INTO TeamMember (memberID,choiceID,memberName,memberPass) values(?,?,?,?);");
            ps.setString(1, member.memberID);
            ps.setString(2, member.choiceID);
            ps.setString(3, member.name);
            ps.setString(4, member.password);
            ps.execute();
            return true;

        } catch (Exception e) {
            throw new Exception("Failed to insert choice: " + e.getMessage());
        }
    }
    
    public int countCurrMembers(String choiceID) throws Exception {
    	try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM TeamMember choiceID = ?;");
			ps.setString(1, choiceID);
		    ResultSet resultSet = ps.executeQuery();
		    int count = 0;
		    
		    while (resultSet.next()) {
		        count++;
		    }
		    
		    resultSet.close();
		    ps.close();
		    return count;
		    
    	} catch (Exception e) {
    		throw new Exception("Failed to count current members: " + e.getMessage());
    	}
    }
    
    public int getMaxMembers(String choiceID) throws Exception {
    	try {
    		PreparedStatement ps = conn.prepareStatement("SELECT * FROM Choice WHERE choiceID = ?;");
			ps.setString(1, choiceID);
		    ResultSet resultSet = ps.executeQuery();
		    
		    // already present?
		    if (resultSet.next()) {
		    	int max = resultSet.getInt("maxMembers");
		    	resultSet.close();
			    ps.close();
		        return max;
		    }
		    
		    resultSet.close();
		    ps.close();
		    return -1;
    	} catch (Exception e) {
    		throw new Exception("Failed to count current members: " + e.getMessage());
    	}
    }
    
    public boolean deleteMembers(String choiceID) throws Exception {
    	try {
    		PreparedStatement ps = conn.prepareStatement("DELETE FROM TeamMember WHERE choiceID = ?");
    		ps.setString(1, choiceID);
    		ps.executeUpdate();
    		return true;
    	} catch (Exception e) {
    		throw new Exception("Failed in deleting feedback: " + e.getMessage());
    	}
    }
    
    public static TeamMember generateMember(ResultSet resultSet) throws Exception {
    	String ID  = resultSet.getString("memberID");
    	String choiceID = resultSet.getString("choiceID");
        String name = resultSet.getString("memberName");
        String password = resultSet.getString("memberPass");
        
        return new TeamMember(ID, choiceID, name, password);
    }
}
