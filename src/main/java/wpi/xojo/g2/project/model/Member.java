package wpi.xojo.g2.project.model;

public class Member {
	public final int memberID;
	public final String name;
	public final String teamID;
	public final String password;
	
	public Member(int ID, String name, String teamID, String password) {
		this.memberID = ID;
		this.name = name;
		this.teamID = teamID;
		this.password = password;
	}
	
	public boolean equals (Object o) {
		if (o == null) { return false; }
		
		if (o instanceof Member) {
			Member other = (Member) o;
			return memberID == (other.memberID);
		}
		
		return false;
	}
	
	public String toString() {
		return "Name: " + name + "\tTeam: " + teamID;
	}
}
