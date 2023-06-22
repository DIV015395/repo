package com.prm.patientdashboard.pageobject;

import com.prm.pageobjects.utils.ExtentReport;
import com.prm.pageobjects.utils.PCDriver;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

/**
 * @Author:-Ajit Yadav
 * email:-ajit.yadav@instantsys.com
 * Date:-15-05-20
 */
public class InvoiceListingPage {
	private PCDriver loginPage;
	private ExtentReport extentReport;
	public InvoiceListingPage(PCDriver loginPage, ExtentReport extentReport) {
		this.loginPage = loginPage;
		this.extentReport=extentReport;
		PageFactory.initElements(loginPage.getDriver(), this);
	}

	/*--webElement of Invoice List page------*/
	@FindBy(xpath = "//a[contains(@href,'receiptsList')]//i[text()='Receipts']")
	private WebElement receiptsListBtn;
	@FindBy(xpath = "//h1[contains(@class,'btmTitle')]")
	private WebElement headerPage;
	@FindBy(xpath = "//span[contains(@class,'mdl-hdr-text')]")
	private WebElement userName;
	@FindBy(id = "Treatment Status")
	private WebElement treatmentStatusDropDown;
	@FindBy(id = "Invoice Status")
	private WebElement invoiceStatusDropDown;
	@FindBy(xpath = "//input[@id='showInvoiceDeleted']/following-sibling::label[text()='Show Details']")
	private WebElement showDetailCheckBox;
	@FindBy(xpath = "//span[@class='cmnicons srch']/../following-sibling::i[text()='Search']")
	private WebElement invoiceListSearchBtn;
	@FindBy(xpath = "//span[@class='cmnicons rst']/../following-sibling::i[text()='Reset']")
	private WebElement resetBtn;
	@FindBy(xpath = "(//b[text()='Total Amount Due: Rs.']/following-sibling::span)[1]")
	private WebElement totalDueAmount;
	@FindBy(xpath = "//b[text()='Total Advance: Rs.']/following-sibling::span")
	private WebElement totalAdvanceAmount;
	@FindBy(xpath = "//span[@class='actn-icn pay']/following-sibling::span[text()='Collect Advance']")
	private WebElement collectAdvanceBtn;
	@FindBy(xpath = "//span[@class='actn-icn pay']/following-sibling::span[text()='Collect Payment']")
	private WebElement collectPaymentBtn;
	@FindBy(xpath = "//i[text()='No Record Found']")
	private WebElement noRecordFoundText;
	@FindBy(className = "dashboardViewIcon")
	private WebElement invoiceViewIcon;
	@FindBy(className = "dashboardDiscountIcon")
	private WebElement invoiceDiscountIcon;
	@FindBy(className = "dashboardPaymentIcon")
	private WebElement invoicePaymentIcon;
	@FindBy(className = "dashboardRecieptIcon1")
	private WebElement invoiceListRecieptIcon;
	@FindBy(className = "dashboardDeleteIcon")
	private WebElement invoiceDeleteIcon;
	@FindBy(className = "dashboardPrintIcon")
	private WebElement invoicePrintIcon;
	// table column name
	@FindBy(xpath = "//table[@class='table table-striped']/thead/tr/th[text()='Date']")
	private WebElement date;
	@FindBy(xpath = "//table[@class='table table-striped']/thead/tr/th[text()='Invoice #']")
	private WebElement invoice;
	@FindBy(xpath = "//table[@class='table table-striped']/thead/tr/th[text()='Trt. Status']")
	private WebElement treatmentStatus;
	@FindBy(xpath = "//table[@class='table table-striped']/thead/tr/th[text()='Net. Amt.']")
	private WebElement netAmount;
	@FindBy(xpath = "//table[@class='table table-striped']/thead/tr/th[text()='Cpn. Value']")
	private WebElement couponAmount;
	@FindBy(xpath = "//table[@class='table table-striped']/thead/tr/th[text()='Amt. Due']")
	private WebElement amountDue;
	@FindBy(xpath = "//table[@class='table table-striped']/thead/tr/th[text()='Amt. Paid']")
	private WebElement amountPaid;
	@FindBy(xpath = "//table[@class='table table-striped']/thead/tr/th[text()='Doctor']")
	private WebElement doctor;
	@FindBy(xpath = "//table[@class='table table-striped']/thead/tr/th[text()='Action']")
	private WebElement actionBtn;
	@FindBy(xpath = "//table[@class='table table-striped']/thead/tr/th[text()='Correction Note(s)']")
	private WebElement correctionNotes;
	@FindBy(xpath = "//table[@class='table table-striped']/tbody/tr")
	private List<WebElement> allRowsInvoices;

	//web element of invoice view modal
	@FindBy(xpath = "//h4[text()='Invoice Details']")
	private WebElement headerViewModal;
	@FindBy(xpath = "//form[@id='invoiceViewForm']//span[contains(@class,'txt_medgry')]")
	private WebElement patientNameViewModal;
	@FindBy(xpath = "//form[@id='invoiceViewForm']//span[contains(@class,'txt_medgry')]/following-sibling::span")
	private WebElement patientIdViewModal;
	@FindBy(xpath = "//form[@id='invoiceViewForm']//span[contains(@class,'cmnicons sv-mdl')]")
	private WebElement saveBtnViewModal;
	@FindBy(xpath = "//form[@id='invoiceViewForm']//span[contains(@class,'cmnicons cncl-mdl')]")
	private WebElement viewModalCloseModal;
	@FindBy(xpath = "//b[text()='Invoice No.']/ancestor::div/following-sibling::span")
	private WebElement invoiceNoViewModal;
	@FindBy(xpath = "//b[text()='Invoice Date']/ancestor::div/following-sibling::span")
	private WebElement invoiceDateViewModal;
	@FindBy(xpath = "//b[text()='Total Due on invoice']/../..")
	private WebElement totalDueViewModal;
	@FindBy(xpath = "//div[contains(text(),'Item Details')]")
	private WebElement headerItemDetails;
	@FindBy(xpath = "//form[@id='invoiceViewForm']//table/thead//th[text()='Treatment Name']")
	private WebElement treatmentNameColumnViewModal;
	@FindBy(xpath = "//form[@id='invoiceViewForm']//table/thead//th[text()='Unit Cost']")
	private WebElement unitCostColumnViewModal;
	@FindBy(xpath = "//form[@id='invoiceViewForm']//table/thead//th[text()='Source(s)']")
	private WebElement sourceColumnViewModal;
	@FindBy(xpath = "//form[@id='invoiceViewForm']//table/thead//th[text()='Net. Amt.']")
	private WebElement netAmtColumnViewModal;
	@FindBy(xpath = "//form[@id='invoiceViewForm']//table/thead//th[text()='Cpn. Val.']")
	private WebElement cpnValColumnViewModal;
	@FindBy(xpath = "//form[@id='invoiceViewForm']//table/thead//th[text()='Coupon Basis']")
	private WebElement couponBasisColumnViewModal;
	@FindBy(xpath = "//form[@id='invoiceViewForm']//table/thead//th[text()='Amt. Pay.']")
	private WebElement AmtPayColumnViewModal;
	@FindBy(xpath = "//div[contains(text(),'Item Details')]/following-sibling::div//tbody/tr")
	private List<WebElement> itemDetailTreatmentViewModal;
	@FindBy(xpath = "//div[contains(text(),'Item Details')]/following-sibling::div//tfoot/tr")
	private List<WebElement> amountIndicate;
	@FindBy(xpath = "//strong[contains(text(),'Total Amount Payable:')]//../following-sibling::td")
	private WebElement totalPayableAmountViewModal;
	//web element of correction notes
	@FindBy(xpath = "//div[contains(text(),'Correction Details')]")
	private WebElement headerCorrectionDetails;
	@FindBy(xpath = "//div[contains(text(),'Correction Details')]/following-sibling::div//tbody/tr")
	private List<WebElement> rowCorrectionDetailsTreatment;
	//web element of the notes
	@FindBy(xpath = "//textarea[@id='invoicenotes']/../../preceding-sibling::div[contains(text(),'Notes')]")
	private List<WebElement> noteHeader;
	@FindBy(xpath = "//textarea[@id='invoicenotes']/../../preceding-sibling::div[contains(text(),'Notes and Notes History')]")
	private List<WebElement> noteHeader_entered;
	@FindBy(id = "invoicenotes")
	private WebElement noteTextFields;
	@FindBy(xpath = "//textarea[@id='invoicenotes']/following-sibling::span[text()='Max. 64 characters left']")
	private WebElement maxCharMsg;
	@FindBy(xpath = "//div[text()='Enter notes!']")
	private WebElement errorNoteMsg;
	@FindBy(xpath = "//tr[@data-ng-repeat='list in invNotesData']/../preceding-sibling::thead//th[text()='Date']")
	private WebElement notesDateColumn;
	@FindBy(xpath = "//tr[@data-ng-repeat='list in invNotesData']/../preceding-sibling::thead//th[text()='Created By']")
	private WebElement notesCreatedByColumn;
	@FindBy(xpath = "//tr[@data-ng-repeat='list in invNotesData']/../preceding-sibling::thead//th[text()='Notes']")
	private WebElement notesNotesColumn;
	@FindBy(xpath = "//table[@class='table table-striped']//tr[@data-ng-repeat='list in invNotesData']")
	private  List<WebElement> notesRow;
	//coupon amount web element
	@FindBy(xpath = "//h4[text()='Apply/Re-Apply Coupon']")
	private WebElement headerCouponModal;
	@FindBy(xpath = "//span[contains(@class,'pnameid')]")
	private WebElement patientNameCoupon;
	@FindBy(xpath = "//div[contains(text(),'Info')]")
	private WebElement headerInfo;
	@FindBy(xpath = "//b[text()='Invoice No.']/../following-sibling::span")
	private WebElement invoiceNoCouponModal;
	@FindBy(xpath = "//b[text()='Invoice Date']/../following-sibling::span")
	private WebElement invoiceDateCouponModal;
	@FindBy(xpath = "//b[text()='Remaining Amt. Due']/../following-sibling::span[@class='ng-binding']")
	private WebElement remainingAmountDueCouponModal;
	@FindBy(xpath = "//form[@id='invoiceCouponForm']//div[contains(text(),'Treatment')]")
	private WebElement treatmentCouponModal;
	@FindBy(xpath = "//div[contains(@class,'col-xs')]//thead/tr/th[text()='Source']")
	private WebElement sourceCouponColumn;
	@FindBy(xpath = "//div[contains(@class,'col-xs')]//thead/tr/th[text()='Treatment']")
	private WebElement treatmentCouponColumn;
	@FindBy(xpath = "//div[contains(@class,'col-xs')]//thead/tr/th[text()='Unit Cost']")
	private WebElement unitCostCouponColumn;
	@FindBy(xpath = "//div[contains(@class,'col-xs')]//thead/tr/th[text()='Net Amt.']")
	private WebElement netAmountCouponColumn;
	@FindBy(xpath = "//div[contains(@class,'col-xs')]//thead/tr/th[text()='Type']")
	private WebElement typeCouponColumn;
	@FindBy(xpath = "//div[contains(@class,'col-xs')]//thead/tr/th[text()='Cpn. Val.']")
	private WebElement cpnValCouponColumn;
	@FindBy(xpath = "//div[contains(@class,'col-xs')]//thead/tr/th[text()='Amt. Pay.']")
	private WebElement AmtPayCouponColumn;
	@FindBy(xpath = "//div[contains(@class,'col-xs')]//thead/tr/th[text()='Source']")
	private WebElement couponBasisCouponColumn;
	@FindBy(xpath = "//div[contains(@class,'col-xs')]//tbody/tr[contains(@data-ng-repeat,'invoiceCouponData')]")
	private List<WebElement> rowTreatmentColumn;
	@FindBy(xpath = "//div[contains(text(),'Coupon Correction Notes')]")
	private WebElement couponAdjustmentNotes;
	@FindBy(id = "cpn_notes")
	private WebElement couponNoteTextFields;
	@FindBy(xpath = "//textarea[@id='cpn_notes']/following-sibling::span[text()='768 characters left']")
	private WebElement couponAdjustmentCahrLimit;
	@FindBy(xpath = "//span[@class='cmnicons sv-mdl']")
	private WebElement saveBtnModal;
	@FindBy(xpath = "//span[@class='cmnicons cncl-mdl']")
	private WebElement closeBtnBtn;
	@FindBy(xpath = "//strong[contains(text(),'Total Amount Payable')]/../following-sibling::td")
	private WebElement amountPayableTabular;
	@FindBy(xpath = "//span[contains(text(),'Please select Coupon(s) basis!')]")
	private WebElement couponBasisMandatoryMsg;
	@FindBy(xpath = "//span[contains(text(),'There is no changes to Save!')]")
	private WebElement msgWithoutChanges;
	@FindBy(xpath = "//span[contains(text(),'Please update Coupon value or Coupon Basis to add Coupon Adjutment Notes!')]")
	private WebElement updateCouponValueCouponBasAddCouponAdjustmentNotes;
	//web element of the delete modal
	@FindBy(xpath = "//h4[text()='Invoice Delete']")
	private WebElement headerDelete;
	@FindBy(xpath = "//h4[text()='Invoice Delete']/following-sibling::span")
	private WebElement patientNameDelete;
	@FindBy(xpath = "//b[text()='Total Due on Invoice']/../following-sibling::span[@class='ng-binding']")
	private WebElement totalDueInvoiceDeleteModal;
	@FindBy(xpath = "//form[@id='deleteReasonForm']//span[@class='cmnicons sv-mdl']")
	private WebElement saveBtnDeleteModal;
	@FindBy(xpath = "//form[@id='deleteReasonForm']//span[@class='cmnicons cncl-mdl']")
	private WebElement closeBtnDeleteModal;
	@FindBy(xpath = "//div[contains(text(),'Info')]")
	private WebElement headerInfoDeleteModal;
	@FindBy(xpath = "//div[contains(text(),'Invoice/Treatment(s) Deletion Reason')]")
	private WebElement headerDeletionReason;
	@FindBy(xpath = "//label[contains(text(),'Reasons')]")
	private WebElement reasonDrpDwnHeader;
	@FindBy(id = "reasons")
	private WebElement reasonDrpdwn;
	@FindBy(xpath = "//form[@id='deleteReasonForm']//thead/tr/th//div[@class='cstm_rdb_ckb']")
	private WebElement invoiceDeleteCheckbox;
	@FindBy(xpath = "//form[@id='deleteReasonForm']//thead/tr/th[text()='Name']")
	private WebElement nameColumnDeleteModal;
	@FindBy(xpath = "//form[@id='deleteReasonForm']//thead/tr/th[text()='Unit Cost']")
	private WebElement unitCostColumnDeleteModal;
	@FindBy(xpath = "//form[@id='deleteReasonForm']//thead/tr/th[text()='Source(s)']")
	private WebElement sourceColumnDeleteModal;
	@FindBy(xpath = "//form[@id='deleteReasonForm']//thead/tr/th[text()='Net Amt.']")
	private WebElement netAmountColumnDeleteModal;
	@FindBy(xpath = "//form[@id='deleteReasonForm']//thead/tr/th[text()='Cpn. Amt.']")
	private WebElement cpnAmountColumnDeleteModal;
	@FindBy(xpath = "//form[@id='deleteReasonForm']//thead/tr/th[text()='Cpn. Basis']")
	private WebElement cpnBasisColumnDeleteModal;
	@FindBy(xpath = "//form[@id='deleteReasonForm']//thead/tr/th[text()='Amt. Pay.']")
	private WebElement amountPayColumnDeleteModal;
	@FindBy(xpath = "//form[@id='deleteReasonForm']//tbody/tr")
	private List<WebElement> rowDeleteModal;
	@FindBy(xpath = "//div[text()='Select Reason!']")
	private WebElement reasonErrorMsg;
	@FindBy(xpath = "//span[contains(text(),'Please select Treatment(s) to request for Delete!')]")
	private WebElement msgTreatmentNotSelected;
	@FindBy(xpath = "//span[contains(text(),'Invoice/Trt deleted successfully!']")
	private WebElement deleteSuccessMsg;

	//selecting the collect by payment
	public void collectPayment(){
		loginPage.waitForPageLoad();
		try {
			loginPage.waitForElementToBeClickable(collectPaymentBtn);
			collectPaymentBtn.click();
			Thread.sleep(8000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	//selecting the collect by payment
	public void collectAdvancePayment(){
		loginPage.waitForPageLoad();
		try {
			loginPage.waitForElementToBeClickable(collectAdvanceBtn);
			collectAdvanceBtn.click();
			Thread.sleep(8000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	//selecting the show details button
	public void selectShowDetails(){
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(showDetailCheckBox);
		showDetailCheckBox.click();
	}
	//selecting the show details button
	public void clickOnSearch(){
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(invoiceListSearchBtn);
		try {
			invoiceListSearchBtn.click();
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	/*Checking at invoice listing page invoices data column names*/

	public void invoiceListTableColumnName() {
		loginPage.waitForPageLoad();
		try {
			Thread.sleep(4000);
			loginPage.waitForElementToBeClickable(date);
			Assert.assertTrue(checkedWebElementDisplayed(date) && checkedWebElementDisplayed(invoice) && checkedWebElementDisplayed(treatmentStatus) && checkedWebElementDisplayed(netAmount)
					&& checkedWebElementDisplayed(couponAmount) && checkedWebElementDisplayed(amountDue) && checkedWebElementDisplayed(amountPaid)
					&& checkedWebElementDisplayed(doctor) && checkedWebElementDisplayed(actionBtn));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	//checking collect advace payment button at invoice listing
	public void checkCollectAdvacePaymentBtn(){
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(collectAdvanceBtn);
		Assert.assertTrue(checkedWebElementDisplayed(collectAdvanceBtn));
	}

	//checking collect advace payment button at invoice listing
	public void checkCollectPaymentBtn(){
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(collectPaymentBtn);
		Assert.assertTrue(checkedWebElementDisplayed(collectPaymentBtn));
	}

	/*Checking header on invoice listing page*/
	public void headerPage(String headerInvoiceList) {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(headerPage);
		Assert.assertTrue(headerPage.getText().contains(headerInvoiceList));
	}

	/*Checking patient name and patient id*/
	public void verifyUserName(String patientName) {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(userName);
		String str = userName.getText();
		String[] split = str.split(" : ");
		boolean flag = split[0].equalsIgnoreCase(patientName) && !(split[1].equals("NA") || split[1].equals(null));
		Assert.assertTrue(flag);
	}

	/*Checking treatment status dropdown at invoice listing page*/
	public void treatmentStatusDropDown(String defaultTreatmentStatusSelected) {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(treatmentStatusDropDown);
		boolean flag1, flag2, match = false;
		flag1 = checkedWebElementDisplayed(treatmentStatusDropDown) && loginPage.firstSelectedOption(treatmentStatusDropDown).contains(defaultTreatmentStatusSelected);
		String[] exp = {"Select Treatment Status", "In-Progress", "Complete"};
		Select select = new Select(treatmentStatusDropDown);
		List<WebElement> options = select.getOptions();
		for (WebElement we : options) {
			for (int i = 0; i < exp.length; i++) {
				if (flag2 = we.getText().equals(exp[i])) {
					match = flag1 && flag2;
				}
			}
			Assert.assertTrue(match);
		}
	}

	/*Checking invoices status dropdown at invoice listing page*/
	public void invoiceStatusDropDown(String defaultInvoiceStatusSelected) {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(invoiceStatusDropDown);
		boolean flag1, flag2, match = false;
		flag1 = checkedWebElementDisplayed(invoiceStatusDropDown) && loginPage.firstSelectedOption(invoiceStatusDropDown).contains(defaultInvoiceStatusSelected);
		String[] exp = {"Select Invoice Status", "New, Pending", "Close"};
		Select select = new Select(invoiceStatusDropDown);
		List<WebElement> options = select.getOptions();
		for (WebElement we : options) {
			for (int i = 0; i < exp.length; i++) {
				if (flag2 = we.getText().equals(exp[i])) {
					match = flag1 && flag2;
				}
			}
			Assert.assertTrue(match);
		}
	}

	/*checking checkbox and "Show Details" text for show details*/
	public void showDetailsCheckBox() {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(showDetailCheckBox);
		Assert.assertTrue(checkedWebElementDisplayed(showDetailCheckBox));
	}

	/*Checking invoice search button and "Search" text at invoice listing page */
	public void checkSearchBtn() {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(invoiceListSearchBtn);
		Assert.assertTrue(checkedWebElementDisplayed(invoiceListSearchBtn));
	}

	public void checkResetBtn() {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(resetBtn);
		Assert.assertTrue(checkedWebElementDisplayed(resetBtn));
	}

	/*checking total due amount text
	 * without invoices total due amount at invoices listing
	 *total advance amount text
	 * without invoices total amount at invoice listing  */
	public void amountSection(int total_Due_Amount,int total_Advance_Amount) {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(totalDueAmount);
		Assert.assertTrue( totalDueAmount.getText().contains(Integer.toString(total_Due_Amount)) && totalAdvanceAmount.getText().contains(Integer.toString(total_Advance_Amount)));
	}

	/*Checking no record found at invoice listing page */
	public void noRecordFound() {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(noRecordFoundText);
		Assert.assertTrue(checkedWebElementDisplayed(noRecordFoundText));
	}

	/*checking receipts list button and text at receipt listing button*/
	public void checkReceiptBtn() {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(receiptsListBtn);
		Assert.assertTrue(checkedWebElementDisplayed(receiptsListBtn));
	}

	/*validating the invoice data*/
	public void invoiceDataTable(){
		loginPage.waitForPageLoad();
		// And iterate over them, getting the cells
		loginPage.visibilityOfListLocated(allRowsInvoices);
		for (WebElement row : allRowsInvoices) {
			List<WebElement> cells = row.findElements(By.tagName("td"));
			//invoice created date
			String createdDate = cells.get(1).getText();
			//invoice number and invoice status
			WebElement invoiceStatus = cells.get(2).findElement(By.xpath("span[contains(@ng-class,'invoiceDataList.invoiceStatus')]"));
			WebElement invoiceNumber = cells.get(2).findElement(By.xpath("span[contains(@ng-class,'invoiceDataList.invoiceStatus')]/following-sibling::span"));
			//treatment status
			WebElement invoiceTreatmentStatus = cells.get(3).findElement(By.xpath("span[contains(@ng-class,'invoiceDataList.treatmentStatus')]"));
			//net amount of invoice
			String[] netAmount = cells.get(4).getText().trim().split("\\.");
			int netAmountPrice = 0;
			//coupon amount of invoice
			String[] couponAmount = cells.get(5).getText().trim().split("\\.");
			int couponAmountTabuler = 0;
			//amount Due of invoices
			String[] amountDue = cells.get(6).getText().trim().split("\\.");
			int amountDueTabular = 0;
			//amount paid of invoice
			String[] amountPaid = cells.get(7).getText().trim().split("\\.");
			int amountPaidTabular = 0;
			//doctor name at invoice
			String doctor=cells.get(8).getText().trim();
			//correction note at invoice
			String correctionNote = cells.get(9).getText().trim();
			try {
				netAmountPrice = NumberFormat.getNumberInstance(Locale.US).parse(netAmount[0]).intValue();
				couponAmountTabuler = NumberFormat.getNumberInstance(Locale.US).parse(couponAmount[0]).intValue();
				amountDueTabular = NumberFormat.getNumberInstance(Locale.US).parse(amountDue[0].trim()).intValue();
				amountPaidTabular = NumberFormat.getNumberInstance(Locale.US).parse(amountPaid[0]).intValue();
			} catch (ParseException e) {
				e.printStackTrace();
			}
			//checking invoices All possible status of invoice
			if (amountPaidTabular == 0) {
				Assert.assertTrue(invoiceStatus.getAttribute("class").contains("new")&&!(invoiceNumber.getText().contains("null")||invoiceNumber.getText().contains("NA")));
			} else if(amountPaidTabular!=0 && amountDueTabular!=0){
				Assert.assertTrue(invoiceStatus.getAttribute("class").contains("pending")&&!(invoiceNumber.getText().contains("null")||invoiceNumber.getText().contains("NA")));
			}else{
				Assert.assertTrue(invoiceStatus.getAttribute("class").contains("close")&&!(invoiceNumber.getText().contains("null")||invoiceNumber.getText().contains("NA")));
			}
			//checking all the possible status of the treatment at invoices listing
			boolean flag1 = invoiceTreatmentStatus.getAttribute("class").contains("in-progress") || invoiceTreatmentStatus.getAttribute("class").contains("start");
			//checking net amount validation
			boolean flag2=(netAmountPrice==(amountDueTabular+couponAmountTabuler+amountPaidTabular));
			//checking coupon amount validation
			boolean flag3=(couponAmountTabuler==(netAmountPrice-(amountDueTabular+amountPaidTabular)));
			//checking amount due validation
			boolean flag4=(amountDueTabular==(netAmountPrice-(couponAmountTabuler+amountPaidTabular)));
			//checking amount paid validation
			boolean flag5=(amountPaidTabular==(netAmountPrice-(amountDueTabular+couponAmountTabuler)));
			//checking doctor name
			boolean flag6=!(doctor.contains("NA")||doctor.contains("null"));
			//checking correction notes
			boolean flag7=correctionNote.contains("NA")||(!correctionNote.contains("null"));
			//checking invoice created date
			boolean flag8=!(createdDate.contains("NA")||createdDate.contains("null"));
			//checking All data of table
			Assert.assertTrue(flag1 && flag2 && flag3 && flag4 && flag5 && flag6 && flag7 && flag8);
		}
	}

	//checking the actions button of all the invoices on the basis of invoices created date
	public void actionsButton_InvoicesCreatedDate(){
		final long MILLIS_PER_DAY = 24 * 60 * 60 * 1000L;
		for (WebElement row : allRowsInvoices) {
			List<WebElement> cells = row.findElements(By.tagName("td"));
			//invoice created date
			String createdDate = cells.get(1).getText();
			//action buttons for every new invoice
			WebElement viewBtn = cells.get(10).findElement(By.xpath("a[contains(@data-ng-click,'invoiceView')]"));
			WebElement couponBtn = cells.get(10).findElement(By.xpath("a[contains(@data-ng-click,'couponView')]"));
			WebElement receiptBtn = cells.get(10).findElement(By.xpath("a[contains(@data-ng-click,'getReceipt')]"));
			WebElement deleteBtn = cells.get(10).findElement(By.xpath("a[contains(@data-ng-click,'deleteInvoice')]"));
			WebElement printBtn = cells.get(10).findElement(By.xpath("a[contains(@data-ng-click,'getPrint')]"));
			//formatting the date according our requirement
			SimpleDateFormat simple = new SimpleDateFormat("dd-MM-yy");
			//current date
			String todayDate=null;
			Date date = new Date();
			//invoice created date in date form
			Date invoiceCreatedDate = null;
			try {
				//parsing String to Date
				invoiceCreatedDate = simple.parse(createdDate);
				todayDate = simple.format(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			//checking coupon button should only appear on the invoice created date with other like print,view,receipt,delete
			if((Math.abs(date.getTime() - invoiceCreatedDate.getTime()) < MILLIS_PER_DAY)&&createdDate.equals(todayDate)){
				Assert.assertTrue(checkedWebElementDisplayed(viewBtn)&&checkedWebElementDisplayed(receiptBtn)&&checkedWebElementDisplayed(printBtn)&&checkedWebElementDisplayed(couponBtn)&&checkedWebElementDisplayed(deleteBtn));
				//checking if invoice created date is within 24 hr hour but not	on same day then delete,print,receipt,view only appear
			}else if((Math.abs(date.getTime() - invoiceCreatedDate.getTime()) < MILLIS_PER_DAY)&&!createdDate.equals(todayDate)){
				Assert.assertTrue(checkedWebElementDisplayed(viewBtn)&&checkedWebElementDisplayed(receiptBtn)&&checkedWebElementDisplayed(printBtn)&&!checkedWebElementDisplayed(couponBtn)&&checkedWebElementDisplayed(deleteBtn));
				//checking actions button appear after the 24 hr.
			}else{
				Assert.assertTrue(checkedWebElementDisplayed(viewBtn)&&checkedWebElementDisplayed(receiptBtn)&&checkedWebElementDisplayed(printBtn)&&!checkedWebElementDisplayed(couponBtn)&&!checkedWebElementDisplayed(deleteBtn));
			}
		}
	}

	//checking the total net amount calculation
	public void netAmountAllInvoices(){
		Assert.assertEquals(totalNetAmount(),(totalAmountPaid()+totalAmountDueAllInvoices()+totalCouponAmount()));
	}

	//checking the total coupon amount calculation
	public void couponAmountAllInvoices(){
		Assert.assertEquals(totalCouponAmount(),(totalNetAmount()-(totalAmountPaid()+totalAmountDueAllInvoices())));
	}

	//checking the total amount due calculation
	public void amountDueAllInvoices(){
		if(totalAmountDue()==totalAmountDueAllInvoices()){
			Assert.assertEquals(totalAmountDueAllInvoices(),(totalNetAmount()-(totalAmountPaid()+totalCouponAmount())));
		}else {
			Assert.fail();
		}
	}

	//checking the total amount paid calculation
	public void amountPaidAllInvoices(){
		Assert.assertEquals(totalAmountPaid(),(totalNetAmount()-(totalCouponAmount()+totalAmountDueAllInvoices())));
	}

	//calculating sum of net amount of all the invoices together
	private long totalNetAmount() {
		loginPage.waitForPageLoad();
		long netAmountPrice = 0,netAmountPrice1 = 0;
		// And iterate over them, getting the cells
		loginPage.visibilityOfListLocated(allRowsInvoices);
		for (WebElement row : allRowsInvoices) {
			List<WebElement> cells = row.findElements(By.tagName("td"));
			String str = cells.get(4).getText().trim();
			String[] str1 = str.split("\\.");
			try {
				netAmountPrice = NumberFormat.getNumberInstance(Locale.US).parse(str1[0]).intValue();
				netAmountPrice1=netAmountPrice1+netAmountPrice;
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return netAmountPrice1;
	}

	//calculating sum of amount paid of all the invoices together
	private long totalAmountPaid() {
		loginPage.waitForPageLoad();
		long amountPaidPrice = 0,amountPaidPrice1=0;
		// And iterate over them, getting the cells
		loginPage.visibilityOfListLocated(allRowsInvoices);
		for (WebElement row : allRowsInvoices) {
			List<WebElement> cells = row.findElements(By.tagName("td"));
			String str = cells.get(7).getText().trim();
			String[] str1 = str.split("\\.");
			try {
				amountPaidPrice = NumberFormat.getNumberInstance(Locale.US).parse(str1[0]).intValue();
				amountPaidPrice1=amountPaidPrice1+amountPaidPrice;
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return amountPaidPrice1;
	}

	//calculating sum of amount due of all the invoices together
	private long totalAmountDueAllInvoices() {
		loginPage.waitForPageLoad();
		long amountDue = 0,amountDue1=0;
		// And iterate over them, getting the cells
		loginPage.visibilityOfListLocated(allRowsInvoices);
		for (WebElement row : allRowsInvoices) {
			List<WebElement> cells = row.findElements(By.tagName("td"));
			String str = cells.get(6).getText().trim();
			String[] str1 = str.split("\\.");
			try {
				amountDue = NumberFormat.getNumberInstance(Locale.US).parse(str1[0].trim()).intValue();
				amountDue1=amountDue1+amountDue;
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return amountDue1;
	}

	//calculating sum of coupon amount of all the invoices together
	private long totalCouponAmount() {
		loginPage.waitForPageLoad();
		long couponAmountTabuler = 0,couponAmountTabuler1=0;
		// And iterate over them, getting the cells
		loginPage.visibilityOfListLocated(allRowsInvoices);
		for (WebElement row : allRowsInvoices) {
			List<WebElement> cells = row.findElements(By.tagName("td"));
			String str = cells.get(5).getText().trim();
			String[] str1 = str.split("\\.");
			try {
				couponAmountTabuler = NumberFormat.getNumberInstance(Locale.US).parse(str1[0]).intValue();
				couponAmountTabuler1=couponAmountTabuler1+couponAmountTabuler;
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return couponAmountTabuler1;
	}

	//converting the string into integer
	private long totalAmountDue() {
		long totalAmountDue = 0;
		try {
			totalAmountDue = NumberFormat.getNumberInstance(Locale.US).parse(totalDueAmount.getText().trim()).intValue();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return totalAmountDue;
	}

	//open the view modal which having invoice created today
	public void openViewModal(){
		loginPage.visibilityOfListLocated(allRowsInvoices);
		if(allRowsInvoices.size()>0) {
			Random randomGenerator = new Random();
			int index = randomGenerator.nextInt(allRowsInvoices.size());
			WebElement item = allRowsInvoices.get(index);
			List<WebElement> cells = item.findElements(By.tagName("td"));
			String invoiceNumber = cells.get(2).findElement(By.xpath("span[contains(@ng-class,'invoiceDataList.invoiceStatus')]/following-sibling::span")).getText();
			String invoice_Number_extentReport = invoiceNumber + "-invoice view Test cases executed";
			extentReport.logger.log(LogStatus.PASS, invoice_Number_extentReport);
			WebElement viewBtn = cells.get(10).findElement(By.xpath("a[contains(@data-ng-click,'invoiceView')]"));
			viewBtn.click();
		}else{
			Assert.fail("There is no invoice");
		}
	}

	//closing the view modal
	public void closeViewModal(){
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(viewModalCloseModal);
		viewModalCloseModal.click();
	}
	//checking patient name and patient id at view modal
	public void patientNamePatientId(String patientName){
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(patientNameViewModal);
		String str = patientNameViewModal.getText();
		String str1=patientIdViewModal.getText();
		boolean flag = str.contains(patientName) && !(str1.equals("NA") || str1.equals(null));
		Assert.assertTrue(flag);
	}
	//validating total due for an invoice at the view modal
	public void totalDueViewModal(){
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(totalDueViewModal);
		String[] split = totalDueViewModal.getText().split("\\r?\\n");
		String[] totalDue_viewModal = split[1].split("\\.");
		String[] invoiceNumber = invoiceNoViewModal.getText().split("/");
		String invoice_tabular = invoiceNumber[1];
		WebElement paidAmount = loginPage.getDriver().findElement(By.xpath("(//span[contains(text(),'" + invoice_tabular + "')]/ancestor::td/following-sibling::td)[5]"));
		String[] amountPaid=paidAmount.getText().trim().split("\\.");
		String[] totalPayableAmountView = totalPayableAmountViewModal.getText().split("\\.");
		long totalDue_viewModal_price=0,amountPaid_price=0,totalPayableAmountView_price=0;
		try {
			totalDue_viewModal_price = NumberFormat.getNumberInstance(Locale.US).parse(totalDue_viewModal[0]).intValue();
			amountPaid_price = NumberFormat.getNumberInstance(Locale.US).parse(amountPaid[0]).intValue();
			totalPayableAmountView_price = NumberFormat.getNumberInstance(Locale.US).parse(totalPayableAmountView[0]).intValue();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Assert.assertTrue(totalPayableAmountView_price==(totalDue_viewModal_price+amountPaid_price));
	}
	//checking column name at invoice view modal
	public void columnNameInvoiceViewModal(){
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(headerItemDetails);
		Assert.assertTrue(checkedWebElementDisplayed(headerItemDetails)&&checkedWebElementDisplayed(treatmentNameColumnViewModal)&&checkedWebElementDisplayed(unitCostColumnViewModal)&&checkedWebElementDisplayed(sourceColumnViewModal)&&checkedWebElementDisplayed(netAmtColumnViewModal)&&checkedWebElementDisplayed(cpnValColumnViewModal)
				&&checkedWebElementDisplayed(couponBasisColumnViewModal)&&checkedWebElementDisplayed(AmtPayColumnViewModal));
	}
	//checking the treatment table in view modal
	public void itemDetails_CorrectionDetails(){
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(headerViewModal);
		boolean headerView_flag = checkedWebElementDisplayed(headerViewModal);
		boolean invoiceNum_flag = !(invoiceNoViewModal.getText().contains("NA") || invoiceNoViewModal.getText().contains("null"));
		boolean invoiceDate_flag = !(invoiceDateViewModal.getText().contains("NA") || invoiceDateViewModal.getText().contains("null"));
		if(headerView_flag&&invoiceNum_flag&&invoiceDate_flag) {
			for (WebElement row : itemDetailTreatmentViewModal) {
				//getting total cell count
				List<WebElement> cells = row.findElements(By.tagName("td"));
				//fetching the treatment name from the view modal of the invoice
				String treatment = cells.get(0).getText();
				//fetching the unit cost from the view modal of the invoice
				String[] unitCost = cells.get(1).getText().split("\\.");
				//fetching the source from the view modal of the invoice
				String source = cells.get(3).getText();
				//fetching the net amount from the view modal of the invoice
				String[] netAmount = cells.get(4).getText().split("\\.");
				//fetching the coupon amount from the view modal of the invoice
				String[] couponAmount = cells.get(5).findElement(By.xpath("span[@class='ng-binding']")).getText().split("\\.");
				//fetching coupon basis
				String couponBasis = cells.get(7).findElement(By.xpath("span[@class='ng-binding']")).getText();
				//fetching the amount payable from the view modal of the invoice
				String[] amountPayable = cells.get(8).findElement(By.xpath("span[@class='ng-binding']")).getText().split("\\.");
				long unit_Cost = 0, net_Amount = 0, coupon_Amount = 0, amount_Payable = 0;
				try {
					unit_Cost = NumberFormat.getNumberInstance(Locale.US).parse(unitCost[0]).intValue();
					net_Amount = NumberFormat.getNumberInstance(Locale.US).parse(netAmount[0]).intValue();
					coupon_Amount = NumberFormat.getNumberInstance(Locale.US).parse(couponAmount[0]).intValue();
					amount_Payable = NumberFormat.getNumberInstance(Locale.US).parse(amountPayable[0]).intValue();
				} catch (ParseException e) {
					e.printStackTrace();
				}
				//validating treatment should not be NA or null
				boolean flag1 = !(treatment.contains("NA") || treatment.contains("null"));
				//unit cost can not 0 and null
				boolean flag2 = !(unit_Cost == 0);
				//checking source
				boolean flag3 = !(source.contains("NA") || source.contains("null"));
				//checking net Amount
				boolean flag4 = (net_Amount == (coupon_Amount + amount_Payable));
				//checking coupon amount
				boolean flag5 = (coupon_Amount == (net_Amount - amount_Payable));
				//checking the amount payable
				boolean flag6 = (amount_Payable == (net_Amount - coupon_Amount));
				//checking treatment in invoices not deleted or no coupon Applied
				if ((!row.getAttribute("class").contains("deleted")) && couponBasis.contains("No Remarks")) {
					//checking coupon basis
					boolean flag7 = couponBasis.contains("No Remarks");
					Assert.assertTrue(flag1 && flag2 && flag3 && flag4 && flag5 && flag6 && flag7);
				} else if (row.getAttribute("class").contains("deleted")) {
					//checking coupon basis
					boolean flag7 = !(couponBasis.contains("NA") || couponBasis.contains("null"));
					//checking the treatment details in the item details
					if (flag1 && flag2 && flag3 && flag4 && flag5 && flag6 && flag7) {
						//checking the correction header after correction note entry
						boolean headerDisplayed = checkedWebElementDisplayed(headerCorrectionDetails);
						//iterating correction entry on the basis of the treatment
						for (WebElement correctionRow : rowCorrectionDetailsTreatment) {
							long treatment_Deleted_Amount = 0, amount_Payable_deletion_all = 0;
							List<WebElement> correctionCells = correctionRow.findElements(By.tagName("td"));
							String treatmentName = correctionCells.get(0).getText();
							String negativeUnitCostDeletion = correctionCells.get(1).getText().split(" ")[0];
							//checking same treatment deleted in items details in the correction details
							if (treatment.equalsIgnoreCase(treatmentName)&&negativeUnitCostDeletion.contains("-")) {
								//checking the negative sign before the unit cost and unit cost price
								String UnitCostDeletion = correctionCells.get(1).getText().split(" ")[1].split("\\.")[0];
								//checking the source from deletion
								String sourceDeletion = correctionCells.get(2).getText();
								//checking the negative sign before the net Amount and net Amount price
								String negativeNetAmount = correctionCells.get(4).getText().split(" ")[0];
								String netAmountDeletion = correctionCells.get(4).getText().split(" ")[1].split("\\.")[0];
								//checking the negative sign before the coupon amount and coupon amount price
								String[] negativeCouponAmount = correctionCells.get(6).getText().split("-");
								String couponAmountDeletion = negativeCouponAmount[1].split("\\.")[0];
								//checking coupon basis on the deletion notes
								String couponBasisDeletion = correctionCells.get(8).getText();
								//checking the negative sign before the amount payable and amount payable price
								System.out.println(correctionCells.get(10).getText()+" payable amount");
								String negativeAmountPayable = correctionCells.get(10).getText().split(" ")[0];
								String amountPayableDeletion = correctionCells.get(10).getText().split(" ")[1].split("\\.")[0];
								//checking correction invoice number
								String correctionInvoiceNumber = loginPage.getDriver().findElement(By.xpath("//td[contains(text(),'" + treatmentName + "')]/ancestor::div[@class='brdr_drk_gry whtBox nogap table-responsive']/preceding-sibling::div//b[text()='Correction Invoice No.']/../following-sibling::span")).getText();
								//checking correction invoice created date
								String correctionInvoiceDate = loginPage.getDriver().findElement(By.xpath("//td[contains(text(),'" + treatmentName + "')]/ancestor::div[@class='brdr_drk_gry whtBox nogap table-responsive']/preceding-sibling::div//b[text()='Correction Invoice Date']/../following-sibling::span")).getText();
								//checking correction action
								String correctionAction = loginPage.getDriver().findElement(By.xpath("//td[contains(text(),'" + treatmentName + "')]/ancestor::div[@class='brdr_drk_gry whtBox nogap table-responsive']/preceding-sibling::div//b[text()='Correction Action']/../following-sibling::span")).getText();
								//checking deletion reason
								String deletionReason = loginPage.getDriver().findElement(By.xpath("//td[contains(text(),'" + treatmentName + "')]/ancestor::div[@class='brdr_drk_gry whtBox nogap table-responsive']/preceding-sibling::div//b[text()='Deletion Reason']/../following-sibling::span")).getText();
								//treatment deleted price
								String treatmentDeletedAmount = loginPage.getDriver().findElement(By.xpath("//td[contains(text(),'" + treatmentName + "')]/../../following-sibling::tfoot//strong[text()='Treatment Deleted for Amount:']/../following-sibling::td")).getText().split(" ")[1];
								long unit_Cost_deletion = 0, net_Amount_deletion = 0, coupon_Amount_deletion = 0, amount_Payable_deletion = 0;
								try {
									unit_Cost_deletion = NumberFormat.getNumberInstance(Locale.US).parse(UnitCostDeletion).intValue();
									net_Amount_deletion = NumberFormat.getNumberInstance(Locale.US).parse(netAmountDeletion).intValue();
									coupon_Amount_deletion = NumberFormat.getNumberInstance(Locale.US).parse(couponAmountDeletion).intValue();
									amount_Payable_deletion = NumberFormat.getNumberInstance(Locale.US).parse(amountPayableDeletion).intValue();
									treatment_Deleted_Amount = NumberFormat.getNumberInstance(Locale.US).parse(treatmentDeletedAmount).intValue();
								} catch (ParseException e) {
									e.printStackTrace();
								}
								amount_Payable_deletion_all = amount_Payable_deletion_all + amount_Payable_deletion;
								boolean correctionInvoice_flag = !(correctionInvoiceNumber.contains("NA") || correctionInvoiceNumber.contains("null"));
								boolean correctionDate_flag = !(correctionInvoiceDate.contains("NA") || correctionInvoiceDate.contains("null"));
								boolean correctionAction_flag = !(correctionAction.contains("NA") || correctionAction.contains("null"));
								boolean correctionReason_flag = !(deletionReason.contains("NA") || deletionReason.contains("null"));
								boolean unit_cost_flag = negativeUnitCostDeletion.contains("-") && !(unit_Cost_deletion == 0);
								boolean source_flag = !(sourceDeletion.contains("NA") || sourceDeletion.contains("null"));
								boolean net_Amount_flag = negativeNetAmount.contains("-") && (net_Amount_deletion == (coupon_Amount_deletion + amount_Payable_deletion));
								boolean coupon_Amount_flag = (coupon_Amount_deletion == (net_Amount_deletion - amount_Payable_deletion));
								boolean coupon_basis_flag = !(couponBasisDeletion.contains("NA") || couponBasisDeletion.contains("null"));
								boolean amount_Payable_flag = negativeAmountPayable.contains("-") && (amount_Payable_deletion == (net_Amount_deletion - coupon_Amount_deletion));
								Assert.assertTrue(correctionInvoice_flag && correctionDate_flag && correctionAction_flag && correctionReason_flag && unit_cost_flag && source_flag && net_Amount_flag && coupon_Amount_flag && coupon_basis_flag && amount_Payable_flag);
							} else {
								continue;
							}
							Assert.assertEquals(treatment_Deleted_Amount, amount_Payable_deletion_all);
						}
					}
				} else {
					boolean flag7 = !couponBasis.contains("No Remarks");
					//checking the treatment details in the item details
					if (flag1 && flag2 && flag3 && flag4 && flag5 && flag6 && flag7) {
						//checking the correction header after correction note entry
						boolean headerDisplayed = checkedWebElementDisplayed(headerCorrectionDetails);
						//iterating correction entry on the basis of the treatment
						for (WebElement correctionRow : rowCorrectionDetailsTreatment) {
							List<WebElement> correctionCells = correctionRow.findElements(By.tagName("td"));
							String treatmentName = correctionCells.get(0).getText();
							//checking same treatment deleted in items details in the correction details
							if (treatment.equalsIgnoreCase(treatmentName)) {
								//checking the negative sign before the unit cost and unit cost price
								String UnitCostCouponAdjust = correctionCells.get(1).getText().split("\\.")[0];
								//checking the source from coupon adjusted
								String sourceCouponAdjust = correctionCells.get(3).getText();
								//checking the net Amount price
								String netAmountCouponAdjust = correctionCells.get(4).getText().split("\\.")[0];
								//checking the coupon amount price
								String couponAmountCouponAdjust = correctionCells.get(5).getText().split("\\r?\\n")[0];
								String couponAmountPrevious = correctionCells.get(5).getText().split("\\r?\\n")[1];
								//checking coupon basis
								String couponBasisText = correctionCells.get(8).getText();
								//checking the  amount payable price
								String amountPayableDeletion = correctionCells.get(9).getText().split("\\r?\\n")[0];
								String amountPayablePrevious = correctionCells.get(9).getText().split("\\r?\\n")[1];
								//checking correction invoice number
								String correctionInvoiceNumber = loginPage.getDriver().findElement(By.xpath("//td[contains(text(),'" + treatmentName + "')]/ancestor::div[@class='brdr_drk_gry whtBox nogap table-responsive']/preceding-sibling::div//b[text()='Correction Invoice No.']/../following-sibling::span")).getText();
								//checking correction invoice created date
								String correctionInvoiceDate = loginPage.getDriver().findElement(By.xpath("//td[contains(text(),'" + treatmentName + "')]/ancestor::div[@class='brdr_drk_gry whtBox nogap table-responsive']/preceding-sibling::div//b[text()='Correction Invoice Date']/../following-sibling::span")).getText();
								//checking correction action
								String correctionAction = loginPage.getDriver().findElement(By.xpath("//td[contains(text(),'" + treatmentName + "')]/ancestor::div[@class='brdr_drk_gry whtBox nogap table-responsive']/preceding-sibling::div//b[text()='Correction Action']/../following-sibling::span")).getText();
								//coupon adjustment notes
								List<WebElement> couponAdjustmentNotes = loginPage.getDriver().findElements(By.xpath("//td[contains(text(),'" + treatmentName + "')]/ancestor::div[@class='brdr_drk_gry whtBox nogap table-responsive']/preceding-sibling::div//b[text()='Coupon Adjustment Notes']/following-sibling::span"));
								//treatment deleted price
								String amountPayableDecreased = loginPage.getDriver().findElement(By.xpath("//td[contains(text(),'" + treatmentName + "')]/../../following-sibling::tfoot//strong[text()='Amount Payable Decreased By:']/../following-sibling::td")).getText().split("\\.")[0];
								long unit_Cost_deletion = 0, net_Amount_deletion = 0, coupon_Amount_deletion = 0, amount_Payable_deletion = 0, treatment_Deleted_Amount = 0;
								try {
									unit_Cost_deletion = NumberFormat.getNumberInstance(Locale.US).parse(UnitCostCouponAdjust).intValue();
									net_Amount_deletion = NumberFormat.getNumberInstance(Locale.US).parse(netAmountCouponAdjust).intValue();
									coupon_Amount_deletion = NumberFormat.getNumberInstance(Locale.US).parse(couponAmountCouponAdjust).intValue();
									amount_Payable_deletion = NumberFormat.getNumberInstance(Locale.US).parse(amountPayableDeletion).intValue();
									treatment_Deleted_Amount = NumberFormat.getNumberInstance(Locale.US).parse(amountPayableDeletion).intValue();
								} catch (ParseException e) {
									e.printStackTrace();
								}
								boolean correctionInvoice_flag = !(correctionInvoiceNumber.contains("NA") || correctionInvoiceNumber.contains("null"));
								boolean correctionDate_flag = !(correctionInvoiceDate.contains("NA") || correctionInvoiceDate.contains("null"));
								boolean correctionAction_flag = !(correctionAction.contains("NA") || correctionAction.contains("null"));
								boolean couponAdjustmentNotes_flag = false;
								if (couponAdjustmentNotes.size() > 0) {
									for (int i = 0; couponAdjustmentNotes.size() > i; i++) {
										couponAdjustmentNotes_flag = !(couponAdjustmentNotes.get(i).getText().contains("NA") || couponAdjustmentNotes.get(i).getText().contains("null"));
									}
								} else {
									couponAdjustmentNotes_flag = true;
								}
								boolean unit_cost_flag = !(unit_Cost_deletion == 0);
								boolean source_flag = !(sourceCouponAdjust.contains("NA") || sourceCouponAdjust.contains("null"));
								boolean net_Amount_flag = (net_Amount_deletion == (coupon_Amount_deletion + amount_Payable_deletion));
								boolean coupon_Amount_flag = !(couponAmountPrevious.contains("NA") || couponAmountPrevious.contains("null")) && (coupon_Amount_deletion == (net_Amount_deletion - amount_Payable_deletion));
								boolean coupon_basis_flag = !(couponBasisText.contains("No Remarks") || couponBasisText.contains("null") || couponBasisText.contains("NA"));
								boolean amount_Payable_flag = !(amountPayablePrevious.contains("NA") || amountPayablePrevious.contains("null")) && (amount_Payable_deletion == (net_Amount_deletion - coupon_Amount_deletion));
								boolean treatment_Deleted_Amount_flag = !(treatment_Deleted_Amount == 0);
								Assert.assertTrue(correctionInvoice_flag && correctionDate_flag && correctionAction_flag && couponAdjustmentNotes_flag && unit_cost_flag && source_flag && net_Amount_flag && coupon_Amount_flag && coupon_basis_flag && amount_Payable_flag && treatment_Deleted_Amount_flag);
							} else {
								continue;
							}
						}
					}
				}
			}
		}else {
			Assert.fail();
		}
	}
	//checking notes fields at invoice view

	public void noteBehaviorViewModal(){
		if(notesRow.size()==0){
			boolean flag1=noteHeader.get(0).getText().contains("Notes")&&checkedWebElementDisplayed(noteTextFields)&&checkedWebElementDisplayed(maxCharMsg)&&noteTextFields.getAttribute("placeholder").contains("enter invoice notes");
			Assert.assertTrue(flag1);
			saveBtnViewModal.click();
			Assert.assertTrue(flag1&&checkedWebElementDisplayed(errorNoteMsg));
		}else if(noteHeader_entered.size()==1){
			boolean flag1=noteHeader_entered.get(0).getText().contains("Notes and Notes History")&&checkedWebElementDisplayed(noteTextFields)&&checkedWebElementDisplayed(maxCharMsg)&&noteTextFields.getAttribute("placeholder").contains("enter invoice notes");
			for (WebElement web:notesRow) {
				List<WebElement> cells = web.findElements(By.tagName("td"));
				String noteCreatedDate = cells.get(0).getText();
				String noteCreatedBy = cells.get(1).getText();
				String notesNotes = cells.get(2).getText();
				boolean notesColumn_flag=checkedWebElementDisplayed(notesDateColumn)&&checkedWebElementDisplayed(notesCreatedByColumn)&&checkedWebElementDisplayed(notesNotesColumn);
				boolean noteCreatedDate_flag =!(noteCreatedDate.contains("NA") || noteCreatedDate.contains("null"));
				boolean noteCreatedBy_flag = !(noteCreatedBy.contains("NA") || noteCreatedBy.contains("null"));
				boolean notesNotes_flag = !(notesNotes.contains("NA") || notesNotes.contains("null"));
				Assert.assertTrue(flag1&&notesColumn_flag&&noteCreatedDate_flag&&noteCreatedBy_flag&&notesNotes_flag);
			}
		}else{
			Assert.fail();
		}
	}
	//function for coupon modal
	public void checkPatientNameCouponModal(String patientName){
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(patientNameCoupon);
		String[] split=patientNameCoupon.getText().split(":");
		Assert.assertTrue(split[0].contains(patientName)&&!(split[1].contains("NA")||split[1].contains("null")));
	}
	//open the coupon modal
	public void openCouponModal(String invoiceCreatedDate){
		loginPage.waitForPageLoad();
		try {
			WebElement web = loginPage.getDriver().findElement(By.xpath("//td[contains(text(),'" + invoiceCreatedDate + "')]/following-sibling::td/a[contains(@data-ng-click,'couponView')]"));
			loginPage.waitForElementToBeClickable(web);
			web.click();
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	//open delete modal
	public void openDeleteModal(String invoiceCreatedDate){
		loginPage.waitForPageLoad();
		try {
			WebElement web = loginPage.getDriver().findElement(By.xpath("//td[contains(text(),'" + invoiceCreatedDate + "')]/following-sibling::td/a[contains(@data-ng-click,'deleteInvoice')]"));
			loginPage.waitForElementToBeClickable(web);
			web.click();
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	//close the coupon modal
	public void closeModal(){
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(closeBtnBtn);
		try {
			closeBtnBtn.click();
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	//header of coupon popup
	public void headerCouponPopup(){
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(headerCouponModal);
		Assert.assertTrue(checkedWebElementDisplayed(headerCouponModal)&&checkedWebElementDisplayed(headerInfo));
	}
	//invoice Number at coupon popup
	public void invoiceNumberInvoiceDate(String invoiceCreatedDate){
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(invoiceDateCouponModal);
		Assert.assertTrue(invoiceDateCouponModal.getText().contains(invoiceCreatedDate)&&!(invoiceNoCouponModal.getText().contains("NA")||invoiceNoCouponModal.getText().contains("null")));
	}
	//total amount due in coupon modal
	public void totalDueCouponModal(){
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(remainingAmountDueCouponModal);
		try {
			long amountDueCouponModal = NumberFormat.getNumberInstance(Locale.US).parse(remainingAmountDueCouponModal.getText()).intValue();
			Assert.assertEquals(amountDueCouponModal,amountPayableCouponTabular());
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	//amount payable in coupon modal
	public long amountPayableCouponTabular(){
		loginPage.waitForPageLoad();
		long amountPayCouponModal = 0,amountPayCouponModal1=0;
		// And iterate over them, getting the cells
		loginPage.visibilityOfListLocated(rowTreatmentColumn);
		for (WebElement row : rowTreatmentColumn) {
			List<WebElement> cells = row.findElements(By.tagName("td"));
			String str = cells.get(6).getText().trim();
			String[] str1 = str.split("\\.");
			try {
				amountPayCouponModal = NumberFormat.getNumberInstance(Locale.US).parse(str1[0]).intValue();
				amountPayCouponModal1=amountPayCouponModal1+amountPayCouponModal;
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return amountPayCouponModal1;
	}
	//treatment name and it all prices at coupon modal
	public void treatmentTable(String coupon,long amount){
		//checking the column name at coupon popup
		boolean flag = checkedWebElementDisplayed(treatmentCouponModal) && checkedWebElementDisplayed(sourceCouponColumn) && checkedWebElementDisplayed(treatmentCouponColumn) && checkedWebElementDisplayed(unitCostCouponColumn) && checkedWebElementDisplayed(netAmountCouponColumn)
				&& checkedWebElementDisplayed(typeCouponColumn) && checkedWebElementDisplayed(cpnValCouponColumn) && checkedWebElementDisplayed(AmtPayCouponColumn) && checkedWebElementDisplayed(couponBasisCouponColumn);
		if(flag){
			//iterating the row at coupon popup
			loginPage.visibilityOfListLocated(rowTreatmentColumn);
			for (WebElement row:rowTreatmentColumn) {
				List<WebElement> cells = row.findElements(By.tagName("td"));
				//fetching source name from the table
				String source = cells.get(0).getText();
				//fetching treatment name from the table
				String treatmentName = cells.get(1).getText();
				//fetching unit cost from the table
				String[] unitCost = cells.get(2).getText().split("\\.");
				//fetching net amount from the table
				String[] netAmount = cells.get(3).getText().split("\\.");
				//selecting the coupon type amount or percentage
				WebElement couponType = loginPage.getDriver().findElement(By.xpath("//td[contains(text(),'"+treatmentName+"')]/following-sibling::td//select[@id='Type']"));
				loginPage.selectFromDropDownByVisibleText(couponType,coupon);
				//entering the value in the text fileds
				WebElement couponValue=loginPage.getDriver().findElement(By.xpath("//td[contains(text(),'"+treatmentName+"')]/following-sibling::td//input[contains(@ng-change,'calculateAmountPayPerTreatmen')]"));
				couponValue.clear();
				couponValue.sendKeys(Long.toString(amount));
				//fetching the amount payable from the table after applying the discount
				String[] amountPayable = cells.get(6).getText().split("\\.");
				//selecting coupon type discount popup
				WebElement couponBasis=loginPage.getDriver().findElement(By.xpath("//td[contains(text(),'"+treatmentName+"')]/following-sibling::td//select[@id='basis']"));
				loginPage.selectFromDropDownByIndex(couponBasis,2);
				long unitCostCoupon=0,netAmountCoupon=0,amountPayableCoupon=0;
				try {
					unitCostCoupon = NumberFormat.getNumberInstance(Locale.US).parse(unitCost[0]).intValue();
					netAmountCoupon=NumberFormat.getNumberInstance(Locale.US).parse(netAmount[0]).intValue();
					amountPayableCoupon=NumberFormat.getNumberInstance(Locale.US).parse(amountPayable[0]).intValue();
				} catch (ParseException e) {
					e.printStackTrace();
				}
				//calculating effective amount after appling the coupon
				if(coupon.equalsIgnoreCase("%")) {
					long amount_Pay = (netAmountCoupon * amount) / 100;
					Assert.assertEquals((netAmountCoupon-amount_Pay),amountPayableCoupon);
				}else if(coupon.equalsIgnoreCase("Amt")){
					Assert.assertEquals((netAmountCoupon-amount),amountPayableCoupon);
				}else {
					Assert.fail();
				}
				//validating source and unit cost
				boolean flag1=!(unitCostCoupon==0);
				boolean flag2=!(source.contains("NA")||source.contains("null"));
				Assert.assertTrue(flag1&&flag2);
			}
		}else {
			Assert.fail();
		}
	}
	//treatment name and it all prices at coupon modal
	public void treatmentTableAfterCouponApplied(String coupon,long amount){
		//checking the column name at coupon popup
		boolean flag = checkedWebElementDisplayed(treatmentCouponModal) && checkedWebElementDisplayed(sourceCouponColumn) && checkedWebElementDisplayed(treatmentCouponColumn) && checkedWebElementDisplayed(unitCostCouponColumn) && checkedWebElementDisplayed(netAmountCouponColumn)
				&& checkedWebElementDisplayed(typeCouponColumn) && checkedWebElementDisplayed(cpnValCouponColumn) && checkedWebElementDisplayed(AmtPayCouponColumn) && checkedWebElementDisplayed(couponBasisCouponColumn);
		if(flag){
			//iterating the row at coupon popup
			loginPage.visibilityOfListLocated(rowTreatmentColumn);
			for (WebElement row:rowTreatmentColumn) {
				List<WebElement> cells = row.findElements(By.tagName("td"));
				//fetching source name from the table
				String source = cells.get(0).getText();
				//fetching treatment name from the table
				String treatmentName = cells.get(1).getText();
				//fetching unit cost from the table
				String[] unitCost = cells.get(2).getText().split("\\.");
				//fetching net amount from the table
				String[] netAmount = cells.get(3).getText().split("\\.");
				//selecting the coupon type amount or percentage
				WebElement couponType = loginPage.getDriver().findElement(By.xpath("//td[contains(text(),'"+treatmentName+"')]/following-sibling::td//select[@id='Type']"));
				loginPage.selectFromDropDownByVisibleText(couponType,coupon);
				//entering the value in the text fileds
				WebElement couponValue=loginPage.getDriver().findElement(By.xpath("//td[contains(text(),'"+treatmentName+"')]/following-sibling::td//input[contains(@ng-change,'calculateAmountPayPerTreatmen')]"));
				//fetching the amount payable from the table after applying the discount
				String[] amountPayable = cells.get(6).getText().split("\\.");
				//selecting coupon type discount popup
				WebElement couponBasis=loginPage.getDriver().findElement(By.xpath("//td[contains(text(),'"+treatmentName+"')]/following-sibling::td//select[@id='basis']"));
				loginPage.selectFromDropDownByIndex(couponBasis,2);
				long unitCostCoupon=0,netAmountCoupon=0,amountPayableCoupon=0;
				try {
					unitCostCoupon = NumberFormat.getNumberInstance(Locale.US).parse(unitCost[0]).intValue();
					netAmountCoupon=NumberFormat.getNumberInstance(Locale.US).parse(netAmount[0]).intValue();
					amountPayableCoupon=NumberFormat.getNumberInstance(Locale.US).parse(amountPayable[0]).intValue();
				} catch (ParseException e) {
					e.printStackTrace();
				}
				//calculating effective amount after appling the coupon
				if(coupon.equalsIgnoreCase("%")) {
					long amount_Pay = (netAmountCoupon * amount) / 100;
					Assert.assertEquals((netAmountCoupon-amount_Pay),amountPayableCoupon);
				}else if(coupon.equalsIgnoreCase("Amt")){
					Assert.assertEquals((netAmountCoupon-amount),amountPayableCoupon);
				}else {
					Assert.fail();
				}
				//validating source and unit cost
				boolean flag1=!(unitCostCoupon==0);
				boolean flag2=!(source.contains("NA")||source.contains("null"));
				Assert.assertTrue(flag1&&flag2);
			}
		}else {
			Assert.fail();
		}
	}
	//save the coupon modal details
	public void saveModalDetails(){
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(saveBtnModal);
		try {
			saveBtnModal.click();
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	//validating the message we tap on save button without changing any data
	public void withoutChangeCouponMsg(){
		loginPage.waitForPageLoad();
		try {
			Thread.sleep(1000);
			Assert.assertTrue(checkedWebElementDisplayed(msgWithoutChanges));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	//validating the message we tap on save button without changing any data
	public void updateCouponValueCouponBasAddCouponAdjustmentNotes(){
		loginPage.waitForPageLoad();
		try {
			Thread.sleep(1000);
			Assert.assertTrue(checkedWebElementDisplayed(updateCouponValueCouponBasAddCouponAdjustmentNotes));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	//checking mandatory web element coupon adjustment notes and entering the notes
	public void couponAdjustmentNotes(String adjustment_notes){
		loginPage.waitForPageLoad();
		if(checkedWebElementDisplayed(couponAdjustmentNotes)&&checkedWebElementDisplayed(couponNoteTextFields)&&checkedWebElementDisplayed(couponNoteTextFields)&&couponNoteTextFields.getAttribute("placeholder").contains("enter notes")){
			couponNoteTextFields.sendKeys(adjustment_notes);
		}else {
			Assert.fail();
		}
	}
	//function for coupon modal
	public void checkPatientNameDeleteModal(String patientName){
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(patientNameDelete);
		String[] split=patientNameDelete.getText().split(":");
		Assert.assertTrue(split[0].contains(patientName)&&!(split[1].contains("NA")||split[1].contains("null")));
	}
	public void checkHeaderDeleteModal(){
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(headerDelete);
		Assert.assertTrue(checkedWebElementDisplayed(headerDelete)&&checkedWebElementDisplayed(headerInfoDeleteModal));
	}
	//amount payable in Delete modal
	public long amountDueDeleteTabular(){
		loginPage.waitForPageLoad();
		long amountPayCouponModal = 0,amountPayCouponModal1=0;
		// And iterate over them, getting the cells
		loginPage.visibilityOfListLocated(rowDeleteModal);
		for (WebElement row : rowDeleteModal) {
			List<WebElement> cells = row.findElements(By.tagName("td"));
			String str = cells.get(7).getText().trim();
			String[] str1 = str.split("\\.");
			try {
				amountPayCouponModal = NumberFormat.getNumberInstance(Locale.US).parse(str1[0]).intValue();
				amountPayCouponModal1=amountPayCouponModal1+amountPayCouponModal;
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return amountPayCouponModal1;
	}
	//total amount due in coupon modal
	public void totalDueDeleteModal(){
		loginPage.waitForPageLoad();
		try {
			long amountDueCouponModal = NumberFormat.getNumberInstance(Locale.US).parse(totalDueInvoiceDeleteModal.getText()).intValue();
			Assert.assertEquals(amountDueCouponModal,amountDueDeleteTabular());
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	/*Checking invoices delete reason dropdown at invoice delete modal*/
	public void invoiceDeleteReason(String defaultInvoiceStatusSelected) {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(reasonDrpDwnHeader);
		if(checkedWebElementDisplayed(reasonDrpDwnHeader)&&checkedWebElementDisplayed(headerDeletionReason)) {
			boolean flag1, flag2, match = false;
			flag1 = checkedWebElementDisplayed(reasonDrpdwn) && loginPage.firstSelectedOption(reasonDrpdwn).contains(defaultInvoiceStatusSelected);
			String[] exp = {"Select Reasons", "Change in treatment plan", "Incorrect treatment plan", "Invoice cancel", "Receipt delete reasons", "Others"};
			Select select = new Select(reasonDrpdwn);
			List<WebElement> options = select.getOptions();
			for (WebElement we : options) {
				for (int i = 0; i < exp.length; i++) {
					if (flag2 = we.getText().equals(exp[i])) {
						match = flag1 && flag2;
					}
				}
				Assert.assertTrue(match);
			}
		}else{
			Assert.fail();
		}
	}
	//selecting treatment in delete modal for delete
	public void selectTreatment(String treatment){
		loginPage.waitForElementToBeClickable(invoiceDeleteCheckbox);
		boolean flag=checkedWebElementDisplayed(invoiceDeleteCheckbox)&&checkedWebElementDisplayed(nameColumnDeleteModal)&&checkedWebElementDisplayed(unitCostColumnDeleteModal)&&checkedWebElementDisplayed(sourceColumnDeleteModal)&&checkedWebElementDisplayed(netAmountColumnDeleteModal)&&checkedWebElementDisplayed(cpnAmountColumnDeleteModal)&&checkedWebElementDisplayed(cpnBasisColumnDeleteModal)&&checkedWebElementDisplayed(amountPayColumnDeleteModal);
		//deleting the invoice
		if(treatment.equalsIgnoreCase("all")){
			if(flag){
				invoiceDeleteCheckbox.click();
			}else {
				Assert.fail();
			}
		}else{
			//iterating the treatment row of delete modal
			for (WebElement row:rowDeleteModal) {
				List<WebElement> cells = row.findElements(By.tagName("td"));
				//fetching the treatment name
				String treatmentName = cells.get(1).getText();
				//fetching the checkbox
				WebElement checkbox = loginPage.getDriver().findElement(By.xpath("//td[contains(text(),'" + treatmentName + "')]/preceding-sibling::td//label[@class='txtIndent noMrgn topLeft']"));
				//fetching the unit cost
				String[] unitCost = cells.get(2).getText().split("\\.");
				//fetching the source number
				String source = cells.get(3).getText();
				//fetching the net amount
				String[] netAmount = cells.get(4).getText().split("\\.");
				//fetching the coupon amount
				String[] cpnAmount = cells.get(5).getText().split("\\.");
				//fetching the coupon basis text
				String couponBasis = cells.get(6).getText();
				//fetching the amount payable
				String[] amountPayable = cells.get(7).getText().split("\\.");
				long unit_Cost=0,net_Amount=0,cpn_Amount=0,amount_Payable=0;
				try {
					unit_Cost = NumberFormat.getNumberInstance(Locale.US).parse(unitCost[0]).intValue();
					net_Amount = NumberFormat.getNumberInstance(Locale.US).parse(netAmount[0]).intValue();
					cpn_Amount = NumberFormat.getNumberInstance(Locale.US).parse(cpnAmount[0]).intValue();
					amount_Payable = NumberFormat.getNumberInstance(Locale.US).parse(amountPayable[0]).intValue();
				} catch (ParseException e) {
					e.printStackTrace();
				}
				boolean flag1=!(treatmentName.contains("NA")||treatmentName.contains("null"));
				boolean flag2=checkedWebElementDisplayed(checkbox);
				boolean flag3=!(source.contains("NA")||source.contains("null"));
				boolean flag4=!(unit_Cost==0);
				boolean flag5=(net_Amount==(cpn_Amount+amount_Payable));
				boolean flag6=(cpn_Amount==(net_Amount-amount_Payable));
				boolean flag7=(amount_Payable==(net_Amount-cpn_Amount));
				boolean flag8=!(couponBasis.contains("NA")||couponBasis.contains("null"));
				//selecting the treatment
				if(flag&&flag1&&flag2&&flag3&&flag4&&flag5&&flag6&&flag7&&flag8&&treatmentName.contains(treatment)){
					checkbox.click();
					break;
				}else {
					Assert.fail();
				}
			}
		}
	}

	//error if no reason is selected
	public void errorNoReasonSelected(){
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(reasonErrorMsg);
		Assert.assertTrue(checkedWebElementDisplayed(reasonErrorMsg));
	}
	//error message if treatment
	public void errorMsgTreatmentNotSelected(){
		loginPage.waitForPageLoad();
		try {
			Thread.sleep(1000);
			Assert.assertTrue(checkedWebElementDisplayed(msgTreatmentNotSelected));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	//select reason for deleteing the treatment
	public void selectDeleteReason(String reason){
		loginPage.waitForPageLoad();
		loginPage.selectFromDropDownByVisibleText(reasonDrpdwn,reason);
	}

	//validating correction notes at invoice listing
	public void correctionNotes(String invoiceCreatedDate,String correctionNotes){
		loginPage.waitForPageLoad();
		WebElement web = loginPage.getDriver().findElement(By.xpath("//td[contains(text(),'" + invoiceCreatedDate + "')]/following-sibling::td[contains(text(),'" + correctionNotes + "')]"));
		loginPage.waitForElementToBeClickable(web);
		Assert.assertTrue(checkedWebElementDisplayed(web));
	}

	//validating invoice deleted and checked after selecting the show details button
	public void invoiceDeleted(String invoiceCreatedDate){
		loginPage.waitForPageLoad();
		WebElement web = loginPage.getDriver().findElement(By.xpath("//td[contains(text(),'" + invoiceCreatedDate + "')]/following-sibling::td[contains(@class,'deleted')]"));
		loginPage.waitForElementToBeClickable(web);
		Assert.assertTrue(checkedWebElementDisplayed(web));
	}

	//validated all cases after delete at invoice listing like coupon adjusted,treatment deleted,invoice deleted and both treatment deleted and invoice deleted
	public void negativeInvoiceAfterDelete(String invoiceCreatedDate){
		loginPage.waitForPageLoad();
		WebElement invoiceDelete = loginPage.getDriver().findElement(By.xpath("//td[contains(text(),'" + invoiceCreatedDate + "')]/following-sibling::td[contains(text(),'Invoice Deleted')]"));
		WebElement invoiceDeleteCouponAdjusted = loginPage.getDriver().findElement(By.xpath("//td[contains(text(),'" + invoiceCreatedDate + "')]/following-sibling::td[contains(text(),'Both trt deleted and Cpn. value adjusted')]"));
		List<WebElement> couponAdjusted = loginPage.getDriver().findElements(By.xpath("//td[contains(text(),'" + invoiceCreatedDate + "')]/following-sibling::td[contains(text(),'Cpn. value Adjusted')]"));
		WebElement treatmentDeleted = loginPage.getDriver().findElement(By.xpath("//td[contains(text(),'" + invoiceCreatedDate + "')]/following-sibling::td[contains(text(),'Treatment Deleted')]"));
		Assert.assertTrue(checkedWebElementDisplayed(invoiceDelete)&&checkedWebElementDisplayed(treatmentDeleted)&&checkedWebElementDisplayed(invoiceDeleteCouponAdjusted)&&(couponAdjusted.size()==2));
	}
	private boolean checkedWebElementDisplayed(WebElement webElement) {
		return (webElement.isDisplayed());
	}
}
