package com.prm.scripts;

import com.prm.pageobjects.utils.TestData;
import com.prm.patientdashboard.pageobject.BasePatientLifeCyclePage;

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
import com.prm.docdashboard.pageobject.BasePage;
import com.prm.docdashboard.pageobject.DoctorDashBoard;
import com.prm.docdashboard.pageobject.LoginPage;
import com.prm.docdashboard.pageobject.PatientDashboardPage;
import com.prm.pageobjects.utils.ExtentReport;
import com.prm.pageobjects.utils.ReadConfig;
import com.prm.pageobjects.utils.ReadExcelData;
import com.prm.pageobjects.utils.SheetTest;
import com.prm.patientdashboard.pageobject.PrescriptionListingPage;
import com.prm.patientdashboard.pageobject.PrescriptionPage;
import com.relevantcodes.extentreports.LogStatus;

public class PrescriptionTestCase {

	private LoginPage loginPage = new LoginPage();
	private DoctorDashBoard doctorDashboard = new DoctorDashBoard(loginPage);
	private BasePage basePage = new BasePage(loginPage);
	private ExtentReport extentReport = new ExtentReport(loginPage, "PrescriptionTestCase");
	private PatientDashboardPage patientDashboardPage = new PatientDashboardPage(loginPage);
	private BasePatientLifeCyclePage basePatientLifeCyclePage = new BasePatientLifeCyclePage(loginPage);
	private PrescriptionPage prescriptionPage = new PrescriptionPage(loginPage);
	private PrescriptionListingPage prescriptionListingPage = new PrescriptionListingPage(loginPage);
	
	
	/*
	 * Storing mandatory input required to run Prescription test cases
	 * or please update the mandatory input before running the test cases
	 */
	/*input data for validation*/
	private static final String FILE_PATH = TestData.getInstance().getInputData("prescription_file_path");
	private static final String SHEET = TestData.getInstance().getInputData("prescription_file_sheet_name");
	private static final String PATIENT_NAME= TestData.getInstance().getInputData("prescription_patient_name");
	private static final String MOBILE_NUMBER = TestData.getInstance().getInputData("prescription_patient_mobile");
	private static final String DOCTOR_NIK_NAME = TestData.getInstance().getInputData("prescription_doctor_nikName");
	private static final String CLINIC_NAME = TestData.getInstance().getInputData("prescription_clinic_name");
	private static final String EMAIL_ID = TestData.getInstance().getInputData("prescription_patient_email");
	
	/*
	 * Messages for every test cases which will be print on the extent report
	 */
	private static final String SCRIPTS_STARTED_MSG = "@@ Test scripts has been started here @@";
	private static final String STATUS_OF_SCRIPTS = "@@ Test script has been completed @@";
	public static final String  CHECKED_PRESCRIPTION_ADD_AND_PRESCRIPTION_LiSTING_UI = "Validated Prescription add and Prescription listing page without having any prescription of the Patient -#checkedPrescriptionAddAndPrescriptionListingUi";
	private static final String ADD_PRESCRIPTION = "Validated Prescription Add Page and Prescription listing when we add Prescription on it -#addPrescription";
	private static final String CHECKED_PRESCRIPTION_LISTING_AFTER_ADD_PRESCRIPTION = "Validated Prescription listing Page after adding Prescription -#checkedPrescriptionAddPrescriptionListingUi";
	private static final String EDIT_PRESCRIPTION = "Validated data on all fileds when we click on Edit button and also change data after change validate on input and Prescription listing of modified data of prescription -#editPrescription";
	private static final String DELETE_PRESCRIPTION_FROM_INPUT_LIST = "Validated when we delete same Prescription from Input List -#deletePrescriptionFromInputList";
	private static final String DELETE_PRESCRIPTION_FROM_PRESCRIPTION_LISTING = "Validated when we delete same Prescription from Prescription listing -#deletePrescriptionFromPrescriptionListing";
	private static final String VALIDATION_MSGS_ON_ADD_PRESCRIPTION_PAGE = "Validated All mandatory fields with error Message -#validationMsgsOnAddPrescriptionPage";
	
	/*
     * Module and Sheet Name for getting Data from Google sheet
     */
    final String MODULE_NAME="Prescription";
    final String GOOGLE_SHEET_NAME="PrescriptionTestData";
    final String Product_SALE_PAGE_TITLE="Product Sales";
    
    /*
     * Initalizing Map for storing Google Sheet data for the test
     */
    private Map<String, String> patntPresData;
    private Map<String,String> patntPresData1;
   
	
	/*
	 * loginPage with UserName and Password
	 * search for patients from the doctor dashboard
	 * verified patients will redirect at the patient dashboard
	 */
	@BeforeClass
	public void setup() {
		patntPresData = prepareData("MODULE_NAME", "GOOGLE_SHEET_NAME", "A2", "Q2");
		patntPresData1 = prepareData("MODULE_NAME", "GOOGLE_SHEET_NAME", "A3", "Q3");
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
		basePage.selectClinicFrmHeader(CLINIC_NAME);
		basePage.enterMobileNo(MOBILE_NUMBER);
		basePage.clickOnSearchBtn();
		basePage.clickOnPatient(MOBILE_NUMBER, PATIENT_NAME);
		Assert.assertTrue(basePage.verification().contains("Patient Dashboard"));
	}

	@BeforeMethod
	public void verifyPatientDashBoardHomePage() {
		extentReport.logger.log(LogStatus.PASS, SCRIPTS_STARTED_MSG);
		if(patientDashboardPage.dueWarningPopup.size()>0){
			patientDashboardPage.hideDueWarningPopup();
		}else {
			basePatientLifeCyclePage.clickOnAlert();
		}
	}
	
	public Map<String, String> prepareData(String moduleName, String sheetName, String rowRange, String colRange)
	{
		Map<String, String> regData = null;
		try {
		   regData = SheetTest.getDataFromGoogleSheet(moduleName, sheetName, rowRange, colRange);
		} catch (GeneralSecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return regData;
	}
	
	/*
	 * checked header of the Prescription page
	 * checked Prescription add and Prescription listing page without any Prescription when we redirect for the first time
	 * checked the patient name and id should not be "null" or "NA"
	 * checked patient Dashboard, Save, Reset and Cancel button.
	 * checked left side navigation all button at both Prescription add and Prescription listing page.
	 * checked Prescription listing page header.
	 * checked no record message displayed on Prescription listing page for first time redirection.
	 * checked all fields name present in Add Prescription page.
	 * checked add prescription button on prescription listing page.
	 */
	@Test(enabled = true, priority = 1)
	public void checkedPrescriptionAddAndPrescriptionListingUi() {
		extentReport.logger.log(LogStatus.PASS, CHECKED_PRESCRIPTION_ADD_AND_PRESCRIPTION_LiSTING_UI);
		patientDashboardPage.clickOnPrescriptionTestAdd();
		basePatientLifeCyclePage.clickOnAlert();
		basePatientLifeCyclePage.headerOnAddPage("Add Prescription");
		basePatientLifeCyclePage.openCloseLeftNavigator();
		basePatientLifeCyclePage.webElementOfLeftNavigator();
		basePatientLifeCyclePage.openCloseLeftNavigator();
		basePatientLifeCyclePage.dashBoardBtnVerify();
		basePatientLifeCyclePage.verifyPatientName(PATIENT_NAME);
		/* Right Navigation */
		prescriptionPage.checkSave();
		prescriptionPage.checkCancel();
		prescriptionPage.checkReset();
		/* Right column */
		prescriptionPage.checkBrandAndGenericName();
		prescriptionPage.checkStrength();
		prescriptionPage.checkDuration();
		prescriptionPage.chckStrengthDropDownWebelemet();
		prescriptionPage.chckDurationDropDownWebelement();
		prescriptionPage.chckRouteDropDownWebelement();
		prescriptionPage.checkAllCheckbox();
		prescriptionPage.checkInstruction();
		prescriptionPage.checkNotes();
		Assert.assertTrue(basePage.verification().contains("Add Prescription"));
		/*verify all elements when we go first time on Prescription listing */
		basePatientLifeCyclePage.clickOnDashBoard();
		basePatientLifeCyclePage.clickOnAlert();
		patientDashboardPage.clickOnPrescriptionTestList();
		basePatientLifeCyclePage.headerOnListPage("Prescription Listing");
		basePatientLifeCyclePage.openCloseLeftNavigator();
		basePatientLifeCyclePage.webElementOfLeftNavigator();
		basePatientLifeCyclePage.openCloseLeftNavigator();
		basePatientLifeCyclePage.verifyPatientName(PATIENT_NAME);
		basePatientLifeCyclePage.dashBoardBtnVerify();
		prescriptionListingPage.verifyNoRecordFoundMessage();
		prescriptionListingPage.verifyAddNewBtn();
		Assert.assertTrue(basePage.verification().contains("Prescription Listing"));
	}

	/*
	 * Added Prescription for the patient.
	 * checked same Prescription on the input listing shown in Add Prescription Page.
	 * checked Edit and Cancel button same Prescription on the input listing.
	 * checked same Prescription on the Prescription listing Page.
	 * checked same Prescription on the View pop up shown in Prescription listing Page.
	 * validated all action button present at the both Prescription add and Prescription listing.
	 */
	@Test(enabled = true, priority = 2)
	public void addPrescription() {
		extentReport.logger.log(LogStatus.PASS, ADD_PRESCRIPTION);
		patientDashboardPage.clickOnPrescriptionTestAdd();
		basePatientLifeCyclePage.clickOnAlert();
		basePatientLifeCyclePage.headerOnAddPage("Add Prescription");
		prescriptionPage.addBrandName(patntPresData.get("brand_name"));
		prescriptionPage.addStrength(patntPresData.get("strength"));
		prescriptionPage.addDuration(patntPresData.get("duration"));
		prescriptionPage.addGenericName(patntPresData.get("generic_name"));
		prescriptionPage.selectDrug(patntPresData.get("drug"));
		prescriptionPage.selectDuration(patntPresData.get("duration_dropdown"));
		prescriptionPage.selectRoute(patntPresData.get("route"));
		prescriptionPage.clickOnMorning();
		prescriptionPage.clickOnAfternoon();
		prescriptionPage.clickOnNight();
		prescriptionPage.beforeFood();
		prescriptionPage.enterNote(patntPresData.get("notes"));
		prescriptionPage.clickOnSaveBtn();
		prescriptionPage.checkedInputListDataName();
		prescriptionPage.verifyPrescriptionInputListData(patntPresData.get("brand_name"));
		prescriptionPage.verifyGenericNameInputListData(patntPresData.get("generic_name"));
		prescriptionPage.verifyStrengthInputListData(patntPresData.get("strength"),patntPresData.get("drug"));
		prescriptionPage.verifyDurationInputListData(patntPresData.get("duration"),patntPresData.get("duration_dropdown"));
		prescriptionPage.verifyPrescriptionInputListData(patntPresData.get("route"));
		prescriptionPage.verifyPrescriptionInputListData(patntPresData.get("instruction"));
		prescriptionPage.verifyNotesInputList(patntPresData.get("brand_name"),patntPresData.get("notes"));
		prescriptionPage.clickEditInputList(patntPresData.get("brand_name"));
		prescriptionPage.cancelInputList(patntPresData.get("brand_name"));
		prescriptionPage.clickEditInputList(patntPresData.get("brand_name"));
		prescriptionPage.clickOnSaveBtn();
		basePatientLifeCyclePage.clickOnAlert();
		prescriptionPage.actionBtnInputList(patntPresData.get("brand_name"));
		basePatientLifeCyclePage.clickOnDashBoard();
		basePatientLifeCyclePage.clickOnAlert();
		patientDashboardPage.clickOnPrescriptionTestList();
		basePatientLifeCyclePage.headerOnListPage("Prescription Listing");
		prescriptionListingPage.verifyPrescriptionListHomePage();
		prescriptionListingPage.checkedMainListDataName();
		prescriptionListingPage.prescriptionInMainList(patntPresData.get("brand_name"),patntPresData.get("brand_name"));
		prescriptionListingPage.genericNameInMainList(patntPresData.get("brand_name"),patntPresData.get("generic_name"));
		prescriptionListingPage.strengthMainList(patntPresData.get("brand_name"),patntPresData.get("strength"),patntPresData.get("drug"));
		prescriptionListingPage.durationMainList(patntPresData.get("brand_name"),patntPresData.get("duration"),patntPresData.get("duration_dropdown"));
		prescriptionListingPage.prescriptionInMainList(patntPresData.get("brand_name"),patntPresData.get("route"));
		prescriptionListingPage.prescriptionInMainList(patntPresData.get("brand_name"),patntPresData.get("instruction"));
		prescriptionListingPage.prescriptionInMainList(patntPresData.get("brand_name"),DOCTOR_NIK_NAME);
		prescriptionListingPage.clickViewMainList(patntPresData.get("brand_name"));
		prescriptionListingPage.verifyHeaderInView("Patient Prescription Detail");
		prescriptionListingPage.dataNameInView();
		prescriptionListingPage.checkedPrescriptionInView(patntPresData.get("brand_name"),patntPresData.get("brand_name"));
		prescriptionListingPage.checkedGenericNameInView(patntPresData.get("brand_name"),patntPresData.get("generic_name"));
		prescriptionListingPage.strengthInView(patntPresData.get("brand_name"),patntPresData.get("strength"),patntPresData.get("drug"));
		prescriptionListingPage.durationInView(patntPresData.get("brand_name"),patntPresData.get("duration"),patntPresData.get("duration_dropdown"));
		prescriptionListingPage.checkedPrescriptionInView(patntPresData.get("brand_name"),patntPresData.get("route"));
		prescriptionListingPage.checkedPrescriptionInView(patntPresData.get("brand_name"),patntPresData.get("instruction"));
		prescriptionListingPage.checkedPrescriptionInView(patntPresData.get("brand_name"),DOCTOR_NIK_NAME);
		prescriptionListingPage.notesInView(patntPresData.get("brand_name"),patntPresData.get("notes"));
		prescriptionListingPage.closeViewPopup();
		Assert.assertTrue(basePage.verification().contains("Prescription Listing"));
	}

	/*
	 * checked Prescription on Prescription listing which were added in above test case.
	 * checked all button shown against Added Prescription on Prescription listing.
	 * In view checked all data like pop up heading patient name date and all data.
	 */			
	@Test(enabled = true, priority = 3)
	public void checkedPrescriptionListingAfterAddPrescription() {
		extentReport.logger.log(LogStatus.PASS, CHECKED_PRESCRIPTION_LISTING_AFTER_ADD_PRESCRIPTION);
		patientDashboardPage.clickOnPrescriptionTestList();
		basePatientLifeCyclePage.headerOnListPage("Prescription Listing");
		basePatientLifeCyclePage.dashBoardBtnVerify();
		prescriptionListingPage.verifyAddNewBtn();
		basePatientLifeCyclePage.verifyPatientName(PATIENT_NAME);
		basePatientLifeCyclePage.verifyDates();
		prescriptionListingPage.verifyClinicInPrescriptionListing(patntPresData.get("brand_name"), CLINIC_NAME);
		prescriptionListingPage.prescriptionInMainList(patntPresData.get("brand_name"),patntPresData.get("brand_name"));
		prescriptionListingPage.genericNameInMainList(patntPresData.get("brand_name"),patntPresData.get("generic_name"));
		prescriptionListingPage.strengthMainList(patntPresData.get("brand_name"),patntPresData.get("strength"),patntPresData.get("drug"));
		prescriptionListingPage.durationMainList(patntPresData.get("brand_name"),patntPresData.get("duration"),patntPresData.get("duration_dropdown"));
		prescriptionListingPage.prescriptionInMainList(patntPresData.get("brand_name"),patntPresData.get("route"));
		prescriptionListingPage.prescriptionInMainList(patntPresData.get("brand_name"),patntPresData.get("instruction"));
		prescriptionListingPage.prescriptionInMainList(patntPresData.get("brand_name"),DOCTOR_NIK_NAME);
		prescriptionListingPage.verifyEditBtnMainList(patntPresData.get("brand_name"));
		prescriptionListingPage.verifyPrintBtnMainList(patntPresData.get("brand_name"));
		prescriptionListingPage.verifySendBtnMainList(patntPresData.get("brand_name"));
		basePatientLifeCyclePage.clickViewBtn(CLINIC_NAME);
		prescriptionListingPage.verifyHeaderInView("Patient Prescription Detail");
		prescriptionListingPage.dataNameInView();
		prescriptionListingPage.verifyPatientNameInView(PATIENT_NAME);
		prescriptionListingPage.verifyDateInViewPopUp();
		prescriptionListingPage.checkedPrescriptionInView(patntPresData.get("brand_name"),patntPresData.get("brand_name"));
		prescriptionListingPage.checkedGenericNameInView(patntPresData.get("brand_name"),patntPresData.get("generic_name"));
		prescriptionListingPage.strengthInView(patntPresData.get("brand_name"),patntPresData.get("strength"),patntPresData.get("drug"));
		prescriptionListingPage.durationInView(patntPresData.get("brand_name"),patntPresData.get("duration"),patntPresData.get("duration_dropdown"));
		prescriptionListingPage.checkedPrescriptionInView(patntPresData.get("brand_name"),patntPresData.get("route"));
		prescriptionListingPage.checkedPrescriptionInView(patntPresData.get("brand_name"),patntPresData.get("instruction"));
		prescriptionListingPage.checkedPrescriptionInView(patntPresData.get("brand_name"),DOCTOR_NIK_NAME);
		prescriptionListingPage.notesInView(patntPresData.get("brand_name"),patntPresData.get("notes"));
		prescriptionListingPage.closeViewPopup();
		prescriptionListingPage.clickSendBtnMainList(ReadExcelData.readExcelData(FILE_PATH, SHEET, 1, 1));
		prescriptionListingPage.verifyHeaderInView("Update Patient Vitals");
		prescriptionListingPage.dataNameInSend();
		prescriptionListingPage.verifyPatientNameInSend(PATIENT_NAME);
		prescriptionListingPage.verifyPatientMobileInSend(MOBILE_NUMBER);
		prescriptionListingPage.addEmailInSend(EMAIL_ID);
		prescriptionListingPage.clickSaveBtnInSend();
		prescriptionListingPage.successfullyMessage();
		Assert.assertTrue(basePage.verification().contains("Prescription Listing"));
	}

	/*
	 * Edit Prescription which is previously added.
	 * checked all data coming into all fields or not when we click on edit button on Add Prescription Page.
	 * modified same prescription and checked on Input list and Prescription listing Page.
	 * checked modified prescription on Main list and View Pop on Prescription listing Page.
	 */
	@Test(enabled = true, priority = 4)
	public void editPrescription() {
		extentReport.logger.log(LogStatus.PASS, EDIT_PRESCRIPTION);
		patientDashboardPage.clickOnPrescriptionTestAdd();
		basePatientLifeCyclePage.clickOnAlert();
		basePatientLifeCyclePage.headerOnAddPage("Add Prescription");
		prescriptionPage.clickEditInputList(patntPresData.get("brand_name"));
		basePatientLifeCyclePage.headerOnAddPage("Add Prescription");
		prescriptionPage.getBrandName(patntPresData.get("brand_name"));
		prescriptionPage.getStrength(patntPresData.get("strength"));
		prescriptionPage.getDuration(patntPresData.get("duration"));
		prescriptionPage.getGenericName(patntPresData.get("generic_name"));
		prescriptionPage.getDrug(patntPresData.get("drug"));
		prescriptionPage.getDurationDrpDwn(patntPresData.get("duration_dropdown"));
		prescriptionPage.getRoute(patntPresData.get("route"));
		prescriptionPage.getNotes(patntPresData.get("route"));
		prescriptionPage.addBrandName(patntPresData1.get("brand_name"));
		prescriptionPage.addStrength(patntPresData1.get("strength"));
		prescriptionPage.addDuration(patntPresData1.get("duration"));
		prescriptionPage.addGenericName(patntPresData1.get("generic_name"));
		prescriptionPage.selectDrug(patntPresData1.get("drug"));
		prescriptionPage.selectDuration(patntPresData1.get("duration_dropdown"));
		prescriptionPage.selectRoute(patntPresData1.get("route"));
		prescriptionPage.clickOnMorning();
		prescriptionPage.clickOnAfternoon();
		prescriptionPage.beforeFood();
		prescriptionPage.enterNote(patntPresData1.get("notes"));
		prescriptionPage.clickOnSaveBtn();
		basePatientLifeCyclePage.clickOnAlert();
		prescriptionPage.verifyPrescriptionInputListData(patntPresData1.get("brand_name"));
		prescriptionPage.verifyGenericNameInputListData(patntPresData1.get("generic_name"));
		prescriptionPage.verifyStrengthInputListData(patntPresData1.get("strength"),patntPresData1.get("drug"));
		prescriptionPage.verifyDurationInputListData(patntPresData1.get("duration"),patntPresData1.get("duration_dropdown"));
		prescriptionPage.verifyPrescriptionInputListData(patntPresData1.get("route"));
		prescriptionPage.verifyPrescriptionInputListData(patntPresData1.get("instruction"));
		prescriptionPage.verifyNotesInputList(patntPresData1.get("brand_name"),patntPresData1.get("notes"));
		basePatientLifeCyclePage.clickOnDashBoard();
		basePatientLifeCyclePage.clickOnAlert();
		patientDashboardPage.clickOnPrescriptionTestList();
		basePatientLifeCyclePage.headerOnListPage("Prescription Listing");
		prescriptionListingPage.prescriptionInMainList(patntPresData1.get("brand_name"),patntPresData1.get("brand_name"));
		prescriptionListingPage.genericNameInMainList(patntPresData1.get("brand_name"),patntPresData1.get("generic_name"));
		prescriptionListingPage.strengthMainList(patntPresData1.get("brand_name"),patntPresData1.get("strength"),patntPresData1.get("drug"));
		prescriptionListingPage.durationMainList(patntPresData1.get("brand_name"),patntPresData1.get("duration"),patntPresData1.get("duration_dropdown"));
		prescriptionListingPage.prescriptionInMainList(patntPresData1.get("brand_name"),patntPresData1.get("route"));
		prescriptionListingPage.prescriptionInMainList(patntPresData1.get("brand_name"),patntPresData1.get("instruction"));
		prescriptionListingPage.prescriptionInMainList(patntPresData1.get("brand_name"),DOCTOR_NIK_NAME);
		prescriptionListingPage.clickViewMainList(patntPresData1.get("brand_name"));
		prescriptionListingPage.verifyHeaderInView("Patient Prescription Detail");
		prescriptionListingPage.checkedPrescriptionInView(patntPresData1.get("brand_name"),patntPresData1.get("brand_name"));
		prescriptionListingPage.checkedGenericNameInView(patntPresData1.get("brand_name"),patntPresData1.get("generic_name"));
		prescriptionListingPage.strengthInView(patntPresData1.get("brand_name"),patntPresData1.get("strength"),patntPresData1.get("drug"));
		prescriptionListingPage.durationInView(patntPresData1.get("brand_name"),patntPresData1.get("duration"),patntPresData1.get("duration_dropdown"));
		prescriptionListingPage.checkedPrescriptionInView(patntPresData1.get("brand_name"),patntPresData1.get("route"));
		prescriptionListingPage.checkedPrescriptionInView(patntPresData1.get("brand_name"),patntPresData1.get("instruction"));
		prescriptionListingPage.checkedPrescriptionInView(patntPresData1.get("brand_name"),DOCTOR_NIK_NAME);
		prescriptionListingPage.notesInView(patntPresData1.get("brand_name"),patntPresData1.get("notes"));
		prescriptionListingPage.closeViewPopup();
		Assert.assertTrue(basePage.verification().contains("Prescription Listing"));
	}

	/*
	 * Delete Prescription which is previously added/edit.
	 * checked whether prescription is deleted or not from Input list shown on Add Prescription Page.
	 * checked whether prescription is deleted or not from Main list and View of Main list shown on Prescription listing Page.
	 */								
	@Test(enabled = true, priority = 5)
	public void deletePrescriptionFromInputList() {
		extentReport.logger.log(LogStatus.PASS, DELETE_PRESCRIPTION_FROM_INPUT_LIST);
		patientDashboardPage.clickOnPrescriptionTestAdd();
		basePatientLifeCyclePage.clickOnAlert();
		basePatientLifeCyclePage.headerOnAddPage("Add Prescription");
		prescriptionPage.clickOnDeleteBtn(patntPresData1.get("brand_name"));
		prescriptionPage.clickOnYesDelete();
		basePatientLifeCyclePage.headerOnAddPage("Add Prescription");
		prescriptionPage.verifyPrescriptionDeletedInputList(patntPresData1.get("brand_name"));
		basePatientLifeCyclePage.clickOnDashBoard();
		basePatientLifeCyclePage.clickOnAlert();
		patientDashboardPage.clickOnPrescriptionTestList();
		basePatientLifeCyclePage.headerOnListPage("Prescription Listing");
		prescriptionListingPage.verifyPrescriptionDeletedMainList(patntPresData1.get("brand_name"));
		prescriptionListingPage.clickViewMainList(patntPresData1.get("brand_name"));
		prescriptionListingPage.verifyPrescriptionDeletedInViewMainList(patntPresData1.get("brand_name"));
		prescriptionListingPage.closeViewPopup();
		Assert.assertTrue(basePage.verification().contains("Prescription Listing"));
	}

	/*
	 * Add Prescription for check the delete scenario and Delete Prescription from Prescription listing.
	 * checked whether prescription is deleted or not from Input list shown on Add Prescription Page.
	 * checked whether prescription is deleted or not from Main list and View of Main list shown on Prescription listing Page.
	 */
	@Test(enabled = true, priority = 6)
	public void deletePrescriptionFromPrescriptionListing() {
		extentReport.logger.log(LogStatus.PASS, DELETE_PRESCRIPTION_FROM_PRESCRIPTION_LISTING);
		patientDashboardPage.clickOnPrescriptionTestAdd();
		basePatientLifeCyclePage.clickOnAlert();
		basePatientLifeCyclePage.headerOnAddPage("Add Prescription");
		prescriptionPage.addBrandName(patntPresData.get("brand_name"));
		prescriptionPage.addStrength(patntPresData.get("strength"));
		prescriptionPage.addDuration(patntPresData.get("duration"));
		prescriptionPage.addGenericName(patntPresData.get("generic_name"));
		prescriptionPage.selectDrug(patntPresData.get("drug"));
		prescriptionPage.selectDuration(patntPresData.get("duration_dropdown"));
		prescriptionPage.selectRoute(patntPresData.get("route"));
		prescriptionPage.clickOnMorning();
		prescriptionPage.clickOnAfternoon();
		prescriptionPage.clickOnNight();
		prescriptionPage.beforeFood();
		prescriptionPage.enterNote(patntPresData.get("notes"));
		prescriptionPage.clickOnSaveBtn();
		basePatientLifeCyclePage.clickOnAlert();
		basePatientLifeCyclePage.clickOnDashBoard();
		basePatientLifeCyclePage.clickOnAlert();
		patientDashboardPage.clickOnPrescriptionTestList();
		basePatientLifeCyclePage.headerOnListPage("Prescription Listing");
		basePatientLifeCyclePage.verifyDates();
		basePatientLifeCyclePage.clickDeleteBtn(CLINIC_NAME);
		prescriptionListingPage.clickDeletePop();
		prescriptionListingPage.checkBtnAfterDelete();
		prescriptionListingPage.verifyPrescriptionDeletedMainList(patntPresData1.get("brand_name"));
		basePatientLifeCyclePage.clickViewBtn(CLINIC_NAME);
		prescriptionListingPage.dataNameInView();
		prescriptionListingPage.verifyPrescriptionDeletedInViewMainList(patntPresData1.get("brand_name"));
		basePatientLifeCyclePage.closeViewPopup();
		basePatientLifeCyclePage.clickOnDashBoard();
		basePatientLifeCyclePage.clickOnAlert();
		patientDashboardPage.clickOnPrescriptionTestAdd();
		basePatientLifeCyclePage.clickOnAlert();
		basePatientLifeCyclePage.headerOnAddPage("Add Prescription");
		prescriptionPage.verifyPrescriptionDeletedInputList(patntPresData1.get("brand_name"));
		Assert.assertTrue(basePage.verification().contains("Add Prescription"));
	}

	/*
	 * checked all scenario related to validation msgs on Add prescription Page of mandatory fields.
	 */
	@Test(enabled = true, priority = 7)
	public void validationMsgAddPrescriptionPage() {
		extentReport.logger.log(LogStatus.PASS, VALIDATION_MSGS_ON_ADD_PRESCRIPTION_PAGE);
		patientDashboardPage.clickOnPrescriptionTestAdd();
		basePatientLifeCyclePage.clickOnAlert();
		basePatientLifeCyclePage.headerOnAddPage("Add Prescription");
		prescriptionPage.clickOnSaveBtn();
		prescriptionPage.checkedErrorBrand("Enter Brand Name");
		prescriptionPage.checkedErrorGeneric("Enter Generic Name");
		prescriptionPage.checkedErrorDuration("Enter Duration");
		prescriptionPage.checkedErrorDurationDrpDwn("Select Duration Unit");
		prescriptionPage.checkedErrorDosage("Select Dosage");
		prescriptionPage.checkedErrorInstruction("Select Instruction");
		prescriptionPage.clickOnResetBtn();
		basePatientLifeCyclePage.clickOnAlert();
		prescriptionPage.addDuration(patntPresData.get("duration"));
		prescriptionPage.addGenericName(patntPresData.get("generic_name"));
		prescriptionPage.selectDuration(patntPresData.get("duration_dropdown"));
		prescriptionPage.clickOnMorning();
		prescriptionPage.beforeFood();
		prescriptionPage.clickOnSaveBtn();
		prescriptionPage.checkedErrorBrand("Enter Brand Name");
		prescriptionPage.clickOnResetBtn();
		basePatientLifeCyclePage.clickOnAlert();
		prescriptionPage.addBrandName(patntPresData.get("brand_name"));
		prescriptionPage.addGenericName(patntPresData.get("generic_name"));
		prescriptionPage.selectDuration(patntPresData.get("duration_dropdown"));
		prescriptionPage.clickOnMorning();
		prescriptionPage.beforeFood();
		prescriptionPage.clickOnSaveBtn();
		prescriptionPage.checkedErrorDuration("Enter Duration");
		prescriptionPage.clickOnResetBtn();
		basePatientLifeCyclePage.clickOnAlert();
		prescriptionPage.addBrandName(patntPresData.get("brand_name"));
		prescriptionPage.addDuration(patntPresData.get("duration"));
		prescriptionPage.selectDuration(patntPresData.get("duration_dropdown"));
		prescriptionPage.clickOnMorning();
		prescriptionPage.beforeFood();
		prescriptionPage.clickOnSaveBtn();
		prescriptionPage.checkedErrorGeneric("Enter Generic Name");
		prescriptionPage.clickOnResetBtn();
		basePatientLifeCyclePage.clickOnAlert();
		prescriptionPage.addBrandName(patntPresData.get("brand_name"));
		prescriptionPage.addGenericName(patntPresData.get("generic_name"));
		prescriptionPage.addDuration(patntPresData.get("duration"));
		prescriptionPage.clickOnMorning();
		prescriptionPage.beforeFood();
		prescriptionPage.clickOnSaveBtn();
		prescriptionPage.checkedErrorDurationDrpDwn("Select Duration Unit");
		prescriptionPage.clickOnResetBtn();
		basePatientLifeCyclePage.clickOnAlert();
		prescriptionPage.addBrandName(patntPresData.get("brand_name"));
		prescriptionPage.addDuration(patntPresData.get("duration"));
		prescriptionPage.addGenericName(patntPresData.get("generic_name"));
		prescriptionPage.selectDuration(patntPresData.get("duration_dropdown"));
		prescriptionPage.beforeFood();
		prescriptionPage.clickOnSaveBtn();
		prescriptionPage.checkedErrorDosage("Select Dosage");
		prescriptionPage.clickOnResetBtn();
		basePatientLifeCyclePage.clickOnAlert();
		prescriptionPage.addBrandName(patntPresData.get("brand_name"));
		prescriptionPage.addDuration(patntPresData.get("duration"));
		prescriptionPage.addGenericName(patntPresData.get("generic_name"));
		prescriptionPage.selectDuration(patntPresData.get("duration_dropdown"));
		prescriptionPage.clickOnMorning();
		prescriptionPage.clickOnSaveBtn();
		prescriptionPage.checkedErrorInstruction("Select Instruction");
		prescriptionPage.clickOnResetBtn();
		basePatientLifeCyclePage.clickOnAlert();
		Assert.assertTrue(basePage.verification().contains("Add Prescription"));
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
