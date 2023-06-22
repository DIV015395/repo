package com.prm.docdashboard.pageobject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.time.DateUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;
import org.testng.Assert;
import com.prm.pageobjects.utils.DatePicker;
import com.prm.pageobjects.utils.PCDriver;

/**
 * 
 * @author Ajit
 *
 */
public class ClinicEventsPage {
	private PCDriver loginPage;
	DatePicker datepicker;
	AppointmentsLisitngPage clinicAppointmentsPage;

	public ClinicEventsPage(PCDriver loginPage) {
		this.loginPage = loginPage;
		PageFactory.initElements(loginPage.getDriver(), this);
		clinicAppointmentsPage = new AppointmentsLisitngPage(loginPage);
		datepicker = new DatePicker(loginPage);
	}

	@FindBy(xpath = "//a[text()='Events']")
	private WebElement eventBtn;
	@FindBy(id = "eventFromDate")
	private WebElement eventFromDate;
	@FindBy(id = "eventToDate")
	private WebElement eventToDate;
	@FindBy(id = "eventCategory")
	private WebElement selEventCat;
	@FindBy(xpath = "//a[@id='eventSearchBtn']//b[text()='Search']")
	private WebElement searchBtnOnEvent;
	@FindBy(xpath = "//table[@id='clinicEventAllListTable']//th[text()='Date']")
	private WebElement date;
	@FindBy(xpath = "//table[@id='clinicEventAllListTable']//th[text()='Duration']")
	private WebElement duration;
	@FindBy(xpath = "//table[@id='clinicEventAllListTable']//th[text()='Title']")
	private WebElement title;
	@FindBy(xpath = "//table[@id='clinicEventAllListTable']//th[text()='Category']")
	private WebElement category;
	@FindBy(xpath = "//table[@id='clinicEventAllListTable']//th[text()='Doctor']")
	private WebElement doctor;
	@FindBy(xpath = "//table[@id='clinicEventAllListTable']//th[text()='Clinic']")
	private WebElement clinic;
	@FindBy(xpath = "//table[@id='clinicEventAllListTable']//th[text()='Action']")
	private WebElement action;
	@FindBy(xpath = "//table[@id='clinicEventAllListTable']//span[@class='dashboardEditIcon']")
	private WebElement editBtn;
	@FindBy(xpath = "//table[@id='clinicEventAllListTable']//span[@class='dashboardDeleteIcon']")
	private WebElement deleteBtn;
	@FindBy(xpath = "//a[@class='noDeleteBtn ui-btn ui-shadow ui-btn-corner-all ui-mini ui-btn-inline ui-btn-up-c']")
	private WebElement noBtnDelete;
	@FindBy(xpath = "//div[@id='deletePopUp']//span[text()='Yes']")
	private WebElement yesBtnDelete;
	@FindBy(xpath = "//th[text()='Date']/../../..//tbody//td[contains(@class,'sorting_')]")
	private List<WebElement> timeSlotApp;
	@FindBy(xpath = "//div[@class='clearfix alertMessage'][contains(.,'From date cannot be later than to date')]")
	private WebElement MsgFrmDateLater;
	@FindBy(css = "a[id='clinicEventAllListTable_next']")
	private WebElement nextBtn;

	public void selectFrmdateInEvent(String SelectDate) {
		loginPage.WaitTillElementIsPresent(eventFromDate);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		datepicker.selectDateofAppCal(SelectDate, eventFromDate);
	}

	public void selectTodateInEvent(String SelectDate) {
		loginPage.WaitTillElementIsPresent(eventToDate);
		datepicker.selectDateofAppCal(SelectDate, eventToDate);
	}

	public void selectCategroy(String categoryName) {
		loginPage.waitForElementToBeClickable(selEventCat);
		loginPage.selectFromDropDownByVisibleText(selEventCat, categoryName);
	}

	public void clickOnSearch() {
		loginPage.waitForElementToBeClickable(searchBtnOnEvent);
		searchBtnOnEvent.click();
	}

	public void clickOnEventBtn() {
		loginPage.waitForElementToBeClickable(eventBtn);
		eventBtn.click();
	}

	public void selectDoctor(String doctor) {
		loginPage.waitForElementToBeClickable(clinicAppointmentsPage.docDropDoownBtn);
		loginPage.selectFromDropDownByVisibleText(clinicAppointmentsPage.docDropDoownBtn, doctor);
	}

	public void verifyTitleDisplayOnEventlist(String titleOnEven) {
		loginPage.waitForPageLoad();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		WebElement title = loginPage.getDriver().findElement(By.xpath("//td[contains(text(),'" + titleOnEven + "')]"));
		loginPage.WaitTillElementIsPresent(title);
		boolean flag = title.isDisplayed();
		if (flag) {
		} else {
			Assert.assertFalse(flag);
		}
	}

	public void verifyAllMandatoryWebElement() {
		loginPage.waitForPageLoad();
		boolean flag;

		if (flag = eventFromDate.isDisplayed() && eventToDate.isDisplayed() && selEventCat.isDisplayed()
				&& searchBtnOnEvent.isDisplayed() && clinicAppointmentsPage.docDropDoownBtn.isDisplayed() && date.isDisplayed()
				&& duration.isDisplayed() && title.isDisplayed() && category.isDisplayed() && doctor.isDisplayed()
				&& clinic.isDisplayed() && action.isDisplayed()) {
		} else {
			Assert.assertTrue(flag);
		}
	}

	public boolean verifyEditAndDeletBtn() {
		loginPage.waitForPageLoad();
		loginPage.WaitTillElementIsPresent(deleteBtn);
		boolean flag;
		if (flag = editBtn.isDisplayed() && deleteBtn.isDisplayed()) {
		} else {
			Assert.assertTrue(flag);
		}
		return flag;
	}

	public void behaviourEditBtn() {
		loginPage.waitForElementToBeClickable(editBtn);
		editBtn.click();
		loginPage.waitForPageLoad();
	}

	public void clickOnDeleteBtn() {
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		loginPage.waitForElementToBeClickable(deleteBtn);
		deleteBtn.click();
	}

	public void clickOnNoBtnDeletePopUp() {
		// using sikuli here
		Screen screen = new Screen();
		Pattern pattern = new Pattern("D:\\clone\\prmautomation\\sikuliImages\\no.PNG");
		try {
			screen.wait(pattern, 3000);
			screen.click(pattern);
		} catch (FindFailed e) {
			e.printStackTrace();
		}
	}

	public void clickOnYesBtnDeletePopUp() {
		Screen screen = new Screen();
		Pattern pattern = new Pattern("D:\\clone\\prmautomation\\sikuliImages\\Yes.PNG");
		try {
			screen.wait(pattern, 3000);
			screen.click(pattern);
		} catch (FindFailed e) {
			e.printStackTrace();
		}
	}

	public void verifyAfterDelete(String titleOnEven) {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		loginPage.waitForPageLoad();
		WebElement title = loginPage.getDriver().findElement(By.xpath("//span[text()='" + titleOnEven + "']"));
		loginPage.WaitTillElementIsPresent(title);
		boolean flag = title.getAttribute("class").equals("strikeOff");
		if (flag) {
		} else {
			Assert.assertTrue(flag);
		}
	}

	public void dateFilter(String frmDate, String toDate) {
		SimpleDateFormat dt = new SimpleDateFormat("MM/dd/yy");
		try {
			Date Date1 = dt.parse(frmDate);
			Date Date2 = dt.parse(toDate);
			loginPage.waitForPageLoad();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			while (!nextBtn.getAttribute("class").contains("disabled")) {
				for (int i = 0; i < timeSlotApp.size(); i++) {
					boolean flag;
					if (flag = timeSlotApp.get(i).isDisplayed()) {
						String Apptime = timeSlotApp.get(i).getText();
						SimpleDateFormat dt1 = new SimpleDateFormat("dd-MM-yy");
						Date AppDate = dt1.parse(Apptime);
						boolean samedate = DateUtils.isSameDay(Date1, AppDate) || DateUtils.isSameDay(Date2, AppDate);
						boolean counter = AppDate.before(Date2) && AppDate.after(Date1);
						if (counter || samedate) {
						} else {
							Assert.assertTrue(counter);
						}
					} else {
						Assert.assertTrue(flag);
					}
				}
				loginPage.waitForPageLoad();
				nextBtn.click();
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public void checkFrmDateIsNotLaterToToDate() {
		loginPage.waitForElementToBeClickable(MsgFrmDateLater);
		Assert.assertTrue(MsgFrmDateLater.getText().contains("From date cannot be later than to date"));
		loginPage.waitForElementToBeClickable(clinicAppointmentsPage.noRecordFoundMsg);
		Assert.assertTrue(clinicAppointmentsPage.noRecordFoundMsg.isDisplayed());
	}

	public void verifyFilterDoctorInEventList(String DoctorName) {
		loginPage.waitForPageLoad();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		while (!nextBtn.getAttribute("class").contains("disabled")) {
			loginPage.waitForElementToBeEnable(By.id("clinicApptAllListTable_next"));
			List<WebElement> allDoctor = loginPage.getDriver()
					.findElements(By.xpath("//table[@id='clinicEventAllListTable']/../../..//tbody//td[5]"));
			int allDoctorSize = allDoctor.size();
			List<WebElement> filterDoctor = loginPage.getDriver().findElements(By.xpath(
					"//table[@id='clinicEventAllListTable']/../../..//td[contains(text(),'" + DoctorName + "')]"));
			List<WebElement> allDoctorFilter = loginPage.getDriver().findElements(
					By.xpath("//table[@id='clinicEventAllListTable']/../../..//td[contains(text(),'All Doctors')]"));
			List<WebElement> StrikeAllDoctor = loginPage.getDriver().findElements(
					By.xpath("//table[@id='clinicEventAllListTable']//span[contains(text(),'All Doctors')]"));
			List<WebElement> strikDoctor = loginPage.getDriver().findElements(
					By.xpath("//table[@id='clinicEventAllListTable']//span[contains(text(),'" + DoctorName + "')]"));
			int total = filterDoctor.size() + allDoctorFilter.size() + StrikeAllDoctor.size() + strikDoctor.size();
			try {
				boolean flag = (allDoctorSize == total);
				if (flag) {
					loginPage.waitForElementToBeClickable(nextBtn);
					nextBtn.click();
				} else {
					Assert.assertTrue(flag);
					break;
				}
			} catch (Exception e) {

				e.printStackTrace();
			}
		}
	}

	public void verifyFilterCategoryInEventList(String category) {
		loginPage.waitForPageLoad();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		while (!nextBtn.getAttribute("class").contains("disabled")) {
			List<WebElement> allDoctor = loginPage.getDriver()
					.findElements(By.xpath("//table[@id='clinicEventAllListTable']//tbody//td[4]"));
			int allDoctorSize = allDoctor.size();
			List<WebElement> filterCategory = loginPage.getDriver().findElements(
					By.xpath("//table[@id='clinicEventAllListTable']//td[contains(text(),'" + category + "')]"));
			List<WebElement> strikeCategory = loginPage.getDriver().findElements(
					By.xpath("//table[@id='clinicEventAllListTable']//span[contains(text(),'" + category + "')]"));
			int total = filterCategory.size() + strikeCategory.size();
			try {
				boolean flag = (allDoctorSize == total);
				if (flag) {
					loginPage.waitForElementToBeClickable(nextBtn);
					nextBtn.click();
				} else {
					Assert.assertTrue(flag);
					break;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
