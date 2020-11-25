package wpi.xojo.g2.project.http;

public class ParticipateChoiceRequest {
	public String name;
	public String password;
	public int choiceID;
	
	public ParticipateChoiceRequest(String name, String password, int choiceID) {
		this.name = name;
		this.password = password;
		this.choiceID = choiceID;
	}
	
	public ParticipateChoiceRequest() {
	}
	
}
