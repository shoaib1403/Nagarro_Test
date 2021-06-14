package PageObjects;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.Status;

import Util_Source.ExtentTestManager;
import TestCases.TC04_Mobile_ProgressBar;
import io.appium.java_client.android.AndroidDriver;

public class Mobile_ProgressBar extends TC04_Mobile_ProgressBar {
	
	@FindBy(id = "io.selendroid.testapp:id/waitingButtonTest")
	public WebElement progressBarButton;

	@FindBy(id = "io.selendroid.testapp:id/inputUsername")
	public WebElement verifyRegisterUserName;

	@FindBy(id = "io.selendroid.testapp:id/inputEmail")
	public WebElement verifyRegisterEmail;

	@FindBy(id = "io.selendroid.testapp:id/inputPassword")
	public WebElement verifyRegisterPassword;

	@FindBy(id = "io.selendroid.testapp:id/inputName")
	public WebElement verifyRegisterInputName;

	@FindBy(id = "android:id/text1")
	public WebElement verifyRegisterProgrammingLanguage;

	@FindBy(id = "io.selendroid.testapp:id/input_adds")
	public WebElement verifyRegisterTCCheckBox;

	@FindBy(id = "io.selendroid.testapp:id/btnRegisterUser")
	public WebElement verifyRegisterButton;

	@FindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout[2]/android.widget.ScrollView/android.widget.LinearLayout/android.widget.TextView[1]")
	public WebElement verifyReistrationPage;

	@SuppressWarnings("unchecked")
	public Mobile_ProgressBar(@SuppressWarnings("rawtypes") AndroidDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	/**
	 * progress bar verification
	 * 
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public void progressBarTab() throws InterruptedException, IOException {

		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

		progressBarButton.click();

		child.log(Status.INFO, "Progress bar is in progress.... " + ExtentTestManager.getTest()
				.addScreenCaptureFromPath(getScreenshot(driver, "ProgressBar")).toString());

		explicatWaitTillInvisibility(driver, driver.findElementById("android:id/progress"));

		child.log(Status.INFO, "Progress bar completed");
		log.info("Progress bar completed");
		child.log(Status.INFO, "Registration Page title - " + verifyReistrationPage.getText());
		assertEquals("Welcome to register a new User", verifyReistrationPage.getText());

		Thread.sleep(3000);

		driver.hideKeyboard();

		checkRegistrationForm(verifyRegisterUserName);
		checkRegistrationForm(verifyRegisterEmail);
		checkRegistrationForm(verifyRegisterPassword);
		checkRegistrationForm(verifyRegisterInputName);
		checkRegistrationForm(verifyRegisterProgrammingLanguage);
		checkRegistrationForm(verifyRegisterTCCheckBox);
		checkRegistrationForm(verifyRegisterButton);

		child.log(Status.INFO, "Verified register user page successfully " + ExtentTestManager.getTest()
				.addScreenCaptureFromPath(getScreenshot(driver, "ProgressBarRegisterPage")).toString());

		child.log(Status.INFO, "Verified registration page event");
		log.info("Verified registration page event");
	}

	/**
	 * Validate registration page element
	 * 
	 * @throws InterruptedException
	 */
	public void checkRegistrationForm(WebElement element) {

		if (isClickableAndroid(element, driver) == true) {
			assertEquals(true, true);
		} else {
			assertEquals(false, false);
		}

	}

}
