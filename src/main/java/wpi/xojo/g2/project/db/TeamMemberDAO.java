package wpi.xojo.g2.project.db;

import java.sql.Date;
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
    
    public static TeamMember generateMember(ResultSet resultSet) throws Exception {
    	int ID  = resultSet.getInt("memberID");
        String name = resultSet.getString("name_Str");
        int choiceID = resultSet.getInt("choiceID");
        String password = resultSet.getString("password_str");
        
        return new TeamMember(ID, name, choiceID, password);
    }
}
