package com.prm.docdashboard.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;

import com.prm.pageobjects.utils.PCDriver;

/**
 * 
 * @author Ajit Yadav
 *
 */
public class DoctorDashBoard {
	private PCDriver loginPage;

	public DoctorDashBoard(PCDriver loginPage) {
		this.loginPage = loginPage;
		PageFactory.initElements(loginPage.getDriver(), this);
	}

	@FindBy(xpath = "//a[@src='images/clove-logo.png'])")
	private WebElement clovelogo;
	@FindBy(id = "dashBoardScheduler")
	private WebElement scheduler;
	@FindBy(id = "dashBoardAppointmentAdd")
	private WebElement AppAddBtn;
	@FindBy(className = "clearfix mainDashboard")
	private WebElement Dashboard;
	@FindBy(id = "dashBoardScheduler")
	private WebElement dhBdSc;
	@FindBy(className = "clearfix saveMessage")
	private WebElement successMsg;
	@FindBy(id = "dashBoardAppointment")
	private WebElement AppListBtn;
	@FindBy(id = "dashBoardPatientsAdd")
	private WebElement patientAdd;
	@FindBy(xpath = "//div[@id='customLoading'][not(contains(@style,'none'))]")
	public WebElement customOverlay;
	@FindBy(id="dashBoardMiscList")
	public WebElement miscCallsListBtn;
	@FindBy(id="dashBoardMiscAdd")
	public WebElement miscCallsAddBtn;
	@FindBy(id="dashBoardFBList")
	private WebElement feedbackListing;
	@FindBy(id = "dashBoardSubscriberList")
	private WebElement subscriberBtn;
	@FindBy(id = "dashBoardCampSubs")
	private WebElement dashBoardCampSubs;
	@FindBy(id="dashBoardProducts")
	private WebElement dashboardProductSale;

	public void clickSubscriber() {
		try {
			loginPage.waitForPageLoad();
			Thread.sleep(5000);
			subscriberBtn.click();
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void clickCampSubscriberAdd() {
		try {
			loginPage.waitForPageLoad();
			Thread.sleep(5000);
			dashBoardCampSubs.click();
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void doctorDashboardHomePage() {
		loginPage.waitForElementToBeClickable(AppAddBtn);
		Assert.assertTrue(loginPage.getDriver().getTitle().contains("Doctor Dashboard"));
	}

	public void clickOnSchedular() {
		loginPage.waitForPageLoad();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		loginPage.executeScript(scheduler);
	}
	
	
	public void clickonAppointmentAdd() {
		loginPage.waitForElementToDisappear((By.xpath("//div[@class='ui-widget-overlay']")));
		loginPage.waitForSpinnerToDisappear();;
		loginPage.waitForElementVisibility(AppAddBtn, 4000);
		AppAddBtn.click();
	}

//	public void clickonAppointmentAdd() {
//		try {
//			loginPage.waitForPageLoad();
//			loginPage.executeScript(AppAddBtn);
//			Thread.sleep(2500);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
	public void clickOnAppListBtn() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		loginPage.waitForElementToDisappear((By.xpath("//div[@class='ui-widget-overlay']")));
//		loginPage.waitForSpinnerToDisappear();;
		loginPage.waitForElementVisibility(AppListBtn, 4000);
		AppListBtn.click();
	}

//	public void clickOnAppListBtn() {
//		loginPage.waitForPageLoad();
//		try {
//			Thread.sleep(12000);
//			AppListBtn.click();
//			Thread.sleep(5000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//	}

	public void clickOnPatientAdd() {
		loginPage.waitForPageLoad();
		loginPage.executeScript(patientAdd);
	}
	
	public void verifyMiscCalls() {
		loginPage.WaitTillElementIsPresent(miscCallsListBtn);
		
	}
	
	public void clickOnMiscCallsList() {
		loginPage.waitForSpinnerToDisappearOnDoctorDashboard();
		loginPage.waitForElementToBeClickable ( miscCallsListBtn );
		miscCallsListBtn.click ();
		loginPage.waitForSpinnerToDisappear();
	}

	
	public void clickOnMiscCallsAdd() {
		    loginPage.waitForSpinnerToDisappearOnDoctorDashboard();
			loginPage.waitForElementToBeClickable ( miscCallsAddBtn );
			miscCallsAddBtn.click ();
			loginPage.waitForSpinnerToDisappear();
	}

	public void clickFeedBackListing() {
		loginPage.waitForPageLoad();
		try {
			Thread.sleep(10000);
			feedbackListing.click();
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void clickOnProductSales(){
		loginPage.waitForSpinnerToDisappear();
		loginPage.waitForElementToBeClickable ( dashboardProductSale );
		dashboardProductSale.click ();
		loginPage.waitForSpinnerToDisappear();
	}
}
