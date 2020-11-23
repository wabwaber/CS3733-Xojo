package wpi.xojo.g2.project.http;

public class createChoiceRequest {
	String choiceID;
	String choiceName;
	String choiceDesc;
	
	public String getChoiceID() {
		return choiceID;
	}
	public void setChoiceID(String choiceID) {
		this.choiceID = choiceID;
	}
	public String getChoiceName() {
		return choiceName;
	}
	public void setChoiceName(String choiceName) {
		this.choiceName = choiceName;
	}
	public String getChoiceDesc() {
		return choiceDesc;
	}
	public void setChoiceDesc(String choiceDesc) {
		this.choiceDesc = choiceDesc;
	}
	
	public String toString() {
		return "Choice with ID:" + choiceID;
	}
	
	public createChoiceRequest(String choiceID, String choiceName, String choiceDesc) {
		this.choiceID = choiceID;
		this.choiceName = choiceName;
		this.choiceDesc = choiceDesc;
	}
	
	public createChoiceRequest() {
		
	}
}

