package wpi.xojo.g2.project.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import wpi.xojo.g2.project.model.Alternative;

public class AlternativeDAO {
	java.sql.Connection conn;
	
	final String tblName = "Alternative";   // Exact capitalization

    public AlternativeDAO() {
    	try  {
    		conn = DatabaseUtil.connect();
    	} catch (Exception e) {
    		conn = null;
    	}
    }
    
    public Alternative getAlternative(int ID) throws Exception {
    	try {
            Alternative alternative = null;
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tblName + " WHERE name=?;");
            ps.setInt(1,  ID);
            ResultSet resultSet = ps.executeQuery();
            
            while (resultSet.next()) {
                alternative = AlternativeDAO.generateAlternative(resultSet);
            }
            resultSet.close();
            ps.close();
            
            return alternative;

        } catch (Exception e) {
        	e.printStackTrace();
            throw new Exception("Failed in getting alternative: " + e.getMessage());
        }
    }

	public static Alternative generateAlternative(ResultSet resultSet) throws Exception {
		int ID  = resultSet.getInt("alternativeID");
        int choiceID = resultSet.getInt("choiceID");
        String description = resultSet.getString("description_str");
        
        return new Alternative(ID, choiceID, description);
	}

	public boolean addAlternative(Alternative alternative) throws Exception {
		try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tblName + " WHERE alternativeID = ?;");
            ps.setInt(1, alternative.alternativeID);
            ResultSet resultSet = ps.executeQuery();
            
            if (resultSet.next()) {
                resultSet.close();
                return false;
            }
            
            resultSet.close();
            
            ps = conn.prepareStatement("INSERT INTO " + tblName + " (alternativeID,choiceID,description_str,selected) values(?,?,?,?);");
            ps.setInt(1, alternative.alternativeID);
            ps.setInt(2, alternative.choiceID);
            ps.setString(3, alternative.description);
            ps.setBoolean(4, alternative.selected);
            ps.execute();
            return true;
            
		} catch (Exception e) {
			throw new Exception("Failed to insert choice: " + e.getMessage());
		}
	}
}
    
