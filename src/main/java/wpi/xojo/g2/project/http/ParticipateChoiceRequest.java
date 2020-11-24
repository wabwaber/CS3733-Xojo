package wpi.xojo.g2.project.http;

public class ParticipateChoiceRequest {
	public String ID;
	public String name;
	public String password;
	
	
	public ParticipateChoiceRequest(String ID, String name, String password) {
		this.ID = ID;
		this.name = name;
		this.password = password;
	}
	
	public ParticipateChoiceRequest() {
	}
	
}
