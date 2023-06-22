package com.prm.scripts;

import com.prm.docdashboard.pageobject.*;
import com.prm.pageobjects.utils.ExtentReport;
import com.prm.pageobjects.utils.ReadConfig;
import com.prm.pageobjects.utils.ReadExcelData;
import com.prm.pageobjects.utils.TestData;
import com.prm.patientdashboard.pageobject.*;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

public class AppointmentCovidTestcase {
    private LoginPage loginPage = new LoginPage();
    private DoctorDashBoard doctorDashboard = new DoctorDashBoard(loginPage);
    private CovidDeclarationForm covidDeclarationForm = new CovidDeclarationForm(loginPage);
    private CovidAppointmentNewUI covidAppointmentNewUI = new CovidAppointmentNewUI(loginPage);
    private AppointmentAddPage appointmentPage = new AppointmentAddPage(loginPage);
    private AppointmentsLisitngPage clinicAppointmentsPage = new AppointmentsLisitngPage(loginPage);
    private BasePage basePage = new BasePage(loginPage);
    private PatientRegistrationPage patientRegistrationPage = new PatientRegistrationPage(loginPage);
    private PatientDashboardPage patientDashboardPage = new PatientDashboardPage(loginPage);
    private ExtentReport extentReport = new ExtentReport(loginPage, "AppointmentCovidTestcase");
    private BasePatientLifeCyclePage basePatientLifeCyclePage = new BasePatientLifeCyclePage(loginPage);
    private AppointmentsListPage appointmentsListPage = new AppointmentsListPage(loginPage);
    private OralExamsPage oralExamsPage = new OralExamsPage(loginPage);
    private TreatmentPlansPage treatmentPlansPage = new TreatmentPlansPage(loginPage);
    private TreatmentPlanListingPage treatmentPlanListingPage = new TreatmentPlanListingPage(loginPage);
    private WorksDonePage worksDonePage = new WorksDonePage(loginPage);
    private WorkDoneHistoryPage workDoneHistoryPage = new WorkDoneHistoryPage(loginPage);

    //file used to store input data
    private static final String FILE_PATH = TestData.getInstance().getInputData("covid_test_data_file_path");
    private static final String APPOINTMENT_SHEET = TestData.getInstance().getInputData("appointment_covid_test_data_sheet_name");
    private static final String TREATMENT_SHEET = TestData.getInstance().getInputData("treatment_covid_test_data_sheet_Name");
    private static final String TODAY_DATE = TestData.getInstance().getTodayDate();

    // Message here which We will print extent execution report after execution
    private static final String DOCTOR_DASHBOARD = "@@ Test scripts has been started here @@";
    private static final String STATUS_OF_SCRIPTS = "@@ Test script has been completed here@@";
    private static final String APPOINTMENT_COVID_PATIENT="Redirected at the appointment add page\n" +
            "     * checked default text for covid at appointment page\n" +
            "     * enter new patient details for appointment and trying to save the appointment\n" +
            "     * Verified covid alert message for new patient and grey button,covid modal open button\n" +
            "     * verifying all the question present at covid modal with their respective options\n" +
            "     * validated all question mandatory filed one by one with their error message";
    private static final String BOOK_APPOINTMENT_GREEN_FLAG ="* Book appointment for a new patient with green covid status\n" +
            "     * redirected to appointment listing page checked same appointment with green status\n" +
            "     * Also open covid form at the appointment listing and checked same option selected\n" +
            "     * clicking on same appointment it will redirect to the patient registration page\n" +
            "     * enter the alternate name,alternate mobile,age and registered the patient it will redirect to patient dashboard\n" +
            "     * close alert modal at patient dashboard and open the covid modal and verify same options selected and close the covid modal\n" +
            "     * now redirect at appointment listing(patient dashboard) and open covid modal and check all the data at the covis modal";
    private static final String TREATMENT_GREEN_PATIENT ="Search same patient at global search and click on the patient it will redirect to patient dashboard\n" +
            "     * now close alert modal at patient dashboard\n" +
            "     * now select at teeth and select two treatment of the diagnosis and save it and verify same at treatment input listing\n" +
            "     * now try to start treatment from treatment plan input listing and treatment plan listing without temperature and oximeter checked alert message in both cases\n" +
            "     * now update the temperature and oximeter and start the treatment from treatment input list and treatment plan listing";
    private static final String WORK_DONE_ADD_PAGE ="verify the treatment details at the work done add page and work done history page";
    private static final String TREATMENT_STAGE_INPROGRESS="Changing the work done stages and checked All new updated data at work done add page and work done history page";
    private static final String TREATMENT_STAGE_COMPLETE="Changing the stages of the treatment to complete and checked all new changes at the work done history and work done add";
    private static final String FOLLOW_UP="Validated all cases of the follow up modal and checked same at work done history and work done add";
    private static final String CONVERTING_GREEN_TO_RED_FLAG ="Now converting the covid status from green to red\n" +
            "     * Adding new treatment for red covid patient\n" +
            "     * checking checkbox should not be present in treatment input listing\n" +
            "     * and also checking start should not be present at the treatment plan listing";
    private static final String RE_TREAT="Validated retreat for the treatment scenario at the both page work done history and work done add page";
    private static final String APPOINTMENT_RED_COVID_PATIENT ="Redirecting at Appointment add page\n" +
            "     * selecting same patient from the suggestion\n" +
            "     * validated appointment should not be book for red covid flag patient";


    /**
     * Redirecting to the provides url in qa.properties
     * entering the username, password and click on submit button
     * verifying it is redirected at doctor dashboard page successfully
     */
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

    /**
     * This always check test cases should be start from the doctor dashboard page
     */
    @BeforeMethod()
    public void doctorDashboard() {
        extentReport.logger.log(LogStatus.PASS, DOCTOR_DASHBOARD);
        Assert.assertTrue(basePage.verification().contains("Doctor Dashboard"));
    }

    /**
     * Redirected at the appointment add page
     * checked default text for covid at appointment page
     * enter new patient details for appointment and trying to save the appointment
     * Verified covid alert message for new patient and grey button,covid modal open button
     * verifying all the question present at covid modal with their respective options
     * validated all question mandatory filed one by one with their error message
     */
    @Test(priority = 1)
    public void appointmentCovidPatient() {
        extentReport.logger.log(LogStatus.PASS, APPOINTMENT_COVID_PATIENT);
        doctorDashboard.clickonAppointmentAdd();
        appointmentPage.covidOption();
        appointmentPage.enterPatientName(ReadExcelData.readExcelData(FILE_PATH, APPOINTMENT_SHEET, 1, 1));
        appointmentPage.enterMobileNumber(ReadExcelData.readExcelData(FILE_PATH, APPOINTMENT_SHEET, 1, 2));
        appointmentPage.enterEmailAddress(ReadExcelData.readExcelData(FILE_PATH, APPOINTMENT_SHEET, 1, 3));
        appointmentPage.selectClinicFromDropdown(ReadExcelData.readExcelData(FILE_PATH, APPOINTMENT_SHEET, 1, 5));
        appointmentPage.appointmentDate(ReadExcelData.readExcelData(FILE_PATH, APPOINTMENT_SHEET, 1, 6));
        appointmentPage.selectTimeSlotFromDropdown(ReadExcelData.readExcelData(FILE_PATH, APPOINTMENT_SHEET, 1, 7));
        appointmentPage.enterTimeDuration(ReadExcelData.readExcelData(FILE_PATH, APPOINTMENT_SHEET, 1, 8));
        appointmentPage.selectDoctorFromDropdown(ReadExcelData.readExcelData(FILE_PATH, APPOINTMENT_SHEET, 1, 9));
        appointmentPage.enterNote(ReadExcelData.readExcelData(FILE_PATH, APPOINTMENT_SHEET, 1, 10));
        appointmentPage.clickOnSaveBtn();
//        appointmentPage.covidAlertMsgGrey();
        appointmentPage.checkPatientCovidStatus();
        appointmentPage.openCovidForm();
        covidAppointmentNewUI.patientNameCovidForm(ReadExcelData.readExcelData(FILE_PATH, APPOINTMENT_SHEET, 1, 1));
        covidAppointmentNewUI.checkActionsNewPatient();
        covidAppointmentNewUI.question_1();
        covidAppointmentNewUI.question_2();
        covidAppointmentNewUI.question_3();
        covidAppointmentNewUI.question_4();
        covidAppointmentNewUI.question_5();
        covidAppointmentNewUI.question_6();
        covidAppointmentNewUI.question_7();
        covidAppointmentNewUI.question_8();
        covidAppointmentNewUI.question_9();
        covidAppointmentNewUI.question_10();
        covidAppointmentNewUI.question_11();
        covidAppointmentNewUI.question_12();
        covidAppointmentNewUI.noOption(2);
        covidAppointmentNewUI.noOption(3);
        covidAppointmentNewUI.noOption(4);
        covidAppointmentNewUI.noOption(5);
        covidAppointmentNewUI.noOption(6);
        covidAppointmentNewUI.noOption(7);
        covidAppointmentNewUI.noOption(8);
        covidAppointmentNewUI.noOption(9);
        covidAppointmentNewUI.saveCovidForm();
        covidAppointmentNewUI.errorMsgDisplayed();
        covidAppointmentNewUI.closeCovidForm();
        appointmentPage.openCovidForm();
        covidAppointmentNewUI.patientNameCovidForm(ReadExcelData.readExcelData(FILE_PATH, APPOINTMENT_SHEET, 1, 1));
        covidAppointmentNewUI.selectFirstQuestion("High Fever");
        covidAppointmentNewUI.noOption(3);
        covidAppointmentNewUI.noOption(4);
        covidAppointmentNewUI.noOption(5);
        covidAppointmentNewUI.noOption(6);
        covidAppointmentNewUI.noOption(7);
        covidAppointmentNewUI.noOption(8);
        covidAppointmentNewUI.noOption(9);
        covidAppointmentNewUI.saveCovidForm();
        covidAppointmentNewUI.errorMsgDisplayed();
        covidAppointmentNewUI.closeCovidForm();
        appointmentPage.openCovidForm();
        covidAppointmentNewUI.patientNameCovidForm(ReadExcelData.readExcelData(FILE_PATH, APPOINTMENT_SHEET, 1, 1));
        covidAppointmentNewUI.selectFirstQuestion("None of Them");
        covidAppointmentNewUI.noOption(2);
        covidAppointmentNewUI.noOption(4);
        covidAppointmentNewUI.noOption(5);
        covidAppointmentNewUI.noOption(6);
        covidAppointmentNewUI.noOption(7);
        covidAppointmentNewUI.noOption(8);
        covidAppointmentNewUI.noOption(9);
        covidAppointmentNewUI.saveCovidForm();
        covidAppointmentNewUI.errorMsgDisplayed();
        covidAppointmentNewUI.closeCovidForm();
        appointmentPage.openCovidForm();
        covidAppointmentNewUI.patientNameCovidForm(ReadExcelData.readExcelData(FILE_PATH, APPOINTMENT_SHEET, 1, 1));
        covidAppointmentNewUI.selectFirstQuestion("None of Them");
        covidAppointmentNewUI.noOption(2);
        covidAppointmentNewUI.noOption(3);
        covidAppointmentNewUI.noOption(5);
        covidAppointmentNewUI.noOption(6);
        covidAppointmentNewUI.noOption(7);
        covidAppointmentNewUI.noOption(8);
        covidAppointmentNewUI.noOption(9);
        covidAppointmentNewUI.saveCovidForm();
        covidAppointmentNewUI.errorMsgDisplayed();
        covidAppointmentNewUI.closeCovidForm();
        appointmentPage.openCovidForm();
        covidAppointmentNewUI.patientNameCovidForm(ReadExcelData.readExcelData(FILE_PATH, APPOINTMENT_SHEET, 1, 1));
        covidAppointmentNewUI.selectFirstQuestion("None of Them");
        covidAppointmentNewUI.noOption(2);
        covidAppointmentNewUI.noOption(3);
        covidAppointmentNewUI.noOption(4);
        covidAppointmentNewUI.noOption(6);
        covidAppointmentNewUI.noOption(7);
        covidAppointmentNewUI.noOption(8);
        covidAppointmentNewUI.noOption(9);
        covidAppointmentNewUI.saveCovidForm();
        covidAppointmentNewUI.errorMsgDisplayed();
        covidAppointmentNewUI.closeCovidForm();
        appointmentPage.openCovidForm();
        covidAppointmentNewUI.patientNameCovidForm(ReadExcelData.readExcelData(FILE_PATH, APPOINTMENT_SHEET, 1, 1));
        covidAppointmentNewUI.selectFirstQuestion("None of Them");
        covidAppointmentNewUI.noOption(2);
        covidAppointmentNewUI.noOption(3);
        covidAppointmentNewUI.noOption(4);
        covidAppointmentNewUI.noOption(5);
        covidAppointmentNewUI.noOption(7);
        covidAppointmentNewUI.noOption(8);
        covidAppointmentNewUI.noOption(9);
        covidAppointmentNewUI.saveCovidForm();
        covidAppointmentNewUI.errorMsgDisplayed();
        covidAppointmentNewUI.closeCovidForm();
        appointmentPage.openCovidForm();
        covidAppointmentNewUI.patientNameCovidForm(ReadExcelData.readExcelData(FILE_PATH, APPOINTMENT_SHEET, 1, 1));
        covidAppointmentNewUI.selectFirstQuestion("None of Them");
        covidAppointmentNewUI.noOption(2);
        covidAppointmentNewUI.noOption(3);
        covidAppointmentNewUI.noOption(4);
        covidAppointmentNewUI.noOption(5);
        covidAppointmentNewUI.noOption(6);
        covidAppointmentNewUI.noOption(8);
        covidAppointmentNewUI.noOption(9);
        covidAppointmentNewUI.saveCovidForm();
        covidAppointmentNewUI.errorMsgDisplayed();
        covidAppointmentNewUI.closeCovidForm();
        appointmentPage.openCovidForm();
        covidAppointmentNewUI.patientNameCovidForm(ReadExcelData.readExcelData(FILE_PATH, APPOINTMENT_SHEET, 1, 1));
        covidAppointmentNewUI.selectFirstQuestion("None of Them");
        covidAppointmentNewUI.noOption(2);
        covidAppointmentNewUI.noOption(3);
        covidAppointmentNewUI.noOption(4);
        covidAppointmentNewUI.noOption(5);
        covidAppointmentNewUI.noOption(6);
        covidAppointmentNewUI.noOption(7);
        covidAppointmentNewUI.noOption(9);
        covidAppointmentNewUI.saveCovidForm();
        covidAppointmentNewUI.errorMsgDisplayed();
        covidAppointmentNewUI.closeCovidForm();
        appointmentPage.openCovidForm();
        covidAppointmentNewUI.patientNameCovidForm(ReadExcelData.readExcelData(FILE_PATH, APPOINTMENT_SHEET, 1, 1));
        covidAppointmentNewUI.selectFirstQuestion("None of Them");
        covidAppointmentNewUI.noOption(2);
        covidAppointmentNewUI.noOption(3);
        covidAppointmentNewUI.noOption(4);
        covidAppointmentNewUI.noOption(5);
        covidAppointmentNewUI.noOption(6);
        covidAppointmentNewUI.noOption(7);
        covidAppointmentNewUI.noOption(8);
        covidAppointmentNewUI.saveCovidForm();
        covidAppointmentNewUI.errorMsgDisplayed();
        covidAppointmentNewUI.closeCovidForm();
        Assert.assertTrue(basePage.verification().contains("Appointment"));
    }

    /**
     * Book appointment for a new patient with green covid status
     * redirected to appointment listing page checked same appointment with green status
     * Also open covid form at the appointment listing and checked same option selected
     * clicking on same appointment it will redirect to the patient registration page
     * enter the alternate name,alternate mobile,age and registered the patient it will redirect to patient dashboard
     * close alert modal at patient dashboard and open the covid modal and verify same options selected and close the covid modal
     * now redirect at appointment listing(patient dashboard) and open covid modal and check all the data at the covis modal
     */
    @Test(priority = 2)
    public void bookAppointmentGreenFlag() {
        extentReport.logger.log(LogStatus.PASS, BOOK_APPOINTMENT_GREEN_FLAG);
        doctorDashboard.clickonAppointmentAdd();
        appointmentPage.enterPatientName(ReadExcelData.readExcelData(FILE_PATH, APPOINTMENT_SHEET, 1, 1));
        appointmentPage.enterMobileNumber(ReadExcelData.readExcelData(FILE_PATH, APPOINTMENT_SHEET, 1, 2));
        appointmentPage.enterEmailAddress(ReadExcelData.readExcelData(FILE_PATH, APPOINTMENT_SHEET, 1, 3));
        appointmentPage.selectClinicFromDropdown(ReadExcelData.readExcelData(FILE_PATH, APPOINTMENT_SHEET, 1, 5));
        appointmentPage.appointmentDate(ReadExcelData.readExcelData(FILE_PATH, APPOINTMENT_SHEET, 1, 6));
        appointmentPage.selectTimeSlotFromDropdown(ReadExcelData.readExcelData(FILE_PATH, APPOINTMENT_SHEET, 1, 7));
        appointmentPage.enterTimeDuration(ReadExcelData.readExcelData(FILE_PATH, APPOINTMENT_SHEET, 1, 8));
        appointmentPage.selectDoctorFromDropdown(ReadExcelData.readExcelData(FILE_PATH, APPOINTMENT_SHEET, 1, 9));
        appointmentPage.enterNote(ReadExcelData.readExcelData(FILE_PATH, APPOINTMENT_SHEET, 1, 10));
        appointmentPage.openCovidForm();
        covidAppointmentNewUI.patientNameCovidForm(ReadExcelData.readExcelData(FILE_PATH, APPOINTMENT_SHEET, 1, 1));
        covidAppointmentNewUI.selectFirstQuestion("None of Them");
        covidAppointmentNewUI.noOption(2);
        covidAppointmentNewUI.noOption(3);
        covidAppointmentNewUI.noOption(4);
        covidAppointmentNewUI.noOption(5);
        covidAppointmentNewUI.noOption(6);
        covidAppointmentNewUI.noOption(7);
        covidAppointmentNewUI.noOption(8);
        covidAppointmentNewUI.noOption(9);
        covidAppointmentNewUI.greenFlag();
        covidAppointmentNewUI.saveCovidForm();
        appointmentPage.covidStatusUpdate();
        appointmentPage.patientCovidGreen();
        appointmentPage.clickOnSaveBtn();
        basePage.selectClinicFrmHeader(ReadExcelData.readExcelData(FILE_PATH, APPOINTMENT_SHEET, 1, 5));
        doctorDashboard.clickOnAppListBtn();
        clinicAppointmentsPage.appointmentStatus(ReadExcelData.readExcelData(FILE_PATH, APPOINTMENT_SHEET, 1, 1), "New");
        clinicAppointmentsPage.appointmentType(ReadExcelData.readExcelData(FILE_PATH, APPOINTMENT_SHEET, 1, 1), "Confirmed");
        clinicAppointmentsPage.verifyClinic(ReadExcelData.readExcelData(FILE_PATH, APPOINTMENT_SHEET, 1, 1), ReadExcelData.readExcelData(FILE_PATH, APPOINTMENT_SHEET, 1, 5));
        clinicAppointmentsPage.viewButton(ReadExcelData.readExcelData(FILE_PATH, APPOINTMENT_SHEET, 1, 1));
        clinicAppointmentsPage.editButton(ReadExcelData.readExcelData(FILE_PATH, APPOINTMENT_SHEET, 1, 1));
        clinicAppointmentsPage.deleteButton(ReadExcelData.readExcelData(FILE_PATH, APPOINTMENT_SHEET, 1, 1));
        clinicAppointmentsPage.openGreenFlag(ReadExcelData.readExcelData(FILE_PATH, APPOINTMENT_SHEET, 1, 1));
        covidDeclarationForm.patientNameCovidForm(ReadExcelData.readExcelData(FILE_PATH, APPOINTMENT_SHEET, 1, 1));
        covidDeclarationForm.selectedFirstQuestion("None of Them");
        covidDeclarationForm.noOptionSelected(2);
        covidDeclarationForm.noOptionSelected(3);
        covidDeclarationForm.noOptionSelected(4);
        covidDeclarationForm.noOptionSelected(5);
        covidDeclarationForm.noOptionSelected(6);
        covidDeclarationForm.noOptionSelected(7);
        covidDeclarationForm.noOptionSelected(8);
        covidDeclarationForm.noOptionSelected(9);
        covidDeclarationForm.closeCovidForm();
        clinicAppointmentsPage.clickOnPatient(ReadExcelData.readExcelData(FILE_PATH, APPOINTMENT_SHEET, 1, 1));
        patientRegistrationPage.enterAlterContactName(ReadExcelData.readExcelData(FILE_PATH, APPOINTMENT_SHEET, 1, 1));
        patientRegistrationPage.enterAlternateMobileNO(ReadExcelData.readExcelData(FILE_PATH, APPOINTMENT_SHEET, 1, 2));
        patientRegistrationPage.enterAge("27");
        patientRegistrationPage.clickOnApproveSave();
        basePatientLifeCyclePage.clickOnAlert();
        patientDashboardPage.openCovidForm();
        covidDeclarationForm.patientNameCovidForm(ReadExcelData.readExcelData(FILE_PATH, APPOINTMENT_SHEET, 1, 1));
        covidDeclarationForm.selectedFirstQuestion("None of Them");
        covidDeclarationForm.noOptionSelected(2);
        covidDeclarationForm.noOptionSelected(3);
        covidDeclarationForm.noOptionSelected(4);
        covidDeclarationForm.noOptionSelected(5);
        covidDeclarationForm.noOptionSelected(6);
        covidDeclarationForm.noOptionSelected(7);
        covidDeclarationForm.noOptionSelected(8);
        covidDeclarationForm.noOptionSelected(9);
        covidDeclarationForm.closeCovidForm();
        patientDashboardPage.clickOnAppList();
        appointmentsListPage.openCovidForm();
        covidDeclarationForm.patientNameCovidForm(ReadExcelData.readExcelData(FILE_PATH, APPOINTMENT_SHEET, 1, 1));
        covidDeclarationForm.selectedFirstQuestion("None of Them");
        covidDeclarationForm.noOptionSelected(2);
        covidDeclarationForm.noOptionSelected(3);
        covidDeclarationForm.noOptionSelected(4);
        covidDeclarationForm.noOptionSelected(5);
        covidDeclarationForm.noOptionSelected(6);
        covidDeclarationForm.noOptionSelected(7);
        covidDeclarationForm.noOptionSelected(8);
        covidDeclarationForm.noOptionSelected(9);
        covidDeclarationForm.closeCovidForm();
        Assert.assertTrue(basePage.verification().contains("Appointments List"));
    }

    /**
     * Search same patient at global search and click on the patient it will redirect to patient dashboard
     * now close alert modal at patient dashboard
     * now select at teeth and select two treatment of the diagnosis and save it and verify same at treatment input listing
     * now try to start treatment from treatment plan input listing and treatment plan listing without temperature and oximeter checked alert message in both cases
     * now update the temperature and oximeter and start the treatment from treatment input list and treatment plan listing
     */
    @Test(priority = 3,dependsOnMethods = "bookAppointmentGreenFlag")
    public void treatmentGreenPatient() {
        extentReport.logger.log(LogStatus.PASS, TREATMENT_GREEN_PATIENT);
        basePage.enterMobileNo(ReadExcelData.readExcelData(FILE_PATH, APPOINTMENT_SHEET, 1, 2));
        basePage.clickOnSearchBtn();
        basePage.clickOnPatient(ReadExcelData.readExcelData(FILE_PATH, APPOINTMENT_SHEET, 1, 2), ReadExcelData.readExcelData(FILE_PATH, APPOINTMENT_SHEET, 1, 1));
        basePatientLifeCyclePage.clickOnAlert();
        patientDashboardPage.clickOnTreatmentPlanAddBtn();
        basePatientLifeCyclePage.clickOnAlert();
        oralExamsPage.clickOnTeethImage("Adult", "24");
        treatmentPlansPage.verifySeletecdTeethOnPopup("24");
        treatmentPlansPage.clickOnConsultationXRays();
        treatmentPlansPage.clickOnTreatments(ReadExcelData.readExcelData(FILE_PATH, TREATMENT_SHEET, 1, 1));
        treatmentPlansPage.clickOnTreatments(ReadExcelData.readExcelData(FILE_PATH, TREATMENT_SHEET, 2, 1));
        treatmentPlansPage.saveTreatment();
        basePatientLifeCyclePage.clickOnAlert();
        treatmentPlansPage.checkedInputListDataName();
        treatmentPlansPage.selectTreatmentInputList(ReadExcelData.readExcelData(FILE_PATH, TREATMENT_SHEET, 1, 2));
        treatmentPlansPage.clickOnSaveBtnTreatmentInputList();
        treatmentPlansPage.setCovidUpdateAlert();
        basePatientLifeCyclePage.clickOnDashBoard();
        basePatientLifeCyclePage.clickOnAlert();
        patientDashboardPage.clickOnTreatmentPlanListBtn();
        treatmentPlanListingPage.verifyDatesTreatmentPlanList();
        treatmentPlanListingPage.verifyStartBtn(TODAY_DATE);
        treatmentPlanListingPage.clickOnStartBtn(TODAY_DATE);
        treatmentPlanListingPage.checkedWithoutTreatmentStart();
        treatmentPlanListingPage.selectTreatmentInTreatmentListPage(ReadExcelData.readExcelData(FILE_PATH, TREATMENT_SHEET, 1, 2));
        treatmentPlanListingPage.clickOnStartBtn(TODAY_DATE);
        treatmentPlanListingPage.setCovidUpdateAlert();
        basePatientLifeCyclePage.clickOnDashBoard();
        basePatientLifeCyclePage.clickOnAlert();
        patientDashboardPage.openCovidForm();
        covidDeclarationForm.patientNameCovidForm(ReadExcelData.readExcelData(FILE_PATH, APPOINTMENT_SHEET, 1, 1));
        covidDeclarationForm.selectedFirstQuestion("None of Them");
        covidDeclarationForm.noOptionSelected(2);
        covidDeclarationForm.noOptionSelected(3);
        covidDeclarationForm.noOptionSelected(4);
        covidDeclarationForm.noOptionSelected(5);
        covidDeclarationForm.noOptionSelected(6);
        covidDeclarationForm.noOptionSelected(7);
        covidDeclarationForm.noOptionSelected(8);
        covidDeclarationForm.noOptionSelected(9);
        covidDeclarationForm.patientTemperature();
        covidDeclarationForm.patientOximeter();
        covidDeclarationForm.setPatientTemperature("97");
        covidDeclarationForm.setPatientOximeter("100");
        covidDeclarationForm.saveCovidForm();
        basePatientLifeCyclePage.clickOnAlert();
        patientDashboardPage.openCovidForm();
        covidDeclarationForm.checkPatientTemperature("97");
        covidDeclarationForm.checkPatientOximeter("100");
        covidDeclarationForm.closeCovidForm();
        patientDashboardPage.clickOnTreatmentPlanListBtn();
        treatmentPlanListingPage.selectTreatmentInTreatmentListPage(ReadExcelData.readExcelData(FILE_PATH, TREATMENT_SHEET, 1, 2));
        treatmentPlanListingPage.clickOnStartBtn(TODAY_DATE);
        Assert.assertTrue(basePage.verification().contains("Works Done"));
        loginPage.getDriver().navigate().back();
        treatmentPlanListingPage.clickOnEditBtn(TODAY_DATE);
        treatmentPlansPage.selectTreatmentInputList(ReadExcelData.readExcelData(FILE_PATH, TREATMENT_SHEET, 2, 2));
        basePatientLifeCyclePage.clickOnAlert();
        treatmentPlansPage.clickOnSaveBtnTreatmentInputList();
        Assert.assertTrue(basePage.verification().contains("Works Done"));
    }

    /**
     * verify the treatment details at the work done add page and work done history page
     */
    @Test(priority = 4,dependsOnMethods = "treatmentGreenPatient")
    public void workDoneAddPage() {
        extentReport.logger.log(LogStatus.PASS, WORK_DONE_ADD_PAGE);
        basePage.enterMobileNo(ReadExcelData.readExcelData(FILE_PATH, APPOINTMENT_SHEET, 1, 2));
        basePage.clickOnSearchBtn();
        basePage.clickOnPatient(ReadExcelData.readExcelData(FILE_PATH, APPOINTMENT_SHEET, 1, 2), ReadExcelData.readExcelData(FILE_PATH, APPOINTMENT_SHEET, 1, 1));
        patientDashboardPage.hideDueWarningPopup();
        patientDashboardPage.clickOnWorkDoneAdd();
        worksDonePage.checkedAddAllBtn();
        worksDonePage.checkLabWorkOrderBtn();
        worksDonePage.checkedInvoiceListBtn();
        worksDonePage.checkedPrintBtn();
        worksDonePage.checkedHistoryBtn();
        basePatientLifeCyclePage.dashBoardBtnVerify();
        worksDonePage.checkDateTreatment(ReadExcelData.readExcelData(FILE_PATH, TREATMENT_SHEET, 1, 2), TODAY_DATE);
        worksDonePage.checkSourceNo(ReadExcelData.readExcelData(FILE_PATH, TREATMENT_SHEET, 1, 2), "24");
        worksDonePage.checkedStatusTreatment(ReadExcelData.readExcelData(FILE_PATH, TREATMENT_SHEET, 1, 2), "Started");
        worksDonePage.checkProgressDropDown(ReadExcelData.readExcelData(FILE_PATH, TREATMENT_SHEET, 1, 2));
        worksDonePage.checkSuspededBtn(ReadExcelData.readExcelData(FILE_PATH, TREATMENT_SHEET, 1, 2));
        worksDonePage.checkDataName(ReadExcelData.readExcelData(FILE_PATH, TREATMENT_SHEET, 1, 2));
        worksDonePage.checkBox(ReadExcelData.readExcelData(FILE_PATH, TREATMENT_SHEET, 1, 2));
        worksDonePage.modifiedDate(ReadExcelData.readExcelData(FILE_PATH, TREATMENT_SHEET, 1, 2), TODAY_DATE);
        worksDonePage.checkDoctorSelected(ReadExcelData.readExcelData(FILE_PATH, TREATMENT_SHEET, 1, 2), ReadExcelData.readExcelData(FILE_PATH, APPOINTMENT_SHEET, 1, 9));
        worksDonePage.checkedClinic(ReadExcelData.readExcelData(FILE_PATH, TREATMENT_SHEET, 1, 2), ReadExcelData.readExcelData(FILE_PATH, APPOINTMENT_SHEET, 1, 5));
        worksDonePage.checkTimeSelected(ReadExcelData.readExcelData(FILE_PATH, TREATMENT_SHEET, 1, 2), "Select Time");
        worksDonePage.checkedRemarks(ReadExcelData.readExcelData(FILE_PATH, TREATMENT_SHEET, 1, 2));
        worksDonePage.checkedAddButton(ReadExcelData.readExcelData(FILE_PATH, TREATMENT_SHEET, 1, 2));
        /*-------------work done history check all data-----------------*/
        worksDonePage.clickWorkDoneHistory();
        workDoneHistoryPage.checkedInvoiceListBtn();
        workDoneHistoryPage.checkLabWorkOrderBtn();
        workDoneHistoryPage.checkedPrintBtn();
        basePatientLifeCyclePage.verifyAddNewBtn();
        basePatientLifeCyclePage.dashBoardBtnVerify();
        workDoneHistoryPage.checkDateTreatment(ReadExcelData.readExcelData(FILE_PATH, TREATMENT_SHEET, 1, 2), TODAY_DATE);
        workDoneHistoryPage.checkSourceNo(ReadExcelData.readExcelData(FILE_PATH, TREATMENT_SHEET, 1, 2), "24");
        workDoneHistoryPage.checkedStatusTreatment(ReadExcelData.readExcelData(FILE_PATH, TREATMENT_SHEET, 1, 2), "In-Progress");
        workDoneHistoryPage.checkDataName(ReadExcelData.readExcelData(FILE_PATH, TREATMENT_SHEET, 1, 2));
        workDoneHistoryPage.modifiedDate(ReadExcelData.readExcelData(FILE_PATH, TREATMENT_SHEET, 1, 2), TODAY_DATE);
        workDoneHistoryPage.checkDoctorTreated(ReadExcelData.readExcelData(FILE_PATH, TREATMENT_SHEET, 1, 2), ReadExcelData.readExcelData(FILE_PATH, APPOINTMENT_SHEET, 1, 9));
        workDoneHistoryPage.checkedClinic(ReadExcelData.readExcelData(FILE_PATH, TREATMENT_SHEET, 1, 2), ReadExcelData.readExcelData(FILE_PATH, APPOINTMENT_SHEET, 1, 5));
        workDoneHistoryPage.checkSpentTime(ReadExcelData.readExcelData(FILE_PATH, TREATMENT_SHEET, 1, 2), "15");
        workDoneHistoryPage.checkStageTreatment(ReadExcelData.readExcelData(FILE_PATH, TREATMENT_SHEET, 1, 2), "Started");
        workDoneHistoryPage.checkedRemarks(ReadExcelData.readExcelData(FILE_PATH, TREATMENT_SHEET, 1, 2), "No Remarks");
        workDoneHistoryPage.checkPrintButtonDisplayed();
        Assert.assertTrue(basePage.verification().contains("Work Done History"));
    }

    /**
     * Changing the work done stages and checked All new updated data at work done add page and work done history page
     */
    @Test(priority = 5,dependsOnMethods = "workDoneAddPage")
    public void treatmentStageInProgress() {
        extentReport.logger.log(LogStatus.PASS, TREATMENT_STAGE_INPROGRESS);
        basePage.enterMobileNo(ReadExcelData.readExcelData(FILE_PATH, APPOINTMENT_SHEET, 1, 2));
        basePage.clickOnSearchBtn();
        basePage.clickOnPatient(ReadExcelData.readExcelData(FILE_PATH, APPOINTMENT_SHEET, 1, 2), ReadExcelData.readExcelData(FILE_PATH, APPOINTMENT_SHEET, 1, 1));
        basePatientLifeCyclePage.clickOnAlert();
        patientDashboardPage.clickOnWorkDoneAdd();
        basePatientLifeCyclePage.headerOnAddPage("Works Done");
        worksDonePage.selectDoctor(ReadExcelData.readExcelData(FILE_PATH, TREATMENT_SHEET, 1, 2), ReadExcelData.readExcelData(FILE_PATH, APPOINTMENT_SHEET, 1, 9));
        worksDonePage.selectTime(ReadExcelData.readExcelData(FILE_PATH, TREATMENT_SHEET, 1, 2), "15");
        worksDonePage.enterRemarks(ReadExcelData.readExcelData(FILE_PATH, TREATMENT_SHEET, 1, 2), "it is for testing");
        worksDonePage.selectStages(ReadExcelData.readExcelData(FILE_PATH, TREATMENT_SHEET, 1, 2), "In-Progress");
        worksDonePage.clickOnAdd(ReadExcelData.readExcelData(FILE_PATH, TREATMENT_SHEET, 1, 2));
        worksDonePage.closeAppoitmentPopup();
        worksDonePage.checkedStatusTreatment(ReadExcelData.readExcelData(FILE_PATH, TREATMENT_SHEET, 1, 2), "In-Progress");
        worksDonePage.modifiedDate(ReadExcelData.readExcelData(FILE_PATH, TREATMENT_SHEET, 1, 2), TODAY_DATE);
        worksDonePage.checkDoctorTreated(ReadExcelData.readExcelData(FILE_PATH, TREATMENT_SHEET, 1, 2), ReadExcelData.readExcelData(FILE_PATH, APPOINTMENT_SHEET, 1, 9));
        worksDonePage.checkSpentTime(ReadExcelData.readExcelData(FILE_PATH, TREATMENT_SHEET, 1, 2), "15");
        worksDonePage.checkStatus(ReadExcelData.readExcelData(FILE_PATH, TREATMENT_SHEET, 1, 2), "In-Progress");
        worksDonePage.checkRemarksWorkDoneAddPage(ReadExcelData.readExcelData(FILE_PATH, TREATMENT_SHEET, 1, 2), "it is for testing");
        worksDonePage.checkEdit(ReadExcelData.readExcelData(FILE_PATH, TREATMENT_SHEET, 1, 2));
        /*-------Changed stages of treatment and checked in workdone History---------*/
        worksDonePage.clickWorkDoneHistory();
        workDoneHistoryPage.checkDateTreatment(ReadExcelData.readExcelData(FILE_PATH, TREATMENT_SHEET, 1, 2), TODAY_DATE);
        workDoneHistoryPage.checkSourceNo(ReadExcelData.readExcelData(FILE_PATH, TREATMENT_SHEET, 1, 2), "24");
        workDoneHistoryPage.checkedStatusTreatment(ReadExcelData.readExcelData(FILE_PATH, TREATMENT_SHEET, 1, 2), "In-Progress");
        workDoneHistoryPage.checkDataName(ReadExcelData.readExcelData(FILE_PATH, TREATMENT_SHEET, 1, 2));
        workDoneHistoryPage.modifiedDate(ReadExcelData.readExcelData(FILE_PATH, TREATMENT_SHEET, 1, 2), TODAY_DATE);
        workDoneHistoryPage.checkDoctorTreated(ReadExcelData.readExcelData(FILE_PATH, TREATMENT_SHEET, 1, 2), ReadExcelData.readExcelData(FILE_PATH, APPOINTMENT_SHEET, 1, 9));
        workDoneHistoryPage.checkedClinic(ReadExcelData.readExcelData(FILE_PATH, TREATMENT_SHEET, 1, 2), ReadExcelData.readExcelData(FILE_PATH, APPOINTMENT_SHEET, 1, 5));
        workDoneHistoryPage.checkSpentTime(ReadExcelData.readExcelData(FILE_PATH, TREATMENT_SHEET, 1, 2), "15");
        workDoneHistoryPage.checkStageTreatment(ReadExcelData.readExcelData(FILE_PATH, TREATMENT_SHEET, 1, 2), "In-Progress");
        workDoneHistoryPage.checkedRemarks(ReadExcelData.readExcelData(FILE_PATH, TREATMENT_SHEET, 1, 2), "it is for testing");
        workDoneHistoryPage.checkPrintButtonDisplayed();
        Assert.assertTrue(basePage.verification().contains("Work Done History"));
    }

    /**
     * changing the stages of the treatment to complete and checked all new changes at the work done history and work done add
     */
    @Test(priority = 6,dependsOnMethods = "treatmentStageInProgress")
    public void treatmentStageComplete() {
        extentReport.logger.log(LogStatus.PASS, TREATMENT_STAGE_COMPLETE);
        basePage.enterMobileNo(ReadExcelData.readExcelData(FILE_PATH, APPOINTMENT_SHEET, 1, 2));
        basePage.clickOnSearchBtn();
        basePage.clickOnPatient(ReadExcelData.readExcelData(FILE_PATH, APPOINTMENT_SHEET, 1, 2), ReadExcelData.readExcelData(FILE_PATH, APPOINTMENT_SHEET, 1, 1));
        basePatientLifeCyclePage.clickOnAlert();
        patientDashboardPage.clickOnWorkDoneAdd();
        basePatientLifeCyclePage.headerOnAddPage("Works Done");
        worksDonePage.selectDoctor(ReadExcelData.readExcelData(FILE_PATH, TREATMENT_SHEET, 1, 2), ReadExcelData.readExcelData(FILE_PATH, APPOINTMENT_SHEET, 1, 9));
        worksDonePage.selectTime(ReadExcelData.readExcelData(FILE_PATH, TREATMENT_SHEET, 1, 2), "30");
        worksDonePage.enterRemarks(ReadExcelData.readExcelData(FILE_PATH, TREATMENT_SHEET, 1, 2), "Change to complete");
        worksDonePage.selectStages(ReadExcelData.readExcelData(FILE_PATH, TREATMENT_SHEET, 1, 2), "Completed");
        worksDonePage.clickOnAdd(ReadExcelData.readExcelData(FILE_PATH, TREATMENT_SHEET, 1, 2));
        workDoneHistoryPage.checkDateTreatment(ReadExcelData.readExcelData(FILE_PATH, TREATMENT_SHEET, 1, 2), TODAY_DATE);
        workDoneHistoryPage.checkSourceNo(ReadExcelData.readExcelData(FILE_PATH, TREATMENT_SHEET, 1, 2), "24");
        workDoneHistoryPage.checkedStatusTreatment(ReadExcelData.readExcelData(FILE_PATH, TREATMENT_SHEET, 1, 2), "Completed");
        workDoneHistoryPage.followUpBtn(ReadExcelData.readExcelData(FILE_PATH, TREATMENT_SHEET, 1, 2));
        workDoneHistoryPage.reTreatBtn(ReadExcelData.readExcelData(FILE_PATH, TREATMENT_SHEET, 1, 2));
        workDoneHistoryPage.checkDataName(ReadExcelData.readExcelData(FILE_PATH, TREATMENT_SHEET, 1, 2));
        workDoneHistoryPage.modifiedDate(ReadExcelData.readExcelData(FILE_PATH, TREATMENT_SHEET, 1, 2), TODAY_DATE);
        workDoneHistoryPage.checkDoctorTreated(ReadExcelData.readExcelData(FILE_PATH, TREATMENT_SHEET, 1, 2), ReadExcelData.readExcelData(FILE_PATH, APPOINTMENT_SHEET, 1, 9));
        workDoneHistoryPage.checkedClinic(ReadExcelData.readExcelData(FILE_PATH, TREATMENT_SHEET, 1, 2), ReadExcelData.readExcelData(FILE_PATH, APPOINTMENT_SHEET, 1, 5));
        workDoneHistoryPage.checkSpentTime(ReadExcelData.readExcelData(FILE_PATH, TREATMENT_SHEET, 1, 2), "30");
        workDoneHistoryPage.checkStageTreatment(ReadExcelData.readExcelData(FILE_PATH, TREATMENT_SHEET, 1, 2), "Completed");
        workDoneHistoryPage.checkedRemarks(ReadExcelData.readExcelData(FILE_PATH, TREATMENT_SHEET, 1, 2), "Change to complete");
        workDoneHistoryPage.checkedInvoiceListBtn();
        workDoneHistoryPage.checkLabWorkOrderBtn();
        workDoneHistoryPage.checkedPrintBtn();
        basePatientLifeCyclePage.verifyAddNewBtn();
        basePatientLifeCyclePage.dashBoardBtnVerify();
        Assert.assertTrue(basePage.verification().contains("Work Done History"));
    }

    /**
     * validated all cases of the follow up modal and checked same at work done history and work done add
     */
    @Test(priority = 7,dependsOnMethods = "treatmentStageComplete")
    public void followUp() {
        extentReport.logger.log(LogStatus.PASS, FOLLOW_UP);
        basePage.enterMobileNo(ReadExcelData.readExcelData(FILE_PATH, APPOINTMENT_SHEET, 1, 2));
        basePage.clickOnSearchBtn();
        basePage.clickOnPatient(ReadExcelData.readExcelData(FILE_PATH, APPOINTMENT_SHEET, 1, 2), ReadExcelData.readExcelData(FILE_PATH, APPOINTMENT_SHEET, 1, 1));
        basePatientLifeCyclePage.clickOnAlert();
        patientDashboardPage.clickOnWorkDoneHistory();
        workDoneHistoryPage.checkedStatusTreatment(ReadExcelData.readExcelData(FILE_PATH, TREATMENT_SHEET, 1, 2), "Completed");
        workDoneHistoryPage.clickFollowUpBtn(ReadExcelData.readExcelData(FILE_PATH, TREATMENT_SHEET, 1, 2));
        workDoneHistoryPage.headerFollowUpModal("Follow up");
        workDoneHistoryPage.closeBtnFollowUpModal();
        workDoneHistoryPage.treatmentFollowUpModal(ReadExcelData.readExcelData(FILE_PATH, TREATMENT_SHEET, 1, 2));
        workDoneHistoryPage.sourceFollowUpModal("24");
        workDoneHistoryPage.clinicNameFollowUpModal(ReadExcelData.readExcelData(FILE_PATH, APPOINTMENT_SHEET, 1, 5));
        workDoneHistoryPage.saveFollowUpDetails();
        workDoneHistoryPage.errorMessageDisplayed();
        workDoneHistoryPage.notesFollowUp("follow up testing");
        workDoneHistoryPage.saveFollowUpDetails();
        workDoneHistoryPage.closeBtnFollowUpModal();
        workDoneHistoryPage.selectSpentTime("15");
        workDoneHistoryPage.selectTreatingDentistFollowUpModal(ReadExcelData.readExcelData(FILE_PATH, APPOINTMENT_SHEET, 1, 9));
        workDoneHistoryPage.saveFollowUpDetails();
        workDoneHistoryPage.checkDateTreatment(ReadExcelData.readExcelData(FILE_PATH, TREATMENT_SHEET, 1, 2), TODAY_DATE);
        workDoneHistoryPage.checkSourceNo(ReadExcelData.readExcelData(FILE_PATH, TREATMENT_SHEET, 1, 2), "24");
        workDoneHistoryPage.checkedStatusTreatment(ReadExcelData.readExcelData(FILE_PATH, TREATMENT_SHEET, 1, 2), "Completed");
        workDoneHistoryPage.followUpBtn(ReadExcelData.readExcelData(FILE_PATH, TREATMENT_SHEET, 1, 2));
        workDoneHistoryPage.reTreatBtn(ReadExcelData.readExcelData(FILE_PATH, TREATMENT_SHEET, 1, 2));
        workDoneHistoryPage.checkDataName(ReadExcelData.readExcelData(FILE_PATH, TREATMENT_SHEET, 1, 2));
        workDoneHistoryPage.modifiedDate(ReadExcelData.readExcelData(FILE_PATH, TREATMENT_SHEET, 1, 2), TODAY_DATE);
        workDoneHistoryPage.checkDoctorTreated(ReadExcelData.readExcelData(FILE_PATH, TREATMENT_SHEET, 1, 2), ReadExcelData.readExcelData(FILE_PATH, APPOINTMENT_SHEET, 1, 9));
        workDoneHistoryPage.checkedClinic(ReadExcelData.readExcelData(FILE_PATH, TREATMENT_SHEET, 1, 2), ReadExcelData.readExcelData(FILE_PATH, APPOINTMENT_SHEET, 1, 5));
        workDoneHistoryPage.checkSpentTime(ReadExcelData.readExcelData(FILE_PATH, TREATMENT_SHEET, 1, 2), "15");
        workDoneHistoryPage.checkStageTreatment(ReadExcelData.readExcelData(FILE_PATH, TREATMENT_SHEET, 1, 2), "Follow Up");
        workDoneHistoryPage.checkedRemarks(ReadExcelData.readExcelData(FILE_PATH, TREATMENT_SHEET, 1, 2), "follow up testing");
        workDoneHistoryPage.checkedInvoiceListBtn();
        workDoneHistoryPage.checkLabWorkOrderBtn();
        workDoneHistoryPage.checkedPrintBtn();
        basePatientLifeCyclePage.verifyAddNewBtn();
        basePatientLifeCyclePage.dashBoardBtnVerify();
        Assert.assertTrue(basePage.verification().contains("Work Done History"));
    }

    /**
     * Now converting the covid status from green to red
     * Adding new treatment for red covid patient
     * checking checkbox should not be present in treatment input listing
     * and also checking start should not be present at the treatment plan listing
     */
    @Test(priority = 8,dependsOnMethods = "bookAppointmentGreenFlag")
    public void convertingGreenToRedFlag() {
        extentReport.logger.log(LogStatus.PASS, CONVERTING_GREEN_TO_RED_FLAG);
        basePage.enterMobileNo(ReadExcelData.readExcelData(FILE_PATH, APPOINTMENT_SHEET, 1, 2));
        basePage.clickOnSearchBtn();
        basePage.clickOnPatient(ReadExcelData.readExcelData(FILE_PATH, APPOINTMENT_SHEET, 1, 2), ReadExcelData.readExcelData(FILE_PATH, APPOINTMENT_SHEET, 1, 1));
        basePatientLifeCyclePage.clickOnAlert();
        patientDashboardPage.openCovidForm();
        covidDeclarationForm.patientNameCovidForm(ReadExcelData.readExcelData(FILE_PATH, APPOINTMENT_SHEET, 1, 1));
        covidDeclarationForm.selectedFirstQuestion("None of Them");
        covidDeclarationForm.noOptionSelected(2);
        covidDeclarationForm.noOptionSelected(3);
        covidDeclarationForm.noOptionSelected(4);
        covidDeclarationForm.noOptionSelected(5);
        covidDeclarationForm.noOptionSelected(6);
        covidDeclarationForm.noOptionSelected(7);
        covidDeclarationForm.noOptionSelected(8);
        covidDeclarationForm.noOptionSelected(9);
        covidDeclarationForm.checkPatientTemperature("97");
        covidDeclarationForm.checkPatientOximeter("100");
        covidDeclarationForm.setPatientTemperature("100");
        covidDeclarationForm.setPatientOximeter("90");
        covidDeclarationForm.saveCovidForm();
        basePatientLifeCyclePage.clickOnAlert();
        patientDashboardPage.checkPatientRedFlag();
        patientDashboardPage.clickOnTreatmentPlanAddBtn();
        basePatientLifeCyclePage.clickOnAlert();
        oralExamsPage.clickOnTeethImage("Adult", "24");
        treatmentPlansPage.verifySeletecdTeethOnPopup("24");
        treatmentPlansPage.clickOnConsultationXRays();
        treatmentPlansPage.clickOnTreatments(ReadExcelData.readExcelData(FILE_PATH, TREATMENT_SHEET, 1, 1));
        treatmentPlansPage.saveTreatment();
        basePatientLifeCyclePage.clickOnAlert();
        treatmentPlansPage.checkedInputListDataName();
        treatmentPlansPage.startCheckBoxInputListNotPresent();
        treatmentPlansPage.clickOnSaveBtnTreatmentInputList();
        treatmentPlanListingPage.checkStartBtnNotPresent(TODAY_DATE);
        basePatientLifeCyclePage.clickOnDashBoard();
        basePatientLifeCyclePage.clickOnAlert();
        patientDashboardPage.clickOnAppAdd();
        appointmentPage.selectDoctorFromDropdown(ReadExcelData.readExcelData(FILE_PATH, APPOINTMENT_SHEET, 1, 9));
        appointmentPage.patientCovidRed();
        appointmentPage.clickOnSaveBtn();
        appointmentPage.covidAlertMsgRed();
        Assert.assertTrue(basePage.verification().contains("Add Appointment"));
    }

    /**
     * validated retreat for the treatment scenario at the both page work done history and work done add page
     */
    @Test(priority = 9,dependsOnMethods = "treatmentStageComplete")
    public void reTreatRedCovidPatient() {
        extentReport.logger.log(LogStatus.PASS, RE_TREAT);
        basePage.enterMobileNo(ReadExcelData.readExcelData(FILE_PATH, APPOINTMENT_SHEET, 1, 2));
        basePage.clickOnSearchBtn();
        basePage.clickOnPatient(ReadExcelData.readExcelData(FILE_PATH, APPOINTMENT_SHEET, 1, 2), ReadExcelData.readExcelData(FILE_PATH, APPOINTMENT_SHEET, 1, 1));
        basePatientLifeCyclePage.clickOnAlert();
        patientDashboardPage.clickOnWorkDoneHistory();
        workDoneHistoryPage.followUpBtnRedCovidFlag(ReadExcelData.readExcelData(FILE_PATH, TREATMENT_SHEET, 1, 2));
        workDoneHistoryPage.clickReTreatBtn(ReadExcelData.readExcelData(FILE_PATH, TREATMENT_SHEET, 1, 2));
        workDoneHistoryPage.headerReTreatModal("Are you sure you want to re-treat this treatment?");
        workDoneHistoryPage.closeBtnRetreatBtn();
        workDoneHistoryPage.selectTreatingDentistReTreatModal(ReadExcelData.readExcelData(FILE_PATH, APPOINTMENT_SHEET, 1, 9));
        workDoneHistoryPage.stageDropDownFirstTime();
        workDoneHistoryPage.saveRetreatDetails();
        workDoneHistoryPage.errorMessage("Select Reason");
        workDoneHistoryPage.selectReason("Others");
        workDoneHistoryPage.saveRetreatDetails();
        workDoneHistoryPage.errorMessage("Enter Notes");
        workDoneHistoryPage.notesRetreat("re-treat testing");
        workDoneHistoryPage.selectStage("Select Stage");
        workDoneHistoryPage.saveRetreatDetails();
        workDoneHistoryPage.errorMessage("Select Stage");
        workDoneHistoryPage.selectStage("In-Progress");
        workDoneHistoryPage.saveRetreatDetails();
        worksDonePage.checkedStatusTreatment(ReadExcelData.readExcelData(FILE_PATH, TREATMENT_SHEET, 1, 2), "In-Progress");
        worksDonePage.modifiedDate(ReadExcelData.readExcelData(FILE_PATH, TREATMENT_SHEET, 1, 2), TODAY_DATE);
        worksDonePage.checkDoctorTreated(ReadExcelData.readExcelData(FILE_PATH, TREATMENT_SHEET, 1, 2), ReadExcelData.readExcelData(FILE_PATH, APPOINTMENT_SHEET, 1, 9));
        worksDonePage.checkSpentTime(ReadExcelData.readExcelData(FILE_PATH, TREATMENT_SHEET, 1, 2), "0");
        worksDonePage.checkStatus(ReadExcelData.readExcelData(FILE_PATH, TREATMENT_SHEET, 1, 2), "In-Progress: Re-Treat");
        worksDonePage.checkRemarksWorkDoneAddPage(ReadExcelData.readExcelData(FILE_PATH, TREATMENT_SHEET, 1, 2), "re-treat testing");
        worksDonePage.workDoneAddBtnRedCovidPatient(ReadExcelData.readExcelData(FILE_PATH, TREATMENT_SHEET, 1, 2));
        Assert.assertTrue(basePage.verification().contains("Works Done"));
    }

    /**
     * Redirecting at Appointment add page
     * selecting same patient from the suggestion
     * validated appointment should not be book for red covid flag patient
     */
    @Test(priority = 10,dependsOnMethods = "bookAppointmentGreenFlag")
    public void appointmentRedCovidPatient() {
        extentReport.logger.log(LogStatus.PASS, APPOINTMENT_RED_COVID_PATIENT);
        doctorDashboard.clickonAppointmentAdd();
        appointmentPage.enterPatientName(ReadExcelData.readExcelData(FILE_PATH, APPOINTMENT_SHEET, 1, 1));
        appointmentPage.selectPatientSuggestion(ReadExcelData.readExcelData(FILE_PATH, APPOINTMENT_SHEET, 1, 1));
        appointmentPage.patientCovidRed();
        appointmentPage.selectDoctorFromDropdown(ReadExcelData.readExcelData(FILE_PATH, APPOINTMENT_SHEET, 1, 9));
        appointmentPage.clickOnSaveBtn();
        appointmentPage.covidAlertMsgRed();
        Assert.assertTrue(basePage.verification().contains("Appointment"));
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

