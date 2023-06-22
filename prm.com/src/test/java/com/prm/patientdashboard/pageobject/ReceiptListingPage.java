package com.prm.patientdashboard.pageobject;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import com.prm.pageobjects.utils.ExtentReport;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import com.prm.pageobjects.utils.PCDriver;
import com.relevantcodes.extentreports.LogStatus;

public class ReceiptListingPage {

	private PCDriver loginPage;
	private ExtentReport extentReport;

	public ReceiptListingPage(PCDriver loginPage,ExtentReport extentReport) {
		this.loginPage = loginPage;
		this.extentReport=extentReport;
		PageFactory.initElements(loginPage.getDriver(), this);
	}

	/*Receipt Listing WebElements*/
	@FindBy(id = "urgentPatientNamePid")
	private WebElement patientNameReceiptListing;
	@FindBy(xpath = "//div[@id='noRecordFound']//i[contains(text(),'No Record Found')]")
	private WebElement NoRecordFoundMsg;
	@FindBy(xpath="//h1[@class='btmTitle pull-left']")
	private WebElement headerReceiptListing;
	@FindBy(id = "sendReceipts")
	private WebElement sendReceiptBtn;
	@FindBy(css	= "span[class='cmnicons srch']")
	private WebElement searchBtn;
	@FindBy(id = "fromDate")
	private WebElement fromDateField;
	@FindBy(id = "toDate")
	private WebElement toDateField;
	@FindBy(xpath="//span[text()='Show Details']")
	private WebElement showDetailsBtn;
	@FindBy(xpath = "//div[@class='amtDueAdv']//b[text()='Amount Due Rs.']")
	private WebElement AmountDueText;
	@FindBy(xpath= "//b[text()='Amount Due Rs.']/following-sibling::span[@id='totaldueamount']")
	private WebElement dueAmount;
	@FindBy(xpath = "//div[@class='amtDueAdv']//b[text()='Advance Rs.']")
	private WebElement AdvanceAmountText;
	@FindBy(xpath= "//b[text()='Advance Rs.']/following-sibling::span[@id='totalAdvanceAmount']")
	private WebElement advanceAmount;
	@FindBy(xpath="//table[@id='receiptsListTable']/tbody/tr")
	private List<WebElement> rowTreatmentColumn;
	@FindBy(xpath= "//table[@id='receiptsListTable']//th[text()='Date']")
	private WebElement dateHead;
	@FindBy(xpath= "//table[@id='receiptsListTable']//th[contains(text(),'Receipt')]")
	private WebElement receiptHead;
	@FindBy(xpath= "//table[@id='receiptsListTable']//th[contains(text(),'Invoice')]")
	private WebElement invoiceHead;
	@FindBy(xpath= "//table[@id='receiptsListTable']//th[text()='Amount Paid']")
	private WebElement amountPaidHead;
	@FindBy(xpath= "//table[@id='receiptsListTable']//th[text()='Payment Method']")
	private WebElement paymentMethodHead;
	@FindBy(xpath= "//table[@id='receiptsListTable']//th[text()='Void Ptr Id/Status']")
	private WebElement ptrIdAndStatusHead;
	@FindBy(xpath= "//table[@id='receiptsListTable']//th[text()='Doctor']")
	private WebElement doctorHead;
	@FindBy(xpath= "//table[@id='receiptsListTable']//th[text()='Action']")
	private WebElement actionHead;
	@FindBy(xpath = "//div[@id='receiptView']//span[contains(@class,'cmnicons cncl-mdl')]")
	private WebElement closeViewModal;
	//web element of Receipt view modal
	@FindBy(xpath = "//h4[text()='Receipt Details']")
	private WebElement receiptHeaderInView;
	@FindBy(id = "urgentPatientName")
	private WebElement patientNameViewModal;
	@FindBy(id = "urgentPatientPID")
	private WebElement patientIdViewModal;
	@FindBy(xpath = "//div[contains(text(),'Info')]")
	private WebElement infoHeadInView;
	@FindBy(xpath = "//b[text()='Receipt no.']")
	private WebElement receiptNoHeadInView;
	@FindBy(xpath = "//b[text()='Receipt Date']")
	private WebElement receiptDateHeadInView;
	@FindBy(xpath = "//div[contains(text(),'Payment Details')]")
	private WebElement paymentDetailsHeadInView;
	@FindBy(xpath = "//b[text()='Amount Received']")
	private WebElement amountReceivedHeadInView;
	@FindBy(xpath = "//b[text()='Mode Of Payment']")
	private WebElement modePaymentHeadInView;
	@FindBy(xpath = "//b[text()='Received As']")
	private WebElement receivedAsHeadInView;
	@FindBy(xpath = "//div[contains(text(),'Notes')]")
	private WebElement notesHeadInView;
	@FindBy(xpath = "//b[text()='Receipt no.']/../following-sibling::span")
	private WebElement receiptNumViewModal;
	@FindBy(xpath = "//b[text()='Receipt Date']/../following-sibling::span")
	private WebElement receiptDate;
	@FindBy(xpath = "//b[text()='Amount Received']/../following-sibling::div")
	private WebElement amountReceive;
	@FindBy(xpath = "//b[text()='Mode Of Payment']/../following-sibling::div")
	private WebElement modePayment;
	@FindBy(xpath = "//b[text()='Received As']/../following-sibling::div")
	private WebElement receivedAs;
	@FindBy(xpath = "//div[contains(text(),'Notes')]/following-sibling::div/div[@id='receiptPopUpNotes']")
	private WebElement notes;
	/*Delete Pop up */
	@FindBy(xpath = "//div[text()='Delete Receipt']")
	private WebElement deleteReasonHead;
	@FindBy(id = "deleteSelect")
	private WebElement reasonDrpDwn;
	@FindBy(xpath="//button[contains(@class,'yesDeleteButton')]//span[@class='cmnicons sv-mdl']")
	private WebElement reasonSaveBtn;
	@FindBy(xpath = "//div[contains(text(),'Receipt deleted successfully!')]")
	private WebElement successfullMsg;

	/*---------Functions---------------*/
	/*checking the patient name and patient id is not null*/
	public void verifyPatientName(String patientName) {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(patientNameReceiptListing);
		String str = patientNameReceiptListing.getText();
		String[] split = str.split(" : ");
		boolean flag = split[0].contains(patientName) && !(split[1].equals("NA") || split[1].equals("Null"));
		Assert.assertTrue(flag);
	}

	/*checking the header of the Receipt Listing page*/
	public void headerOnListPage(String Header) {
		loginPage.waitForPageLoad();
		loginPage.WaitTillElementIsPresent(headerReceiptListing);
		try {
			Thread.sleep(5000);
			Assert.assertTrue(headerReceiptListing.getText().contains(Header));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void noRecordMsgDisplayed() {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(NoRecordFoundMsg);
		Assert.assertTrue(checkedWebElementDisplayed(NoRecordFoundMsg));
	}

	public void checkData() {
		loginPage.waitForPageLoad();
		Assert.assertTrue(checkedWebElementDisplayed(sendReceiptBtn)&& checkedWebElementDisplayed(searchBtn)&& checkedWebElementDisplayed(showDetailsBtn)
				&& checkedWebElementDisplayed(fromDateField)&& fromDateField.getAttribute("placeholder").contains("from (dd-mm-yy)") && checkedWebElementDisplayed(dueAmount)
				&& checkedWebElementDisplayed(toDateField)&& toDateField.getAttribute("placeholder").contains("to (dd-mm-yy)")&& checkedWebElementDisplayed(advanceAmount));
	}

	public void checkReceiptListingTableHead() {
		loginPage.waitForPageLoad();
		Assert.assertTrue(checkedWebElementDisplayed(dateHead)&& checkedWebElementDisplayed(receiptHead)&& checkedWebElementDisplayed(invoiceHead)&& checkedWebElementDisplayed(actionHead)
				&& checkedWebElementDisplayed(amountPaidHead)&& checkedWebElementDisplayed(paymentMethodHead)&& checkedWebElementDisplayed(ptrIdAndStatusHead)&& checkedWebElementDisplayed(doctorHead));
	}

	//check Advance Amount shown on top in Receipt listing Page
	public long checkAdvanceAmount(){
		loginPage.waitForPageLoad();
		long amountAdvance = 0;
		try {
			amountAdvance = NumberFormat.getNumberInstance(Locale.US).parse(advanceAmount.getText()).intValue();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return amountAdvance;
	}

	//check Due Amount shown on top in Receipt listing Page
	private long checkDueAmount(){
		loginPage.waitForPageLoad();
		long amountDue = 0;
		try {
			amountDue = NumberFormat.getNumberInstance(Locale.US).parse(dueAmount.getText()).intValue();
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return amountDue;
	}

	//amount payable in coupon modal
	private long amountPaidTabular(){
		loginPage.waitForPageLoad();
		long amountPaid = 0,amountPaid1=0;
		// And iterate over them, getting the cells
		for (WebElement row : rowTreatmentColumn) {
			List<WebElement> cells = row.findElements(By.tagName("td"));
			String str = cells.get(3).getText().trim();
			String[] str1 = str.split("\\.");
			try {
				amountPaid = NumberFormat.getNumberInstance(Locale.US).parse(str1[0]).intValue();
				amountPaid1=amountPaid1+amountPaid;
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return amountPaid1;
	}

	public void checkTotalAdvance() {
		loginPage.waitForPageLoad();
		Assert.assertEquals(checkAdvanceAmount(), amountPaidTabular());
	}

	/*validating the invoice data*/
	public void receiptDataTable(){
		loginPage.waitForPageLoad();
		// And iterate over them, getting the cells
		for (WebElement row : rowTreatmentColumn) {
			List<WebElement> cells = row.findElements(By.tagName("td"));
			//receipt created date
			String createdDate = cells.get(0).getText();
			//receipt number
			WebElement receiptNumber = cells.get(1).findElement(By.cssSelector("span[class='dis-block']"));
			//invoice number
			String invoiceNumber = cells.get(2).getText().trim();
			//amount paid of receipt
			String[] amountPaid = cells.get(3).getText().trim().split("\\.");
			int amountPaidTabular = 0;
			//Payment Method
			String paymentMethod = cells.get(4).getText();
			String[] paymentMode = null;
			String actual_PaymentMethod = null;
			if(paymentMethod.contains("-")) {
				paymentMode=paymentMethod.split("-");
				Assert.assertTrue(!(paymentMode[0].contains("NA")||paymentMode[0].contains("null"))&&!(paymentMode[1].contains("NA")||paymentMode[1].contains("null")));
			}
			else {
				actual_PaymentMethod=paymentMethod;
				Assert.assertTrue(!(actual_PaymentMethod.contains("NA")||actual_PaymentMethod.contains("null")));
			}
			//	WebElement paymentMethod = cells.get(5).findElement(By.xpath("//span[@class='dis-block']/../following-sibling::td[3]"));
			//coupon amount of receipt
			WebElement ptrIdStatus = cells.get(5).findElement(By.xpath("//span[@class='dis-block']/../following-sibling::td[4]"));
			//doctor name at receipt
			String doctor=cells.get(6).getText().trim();
			try {
				amountPaidTabular = NumberFormat.getNumberInstance(Locale.US).parse(amountPaid[0]).intValue();
			} catch (ParseException e) {
				e.printStackTrace();
			}
			//checking all the possible status of the treatment at invoices listing
			boolean flag1 = !(receiptNumber.getText().contains("null")||receiptNumber.getText().contains("NA"));
			//checking net amount validation
			boolean flag2= !(invoiceNumber.contains("null")||invoiceNumber.contains("NA")||invoiceNumber.contains(" "));
			//checking coupon amount validation
			//checking doctor name
			boolean flag6=!(doctor.contains("NA")||doctor.contains("null"));
			//checking correction notes
			boolean flag8=!(createdDate.contains("NA")||createdDate.contains("null"));
			//checking All data of table
			Assert.assertTrue(flag1 && flag2   && flag6  && flag8);
		}
	}

	//checking the actions button of all the invoices on the basis of invoices created date
	public void actionsButton_ReceiptsCreatedDate(){
		final long MILLIS_PER_DAY = 24 * 60 * 60 * 1000L;
		for (WebElement row : rowTreatmentColumn) {
			List<WebElement> cells = row.findElements(By.tagName("td"));
			//Receipt created date
			String createdDate = cells.get(0).getText();
			//action buttons for every new Receipt
			WebElement viewBtn = cells.get(7).findElement(By.xpath("a[contains(@class,'patientReceiptViewCall')]"));
			WebElement editBtn = cells.get(7).findElement(By.xpath("a[contains(@class,'patientReceiptEditCall')]"));
			WebElement deleteBtn = cells.get(7).findElement(By.xpath("a[contains(@class,'deleteReciept')]"));
			WebElement printBtn = cells.get(7).findElement(By.xpath("a[contains(@class,'patientReceiptPrintCall')]"));
			//formatting the date according our requirement
			SimpleDateFormat simple = new SimpleDateFormat("dd-MM-yy");
			//current date
			String todayDate=null;
			Date date = new Date();
			//Receipt created date in date form
			Date receiptCreatedDate = null;
			try {
				//parsing String to Date
				receiptCreatedDate = simple.parse(createdDate);
				todayDate = simple.format(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			//checking if invoice created date is within 24 hr hour but not	on same day then delete,print,receipt,view only appear
			if((Math.abs(date.getTime() - receiptCreatedDate.getTime()) < MILLIS_PER_DAY)){
				Assert.assertTrue(checkedWebElementDisplayed(viewBtn)&&checkedWebElementDisplayed(printBtn)&&checkedWebElementDisplayed(editBtn)&&checkedWebElementDisplayed(deleteBtn));
				//checking actions button appear after the 24 hr.
			}else{
				Assert.assertTrue(checkedWebElementDisplayed(viewBtn)&&checkedWebElementDisplayed(printBtn)&&!checkedWebElementDisplayed(editBtn)&&!checkedWebElementDisplayed(deleteBtn));
			}
		}
	}

	//open the view modal which having Receipt created today
	public void openViewModal(){
		if(rowTreatmentColumn.size()>0) {
			Random randomGenerator = new Random();
			int index = randomGenerator.nextInt(rowTreatmentColumn.size());
			WebElement item = rowTreatmentColumn.get(index);
			List<WebElement> cells = item.findElements(By.tagName("td"));
			String receiptNumber = cells.get(1).findElement(By.cssSelector("span[class='dis-block']")).getText();
			String receipt_Number_extentReport = receiptNumber + "-Receipt view Test cases executed";
			extentReport.logger.log(LogStatus.PASS, receipt_Number_extentReport);
			WebElement viewBtn = cells.get(7).findElement(By.xpath("a[contains(@class,'patientReceiptViewCall')]"));
			viewBtn.click();
		}else{
			Assert.fail("There is no Receipt");
		}
	}

	//closing the view modal
	public void closeViewModal(){
		loginPage.waitForPageLoad();
		closeViewModal.click();
	}

	//checking patient name and patient id at view modal
	public void patientNamePatientId(String patientName){
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(patientNameViewModal);
		String str = patientNameViewModal.getText();
		String str1=patientIdViewModal.getText();
		boolean flag = str.contains(patientName) && !(str1.equals("NA") || str1.equals(null));
		Assert.assertTrue(flag);
	}

	/*checking the header of the Receipt In view Modal*/
	public void receiptHeaderInView(String Header) {
		loginPage.waitForPageLoad();
		loginPage.WaitTillElementIsPresent(receiptHeaderInView);
		try {
			Thread.sleep(5000);
			Assert.assertTrue(receiptHeaderInView.getText().contains(Header));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void checkDataInView() {
		loginPage.waitForPageLoad();
		Assert.assertTrue(checkedWebElementDisplayed(infoHeadInView)&& checkedWebElementDisplayed(receiptNoHeadInView)&& checkedWebElementDisplayed(receiptDateHeadInView)
				&& checkedWebElementDisplayed(paymentDetailsHeadInView)&&checkedWebElementDisplayed(amountReceivedHeadInView)&&checkedWebElementDisplayed(modePaymentHeadInView)&& checkedWebElementDisplayed(receivedAsHeadInView)
				&& checkedWebElementDisplayed(notesHeadInView));
	}

	//viewData
	public void viewData(){
		loginPage.waitForPageLoad();
		String receipt = receiptNumViewModal.getText().split("/")[1];
		String receipt_Date = receiptDate.getText();
		String amount_Received = amountReceive.getText();
		String mode_Payment = modePayment.getText();
		String received_As = receivedAs.getText();
		//fetching cells from the receipt listing
		String receiptListingDAte = loginPage.getDriver().findElement(By.xpath("//tbody[@id='"+receipt+"']/tr/td[1]")).getText().trim();
		String receiptListing_receipt = loginPage.getDriver().findElement(By.xpath("//tbody[@id='"+receipt+"']/tr/td[2]")).getText().trim();
		String receiptListing_AmountPaid =  loginPage.getDriver().findElement(By.xpath("//tbody[@id='"+receipt+"']/tr/td[4]")).getText().trim();
		String receiptListing_modePayment =  loginPage.getDriver().findElement(By.xpath("//tbody[@id='"+receipt+"']/tr/td[5]")).getText().trim();
		String receiptListing_Doctor =  loginPage.getDriver().findElement(By.xpath("//tbody[@id='"+receipt+"']/tr/td[7]")).getText().trim();
		boolean receipt_flag = receipt.equals(receiptListing_receipt);
		boolean ReceiptDate_flag=receipt_Date.equals(receiptListingDAte);
		boolean amount_flag=amount_Received.equals(receiptListing_AmountPaid);
		boolean modePayment_flag=mode_Payment.equals(receiptListing_modePayment);
		boolean note_flag=!(notes.getText().contains("NA")||notes.getText().contains("null"));
		boolean receivedAs_flag=!(received_As.contains("NA")||received_As.contains("null"));
		boolean doctor_flag=!(receiptListing_Doctor.contains("NA")||receiptListing_Doctor.contains("null"));
		Assert.assertTrue(receipt_flag&&ReceiptDate_flag&&amount_flag&&modePayment_flag&&note_flag&&receivedAs_flag&&doctor_flag);
	}

	public void clickEditBtn(String mode) {
		loginPage.waitForPageLoad();
		WebElement ele = loginPage.getDriver().findElement(By.xpath("//td[contains(text(),'"+mode+"')]/following-sibling::td/a[@class='patientReceiptEditCall btn']"));
		ele.click();
	}

	public void clickDeleteBtn(String mode) {
		loginPage.waitForPageLoad();
		WebElement ele = loginPage.getDriver().findElement(By.xpath("//td[contains(text(),'"+mode+"')]/following-sibling::td/a[@class='deleteReciept btn']"));
		ele.click();
	}

	/*checking all option in Reason Dropdown present in Additional details Panel*/
	public void checkRequireDropDwn() {
		loginPage.waitForPageLoad();
		boolean flag1,flag2, match= false;
		flag1=checkedWebElementDisplayed(reasonDrpDwn);
		String[] exp = { "Select a reason", "Advance refund", "Change in treatment plan", "Incorrect payment received","Incorrect treatment plan","Payment refund","Others"};
		Select select = new Select(reasonDrpDwn);
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

	/*Select the Reason after click on ReTreat button from Reason Dropdown field*/
	public void selectRequire(String reasonValue) {
		loginPage.waitForPageLoad();
		try {
			Thread.sleep(3000);
			loginPage.selectFromDropDownByVisibleText(reasonDrpDwn, reasonValue);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void clickSaveBtnOnReasonPopUp() {
		loginPage.waitForPageLoad();
		reasonSaveBtn.click();
	}

	/*-- Validate the Successfull Msg --*/
	public void verifySuccessfullMessage(){
		loginPage.waitForPageLoad();
		try {
			Thread.sleep(5000);
			loginPage.softAssert().assertTrue(checkedWebElementDisplayed(successfullMsg));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	//Select the Show Details checkbox
	public void clickShowDetails(){
		loginPage.waitForPageLoad();
		showDetailsBtn.click();
	}

	//click the Search button
	public void clickSearchBtn(){
		loginPage.waitForPageLoad();
		searchBtn.click();
	}

	private boolean checkedWebElementDisplayed(WebElement webElement) {
		return (webElement.isDisplayed());
	}


}
