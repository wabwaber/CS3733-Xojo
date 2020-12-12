package wpi.xojo.g2.project;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import com.google.gson.Gson;

import wpi.xojo.g2.project.http.GetMemberRequest;
import wpi.xojo.g2.project.http.GetMemberResponse;

public class GetMemberHandlerTest extends LambdaTest {

	void testSuccessInput(String incoming) throws IOException {
		GetMemberHandler handler = new GetMemberHandler();
		GetMemberRequest req = new Gson().fromJson(incoming, GetMemberRequest.class);
		
		GetMemberResponse resp = handler.handleRequest(req, createContext("create"));
		Assert.assertEquals(200, resp.httpCode);
	}
	
	void testFailInput(String incoming) throws IOException {
		GetMemberHandler handler = new GetMemberHandler();
		GetMemberRequest req = new Gson().fromJson(incoming, GetMemberRequest.class);
		
		GetMemberResponse resp = handler.handleRequest(req, createContext("create"));
		Assert.assertNotEquals(200, resp.httpCode);
	}
	
	@Test
	public void test() {
		GetMemberRequest success = new GetMemberRequest("1");
		GetMemberRequest fail = new GetMemberRequest("not real");
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
