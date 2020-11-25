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
    public TeamMember getMember(int ID) throws Exception {
    	try {
            TeamMember member = null;
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tblName + " WHERE name=?;");
            ps.setInt(1,  ID);
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
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tblName + " WHERE memberID = ?;");
            ps.setInt(1, member.memberID);
            ResultSet resultSet = ps.executeQuery();
            
            // already present?
            if (resultSet.next()) {
                resultSet.close();
                return false;
            }
            
            resultSet.close();

            ps = conn.prepareStatement("INSERT INTO " + tblName + " (memberID,name_str,choice_ID,password_str) values(?,?,?,?);");
            ps.setInt(1, member.memberID);
            ps.setString(2, member.name);
            ps.setInt(3, member.choiceID);
            ps.setString(4, member.password);
            ps.execute();
            return true;

        } catch (Exception e) {
            throw new Exception("Failed to insert choice: " + e.getMessage());
        }
    }
    
    public static TeamMember generateMember(ResultSet resultSet) throws Exception {
    	int ID  = resultSet.getInt("memberID");
        String name = resultSet.getString("name_Str");
        int choiceID = resultSet.getInt("choiceID");
        String password = resultSet.getString("password_str");
        
        return new TeamMember(ID, name, choiceID, password);
    }
}
