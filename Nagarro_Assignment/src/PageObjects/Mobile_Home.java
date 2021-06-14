package PageObjects;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.Status;

import TestCases.TC01_Mobile_Home;
import io.appium.java_client.android.AndroidDriver;

public class Mobile_Home extends TC01_Mobile_Home {
	
	@FindBy(id = "android:id/button1")
	WebElement popoupButton;

	@FindBy(id = "android:id/title")
	WebElement homepageTitle;

	@FindBy(id = "io.selendroid.testapp:id/buttonTest")
	WebElement enButton;

	@FindBy(id = "android:id/button2")
	WebElement EndActivityNoButton;

	@SuppressWarnings("unchecked")
	public Mobile_Home(@SuppressWarnings("rawtypes") AndroidDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	/**
	 * Verify popup if present and homepage title
	 * 
	 */
	public void commonBase() {

		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

		if (popoupButton.isDisplayed()) {
			popoupButton.click();
		}
		child.log(Status.INFO, "Homepage title is - " + homepageTitle.getText());
		log.info("Homepage title is - " + homepageTitle.getText());
		assertEquals("selendroid-test-app", homepageTitle.getText());

	}

	/**
	 * Click on EN button and perform required action
	 * 
	 */
	public void enBtnVerify() {

		enButton.click();

		EndActivityNoButton.click();

		assertEquals("selendroid-test-app", homepageTitle.getText());

	}
}
