package TestCases;

import java.awt.AWTException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
//import org.apache.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Util_Source.ExtentTestManager;
import Util_Source.TestBase;
import PageObjects.Mobile_FileLogo;
import PageObjects.Mobile_Home;

public class TC03_Mobile_FileLogo extends TestBase {

	public static final Logger log = LogManager.getLogger(TC03_Mobile_FileLogo.class.getName());

	public static ExtentTest child;

	@BeforeMethod
	public void init() throws IOException {
		capabilities();
	}

	@Test(priority = 1)
	@Parameters({ "name", "password", "emailID" })
	public void fileLogo(String name, String password, String emailID)
			throws IOException, InterruptedException, AWTException {

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		child = ExtentTestManager.startTest("Mobile - File Logo", "File Logo");
		child.log(Status.INFO, "Register in file logo page and verify");
		log.info("Register in file logo page and verify");
		Mobile_Home homepage = new Mobile_Home(driver);
		homepage.commonBase();

		Mobile_FileLogo fileLogoPage = new Mobile_FileLogo(driver);
		fileLogoPage.fileLogoTab(name, password, emailID);

		child.log(Status.INFO, "Successfuflly Registered in file logo page and verified");
		log.info("Successfuflly Registered in file logo page and verified");
	}

	@AfterMethod
	public void endTest() {
		if (driver != null) {
			driver.quit();
		}
	}

}
