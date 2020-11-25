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
        String description = resultSet.getString("description");
        
        return new Alternative(ID, choiceID, description);
	}

	public boolean addAlternative(Alternative choice) {
		// TODO Auto-generated method stub
		return false;
	}
}
    
