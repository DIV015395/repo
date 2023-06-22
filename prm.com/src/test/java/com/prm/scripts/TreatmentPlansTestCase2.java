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

/**
 *  @author Ajit Yadav & Vibhanshu Vinay
 *
 */
public class TreatmentPlansTestCase2 {
	private LoginPage loginPage = new LoginPage();
	private DoctorDashBoard doctorDashboard = new DoctorDashBoard(loginPage);
	private BasePage basePage = new BasePage(loginPage);
	private AppointmentAddPage appointmentPage = new AppointmentAddPage(loginPage);
	private ExtentReport extentReport = new ExtentReport(loginPage, "TreatmentPlansTestCase2");
	private PatientDashboardPage patientDashboardPage = new PatientDashboardPage(loginPage);
	private BasePatientLifeCyclePage basePatientLifeCyclePage = new BasePatientLifeCyclePage(loginPage);
	private OralExamsPage oralExamsPage = new OralExamsPage(loginPage);
	private TreatmentPlansPage treatmentPlansPage = new TreatmentPlansPage(loginPage);
	private TreatmentPlanListingPage treatmentPlanListingPage = new TreatmentPlanListingPage(loginPage);
	private AppointmentsListPage appointmentsListPage = new AppointmentsListPage(loginPage);


	/*please give the input before the execution of test cases and date format should be "dd-MM-yy" */
//	private static final String FILE_PATH = TestData.getInstance().getInputData("treatment_plan_file_path");
//	private static final String SHEET = TestData.getInstance().getInputData("treatment_plan_file_sheet_name");
	private static final String TODAY_DATE = TestData.getInstance().getTodayDate();
//	private static final String PATIENT_NAME= TestData.getInstance().getInputData("treatment_plan_patient_name");
//	private static final String MOBILE_NUMBER = TestData.getInstance().getInputData("treatment_plan_patient_mobile");
	private static final String DOCTOR_NAME = TestData.getInstance().getInputData("treatment_plan_doctor_name");
//	private static final String DOCTOR_NIK_NAME = TestData.getInstance().getInputData("treatment_plan_doctor_nikName");
//	private static final String CLINIC_NAME = TestData.getInstance().getInputData("treatment_plan_clinic_name");
	


	/*Messages for the extent report*/
	
	private static final String SCRIPTS_STARTED_MSG = "@@ Test scripts has been started here @@";
	private static final String STATUS_OF_SCRIPTS = "@@ Test script has been completed @@";
//	private static final String CREATE_ORAL_EXAM_SINGLE_TEETH = "Validated add oral Exam for single teeth and checked in findings - #createOralExamSingleTeeth";
//	private static final String CREATE_ORAL_EXAM_MULTIPLE_TEETH = "Validated add oral Exam for multiple teeth and checked in findings - #createOralExamMultipleTeeth";
//	private static final String START_TREATMENT_FROM_FINDINGS = "Validated Add Treatment By selecting findings and check in main list and view - #addTreatmentFromFindings";
	private static final String FUNCTIONALITY_OF_START_BTN = "Checking the functionality of discount coupon - #functionalityOfStartBtn";
	private static final String FUNCTIONALITY_OF_COPY_BTN = "Validated functionality of copy Btn - #functionalityOfCopyBtn";
	private static final String FUNCTIONALITY_OF_DISCOUNT = "Checking the functionality of discount coupon - #functionalityOfDiscount";
	private static final String ADDING_TREATMENT_CHECK_IN_INPUT ="Validated Treatment After adding in input list";
	Map<String, String> patntTrtmntPlanData = null;
	@BeforeClass
	public void setup() {
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
	public void verifyPatientDashBoardHomePage() {
		extentReport.logger.log(LogStatus.PASS, SCRIPTS_STARTED_MSG);
		basePatientLifeCyclePage.clickOnAlert();
	}
	
	
	@Test(enabled=true,description = "Add Treatment in the Listing",priority=1)
	public void addingTreatmentCheckInInputAndMainList() {
		extentReport.logger.log(LogStatus.PASS, ADDING_TREATMENT_CHECK_IN_INPUT);
		basePatientLifeCyclePage.clickOnAlert();
		patientDashboardPage.clickOnTreatmentPlanAddBtn();
		basePatientLifeCyclePage.clickOnAlert();
		oralExamsPage.clickOnTeethImage("Adult", "24");
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
	}

	@Test(enabled = true,description = "Check The Functionality of Start Button",priority = 2)
	public void functionalityOfStartBtn() {
		extentReport.logger.log(LogStatus.PASS, FUNCTIONALITY_OF_START_BTN);
		patientDashboardPage.clickOnAppList();
		appointmentsListPage.appointmentAvailable();
		basePatientLifeCyclePage.clickOnDashBoard();
		basePatientLifeCyclePage.clickOnAlert();
		patientDashboardPage.clickOnTreatmentPlanListBtn();
		treatmentPlanListingPage.checkStartBtnNotPresent(TODAY_DATE);
		basePatientLifeCyclePage.clickOnDashBoard();
		basePatientLifeCyclePage.clickOnAlert();
		patientDashboardPage.clickOnAppAdd();
		appointmentPage.selectDoctorFromDropdown(DOCTOR_NAME);
		appointmentPage.clickOnSaveBtn();
		basePatientLifeCyclePage.clickOnAlert();
		patientDashboardPage.clickOnAppList();
		appointmentsListPage.appointmentAdded();
	}
	
	/*ORAL EXAM NOW PART OF PATIENT DASHBOARD*/

//	@Test(enabled = true,priority = 2)
//	public void createOralExamSingleTeeth() {
//		extentReport.logger.log(LogStatus.PASS, CREATE_ORAL_EXAM_SINGLE_TEETH);
//		patientDashboardPage.clickOnTreatmentPlanAddBtn();
//		basePatientLifeCyclePage.clickOnAlert();
//		oralExamsPage.clickOnTeethImage("Adult", "23");
//		treatmentPlansPage.verifySeletecdTeethOnPopup("23");
//		treatmentPlansPage.clickOnOralExamBtn();
//		// ------
//		oralExamsPage.clickOnBuccal();
//		treatmentPlansPage.clickOnProvisionals(ReadExcelData.readExcelData(FILE_PATH, SHEET, 11, 2));
//		oralExamsPage.clickOnSoftTissueOnPopup();
//		treatmentPlansPage.clickOnLip();
//		treatmentPlansPage.clickOnProvisionals(ReadExcelData.readExcelData(FILE_PATH, SHEET, 12, 2));
//		oralExamsPage.clickOnHardTissueInPopup();
//		oralExamsPage.clickOnMandibularBody();
//		treatmentPlansPage.clickOnProvisionals(ReadExcelData.readExcelData(FILE_PATH, SHEET, 13, 2));
//		oralExamsPage.enterOralExamNotes("it is for testig");
//		treatmentPlansPage.clickOnSaveOralBtn();
//		treatmentPlansPage.verifySelectedProvisinalsIntreatmentPopup(ReadExcelData.readExcelData(FILE_PATH, SHEET, 11, 2));
//		treatmentPlansPage.verifySelectedProvisinalsIntreatmentPopup(ReadExcelData.readExcelData(FILE_PATH, SHEET, 12, 2));
//		treatmentPlansPage.verifySelectedProvisinalsIntreatmentPopup(ReadExcelData.readExcelData(FILE_PATH, SHEET, 13, 2));
//		treatmentPlansPage.closeTreatmentPopup();
//		treatmentPlansPage.clickOnFindingsBtn();
//		treatmentPlansPage.verifyToothOnFinding("23");
//		treatmentPlansPage.verifyTissuesInFinding("23", ReadExcelData.readExcelData(FILE_PATH, SHEET, 11, 1));
//		treatmentPlansPage.verifyTissuesInFinding("23", ReadExcelData.readExcelData(FILE_PATH, SHEET, 12, 1));
//		treatmentPlansPage.verifyTissuesInFinding("23", ReadExcelData.readExcelData(FILE_PATH, SHEET, 13, 1));
//		treatmentPlansPage.verifyProvisionalsInFinding("23", ReadExcelData.readExcelData(FILE_PATH, SHEET, 11, 2));
//		treatmentPlansPage.verifyProvisionalsInFinding("23", ReadExcelData.readExcelData(FILE_PATH, SHEET, 12, 2));
//		treatmentPlansPage.verifyProvisionalsInFinding("23", ReadExcelData.readExcelData(FILE_PATH, SHEET, 13, 2));
//		treatmentPlansPage.verifySelectBtn("23");
//		Assert.assertTrue(basePage.verification().contains("Treatment Plans"));
//	}

//	@Test(enabled = true,priority = 3)
//	public void createOralExamMultipleTeeth() {
//		extentReport.logger.log(LogStatus.PASS, CREATE_ORAL_EXAM_MULTIPLE_TEETH);
//		patientDashboardPage.clickOnTreatmentPlanAddBtn();
//		basePatientLifeCyclePage.clickOnAlert();
//		treatmentPlansPage.clickOnNewTeethBtn();
//		oralExamsPage.selectMultipleTeeth("Mixed", "18");
//		oralExamsPage.selectMultipleTeeth("Mixed", "22");
//		oralExamsPage.selectMultipleTeeth("Mixed", "21");
//		oralExamsPage.clickOnTeethImage("Mixed", "21");
//		treatmentPlansPage.verifySeletecdTeethOnPopup("18");
//		treatmentPlansPage.verifySeletecdTeethOnPopup("22");
//		treatmentPlansPage.verifySeletecdTeethOnPopup("21");
//		treatmentPlansPage.clickOnOralExamBtn();
//		// ----------
//		treatmentPlansPage.clickOnBuccalForMultipleTeeth();
//		treatmentPlansPage.clickOnProvisionals(ReadExcelData.readExcelData(FILE_PATH, SHEET, 11, 2));
//		oralExamsPage.clickOnSoftTissueOnPopup();
//		treatmentPlansPage.clickOnLip();
//		treatmentPlansPage.clickOnProvisionals(ReadExcelData.readExcelData(FILE_PATH, SHEET, 12, 2));
//		oralExamsPage.clickOnHardTissueInPopup();
//		oralExamsPage.clickOnMandibularBody();
//		treatmentPlansPage.clickOnProvisionals(ReadExcelData.readExcelData(FILE_PATH, SHEET, 13, 2));
//		oralExamsPage.enterOralExamNotes("it is for testig");
//		treatmentPlansPage.clickOnSaveOralBtn();
//		treatmentPlansPage.verifySelectedProvisinalsIntreatmentPopup(ReadExcelData.readExcelData(FILE_PATH, SHEET, 11, 2));
//		treatmentPlansPage.verifySelectedProvisinalsIntreatmentPopup(ReadExcelData.readExcelData(FILE_PATH, SHEET, 12, 2));
//		treatmentPlansPage.verifySelectedProvisinalsIntreatmentPopup(ReadExcelData.readExcelData(FILE_PATH, SHEET, 13, 2));
//		treatmentPlansPage.closeTreatmentPopup();
//		treatmentPlansPage.clickOnFindingsBtn();
//		treatmentPlansPage.verifyToothOnFinding("18");
//		treatmentPlansPage.verifyTissuesInFinding("18", ReadExcelData.readExcelData(FILE_PATH, SHEET, 11, 1));
//		treatmentPlansPage.verifyTissuesInFinding("18", ReadExcelData.readExcelData(FILE_PATH, SHEET, 12, 1));
//		treatmentPlansPage.verifyTissuesInFinding("18", ReadExcelData.readExcelData(FILE_PATH, SHEET, 13, 1));
//		treatmentPlansPage.verifyProvisionalsInFinding("18", ReadExcelData.readExcelData(FILE_PATH, SHEET, 11, 2));
//		treatmentPlansPage.verifyProvisionalsInFinding("18", ReadExcelData.readExcelData(FILE_PATH, SHEET, 12, 2));
//		treatmentPlansPage.verifyProvisionalsInFinding("18", ReadExcelData.readExcelData(FILE_PATH, SHEET, 13, 2));
//		treatmentPlansPage.verifySelectBtn("18");
//		Assert.assertTrue(basePage.verification().contains("Treatment Plans"));
//	}

//	@Test(enabled = true,priority = 4, dependsOnMethods = { "createOralExamSingleTeeth","createOralExamMultipleTeeth" })
//	public void addTreatmentFromFindings() {
//		extentReport.logger.log(LogStatus.PASS, START_TREATMENT_FROM_FINDINGS);
//		patientDashboardPage.clickOnTreatmentPlanAddBtn();
//		basePatientLifeCyclePage.clickOnAlert();
//		treatmentPlansPage.selectTeethInFindings("23");
//		treatmentPlansPage.selectTeethInFindings("18");
//		treatmentPlansPage.clickOnCreatebtn();
//		treatmentPlansPage.clickOnConsultationXRays();
//		treatmentPlansPage.clickOnTreatments(ReadExcelData.readExcelData(FILE_PATH, SHEET, 1, 1));
//		treatmentPlansPage.clickOnTreatments(ReadExcelData.readExcelData(FILE_PATH, SHEET, 2, 1));
//		treatmentPlansPage.saveTreatment();
//		treatmentPlansPage.selectedTreamentInIputList(ReadExcelData.readExcelData(FILE_PATH, SHEET, 1, 2));
//		treatmentPlansPage.selectedTreamentInIputList(ReadExcelData.readExcelData(FILE_PATH, SHEET, 1, 2));
//		// -checking treatment plan listing -------------
//		basePatientLifeCyclePage.clickOnDashBoard();
//		basePatientLifeCyclePage.clickOnAlert();
//		patientDashboardPage.clickOnTreatmentPlanListBtn();
//		treatmentPlanListingPage.verifyDatesTreatmentPlanList();
//		treatmentPlanListingPage.selectedTreatmentInTreatmentList(ReadExcelData.readExcelData(FILE_PATH, SHEET, 1, 2),ReadExcelData.readExcelData(FILE_PATH, SHEET, 1,3));
//		treatmentPlanListingPage.selectedTreatmentInTreatmentList(ReadExcelData.readExcelData(FILE_PATH, SHEET, 2, 2),ReadExcelData.readExcelData(FILE_PATH, SHEET, 2,3));
//		// ---------checking respective actions Btn& Please pass current date in format
//		// of "dd-MM-YY"
//		treatmentPlanListingPage.verifyCopyBtn(TODAY_DATE);
//		// treatmentPlanListingPage.verifyStartBtn(todayDate);
//		treatmentPlanListingPage.verifyMarkBtn(TODAY_DATE);
//		treatmentPlanListingPage.verifyViewBtn(TODAY_DATE);
//		treatmentPlanListingPage.verifyEditBtn(TODAY_DATE);
//		treatmentPlanListingPage.verifyDeleteBtn(TODAY_DATE);
//		treatmentPlanListingPage.verifyPrintBtn(TODAY_DATE);
//		treatmentPlanListingPage.clickOnViewBtn(TODAY_DATE);
//		treatmentPlanListingPage.verifyTreatmentInViewPopup(ReadExcelData.readExcelData(FILE_PATH, SHEET, 1, 2));
//		treatmentPlanListingPage.verifyTreatmentInViewPopup(ReadExcelData.readExcelData(FILE_PATH, SHEET, 2, 2));
//		treatmentPlanListingPage.clickOnCloseBtnViewPopup();
//		Assert.assertTrue(basePage.verification().contains("Treatment Plan Listing"));
//	}

   @Test(enabled = true,priority = 3,description = "Check the Discount Functionality",dependsOnMethods = "addingTreatmentCheckInInputAndMainList")
	public void functionalityOfDiscount() {
		extentReport.logger.log(LogStatus.PASS, FUNCTIONALITY_OF_DISCOUNT);
		basePatientLifeCyclePage.clickOnAlert();
		patientDashboardPage.clickOnTreatmentPlanListBtn();
		treatmentPlanListingPage.clickOnEditBtn(TODAY_DATE);
		treatmentPlansPage.intiallyBothPriceSameDiscount(patntTrtmntPlanData.get("planGroup1_Plan1_FullName"));
		treatmentPlansPage.discountInPercentage(patntTrtmntPlanData.get("planGroup1_Plan1_FullName"));
		treatmentPlansPage.enterDiscountValue(patntTrtmntPlanData.get("planGroup1_Plan1_FullName"), "20");
		treatmentPlansPage.checkFunctionalityOfPercentageDis(patntTrtmntPlanData.get("planGroup1_Plan1_FullName"), 20);
		treatmentPlansPage.clickOnSaveBtnTreatmentInputList();
		treatmentPlansPage.verifyMsgWithoutCoupon();
		treatmentPlansPage.couponBasis(patntTrtmntPlanData.get("planGroup1_Plan1_FullName"));
		treatmentPlansPage.clickOnSaveBtnTreatmentInputList();
		treatmentPlanListingPage.verifyCouponImpactInMainList(patntTrtmntPlanData.get("planGroup1_Plan1_FullName"));
		treatmentPlanListingPage.clickOnViewBtn(TODAY_DATE);
		treatmentPlanListingPage.couponDiscountInViewPopup(patntTrtmntPlanData.get("planGroup1_Plan1_FullName"));
		treatmentPlanListingPage.clickOnCloseBtnViewPopup();
		treatmentPlanListingPage.clickOnEditBtn(TODAY_DATE);
		treatmentPlansPage.discountInAmount(patntTrtmntPlanData.get("planGroup1_Plan2_FullName"));
		treatmentPlansPage.enterDiscountValue(patntTrtmntPlanData.get("planGroup1_Plan2_FullName"), "20");
		treatmentPlansPage.checkFunctionalityOfAmountDis(patntTrtmntPlanData.get("planGroup1_Plan2_FullName"), 20);
		treatmentPlansPage.couponBasis(patntTrtmntPlanData.get("planGroup1_Plan2_FullName"));
		treatmentPlansPage.clickOnSaveBtnTreatmentInputList();
		treatmentPlanListingPage.verifyCouponImpactInMainList(patntTrtmntPlanData.get("planGroup1_Plan1_FullName"));
		treatmentPlanListingPage.clickOnViewBtn(TODAY_DATE);
		treatmentPlanListingPage.couponDiscountInViewPopup(patntTrtmntPlanData.get("planGroup1_Plan1_FullName"));
		treatmentPlanListingPage.clickOnCloseBtnViewPopup();
		Assert.assertTrue(basePage.verification().contains("Treatment Plan Listing"));
	}

    @Test(enabled = true,priority = 4, dependsOnMethods = "addingTreatmentCheckInInputAndMainList")
	public void functionalityOfCopyBtn() {
		extentReport.logger.log(LogStatus.PASS, FUNCTIONALITY_OF_COPY_BTN);
		patientDashboardPage.clickOnTreatmentPlanListBtn();
		treatmentPlanListingPage.verifyDatesTreatmentPlanList();
		treatmentPlanListingPage.clickOnCopyBtn(TODAY_DATE);
		treatmentPlanListingPage.actionBtn();
		treatmentPlanListingPage.afterCopyTreatmentInMainList(patntTrtmntPlanData.get("planGroup1_Plan1_FullName"));
		treatmentPlanListingPage.afterCopyTreatmentInMainList(patntTrtmntPlanData.get("planGroup1_Plan2_FullName"));
		treatmentPlanListingPage.afterCopyPlan();
		Assert.assertTrue(basePage.verification().contains("Treatment Plan Listing"));
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
