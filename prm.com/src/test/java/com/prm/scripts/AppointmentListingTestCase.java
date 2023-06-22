package com.prm.scripts;

import com.prm.pageobjects.utils.TestData;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.prm.docdashboard.pageobject.AppointmentAddPage;
import com.prm.docdashboard.pageobject.BasePage;
import com.prm.docdashboard.pageobject.AppointmentsLisitngPage;
import com.prm.docdashboard.pageobject.DoctorDashBoard;
import com.prm.docdashboard.pageobject.LoginPage;
import com.relevantcodes.extentreports.LogStatus;
import com.prm.pageobjects.utils.ExtentReport;
import com.prm.pageobjects.utils.ReadConfig;
import com.prm.pageobjects.utils.ReadExcelData;

/**
 * 
 * @author Ajit
 *
 */
public class AppointmentListingTestCase {
	LoginPage loginPage = new LoginPage();
	DoctorDashBoard doctorDashboard = new DoctorDashBoard(loginPage);
	AppointmentAddPage appointmentAddPage = new AppointmentAddPage(loginPage);
	AppointmentsLisitngPage appointmentsLisitngPage = new AppointmentsLisitngPage(loginPage);
	BasePage basePage = new BasePage(loginPage);
	ExtentReport extentReport = new ExtentReport(loginPage, "AppointmentListingTestCase");

	//file used to store input data
	private static final String FILE_PATH = TestData.getInstance().getInputData("appointmentList_eventList_test_data_file_path");
	private static final String SHEET = TestData.getInstance().getInputData("appointmentList_eventList_test_data_sheet_name");;
	// Message here which We will print after excution

	private static final String VERIFY_APPOINTMENT_LIST_HOME_PAGE_MSG = "@@ Test scripts has been started here @@";
	private static final String STATUS_OF_SCRIPTS = "@@ Test script has been completed @@";
	private static final String VERIFY_ALL_ELEMENT_ON_APP_LIST_HP = "2: AppointmentList 1:It verified all element are present";
	private static final String VERIFY_APPOINTMENT_TYPE_FILTER = "2: AppointmentList #52:Appointment type filter has been verified";
	private static final String VERIFY_DOCTOR_FILTER = "2: AppointmentList #53:Doctor filter is working fine After selecting from dropdown";
	private static final String CHECKED_FRM_DATE_IS_BEFORE_AFTER_DATE = "2: AppointmentList #54:Validated from Date is Before After Date";
	private static final String VERIFY_CONFIRM_WITH_CONFIRMED = "2: AppointmentList #57:Confirmed appointment with Edit,View and Delete btn";
	private static final String VERIFY_VIEW_BTN_BEHAVIOUR = "2: AppointmentList #58:Verified Behaviour of view Btn";
	private static final String CLICK_ON_EDIT_AND_VERIFY_PAST_DATE = "2: AppointmentList #59:On past Date it is not allowing.A popup apear time is already passed ";
	private static final String VERIFY_ON_CLICK_DELETE_POP_UP_YES = "2: AppointmentList #61:After clicking Delete popup has benn verified";
	private static final String VERIFY_EDIT_BTN = "2: AppointmentList #65:Verified Behaviour of Edit Btn";
	private static final String DATE_FILTER_ALONG_WITH_APP_TYPE = "2: AppointmentList #5:Date filter working fine with App Type filter";
	private static final String NO_RECORD_FOUND_MSG_VERIFICATION = "2: AppointmentList #67:noRecordFound Message is displayed on no data";
	
	//
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

	@BeforeMethod(description = VERIFY_APPOINTMENT_LIST_HOME_PAGE_MSG, enabled = true)
	public void verifyAppointmentListHomePage() {
		extentReport.logger.log(LogStatus.PASS, VERIFY_APPOINTMENT_LIST_HOME_PAGE_MSG);
		doctorDashboard.clickOnAppListBtn();
		Assert.assertTrue(basePage.verification().contains("Clinic Appointments"));
	}

	@Test(description = VERIFY_ALL_ELEMENT_ON_APP_LIST_HP, enabled = true, priority = 1)
	public void verifyAllElementOnAppListHp() {
		extentReport.logger.log(LogStatus.PASS, VERIFY_ALL_ELEMENT_ON_APP_LIST_HP);
		appointmentsLisitngPage.verifiyAppointmentListElements();
		Assert.assertTrue(basePage.verification().contains("Clinic Appointments"));
	}

	@Test(description = VERIFY_APPOINTMENT_TYPE_FILTER, enabled = true, priority = 2)
	public void verifyAppointmentTypeFilter() {
		extentReport.logger.log(LogStatus.PASS, VERIFY_APPOINTMENT_TYPE_FILTER);
		// basePage.selectClinicFrmHeader("Amar Colony");
		appointmentsLisitngPage.selectAppType("Tentative");
		appointmentsLisitngPage.clickOnSearchBtn();
		appointmentsLisitngPage.appointmentTypeFilter("Tentative");
		Assert.assertTrue(basePage.verification().contains("Clinic Appointments"));
	}

	@Test(description = VERIFY_DOCTOR_FILTER, enabled = true, priority = 3)
	public void verifyDoctorFilter() {
		extentReport.logger.log(LogStatus.PASS, VERIFY_DOCTOR_FILTER);
		appointmentsLisitngPage.selectDoctorFRmDrpDwn(ReadExcelData.readExcelData(FILE_PATH, SHEET, 1, 3));
		appointmentsLisitngPage.doctorTypeFilter(ReadExcelData.readExcelData(FILE_PATH, SHEET, 1, 4));
		Assert.assertTrue(basePage.verification().contains("Clinic Appointments"));
	}

	@Test(description = CHECKED_FRM_DATE_IS_BEFORE_AFTER_DATE, enabled = true, priority = 4)
	public void checkedFrmDateIsBeforeAfterDate() {
		extentReport.logger.log(LogStatus.PASS, CHECKED_FRM_DATE_IS_BEFORE_AFTER_DATE);
		appointmentsLisitngPage.checkingFromDateIsBeforeAfterDate(ReadExcelData.readExcelData(FILE_PATH, SHEET, 2, 1), ReadExcelData.readExcelData(FILE_PATH, SHEET, 2, 2));
		appointmentsLisitngPage.enterFromDate(ReadExcelData.readExcelData(FILE_PATH, SHEET, 2, 1));
		appointmentsLisitngPage.enterToDate(ReadExcelData.readExcelData(FILE_PATH, SHEET, 2, 2));
		appointmentsLisitngPage.clickOnSearchBtn();
		appointmentsLisitngPage.dateFilter(ReadExcelData.readExcelData(FILE_PATH, SHEET, 2, 1), ReadExcelData.readExcelData(FILE_PATH, SHEET, 2, 2));
		Assert.assertTrue(basePage.verification().contains("Clinic Appointments"));
	}

	@Test(description = DATE_FILTER_ALONG_WITH_APP_TYPE, enabled = true, priority = 5)
	public void dateFilterAlongWithAppType() {
		extentReport.logger.log(LogStatus.PASS, DATE_FILTER_ALONG_WITH_APP_TYPE);
		appointmentsLisitngPage.enterFromDate(ReadExcelData.readExcelData(FILE_PATH, SHEET, 3, 1));
		appointmentsLisitngPage.enterToDate(ReadExcelData.readExcelData(FILE_PATH, SHEET, 3, 2));
		appointmentsLisitngPage.selectAppType("Tentative");
		appointmentsLisitngPage.clickOnSearchBtn();
		appointmentsLisitngPage.appointmentTypeFilter("Tentative");
		appointmentsLisitngPage.intiateData();
		appointmentsLisitngPage.dateFilter(ReadExcelData.readExcelData(FILE_PATH, SHEET, 3, 1), ReadExcelData.readExcelData(FILE_PATH, SHEET, 3, 2));
		Assert.assertTrue(basePage.verification().contains("Clinic Appointments"));
	}

	@Test(description = VERIFY_VIEW_BTN_BEHAVIOUR, enabled = true, priority = 6)
	public void verifyViewBtnBehaviour() {
		extentReport.logger.log(LogStatus.PASS, VERIFY_VIEW_BTN_BEHAVIOUR);
		appointmentsLisitngPage.verifyBehaviourOfViewBtn();
		Assert.assertTrue(basePage.verification().contains("Clinic Appointments"));
	}

	@Test(description = VERIFY_CONFIRM_WITH_CONFIRMED, enabled = true, priority = 7)
	public void verifyConfirm() {
		appointmentsLisitngPage.enterFromDate(ReadExcelData.readExcelData(FILE_PATH, SHEET, 4, 1));
		appointmentsLisitngPage.enterToDate(ReadExcelData.readExcelData(FILE_PATH, SHEET, 4, 2));
		appointmentsLisitngPage.selectAppType("Confirmed");
		appointmentsLisitngPage.selectAppStatusType("No-Show");
		appointmentsLisitngPage.clickOnSearchBtn();
		appointmentsLisitngPage.verifyConfirmWithNoShow();
		appointmentsLisitngPage.selectAppStatusType("Show");
		appointmentsLisitngPage.clickOnSearchBtn();
		appointmentsLisitngPage.confirmWithShow();
		appointmentsLisitngPage.selectAppStatusType("Checked-In");
		appointmentsLisitngPage.clickOnSearchBtn();
		appointmentsLisitngPage.confirmWithCheckIn();
		appointmentsLisitngPage.selectAppStatusType("Expired");
		appointmentsLisitngPage.clickOnSearchBtn();
		appointmentsLisitngPage.confirmWithExpired();
		Assert.assertTrue(basePage.verification().contains("Clinic Appointments"));
	}

	@Test(description = VERIFY_CONFIRM_WITH_CONFIRMED, enabled = true, priority = 8)
	public void verifyConfirmWithConfirmed() {
		extentReport.logger.log(LogStatus.PASS, VERIFY_CONFIRM_WITH_CONFIRMED);
		appointmentsLisitngPage.selectAppType("Confirmed");
		// appointmentsLisitngPage.selectAppStatusType("Confirmed");
		appointmentsLisitngPage.clickOnSearchBtn();
		appointmentsLisitngPage.confirmWithConfirmed();
		Assert.assertTrue(basePage.verification().contains("Clinic Appointments"));
	}

	@Test(description = VERIFY_ON_CLICK_DELETE_POP_UP_YES, enabled = true, priority = 10)
	public void editAndDeleteValidated() {
		extentReport.logger.log(LogStatus.PASS, VERIFY_ON_CLICK_DELETE_POP_UP_YES);
		basePage.backTODoctorDashboard();
		doctorDashboard.clickonAppointmentAdd();
		appointmentAddPage.enterPatientName("Dummy test");
		appointmentAddPage.enterMobileNumber("8470833998");
		appointmentAddPage.selectClinicFromDropdown(ReadExcelData.readExcelData(FILE_PATH, SHEET, 7, 5));
		appointmentAddPage.selectDateofAppointment(ReadExcelData.readExcelData(FILE_PATH, SHEET, 7, 1));
		appointmentAddPage.selectTimeSlotFromDropdown(ReadExcelData.readExcelData(FILE_PATH, SHEET, 7, 2));
		appointmentAddPage.selectDoctorFromDropdown(ReadExcelData.readExcelData(FILE_PATH, SHEET, 7, 3));
		appointmentAddPage.clickOnSaveBtn();
		doctorDashboard.clickOnAppListBtn();
		basePage.selectClinicFrmHeader(ReadExcelData.readExcelData(FILE_PATH, SHEET, 7, 5));
		appointmentsLisitngPage.enterFromDate(ReadExcelData.readExcelData(FILE_PATH, SHEET, 7, 1));
		appointmentsLisitngPage.enterToDate(ReadExcelData.readExcelData(FILE_PATH, SHEET, 7, 1));
		appointmentsLisitngPage.selectAppType(ReadExcelData.readExcelData(FILE_PATH, SHEET, 7, 4));
		appointmentsLisitngPage.clickOnSearchBtn();
		appointmentsLisitngPage.clickEditButton("Dummy test");
		appointmentAddPage.selectDateofAppointment(ReadExcelData.readExcelData(FILE_PATH, SHEET, 8, 1));
		appointmentAddPage.selectTimeSlotFromDropdown(ReadExcelData.readExcelData(FILE_PATH, SHEET, 8, 2));
		appointmentAddPage.clickOnSaveBtn();
		appointmentsLisitngPage.enterFromDate(ReadExcelData.readExcelData(FILE_PATH, SHEET, 8, 1));
		appointmentsLisitngPage.enterToDate(ReadExcelData.readExcelData(FILE_PATH, SHEET, 8, 1));
		appointmentsLisitngPage.clickOnSearchBtn();
		appointmentsLisitngPage.verifyAfterEditAtAppList("Dummy test", ReadExcelData.readExcelData(FILE_PATH, SHEET, 9, 1));
		appointmentsLisitngPage.clickDeleteButton("Dummy test");
		appointmentsLisitngPage.verifyWebElementDeletePopup();
		appointmentsLisitngPage.clickNoDeletePopup();
		appointmentsLisitngPage.clickDeleteButton("Dummy test");
		appointmentsLisitngPage.clickYesDeletePopup();
		appointmentsLisitngPage.verifyErrorMessage();
		appointmentsLisitngPage.selectReasonDelete();
		appointmentsLisitngPage.clickYesDeletePopup();
		appointmentsLisitngPage.strikeOff("Dummy test");
		Assert.assertTrue(basePage.verification().contains("Clinic Appointments"));
	}

	@Test(description = NO_RECORD_FOUND_MSG_VERIFICATION, enabled = true, priority = 11)
	public void noRecordFoundMsgVerification() {
		extentReport.logger.log(LogStatus.PASS, NO_RECORD_FOUND_MSG_VERIFICATION);
		appointmentsLisitngPage.enterToDate(ReadExcelData.readExcelData(FILE_PATH, SHEET, 5, 2));
		appointmentsLisitngPage.clickOnSearchBtn();
		appointmentsLisitngPage.noRecordFoundMsg();
		Assert.assertTrue(appointmentsLisitngPage.verifiyAppListHP());
	}

	@AfterMethod(description = STATUS_OF_SCRIPTS, enabled = true)
	public void statusOfScripts(ITestResult result) {
		extentReport.onTestFailure(result);
		basePage.backTODoctorDashboard();
		extentReport.logger.log(LogStatus.PASS, STATUS_OF_SCRIPTS);
	}

	@AfterClass
	public void tearDown() {
		extentReport.report.endTest(extentReport.logger);
		extentReport.report.flush();
		// extentReport.report.close();
		basePage.clickOnlogOut();
	}
}
