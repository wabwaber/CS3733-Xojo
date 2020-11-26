package wpi.xojo.g2.project.http;

import wpi.xojo.g2.project.model.Choice;

public class GetChoiceResponse {
	public final Choice choice;
	public final String error;
	public final int httpCode;
	
	public GetChoiceResponse(String error, int code) {
		this.choice = null;
		this.error = error;
		this.httpCode = code;
	}
	
	public GetChoiceResponse(Choice choice) {
		this.choice = choice;
		this.error = "";
		this.httpCode = 200;
	}
}
