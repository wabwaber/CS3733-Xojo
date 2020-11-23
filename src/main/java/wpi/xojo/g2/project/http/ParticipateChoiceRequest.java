package wpi.xojo.g2.project.http;

public class ParticipateChoiceRequest {
	String ID;
	String name;
	String password;
	
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public ParticipateChoiceRequest(String ID, String name, String password) {
		this.ID = ID;
		this.name = name;
		this.password = password;
	}
	
	public ParticipateChoiceRequest() {
	}
	
}
