package Util_Source;

import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {

	private static ExtentReports extent;
	private static String reportFileName = "Nagarro-Assignment-Report" + ".html";
	private static String fileSeperator = System.getProperty("file.separator");
	private static String reportFilepath = System.getProperty("user.dir") + fileSeperator + "TestReport";
	private static String reportFileLocation = reportFilepath + fileSeperator + reportFileName;

	public static ExtentReports getInstance() {
		if (extent == null)
			createInstance();
		return extent;
	}

	// Create an extent report instance
	public static ExtentReports createInstance() {
		String fileName = getReportPath(reportFilepath); 
		ExtentSparkReporter spark = new ExtentSparkReporter(fileName);
		//htmlReporter.config().setChartVisibilityOnOpen(false);
		spark.config().setTheme(Theme.DARK);
		spark.config().setDocumentTitle(reportFileName);
		spark.config().setEncoding("utf-8");
		spark.config().setReportName(reportFileName);

		extent = new ExtentReports();
		extent.attachReporter(spark);
		// Set environment details
		extent.setSystemInfo("OS", "Mobile - Android and Web");
		extent.setSystemInfo("Product", "jquery-web and selendroid-Mobile App");
		extent.setSystemInfo("Environment", ReadPropertiesFile.getProperty("environment"));

		return extent;
	}

	// Create the report path
	private static String getReportPath(String path) {
		File testDirectory = new File(path);
		if (!testDirectory.exists()) {
			if (testDirectory.mkdir()) {
				System.out.println("Directory: " + path + " is created!");
				return reportFileLocation;
			} else {
				System.out.println("Failed to create directory: " + path);
				return System.getProperty("user.dir");
			}
		} else {
			System.out.println("Directory already exists: " + path);
		}
		return reportFileLocation;
	}

}
