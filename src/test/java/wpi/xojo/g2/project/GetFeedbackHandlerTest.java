package wpi.xojo.g2.project;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import com.google.gson.Gson;

import wpi.xojo.g2.project.http.GetFeedbackRequest;
import wpi.xojo.g2.project.http.GetFeedbackResponse;

public class GetFeedbackHandlerTest extends LambdaTest {

	void testSuccessInput(String incoming) throws IOException {
		GetFeedbackHandler handler = new GetFeedbackHandler();
		GetFeedbackRequest req = new Gson().fromJson(incoming, GetFeedbackRequest.class);
		
		GetFeedbackResponse resp = handler.handleRequest(req, createContext("create"));
		Assert.assertEquals(200, resp.httpCode);
	}
	
	void testFailInput(String incoming) throws IOException {
		GetFeedbackHandler handler = new GetFeedbackHandler();
		GetFeedbackRequest req = new Gson().fromJson(incoming, GetFeedbackRequest.class);
		
		GetFeedbackResponse resp = handler.handleRequest(req, createContext("create"));
		Assert.assertNotEquals(200, resp.httpCode);
	}
	
	@Test
	public void testGetFeedback() {
		GetFeedbackRequest success = new GetFeedbackRequest("1");
		GetFeedbackRequest fail = new GetFeedbackRequest("not real");
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