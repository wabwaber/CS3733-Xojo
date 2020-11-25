package wpi.xojo.g2.project.model;

public class Alternative {
	public final int alternativeID;
	public final String choiceID;
	public final String description;
	public boolean selected;
	
	public Alternative(int ID, String choiceID, String desc, boolean selected) {
		this.alternativeID = ID;
		this.choiceID = choiceID;
		this.description = desc;
		this.selected = selected;
	}
	
	public Alternative(int ID, String choiceID, String desc) {
		this.alternativeID = ID;
		this.choiceID = choiceID;
		this.description = desc;
		this.selected = false;
	}
	

	public boolean equals (Object o) {
		if (o == null) { return false; }
		
		if (o instanceof Alternative) {
			Alternative other = (Alternative) o;
			return alternativeID == other.alternativeID;
		}
		
		return false;
	}
	
	public String toString() {
		return "Alternative ID: " + alternativeID + "\tAssociated choice: " + choiceID;
	}
}
