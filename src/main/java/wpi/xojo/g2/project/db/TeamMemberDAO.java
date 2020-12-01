package wpi.xojo.g2.project.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import wpi.xojo.g2.project.model.TeamMember;


public class TeamMemberDAO {
	java.sql.Connection conn;
	
	final String tblName = "TeamMember";   // Exact capitalization

    public TeamMemberDAO() {
    	try  {
    		conn = DatabaseUtil.connect();
    	} catch (Exception e) {
    		conn = null;
    	}
    }
    public TeamMember getMember(String name, String choiceID) throws Exception {
    	try {
            TeamMember member = null;
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tblName + " WHERE name_str = ? and choiceID = ?;");
            ps.setString(1,  name);
            ps.setString(2, choiceID);
            ResultSet resultSet = ps.executeQuery();
            
            while (resultSet.next()) {
                member = TeamMemberDAO.generateMember(resultSet);
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
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tblName + " WHERE name_str = ? and choiceID = ;");
            ps.setString(1, member.name);
            ps.setString(2, member.choiceID);
            ResultSet resultSet = ps.executeQuery();
            
            // already present?
            if (resultSet.next()) {
                resultSet.close();
                return false;
            }
            
            resultSet.close();

            ps = conn.prepareStatement("INSERT INTO " + tblName + " (memberID,choiceID,name_str,password_str) values(?,?,?,?);");
            ps.setString(1, member.memberID);
            ps.setString(3, member.choiceID);
            ps.setString(2, member.name);
            ps.setString(4, member.password);
            ps.execute();
            return true;

        } catch (Exception e) {
            throw new Exception("Failed to insert choice: " + e.getMessage());
        }
    }
    
    public static TeamMember generateMember(ResultSet resultSet) throws Exception {
    	String ID  = resultSet.getString("memberID");
        String name = resultSet.getString("name_Str");
        String choiceID = resultSet.getString("choiceID");
        String password = resultSet.getString("password_str");
        
        return new TeamMember(ID, choiceID, name, password);
    }
}
