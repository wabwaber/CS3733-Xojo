package wpi.xojo.g2.project.http;

public class ParticipateChoiceRequest {
	public int memberID;
	public String name;
	public String password;
	public int choiceID;
	
	public ParticipateChoiceRequest(int memberID, String name, String password, int choiceID) {
		this.memberID = memberID;
		this.name = name;
		this.password = password;
		this.choiceID = choiceID;
	}
	
	public ParticipateChoiceRequest() {
	}
	
}
