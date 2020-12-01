package wpi.xojo.g2.project.http;

public class ParticipateChoiceRequest {
	public String name;
	public String password;
	public String choiceID;
	
	public ParticipateChoiceRequest(String choiceID, String name, String password) {
		this.name = name;
		this.password = password;
		this.choiceID = choiceID;
	}
	
	public ParticipateChoiceRequest() {
	}
	
}
