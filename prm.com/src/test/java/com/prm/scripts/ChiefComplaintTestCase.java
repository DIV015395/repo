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
import com.prm.patientdashboard.pageobject.AppointmentsListPage;
import com.prm.patientdashboard.pageobject.ChiefComplaintAddPage;
import com.prm.patientdashboard.pageobject.ChiefComplaintListingPage;
import com.relevantcodes.extentreports.LogStatus;
import com.prm.pageobjects.utils.ExtentReport;
import com.prm.pageobjects.utils.ReadConfig;
import com.prm.pageobjects.utils.ReadExcelData;
import com.prm.pageobjects.utils.SheetTest;

/**
 * 
 * @author Ajit Yadav
 *
 */
public class ChiefComplaintTestCase {

	private LoginPage loginPage = new LoginPage();
	private DoctorDashBoard doctorDashboard = new DoctorDashBoard(loginPage);
	private BasePage basePage = new BasePage(loginPage);
	private ExtentReport extentReport = new ExtentReport(loginPage, "ChiefComplaintTestCase");
	private PatientDashboardPage patientDashboardPage = new PatientDashboardPage(loginPage);
	private ChiefComplaintListingPage chiefComplaintListingPage = new ChiefComplaintListingPage(loginPage);
	private ChiefComplaintAddPage chiefComplaintAddPage = new ChiefComplaintAddPage(loginPage);
	private BasePatientLifeCyclePage basePatientLifeCyclePage = new BasePatientLifeCyclePage(loginPage);
	private AppointmentsListPage appointmentsListPage = new AppointmentsListPage(loginPage);
	private AppointmentAddPage appointmentPage = new AppointmentAddPage(loginPage);

	/*Mandatory Inputs for chief complaint Test Cases*/
//	private static final String PATIENT_NAME=TestData.getInstance().getInputData("chief_complaint_patient_name");
//	private static final String MOBILE_NUMBER = TestData.getInstance().getInputData("chief_complaint_patient_mobile");
//	private static final String DOCTOR_NAME = TestData.getInstance().getInputData("chief_complaint_doctor_name");
//	private static final String DOCTOR_NIK_NAME = TestData.getInstance().getInputData("chief_complaint_doctor_nikName");
//	private static final String CLINIC_NAME = TestData.getInstance().getInputData("chief_complaint_clinic_name");


	/*Test Cases details for extent report*/
	
	private static final String SCRIPTS_STARTED_MSG = "@@ Test scripts has been started here @@";
	private static final String STATUS_OF_SCRIPTS = "@@ Test script has been completed @@";
	private static final String CHIEF_COMPLAINT_LIST_HOME_PAGE = "Validating Chief Complaint listing without Chief Complaints - #chiefCompalintListHomePage";
	private static final String CHIEF_COMPLAINT_ADD_PAGE_FIRST_TIME = "Validating Chief Complaint Add when Redirects for first time - #chiefComplaintAddPageFirstTime";
	private static final String APPOINTMENT_WITH_OUT_CHIEF_COMPLAINT_IMPACT_IN_CHIEF_COMPLAINT_LIST = "Appointment WithOut ChiefComplaint Impact In Chief Complaint Listing - #appointmentWithOutChiefComplaintImpactInChiefComplaintList";
	private static final String PAIN_POPUP_ELEMENT_AND_BEHAVIOUR = "Validating all User Interface and Behaviour of Pain popup at chief complaint Add page - #painPopupElementAndBehaviour";
	private static final String VERIFY_DELETE_BTN_CCADD_PAGE = "Validating chief complaint editing and deleting functionality - #VerifyDeleteBTnCCAddPage";
	private static final String ADD_CCWITHOUT_APP_SAME_DAY = "Adding Chief Complaint Without Appointment on same day - #addCCWithoutAppSameDay";
	private static final String CHIEF_COMPLAINT_BY_CLINIC_WISE = "Adding Chief Complaint By changing clinic - #chiefComplaintByClinicWise";
	Map<String, String> patntChiefCmplntData = null;

	/*loginPage with UserName and Password
	* search for patients from the doctor dashboard
	* verified patients will redirect at the patient dashboard*/
	 
	@BeforeClass
	public void setup() {
		patntChiefCmplntData = SheetTest.prepareData("ChiefComplaint","PatientChiefComplaint","A2", "H2");
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
		basePage.selectClinicFrmHeader(patntChiefCmplntData.get("clinicLocation"));
		basePage.enterMobileNo(patntChiefCmplntData.get("patient_mobile"));
		basePage.clickOnSearchBtn();
		basePage.clickOnPatient(patntChiefCmplntData.get("patient_mobile"),patntChiefCmplntData.get("patient_name"));
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
	
//	/*checked chief complaint listing without any chief complaints
//	* checked left side module navigator */
	
	@Test(enabled=true,description = "Verify Chief Complaint Listing & Elements",priority = 1)
	public void chiefComplaintListHomePage() {
	extentReport.logger.log(LogStatus.PASS, CHIEF_COMPLAINT_LIST_HOME_PAGE);
	patientDashboardPage.clickOnChiefComplaintlistBtn();
	basePatientLifeCyclePage.headerOnListPage("Chief Complaint Listing");
	basePatientLifeCyclePage.verifyPatientName(patntChiefCmplntData.get("patient_name"));
	basePatientLifeCyclePage.verifyAddNewBtn();
	basePatientLifeCyclePage.dashBoardBtnVerify();
//	chiefComplaintListingPage.verifyNoRecordFoundMessage();
	basePatientLifeCyclePage.openCloseLeftNavigator();
	basePatientLifeCyclePage.webElementOfLeftNavigator();
	basePatientLifeCyclePage.openCloseLeftNavigator();
	basePatientLifeCyclePage.clickOnDashBoard();
	basePatientLifeCyclePage.clickOnAlert();
	patientDashboardPage.clickOnChiefComplaintAddBtn();
//	basePatientLifeCyclePage.clickOnAlert();
	basePatientLifeCyclePage.headerOnAddPage("Chief Complaints");
	basePatientLifeCyclePage.verifyPatientName(patntChiefCmplntData.get("patient_name"));
	basePatientLifeCyclePage.openCloseLeftNavigator();
	basePatientLifeCyclePage.webElementOfLeftNavigator();
	basePatientLifeCyclePage.openCloseLeftNavigator();
	chiefComplaintAddPage.VerifyChiefCompliantBoxes();
	basePatientLifeCyclePage.dashBoardBtnVerify();
	chiefComplaintAddPage.verifyOralExamBtn();
	Assert.assertTrue(basePage.verification().contains("Chief Complaints"));
	}

//	/*adding chief complaints for the patient without having chief complaints at the same day
//	* deleted from the input list of the chief complaint Add page
//	* deleted checked at input listing,chief complaint listing and view modal*/	
	
	@Test(enabled=true,description = "Add Verify & Delete Chief Complaint without Appointment", priority = 2)
	public void addCCWithoutAppSameDay() {
		extentReport.logger.log(LogStatus.PASS, ADD_CCWITHOUT_APP_SAME_DAY);
		patientDashboardPage.clickOnAppList();
		appointmentsListPage.appointmentAvailable();
		basePatientLifeCyclePage.clickOnDashBoard();
		basePatientLifeCyclePage.clickOnAlert();
		patientDashboardPage.clickOnChiefComplaintAddBtn();
		basePatientLifeCyclePage.clickOnAlert();
		chiefComplaintAddPage.clickOnChiefComplaint("Bleeding Gums");
		chiefComplaintAddPage.chiefComplaintAddSuccessMsg();
		chiefComplaintAddPage.checkedInputlistDataName();
		chiefComplaintAddPage.withoutLocationInputList("Bleeding Gums", "NA");
		chiefComplaintAddPage.intensityInputList("Bleeding Gums", "NA");
		chiefComplaintAddPage.periodsBehaviour("Bleeding Gums", 0, 0, "NA");
		chiefComplaintAddPage.notesInputList("Bleeding Gums", "NA");
		chiefComplaintAddPage.actionBtnInputList("Bleeding Gums");
		chiefComplaintAddPage.clickOnDeleteBtn("Bleeding Gums");
		chiefComplaintAddPage.deletedChiefComplaintInInputList("Bleeding Gums");
		basePatientLifeCyclePage.clickOnDashBoard();
		basePatientLifeCyclePage.clickOnAlert();
		patientDashboardPage.clickOnChiefComplaintlistBtn();
		basePatientLifeCyclePage.verifyDates();
		chiefComplaintListingPage.deletedChiefComplaintInMainList("Bleeding Gums");
		basePatientLifeCyclePage.clickViewBtn(patntChiefCmplntData.get("clinicLocation"));
		chiefComplaintListingPage.verifyDateInView();
		chiefComplaintListingPage.deletedChiefComplaintInView("Bleeding Gums");
        chiefComplaintListingPage.closeViewPopup();
		Assert.assertTrue(basePage.verification().contains("Chief Complaint Listing"));
	}
	
	/*adding appointment
	* checked chief complaint listing
	* any chief complaints are not added at the time of appointment creation*/
	
	@Test(enabled=true,description = "Book Appointment With Chief Complaint", priority = 3)
	public void appointmentWithOutChiefComplaintImpactInChiefComplaintList() {
		extentReport.logger.log(LogStatus.PASS, APPOINTMENT_WITH_OUT_CHIEF_COMPLAINT_IMPACT_IN_CHIEF_COMPLAINT_LIST);
		patientDashboardPage.clickOnAppAdd();
		appointmentPage.selectDoctorFromDropdown(patntChiefCmplntData.get("doctor"));
		appointmentPage.selectReferralFromDropdown("Patient");
	    appointmentPage.enterReferralDetails("self");
	    appointmentPage.clickOnChiefComplaints("Tooth Decay");
		appointmentPage.clickOnSaveBtn();
		basePatientLifeCyclePage.clickOnAlert();
		patientDashboardPage.clickOnChiefComplaintlistBtn();
		chiefComplaintListingPage.chiefComplaintInMainList("Tooth Decay");
		Assert.assertTrue(basePage.verification().contains("Chief Complaint Listing"));
	}
	
//	/*checked chief complaints add and chief complaint listing page default screen
//	* checked left side navigation module
//	*All chief complaints boxes */
	
	@Test(enabled = true, priority = 4)
	public void chiefComplaintAddPage() {
		extentReport.logger.log(LogStatus.PASS, CHIEF_COMPLAINT_ADD_PAGE_FIRST_TIME);
		patientDashboardPage.clickOnChiefComplaintAddBtn();
		basePatientLifeCyclePage.clickOnAlert();
		chiefComplaintAddPage.clickOnChiefComplaint("Discolored Tooth");
		chiefComplaintAddPage.chiefComplaintAddSuccessMsg();
		chiefComplaintAddPage.checkedInputlistDataName();
		chiefComplaintAddPage.withoutLocationInputList("Discolored Tooth", "NA");
		chiefComplaintAddPage.intensityInputList("Discolored Tooth", "NA");
		chiefComplaintAddPage.periodsBehaviour("Discolored Tooth", 0, 0, "NA");
		chiefComplaintAddPage.notesInputList("Discolored Tooth", "NA");
		chiefComplaintAddPage.actionBtnInputList("Discolored Tooth");
		basePatientLifeCyclePage.clickOnDashBoard();
		basePatientLifeCyclePage.clickOnAlert();
		patientDashboardPage.clickOnChiefComplaintlistBtn();
		basePatientLifeCyclePage.verifyDates();
		basePatientLifeCyclePage.actionBtnMainList(patntChiefCmplntData.get("clinicLocation"));
		chiefComplaintListingPage.checkedMainlistDataName();
		chiefComplaintListingPage.chiefComplaintInMainList("Discolored Tooth");
		chiefComplaintListingPage.locationMainList("Discolored Tooth", "NA");
		chiefComplaintListingPage.intensityMainList("Discolored Tooth", "NA");
		chiefComplaintListingPage.periodsInMainList("Discolored Tooth", 0, 0, "NA");
		chiefComplaintListingPage.chiefComplaintcreatedBy("Discolored Tooth", patntChiefCmplntData.get("doctorNickName"));
		chiefComplaintListingPage.notesMainList("Discolored Tooth", "NA");
		basePatientLifeCyclePage.clickViewBtn(patntChiefCmplntData.get("clinicLocation"));
		chiefComplaintListingPage.verifyDateInView();
		chiefComplaintListingPage.viewChiefComplaint();
		chiefComplaintListingPage.dataNameInView();
		chiefComplaintListingPage.checkedChiefComplaintInView("Discolored Tooth");
		chiefComplaintListingPage.locationInView("Discolored Tooth", "NA");
		chiefComplaintListingPage.intentsityInView("Discolored Tooth", "NA");
		chiefComplaintListingPage.periodsInView("Discolored Tooth", 0, 0, "NA");
		chiefComplaintListingPage.userNameInView("Discolored Tooth", patntChiefCmplntData.get("doctorNickName"));
		chiefComplaintListingPage.notesInView("Discolored Tooth", "NA");
		chiefComplaintListingPage.closeViewPopup();
		Assert.assertTrue(basePage.verification().contains("Chief Complaint Listing"));
	}
	

//	/*checked the all webelement of the popup of the pain,Sensitivity and other
//	* also checked the behavior of the popup
//	* also added pain chief complaints from the popup
//	* and checked at chief complaint add page input listing and
//	* chief complaint listing page and view modal*/
	
	@Test(enabled = true, priority = 5)
	public void painPopupElementAndBehaviour() {
		extentReport.logger.log(LogStatus.PASS, PAIN_POPUP_ELEMENT_AND_BEHAVIOUR);
		patientDashboardPage.clickOnChiefComplaintAddBtn();
		basePatientLifeCyclePage.clickOnAlert();
		chiefComplaintAddPage.clickOnChiefComplaint("Pain");
		chiefComplaintAddPage.checkedPopupElement();
		chiefComplaintAddPage.multiSelectOfLocation();
		chiefComplaintAddPage.clickOnSevere();
		chiefComplaintAddPage.clickOnModerate();
		chiefComplaintAddPage.verifySevereIsNotSelected();
		chiefComplaintAddPage.SelectMonth(4);
		chiefComplaintAddPage.clickOnSaveBtn();
		chiefComplaintAddPage.intensityInputList("Pain", "Moderate");
		chiefComplaintAddPage.periodsBehaviour("Pain", 4, 0, "Value");
		chiefComplaintAddPage.actionBtnInputList("Pain");
		chiefComplaintAddPage.clickEditInInputList("Pain");
		chiefComplaintAddPage.SelectDays(12);
		chiefComplaintAddPage.enterNote("notes testing");
		chiefComplaintAddPage.clickOnSaveBtn();
		chiefComplaintAddPage.intensityInputList("Pain", "Moderate");
		chiefComplaintAddPage.periodsBehaviour("Pain", 4, 12, "Value");
		chiefComplaintAddPage.notesInputList("Pain", "notes testing");
		chiefComplaintAddPage.actionBtnInputList("Pain");
		basePatientLifeCyclePage.clickOnDashBoard();
		basePatientLifeCyclePage.clickOnAlert();
		patientDashboardPage.clickOnChiefComplaintlistBtn();
		basePatientLifeCyclePage.verifyDates();
		basePatientLifeCyclePage.actionBtnMainList(patntChiefCmplntData.get("clinicLocation"));
		chiefComplaintListingPage.checkedMainlistDataName();
		chiefComplaintListingPage.chiefComplaintInMainList("Pain");
		chiefComplaintListingPage.locationMainList("Pain", "Value");
		chiefComplaintListingPage.intensityMainList("Pain", "Moderate");
		chiefComplaintListingPage.periodsInMainList("Pain", 4, 12, "Value");
		chiefComplaintListingPage.chiefComplaintcreatedBy("Pain", patntChiefCmplntData.get("doctorNickName"));
		chiefComplaintListingPage.notesMainList("Pain", "notes testing");
		basePatientLifeCyclePage.clickViewBtn(patntChiefCmplntData.get("clinicLocation"));
		chiefComplaintListingPage.verifyDateInView();
		chiefComplaintListingPage.viewChiefComplaint();
		chiefComplaintListingPage.dataNameInView();
		chiefComplaintListingPage.checkedChiefComplaintInView("Pain");
		chiefComplaintListingPage.locationInView("Pain", "Value");
		chiefComplaintListingPage.intentsityInView("Pain", "Moderate");
		chiefComplaintListingPage.periodsInView("Pain", 4, 12, "Value");
		chiefComplaintListingPage.userNameInView("Pain", patntChiefCmplntData.get("doctorNickName"));
		chiefComplaintListingPage.notesInView("Pain", "notes testing");
		chiefComplaintListingPage.closeViewPopup();
		Assert.assertTrue(basePage.verification().contains("Chief Complaint Listing"));
	}

	/*added chief complaint Plaque
	* verified success message and checked chief complaints in input listing
	* same checked at chief complaints listing
	* also checked all action button at both page either chief complaints add page or chief complaints listing
	* edited from the chief complaints listing page
	* again edited from the chief complaints add page and checked cancel btn and added notes and saved.same checked at
	* chief complaints add page and chief complaint listing*/
	
	@Test(enabled = true, priority = 6)
	public void VerifyDeleteBTnCCAddPage() {
		extentReport.logger.log(LogStatus.PASS, VERIFY_DELETE_BTN_CCADD_PAGE);
		patientDashboardPage.clickOnChiefComplaintAddBtn();
		basePatientLifeCyclePage.clickOnAlert();
		chiefComplaintAddPage.clickOnChiefComplaint("Plaque");
		chiefComplaintAddPage.chiefComplaintAddSuccessMsg();
		chiefComplaintAddPage.checkedInputlistDataName();
		chiefComplaintAddPage.withoutLocationInputList("Plaque", "NA");
		chiefComplaintAddPage.intensityInputList("Plaque", "NA");
		chiefComplaintAddPage.periodsBehaviour("Plaque", 0, 0, "NA");
		chiefComplaintAddPage.notesInputList("Plaque", "NA");
		chiefComplaintAddPage.actionBtnInputList("Plaque");
		basePatientLifeCyclePage.clickOnDashBoard();
		basePatientLifeCyclePage.clickOnAlert();
		patientDashboardPage.clickOnChiefComplaintlistBtn();
		basePatientLifeCyclePage.verifyDates();
		basePatientLifeCyclePage.actionBtnMainList(patntChiefCmplntData.get("clinicLocation"));
		chiefComplaintListingPage.checkedMainlistDataName();
		chiefComplaintListingPage.chiefComplaintInMainList("Plaque");
		chiefComplaintListingPage.locationMainList("Plaque", "NA");
		chiefComplaintListingPage.intensityMainList("Plaque", "NA");
		chiefComplaintListingPage.periodsInMainList("Plaque", 0, 0, "NA");
		chiefComplaintListingPage.notesMainList("Plaque", "NA");
		basePatientLifeCyclePage.clickEditBtn(patntChiefCmplntData.get("clinicLocation"));
		basePatientLifeCyclePage.clickOnAlert();
		chiefComplaintAddPage.actionBtnInputList("Plaque");
		chiefComplaintAddPage.clickEditInInputList("Plaque");
		chiefComplaintAddPage.chiefComplaintsInputEdit("Plaque");
		chiefComplaintAddPage.cancelInputListEdit("Plaque");
		chiefComplaintAddPage.actionBtnInputList("Plaque");
		chiefComplaintAddPage.clickEditInInputList("Plaque");
		chiefComplaintAddPage.enterNotesInputList("Plaque", "notes testing");
		chiefComplaintAddPage.saveInputListEdit("Plaque");
		chiefComplaintAddPage.actionBtnInputList("Plaque");
		chiefComplaintAddPage.notesInputList("Plaque", "notes testing");
		basePatientLifeCyclePage.clickOnDashBoard();
		basePatientLifeCyclePage.clickOnAlert();
		patientDashboardPage.clickOnChiefComplaintlistBtn();
		basePatientLifeCyclePage.verifyDates();
		basePatientLifeCyclePage.actionBtnMainList(patntChiefCmplntData.get("clinicLocation"));
		chiefComplaintListingPage.checkedMainlistDataName();
		chiefComplaintListingPage.chiefComplaintInMainList("Plaque");
		chiefComplaintListingPage.locationMainList("Plaque", "NA");
		chiefComplaintListingPage.intensityMainList("Plaque", "NA");
		chiefComplaintListingPage.periodsInMainList("Plaque", 0, 0, "NA");
		chiefComplaintListingPage.notesMainList("Plaque", "notes testing");
		Assert.assertTrue(basePage.verification().contains("Chief Complaint Listing"));
	}
	
//	/*added chief complaints in other clinic by adding Appointment with a chief complaints
//	* same patient with chief complaint in different clinic checked at chief complaints listing*/

	@Test(enabled = true, priority = 7)
	public void chiefComplaintByClinicWise() {
		extentReport.logger.log(LogStatus.PASS, CHIEF_COMPLAINT_BY_CLINIC_WISE);
		patientDashboardPage.clickOnAppAdd();
		appointmentPage.selectClinicFromDropdown(TestData.getInstance().getInputData("chief_complaint_another_clinic_name"));
//		appointmentPage.selectDateofAppointment(TestData.getInstance().getInputData("chief_complaint_future_date"));
		appointmentPage.selectDoctorFromDropdown(patntChiefCmplntData.get("doctor"));
		appointmentPage.selectChiefComplaints("Discolored Tooth");
		appointmentPage.clickOnSaveBtn();
		basePatientLifeCyclePage.clickOnAlert();
		patientDashboardPage.clickOnChiefComplaintlistBtn();
		chiefComplaintListingPage.verifyChiefComplaintByClinicWise(TestData.getInstance().getInputData("chief_complaint_another_clinic_name"));
		chiefComplaintListingPage.chiefComplaintInMainList("Discolored Tooth");
		chiefComplaintListingPage.locationMainList("Discolored Tooth", "NA");
		chiefComplaintListingPage.intensityMainList("Discolored Tooth", "NA");
		chiefComplaintListingPage.periodsInMainList("Discolored Tooth", 0, 0, "NA");
		chiefComplaintListingPage.notesMainList("Discolored Tooth", "NA");
		Assert.assertTrue(basePage.verification().contains("Chief Complaint Listing"));
	}

	/*capture test cases status and status send to extent report
	* redirecting at patient dashboard*/
	
	@AfterMethod(description = STATUS_OF_SCRIPTS, enabled = true)
	public void statusOfScripts(ITestResult result) {
		extentReport.onTestFailure(result);
		extentReport.logger.log(LogStatus.PASS, STATUS_OF_SCRIPTS);
		basePatientLifeCyclePage.clickOnDashBoard();
	}

	/*logout from the prm after all test cases execution*/
	@AfterClass
	public void tearDown() {
		extentReport.report.endTest(extentReport.logger);
		extentReport.report.flush();
		extentReport.report.close();
		basePage.clickOnlogOut();
	}

}
