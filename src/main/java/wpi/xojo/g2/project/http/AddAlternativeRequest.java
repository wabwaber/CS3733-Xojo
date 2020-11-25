package wpi.xojo.g2.project.http;

public class AddAlternativeRequest {
	public int alternativeID;
	public String altName;
	public String altDesc;
	public int choiceID;
	
	public AddAlternativeRequest(int alternativeID, String altName, String altDesc, int choiceID) {
		this.alternativeID = alternativeID;
		this.altName = altName;
		this.altDesc = altDesc;
		this.choiceID = choiceID;
	}
	
	public AddAlternativeRequest() {
	}
}
