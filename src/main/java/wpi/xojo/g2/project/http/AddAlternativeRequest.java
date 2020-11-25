package wpi.xojo.g2.project.http;

public class AddAlternativeRequest {
	public int alternativeID;
	public String alternativeDesc;
	public int choiceID;
	
	public AddAlternativeRequest(int altID, String altDesc, int choiceID) {
		this.alternativeID = altID;
		this.alternativeDesc = altDesc;
		this.choiceID = choiceID;
	}
	
	public AddAlternativeRequest() {
	}
}
