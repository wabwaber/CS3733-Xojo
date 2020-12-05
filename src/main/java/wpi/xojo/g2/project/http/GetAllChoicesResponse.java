package wpi.xojo.g2.project.http;

import java.util.List;

import wpi.xojo.g2.project.model.Choice;

public class GetAllChoicesResponse {
	public final List<Choice> list;
	public final String error;
	public final int httpCode;
	
	public GetAllChoicesResponse(String error, int code) {
		this.list = null;
		this.error = error;
		this.httpCode = code;
	}
	
	public GetAllChoicesResponse(List<Choice> list) {
		this.list = list;
		this.error = "";
		this.httpCode = 200;
	}
	
	public String toString() {
		if(list == null) {return "No choices";}
		return "Choices(" + list.size() + ")";
	}
}
