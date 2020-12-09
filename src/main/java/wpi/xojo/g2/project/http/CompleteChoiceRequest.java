package wpi.xojo.g2.project.http;

public class CompleteChoiceRequest {
	
	public String alternativeID;
	public String choiceID;
	
	public String toString() {
		return "Completed choice by choosing alternative " + alternativeID;
	}
	
	public CompleteChoiceRequest() {
		
	}
	
	public CompleteChoiceRequest(String altID, String choiceID) {
		this.alternativeID = altID;
		this.choiceID = choiceID;
	}
}
