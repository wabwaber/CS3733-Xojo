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
	
	void testFailInput(String incoming) throws IOException {
		ChangeVoteHandler handler = new ChangeVoteHandler();
		ChangeVoteRequest req = new Gson().fromJson(incoming, ChangeVoteRequest.class);
		
		ChangeVoteResponse resp = handler.handleRequest(req, createContext("create"));
		Assert.assertNotEquals(200, resp.httpCode);
	}
	
	@Test
	public void test() {
		ChangeVoteRequest success = new ChangeVoteRequest("2", "2", new Boolean(true));
		ChangeVoteRequest fail = new ChangeVoteRequest("Not real", "Not real", new Boolean(true));
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
