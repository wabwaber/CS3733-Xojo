package wpi.xojo.g2.project.model;

import java.sql.Date;

public class Choice {
	public final int choiceID;
	public final String name;
	public final String description;
	public final Date dateCompleted;
	
	public Choice(int ID, String name, String desc, Date date) {
		this.choiceID = ID;
		this.name = name;
		this.description = desc;
		this.dateCompleted = date;
	}
	
	public Choice(int ID, String name, String desc) {
		this.choiceID = ID;
		this.name = name;
		this.description = desc;
		this.dateCompleted = null;
	}
	
	public boolean equals (Object o) {
		if (o == null) { return false; }
		
		if (o instanceof Choice) {
			Choice other = (Choice) o;
			return choiceID == (other.choiceID);
		}
		
		return false;
	}
	
	public String toString() {
		return "Choice ID: " + choiceID;
	}
	
}
