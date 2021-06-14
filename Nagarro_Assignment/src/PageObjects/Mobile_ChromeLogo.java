package PageObjects;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.Status;

import TestCases.TC02_Mobile_Chrome;
import Util_Source.ExtentTestManager;

import io.appium.java_client.android.AndroidDriver;

public class Mobile_ChromeLogo extends TC02_Mobile_Chrome {

	@FindBy(id = "android:id/title")
	public WebElement homepageTitle;

	@FindBy(id = "io.selendroid.testapp:id/buttonStartWebview")
	public WebElement chromeLogoButton;

	@FindBy(xpath = "//android.view.View[@package='io.selendroid.testapp']")
	public WebElement verifyFormPage;

	@FindBy(xpath = "//android.widget.EditText[@package='io.selendroid.testapp']")
	public WebElement nameInput;

	@FindBy(xpath = "//android.widget.Spinner[@package='io.selendroid.testapp'][@scrollable='false']")
	public WebElement selectCarButton;

	@FindBy(xpath = "//android.view.View[@index='0']")
	public WebElement verifyFormPageHeading;

	// Verify added name
	@FindBy(xpath = "//android.view.View[@index='3']")
	public WebElement verifyAddedName;

	// Verify Added car
	@FindBy(xpath = "//android.view.View[@index='5']")
	public WebElement verifyAddedCar;

	@FindBy(xpath = "//android.view.View[@index='9']")
	public WebElement StartAgainButton;

	@SuppressWarnings("unchecked")
	public Mobile_ChromeLogo(@SuppressWarnings("rawtypes") AndroidDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	/**
	 * Add name and car details in chrome and verify
	 * 
	 * @param name, carName
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public void chromeLogoTab(String name, String carName) throws InterruptedException, IOException {

		explicatWait(driver, chromeLogoButton);

		chromeLogoButton.click();

		explicatWait(driver, driver.findElementById("io.selendroid.testapp:id/tableRowWebview"));

		assertEquals("Hello, can you please tell me your name?", verifyFormPage.getText());

		child.log(Status.INFO, "Name entered is - " + name);

		nameInput.click();
		nameInput.clear();
		nameInput.sendKeys(name);

		selectCarButton.click();

		String carValue = "//android.widget.CheckedTextView[@package='io.selendroid.testapp'][@text='" + carName + "']";
		driver.findElement(By.xpath(carValue)).click();
		child.log(Status.INFO, "Car name entered is - " + carName);

		child.log(Status.INFO, "Item selected - " + ExtentTestManager.getTest()
				.addScreenCaptureFromPath(getScreenshot(driver, "ItemSelected")).toString());

		driver.findElement(By.xpath("//android.widget.Button[@text='Send me your name!']")).click();

		// Verified heading
		assertEquals("This is my way of saying hello", verifyFormPageHeading.getText());

		String expextedName = "\"" + name + "\"";

		// assert added name
		assertEquals(expextedName, verifyAddedName.getText());

		// Assert added car
		String expextedCar = "\"" + carName + "\"";
		assertEquals(expextedCar.toLowerCase(), verifyAddedCar.getText());

		child.log(Status.INFO, "Added data in chrome logo verification - " + ExtentTestManager.getTest()
				.addScreenCaptureFromPath(getScreenshot(driver, "ChromeLogoAddedData")).toString());

		child.log(Status.INFO, "Verified heading, name and car name");

		StartAgainButton.click();

	}

}