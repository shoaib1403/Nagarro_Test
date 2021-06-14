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
import PageObjects.Mobile_Home;
import PageObjects.Mobile_ProgressBar;

public class TC04_Mobile_ProgressBar extends TestBase {

	public static final Logger log = LogManager.getLogger(TC04_Mobile_ProgressBar.class.getName());

	public static ExtentTest child;

	@BeforeMethod
	public void init() throws IOException {
		capabilities();
	}

	@Test(priority = 1)
	public void verifyProgressBar() throws IOException, InterruptedException, AWTException {

		child = ExtentTestManager.startTest("Mobile - verify progress bar", "Verify progress bar");
		child.log(Status.INFO, "Verify progress bar");
		log.info("Verify progress bar");
		Mobile_Home homepage = new Mobile_Home(driver);
		homepage.commonBase();

		Mobile_ProgressBar progressBarPage = new Mobile_ProgressBar(driver);
		progressBarPage.progressBarTab();
		log.info("successfully verified progress bar and registartion page element");
		child.log(Status.INFO, "successfully verified progress bar and registartion page element");

	}

	@AfterMethod
	public void endTest() {
		if (driver != null) {
			driver.quit();
		}
	}

}
