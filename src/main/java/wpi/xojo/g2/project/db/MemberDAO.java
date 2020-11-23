package wpi.xojo.g2.project.db;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import wpi.xojo.g2.project.model.Member;


public class MemberDAO {
	java.sql.Connection conn;
	
	final String tblName = "Member";   // Exact capitalization

    public MemberDAO() {
    	try  {
    		conn = DatabaseUtil.connect();
    	} catch (Exception e) {
    		conn = null;
    	}
    }
    public Member getMember(int ID) throws Exception {
    	try {
            Member member = null;
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tblName + " WHERE name=?;");
            ps.setInt(1,  ID);
            ResultSet resultSet = ps.executeQuery();
            
            while (resultSet.next()) {
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
    
    public static Member generateMember(ResultSet resultSet) throws Exception {
    	int ID  = resultSet.getInt("memberID");
        String name = resultSet.getString("name");
        String teamID = resultSet.getString("teamID");
        String password = resultSet.getString("password");
        
        return new Member(ID, name, teamID, password);
    }
}