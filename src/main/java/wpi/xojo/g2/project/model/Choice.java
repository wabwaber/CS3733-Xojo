package wpi.xojo.g2.project.model;

import java.sql.Timestamp;
import java.util.UUID;

public class Choice {
	public final String choiceID;
	public final String name;
	public final String description;
	public final int maxMembers;
	public final Timestamp dateCreated;
	
	public Choice(String ID, String name, String desc, int maxMembers, Timestamp date) {
		this.choiceID = ID;
		this.name = name;
		this.description = desc;
		this.maxMembers = maxMembers;
		this.dateCreated = date;
	}
	
	public Choice(String name, String desc, int maxMembers) {
		String id = UUID.randomUUID().toString();
		this.choiceID = id;
		this.name = name;
		this.description = desc;
		this.maxMembers = maxMembers;	
		Timestamp d = new Timestamp(java.lang.System.currentTimeMillis());
		this.dateCreated = d;
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
