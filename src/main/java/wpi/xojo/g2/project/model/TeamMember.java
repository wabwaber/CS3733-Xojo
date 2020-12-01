package wpi.xojo.g2.project.model;

import java.util.UUID;

public class TeamMember {
	public final String memberID;
	public final String name;
	public final String choiceID;
	public final String password;
	
	public TeamMember(String ID, String choiceID, String name, String password) {
		this.memberID = ID;
		this.name = name;
		this.choiceID = choiceID;
		this.password = password;
	}
	
	public TeamMember(String choiceID, String name, String password) {
		String id = UUID.randomUUID().toString();
		this.memberID = id;
		this.name = name;
		this.choiceID = choiceID;
		this.password = password;
	}
	
	public boolean equals (Object o) {
		if (o == null) { return false; }
		
		if (o instanceof TeamMember) {
			TeamMember other = (TeamMember) o;
			return memberID.equals(other.memberID);
		}
		
		return false;
	}
	
	public String toString() {
		return "Name: " + name + "\tChoice: " + choiceID;
	}
}
