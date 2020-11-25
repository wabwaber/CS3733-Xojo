package wpi.xojo.g2.project.model;

public class Team {
	public final int teamID;
	public final int choiceID;
	public final int maxMembers;
	
	public Team(int ID, int choiceID, int maxMembers) {
		this.teamID = ID;
		this.choiceID = choiceID;
		this.maxMembers = maxMembers;
	}
	
	public boolean equals (Object o) {
		if (o == null) { return false; }
		
		if (o instanceof Team) {
			Team other = (Team) o;
			return teamID == (other.teamID);
		}
		
		return false;
	}
	
	public String toString() {
		return "Team ID: " + teamID + "\tchoice ID: " + choiceID;
	}
}
