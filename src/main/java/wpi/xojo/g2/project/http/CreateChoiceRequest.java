package wpi.xojo.g2.project.http;

public class CreateChoiceRequest {
	public int choiceID;
	public String choiceName;
	public String choiceDesc;
	public int maxMembers;
	
	public String toString() {
		return "Choice with ID:" + choiceID;
	}
	
	public CreateChoiceRequest(int choiceID, String choiceName, String choiceDesc, int maxMembers) {
		this.choiceID = choiceID;
		this.choiceName = choiceName;
		this.choiceDesc = choiceDesc;
		this.maxMembers = maxMembers;
	}
	
	public CreateChoiceRequest() {
		
	}
}

