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
	
	@Test
	public void testGet() {
		GetChoiceRequest ccr = new GetChoiceRequest("e781cbeb-afeb-43e3-b49d-ea8e5db85fa2");
		String SAMPLE_INPUT_STRING = new Gson().toJson(ccr);
		
		try {
			testSuccessInput(SAMPLE_INPUT_STRING);
		} catch (IOException ioe) {
			Assert.fail("Invalid:" + ioe.getMessage());
		}
	}

}
