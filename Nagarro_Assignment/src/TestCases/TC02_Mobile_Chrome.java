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
import PageObjects.Mobile_ChromeLogo;
import PageObjects.Mobile_Home;

public class TC02_Mobile_Chrome extends TestBase {

	public static final Logger log = LogManager.getLogger(TC02_Mobile_Chrome.class.getName());
	public static ExtentTest child;

	@BeforeMethod
	public void init() throws IOException {
		capabilities();
	}

	@Test(priority = 1)
	public void addNameChrome() throws IOException, InterruptedException, AWTException {

		child = ExtentTestManager.startTest("Mobile - Add details in chrome", "Add details in chrome");
		child.log(Status.INFO, "Add name and car in chrome and verify");
		Mobile_Home homepage = new Mobile_Home(driver);
		homepage.commonBase();

		Mobile_ChromeLogo chromeLogoPage = new Mobile_ChromeLogo(driver);
		chromeLogoPage.chromeLogoTab("Shoaib", "Mercedes");
		log.info("successfully added name and car in chrome and verified");
		child.log(Status.INFO, "successfully added name and car in chrome and verified");
	}

	@AfterMethod
	public void endTest() {
		if (driver != null) {
			driver.quit();
		}
	}

}
