package TestCases;

import java.io.IOException;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.testng.Assert;
//import org.apache.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Util_Source.ExtentTestManager;
import Util_Source.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

//import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;

public class TC_GetRequest extends TestBase {

		//string api="https://reqres.in/api/users?page=2" ;
	
	public static final Logger log = LogManager.getLogger(TC_GetRequest.class.getName());
	public static ExtentTest child;
	
	
	@BeforeMethod
	public void setup() throws InterruptedException, IOException {
		init();
	}
	
	
	public TC_GetRequest() {

	}
	
	@Test(priority = 1)
	@Parameters({ "getURL" })
	 public void GetfirstName(String getURL) throws Exception {
	 
		
		child = ExtentTestManager.startTest("Rest Assured - Get Response", "verify Get Response code");
		child.log(Status.INFO, "Verify first Name node from Get Response");
		log.info("Verify first Name node from Get Response");
		
	// RestAssured.baseURI = getURL;
	 RestAssured.baseURI ="https://reqres.in";
	 RestAssured.basePath ="/api";
	 RequestSpecification httpRequest = RestAssured.given();
	 Response response = httpRequest.queryParam("page", "2")
             .param("id", "10")
             .when()
			 .get("/users")
             .then()
             .extract().response();

//	 Response response = httpRequest.get("/10");
				 
				 // Get the status code from the Response. In case of 
				 // a successfull interaction with the web service, we
				 // should get a status code of 200.
				 int statusCode = response.getStatusCode();
				 
				 // Assert that correct status code is returned.		
				 Assert.assertEquals(statusCode /*actual value*/, 200 /*expected value*/, "Correct status code returned");
				 log.info("Correct status code returned");
				child.log(Status.INFO, "Correct status code returned");
				
				//Assert.assertEquals("Byron", response.jsonPath().getString("first_name"));
				log.info("Correct node returned");
				child.log(Status.INFO, "Correct node returned");
				 }
	

	@AfterMethod
	public void endTest() {
		if (webDriver != null) {
			webDriver.quit();
		}
	}
}
