package com.prm.scripts;

import com.prm.pageobjects.utils.TestData;
import com.prm.patientdashboard.pageobject.BasePatientLifeCyclePage;

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
import com.prm.docdashboard.pageobject.PatientDashboardPage;
import com.prm.patientdashboard.pageobject.DiagnosticListingPage;
import com.prm.patientdashboard.pageobject.DiagnosticTestsPage;
import com.relevantcodes.extentreports.LogStatus;
import com.prm.pageobjects.utils.ExtentReport;
import com.prm.pageobjects.utils.ReadConfig;
import com.prm.pageobjects.utils.SheetTest;

public class DiagnosticTestsTestCase {

	private LoginPage loginPage = new LoginPage();
	private DoctorDashBoard doctorDashboard = new DoctorDashBoard(loginPage);
	private BasePage basePage = new BasePage(loginPage);
	private ExtentReport extentReport = new ExtentReport(loginPage, "DiagnosticTestsTestCase");
	private PatientDashboardPage patientDashboardPage = new PatientDashboardPage(loginPage);
	private BasePatientLifeCyclePage basePatientLifeCyclePage = new BasePatientLifeCyclePage(loginPage);
	private DiagnosticTestsPage diagnosticTestsPage = new DiagnosticTestsPage(loginPage);
	private DiagnosticListingPage diagnosticListingPage = new DiagnosticListingPage(loginPage);

	/*Mandatory inputs required before execution report*/
	private static final String PATIENT_NAME= TestData.getInstance().getInputData("diagnostic_test_patient_name");
	private static final String MOBILE_NUMBER = TestData.getInstance().getInputData("diagnostic_test_patient_mobile");
	private static final String DOCTOR_NAME = TestData.getInstance().getInputData("diagnostic_test_doctor_name");
	private static final String DOCTOR_NIK_NAME = TestData.getInstance().getInputData("diagnostic_test_doctor_nikName");
	private static final String CLINIC_NAME = TestData.getInstance().getInputData("diagnostic_test_clinic_name");


	/*-----------------------Messages for Extent Report---------------------------------*/
	private static final String SCRIPTS_STARTED_MSG = "@@ Test scripts has been started here @@";
	private static final String STATUS_OF_SCRIPTS = "@@ Test script has been completed @@";
	private static final String CHECKED_DIAGNOSTICS_ADD_UI = "Validating the Diagnosis Add page and Diagnosis listing page for first time without any diagnosis - #checkedDiagnosticsAddUi";
	private static final String ADD_DIAGNOSTICS = "Validating the Diagnosis Add page and Diagnosis listing page after adding a diagnosis - #addDiagnostics";
	Map<String, String> patntDiagnosticData = null;

	/* loginPage with UserName and password
	 * search for patients from the doctor dashboard
	 * verified patients will redirect at the patient dashboard*/
	@BeforeClass
	public void setup() {
		patntDiagnosticData = SheetTest.prepareData("DiagnosticData","Diagnostic","A1","Z");
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
		basePage.selectClinicFrmHeader(patntDiagnosticData.get("clinicLocation"));
		basePage.enterMobileNo(patntDiagnosticData.get("patient_mobile"));
		basePage.clickOnSearchBtn();
		basePage.clickOnPatient(patntDiagnosticData.get("patient_mobile"),patntDiagnosticData.get("patient_name"));
		Assert.assertTrue(basePage.verification().contains("Patient Dashboard"));
	}

	@BeforeMethod
	public void verifyPatientdashBoardHomePage() {
		extentReport.logger.log(LogStatus.PASS, SCRIPTS_STARTED_MSG);
		if(patientDashboardPage.dueWarningPopup.size()>0){
			patientDashboardPage.hideDueWarningPopup();
		}else {
			basePatientLifeCyclePage.clickOnAlert();
		}

	}

	/*checked left navigation module name
	* checked patient name and patient i'd is not null
	* checked header of the page,patient dashboard Btn,treatment btn and oral Exam Btn
	* checked All diagnostic present at diagnostic Add page
	* checked all webElement of the Diagnosis >> IOPAR
	* checked all webElement of the Diagnosis >> Imaging
	* checked all webElement of the Diagnosis >> Blood
	* checked all webElement of the Diagnosis >> Urine
	* checked all webElement of the Diagnosis >> Biochemical
	* checked all webElement of the Diagnosis >> Markers
	* checked webElement of diagnostic test listing page without any diagnosis */
	
	@Test(priority = 1)
	public void checkedDiagnosticsAddUi() {
		extentReport.logger.log(LogStatus.PASS, CHECKED_DIAGNOSTICS_ADD_UI);
		patientDashboardPage.clickOnDiagnosticsTestAdd();
		basePatientLifeCyclePage.clickOnAlert();
		basePatientLifeCyclePage.openCloseLeftNavigator();
		basePatientLifeCyclePage.webElementOfLeftNavigator();
		basePatientLifeCyclePage.openCloseLeftNavigator();
		basePatientLifeCyclePage.dashBoardBtnVerify();
		basePatientLifeCyclePage.verifyPatientName(patntDiagnosticData.get("patient_name"));
		basePatientLifeCyclePage.headerOnAddPage("Diagnostic Tests");
		diagnosticTestsPage.checked_Trt_oralExam_file_Btn();
		diagnosticTestsPage.checkedDiagnosisBox();
		/*-----------------iopar Ui------------------------*/
		diagnosticTestsPage.clickIopar();
		diagnosticTestsPage.checkedDiagnosisHeader("Diagnosis >> IOPAR");
		diagnosticTestsPage.checked_Save_Close_Notes();
		diagnosticTestsPage.checkedAdultSelected();
		diagnosticTestsPage.checkedAdultTeeths();
		diagnosticTestsPage.clickPedo();
		diagnosticTestsPage.checkedPedoSelected();
		diagnosticTestsPage.checkedPedoTeeths();
		diagnosticTestsPage.clickMixed();
		diagnosticTestsPage.checkedMixedSelected();
		diagnosticTestsPage.checkedMixedTeeths();
		diagnosticTestsPage.closeDiagnosisPopup();
		/*----------------Imaging ui--------------*/
		diagnosticTestsPage.clickImaging();
		diagnosticTestsPage.checkedDiagnosisHeader("Diagnosis >> Imaging");
		diagnosticTestsPage.checked_Save_Close_Notes();
		diagnosticTestsPage.checkedImagingDiagnosis();
		diagnosticTestsPage.closeDiagnosisPopup();
		/*--------------Blood ui-------------------*/
		diagnosticTestsPage.clickBlood();
		diagnosticTestsPage.checkedDiagnosisHeader("Diagnosis >> Blood");
		diagnosticTestsPage.checked_Save_Close_Notes();
		diagnosticTestsPage.checkedBloodDiagnosis();
		diagnosticTestsPage.closeDiagnosisPopup();
		/*---------------Urine ui-----------------*/
		diagnosticTestsPage.clickUrine();
		diagnosticTestsPage.checkedDiagnosisHeader("Diagnosis >> Urine");
		diagnosticTestsPage.checked_Save_Close_Notes();
		diagnosticTestsPage.checkedUrineDiagnosis();
		diagnosticTestsPage.closeDiagnosisPopup();
		/*----------------Bio-chemical ui------------*/
		diagnosticTestsPage.clickBiochemicalButton();
		diagnosticTestsPage.checkedDiagnosisHeader("Diagnosis >> Biochemical");
		diagnosticTestsPage.checked_Save_Close_Notes();
		diagnosticTestsPage.checkedBioChemicalDiagnosis();
		diagnosticTestsPage.closeDiagnosisPopup();
		/*---------------Markers ui------------------*/
		diagnosticTestsPage.clickMarkersButton();
		diagnosticTestsPage.checkedDiagnosisHeader("Diagnosis >> Markers");
		diagnosticTestsPage.checked_Save_Close_Notes();
		diagnosticTestsPage.checkedMarkersDiagnosis();
		diagnosticTestsPage.closeDiagnosisPopup();
		/*--------------Diagnosis listing----------------*/
		basePatientLifeCyclePage.clickOnDashBoard();
		basePatientLifeCyclePage.clickOnAlert();
		patientDashboardPage.clickOnDiagnosticsTestList();
		basePatientLifeCyclePage.dashBoardBtnVerify();
		basePatientLifeCyclePage.verifyAddNewBtn();
		basePatientLifeCyclePage.headerOnListPage("Diagnostic Listing");
		basePatientLifeCyclePage.verifyPatientName(patntDiagnosticData.get("patient_name"));
		/* First Time Only */
//		basePatientLifeCyclePage.noRecordMsgDisplayed();
		Assert.assertTrue(basePage.verification().contains("Diagnostic Listing"));
	}

	/*Added Diagnosis for the IOPAR and Imaging
	* checked same added diagnosis at input list
	* checked the same diagnosis at the Diagnosis listing and diagnosis listing view*/
	
	@Test(priority = 2)
	public void addDiagnostics() {
		extentReport.logger.log(LogStatus.PASS, ADD_DIAGNOSTICS);
		patientDashboardPage.clickOnDiagnosticsTestAdd();
		basePatientLifeCyclePage.clickOnAlert();
		/*-------------------adding iopar---------------*/
		diagnosticTestsPage.clickIopar();
		diagnosticTestsPage.saveDiagnosis();
		diagnosticTestsPage.withoutIoparAlert();
		diagnosticTestsPage.selectTeeth("adult", "32");
		diagnosticTestsPage.enterDiagnosisNotes("iopar testing");
		diagnosticTestsPage.saveDiagnosis();
		diagnosticTestsPage.checkedSuccessAlert();
		/*-------------------adding imaging------------*/
		diagnosticTestsPage.clickImaging();
		diagnosticTestsPage.saveDiagnosis();
		diagnosticTestsPage.withoutDiagnosisAlert();
		diagnosticTestsPage.selectDiagnosis("OPG");
		diagnosticTestsPage.enterDiagnosisNotes("Imaging testing");
		diagnosticTestsPage.saveDiagnosis();
		diagnosticTestsPage.checkedSuccessAlert();
		/*-------------------checked iopar in inputlist-------*/
		diagnosticTestsPage.checkedDiagnosisInputListData();
		diagnosticTestsPage.checkedCategory("IOPAR");
		diagnosticTestsPage.checkedInvestigation("IOPAR", "32");
		diagnosticTestsPage.checkedDeleteWithInvestigation("IOPAR", "32");
		diagnosticTestsPage.notesDiagnosisInputList("IOPAR", "32", "iopar testing");
		diagnosticTestsPage.checkedDeleteBtnInputlist("IOPAR");
		diagnosticTestsPage.checkedEditBtnInputlist("IOPAR");
		/*-------------------checked imaging in inputlist-----*/
		diagnosticTestsPage.checkedCategory("Imaging");
		diagnosticTestsPage.checkedInvestigation("Imaging", "OPG");
		diagnosticTestsPage.checkedDeleteWithInvestigation("Imaging", "OPG");
		diagnosticTestsPage.notesDiagnosisInputList("Imaging", "OPG", "Imaging testing");
		diagnosticTestsPage.checkedDeleteBtnInputlist("Imaging");
		diagnosticTestsPage.checkedEditBtnInputlist("Imaging");
		basePatientLifeCyclePage.clickOnDashBoard();
		basePatientLifeCyclePage.clickOnAlert();
		patientDashboardPage.clickOnDiagnosticsTestList();
		basePatientLifeCyclePage.verifyDates();
		basePatientLifeCyclePage.actionBtnMainList(patntDiagnosticData.get("clinicLocation"));
		basePatientLifeCyclePage.verifyPatientName(patntDiagnosticData.get("patient_name"));
		diagnosticListingPage.checkedDataName();
		diagnosticListingPage.checkedDiagnosisMainList("Imaging");
		diagnosticListingPage.investigationMainList("Imaging", "OPG");
		diagnosticListingPage.createdByMainList("Imaging", patntDiagnosticData.get("doctorNickName"));
		diagnosticListingPage.notesByMainList("Imaging", "Imaging testing");
		diagnosticListingPage.checkedDiagnosisMainList("IOPAR");
		diagnosticListingPage.investigationMainList("IOPAR", "32");
		diagnosticListingPage.createdByMainList("IOPAR", patntDiagnosticData.get("doctorNickName"));
		diagnosticListingPage.notesByMainList("IOPAR", "iopar testing");
		basePatientLifeCyclePage.clickViewBtn(patntDiagnosticData.get("clinicLocation"));
		basePatientLifeCyclePage.headerViewPopup("Diagnostics View");
		diagnosticListingPage.checkedDataNameViewPopup();
		diagnosticListingPage.verifyPatientNameInView(patntDiagnosticData.get("patient_name"));		
		diagnosticListingPage.verifyDateInView();
		diagnosticListingPage.checkDiagnosisViewPopup("Imaging");
		diagnosticListingPage.investigationView("Imaging", "OPG");
		diagnosticListingPage.createdByView("Imaging", patntDiagnosticData.get("doctorNickName"));
		diagnosticListingPage.notesView("Imaging", "Imaging testing");
		diagnosticListingPage.checkDiagnosisViewPopup("IOPAR");
		diagnosticListingPage.investigationView("IOPAR", "32");
		diagnosticListingPage.createdByView("IOPAR", patntDiagnosticData.get("doctorNickName"));
		diagnosticListingPage.notesView("IOPAR", "iopar testing");
		basePatientLifeCyclePage.closeViewPopup();
		Assert.assertTrue(basePage.verification().contains("Diagnostic Listing"));
		loginPage.softAssert().assertAll();
	}

	/*Added Diagnosis for the IOPAR and Imaging
	* checked same added diagnosis at input list
	* checked the same diagnosis at the Diagnosis listing and diagnosis listing view*/
	
	@Test(priority = 3)
	public void editDiagnostics() {
		extentReport.logger.log(LogStatus.PASS, ADD_DIAGNOSTICS);
		patientDashboardPage.clickOnDiagnosticsTestAdd();
		basePatientLifeCyclePage.clickOnAlert();
		diagnosticTestsPage.clickEditBtnInputlist("Imaging");
		diagnosticTestsPage.checkSelectedInvestigation("OPG");
		diagnosticTestsPage.verifyEnteredDiagnosisNotes();
		diagnosticTestsPage.selectDiagnosis("CBCT");
		diagnosticTestsPage.enterDiagnosisNotes(" CBCT Notes");
		diagnosticTestsPage.saveDiagnosis();
		diagnosticTestsPage.checkedUpdateSuccessAlert();
		/*-------------------checked Imaging in Input list-------*/
		diagnosticTestsPage.checkedDiagnosisInputListData();
		diagnosticTestsPage.checkedCategory("Imaging");
		diagnosticTestsPage.checkedInvestigation("Imaging", "OPG");
		diagnosticTestsPage.checkedInvestigation("Imaging", "CBCT");
		diagnosticTestsPage.checkedDeleteWithInvestigation("Imaging", "OPG");
		diagnosticTestsPage.checkedDeleteWithInvestigation("Imaging", "CBCT");
		diagnosticTestsPage.notesDiagnosisInputList("Imaging", "OPG", "Imaging testing CBCT Notes");
		diagnosticTestsPage.checkedDeleteBtnInputlist("Imaging");
		diagnosticTestsPage.checkedEditBtnInputlist("Imaging");
		basePatientLifeCyclePage.clickOnDashBoard();
		basePatientLifeCyclePage.clickOnAlert();
		/*-------------------checked Imaging in Main list-------*/
		patientDashboardPage.clickOnDiagnosticsTestList();
		basePatientLifeCyclePage.verifyDates();
		basePatientLifeCyclePage.actionBtnMainList(patntDiagnosticData.get("clinicLocation"));
		basePatientLifeCyclePage.verifyPatientName(patntDiagnosticData.get("patient_name"));
		diagnosticListingPage.checkedDataName();
		diagnosticListingPage.checkedDiagnosisMainList("Imaging");
		diagnosticListingPage.investigationMainList("Imaging", "OPG");
		diagnosticListingPage.investigationMainList("Imaging", "CBCT");
		diagnosticListingPage.createdByMainList("Imaging", patntDiagnosticData.get("doctorNickName"));
		diagnosticListingPage.notesByMainList("Imaging", "Imaging testing CBCT Notes");
		basePatientLifeCyclePage.clickViewBtn(patntDiagnosticData.get("clinicLocation"));
		basePatientLifeCyclePage.headerViewPopup("Diagnostics View");
		diagnosticListingPage.checkedDataNameViewPopup();
		diagnosticListingPage.verifyPatientNameInView(patntDiagnosticData.get("patient_name"));
		diagnosticListingPage.verifyDateInView();
		diagnosticListingPage.checkDiagnosisViewPopup("Imaging");
		diagnosticListingPage.investigationView("Imaging", "OPG");
		diagnosticListingPage.investigationView("Imaging", "CBCT");
		diagnosticListingPage.createdByView("Imaging", patntDiagnosticData.get("doctorNickName"));
		diagnosticListingPage.notesView("Imaging", "Imaging testing CBCT Notes");
		basePatientLifeCyclePage.closeViewPopup();
		Assert.assertTrue(basePage.verification().contains("Diagnostic Listing"));
		loginPage.softAssert().assertAll();
	}
	
	/*Deleted Diagnosis for the IOPAR and Imaging
	* checked same added diagnosis at input list
	* checked the same diagnosis at the Diagnosis listing and diagnosis listing view*/
	
	@Test(priority = 4)
	public void deleteDiagnostics() {
		extentReport.logger.log(LogStatus.PASS, ADD_DIAGNOSTICS);
		patientDashboardPage.clickOnDiagnosticsTestAdd();
		basePatientLifeCyclePage.clickOnAlert();
		diagnosticTestsPage.clickEditBtnInputlist("Imaging");
		diagnosticTestsPage.checkSelectedInvestigation("OPG");
		diagnosticTestsPage.verifyEnteredDiagnosisNotes();
		diagnosticTestsPage.selectDiagnosis("CBCT");
		diagnosticTestsPage.enterDiagnosisNotes(" CBCT Notes");
		diagnosticTestsPage.saveDiagnosis();
		diagnosticTestsPage.checkedUpdateSuccessAlert();
		/*-------------------checked Imaging in Input list-------*/
		diagnosticTestsPage.checkedDiagnosisInputListData();
		diagnosticTestsPage.checkedCategory("Imaging");
		diagnosticTestsPage.checkedInvestigation("Imaging", "OPG");
		diagnosticTestsPage.checkedInvestigation("Imaging", "CBCT");
		diagnosticTestsPage.checkedDeleteWithInvestigation("Imaging", "OPG");
		diagnosticTestsPage.checkedDeleteWithInvestigation("Imaging", "CBCT");
		diagnosticTestsPage.notesDiagnosisInputList("Imaging", "OPG", "Imaging testing CBCT Notes CBCT Notes");
		diagnosticTestsPage.checkedDeleteBtnInputlist("Imaging");
		diagnosticTestsPage.checkedEditBtnInputlist("Imaging");
		basePatientLifeCyclePage.clickOnDashBoard();
		basePatientLifeCyclePage.clickOnAlert();
		/*-------------------checked Imaging in Main list-------*/
		patientDashboardPage.clickOnDiagnosticsTestList();
		basePatientLifeCyclePage.verifyDates();
		basePatientLifeCyclePage.actionBtnMainList(patntDiagnosticData.get("clinicLocation"));
		basePatientLifeCyclePage.verifyPatientName(patntDiagnosticData.get("patient_name"));
		diagnosticListingPage.checkedDataName();
		diagnosticListingPage.checkedDiagnosisMainList("Imaging");
		diagnosticListingPage.investigationMainList("Imaging", "OPG");
		diagnosticListingPage.investigationMainList("Imaging", "CBCT");
		diagnosticListingPage.createdByMainList("Imaging", patntDiagnosticData.get("doctorNickName"));
		diagnosticListingPage.notesByMainList("Imaging", "Imaging testing CBCT Notes");
		basePatientLifeCyclePage.clickViewBtn(patntDiagnosticData.get("clinicLocation"));
		basePatientLifeCyclePage.headerViewPopup("Diagnostics View");
		diagnosticListingPage.checkedDataNameViewPopup();
		diagnosticListingPage.verifyPatientNameInView(patntDiagnosticData.get("patient_name"));
		diagnosticListingPage.verifyDateInView();
		diagnosticListingPage.checkDiagnosisViewPopup("Imaging");
		diagnosticListingPage.investigationView("Imaging", "OPG");
		diagnosticListingPage.investigationView("Imaging", "CBCT");
		diagnosticListingPage.createdByView("Imaging", patntDiagnosticData.get("doctorNickName"));
		diagnosticListingPage.notesView("Imaging", "Imaging testing CBCT Notes CBCT Notes");
		basePatientLifeCyclePage.closeViewPopup();
		Assert.assertTrue(basePage.verification().contains("Diagnostic Listing"));
		loginPage.softAssert().assertAll();
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
