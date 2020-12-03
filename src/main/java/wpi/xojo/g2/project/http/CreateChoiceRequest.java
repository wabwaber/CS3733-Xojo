package wpi.xojo.g2.project.http;

public class CreateChoiceRequest {
	public String choiceName;
	public String choiceDesc;
	public int maxMembers;
	
	public String toString() {
		return "Choice name: " + choiceName;
	}
	
	public CreateChoiceRequest(String choiceName, String choiceDesc, int maxMembers) {
		this.choiceName = choiceName;
		this.choiceDesc = choiceDesc;
		this.maxMembers = maxMembers;
	}
	
	public CreateChoiceRequest() {
		
	}
}

