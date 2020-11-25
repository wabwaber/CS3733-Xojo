package wpi.xojo.g2.project.http;

public class CreateChoiceRequest {
	public int choiceID;
	public String choiceName;
	public String choiceDesc;
	
	public String toString() {
		return "Choice with ID:" + choiceID;
	}
	
	public CreateChoiceRequest(int choiceID, String choiceName, String choiceDesc) {
		this.choiceID = choiceID;
		this.choiceName = choiceName;
		this.choiceDesc = choiceDesc;
	}
	
	public CreateChoiceRequest() {
		
	}
}

