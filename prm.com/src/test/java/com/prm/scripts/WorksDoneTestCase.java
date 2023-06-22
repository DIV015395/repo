package com.prm.scripts;

import com.prm.pageobjects.utils.TestData;
import com.prm.patientdashboard.pageobject.*;

import java.util.Map;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.prm.docdashboard.pageobject.AppointmentAddPage;
import com.prm.docdashboard.pageobject.BasePage;
import com.prm.docdashboard.pageobject.DoctorDashBoard;
import com.prm.docdashboard.pageobject.LoginPage;
import com.prm.docdashboard.pageobject.PatientDashboardPage;
import com.relevantcodes.extentreports.LogStatus;
import com.prm.pageobjects.utils.ExtentReport;
import com.prm.pageobjects.utils.ReadConfig;
import com.prm.pageobjects.utils.ReadExcelData;
import com.prm.pageobjects.utils.SheetTest;

public class WorksDoneTestCase {
	private LoginPage loginPage = new LoginPage();
	private DoctorDashBoard doctorDashboard = new DoctorDashBoard(loginPage);
	private BasePage basePage = new BasePage(loginPage);
	private ExtentReport extentReport = new ExtentReport(loginPage, "WorksDoneTestCase");
	private PatientDashboardPage patientDashboardPage = new PatientDashboardPage(loginPage);
	private BasePatientLifeCyclePage basePatientLifeCyclePage = new BasePatientLifeCyclePage(loginPage);
	private WorksDonePage worksDonePage = new WorksDonePage(loginPage);
	private TreatmentPlansPage treatmentPlansPage = new TreatmentPlansPage(loginPage);
	private TreatmentPlanListingPage treatmentPlanListingPage = new TreatmentPlanListingPage(loginPage);
	private OralExamsPage OralExamsPage = new OralExamsPage(loginPage);
	private WorkDoneHistoryPage workDoneHistoryPage = new WorkDoneHistoryPage(loginPage);
	private AppointmentAddPage appointmentPage = new AppointmentAddPage(loginPage);
	Map<String, String> workDoneData = null;

	/**
	 * Storing mandatory input required to run work done test cases
	 * or please update the mandatory input before running the test cases
	 */
	private static final String FILE_PATH = TestData.getInstance().getInputData("work_done_file_path");
	private static final String SHEET = TestData.getInstance().getInputData("work_done_file_sheet_name");
	private static final String TODAY_DATE = TestData.getInstance().getTodayDate();
//	private static final String PATIENT_NAME= TestData.getInstance().getInputData("work_done_patient_name");
//	private static final String MOBILE_NUMBER = TestData.getInstance().getInputData("work_done_patient_mobile");
	private static final String DOCTOR_NAME = TestData.getInstance().getInputData("work_done_doctor_name");
	private static final String CLINIC_NAME = TestData.getInstance().getInputData("work_done_clinic_name");

	/**
	 * Messages for every test cases which will be print on the extent report
	 */
	private static final String SCRIPTS_STARTED_MSG = "@@ Test scripts has been started here @@";
	private static final String STATUS_OF_SCRIPTS = "@@ Test script has been completed @@";
	private static final String CHECKED_WORK_DONE_ADD_AND_HISTORY_NO_TREATMENT = "Validated work done add and work done history page without any treatment for the patient - #checkedWorkDoneAddAndHistoryNoTreatment";
	private static final String START_TREATMENT = "Validating the work done add page and work done history page after starting the treatment for the patient - #startTreatment";
	private static final String TREATMENT_STAGE_IN_PROGRESS = "Validating the work add page and work done history page after changing the status of the treatment from started to In-progress - #treatmentStageInProgress";
	private static final String TREATMENT_STAGE_COMPLETE = "Validating the work done add and work done history page after changing the status of the treatment from in-progress to complete - #treatmentStageComplete";
	private static final String FOLLOW_UP = "Validating the work done history page, follow up modal and work done add page after doing follow up for the patient - #followUp";
	private static final String RE_TREAT = "Validating the work done history page, retreat modal and work done add page after changing status of the treatment from follow up to retreat for the patient -#reTreat";


	/**
	 * loginPage with UserName and Password
	 * search for patients from the doctor dashboard
	 * verified patients will redirect at the patient dashboard
	 */
	
	@BeforeClass
	public void setup() {
		workDoneData = SheetTest.prepareData("WorkDoneData","WorkDone","A1","Z");
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
		basePage.enterMobileNo(workDoneData.get("patient_mobile"));
		basePage.clickOnSearchBtn();
		basePage.clickOnPatient(workDoneData.get("patient_mobile"),workDoneData.get("patient_name"));
		Assert.assertTrue(basePage.verification().contains("Patient Dashboard"));
	}

	@BeforeMethod
	public void verifyPatientdashBoardHomePage() {
		extentReport.logger.log(LogStatus.PASS, SCRIPTS_STARTED_MSG);
	}

	/**
	 * checked header of the work done page
	 * checked works done add page without any treatment when we redirect for the first time
	 * checked the patient name and id should not be "null" or "NA"
	 * checked patient dashboard button, invoice button and works done history button
	 * checked left side navigation all button at both work done add page and Work Done History
	 * checked work done history page header
	 * checked no record message displayed at the both page for first time  redirection
	 */
	
	
	@Test(enabled = true, priority = 1)
	public void checkedWorkDoneAddAndHistoryNoTreatment() {
		extentReport.logger.log(LogStatus.PASS, CHECKED_WORK_DONE_ADD_AND_HISTORY_NO_TREATMENT);
		basePatientLifeCyclePage.clickOnAlert();
		patientDashboardPage.clickOnWorkDoneAdd();
		basePatientLifeCyclePage.dashBoardBtnVerify();
		basePatientLifeCyclePage.headerOnAddPage("Works Done");
		/* Only For First Time */
//		basePatientLifeCyclePage.noRecordMsgDisplayed();
		worksDonePage.checkedUserName(workDoneData.get("patient_name"));
		worksDonePage.checkedInvoiceListBtn();
		worksDonePage.checkedCollectAdvanceBtn();
		worksDonePage.checkedHistoryBtn();
		worksDonePage.checkLabWorkOrderBtn();
		basePatientLifeCyclePage.openCloseLeftNavigator();
		basePatientLifeCyclePage.webElementOfLeftNavigator();
		basePatientLifeCyclePage.openCloseLeftNavigator();
		worksDonePage.clickWorkDoneHistory();
		basePatientLifeCyclePage.dashBoardBtnVerify();
		basePatientLifeCyclePage.headerOnAddPage("Work Done History");
		basePatientLifeCyclePage.noRecordMsgDisplayed();
		worksDonePage.checkedUserName(workDoneData.get("patient_name"));
		workDoneHistoryPage.checkedInvoiceListBtn();
		worksDonePage.checkedCollectAdvanceBtn();
		basePatientLifeCyclePage.verifyAddNewBtn();
		workDoneHistoryPage.checkLabWorkOrderBtn();
		basePatientLifeCyclePage.openCloseLeftNavigator();
		basePatientLifeCyclePage.webElementOfLeftNavigator();
		basePatientLifeCyclePage.openCloseLeftNavigator();
		Assert.assertTrue(basePage.verification().contains("Work Done History"));
	}

	/**
	 * Added treatment for the patient
	 * checked same treatment at the treatment listing
	 * checked the start button at treatment listing if not present then added Appointment for the same patient
	 * started treatment from the treatment plan listing
	 * checked toast message starting the treatment without selecting any treatment at treatment plan listing
	 * selecting the treatment and started
	 * validated same treatment at work done add page
	 * same checked at the work done history page
	 * validated all action button present at the both work done add and work done history page
	 */
	
	@Test(enabled = true, priority = 2,description = "Start Treatment For Auto-Complete Plans")
	public void startTreatmenAutoComplete() {
		extentReport.logger.log(LogStatus.PASS, START_TREATMENT);
		patientDashboardPage.hideDueWarningPopup();
		basePatientLifeCyclePage.clickOnAlert();
		patientDashboardPage.clickOnTreatmentPlanAddBtn();
		basePatientLifeCyclePage.clickOnAlert();
		treatmentPlansPage.clickOnNewTeethBtn();
		OralExamsPage.clickOnTeethImage("Adult", "24");
		treatmentPlansPage.verifySeletecdTeethOnPopup("24");
		treatmentPlansPage.clickOnConsultationXRays();
		treatmentPlansPage.clickOnTreatments(workDoneData.get("planGroup4_Plan1"));
		treatmentPlansPage.saveTreatment();
		basePatientLifeCyclePage.clickOnDashBoard();
		basePatientLifeCyclePage.clickOnAlert();
		patientDashboardPage.clickOnTreatmentPlanListBtn();
		treatmentPlanListingPage.verifyDatesTreatmentPlanList();
		/*Checking start button and booking appointment for appearing start btn in doctor*/
//		treatmentPlanListingPage.checkStartBtnNotPresent(TODAY_DATE);
		basePatientLifeCyclePage.clickOnDashBoard();
		basePatientLifeCyclePage.clickOnAlert();
		patientDashboardPage.clickOnAppAdd();
		appointmentPage.selectDoctorFromDropdown(workDoneData.get("doctor"));
        appointmentPage.selectReferralFromDropdown("Patient");
		appointmentPage.clickOnSaveBtn();
		basePatientLifeCyclePage.clickOnAlert();
		patientDashboardPage.clickOnTreatmentPlanListBtn();
		treatmentPlanListingPage.verifyDatesTreatmentPlanList();
		treatmentPlanListingPage.clickOnStartBtn(TODAY_DATE);
		treatmentPlanListingPage.checkedWithoutTreatmentStart();
		treatmentPlanListingPage.selectTreatmentInTreatmentListPage(workDoneData.get("planGroup4_Plan1_FullName"));
		treatmentPlanListingPage.clickOnStartBtn(TODAY_DATE);
		worksDonePage.checkedTreatmentStartMsgDisplayed();
		worksDonePage.checkedAddAllBtn();
		worksDonePage.checkLabWorkOrderBtn();
		worksDonePage.checkedInvoiceListBtn();
		worksDonePage.checkedCollectPaymentBtn();
		worksDonePage.checkedPrintBtn();
		worksDonePage.checkedHistoryBtn();
		basePatientLifeCyclePage.dashBoardBtnVerify();
		worksDonePage.checkDateTreatment(workDoneData.get("planGroup4_Plan1_FullName"),TODAY_DATE);
		worksDonePage.checkSourceNo(workDoneData.get("planGroup4_Plan1_FullName"), "24");
		worksDonePage.checkedStatusTreatment(workDoneData.get("planGroup4_Plan1_FullName"),"Completed");
		worksDonePage.checkProgressDropDown(workDoneData.get("planGroup4_Plan1_FullName"));
		worksDonePage.checkDataName(workDoneData.get("planGroup4_Plan1_FullName"));
		worksDonePage.checkBox(workDoneData.get("planGroup4_Plan1_FullName"));
		worksDonePage.modifiedDate(workDoneData.get("planGroup4_Plan1_FullName"), TODAY_DATE);
		worksDonePage.checkDoctorSelected(workDoneData.get("planGroup4_Plan1_FullName"), workDoneData.get("doctor"));
		worksDonePage.checkedClinic(workDoneData.get("planGroup4_Plan1_FullName"), workDoneData.get("clinicLocation"));
		worksDonePage.checkTimeSelected(workDoneData.get("planGroup4_Plan1_FullName"), "Select");
		worksDonePage.checkedRemarks(workDoneData.get("planGroup4_Plan1_FullName"));
		worksDonePage.checkedAddButton(workDoneData.get("planGroup4_Plan1_FullName"));
		/*-------------workdone history check all data-----------------*/
		worksDonePage.clickWorkDoneHistory();
		workDoneHistoryPage.checkedInvoiceListBtn();
		worksDonePage.checkedCollectPaymentBtn();
		workDoneHistoryPage.checkLabWorkOrderBtn();
		workDoneHistoryPage.checkedPrintBtn();
		basePatientLifeCyclePage.verifyAddNewBtn();
		basePatientLifeCyclePage.dashBoardBtnVerify();
		workDoneHistoryPage.checkDateTreatment(workDoneData.get("planGroup4_Plan1_FullName"), TODAY_DATE);
		workDoneHistoryPage.checkSourceNo(workDoneData.get("planGroup4_Plan1_FullName"), "24");
		workDoneHistoryPage.checkedStatusTreatment(workDoneData.get("planGroup4_Plan1_FullName"), "Completed");
		workDoneHistoryPage.checkDataName(workDoneData.get("planGroup4_Plan1_FullName"));
		workDoneHistoryPage.modifiedDate(workDoneData.get("planGroup4_Plan1_FullName"), TODAY_DATE);
		workDoneHistoryPage.checkDoctorTreated(workDoneData.get("planGroup4_Plan1_FullName"),workDoneData.get("doctor"));
		workDoneHistoryPage.checkStageTreatment(workDoneData.get("planGroup4_Plan1_FullName"), "Completed");
		workDoneHistoryPage.checkedRemarks(workDoneData.get("planGroup4_Plan1_FullName"), "No Remarks");
		workDoneHistoryPage.checkPrintButtonDisplayed();
		Assert.assertTrue(basePage.verification().contains("Work Done History"));
	}
	
	@Test(enabled = true, priority = 3,description = "Start Treatment For Non Auto-Complete Plans")
	public void startTreatment() {
		extentReport.logger.log(LogStatus.PASS, START_TREATMENT);
		patientDashboardPage.hideDueWarningPopup();
		basePatientLifeCyclePage.clickOnAlert();
		patientDashboardPage.clickOnTreatmentPlanAddBtn();
		basePatientLifeCyclePage.clickOnAlert();
		treatmentPlansPage.clickOnNewTeethBtn();
		OralExamsPage.clickOnTeethImage("Adult", "24");
		treatmentPlansPage.verifySeletecdTeethOnPopup("24");
		treatmentPlansPage.clickOnCrowns();
		treatmentPlansPage.clickOnTreatments(workDoneData.get("planGroup1_Plan1"));
		treatmentPlansPage.saveTreatment();
		basePatientLifeCyclePage.clickOnDashBoard();
		basePatientLifeCyclePage.clickOnAlert();
		patientDashboardPage.clickOnTreatmentPlanListBtn();
		treatmentPlanListingPage.verifyDatesTreatmentPlanList();
		/*Checking start button and booking appointment for appearing start btn in doctor*/
//		treatmentPlanListingPage.checkStartBtnNotPresent(TODAY_DATE);
		basePatientLifeCyclePage.clickOnDashBoard();
		basePatientLifeCyclePage.clickOnAlert();
//		patientDashboardPage.clickOnAppAdd();
//		appointmentPage.selectDoctorFromDropdown(workDoneData.get("doctor"));
//      appointmentPage.selectReferralFromDropdown("Patient");
//		appointmentPage.clickOnSaveBtn();
//		basePatientLifeCyclePage.clickOnAlert();
		patientDashboardPage.clickOnTreatmentPlanListBtn();
		treatmentPlanListingPage.verifyDatesTreatmentPlanList();
		treatmentPlanListingPage.clickOnStartBtn(TODAY_DATE);
		treatmentPlanListingPage.checkedWithoutTreatmentStart();
		treatmentPlanListingPage.selectTreatmentInTreatmentListPage(workDoneData.get("planGroup1_Plan1_FullName"));
		treatmentPlanListingPage.clickOnStartBtn(TODAY_DATE);
		worksDonePage.checkedTreatmentStartMsgDisplayed();
		worksDonePage.checkedAddAllBtn();
		worksDonePage.checkLabWorkOrderBtn();
		worksDonePage.checkedInvoiceListBtn();
		worksDonePage.checkedCollectPaymentBtn();
		worksDonePage.checkedPrintBtn();
		worksDonePage.checkedHistoryBtn();
		basePatientLifeCyclePage.dashBoardBtnVerify();
		worksDonePage.checkDateTreatment(workDoneData.get("planGroup1_Plan1_FullName"), TODAY_DATE);
		worksDonePage.checkSourceNo(workDoneData.get("planGroup1_Plan1_FullName"), "24");
		worksDonePage.checkedStatusTreatment(workDoneData.get("planGroup1_Plan1_FullName"), "Started");
		worksDonePage.checkProgressDropDown(workDoneData.get("planGroup1_Plan1_FullName"));
		worksDonePage.checkSuspededBtn(workDoneData.get("planGroup1_Plan1_FullName"));
		worksDonePage.checkDataName(workDoneData.get("planGroup1_Plan1_FullName"));
		worksDonePage.checkBox(workDoneData.get("planGroup1_Plan1_FullName"));
		worksDonePage.modifiedDate(workDoneData.get("planGroup1_Plan1_FullName"), TODAY_DATE);
		worksDonePage.checkDoctorSelected(workDoneData.get("planGroup1_Plan1_FullName"), workDoneData.get("doctor"));
		worksDonePage.checkedClinic(workDoneData.get("planGroup1_Plan1_FullName"), workDoneData.get("clinicLocation"));
		worksDonePage.checkTimeSelected(workDoneData.get("planGroup1_Plan1_FullName"), "Select");
		worksDonePage.checkedRemarks(workDoneData.get("planGroup1_Plan1_FullName"));
		worksDonePage.checkedAddButton(workDoneData.get("planGroup1_Plan1_FullName"));
		/*-------------workdone history check all data-----------------*/
		worksDonePage.clickWorkDoneHistory();
		workDoneHistoryPage.checkedInvoiceListBtn();
		worksDonePage.checkedCollectPaymentBtn();
		workDoneHistoryPage.checkLabWorkOrderBtn();
		workDoneHistoryPage.checkedPrintBtn();
		basePatientLifeCyclePage.verifyAddNewBtn();
		basePatientLifeCyclePage.dashBoardBtnVerify();
		workDoneHistoryPage.checkDateTreatment(workDoneData.get("planGroup1_Plan1_FullName"), TODAY_DATE);
		workDoneHistoryPage.checkSourceNo(workDoneData.get("planGroup1_Plan1_FullName"), "24");
		workDoneHistoryPage.checkedStatusTreatment(workDoneData.get("planGroup1_Plan1_FullName"), "In-Progress");
		workDoneHistoryPage.checkDataName(workDoneData.get("planGroup1_Plan1_FullName"));
		workDoneHistoryPage.modifiedDate(workDoneData.get("planGroup1_Plan1_FullName"), TODAY_DATE);
		workDoneHistoryPage.checkDoctorTreated(workDoneData.get("planGroup1_Plan1_FullName"),workDoneData.get("doctor"));
		workDoneHistoryPage.checkedClinic(workDoneData.get("planGroup1_Plan1_FullName"), workDoneData.get("clinicLocation"));
		workDoneHistoryPage.checkSpentTime(workDoneData.get("planGroup1_Plan1_FullName"), "15");
		workDoneHistoryPage.checkStageTreatment(workDoneData.get("planGroup1_Plan1_FullName"), "Started");
		workDoneHistoryPage.checkedRemarks(workDoneData.get("planGroup1_Plan1_FullName"), "No Remarks");
		workDoneHistoryPage.checkPrintButtonDisplayed();
		Assert.assertTrue(basePage.verification().contains("Work Done History"));
	}

	/**
	 * Changing the work done stages and checked All new updated data at work done add page and work done history page
	 */
	@Test(enabled = true, priority = 4,description = "Verify Treatment Stages")
	public void treatmentStageInProgress() {
		extentReport.logger.log(LogStatus.PASS, TREATMENT_STAGE_IN_PROGRESS);
		patientDashboardPage.hideDueWarningPopup();
		basePatientLifeCyclePage.clickOnAlert();
		patientDashboardPage.clickOnWorkDoneAdd();
		basePatientLifeCyclePage.headerOnAddPage("Works Done");
		worksDonePage.selectDoctor(workDoneData.get("planGroup1_Plan1_FullName"), workDoneData.get("doctor"));
		worksDonePage.selectTime(workDoneData.get("planGroup1_Plan1_FullName"), "15");
		worksDonePage.enterRemarks(workDoneData.get("planGroup1_Plan1_FullName"), "it is for testing");
		worksDonePage.selectStages(workDoneData.get("planGroup1_Plan1_FullName"), "In-Progress");
		worksDonePage.clickOnAdd(workDoneData.get("planGroup1_Plan1_FullName"));
		worksDonePage.closeAppoitmentPopup();
		worksDonePage.checkedStatusTreatment(workDoneData.get("planGroup1_Plan1_FullName"), "In-Progress");
		worksDonePage.modifiedDate(workDoneData.get("planGroup1_Plan1_FullName"), TODAY_DATE);
		worksDonePage.checkDoctorTreated(workDoneData.get("planGroup1_Plan1_FullName"), workDoneData.get("doctor"));
		worksDonePage.checkSpentTime(workDoneData.get("planGroup1_Plan1_FullName"), "15");
		worksDonePage.checkStatus(workDoneData.get("planGroup1_Plan1_FullName"), "In-Progress");
		worksDonePage.checkRemarksWorkDoneAddPage(workDoneData.get("planGroup1_Plan1_FullName"),"it is for testing");
		worksDonePage.checkEdit(workDoneData.get("planGroup1_Plan1_FullName"));
		/*-------Changed stages of treatment and checked in workdone History---------*/
		worksDonePage.clickWorkDoneHistory();
		workDoneHistoryPage.checkDateTreatment(workDoneData.get("planGroup1_Plan1_FullName"), TODAY_DATE);
		workDoneHistoryPage.checkSourceNo(workDoneData.get("planGroup1_Plan1_FullName"), "24");
		workDoneHistoryPage.checkedStatusTreatment(workDoneData.get("planGroup1_Plan1_FullName"), "In-Progress");
		workDoneHistoryPage.checkDataName(workDoneData.get("planGroup1_Plan1_FullName"));
		workDoneHistoryPage.modifiedDate(workDoneData.get("planGroup1_Plan1_FullName"), TODAY_DATE);
		workDoneHistoryPage.checkDoctorTreated(workDoneData.get("planGroup1_Plan1_FullName"), workDoneData.get("doctor"));
		workDoneHistoryPage.checkedClinic(workDoneData.get("planGroup1_Plan1_FullName"), workDoneData.get("clinicLocation"));
		workDoneHistoryPage.checkSpentTime(workDoneData.get("planGroup1_Plan1_FullName"), "15");
		workDoneHistoryPage.checkStageTreatment(workDoneData.get("planGroup1_Plan1_FullName"), "In-Progress");
		workDoneHistoryPage.checkedRemarks(workDoneData.get("planGroup1_Plan1_FullName"), "it is for testing");
		workDoneHistoryPage.checkPrintButtonDisplayed();
		Assert.assertTrue(basePage.verification().contains("Work Done History"));
	}

	/**
	 * changing the stages of the treatment to complete and checked all new changes at the work done history and work done add
	 */
	@Test(enabled = true, priority = 5,dependsOnMethods = {"treatmentStageInProgress"})
	public void treatmentStageComplete() {
		extentReport.logger.log(LogStatus.PASS, TREATMENT_STAGE_COMPLETE);
		patientDashboardPage.clickOnWorkDoneAdd();
		basePatientLifeCyclePage.headerOnAddPage("Works Done");
		worksDonePage.selectDoctor(workDoneData.get("planGroup1_Plan1_FullName"), workDoneData.get("doctor"));
		worksDonePage.selectTime(workDoneData.get("planGroup1_Plan1_FullName"), "30");
		worksDonePage.enterRemarks(workDoneData.get("planGroup1_Plan1_FullName"), "Change to complete");
		worksDonePage.selectStages(workDoneData.get("planGroup1_Plan1_FullName"), "Completed");
		worksDonePage.clickOnAdd(workDoneData.get("planGroup1_Plan1_FullName"));
		workDoneHistoryPage.checkDateTreatment(workDoneData.get("planGroup1_Plan1_FullName"), TODAY_DATE);
		workDoneHistoryPage.checkSourceNo(workDoneData.get("planGroup1_Plan1_FullName"), "24");
		workDoneHistoryPage.checkedStatusTreatment(workDoneData.get("planGroup1_Plan1_FullName"), "Completed");
		workDoneHistoryPage.followUpBtn(workDoneData.get("planGroup1_Plan1_FullName"));
		workDoneHistoryPage.reTreatBtn(workDoneData.get("planGroup1_Plan1_FullName"));
		workDoneHistoryPage.checkDataName(workDoneData.get("planGroup1_Plan1_FullName"));
		workDoneHistoryPage.modifiedDate(workDoneData.get("planGroup1_Plan1_FullName"), TODAY_DATE);
		workDoneHistoryPage.checkDoctorTreated(workDoneData.get("planGroup1_Plan1_FullName"), workDoneData.get("doctor"));
		workDoneHistoryPage.checkedClinic(workDoneData.get("planGroup1_Plan1_FullName"), workDoneData.get("clinicLocation"));
		workDoneHistoryPage.checkSpentTime(workDoneData.get("planGroup1_Plan1_FullName"), "30");
		workDoneHistoryPage.checkStageTreatment(workDoneData.get("planGroup1_Plan1_FullName"), "Completed");
		workDoneHistoryPage.checkedRemarks(workDoneData.get("planGroup1_Plan1_FullName"), "Change to complete");
		workDoneHistoryPage.checkedInvoiceListBtn();
		workDoneHistoryPage.checkLabWorkOrderBtn();
		workDoneHistoryPage.checkedPrintBtn();
		basePatientLifeCyclePage.verifyAddNewBtn();
		basePatientLifeCyclePage.dashBoardBtnVerify();
		basePatientLifeCyclePage.clickOnAddNewBtn();
		basePatientLifeCyclePage.noRecordMsgDisplayed();
		worksDonePage.checkLabWorkOrderBtn();
		worksDonePage.checkedInvoiceListBtn();
		worksDonePage.checkedHistoryBtn();
		basePatientLifeCyclePage.dashBoardBtnVerify();
		worksDonePage.checkedPrintBtnNotPresent();
		worksDonePage.checkedAddAllBtnNotPresent();
		Assert.assertTrue(basePage.verification().contains("Works Done"));
	}

	/**
	 * validated all cases of the follow up modal and checked same at work done history and work done add
	 */
	@Test(enabled = true, priority = 6,dependsOnMethods = {"treatmentStageComplete"})
	public void followUp(){
		extentReport.logger.log(LogStatus.PASS, FOLLOW_UP);
		patientDashboardPage.clickOnWorkDoneHistory();
		workDoneHistoryPage.checkedStatusTreatment(workDoneData.get("planGroup1_Plan1_FullName"), "Completed");
		workDoneHistoryPage.clickFollowUpBtn(workDoneData.get("planGroup1_Plan1_FullName"));
		workDoneHistoryPage.headerFollowUpModal("Follow up");
		workDoneHistoryPage.closeBtnFollowUpModal();
		workDoneHistoryPage.treatmentFollowUpModal(workDoneData.get("planGroup1_Plan1_FullName"));
		workDoneHistoryPage.sourceFollowUpModal("24");
		workDoneHistoryPage.clinicNameFollowUpModal(workDoneData.get("clinicLocation"));
		workDoneHistoryPage.saveFollowUpDetails();
		workDoneHistoryPage.errorMessageDisplayed();
		workDoneHistoryPage.notesFollowUp("follow up testing");
		workDoneHistoryPage.saveFollowUpDetails();
		workDoneHistoryPage.closeBtnFollowUpModal();
		workDoneHistoryPage.selectSpentTime("15");
		workDoneHistoryPage.selectTreatingDentistFollowUpModal(workDoneData.get("doctor"));
		workDoneHistoryPage.saveFollowUpDetails();
		workDoneHistoryPage.checkDateTreatment(workDoneData.get("planGroup1_Plan1_FullName"), TODAY_DATE);
		workDoneHistoryPage.checkSourceNo(workDoneData.get("planGroup1_Plan1_FullName"), "24");
		workDoneHistoryPage.checkedStatusTreatment(workDoneData.get("planGroup1_Plan1_FullName"), "Completed");
		workDoneHistoryPage.followUpBtn(workDoneData.get("planGroup1_Plan1_FullName"));
		workDoneHistoryPage.reTreatBtn(workDoneData.get("planGroup1_Plan1_FullName"));
		workDoneHistoryPage.checkDataName(workDoneData.get("planGroup1_Plan1_FullName"));
		workDoneHistoryPage.modifiedDate(workDoneData.get("planGroup1_Plan1_FullName"), TODAY_DATE);
		workDoneHistoryPage.checkDoctorTreated(workDoneData.get("planGroup1_Plan1_FullName"), workDoneData.get("doctor"));
		workDoneHistoryPage.checkedClinic(workDoneData.get("planGroup1_Plan1_FullName"), workDoneData.get("clinicLocation"));
		workDoneHistoryPage.checkSpentTime(workDoneData.get("planGroup1_Plan1_FullName"), "15");
		workDoneHistoryPage.checkStageTreatment(workDoneData.get("planGroup1_Plan1_FullName"), "Follow Up");
		workDoneHistoryPage.checkedRemarks(workDoneData.get("planGroup1_Plan1_FullName"), "follow up testing");
		workDoneHistoryPage.checkedInvoiceListBtn();
		workDoneHistoryPage.checkLabWorkOrderBtn();
		workDoneHistoryPage.checkedPrintBtn();
		basePatientLifeCyclePage.verifyAddNewBtn();
		basePatientLifeCyclePage.dashBoardBtnVerify();
		basePatientLifeCyclePage.clickOnAddNewBtn();
		basePatientLifeCyclePage.noRecordMsgDisplayed();
		worksDonePage.checkLabWorkOrderBtn();
		worksDonePage.checkedInvoiceListBtn();
		worksDonePage.checkedHistoryBtn();
		basePatientLifeCyclePage.dashBoardBtnVerify();
		worksDonePage.checkedPrintBtnNotPresent();
		worksDonePage.checkedAddAllBtnNotPresent();
		Assert.assertTrue(basePage.verification().contains("Works Done"));
	}

	/**
	 * validated retreat for the treatment scenario at the both page work done history and work done add page
	 */
	@Test(enabled = true, priority = 7,dependsOnMethods = {"followUp"})
	public void reTreat(){
		extentReport.logger.log(LogStatus.PASS, RE_TREAT);
		patientDashboardPage.clickOnWorkDoneHistory();
		workDoneHistoryPage.clickReTreatBtn(workDoneData.get("planGroup1_Plan1_FullName"));
		workDoneHistoryPage.headerReTreatModal("Are you sure you want to re-treat this treatment?");
		workDoneHistoryPage.closeBtnRetreatBtn();
		workDoneHistoryPage.selectTreatingDentistReTreatModal(workDoneData.get("doctor"));
		workDoneHistoryPage.stageDropDownFirstTime();
		workDoneHistoryPage.saveRetreatDetails();
		workDoneHistoryPage.errorMessage("Select Reason");
		workDoneHistoryPage.selectReason("Others");
		workDoneHistoryPage.saveRetreatDetails();
		workDoneHistoryPage.errorMessage("Enter Notes");
		workDoneHistoryPage.notesRetreat("re-treat testing");
		workDoneHistoryPage.selectStage("Select Stage");
		workDoneHistoryPage.saveRetreatDetails();
		workDoneHistoryPage.errorMessage("Select Stage");
		workDoneHistoryPage.selectStage("In-Progress");
		workDoneHistoryPage.saveRetreatDetails();
		worksDonePage.checkedStatusTreatment(workDoneData.get("planGroup1_Plan1_FullName"), "In-Progress");
		worksDonePage.modifiedDate(workDoneData.get("planGroup1_Plan1_FullName"), TODAY_DATE);
		worksDonePage.checkDoctorTreated(workDoneData.get("planGroup1_Plan1_FullName"), workDoneData.get("doctor"));
		worksDonePage.checkSpentTime(workDoneData.get("planGroup1_Plan1_FullName"), "0");
		worksDonePage.checkStatus(workDoneData.get("planGroup1_Plan1_FullName"), "In-Progress: Re-Treat");
		worksDonePage.checkRemarksWorkDoneAddPage(workDoneData.get("planGroup1_Plan1_FullName"),"re-treat testing");
		Assert.assertTrue(basePage.verification().contains("Works Done"));
	}


	@AfterMethod(description = STATUS_OF_SCRIPTS, enabled = true)
	public void statusOfScripts(ITestResult result) {
		extentReport.onTestFailure(result);
		extentReport.logger.log(LogStatus.PASS, STATUS_OF_SCRIPTS);
		basePatientLifeCyclePage.clickOnDashBoard();
	}

	@AfterClass
	public void tearDown() {
		extentReport.report.endTest(extentReport.logger);
		extentReport.report.flush();
		// extentReport.report.close();
		basePage.clickOnlogOut();
	}
}
