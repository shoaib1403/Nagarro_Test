package TestCases;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Util_Source.ExtentTestManager;
import Util_Source.TestBase;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;

public class TC_PostRequest extends TestBase {
	
	public static final Logger log = LogManager.getLogger(TC_PostRequest.class.getName());
	public static ExtentTest child;
	
	
	@BeforeMethod
	public void setup() throws InterruptedException, IOException {
		init();
	}
	
	public TC_PostRequest() {

	}
	

	@Test(priority = 1)
	@Parameters({ "getURL","name","job" })
	public void PostStatusScheme(String getURL, String name, String job) throws Exception {
		 
		
		child = ExtentTestManager.startTest("Rest Assured - POst Request", "verify Post Response code");
		child.log(Status.INFO, "Verify first Name and Job nodes through Post Request");
		log.info("Verify first Name and Job nodes through Post Request");
		 
//		 RestAssured.baseURI =getURL;
		RestAssured.baseURI ="https://reqres.in";
		RestAssured.basePath ="/api";
/*		 RequestSpecification request = RestAssured.given();
		 
		 JSONObject requestParams = new JSONObject();
		 requestParams.put("name", name); // Cast
		 requestParams.put("job", job);
		
		 request.body(requestParams.toJSONString());
		 Response response = request.post("/register");		*/
		 
		 String requestBody = "{\n" +
		            "  \"name\": \"Bryant\",\n" +
		            "  \"job\": \"BA\" \n" +
		            "}\n" + "";
		 
		 Response response = given()
	                .contentType("application/json")
	                .body(requestBody)
	                .when()
	                .post("/users")
	                .then()
	                .using()
	                .extract().response();
		 
		 String Body = response.getBody().asString();
		 int statusCode = response.getStatusCode();
		 Assert.assertEquals(statusCode, 201);
		 //Assert.assertEquals( "Correct Success code was returned", statusCode, "OPERATION_SUCCESS");
		 log.info("Correct status code returned");
		child.log(Status.INFO, "Correct status code returned");
		Assert.assertEquals("Bryant", response.jsonPath().getString("name"));
		Assert.assertEquals("BA", response.jsonPath().getString("job"));
	//	Assert.assertEquals("101", response.jsonPath().getString("id"));
		
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