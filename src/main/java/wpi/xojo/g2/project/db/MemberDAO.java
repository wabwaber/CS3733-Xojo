package wpi.xojo.g2.project.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import wpi.xojo.g2.project.model.TeamMember;


public class MemberDAO {
	java.sql.Connection conn;
	
	final String tblName = "TeamMember";   // Exact capitalization

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
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tblName + " WHERE memberName = ? and choiceID = ?;");
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
    
    public boolean addMember(TeamMember member) throws Exception {
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tblName + " WHERE memberName = ? and choiceID = ;");
            ps.setString(1, member.name);
            ps.setString(2, member.choiceID);
            ResultSet resultSet = ps.executeQuery();
            
            // already present?
            if (resultSet.next()) {
                resultSet.close();
                return false;
            }
            
            resultSet.close();

            ps = conn.prepareStatement("INSERT INTO " + tblName + " (memberID,choiceID,memberName,memberPass) values(?,?,?,?);");
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
    
    public static TeamMember generateMember(ResultSet resultSet) throws Exception {
    	String ID  = resultSet.getString("memberID");
    	String choiceID = resultSet.getString("choiceID");
        String name = resultSet.getString("memberName");
        String password = resultSet.getString("memberPass");
        
        return new TeamMember(ID, choiceID, name, password);
    }
}
