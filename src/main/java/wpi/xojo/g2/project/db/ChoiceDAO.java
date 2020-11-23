package wpi.xojo.g2.project.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import wpi.xojo.g2.project.model.Alternative;
import wpi.xojo.g2.project.model.Choice;


public class ChoiceDAO {
	java.sql.Connection conn;
	
	final String tblName = "Choice";   // Exact capitalization

    public ChoiceDAO() {
    	try  {
    		conn = DatabaseUtil.connect();
    	} catch (Exception e) {
    		conn = null;
    	}
    }
    
    public Choice getChoice(String ID) throws Exception {
    	try {
            Choice choice = null;
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tblName + " WHERE choiceID=?;");
            ps.setString(1,  ID);
            ResultSet resultSet = ps.executeQuery();
            
            while (resultSet.next()) {
                choice = ChoiceDAO.generateChoice(resultSet);
            }
            resultSet.close();
            ps.close();
            
            return choice;

        } catch (Exception e) {
        	e.printStackTrace();
            throw new Exception("Failed in getting choice: " + e.getMessage());
        }
    }
    
    public List<Alternative> getChoiceAlternatives(String ID) throws Exception {
    	List<Alternative> alternatives = new ArrayList<>();
        try {
            Statement statement = conn.createStatement();
            String query = "SELECT * FROM Choice C JOIN Alternative A ON C.choiceID = A.choiceID WHERE choiceID = " + ID + ";";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
            	Alternative c = AlternativeDAO.generateAlternative(resultSet);
            	alternatives.add(c);
            }
            resultSet.close();
            statement.close();
            return alternatives;

        } catch (Exception e) {
            throw new Exception("Failed in getting alternatives: " + e.getMessage());
        }
    }
    
    public static Choice generateChoice(ResultSet resultSet) throws Exception {
        String ID  = resultSet.getString("choiceID");
        String name = resultSet.getString("name");
        String description = resultSet.getString("description");
        Date date = resultSet.getDate("date_completed");
        
        return new Choice (ID, name, description, date);
    }
}
