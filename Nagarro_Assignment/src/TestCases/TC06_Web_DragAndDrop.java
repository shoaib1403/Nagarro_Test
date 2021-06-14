package TestCases;

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
import PageObjects.Web_DragAndDrop;

public class TC06_Web_DragAndDrop extends TestBase {

	public static final Logger log = LogManager.getLogger(TC06_Web_DragAndDrop.class.getName());
	public static ExtentTest child;

	@BeforeMethod
	public void setup() throws InterruptedException, IOException {
		init();
	}

	public TC06_Web_DragAndDrop() {

	}

	@Test(priority = 1)
	public void dragAndDrop() throws Exception {

		child = ExtentTestManager.startTest("Web - verify drag and drop", "Verify drag and drop");
		child.log(Status.INFO, "Drag and drop from source to destination");
		log.info("Drag and drop from source to destination");
		Web_DragAndDrop droppable = new Web_DragAndDrop(webDriver);
		droppable.dragDropDroppableTab();
		
	}

	@AfterMethod
	public void endTest() {
		if (webDriver != null) {
			webDriver.quit();
		}
	}
}
