package wpi.xojo.g2.project.model;

import java.sql.Date;

public class Choice {
	public final String choiceID;
	public final String name;
	public final String description;
	public final Date date;
	
	public Choice(String ID, String name, String desc, Date date) {
		this.choiceID = ID;
		this.name = name;
		this.description = desc;
		this.date = date;
	}
	
	public boolean equals (Object o) {
		if (o == null) { return false; }
		
		if (o instanceof Choice) {
			Choice other = (Choice) o;
			return choiceID.equals(other.choiceID);
		}
		
		return false;
	}
	
	public String toString() {
		return "Choice ID: " + choiceID;
	}
	
}
