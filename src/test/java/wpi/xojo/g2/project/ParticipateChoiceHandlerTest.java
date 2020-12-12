package wpi.xojo.g2.project;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import com.google.gson.Gson;

import wpi.xojo.g2.project.http.ParticipateChoiceRequest;
import wpi.xojo.g2.project.http.ParticipateChoiceResponse;

public class ParticipateChoiceHandlerTest extends LambdaTest {

	void testSuccessInput(String incoming) throws IOException {
		ParticipateChoiceHandler handler = new ParticipateChoiceHandler();
		ParticipateChoiceRequest req = new Gson().fromJson(incoming, ParticipateChoiceRequest.class);
		
		ParticipateChoiceResponse resp = handler.handleRequest(req, createContext("create"));
		Assert.assertEquals(200, resp.httpCode);
	}
	
	void testFailInput(String incoming) throws IOException {
		ParticipateChoiceHandler handler = new ParticipateChoiceHandler();
		ParticipateChoiceRequest req = new Gson().fromJson(incoming, ParticipateChoiceRequest.class);
		
		ParticipateChoiceResponse resp = handler.handleRequest(req, createContext("create"));
		Assert.assertNotEquals(200, resp.httpCode);
	}
	
	@Test
	public void test() {
		ParticipateChoiceRequest success = new ParticipateChoiceRequest("2", "Participant", "");
		ParticipateChoiceRequest fail = new ParticipateChoiceRequest("not real", "This won't work hopefully", "");
		String successJSON = new Gson().toJson(success);
		String failJSON = new Gson().toJson(fail);
		try {
			testSuccessInput(successJSON);
			testFailInput(failJSON);
		} catch (IOException ioe) {
			Assert.fail("Invalid:" + ioe.getMessage());
		}
	}
}
