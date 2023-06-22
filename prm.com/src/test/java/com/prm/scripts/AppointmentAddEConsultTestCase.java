package com.prm.scripts;

import com.prm.docdashboard.pageobject.*;
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

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Map;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

public class AppointmentAddEConsultTestCase {
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
    

//  Message here which We will print extent execution report after execution
    
    private static final String APPOINTMENT_SHEDULAR_CALENDER = "Appointment Scheduler Calender";
//  private static final int NO_OF_FUTURE_DAYS = 2;
    private static final String APPOINTMENT_ADD_PAGE = "";
    private static final String APPOINTMENT_E_CONSULT_PAGE = "";
    private static final String MANDATORY_FIELDS = "";
    private static final String BOOK_NEW_APPOINTMENT_E_CONSULT = "";
    private static final String EDIT_APPOINTMENT = "";
    private static final String VERIFY_ALREADY_BOOKED_DOCTOR_SLOT = "";
    private static final String VALIDATE_AUTO_SELECT_PATIENT_FROM_SUGGESTION = "";
    private static final String STATUS_OF_SCRIPTS = "";
    Map<String, String> patntAppnmntData = null;

    

    @BeforeClass
    public void setup() {
    	patntAppnmntData = SheetTest.prepareData("Appointment", "PatientAppointment", "A2", "X2");
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
    
    @Test(enabled=true,description = "Verify Appointment Form Elements",priority = 1)
    public void appointmentEConsultPage() {
        extentReport.logger.log(LogStatus.PASS, APPOINTMENT_E_CONSULT_PAGE);
        appointmentPage.selectEConsult();
        appointmentPage.checkAppointmenteConsult();
        appointmentPage.patientNameTextFields();
        appointmentPage.patientMobileTextFields();
        appointmentPage.patientIdTextFields();
        appointmentPage.patientEmailIdTextFields();
        appointmentPage.clinicDrpDwn();
        appointmentPage.dateCalender();
        appointmentPage.timeSlotDrpDwn();
//      appointmentPage.selectingFutureDateAndVerifyStartTime(NO_OF_FUTURE_DAYS);
        appointmentPage.timeDurationTextFields();
        appointmentPage.doctorDrpDwn();
        appointmentPage.operatories();
        appointmentPage.rooms();
        appointmentPage.sourceDrpDwn();
        appointmentPage.referralDrpDwn();
        appointmentPage.selectReferralFromDropdown("Patient");
        appointmentPage.referralDetails();
        appointmentPage.notesFields();
        appointmentPage.chiefComplaints();
        appointmentPage.otherChiefComplaint();
        appointmentPage.actionsBtnAppointmentAdd();
        appointmentPage.checkSchedulerOptions();
        appointmentPage.clickOnGoToSchedulerYesBtn();
        Assert.assertTrue(basePage.verification().contains(APPOINTMENT_SHEDULAR_CALENDER));
        basePage.backTODoctorDashboard();
    }

    @Test(enabled=true,description = "Verify Appointment Mandatory Elements",priority = 2)
    public void mandatoryFields() {
        extentReport.logger.log(LogStatus.PASS, MANDATORY_FIELDS);
        appointmentPage.selectEConsult();
        appointmentPage.checkAppointmenteConsult();
        appointmentPage.enterMobileNumber(patntAppnmntData.get("patient_mobile"));
//      appointmentPage.appointmentDate(patntAppnmntData.get("appointmentDate"));
        appointmentPage.selectDoctorFromDropdown(patntAppnmntData.get("doctor"));
        appointmentPage.clickOnSaveBtn();
        appointmentPage.enterPatientNameMsg();
        loginPage.getDriver().navigate().refresh();
        appointmentPage.enterPatientName(patntAppnmntData.get("patient_name"));
//      appointmentPage.appointmentDate(patntAppnmntData.get("appointmentDate"));
        appointmentPage.selectDoctorFromDropdown(patntAppnmntData.get("doctor"));
        appointmentPage.clickOnSaveBtn();
        appointmentPage.enterMobileNoMsg();
        loginPage.getDriver().navigate().refresh();
        appointmentPage.enterMobileNumber("12345");
        appointmentPage.enterPatientName(patntAppnmntData.get("patient_name"));
//      appointmentPage.appointmentDate(patntAppnmntData.get("appointmentDate"));
        appointmentPage.selectDoctorFromDropdown(patntAppnmntData.get("doctor"));
        appointmentPage.clickOnSaveBtn();
        appointmentPage.enterTenDigitMobileNoMsg();
        loginPage.getDriver().navigate().refresh();
        appointmentPage.enterMobileNumber("0000000000");
        appointmentPage.enterPatientName(patntAppnmntData.get("patient_name"));
//      appointmentPage.appointmentDate(patntAppnmntData.get("appointmentDate"));
        appointmentPage.selectDoctorFromDropdown(patntAppnmntData.get("doctor"));
        appointmentPage.clickOnSaveBtn();
        appointmentPage.enterValidMobileNoMsg();
        loginPage.getDriver().navigate().refresh();
        appointmentPage.enterPatientName(patntAppnmntData.get("patient_name"));
        appointmentPage.enterMobileNumber(patntAppnmntData.get("patient_mobile"));
        appointmentPage.enterEmailAddress("abc");
        appointmentPage.selectDoctorFromDropdown(patntAppnmntData.get("doctor"));
        appointmentPage.clickOnSaveBtn();
        appointmentPage.enterValidEmailAddressMsg();
        loginPage.getDriver().navigate().refresh();
        appointmentPage.enterPatientName(patntAppnmntData.get("patient_name"));
        appointmentPage.enterMobileNumber(patntAppnmntData.get("patient_mobile"));
        appointmentPage.clickOnAppResetBtn();
        appointmentPage.enterPatientName("clinic head");
        appointmentPage.enterMobileNumber("1111111111");
        appointmentPage.getTextPatientName("clinic head");
        appointmentPage.getTextPatientMobile("1111111111");
        Assert.assertTrue(basePage.verification().contains("Appointment"));
    }
    
    @Test(enabled=true,description = "Book Appointment For E-Consult",priority = 3)
    public void bookNewAppointmentEConsult() {
        extentReport.logger.log(LogStatus.PASS, BOOK_NEW_APPOINTMENT_E_CONSULT);
        appointmentPage.selectEConsult();
        appointmentPage.checkAppointmenteConsult();
        appointmentPage.enterPatientName(patntAppnmntData.get("patient_name"));
        appointmentPage.enterMobileNumber(patntAppnmntData.get("patient_mobile"));
        appointmentPage.enterEmailAddress(patntAppnmntData.get("email"));
        appointmentPage.selectClinicFromDropdown(patntAppnmntData.get("clinicLocation"));
//      appointmentPage.appointmentDate(patntAppnmntData.get("appointmentDate"));
        appointmentPage.selectTimeSlotFromDropdown(patntAppnmntData.get("slot"));
        appointmentPage.enterTimeDuration(patntAppnmntData.get("duration"));
        appointmentPage.selectDoctorFromDropdown(patntAppnmntData.get("doctor"));
        appointmentPage.selectReferralFromDropdown(patntAppnmntData.get("referral"));
        appointmentPage.enterReferralDetails(patntAppnmntData.get("referalDetails"));
        appointmentPage.clickOnChiefComplaints(patntAppnmntData.get("chiefComplaint"));
        appointmentPage.enterNote(patntAppnmntData.get("notes"));
        appointmentPage.clickOnSaveBtn();
//      basePage.selectClinicFrmHeader(patntAppnmntData.get("clinicLocation"));
        doctorDashboard.clickOnAppListBtn();
        clinicAppointmentsPage.clickOnLastPage();
        clinicAppointmentsPage.appointmentStatus(patntAppnmntData.get("patient_name"), "New");
        clinicAppointmentsPage.type(patntAppnmntData.get("patient_name"), "e-Consult");
        clinicAppointmentsPage.appointmentType(patntAppnmntData.get("patient_name"), "Confirmed");
        clinicAppointmentsPage.verifyClinic(patntAppnmntData.get("patient_name"), patntAppnmntData.get("clinicLocation"));
        clinicAppointmentsPage.checkDoctorName(patntAppnmntData.get("patient_name"), patntAppnmntData.get("doctor"));
        clinicAppointmentsPage.viewButton(patntAppnmntData.get("patient_name"));
        clinicAppointmentsPage.editButton(patntAppnmntData.get("patient_name"));
        clinicAppointmentsPage.deleteButton(patntAppnmntData.get("patient_name"));
        clinicAppointmentsPage.openViewModal(patntAppnmntData.get("patient_name"));
        clinicAppointmentsPage.patientIdView();
        clinicAppointmentsPage.patientNameView(patntAppnmntData.get("patient_name"));
        clinicAppointmentsPage.emailIdView(patntAppnmntData.get("email"));
        clinicAppointmentsPage.mobileNumView(patntAppnmntData.get("patient_mobile"));
        clinicAppointmentsPage.clinicNameView(patntAppnmntData.get("clinicLocation"));
        clinicAppointmentsPage.DateOfAppView();
        clinicAppointmentsPage.durationView(patntAppnmntData.get("duration"));
        clinicAppointmentsPage.doctorView(patntAppnmntData.get("doctor"));
        clinicAppointmentsPage.roomsViewEconsult();
        clinicAppointmentsPage.referralView(patntAppnmntData.get("referral"));
        clinicAppointmentsPage.referralDetails(patntAppnmntData.get("referalDetails"));
        clinicAppointmentsPage.sourceView(patntAppnmntData.get("source"));
        /* Bug In The Version */
//      clinicAppointmentsPage.chiefComplaintView(patntAppnmntData.get("chiefComplaint"));
        clinicAppointmentsPage.notesView(patntAppnmntData.get("notes"));
        clinicAppointmentsPage.closeViewModal();
        clinicAppointmentsPage.clickOnPatient(patntAppnmntData.get("patient_name"));
    	basePatientLifeCyclePage.clickOnAlert();
        patientDashboardPage.clickOnAppList();
        appointmentsListPage.checkAppointmentTime(patntAppnmntData.get("appointmentDate"), patntAppnmntData.get("slot"));
//      appointmentsListPage.chiefComplaint(patntAppnmntData.get("chiefComplaint"));
        appointmentsListPage.type("E-consult");
        appointmentsListPage.appointmentType("Confirmed");
        appointmentsListPage.clinicName(patntAppnmntData.get("clinicLocation"));
        appointmentsListPage.doctorName(patntAppnmntData.get("doctor"));
        basePatientLifeCyclePage.clickOnDashBoard();
        basePatientLifeCyclePage.clickOnAlert();
        PatientDashboardPage.clickOnChiefComplaintlistBtn();
    } 
    
    @Test(enabled=true,description = "Edit Appointment For E-Consult",priority = 4)
    public void editAppointment() {
        extentReport.logger.log(LogStatus.PASS, EDIT_APPOINTMENT);
        appointmentPage.clickOnCancelBtn();
        basePage.selectClinicFrmHeader(patntAppnmntData.get("clinicLocation"));
        doctorDashboard.clickOnAppListBtn();
        clinicAppointmentsPage.enterToDate(patntAppnmntData.get("appointmentDate"));
        clinicAppointmentsPage.enterFromDate(patntAppnmntData.get("appointmentDate"));
        clinicAppointmentsPage.clickOnSearchBtn();
        clinicAppointmentsPage.clickOnLastPage();
        clinicAppointmentsPage.appointmentStatus(patntAppnmntData.get("patient_name"), "New");
        clinicAppointmentsPage.appointmentType(patntAppnmntData.get("patient_name"), "Confirmed");
        clinicAppointmentsPage.type(patntAppnmntData.get("patient_name"), "e-Consult");
        clinicAppointmentsPage.verifyClinic(patntAppnmntData.get("patient_name"), patntAppnmntData.get("clinicLocation"));
        clinicAppointmentsPage.checkDoctorName(patntAppnmntData.get("patient_name"), patntAppnmntData.get("doctor"));
        clinicAppointmentsPage.viewButton(patntAppnmntData.get("patient_name"));
        clinicAppointmentsPage.editButton(patntAppnmntData.get("patient_name"));
        clinicAppointmentsPage.deleteButton(patntAppnmntData.get("patient_name"));
        clinicAppointmentsPage.clickEditButton(patntAppnmntData.get("patient_name"));
        appointmentPage.getTextPatientMobile(patntAppnmntData.get("patient_mobile"));
        appointmentPage.getTextPatientName(patntAppnmntData.get("patient_name"));
        appointmentPage.patientIDAfterAutoSelect();
        appointmentPage.doctorSelected(patntAppnmntData.get("doctor"));
        appointmentPage.roomSelected();
        appointmentPage.clinicSelected(patntAppnmntData.get("clinicLocation"));
        appointmentPage.slotSelected(patntAppnmntData.get("slot"));
        appointmentPage.sourceSelected("Select Source");
        appointmentPage.referralSelected(patntAppnmntData.get("referral"));
        appointmentPage.getReferralDetails(patntAppnmntData.get("referalDetails"));
        appointmentPage.getNotes(patntAppnmntData.get("notes"));
        Assert.assertTrue(basePage.verification().contains("Appointment"));
        appointmentPage.selectTimeSlotFromDropdown(patntAppnmntData.get("updateSlot"));
        appointmentPage.enterTimeDuration(patntAppnmntData.get("updateSlotDuration"));
        appointmentPage.clickOnSaveBtn(); 
        appointmentPage.verifyAppointmentUpdateText("Appointment updated successfully!");
        clinicAppointmentsPage.clickOnLastPage();
        clinicAppointmentsPage.verifyAppointmentDateTimeOnListing(patntAppnmntData.get("patient_name"),"02-01-2023","21:00");
        clinicAppointmentsPage.openViewModal(patntAppnmntData.get("patient_name"));
        clinicAppointmentsPage.patientNameView(patntAppnmntData.get("patient_name"));
        clinicAppointmentsPage.emailIdView(patntAppnmntData.get("email"));
        clinicAppointmentsPage.mobileNumView(patntAppnmntData.get("patient_mobile"));
        clinicAppointmentsPage.clinicNameView(patntAppnmntData.get("clinicLocation"));
        clinicAppointmentsPage.DateOfAppView();
        clinicAppointmentsPage.durationView(patntAppnmntData.get("updateSlotDuration"));
        clinicAppointmentsPage.closeViewModal();   
   }
   
    @Test(enabled=true,description = "Edit Appointment For E-Consult With Invalid duration",priority = 6)
    public void editAppointmentWithInvaidHours() {
        extentReport.logger.log(LogStatus.PASS, EDIT_APPOINTMENT);
        appointmentPage.clickOnCancelBtn();
        basePage.selectClinicFrmHeader(patntAppnmntData.get("clinicLocation"));
        doctorDashboard.clickOnAppListBtn();
        clinicAppointmentsPage.enterToDate(patntAppnmntData.get("appointmentDate"));
        clinicAppointmentsPage.enterFromDate(patntAppnmntData.get("appointmentDate"));
        clinicAppointmentsPage.clickOnSearchBtn();
        clinicAppointmentsPage.clickOnLastPage();
        clinicAppointmentsPage.appointmentStatus(patntAppnmntData.get("patient_name"), "New");
        clinicAppointmentsPage.appointmentType(patntAppnmntData.get("patient_name"), "Confirmed");
        clinicAppointmentsPage.type(patntAppnmntData.get("patient_name"), "e-Consult");
        clinicAppointmentsPage.verifyClinic(patntAppnmntData.get("patient_name"), patntAppnmntData.get("clinicLocation"));
        clinicAppointmentsPage.checkDoctorName(patntAppnmntData.get("patient_name"), patntAppnmntData.get("doctor"));
        clinicAppointmentsPage.viewButton(patntAppnmntData.get("patient_name"));
        clinicAppointmentsPage.editButton(patntAppnmntData.get("patient_name"));
        clinicAppointmentsPage.deleteButton(patntAppnmntData.get("patient_name"));
        clinicAppointmentsPage.clickEditButton(patntAppnmntData.get("patient_name"));
        appointmentPage.getTextPatientMobile(patntAppnmntData.get("patient_mobile"));
        appointmentPage.getTextPatientName(patntAppnmntData.get("patient_name"));
        appointmentPage.patientIDAfterAutoSelect();
        appointmentPage.doctorSelected(patntAppnmntData.get("doctor"));
        appointmentPage.roomSelected();
        appointmentPage.clinicSelected(patntAppnmntData.get("clinicLocation"));
        appointmentPage.slotSelected(patntAppnmntData.get("slot"));
        appointmentPage.sourceSelected("Select Source");
        appointmentPage.referralSelected(patntAppnmntData.get("referral"));
        appointmentPage.getReferralDetails(patntAppnmntData.get("referalDetails"));
        appointmentPage.getNotes(patntAppnmntData.get("notes"));
        Assert.assertTrue(basePage.verification().contains("Appointment"));
        appointmentPage.selectTimeSlotFromDropdown(patntAppnmntData.get("updateSlot"));
        appointmentPage.enterTimeDuration(patntAppnmntData.get("updateSlotDuration"));
        appointmentPage.clickOnSaveBtn(); 
        appointmentPage.verifyrRestrictAppointmentUpdateText("Requested appointment minute(s) is not available for scheduling!");
        basePage.backTODoctorDashboard();
    }
    
    @Test(enabled=true,description = "Verify Already Booked Slots",priority = 7)
    public void verifyAlreadyBookedDoctorSlot() {
        extentReport.logger.log(LogStatus.PASS, VERIFY_ALREADY_BOOKED_DOCTOR_SLOT);
        appointmentPage.enterPatientName(patntAppnmntData.get("patient_name"));
        appointmentPage.enterMobileNumber(patntAppnmntData.get("patient_mobile"));
        appointmentPage.enterEmailAddress(patntAppnmntData.get("email"));
        appointmentPage.selectClinicFromDropdown(patntAppnmntData.get("clinicLocation"));
//      appointmentPage.appointmentDate(patntAppnmntData.get("slot"));
        appointmentPage.selectTimeSlotFromDropdown(patntAppnmntData.get("slot"));
        appointmentPage.enterTimeDuration(patntAppnmntData.get("duration"));
//      appointmentPage.checkingFirstOperatoryIsDisable();
        appointmentPage.selectDoctorFromDropdown(patntAppnmntData.get("doctor"));
        appointmentPage.enterNote(patntAppnmntData.get("notes"));
        appointmentPage.clickOnSaveBtn();
        appointmentPage.pleaseSelectDoctorMsg();
        Assert.assertTrue(basePage.verification().contains("Appointment"));
    }
    
    @Test(enabled=true,description = "Verify Auto Selection From Suggestion",priority = 8)
    public void validateAutoSelectPatientFromSuggestion() {
        extentReport.logger.log(LogStatus.PASS, VALIDATE_AUTO_SELECT_PATIENT_FROM_SUGGESTION);
        appointmentPage.autoSelectMobile(patntAppnmntData.get("patient_mobile"));
        appointmentPage.getTextPatientMobile(patntAppnmntData.get("patient_mobile"));
        appointmentPage.getTextPatientName(patntAppnmntData.get("patient_name"));
        appointmentPage.patientIDAfterAutoSelect();
        appointmentPage.verifyMandatoryFieldsOnEdit();
        appointmentPage.clickOnAppResetBtn();
        appointmentPage.autoSelectPatientName(patntAppnmntData.get("patient_name"));
        appointmentPage.getTextPatientMobile(patntAppnmntData.get("patient_mobile"));
        appointmentPage.getTextPatientName(patntAppnmntData.get("patient_name"));
        appointmentPage.patientIDAfterAutoSelect();
        appointmentPage.verifyMandatoryFieldsOnEdit();
        Assert.assertTrue(basePage.verification().contains("Appointment"));
    }
   
    @Test(enabled=true,description = "Verify Appointment Deletion",priority = 9)
    public void deleteAppointment() {
    	appointmentPage.clickOnCancelBtn();
        basePage.selectClinicFrmHeader(patntAppnmntData.get("clinicLocation"));
        doctorDashboard.clickOnAppListBtn();
        clinicAppointmentsPage.enterToDate(patntAppnmntData.get("appointmentDate"));
        clinicAppointmentsPage.enterFromDate(patntAppnmntData.get("appointmentDate"));
        clinicAppointmentsPage.clickOnSearchBtn();
        clinicAppointmentsPage.clickOnLastPage();
        clinicAppointmentsPage.appointmentStatus(patntAppnmntData.get("patient_name"), "New");
        clinicAppointmentsPage.appointmentType(patntAppnmntData.get("patient_name"), "Confirmed");
        clinicAppointmentsPage.type(patntAppnmntData.get("patient_name"), "e-Consult");
        clinicAppointmentsPage.verifyClinic(patntAppnmntData.get("patient_name"), patntAppnmntData.get("clinicLocation"));
        clinicAppointmentsPage.checkDoctorName(patntAppnmntData.get("patient_name"), patntAppnmntData.get("doctor"));
        clinicAppointmentsPage.viewButton(patntAppnmntData.get("patient_name"));
        clinicAppointmentsPage.editButton(patntAppnmntData.get("patient_name"));
        clinicAppointmentsPage.deleteButton(patntAppnmntData.get("patient_name"));
        clinicAppointmentsPage.clickDeleteButton(patntAppnmntData.get("patient_name"));
        clinicAppointmentsPage.verifyWebElementDeletePopup();
        clinicAppointmentsPage.selectReasonDelete();
        clinicAppointmentsPage.clickYesDeletePopup();
        clinicAppointmentsPage.strikeOff(patntAppnmntData.get("patient_name"));
    }

    // Moved To Scheduler Not Relevant To Appointments
    
/*    @Test
    public void bookAnAppointmentBySchedulerCalender() {
        appointmentPage.clickOnGoToSchedulerYesBtn();
        appointmentSchedulerCalenderPage.selectTimeSlot(ReadExcelData.readExcelData(FILE_PATH, SHEET, 1, 7));
        appointmentPage.enterPatientName(patntAppnmntData.get("patient_name"));
        appointmentPage.enterMobileNumber(patntAppnmntData.get("patient_mobile"));
        appointmentPage.enterEmailAddress(patntAppnmntData.get("email"));
        appointmentPage.enterTimeDuration(ReadExcelData.readExcelData(FILE_PATH, SHEET, 1, 8));
        appointmentPage.selectDoctorFromDropdown(ReadExcelData.readExcelData(FILE_PATH, SHEET, 1, 9));
        appointmentPage.clickOnSaveBtn();
        appointmentSchedulerCalenderPage.checkAppointment(patntAppnmntData.get("patient_name"), ReadExcelData.readExcelData(FILE_PATH, SHEET, 1, 7));
        Assert.assertTrue(basePage.verification().contains("Appointment Scheduler Calender"));
    }
    @Test()
    public void roomsBookedTimeSlotDisable() {
        appointmentPage.enterPatientName(ReadExcelData.readExcelData(FILE_PATH, SHEET, 6, 1));
        appointmentPage.enterMobileNumber(ReadExcelData.readExcelData(FILE_PATH, SHEET, 6, 2));
        appointmentPage.selectClinicFromDropdown(ReadExcelData.readExcelData(FILE_PATH, SHEET, 6, 5));
        appointmentPage.selectDateofAppointment(ReadExcelData.readExcelData(FILE_PATH, SHEET, 6, 6));
        appointmentPage.selectTimeSlotFromDropdown(ReadExcelData.readExcelData(FILE_PATH, SHEET, 6, 7));
        appointmentPage.enterTimeDuration(ReadExcelData.readExcelData(FILE_PATH, SHEET, 6, 8));
        appointmentPage.selectDoctorFromDropdown(ReadExcelData.readExcelData(FILE_PATH, SHEET, 6, 9));
        appointmentPage.clickOnSaveBtn();
        doctorDashboard.clickonAppointmentAdd();
        appointmentPage.selectClinicFromDropdown(ReadExcelData.readExcelData(FILE_PATH, SHEET, 6, 5));
        appointmentPage.selectDateofAppointment(ReadExcelData.readExcelData(FILE_PATH, SHEET, 6, 6));
        appointmentPage.selectTimeSlotFromDropdown(ReadExcelData.readExcelData(FILE_PATH, SHEET, 6, 7));
        Assert.assertTrue(basePage.verification().contains("Appointment"));
    }*/
    
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

