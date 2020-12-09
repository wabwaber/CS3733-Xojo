package wpi.xojo.g2.project.model;

import java.sql.Timestamp;

public class Feedback {
	public final String alternativeID;
	public final String memberID;
	public final String description;
	public final Timestamp timeCreated;
	
	public Feedback(String alternativeID, String memberID, String desc, Timestamp time) {
		this.alternativeID = alternativeID;
		this.memberID = memberID;
		this.description = desc;
		this.timeCreated = time;
	}
	
	public Feedback(String alternativeID, String memberID, String desc) {
		this.alternativeID = alternativeID;
		this.memberID = memberID;
		this.description = desc;
		Timestamp t = new Timestamp(java.lang.System.currentTimeMillis());
		this.timeCreated = t;
	}
	
	public boolean equals (Object o) {
		if (o == null) { return false; }
		
		if (o instanceof Feedback) {
			Feedback other = (Feedback) o;
			return (alternativeID.equals(other.alternativeID) && memberID.equals(other.memberID) && timeCreated.equals(other.timeCreated));
		}
		
		return false;
	}
	
	public String toString() {
		return "Feedback: " + description;
	}
	
}
