package com.prm.pageobjects.utils;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Platform;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
//import org.openqa.selenium.phantomjs.PhantomJSDriver;
//import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import bsh.ParseException;

/**
 * 
 * @author Ajit Yadav
 *
 */
public class PCDriver {

	public WebDriver driver;
	// log4jClass log = new log4jClass();

	public void invokeBrowser(String browserName) throws IOException {

		switch (browserName) {

		case "firefox":
			DesiredCapabilities cap = new DesiredCapabilities();
			cap.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
			System.setProperty("webdriver.gecko.driver", ".\\exeFiles\\geckodriver.exe");
			driver = new FirefoxDriver(cap);
			driver.get(ReadConfig.getInstance().getApplicationUrl());
			driver.manage().window().maximize();
			// log.info("Browser Invoked");
			break;

		case "htmlunit":
			DesiredCapabilities capHtml = DesiredCapabilities.htmlUnit();
			capHtml.acceptInsecureCerts();
			capHtml.setJavascriptEnabled(true);

			System.setProperty("webdriver.gecko.driver",
					ReadConfig.getInstance().getDriverPath().toString() + "geckodriver.exe");
			driver = new HtmlUnitDriver(capHtml);
			driver.get(ReadConfig.getInstance().getApplicationUrl());
			// log.info("Browser Invoked");
			break;

		case "chrome":
			System.setProperty("webdriver.chrome.driver", ".\\exeFiles\\chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.get(ReadConfig.getInstance().getApplicationUrl());
			break;

		case "ie":
			DesiredCapabilities capIE = DesiredCapabilities.edge();
			capIE.setPlatform(Platform.WIN10);
			capIE.acceptInsecureCerts();
			capIE.setJavascriptEnabled(true);
			System.setProperty("webdriver.ie.driver",
					ReadConfig.getInstance().getDriverPath().toString() + "IEDriverServer.exe");
			driver = new InternetExplorerDriver(capIE);
			/*
			 * case "edge": DesiredCapabilities capEdge = DesiredCapabilities.edge();
			 * capEdge.setPlatform(Platform.WIN10); capEdge.acceptInsecureCerts();
			 * capEdge.setJavascriptEnabled(true); EdgeOptions options = new EdgeOptions();
			 * options.setPageLoadStrategy("eager");
			 * System.setProperty("webdriver.edge.driver",
			 * ReadConfig.getInstance().getDriverPath().toString() +
			 * "MicrosoftWebDriver.exe"); driver = new EdgeDriver(options);
			 * 
			 * case "phantomjs": DesiredCapabilities capPhantom =
			 * DesiredCapabilities.phantomjs(); ArrayList<String> cliArgsCap = new
			 * ArrayList<String>(); cliArgsCap.add("--web-security=false");
			 * cliArgsCap.add("--ssl-protocol=any");
			 * cliArgsCap.add("--ignore-ssl-errors=true");
			 * cliArgsCap.add("--webdriver-loglevel=NONE");
			 * cliArgsCap.add("--load-images=true"); //
			 * capPhantom.setBrowserName("PhantomJs");
			 * capPhantom.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
			 * capPhantom.setCapability("trustAllSSLCertificates", true);
			 * 
			 * capPhantom.setCapability(PhantomJSDriverService.PHANTOMJS_CLI_ARGS,
			 * cliArgsCap);
			 * 
			 * capPhantom.setJavascriptEnabled(true);
			 * capPhantom.setCapability("takesScreenshot", true);
			 * capPhantom.setCapability(PhantomJSDriverService.
			 * PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
			 * ReadConfig.getInstance().getDriverPath().toString() + "phantomjs.exe");
			 * driver = new PhantomJSDriver(capPhantom);
			 * driver.manage().window().maximize();
			 * driver.get(ReadConfig.getInstance().getApplicationUrl());
			 */

		}
	}

	public void close() {
		driver.close();
	}

	public WebDriver getDriver() {
		return driver;
	}

	public void acceptAlert() {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.alertIsPresent());
		driver.switchTo().alert().accept();
	}

	public void dismissAlert() {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.alertIsPresent());
		driver.switchTo().alert().dismiss();
	}
	public SoftAssert softAssert(){
		waitForPageLoad();
		SoftAssert soft=new SoftAssert();
		return soft;
	}
	public void waitForPageLoad() {
		ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
			}
		};
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(pageLoadCondition);
	}
	

	public void waitForElementToBeClickable(WebElement ele) {
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.elementToBeClickable(ele));
	}
	
	public void waitForElementVisibility(WebElement ele,int secs) {
		WebDriverWait wait = new WebDriverWait(driver,secs);
		wait.until(ExpectedConditions.visibilityOf(ele));
	}

	public void visibilityOfListLocated(List<WebElement> ele) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfAllElements(ele));
	}

	public void WaitTillElementIsPresent(final WebElement ele) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(60))
				.pollingEvery(Duration.ofMillis(500)).ignoring(NoSuchElementException.class);				
		wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return ele;
			}
		});
	}

	public void selectFromDropDownByIndex(WebElement ele, int index) {
		waitForElementToBeClickable(ele);
		new Select(ele).selectByIndex(index);
	}

	public void deSelectFromDropDown(WebElement ele) {
		waitForElementToBeClickable(ele);
		new Select(ele).deselectAll();
	}
	public void selectFromDropDownByVisibleText(WebElement ele, String value) {
		waitForElementToBeClickable(ele);
		new Select(ele).selectByVisibleText(value);
	}
	
	public String firstSelectedOption(WebElement ele) {
		waitForElementToBeClickable(ele);
		String name = new Select(ele).getFirstSelectedOption().getText();
		System.out.println(name);
		return name;
	} 
	
	public void uploadFile(String str) throws AWTException {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		StringSelection selection = new StringSelection(str);
		java.awt.datatransfer.Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(selection, null);
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		robot.keyPress(KeyEvent.VK_ENTER);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		robot.keyRelease(KeyEvent.VK_ENTER);
	}

	public void waitForElementToDisappear(By by) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.ignoring(StaleElementReferenceException.class).until(ExpectedConditions.invisibilityOfElementLocated(by));
	}
	
	public void waitTillInvisiblityOfElement(WebElement element) {
		WebDriverWait wait=new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.invisibilityOf(element));
		
	}
	
	public void waitForPageToBecomeActive() {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='modal-backdrop fade  in']")));
	}
	
	public void waitForModalOverlayToDisappear() {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='modal overlay show']")));
	}
	
	public void waitForSpinnerToDisappear() {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//img[@class='spinner']")));
	}
	
	public void waitForUIWidgetOverlayToDisappear(){
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='ui-widget-overlay']")));
	}


	public void waitForSpinnerToDisappearOnDoctorDashboard() {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='ui-widget-overlay']")));
	}
	
	public void hoverOnElement(WebElement ele) {
		Actions action = new Actions(driver);
		action.moveToElement(ele);
		action.perform();
	}
	
	public void moveToElementAndClick(WebElement ele) {
		Actions action = new Actions(driver);
		action.moveToElement(ele).click().build().perform();
	
	}
	
	public void clickEscapeKeyboardButton()
	{
		Actions action = new Actions(driver);
		action.sendKeys(Keys.ESCAPE);
	}

	public void switchToDefaultContent() {
		driver.switchTo().defaultContent();
	}

	public void switchToFrame(WebElement frame) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frame));
	}

	public void switchToFrameBasedOnFrameName(String frameName) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameName)); 
	}

	public void waitForElementToBeEnable(By by) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.presenceOfElementLocated(by));
	}
	
	public void waitForModalToInvisibility() {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		WebElement modal = driver.findElement(By.xpath("/div[@id='profileview']//button[@class='close']"));
		wait.until(ExpectedConditions.invisibilityOf(modal));
	}
	
	public void executeScript(WebElement ele) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", ele);
	}

	public void scrollPage(int height) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollTo(0, " + height + ")");
	}
	
	public void scrollToView(WebElement ele) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView(true);",ele);
	}
	
	public String convert24HourFormatTo12HourFormat(String time) throws java.text.ParseException, ParseException
	{
		final SimpleDateFormat sdf = new SimpleDateFormat("H:mm");
		final java.util.Date dateObj = sdf.parse(time);
		return new SimpleDateFormat("h:mm a").format(dateObj);
	}
	
	public String convertFormatingDate(String conversionDate) throws java.text.ParseException
	{
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		java.util.Date date = sdf.parse(conversionDate);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String dateText = date.toString().formatted(dtf);
		return dateText;
	}

	public void verifyPageTitle(String expectTitle) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.titleIs(expectTitle));
	
	}
	public WebElement findElement(By arg0) {
		return null;
	}

	public void switchToWindow(String strWindowName) {
		driver.switchTo().window(strWindowName);
	}

	public void switchToDefaultWindow() {
		driver.switchTo().defaultContent();
	}

	public List<WebElement> findElements(By arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public void get(String arg0) {
		// TODO Auto-generated method stub
	}

	public String getCurrentUrl() {
		return driver.getCurrentUrl();
	}

	public String getPageSource() {
		return driver.getPageSource();
	}

	public String getTitle() {
		return driver.getTitle();
	}
	
	public void navigateBack()
	{
		driver.navigate().back();
	}

	public String getWindowHandle() {
		return null;
	}

	public Set<String> getWindowHandles() {
		return driver.getWindowHandles();
	}

	public void pressTabKey(WebDriver driver) {
		Actions action=new Actions(driver);
		action.sendKeys( Keys.TAB).build().perform();

	}
	
	public String getCurrentTime() {
		// Get the current date and time in India
        LocalDateTime indiaTime = LocalDateTime.now(ZoneId.of("Asia/Kolkata"));

        // Create a formatter for the time format you want (hh:mm a)
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");

        // Format the time with the formatter and print it
        String formattedTime = indiaTime.format(formatter);
        System.out.println("Current time in India: " + formattedTime);
        
        return formattedTime;
	}
}
