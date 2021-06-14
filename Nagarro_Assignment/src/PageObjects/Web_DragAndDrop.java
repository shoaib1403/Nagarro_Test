package PageObjects;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.aventstack.extentreports.Status;
import org.testng.Assert;

import Util_Source.ExtentTestManager;

import TestCases.TC06_Web_DragAndDrop;

public class Web_DragAndDrop extends TC06_Web_DragAndDrop {
	
	@FindBy(xpath = "//li/a[@href='https://jqueryui.com/demos/']")
	WebElement verifyHomePage;

	@FindBy(xpath = "//a[contains(text(),'Droppable')]")
	WebElement droppableButton;

	@FindBy(xpath = "//h1[@class='entry-title']")
	WebElement verifyDroppabletitle;

	@FindBy(xpath = "//iframe[@src='/resources/demos/droppable/default.html']")
	WebElement dragDropFrame;

	@FindBy(id = "draggable")
	WebElement dragSource;

	@FindBy(id = "droppable")
	WebElement dragDestination;

	@FindBy(xpath = "//div[@id='droppable']/p")
	WebElement verifyDroppedSuccess;

	public Web_DragAndDrop(WebDriver driver) {
		this.webDriver = driver;
		PageFactory.initElements(driver, this);
	}

	public void dragDropDroppableTab() throws IOException, InterruptedException {

		explicatWait(webDriver, verifyHomePage);

		Assert.assertEquals("Demos", verifyHomePage.getText());

		droppableButton.click();

		Assert.assertEquals("Droppable", verifyDroppabletitle.getText());

		webDriver.switchTo().frame(dragDropFrame);

		scrollView(webDriver, dragSource);

		Actions act = new Actions(webDriver);
		act.dragAndDrop(dragSource, dragDestination).build().perform();

		Assert.assertEquals("Dropped!", verifyDroppedSuccess.getText());

		log.info("Dragged and Dropped successfully");
		child.log(Status.INFO, "Dragged and Dropped successfully");

		child.log(Status.INFO, "Dragged and Dropped - " + ExtentTestManager.getTest()
				.addScreenCaptureFromPath(getScreenshot(webDriver, "DraggedDropped")).toString());

	}

}
