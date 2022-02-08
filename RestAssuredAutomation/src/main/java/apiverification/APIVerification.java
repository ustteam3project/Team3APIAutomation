package apiverification;


import java.util.Map;

import org.testng.Assert;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class APIVerification {

	public static void responseCodeValiddation(Response response, int statusCode) {

		Assert.assertEquals(statusCode, response.getStatusCode());
		
	}
	public static void responseContentTypeValiddation(Response response, String contenttype) {

		Assert.assertEquals(contenttype, response.getContentType());
		
	}
	public static void responseStatusLineValiddation(Response response, String statusline) {

		Assert.assertEquals(statusline, response.getStatusLine());
		
	}
	public static void responseBodyValiddation(Response response, String Key) {
		
		ResponseBody body = response.getBody();
	    String bodyStringValue = body.asString();
	    Assert.assertTrue(bodyStringValue.contains(Key));

		
	}

	
}
