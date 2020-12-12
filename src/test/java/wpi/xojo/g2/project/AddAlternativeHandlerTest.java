package wpi.xojo.g2.project;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import com.google.gson.Gson;

import wpi.xojo.g2.project.http.AddAlternativeRequest;
import wpi.xojo.g2.project.http.AddAlternativeResponse;

public class AddAlternativeHandlerTest extends LambdaTest {

	void testSuccessInput(String incoming) throws IOException {
		AddAlternativeHandler handler = new AddAlternativeHandler();
		AddAlternativeRequest req = new Gson().fromJson(incoming, AddAlternativeRequest.class);
		
		AddAlternativeResponse resp = handler.handleRequest(req, createContext("create"));
		Assert.assertEquals(200, resp.httpCode);
	}
	
	void testFailInput(String incoming) throws IOException {
		AddAlternativeHandler handler = new AddAlternativeHandler();
		AddAlternativeRequest req = new Gson().fromJson(incoming, AddAlternativeRequest.class);
		
		AddAlternativeResponse resp = handler.handleRequest(req, createContext("create"));
		Assert.assertNotEquals(200, resp.httpCode);
	}
	
	
	@Test
	public void test() {
		AddAlternativeRequest success = new AddAlternativeRequest("2", "This is an add alternative request");
		AddAlternativeRequest fail = new AddAlternativeRequest("Not a real choiceID", "This is an unsucessful add alternative request");
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
