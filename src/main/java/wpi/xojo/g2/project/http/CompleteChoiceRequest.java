package wpi.xojo.g2.project.http;

public class CompleteChoiceRequest {
	
	public String choiceID;
	public String alternativeID;
	
	public String toString() {
		return "Completed choice by choosing alternative " + alternativeID;
	}
	
	public CompleteChoiceRequest(String choiceID, String altID) {
		this.choiceID = choiceID;
		this.alternativeID = altID;
	}
	
	public CompleteChoiceRequest() {
	}
}
