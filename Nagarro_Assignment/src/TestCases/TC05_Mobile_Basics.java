package TestCases;

import java.awt.AWTException;
import java.io.IOException;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
//import org.apache.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Util_Source.ExtentTestManager;
import Util_Source.TestBase;
import PageObjects.Mobile_Basics;
import PageObjects.Mobile_Home;

public class TC05_Mobile_Basics extends TestBase {

	public static final Logger log = LogManager.getLogger(TC05_Mobile_Basics.class.getName());
	public static ExtentTest child;

	@BeforeMethod
	public void init() throws IOException {
		capabilities();
	}

	@Test(priority = 1)
	public void verifyDisplaysAToastTest() throws IOException, InterruptedException, AWTException {

		child = ExtentTestManager.startTest("Mobile - Verify Toast", "Verify Toast");
		child.log(Status.INFO, "verify displays a Toast in selendroid");
		log.info("verify displays a Toast in selendroid");
		Mobile_Home homepage = new Mobile_Home(driver);
		homepage.commonBase();

		Mobile_Basics basics = new Mobile_Basics(driver);
		basics.verifyDisplaysAToast();
		log.info("Displays a Toast is successfully verified");
		child.log(Status.INFO, "Displays a Toast is successfully verified");

	}

	@Test(priority = 2)
	public void verifyDisplaysPopupTest() throws IOException, InterruptedException, AWTException {

		child = ExtentTestManager.startTest("Mobile - Verify Dismiss popup ", "Verify Dismiss popup");
		child.log(Status.INFO, "verify Dismiss popup in selendroid");

		Mobile_Home homepage = new Mobile_Home(driver);
		homepage.commonBase();

		Mobile_Basics basics = new Mobile_Basics(driver);
		basics.verifyDisplaysPopup();

		child.log(Status.INFO, "successfully verified Dismiss popup");

	}

	@Test(priority = 3)
	public void verifyUnhandledExceptionTest() throws IOException, InterruptedException, AWTException {

		child = ExtentTestManager.startTest("Mobile - verify unhandled exception in Tap",
				"Verify unhandled exception in Tap");
		child.log(Status.INFO, "verify unhandled exception in Tap in selandroid");

		Mobile_Home homepage = new Mobile_Home(driver);
		homepage.commonBase();

		Mobile_Basics basics = new Mobile_Basics(driver);
		basics.verifyUnhandledException();

		child.log(Status.INFO, "successfully verified Unhandled Exception in Tap");

	}

	@Test(priority = 4)
	public void verifyTypeAndUnhandledExceptionTest() throws IOException, InterruptedException, AWTException {

		child = ExtentTestManager.startTest("Mobile - verify unhandled exception by type",
				"Verify unhandled exception by type");
		child.log(Status.INFO, "verify unhandled exception with input type in selandroid");

		Mobile_Home homepage = new Mobile_Home(driver);
		homepage.commonBase();

		Mobile_Basics basics = new Mobile_Basics(driver);
		basics.verifyTypeAndUnhandledException("Test");

		child.log(Status.INFO, "successfully verified Unhandled Exception with input type");

	}

	@AfterMethod
	public void endTest() {
		if (driver != null) {
			driver.quit();
		}
	}

}
