package wpi.xojo.g2.project;


import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import com.google.gson.Gson;

import wpi.xojo.g2.project.http.GetVotesRequest;
import wpi.xojo.g2.project.http.GetVotesResponse;

public class GetVotesHandlerTest extends LambdaTest {

	void testSuccessInput(String incoming) throws IOException {
		GetVotesHandler handler = new GetVotesHandler();
		GetVotesRequest req = new Gson().fromJson(incoming, GetVotesRequest.class);
		
		GetVotesResponse resp = handler.handleRequest(req, createContext("create"));
		Assert.assertEquals(200, resp.httpCode);
	}
	
	void testFailInput(String incoming) throws IOException {
		GetVotesHandler handler = new GetVotesHandler();
		GetVotesRequest req = new Gson().fromJson(incoming, GetVotesRequest.class);
		
		GetVotesResponse resp = handler.handleRequest(req, createContext("create"));
		Assert.assertNotEquals(200, resp.httpCode);
	}
	
	@Test
	public void test() {
		GetVotesRequest success = new GetVotesRequest("1");
		GetVotesRequest fail = new GetVotesRequest("not real");
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
