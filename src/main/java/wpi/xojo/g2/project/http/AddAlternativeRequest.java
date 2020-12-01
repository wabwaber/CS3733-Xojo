package wpi.xojo.g2.project.http;

public class AddAlternativeRequest {
	public String choiceID;
	public String alternativeName;
	public String alternativeDesc;
	
	public AddAlternativeRequest(String choiceID, String name, String desc) {
		this.choiceID = choiceID;
		this.alternativeName = name;
		this.alternativeDesc = desc;
	}
	
	public AddAlternativeRequest() {
	}
}
