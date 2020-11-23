package wpi.xojo.g2.project.db;

import java.sql.*;


public class ChoiceDAO {
java.sql.Connection conn;
	
	final String tblName = "Constants";   // Exact capitalization

    public ChoiceDAO() {
    	try  {
    		conn = DatabaseUtil.connect();
    	} catch (Exception e) {
    		conn = null;
    	}
    }
}
