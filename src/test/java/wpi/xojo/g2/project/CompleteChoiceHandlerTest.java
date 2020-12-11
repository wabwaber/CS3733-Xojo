package wpi.xojo.g2.project;


import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import com.google.gson.Gson;

import wpi.xojo.g2.project.http.CompleteChoiceRequest;
import wpi.xojo.g2.project.http.CompleteChoiceResponse;

public class CompleteChoiceHandlerTest extends LambdaTest {

	void testSuccessInput(String incoming) throws IOException {
		CompleteChoiceHandler handler = new CompleteChoiceHandler();
		CompleteChoiceRequest req = new Gson().fromJson(incoming, CompleteChoiceRequest.class);
		
		CompleteChoiceResponse resp = handler.handleRequest(req, createContext("create"));
		Assert.assertEquals(200, resp.httpCode);
	}
	
	void testFailInput(String incoming) throws IOException {
		CompleteChoiceHandler handler = new CompleteChoiceHandler();
		CompleteChoiceRequest req = new Gson().fromJson(incoming, CompleteChoiceRequest.class);
		
		CompleteChoiceResponse resp = handler.handleRequest(req, createContext("create"));
		Assert.assertNotEquals(200, resp.httpCode);
	}
	
	@Test
	public void test() {
		CompleteChoiceRequest success = new CompleteChoiceRequest("3", "3");
		CompleteChoiceRequest fail = new CompleteChoiceRequest("Not real", "Not real");
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
