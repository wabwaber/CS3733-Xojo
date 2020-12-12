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
	
	void testFailInput(String incoming) throws IOException {
		SetVotesHandler handler = new SetVotesHandler();
		SetVotesRequest req = new Gson().fromJson(incoming, SetVotesRequest.class);
		
		SetVotesResponse resp = handler.handleRequest(req, createContext("create"));
		Assert.assertNotEquals(200, resp.httpCode);
	}
	
	@Test
	public void test() {
		SetVotesRequest success = new SetVotesRequest("2", "3");
		SetVotesRequest fail = new SetVotesRequest("not real", "not real");
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
