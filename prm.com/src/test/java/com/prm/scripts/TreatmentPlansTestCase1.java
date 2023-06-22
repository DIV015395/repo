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
//import com.prm.pageobjects.utils.ReadExcelData;
import com.prm.pageobjects.utils.SheetTest;
/**
 * 
 * @author Ajit Yadav & Vibhanshu Vinay
 *
 */
public class TreatmentPlansTestCase1 {	
	private LoginPage loginPage=new LoginPage();
	private DoctorDashBoard doctorDashboard=new DoctorDashBoard(loginPage);
	private BasePage basePage=new BasePage(loginPage);
	private ExtentReport extentReport=new ExtentReport(loginPage,"TreatmentPlansTestCase1");
	private PatientDashboardPage patientDashboardPage=new PatientDashboardPage(loginPage);
	private BasePatientLifeCyclePage basePatientLifeCyclePage=new BasePatientLifeCyclePage(loginPage);
	private OralExamsPage OralExamsPage=new OralExamsPage(loginPage);
	private TreatmentPlansPage treatmentPlansPage=new TreatmentPlansPage(loginPage);
	private TreatmentPlanListingPage treatmentPlanListingPage=new TreatmentPlanListingPage(loginPage);
	private WorksDonePage worksDonePage=new WorksDonePage(loginPage);
	private WorkDoneHistoryPage workDoneHistoryPage = new WorkDoneHistoryPage(loginPage);
	private AppointmentAddPage appointmentAddPage = new AppointmentAddPage(loginPage);
	private AppointmentsListPage appointmentsListPage = new AppointmentsListPage(loginPage);

	/**
	 * Storing mandatory input required to run Treatment Plan test cases
	 * or please update the mandatory input before running the test cases
	 */
//	private static final String FILE_PATH = TestData.getInstance().getInputData("treatment_plan_file_path");
//	private static final String SHEET = TestData.getInstance().getInputData("treatment_plan_file_sheet_name");
	private static final String TODAY_DATE =TestData.getInstance().getTodayDate();
//	private static final String PATIENT_NAME= TestData.getInstance().getInputData("treatment_plan_patient_name");
//	private static final String MOBILE_NUMBER = TestData.getInstance().getInputData("treatment_plan_patient_mobile");
	private static final String DOCTOR_NAME = TestData.getInstance().getInputData("treatment_plan_doctor_name");
//	private static final String CLINIC_NAME = TestData.getInstance().getInputData("treatment_plan_clinic_name");

	/**
	 * Messages for every test cases which will be print on the extent report
	 */
	private static final String SCRIPTS_STARTED_MSG ="@@ Test scripts has been started here @@";
	private static final String STATUS_OF_SCRIPTS ="@@ Test script has been completed @@";
	private static final String CHECK_UI_TREATMENT_PLAN_ADD_PAGE ="Validated Mandatory web element of treatment plan add Page - #checkUiTreatmentPlanAddPage";
	private static final String VERIFY_UI_OF_TEETH_FINDING_POPUP ="Validated Ui of teeth findings popup at treatment plan add page - #verifyUiOfTeethFindingPopup";
//	private static final String CHECKED_ORAL_EXAM_FROM_TREATMENT_POPUP ="Validated web element of oral exam in treatment plan Add page from teeth findings popup - #checkedOralExamFromTreatmentPopup";
	private static final String ADDING_TREATMENT_CHECK_IN_INPUT_AND_MAIN_LIST ="Validated Treatment After adding in input list,treatment list and treatment list view - #addingTreatmentCheckInInputAndMainList";
	private static final String VERIFY_VIEW_AND_EDIT_BTN ="Validated In View and In main list and After edit it reflect Everywhere - #verifyViewAndEditBtn";
	private static final String CHECK_FUNCTIONALITY_OF_INPUT_LIST_EDIT_BTN ="Validated functionality  of edit Btn in Input List - #checkEditInputList";
	private static final String CHECK_FUNCTIONALITY_OF_BOOK_BTN ="Validated funtionality of Book Btn - #checkFunctionalityOfBookBtn";
	private static final String CHECK_FUNTIONALITY_OF_PRINT_BTN ="Validated print Btn please verify where downloaded - #checkFuntionalityOfPrintBtn";
	private static final String CHECK_BEHAVIOUR_OF_SAVE_BTN_ON_INPUT_LIST ="Validated functionality Save btn in input list - #checkbehaviourOfSaveBtnOnInputList";
	private static final String CHECK_BEHAVIOUR_SELECTED_TREATMENT_TAB ="Validated functionality selected treatment tab - #checkBehaviourSelectedTreatmentTab";
	private static final String CHECK_ON_WORKDONE_PAGE_AFTER_TREATEMENT_START ="Validated workdone page after treatment starts- #checkOnWorkDonePageAfterTreatmentStart";
	private static final String VERIFY_DELETE_IN_TREATMENT_LIST ="Validated Delete Btn behaviour and Validated impact respectivily - #verifyDeleteInTreatmentList";

	Map<String, String> patntTrtmntPlanData = null;
	
	/**
	 * loginPage with UserName and Password
	 * search for patients from the doctor dashboard
	 * verified patients will redirect at the patient dashboard
	 */
	
	@BeforeClass
	public void setup( ) {
		patntTrtmntPlanData = SheetTest.prepareData("Treatment&Plan","TreatmentPlans","A1","Z");
		extentReport.logger = extentReport.report.startTest(this.getClass().getSimpleName());
		extentReport.logger.log(LogStatus.INFO, LoginPage.testCaseRunningStatusmsg);
		extentReport.logger.log(LogStatus.PASS, LoginPage.browserMsg);	
		String username=ReadConfig.getInstance().getUsername().toString();			
		String password=ReadConfig.getInstance().getPassword().toString();	
		loginPage.login(username, password);
		extentReport.logger.log(LogStatus.PASS, LoginPage.userNameMsg);				
		extentReport.logger.log(LogStatus.PASS, LoginPage.passwordMsg);			
		extentReport.logger.log(LogStatus.PASS, LoginPage.loginBtnClickMsg);
		doctorDashboard.doctorDashboardHomePage();
		basePage.selectClinicFrmHeader(patntTrtmntPlanData.get("clinicLocation"));
		basePage.enterMobileNo(patntTrtmntPlanData.get("patient_mobile"));
		basePage.clickOnSearchBtn();
		basePage.clickOnPatient(patntTrtmntPlanData.get("patient_mobile"),patntTrtmntPlanData.get("patient_name"));
		Assert.assertTrue(basePage.verification().contains("Patient Dashboard"));
	}
	
	
	@BeforeMethod
	public void verifyPatientdashBoardHomePage() {
		extentReport.logger.log(LogStatus.PASS, SCRIPTS_STARTED_MSG);
	}
	
	/**
	 * checked header of the Treatment Plan Add and Listing page
	 * checked treatment Plan/List add page without any treatment when we redirect for the first time
	 * checked the patient name and id should not be "null" or "NA"
	 * checked patient dashboard button, invoice button and works done history button
	 * checked left side navigation all button at both work done add page and Work Done History
	 * checked work done history page header
	 * checked no record message displayed at the both page for first time  redirection
	 */
	
	@Test(enabled=true,description = "Verify UI of Treatment And Plans",priority=1)
	public void checkUiTreatmentPlanAddPage() {
		extentReport.logger.log(LogStatus.PASS, CHECK_UI_TREATMENT_PLAN_ADD_PAGE);
		basePatientLifeCyclePage.clickOnAlert();
		patientDashboardPage.clickOnTreatmentPlanAddBtn();
		basePatientLifeCyclePage.clickOnAlert();
		basePatientLifeCyclePage.openCloseLeftNavigator();
		basePatientLifeCyclePage.webElementOfLeftNavigator();
		basePatientLifeCyclePage.openCloseLeftNavigator();
		treatmentPlansPage.verifyHeader();
		treatmentPlansPage.clickOnFindingsBtn();
//		treatmentPlansPage.verifyNoRecordMsg();
		treatmentPlansPage.verifyCreateBtnIsPresent();
		treatmentPlansPage.verifyProductAddBtnIsPresent();
		basePatientLifeCyclePage.dashBoardBtnVerify();
		treatmentPlansPage.clickOnNewTeethBtn();
		OralExamsPage.verifyAdultsTeeths();
		treatmentPlansPage.allTeethByQuadrantAndByArchIsPresent();
		OralExamsPage.clickOnPedo();
		OralExamsPage.verifyPedoTeeths();
		treatmentPlansPage.allTeethAndByQuadrantIsPresent();
		OralExamsPage.clickOnMixed();
		OralExamsPage.verifyMixedTeeths();
		treatmentPlansPage.allTeethAndByQuadrantIsPresent();
		treatmentPlansPage.verifyProductAddBtnIsPresent();
		basePatientLifeCyclePage.dashBoardBtnVerify();
		basePatientLifeCyclePage.clickOnDashBoard();
		basePatientLifeCyclePage.clickOnAlert();
		patientDashboardPage.clickOnTreatmentPlanListBtn();
		treatmentPlanListingPage.verifyHeaderOfPage("Treatment Plan Listing");
       //treatmentPlanListingPage.verifyNoRecordMsg();
		basePatientLifeCyclePage.verifyAddNewBtn();
		basePatientLifeCyclePage.dashBoardBtnVerify();
		treatmentPlansPage.verifyProductAddBtnIsPresent();
		basePatientLifeCyclePage.openCloseLeftNavigator();
		basePatientLifeCyclePage.webElementOfLeftNavigator();
		basePatientLifeCyclePage.openCloseLeftNavigator();
		Assert.assertTrue(basePage.verification().contains("Treatment Plan Listing"));
	}
	
	/**
     * Check Teeth Findings All Plans Treatment Groups.
	 * Verification of plans based upon groups.
	 */

	@Test(enabled=true,description = "Verify UI of Teeth Finding Pop-up",priority=2)
	public void verifyUiOfTeethFindingPopup() {
		extentReport.logger.log(LogStatus.PASS, VERIFY_UI_OF_TEETH_FINDING_POPUP);
		basePatientLifeCyclePage.clickOnAlert();
		patientDashboardPage.clickOnTreatmentPlanAddBtn();
		basePatientLifeCyclePage.clickOnAlert();
		OralExamsPage.clickOnTeethImage("Adult", "43");
		treatmentPlansPage.verifySeletecdTeethOnPopup("43");
		treatmentPlansPage.checkWebElementsOfPopup();
		treatmentPlansPage.clickOnConsultationXRays();
		treatmentPlansPage.verifyTreatments();
		treatmentPlansPage.clickOnEndo();
		treatmentPlansPage.verifyTreatments();
		treatmentPlansPage.clickOnExtractions();
		treatmentPlansPage.verifyTreatments();
		treatmentPlansPage.clickOnMinorOralSurgery();
		treatmentPlansPage.verifyTreatments();
		treatmentPlansPage.clickOnProsthoExceptCrowns();
		treatmentPlansPage.verifyTreatments();
		treatmentPlansPage.clickOnCrowns();
		treatmentPlansPage.verifyTreatments();
		treatmentPlansPage.clickOnImplantCrowns();
		treatmentPlansPage.verifyTreatments();
		treatmentPlansPage.clickOnPerio();
		treatmentPlansPage.verifyTreatments();
		treatmentPlansPage.clickOnOrtho();
		treatmentPlansPage.verifyTreatments();
		treatmentPlansPage.clickOnAlignersLMB();
		treatmentPlansPage.verifyTreatments();
		treatmentPlansPage.clickOnAlignersOthers();
		treatmentPlansPage.verifyTreatments();
		treatmentPlansPage.clickOnPedo();
		treatmentPlansPage.verifyTreatments();
		treatmentPlansPage.clickOnImplantsAB();
		treatmentPlansPage.verifyTreatments();
		treatmentPlansPage.clickOnImplantsNobel();
		treatmentPlansPage.verifyTreatments();
		treatmentPlansPage.clickOnImplantOthers();
		treatmentPlansPage.verifyTreatments();
		treatmentPlansPage.clickOnBoneGraftAndMembranes();
		treatmentPlansPage.verifyTreatments();
		treatmentPlansPage.clickOnLasers();
		treatmentPlansPage.verifyTreatments();
		treatmentPlansPage.clickOnIpCDOPBank();
		treatmentPlansPage.verifyTreatments();
		treatmentPlansPage.clickOnMembership();
		treatmentPlansPage.verifyTreatments();
		treatmentPlansPage.clickOnOthers();
		treatmentPlansPage.verifyTreatments();
		treatmentPlansPage.closeTreatmentPopup();
		Assert.assertTrue(basePage.verification().contains("Treatment Plans"));
	}
	
	/***This Test case is not work as of now as Edit button is remove from Treatment Pop up Plan*/
	
//	@Test(enabled=false,priority=3)
//	public void checkedOralExamFromTreatmentPopup() {
//		extentReport.logger.log(LogStatus.PASS, CHECKED_ORAL_EXAM_FROM_TREATMENT_POPUP);
//		basePatientLifeCyclePage.clickOnAlert();
//		patientDashboardPage.clickOnTreatmentPlanAddBtn();
//		basePatientLifeCyclePage.clickOnAlert();
//		OralExamsPage.clickOnTeethImage("Adult", "24");
//		treatmentPlansPage.verifySeletecdTeethOnPopup("24");
//		treatmentPlansPage.clickOnOralExamBtn();
//		OralExamsPage.clickOnBuccal();
//		treatmentPlansPage.verifyProvisinals();
//		OralExamsPage.clickOnDistal();
//		treatmentPlansPage.verifyProvisinals();
//		OralExamsPage.clickOnMesial();
//		treatmentPlansPage.verifyProvisinals();
//		OralExamsPage.clickOnOcclusal();
//		treatmentPlansPage.verifyProvisinals();
//		OralExamsPage.clickOnPalatal();
//		treatmentPlansPage.verifyProvisinals();
//		OralExamsPage.clickOnAllSurface();
//		treatmentPlansPage.verifyProvisinals();
//		OralExamsPage.clickOnPerio();
//		treatmentPlansPage.verifyProvisinals();
//		treatmentPlansPage.verifyIoparBtn();
//		treatmentPlansPage.clickOnIOPARBtn();
//		treatmentPlansPage.verifyIOPARNotes();
//		OralExamsPage.verifyToothObservationRemark();
//		OralExamsPage.clickOnSoftTissueOnPopup();
//		treatmentPlansPage.clickOnLip();
//		OralExamsPage.verifySoftProvisinals();
//		treatmentPlansPage.clickOnCheek();
//		OralExamsPage.verifySoftProvisinals();
//		treatmentPlansPage.clickOnTongue();
//		OralExamsPage.verifySoftProvisinals();
//		treatmentPlansPage.clickOnFloorOfMouth();
//		OralExamsPage.verifySoftProvisinals();
//		treatmentPlansPage.clickOnPalate();
//		OralExamsPage.verifySoftProvisinals();
//		treatmentPlansPage.clickOnGingiva();
//		OralExamsPage.verifySoftProvisinals();
//		treatmentPlansPage.clickOnVestibule();
//		OralExamsPage.verifySoftProvisinals();
//		treatmentPlansPage.clickOnFrenum();
//		OralExamsPage.verifySoftProvisinals();
//		treatmentPlansPage.clickOnSalivaryGland();
//		OralExamsPage.verifySoftProvisinals();
//		treatmentPlansPage.clickOnLymphNodes();
//		OralExamsPage.verifySoftProvisinals();
//		OralExamsPage.verifyToothObservationRemark();
//		OralExamsPage.clickOnHardTissueInPopup();
//		OralExamsPage.clickOnMandibularAngle();
//		OralExamsPage.verifyHardProvisionals();
//		OralExamsPage.clickOnMandibularBody();
//		OralExamsPage.verifyHardProvisionals();
//		OralExamsPage.clickOnMaxillaryTuberosity();
//		OralExamsPage.verifyHardProvisionals();
//		OralExamsPage.clickOnPosteriorMaxilla();
//		OralExamsPage.verifyHardProvisionals();
//		OralExamsPage.clickOnPreMaxilla();
//		OralExamsPage.verifyHardProvisionals();
//		OralExamsPage.clickOnMaxillarySinus();
//		OralExamsPage.verifyHardProvisionals();
//		OralExamsPage.clickOnMandibularSymphysis();
//		OralExamsPage.verifyHardProvisionals();
//		OralExamsPage.clickOnTMJoint();
//		OralExamsPage.verifyHardProvisionals();
//		OralExamsPage.verifyToothObservationRemark();
//		treatmentPlansPage.clickOnCloseBtnOralExam();
//		treatmentPlansPage.closeTreatmentPopup();
//		Assert.assertTrue(basePage.verification().contains("Treatment Plans"));
//	}

	@Test(enabled=true,description = "Add Treatment in the Listing & Verification of Listing Buttons",priority=4)
	public void addingTreatmentCheckInInputAndMainList() {
		extentReport.logger.log(LogStatus.PASS, ADDING_TREATMENT_CHECK_IN_INPUT_AND_MAIN_LIST);
		basePatientLifeCyclePage.clickOnAlert();
		patientDashboardPage.clickOnTreatmentPlanAddBtn();
		basePatientLifeCyclePage.clickOnAlert();
		OralExamsPage.clickOnTeethImage("Adult", "24");
		treatmentPlansPage.verifySeletecdTeethOnPopup("24");
		treatmentPlansPage.clickOnConsultationXRays();
		treatmentPlansPage.clickOnTreatments(patntTrtmntPlanData.get("planGroup1_Plan1"));
		treatmentPlansPage.clickOnTreatments(patntTrtmntPlanData.get("planGroup1_Plan2"));
		treatmentPlansPage.saveTreatment();
    	basePatientLifeCyclePage.clickOnAlert();
    	treatmentPlansPage.checkedInputListDataName();
    	treatmentPlansPage.intiallyBothPriceSameDiscount(patntTrtmntPlanData.get("planGroup1_Plan1"));
    	treatmentPlansPage.intiallyBothPriceSameDiscount(patntTrtmntPlanData.get("planGroup1_Plan2"));
		treatmentPlansPage.checkEditSaveInputList("24");
		treatmentPlansPage.checkDeleteInputList("24");
		//-------checking checkbox at input list and start button main list-------
		
		treatmentPlansPage.startCheckBoxInputListNotPresent();
		treatmentPlansPage.clickOnSaveBtnTreatmentInputList();
        treatmentPlanListingPage.checkStartBtnNotPresent(TODAY_DATE);
		basePatientLifeCyclePage.clickOnDashBoard();
		basePatientLifeCyclePage.clickOnAlert();
		patientDashboardPage.clickOnAppList();
        appointmentsListPage.appointmentAvailable();
		basePatientLifeCyclePage.clickOnDashBoard(); 
		basePatientLifeCyclePage.clickOnAlert();
		patientDashboardPage.clickOnAppAdd();
		appointmentAddPage.selectDoctorFromDropdown(DOCTOR_NAME);
		appointmentAddPage.clickOnSaveBtn();
		basePatientLifeCyclePage.clickOnAlert();
		patientDashboardPage.clickOnTreatmentPlanListBtn();
		treatmentPlanListingPage.verifyStartBtn(TODAY_DATE);
		treatmentPlanListingPage.clickOnEditBtn(TODAY_DATE);
		treatmentPlansPage.selectedTreamentInIputList(patntTrtmntPlanData.get("planGroup1_Plan1"));
		treatmentPlansPage.selectedTreamentInIputList(patntTrtmntPlanData.get("planGroup1_Plan2"));
		//--checking in finding-----
		treatmentPlansPage.clickOnFindingsBtn();
		treatmentPlansPage.selectedTreamentInIputList(patntTrtmntPlanData.get("planGroup1_Plan1"));
		treatmentPlansPage.selectedTreamentInIputList(patntTrtmntPlanData.get("planGroup1_Plan2"));
		treatmentPlansPage.verifyNoRecordMsg();
		//-checking treatment plan listing -------------
		basePatientLifeCyclePage.clickOnDashBoard();
		basePatientLifeCyclePage.clickOnAlert();
		patientDashboardPage.clickOnTreatmentPlanListBtn();
		treatmentPlanListingPage.verifyDatesTreatmentPlanList();
		treatmentPlanListingPage.checkedDataNameMainList();
		treatmentPlanListingPage.selectedTreatmentInTreatmentList(patntTrtmntPlanData.get("planGroup1_Plan1_FullName"),patntTrtmntPlanData.get("companyName"));
		treatmentPlanListingPage.selectedTreatmentInTreatmentList(patntTrtmntPlanData.get("planGroup1_Plan2_FullName"),patntTrtmntPlanData.get("companyName"));
		//---------checking respective actions Btn& Please pass current date in format of "dd-MM-YY"
		treatmentPlanListingPage.verifyCopyBtn(TODAY_DATE);
		treatmentPlanListingPage.verifyStartBtn(TODAY_DATE);
		treatmentPlanListingPage.verifyMarkBtn(TODAY_DATE);
		treatmentPlanListingPage.verifyViewBtn(TODAY_DATE);
		treatmentPlanListingPage.verifyEditBtn(TODAY_DATE);
		treatmentPlanListingPage.verifyDeleteBtn(TODAY_DATE);
		treatmentPlanListingPage.verifyPrintBtn(TODAY_DATE);
		//----------check treatment in treatment list view--------//
		treatmentPlanListingPage.clickOnViewBtn(TODAY_DATE);
		treatmentPlanListingPage.verifyTreatmentInViewPopup(patntTrtmntPlanData.get("planGroup1_Plan1_FullName"));
		treatmentPlanListingPage.verifyTreatmentInViewPopup(patntTrtmntPlanData.get("planGroup1_Plan2_FullName"));
		treatmentPlanListingPage.verifyHeaderInViewPopup("Treatment Plan View");
		treatmentPlanListingPage.clickOnCloseBtnViewPopup();
		Assert.assertTrue(basePage.verification().contains("Treatment Plan Listing"));
	}
	
	@Test(enabled=true,priority=5,description = "Verify View Edit & Delete Single Treatment",dependsOnMethods="addingTreatmentCheckInInputAndMainList")
	public void verifyViewAndEditBtn() {
		extentReport.logger.log(LogStatus.PASS, VERIFY_VIEW_AND_EDIT_BTN);
		basePatientLifeCyclePage.clickOnAlert();
		patientDashboardPage.clickOnTreatmentPlanListBtn();
		treatmentPlanListingPage.clickOnViewBtn(TODAY_DATE);
		treatmentPlanListingPage.verifyTreatmentInViewPopup(patntTrtmntPlanData.get("planGroup1_Plan1_FullName"));
		treatmentPlanListingPage.verifyTreatmentInViewPopup(patntTrtmntPlanData.get("planGroup1_Plan2_FullName"));
		treatmentPlanListingPage.clickOnCloseBtnViewPopup();
		treatmentPlanListingPage.clickOnEditBtn(TODAY_DATE);
		//---verify delete Btn and also delete the Treatment from input list
		treatmentPlansPage.deleteTreatment(patntTrtmntPlanData.get("planGroup1_Plan1_FullName"));
		treatmentPlansPage.clickOnYesForDelete();
		treatmentPlansPage.verifyDeleteSuccessMsg();
		treatmentPlansPage.verifyAfterDelete(patntTrtmntPlanData.get("planGroup1_Plan1_FullName"));
		//--verify deleted treatment in treatment listing page and also in view PopUp
		basePatientLifeCyclePage.clickOnDashBoard();
		basePatientLifeCyclePage.clickOnAlert();
		patientDashboardPage.clickOnTreatmentPlanListBtn();
		treatmentPlanListingPage.verifyAfterDeleteInTreatmentList(patntTrtmntPlanData.get("planGroup1_Plan1_FullName"));
		treatmentPlanListingPage.clickOnViewBtn(TODAY_DATE);
		treatmentPlanListingPage.verifyAfterDeleteInTreatmentListViewPopup(patntTrtmntPlanData.get("planGroup1_Plan1_FullName"));
		treatmentPlanListingPage.clickOnCloseBtnViewPopup();
		Assert.assertTrue(basePage.verification().contains("Treatment Plan Listing"));
	}
	
   @Test(enabled=true,priority=6,description = "Verify Edit And Add New Treatment Along With Old",dependsOnMethods="verifyViewAndEditBtn")
	public void checkEditInputList() {
		extentReport.logger.log(LogStatus.PASS, CHECK_FUNCTIONALITY_OF_INPUT_LIST_EDIT_BTN);
		basePatientLifeCyclePage.clickOnAlert();
		patientDashboardPage.clickOnTreatmentPlanListBtn();
		treatmentPlanListingPage.clickOnEditBtn(TODAY_DATE);
		treatmentPlansPage.clickOnEditBtnTreatmentInputList();
		treatmentPlansPage.clickOnPedo();
		treatmentPlansPage.clickOnTreatments(patntTrtmntPlanData.get("planGroup2_Plan1"));
		treatmentPlansPage.saveTreatment();
     	treatmentPlansPage.verifyAfterDelete(patntTrtmntPlanData.get("planGroup1_Plan1"));
		treatmentPlansPage.selectedTreamentInIputList(patntTrtmntPlanData.get("planGroup1_Plan2_FullName"));
		treatmentPlansPage.selectedTreamentInIputList(patntTrtmntPlanData.get("planGroup2_Plan1_FullName"));
		//--checking in finding-----
		treatmentPlansPage.clickOnFindingsBtn();
		treatmentPlansPage.verifyAfterDelete(patntTrtmntPlanData.get("planGroup1_Plan1"));
		treatmentPlansPage.selectedTreamentInIputList(patntTrtmntPlanData.get("planGroup1_Plan2_FullName"));
		treatmentPlansPage.selectedTreamentInIputList(patntTrtmntPlanData.get("planGroup2_Plan1_FullName"));
		treatmentPlansPage.verifyNoRecordMsg();
		//-checking treatment plan listing -------------
		basePatientLifeCyclePage.clickOnDashBoard();
		basePatientLifeCyclePage.clickOnAlert();
		patientDashboardPage.clickOnTreatmentPlanListBtn();
		treatmentPlanListingPage.verifyDatesTreatmentPlanList();
		treatmentPlanListingPage.verifyAfterDeleteInTreatmentList(patntTrtmntPlanData.get("planGroup1_Plan1_FullName"));
		treatmentPlanListingPage.selectedTreatmentInTreatmentList(patntTrtmntPlanData.get("planGroup1_Plan2_FullName"),patntTrtmntPlanData.get("companyName"));
		treatmentPlanListingPage.selectedTreatmentInTreatmentList(patntTrtmntPlanData.get("planGroup2_Plan1_FullName"),patntTrtmntPlanData.get("companyName"));
		//--checking in view popup on treatment plan list--
		treatmentPlanListingPage.clickOnViewBtn(TODAY_DATE);
		treatmentPlanListingPage.verifyAfterDeleteInTreatmentListViewPopup(patntTrtmntPlanData.get("planGroup1_Plan1_FullName"));
		treatmentPlanListingPage.verifyTreatmentInViewPopup(patntTrtmntPlanData.get("planGroup1_Plan2_FullName"));
		treatmentPlanListingPage.verifyTreatmentInViewPopup(patntTrtmntPlanData.get("planGroup2_Plan1_FullName"));
		treatmentPlanListingPage.clickOnCloseBtnViewPopup();
		Assert.assertTrue(basePage.verification().contains("Treatment Plan Listing"));
	}
	
	@Test(enabled=true,priority=7,description = "Mark The Treatment As Patient Preferred",dependsOnMethods="addingTreatmentCheckInInputAndMainList")
	public void checkFunctionalityOfMarkBtn() {
		extentReport.logger.log(LogStatus.PASS, CHECK_FUNCTIONALITY_OF_BOOK_BTN);
		basePatientLifeCyclePage.clickOnAlert();
		patientDashboardPage.clickOnTreatmentPlanListBtn();
		treatmentPlanListingPage.selectTreatmentInTreatmentListPage(patntTrtmntPlanData.get("planGroup1_Plan2_FullName"));
		treatmentPlanListingPage.clickOnMarkBtn(TODAY_DATE);
		treatmentPlanListingPage.verifyMarkedMsg();
		treatmentPlanListingPage.verifyAfterMarked(patntTrtmntPlanData.get("planGroup1_Plan2_FullName"));
		treatmentPlanListingPage.verifyUnbookBtn(TODAY_DATE);
		Assert.assertTrue(basePage.verification().contains("Treatment Plan Listing"));
		loginPage.softAssert().assertAll();
	}
	

	@Test(enabled=true,priority=8,description = "Check Functionality For Print Button",dependsOnMethods="addingTreatmentCheckInInputAndMainList")
	public void checkFunctionalityPrintBtn() {
		extentReport.logger.log(LogStatus.PASS, CHECK_FUNTIONALITY_OF_PRINT_BTN);
		basePatientLifeCyclePage.clickOnAlert();
		patientDashboardPage.clickOnTreatmentPlanListBtn();
		treatmentPlanListingPage.clickOnPrintBtn(TODAY_DATE);
		Assert.assertTrue(basePage.verification().contains("Treatment Plan Listing"));
	}

	@Test(enabled=true,priority=9,description = "Behaviour of Selected Treatment Selction/Deselection")
	public void checkBehaviourSelectedTreatmentTab() {
		extentReport.logger.log(LogStatus.PASS, CHECK_BEHAVIOUR_SELECTED_TREATMENT_TAB);
		basePatientLifeCyclePage.clickOnAlert();
		patientDashboardPage.clickOnTreatmentPlanAddBtn();
		basePatientLifeCyclePage.clickOnAlert();
		OralExamsPage.clickOnTeethImage("Adult", "25");
		treatmentPlansPage.verifySeletecdTeethOnPopup("25");
		//----selecting treatment here
		treatmentPlansPage.clickOnConsultationXRays();
		treatmentPlansPage.clickOnTreatments(patntTrtmntPlanData.get("planGroup1_Plan1"));
		treatmentPlansPage.clickOnTreatments(patntTrtmntPlanData.get("planGroup1_Plan2"));
		treatmentPlansPage.clickOnPedo();
		treatmentPlansPage.clickOnTreatments(patntTrtmntPlanData.get("planGroup2_Plan1"));
		treatmentPlansPage.clickOnTreatments(patntTrtmntPlanData.get("planGroup2_Plan2"));
		treatmentPlansPage.clickOnSelectedTreatment();
		//---deselecting treatment from selected treatment tab
		treatmentPlansPage.removeTreatmentFromSelectedTreatmentTab(patntTrtmntPlanData.get("planGroup1_Plan1_FullName"));
		treatmentPlansPage.removeTreatmentFromSelectedTreatmentTab(patntTrtmntPlanData.get("planGroup1_Plan2_FullName"));
		treatmentPlansPage.removeTreatmentFromSelectedTreatmentTab(patntTrtmntPlanData.get("planGroup2_Plan1_FullName"));
		treatmentPlansPage.removeTreatmentFromSelectedTreatmentTab(patntTrtmntPlanData.get("planGroup2_Plan2_FullName"));
		//-----verify treatment is deselected from treatment groups
		treatmentPlansPage.clickOnTreatmentGroups();
		treatmentPlansPage.clickOnConsultationXRays();
		treatmentPlansPage.deselectedTreatment(patntTrtmntPlanData.get("planGroup1_Plan1"));
		treatmentPlansPage.deselectedTreatment(patntTrtmntPlanData.get("planGroup1_Plan2"));
		treatmentPlansPage.clickOnPedo();
		treatmentPlansPage.deselectedTreatment(patntTrtmntPlanData.get("planGroup2_Plan1"));
		treatmentPlansPage.deselectedTreatment(patntTrtmntPlanData.get("planGroup2_Plan2"));
		treatmentPlansPage.saveTreatmentWithMessage();
		treatmentPlansPage.verifyWithoutTreatmentMsg();
		treatmentPlansPage.closeTreatmentPopup();
		Assert.assertTrue(basePage.verification().contains("Treatment Plans"));
	}

	@Test(enabled=true,priority=10,dependsOnMethods="addingTreatmentCheckInInputAndMainList")
	public void checkBehaviourOfSaveBtnOnInputList() {
		extentReport.logger.log(LogStatus.PASS, CHECK_BEHAVIOUR_OF_SAVE_BTN_ON_INPUT_LIST);
		basePatientLifeCyclePage.clickOnAlert();
		patientDashboardPage.clickOnTreatmentPlanListBtn();
		treatmentPlanListingPage.clickOnEditBtn(TODAY_DATE);
		treatmentPlansPage.clickOnSaveBtnTreatmentInputList();
		treatmentPlanListingPage.selectTreatmentInTreatmentListPage(patntTrtmntPlanData.get("planGroup1_Plan2_FullName"));
		treatmentPlanListingPage.clickOnStartBtn(TODAY_DATE);
		/*--below is written for Admin loginPage -*/
	  /*treatmentPlanListingPage.verifyHeaderOfDoctorListPopup();
		treatmentPlanListingPage.selectDoctor("Amit Bhatia");
		treatmentPlanListingPage.clickOnSaveBtnInDoctorList();*/
		Assert.assertTrue(basePage.verification().contains("Works Done"));
	}
	
	@Test(enabled=true,priority=11)
	public void checkOnWorkDonePageAfterTreatmentStart() {
		extentReport.logger.log(LogStatus.PASS, CHECK_ON_WORKDONE_PAGE_AFTER_TREATEMENT_START);
		
		patientDashboardPage.hideDueWarningPopup();
		basePatientLifeCyclePage.clickOnAlert();
		patientDashboardPage.clickOnWorkDoneAdd();
		basePatientLifeCyclePage.headerOnAddPage("Works Done");
		worksDonePage.checkDateTreatment(patntTrtmntPlanData.get("planGroup1_Plan1_FullName"), TODAY_DATE);
		worksDonePage.checkSourceNo(patntTrtmntPlanData.get("planGroup1_Plan1_FullName"), "24");
		worksDonePage.checkedStatusTreatment(patntTrtmntPlanData.get("planGroup1_Plan1_FullName"), "Started");
		worksDonePage.checkProgressDropDown(patntTrtmntPlanData.get("planGroup1_Plan1_FullName"));
		worksDonePage.checkSuspededBtn(patntTrtmntPlanData.get("planGroup1_Plan1_FullName"));
		worksDonePage.checkDataName(patntTrtmntPlanData.get("planGroup1_Plan1_FullName"));
		worksDonePage.checkBox(patntTrtmntPlanData.get("planGroup1_Plan1_FullName"));
		worksDonePage.modifiedDate(patntTrtmntPlanData.get("planGroup1_Plan1_FullName"), TODAY_DATE);
		worksDonePage.checkDoctorSelected(patntTrtmntPlanData.get("planGroup1_Plan1_FullName"), patntTrtmntPlanData.get("doctor"));
		worksDonePage.checkedClinic(patntTrtmntPlanData.get("planGroup1_Plan1_FullName"), patntTrtmntPlanData.get("clinicLocation"));
		worksDonePage.checkTimeSelected(patntTrtmntPlanData.get("planGroup1_Plan1_FullName"), "Select Time");
		worksDonePage.checkedRemarks(patntTrtmntPlanData.get("planGroup1_Plan1_FullName"));
		worksDonePage.checkedAddButton(patntTrtmntPlanData.get("planGroup1_Plan1_FullName"));
		/*-------------workdone history check all data-----------------*/
		worksDonePage.clickWorkDoneHistory();
		workDoneHistoryPage.checkedInvoiceListBtn();
		workDoneHistoryPage.checkLabWorkOrderBtn();
		workDoneHistoryPage.checkedPrintBtn();
		basePatientLifeCyclePage.verifyAddNewBtn();
		basePatientLifeCyclePage.dashBoardBtnVerify();
		workDoneHistoryPage.checkDateTreatment(patntTrtmntPlanData.get("planGroup1_Plan1_FullName"), TODAY_DATE);
		workDoneHistoryPage.checkSourceNo(patntTrtmntPlanData.get("planGroup1_Plan1_FullName"), "24");
		workDoneHistoryPage.checkedStatusTreatment(patntTrtmntPlanData.get("planGroup1_Plan1_FullName"), "In-Progress");
		workDoneHistoryPage.checkDataName(patntTrtmntPlanData.get("planGroup1_Plan1_FullName"));
		workDoneHistoryPage.modifiedDate(patntTrtmntPlanData.get("planGroup1_Plan1_FullName"), TODAY_DATE);
		workDoneHistoryPage.checkDoctorTreated(patntTrtmntPlanData.get("planGroup1_Plan1_FullName"), patntTrtmntPlanData.get("doctor"));
		workDoneHistoryPage.checkedClinic(patntTrtmntPlanData.get("planGroup1_Plan1_FullName"), patntTrtmntPlanData.get("clinicLocation"));
		workDoneHistoryPage.checkSpentTime(patntTrtmntPlanData.get("planGroup1_Plan1_FullName"), "15");
		workDoneHistoryPage.checkStageTreatment(patntTrtmntPlanData.get("planGroup1_Plan1_FullName"), "Started");
		workDoneHistoryPage.checkedRemarks(patntTrtmntPlanData.get("planGroup1_Plan1_FullName"), "No Remarks");
		workDoneHistoryPage.checkPrintButtonDisplayed();
		Assert.assertTrue(basePage.verification().contains("Work Done History"));
	}
	

	@Test(enabled=true,priority=12,description = "Delete The Added Treatment And Verification",dependsOnMethods="addingTreatmentCheckInInputAndMainList")
	public void verifyDeleteInTreatmentList() {
		extentReport.logger.log(LogStatus.PASS, VERIFY_DELETE_IN_TREATMENT_LIST);
		basePatientLifeCyclePage.clickOnAlert();
		patientDashboardPage.clickOnTreatmentPlanListBtn();
		treatmentPlanListingPage.verifyDatesTreatmentPlanList();
		treatmentPlanListingPage.clickOnDeleteBtn(TODAY_DATE);
		treatmentPlanListingPage.clickOnYesBtnForDelete();
		treatmentPlanListingPage.verifyDeleteSuccessMsg();
		treatmentPlanListingPage.verifyAfterDeleteInTreatmentList(patntTrtmntPlanData.get("planGroup1_Plan1_FullName"));
		treatmentPlanListingPage.checkNotDeletedTreatment(patntTrtmntPlanData.get("planGroup1_Plan2_FullName"));
		treatmentPlanListingPage.verifyAfterDeleteInTreatmentList(patntTrtmntPlanData.get("planGroup2_Plan1_FullName"));
		treatmentPlanListingPage.verifyViewBtn(TODAY_DATE);
		treatmentPlanListingPage.verifyPrintBtn(TODAY_DATE);
		treatmentPlanListingPage.clickOnViewBtn(TODAY_DATE);
		treatmentPlanListingPage.verifyAfterDeleteInTreatmentListViewPopup(patntTrtmntPlanData.get("planGroup1_Plan1_FullName"));
		treatmentPlanListingPage.notDeletedTreatmentInView(patntTrtmntPlanData.get("planGroup1_Plan2_FullName"));
		treatmentPlanListingPage.verifyAfterDeleteInTreatmentListViewPopup(patntTrtmntPlanData.get("planGroup2_Plan1_FullName"));
		treatmentPlanListingPage.clickOnCloseBtnViewPopup();
		Assert.assertTrue(basePage.verification().contains("Treatment Plan Listing"));
	}

	@AfterMethod(description= STATUS_OF_SCRIPTS,enabled =true)
	public void statusOfScripts(ITestResult result) {       
		extentReport.onTestFailure(result);
		extentReport.logger.log(LogStatus.PASS, STATUS_OF_SCRIPTS);
		basePatientLifeCyclePage.clickOnDashBoard();
	}

	@AfterClass
	public void tearDown() {
		extentReport.report.endTest(extentReport.logger);
		extentReport.report.flush();
		//	extentReport.report.close();
		basePage.clickOnlogOut();
	}

}
