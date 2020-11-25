package wpi.xojo.g2.project.model;

public class TeamMember {
	public final int memberID;
	public final String name;
	public final int choiceID;
	public final String password;
	
	public TeamMember(int ID, String name, int choiceID, String password) {
		this.memberID = ID;
		this.name = name;
		this.choiceID = choiceID;
		this.password = password;
	}
	
	public boolean equals (Object o) {
		if (o == null) { return false; }
		
		if (o instanceof TeamMember) {
			TeamMember other = (TeamMember) o;
			return memberID == (other.memberID);
		}
		
		return false;
	}
	
	public String toString() {
		return "Name: " + name + "\tChoice: " + choiceID;
	}
}
