package com.prm.scripts;

import com.prm.docdashboard.pageobject.*;
import com.prm.pageobjects.utils.ExtentReport;
import com.prm.pageobjects.utils.ReadConfig;
import com.prm.pageobjects.utils.ReadExcelData;
import com.prm.pageobjects.utils.TestData;
import com.prm.patientdashboard.pageobject.AppointmentsListPage;
import com.prm.patientdashboard.pageobject.*;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

/**
 * @Author:-Ajit Yadav
 * email:-ajit.yadav@instantsys.com
 * Date:-15-05-20
 */
public class InvoiceListingTestCase {
    private LoginPage loginPage = new LoginPage();
    private DoctorDashBoard doctorDashboard = new DoctorDashBoard(loginPage);
    private BasePage basePage = new BasePage(loginPage);
    private ExtentReport extentReport = new ExtentReport(loginPage, "InvoiceListingTestCase");
    private PatientDashboardPage patientDashboardPage = new PatientDashboardPage(loginPage);
    private BasePatientLifeCyclePage basePatientLifeCyclePage = new BasePatientLifeCyclePage(loginPage);
    private TreatmentPlansPage treatmentPlansPage = new TreatmentPlansPage(loginPage);
    private TreatmentPlanListingPage treatmentPlanListingPage = new TreatmentPlanListingPage(loginPage);
    private OralExamsPage OralExamsPage = new OralExamsPage(loginPage);
    private WorksDonePage worksDonePage = new WorksDonePage(loginPage);
    private InvoiceListingPage invoiceListingPage = new InvoiceListingPage(loginPage,extentReport);
    private AppointmentAddPage appointmentPage = new AppointmentAddPage(loginPage);
    private AppointmentsListPage appointmentsListPage = new AppointmentsListPage(loginPage);
    private NewReceiptPage newReceiptPage = new NewReceiptPage(loginPage);

    /**
     * update Details of new patient to execute all test cases of invoice listing
     */
    private static final String FILE_PATH = TestData.getInstance().getInputData("invoice_file_path");
    private static final String SHEET = TestData.getInstance().getInputData("invoices_file_sheet_name");
    private static final String INVOICE_CREATED_DATE =TestData.getInstance().getInputData("invoice_created_date");
    private static final String PATIENT_NAME=TestData.getInstance().getInputData("invoice_patient_name");
    private static final String MOBILE_NUMBER = TestData.getInstance().getInputData("invoice_patient_mobile");
    private static final String DOCTOR_NAME = TestData.getInstance().getInputData("invoice_doctor_name");
    private static final String DOCTOR_NIK_NAME = TestData.getInstance().getInputData("invoice_doctor_nikName");
    private static final String CLINIC_NAME = TestData.getInstance().getInputData("invoice_clinic_name");
    /**
     * test cases message which will print on the extent report after test cases execution complete
     */
    private static final String SCRIPTS_STARTED_MSG = "@@ Test scripts has been started here @@";
    private static final String STATUS_OF_SCRIPTS = "@@ Test script has been completed @@";
    private static final String INVOICE_LIST_WITHOUT_INVOICES = "Validating invoice listing page without any invoice for the a patient - #InvoiceListWithoutInvoices";
    private static final String CREATE_INVOICE = "Validating the invoice listing page after creating a invoice - #createInvoice";
    private static final String DISCOUNT_COUPON_APPLIED = "Validating the data at view modal for a particular invoice - #discountCouponApplied";
    private static final String DELETE_TREATMENT_INVOICE_DELETE = "Validating the New Receipt page - #deleteTreatmentInvoiceDelete";
    private static final String INVOICE_LISTING_VIEW_MODAL = "Validating discount popup of the invoice - #invoiceListingViewModal";


    @BeforeClass
    public void setup() {
        extentReport.logger = extentReport.report.startTest(this.getClass().getSimpleName());
        extentReport.logger.log(LogStatus.INFO, LoginPage.testCaseRunningStatusmsg);
        extentReport.logger.log(LogStatus.PASS, LoginPage.browserMsg);
        String username = ReadConfig.getInstance().getUsername();
        String password = ReadConfig.getInstance().getPassword();
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

    /**
     * checked side navigation all module button
     * checked header of the invoice listing page
     * checked patient name and patient id at invoice listing
     * checked receipt button
     * checked patient Dash board button
     * checked treatment status drop down its options and its default selection
     * checked invoices status drop down its options and its default selection
     * checked date filter
     * checked show details button
     * checked search button and reset button
     * checked for first time total due amount and total advance amount
     * checked collect advance payment button
     * checked No Record Found message!
     */
    @Test(priority = 1)
    public void InvoiceListWithoutInvoices() {
        extentReport.logger.log(LogStatus.PASS, INVOICE_LIST_WITHOUT_INVOICES);
        patientDashboardPage.clickOnInvoiceList();
        basePatientLifeCyclePage.openCloseLeftNavigator();
        basePatientLifeCyclePage.webElementOfLeftNavigator();
        basePatientLifeCyclePage.openCloseLeftNavigator();
        invoiceListingPage.headerPage("Invoice Listing");
        invoiceListingPage.verifyUserName(PATIENT_NAME);
        invoiceListingPage.checkReceiptBtn();
        basePatientLifeCyclePage.dashBoardBtnVerify();
        invoiceListingPage.treatmentStatusDropDown("Select Treatment Status");
        invoiceListingPage.invoiceStatusDropDown("Select Invoice Status");
        invoiceListingPage.showDetailsCheckBox();
        invoiceListingPage.checkSearchBtn();
        invoiceListingPage.checkResetBtn();
        invoiceListingPage.amountSection(0,0);
        invoiceListingPage.checkCollectAdvacePaymentBtn();
        invoiceListingPage.noRecordFound();
        Assert.assertTrue(basePage.verification().contains("Invoice Listing"));
    }

    /**
     * creating a treatment for the patient
     * checked Appointment not available withing last 24 hr then booking an appointment
     * start the treatment from the treatment plan listing
     * Invoice created and checked the invoices listing
     */
    @Test(priority = 2)
    public void createInvoice() {
        extentReport.logger.log(LogStatus.PASS, CREATE_INVOICE);
        patientDashboardPage.clickOnTreatmentPlanAddBtn();
        basePatientLifeCyclePage.clickOnAlert();
        OralExamsPage.clickOnTeethImage("Adult", "24");
        treatmentPlansPage.verifySeletecdTeethOnPopup("24");
        treatmentPlansPage.clickOnConsultationXRays();
        treatmentPlansPage.clickOnTreatments(ReadExcelData.readExcelData(FILE_PATH, SHEET, 1, 1));
        treatmentPlansPage.clickOnTreatments(ReadExcelData.readExcelData(FILE_PATH, SHEET, 2, 1));
        treatmentPlansPage.saveTreatment();
        treatmentPlansPage.startCheckBoxInputListNotPresent();
        treatmentPlansPage.clickOnSaveBtnTreatmentInputList();
        treatmentPlanListingPage.checkStartBtnNotPresent(INVOICE_CREATED_DATE);
        basePatientLifeCyclePage.clickOnDashBoard();
        basePatientLifeCyclePage.clickOnAlert();
        patientDashboardPage.clickOnAppList();
        appointmentsListPage.appointmentAvailable();
        basePatientLifeCyclePage.clickOnDashBoard();
        basePatientLifeCyclePage.clickOnAlert();
        patientDashboardPage.clickOnAppAdd();
        appointmentPage.selectDoctorFromDropdown(DOCTOR_NAME);
        appointmentPage.clickOnSaveBtn();
        basePatientLifeCyclePage.clickOnAlert();
        patientDashboardPage.clickOnTreatmentPlanListBtn();
        treatmentPlanListingPage.verifyStartBtn(INVOICE_CREATED_DATE);
        treatmentPlanListingPage.selectTreatmentInTreatmentListPage(ReadExcelData.readExcelData(FILE_PATH, SHEET, 1, 2));
        treatmentPlanListingPage.selectTreatmentInTreatmentListPage(ReadExcelData.readExcelData(FILE_PATH, SHEET, 2, 2));
        treatmentPlanListingPage.clickOnStartBtn(INVOICE_CREATED_DATE);
        worksDonePage.clickInvoiceListBtn();
        invoiceListingPage.invoiceListTableColumnName();
        invoiceListingPage.invoiceDataTable();
        invoiceListingPage.actionsButton_InvoicesCreatedDate();
        Assert.assertTrue(basePage.verification().contains("Invoice Listing"));
    }

    /**
     * open coupon modal
     * checked message if we try to save the coupon modal without any changes
     * checked patient name at coupon modal
     * checked header of the coupon modal
     * checked invoice number.invoice created date
     * checked total due amount at coupon modal
     * checked column name at coupon modal for the treatment
     * Applied coupon in percentage and also validated the effective price
     * validated coupon adjustment notes text fields and enter notes
     * Again reopen the coupon modal and applied in amount
     * checked effective prize at coupon amount
     * close the coupon modal
     * same treatment checking at treatment listing coupon applied
     * also same treatment with applied coupon checked view modal at treatment plan listing
     */
    @Test(priority = 3)
    public void discountCouponApplied(){
        extentReport.logger.log(LogStatus.PASS, DISCOUNT_COUPON_APPLIED);
        patientDashboardPage.clickOnInvoiceList();
        invoiceListingPage.openCouponModal(INVOICE_CREATED_DATE);
        invoiceListingPage.saveModalDetails();
        invoiceListingPage.withoutChangeCouponMsg();
        invoiceListingPage.checkPatientNameCouponModal(PATIENT_NAME);
        invoiceListingPage.headerCouponPopup();
        invoiceListingPage.invoiceNumberInvoiceDate(INVOICE_CREATED_DATE);
        invoiceListingPage.couponAdjustmentNotes("coupon notes");
        invoiceListingPage.saveModalDetails();
        invoiceListingPage.updateCouponValueCouponBasAddCouponAdjustmentNotes();
        invoiceListingPage.closeModal();
        invoiceListingPage.openCouponModal(INVOICE_CREATED_DATE);
        invoiceListingPage.totalDueCouponModal();
        invoiceListingPage.treatmentTable("%",20);
        invoiceListingPage.couponAdjustmentNotes("coupon notes");
        invoiceListingPage.saveModalDetails();
        invoiceListingPage.openCouponModal(INVOICE_CREATED_DATE);
        invoiceListingPage.treatmentTableAfterCouponApplied("%",20);
        invoiceListingPage.treatmentTable("Amt",20);
        invoiceListingPage.saveModalDetails();
        invoiceListingPage.openCouponModal(INVOICE_CREATED_DATE);
        invoiceListingPage.treatmentTableAfterCouponApplied("Amt",20);
        invoiceListingPage.closeModal();
        basePatientLifeCyclePage.clickOnDashBoard();
        basePatientLifeCyclePage.clickOnAlert();
        patientDashboardPage.clickOnTreatmentPlanListBtn();
        treatmentPlanListingPage.verifyDatesTreatmentPlanList();
        treatmentPlanListingPage.verifyCouponImpactInMainList(ReadExcelData.readExcelData(FILE_PATH, SHEET, 1, 2));
        treatmentPlanListingPage.verifyCouponImpactInMainList(ReadExcelData.readExcelData(FILE_PATH, SHEET, 2, 2));
        treatmentPlanListingPage.clickOnViewBtn(INVOICE_CREATED_DATE);
        treatmentPlanListingPage.couponDiscountInViewPopup(ReadExcelData.readExcelData(FILE_PATH, SHEET, 1, 2));
        treatmentPlanListingPage.verifyCouponImpactInMainList(ReadExcelData.readExcelData(FILE_PATH, SHEET, 2, 2));
        treatmentPlanListingPage.clickOnCloseBtnViewPopup();
        Assert.assertTrue(basePage.verification().contains("Treatment Plan Listing"));
    }

    /**
     * open the delete modal
     * validated the patient name at delete modal
     * checked invoice number and invoice created
     * checked total due amount
     * validated delete reason drop down and it options
     * validated msg if user try to delete the treatment without selecting any treatment and reason
     * validated message if user selected reason but not selected any treatment
     * selected a treatment and deleted
     * after deleted and coupon applied the invoice checked invoice listing
     * again open the delete modal and deleted the invoice
     * again checked the same invoice at the invoice listing after deleted the invoice
     * by selecting the show details modal search for deleted invoices
     * checked all coupon adjusted, treatment deleted and both treatment deleted and coupon adjusted
     * checked both treatment deleted  at the treatment listing and treatment plan listing view modal
     */
    @Test(priority = 4)
    public void deleteTreatmentInvoiceDelete(){
        extentReport.logger.log(LogStatus.PASS, DELETE_TREATMENT_INVOICE_DELETE);
        patientDashboardPage.clickOnInvoiceList();
        invoiceListingPage.openDeleteModal(INVOICE_CREATED_DATE);
        invoiceListingPage.checkPatientNameDeleteModal(PATIENT_NAME);
        invoiceListingPage.checkHeaderDeleteModal();
        invoiceListingPage.invoiceNumberInvoiceDate(INVOICE_CREATED_DATE);
        invoiceListingPage.totalDueDeleteModal();
        invoiceListingPage.invoiceDeleteReason("Select Reasons");
        invoiceListingPage.saveModalDetails();
        invoiceListingPage.errorNoReasonSelected();
        invoiceListingPage.selectDeleteReason("Incorrect treatment plan");
        invoiceListingPage.saveModalDetails();
        invoiceListingPage.errorMsgTreatmentNotSelected();
        invoiceListingPage.selectTreatment(ReadExcelData.readExcelData(FILE_PATH, SHEET, 1, 2));
        invoiceListingPage.saveModalDetails();
        invoiceListingPage.correctionNotes(INVOICE_CREATED_DATE,"Both trt deleted and Cpn. value adjusted");
        invoiceListingPage.openDeleteModal(INVOICE_CREATED_DATE);
        invoiceListingPage.selectDeleteReason("Incorrect treatment plan");
        invoiceListingPage.selectTreatment("all");
        invoiceListingPage.saveModalDetails();
        invoiceListingPage.selectShowDetails();
        invoiceListingPage.clickOnSearch();
        invoiceListingPage.invoiceDeleted(INVOICE_CREATED_DATE);
        invoiceListingPage.negativeInvoiceAfterDelete(INVOICE_CREATED_DATE);
        basePatientLifeCyclePage.clickOnDashBoard();
        basePatientLifeCyclePage.clickOnAlert();
        patientDashboardPage.clickOnTreatmentPlanListBtn();
        treatmentPlanListingPage.verifyDatesTreatmentPlanList();
        treatmentPlanListingPage.verifyAfterDeleteInTreatmentList(ReadExcelData.readExcelData(FILE_PATH, SHEET, 1, 2));
        treatmentPlanListingPage.verifyAfterDeleteInTreatmentList(ReadExcelData.readExcelData(FILE_PATH, SHEET, 2, 2));
        treatmentPlanListingPage.clickOnViewBtn(INVOICE_CREATED_DATE);
        treatmentPlanListingPage.verifyAfterDeleteInTreatmentListViewPopup(ReadExcelData.readExcelData(FILE_PATH, SHEET, 1, 2));
        treatmentPlanListingPage.verifyAfterDeleteInTreatmentListViewPopup(ReadExcelData.readExcelData(FILE_PATH, SHEET, 1, 2));
        treatmentPlanListingPage.clickOnCloseBtnViewPopup();
        Assert.assertTrue(basePage.verification().contains("Treatment Plan Listing"));
    }
    /**
     * checked invoices column name
     * checked for each invoice created date,invoices status,invoices number,treatment status,net amount,coupon amount,amount due,amount paid,doctor,correction notes
     * checking for all invoices together net amount,coupon amount,amount due,amount paid
     * checked actions button coupon button,view button,delete button,receipt button and print button on the basis of the invoices created date
     * selected a random invoice and open view modal
     * checked header,patient name ,patient Id and save and close button.
     * checked all treatment for that particular invoice
     * if treatment is deleted from the invoices then checked correction deletion entry for the treatment at view modal in correction detail.
     * if coupon adjusted then checked correction coupon adjustment entry at view modal
     * checked notes section for the both cases either with note or without note
     * after executed the view modal scenario it will close the popup
     */
    @Test(priority = 5)
    public void invoiceListingViewModal(){
        extentReport.logger.log(LogStatus.PASS, INVOICE_LISTING_VIEW_MODAL);
        patientDashboardPage.clickOnInvoiceList();
        invoiceListingPage.invoiceListTableColumnName();
        invoiceListingPage.invoiceDataTable();
        invoiceListingPage.netAmountAllInvoices();
        invoiceListingPage.couponAmountAllInvoices();
        invoiceListingPage.amountDueAllInvoices();
        invoiceListingPage.amountPaidAllInvoices();
        invoiceListingPage.actionsButton_InvoicesCreatedDate();
        invoiceListingPage.checkCollectPaymentBtn();
        invoiceListingPage.openViewModal();
        invoiceListingPage.patientNamePatientId(PATIENT_NAME);
        invoiceListingPage.totalDueViewModal();
        invoiceListingPage.columnNameInvoiceViewModal();
        invoiceListingPage.itemDetails_CorrectionDetails();
        invoiceListingPage.noteBehaviorViewModal();
        invoiceListingPage.closeViewModal();
        Assert.assertTrue(basePage.verification().contains("Invoice Listing"));
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
