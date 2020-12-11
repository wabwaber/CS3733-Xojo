package wpi.xojo.g2.project.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import wpi.xojo.g2.project.model.Alternative;
import wpi.xojo.g2.project.model.Choice;


public class ChoiceDAO {
	java.sql.Connection conn;

    public ChoiceDAO() {
    	try  {
    		conn = DatabaseUtil.connect();
    	} catch (Exception e) {
    		conn = null;
    	}
    }
    
    /**
     * Gets the choice from the database
     * @param choiceID the choiceID
     * @return The choice with the corresponding choiceID
     * @throws Exception
     */
    public Choice getChoice(String choiceID) throws Exception {
    	try {
            Choice choice = null;
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Choice WHERE choiceID = ?;");
            ps.setString(1,  choiceID);
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
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Choice WHERE choiceID = ?;");
            ps.setString(1, choice.choiceID);
            ResultSet resultSet = ps.executeQuery();
            
            // already present?
            if (resultSet.next()) {
                resultSet.close();
                return false;
            }
            
            resultSet.close();

            ps = conn.prepareStatement("INSERT INTO Choice (choiceID,choiceName,choiceDesc,maxMembers,timeCreated,completed) values(?,?,?,?,?,?);");
            ps.setString(1, choice.choiceID);
            ps.setString(2, choice.name);
            ps.setString(3, choice.description);
            ps.setInt(4, choice.maxMembers);
            ps.setTimestamp(5, choice.timeCreated);
            ps.setBoolean(6, choice.completed);
            ps.execute();
            ps.close();
            return true;

        } catch (Exception e) {
            throw new Exception("Failed to insert choice: " + e.getMessage());
        }
    }
    
    
    /**
     * Gets a list of alternatives for a given choice
     * @param choiceID The choiceID
     * @return A list of alternatives associated with the choice
     * @throws Exception
     */
    public List<Alternative> getChoiceAlternatives(String choiceID) throws Exception {
    	List<Alternative> alternatives = new ArrayList<Alternative>();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Alternative WHERE choiceID = ?;");
            ps.setString(1, choiceID);
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
    
    /**
     * Gets all the choices created
     * @return all the choices in tha database
     * @throws Exception
     */
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
    
    public boolean completeChoice(String choiceID) throws Exception {
    	try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM Choice WHERE choiceID = ?");
	        ps.setString(1, choiceID);
	        ResultSet resultSet = ps.executeQuery();
	        
	        if (resultSet.next()) {
	        	ps.close();
	        	ps = conn.prepareStatement("UPDATE Choice SET completed = ? WHERE choiceID = ?");
	        	ps.setBoolean(1, true);
	        	ps.setString(2, choiceID);
	            ps.executeUpdate();
	            return true;
	        }
	        
	        resultSet.close();
	        ps.close();
	        return false;
        
		} catch (Exception e) {
	        throw new Exception("Failed to complete choice: " + e.getMessage());
		}
    }
    
    public boolean deleteChoices(Long t) throws Exception {
    	try {
    		
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM Choice WHERE timeCreated < ?");
			Timestamp time = new Timestamp(t);
	        ps.setTimestamp(1, time);
	        ResultSet resultSet = ps.executeQuery();
	        AlternativeDAO adao = new AlternativeDAO();
	        MemberDAO mdao = new MemberDAO();
	        
	        while (resultSet.next()) {
	        	String id = resultSet.getString("choiceID");
	        	if (!adao.deleteAlternatives(id)) {
	        		return false;
	        	}
	        	if(!mdao.deleteMembers(id)) {
	        		return false;
	        	}
	        	PreparedStatement ps2 = conn.prepareStatement("DELETE FROM Choice WHERE choiceID = ?");
	        	ps2.setString(1, id);
	        	ps2.executeUpdate();
	        	ps2.close();
	        }
	        ps.close();
	        return true;
    	} catch (Exception e) {
    		throw new Exception("Failed to delete choices: " + e.getMessage());
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
        Timestamp time = resultSet.getTimestamp("timeCreated");
        boolean completed = resultSet.getBoolean("completed");
        
        return new Choice (ID, name, description, maxMembers, time, completed);
    }

}
