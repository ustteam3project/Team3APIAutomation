package FunctionalTesting;

import java.io.IOException;

import org.json.JSONObject;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import apiConfigs.APIPath;
import apiConfigs.APIPath.apiPath;
import apiverification.APIVerification;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.response.Response;
import utils.ObjectReference;

public class APIRequestAutomation {

	ObjectReference obj;
	ExtentHtmlReporter htmlReporter;
	static ExtentReports extent;
	static ExtentTest test;

	public APIRequestAutomation() throws IOException {
		obj = new ObjectReference();
	}

	@BeforeSuite
	public void setup1() {

		htmlReporter = new ExtentHtmlReporter("extentReport.html");
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);

	}

	@BeforeMethod
	public void setup() {

		String path1 = obj.getBaseURI();

		RestAssured.baseURI = path1;

	}

	@Test
	public void getmethodvalidation() {

		test = extent.createTest("PostAPI Test", "Validate GET API Responce");

		Response response = RestAssured.given().when().get(APIPath.apiPath.GET_LIST_OF_USER);

		test.log(Status.INFO, "My test is starting.....");

		System.out.println(response.prettyPrint());
		APIVerification.responseCodeValiddation(response, 200);
		test.log(Status.PASS, "Successfully validated status code,Status code is :: " + response.getStatusCode());
		APIVerification.responseContentTypeValiddation(response, "application/json; charset=utf-8");
		test.log(Status.PASS, "Successfully validated ContentType,ContentType is :: " + response.getContentType());
		APIVerification.responseStatusLineValiddation(response, "HTTP/1.1 200 OK");
		test.log(Status.PASS, "Successfully validated StatusLine,StatusLine is :: " + response.getContentType());
		System.out.println(response.getTime());
		test.log(Status.PASS, "Successfully validated GetTime,GetTime is :: " + response.getTime());
		APIVerification.responseBodyValiddation(response,"email");
		test.log(Status.PASS, "Successfully validated Responce Body Field");
	}

	@Test
	public static void postmethodvalidation() {

		test = extent.createTest("PostAPI Test", "Validate POST API Responce");

		test.log(Status.INFO, "My test is starting.....");

		JSONObject request = new JSONObject();
		request.put("name", "morpheus");
		request.put("job", "leader");

		Response response = RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON)
				.body(request.toString()).when().post(APIPath.apiPath.CREATE_USER);

		System.out.println(response.prettyPrint());
		APIVerification.responseCodeValiddation(response, 201);
		test.log(Status.PASS, "Successfully validated status code,Status code is :: " + response.getStatusCode());
		APIVerification.responseContentTypeValiddation(response, "application/json; charset=utf-8");
		test.log(Status.PASS, "Successfully validated ContentType,ContentType is :: " + response.getContentType());
		APIVerification.responseStatusLineValiddation(response, "HTTP/1.1 201 Created");
		test.log(Status.PASS, "Successfully validated StatusLine,StatusLine is :: " + response.getContentType());
		System.out.println(response.getTime());
		test.log(Status.PASS, "Successfully validated GetTime,GetTime is :: " + response.getTime());
	}
	
	@Test
	public static void putmethodvalidation() {

		test = extent.createTest("PutAPI Test", "Validate PUT API Responce");

		test.log(Status.INFO, "My test is starting.....");

		JSONObject request = new JSONObject();
		request.put("name", "morpheus");
		request.put("job", "leader");

		Response response = RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON)
				.body(request.toString()).when().put(APIPath.apiPath.CREATE_USER_PUT);

		System.out.println(response.prettyPrint());
		APIVerification.responseCodeValiddation(response, 200);
		test.log(Status.PASS, "Successfully validated status code,Status code is :: " + response.getStatusCode());
		APIVerification.responseContentTypeValiddation(response, "application/json; charset=utf-8");
		test.log(Status.PASS, "Successfully validated ContentType,ContentType is :: " + response.getContentType());
		APIVerification.responseStatusLineValiddation(response, "HTTP/1.1 200 OK");
		test.log(Status.PASS, "Successfully validated StatusLine,StatusLine is :: " + response.getContentType());
		System.out.println(response.getTime());
		test.log(Status.PASS, "Successfully validated GetTime,GetTime is :: " + response.getTime());
	}

	@Test
		public void deltemethodvalidation() {

			test = extent.createTest("DeleteAPI Test", "Validate Delete API Responce");

			Response response = RestAssured.given().when().delete(APIPath.apiPath.CREATE_DELETE);

			test.log(Status.INFO, "My test is starting.....");

			System.out.println(response.prettyPrint());
			APIVerification.responseCodeValiddation(response, 204);
			test.log(Status.PASS, "Successfully validated status code,Status code is :: " + response.getStatusCode());
			APIVerification.responseStatusLineValiddation(response, "HTTP/1.1 204 No Content");
			test.log(Status.PASS, "Successfully validated StatusLine,StatusLine is :: " + response.getContentType());
			System.out.println(response.getTime());
			test.log(Status.PASS, "Successfully validated GetTime,GetTime is :: " + response.getTime());
			APIVerification.responseBodyValiddation(response,"email");
			
			
		}
	
	@AfterMethod
	public void teardown(ITestResult result) throws IOException {
		
		if (result.getStatus() == ITestResult.FAILURE) {
			
			test.fail(result.getThrowable().getMessage());
	        test.log(Status.FAIL,
	                MarkupHelper.createLabel(result.getName()
	                        + " Test case FAILED due to below issues:",
	                        ExtentColor.RED));
	        test.fail(result.getThrowable());
	    } else if (result.getStatus() == ITestResult.SUCCESS) {
	        test.log(
	                Status.PASS,
	                MarkupHelper.createLabel(result.getName()
	                        + " Test Case PASSED", ExtentColor.GREEN));
	    } else {
	        test.log(
	                Status.SKIP,
	                MarkupHelper.createLabel(result.getName()
	                        + " Test Case SKIPPED", ExtentColor.ORANGE));
	        test.skip(result.getThrowable());
	    }

		
	}
	
	
	@AfterSuite
	public void teardown() {

		extent.flush();
		//commit on git 

	}

}
