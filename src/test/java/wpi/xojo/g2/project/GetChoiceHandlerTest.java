package wpi.xojo.g2.project;

import org.junit.Assert;
import java.io.IOException;
import org.junit.Test;

import com.google.gson.Gson;

import wpi.xojo.g2.project.http.GetChoiceRequest;
import wpi.xojo.g2.project.http.GetChoiceResponse;

public class GetChoiceHandlerTest extends LambdaTest {

	
	void testSuccessInput(String incoming) throws IOException {
		GetChoiceHandler handler = new GetChoiceHandler();
		GetChoiceRequest req = new Gson().fromJson(incoming, GetChoiceRequest.class);
		
		GetChoiceResponse resp = handler.handleRequest(req, createContext("create"));
		Assert.assertEquals(200, resp.httpCode);
	}
	
	void testFailInput(String incoming) throws IOException {
		GetChoiceHandler handler = new GetChoiceHandler();
		GetChoiceRequest req = new Gson().fromJson(incoming, GetChoiceRequest.class);
		
		GetChoiceResponse resp = handler.handleRequest(req, createContext("create"));
		Assert.assertNotEquals(200, resp.httpCode);
	}
	
	@Test
	public void testGet() {
		GetChoiceRequest success = new GetChoiceRequest("1");
		GetChoiceRequest fail = new GetChoiceRequest("Does not exist");
		String successJson = new Gson().toJson(success);
		String failJson = new Gson().toJson(fail);
		try {
			testSuccessInput(successJson);
			testFailInput(failJson);
		} catch (IOException ioe) {
			Assert.fail("Invalid:" + ioe.getMessage());
		}
	}

}
