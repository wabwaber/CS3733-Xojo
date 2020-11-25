package wpi.xojo.g2.project.http;

import wpi.xojo.g2.project.model.Choice;

public class CreateChoiceResponse {
	public final Choice choice;
	public final String error;
	public final int httpCode;
	
	public CreateChoiceResponse (String s, int code) {
		this.choice = null;
		this.error = s;
		this.httpCode = code;
	}
	
	// 200 means success
	public CreateChoiceResponse (Choice c) {
		this.choice = c;
		this.error = "";
		this.httpCode = 200;
	}
	
	public String toString() {
		if (this.httpCode == 200) {
			return "Choice created";
		}
		return "Error :" + error;
	}
}
