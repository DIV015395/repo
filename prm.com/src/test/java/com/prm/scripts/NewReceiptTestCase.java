package com.prm.scripts;

import com.prm.docdashboard.pageobject.*;
import com.prm.pageobjects.utils.ReadExcelData;
import com.prm.patientdashboard.pageobject.*;
import com.prm.patientdashboard.pageobject.AppointmentsListPage;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.prm.pageobjects.utils.ExtentReport;
import com.prm.pageobjects.utils.ReadConfig;
import com.prm.pageobjects.utils.TestData;
import com.relevantcodes.extentreports.LogStatus;

public class NewReceiptTestCase {

	private LoginPage loginPage = new LoginPage();
	private DoctorDashBoard doctorDashBoard = new DoctorDashBoard(loginPage);
	private BasePage basePage = new BasePage(loginPage);
	private ExtentReport extentReport = new ExtentReport(loginPage, "LabWorkOrderTestCase");
	private PatientDashboardPage patientDashboardPage = new PatientDashboardPage(loginPage);
	private BasePatientLifeCyclePage basePatientLifeCyclePage = new BasePatientLifeCyclePage(loginPage);
	private NewReceiptPage newReceiptPage =new NewReceiptPage(loginPage);
	private ReceiptListingPage receiptListingPage =new ReceiptListingPage(loginPage,extentReport);
	private OralExamsPage oralExamsPage=new OralExamsPage(loginPage);
	private TreatmentPlansPage treatmentPlansPage=new TreatmentPlansPage(loginPage);
	private TreatmentPlanListingPage treatmentPlanListingPage=new TreatmentPlanListingPage(loginPage);
	private AppointmentsListPage appointmentsListPage=new AppointmentsListPage(loginPage);
	private AppointmentAddPage appointmentPage=new AppointmentAddPage(loginPage);
	private WorksDonePage worksDonePage=new WorksDonePage(loginPage);
	private InvoiceListingPage invoiceListingPage=new InvoiceListingPage(loginPage,extentReport);

	/*
	 * Storing mandatory input required to run LWO test cases,make sure you
	 * update the mandatory input before running the test cases
	 *
	 */
	private static final String FILE_PATH = TestData.getInstance().getInputData("receipt_file_path");
	private static final String SHEET = TestData.getInstance().getInputData("receipt_file_sheet_name");
	private static final String PATIENT_NAME= TestData.getInstance().getInputData("receipt_patient_name");
	private static final String MOBILE_NUMBER = TestData.getInstance().getInputData("receipt_patient_mobile");
	private static final String DOCTOR_NAME = TestData.getInstance().getInputData("receipt_doctor_name");
	private static final String DOCTOR_NIK_NAME = TestData.getInstance().getInputData("receipt_doctor_nikName");
	private static final String CLINIC_NAME = TestData.getInstance().getInputData("receipt_clinic_name");
	private static final String INVOICE_CREATED_DATE=TestData.getInstance().getTodayDate();

	/*
	 * Messages for every test cases which will be print on the extent report
	 */
	private static final String SCRIPTS_STARTED_MSG  = "@@ Test scripts has been started here @@";
	private static final String STATUS_OF_SCRIPTS  = "@@ Test script has been completed @@";

	/*
	 * loginPage with username and password
	 * search for patients from the doctor dashboard
	 * verified patients will redirect at the patient dashboard
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
		doctorDashBoard.doctorDashboardHomePage();
		basePage.selectClinicFrmHeader(CLINIC_NAME);
		basePage.enterMobileNo(MOBILE_NUMBER);
		basePage.clickOnSearchBtn();
		basePage.clickOnPatient(MOBILE_NUMBER, PATIENT_NAME);
		Assert.assertTrue(basePage.verification().contains("Patient Dashboard"));
	}

	@BeforeMethod
	public void verifyPatientDashBoardHomePage() {
		extentReport.logger.log(LogStatus.PASS, SCRIPTS_STARTED_MSG );
		if(patientDashboardPage.dueWarningPopup.size()>0){
			patientDashboardPage.hideDueWarningPopup();
		}else {
			basePatientLifeCyclePage.clickOnAlert();
		}
	}

	@Test(enabled= true, priority = 1)
	public void checkedAdvanceReceiptAndListUI() {
		extentReport.logger.log(LogStatus.PASS, SCRIPTS_STARTED_MSG );
		patientDashboardPage.clickOnInvoiceList();
		basePatientLifeCyclePage.headerOnAddPage("Invoice Listing");
		invoiceListingPage.collectAdvancePayment();
		newReceiptPage.headerOfPage("New Receipts (Advance)");
		basePatientLifeCyclePage.verifyPatientName(PATIENT_NAME);
		newReceiptPage.checkActionBtnForAdvance();
		newReceiptPage.checkModesAndTextFields();
		newReceiptPage.checkCollectAdvanceAndAddPaymentBtn();
		newReceiptPage.checkAndSelectAllModes("Cash","10", "NA","NA");
		newReceiptPage.checkCash();
		newReceiptPage.checkAndSelectAllModes("Card","10", "NA","NA");
		newReceiptPage.checkSubTypeDropDown("Card");
		newReceiptPage.selectSubType("Bajaj Finance");
		newReceiptPage.checkTxnIdOfSubType("Bajaj Finance");
		newReceiptPage.selectSubType("EMI");
		newReceiptPage.checkTxnIdOfSubType("EMI");
		newReceiptPage.checkCheque();
		newReceiptPage.checkNetBanking();
		newReceiptPage.checkAndSelectAllModes("Wallet", "10", "NA", "NA");
		newReceiptPage.checkSubTypeDropDown("Wallet");
		newReceiptPage.selectSubType("UPI");
		newReceiptPage.clickDashboardBtn();
		basePatientLifeCyclePage.clickOnAlert();
		patientDashboardPage.clickOnReceiptList();
		receiptListingPage.headerOnListPage("Receipt Listing");
		basePatientLifeCyclePage.openCloseLeftNavigator();
		basePatientLifeCyclePage.webElementOfLeftNavigator();
		basePatientLifeCyclePage.openCloseLeftNavigator();
		receiptListingPage.verifyPatientName(PATIENT_NAME);
		basePatientLifeCyclePage.dashBoardBtnVerify();
		receiptListingPage.noRecordMsgDisplayed();
		receiptListingPage.checkData();
		Assert.assertTrue(basePage.verification().contains("Receipt Listing"));
	}

	@Test(enabled= true, priority = 2)
	public void checkValidationMsgs() {
		extentReport.logger.log(LogStatus.PASS, SCRIPTS_STARTED_MSG );
		patientDashboardPage.clickOnInvoiceList();
		basePatientLifeCyclePage.headerOnAddPage("Invoice Listing");
		invoiceListingPage.collectAdvancePayment();
		newReceiptPage.headerOfPage("New Receipts (Advance)");
		newReceiptPage.clickSaveBtn();
		newReceiptPage.checkedErrorMode("Please select mode!");
		newReceiptPage.checkedErrorAmount("Please enter amount!");
		newReceiptPage.selectModes("Card");
		newReceiptPage.checkedErrorSubType("Please select sub type!");
		newReceiptPage.selectSubType("Bajaj Finance");
		newReceiptPage.checkedErrorTxnId("Enter txn id!");
		newReceiptPage.checkedErrorAmount("Please enter amount!");
		newReceiptPage.selectModes("Cheque");
		newReceiptPage.checkedErrorChequeNo("Enter cheque number!");
		newReceiptPage.checkedErrorBankName("Enter bank Name!");
		newReceiptPage.checkedErrorAmount("Please enter amount!");
		newReceiptPage.selectModes("NetBanking");
		newReceiptPage.checkedErrorTxnRefId("Enter transaction number!");
		newReceiptPage.checkedErrorBankName("Enter bank Name!");
		newReceiptPage.checkedErrorAmount("Please enter amount!");
		newReceiptPage.selectModes("Wallet");
		newReceiptPage.checkedErrorSubType("Please select sub type!");
		newReceiptPage.checkedErrorAmount("Please enter amount!");
		Assert.assertTrue(basePage.verification().contains("New Receipts"));
	}
	
	@Test(enabled= true, priority = 3)
	public void collectPayment() {
		extentReport.logger.log(LogStatus.PASS, SCRIPTS_STARTED_MSG );
		patientDashboardPage.clickOnInvoiceList();
		basePatientLifeCyclePage.headerOnAddPage("Invoice Listing");
		invoiceListingPage.collectAdvancePayment();
		newReceiptPage.headerOfPage("New Receipts (Advance)");
		newReceiptPage.checkAndSelectAllModes("Cash", "100", "NA", "NA");
		newReceiptPage.enterNotes("cash Test");
		newReceiptPage.clickSaveContinueBtn();
		newReceiptPage.verifySuccessfullMessage();
		newReceiptPage.headerOfPage("New Receipts (Advance)");
		newReceiptPage.checkAndSelectAllModes("Card", "100", "NA", "NA");
		newReceiptPage.selectSubType("Bajaj Finance");
		newReceiptPage.enterTxnId("123456");
		newReceiptPage.enterNotes("Bajaj Finance");
		newReceiptPage.clickSaveContinueBtn();
		newReceiptPage.headerOfPage("New Receipts (Advance)");
		newReceiptPage.checkAndSelectAllModes("Card", "100", "NA", "NA");
		newReceiptPage.selectSubType("Card");
		newReceiptPage.enterNotes("card Test");
		newReceiptPage.clickSaveContinueBtn();
		newReceiptPage.headerOfPage("New Receipts (Advance)");
		newReceiptPage.checkAndSelectAllModes("Cheque", "100", "654321", "SBI Bank");
		newReceiptPage.enterNotes("cheque Test");
		newReceiptPage.clickSaveContinueBtn();
		newReceiptPage.headerOfPage("New Receipts (Advance)");
		newReceiptPage.checkAndSelectAllModes("NetBanking", "100", "222222", "SBI Bank");
		newReceiptPage.enterNotes("cheque Test");
		newReceiptPage.clickSaveContinueBtn();
		newReceiptPage.headerOfPage("New Receipts (Advance)");
		newReceiptPage.checkAndSelectAllModes("Wallet", "100", "NA", "NA");
		newReceiptPage.selectSubType("UPI");
		newReceiptPage.enterNotes("cheque Test");
		newReceiptPage.clickSaveBtn();
		receiptListingPage.headerOnListPage("Receipt Listing");
		receiptListingPage.checkTotalAdvance();
		receiptListingPage.checkReceiptListingTableHead();
		receiptListingPage.checkTotalAdvance();
		receiptListingPage.actionsButton_ReceiptsCreatedDate();
		receiptListingPage.receiptDataTable();
		receiptListingPage.openViewModal();
		receiptListingPage.receiptHeaderInView("Receipt Details");
		receiptListingPage.patientNamePatientId(PATIENT_NAME);
		receiptListingPage.checkDataInView();
		receiptListingPage.viewData();
		receiptListingPage.closeViewModal();
		Assert.assertTrue(basePage.verification().contains("Receipt Listing"));
	}
	@Test(enabled= true, priority = 4)
	public void editReceipt() {
		extentReport.logger.log(LogStatus.PASS, SCRIPTS_STARTED_MSG );
		patientDashboardPage.clickOnReceiptList();
		receiptListingPage.headerOnListPage("Receipt Listing");
		receiptListingPage.clickEditBtn("Card");
		newReceiptPage.headerOfPage("Edit Receipts (Advance)");
		basePatientLifeCyclePage.verifyPatientName(PATIENT_NAME);
		newReceiptPage.checkEditReceiptData("Card", "Bajaj Finance");
		newReceiptPage.checkActionBtnForAdvance();
		newReceiptPage.selectModes("Cheque");
		newReceiptPage.enterNotes("Testing");
		newReceiptPage.clickSaveBtn();
		receiptListingPage.headerOnListPage("Receipt Listing");
		receiptListingPage.openViewModal();
		receiptListingPage.receiptHeaderInView("Receipt Details");
		receiptListingPage.patientNamePatientId(PATIENT_NAME);
		receiptListingPage.viewData();
		receiptListingPage.closeViewModal();
		receiptListingPage.headerOnListPage("Receipt Listing");
		receiptListingPage.clickEditBtn("Cash");
		newReceiptPage.headerOfPage("Edit Receipts (Advance)");
		basePatientLifeCyclePage.verifyPatientName(PATIENT_NAME);
		newReceiptPage.checkEditReceiptData("Cash", "NA");
		newReceiptPage.checkActionBtnForAdvance();
		newReceiptPage.selectModes("Card");
		newReceiptPage.selectSubType("Card");
		newReceiptPage.enterTxnId("1234");
		newReceiptPage.enterNotes("Testing");
		newReceiptPage.clickSaveBtn();
		receiptListingPage.headerOnListPage("Receipt Listing");
		receiptListingPage.openViewModal();
		receiptListingPage.receiptHeaderInView("Receipt Details");
		receiptListingPage.patientNamePatientId(PATIENT_NAME);
		receiptListingPage.viewData();
		receiptListingPage.closeViewModal();
		Assert.assertTrue(basePage.verification().contains("Receipt Listing"));
	}

	@Test(enabled= true, priority = 5)
	public void deleteReceipt() {
		extentReport.logger.log(LogStatus.PASS, SCRIPTS_STARTED_MSG );
		extentReport.logger.log(LogStatus.PASS, "Test");
		patientDashboardPage.clickOnReceiptList();
		receiptListingPage.headerOnListPage("Receipt Listing");
		receiptListingPage.clickDeleteBtn("Cash");
		receiptListingPage.checkRequireDropDwn();
		receiptListingPage.selectRequire("Advance refund");
		receiptListingPage.clickSaveBtnOnReasonPopUp();
		receiptListingPage.verifySuccessfullMessage();
		receiptListingPage.headerOnListPage("Receipt Listing");
		receiptListingPage.clickShowDetails();
		receiptListingPage.clickSearchBtn();
		Assert.assertTrue(basePage.verification().contains("Receipt Listing"));
	}

	@Test(priority = 6)
	public void createInvoice(){
		extentReport.logger.log(LogStatus.PASS, SCRIPTS_STARTED_MSG );
		patientDashboardPage.clickOnTreatmentPlanAddBtn();
		oralExamsPage.clickOnTeethImage("Adult", "24");
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
		invoiceListingPage.collectPayment();
		newReceiptPage.headerOfPage("New Receipts");
		basePatientLifeCyclePage.verifyPatientName(PATIENT_NAME);
		newReceiptPage.invoiceTable();
		Assert.assertTrue(basePage.verification().contains("New Receipts"));
	}
	@Test(priority = 7)
	public void amountCalculation(){
		extentReport.logger.log(LogStatus.PASS, SCRIPTS_STARTED_MSG );
		patientDashboardPage.clickOnInvoiceList();
		invoiceListingPage.invoiceListTableColumnName();
		invoiceListingPage.invoiceDataTable();
		invoiceListingPage.actionsButton_InvoicesCreatedDate();
		invoiceListingPage.collectPayment();
		newReceiptPage.headerOfPage("New Receipts");
		basePatientLifeCyclePage.verifyPatientName(PATIENT_NAME);
		newReceiptPage.invoiceTable();
		newReceiptPage.alertMessages();
		Assert.assertTrue(basePage.verification().contains("New Receipts"));
	}
	@Test(priority = 8)
	public void paymentDetails(){
		extentReport.logger.log(LogStatus.PASS, SCRIPTS_STARTED_MSG );
		patientDashboardPage.clickOnInvoiceList();
		invoiceListingPage.collectPayment();
		newReceiptPage.checkAndSelectAllModes("Cash", "100", "NA", "NA");
		newReceiptPage.enterNotes("cash Added");
		newReceiptPage.saveDraftAmount();
		newReceiptPage.checkAndSelectAllModes("Card", "100", "NA", "NA");
		newReceiptPage.selectSubType("Bajaj Finance");
		newReceiptPage.enterTxnId("654321");
		newReceiptPage.enterNotes("card added");
		newReceiptPage.saveDraftAmount();
		newReceiptPage.checkAndSelectAllModes("Cheque", "100", "123456", "SBI Bank");
		newReceiptPage.enterNotes("cheque added");
		newReceiptPage.saveDraftAmount();
		newReceiptPage.checkAndSelectAllModes("NetBanking", "100", "123456", "SBI Bank");
		newReceiptPage.enterNotes("net banking added");
		newReceiptPage.saveDraftAmount();
		newReceiptPage.checkAndSelectAllModes("Wallet", "100", "NA", "NA");
		newReceiptPage.selectSubType("UPI");
		newReceiptPage.enterNotes("wallet added");
		newReceiptPage.saveDraftAmount();
		newReceiptPage.paymentDetailsTable("Cash","100","NA","NA");
		newReceiptPage.paymentDetailsTable("Card","100","NA","654321");
		newReceiptPage.paymentDetailsTable("Cheque","100","SBI Bank","123456");
		newReceiptPage.paymentDetailsTable("NetBanking","100","SBI Bank","123456");
		newReceiptPage.paymentDetailsTable("Wallet","100","NA","NA");
		newReceiptPage.totalReceipt_RemainingAmountDue();
		newReceiptPage.deleteReceipt("Cash","100");
		newReceiptPage.noDeleteReceipt();
		newReceiptPage.paymentDetailsTable("Cash","100","NA","NA");
		newReceiptPage.deleteReceipt("Cash","100");
		newReceiptPage.yesDeleteReceipt();
		newReceiptPage.checkDeletedReceipt("Cash","100");
		newReceiptPage.totalReceipt_RemainingAmountDue();
		newReceiptPage.checkApplyPaymentButton();
		Assert.assertTrue(basePage.verification().contains("New Receipts"));
	}
	@Test(priority = 9)
	public void amountScenario(){
		extentReport.logger.log(LogStatus.PASS, SCRIPTS_STARTED_MSG );
		patientDashboardPage.clickOnInvoiceList();
		invoiceListingPage.collectPayment();
		newReceiptPage.selectInvoices();
		newReceiptPage.applyPayment();
		newReceiptPage.totalReceipt_RemainingAmountDue();
		newReceiptPage.invoiceTable();
		newReceiptPage.totalDue_totalCollection();
		newReceiptPage.addingReceiptEqualRemainingAmount();
		newReceiptPage.totalReceipt_RemainingAmountDue();
		newReceiptPage.invoiceTable();
		newReceiptPage.totalDue_totalCollection();
		newReceiptPage.applyPayment();
		newReceiptPage.totalReceipt_RemainingAmountDue();
		newReceiptPage.invoiceTable();
		newReceiptPage.totalDue_totalCollection();
		newReceiptPage.checkAndSelectAllModes("Cash", "100", "NA", "NA");
		newReceiptPage.saveDraftAmount();
		newReceiptPage.advanceCreatedNewReceipt();
		newReceiptPage.applyPayment();
		newReceiptPage.clickSaveBtn();
		Assert.assertTrue(basePage.verification().equals("Receipt Listing"));
	}

	@AfterMethod(description = STATUS_OF_SCRIPTS , enabled = true)
	public void statusOfScripts(ITestResult result) {
		extentReport.onTestFailure(result);
		extentReport.logger.log(LogStatus.PASS, STATUS_OF_SCRIPTS );
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
