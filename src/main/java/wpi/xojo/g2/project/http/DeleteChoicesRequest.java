package wpi.xojo.g2.project.http;

import java.sql.Timestamp;

public class DeleteChoicesRequest {

	public Timestamp time;

	public DeleteChoicesRequest() {

	}

	public DeleteChoicesRequest(Timestamp time) {
		this.time = time;
	}

	public String toString() {
		return "Deleted choices before this time: " + time;
	}

}
