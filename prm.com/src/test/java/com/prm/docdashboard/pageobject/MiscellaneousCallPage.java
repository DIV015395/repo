package com.prm.docdashboard.pageobject;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.prm.pageobjects.utils.PCDriver;

public class MiscellaneousCallPage {
	private PCDriver login;
	public MiscellaneousCallPage(PCDriver login) {
		this.login = login;
		PageFactory.initElements(login.getDriver(), this);
	}

	// WebElement on New Miscellaneous Call Screen
	@FindBy(id = "type")
	private WebElement typeDrpDwn;
	@FindBy(xpath = "//label[text()='Type']")
	private WebElement type;
	@FindBy(id = "callDuration")
	private WebElement durationTxtBx;
	@FindBy(xpath = "//label[text()='Duration']")
	private WebElement duration;
	@FindBy(id = "disposition")
	private WebElement dispositionDrpDwn;
	@FindBy(xpath = "//label[text()='Disposition']")
	private WebElement disposition;
	@FindBy(id = "info")
	private WebElement infoDrpDwn;
	@FindBy(xpath = "//label[text()='Info']")
	private WebElement info;
	@FindBy(id = "name")
	private WebElement nameTxtBx;
	@FindBy(xpath = "//label[text()='Name']")
	private WebElement name;
	@FindBy(id = "mobile")
	private WebElement mobileTxtBx;
	@FindBy(xpath = "//label[text()='Name']")
	private WebElement mobile;
	@FindBy(id = "notes")
	private WebElement notesTxtBx;
	@FindBy(xpath = "//label[text()='Notes:']")
	private WebElement notes;

	/*-------All Right Navigations buttons-----*/
	@FindBy(xpath = "//div[contains(@class,'sideNavigation')]//i[text()='Save']")
	private WebElement saveBtn;
	@FindBy(xpath = "//div[contains(@class,'sideNavigation')]//i[text()='Reset']")
	private WebElement resetBtn;
	@FindBy(xpath = "//div[contains(@class,'sideNavigation')]//i[text()='Cancel']")
	private WebElement cancelBtn;

	/*-------All Header Navigations buttons-------*/
	@FindBy(xpath = "//div[contains(@class,'jumbotron')]//i[text()='Work Area']")
	private WebElement workAreaBtn;
	@FindBy(xpath = "//div[contains(@class,'jumbotron')]//i[text()='Misc. Calls']")
	private WebElement miscCallsListBtn;
	@FindBy(xpath = "//div[contains(@class,'jumbotron')]//i[text()='Subscribers']")
	private WebElement subscribersBtn;

	/*----Subscriber listing---*/
	@FindBy(xpath="//i[text()='Misc. Call+']")
	private WebElement miscCallsAddBtn;

	/*-----My Work Area----*/
	@FindBy(xpath="//b[text()='Dashboard']")
	private WebElement dashBoardBtn;

	/*-----Validation Error messages---*/
	@FindBy(xpath = "//label[text()='Disposition']/following-sibling::div/div[@class='error ng-scope']")
	private WebElement errorDispositionMsg;
	@FindBy(xpath = "//label[text()='Info']/following-sibling::div/div[@class='error ng-scope']")
	private WebElement errorInfoMsg;
	@FindBy(xpath = "//label[text()='Name']/following-sibling::div/div[@class='error ng-scope']")
	private WebElement errorNameMsg;
	@FindBy(xpath = "//label[text()='Mobile']/following-sibling::div/div[@class='error ng-scope']")
	private WebElement errorMobileMsg;

	/*----------------Functions-----------------------*/

	/*-- Checking the All Button shown in New Miscellaneous Call Page --*/
	public void checkedAllBtnInMiscellaneousCallAdd() {
		login.waitForPageLoad();
		Assert.assertTrue(checkedWebElementDisplayed(type) && checkedWebElementDisplayed(info) &&checkedWebElementDisplayed(disposition) &&checkedWebElementDisplayed(workAreaBtn) && checkedWebElementDisplayed(miscCallsListBtn) && checkedWebElementDisplayed(subscribersBtn)
				&& checkedWebElementDisplayed(saveBtn) && checkedWebElementDisplayed(resetBtn) && checkedWebElementDisplayed(cancelBtn));
	}

	/*-- Checking all options in Type DropDown --*/
	public void checkTypeDropDwn() {
		login.waitForPageLoad();
		boolean flag1, flag2, match = false;
		flag1 = checkedWebElementDisplayed(typeDrpDwn);
		String[] exp = {"Select Type","Inbound", "Outbound" };
		Select sel = new Select(typeDrpDwn);
		List<WebElement> options = sel.getOptions();
		for (WebElement we : options) {
			for (int i = 0; i < exp.length; i++) {
				if (flag2 = we.getText().equals(exp[i])) {
					match = flag1 && flag2;
				}
			}
			Assert.assertTrue(match);
		}
	}

	/*-- Select one option from Type DropDown --*/
	public void selectType(String typeValue) {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		login.waitForPageLoad();
		login.selectFromDropDownByVisibleText(typeDrpDwn, typeValue);
	}

	/*-- Checking duration TextField---*/
	public void checkDuration() {
		login.waitForPageLoad();
		Assert.assertTrue(checkedWebElementDisplayed(duration) && checkedWebElementDisplayed(durationTxtBx)
				&& durationTxtBx.getAttribute("placeholder").contains("minutes"));
	}

	/*-- Enter time in duration TextField---*/
	public void addDuration(String duration) {
		login.waitForPageLoad();
		durationTxtBx.clear();
		durationTxtBx.sendKeys(duration);
	}

	/*-- Checking all options in Disposition DropDown --*/
	public void checkDispositionDropDown() {
		login.waitForPageLoad();
		boolean flag1, flag2, match = false;
		flag1 = checkedWebElementDisplayed(dispositionDrpDwn);
		String[] exp = { "Select Disposition", "Busy", "Call Disconnected", "Call Me Back", "Clove Staff/Clinic",
				"Get Back To Patient", "Information Provided", "Invalid Number", "No Connect", "No Response",
				"Not Interested", "Patient Will Call", "Test Call", "Wrong Number" };
		Select sel = new Select(dispositionDrpDwn);
		List<WebElement> options = sel.getOptions();
		for (WebElement we : options) {
			for (int i = 0; i < exp.length; i++) {
				if (flag2 = we.getText().equals(exp[i])) {
					match = flag1 && flag2;
				}
			}
			Assert.assertTrue(match);
		}
	}

	/*-- Select one option from Disposition DropDown --*/
	public void selectDisposition(String dispositionValue) {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		login.waitForPageLoad();
		login.selectFromDropDownByVisibleText(dispositionDrpDwn, dispositionValue);
	}

	/*-- Checking all options in Info DropDown --*/
	public void checkInfoDropDown() {
		login.waitForPageLoad();
		boolean flag1, flag2, match = false;
		flag1 = checkedWebElementDisplayed(infoDrpDwn);
		String[] exp = { "Select Info", "Clove Clinic", "Clove Staff", "External Business", "Head Office",
				"Job Enquiry", "Location Query", "Test Call" };
		Select sel = new Select(infoDrpDwn);
		List<WebElement> options = sel.getOptions();
		for (WebElement we : options) {
			for (int i = 0; i < exp.length; i++) {
				if (flag2 = we.getText().equals(exp[i])) {
					match = flag1 && flag2;
				}
			}
			Assert.assertTrue(match);
		}
	}

	/*-- Select one option from Info DropDown --*/
	public void selectInfo(String infoValue) {
		login.waitForPageLoad();
		login.selectFromDropDownByVisibleText(infoDrpDwn, infoValue);
	}

	/*-- Checking Name TextField---*/
	public void checkNameTxtBx() {
		login.waitForPageLoad();
		Assert.assertTrue(checkedWebElementDisplayed(name) && checkedWebElementDisplayed(nameTxtBx)
				&& nameTxtBx.getAttribute("placeholder").contains("name"));
	}

	/*-- Enter Name in Name TextField---*/
	public void enterNameInTxtBx(String name) {
		login.waitForPageLoad();
		nameTxtBx.clear();
		nameTxtBx.sendKeys(name);
	}

	/*-- Checking mobile TextField---*/
	public void checkMobileTxtBx() {
		login.waitForPageLoad();
		Assert.assertTrue(checkedWebElementDisplayed(mobile) && checkedWebElementDisplayed(mobileTxtBx)
				&& mobileTxtBx.getAttribute("placeholder").contains("enter 10 digit mobile no"));
	}

	/*-- Enter Mobile Number in Mobile TextField---*/
	public void enterMobileInTxtBx(String mobile) {
		login.waitForPageLoad();
		mobileTxtBx.clear();
		mobileTxtBx.sendKeys(mobile);
	}

	/*-- Checking Notes TextField---*/
	public void checkNotesTxtBx() {
		login.waitForPageLoad();
		Assert.assertTrue(checkedWebElementDisplayed(notes) && checkedWebElementDisplayed(notesTxtBx) &&
				notesTxtBx.getAttribute("placeholder").contains("notes"));
	}

	/*-- Enter Data in Notes TextField---*/
	public void addNotes(String notes) {
		login.waitForPageLoad();
		notesTxtBx.clear();
		notesTxtBx.sendKeys(notes);
	}

	/*-- Click on Save button shown in New Miscellaneous Call page---*/
	public void clickOnSave() {
		login.waitForPageLoad();
		saveBtn.click();
		login.waitForSpinnerToDisappear();
	}

	/*-- Click on Reset button shown in New Miscellaneous Call page---*/
	public void clickOnResetBtn() {
		login.waitForElementToBeClickable(resetBtn);
		resetBtn.click();
	}

	/*-- Click on Cancel button shown in New Miscellaneous Call page---*/
	public void clickOnCancelBtn(){
		login.waitForPageLoad();
		cancelBtn.click();
		login.waitForSpinnerToDisappear();
	}

	/*-- Click on Subscriber button shown in New Miscellaneous Call page---*/
	public void clickOnSubscribers() {
		login.waitForPageLoad();
		subscribersBtn.click();
	}

	/*-- Click on Miscellaneous Add call button shown in Subscriber page---*/
	public void clickMiscsCallAddBtn() {
		login.waitForSpinnerToDisappear();
		login.waitForElementToBeClickable(miscCallsAddBtn);
		miscCallsAddBtn.click();
	}

	/*-- Click on Miscellaneous Call listing button shown in New Miscellaneous Call page---*/
	public void clickMiscCallListBtn() {
		login.waitForSpinnerToDisappear();
		login.waitForElementToBeClickable(miscCallsListBtn);
		miscCallsListBtn.click();
	}

	/*-- Click on Work Area button shown in New Miscellaneous Call page---*/
	public void clickWorkAreaBtn() {
		login.waitForPageLoad();
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		workAreaBtn.click();
	}

	/*-- Verifying My Work Page show after click on My Work Area button---*/
	public void toverifyMyWorkAreaHomePage() {
		login.waitForPageLoad();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Assert.assertTrue(login.getTitle().contains("My Work Area"));
	}

	/*-- Click on DashBoard button shown on My Work Area Page---*/
	public void clickOnDashboardBtnShowMyWorkArea() {
		login.waitForPageLoad();
		login.waitForElementToBeClickable(dashBoardBtn);
		dashBoardBtn.click();
	}

	/*--Check Error messages for Disposition---*/
	public void checkedErrorDisposition(String errorMsg) {
		login.waitForPageLoad();
		Assert.assertTrue(checkedWebElementDisplayed(errorDispositionMsg)&& errorDispositionMsg.getText().contains(errorMsg));
	}

	/*--Check Error messages for Info---*/
	public void checkedErrorInfo(String errorMsg) {
		login.waitForPageLoad();
		Assert.assertTrue(checkedWebElementDisplayed(errorInfoMsg)&& errorInfoMsg.getText().contains(errorMsg));
	}

	/*--Check Error messages for Name---*/
	public void checkedErrorName(String errorMsg) {
		login.waitForPageLoad();
		Assert.assertTrue(checkedWebElementDisplayed(errorNameMsg)&& errorNameMsg.getText().contains(errorMsg));
	}

	/*--Check Error messages for Mobile---*/
	public void checkedErrorMobile(String errorMsg) {
		login.waitForPageLoad();
		Assert.assertTrue(checkedWebElementDisplayed(errorMobileMsg)&& errorMobileMsg.getText().contains(errorMsg));
	}

	private boolean checkedWebElementDisplayed(WebElement ele) {
		return (ele.isDisplayed());
	}

}
