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
    
    /**
     * Gets the choice from the database
     * @param ID the choiceID
     * @return The choice with the corresponding choiceID
     * @throws Exception
     */
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
    
    /**
     * Adds a choice to the database
     * @param choice The choice to be added
     * @return Whether or not the adding was successful
     * @throws Exception
     */
    public boolean addChoice(Choice choice) throws Exception {
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tblName + " WHERE choiceID = ?;");
            ps.setString(1, choice.choiceID);
            ResultSet resultSet = ps.executeQuery();
            
            // already present?
            if (resultSet.next()) {
                resultSet.close();
                return false;
            }
            
            resultSet.close();

            ps = conn.prepareStatement("INSERT INTO " + tblName + " (choiceID,choiceName,choiceDesc,maxMembers,dateCreated) values(?,?,?,?,?);");
            ps.setString(1, choice.choiceID);
            ps.setString(2, choice.name);
            ps.setString(3, choice.description);
            ps.setInt(4, choice.maxMembers);
            ps.setTimestamp(5, choice.dateCreated);
            ps.execute();
            return true;

        } catch (Exception e) {
            throw new Exception("Failed to insert choice: " + e.getMessage());
        }
    }
    
    
    /**
     * Gets a list of alternatives for a given choice
     * @param ID The choiceID
     * @return A list of alternatives associated with the choice
     * @throws Exception
     */
    public List<Alternative> getChoiceAlternatives(String ID) throws Exception {
    	List<Alternative> alternatives = new ArrayList<Alternative>();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Alternative WHERE choiceID = ?;");
            ps.setString(1, ID);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
            	Alternative a = AlternativeDAO.generateAlternative(resultSet);
            	alternatives.add(a);
            }
            resultSet.close();
            ps.close();
            return alternatives;

        } catch (Exception e) {
            throw new Exception("Failed in getting alternatives: " + e.getMessage());
        }
    }
    
    public List<Choice> getAllChoices() throws Exception {
    	List<Choice> choices = new ArrayList<Choice>();
    	try {
    		PreparedStatement ps = conn.prepareStatement("SELECT * FROM Choice;");
    		ResultSet resultSet = ps.executeQuery();
    		
    		while (resultSet.next()) {
    			Choice c = ChoiceDAO.generateChoice(resultSet);
    			choices.add(c);
    		}
    		
    		resultSet.close();
            ps.close();
            return choices;
    	} catch (Exception e) {
            throw new Exception("Failed in getting all choices: " + e.getMessage());
    	}
    }
    

    /**
     * Takes in a resultSet and turns it into a choice
     * @param resultSet the resultSet
     * @return A choice representation of the resultSet
     * @throws Exception
     */
    public static Choice generateChoice(ResultSet resultSet) throws Exception {
        String ID  = resultSet.getString("choiceID");
        String name = resultSet.getString("choiceName");
        String description = resultSet.getString("choiceDesc");
        int maxMembers = resultSet.getInt("maxMembers");
        Timestamp date = resultSet.getTimestamp("dateCreated");
        
        return new Choice (ID, name, description, maxMembers, date);
    }

}
