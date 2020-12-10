package wpi.xojo.g2.project.model;

import java.sql.Timestamp;

public class FeedbackName {
	public String name;
	public String description;
	public Timestamp timeCreated;
	
	public FeedbackName(String name, String description, Timestamp timeCreated) {
		this.name = name;
		this.description = description;
		this.timeCreated = timeCreated;
	}

}
