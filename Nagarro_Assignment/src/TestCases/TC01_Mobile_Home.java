package TestCases;

import java.io.IOException;

//import org.apache.log4j.Logger;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Util_Source.ExtentTestManager;
import Util_Source.TestBase;
import PageObjects.Mobile_Home;

public class TC01_Mobile_Home extends TestBase {

	public static final Logger log = LogManager.getLogger(TC01_Mobile_Home.class.getName());

	public static ExtentTest child;

	@BeforeMethod
	public void init() throws IOException {
		capabilities();
	}

	@Test(priority = 1)
	public void enBtnVerify() throws IOException, InterruptedException {

		child = ExtentTestManager.startTest("Mobile - verify selendroid homepage", "verify selandroid homepage");
		child.log(Status.INFO, "verify selendroid homepage");
		log.info("verify selandroid homepage");

		Mobile_Home homepage = new Mobile_Home(driver);
		homepage.commonBase();

		homepage.enBtnVerify();

		child.log(Status.INFO, "Successfully verified selendroid homepage");
		log.info("Successfully verified seledroid homepage");
	}

	@AfterMethod
	public void endTest() {
		if (driver != null) {
			driver.quit();
		}
	}

}
