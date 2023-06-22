package com.prm.scripts;

import com.prm.pageobjects.utils.TestData;
import com.prm.patientdashboard.pageobject.*;
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
import com.prm.pageobjects.utils.ExtentReport;
import com.prm.pageobjects.utils.ReadConfig;
import com.prm.pageobjects.utils.ReadExcelData;
import com.relevantcodes.extentreports.LogStatus;

public class LabWorkOrderListingTestCase2 {

	private LoginPage loginPage = new LoginPage();
	private DoctorDashBoard doctorDashBoard = new DoctorDashBoard(loginPage);
	private BasePage basePage = new BasePage(loginPage);
	private ExtentReport extentReport = new ExtentReport(loginPage, "LabWorkOrderTestCase");
	private PatientDashboardPage patientDashboardPage = new PatientDashboardPage(loginPage);
	private OralExamsPage oralExamsPage=new OralExamsPage(loginPage);
	private BasePatientLifeCyclePage basePatientLifeCyclePage = new BasePatientLifeCyclePage(loginPage);
	private TreatmentPlansPage treatmentPlansPage = new TreatmentPlansPage(loginPage);
	private TreatmentPlanListingPage treatmentPlanListingPage= new TreatmentPlanListingPage(loginPage);
	private WorksDonePage workDonePage = new WorksDonePage(loginPage);
	private WorkDoneHistoryPage workDoneHistoryPage = new WorkDoneHistoryPage(loginPage);
	private InvoiceListingPage invoiceListingPage = new InvoiceListingPage(loginPage,extentReport);
	private NewReceiptPage newReceiptPage = new NewReceiptPage(loginPage);
	private LabWorkOrderListingPage labWorkOrderListingPage = new LabWorkOrderListingPage(loginPage);
	private AppointmentAddPage appointmentPage = new AppointmentAddPage(loginPage);
	private LabWorkOrderEditPage labWorkOrderEditPage = new LabWorkOrderEditPage(loginPage);

	/*
	 * Storing mandatory input required to run work done test cases
	 * or please update the mandatory input before running the test cases
	 */
	private static final String FILE_PATH = TestData.getInstance().getInputData("lab_work_order2_file_path");
	private static final String SHEET = TestData.getInstance().getInputData("lab_work_order2_file_sheet_name");
	private static final String PATIENT_NAME= TestData.getInstance().getInputData("lab_work_order2_patient_name");
	private static final String MOBILE_NUMBER = TestData.getInstance().getInputData("lab_work_order2_patient_mobile");
	private static final String DOCTOR_NAME = TestData.getInstance().getInputData("lab_work_order2_doctor_name");
	private static final String DOCTOR_FIRST_NAME = TestData.getInstance().getInputData("lab_work_order2_doctor_first_name");
	private static final String CLINIC_NAME = TestData.getInstance().getInputData("lab_work_order2_clinic_name");
	private static final String TODAY_DATE=TestData.getInstance().getTodayDate();
	private static final String START_DATE_TIME=TestData.getInstance().getFewMinuteBackFromCurrentDateTime();
	private static final String END_DATE_TIME=TestData.getInstance().getFewMinuteAfterFromCurrentDateTime();

	/*
	 * Messages for every test cases which will be print on the extent report
	 */
	private static final String SCRIPTS_STARTED_MSG  = "@@ Test scripts has been started here @@";
	private static final String STATUS_OF_SCRIPTS  = "@@ Test script has been completed @@";
	private static final String CHECK_SUSPEND_RELATED_SCENARIO_ON_LAB_WORK_ORDER = "Validate Scenerio of Suspend related and check the impact on Lab Work Order -#checkSuspendRelatedScenarioOnLabWorkOrder";
	private static final String CHECK_SUSPEND_RELATED_ANOTHER_SCENARIO_ON_LAB_WORK_ORDER = "Validate Another Scenerio of Suspend related and check the impact on Lab Work Order -#CheckSuspendRelatedAnotherScenerioOnLWO";
	private static final String CHECK_BRIDGEABLE_RELATED_SCENARIO = "Validate Bridgeable related Scenario and Check the Impact on LWO -#checkbridgeableRelatedScenerio";
	private static final String CHECK_BY_ARCH_RELATED_SCENARIO = "Validate ByArch related Scenario and Check the Impact on LWO -#checkByArchRelatedScenario";

	/*
	 * loginPage with username and password
	 * search for patients from the doctor dashboard
	 * verified patients will redirect at the patient dashboard
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
		doctorDashBoard.doctorDashboardHomePage();
		basePage.selectClinicFrmHeader(CLINIC_NAME);
		basePage.enterMobileNo(MOBILE_NUMBER);
		basePage.clickOnSearchBtn();
		basePage.clickOnPatient(MOBILE_NUMBER, PATIENT_NAME);
		Assert.assertTrue(basePage.verification().contains("Patient Dashboard"));
	}

	@BeforeMethod
	public void verifyPatientdashBoardHomePage() {
		extentReport.logger.log(LogStatus.PASS, SCRIPTS_STARTED_MSG );
	}

	/*
	 *check the Impact on LWO when we start two Teeth Trt
	 *check the Impact on LWO when we suspend First Teeth
	 *check the Impact on LWO when we Complete the Trt of second Teeth
	 *check the Impact on LWO when we reopen the suspended Trt
	 *check the Impact on LWO when we Completed Treatment of reopen Trt
	 *check the Impact on LWO when we take the Payment of that LWO
	 */
	@Test(enabled=true, priority=1)
	public void checkSuspendRelatedScenarioOnLabWorkOrder() {
		extentReport.logger.log(LogStatus.PASS, CHECK_SUSPEND_RELATED_SCENARIO_ON_LAB_WORK_ORDER);
		patientDashboardPage.clickOnAppAdd();
		appointmentPage.selectDoctorFromDropdown(DOCTOR_NAME);
		appointmentPage.clickOnSaveBtn();
		basePatientLifeCyclePage.clickOnAlert();
		patientDashboardPage.clickOnTreatmentPlanAddBtn();
		basePatientLifeCyclePage.clickOnAlert();
		treatmentPlansPage.clickOnNewTeethBtn();
		oralExamsPage.selectMultipleTeeth("Adult",ReadExcelData.readExcelData(FILE_PATH, SHEET, 2,4 ));
		oralExamsPage.clickOnTeethImage("Adult",ReadExcelData.readExcelData(FILE_PATH, SHEET, 3,4 ));
		treatmentPlansPage.clickOnYes();
		treatmentPlansPage.clickOnIpCDOPBank();
		treatmentPlansPage.clickOnSearchField(ReadExcelData.readExcelData(FILE_PATH, SHEET, 20,1 ));
		treatmentPlansPage.clickOnTreatments(ReadExcelData.readExcelData(FILE_PATH, SHEET, 20,1 ));
		treatmentPlansPage.saveTreatment();
		treatmentPlansPage.clickOnSaveBtnTreatmentInputList();
		treatmentPlanListingPage.verifyStartBtn(TODAY_DATE);
		treatmentPlanListingPage.selectTreatmentInTreatmentListPage(ReadExcelData.readExcelData(FILE_PATH, SHEET, 20,2 ));
		treatmentPlanListingPage.clickOnStartBtn(TODAY_DATE);
		basePatientLifeCyclePage.headerOnAddPage("Works Done");
		workDonePage.clickLWOBtnWD();
		basePatientLifeCyclePage.headerOnAddPage("Lab Work Order Listing");
		labWorkOrderListingPage.clickOnEdit(ReadExcelData.readExcelData(FILE_PATH, SHEET, 2,4));
		labWorkOrderEditPage.selectLab(ReadExcelData.readExcelData(FILE_PATH, SHEET, 2,0));
		labWorkOrderEditPage.clickOnItemSend();
		labWorkOrderEditPage.selectItemsSend(ReadExcelData.readExcelData(FILE_PATH, SHEET, 2,5));
		labWorkOrderEditPage.addFile("D:\\Clone\\prmautomation\\PatientFiles\\"+ReadExcelData.readExcelData(FILE_PATH, SHEET, 2,21));
		labWorkOrderEditPage.checkedAdditionalDetailPanel();
		labWorkOrderEditPage.selectRequire(ReadExcelData.readExcelData(FILE_PATH, SHEET, 2,10));
		labWorkOrderEditPage.selectShade(ReadExcelData.readExcelData(FILE_PATH, SHEET, 2,11));
		labWorkOrderEditPage.selectShadeOptionFromDrpDwn(ReadExcelData.readExcelData(FILE_PATH, SHEET, 2,12));
		labWorkOrderEditPage.selectStainsInternal(ReadExcelData.readExcelData(FILE_PATH, SHEET, 2,13));
		labWorkOrderEditPage.selectStainsExternal(ReadExcelData.readExcelData(FILE_PATH, SHEET, 2,14));
		labWorkOrderEditPage.addTransluency(ReadExcelData.readExcelData(FILE_PATH, SHEET, 2,15));
		labWorkOrderEditPage.addTexture(ReadExcelData.readExcelData(FILE_PATH, SHEET, 2,16));
		labWorkOrderEditPage.selectConfigurations(ReadExcelData.readExcelData(FILE_PATH, SHEET, 2,17), ReadExcelData.readExcelData(FILE_PATH, SHEET, 2,18));
		labWorkOrderEditPage.enterNotes(ReadExcelData.readExcelData(FILE_PATH, SHEET, 2,20));
		labWorkOrderEditPage.clickSubmittedToLab();
		labWorkOrderListingPage.verifySuccessfullMessage();
		basePatientLifeCyclePage.headerOnAddPage("Lab Work Order Listing");
		basePatientLifeCyclePage.clickOnDashBoard();
		basePatientLifeCyclePage.clickOnAlert();
		patientDashboardPage.clickOnWorkDoneAdd();
		basePatientLifeCyclePage.headerOnAddPage("Works Done");
		workDonePage.clickSuspendBtn(ReadExcelData.readExcelData(FILE_PATH, SHEET, 2,4));
		workDonePage.selectRequire("Assuring for refund.");
		workDonePage.clickSaveBtnOnReasonPopUp();
		workDonePage.clickLWOBtnWD();
		basePatientLifeCyclePage.headerOnAddPage("Lab Work Order Listing");
		labWorkOrderListingPage.checkInActiveEntry(ReadExcelData.readExcelData(FILE_PATH, SHEET, 2,4),ReadExcelData.readExcelData(FILE_PATH, SHEET, 1,7),"Trt. Suspended");
		labWorkOrderListingPage.checkInActiveRevisionReasonMainList(ReadExcelData.readExcelData(FILE_PATH, SHEET, 2,4), ReadExcelData.readExcelData(FILE_PATH, SHEET, 1,7), ReadExcelData.readExcelData(FILE_PATH, SHEET, 4,8));
		labWorkOrderListingPage.checkInActiveViewBtn(ReadExcelData.readExcelData(FILE_PATH, SHEET, 2,4),ReadExcelData.readExcelData(FILE_PATH, SHEET, 1,7));
		labWorkOrderListingPage.checkDateTimeMainList(ReadExcelData.readExcelData(FILE_PATH, SHEET, 3,4), START_DATE_TIME, END_DATE_TIME);
		labWorkOrderListingPage.checkSourceForSuspAndRetreatMainList(ReadExcelData.readExcelData(FILE_PATH, SHEET, 2,4));
		labWorkOrderListingPage.checkSourceMainList(ReadExcelData.readExcelData(FILE_PATH, SHEET, 3,4));
		labWorkOrderListingPage.checkRevisionReasonMainList(ReadExcelData.readExcelData(FILE_PATH, SHEET, 3,4), ReadExcelData.readExcelData(FILE_PATH, SHEET, 2,7), ReadExcelData.readExcelData(FILE_PATH, SHEET, 1,8));
		labWorkOrderListingPage.clickOnView(ReadExcelData.readExcelData(FILE_PATH, SHEET, 3,4));
		labWorkOrderListingPage.verifyHeaderInView("Lab Work Order");
		labWorkOrderListingPage.checkSourceForSuspendedViewList(ReadExcelData.readExcelData(FILE_PATH, SHEET, 2,4), "Suspended");
		labWorkOrderListingPage.checkStatusEnableInView(ReadExcelData.readExcelData(FILE_PATH, SHEET, 1,9));
		labWorkOrderListingPage.checkStatusEnableInView(ReadExcelData.readExcelData(FILE_PATH, SHEET, 2,9));
		labWorkOrderListingPage.verifyStatusInView(ReadExcelData.readExcelData(FILE_PATH, SHEET, 1,9), START_DATE_TIME, END_DATE_TIME);
		labWorkOrderListingPage.verifyStatusInView(ReadExcelData.readExcelData(FILE_PATH, SHEET, 2,9), START_DATE_TIME, END_DATE_TIME);
		labWorkOrderListingPage.checkRevisionNoViewList(ReadExcelData.readExcelData(FILE_PATH, SHEET, 2,22));
		labWorkOrderListingPage.verifyRevisionDateInView(START_DATE_TIME, END_DATE_TIME);
		labWorkOrderListingPage.checkCurrentStatusViewList(ReadExcelData.readExcelData(FILE_PATH, SHEET, 2,9));
		labWorkOrderListingPage.clickViewScreenClose();
		basePatientLifeCyclePage.clickOnDashBoard();
		basePatientLifeCyclePage.clickOnAlert();
		patientDashboardPage.clickOnWorkDoneAdd();
		basePatientLifeCyclePage.headerOnAddPage("Works Done");
		workDonePage.selectTime(ReadExcelData.readExcelData(FILE_PATH, SHEET, 20, 2), "15");
		workDonePage.enterRemarks(ReadExcelData.readExcelData(FILE_PATH, SHEET, 20, 2), "Change to complete");
		workDonePage.selectStages(ReadExcelData.readExcelData(FILE_PATH, SHEET, 20, 2), "Cementation");
		workDonePage.clickOnAdd(ReadExcelData.readExcelData(FILE_PATH, SHEET, 20, 2));
		basePatientLifeCyclePage.headerOnAddPage("Work Done History");
		workDonePage.clickLWOBtnWD();
		basePatientLifeCyclePage.headerOnAddPage("Lab Work Order Listing");
		labWorkOrderListingPage.checkStatusDateMainList(ReadExcelData.readExcelData(FILE_PATH, SHEET, 3,4),ReadExcelData.readExcelData(FILE_PATH, SHEET, 4,9), START_DATE_TIME, END_DATE_TIME);
		labWorkOrderListingPage.clickOnView(ReadExcelData.readExcelData(FILE_PATH, SHEET, 3,4));
		labWorkOrderListingPage.verifyHeaderInView("Lab Work Order");
		labWorkOrderListingPage.checkStatusEnableInView(ReadExcelData.readExcelData(FILE_PATH, SHEET, 1,9));
		labWorkOrderListingPage.checkStatusEnableInView(ReadExcelData.readExcelData(FILE_PATH, SHEET, 2,9));
		labWorkOrderListingPage.checkStatusEnableInView(ReadExcelData.readExcelData(FILE_PATH, SHEET, 4,9));
		labWorkOrderListingPage.verifyStatusInView(ReadExcelData.readExcelData(FILE_PATH, SHEET, 1,9),START_DATE_TIME, END_DATE_TIME);
		labWorkOrderListingPage.verifyStatusInView(ReadExcelData.readExcelData(FILE_PATH, SHEET, 2,9),START_DATE_TIME, END_DATE_TIME);
		labWorkOrderListingPage.verifyStatusInView(ReadExcelData.readExcelData(FILE_PATH, SHEET, 4,9),START_DATE_TIME, END_DATE_TIME);
		labWorkOrderListingPage.checkCurrentStatusViewList(ReadExcelData.readExcelData(FILE_PATH, SHEET, 4,9));
		labWorkOrderListingPage.clickViewScreenClose();
		basePatientLifeCyclePage.clickOnDashBoard();
		basePatientLifeCyclePage.clickOnAlert();
		patientDashboardPage.clickOnWorkDoneHistory();
		basePatientLifeCyclePage.headerOnAddPage("Work Done History");
		workDoneHistoryPage.clickReOpenBtn(ReadExcelData.readExcelData(FILE_PATH, SHEET, 2,4));
		workDoneHistoryPage.selectRequire("Patient returned back for the treatment.");
		workDoneHistoryPage.clickSaveBtnOnReasonPopUp();
		workDonePage.clickLWOBtnWD();
		basePatientLifeCyclePage.headerOnAddPage("Lab Work Order Listing");
		labWorkOrderListingPage.checkInActiveEntry(ReadExcelData.readExcelData(FILE_PATH, SHEET, 3,4),ReadExcelData.readExcelData(FILE_PATH, SHEET, 2,7),"Trt. Reopened");
		labWorkOrderListingPage.checkInActiveRevisionReasonMainList(ReadExcelData.readExcelData(FILE_PATH, SHEET, 3,4), ReadExcelData.readExcelData(FILE_PATH, SHEET, 2,7), ReadExcelData.readExcelData(FILE_PATH, SHEET, 5,8));
		labWorkOrderListingPage.checkInActiveViewBtn(ReadExcelData.readExcelData(FILE_PATH, SHEET, 3,4),ReadExcelData.readExcelData(FILE_PATH, SHEET, 2,7));
		labWorkOrderListingPage.checkDateTimeMainList(ReadExcelData.readExcelData(FILE_PATH, SHEET, 3,4), START_DATE_TIME, END_DATE_TIME);
		labWorkOrderListingPage.checkSourceForSuspAndRetreatMainList(ReadExcelData.readExcelData(FILE_PATH, SHEET, 2,4));
		labWorkOrderListingPage.checkSourceMainList(ReadExcelData.readExcelData(FILE_PATH, SHEET, 3,4));
		labWorkOrderListingPage.checkRevisionReasonMainList(ReadExcelData.readExcelData(FILE_PATH, SHEET, 3,4), ReadExcelData.readExcelData(FILE_PATH, SHEET, 3,7), ReadExcelData.readExcelData(FILE_PATH, SHEET, 1,8));
		labWorkOrderListingPage.clickOnView(ReadExcelData.readExcelData(FILE_PATH, SHEET, 3,4));
		labWorkOrderListingPage.verifyHeaderInView("Lab Work Order");
		labWorkOrderListingPage.checkSourceForReopenAndTrtCompleteViewList(ReadExcelData.readExcelData(FILE_PATH, SHEET, 2,4), "Reopened");
		labWorkOrderListingPage.checkStatusEnableInView(ReadExcelData.readExcelData(FILE_PATH, SHEET, 1,9));
		labWorkOrderListingPage.checkStatusEnableInView(ReadExcelData.readExcelData(FILE_PATH, SHEET, 2,9));
		labWorkOrderListingPage.verifyStatusInView(ReadExcelData.readExcelData(FILE_PATH, SHEET, 1,9), START_DATE_TIME, END_DATE_TIME);
		labWorkOrderListingPage.verifyStatusInView(ReadExcelData.readExcelData(FILE_PATH, SHEET, 2,9), START_DATE_TIME, END_DATE_TIME);
		labWorkOrderListingPage.checkRevisionNoViewList(ReadExcelData.readExcelData(FILE_PATH, SHEET, 3,22));
		labWorkOrderListingPage.verifyRevisionDateInView(START_DATE_TIME, END_DATE_TIME);
		labWorkOrderListingPage.checkCurrentStatusViewList(ReadExcelData.readExcelData(FILE_PATH, SHEET, 2,9));
		labWorkOrderListingPage.clickViewScreenClose();
		basePatientLifeCyclePage.clickOnDashBoard();
		basePatientLifeCyclePage.clickOnAlert();
		patientDashboardPage.clickOnWorkDoneAdd();
		basePatientLifeCyclePage.headerOnAddPage("Works Done");
		workDonePage.selectTime(ReadExcelData.readExcelData(FILE_PATH, SHEET, 20, 2), "15");
		workDonePage.enterRemarks(ReadExcelData.readExcelData(FILE_PATH, SHEET, 20, 2), "Change to complete");
		workDonePage.selectStages(ReadExcelData.readExcelData(FILE_PATH, SHEET, 20, 2), "Cementation");
		workDonePage.clickOnAdd(ReadExcelData.readExcelData(FILE_PATH, SHEET, 20, 2));
		basePatientLifeCyclePage.headerOnAddPage("Work Done History");
		workDonePage.clickLWOBtnWD();
		basePatientLifeCyclePage.headerOnAddPage("Lab Work Order Listing");
		labWorkOrderListingPage.checkStatusDateMainList(ReadExcelData.readExcelData(FILE_PATH, SHEET, 3,4),ReadExcelData.readExcelData(FILE_PATH, SHEET, 4,9), START_DATE_TIME, END_DATE_TIME);
		labWorkOrderListingPage.clickOnView(ReadExcelData.readExcelData(FILE_PATH, SHEET, 3,4));
		labWorkOrderListingPage.verifyHeaderInView("Lab Work Order");
		labWorkOrderListingPage.checkSourceForReopenAndTrtCompleteViewList(ReadExcelData.readExcelData(FILE_PATH, SHEET, 2,4),ReadExcelData.readExcelData(FILE_PATH, SHEET, 6,8));
		labWorkOrderListingPage.checkStatusEnableInView(ReadExcelData.readExcelData(FILE_PATH, SHEET, 1,9));
		labWorkOrderListingPage.checkStatusEnableInView(ReadExcelData.readExcelData(FILE_PATH, SHEET, 2,9));
		labWorkOrderListingPage.checkStatusEnableInView(ReadExcelData.readExcelData(FILE_PATH, SHEET, 4,9));
		labWorkOrderListingPage.verifyStatusInView(ReadExcelData.readExcelData(FILE_PATH, SHEET, 1,9),START_DATE_TIME, END_DATE_TIME);
		labWorkOrderListingPage.verifyStatusInView(ReadExcelData.readExcelData(FILE_PATH, SHEET, 2,9),START_DATE_TIME, END_DATE_TIME);
		labWorkOrderListingPage.verifyStatusInView(ReadExcelData.readExcelData(FILE_PATH, SHEET, 4,9),START_DATE_TIME, END_DATE_TIME);
		labWorkOrderListingPage.checkCurrentStatusViewList(ReadExcelData.readExcelData(FILE_PATH, SHEET, 4,9));
		labWorkOrderListingPage.clickViewScreenClose();
		basePatientLifeCyclePage.clickOnDashBoard();
		basePatientLifeCyclePage.clickOnAlert();
		patientDashboardPage.clickOnInvoiceList();
		invoiceListingPage.headerPage("Invoice Listing");
//		invoiceListingPage.clickInvoicePaymentBtn();
//		newReceiptPage.headerNewReceipt("New Receipt");
//		newReceiptPage.clickPaymentByInvoice();
//		newReceiptPage.collectByInvoiceNewReceipts(5980);
//		newReceiptPage.selectPaymentMode("Cash");
//		newReceiptPage.saveNewReceipts();
		basePatientLifeCyclePage.clickOnDashBoard();
		basePatientLifeCyclePage.clickOnAlert();
		patientDashboardPage.clickOnLabWorkOrderAdd();
		basePatientLifeCyclePage.headerOnAddPage("Lab Work Order Listing");
		labWorkOrderListingPage.checkStatusDateMainList(ReadExcelData.readExcelData(FILE_PATH, SHEET, 3,4),ReadExcelData.readExcelData(FILE_PATH, SHEET, 5,9), START_DATE_TIME, END_DATE_TIME);
		labWorkOrderListingPage.clickOnView(ReadExcelData.readExcelData(FILE_PATH, SHEET, 3,4));
		labWorkOrderListingPage.verifyHeaderInView("Lab Work Order");
		labWorkOrderListingPage.checkSourceForReopenAndTrtCompleteViewList(ReadExcelData.readExcelData(FILE_PATH, SHEET, 2,4),"Payment and Trt. Completed");
		labWorkOrderListingPage.checkStatusEnableInView(ReadExcelData.readExcelData(FILE_PATH, SHEET, 1,9));
		labWorkOrderListingPage.checkStatusEnableInView(ReadExcelData.readExcelData(FILE_PATH, SHEET, 2,9));
		labWorkOrderListingPage.checkStatusEnableInView(ReadExcelData.readExcelData(FILE_PATH, SHEET, 3,9));
		labWorkOrderListingPage.checkStatusEnableInView(ReadExcelData.readExcelData(FILE_PATH, SHEET, 4,9));
		labWorkOrderListingPage.checkStatusEnableInView(ReadExcelData.readExcelData(FILE_PATH, SHEET, 6,9));
		labWorkOrderListingPage.verifyStatusInView(ReadExcelData.readExcelData(FILE_PATH, SHEET, 1,9),START_DATE_TIME, END_DATE_TIME);
		labWorkOrderListingPage.verifyStatusInView(ReadExcelData.readExcelData(FILE_PATH, SHEET, 2,9),START_DATE_TIME, END_DATE_TIME);
		labWorkOrderListingPage.verifyStatusInView(ReadExcelData.readExcelData(FILE_PATH, SHEET, 3,9),START_DATE_TIME, END_DATE_TIME);
		labWorkOrderListingPage.verifyStatusInView(ReadExcelData.readExcelData(FILE_PATH, SHEET, 4,9),START_DATE_TIME, END_DATE_TIME);
		labWorkOrderListingPage.verifyStatusInView(ReadExcelData.readExcelData(FILE_PATH, SHEET, 6,9),START_DATE_TIME, END_DATE_TIME);
		labWorkOrderListingPage.checkCurrentStatusViewList(ReadExcelData.readExcelData(FILE_PATH, SHEET, 5,9));
		labWorkOrderListingPage.clickViewScreenClose();
		Assert.assertTrue(basePage.verification().contains("Lab Work Order Listing"));
	}

	/*
	 *check the Impact on LWO when we start two Teeth Trt
	 *check the Impact on LWO when we suspend First Teeth
	 *check the Impact on LWO when we Complete the WorkDone of second Teeth and also take full payment
	 *check the Impact on LWO when we reopen the suspended Trt
	 *
	 */
	@Test(enabled=true, priority=2)
	public void CheckSuspendRelatedAnotherScenerioOnLWO() {
		extentReport.logger.log(LogStatus.PASS, CHECK_SUSPEND_RELATED_ANOTHER_SCENARIO_ON_LAB_WORK_ORDER);
		basePatientLifeCyclePage.clickOnAlert();
		patientDashboardPage.clickOnTreatmentPlanAddBtn();
		basePatientLifeCyclePage.clickOnAlert();
		treatmentPlansPage.clickOnNewTeethBtn();
		oralExamsPage.selectMultipleTeeth("Adult",ReadExcelData.readExcelData(FILE_PATH, SHEET, 3,4 ));
		oralExamsPage.clickOnTeethImage("Adult",ReadExcelData.readExcelData(FILE_PATH, SHEET, 4,4 ));
		treatmentPlansPage.clickOnYes();
		treatmentPlansPage.clickOnBoneGraftAndMembranes();
		treatmentPlansPage.clickOnSearchField(ReadExcelData.readExcelData(FILE_PATH, SHEET, 19,1 ));
		treatmentPlansPage.clickOnTreatments(ReadExcelData.readExcelData(FILE_PATH, SHEET, 19,1 ));
		treatmentPlansPage.saveTreatment();
		treatmentPlansPage.clickOnSaveBtnTreatmentInputList();
		treatmentPlanListingPage.verifyStartBtn(TODAY_DATE);
		treatmentPlanListingPage.selectTreatmentInTreatmentListPage(ReadExcelData.readExcelData(FILE_PATH, SHEET, 19,2 ));
		treatmentPlanListingPage.clickOnStartBtn(TODAY_DATE);
		basePatientLifeCyclePage.headerOnAddPage("Works Done");
		workDonePage.clickLWOBtnWD();
		basePatientLifeCyclePage.headerOnAddPage("Lab Work Order Listing");
		labWorkOrderListingPage.clickOnEdit(ReadExcelData.readExcelData(FILE_PATH, SHEET, 3,4));
		labWorkOrderEditPage.selectLab(ReadExcelData.readExcelData(FILE_PATH, SHEET, 2,0));
		labWorkOrderEditPage.clickOnItemSend();
		labWorkOrderEditPage.selectItemsSend(ReadExcelData.readExcelData(FILE_PATH, SHEET, 2,5));
		labWorkOrderEditPage.addFile("D:\\Clone\\prmautomation\\PatientFiles\\"+ReadExcelData.readExcelData(FILE_PATH, SHEET, 2,21));
		labWorkOrderEditPage.checkedAdditionalDetailPanel();
		labWorkOrderEditPage.selectRequire(ReadExcelData.readExcelData(FILE_PATH, SHEET, 2,10));
		labWorkOrderEditPage.selectShade(ReadExcelData.readExcelData(FILE_PATH, SHEET, 2,11));
		labWorkOrderEditPage.selectShadeOptionFromDrpDwn(ReadExcelData.readExcelData(FILE_PATH, SHEET, 2,12));
		labWorkOrderEditPage.selectStainsInternal(ReadExcelData.readExcelData(FILE_PATH, SHEET, 2,13));
		labWorkOrderEditPage.selectStainsExternal(ReadExcelData.readExcelData(FILE_PATH, SHEET, 2,14));
		labWorkOrderEditPage.addTransluency(ReadExcelData.readExcelData(FILE_PATH, SHEET, 2,15));
		labWorkOrderEditPage.addTexture(ReadExcelData.readExcelData(FILE_PATH, SHEET, 2,16));
		labWorkOrderEditPage.selectConfigurations(ReadExcelData.readExcelData(FILE_PATH, SHEET, 2,17), ReadExcelData.readExcelData(FILE_PATH, SHEET, 2,18));
		labWorkOrderEditPage.enterNotes(ReadExcelData.readExcelData(FILE_PATH, SHEET, 2,20));
		labWorkOrderEditPage.clickSubmittedToLab();
		labWorkOrderListingPage.verifySuccessfullMessage();
		basePatientLifeCyclePage.headerOnAddPage("Lab Work Order Listing");
		basePatientLifeCyclePage.clickOnDashBoard();
		basePatientLifeCyclePage.clickOnAlert();
		patientDashboardPage.clickOnWorkDoneAdd();
		basePatientLifeCyclePage.headerOnAddPage("Works Done");
		workDonePage.selectTime(ReadExcelData.readExcelData(FILE_PATH, SHEET, 19, 2), "15");
		workDonePage.enterRemarks(ReadExcelData.readExcelData(FILE_PATH, SHEET, 19, 2), "Change to complete");
		workDonePage.selectStages(ReadExcelData.readExcelData(FILE_PATH, SHEET, 19, 2), "Completed");
		workDonePage.clickOnAdd(ReadExcelData.readExcelData(FILE_PATH, SHEET, 19, 2));
		basePatientLifeCyclePage.headerOnAddPage("Work Done History");
		workDoneHistoryPage.clickAddNewBtn();
		basePatientLifeCyclePage.headerOnAddPage("Works Done");
		workDonePage.clickSuspendBtn(ReadExcelData.readExcelData(FILE_PATH, SHEET, 4,4));
		workDonePage.selectRequire("Assuring for refund.");
		workDonePage.clickSaveBtnOnReasonPopUp();
		basePatientLifeCyclePage.headerOnAddPage("Work Done History");
		basePatientLifeCyclePage.clickOnDashBoard();
		basePatientLifeCyclePage.clickOnAlert();
		patientDashboardPage.clickOnInvoiceList();
		invoiceListingPage.headerPage("Invoice Listing");
//		invoiceListingPage.clickInvoicePaymentBtn();
//		newReceiptPage.headerNewReceipt("New Receipt");
//		newReceiptPage.clickPaymentByInvoice();
//		newReceiptPage.collectByInvoiceNewReceipts(2180);
//		newReceiptPage.selectPaymentMode("Cash");
//		newReceiptPage.saveNewReceipts();
		basePatientLifeCyclePage.clickOnDashBoard();
		basePatientLifeCyclePage.clickOnAlert();
		patientDashboardPage.clickOnWorkDoneHistory();
		basePatientLifeCyclePage.headerOnAddPage("Work Done History");
		workDoneHistoryPage.clickReOpenBtn(ReadExcelData.readExcelData(FILE_PATH, SHEET, 4,4));
		workDoneHistoryPage.selectRequire("Patient returned back for the treatment.");
		workDoneHistoryPage.clickSaveBtnOnReasonPopUp();
		workDonePage.clickLWOBtnWD();
		basePatientLifeCyclePage.headerOnAddPage("Lab Work Order Listing");
		labWorkOrderListingPage.checkSourceForSuspAndRetreatMainList(ReadExcelData.readExcelData(FILE_PATH, SHEET, 4,4));
		labWorkOrderListingPage.checkDateTimeMainList(ReadExcelData.readExcelData(FILE_PATH, SHEET, 4,4), START_DATE_TIME, END_DATE_TIME);
		labWorkOrderListingPage.checkSourceMainList(ReadExcelData.readExcelData(FILE_PATH, SHEET, 4,4));
		labWorkOrderListingPage.checkRevisionReasonMainList(ReadExcelData.readExcelData(FILE_PATH, SHEET, 4,4), ReadExcelData.readExcelData(FILE_PATH, SHEET, 1,7), ReadExcelData.readExcelData(FILE_PATH, SHEET, 1,8));
		labWorkOrderListingPage.checkStatusDateMainList(ReadExcelData.readExcelData(FILE_PATH, SHEET, 4,4), ReadExcelData.readExcelData(FILE_PATH, SHEET, 1,9), START_DATE_TIME, END_DATE_TIME);
		labWorkOrderListingPage.clickOnView(ReadExcelData.readExcelData(FILE_PATH, SHEET, 4,4));
		labWorkOrderListingPage.verifyHeaderInView("Lab Work Order");
		labWorkOrderListingPage.checkSourceViewList(ReadExcelData.readExcelData(FILE_PATH, SHEET, 4,4), ReadExcelData.readExcelData(FILE_PATH, SHEET, 4,4));
		labWorkOrderListingPage.checkStatusEnableInView(ReadExcelData.readExcelData(FILE_PATH, SHEET, 1,9));
		labWorkOrderListingPage.verifyStatusInView(ReadExcelData.readExcelData(FILE_PATH, SHEET, 1,9), START_DATE_TIME, END_DATE_TIME);
		labWorkOrderListingPage.clickViewScreenClose();
		Assert.assertTrue(basePage.verification().contains("Lab Work Order Listing"));
	}

	/*
	 *check the Impact on LWO when we start bridgeable enable Trt
	 */
	@Test(enabled=true, priority=3)
	public void checkbridgeableRelatedScenerio() {
		extentReport.logger.log(LogStatus.PASS, CHECK_BRIDGEABLE_RELATED_SCENARIO);
		basePatientLifeCyclePage.clickOnAlert();
		patientDashboardPage.clickOnTreatmentPlanAddBtn();
		basePatientLifeCyclePage.clickOnAlert();
		treatmentPlansPage.clickOnNewTeethBtn();
		oralExamsPage.selectMultipleTeeth("Adult",ReadExcelData.readExcelData(FILE_PATH, SHEET, 5,4 ));
		oralExamsPage.clickOnTeethImage("Adult",ReadExcelData.readExcelData(FILE_PATH, SHEET, 6,4 ));
		treatmentPlansPage.clickOnYes();
		treatmentPlansPage.clickOnProsthoExceptCrowns();
		treatmentPlansPage.clickOnSearchField(ReadExcelData.readExcelData(FILE_PATH, SHEET, 21,1 ));
		treatmentPlansPage.clickOnTreatments(ReadExcelData.readExcelData(FILE_PATH, SHEET, 21,1 ));
		treatmentPlansPage.clickPartOfBridge();
		treatmentPlansPage.checkTreatmentsIsSelectedAsBridgeable(ReadExcelData.readExcelData(FILE_PATH, SHEET, 21,1 ));
		treatmentPlansPage.saveTreatment();
		treatmentPlansPage.clickOnSaveBtnTreatmentInputList();
		treatmentPlanListingPage.verifyStartBtn(TODAY_DATE);
		treatmentPlanListingPage.selectTreatmentInTreatmentListPage(ReadExcelData.readExcelData(FILE_PATH, SHEET, 21,2 ));
		treatmentPlanListingPage.clickOnStartBtn(TODAY_DATE);
		basePatientLifeCyclePage.headerOnAddPage("Works Done");
		workDonePage.clickLWOBtnWD();
		basePatientLifeCyclePage.headerOnAddPage("Lab Work Order Listing");
		labWorkOrderListingPage.clickOnView(ReadExcelData.readExcelData(FILE_PATH, SHEET, 5,4 ));
		labWorkOrderListingPage.verifyHeaderInView("Lab Work Order");
		labWorkOrderListingPage.checkStatusEnableInView(ReadExcelData.readExcelData(FILE_PATH, SHEET, 1,9 ));
		labWorkOrderListingPage.verifyStatusInView(ReadExcelData.readExcelData(FILE_PATH, SHEET, 1,9 ), START_DATE_TIME, END_DATE_TIME);
		labWorkOrderListingPage.verifyTreatmentNameView(ReadExcelData.readExcelData(FILE_PATH, SHEET, 21,1), 2);
		labWorkOrderListingPage.clickViewScreenClose();
		Assert.assertTrue(basePage.verification().contains("Lab Work Order Listing"));
	}

	/*
	 *check the Impact on LWO when we start Arch enable Trt
	 */
	@Test(enabled=true, priority=4)
	public void checkByArchRelatedScenario() {
		extentReport.logger.log(LogStatus.PASS, CHECK_BY_ARCH_RELATED_SCENARIO);
		basePatientLifeCyclePage.clickOnAlert();
		patientDashboardPage.clickOnTreatmentPlanAddBtn();
		basePatientLifeCyclePage.clickOnAlert();
		treatmentPlansPage.clickOnNewTeethBtn();
		treatmentPlansPage.clickOnByArch();
		oralExamsPage.clickOnTeethImage("Adult",ReadExcelData.readExcelData(FILE_PATH, SHEET, 6,4 ));
		treatmentPlansPage.clickOnProsthoExceptCrowns();
		treatmentPlansPage.clickOnSearchField(ReadExcelData.readExcelData(FILE_PATH, SHEET, 22,1 ));
		treatmentPlansPage.clickOnTreatments(ReadExcelData.readExcelData(FILE_PATH, SHEET, 22,1 ));
		treatmentPlansPage.saveTreatment();
		treatmentPlansPage.clickOnSaveBtnTreatmentInputList();
		treatmentPlanListingPage.verifyStartBtn(TODAY_DATE);
		treatmentPlanListingPage.selectTreatmentInTreatmentListPage(ReadExcelData.readExcelData(FILE_PATH, SHEET, 22,2 ));
		treatmentPlanListingPage.clickOnStartBtn(TODAY_DATE);
		basePatientLifeCyclePage.headerOnAddPage("Works Done");
		workDonePage.clickLWOBtnWD();
		basePatientLifeCyclePage.headerOnAddPage("Lab Work Order Listing");
		labWorkOrderListingPage.checkClinicNameMainList(ReadExcelData.readExcelData(FILE_PATH, SHEET, 7,4 ),CLINIC_NAME);
		labWorkOrderListingPage.checkLabNameMainList(ReadExcelData.readExcelData(FILE_PATH, SHEET, 7,4 ), ReadExcelData.readExcelData(FILE_PATH, SHEET, 1,0 ));
		labWorkOrderListingPage.checkDateMainList(ReadExcelData.readExcelData(FILE_PATH, SHEET, 7,4),TODAY_DATE );
		labWorkOrderListingPage.checkOrderTypeMainList(ReadExcelData.readExcelData(FILE_PATH, SHEET, 7,4 ),ReadExcelData.readExcelData(FILE_PATH, SHEET, 1,1 ));
		labWorkOrderListingPage.checkLabWorkOrderNo(ReadExcelData.readExcelData(FILE_PATH, SHEET, 7,4));
		labWorkOrderListingPage.checkDateTimeMainList(ReadExcelData.readExcelData(FILE_PATH, SHEET, 7,4), START_DATE_TIME, END_DATE_TIME);
		labWorkOrderListingPage.checkWorkDetailsMainlist(ReadExcelData.readExcelData(FILE_PATH, SHEET, 7,4),ReadExcelData.readExcelData(FILE_PATH, SHEET, 4,2));
		labWorkOrderListingPage.checkSourceMainList(ReadExcelData.readExcelData(FILE_PATH, SHEET, 7,4));
		labWorkOrderListingPage.checkDoctorMainList(ReadExcelData.readExcelData(FILE_PATH, SHEET, 7,4),DOCTOR_FIRST_NAME);
		labWorkOrderListingPage.checkItemSentMainList(ReadExcelData.readExcelData(FILE_PATH, SHEET, 7,4),ReadExcelData.readExcelData(FILE_PATH, SHEET, 1,5));
		labWorkOrderListingPage.checkRevisionReasonMainList(ReadExcelData.readExcelData(FILE_PATH, SHEET, 7,4),ReadExcelData.readExcelData(FILE_PATH, SHEET, 1,7),ReadExcelData.readExcelData(FILE_PATH, SHEET, 1,8));
		labWorkOrderListingPage.checkStatusDateMainList(ReadExcelData.readExcelData(FILE_PATH, SHEET, 7,4),ReadExcelData.readExcelData(FILE_PATH, SHEET, 1,9), START_DATE_TIME, END_DATE_TIME);
		labWorkOrderListingPage.clickOnView(ReadExcelData.readExcelData(FILE_PATH, SHEET, 7,4));
		labWorkOrderListingPage.checkedViewDataName();
		labWorkOrderListingPage.verifyHeaderInView("Lab Work Order");
		labWorkOrderListingPage.verifyPatientNameView(PATIENT_NAME);
		labWorkOrderListingPage.verifyLabWorkOrderNoView();
		labWorkOrderListingPage.verifyDateTimeInView(START_DATE_TIME, END_DATE_TIME);
		labWorkOrderListingPage.checkLabNameViewList(ReadExcelData.readExcelData(FILE_PATH, SHEET, 1,0));
		labWorkOrderListingPage.checkOrderTypeViewList(ReadExcelData.readExcelData(FILE_PATH, SHEET, 1,1));
		labWorkOrderListingPage.checkWorkDetailViewList(ReadExcelData.readExcelData(FILE_PATH, SHEET, 4,2));
		labWorkOrderListingPage.checkOtherInfoViewList(ReadExcelData.readExcelData(FILE_PATH, SHEET, 1,3));
		labWorkOrderListingPage.checkSourceViewList(ReadExcelData.readExcelData(FILE_PATH, SHEET, 7,4),ReadExcelData.readExcelData(FILE_PATH, SHEET, 1,4));
		labWorkOrderListingPage.checkItemSentViewList(ReadExcelData.readExcelData(FILE_PATH, SHEET, 1,5));
		labWorkOrderListingPage.checkRequireViewList(ReadExcelData.readExcelData(FILE_PATH, SHEET, 1,10));
		labWorkOrderListingPage.checkShadeViewList(ReadExcelData.readExcelData(FILE_PATH, SHEET, 1,11),ReadExcelData.readExcelData(FILE_PATH, SHEET, 1,11));
		labWorkOrderListingPage.checkStainsInternalViewList(ReadExcelData.readExcelData(FILE_PATH, SHEET, 1,13));
		labWorkOrderListingPage.checkStainsExternalViewList(ReadExcelData.readExcelData(FILE_PATH, SHEET, 1,14));
		labWorkOrderListingPage.checkTranslucencyViewList(ReadExcelData.readExcelData(FILE_PATH, SHEET, 1,15));
		labWorkOrderListingPage.checkTextureViewList(ReadExcelData.readExcelData(FILE_PATH, SHEET, 1,16));
		labWorkOrderListingPage.checkConfigurationsViewList(ReadExcelData.readExcelData(FILE_PATH, SHEET, 1,17),ReadExcelData.readExcelData(FILE_PATH, SHEET, 1,17));
		labWorkOrderListingPage.checkSurfaceDetailsViewList(ReadExcelData.readExcelData(FILE_PATH, SHEET, 1,19),"NA");
		labWorkOrderListingPage.checkNotesViewList(ReadExcelData.readExcelData(FILE_PATH, SHEET, 1,20));
		labWorkOrderListingPage.checkStatusEnableInView(ReadExcelData.readExcelData(FILE_PATH, SHEET, 1,9));
		labWorkOrderListingPage.verifyStatusInView(ReadExcelData.readExcelData(FILE_PATH, SHEET, 1,9),START_DATE_TIME, END_DATE_TIME);
		labWorkOrderListingPage.verifyTreatmentNameView(ReadExcelData.readExcelData(FILE_PATH, SHEET, 22,1),1);
		labWorkOrderListingPage.clickViewScreenClose();
		Assert.assertTrue(basePage.verification().contains("Lab Work Order Listing"));
	}

	@AfterMethod(description = STATUS_OF_SCRIPTS , enabled = true)
	public void statusOfScripts(ITestResult result) {
		extentReport.onTestFailure(result);
		extentReport.logger.log(LogStatus.PASS, STATUS_OF_SCRIPTS );
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
