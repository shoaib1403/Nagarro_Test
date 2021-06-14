package Util_Source;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.logging.log4j.Logger;
//import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.logging.log4j.LogManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.Reporter;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import io.github.bonigarcia.wdm.managers.FirefoxDriverManager;

public class TestBase {

	public static final Logger log = LogManager.getLogger(TestBase.class.getName());
	String browser = ReadPropertiesFile.getProperty("browser");

	public WebDriver webDriver;
	public AndroidDriver<AndroidElement> driver;

	/**
	 * Initialize a browser, enter URL
	 * 
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public void init() throws InterruptedException, IOException {
		selectBrowser(browser);
		getUrl(ReadPropertiesFile.getProperty("applicationUrl"));
		String log4jConfPath = "log4j.properties";
		PropertyConfigurator.configure(log4jConfPath);

	}

	/**
	 * Select a browser to perform action
	 * 
	 * @param Browser name
	 * @throws InterruptedException
	 */
	public void selectBrowser(String browser) throws InterruptedException {
		if (browser.equalsIgnoreCase("chrome")) {
			log.info("Creating object of " + browser);
			System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
			ChromeDriverManager.chromedriver().setup();
			webDriver = new ChromeDriver();
			TimeUnit.SECONDS.sleep(1);

		} else if (browser.equalsIgnoreCase("firefox")) {
			FirefoxDriverManager.firefoxdriver().setup();
			webDriver = new FirefoxDriver();
		} else if (browser.equalsIgnoreCase("headless")) {
			System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
			ChromeDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--headless", "--window-size=1200,1200", "--ignore-certificate-errors", "--silent");
			webDriver = new ChromeDriver(options);

		}
	}

	/**
	 * Get application URL
	 * 
	 * @param URL
	 */
	public void getUrl(String url) {

		log.info(url);

		webDriver.manage().window().maximize();
		webDriver.get(url);
		webDriver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);

	}

	/**
	 * Android Device config
	 * 
	 * @throws IOException
	 * 
	 *
	 */
	public void capabilities() throws IOException {

		File file = new File(ReadPropertiesFile.getProperty("SelendroidApp"));
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, ReadPropertiesFile.getProperty("deviceName"));
		cap.setCapability(MobileCapabilityType.PLATFORM_NAME, ReadPropertiesFile.getProperty("platformName"));
		//cap.setCapability(MobileCapabilityType.VERSION, "v1.15.1");
		cap.setCapability(MobileCapabilityType.UDID, ReadPropertiesFile.getProperty("udid"));
		cap.setCapability(MobileCapabilityType.APP, file.getAbsolutePath());
		cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");

		driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), cap);

	}

	/**
	 * Generate screenshot
	 * 
	 * @param WebDriver and image name
	 * 
	 * @throws IOException
	 */
	public String getScreenshot(WebDriver driver, String imageName) throws IOException {

		File image = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		if (imageName.equals("")) {
			imageName = "_blank";
		}

		Calendar calander = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");

		String reportDirectory = new File(System.getProperty("user.dir")).getAbsolutePath()
				+ "/src/Screenshot/";

		String actualImageName = reportDirectory + imageName + "_" + format.format(calander.getTime()) + ".png";
		File destFile = new File(actualImageName);
		FileUtils.copyFile(image, destFile);

		Reporter.log("<a href= '" + destFile.getAbsolutePath() + "'> <img src='" + destFile.getAbsolutePath()
				+ "' height='100' width ='100'/></a>");

		return actualImageName;

	}

	/**
	 * Method to Check if element is clickable or not
	 * 
	 */
	public static boolean isClickableAndroid(WebElement element, AndroidDriver<AndroidElement> driver) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 2);
			wait.until(ExpectedConditions.elementToBeClickable(element));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Method to wait till element is visible
	 * 
	 */
	public void explicatWait(WebDriver driver, WebElement xpathValue) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(xpathValue));
	}

	/**
	 * Method to wait till element become invisible
	 * 
	 */
	public void explicatWaitTillInvisibility(WebDriver driver, WebElement xpathValue) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.invisibilityOf(xpathValue));

	}

	/**
	 * scroll till element is visible
	 * 
	 * @param WebDriver and WebElement
	 */
	public void scrollView(WebDriver driver, WebElement element) {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	/**
	 * Method to Check if element is clickable or not
	 * 
	 */
	public static boolean isClickableWeb(WebElement el, WebDriver driver) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 2);
			wait.until(ExpectedConditions.elementToBeClickable(el));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
