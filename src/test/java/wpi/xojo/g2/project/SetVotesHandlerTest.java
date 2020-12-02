package wpi.xojo.g2.project;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import com.google.gson.Gson;

import wpi.xojo.g2.project.http.SetVotesRequest;
import wpi.xojo.g2.project.http.SetVotesResponse;

public class SetVotesHandlerTest extends LambdaTest {

	void testSuccessInput(String incoming) throws IOException {
		SetVotesHandler handler = new SetVotesHandler();
		SetVotesRequest req = new Gson().fromJson(incoming, SetVotesRequest.class);
		
		SetVotesResponse resp = handler.handleRequest(req, createContext("create"));
		Assert.assertEquals(200, resp.httpCode);
	}
	
	@Test
	public void test() {
		SetVotesRequest ccr = new SetVotesRequest("59be4ecb-dc79-47b6-b96c-578458c0c802", "6457584e-25d7-4a01-a1c5-2aec84f9345f");
		String SAMPLE_INPUT_STRING = new Gson().toJson(ccr);
		
		try {
			testSuccessInput(SAMPLE_INPUT_STRING);
		} catch (IOException ioe) {
			Assert.fail("Invalid:" + ioe.getMessage());
		}
	}

}
