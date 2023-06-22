package com.prm.pageobjects.utils;

import com.google.common.io.Files;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Ajit Yadav
 *
 */
public class ExtentReport {
	ITestResult result;
	private PCDriver loginPage;
	public ExtentReports report;
	public ExtentTest logger;

	public ExtentReport() {
	}

	public ExtentReport(PCDriver loginPage, String reportName) {
		this.loginPage = loginPage;
		report = new ExtentReports(".\\ExtentReport\\" + reportName + ".html", true);
		report.loadConfig(new File(System.getProperty("user.dir") + "\\ExtentReport.xml"));
	}

	public String getScreenShot(WebDriver driver, String screenshotName) throws Exception {
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		// after execution, you could see a folder "FailedTestsScreenshots" under src
		// folder
		String destination = "FailedTestsScreenshots/" + screenshotName + dateName + ".png";
		File finalDestination = new File(System.getProperty("user.dir"), destination);
		Files.copy(source, finalDestination);
		return destination;
	}

	public void onTestFailure(ITestResult result) {
		if (result.getStatus() == ITestResult.SUCCESS) {
		} else {
			try {
				logger.log(LogStatus.FAIL, logger.addScreenCapture(getScreenShot(loginPage.getDriver(), result.getName())));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
