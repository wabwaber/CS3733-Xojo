package wpi.xojo.g2.project;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import com.google.gson.Gson;


import wpi.xojo.g2.project.http.AddFeedbackRequest;
import wpi.xojo.g2.project.http.AddFeedbackResponse;

public class AddFeedbackHandlerTest extends LambdaTest {
	void testSuccessInput(String incoming) throws IOException {
		AddFeedbackHandler handler = new AddFeedbackHandler();
		AddFeedbackRequest req = new Gson().fromJson(incoming, AddFeedbackRequest.class);
		
		AddFeedbackResponse resp = handler.handleRequest(req, createContext("create"));
		Assert.assertEquals(200, resp.httpCode);
	}
	
	void testFailInput(String incoming) throws IOException {
		AddFeedbackHandler handler = new AddFeedbackHandler();
		AddFeedbackRequest req = new Gson().fromJson(incoming, AddFeedbackRequest.class);
		
		AddFeedbackResponse resp = handler.handleRequest(req, createContext("create"));
		Assert.assertNotEquals(200, resp.httpCode);
	}
	
	
	@Test
	public void test() {
		AddFeedbackRequest success = new AddFeedbackRequest("2", "2", "This is an add feedback test");
		AddFeedbackRequest fail = new AddFeedbackRequest("Not a real alternativeID", "Not a real memberID", "This is an unsucessful add feedback test");
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
