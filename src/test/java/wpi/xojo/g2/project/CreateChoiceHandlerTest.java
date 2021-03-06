package wpi.xojo.g2.project;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import com.google.gson.Gson;

import wpi.xojo.g2.project.http.CreateChoiceRequest;
import wpi.xojo.g2.project.http.CreateChoiceResponse;

public class CreateChoiceHandlerTest extends LambdaTest {
	
	void testSuccessInput(String incoming) throws IOException {
		CreateChoiceHandler handler = new CreateChoiceHandler();
		CreateChoiceRequest req = new Gson().fromJson(incoming, CreateChoiceRequest.class);
		
		CreateChoiceResponse resp = handler.handleRequest(req, createContext("create"));
		Assert.assertEquals(200, resp.httpCode);
	}

	@Test
	public void testCreate() {
		
		CreateChoiceRequest ccr = new CreateChoiceRequest("CreateChoiceTest", "This is the test for creating choice", 3);
		String SAMPLE_INPUT_STRING = new Gson().toJson(ccr);
		
		try {
			testSuccessInput(SAMPLE_INPUT_STRING);
		} catch (IOException ioe) {
			Assert.fail("Invalid:" + ioe.getMessage());
		}
	}

}
