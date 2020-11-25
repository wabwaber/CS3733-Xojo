package wpi.xojo.g2.project.http;

import java.util.List;

import wpi.xojo.g2.project.model.Alternative;

public class GetAlternativesResponse {
	public final List<Alternative> list;
	public final String error;
	public final int httpCode;
	
	public GetAlternativesResponse(String error, int code) {
		this.list = null;
		this.error = error;
		this.httpCode = code;
	}
	
	public GetAlternativesResponse(List<Alternative> list) {
		this.list = list;
		this.error = "";
		this.httpCode = 200;
	}
	
	public String toString() {
		if(list == null) {return "No Alternatives";}
		return "Alternatives(" + list.size() + ")";
	}
}
