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
import com.prm.docdashboard.pageobject.AppointmentAddPage;
import com.prm.docdashboard.pageobject.BasePage;
import com.prm.docdashboard.pageobject.DoctorDashBoard;
import com.prm.docdashboard.pageobject.LoginPage;
import com.prm.docdashboard.pageobject.PatientDashboardPage;
import com.prm.docdashboard.pageobject.PatientRegistrationPage;
import com.relevantcodes.extentreports.LogStatus;
import com.prm.pageobjects.utils.ExtentReport;
import com.prm.pageobjects.utils.ReadConfig;
import com.prm.pageobjects.utils.SheetTest;

/**
 * 
 * @author Ajit Yadav & Vibhanshu Vinay
 *
 */


public class PatientRegistrationTestCase {
	private LoginPage loginPage = new LoginPage();
	private DoctorDashBoard doctorDashboard = new DoctorDashBoard(loginPage);
	private BasePage basePage = new BasePage(loginPage);
	private PatientRegistrationPage patientRegistrationPage = new PatientRegistrationPage(loginPage);
	private AppointmentAddPage appointmentPage = new AppointmentAddPage(loginPage);
	private PatientDashboardPage patientDashboardPage = new PatientDashboardPage(loginPage);
	private ExtentReport extentReport = new ExtentReport(loginPage, "PatientRegistrationTestCase");
	private BasePatientLifeCyclePage basePatientLifeCyclePage = new BasePatientLifeCyclePage(loginPage);

	
 
	//test data
	
	private static final String PATIENT_NAME= TestData.getInstance().getInputData("patient_name");
	private static final String PATIENT_GENDER= TestData.getInstance().getInputData("patient_gender");
	private static final String PATIENT_MOBILE= TestData.getInstance().getInputData("patient_mobile");
	private static final String EmailAddress= TestData.getInstance().getInputData("email");
	private static final String PATIENT_ALTERNATE_NAME= TestData.getInstance().getInputData("patient_alternate_name");
	private static final String PATIENT_ALTERNATE_MOBILE= TestData.getInstance().getInputData("patient_alternate_mobile");
	private static final String Reaching_Time= TestData.getInstance().getInputData("reaching_time");
	private static final String PATIENT_AGE= TestData.getInstance().getInputData("patient_age");
	private static final String OCCUPATION= TestData.getInstance().getInputData("occupationOptn");
	private static final String ADDRESS= TestData.getInstance().getInputData("address");
	private static final String CITY= TestData.getInstance().getInputData("city");
	private static final String STATE= TestData.getInstance().getInputData("state");
	private static final String PIN_CODE= TestData.getInstance().getInputData("pin_code");
	private static final String MED_HIST_DIS_1= TestData.getInstance().getInputData("medical_hist_disease_1");
	private static final String MED_HIST_STS_1= TestData.getInstance().getInputData("medical_hist_status_1");
	private static final String MED_HIST_DIS_2= TestData.getInstance().getInputData("medical_hist_disease_2");
	private static final String MED_HIST_STS_2= TestData.getInstance().getInputData("medical_hist_status_2");
	private static final String OTH_MED_HIST= TestData.getInstance().getInputData("other_medical_hist");
	private static final String PAST_HIST_NOTES= TestData.getInstance().getInputData("past_hist_notes");
	private static final String ALLRGY_NOTES= TestData.getInstance().getInputData("allergy_notes");
	private static final String MEDICATN_NOTES= TestData.getInstance().getInputData("medication_notes");
	private static final String GROUP_NOTES= TestData.getInstance().getInputData("group_notes");
	private static final String PATIENT_NOTES= TestData.getInstance().getInputData("patient_notes");
	
	private static final String EDIT_PATIENT_NAME= TestData.getInstance().getInputData("edit_patient_name");
	private static final String EDIT_PATIENT_MOBILE= TestData.getInstance().getInputData("edit_patient_mobile");
	private static final String EDIT_PATIENT_EMAIL= TestData.getInstance().getInputData("edit_patient_email");
	
	
//	private static final String PATIENT_DOCTOR_NAME= TestData.getInstance().getInputData("patient_doctor_name");
	private static final String teeth[]= {TestData.getInstance().getInputData("teeth1"),TestData.getInstance().getInputData("teeth2")};
	private static final String dentalCheckUpOptn= TestData.getInstance().getInputData("dentalCheckUp_Optn1");

	//Messages for extent report
	private static final String HEADER_LISTING_PAGE = "Patient Registration";
	private static final String VERIFY_PATIENT_REGISTRATION_HOME_PAGE = "@@ Test scripts has been started here @@";
	private static final String STATUS_OF_SCRIPTS = "@@ Test scripts has been completed here @@";
	private static final String PATIENT_REGISTRATION_PAGE = "Validating patient registration page All Web Element";
	private static final String CHECK_MANDATORY_FIELDS = "Validating all mandatory fields patient name,mobile,patient alternate name,patient alternate mobile,guardian name ,guardian mobile and age";
//	private static final String BEHAVIOUR_OF_PAST_HISTORY = "Validating the teeth behavior at past history";
	private static final String CREATE_PATIENT = "Validating create new patient with best time to reach,address,medical history and patient notes";
	private static final String VALIDATE_PATIENT_DETAILS = "Validating new patient details which are provided during registration process.";
	private static final String BEHAVIOUR_OF_ALL_SELECT_OF_MIXED = "Search the patient from the global search edit the patient and update past history with all teeth";
	private static final String DENTAL_CHECKUP_MEDICATION_ALLERGIES = "Checking the Dental checkup,medication and allergies";
	private static final String VERIFY_ALREADY_EXITING_PATIENT_MSG = "Validating messages for already exiting patient and approve  appointment save scenario";
	private static final String VERIFY_EDIT_PATIENT_DASHBOARD = "Validating the editing patient from the patient dash board and checked same details prefilled at the patient registration";
	private static final String DEFAULT_COUNTRY = "India";
	private static final String SELECT_COUNTRY_TEST= "Pakistan";
	private static final String SELECT_COUNTRY= "India";
	private static final String VERIFY_STATE= "Delhi";
	private static final String DOB_FOR_TEST_AGE_CALCULATION= "08-09-1994";
	private static final String MOBILE_UNDER_TEN_DIGIT= "258951";
	Map<String, String> patntRegData = null;



	/**
	 * Assign name to extent report
	 * Launching browser
	 * enter UserName and Password
	 * Redirecting to the doctor dash board
	 */
	@BeforeClass
	public void setup() {
		patntRegData = prepareData("Registration", "PatientRegistration", "A2", "Q2");
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
	
	/*
	 * Redirecting to the patient registration page
	 */
	
	@BeforeMethod(description = VERIFY_PATIENT_REGISTRATION_HOME_PAGE, enabled = true)
	public void verifyPatientRegistrationHomePage() {
		extentReport.logger.log(LogStatus.PASS, VERIFY_PATIENT_REGISTRATION_HOME_PAGE);
		if (basePage.verification().contains("Patient Registration")) {
			loginPage.getDriver().navigate().refresh();
		} else if (basePage.verification().contains("Doctor Dashboard")) {
			doctorDashboard.clickOnPatientAdd();
		} 
		else {
				basePage.backTODoctorDashboard();
				loginPage.waitForSpinnerToDisappear();
				doctorDashboard.clickOnPatientAdd();		
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
	 * Checking patient name,age,email id,mobile,landline and max limit for landline
	 * alternate patient name,alternate patient contact,primary doctor and it first selected
	 * reach time,occupation,address,default selected country,save and approve button,reset button,cancel button,approve and appointment
	 * male gender and it should be selected,female gender,other gender,patient past history and checking if DOB entered then age should be automatically filled checked its calculation
	 */
	
	@Test(enabled=true,description = PATIENT_REGISTRATION_PAGE,priority = 1)
	public void patientRegistrationPage() {
		extentReport.logger.log(LogStatus.PASS, PATIENT_REGISTRATION_PAGE);
		basePatientLifeCyclePage.headerOnListPage(HEADER_LISTING_PAGE);
		patientRegistrationPage.checkPatientName();
		patientRegistrationPage.checkGender();
		patientRegistrationPage.checkAge();
		patientRegistrationPage.checkEmailId();
		patientRegistrationPage.checkMobile();
		patientRegistrationPage.checkSmsCallEmailCheckbox();
		patientRegistrationPage.checkLandline();
		patientRegistrationPage.landlineMaxLength();
		patientRegistrationPage.checkAlternateContactName();
		patientRegistrationPage.checkAlternateContactNo();
//		patientRegistrationPage.checkPrimaryDentist(PATIENT_DOCTOR_NAME);
		patientRegistrationPage.checkReachTime();
		patientRegistrationPage.checkOccupation();
		patientRegistrationPage.checkOccupationOthers();
		patientRegistrationPage.checkAddress();
		patientRegistrationPage.byDefaultCountry(DEFAULT_COUNTRY);
		patientRegistrationPage.selectCountry(SELECT_COUNTRY_TEST);
		patientRegistrationPage.selectCountry(SELECT_COUNTRY);
		patientRegistrationPage.verifyStatesAccordingToCountry(VERIFY_STATE);
		patientRegistrationPage.checkActionsButton();
		patientRegistrationPage.scrollUp();
		patientRegistrationPage.patientPastHistory();
		patientRegistrationPage.scrollDown();
		patientRegistrationPage.checkAgeCalculation(DOB_FOR_TEST_AGE_CALCULATION);
		Assert.assertTrue(basePage.verification().contains(HEADER_LISTING_PAGE));
	}

//	 * Checked patient name is mandatory field
//	 * Checked patient mobile is mandatory field
//	 * Checked patient alternate name is mandatory field
//	 * Checked patient alternate mobile is mandatory field
//	 * Checked patient age is mandatory field
//	 * Checked guardian name is mandatory field
//	 * Checked guardian mobile is mandatory field
	
	@Test(enabled=true,description = CHECK_MANDATORY_FIELDS,priority = 2)
	public void checkMandatoryFields(){
		extentReport.logger.log(LogStatus.PASS, CHECK_MANDATORY_FIELDS);
		basePatientLifeCyclePage.headerOnListPage("Patient Registration");
		patientRegistrationPage.selectGenderMale(PATIENT_GENDER);
		patientRegistrationPage.enterAge(PATIENT_AGE);
		patientRegistrationPage.enterMobileNO(PATIENT_MOBILE);
		patientRegistrationPage.enterAlterContactName(PATIENT_ALTERNATE_NAME);
		patientRegistrationPage.enterAlternateMobileNO(PATIENT_ALTERNATE_MOBILE);
		patientRegistrationPage.clickOnApproveSave();
		patientRegistrationPage.enterPatientNameMsg();
	
		loginPage.getDriver().navigate().refresh();
		patientRegistrationPage.enterPatientName(PATIENT_NAME);
		patientRegistrationPage.enterAge(PATIENT_AGE);
		patientRegistrationPage.enterMobileNO(PATIENT_MOBILE);
		patientRegistrationPage.enterAlterContactName(PATIENT_ALTERNATE_NAME);
		patientRegistrationPage.enterAlternateMobileNO(PATIENT_ALTERNATE_MOBILE);
		patientRegistrationPage.clickOnApproveSave();
		patientRegistrationPage.selectGenderMsg();
	
		// -------Validate Enter Mobile Number Message---------------
		loginPage.getDriver().navigate().refresh();
	    patientRegistrationPage.enterPatientName(PATIENT_NAME);
		patientRegistrationPage.selectGenderMale(PATIENT_GENDER);
		patientRegistrationPage.enterAge(PATIENT_AGE);
		patientRegistrationPage.enterAlterContactName(PATIENT_ALTERNATE_NAME);
		patientRegistrationPage.enterAlternateMobileNO(PATIENT_ALTERNATE_MOBILE);
		patientRegistrationPage.clickOnApproveSave();
		patientRegistrationPage.enterMobileNoMsg();
		
		// -------Validate Mobile Number Length  Message---------------
		loginPage.getDriver().navigate().refresh();
		patientRegistrationPage.enterPatientName(PATIENT_NAME);
		patientRegistrationPage.selectGenderMale(PATIENT_GENDER);
		patientRegistrationPage.enterAge(PATIENT_AGE);
		patientRegistrationPage.enterAlterContactName(PATIENT_ALTERNATE_NAME);
		patientRegistrationPage.enterAlternateMobileNO(PATIENT_ALTERNATE_MOBILE);
		patientRegistrationPage.enterMobileNO(MOBILE_UNDER_TEN_DIGIT);
		patientRegistrationPage.clickOnApproveSave();
		patientRegistrationPage.enterTenDigitMobileNoMsg();
	
		// -------Validate Invalid Mobile Number Message---------------
		loginPage.getDriver().navigate().refresh();
		patientRegistrationPage.enterPatientName(PATIENT_NAME);
		patientRegistrationPage.selectGenderMale(PATIENT_GENDER);
		patientRegistrationPage.enterAge(PATIENT_AGE);
		patientRegistrationPage.enterAlterContactName(PATIENT_ALTERNATE_NAME);
		patientRegistrationPage.enterAlternateMobileNO(PATIENT_ALTERNATE_MOBILE);
		patientRegistrationPage.enterMobileNO("000000000");
		patientRegistrationPage.clickOnApproveSave();
		patientRegistrationPage.enterValidMobileNoMsg();
		
		// -------Validate Without Providing Email---------------
		loginPage.getDriver().navigate().refresh();
		patientRegistrationPage.enterPatientName(PATIENT_NAME);
		patientRegistrationPage.selectGenderMale(PATIENT_GENDER);
		patientRegistrationPage.enterAge(PATIENT_AGE);
		patientRegistrationPage.enterAlterContactName(PATIENT_ALTERNATE_NAME);
		patientRegistrationPage.enterAlternateMobileNO(PATIENT_ALTERNATE_MOBILE);
		patientRegistrationPage.clickOnApproveSave();
		patientRegistrationPage.validateEnterEmailMsg();
		
		// -------Validate Invalid Email Message---------------
		loginPage.getDriver().navigate().refresh();
		patientRegistrationPage.enterPatientName(PATIENT_NAME);
		patientRegistrationPage.selectGenderMale(PATIENT_GENDER);
		patientRegistrationPage.enterAge(PATIENT_AGE);
		patientRegistrationPage.enterAlterContactName(PATIENT_ALTERNATE_NAME);
		patientRegistrationPage.enterEmailAddress("dentals.patientyopmail.com");
		patientRegistrationPage.enterAlternateMobileNO(PATIENT_ALTERNATE_MOBILE);
		patientRegistrationPage.clickOnApproveSave();
		patientRegistrationPage.validateInvalidEmailMsg();
		
		// -------Validate Email Verification ---------------
		loginPage.getDriver().navigate().refresh();
		patientRegistrationPage.enterPatientName(PATIENT_NAME);
		patientRegistrationPage.selectGenderMale(PATIENT_GENDER);
		patientRegistrationPage.enterAge(PATIENT_AGE);
		patientRegistrationPage.enterAlterContactName(PATIENT_ALTERNATE_NAME);
		patientRegistrationPage.enterEmailAddress("dentals.patient@gmail.com");
		patientRegistrationPage.enterAlternateMobileNO(PATIENT_ALTERNATE_MOBILE);
		patientRegistrationPage.clickOnApproveSave();
		patientRegistrationPage.verificationOfValidEmail();
		
		// -------Validate Alternate Contact Name Message ---------------
		loginPage.getDriver().navigate().refresh();
		patientRegistrationPage.enterPatientName(PATIENT_NAME);
		patientRegistrationPage.selectGenderMale(PATIENT_GENDER);
		patientRegistrationPage.enterMobileNO(PATIENT_MOBILE);
		patientRegistrationPage.enterAge(PATIENT_AGE);
		patientRegistrationPage.enterAlternateMobileNO(PATIENT_ALTERNATE_MOBILE);
		patientRegistrationPage.clickOnApproveSave();
		patientRegistrationPage.enterAlternateContactNameMsg();
		
		// -------Validate Alternate Contact Number Message ---------------
		loginPage.getDriver().navigate().refresh();
		patientRegistrationPage.enterPatientName(PATIENT_NAME);
		patientRegistrationPage.selectGenderMale(PATIENT_GENDER);
		patientRegistrationPage.enterMobileNO(PATIENT_MOBILE);
		patientRegistrationPage.enterAge(PATIENT_AGE);
		patientRegistrationPage.enterAlterContactName(PATIENT_ALTERNATE_NAME);
		patientRegistrationPage.clickOnApproveSave();
     	patientRegistrationPage.enterAlternateContactNoMsg();
	
		loginPage.getDriver().navigate().refresh();
		patientRegistrationPage.enterPatientName(PATIENT_NAME);
		patientRegistrationPage.selectGenderMale(PATIENT_GENDER);
		patientRegistrationPage.enterAlterContactName(PATIENT_ALTERNATE_NAME);
		patientRegistrationPage.enterAge(PATIENT_AGE);
		patientRegistrationPage.enterMobileNO(PATIENT_MOBILE);
		patientRegistrationPage.enterAlternateMobileNO("1111");
		patientRegistrationPage.clickOnApproveSave();
		patientRegistrationPage.enterTenDigitMobileNoMsg();
	
		loginPage.getDriver().navigate().refresh();
		patientRegistrationPage.enterPatientName(PATIENT_NAME);
		patientRegistrationPage.selectGenderMale(PATIENT_GENDER);
		patientRegistrationPage.enterAlterContactName(PATIENT_ALTERNATE_NAME);
		patientRegistrationPage.enterAge(PATIENT_AGE);
		patientRegistrationPage.enterMobileNO("0101010101");
		patientRegistrationPage.enterAlternateMobileNO(PATIENT_ALTERNATE_MOBILE);
		patientRegistrationPage.clickOnApproveSave();
		patientRegistrationPage.enterValidMobileNoMsg();
	
		loginPage.getDriver().navigate().refresh();
		patientRegistrationPage.enterPatientName(PATIENT_NAME);
		patientRegistrationPage.selectGenderMale(PATIENT_GENDER);
		patientRegistrationPage.enterMobileNO(PATIENT_MOBILE);
		patientRegistrationPage.enterAlterContactName(PATIENT_ALTERNATE_NAME);
		patientRegistrationPage.enterAlternateMobileNO(PATIENT_ALTERNATE_MOBILE);
		// -------always give age less than 18 here---------------
		patientRegistrationPage.enterAge("12");
		patientRegistrationPage.enterGuardianNo("1111111111");
		patientRegistrationPage.clickOnApproveSave();
		patientRegistrationPage.enterGuardianNameMsg();
	
		loginPage.getDriver().navigate().refresh();
		patientRegistrationPage.enterPatientName(PATIENT_NAME);
		patientRegistrationPage.selectGenderMale(PATIENT_GENDER);
		patientRegistrationPage.enterMobileNO(PATIENT_MOBILE);
		patientRegistrationPage.enterAlterContactName(PATIENT_ALTERNATE_NAME);
		patientRegistrationPage.enterAlternateMobileNO(PATIENT_ALTERNATE_MOBILE);
		// -------always give age less than 18 here---------------
		patientRegistrationPage.enterAge("12");
		patientRegistrationPage.enterGuardianName("guardian name");
		patientRegistrationPage.clickOnApproveSave();
		patientRegistrationPage.enterGuardianContactNoMsg();
		patientRegistrationPage.enterGuardianName("guardian name");
		patientRegistrationPage.clickOnApproveSave();
		patientRegistrationPage.enterGuardianContactNoMsg();
		patientRegistrationPage.enterGuardianNo("11111");
		patientRegistrationPage.clickOnApproveSave();
		patientRegistrationPage.enterTenDigitMobileNoMsg();
		patientRegistrationPage.enterGuardianNo("0000000000");
		patientRegistrationPage.clickOnApproveSave();
		patientRegistrationPage.enterValidMobileNoMsg();
		// -------give age 18 or more than 18--------
		patientRegistrationPage.enterAge(PATIENT_AGE);
		patientRegistrationPage.guardianDetailsHide();
		Assert.assertTrue(basePage.verification().contains(HEADER_LISTING_PAGE));
	}


//	 * Creating new patient with beat time to reach,Address,medical history,with patient groups and patient notes
//	 * checked same patient name at patient dash board at the top
//	 * Then Opening the view modal and checked all the data at view modal and close the view modal
//   * Then print the patient profile to check the medical history

	
	@Test(enabled=true,description = CREATE_PATIENT,priority = 3)
	public void createNewPatient() {
		extentReport.logger.log(LogStatus.PASS, CREATE_PATIENT);
		basePatientLifeCyclePage.headerOnListPage(HEADER_LISTING_PAGE);
		patientRegistrationPage.enterPatientName(patntRegData.get("patient_name"));
		patientRegistrationPage.selectGenderMale(patntRegData.get("patient_gender"));
		patientRegistrationPage.enterAge(patntRegData.get("patient_age"));
		patientRegistrationPage.enterEmailAddress(patntRegData.get("email"));	
		patientRegistrationPage.enterMobileNO(patntRegData.get("patient_mobile"));
		patientRegistrationPage.enterAlterContactName(patntRegData.get("patient_alternate_name"));
		patientRegistrationPage.enterAlternateMobileNO(patntRegData.get("patient_alternate_mobile"));
		patientRegistrationPage.enterInReachTime(patntRegData.get("reaching_time"));
		patientRegistrationPage.selectOccupation(patntRegData.get("occupationOptn"));
		patientRegistrationPage.enterAddress(patntRegData.get("address"));
		patientRegistrationPage.enterCity(patntRegData.get("city"));
		patientRegistrationPage.selectState(patntRegData.get("state"));
		
		patientRegistrationPage.enterPinCode(patntRegData.get("pin_code"));
//		patientRegistrationPage.verifyCharacterLimitOfPinText();
		patientRegistrationPage.clickOnMedicalHistory();
		patientRegistrationPage.scrollDown();
		patientRegistrationPage.selectMedicalHistory(MED_HIST_DIS_1, MED_HIST_STS_1);
		patientRegistrationPage.selectMedicalHistory(MED_HIST_DIS_2, MED_HIST_STS_2);
		patientRegistrationPage.selectAllMedicalHistory(OTH_MED_HIST);
		patientRegistrationPage.clickOnAddBtnPastHistory();
		patientRegistrationPage.clickOnAdult();
		patientRegistrationPage.selectTeeth(teeth);
		patientRegistrationPage.verifyTeethIsSelected(teeth);
		patientRegistrationPage.enterNotes(PAST_HIST_NOTES);
		patientRegistrationPage.clickOnSaveBtnOfPastHistory();
		patientRegistrationPage.verifySelectedTeethPastHistory(teeth);
		patientRegistrationPage.clickOnDentalCheckUP();
		patientRegistrationPage.selectDentalCheckUpOptions(dentalCheckUpOptn);
		patientRegistrationPage.clickOnAllergies();
		patientRegistrationPage.clickOnAllergiesYesBtn();
		patientRegistrationPage.enterAllergy(ALLRGY_NOTES);
		patientRegistrationPage.clickMedication();
		patientRegistrationPage.clickOnMedicationCheckUpYesBtn();
		patientRegistrationPage.enterMedication(MEDICATN_NOTES);
		patientRegistrationPage.clickOnGroup();
		patientRegistrationPage.enterGroup(GROUP_NOTES);
		patientRegistrationPage.clickOnPatientNotes();
    	patientRegistrationPage.enterPatientNotes(PATIENT_NOTES);
    	patientRegistrationPage.clickOnApproveSave();
		basePatientLifeCyclePage.clickOnAlert();
		patientDashboardPage.patientNamePatientDashboard(patntRegData.get("patient_name"));
		patientDashboardPage.clickOnProfileViewBtn();
		patientDashboardPage.verifyTimeReachOnPatientProfile(Reaching_Time);
		patientDashboardPage.verifyOccupationOnPatientProfile(OCCUPATION);
		patientDashboardPage.verifyAddressOnPatientProfile(ADDRESS);
		patientDashboardPage.verifyCityOnPatientProfile(CITY);
		patientDashboardPage.verifyStateOnPatientProfile(STATE);
		patientDashboardPage.verifyPostalCodeOnPatientProfile(PIN_CODE);
		patientDashboardPage.verifyPatientNotesOnPatientProfile(PATIENT_NOTES);
		patientDashboardPage.verifyGroupNameOnPatientProfile(GROUP_NOTES);
    	patientDashboardPage.clickOnClosePopUp();
		loginPage.clickEscapeKeyboardButton();
		patientDashboardPage.clickOnPrint();
		Assert.assertTrue(basePage.verification().contains(HEADER_LISTING_PAGE));
	}
	

//	 * Validate of Patient Registration Details 
//	 * checked same patient name at patient dash board at the top

	
	@Test(enabled=true,description = VALIDATE_PATIENT_DETAILS,priority = 4)
	public void validteDetailsOfPatient() {
		extentReport.logger.log(LogStatus.PASS, VALIDATE_PATIENT_DETAILS);
		basePatientLifeCyclePage.headerOnListPage(HEADER_LISTING_PAGE);
    	patientRegistrationPage.clickOnCancelBtn();
    	basePage.enterMobileNo(patntRegData.get("patient_mobile"));
    	basePage.clickOnSearchBtn();
		basePage.clickOnPatient(patntRegData.get("patient_mobile"),patntRegData.get("patient_name"));
		patientDashboardPage.medicalProblemAlertModal(MED_HIST_DIS_1, MED_HIST_STS_1);
		patientDashboardPage.medicalProblemAlertModal(MED_HIST_DIS_2, MED_HIST_STS_2);
		patientDashboardPage.medicationAlertModal(MEDICATN_NOTES);
		patientDashboardPage.allergiesAlertModal(ALLRGY_NOTES);
    	basePatientLifeCyclePage.clickOnAlert();
		patientDashboardPage.clickOnProfileViewBtn();
		patientDashboardPage.verifyHeadInProfileView();
		patientDashboardPage.checkAllDataInPatientProfileView();	
		patientDashboardPage.verifyPatientNameIDOnPatientProfile(patntRegData.get("patient_name"));
		patientDashboardPage.verifyGenderOnPatientProfile(patntRegData.get("patient_gender"));
		patientDashboardPage.verifyAgeOnPatientProfile(patntRegData.get("patient_age"));
    	patientDashboardPage.verifyPatientEmailOnPatientProfile(patntRegData.get("email"));
		patientDashboardPage.verifyPatientMoblieOnPatientProfile(patntRegData.get("patient_mobile"));
		patientDashboardPage.verifyAlternateNameOnPatientProfile(patntRegData.get("patient_alternate_name"));
		patientDashboardPage.verifyAlterNateNoOnPatientProfile(patntRegData.get("patient_alternate_mobile"));
		patientDashboardPage.verifyTimeReachOnPatientProfile(patntRegData.get("reaching_time"));
		patientDashboardPage.verifyOccupationOnPatientProfile(patntRegData.get("occupationOptn"));
		patientDashboardPage.verifyAddressOnPatientProfile(patntRegData.get("address"));
		patientDashboardPage.verifyCityOnPatientProfile(patntRegData.get("city"));
		patientDashboardPage.verifyStateOnPatientProfile(patntRegData.get("state"));
		patientDashboardPage.verifyPostalCodeOnPatientProfile(patntRegData.get("pin_code"));
		patientDashboardPage.verifyToothNoOnPatientProfile(teeth);
		patientDashboardPage.verifyNotesOnPastHistoryPatientProfile(PAST_HIST_NOTES);
		patientDashboardPage.verifyGroupNameOnPatientProfile(GROUP_NOTES);
		patientDashboardPage.verifyPatientNotesOnPatientProfile(PATIENT_NOTES);
		patientDashboardPage.clickOnClosePopUp();		
	}
	
//	* Search the patient from global
//	* Edit the patient from the patient listing	 
//	* Checked All teeth functionality at the past history modal* save all teeth of mixed and save it.
//	* Checked same at the patient dash board patient view modal


	@Test(enabled=true,description = BEHAVIOUR_OF_ALL_SELECT_OF_MIXED,priority = 5)
	public void behaviourOfAllSelectOfMixed() {
		extentReport.logger.log(LogStatus.PASS, BEHAVIOUR_OF_ALL_SELECT_OF_MIXED);
		basePatientLifeCyclePage.headerOnListPage(HEADER_LISTING_PAGE);
		patientRegistrationPage.clickOnCancelBtn();
		basePage.enterMobileNo(patntRegData.get("patient_mobile"));
		basePage.clickOnSearchBtn();
		basePage.editPatient(patntRegData.get("patient_name"),patntRegData.get("patient_mobile"));
    	patientRegistrationPage.clickOnAddBtnPastHistory();
		patientRegistrationPage.clickOnMixed();
		patientRegistrationPage.clickOnAllTeeth();
		patientRegistrationPage.clickOnAdult();
		patientRegistrationPage.clickOnPedo();
		patientRegistrationPage.pedoCheckedAllTeethDeselected();
		patientRegistrationPage.clickOnMixed();
		patientRegistrationPage.adultCheckedAllTeethDeselected();
		patientRegistrationPage.clickOnAllTeeth();
		patientRegistrationPage.enterNotes(PATIENT_NOTES);
		patientRegistrationPage.clickOnSaveBtnOfPastHistory();
		patientRegistrationPage.clickOnDeleteBtn();
		patientRegistrationPage.verifyPastHistoryAfterDelete();
		patientRegistrationPage.clickOnAddBtnPastHistory();
		patientRegistrationPage.clickOnMixed();
		patientRegistrationPage.clickOnAllTeeth();
		patientRegistrationPage.enterNotes(PATIENT_NOTES);
		patientRegistrationPage.clickOnSaveBtnOfPastHistory();
		patientRegistrationPage.scrollUp();
		patientRegistrationPage.clickOnApproveSave();
		basePatientLifeCyclePage.clickOnAlert();
		patientDashboardPage.clickOnProfileViewBtn();
		patientDashboardPage.verifyPatientNameIDOnPatientProfile(patntRegData.get("patient_name"));
		patientDashboardPage.verifyPatientMoblieOnPatientProfile(patntRegData.get("patient_mobile"));
		patientDashboardPage.selectedTeethVerificationPatientDashboard("All mixed");
		patientDashboardPage.clickOnClosePopUp();		
	}

/**	* Enter the details of exiting patient and checked
* Checked it is showing patient already exit
* Reset the data and checked all page is refreshed
* Saving the Approve and appointment it will redirect to appointment add page with prefilled name and mobile
*/
	
	@Test(enabled=true,description = VERIFY_ALREADY_EXITING_PATIENT_MSG,priority = 6)
	public void verifyAlreadyExitingPatientMsg() {
		extentReport.logger.log(LogStatus.PASS, VERIFY_ALREADY_EXITING_PATIENT_MSG);
		basePatientLifeCyclePage.headerOnListPage(HEADER_LISTING_PAGE);
		patientRegistrationPage.enterPatientName(patntRegData.get("patient_name"));
		patientRegistrationPage.selectGenderMale(patntRegData.get("patient_gender"));
		patientRegistrationPage.enterAge(patntRegData.get("patient_age"));
		patientRegistrationPage.enterEmailAddress(patntRegData.get("email"));	
		patientRegistrationPage.enterMobileNO(patntRegData.get("patient_mobile"));
		patientRegistrationPage.enterAlterContactName(patntRegData.get("patient_alternate_name"));
		patientRegistrationPage.enterAlternateMobileNO(patntRegData.get("patient_alternate_mobile"));
		patientRegistrationPage.enterInReachTime(patntRegData.get("reaching_time"));
		patientRegistrationPage.selectOccupation(patntRegData.get("occupationOptn"));
		patientRegistrationPage.enterAddress(patntRegData.get("address"));
		patientRegistrationPage.enterCity(patntRegData.get("city"));
		patientRegistrationPage.selectState(patntRegData.get("state"));
		patientRegistrationPage.enterPinCode(patntRegData.get("pin_code"));
		patientRegistrationPage.clickOnApproveSave();
		patientRegistrationPage.nameAndPhoneAlreadyExit();
		patientRegistrationPage.clickOnCancelBtn();
//		patientRegistrationPage.clickOnApproveApp();
//		patientRegistrationPage.nameAndPhoneAlreadyExit();
//		patientRegistrationPage.clickOnResetBtn();
//		patientRegistrationPage.clickOnApproveSave();
//		patientRegistrationPage.enterMobileNoMsg();
		basePage.enterMobileNo(patntRegData.get("patient_mobile"));
		basePage.clickOnSearchBtn();
		basePage.editPatient(patntRegData.get("patient_name"),patntRegData.get("patient_mobile"));
		patientRegistrationPage.clickOnApproveApp();
		appointmentPage.getTextPatientName(patntRegData.get("patient_name"));
		appointmentPage.getTextPatientMobile(patntRegData.get("patient_mobile"));
		Assert.assertTrue(basePage.verification().contains("Appointment"));
	}

	/* Edited patient from the patient dash board
	 * checked All details is prefilled and same */
	
    @Test(enabled=true,description = VERIFY_EDIT_PATIENT_DASHBOARD,priority = 8 /*dependsOnMethods = "createNewPatient"*/)
	public void verifyEditPatientDashBoard() {
		extentReport.logger.log(LogStatus.PASS, VERIFY_EDIT_PATIENT_DASHBOARD);
		basePatientLifeCyclePage.headerOnListPage("Patient Registration");
		patientRegistrationPage.clickOnCancelBtn();
		basePage.enterMobileNo(patntRegData.get("patient_mobile"));
		basePage.clickOnSearchBtn();
		basePage.clickOnPatient(patntRegData.get("patient_mobile"),patntRegData.get("patient_name"));
		basePatientLifeCyclePage.clickOnAlert();
		patientDashboardPage.clickProfileEditBtn();
		patientRegistrationPage.verifyEditPatientName(patntRegData.get("patient_name"));
		patientRegistrationPage.verifyEditMobile(patntRegData.get("patient_mobile"));
		patientRegistrationPage.verifyEditEmail(patntRegData.get("email"));
		patientRegistrationPage.enterPatientName(patntRegData.get("edit_patient_name"));
		patientRegistrationPage.enterEmailAddress(patntRegData.get("edit_patient_email"));	
		patientRegistrationPage.enterMobileNO(patntRegData.get("edit_patient_mobile"));
		patientRegistrationPage.clickOnApproveSave();
		basePatientLifeCyclePage.clickOnAlert();
		patientDashboardPage.patientNamePatientDashboard(patntRegData.get("edit_patient_name"));
		patientDashboardPage.clickOnProfileViewBtn();
		patientDashboardPage.verifyHeadInProfileView();
		patientDashboardPage.verifyPatientEmailOnPatientProfile(patntRegData.get("edit_patient_email"));
		patientDashboardPage.verifyPatientMoblieOnPatientProfile(patntRegData.get("edit_patient_mobile"));
		patientDashboardPage.verifyPatientNameIDOnPatientProfile(patntRegData.get("edit_patient_name"));
		patientDashboardPage.clickHistoryOnPatientProfile();
		patientDashboardPage.checkAllDataInPatientHistoryView();
		patientDashboardPage.clickOnClosePopUp();	
	}

	@AfterMethod(description = STATUS_OF_SCRIPTS, enabled = true)
	public void statusOfScripts(ITestResult result) {
		extentReport.onTestFailure(result);
		extentReport.logger.log(LogStatus.PASS, STATUS_OF_SCRIPTS);
	}

	@AfterClass
	public void tearDown() {
		extentReport.report.endTest(extentReport.logger);
		extentReport.report.flush();
    	basePage.clickOnlogOut();
	}

}
