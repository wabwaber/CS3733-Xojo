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
    
    public Alternative getAlternative(String ID) throws Exception {
    	try {
            Alternative alternative = null;
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tblName + " WHERE alternativeID = ?");
            ps.setString(1,  ID);
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

	public boolean addAlternative(Alternative alternative) throws Exception {
		try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tblName + " WHERE alternativeID = ?;");
            ps.setString(1, alternative.alternativeID);
            ResultSet resultSet = ps.executeQuery();
            
            if (resultSet.next()) {
                resultSet.close();
                return false;
            }
            
            resultSet.close();
            
            ps = conn.prepareStatement("INSERT INTO " + tblName + " (alternativeID,choiceID,alternativeDesc,selected) values(?,?,?,?);");
            ps.setString(1, alternative.alternativeID);
            ps.setString(2, alternative.choiceID);
            ps.setString(3, alternative.description);
            ps.setBoolean(4, alternative.selected);
            ps.execute();
            return true;
            
		} catch (Exception e) {
			throw new Exception("Failed to insert choice: " + e.getMessage());
		}
	}
	
	public static Alternative generateAlternative(ResultSet resultSet) throws Exception {
		String ID  = resultSet.getString("alternativeID");
        String choiceID = resultSet.getString("choiceID");
        String description = resultSet.getString("description_str");
        boolean selected = resultSet.getBoolean("selected");
        
        return new Alternative(ID, choiceID, description, selected);
	}
}
    
