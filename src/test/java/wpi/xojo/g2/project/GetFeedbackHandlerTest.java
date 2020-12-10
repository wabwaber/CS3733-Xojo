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
	
	@Test
	public void testGet() {
		GetFeedbackRequest ccr = new GetFeedbackRequest("5058b13a-c060-4eca-8621-b5d5be73739b");
		String SAMPLE_INPUT_STRING = new Gson().toJson(ccr);
		try {
			testSuccessInput(SAMPLE_INPUT_STRING);
		} catch (IOException ioe) {
			Assert.fail("Invalid:" + ioe.getMessage());
		}
	}

}