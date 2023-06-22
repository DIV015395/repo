package com.prm.scripts;

import com.prm.docdashboard.pageobject.*;
import com.prm.pageobjects.utils.ExtentReport;
import com.prm.pageobjects.utils.ReadConfig;
//import com.prm.pageobjects.utils.ReadExcelData;
import com.prm.pageobjects.utils.SheetTest;
import com.prm.pageobjects.utils.TestData;
import com.prm.patientdashboard.pageobject.*;
import com.prm.patientdashboard.pageobject.AppointmentsListPage;
import com.relevantcodes.extentreports.LogStatus;
import java.util.Map;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

public class FeedBackTestCase {
    private LoginPage loginPage = new LoginPage();
    private DoctorDashBoard doctorDashboard = new DoctorDashBoard(loginPage);
    private AppointmentAddPage appointmentPage = new AppointmentAddPage(loginPage);
    private BasePage basePage = new BasePage(loginPage);
    private ExtentReport extentReport = new ExtentReport(loginPage, "FeedBackTestCase");
    private PatientDashboardPage patientDashboardPage = new PatientDashboardPage(loginPage);
    private BasePatientLifeCyclePage basePatientLifeCyclePage = new BasePatientLifeCyclePage(loginPage);
    private OralExamsPage oralExamsPage = new OralExamsPage(loginPage);
    private TreatmentPlansPage treatmentPlansPage = new TreatmentPlansPage(loginPage);
    private TreatmentPlanListingPage treatmentPlanListingPage = new TreatmentPlanListingPage(loginPage);
    private WorksDonePage worksDonePage = new WorksDonePage(loginPage);
    private WorkDoneHistoryPage workDoneHistoryPage = new WorkDoneHistoryPage(loginPage);
    private AppointmentsListPage appointmentsListPage=new AppointmentsListPage(loginPage);
    private FeedbackAddPage feedbackAddPage = new FeedbackAddPage(loginPage);
    private FeedbackListingPage feedbackListingPage = new FeedbackListingPage(loginPage);
    private AppointmentsLisitngPage clinicAppointmentsPage = new AppointmentsLisitngPage(loginPage);

    /**
     * Please update the input before executing the test cases
     */
    private static final String TODAY_DATE = TestData.getInstance().getTodayDate();
    
    
    /**
     * Messages used to print at extent report
     */
    private static final String STARTED_SCRIPTS = "@@ Test scripts has been started here @@";
    private static final String STATUS_OF_SCRIPTS = "@@ Test script has been completed here@@";
    private static final String CREATING_TREATMENT ="Added treatment for the patient and completed the work done for the same patient :-creatingTreatment";
    private static final String DOCTOR_FEEDBACK_ADD_UI_FEEDBACK_LISTING ="Validated feedback add page UI for first time and feed back listing :-doctorFeedbackAddUiFeedbackListing";
    private static final String BEHAVIOUR_FEEDBACK_ADD_UI_PAGE ="Validated Behavior of feedback Add ui page :-behaviourFeedbackAddUiPage";
    private static final String DOCTOR_ADDED_FEEDBACK ="Added feed back by the doctor and validated All mandatory data at feedback listing and feedback listing view modal :-doctorAddedFeedback";
    private static final String EDIT_FEED_BACK ="Edit the feedback from the feedback listing and changes the input and updated input checked at the both feedback listing and feedback view modal :-editFeedBack";

    /*
     * Google Sheet Details
     */
    
    private static final String MODULE_NAME="Feedback";
    private static final String SHEET_NAME="FeedbackTestData";
    private Map<String ,String> feedbackTestScriptData=SheetTest.prepareData(MODULE_NAME, SHEET_NAME, "A2", "G2");

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
        doctorDashboard.doctorDashboardHomePage();
    }

    @BeforeMethod(description = STARTED_SCRIPTS, enabled = true)
    public void verifyAppointmentAddHomePage() {
        extentReport.logger.log(LogStatus.PASS, STARTED_SCRIPTS);
        loginPage.waitForPageLoad();
    }

    /**
     * Added treatment for the patient
     * checked Appointment is available or not for the patient within 24 hr
     * started the treatment and completed All work done of the patient
     * Now checked feedback Add button should be available at Appointment listing after work done completed
     */
    @Test(enabled = true,priority = 1)
    public void creatingTreatment(){
        extentReport.logger.log(LogStatus.PASS, CREATING_TREATMENT);
        basePage.selectClinicFrmHeader(feedbackTestScriptData.get("clinic_name"));
        basePage.enterMobileNo(feedbackTestScriptData.get("patient_mobileNumber"));
        basePage.clickOnSearchBtn();
        basePage.clickOnPatient(feedbackTestScriptData.get("patient_mobileNumber"),feedbackTestScriptData.get("patient_name"));
        patientDashboardPage.hideDueWarningPopup();
        basePatientLifeCyclePage.clickOnAlert();
        patientDashboardPage.clickOnTreatmentPlanAddBtn();
        basePatientLifeCyclePage.clickOnAlert();
        oralExamsPage.clickOnTeethImage("Adult", "24");
        treatmentPlansPage.verifySeletecdTeethOnPopup("24");
        treatmentPlansPage.clickOnConsultationXRays();
        treatmentPlansPage.clickOnTreatments(feedbackTestScriptData.get("treatment_shortName"));
        treatmentPlansPage.saveTreatment();
        basePatientLifeCyclePage.clickOnDashBoard();
        basePatientLifeCyclePage.clickOnAlert();
        patientDashboardPage.clickOnTreatmentPlanListBtn();
        treatmentPlanListingPage.verifyDatesTreatmentPlanList();
        /*Checking start button and booking appointment for appearing start btn in doctor*/
        treatmentPlanListingPage.checkStartBtnNotPresent(TODAY_DATE);
        basePatientLifeCyclePage.clickOnDashBoard();
        basePatientLifeCyclePage.clickOnAlert();
        patientDashboardPage.clickOnAppList();
        appointmentsListPage.appointmentAvailable();
        basePatientLifeCyclePage.clickOnDashBoard();
        basePatientLifeCyclePage.clickOnAlert();
        patientDashboardPage.clickOnAppAdd();
        appointmentPage.selectDoctorFromDropdown(feedbackTestScriptData.get("doctor_name"));
        appointmentPage.clickOnSaveBtn();
        basePatientLifeCyclePage.clickOnAlert();
        patientDashboardPage.clickOnTreatmentPlanListBtn();
        treatmentPlanListingPage.verifyDatesTreatmentPlanList();
        treatmentPlanListingPage.clickOnStartBtn(TODAY_DATE);
        treatmentPlanListingPage.checkedWithoutTreatmentStart();
        treatmentPlanListingPage.selectTreatmentInTreatmentListPage(feedbackTestScriptData.get("treatment_fullName"));
        treatmentPlanListingPage.clickOnStartBtn(TODAY_DATE);
        worksDonePage.checkedTreatmentStartMsgDisplayed();
        worksDonePage.selectDoctor(feedbackTestScriptData.get("treatment_fullName"), feedbackTestScriptData.get("doctor_name"));
        worksDonePage.selectTime(feedbackTestScriptData.get("treatment_fullName"), "30");
        worksDonePage.enterRemarks(feedbackTestScriptData.get("treatment_fullName"), "Change to complete");
        worksDonePage.selectStages(feedbackTestScriptData.get("treatment_fullName"), "Completed");
        worksDonePage.clickOnAdd(feedbackTestScriptData.get("treatment_fullName"));
        workDoneHistoryPage.checkDateTreatment(feedbackTestScriptData.get("treatment_fullName"), TODAY_DATE);
        workDoneHistoryPage.checkSourceNo(feedbackTestScriptData.get("treatment_fullName"), "24");
        workDoneHistoryPage.checkedStatusTreatment(feedbackTestScriptData.get("treatment_fullName"), "Completed");
        workDoneHistoryPage.followUpBtn(feedbackTestScriptData.get("treatment_fullName"));
        workDoneHistoryPage.reTreatBtn(feedbackTestScriptData.get("treatment_fullName"));
        workDoneHistoryPage.checkDataName(feedbackTestScriptData.get("treatment_fullName"));
        workDoneHistoryPage.modifiedDate(feedbackTestScriptData.get("treatment_fullName"), TODAY_DATE);
        workDoneHistoryPage.checkDoctorTreated(feedbackTestScriptData.get("treatment_fullName"), feedbackTestScriptData.get("doctor_name"));
        workDoneHistoryPage.checkedClinic(feedbackTestScriptData.get("treatment_fullName"), feedbackTestScriptData.get("clinic_name"));
        workDoneHistoryPage.checkSpentTime(feedbackTestScriptData.get("treatment_fullName"), "30");
        workDoneHistoryPage.checkStageTreatment(feedbackTestScriptData.get("treatment_fullName"), "Completed");
        workDoneHistoryPage.checkedRemarks(feedbackTestScriptData.get("treatment_fullName"), "Change to complete");
        workDoneHistoryPage.checkedInvoiceListBtn();
        workDoneHistoryPage.checkLabWorkOrderBtn();
        workDoneHistoryPage.checkedPrintBtn();
        basePatientLifeCyclePage.verifyAddNewBtn();
        basePatientLifeCyclePage.dashBoardBtnVerify();
        basePatientLifeCyclePage.clickOnAddNewBtn();
        basePatientLifeCyclePage.noRecordMsgDisplayed();
        worksDonePage.checkLabWorkOrderBtn();
        worksDonePage.checkedInvoiceListBtn();
        worksDonePage.checkedHistoryBtn();
        basePatientLifeCyclePage.dashBoardBtnVerify();
        worksDonePage.checkedPrintBtnNotPresent();
        worksDonePage.checkedAddAllBtnNotPresent();
        basePatientLifeCyclePage.clickOnDashBoard();
        patientDashboardPage.hideDueWarningPopup();
        patientDashboardPage.clickOnAppList();
        appointmentsListPage.checkFeedbackBtn(TODAY_DATE);
        Assert.assertTrue(basePage.verification().contains("Appointments List"));
        loginPage.softAssert().assertAll();
    }

    /**
     * validated feedback Add ui page all web element
     * validated feedback listing page  All web element
     */
    @Test(enabled = true,priority = 2)
    public void doctorFeedbackAddUiFeedbackListing(){
        extentReport.logger.log(LogStatus.PASS, DOCTOR_FEEDBACK_ADD_UI_FEEDBACK_LISTING);
        basePage.selectClinicFrmHeader(feedbackTestScriptData.get("clinic_name"));
        basePage.enterMobileNo(feedbackTestScriptData.get("patient_mobileNumber"));
        basePage.clickOnSearchBtn();
        basePage.clickOnPatient(feedbackTestScriptData.get("patient_mobileNumber"),feedbackTestScriptData.get("patient_name"));
        patientDashboardPage.hideDueWarningPopup();
        basePatientLifeCyclePage.clickOnAlert();
        patientDashboardPage.clickOnAppList();
        appointmentsListPage.checkFeedbackBtn(TODAY_DATE);
        appointmentsListPage.clickOnFeedbackBtn(TODAY_DATE);
        feedbackAddPage.checkFeedBackHeader("Add Feedback");
        feedbackAddPage.verifyPatientName(feedbackTestScriptData.get("patient_name"));
        feedbackAddPage.checkSaveBtn();
        feedbackAddPage.checkResetBtn();
        feedbackAddPage.checkCancelBtn();
        feedbackAddPage.checkDashboardBtn();
        feedbackAddPage.topSectionFeedbackHeader();
        feedbackAddPage.feedBackDateDisplayed();
        feedbackAddPage.feedBackSerialNumberDisplayed();
        feedbackAddPage.feedBackAddFilesDisplayed();
        feedbackAddPage.bottomSectionFeedbackHeader();
        feedbackAddPage.feedbackType("Patient Feedback");
        feedbackAddPage.checkMessageRating();
        feedbackAddPage.rateCount();
        basePage.backTODoctorDashboard();
        doctorDashboard.clickFeedBackListing();
        feedbackListingPage.feedbackListingHeader("Feedback Listing");
        feedbackListingPage.feedbackTypeDropDown("Select Type");
        feedbackListingPage.feedbackSearchType("Select Search By");
        feedbackListingPage.doctorDropDown("Select Doctor");
        feedbackListingPage.detailsFields();
        feedbackListingPage.dateFilter();
        feedbackListingPage.createdRadioBtn();
        feedbackListingPage.submittedRadioBtn();
        feedbackListingPage.searchBtn();
        feedbackListingPage.resetBtn();
        feedbackListingPage.noRecordFoundDisplayed();
        Assert.assertTrue(basePage.verification().contains("Feedback Listing"));
    }

    /**
     * validated feedback date for first time
     * validated serial number is mandatory fields
     * validated file upload is mandatory for rate 1 to 6.
     * validated All web element for rating 1 to 10.
     * validated reset functionality
     * validated cancel functionality
     */
    @Test(enabled = true,priority = 3)
    public void behaviourFeedbackAddUiPage(){
        extentReport.logger.log(LogStatus.PASS, BEHAVIOUR_FEEDBACK_ADD_UI_PAGE);
        basePage.selectClinicFrmHeader(feedbackTestScriptData.get("clinic_name"));
        doctorDashboard.clickOnAppListBtn();
//        clinicAppointmentsPage.selectDoctorFRmDrpDwn(feedbackTestScriptData.get("doctor_name"));
        clinicAppointmentsPage.clickFeedbackBtn(feedbackTestScriptData.get("patient_name"));
        feedbackAddPage.feedbackDate(TODAY_DATE);
        feedbackAddPage.savefeedBackAdd();
        feedbackAddPage.serialMandatoryFields();
        feedbackAddPage.enterSerialNumber("12345");
        feedbackAddPage.savefeedBackAdd();
        feedbackAddPage.toastMsgQuestions();
        feedbackAddPage.selectRating(1);
        feedbackAddPage.options_rating_1();
        feedbackAddPage.savefeedBackAdd();
        feedbackAddPage.fileUploadToastMsg();
        feedbackAddPage.selectRating(2);
        feedbackAddPage.options_rating_2();
        feedbackAddPage.savefeedBackAdd();
        feedbackAddPage.fileUploadToastMsg();
        feedbackAddPage.selectRating(3);
        feedbackAddPage.options_rating_3();
        feedbackAddPage.savefeedBackAdd();
        feedbackAddPage.fileUploadToastMsg();
        feedbackAddPage.selectRating(4);
        feedbackAddPage.options_rating_4();
        feedbackAddPage.savefeedBackAdd();
        feedbackAddPage.fileUploadToastMsg();
        feedbackAddPage.selectRating(5);
        feedbackAddPage.options_rating_5();
        feedbackAddPage.savefeedBackAdd();
        feedbackAddPage.fileUploadToastMsg();
        feedbackAddPage.selectRating(6);
        feedbackAddPage.options_rating_6();
        feedbackAddPage.savefeedBackAdd();
        feedbackAddPage.fileUploadToastMsg();
        feedbackAddPage.selectRating(7);
        feedbackAddPage.options_rating_7();
        feedbackAddPage.selectRating(8);
        feedbackAddPage.options_rating_8();
        feedbackAddPage.selectRating(9);
        feedbackAddPage.options_rating_9();
        feedbackAddPage.selectRating(10);
        feedbackAddPage.options_rating_10();
        feedbackAddPage.resetfeedBackAdd();
        feedbackAddPage.deSelectRating();
        feedbackAddPage.feedbackSerialNumber("");
        feedbackAddPage.cancelfeedBackAdd();
        loginPage.softAssert().assertAll();
        Assert.assertEquals(basePage.verification(),"Appointments/Event Listing");
    }
//
//    /**
//     * validated file Attachment functionality
//     * validated Added feedback with input
//     * checked all data at the feedback listing and feedback listing view modal
//     * After adding the feedback checked at the appointment listing feedback button should be disable
//     */
    @Test(enabled = true,priority = 4)
    public void doctorAddedFeedback(){
        extentReport.logger.log(LogStatus.PASS, DOCTOR_ADDED_FEEDBACK);
        basePage.selectClinicFrmHeader(feedbackTestScriptData.get("clinic_name"));
        doctorDashboard.clickOnAppListBtn();
//        clinicAppointmentsPage.selectDoctorFRmDrpDwn(feedbackTestScriptData.get("doctor_name"));
        clinicAppointmentsPage.clickFeedbackBtn(feedbackTestScriptData.get("patient_name"));
//        feedbackAddPage.feedbackDate(TODAY_DATE);
        feedbackAddPage.enterSerialNumber("12345");
        feedbackAddPage.selectRating(1);
        feedbackAddPage.savefeedBackAdd();
        feedbackAddPage.fileUploadToastMsg();
        feedbackAddPage.uploadFeedbackFiles(System.getProperty("user.dir")+"\\PatientFiles\\File1.jpg");
        feedbackAddPage.removeAttachedFile();
        feedbackAddPage.closeFileRemove();
        feedbackAddPage.removeAttachedFile();
        feedbackAddPage.yesFileRemove();
        feedbackAddPage.feedBackAddFilesDisplayed();
        feedbackAddPage.uploadFeedbackFiles(System.getProperty("user.dir")+"\\PatientFiles\\File1.jpg");
        feedbackAddPage.selectDentistInteractionRate_1();
        feedbackAddPage.addFeedbackNotes_rate_1("testing feedback");
        feedbackAddPage.savefeedBackAdd();
        feedbackListingPage.feedbackListingColumnName();
        feedbackListingPage.patientName_PatientId(feedbackTestScriptData.get("patient_name"));
        feedbackListingPage.feedbackType(feedbackTestScriptData.get("patient_name"),"Patient Feedback");
        feedbackListingPage.feedbackDate(feedbackTestScriptData.get("patient_name"),TODAY_DATE);
        feedbackListingPage.mobileNumber(feedbackTestScriptData.get("patient_name"),feedbackTestScriptData.get("patient_mobileNumber"));
        feedbackListingPage.emailId(feedbackTestScriptData.get("patient_name"),"NA");
        feedbackListingPage.submittedBy(feedbackTestScriptData.get("patient_name"),feedbackTestScriptData.get("doctor_nickName"));
        feedbackListingPage.fileAttached(feedbackTestScriptData.get("patient_name"),"File1");
        feedbackListingPage.feedbackViewBtn(feedbackTestScriptData.get("patient_name"));
        feedbackListingPage.feedbackEditBtn(feedbackTestScriptData.get("patient_name"));
        feedbackListingPage.clickFeedbackViewBtn(feedbackTestScriptData.get("patient_name"));
        feedbackListingPage.headerFeedbackView("Feedback Details");
        feedbackListingPage.patientNameInView(feedbackTestScriptData.get("patient_name"));
        feedbackListingPage.checkFeedbackDetailsHeader();
        feedbackListingPage.clinicNameFeedbackView(feedbackTestScriptData.get("clinic_name"));
        feedbackListingPage.emailIdFeedbackView("NA");
        feedbackListingPage.mobileNoFeedbackView(feedbackTestScriptData.get("patient_mobileNumber"));
        feedbackListingPage.addressFeedbackView("");
        feedbackListingPage.submittionDateFeedbackView(TODAY_DATE);
        feedbackListingPage.serialNoFeedbackView("12345");
        feedbackListingPage.feedbackTypeInView("Patient Feedback");
        feedbackListingPage.ratingFeedbackViewModal("1");
        feedbackListingPage.reasonFeedbackViewModal("Dentist Interaction");
        feedbackListingPage.remarkFeedbackViewModal("testing feedback");
        feedbackListingPage.closeFeedBackViewModal();
        feedbackListingPage.clickOnDashboard();
        doctorDashboard.clickOnAppListBtn();
        clinicAppointmentsPage.selectDoctorFRmDrpDwn(feedbackTestScriptData.get("doctor_name"));
        clinicAppointmentsPage.checkFeedbackBtnDisable(TODAY_DATE);
        Assert.assertEquals(basePage.verification(),"Feedback Listing");
    }

//    /**
//     * validated edit scenario
//     * changes the input from edit and updated input checked at the feedback listing and feedback view modal
//     */
    @Test(enabled = true,priority = 5)
    public void editFeedBack(){
        extentReport.logger.log(LogStatus.PASS, EDIT_FEED_BACK);
        doctorDashboard.clickFeedBackListing();
        feedbackListingPage.clickFeedbackEditBtn(feedbackTestScriptData.get("patient_name"));
        feedbackAddPage.checkFeedBackHeader("Edit Feedback");
        feedbackAddPage.feedbackDate(TODAY_DATE);
        feedbackAddPage.feedbackSerialNumber("12345");
        feedbackAddPage.feedBackAddFilesDisplayed();
        feedbackAddPage.editedFileFeedbackListing("File1");
        feedbackAddPage.feedbackType("Patient Feedback");
        feedbackAddPage.ratingSelected(1);
        feedbackAddPage.enterSerialNumber("23456");
        feedbackAddPage.selectRating(9);
        feedbackAddPage.addFeedbackNotes_rate_9("updated feedback remarks");
        feedbackAddPage.savefeedBackAdd();
        feedbackListingPage.feedbackListingColumnName();
        feedbackListingPage.patientName_PatientId(feedbackTestScriptData.get("patient_name"));
        feedbackListingPage.feedbackType(feedbackTestScriptData.get("patient_name"),"Patient Feedback");
        feedbackListingPage.feedbackDate(feedbackTestScriptData.get("patient_name"),TODAY_DATE);
        feedbackListingPage.mobileNumber(feedbackTestScriptData.get("patient_name"),feedbackTestScriptData.get("patient_mobileNumber"));
        feedbackListingPage.emailId(feedbackTestScriptData.get("patient_name"),"NA");
        feedbackListingPage.submittedBy(feedbackTestScriptData.get("patient_name"),feedbackTestScriptData.get("doctor_nickName"));
        feedbackListingPage.fileAttached(feedbackTestScriptData.get("patient_name"),"File1");
        feedbackListingPage.feedbackViewBtn(feedbackTestScriptData.get("patient_name"));
        feedbackListingPage.feedbackEditBtn(feedbackTestScriptData.get("patient_name"));
        feedbackListingPage.clickFeedbackViewBtn(feedbackTestScriptData.get("patient_name"));
        feedbackListingPage.headerFeedbackView("Feedback Details");
        feedbackListingPage.patientNameInView(feedbackTestScriptData.get("patient_name"));
        feedbackListingPage.checkFeedbackDetailsHeader();
        feedbackListingPage.clinicNameFeedbackView(feedbackTestScriptData.get("clinic_name"));
        feedbackListingPage.emailIdFeedbackView("NA");
        feedbackListingPage.mobileNoFeedbackView(feedbackTestScriptData.get("patient_mobileNumber"));
        feedbackListingPage.addressFeedbackView("");
        feedbackListingPage.submittionDateFeedbackView(TODAY_DATE);
        feedbackListingPage.serialNoFeedbackView("23456");
        feedbackListingPage.feedbackTypeInView("Patient Feedback");
        feedbackListingPage.ratingFeedbackViewModal("9");
        feedbackListingPage.reasonFeedbackViewModal("NA");
        feedbackListingPage.remarkFeedbackViewModal("NA");
        feedbackListingPage.closeFeedBackViewModal();
        Assert.assertEquals(basePage.verification(),"Feedback Listing");
    }


    @AfterMethod(description = STATUS_OF_SCRIPTS, enabled = true)
    public void statusOfScripts(ITestResult result) {
        extentReport.onTestFailure(result);
        extentReport.logger.log(LogStatus.PASS, STATUS_OF_SCRIPTS);
        basePage.backTODoctorDashboard();
    }

    @AfterClass
    public void tearDown() {
        extentReport.report.endTest(extentReport.logger);
        extentReport.report.flush();
        // extentReport.report.close();
        basePage.clickOnlogOut();
    }
}
