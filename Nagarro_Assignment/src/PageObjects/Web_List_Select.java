package PageObjects;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import Util_Source.ExtentTestManager;
import TestCases.TC07_Web_List_Select;
import com.aventstack.extentreports.Status;


public class Web_List_Select extends TC07_Web_List_Select {
	
	@FindBy(xpath = "//li/a[@href='https://jqueryui.com/demos/']")
	WebElement verifyHomePage;

	@FindBy(xpath = "//a[contains(text(),'Selectable')]")
	WebElement selectableButton;

	@FindBy(xpath = "//h1[@class='entry-title']")
	WebElement verifySelectabletitle;

	@FindBy(xpath = "//iframe[@src='/resources/demos/selectable/default.html']")
	WebElement selectFrame;

	@FindBy(xpath = "//ol[@id='selectable']/li")
	List<WebElement> listOfItems;

	@FindBy(id = "selectable")
	WebElement itemView;

	public Web_List_Select(WebDriver driver) {
		this.webDriver = driver;
		PageFactory.initElements(driver, this);
	}


	public void selectItemTab(String itemsToSelect) throws InterruptedException, IOException {

		explicatWait(webDriver, verifyHomePage);

		Assert.assertEquals("Demos", verifyHomePage.getText());

		selectableButton.click();

		Assert.assertEquals("Selectable", verifySelectabletitle.getText());

		webDriver.switchTo().frame(selectFrame);
		scrollView(webDriver, itemView);

		String[] inputItemList = itemsToSelect.split(",");
		List<WebElement> webElementList = new ArrayList<>();
		for (WebElement option : listOfItems) {

			for (int i = 0; i < inputItemList.length; i++) {
				if (option.getText().equals(inputItemList[i].toString())) {
					webElementList.add(option);
				}
			}
		}

		for (int i = 0; i < webElementList.size(); i++) {
			Actions builder = new Actions(webDriver);
			builder.keyDown(Keys.CONTROL).click(webElementList.get(i)).keyUp(Keys.CONTROL);
			Action selectMultiple = builder.build();
			selectMultiple.perform();
		}

		child.log(Status.INFO, "Item selected - " + ExtentTestManager.getTest()
				.addScreenCaptureFromPath(getScreenshot(webDriver, "ItemSelected")).toString());

		log.info("Items selected successfully");
		child.log(Status.INFO, "Items selected successfully");

	}

}
