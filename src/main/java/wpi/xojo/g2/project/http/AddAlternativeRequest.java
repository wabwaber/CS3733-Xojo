package wpi.xojo.g2.project.http;

public class AddAlternativeRequest {
	String altName;
	String altDesc;
	public String getAltName() {
		return altName;
	}
	public void setAltName(String altName) {
		this.altName = altName;
	}
	public String getAltDesc() {
		return altDesc;
	}
	public void setAltDesc(String altDesc) {
		this.altDesc = altDesc;
	}
	
	public AddAlternativeRequest(String altName, String altDesc) {
		this.altName = altName;
		this.altDesc = altDesc;
	}
	
	public AddAlternativeRequest() {
	}
}
