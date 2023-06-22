package com.prm.scripts;
import com.prm.docdashboard.pageobject.*;

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
import com.prm.docdashboard.pageobject.AppointmentSchedulerCalenderPage;
import com.prm.docdashboard.pageobject.AppointmentsLisitngPage;
import com.prm.docdashboard.pageobject.BasePage;
import com.prm.docdashboard.pageobject.DoctorDashBoard;
import com.prm.docdashboard.pageobject.LoginPage;
import com.prm.docdashboard.pageobject.PatientDashboardPage;
import com.prm.docdashboard.pageobject.PatientRegistrationPage;
import com.prm.pageobjects.utils.ExtentReport;
import com.prm.pageobjects.utils.ReadConfig;
import com.prm.pageobjects.utils.ReadExcelData;
import com.prm.pageobjects.utils.SheetTest;
import com.prm.pageobjects.utils.TestData;
import com.prm.patientdashboard.pageobject.AppointmentsListPage;
import com.prm.patientdashboard.pageobject.BasePatientLifeCyclePage;
import com.prm.patientdashboard.pageobject.ChiefComplaintAddPage;
import com.prm.patientdashboard.pageobject.ChiefComplaintListingPage;
import com.relevantcodes.extentreports.LogStatus;

public class AppointmentAddScanTestCase {
    private LoginPage loginPage = new LoginPage();
    private DoctorDashBoard doctorDashboard = new DoctorDashBoard(loginPage);
    private AppointmentAddPage appointmentPage = new AppointmentAddPage(loginPage);
    private AppointmentSchedulerCalenderPage appointmentSchedulerCalenderPage = new AppointmentSchedulerCalenderPage(loginPage);
    private AppointmentsLisitngPage clinicAppointmentsPage = new AppointmentsLisitngPage(loginPage);
    private BasePage basePage = new BasePage(loginPage);
    private PatientRegistrationPage patientRegistrationPage = new PatientRegistrationPage(loginPage);
    private PatientDashboardPage PatientDashboardPage = new PatientDashboardPage(loginPage);
    private ExtentReport extentReport = new ExtentReport(loginPage, "AppointmentAddEConsultTestCase");
    private ChiefComplaintListingPage chiefComplaintListingPage = new ChiefComplaintListingPage(loginPage);
    private ChiefComplaintAddPage chiefComplaintAddPage = new ChiefComplaintAddPage(loginPage);
    private BasePatientLifeCyclePage basePatientLifeCyclePage = new BasePatientLifeCyclePage(loginPage);
    private PatientDashboardPage patientDashboardPage = new PatientDashboardPage(loginPage);
    private AppointmentsListPage appointmentsListPage = new AppointmentsListPage(loginPage);

    // Message here which We will print extent execution report after execution
    private static final String APPOINTMENT_SHEDULAR_CALENDER = "Appointment Scheduler Calender";
//    private static final int NO_OF_FUTURE_DAYS = 2;
    private static final String APPOINTMENT_ADD_PAGE = "";
    private static final String APPOINTMENT_E_CONSULT_PAGE = "";
    private static final String MANDATORY_FIELDS = "";
    private static final String BOOK_NEW_APPOINTMENT_E_CONSULT = "";
//    private static final String EDIT_APPOINTMENT = "";
//    private static final String VERIFY_ALREADY_BOOKED_DOCTOR_SLOT = "";
//    private static final String VALIDATE_AUTO_SELECT_PATIENT_FROM_SUGGESTION = "";
    private static final String STATUS_OF_SCRIPTS = "";
    Map<String, String> patntAppnmntData = null;

    @BeforeClass
    public void setup() {
    	patntAppnmntData = prepareData("Appointment", "PatientAppointment", "A4", "X4");
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
        basePage.selectClinicFrmHeader(patntAppnmntData.get("clinicLocation"));
        basePage.clickOnSearchBtn();
    }

    @BeforeMethod(description = APPOINTMENT_ADD_PAGE, enabled = true)
    public void appointmentAddPage() {
    	loginPage.waitForSpinnerToDisappear();
        extentReport.logger.log(LogStatus.PASS, APPOINTMENT_ADD_PAGE);
        String actualTitle = loginPage.getDriver().getTitle();
        if (actualTitle.contains("Doctor Dashboard")) {
//      	 doctorDashboard.clickOnAppListBtn();
           doctorDashboard.clickonAppointmentAdd();
        } else if (actualTitle.contains("Appointment")) {
        	
            loginPage.getDriver().navigate().refresh();
        } 
        else {
            basePage.backToDoctorDashbrd();
            doctorDashboard.clickonAppointmentAdd();
        }
        appointmentPage.toVerifyAppointmentHomePage();
    }
    
    public Map<String, String> prepareData(String moduleName, String sheetName, String rowRange, String colRange){
    	
    		Map<String, String> appnmntData = null;
    		try {
    			appnmntData = SheetTest.getDataFromGoogleSheet(moduleName, sheetName, rowRange, colRange);
    		} catch (GeneralSecurityException e) {
    			e.printStackTrace();
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
    		return appnmntData;
    	}
    
@Test(enabled=true,description = "Verify Scan Appointment Form Fields",priority = 1)
   public void appointmentScanPage() {
        extentReport.logger.log(LogStatus.PASS, APPOINTMENT_E_CONSULT_PAGE);
        appointmentPage.selectScan();
        appointmentPage.patientNameTextFields();
        appointmentPage.patientMobileTextFields();
        appointmentPage.patientIdTextFields();
        appointmentPage.patientEmailIdTextFields();
        appointmentPage.clinicDrpDwn();
        appointmentPage.selectClinicFromDropdown("");
        appointmentPage.dateCalender();
        appointmentPage.timeSlotDrpDwn();
//      appointmentPage.selectingFutureDateAndVerifyStartTime(NO_OF_FUTURE_DAYS);
        appointmentPage.timeDurationTextFields();
        appointmentPage.doctorDrpDwn();
        appointmentPage.scanners();
        appointmentPage.sourceDrpDwn();
        appointmentPage.referralDrpDwn();
        appointmentPage.selectReferralFromDropdown("Patient");
        appointmentPage.referralDetails();
        appointmentPage.notesFields();
        appointmentPage.chiefComplaints();
        appointmentPage.otherChiefComplaint();
        appointmentPage.verifyServiceType("Home");
        appointmentPage.verifyServiceType("Clinic");
        appointmentPage.actionsBtnAppointmentAdd();
        appointmentPage.checkSchedulerOptions();
        appointmentPage.clickOnGoToSchedulerYesBtn();
        Assert.assertTrue(basePage.verification().contains(APPOINTMENT_SHEDULAR_CALENDER));
        basePage.backTODoctorDashboard();
}

    @Test(enabled=true,description = "Verify Scan Appointment Mandatory Form Fields",priority = 2)
    public void mandatoryFields() {
        extentReport.logger.log(LogStatus.PASS, MANDATORY_FIELDS);
        appointmentPage.checkAppointmentInClinic();
        appointmentPage.selectScan();
        appointmentPage.selectClinicFromDropdown(patntAppnmntData.get("scanCenter"));
        appointmentPage.enterMobileNumber(patntAppnmntData.get("patient_mobile"));
//      appointmentPage.appointmentDate(patntAppnmntData.get("appointmentDate"));
        appointmentPage.selectDoctorFromDropdown(patntAppnmntData.get("doctor"));
        appointmentPage.clickOnSaveBtn();
        appointmentPage.enterPatientNameMsg();
        loginPage.getDriver().navigate().refresh();
        appointmentPage.selectScan();
        appointmentPage.selectClinicFromDropdown(patntAppnmntData.get("scanCenter"));
        appointmentPage.enterPatientName(patntAppnmntData.get("patient_name"));
//      appointmentPage.appointmentDate(patntAppnmntData.get("appointmentDate"));
        appointmentPage.selectDoctorFromDropdown(patntAppnmntData.get("doctor"));
        appointmentPage.clickOnSaveBtn();
        appointmentPage.enterMobileNoMsg();
        loginPage.getDriver().navigate().refresh();
        appointmentPage.selectScan();
        appointmentPage.selectClinicFromDropdown(patntAppnmntData.get("scanCenter"));
        appointmentPage.enterMobileNumber("12345");
        appointmentPage.enterPatientName(patntAppnmntData.get("patient_name"));
//      appointmentPage.appointmentDate(patntAppnmntData.get("appointmentDate"));
        appointmentPage.selectDoctorFromDropdown(patntAppnmntData.get("doctor"));
        appointmentPage.clickOnSaveBtn();
        appointmentPage.enterTenDigitMobileNoMsg();
        loginPage.getDriver().navigate().refresh();
        appointmentPage.selectScan();
        appointmentPage.selectClinicFromDropdown(patntAppnmntData.get("scanCenter"));
        appointmentPage.enterMobileNumber("0000000000");
        appointmentPage.enterPatientName(patntAppnmntData.get("patient_name"));
//      appointmentPage.appointmentDate(patntAppnmntData.get("appointmentDate"));
        appointmentPage.selectDoctorFromDropdown(patntAppnmntData.get("doctor"));
        appointmentPage.clickOnSaveBtn();
        appointmentPage.enterValidMobileNoMsg();
        loginPage.getDriver().navigate().refresh();
        appointmentPage.selectScan();
        appointmentPage.selectClinicFromDropdown(patntAppnmntData.get("scanCenter"));
        appointmentPage.enterPatientName(patntAppnmntData.get("patient_name"));
        appointmentPage.enterMobileNumber(patntAppnmntData.get("patient_mobile"));
        appointmentPage.enterEmailAddress("abc");
        appointmentPage.selectDoctorFromDropdown(patntAppnmntData.get("doctor"));
        appointmentPage.clickOnSaveBtn();
        appointmentPage.enterValidEmailAddressMsg();
        loginPage.getDriver().navigate().refresh();
        appointmentPage.selectScan();
        appointmentPage.selectClinicFromDropdown(patntAppnmntData.get("scanCenter"));
        appointmentPage.enterPatientName(patntAppnmntData.get("patient_name"));
        appointmentPage.enterMobileNumber(patntAppnmntData.get("patient_mobile"));
        appointmentPage.selectDoctorFromDropdown(patntAppnmntData.get("doctor"));
        appointmentPage.clickOnSaveBtn();
        appointmentPage.validateServiceTypeMandatoryFieldsMessages("Home");
        appointmentPage.validateServiceTypeMandatoryFieldsMessages("Clinic");
        loginPage.getDriver().navigate().refresh();
        appointmentPage.selectScan();
        appointmentPage.selectClinicFromDropdown(patntAppnmntData.get("scanCenter"));
        appointmentPage.enterPatientName(patntAppnmntData.get("patient_name"));
        appointmentPage.enterMobileNumber(patntAppnmntData.get("patient_mobile"));
        appointmentPage.clickOnAppResetBtn();
        appointmentPage.enterPatientName("clinic head");
        appointmentPage.enterMobileNumber("1111111111");
        appointmentPage.getTextPatientName("clinic head");
        appointmentPage.getTextPatientMobile("1111111111");
        Assert.assertTrue(basePage.verification().contains("Appointment"));
    }
    
    @Test(enabled=true,description = "Add Scan Appointment",priority = 3)
    public void bookNewAppointmentScanHome() {
        extentReport.logger.log(LogStatus.PASS, BOOK_NEW_APPOINTMENT_E_CONSULT);
        appointmentPage.checkAppointmentInClinic();
        appointmentPage.selectScan();
        appointmentPage.selectClinicFromDropdown(patntAppnmntData.get("scanCenter"));
        appointmentPage.enterPatientName(patntAppnmntData.get("patient_name"));
        appointmentPage.enterMobileNumber(patntAppnmntData.get("patient_mobile"));
        appointmentPage.enterEmailAddress(patntAppnmntData.get("email"));
        appointmentPage.selectClinicFromDropdown(patntAppnmntData.get("scanCenter"));
//      appointmentPage.appointmentDate(patntAppnmntData.get("appointmentDate"));
        appointmentPage.selectTimeSlotFromDropdown(patntAppnmntData.get("slot"));
        appointmentPage.enterTimeDuration(patntAppnmntData.get("duration"));
        appointmentPage.selectDoctorFromDropdown(patntAppnmntData.get("doctor"));
        appointmentPage.selectReferralFromDropdown(patntAppnmntData.get("referral"));
        appointmentPage.enterReferralDetails(patntAppnmntData.get("referalDetails"));
        appointmentPage.clickOnChiefComplaints(patntAppnmntData.get("chiefComplaint"));
        appointmentPage.enterNote(patntAppnmntData.get("notes"));
        appointmentPage.addHomeServiceAddress("B20");
        appointmentPage.addHomeServiceCity("Gorakhpur");
        appointmentPage.addHomeServicePincode("201013");
        appointmentPage.selectServiceState("Uttar Pradesh");
        appointmentPage.clickOnSaveBtn();
        basePage.selectClinicFrmHeader(patntAppnmntData.get("scanCenter"));
        doctorDashboard.clickOnAppListBtn();
        clinicAppointmentsPage.clickOnLastPage();
        clinicAppointmentsPage.appointmentStatus(patntAppnmntData.get("patient_name"), "New");
        clinicAppointmentsPage.type(patntAppnmntData.get("patient_name"), "Scan");
        clinicAppointmentsPage.appointmentType(patntAppnmntData.get("patient_name"), "Confirmed");
        clinicAppointmentsPage.verifyClinic(patntAppnmntData.get("patient_name"), patntAppnmntData.get("scanCenter"));
        clinicAppointmentsPage.checkDoctorName(patntAppnmntData.get("patient_name"),patntAppnmntData.get("doctor"));
        clinicAppointmentsPage.verifyServiceType(patntAppnmntData.get("patient_name"),"@Home");
        clinicAppointmentsPage.verifyServiceLocation(patntAppnmntData.get("patient_name"),"B20");
        clinicAppointmentsPage.viewButton(patntAppnmntData.get("patient_name"));
        clinicAppointmentsPage.editButton(patntAppnmntData.get("patient_name"));
        clinicAppointmentsPage.deleteButton(patntAppnmntData.get("patient_name"));
        clinicAppointmentsPage.openViewModal(patntAppnmntData.get("patient_name"));
        clinicAppointmentsPage.patientIdView();
        clinicAppointmentsPage.patientNameView(patntAppnmntData.get("patient_name"));
        clinicAppointmentsPage.emailIdView(patntAppnmntData.get("email"));
        clinicAppointmentsPage.mobileNumView(patntAppnmntData.get("patient_mobile"));
        clinicAppointmentsPage.clinicNameView(patntAppnmntData.get("scanCenter"));
//      clinicAppointmentsPage.DateOfAppView();
        clinicAppointmentsPage.durationView(patntAppnmntData.get("duration"));
        clinicAppointmentsPage.doctorView(patntAppnmntData.get("doctor"));
        clinicAppointmentsPage.referralView(patntAppnmntData.get("referral"));
        clinicAppointmentsPage.referralDetails(patntAppnmntData.get("referalDetails"));
        clinicAppointmentsPage.sourceView(patntAppnmntData.get("source"));
//      clinicAppointmentsPage.chiefComplaintViewpatntAppnmntData.get("chiefComplaint"));
        clinicAppointmentsPage.notesView(patntAppnmntData.get("notes"));
        clinicAppointmentsPage.closeViewModal();
        clinicAppointmentsPage.clickOnPatient(patntAppnmntData.get("patient_name"));
     	basePatientLifeCyclePage.clickOnAlert();
        patientDashboardPage.clickOnAppList();
        appointmentsListPage.checkAppointmentTime(patntAppnmntData.get("appointmentDate"), patntAppnmntData.get("slot"));
//      appointmentsListPage.chiefComplaintpatntAppnmntData.get("chiefComplaint"));
        appointmentsListPage.type("Scan");
        appointmentsListPage.appointmentType("Confirmed");
        appointmentsListPage.clinicName(patntAppnmntData.get("scanCenter"));
        appointmentsListPage.doctorName(patntAppnmntData.get("doctor"));
        appointmentsListPage.checkViewBtn();
        appointmentsListPage.checkEditBtn();
        appointmentsListPage.checkDeleteBtn();
        basePatientLifeCyclePage.clickOnDashBoard();
        basePatientLifeCyclePage.clickOnAlert();
        PatientDashboardPage.clickOnChiefComplaintAddBtn();
        basePatientLifeCyclePage.clickOnAlert();
        chiefComplaintAddPage.verifyChiefComplaintsInputList("NA");
        basePatientLifeCyclePage.clickOnDashBoard();
        basePatientLifeCyclePage.clickOnAlert();
        PatientDashboardPage.clickOnChiefComplaintlistBtn();
        chiefComplaintListingPage.chiefComplaintInMainList("NA");
        Assert.assertTrue(basePage.verification().contains("Chief Complaint Listing"));
    }
    
    
  @Test(enabled=true,description = "Delete Appointment Add Scan Appointment",priority = 4)
  public void deleteAppointment() {
  	appointmentPage.clickOnCancelBtn();
      basePage.selectClinicFrmHeader(patntAppnmntData.get("scanCenter"));
      doctorDashboard.clickOnAppListBtn();
      clinicAppointmentsPage.enterToDate(patntAppnmntData.get("appointmentDate"));
      clinicAppointmentsPage.enterFromDate(patntAppnmntData.get("appointmentDate"));
      clinicAppointmentsPage.clickOnSearchBtn();
      clinicAppointmentsPage.clickOnLastPage();
      clinicAppointmentsPage.appointmentStatus(patntAppnmntData.get("patient_name"), "New");
      clinicAppointmentsPage.appointmentType(patntAppnmntData.get("patient_name"), "Confirmed");
      clinicAppointmentsPage.type(patntAppnmntData.get("patient_name"), "Scan");
      clinicAppointmentsPage.verifyClinic(patntAppnmntData.get("patient_name"),"Amanora");
      clinicAppointmentsPage.checkDoctorName(patntAppnmntData.get("patient_name"), patntAppnmntData.get("doctor"));
      clinicAppointmentsPage.viewButton(patntAppnmntData.get("patient_name"));
      clinicAppointmentsPage.editButton(patntAppnmntData.get("patient_name"));
      clinicAppointmentsPage.deleteButton(patntAppnmntData.get("patient_name"));
      clinicAppointmentsPage.clickDeleteButton(patntAppnmntData.get("patient_name"));
      clinicAppointmentsPage.verifyWebElementDeletePopup();
      clinicAppointmentsPage.selectReasonDelete();
      clinicAppointmentsPage.clickYesDeletePopup();
      clinicAppointmentsPage.strikeOff(patntAppnmntData.get("patient_name"));
      basePage.backTODoctorDashboard();
  }
  
  @Test(enabled=true,description = "Add Scan Clinic Appointment",priority = 5)
  public void bookNewAppointmentScanClinic() {
      extentReport.logger.log(LogStatus.PASS, BOOK_NEW_APPOINTMENT_E_CONSULT);
      appointmentPage.selectScan();
      appointmentPage.selectClinicFromDropdown(patntAppnmntData.get("scanCenter"));
      appointmentPage.enterPatientName(patntAppnmntData.get("patient_name"));
      appointmentPage.enterMobileNumber(patntAppnmntData.get("patient_mobile"));
      appointmentPage.enterEmailAddress(patntAppnmntData.get("email"));
//    appointmentPage.selectClinicFromDropdown(patntAppnmntData.get("scanCenter"));
//    appointmentPage.appointmentDate(patntAppnmntData.get("appointmentDate"));
      appointmentPage.selectTimeSlotFromDropdown(patntAppnmntData.get("slot"));
      appointmentPage.enterTimeDuration(patntAppnmntData.get("duration"));
      appointmentPage.selectDoctorFromDropdown(patntAppnmntData.get("doctor"));
      appointmentPage.selectReferralFromDropdown(patntAppnmntData.get("referral"));
      appointmentPage.enterReferralDetails(patntAppnmntData.get("referalDetails"));
      appointmentPage.clickOnChiefComplaints(patntAppnmntData.get("chiefComplaint"));
      appointmentPage.enterNote(patntAppnmntData.get("notes"));
      appointmentPage.selectClinicServiceType();
      appointmentPage.selectServiceClinic("Amanora");
      appointmentPage.clickOnSaveBtn();
      basePage.selectClinicFrmHeader(patntAppnmntData.get("scanCenter"));
      doctorDashboard.clickOnAppListBtn();
      clinicAppointmentsPage.clickOnLastPage();
      clinicAppointmentsPage.appointmentStatus(patntAppnmntData.get("patient_name"), "New");
      clinicAppointmentsPage.type(patntAppnmntData.get("patient_name"), "Scan");
      clinicAppointmentsPage.appointmentType(patntAppnmntData.get("patient_name"), "Confirmed");
      clinicAppointmentsPage.verifyClinic(patntAppnmntData.get("patient_name"), patntAppnmntData.get("scanCenter"));
      clinicAppointmentsPage.checkDoctorName(patntAppnmntData.get("patient_name"), patntAppnmntData.get("doctor"));
      clinicAppointmentsPage.verifyServiceType(patntAppnmntData.get("patient_name"),"@Clinic");
      clinicAppointmentsPage.verifyServiceLocation(patntAppnmntData.get("patient_name"),"B20");
      clinicAppointmentsPage.viewButton(patntAppnmntData.get("patient_name"));
      clinicAppointmentsPage.editButton(patntAppnmntData.get("patient_name"));
      clinicAppointmentsPage.deleteButton(patntAppnmntData.get("patient_name"));
      clinicAppointmentsPage.openViewModal(patntAppnmntData.get("patient_name"));
      clinicAppointmentsPage.patientIdView();
      clinicAppointmentsPage.patientNameView(patntAppnmntData.get("patient_name"));
      clinicAppointmentsPage.emailIdView(patntAppnmntData.get("email"));
      clinicAppointmentsPage.mobileNumView(patntAppnmntData.get("patient_mobile"));
      clinicAppointmentsPage.clinicNameView(patntAppnmntData.get("scanCenter"));
//    clinicAppointmentsPage.DateOfAppView();
      clinicAppointmentsPage.durationView(patntAppnmntData.get("duration"));
      clinicAppointmentsPage.doctorView(patntAppnmntData.get("doctor"));
      clinicAppointmentsPage.referralView(patntAppnmntData.get("referral"));
      clinicAppointmentsPage.referralDetails(patntAppnmntData.get("referalDetails"));
      clinicAppointmentsPage.sourceView(patntAppnmntData.get("source"));
//    clinicAppointmentsPage.chiefComplaintViewpatntAppnmntData.get("chiefComplaint"));
      clinicAppointmentsPage.notesView(patntAppnmntData.get("notes"));
      clinicAppointmentsPage.closeViewModal();
      clinicAppointmentsPage.clickOnPatient(patntAppnmntData.get("patient_name"));
   	  basePatientLifeCyclePage.clickOnAlert();
      patientDashboardPage.clickOnAppList();
      appointmentsListPage.checkAppointmentTime(patntAppnmntData.get("appointmentDate"), patntAppnmntData.get("slot"));
//    appointmentsListPage.chiefComplaintpatntAppnmntData.get("chiefComplaint"));
      appointmentsListPage.type("Scan");
      appointmentsListPage.appointmentType("Confirmed");
      appointmentsListPage.clinicName("Amanora");
      appointmentsListPage.doctorName(patntAppnmntData.get("doctor"));
      appointmentsListPage.checkViewBtn();
      appointmentsListPage.checkEditBtn();
      appointmentsListPage.checkDeleteBtn();
      basePatientLifeCyclePage.clickOnDashBoard();
      basePatientLifeCyclePage.clickOnAlert();
      PatientDashboardPage.clickOnChiefComplaintAddBtn();
      basePatientLifeCyclePage.clickOnAlert();
      chiefComplaintAddPage.verifyChiefComplaintsInputList("NA");
      basePatientLifeCyclePage.clickOnDashBoard();
      basePatientLifeCyclePage.clickOnAlert();
     PatientDashboardPage.clickOnChiefComplaintlistBtn();
     chiefComplaintListingPage.chiefComplaintInMainList("NA");
     Assert.assertTrue(basePage.verification().contains("Chief Complaint Listing"));
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
        extentReport.report.close();
        basePage.clickOnlogOut();
        loginPage.close();
    }
}
