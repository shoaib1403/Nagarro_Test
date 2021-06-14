package TestCases;

import java.io.IOException;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
//import org.apache.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Util_Source.ExtentTestManager;
import Util_Source.TestBase;
import PageObjects.Web_List_Select;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class TC07_Web_List_Select extends TestBase {

	public static final Logger log = LogManager.getLogger(TC07_Web_List_Select.class.getName());

	public static ExtentTest child;

	@BeforeMethod
	public void setup() throws InterruptedException, IOException {
		init();
	}

	public TC07_Web_List_Select() {

	}

	@Test(priority = 1)
	@Parameters({ "itemList" })
	public void selectable(String itemList) throws Exception {

		child = ExtentTestManager.startTest("Web - verify multiple select", "verify multiple select");
		child.log(Status.INFO, "Verify multiple select in jqueryui");
		log.info("Verify multiple select in jqueryui");
		Web_List_Select selectable = new Web_List_Select(webDriver);
		selectable.selectItemTab(itemList);
		log.info("Selected multiple elements successfully");
		child.log(Status.INFO, "Selected multiple elements successfully");
	}

	@AfterMethod
	public void endTest() {
		if (webDriver != null) {
			webDriver.quit();
		}
	}

}
