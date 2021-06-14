package TestCases;

import java.io.IOException;
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
import PageObjects.Web_ControlGroup;

public class TC08_Web_Controlgroup extends TestBase {

	public static final Logger log = LogManager.getLogger(TC08_Web_Controlgroup.class.getName());

	public static ExtentTest child;

	@BeforeMethod
	public void setup() throws InterruptedException, IOException {
		init();
	}

	public TC08_Web_Controlgroup() {

	}

	@Test(priority = 1)
	@Parameters({ "rentCar1", "transmissionType1",  "numberofCar1", "rentCar2", "transmissionType2", "numberofCar2" })
	public void RentCarIncontrolgroup(String rentCar1, String transmissionType1, String numberofCar1, String rentCar2,
			String transmissionType2, String numberofCar2) throws Exception {

		child = ExtentTestManager.startTest("Web - Search Competitor", "Search Competitor");
		child.log(Status.INFO, "Search Competitor in true voice");
		log.info("Search Competitor in true voice");
		Web_ControlGroup controlgroup = new Web_ControlGroup(webDriver);
		controlgroup.controlgroupTab(rentCar1, transmissionType1,  numberofCar1, rentCar2, transmissionType2, numberofCar2);

	}

	@AfterMethod
	public void endTest() {
		if (webDriver != null) {
			webDriver.quit();
		}
	}

}
