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
    
    public Choice getChoice(int ID) throws Exception {
    	try {
            Choice choice = null;
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tblName + " WHERE choiceID=?;");
            ps.setInt(1,  ID);
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
    
    public boolean addChoice(Choice choice) throws Exception {
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tblName + " WHERE choiceID = ?;");
            ps.setInt(1, choice.choiceID);
            ResultSet resultSet = ps.executeQuery();
            
            // already present?
            if (resultSet.next()) {
                resultSet.close();
                return false;
            }
            
            resultSet.close();

            ps = conn.prepareStatement("INSERT INTO " + tblName + " (choiceID,name_str,description_str,date_completed) values(?,?,?,?);");
            ps.setInt(1, choice.choiceID);
            ps.setString(2, choice.name);
            ps.setString(3, choice.description);
            ps.setDate(4, choice.dateCompleted);
            ps.execute();
            return true;

        } catch (Exception e) {
            throw new Exception("Failed to insert choice: " + e.getMessage());
        }
    }
    
    public List<Alternative> getChoiceAlternatives(int ID) throws Exception {
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
        int ID  = resultSet.getInt("choiceID");
        String name = resultSet.getString("name_str");
        String description = resultSet.getString("description_str");
        Date date = resultSet.getDate("date_completed");
        
        return new Choice (ID, name, description, date);
    }
}
