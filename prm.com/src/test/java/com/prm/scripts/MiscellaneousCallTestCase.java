package com.prm.scripts;

import com.prm.pageobjects.utils.TestData;
import com.prm.patientdashboard.pageobject.BasePatientLifeCyclePage;

import java.io.IOException;
import java.util.Map;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.prm.docdashboard.pageobject.BasePage;
import com.prm.docdashboard.pageobject.DoctorDashBoard;
import com.prm.docdashboard.pageobject.LoginPage;
import com.prm.docdashboard.pageobject.MiscellaneousCallListingPage;
import com.prm.docdashboard.pageobject.MiscellaneousCallPage;
import com.prm.pageobjects.utils.ExtentReport;
import com.prm.pageobjects.utils.ReadConfig;
import com.prm.pageobjects.utils.ReadExcelData;
import com.prm.pageobjects.utils.SheetTest;
import com.relevantcodes.extentreports.LogStatus;

public class MiscellaneousCallTestCase {

	private LoginPage loginPage  = new LoginPage();
	private DoctorDashBoard doctorDashboard  = new DoctorDashBoard(loginPage );
	private BasePage basePage  = new BasePage(loginPage );
	private ExtentReport extentReport = new ExtentReport(loginPage , "MiscellaneousCallTestCase");
	private BasePatientLifeCyclePage basePatientLifeCyclePage = new BasePatientLifeCyclePage(loginPage );
	private MiscellaneousCallPage miscellaneousCallPage = new MiscellaneousCallPage(loginPage );
	private MiscellaneousCallListingPage miscellaneousCallListingPage = new MiscellaneousCallListingPage(loginPage );

	/*
	 * Messages for every test cases which will be print on the extent report
	 */
	private static final String SCRIPTS_STARTED_MSG = "@@ Test scripts has been started here @@";
	private static final String STATUS_OF_SCRIPTS = "@@ Test script has been completed @@";
	private static final String CHECKED_MISCELLANEOUS_ADD_AND_LIST_UI = "Validate Miscellaneous Add and Listing for the First time -#checkedMiscellaneousAddAndListUi";
	private static final String ADD_MISCELLANEOUS_CALL = "Enter data on New Miscellaneous Call and Validate the same data on Miscellaneous Listing Page -#addMiscellaneousCall";
	private static final String CHECK_VALIDATION_MSGS_IN_NEW_MISCELLANEOUS_CALL = "Validate All mandatory fields with error Message on New Miscellaneous Call Page -#checkValidationMsgsInNewMiscellaneousCall";
	private static final String CHECK_ALL_BUTTON_ON_NEW_MISCELLANEOUS_CALL = "Validate All buttons and their redirection on New Miscellaneous Call Page -#checkAllButtonOnNewMiscellaneousCall";
	private static final String ADD_ANOTHER_MISCELLANEOUS_CALL = "Enter one more data on New Miscellaneous Call and Validate the same data on Miscellaneous Listing Page -#addMiscellaneousCall";
	private static final String CHECK_ALL_BUTTON_ON_MISCELLANEOUS_CALL_LIST = "Validate All buttons and their redirection on Miscellaneous Call Listing Page -#checkAllButtonOnMiscellaneousCallList";
	private static final String VERIFY_DATA_AFTER_SELECT_FILTER = "Validate data on listing after selection of Filter on Miscellaneous Call Listing Page -#verifyDataAfterSelectFilter";
	private static final String VERIFY_MULTIPLE_DATA_AFTER_SELECT_FILTER = "Validate Multiple data on listing after selection of Filter on Miscellaneous Call Listing Page -#verifyMultipleDataAfterSelectFilter";

	/*
	 *Page Title  
	 */
	private final String MISCELLANEOUS_CALL_LISTING_TITLE="Miscellaneous Call Listing";
	private final String ADD_MISCELLANEOUS_CALL_TITLE="New Miscellaneous Call";
	private final String SUBSCRIBER_LISTING_TITLE="Subscriber Listing";
	
	/*
	 * Google Sheet data details
	 */
	private static final String MODULE_NAME="Miscellaneous Call";
	private static final String TEST_DATA_SHEET_NAME="MiscellaneousCallTestData";
	
	/*
	 * Test Data from google Sheet 
	 */
	private Map<String,String> miscellaneousCallData=SheetTest.prepareData(MODULE_NAME, TEST_DATA_SHEET_NAME, "A2", "L2");
	

	/*
	 * Storing mandatory input required to run Miscellaneous test cases,make sure you
	 * update the mandatory input before running the test cases
	 */
	private static final String TODAY_DATE= TestData.getInstance().getTodayDate();
	private final String CALLER_NICKNAME="Super";
	private static String CURRENT_TIME;
	
	
	/*
	 * LoginPage with UserName and Password
	 * Search for patients from the doctor DashBoard
	 * Verified patients will redirect at the patient DashBoard
	 */
	@BeforeClass
	public void setup() {
		extentReport.logger = extentReport.report.startTest(this.getClass().getSimpleName());
		extentReport.logger.log(LogStatus.INFO, LoginPage.testCaseRunningStatusmsg);
		extentReport.logger.log(LogStatus.PASS, LoginPage.browserMsg);
		String username = ReadConfig.getInstance().getUsername().toString();
		String password = ReadConfig.getInstance().getPassword().toString();
		loginPage.login(username, password);
		extentReport.logger.log(LogStatus.PASS, LoginPage.userNameMsg);
		extentReport.logger.log(LogStatus.PASS, LoginPage.passwordMsg);
		extentReport.logger.log(LogStatus.PASS, LoginPage.loginBtnClickMsg);
		doctorDashboard.doctorDashboardHomePage();
	}

	@BeforeMethod
	public void verifyPatientdashBoardHomePage() {
		extentReport.logger.log(LogStatus.PASS, SCRIPTS_STARTED_MSG);
	}

	/*
	 * check header of Miscellaneous listing page
	 * check Miscellaneous Add new button shown in Miscellaneous listing page and no record message displayed redirect for first time
	 * check All filter with options present in All DropDown and All TextBox
	 * check header of New Miscellaneous Call page and also check all buttons in New page
	 * check name of all element in New miscellaneous page
	 */
	@Test(enabled = true, priority = 1)
	public void checkedMiscellaneousAddAndListUi() {
		extentReport.logger.log(LogStatus.PASS, CHECKED_MISCELLANEOUS_ADD_AND_LIST_UI);
		doctorDashboard.clickOnMiscCallsList();
		basePatientLifeCyclePage.headerOnListPage(MISCELLANEOUS_CALL_LISTING_TITLE);
		basePatientLifeCyclePage.verifyAddNewBtn();
		miscellaneousCallListingPage.checkedAllBtnInMiscellaneousCallListing();
//		basePatientLifeCyclePage.noRecordMsgDisplayed();
		miscellaneousCallListingPage.checkTypeDropDwn();
		miscellaneousCallListingPage.checkDispositionDropDown();
		miscellaneousCallListingPage.checkCallerNameDropDown();
		miscellaneousCallListingPage.checkAllTextBoxFilter();
		basePatientLifeCyclePage.clickOnAddNewBtn();
		basePatientLifeCyclePage.headerOnAddPage(ADD_MISCELLANEOUS_CALL_TITLE);
		miscellaneousCallPage.checkedAllBtnInMiscellaneousCallAdd();
		miscellaneousCallPage.checkTypeDropDwn();
		miscellaneousCallPage.checkDuration();
		miscellaneousCallPage.checkDispositionDropDown();
		miscellaneousCallPage.checkInfoDropDown();
		miscellaneousCallPage.checkNameTxtBx();
		miscellaneousCallPage.checkMobileTxtBx();
		miscellaneousCallPage.checkNotesTxtBx();
		miscellaneousCallPage.clickOnCancelBtn();
		Assert.assertTrue(basePage.verification().contains(MISCELLANEOUS_CALL_LISTING_TITLE));
	
	}

	/*
	 * Add one record in Miscellaneous call, click on save and also check successful message
	 * check same record on Miscellaneous listing
	 */
	@Test(enabled = true ,priority=2)
	public void addMiscellaneousCall() {
		extentReport.logger.log(LogStatus.PASS, ADD_MISCELLANEOUS_CALL);
		doctorDashboard.clickOnMiscCallsAdd();
		basePatientLifeCyclePage.headerOnAddPage(ADD_MISCELLANEOUS_CALL_TITLE);
		miscellaneousCallPage.selectType(miscellaneousCallData.get("call_type"));
		miscellaneousCallPage.addDuration(miscellaneousCallData.get("call_duration"));
		miscellaneousCallPage.selectDisposition(miscellaneousCallData.get("call_disposition"));
		miscellaneousCallPage.selectInfo(miscellaneousCallData.get("call_info"));
		miscellaneousCallPage.enterNameInTxtBx(miscellaneousCallData.get("call_name"));
		miscellaneousCallPage.enterMobileInTxtBx(miscellaneousCallData.get("mobileNumber"));
		miscellaneousCallPage.addNotes(miscellaneousCallData.get("notes"));
		miscellaneousCallPage.clickOnSave();
		CURRENT_TIME=loginPage.getCurrentTime();
		miscellaneousCallListingPage.verifySuccessfullMessage();
		basePatientLifeCyclePage.headerOnListPage(MISCELLANEOUS_CALL_LISTING_TITLE);
		miscellaneousCallListingPage.checkedMainListDataName();
		miscellaneousCallListingPage.checkDateTimeMainList(miscellaneousCallData.get("mobileNumber"), TODAY_DATE, CURRENT_TIME);
		miscellaneousCallListingPage.nameInMainList(TODAY_DATE,miscellaneousCallData.get("mobileNumber"),miscellaneousCallData.get("call_name"));
		miscellaneousCallListingPage.miscellaneousCallList(TODAY_DATE,miscellaneousCallData.get("call_name"),miscellaneousCallData.get("mobileNumber"));
		miscellaneousCallListingPage.miscellaneousCallList(TODAY_DATE,miscellaneousCallData.get("mobileNumber"),miscellaneousCallData.get("call_type"));
		miscellaneousCallListingPage.miscellaneousCallList(TODAY_DATE,miscellaneousCallData.get("mobileNumber"),miscellaneousCallData.get("call_disposition"));
		miscellaneousCallListingPage.miscellaneousCallList(TODAY_DATE,miscellaneousCallData.get("mobileNumber"),miscellaneousCallData.get("call_info"));
		miscellaneousCallListingPage.miscellaneousCallList(TODAY_DATE,miscellaneousCallData.get("mobileNumber"),CALLER_NICKNAME);
		miscellaneousCallListingPage.notesCallList(TODAY_DATE,miscellaneousCallData.get("mobileNumber"),miscellaneousCallData.get("notes"));
		Assert.assertTrue(basePage.verification().contains(MISCELLANEOUS_CALL_LISTING_TITLE));
	}

	/*
	 * Check All scenario related to validation on Miscellaneous Add Page
	 */
	@Test(enabled=true,priority=3)
	public void checkValidationMsgsInNewMiscellaneousCall() {
		extentReport.logger.log(LogStatus.PASS, CHECK_VALIDATION_MSGS_IN_NEW_MISCELLANEOUS_CALL);
		doctorDashboard.clickOnMiscCallsList();
		basePatientLifeCyclePage.clickOnAddNewBtn();
		miscellaneousCallPage.clickOnSave();
		miscellaneousCallPage.checkedErrorDisposition("Select Disposition");
		miscellaneousCallPage.checkedErrorInfo("Enter call info");
		miscellaneousCallPage.checkedErrorName("Enter name");
		miscellaneousCallPage.checkedErrorMobile("Enter mobile no.");
		miscellaneousCallPage.clickOnResetBtn();
		basePatientLifeCyclePage.headerOnAddPage(ADD_MISCELLANEOUS_CALL_TITLE);
		miscellaneousCallPage.selectInfo(miscellaneousCallData.get("call_info"));
		miscellaneousCallPage.enterNameInTxtBx(miscellaneousCallData.get("call_name"));
		miscellaneousCallPage.enterMobileInTxtBx(miscellaneousCallData.get("mobileNumber"));
		miscellaneousCallPage.clickOnSave();
		miscellaneousCallPage.checkedErrorDisposition("Select Disposition");
		miscellaneousCallPage.clickOnResetBtn();
		basePatientLifeCyclePage.headerOnAddPage(ADD_MISCELLANEOUS_CALL_TITLE);
		miscellaneousCallPage.selectDisposition(miscellaneousCallData.get("call_disposition"));
		miscellaneousCallPage.enterNameInTxtBx(miscellaneousCallData.get("call_name"));
		miscellaneousCallPage.enterMobileInTxtBx(miscellaneousCallData.get("mobileNumber"));
		miscellaneousCallPage.clickOnSave();
		miscellaneousCallPage.checkedErrorInfo("Enter call info");
		miscellaneousCallPage.clickOnResetBtn();
		basePatientLifeCyclePage.headerOnAddPage(ADD_MISCELLANEOUS_CALL_TITLE);
		miscellaneousCallPage.selectDisposition(miscellaneousCallData.get("call_disposition"));
		miscellaneousCallPage.selectInfo(miscellaneousCallData.get("call_info"));
		miscellaneousCallPage.enterMobileInTxtBx(miscellaneousCallData.get("mobileNumber"));
		miscellaneousCallPage.clickOnSave();
		miscellaneousCallPage.checkedErrorName("Enter name");
		miscellaneousCallPage.clickOnResetBtn();
		basePatientLifeCyclePage.headerOnAddPage(ADD_MISCELLANEOUS_CALL_TITLE);
		miscellaneousCallPage.selectDisposition(miscellaneousCallData.get("call_disposition"));
		miscellaneousCallPage.selectInfo(miscellaneousCallData.get("call_info"));
		miscellaneousCallPage.enterNameInTxtBx(miscellaneousCallData.get("call_name"));
		miscellaneousCallPage.clickOnSave();
		miscellaneousCallPage.checkedErrorMobile("Enter mobile no.");
		miscellaneousCallPage.clickOnCancelBtn();
		Assert.assertTrue(basePage.verification().contains(MISCELLANEOUS_CALL_LISTING_TITLE));
	}

	/*
	 * Check All buttons shown in New Miscellaneous Call Page and also check the redirection of each button
	 */
	@Test(enabled=true,priority=4)
	public void checkAllButtonOnNewMiscellaneousCall() {
		extentReport.logger.log(LogStatus.PASS, CHECK_ALL_BUTTON_ON_NEW_MISCELLANEOUS_CALL );
		doctorDashboard.clickOnMiscCallsList();
		basePatientLifeCyclePage.clickOnAddNewBtn();
		basePatientLifeCyclePage.headerOnAddPage(ADD_MISCELLANEOUS_CALL_TITLE);
		miscellaneousCallPage.clickOnSubscribers();
		basePatientLifeCyclePage.headerOnListPage(SUBSCRIBER_LISTING_TITLE);
		miscellaneousCallPage.clickMiscsCallAddBtn();
		basePatientLifeCyclePage.headerOnAddPage(ADD_MISCELLANEOUS_CALL_TITLE);
		miscellaneousCallPage.clickMiscCallListBtn();
		basePatientLifeCyclePage.headerOnListPage(MISCELLANEOUS_CALL_LISTING_TITLE);
		basePatientLifeCyclePage.clickOnAddNewBtn();
		basePatientLifeCyclePage.headerOnAddPage(ADD_MISCELLANEOUS_CALL_TITLE);
		miscellaneousCallPage.clickWorkAreaBtn();
		miscellaneousCallPage.toverifyMyWorkAreaHomePage();
		miscellaneousCallPage.clickOnDashboardBtnShowMyWorkArea();
		doctorDashboard.clickOnMiscCallsList();
		basePage.verifyPageTitle(MISCELLANEOUS_CALL_LISTING_TITLE);
	}

	/*
	 * Check All buttons shown in Miscellaneous Call Listing Page and also check the redirection of each button
	 */
	@Test(enabled=true, priority=6)
	public void checkAllButtonOnMiscellaneousCallList() {
		extentReport.logger.log(LogStatus.PASS, CHECK_ALL_BUTTON_ON_MISCELLANEOUS_CALL_LIST);
		doctorDashboard.clickOnMiscCallsList();
		basePatientLifeCyclePage.headerOnListPage(MISCELLANEOUS_CALL_LISTING_TITLE);
		miscellaneousCallListingPage.clickOnSubscribers();
		basePatientLifeCyclePage.headerOnListPage(SUBSCRIBER_LISTING_TITLE);
		miscellaneousCallListingPage.clickMiscsCallListBtn();
		basePatientLifeCyclePage.headerOnListPage(MISCELLANEOUS_CALL_LISTING_TITLE);
		miscellaneousCallListingPage.checkTypeDropDwn();
		miscellaneousCallListingPage.checkDispositionDropDown();
		miscellaneousCallListingPage.checkCallerNameDropDown();
		miscellaneousCallListingPage.checkAllTextBoxFilter();
		basePage.verifyPageTitle(MISCELLANEOUS_CALL_LISTING_TITLE);
	}

	/*
	 * check All filter with options present in All DropDown and All TextBox shown in Miscellaneous List Page
	 * Also check data on a listing after selection of Filter
	 */
	@Test(enabled=true, priority=7)
	public void verifyDropDownFilterOnListing() {
		extentReport.logger.log(LogStatus.PASS, VERIFY_DATA_AFTER_SELECT_FILTER);
		doctorDashboard.clickOnMiscCallsList();
		basePatientLifeCyclePage.headerOnListPage(MISCELLANEOUS_CALL_LISTING_TITLE);
		miscellaneousCallListingPage.selectType(miscellaneousCallData.get("call_type"));
		miscellaneousCallListingPage.clickSearchBtn();
		miscellaneousCallListingPage.verifyDataOnCallTypeFilter(miscellaneousCallData.get("call_type"));
		miscellaneousCallListingPage.clickOnResetBtn();
//		
		miscellaneousCallListingPage.selectType(miscellaneousCallData.get("call_type"));
		miscellaneousCallListingPage.selectDisposition(miscellaneousCallData.get("call_disposition"));
		miscellaneousCallListingPage.clickSearchBtn();
		miscellaneousCallListingPage.verifyDataOnCallTypeFilter(miscellaneousCallData.get("call_type"));
		miscellaneousCallListingPage.verifyDataOnCallDispositionFilter(miscellaneousCallData.get("call_disposition"));
		miscellaneousCallListingPage.clickOnResetBtn();
		
		miscellaneousCallListingPage.selectType(miscellaneousCallData.get("call_type"));
		miscellaneousCallListingPage.selectDisposition(miscellaneousCallData.get("call_disposition"));
		miscellaneousCallListingPage.selectCallerName(miscellaneousCallData.get("caller_name"));
		miscellaneousCallListingPage.clickSearchBtn();
		miscellaneousCallListingPage.verifyDataOnCallTypeFilter(miscellaneousCallData.get("call_type"));
		miscellaneousCallListingPage.verifyDataOnCallDispositionFilter(miscellaneousCallData.get("call_disposition"));
		miscellaneousCallListingPage.verifyDataOnCallerNameFilter(miscellaneousCallData.get("caller_name"));
		miscellaneousCallListingPage.clickOnResetBtn();
//		

		miscellaneousCallListingPage.selectType(miscellaneousCallData.get("call_type"));
		miscellaneousCallListingPage.selectDisposition(miscellaneousCallData.get("call_disposition"));
		miscellaneousCallListingPage.selectCallerName(miscellaneousCallData.get("caller_name"));
		miscellaneousCallListingPage.clickSearchBtn();
		miscellaneousCallListingPage.AddFromDate(miscellaneousCallData.get("fromDate"));
		miscellaneousCallListingPage.AddToDate(miscellaneousCallData.get("toDate"));
		miscellaneousCallListingPage.clickSearchBtn();
		miscellaneousCallListingPage.verifyDataOnCallTypeFilter(miscellaneousCallData.get("call_type"));
		miscellaneousCallListingPage.verifyDataOnCallDispositionFilter(miscellaneousCallData.get("call_disposition"));
		miscellaneousCallListingPage.verifyDataOnCallerNameFilter(miscellaneousCallData.get("caller_name"));
		miscellaneousCallListingPage.veifyDataOnDateFilter(miscellaneousCallData.get("fromDate"),miscellaneousCallData.get("toDate"));
		
		basePage.verifyPageTitle(MISCELLANEOUS_CALL_LISTING_TITLE);
		
	}
	
	/*
	 * * Verify Patient Name  and Patient Mobile Number details search field 
	 */
	@Test
	public void verifyNameMobileSearchFilter() {
		doctorDashboard.clickOnMiscCallsList();
		basePatientLifeCyclePage.headerOnListPage(MISCELLANEOUS_CALL_LISTING_TITLE);
		miscellaneousCallListingPage.addDetails(miscellaneousCallData.get("patientName_details"));
		miscellaneousCallListingPage.clickSearchBtn();
		miscellaneousCallListingPage.verifyDataOnNameSearchFilter(miscellaneousCallData.get("patientName_details"));
		miscellaneousCallListingPage.clickOnResetBtn();
		
		miscellaneousCallListingPage.addDetails(miscellaneousCallData.get("patientMobile_details"));
		miscellaneousCallListingPage.clickSearchBtn();
		miscellaneousCallListingPage.verifyDataOnMobileNumberSearchFilter(miscellaneousCallData.get("patientMobile_details"));
		miscellaneousCallListingPage.clickOnResetBtn();
		
		miscellaneousCallListingPage.selectType(miscellaneousCallData.get("call_type"));
		miscellaneousCallListingPage.selectDisposition(miscellaneousCallData.get("call_disposition"));
		miscellaneousCallListingPage.addDetails(miscellaneousCallData.get("patientName_details"));
		miscellaneousCallListingPage.AddFromDate(miscellaneousCallData.get("fromDate"));
		miscellaneousCallListingPage.AddToDate(miscellaneousCallData.get("toDate"));
		miscellaneousCallListingPage.clickSearchBtn();
		miscellaneousCallListingPage.verifyDataOnNameSearchFilter(miscellaneousCallData.get("patientName_details"));
		miscellaneousCallListingPage.verifyDataOnCallTypeFilter(miscellaneousCallData.get("call_type"));
		miscellaneousCallListingPage.verifyDataOnCallDispositionFilter(miscellaneousCallData.get("call_disposition"));
		miscellaneousCallListingPage.veifyDataOnDateFilter(miscellaneousCallData.get("fromDate"),miscellaneousCallData.get("toDate"));
		
	}

	/*
	 * Check Date filter on Miscellaneous Call Listing
	 */
	@Test
	public void verifyDateFilter() {
		extentReport.logger.log(LogStatus.PASS, VERIFY_MULTIPLE_DATA_AFTER_SELECT_FILTER);
		doctorDashboard.clickOnMiscCallsList();
		basePatientLifeCyclePage.headerOnListPage(MISCELLANEOUS_CALL_LISTING_TITLE);
		miscellaneousCallListingPage.AddFromDate("01-10-20");
		miscellaneousCallListingPage.AddToDate("05-05-21");
		miscellaneousCallListingPage.clickSearchBtn();
		miscellaneousCallListingPage.verifyDateRangeErrorMsg();
		miscellaneousCallListingPage.clickOnResetBtn();
		
		miscellaneousCallListingPage.AddFromDate("01-10-20");
		miscellaneousCallListingPage.AddToDate("05-05-20");
		miscellaneousCallListingPage.clickSearchBtn();
		miscellaneousCallListingPage.verifyFromDateExceedToDateErrorMsg();
		miscellaneousCallListingPage.clickOnResetBtn();
		
		miscellaneousCallListingPage.AddFromDate(miscellaneousCallData.get("fromDate"));
		miscellaneousCallListingPage.AddToDate(miscellaneousCallData.get("toDate"));
		miscellaneousCallListingPage.clickSearchBtn();
		miscellaneousCallListingPage.veifyDataOnDateFilter(miscellaneousCallData.get("fromDate"),miscellaneousCallData.get("toDate"));
		
		
	}
	@AfterMethod(description = STATUS_OF_SCRIPTS, enabled = true)
	public void statusOfScripts(ITestResult result) {
		extentReport.onTestFailure(result);
		extentReport.logger.log(LogStatus.PASS, STATUS_OF_SCRIPTS);
		miscellaneousCallListingPage.clickOnDashboardBtn();
	}

	@AfterClass
	public void tearDown() {
		extentReport.report.endTest(extentReport.logger);
		extentReport.report.flush();
		basePage.clickOnlogOut();
		loginPage.getDriver().quit();
	}
}

