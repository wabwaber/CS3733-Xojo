package wpi.xojo.g2.project.http;

public class ParticipateChoiceRequest {
	public String choiceID;
	public String name;
	public String password;
	
	public String toString() {
		return "Participating choice:" + name;
	}
	
	
	public ParticipateChoiceRequest(String choiceID, String name, String password) {
		this.choiceID = choiceID;
		this.name = name;
		this.password = password;
	}
	
	public ParticipateChoiceRequest() {
	}
	
}
