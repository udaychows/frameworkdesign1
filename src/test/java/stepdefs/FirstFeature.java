package stepdefs;

import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.fasterxml.jackson.core.JsonProcessingException;

import apiMethods.UserApi;
import baseFrameWork.ExtentFactory;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import listeners.TestListener;
import models.testDataModel.JsonData;
import testDataManagement.JsonTestData;
import testDataManagement.TestDataResource;

public class FirstFeature {
	ExtentTest logger;
	ExtentTest test;
	Response response;
	String endpoint;
	String scenario;
	String sheetname;

	@Given("when the user had the {string}")
	public void when_the_user_had_the(String inputFileName) {
		TestDataResource.setTestData(inputFileName, sheetname);
	}

	@When("user hits endpoint Post request {string} to validate {string}")
	public void user_hits_endpoint_Post_request_to_validate(String string, String string2) throws JsonProcessingException {

		JsonData data = JsonTestData.getJsondata(string2);
		System.out.println(data.getValue("ContentType"));
		test = TestListener.report.createTest(data.getTestCaseName());
		ExtentFactory.getInstance().setExtent(test);
		
			UserApi api = new UserApi();
			response = api.hitPostRequest(string, data);
			System.out.println(response.asPrettyString());

		

	}

	@When("method GET with {string} to validate {string}")
	public void method_GET_with_to_validate(String string, String string2) {
		JsonData data = JsonTestData.getJsondata(string2);
		System.out.println(data.getValue("ContentType"));

		test = TestListener.report.createTest(data.getTestCaseName());
		ExtentFactory.getInstance().setExtent(test);
		System.out.println("Test Case get with Thread Id:- " + Thread.currentThread().getId());

		UserApi api = new UserApi();
		response = api.hitGetRequest(string, data);
		System.out.println(response.asPrettyString());

	}

	@Then("status {int}")
	public void status(int int1) {
		Assert.assertEquals(response.getStatusCode(), int1);
	}
	



}
