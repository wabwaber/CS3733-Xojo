package wpi.xojo.g2.project;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import com.google.gson.Gson;

import wpi.xojo.g2.project.http.ChangeVoteRequest;
import wpi.xojo.g2.project.http.ChangeVoteResponse;

public class ChangeVoteHandlerTest extends LambdaTest {
	
	void testSuccessInput(String incoming) throws IOException {
		ChangeVoteHandler handler = new ChangeVoteHandler();
		ChangeVoteRequest req = new Gson().fromJson(incoming, ChangeVoteRequest.class);
		
		ChangeVoteResponse resp = handler.handleRequest(req, createContext("create"));
		Assert.assertEquals(200, resp.httpCode);
	}
	
	@Test
	public void test() {
		ChangeVoteRequest ccr = new ChangeVoteRequest("764da34e-2828-465e-b9cc-0e2fe881d91a", "d6decafa-fb62-403d-8172-2e0e0e226a93", new Boolean(true));
		String SAMPLE_INPUT_STRING = new Gson().toJson(ccr);
		
		try {
			testSuccessInput(SAMPLE_INPUT_STRING);
		} catch (IOException ioe) {
			Assert.fail("Invalid:" + ioe.getMessage());
		}
	}

}
