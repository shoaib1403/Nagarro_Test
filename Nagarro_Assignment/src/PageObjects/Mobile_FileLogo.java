package PageObjects;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.Status;

import Util_Source.ExtentTestManager;
import TestCases.TC03_Mobile_FileLogo;
import io.appium.java_client.android.AndroidDriver;


public class Mobile_FileLogo extends TC03_Mobile_FileLogo {

	@FindBy(id = "android:id/title")
	public WebElement homepageTitle;

	@FindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout[2]/android.widget.ScrollView/android.widget.LinearLayout/android.widget.TextView[1]")
	public WebElement verifyRegistrationpage;

	@FindBy(id = "io.selendroid.testapp:id/buttonTest")
	public WebElement verifyHomePage;

	@FindBy(id = "io.selendroid.testapp:id/inputUsername")
	public WebElement inputUsername;

	@FindBy(id = "io.selendroid.testapp:id/inputEmail")
	public WebElement inputEmail;

	@FindBy(id = "io.selendroid.testapp:id/inputPassword")
	public WebElement inputPassword;

	@FindBy(id = "io.selendroid.testapp:id/inputName")
	public WebElement defaultInputName;

	@FindBy(id = "android:id/text1")
	public WebElement defaultProgrammingLanguageg;

	@FindBy(id = "io.selendroid.testapp:id/input_adds")
	public WebElement TCCheckBox;

	@FindBy(id = "io.selendroid.testapp:id/btnRegisterUser")
	public WebElement registerUserButton;

	@FindBy(id = "io.selendroid.testapp:id/label_name_data")
	public WebElement verifyRegisteredDefaultName;

	@FindBy(id = "io.selendroid.testapp:id/label_username_data")
	public WebElement verifyRegisteredUsername;

	@FindBy(id = "io.selendroid.testapp:id/label_password_data")
	public WebElement verifyRegisteredPassword;

	@FindBy(id = "io.selendroid.testapp:id/label_email_data")
	public WebElement verifyRegisteredEmail;

	@FindBy(id = "io.selendroid.testapp:id/label_preferedProgrammingLanguage_data")
	public WebElement verifyRegisteredProgrammingLanguage;

	@FindBy(id = "io.selendroid.testapp:id/buttonRegisterUser")
	public WebElement verificationPageRegisterUserButton;

	@SuppressWarnings("unchecked")
	public Mobile_FileLogo(@SuppressWarnings("rawtypes") AndroidDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	/**
	 * Register in file logo page and verify
	 * 
	 * @param name, password, emailID
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public void fileLogoTab(String name, String password, String emailID) throws InterruptedException, IOException {

		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

		driver.findElementByAccessibilityId("startUserRegistrationCD").click();

		Thread.sleep(3000);
		driver.hideKeyboard();

		assertEquals("selendroid-test-app", homepageTitle.getText());
		assertEquals("Welcome to register a new User", verifyRegistrationpage.getText());

		inputUsername.sendKeys(name);
		inputEmail.sendKeys(emailID);
		inputPassword.sendKeys(password);

		child.log(Status.INFO, "Name is - " + name + ", Email ID - " + emailID + ", paassword - " + password);

		assertEquals("Mr. Burns", defaultInputName.getText());
		assertEquals("Ruby", defaultProgrammingLanguageg.getText());

		TCCheckBox.click();

		child.log(Status.INFO, "File logo Form data - " + ExtentTestManager.getTest()
				.addScreenCaptureFromPath(getScreenshot(driver, "FileLogoFormData")).toString());

		registerUserButton.click();

		// Verify registered user
		assertEquals("Mr. Burns", verifyRegisteredDefaultName.getText());
		assertEquals(name, verifyRegisteredUsername.getText());
		assertEquals(password, verifyRegisteredPassword.getText());
		assertEquals(emailID, verifyRegisteredEmail.getText());
		assertEquals("Ruby", verifyRegisteredProgrammingLanguage.getText());

		child.log(Status.INFO,
				"Successfully verified Default name, username, password, email ID, programming language");

		child.log(Status.INFO, "File logo item added - " + ExtentTestManager.getTest()
				.addScreenCaptureFromPath(getScreenshot(driver, "FileLogoData")).toString());

		verificationPageRegisterUserButton.click();

		// Verified homepage again
		assertEquals("EN Button", verifyHomePage.getText());
		child.log(Status.INFO, "Verified homepage and text verified is - " + verifyHomePage.getText());

	}

}