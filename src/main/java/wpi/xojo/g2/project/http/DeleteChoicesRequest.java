package wpi.xojo.g2.project.http;


public class DeleteChoicesRequest {

	public Long time;

	public DeleteChoicesRequest() {
	}

	public DeleteChoicesRequest(Long time) {
		this.time = time;
	}

	public String toString() {
		return "Deleted choices before this time: " + time;
	}

}
