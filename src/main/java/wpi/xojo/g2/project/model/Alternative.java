package wpi.xojo.g2.project.model;

import java.util.UUID;

public class Alternative {
	public final String alternativeID;
	public final String choiceID;
	public final String description;
	public final boolean selected;
	
	public Alternative(String ID, String choiceID, String desc, boolean selected) {
		this.alternativeID = ID;
		this.choiceID = choiceID;
		this.description = desc;
		this.selected = selected;
	}
	
	
	public Alternative(String choiceID, String desc) {
		String id = UUID.randomUUID().toString();
		this.alternativeID = id;
		this.choiceID = choiceID;
		this.description = desc;
		this.selected = false;
	}
	
	public boolean equals (Object o) {
		if (o == null) { return false; }
		
		if (o instanceof Alternative) {
			Alternative other = (Alternative) o;
			return alternativeID.equals(other.alternativeID);
		}
		
		return false;
	}
	
	public String toString() {
		return "Alternative ID: " + alternativeID + "\tAssociated choice: " + choiceID;
	}
}
