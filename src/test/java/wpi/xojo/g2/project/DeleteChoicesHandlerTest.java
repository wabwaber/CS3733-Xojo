package wpi.xojo.g2.project;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import com.google.gson.Gson;

import wpi.xojo.g2.project.http.DeleteChoicesRequest;
import wpi.xojo.g2.project.http.DeleteChoicesResponse;

public class DeleteChoicesHandlerTest extends LambdaTest {
	void testSuccessInput(String incoming) throws IOException {
		DeleteChoicesHandler handler = new DeleteChoicesHandler();
		DeleteChoicesRequest req = new Gson().fromJson(incoming, DeleteChoicesRequest.class);
		
		DeleteChoicesResponse resp = handler.handleRequest(req, createContext("create"));
		Assert.assertEquals(200, resp.httpCode);
	}

	@Test
	public void testDelete() {
		
		DeleteChoicesRequest success = new DeleteChoicesRequest(0L);
		String successJSON = new Gson().toJson(success);
		
		try {
			testSuccessInput(successJSON);
		} catch (IOException ioe) {
			Assert.fail("Invalid:" + ioe.getMessage());
		}
	}
}
