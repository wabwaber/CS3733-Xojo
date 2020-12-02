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
	
	@Test
	public void test() {
		AddAlternativeRequest ccr = new AddAlternativeRequest("a667170f-ee95-48ac-8ee4-45a3560f672f", "test name2");
		String SAMPLE_INPUT_STRING = new Gson().toJson(ccr);
		
		try {
			testSuccessInput(SAMPLE_INPUT_STRING);
		} catch (IOException ioe) {
			Assert.fail("Invalid:" + ioe.getMessage());
		}
	}

}
