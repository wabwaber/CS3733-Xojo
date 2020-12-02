package wpi.xojo.g2.project.model;

public class Vote {
	public final String alternativeID;
	public final String memberID;
	public final Boolean isUpvote;
	
	public Vote(String alternativeID, String memberID, Boolean isUpvote) {
		this.alternativeID = alternativeID;
		this.memberID = memberID;
		this.isUpvote = isUpvote;
	}

	public boolean equals (Object o) {
		if (o == null) { return false; }
		
		if (o instanceof Vote) {
			Vote other = (Vote) o;
			return alternativeID.equals(other.alternativeID) && memberID.equals(other.memberID);
		}
		
		return false;
	}
	
	public String toString() {
		return "Alternative ID: " + alternativeID + "\t Member ID: " + memberID; 
	}
}
