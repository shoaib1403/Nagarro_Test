package PageObjects;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.Status;

import Util_Source.ExtentTestManager;
import TestCases.TC05_Mobile_Basics;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;

import static io.appium.java_client.touch.offset.PointOption.point;
import static io.appium.java_client.touch.WaitOptions.waitOptions;


public class Mobile_Basics extends TC05_Mobile_Basics {
	
	@FindBy(id = "io.selendroid.testapp:id/showToastButton")
	public WebElement showToastButton;

	@FindBy(xpath = "/hierarchy/android.widget.Toast")
	public WebElement verifyToastText;

	@FindBy(id = "io.selendroid.testapp:id/showPopupWindowButton")
	public WebElement showPopupWindowButton;

	@FindBy(id = "io.selendroid.testapp:id/exceptionTestButton")
	public WebElement exceptionTestButton;

	@FindBy(id = "io.selendroid.testapp:id/exceptionTestField")
	public WebElement exceptionTestField;

	@SuppressWarnings("unchecked")
	public Mobile_Basics(@SuppressWarnings("rawtypes") AndroidDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	/**
	 * verify Displays a toast.
	 * 
	 * @throws IOException
	 *
	 */
	public void verifyDisplaysAToast() throws IOException {

		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

		child.log(Status.INFO, "Click on toast button");

		showToastButton.click();

		child.log(Status.INFO, "Clicked on toast button - " + ExtentTestManager.getTest()
				.addScreenCaptureFromPath(getScreenshot(driver, "Toast button")).toString());

		WebDriverWait waitForToast = new WebDriverWait(driver, 25);
		waitForToast.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/hierarchy/android.widget.Toast")));

		assertEquals("Hello selendroid toast!", verifyToastText.getText());

		child.log(Status.INFO, "Toast text verified is - " + verifyToastText.getText());

	}

	/**
	 * verify Displays a popup.
	 *
	 * @exception InterruptedException
	 * @throws IOException
	 */
	public void verifyDisplaysPopup() throws InterruptedException, IOException {

		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

		showPopupWindowButton.click();

		child.log(Status.INFO, "Pop message is - displayed " + ExtentTestManager.getTest()
				.addScreenCaptureFromPath(getScreenshot(driver, "Popupmessage")).toString());

		tapByCoordinates(550, 1063);

	}

	/**
	 * verify unhandled Exception when clicking on button
	 *
	 */
	public void verifyUnhandledException() {

		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

		child.log(Status.INFO, "Click on unhandled exception");
		log.info("Click on unhandled exception");
		try {
			exceptionTestButton.click();
		} catch (Exception e) {
			// assert.fail(e.printstackTrace);
			child.log(Status.INFO, "Error log - " + e.toString());
			log.info("Error log - " + e.toString());
		}
	}

	/**
	 * verify unhandled exception by sending text
	 *
	 */
	public void verifyTypeAndUnhandledException(String test) throws IOException, InterruptedException {

		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		child.log(Status.INFO, "Pass 'test' text in input field ");

		try {
			exceptionTestField.sendKeys(test);

		} catch (Exception e) {

			child.log(Status.INFO, "Error log - " + e.toString());
			log.info("Error log - " + e.toString());
		}

	}

	@SuppressWarnings("rawtypes")
	public void tapByCoordinates(int x, int y) {
		new TouchAction(driver).tap(point(x, y)).waitAction(waitOptions(Duration.ofMillis(250))).perform();

	}

}
