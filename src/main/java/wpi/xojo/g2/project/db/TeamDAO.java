package wpi.xojo.g2.project.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import wpi.xojo.g2.project.model.Alternative;
import wpi.xojo.g2.project.model.Member;
import wpi.xojo.g2.project.model.Team;

public class TeamDAO {
	java.sql.Connection conn;
	
	final String tblName = "Team";   // Exact capitalization

    public TeamDAO() {
    	try  {
    		conn = DatabaseUtil.connect();
    	} catch (Exception e) {
    		conn = null;
    	}
    }
    
    public Team getTeam(int ID) throws Exception {
    	try {
            Team team = null;
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tblName + " WHERE name=?;");
            ps.setInt(1,  ID);
            ResultSet resultSet = ps.executeQuery();
            
            while (resultSet.next()) {
                team = TeamDAO.generateTeam(resultSet);
            }
            resultSet.close();
            ps.close();
            
            return team;

        } catch (Exception e) {
        	e.printStackTrace();
            throw new Exception("Failed in getting team: " + e.getMessage());
        }
    }
    
    public List<Member> getTeamMembers(int ID) throws Exception {
    	List<Member> members = new ArrayList<>();
        try {
            Statement statement = conn.createStatement();
            String query = "SELECT * FROM Team T JOIN Member M ON T.teamID = M.teamID WHERE teamID = " + ID + ";";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
            	Member m = MemberDAO.generateMember(resultSet);
            	members.add(m);
            }
            resultSet.close();
            statement.close();
            return members;

        } catch (Exception e) {
            throw new Exception("Failed in getting members: " + e.getMessage());
        }
    }
    
    public static Team generateTeam(ResultSet resultSet) throws Exception {
    	int ID  = resultSet.getInt("teamID");
        String choiceID = resultSet.getString("choiceID");
        int maxMembers = resultSet.getInt("maxMembers");
        
        return new Team(ID, choiceID, maxMembers);
    }
}
