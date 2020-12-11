package wpi.xojo.g2.project;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import wpi.xojo.g2.project.http.GetAllChoicesResponse;

public class GetAllChoicesHandlerTest extends LambdaTest {

	void testSuccessInput() throws IOException {
		GetAllChoicesHandler handler = new GetAllChoicesHandler();
		
		GetAllChoicesResponse resp = handler.handleRequest(new Object(), createContext("create"));
		Assert.assertEquals(200, resp.httpCode);
	}

	@Test
	public void testGetAll() {
		
		
		try {
			testSuccessInput();
		} catch (IOException ioe) {
			Assert.fail("Invalid:" + ioe.getMessage());
		}
	}

}
