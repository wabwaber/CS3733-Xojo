package wpi.xojo.g2.project.http;

public class AddAlternativeRequest {
	public String altName;
	public String altDesc;
	
	public AddAlternativeRequest(String altName, String altDesc) {
		this.altName = altName;
		this.altDesc = altDesc;
	}
	
	public AddAlternativeRequest() {
	}
}
