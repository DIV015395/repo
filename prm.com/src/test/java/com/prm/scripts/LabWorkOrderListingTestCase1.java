package com.prm.scripts;

import com.prm.pageobjects.utils.TestData;
import com.prm.patientdashboard.pageobject.*;

import java.io.IOException;
import java.security.GeneralSecurityException;
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
import com.prm.pageobjects.utils.ExtentReport;
import com.prm.pageobjects.utils.ReadConfig;
import com.prm.pageobjects.utils.ReadExcelData;
import com.prm.pageobjects.utils.SheetTest;
import com.relevantcodes.extentreports.LogStatus;

public class LabWorkOrderListingTestCase1 {

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
	 * Storing mandatory input required to run LWO test cases,make sure you
	 * update the mandatory input before running the test cases
	 *
	 */
	private static final String TRT_FILE_PATH = TestData.getInstance().getInputData("treatment_plan_file_path");
	private static final String FILE_PATH = TestData.getInstance().getInputData("lab_work_order1_file_path");
	private static final String SHEET = TestData.getInstance().getInputData("lab_work_order1_file_sheet_name");
	private static final String TRT_SHEET = TestData.getInstance().getInputData("treatment_plan_file_sheet_name");
	private static final String PATIENT_NAME= TestData.getInstance().getInputData("lab_work_order1_patient_name");
	private static final String MOBILE_NUMBER = TestData.getInstance().getInputData("lab_work_order1_patient_mobile");
	private static final String DOCTOR_NAME = TestData.getInstance().getInputData("lab_work_order1_doctor_name");
	private static final String DOCTOR_FIRST_NAME = TestData.getInstance().getInputData("lab_work_order1_doctor_first_name");
	private static final String CLINIC_NAME = TestData.getInstance().getInputData("lab_work_order1_clinic_name");
	private static final String INSTANCE = TestData.getInstance().getInputData("lab_work_order1_instance");
	private static final String TODAY_DATE=TestData.getInstance().getTodayDate();
	private static final String START_DATE_TIME=TestData.getInstance().getFewMinuteBackFromCurrentDateTime();
	private static final String END_DATE_TIME=TestData.getInstance().getFewMinuteAfterFromCurrentDateTime();

	/*
	 * Messages for every test cases which will be print on the extent report
	 */
	private static final String SCRIPTS_STARTED_MSG  = "@@ Test scripts has been started here @@";
	private static final String STATUS_OF_SCRIPTS  = "@@ Test script has been completed @@";
	private static final String CHECKED_LAB_WORK_ORDER_LIST_UI = "Validate Lab Work Order List for the First time -#CheckedLabWorkOrderListUi";
	private static final String CHECK_LAB_WORK_ORDER_AFTER_TREATMENT_START = "Checked LWO after we start Treatment on all screen View and listing -#checkLabWorkOrderAfterTreatmentStart";
	private static final String CHECK_LAB_WORK_ORDER_ON_EDIT_AFTER_TREATMENT_START = "Checked LWO on Edit after we start Treatment -#CheckLabWorkOrderOnEditAfterTreatmentStart";
	private static final String CHECK_AFTER_ENTER_DATA_IN_LAB_WORK_ORDER_EDIT_PAGE = "Enter data in LWO edit screen and Checked on List and View Screen -#checkAfterEnterDataInLabWorkOrderEditPage";
	private static final String CLICK_SUBMITTED_TO_LAB_OF_ABOVE_LAB_WORK_ORDER = "Clicked Above LWO as Submitted to Lab and Checked On View and list -#clickSubmittedToLabOfAboveLabWorkOrder";
	private static final String COMPLETE_TRT_AND_CHECK_LAB_WORK_ORDER_STATUS = "Completed Trt from WorkDone And Check status on List and View of LWO -#completeTrtAndCheckLWOStatus";
	private static final String COMPLETE_PAYMENT_AND_CHECK_LAB_WORK_ORDER_STATUS = "Completed Payment from Invoice And Check status on List and View of LWO -#completePaymentAndCheckLWOStatus";
	private static final String RETREAT_ABOVE_COMPLETE_TRT_AND_CHECK_LAB_WORK_ORDER_STATUS = "ReTreat Above Completed Trt from WorkDone And Check status on List and View of LWO -#reTreatAboveCompleteTrtAndCheckLWOStatus";
	private static final String COMPLETE_ABOVE_RETREAT_TRT_AND_CHECK_LAB_WORK_ORDER_STATUS = "Completed Above ReTreat Trt from WorkDone And Check status on List and View of LWO -#CompleteAboveReTreatTrtAndCheckLWOStatus";

	/*
     * Module and Sheet Name for getting Data from Google sheet
     */
    final String MODULE_NAME="LWO";
    final String GOOGLE_SHEET_NAME="LWOTestData";
    final String Product_SALE_PAGE_TITLE="Product Sales";
    
    /*
	 * Google Sheet Test Data in Map
	 */
	private Map<String,String>addLWOInDraftMode=SheetTest.prepareData(MODULE_NAME, GOOGLE_SHEET_NAME, "A2", "AB2");
	private Map<String,String> addLWOInSubmittedToLabMode=null;
	private Map<String,String> addLWOInPaymentCompletedMode=null;
	private Map<String,String> addLWOInTreatmentCompletedMode=null;
	private Map<String,String> addLWOInPayableToLabMode=null;
	private Map<String,String> addLWOInPayable=null;
	private Map<String,String> addLWOInPaymentResubmitted=null;
	private Map<String,String> addLWOInPaymentTrtComplete=null;
	private Map<String,String> addLWOInPaymentTrtCompleted=null;
	private Map<String,String> addTrtForCrown=null;
	private Map<String,String> addTrtForCrownPFM=null;
	private Map<String,String> addTrtForCrownFullMetal=null;
	private Map<String,String> addTrtForCrownMetal=null;
	
	
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
		if(patientDashboardPage.dueWarningPopup.size()>0){
			patientDashboardPage.hideDueWarningPopup();
		}else {
			basePatientLifeCyclePage.clickOnAlert();
		}
	}

	/*
	 * check header of LWO listing page
	 * check LWO listing page without any LWO when we redirect for the first time
	 * check the patient name and id should not be "null" or "NA"
	 * check patient dashboard button and no record message displayed redirect for first time
	 * check left side navigation all button on LWO listing page
	 */
	@Test(enabled= true, priority = 1)
	public void checkedLabWorkOrderListUi() {
		extentReport.logger.log(LogStatus.PASS, CHECKED_LAB_WORK_ORDER_LIST_UI);
		basePatientLifeCyclePage.clickOnAlert();
		patientDashboardPage.clickOnLabWorkOrderAdd();
		basePatientLifeCyclePage.openCloseLeftNavigator();
		basePatientLifeCyclePage.webElementOfLeftNavigator();
		basePatientLifeCyclePage.openCloseLeftNavigator();
		basePatientLifeCyclePage.headerOnAddPage("Lab Work Order Listing");
		basePatientLifeCyclePage.dashBoardBtnVerify();
		basePatientLifeCyclePage.verifyPatientName(PATIENT_NAME);
		basePatientLifeCyclePage.noRecordMsgDisplayed();
		Assert.assertTrue(basePage.verification().contains("Lab Work Order Listing"));

		/*
		 * Start LWO enable Trt.
		 * check msg when we complete the Trt from Workdone when LWO have status as Draft
		 * check LWO listing and view screen after Trt start
		 */
	}
	@Test(enabled=true, priority=2)
	public void checkLabWorkOrderAfterTreatmentStart() {
		extentReport.logger.log(LogStatus.PASS, CHECK_LAB_WORK_ORDER_AFTER_TREATMENT_START);
		patientDashboardPage.clickOnAppAdd();
		appointmentPage.selectDoctorFromDropdown(DOCTOR_NAME);
		appointmentPage.clickOnSaveBtn();
		basePatientLifeCyclePage.clickOnAlert();
		patientDashboardPage.clickOnTreatmentPlanAddBtn();
		basePatientLifeCyclePage.clickOnAlert();
		treatmentPlansPage.clickOnNewTeethBtn();
		oralExamsPage.clickOnTeethImage("Adult",addLWOInDraftMode.get("source"));
		treatmentPlansPage.verifySeletecdTeethOnPopup(addLWOInDraftMode.get("source"));
		treatmentPlansPage.clickOnCrowns();
		treatmentPlansPage.clickOnSearchField(addLWOInDraftMode.get("plan_short_name"));
		treatmentPlansPage.clickOnTreatments(addLWOInDraftMode.get("plan_short_name"));
		treatmentPlansPage.saveTreatment();
		treatmentPlansPage.clickOnSaveBtnTreatmentInputList();
		treatmentPlanListingPage.verifyStartBtn(TODAY_DATE);
		treatmentPlanListingPage.selectTreatmentInTreatmentListPage(addLWOInDraftMode.get("plan_full_name"));
		treatmentPlanListingPage.clickOnStartBtn(TODAY_DATE); 
		basePatientLifeCyclePage.headerOnAddPage("Works Done");
		workDonePage.selectTime(addLWOInDraftMode.get("plan_full_name"), "15");
		workDonePage.enterRemarks(addLWOInDraftMode.get("plan_full_name"), "Change to complete");
		workDonePage.selectStages(addLWOInDraftMode.get("plan_full_name"), "Cementation");
		workDonePage.clickOnAdd(addLWOInDraftMode.get("plan_full_name"));
		workDonePage.verifyLWOActionMessage();
		workDonePage.clickLWOBtnWD();
		basePatientLifeCyclePage.headerOnAddPage("Lab Work Order Listing");
		basePatientLifeCyclePage.verifyPatientName(PATIENT_NAME);
		labWorkOrderListingPage.checkClinicNameMainList(addLWOInDraftMode.get("source"),CLINIC_NAME);
		labWorkOrderListingPage.checkLabNameMainList(addLWOInDraftMode.get("source"), addLWOInDraftMode.get("lab_name"));
		labWorkOrderListingPage.checkDateMainList(addLWOInDraftMode.get("source"),TODAY_DATE );
		labWorkOrderListingPage.checkOrderTypeMainList(addLWOInDraftMode.get("source"),addLWOInDraftMode.get("order_type"));
		labWorkOrderListingPage.checkLabWorkOrderNo(addLWOInDraftMode.get("source"));
		labWorkOrderListingPage.checkDateTimeMainList(addLWOInDraftMode.get("source"), START_DATE_TIME, END_DATE_TIME);
		labWorkOrderListingPage.checkWorkDetailsMainlist(addLWOInDraftMode.get("source"),addLWOInDraftMode.get("work_details"));
		labWorkOrderListingPage.checkSourceMainList(addLWOInDraftMode.get("source"));
		labWorkOrderListingPage.checkDoctorMainList(addLWOInDraftMode.get("source"),DOCTOR_FIRST_NAME);
		labWorkOrderListingPage.checkItemSentMainList(addLWOInDraftMode.get("source"),addLWOInDraftMode.get("item_sent"));
		labWorkOrderListingPage.checkRevisionReasonMainList(addLWOInDraftMode.get("source"),addLWOInDraftMode.get("revision"),addLWOInDraftMode.get("reason"));
		labWorkOrderListingPage.checkStatusDateMainList(addLWOInDraftMode.get("source"),addLWOInDraftMode.get("status"), START_DATE_TIME, END_DATE_TIME);
		labWorkOrderListingPage.clickOnView(addLWOInDraftMode.get("source"));
		labWorkOrderListingPage.checkedViewDataName();
		labWorkOrderListingPage.verifyHeaderInView("Lab Work Order");
		labWorkOrderListingPage.verifyPatientNameView(PATIENT_NAME);
		labWorkOrderListingPage.verifyLabWorkOrderNoView();
		labWorkOrderListingPage.verifyDateTimeInView(START_DATE_TIME, END_DATE_TIME);
		labWorkOrderListingPage.checkLabNameViewList(addLWOInDraftMode.get("lab_name"));
		labWorkOrderListingPage.checkOrderTypeViewList(addLWOInDraftMode.get("order_type"));
		labWorkOrderListingPage.checkWorkDetailViewList(addLWOInDraftMode.get("work_details"));
		labWorkOrderListingPage.checkOtherInfoViewList(addLWOInDraftMode.get("other_info"));
		labWorkOrderListingPage.checkSourceViewList(addLWOInDraftMode.get("source"),addLWOInDraftMode.get("source"));
		labWorkOrderListingPage.checkItemSentViewList(addLWOInDraftMode.get("item_sent"));
		labWorkOrderListingPage.checkRequireViewList(addLWOInDraftMode.get("require"));
		labWorkOrderListingPage.checkShadeViewList(addLWOInDraftMode.get("shade"),addLWOInDraftMode.get("shade_option"));
		labWorkOrderListingPage.checkStainsInternalViewList(addLWOInDraftMode.get("stains_internal"));
		labWorkOrderListingPage.checkStainsExternalViewList(addLWOInDraftMode.get("stains_external"));
		labWorkOrderListingPage.checkTranslucencyViewList(addLWOInDraftMode.get("translucency"));
		labWorkOrderListingPage.checkTextureViewList(addLWOInDraftMode.get("texture"));
		labWorkOrderListingPage.checkConfigurationsViewList(addLWOInDraftMode.get("configurations"),addLWOInDraftMode.get("configurations_opt"));
		labWorkOrderListingPage.checkSurfaceDetailsViewList("NA","NA");
		labWorkOrderListingPage.checkNotesViewList(addLWOInDraftMode.get("notes"));
		labWorkOrderListingPage.checkStatusEnableInView(addLWOInDraftMode.get("status"));
		labWorkOrderListingPage.verifyStatusInView(addLWOInDraftMode.get("status"),START_DATE_TIME, END_DATE_TIME);
		labWorkOrderListingPage.verifyTreatmentNameView(addLWOInDraftMode.get("plan_short_name"),1);
		labWorkOrderListingPage.clickViewScreenClose();
		Assert.assertTrue(basePage.verification().contains("Lab Work Order Listing"));
	}

	/*
	 * check LWO edit page after Trt start
	 * check all button shown on LWO edit page
	 */
	@Test(enabled=true, priority=3)
	public void CheckLabWorkOrderOnEditAfterTreatmentStart() {
		extentReport.logger.log(LogStatus.PASS, CHECK_LAB_WORK_ORDER_ON_EDIT_AFTER_TREATMENT_START);
		basePatientLifeCyclePage.clickOnAlert();
		patientDashboardPage.clickOnLabWorkOrderAdd();
		labWorkOrderListingPage.clickOnEdit(addLWOInDraftMode.get("source"));
		labWorkOrderEditPage.verifyHeaderOfPage("Lab Work Order Edit");
		basePatientLifeCyclePage.verifyPatientName(PATIENT_NAME);
		labWorkOrderEditPage.checkedAllFieldsInWorkDetailPanel();
		labWorkOrderEditPage.clinicName(CLINIC_NAME);
		labWorkOrderEditPage.source(addLWOInDraftMode.get("source"));
		labWorkOrderEditPage.labWorkOrderNo();
		labWorkOrderEditPage.checkLabDropDwn(INSTANCE);
		labWorkOrderEditPage.checkOrderType(addLWOInDraftMode.get("order_type"));
		labWorkOrderEditPage.checkWorkDetails(addLWOInDraftMode.get("work_details"));
		labWorkOrderEditPage.checkOtherInfo(addLWOInSubmittedToLabMode.get("other_info"));
		labWorkOrderEditPage.checkItemSendDrpDwn();
		labWorkOrderEditPage.checkedAdditionalDetailPanel();
		labWorkOrderEditPage.checkedAllFieldsInAdditionalDetailPanel();
		labWorkOrderEditPage.checkRequireDropDwn();
		labWorkOrderEditPage.checkStainsInternalDropDwn();
		labWorkOrderEditPage.checkStainsExternalDropDwn();
		labWorkOrderEditPage.checkedTranslucencyTextureAndNotes();
		labWorkOrderEditPage.clickOnBuccal();
		labWorkOrderEditPage.verifyProvisinals();
		labWorkOrderEditPage.clickOnDistal();
		labWorkOrderEditPage.verifyProvisinals();
		labWorkOrderEditPage.clickOnMesial();
		labWorkOrderEditPage.verifyProvisinals();
		labWorkOrderEditPage.clickOnMesial();
		labWorkOrderEditPage.verifyProvisinals();
		labWorkOrderEditPage.checkedActionButtonInEdit();
		labWorkOrderEditPage.clickOnCancelBtn();
		Assert.assertTrue(basePage.verification().contains("Lab Work Order Listing"));
	}

	/*
	 * Enter data into all fields shown in LWO edit Page
	 * check all data in LWO listing and View Screen after data added
	 * click on save as Draft.
	 */
	@Test(enabled=true, priority=4)
	public void checkAfterEnterDataInLabWorkOrderEditPage() {
		extentReport.logger.log(LogStatus.PASS, CHECK_AFTER_ENTER_DATA_IN_LAB_WORK_ORDER_EDIT_PAGE);
		basePatientLifeCyclePage.clickOnAlert();
		patientDashboardPage.clickOnLabWorkOrderAdd();
		labWorkOrderListingPage.clickOnEdit(addLWOInDraftMode.get("source"));
		labWorkOrderEditPage.verifyHeaderOfPage("Lab Work Order Edit");
		labWorkOrderEditPage.selectLab(addLWOInSubmittedToLabMode.get("lab_name"));
		labWorkOrderEditPage.clickOnItemSend();
		labWorkOrderEditPage.selectItemsSend(addLWOInSubmittedToLabMode.get("item_sent"));
		labWorkOrderEditPage.selectItemsSend(addLWOInPaymentCompletedMode.get("item_sent"));
		labWorkOrderEditPage.addFile(System.getProperty("user.dir")+"\\PatientFiles\\"+addLWOInSubmittedToLabMode.get("attach_name"));
		labWorkOrderEditPage.checkedAdditionalDetailPanel();
		labWorkOrderEditPage.selectRequire(addLWOInSubmittedToLabMode.get("require"));
		labWorkOrderEditPage.selectShade(addLWOInSubmittedToLabMode.get("shade"));
		labWorkOrderEditPage.selectShadeOptionFromDrpDwn(addLWOInSubmittedToLabMode.get("shade_option"));
		labWorkOrderEditPage.selectStainsInternal(addLWOInSubmittedToLabMode.get("stains_internal"));
		labWorkOrderEditPage.selectStainsExternal(addLWOInSubmittedToLabMode.get("stains_external"));
		labWorkOrderEditPage.addTransluency(addLWOInSubmittedToLabMode.get("translucency"));
		labWorkOrderEditPage.addTexture(addLWOInSubmittedToLabMode.get("texture"));
		labWorkOrderEditPage.selectConfigurations(addLWOInSubmittedToLabMode.get("configurations"), addLWOInSubmittedToLabMode.get("configurations_opt"));
		labWorkOrderEditPage.clickOnBuccal();
		labWorkOrderEditPage.clickOnToothSiteAndPerioProvisinals();
		labWorkOrderEditPage.verifyProvisinalIsSelected("Buccal");
		labWorkOrderEditPage.selectedToothAndSiteProvisionalDisplayedBelow();
		labWorkOrderEditPage.clickOnDistal();
		labWorkOrderEditPage.clickOnToothSiteAndPerioProvisinals();
		labWorkOrderEditPage.verifyProvisinalIsSelected("Distal");
		labWorkOrderEditPage.selectedToothAndSiteProvisionalDisplayedBelow();
		labWorkOrderEditPage.clickOnMesial();
		labWorkOrderEditPage.clickOnToothSiteAndPerioProvisinals();
		labWorkOrderEditPage.verifyProvisinalIsSelected("Mesial");
		labWorkOrderEditPage.selectedToothAndSiteProvisionalDisplayedBelow();
		labWorkOrderEditPage.clickOnLingual();
		labWorkOrderEditPage.clickOnToothSiteAndPerioProvisinals();
		labWorkOrderEditPage.verifyProvisinalIsSelected("Lingual");
		labWorkOrderEditPage.selectedToothAndSiteProvisionalDisplayedBelow();
		labWorkOrderEditPage.enterNotes(addLWOInSubmittedToLabMode.get("notes"));
		labWorkOrderEditPage.clickSaveAsDraft();
		labWorkOrderListingPage.verifySuccessfullMessage();
		basePatientLifeCyclePage.headerOnAddPage("Lab Work Order Listing");
		labWorkOrderListingPage.checkLabNameMainList(addLWOInDraftMode.get("source"), addLWOInSubmittedToLabMode.get("lab_name"));
		labWorkOrderListingPage.checkItemSentMainList(addLWOInDraftMode.get("source"), addLWOInSubmittedToLabMode.get("item_sent"));
		labWorkOrderListingPage.clickOnView(addLWOInDraftMode.get("source"));
		labWorkOrderListingPage.verifyHeaderInView("Lab Work Order");
		labWorkOrderListingPage.checkLabNameViewList(addLWOInSubmittedToLabMode.get("lab_name"));
		labWorkOrderListingPage.checkItemSentViewList(addLWOInSubmittedToLabMode.get("item_sent"));
		labWorkOrderListingPage.checkItemSentViewList(addLWOInPaymentCompletedMode.get("item_sent"));
		labWorkOrderListingPage.checkRequireViewList(addLWOInSubmittedToLabMode.get("require"));
		labWorkOrderListingPage.checkShadeViewList(addLWOInSubmittedToLabMode.get("shade"), addLWOInSubmittedToLabMode.get("shade_option"));
		labWorkOrderListingPage.checkStainsInternalViewList(addLWOInSubmittedToLabMode.get("stains_internal"));
		labWorkOrderListingPage.checkStainsExternalViewList(addLWOInSubmittedToLabMode.get("stains_external"));
		labWorkOrderListingPage.checkTranslucencyViewList(addLWOInSubmittedToLabMode.get("translucency"));
		labWorkOrderListingPage.checkTextureViewList(addLWOInSubmittedToLabMode.get("texture"));
		labWorkOrderListingPage.checkConfigurationsViewList(addLWOInSubmittedToLabMode.get("configurations"), addLWOInSubmittedToLabMode.get("configurations_opt"));
		labWorkOrderListingPage.checkSurfaceDetailsViewList("Buccal","Chamfer");
		labWorkOrderListingPage.checkSurfaceDetailsViewList("Distal","Chamfer");
		labWorkOrderListingPage.checkSurfaceDetailsViewList("Mesial","Chamfer");
		labWorkOrderListingPage.checkSurfaceDetailsViewList("Lingual","Chamfer");
		labWorkOrderListingPage.checkNotesViewList(addLWOInSubmittedToLabMode.get("notes"));
		labWorkOrderListingPage.checkAttachmentViewList(addLWOInSubmittedToLabMode.get("attach_name"));
		labWorkOrderListingPage.clickViewScreenClose();
		Assert.assertTrue(basePage.verification().contains("Lab Work Order Listing"));
	}

	/*
	 * Get all data from LWO edit page that have added above
	 * Click on Submitted to Lab
	 * Check status and LWO no. should not be Null or NA on LWO listing and view screen
	 * Check button in LWO listing page and also check successful msg shown in LWO listing page
	 * Check Revision detail in LWO View Screen
	 * Edit data and again Click on Submitted to Lab, check new Revision is entered.
	 * Also check InActive Revision
	 * Again check edited data in LWO listing and View Screen
	 */
	@Test(enabled=true, priority=5)
	public void clickSubmittedToLabOfAboveLabWorkOrder() {
		extentReport.logger.log(LogStatus.PASS, CLICK_SUBMITTED_TO_LAB_OF_ABOVE_LAB_WORK_ORDER);
		basePatientLifeCyclePage.clickOnAlert();
		patientDashboardPage.clickOnLabWorkOrderAdd();
		basePatientLifeCyclePage.headerOnAddPage("Lab Work Order Listing");
		labWorkOrderListingPage.clickOnEdit(addLWOInDraftMode.get("source"));
		labWorkOrderEditPage.verifyHeaderOfPage("Lab Work Order Edit");
		labWorkOrderEditPage.getLabDrpDwn(addLWOInSubmittedToLabMode.get("lab_name"));
		labWorkOrderEditPage.clickOnItemSend();
		labWorkOrderEditPage.getItemsSendDrpDwn(addLWOInSubmittedToLabMode.get("item_sent"));
		labWorkOrderEditPage.getItemsSendDrpDwn(addLWOInPaymentCompletedMode.get("item_sent"));
		labWorkOrderEditPage.getFile(addLWOInSubmittedToLabMode.get("attach_name"));
		labWorkOrderEditPage.checkedAdditionalDetailPanel();
		labWorkOrderEditPage.getRequireDrpDwn(addLWOInSubmittedToLabMode.get("require"));
		labWorkOrderEditPage.getShadeOptionDrpDwn(addLWOInSubmittedToLabMode.get("shade_option"));
		labWorkOrderEditPage.getStainsInternalDrpDwn(addLWOInSubmittedToLabMode.get("stains_internal"));
		labWorkOrderEditPage.getStainsExternalDrpDwn(addLWOInSubmittedToLabMode.get("stains_external"));
		labWorkOrderEditPage.getTranslucency(addLWOInSubmittedToLabMode.get("translucency"));
		labWorkOrderEditPage.getTexture(addLWOInSubmittedToLabMode.get("texture"));
		labWorkOrderEditPage.getNotes(addLWOInSubmittedToLabMode.get("notes"));
		labWorkOrderEditPage.clickSubmittedToLab();
		labWorkOrderListingPage.verifySuccessfullMessage();
		basePatientLifeCyclePage.headerOnAddPage("Lab Work Order Listing");
		labWorkOrderListingPage.checkStatusDateMainList(addLWOInDraftMode.get("source"), addLWOInSubmittedToLabMode.get("status"), START_DATE_TIME, END_DATE_TIME);
		labWorkOrderListingPage.checkLabWorkOrderNo(addLWOInDraftMode.get("source"));
		labWorkOrderListingPage.checkPrintBtn(addLWOInDraftMode.get("source"));
		labWorkOrderListingPage.clickOnView(addLWOInDraftMode.get("source"));
		labWorkOrderListingPage.verifyHeaderInView("Lab Work Order");
		labWorkOrderListingPage.verifyLabWorkOrderNoView();
		labWorkOrderListingPage.checkStatusEnableInView(addLWOInDraftMode.get("status"));
		labWorkOrderListingPage.checkStatusEnableInView(addLWOInSubmittedToLabMode.get("status"));
		labWorkOrderListingPage.verifyStatusInView(addLWOInDraftMode.get("status"), START_DATE_TIME, END_DATE_TIME);
		labWorkOrderListingPage.verifyStatusInView(addLWOInSubmittedToLabMode.get("status"), START_DATE_TIME, END_DATE_TIME);
		labWorkOrderListingPage.checkedRevisionDetailsDataNameView();
		labWorkOrderListingPage.checkRevisionNoViewList(addLWOInDraftMode.get("revision"));
		labWorkOrderListingPage.verifyRevisionDateInView(START_DATE_TIME, END_DATE_TIME);
		labWorkOrderListingPage.checkCurrentStatusViewList(addLWOInSubmittedToLabMode.get("status"));
		labWorkOrderListingPage.clickViewScreenClose();
		labWorkOrderListingPage.clickOnEdit(addLWOInDraftMode.get("source"));
		labWorkOrderEditPage.verifyHeaderOfPage("Lab Work Order Edit");
		labWorkOrderEditPage.deleteFile(addLWOInSubmittedToLabMode.get("attach_name"));
		labWorkOrderEditPage.clickOnYesDelete();
		labWorkOrderEditPage.selectLab(addLWOInPaymentCompletedMode.get("lab_name"));
		labWorkOrderEditPage.clickOnItemSend();
		labWorkOrderEditPage.selectItemsSend(addLWOInPaymentCompletedMode.get("item_sent"));
		labWorkOrderEditPage.addUpdateReason(addLWOInDraftMode.get("updation_reason"));
		labWorkOrderEditPage.checkedAdditionalDetailPanel();
		labWorkOrderEditPage.selectRequire(addLWOInPaymentCompletedMode.get("require"));
		labWorkOrderEditPage.selectShade(addLWOInDraftMode.get("shade"));
		labWorkOrderEditPage.addTransluency(addLWOInPaymentCompletedMode.get("translucency"));
		labWorkOrderEditPage.selectConfigurations(addLWOInPaymentCompletedMode.get("configurations"), addLWOInPaymentCompletedMode.get("configurations_opt"));
		labWorkOrderEditPage.enterNotes(addLWOInPaymentCompletedMode.get("notes"));
		labWorkOrderEditPage.clickSubmittedToLab();
		labWorkOrderListingPage.verifySuccessfullMessage();
		basePatientLifeCyclePage.headerOnAddPage("Lab Work Order Listing");
		labWorkOrderListingPage.checkLabNameMainList(addLWOInDraftMode.get("source"),addLWOInPaymentCompletedMode.get("lab_name"));
		labWorkOrderListingPage.checkInActiveEntry(addLWOInDraftMode.get("source"),addLWOInDraftMode.get("revision"),"LWO Updated");
		labWorkOrderListingPage.checkInActiveRevisionReasonMainList(addLWOInDraftMode.get("source"), addLWOInDraftMode.get("revision"), addLWOInSubmittedToLabMode.get("reason"));
		labWorkOrderListingPage.checkInActiveViewBtn(addLWOInDraftMode.get("source"),addLWOInDraftMode.get("revision"));
		labWorkOrderListingPage.checkDateTimeMainList(addLWOInDraftMode.get("source"), START_DATE_TIME, END_DATE_TIME);
		labWorkOrderListingPage.checkItemSentMainList(addLWOInDraftMode.get("source"), addLWOInSubmittedToLabMode.get("item_sent"));
		labWorkOrderListingPage.checkRevisionReasonMainList(addLWOInDraftMode.get("source"), addLWOInSubmittedToLabMode.get("revision"), addLWOInDraftMode.get("reason"));
		labWorkOrderListingPage.checkStatusDateMainList(addLWOInDraftMode.get("source"), addLWOInSubmittedToLabMode.get("status"), START_DATE_TIME, END_DATE_TIME);
		labWorkOrderListingPage.clickOnView(addLWOInDraftMode.get("source"));
		labWorkOrderListingPage.verifyHeaderInView("Lab Work Order");
		labWorkOrderListingPage.checkLabNameViewList(addLWOInPaymentCompletedMode.get("lab_name"));
		labWorkOrderListingPage.checkUpdationReasonViewList(addLWOInDraftMode.get("updation_reason"));
		labWorkOrderListingPage.checkRequireViewList(addLWOInPaymentCompletedMode.get("require"));
		labWorkOrderListingPage.checkShadeViewList(addLWOInPaymentCompletedMode.get("shade"), addLWOInPaymentCompletedMode.get("shade_option"));
		labWorkOrderListingPage.checkTranslucencyViewList(addLWOInPaymentCompletedMode.get("translucency"));
		labWorkOrderListingPage.checkConfigurationsViewList(addLWOInPaymentCompletedMode.get("configurations"), addLWOInPaymentCompletedMode.get("configurations_opt"));
		labWorkOrderListingPage.checkNotesViewList(addLWOInPaymentCompletedMode.get("notes"));
		labWorkOrderListingPage.checkRevisionNoViewList(addLWOInSubmittedToLabMode.get("revision_no"));
		labWorkOrderListingPage.verifyRevisionDateInView(START_DATE_TIME, END_DATE_TIME);
		labWorkOrderListingPage.checkCurrentStatusViewList(addLWOInSubmittedToLabMode.get("status"));
		Assert.assertTrue(basePage.verification().contains("Lab Work Order Listing"));
	}

	/*
	 * Partially Complete Trt from WorkDone and check LWO status in LWO listing and View Screen
	 * Fully Completed Trt from WorkDone and check LWO status In LWO listing and View Screen
	 */
	@Test(enabled=true, priority=6)
	public void completeTrtAndCheckLWOStatus() {
		extentReport.logger.log(LogStatus.PASS, COMPLETE_TRT_AND_CHECK_LAB_WORK_ORDER_STATUS);
		basePatientLifeCyclePage.clickOnAlert();
		patientDashboardPage.clickOnWorkDoneAdd();
		basePatientLifeCyclePage.headerOnAddPage("Works Done");
		workDonePage.selectTime(addLWOInDraftMode.get("plan_Full_Name"), "15");
		workDonePage.enterRemarks(addLWOInDraftMode.get("plan_Full_Name"), "it is for testing");
		workDonePage.selectStages(addLWOInDraftMode.get("plan_Full_Name"), "In-Progress");
		workDonePage.clickOnAdd(addLWOInDraftMode.get("plan_Full_Name"));
		workDonePage.closeAppoitmentPopup();
		workDonePage.clickLWOBtnWD();
		basePatientLifeCyclePage.headerOnAddPage("Lab Work Order Listing");
		labWorkOrderListingPage.checkDateTimeMainList(addLWOInDraftMode.get("source"), START_DATE_TIME, END_DATE_TIME);
		labWorkOrderListingPage.checkRevisionReasonMainList(addLWOInDraftMode.get("source"),addLWOInSubmittedToLabMode.get("revision"),addLWOInDraftMode.get("reason"));
		labWorkOrderListingPage.checkStatusDateMainList(addLWOInDraftMode.get("source"),addLWOInSubmittedToLabMode.get("status"), START_DATE_TIME, END_DATE_TIME);
		labWorkOrderListingPage.clickOnView(addLWOInDraftMode.get("source"));
		labWorkOrderListingPage.verifyHeaderInView("Lab Work Order");
		labWorkOrderListingPage.verifyDateTimeInView(START_DATE_TIME, END_DATE_TIME);
		labWorkOrderListingPage.checkStatusEnableInView(addLWOInSubmittedToLabMode.get("status"));
		labWorkOrderListingPage.verifyStatusInView(addLWOInSubmittedToLabMode.get("status"),START_DATE_TIME, END_DATE_TIME);
		labWorkOrderListingPage.clickViewScreenClose();
		basePatientLifeCyclePage.headerOnAddPage("Lab Work Order Listing");
		basePatientLifeCyclePage.clickOnDashBoard();
		basePatientLifeCyclePage.clickOnAlert();
		patientDashboardPage.clickOnWorkDoneAdd();
		basePatientLifeCyclePage.headerOnAddPage("Works Done");
		workDonePage.selectTime(addLWOInDraftMode.get("plan_Full_Name"), "15");
		workDonePage.enterRemarks(addLWOInDraftMode.get("plan_Full_Name"), "Change to complete");
		workDonePage.selectStages(addLWOInDraftMode.get("plan_Full_Name"), "Cementation");
		workDonePage.clickOnAdd(addLWOInDraftMode.get("plan_Full_Name"));
		workDonePage.clickLWOBtnWD();
		basePatientLifeCyclePage.headerOnAddPage("Lab Work Order Listing");
		labWorkOrderListingPage.checkDateTimeMainList(addLWOInDraftMode.get("source"), START_DATE_TIME, END_DATE_TIME);
		labWorkOrderListingPage.checkRevisionReasonMainList(addLWOInDraftMode.get("source"),addLWOInSubmittedToLabMode.get("revision"),addLWOInDraftMode.get("reason"));
		labWorkOrderListingPage.checkStatusDateMainList(addLWOInDraftMode.get("source"),addLWOInTreatmentCompletedMode.get("status"), START_DATE_TIME, END_DATE_TIME);
		labWorkOrderListingPage.clickOnView(addLWOInDraftMode.get("source"));
		labWorkOrderListingPage.verifyHeaderInView("Lab Work Order");
		labWorkOrderListingPage.verifyDateTimeInView(START_DATE_TIME, END_DATE_TIME);
		labWorkOrderListingPage.checkStatusEnableInView(addLWOInDraftMode.get("status"));
		labWorkOrderListingPage.checkStatusEnableInView(addLWOInSubmittedToLabMode.get("status"));
		labWorkOrderListingPage.checkStatusEnableInView(addLWOInTreatmentCompletedMode.get("status"));
		labWorkOrderListingPage.verifyStatusInView(addLWOInDraftMode.get("status"),START_DATE_TIME, END_DATE_TIME);
		labWorkOrderListingPage.verifyStatusInView(addLWOInSubmittedToLabMode.get("status"),START_DATE_TIME, END_DATE_TIME);
		labWorkOrderListingPage.verifyStatusInView(addLWOInTreatmentCompletedMode.get("status"),START_DATE_TIME, END_DATE_TIME);
		labWorkOrderListingPage.checkCurrentStatusViewList(addLWOInTreatmentCompletedMode.get("status"));
		labWorkOrderListingPage.clickViewScreenClose();
		Assert.assertTrue(basePage.verification().contains("Lab Work Order Listing"));
	}

	/*
	 * Take full payment of that Trt and check LWO status in LWO listing and View Screen
	 * Also check status of LWO should show as Payable
	 */
	@Test(enabled=true, priority=7)
	public void completePaymentAndCheckLWOStatus() {
		extentReport.logger.log(LogStatus.PASS, COMPLETE_PAYMENT_AND_CHECK_LAB_WORK_ORDER_STATUS);
		basePatientLifeCyclePage.clickOnAlert();
	/*	patientDashboardPage.clickOnInvoiceList();
		invoiceListingPage.headerPage("Invoice Listing");
//		invoiceListingPage.clickInvoicePaymentBtn();
//		newReceiptPage.headerNewReceipt("New Receipt");
//		newReceiptPage.collectByTreamentNewReceipts(ReadExcelData.readExcelData(FILE_PATH, SHEET, 19, 2), 1090);
//		newReceiptPage.selectPaymentMode("Cash");
//		newReceiptPage.saveNewReceipts();
		basePatientLifeCyclePage.clickOnDashBoard();
		basePatientLifeCyclePage.clickOnAlert(); */
		patientDashboardPage.clickOnLabWorkOrderAdd();
		basePatientLifeCyclePage.headerOnAddPage("Lab Work Order Listing");
		labWorkOrderListingPage.checkDateTimeMainList(addLWOInDraftMode.get("source"), START_DATE_TIME, END_DATE_TIME);
		labWorkOrderListingPage.checkRevisionReasonMainList(addLWOInDraftMode.get("source"),addLWOInSubmittedToLabMode.get("revision"),addLWOInDraftMode.get("reason"));
		labWorkOrderListingPage.checkStatusDateMainList(addLWOInDraftMode.get("source"),addLWOInPayableToLabMode.get("status"), START_DATE_TIME, END_DATE_TIME);
		labWorkOrderListingPage.clickOnView(addLWOInDraftMode.get("source"));
		labWorkOrderListingPage.verifyHeaderInView("Lab Work Order");
		labWorkOrderListingPage.verifyDateTimeInView(START_DATE_TIME, END_DATE_TIME);
		labWorkOrderListingPage.checkStatusEnableInView(addLWOInDraftMode.get("status"));
		labWorkOrderListingPage.checkStatusEnableInView(addLWOInSubmittedToLabMode.get("status"));
		labWorkOrderListingPage.checkStatusEnableInView(addLWOInPaymentCompletedMode.get("status"));
		labWorkOrderListingPage.checkStatusEnableInView(addLWOInTreatmentCompletedMode.get("status"));
		labWorkOrderListingPage.checkStatusEnableInView(addLWOInPayable.get("status"));
		labWorkOrderListingPage.verifyStatusInView(addLWOInDraftMode.get("status"),START_DATE_TIME, END_DATE_TIME);
		labWorkOrderListingPage.verifyStatusInView(addLWOInSubmittedToLabMode.get("status"),START_DATE_TIME, END_DATE_TIME);
		labWorkOrderListingPage.verifyStatusInView(addLWOInPaymentCompletedMode.get("status"),START_DATE_TIME, END_DATE_TIME);
		labWorkOrderListingPage.verifyStatusInView(addLWOInTreatmentCompletedMode.get("status"),START_DATE_TIME, END_DATE_TIME);
		labWorkOrderListingPage.verifyStatusInView(addLWOInPayable.get("status"),START_DATE_TIME, END_DATE_TIME);
		labWorkOrderListingPage.checkCurrentStatusViewList(addLWOInPayableToLabMode.get("status"));
		labWorkOrderListingPage.clickViewScreenClose();
		Assert.assertTrue(basePage.verification().contains("Lab Work Order Listing"));
	}

	/*
	 * From WorkDone Retreat the Trt which is completed above and check the LWO status on LWO listing and View screen
	 * check one ReWork button should also shown
	 * check InActive Revision list
	 * check Reason on listing and View Screen and status should shown as Payable Resubmitted
	 */
	@Test(enabled=true, priority=8)
	public void reTreatAboveCompleteTrtAndCheckLWOStatus() {
		extentReport.logger.log(LogStatus.PASS, RETREAT_ABOVE_COMPLETE_TRT_AND_CHECK_LAB_WORK_ORDER_STATUS);
		basePatientLifeCyclePage.clickOnAlert();
		patientDashboardPage.clickOnWorkDoneHistory();
		basePatientLifeCyclePage.headerOnAddPage("Work Done History");
		workDoneHistoryPage.clickReTreatBtn(addLWOInDraftMode.get("source"));
		workDoneHistoryPage.selectRequire("Re-infection.");
		workDoneHistoryPage.clickSaveBtnOnReasonPopUp();
		workDonePage.clickLWOBtnWD();
		basePatientLifeCyclePage.headerOnAddPage("Lab Work Order Listing");
		labWorkOrderListingPage.clickOnViewAtTimeForRework(addLWOInDraftMode.get("source"));
		labWorkOrderListingPage.verifyHeaderInView("Lab Work Order");
		labWorkOrderListingPage.checkSourceViewList(addLWOInDraftMode.get("source"), "ReTreated");
		labWorkOrderListingPage.clickViewScreenClose();
		labWorkOrderListingPage.clickOnRework(addLWOInDraftMode.get("source"));
		labWorkOrderEditPage.verifyHeaderOfReWorkPage("Lab Work Order Re-work");
		basePatientLifeCyclePage.verifyPatientName(PATIENT_NAME);
		labWorkOrderEditPage.checkedAllFieldsInWorkDetailPanel();
		labWorkOrderEditPage.clinicName(CLINIC_NAME);
		labWorkOrderEditPage.source(addLWOInDraftMode.get("source"));
		labWorkOrderEditPage.labWorkOrderNo();
		labWorkOrderEditPage.checkLabForReWork();
		labWorkOrderEditPage.checkOrderType(addLWOInDraftMode.get("order_type"));
		labWorkOrderEditPage.checkWorkDetails(addLWOInDraftMode.get("work_details"));
		labWorkOrderEditPage.checkOtherInfo(addLWOInSubmittedToLabMode.get("other_info"));
		labWorkOrderEditPage.checkItemSendDrpDwn();
		labWorkOrderEditPage.addUpdateReason(addLWOInSubmittedToLabMode.get("updation_reason"));
		labWorkOrderEditPage.checkedAdditionalDetailPanel();
		labWorkOrderEditPage.getRequireDrpDwn(addLWOInPaymentCompletedMode.get("require"));
		labWorkOrderEditPage.getStainsInternalDrpDwn(addLWOInSubmittedToLabMode.get("stains_internal"));
		labWorkOrderEditPage.getStainsExternalDrpDwn(addLWOInSubmittedToLabMode.get("stains_external"));
		labWorkOrderEditPage.getTranslucency(addLWOInPaymentCompletedMode.get("translucency"));
		labWorkOrderEditPage.getTexture(addLWOInSubmittedToLabMode.get("texture"));
		labWorkOrderEditPage.getNotes(addLWOInPaymentCompletedMode.get("notes"));
		labWorkOrderEditPage.clickOnItemSend();
		labWorkOrderEditPage.selectItemsSend(addLWOInSubmittedToLabMode.get("item_sent"));
		labWorkOrderEditPage.selectItemsSend(addLWOInTreatmentCompletedMode.get("item_sent"));
		labWorkOrderEditPage.addFile(System.getProperty("user.dir")+"\\PatientFiles\\"+addLWOInPaymentCompletedMode.get("attach_name"));
		labWorkOrderEditPage.selectRequire(addLWOInTreatmentCompletedMode.get("require"));
		labWorkOrderEditPage.addTransluency(addLWOInTreatmentCompletedMode.get("translucency"));
		labWorkOrderEditPage.addTexture(addLWOInPaymentCompletedMode.get("texture"));
		labWorkOrderEditPage.enterNotes(addLWOInTreatmentCompletedMode.get("notes"));
		labWorkOrderEditPage.clickSubmittedToLab();
		labWorkOrderListingPage.verifySuccessfullMessageForRework();
		basePatientLifeCyclePage.headerOnAddPage("Lab Work Order Listing");
		labWorkOrderListingPage.checkInActiveEntry(addLWOInDraftMode.get("source"),addLWOInSubmittedToLabMode.get("revision"),addLWOInPaymentCompletedMode.get("reason"));
		labWorkOrderListingPage.checkInActiveRevisionReasonMainListForRework(addLWOInDraftMode.get("source"), addLWOInSubmittedToLabMode.get("revision"),addLWOInPaymentCompletedMode.get("reason"));
		labWorkOrderListingPage.checkInActiveViewBtnForRework(addLWOInDraftMode.get("source"),addLWOInSubmittedToLabMode.get("revision"));
		labWorkOrderListingPage.checkDateTimeMainList(addLWOInDraftMode.get("source"), START_DATE_TIME, END_DATE_TIME);
		labWorkOrderListingPage.checkItemSentMainList(addLWOInDraftMode.get("source"), addLWOInSubmittedToLabMode.get("item_sent"));
		labWorkOrderListingPage.checkItemSentMainList(addLWOInDraftMode.get("source"), addLWOInTreatmentCompletedMode.get("item_sent"));
		labWorkOrderListingPage.checkRevisionReasonMainList(addLWOInDraftMode.get("source"), addLWOInPaymentCompletedMode.get("revision"), addLWOInDraftMode.get("reason"));
		labWorkOrderListingPage.checkStatusDateMainList(addLWOInDraftMode.get("source"), addLWOInPaymentResubmitted.get("status"), START_DATE_TIME, END_DATE_TIME);
		labWorkOrderListingPage.clickOnView(addLWOInDraftMode.get("source"));
		labWorkOrderListingPage.verifyHeaderInView("Lab Work Order");
		labWorkOrderListingPage.checkItemSentViewList(addLWOInSubmittedToLabMode.get("item_sent"));
		labWorkOrderListingPage.checkItemSentViewList(addLWOInTreatmentCompletedMode.get("item_sent"));
		labWorkOrderListingPage.checkUpdationReasonViewList(addLWOInSubmittedToLabMode.get("updation_reason"));
		labWorkOrderListingPage.checkRequireViewList(addLWOInTreatmentCompletedMode.get("require"));
		labWorkOrderListingPage.checkTranslucencyViewList(addLWOInTreatmentCompletedMode.get("translucency"));
		labWorkOrderListingPage.checkTextureViewList(addLWOInPaymentCompletedMode.get("texture"));
		labWorkOrderListingPage.checkNotesViewList(addLWOInTreatmentCompletedMode.get("notes"));
		labWorkOrderListingPage.checkAttachmentViewList(addLWOInPaymentCompletedMode.get("attach_name"));
		labWorkOrderListingPage.checkStatusEnableInView(addLWOInDraftMode.get("status"));
		labWorkOrderListingPage.checkStatusEnableInView(addLWOInSubmittedToLabMode.get("status"));
		labWorkOrderListingPage.checkStatusEnableInView(addLWOInPaymentCompletedMode.get("status"));
		labWorkOrderListingPage.checkStatusEnableInView(addLWOInTreatmentCompletedMode.get("status"));
		labWorkOrderListingPage.checkStatusEnableInView(addLWOInPayable.get("status"));
		labWorkOrderListingPage.checkStatusEnableInView(addLWOInPaymentResubmitted.get("status"));
		labWorkOrderListingPage.verifyStatusInView(addLWOInDraftMode.get("status"),START_DATE_TIME, END_DATE_TIME);
		labWorkOrderListingPage.verifyStatusInView(addLWOInSubmittedToLabMode.get("status"),START_DATE_TIME, END_DATE_TIME);
		labWorkOrderListingPage.verifyStatusInView(addLWOInPaymentCompletedMode.get("status"),START_DATE_TIME, END_DATE_TIME);
		labWorkOrderListingPage.verifyStatusInView(addLWOInTreatmentCompletedMode.get("status"),START_DATE_TIME, END_DATE_TIME);
		labWorkOrderListingPage.verifyStatusInView(addLWOInPayable.get("status"),START_DATE_TIME, END_DATE_TIME);
		labWorkOrderListingPage.verifyStatusInView(addLWOInPaymentResubmitted.get("status"),START_DATE_TIME, END_DATE_TIME);
		labWorkOrderListingPage.checkRevisionNoViewList(addLWOInPaymentCompletedMode.get("revision_no"));
		labWorkOrderListingPage.verifyRevisionDateInView(START_DATE_TIME, END_DATE_TIME);
		labWorkOrderListingPage.checkCurrentStatusViewList(addLWOInPaymentResubmitted.get("status"));
		labWorkOrderListingPage.clickViewScreenClose();
		Assert.assertTrue(basePage.verification().contains("Lab Work Order Listing"));
	}

	/*
	 * Completed the retreated Trt and check status on LWO listing and View Screen
	 * Check in view current status shown as Payable Trt Completed
	 */
	@Test(enabled=true, priority=9)
	public void CompleteAboveReTreatTrtAndCheckLWOStatus() {
		extentReport.logger.log(LogStatus.PASS, COMPLETE_ABOVE_RETREAT_TRT_AND_CHECK_LAB_WORK_ORDER_STATUS);
		basePatientLifeCyclePage.clickOnAlert();
		patientDashboardPage.clickOnWorkDoneAdd();
		basePatientLifeCyclePage.headerOnAddPage("Works Done");
		workDonePage.selectTime(addLWOInDraftMode.get("plan_Full_Name"), "15");
		workDonePage.enterRemarks(addLWOInDraftMode.get("plan_Full_Name"), "Complete Trt after ReTreat");
		workDonePage.selectStages(addLWOInDraftMode.get("plan_Full_Name"), "Cementation");
		workDonePage.clickOnAdd(addLWOInDraftMode.get("plan_Full_Name"));
		workDonePage.clickLWOBtnWD();
		basePatientLifeCyclePage.headerOnAddPage("Lab Work Order Listing");
		labWorkOrderListingPage.checkDateTimeMainList(addLWOInDraftMode.get("source"), START_DATE_TIME, END_DATE_TIME);
		labWorkOrderListingPage.checkRevisionReasonMainList(addLWOInDraftMode.get("source"),addLWOInPaymentCompletedMode.get("revision"),addLWOInDraftMode.get("reason"));
		labWorkOrderListingPage.checkStatusDateMainList(addLWOInDraftMode.get("source"),addLWOInPaymentTrtComplete.get("status"), START_DATE_TIME, END_DATE_TIME);
		labWorkOrderListingPage.checkPrintBtn(addLWOInDraftMode.get("source"));
		labWorkOrderListingPage.clickOnView(addLWOInDraftMode.get("source"));
		labWorkOrderListingPage.verifyHeaderInView("Lab Work Order");
		labWorkOrderListingPage.checkStatusEnableInView(addLWOInDraftMode.get("status"));
		labWorkOrderListingPage.checkStatusEnableInView(addLWOInSubmittedToLabMode.get("status"));
		labWorkOrderListingPage.checkStatusEnableInView(addLWOInPaymentCompletedMode.get("status"));
		labWorkOrderListingPage.checkStatusEnableInView(addLWOInTreatmentCompletedMode.get("status"));
		labWorkOrderListingPage.checkStatusEnableInView(addLWOInPayable.get("status"));
		labWorkOrderListingPage.checkStatusEnableInView(addLWOInPaymentResubmitted.get("status"));
		labWorkOrderListingPage.checkStatusEnableInView(addLWOInPaymentTrtCompleted.get("status"));
		labWorkOrderListingPage.verifyStatusInView(addLWOInDraftMode.get("status"),START_DATE_TIME, END_DATE_TIME);
		labWorkOrderListingPage.verifyStatusInView(addLWOInSubmittedToLabMode.get("status"),START_DATE_TIME, END_DATE_TIME);
		labWorkOrderListingPage.verifyStatusInView(addLWOInPaymentCompletedMode.get("status"),START_DATE_TIME, END_DATE_TIME);
		labWorkOrderListingPage.verifyStatusInView(addLWOInTreatmentCompletedMode.get("status"),START_DATE_TIME, END_DATE_TIME);
		labWorkOrderListingPage.verifyStatusInView(addLWOInPayable.get("status"),START_DATE_TIME, END_DATE_TIME);
		labWorkOrderListingPage.verifyStatusInView(addLWOInPaymentResubmitted.get("status"),START_DATE_TIME, END_DATE_TIME);
		labWorkOrderListingPage.verifyStatusInView(addLWOInPaymentTrtCompleted.get("status"),START_DATE_TIME, END_DATE_TIME);
		labWorkOrderListingPage.checkCurrentStatusViewList(addLWOInPaymentTrtComplete.get("status"));
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
