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

public class AppointmentAddInClinicTestCase{
    private LoginPage loginPage = new LoginPage();
    private DoctorDashBoard doctorDashboard = new DoctorDashBoard(loginPage);
    private AppointmentAddPage appointmentPage = new AppointmentAddPage(loginPage);
    private AppointmentSchedulerCalenderPage appointmentSchedulerCalenderPage = new AppointmentSchedulerCalenderPage(loginPage);
    private AppointmentsLisitngPage appointmentsListingPage = new AppointmentsLisitngPage(loginPage);
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
    private static final int NO_OF_FUTURE_DAYS = 2;
    private static final String APPOINTMENT_ADD_PAGE = "";
    private static final String APPOINTMENT_E_CONSULT_PAGE = "";
    private static final String MANDATORY_FIELDS = "";
    private static final String BOOK_NEW_APPOINTMENT_E_CONSULT = "";
    private static final String EDIT_APPOINTMENT = "";
    private static final String VERIFY_ALREADY_BOOKED_DOCTOR_SLOT = "";
    private static final String VALIDATE_AUTO_SELECT_PATIENT_FROM_SUGGESTION = "";
    private static final String STATUS_OF_SCRIPTS = "";
    private static final String BOOK_NEW_APPOINTMENT_IN_CLINIC ="";
    private static final String BOOK_TENTATIVE_APPOINTMENT ="";
    private static final String APPOINTMENT_IN_CLINIC_PAGE ="";
    Map<String, String> patntAppnmntData = null;

    @BeforeClass
    public void setup() {
    	patntAppnmntData = prepareData("Appointment", "PatientAppointment", "A3", "X3");
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
    public void appointmentPage() {
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

    @Test(enabled=true,description = "Verify Appointment Form Elements",priority = 1)
    public void appointmentInClinicPage() {
        extentReport.logger.log(LogStatus.PASS, APPOINTMENT_IN_CLINIC_PAGE);
        appointmentPage.checkAppointmentInClinic();
        appointmentPage.patientMobileTextFields();
        appointmentPage.patientNameTextFields();
        appointmentPage.patientIdTextFields();
        appointmentPage.patientEmailIdTextFields();
        appointmentPage.selectInClinic();
        appointmentPage.clinicDrpDwn();
        appointmentPage.dateCalender();
        appointmentPage.timeSlotDrpDwn();
 //     appointmentPage.selectingFutureDateAndVerifyStartTime(5);
        appointmentPage.timeDurationTextFields();
        appointmentPage.doctorDrpDwn();
        appointmentPage.checkFirstOperatoriesIsSelected();
        appointmentPage.sourceDrpDwn();
        appointmentPage.referralDrpDwn();
        appointmentPage.selectReferralFromDropdown("Patient");
        appointmentPage.referralDetails();
        appointmentPage.notesFields();
        appointmentPage.tentativeCheckBox();
        appointmentPage.chiefComplaints();
        appointmentPage.otherChiefComplaint();
        appointmentPage.actionsBtnAppointmentAdd();
        appointmentPage.checkSchedulerOptions();
        appointmentPage.clickOnGoToSchedulerYesBtn();
        Assert.assertTrue(basePage.verification().contains("Appointment Scheduler Calender"));
    }

    @Test(enabled=true,description = "Verify Appointment Mandatory Elements",priority = 2)
    public void mandatoryFields() {
        extentReport.logger.log(LogStatus.PASS, MANDATORY_FIELDS);
        appointmentPage.checkAppointmentInClinic();
        appointmentPage.enterMobileNumber(patntAppnmntData.get("patient_mobile"));
 //     appointmentPage.appointmentDate(patntAppnmntData.get("appointmentDate"));
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
   //   appointmentPage.appointmentDate(patntAppnmntData.get("appointmentDate"));
        appointmentPage.selectDoctorFromDropdown(patntAppnmntData.get("doctor"));
        appointmentPage.clickOnSaveBtn();
        appointmentPage.enterTenDigitMobileNoMsg();
        loginPage.getDriver().navigate().refresh();
        appointmentPage.enterMobileNumber("0000000000");
        appointmentPage.enterPatientName(patntAppnmntData.get("patient_name"));
    //  appointmentPage.appointmentDate(patntAppnmntData.get("appointmentDate"));
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

    @Test(enabled=true,description = "Verify Book In-Clinic Appointments",priority = 3)
    public void bookNewAppointmentInClinic() {
        extentReport.logger.log(LogStatus.PASS, BOOK_NEW_APPOINTMENT_IN_CLINIC);
        appointmentPage.checkAppointmentInClinic();
        appointmentPage.enterMobileNumber(patntAppnmntData.get("patient_mobile"));
        appointmentPage.enterPatientName(patntAppnmntData.get("patient_name"));
        appointmentPage.enterEmailAddress(patntAppnmntData.get("email"));
        appointmentPage.selectClinicFromDropdown(patntAppnmntData.get("clinicLocation"));
    //  appointmentPage.appointmentDate(patntAppnmntData.get("appointmentDate"));
        appointmentPage.selectTimeSlotFromDropdown(patntAppnmntData.get("slot"));
        appointmentPage.enterTimeDuration(patntAppnmntData.get("duration"));
        appointmentPage.selectDoctorFromDropdown(patntAppnmntData.get("doctor"));
        appointmentPage.selectReferralFromDropdown(patntAppnmntData.get("referral"));
        appointmentPage.enterReferralDetails(patntAppnmntData.get("referalDetails"));
        appointmentPage.clickOnChiefComplaints(patntAppnmntData.get("chiefComplaint"));
        appointmentPage.enterNote(patntAppnmntData.get("notes"));
        appointmentPage.openCovidForm();
       /* covidDeclarationForm.patientNameCovidForm(patntAppnmntData.get("patient_name"));
        covidDeclarationForm.selectFirstQuestion("None of Them");
        covidDeclarationForm.noOption(2);
        covidDeclarationForm.noOption(3);
        covidDeclarationForm.noOption(4);
        covidDeclarationForm.noOption(5);
        covidDeclarationForm.noOption(6);
        covidDeclarationForm.noOption(7);
        covidDeclarationForm.noOption(8);
        covidDeclarationForm.noOption(9);
        covidDeclarationForm.greenFlag();
        covidDeclarationForm.saveCovidForm();*/
        appointmentPage.patientCovidGreen();
        appointmentPage.clickOnSaveBtn(); 
        basePage.selectClinicFrmHeader(patntAppnmntData.get("clinicLocation"));
        doctorDashboard.clickOnAppListBtn();
        appointmentsListingPage.patientFlag(patntAppnmntData.get("patient_name"), "green");
        appointmentsListingPage.appointmentStatus(patntAppnmntData.get("patient_name"), "New");
        appointmentsListingPage.type(patntAppnmntData.get("patient_name"), "In-Clinic");
        appointmentsListingPage.appointmentType(patntAppnmntData.get("patient_name"), "Confirmed");
        appointmentsListingPage.verifyClinic(patntAppnmntData.get("patient_name"), patntAppnmntData.get("clinicLocation"));
        appointmentsListingPage.checkDoctorName(patntAppnmntData.get("patient_name"), patntAppnmntData.get("doctor"));
        appointmentsListingPage.viewButton(patntAppnmntData.get("patient_name"));
        appointmentsListingPage.editButton(patntAppnmntData.get("patient_name"));
        appointmentsListingPage.deleteButton(patntAppnmntData.get("patient_name"));
        appointmentsListingPage.openViewModal(patntAppnmntData.get("patient_name"));
        appointmentsListingPage.patientIdView();
        appointmentsListingPage.patientNameView(patntAppnmntData.get("patient_name"));
        appointmentsListingPage.emailIdView(patntAppnmntData.get("email"));
        appointmentsListingPage.mobileNumView(patntAppnmntData.get("patient_mobile"));
        appointmentsListingPage.clinicNameView(patntAppnmntData.get("clinicLocation"));
        appointmentsListingPage.DateOfAppView();
        appointmentsListingPage.durationView(patntAppnmntData.get("duration"));
        appointmentsListingPage.doctorView(patntAppnmntData.get("doctor"));
        appointmentsListingPage.operatoriesView();
        appointmentsListingPage.referralView(patntAppnmntData.get("referral"));
        appointmentsListingPage.referralDetails(patntAppnmntData.get("referalDetails"));
        appointmentsListingPage.sourceView(patntAppnmntData.get("source"));
        appointmentsListingPage.chiefComplaintView(patntAppnmntData.get("chiefComplaint"));
        appointmentsListingPage.notesView(patntAppnmntData.get("notes"));
        appointmentsListingPage.closeViewModal();
        appointmentsListingPage.clickOnPatient(patntAppnmntData.get("patient_name"));
        patientRegistrationPage.selectGenderMale(patntAppnmntData.get("patient"));
        patientRegistrationPage.enterAlterContactName(patntAppnmntData.get("patient_name"));
        patientRegistrationPage.enterAlternateMobileNO(patntAppnmntData.get("patient_mobile"));
        patientRegistrationPage.enterAge("27");
        patientRegistrationPage.clickOnApproveSave();
        basePatientLifeCyclePage.clickOnAlert();
        patientDashboardPage.clickOnAppList();
        appointmentsListPage.checkAppointmentTime(patntAppnmntData.get("appointmentDate"), patntAppnmntData.get("slot"));
        appointmentsListPage.chiefComplaint(patntAppnmntData.get("chiefComplaint"));
        appointmentsListPage.type("In-clinic");
        appointmentsListPage.appointmentType("Confirmed");
        appointmentsListPage.clinicName(patntAppnmntData.get("clinicLocation"));
        appointmentsListPage.doctorName(patntAppnmntData.get("doctor"));
        appointmentsListPage.checkViewBtn();
        appointmentsListPage.checkEditBtn();
        appointmentsListPage.checkDeleteBtn();
        appointmentsListPage.openViewModal();
        appointmentsListingPage.patientIdView();
        appointmentsListingPage.patientNameView(patntAppnmntData.get("patient_name"));
        appointmentsListingPage.emailIdView(patntAppnmntData.get("email"));
        appointmentsListingPage.mobileNumView(patntAppnmntData.get("patient_mobile"));
        appointmentsListingPage.clinicNameView(patntAppnmntData.get("clinicLocation"));
        appointmentsListingPage.DateOfAppView();
        appointmentsListingPage.durationView(patntAppnmntData.get("duration"));
        appointmentsListingPage.doctorView(patntAppnmntData.get("doctor"));
        appointmentsListingPage.operatoriesView();
        appointmentsListingPage.referralView(patntAppnmntData.get("referral"));
        appointmentsListingPage.referralDetails(patntAppnmntData.get("referalDetails"));
        appointmentsListingPage.sourceView(patntAppnmntData.get("source"));
        appointmentsListingPage.chiefComplaintView(patntAppnmntData.get("chiefComplaint"));
        appointmentsListingPage.notesView(patntAppnmntData.get("notes"));
        appointmentsListingPage.closeViewModal();
        basePatientLifeCyclePage.clickOnDashBoard();
        basePatientLifeCyclePage.clickOnAlert();
        PatientDashboardPage.clickOnChiefComplaintAddBtn();
        basePatientLifeCyclePage.clickOnAlert();
      chiefComplaintAddPage.verifyChiefComplaintsInputList("Pain");

    }

    @Test(enabled=true,description = "Verify Book Tentative Appointments",priority = 4)
    public void bookTentativeAppointment() {
        extentReport.logger.log(LogStatus.PASS, BOOK_TENTATIVE_APPOINTMENT);
  //    appointmentPage.clickOnCancelBtn();
        basePage.selectClinicFrmHeader(patntAppnmntData.get("clinicLocation"));
        doctorDashboard.clickOnAppListBtn();
        appointmentsListingPage.enterToDate(patntAppnmntData.get("appointmentDate"));
        appointmentsListingPage.enterFromDate(patntAppnmntData.get("appointmentDate"));
        appointmentsListingPage.clickOnSearchBtn();
        appointmentsListingPage.appointmentStatus(patntAppnmntData.get("patient_name"), "New");
        appointmentsListingPage.appointmentType(patntAppnmntData.get("patient_name"), "Confirmed");
        appointmentsListingPage.type(patntAppnmntData.get("patient_name"), "In-Clinic");
        appointmentsListingPage.verifyClinic(patntAppnmntData.get("patient_name"), patntAppnmntData.get("clinicLocation"));
        appointmentsListingPage.checkDoctorName(patntAppnmntData.get("patient_name"), patntAppnmntData.get("doctor"));
        appointmentsListingPage.viewButton(patntAppnmntData.get("patient_name"));
        appointmentsListingPage.editButton(patntAppnmntData.get("patient_name"));
        appointmentsListingPage.deleteButton(patntAppnmntData.get("patient_name")); 
        appointmentsListingPage.clickEditButton(patntAppnmntData.get("patient_name"));
        appointmentPage.getTextPatientMobile(patntAppnmntData.get("patient_mobile"));
        appointmentPage.getTextPatientName(patntAppnmntData.get("patient_name"));
        appointmentPage.patientIDAfterAutoSelect();
        appointmentPage.doctorSelected(patntAppnmntData.get("doctor"));
        appointmentPage.clinicSelected(patntAppnmntData.get("clinicLocation"));
        appointmentPage.slotSelected(patntAppnmntData.get("slot")); 
        appointmentPage.sourceSelected("Select Source");
        appointmentPage.referralSelected(patntAppnmntData.get("referral"));
        appointmentPage.getReferralDetails(patntAppnmntData.get("referalDetails"));
        appointmentPage.getNotes(patntAppnmntData.get("notes"));
        appointmentPage.clickOnTentative();
        appointmentPage.clickOnSaveBtn();
        appointmentsListingPage.appointmentStatus(patntAppnmntData.get("patient_name"), "New");
        appointmentsListingPage.type(patntAppnmntData.get("patient_name"), "In-Clinic");
        appointmentsListingPage.appointmentType(patntAppnmntData.get("patient_name"), "Tentative");
        appointmentsListingPage.verifyClinic(patntAppnmntData.get("patient_name"), patntAppnmntData.get("clinicLocation"));
        appointmentsListingPage.checkDoctorName(patntAppnmntData.get("patient_name"), patntAppnmntData.get("doctor"));
        appointmentsListingPage.viewButton(patntAppnmntData.get("patient_name"));
        appointmentsListingPage.editButton(patntAppnmntData.get("patient_name"));
        appointmentsListingPage.openViewModal(patntAppnmntData.get("patient_name"));
        appointmentsListingPage.patientIdView();
        appointmentsListingPage.tentativeHeaderView();
        appointmentsListingPage.patientNameView(patntAppnmntData.get("patient_name"));
        appointmentsListingPage.emailIdView(patntAppnmntData.get("email"));
        appointmentsListingPage.mobileNumView(patntAppnmntData.get("patient_mobile"));
        appointmentsListingPage.clinicNameView(patntAppnmntData.get("clinicLocation"));
        appointmentsListingPage.DateOfAppView();
        appointmentsListingPage.durationView(patntAppnmntData.get("duration"));
        appointmentsListingPage.doctorView(patntAppnmntData.get("doctor"));
        appointmentsListingPage.operatoriesView();
        appointmentsListingPage.referralView(patntAppnmntData.get("referral"));
        appointmentsListingPage.referralDetails(patntAppnmntData.get("referalDetails"));
        appointmentsListingPage.sourceView(patntAppnmntData.get("source"));
        appointmentsListingPage.chiefComplaintView(patntAppnmntData.get("chiefComplaint"));
        appointmentsListingPage.notesView(patntAppnmntData.get("notes"));
        appointmentsListingPage.closeViewModal();
        appointmentsListingPage.clickEditButton(patntAppnmntData.get("patient_name"));
        appointmentPage.verifyTentativeAfterClickOnEditBtn();
        appointmentPage.verifyMandatoryFieldsOnEdit();
        Assert.assertTrue(basePage.verification().contains("Appointment"));
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
        // extentReport.report.close();
        basePage.clickOnlogOut();
        //loginPage.close();
    }
}
