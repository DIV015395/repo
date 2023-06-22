package com.prm.patientdashboard.pageobject;

import com.prm.pageobjects.utils.PCDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class NewReceiptPage {
	private PCDriver loginPage;

	public NewReceiptPage(PCDriver loginPage) {
		this.loginPage = loginPage;
		PageFactory.initElements(loginPage.getDriver(), this);
	}

	/*New Receipts WebElements*/

	@FindBy(xpath="//h1[text()='New Receipts']")
	private WebElement headerNewReceipt;
	@FindBy(xpath = "//h1[text()='New Receipts (Advance)']")
	private WebElement headerAdvanceNewReceipt;
	@FindBy(xpath="//h1[text()='Edit Receipts (Advance)']")
	private WebElement headerEditReceipt;
	@FindBy(xpath="//label[text()='Mode']")
	private WebElement modeHead;
	@FindBy(xpath="//label[text()='Cash']")
	private WebElement cashBtn;
	@FindBy(xpath="//label[text()='Card']")
	private WebElement cardBtn;
	@FindBy(xpath="//label[text()='Cheque']")
	private WebElement chequeBtn;
	@FindBy(xpath="//label[text()='NetBanking']")
	private WebElement netBankingBtn;
	@FindBy(xpath="//label[text()='Wallet']")
	private WebElement walletBtn;
	@FindBy(xpath="//label[text()='Sub Type']")
	private WebElement subTypeHead;
	@FindBy(id="type")
	private WebElement subTypeDrpDwn;
	@FindBy(xpath="//label[text()='Txn Id']")
	private WebElement txnIdHead;
	@FindBy(id="txnId")
	private WebElement txnIdTextField;
	@FindBy(xpath="//label[@for='paymentReferenceId']")
	private WebElement chequeNumberHead;
	@FindBy(id="paymentReferenceId")
	private WebElement chequeNoAndTxnRefIdTextfield;
	@FindBy(xpath="//label[text()='Bank Name']")
	private WebElement bankNameHead;
	@FindBy(id="bankName")
	private WebElement bankNameTextfield;
	@FindBy(xpath="//label[text()='Txn Ref Id']")
	private WebElement txnRefIdHead;
	@FindBy(xpath="//label[text()='Amount']")
	private WebElement amountHead;
	@FindBy(id="amount")
	private WebElement amountTextField;
	@FindBy(xpath="//label[@class='statLabel ng-binding' and contains(text(),'Collect from Advance')]")
	private WebElement collectAdvanceForBtn;
	@FindBy(xpath="//label[text()='Notes:']")
	private WebElement notesHead;
	@FindBy(id="notes")
	private WebElement notesTextField;
	@FindBy(xpath="//a[@class='buttonAdd' and contains(@data-ng-click,'drafts')]")
	private WebElement addButton;
	@FindBy(xpath="//a[@class='buttonAdd' and contains(@data-ng-click,'Advance')]")
	private WebElement addButtonForAdvance;
	@FindBy(xpath = "//span[contains(text(),'Advance amount added successfully!')]")
	private WebElement successfullMsg;
	@FindBy(xpath = "(//i[text()='Save'])[1]")
	private WebElement saveBtn;
	@FindBy(xpath = "//a[contains(@data-ng-click,'advance')]/i[text()='Save & Continue']")
	private WebElement saveContinueBtn;
	@FindBy(xpath = "(//i[text()='Reset'])[1]")
	private WebElement resetBtnForDraft;
	@FindBy(xpath = "(//i[text()='Cancel'])[1]")
	private WebElement cancelBtn;
	@FindBy(xpath = "(//i[text()='Dashboard'])[1]")
	private WebElement dashboardBtn;
	@FindBy(xpath = "//div[contains(@class,'pull-right ng-scope')]")
	private WebElement receiptNoInEdit;

	/*---------------------Validation Message-------*/
	@FindBy(xpath = "//div[text()='Please select mode!']")
	private WebElement errorModeMsg;
	@FindBy(xpath = "//div[text()='Please enter amount!']")
	private WebElement errorAmountMsg;
	@FindBy(xpath = "//div[text()='Please select sub type!']")
	private WebElement errorSubTypeMsg;
	@FindBy(xpath = "//div[contains(text(),'Enter cheque number!')]")
	private WebElement errorChequeNoMsg;
	@FindBy(xpath = "//div[contains(text(),'Enter bank Name!')]")
	private WebElement errorBankNameMsg;
	@FindBy(xpath = "//div[contains(text(),'Enter transaction number!')]")
	private WebElement errorTransactionNoMsg;
	@FindBy(xpath = "//div[contains(text(),'Enter txn id!')]")
	private WebElement errorTxnIdMsg;
	@FindBy(xpath = "//h4[text()='Invoice No.#']")
	private WebElement invoiceNumberColumnName;
	@FindBy(xpath = "//div[text()='Total Amount']")
	private WebElement totalAmountColumnName;
	@FindBy(xpath = "//div[text()='Coupon']")
	private WebElement couponColumnName;
	@FindBy(xpath = "//div[text()='Amt. Pay.']")
	private WebElement amountPayableColumnName;
	@FindBy(xpath = "//div[text()='Amt. Paid']")
	private WebElement amtPaidColumnName;
	@FindBy(xpath = "//div[text()='Amt. Due']")
	private WebElement amountDueColumnName;
	@FindBy(xpath = "//div[text()='Collected']")
	private WebElement collectedAmount;
	@FindBy(xpath = "//div/div[contains(@data-ng-class,'receiptid')]")
	private List<WebElement> invoiceNumber;
	@FindBy(xpath = "//span[text()='Please select invoice(s)!']")
	private WebElement msgInvoiceNotSelected;
	@FindBy(xpath = "//span[text()='You have not applied any amount!']")
	private WebElement paymentNotAppliedMsg;
	//payment details web element
	@FindBy(xpath = "//tr[contains(@data-ng-repeat,'paymentDetailList')]")
	private List<WebElement> paymentDetailsRow;
	@FindBy(xpath = "//h4[text()='Payment Details']")
	private WebElement paymentDetailsHeader;
	@FindBy(xpath = "//tr[contains(@data-ng-repeat,'paymentDetailList')]/../preceding-sibling::thead//th[text()='Mode']")
	private WebElement modeColumnName;
	@FindBy(xpath = "//tr[contains(@data-ng-repeat,'paymentDetailList')]/../preceding-sibling::thead//th[text()='Type']")
	private WebElement typeColumnName;
	@FindBy(xpath = "//tr[contains(@data-ng-repeat,'paymentDetailList')]/../preceding-sibling::thead//th[text()='Amount']")
	private WebElement amountColumnName;
	@FindBy(xpath = "//tr[contains(@data-ng-repeat,'paymentDetailList')]/../preceding-sibling::thead//th[text()='Status']")
	private WebElement statusColumnName;
	@FindBy(xpath = "//tr[contains(@data-ng-repeat,'paymentDetailList')]/../preceding-sibling::thead//th[text()='Other Info']")
	private WebElement otherInfoColumnName;
	@FindBy(xpath = "//tr[contains(@data-ng-repeat,'paymentDetailList')]/../preceding-sibling::thead//th[text()='Notes']")
	private WebElement notesColumnName;
	@FindBy(xpath = "//tr[contains(@data-ng-repeat,'paymentDetailList')]/../preceding-sibling::thead//th[text()='Action']")
	private WebElement actionColumnName;
	@FindBy(xpath = "//div[contains(text(),'Total Receipt(s)')]/span")
	private WebElement totalReceiptsAmount;
	@FindBy(xpath = "//div[contains(text(),'Remaining Amount Due on Invoice(s):- ')]/span")
	private WebElement remainingAmountDueInvoice;
	@FindBy(xpath = "//div[contains(text(),'Remaining Amount Due on Invoice(s)')]/following-sibling::div/a[contains(text(),'Payment')]")
	private WebElement applyPaymentButton;
	@FindBy(xpath = "//a[text()='Apply Payment']")
	private WebElement applyPaymentBTn_invoiceFoot;
	@FindBy(xpath = "//label[contains(text(),'Notes')]/../following-sibling::div/a[contains(@data-ng-click,'drafts')]")
	private WebElement draftButton;
	@FindBy(xpath = "//div[contains(text(),'Total Due On Invoice(s) / Total Collection')]/span[@class='separator_title']")
	private WebElement totalDue_TotalCollection;
	@FindBy(xpath = "//p[contains(text(),'You are about to delete payment mode and its amount adjusted in invoice')]/../following-sibling::div//span[@class='cmnicons yes-mdl']")
	private WebElement yesDeleteReceipt;
	@FindBy(xpath = "//p[contains(text(),'You are about to delete payment mode and its amount adjusted in invoice')]/../following-sibling::div//span[@class='cmnicons cncl-mdl']")
	private WebElement noDeleteReceipt;
	@FindBy(xpath = "//div[contains(text(),'Advance to be created for amount')]/../following-sibling::span/span")
	private WebElement advaceToBeCreated;
	/*-------Functions------------*/

	public void headerOfPage(String Header) {
		loginPage.waitForPageLoad();
		loginPage.WaitTillElementIsPresent(headerNewReceipt);
		try {
			Thread.sleep(5000);
			if(Header.equals("New Receipts"))
				Assert.assertTrue(headerNewReceipt.getText().contains(Header));
			else if(Header.equals("Edit Receipts (Advance)"))
				Assert.assertTrue(headerEditReceipt.getText().contains(Header));
			else
				Assert.assertTrue(headerAdvanceNewReceipt.getText().contains(Header));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void checkActionBtnForAdvance() {
		loginPage.waitForPageLoad();
		Assert.assertTrue(checkedWebElementDisplayed(saveBtn) && checkedWebElementDisplayed(saveContinueBtn) && 
				checkedWebElementDisplayed(cancelBtn) && checkedWebElementDisplayed(dashboardBtn));
	}

	public void checkModesAndTextFields() {
		loginPage.waitForPageLoad();
		Assert.assertTrue(checkedWebElementDisplayed(modeHead) && checkedWebElementDisplayed(cashBtn)&& checkedWebElementDisplayed(cardBtn)
				&& checkedWebElementDisplayed(chequeBtn) && checkedWebElementDisplayed(netBankingBtn) && checkedWebElementDisplayed(walletBtn)
				&& checkedWebElementDisplayed(amountHead) && checkedWebElementDisplayed(amountTextField) && amountTextField.getAttribute("placeholder").contains("enter amount")
				&& checkedWebElementDisplayed(notesHead) && checkedWebElementDisplayed(notesTextField)&& notesTextField.getAttribute("placeholder").contains("notes")&& notesTextField.getAttribute("maxlength").contains("768"));
	}


	public void checkCash() {
		loginPage.waitForPageLoad();
		cashBtn.click();
		Assert.assertTrue(checkedWebElementDisplayed(cashBtn) && checkedWebElementDisplayed(amountHead) && checkedWebElementDisplayed(amountTextField));
	}

	public void checkSubTypeDropDown(String mode) {
		loginPage.waitForPageLoad();
		boolean flag1, flag2, match = false;
		flag1 = checkedWebElementDisplayed(subTypeDrpDwn);
		if(mode.equals("Card")) {
			String[] exp = { "Select Type", "Card", "Bajaj Finance", "EMI", "Card Direct"};
			Select select = new Select(subTypeDrpDwn);
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
		else {
			String[] exp = { "Select Type", "PayTm", "UPI", "Airtel", "PhonePe", "Clove DHP Card" };
			Select select = new Select(subTypeDrpDwn);
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
	}

	public void selectSubType(String subTypeValue) {
		loginPage.waitForPageLoad();
		loginPage.selectFromDropDownByVisibleText(subTypeDrpDwn, subTypeValue);
	}

	public void checkTxnIdOfSubType(String subType) {
		loginPage.waitForPageLoad();
		if(subType.equals("Bajaj Finance")|| subType.equals("Card Direct"))
			Assert.assertTrue(checkedWebElementDisplayed(txnIdHead) && checkedWebElementDisplayed(txnIdTextField)&& txnIdTextField.getAttribute("placeholder").contains("enter txn id"));
		else {
			Assert.assertEquals(0, loginPage.getDriver().findElements(By.xpath("//label[text()='Txn Id']")).size());
			Assert.assertEquals(0, loginPage.getDriver().findElements(By.id("txnId")).size());
		}
	}

	public void enterTxnId(String txnId) {
		loginPage.waitForPageLoad();
		txnIdTextField.clear();
		txnIdTextField.sendKeys(txnId);
	}

	public void checkCheque() {
		loginPage.waitForPageLoad();
		chequeBtn.click();
		Assert.assertTrue(checkedWebElementDisplayed(chequeNumberHead) && checkedWebElementDisplayed(chequeNoAndTxnRefIdTextfield) && chequeNoAndTxnRefIdTextfield.getAttribute("placeholder").contains("enter cheque number")
				&& checkedWebElementDisplayed(bankNameHead) && checkedWebElementDisplayed(bankNameTextfield) && bankNameTextfield.getAttribute("placeholder").contains("enter bank name") && checkedWebElementDisplayed(amountHead)
				&& checkedWebElementDisplayed(amountTextField));
	}

	public void checkNetBanking() {
		loginPage.waitForPageLoad();
		netBankingBtn.click();
		Assert.assertTrue(checkedWebElementDisplayed(txnRefIdHead) && checkedWebElementDisplayed(chequeNoAndTxnRefIdTextfield) && chequeNoAndTxnRefIdTextfield.getAttribute("placeholder").contains("enter transaction id")
				&& checkedWebElementDisplayed(bankNameHead) && checkedWebElementDisplayed(bankNameTextfield) && bankNameTextfield.getAttribute("placeholder").contains("enter bank name") && checkedWebElementDisplayed(amountHead)
				&& checkedWebElementDisplayed(amountTextField));

	}

	public void checkAndSelectAllModes(String mode,String amount,String cheqAndTxnNo,String bankName) {
		loginPage.waitForPageLoad();
		switch(mode) {
		case "Cash":
			cashBtn.click();
			break;
		case "Card":
			cardBtn.click();
			break;
		case "Cheque":
			chequeBtn.click();
			chequeNoAndTxnRefIdTextfield.clear();
			chequeNoAndTxnRefIdTextfield.sendKeys(cheqAndTxnNo);
			bankNameTextfield.clear();
			bankNameTextfield.sendKeys(bankName);
			break;
		case "NetBanking":
			netBankingBtn.click();
			chequeNoAndTxnRefIdTextfield.clear();
			chequeNoAndTxnRefIdTextfield.sendKeys(cheqAndTxnNo);
			bankNameTextfield.clear();
			bankNameTextfield.sendKeys(bankName);
			break;
		case "Wallet":
			walletBtn.click();
			break;
		}
		amountTextField.clear();
		amountTextField.sendKeys(amount);
	}

	public void selectModes(String mode) {
		loginPage.waitForPageLoad();
		switch(mode) {
		case "Cash":
			cashBtn.click();
			break;

		case "Card":
			cardBtn.click();
			break;

		case "Cheque":
			chequeBtn.click();
			break;

		case "NetBanking":
			netBankingBtn.click();
			break;

		case "Wallet":
			walletBtn.click();
			break;
		}

	}
	public void enterNotes(String notes) {
		loginPage.waitForPageLoad();
		notesTextField.clear();
		notesTextField.sendKeys(notes);
	}

	public void checkCollectAdvanceAndAddPaymentBtn() {
		loginPage.waitForPageLoad();
		if(addButton.isDisplayed()) {
			WebElement element = loginPage.getDriver().findElement(By.xpath("//label[@class='statLabel' and contains(text(),'Collect from Advance')]"));
			Assert.assertTrue(checkedWebElementDisplayed(element)&& checkedWebElementDisplayed(addButton));
		}
		else {
			Assert.assertFalse(checkedWebElementDisplayed(addButton));
			WebElement element = loginPage.getDriver().findElement(By.xpath("//label[@class='statLabel ng-binding' and contains(text(),'Collect from Advance')]"));
			Assert.assertTrue(checkedWebElementDisplayed(element));
		}
	}

	public void clickDashboardBtn() {
		loginPage.waitForPageLoad();
		dashboardBtn.click();
	}

	public void clickSaveBtn() {
		loginPage.waitForPageLoad();
		try {
			saveBtn.click();
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void clickSaveContinueBtn() {
		loginPage.waitForPageLoad();
		saveContinueBtn.click();
	}

	/*-- Validate the Successfull Msg --*/
	public void verifySuccessfullMessage(){
		loginPage.waitForPageLoad();
		try {
			Thread.sleep(1000);
			loginPage.softAssert().assertTrue(checkedWebElementDisplayed(successfullMsg));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/*Checking Error Mode Validation Msg---*/
	public void checkedErrorMode(String errorMsg) {
		loginPage.waitForPageLoad();
		Assert.assertTrue(errorModeMsg.getText().contains(errorMsg));
	}

	/*Checking Error Amount Validation Msg---*/
	public void checkedErrorAmount(String errorMsg) {
		loginPage.waitForPageLoad();
		Assert.assertTrue(errorAmountMsg.getText().contains(errorMsg));
	}

	/*Checking Error Sub Type Validation Msg---*/
	public void checkedErrorSubType(String errorMsg) {
		loginPage.waitForPageLoad();
		Assert.assertTrue(errorSubTypeMsg.getText().contains(errorMsg));
	}

	/*Checking Error Cheque No Validation Msg---*/
	public void checkedErrorChequeNo(String errorMsg) {
		loginPage.waitForPageLoad();
		Assert.assertTrue(errorChequeNoMsg.getText().contains(errorMsg));
	}

	/*Checking Error Bank Name Validation Msg---*/
	public void checkedErrorBankName(String errorMsg) {
		loginPage.waitForPageLoad();
		Assert.assertTrue(errorBankNameMsg.getText().contains(errorMsg));
	}

	/*Checking Error TxnRefId Validation Msg---*/
	public void checkedErrorTxnRefId(String errorMsg) {
		loginPage.waitForPageLoad();
		Assert.assertTrue(errorTransactionNoMsg.getText().contains(errorMsg));
	}

	/*Checking Error TxnId Validation Msg---*/
	public void checkedErrorTxnId(String errorMsg) {
		loginPage.waitForPageLoad();
		Assert.assertTrue(errorTxnIdMsg.getText().contains(errorMsg));
	}

	public void checkEditReceiptData(String mode,String subType) {
		loginPage.waitForPageLoad();
		String str = receiptNoInEdit.getText();
		String[] split = str.split("-");
		try {
			String actual_rec=split[0].trim();
			boolean flag1 = actual_rec.contains("Receipt No.:") && !(split[1].equals("NA") || split[1].equals("Null"));
			Assert.assertTrue(flag1);
		}
		catch(ArrayIndexOutOfBoundsException object1) {
		}
		WebElement ele = loginPage.getDriver().findElement(By.xpath("//label[text()='"+mode+"']/preceding-sibling::input"));
		if(mode.equals("Card")||mode.equals("Wallet")) {
			boolean flag2 = ele.isSelected();
			boolean flag3 = loginPage.firstSelectedOption(subTypeDrpDwn).contains(subType);
			Assert.assertTrue(flag2 && flag3);
		}
		else
			Assert.assertTrue(ele.isSelected());
		Assert.assertFalse(amountTextField.isEnabled());
		Assert.assertTrue(notesTextField.getText().contains("NA")||!notesTextField.getText().contains("null"));
	}

	public void invoiceTable(){
		loginPage.waitForPageLoad();
		//checking the invoice prices column name
		boolean flag=checkedWebElementDisplayed(invoiceNumberColumnName)&&checkedWebElementDisplayed(totalAmountColumnName)&&checkedWebElementDisplayed(couponColumnName)&&checkedWebElementDisplayed(amountPayableColumnName)&&checkedWebElementDisplayed(amtPaidColumnName)&&checkedWebElementDisplayed(amountDueColumnName)&&checkedWebElementDisplayed(collectedAmount);
		if (flag){
			//iterating on the basis of invoices present at new receipt
			for (WebElement invoices:invoiceNumber) {
				//fetching the invoice number
				String invoiceNumber=invoices.getText().trim();
				//fetching cells on the basis of the invoice number
				List<WebElement> invoiceDetailsCells = loginPage.getDriver().findElements(By.xpath("//*[text()[contains(.,'" + invoiceNumber + "')]]/following-sibling::div"));
				//fetching total amount for the same invoice
				String[] totalAmountInvoice = invoiceDetailsCells.get(0).getText().split("\\.");
				//fetching total coupon amount for the same invoice
				String[] couponAmountInvoice = invoiceDetailsCells.get(1).getText().split("\\.");
				//fetching total amount payable for the same invoice
				String[] totalAmountPayableInvoice = invoiceDetailsCells.get(2).getText().split("\\.");
				//fetching total amount paid for the same invoice
				String[] totalAmountPaidInvoice = invoiceDetailsCells.get(3).getText().split("\\.");
				//fetching total amount due for the same invoice
				String[] totalAmountDueInvoice = invoiceDetailsCells.get(4).getText().split("\\.");
				//fetching total amount collected for the same invoice
				String[] totalAmountCollectedInvoice = invoiceDetailsCells.get(5).getText().split("\\.");
				long totalAmountInvoice_cost=0,couponAmountInvoice_cost=0,totalAmountPayableInvoice_cost=0,totalAmountPaidInvoice_cost=0,totalAmountDueInvoice_cost=0,totalAmountCollectedInvoice_cost=0;
				try {
					totalAmountInvoice_cost = NumberFormat.getNumberInstance(Locale.US).parse(totalAmountInvoice[0]).intValue();
					couponAmountInvoice_cost = NumberFormat.getNumberInstance(Locale.US).parse(couponAmountInvoice[0]).intValue();
					totalAmountPayableInvoice_cost = NumberFormat.getNumberInstance(Locale.US).parse(totalAmountPayableInvoice[0]).intValue();
					totalAmountPaidInvoice_cost = NumberFormat.getNumberInstance(Locale.US).parse(totalAmountPaidInvoice[0]).intValue();
					totalAmountDueInvoice_cost = NumberFormat.getNumberInstance(Locale.US).parse(totalAmountDueInvoice[0]).intValue();
					totalAmountCollectedInvoice_cost = NumberFormat.getNumberInstance(Locale.US).parse(totalAmountCollectedInvoice[0]).intValue();
				} catch (ParseException e) {
					e.printStackTrace();
				}
				//treatment column name for same invoices
				WebElement sourceColumnName=loginPage.getDriver().findElement(By.xpath("//*[text()[contains(.,'" + invoiceNumber + "')]]/../following-sibling::div//table/thead/tr/th[text()='Source']"));
				WebElement treatmentNameColumnName=loginPage.getDriver().findElement(By.xpath("//*[text()[contains(.,'" + invoiceNumber + "')]]/../following-sibling::div//table/thead/tr/th[text()='Treatment Name']"));
				WebElement treatmentStatusColumnName=loginPage.getDriver().findElement(By.xpath("//*[text()[contains(.,'" + invoiceNumber + "')]]/../following-sibling::div//table/thead/tr/th[text()='Status']"));
				WebElement unitCostColumnName=loginPage.getDriver().findElement(By.xpath("//*[text()[contains(.,'" + invoiceNumber + "')]]/../following-sibling::div//table/thead/tr/th[text()='Unit (INR)']"));
				WebElement netAmtColumnName=loginPage.getDriver().findElement(By.xpath("//*[text()[contains(.,'" + invoiceNumber + "')]]/../following-sibling::div//table/thead/tr/th[text()='Net Amt.']"));
				WebElement couponColumnName=loginPage.getDriver().findElement(By.xpath("//*[text()[contains(.,'" + invoiceNumber + "')]]/../following-sibling::div//table/thead/tr/th[text()='Coupon']"));
				WebElement amtPayColumnName=loginPage.getDriver().findElement(By.xpath("//*[text()[contains(.,'" + invoiceNumber + "')]]/../following-sibling::div//table/thead/tr/th[text()='Amt. Pay.']"));
				WebElement recdAmtColumnName=loginPage.getDriver().findElement(By.xpath("//*[text()[contains(.,'" + invoiceNumber + "')]]/../following-sibling::div//table/thead/tr/th[text()='Recd. Amt.']"));
				WebElement dueColumnName=loginPage.getDriver().findElement(By.xpath("//*[text()[contains(.,'" + invoiceNumber + "')]]/../following-sibling::div//table/thead/tr/th[text()='Due']"));
				WebElement byTrtColumnName=loginPage.getDriver().findElement(By.xpath("//*[text()[contains(.,'" + invoiceNumber + "')]]/../following-sibling::div//table/thead/tr/th[text()='By Trt.']"));
				//variable used for the calculation the amount for the invoices
				long totalAmount=0,totalCouponAmount=0,totalAmountPayable=0,totalAmountPaid=0,totalAmountDue=0,totalAmountCollected = 0;
				//checking the treatment column name at the every invoice
				if(checkedWebElementDisplayed(sourceColumnName)&&checkedWebElementDisplayed(treatmentNameColumnName)&&checkedWebElementDisplayed(treatmentStatusColumnName)&&checkedWebElementDisplayed(unitCostColumnName)
						&&checkedWebElementDisplayed(netAmtColumnName)&&checkedWebElementDisplayed(couponColumnName)&&checkedWebElementDisplayed(amtPayColumnName)&&checkedWebElementDisplayed(recdAmtColumnName)&&checkedWebElementDisplayed(dueColumnName)&&checkedWebElementDisplayed(byTrtColumnName)){
					//treatment row for a particular invoice
					List<WebElement> treatmentRow = loginPage.getDriver().findElements(By.xpath("//*[text()[contains(.,'" + invoiceNumber + "')]]/../following-sibling::div//table/tbody/tr"));
					//iterating for all treatment  for same invoice
					for (WebElement trtRow:treatmentRow) {
						//fetching the cells for the treatment
						List<WebElement> cells = trtRow.findElements(By.tagName("td"));
						//fetching the source from the treatment
						String source = cells.get(0).getText().trim();
						//fetching the  treatment
						String treatment = cells.get(1).getText().trim();
						//fetching the treatment status
						WebElement treatmentStatus = cells.get(2).findElement(By.tagName("span"));
						//fetching unit cost of the treatment
						String[] unitCost = cells.get(3).getText().trim().split("\\.");
						//fetching net amount of the treatment
						String[] netAmount = cells.get(4).getText().trim().split("\\.");
						//fetching coupon amount of the treatment
						String[] couponAmount = cells.get(5).getText().trim().split("\\.");
						//fetching amount payable of the treatment
						String[] amtPay = cells.get(6).getText().trim().split("\\.");
						//fetching recd amount of the treatment
						String[] recdAmt= cells.get(7).getText().trim().split("\\.");
						//fetching due amount of the treatment
						String[] dueAmt = cells.get(8).getText().trim().split("\\.");
						//fetching collected amount by trt
						List<WebElement> web = cells.get(9).findElements(By.xpath("div//input[@name='collpayment']"));
						long notCollected_Amount_cost=0,collectedAmt_cost=0;
						if(web.size()>0){
							try {
								//if payment has been taken against the treatment then getting amount
								String collectedAmt = (String) ((JavascriptExecutor) loginPage.getDriver()).executeScript("return arguments[0].value;", web.get(0));
								collectedAmt_cost = NumberFormat.getNumberInstance(Locale.US).parse(collectedAmt).intValue();
							} catch (ParseException e) {
								e.printStackTrace();
							}
						}else{
							try {
								//if payment has not been taken against treatment
								String[] notCollectedAmount = cells.get(9).getText().split("\\.");
								String notCollected_Amount = notCollectedAmount[0];
								notCollected_Amount_cost = NumberFormat.getNumberInstance(Locale.US).parse(notCollected_Amount).intValue();
							} catch (ParseException e) {
								e.printStackTrace();
							}
						}
						//calculating the the amount in inr
						long unitCost_cost=0,netAmount_cost=0,couponAmount_cost=0,amtPay_cost=0,recdAmt_cost=0,dueAmt_cost=0;
						try {
							unitCost_cost = NumberFormat.getNumberInstance(Locale.US).parse(unitCost[0]).intValue();
							netAmount_cost = NumberFormat.getNumberInstance(Locale.US).parse(netAmount[0]).intValue();
							couponAmount_cost = NumberFormat.getNumberInstance(Locale.US).parse(couponAmount[0]).intValue();
							amtPay_cost = NumberFormat.getNumberInstance(Locale.US).parse(amtPay[0]).intValue();
							recdAmt_cost = NumberFormat.getNumberInstance(Locale.US).parse(recdAmt[0]).intValue();
							dueAmt_cost = NumberFormat.getNumberInstance(Locale.US).parse(dueAmt[0]).intValue();
						} catch (ParseException e) {
							e.printStackTrace();
						}
						//checking source should not "null" and "NA"
						boolean source_flag = !(source.contains("NA") || source.contains("null"));
						//checking treatment should not "null" and "NA"
						boolean treatment_flag = !(treatment.contains("NA") || treatment.contains("null"));
						//checking all possible treatment status
						boolean treatmentStatus_flag = treatmentStatus.getAttribute("class").contains("in-progress") || treatmentStatus.getAttribute("class").contains("start");
						//checking unit cost can not be equal to zero
						boolean unitCost_cost_flag = !(unitCost_cost == 0);
						//checking net amount calculation
						boolean netAmount_flag = (netAmount_cost == (couponAmount_cost + amtPay_cost));
						//checking coupon amount calculation
						boolean couponAmount_flag = (couponAmount_cost == (netAmount_cost - amtPay_cost));
						//checking amount payable  calculation
						boolean amountPayable_flag = (amtPay_cost == (netAmount_cost - couponAmount_cost));
						boolean recdAmt_flag=false,amountDue_flag=false,byTrt_flag=false;
						//checking if payment has been taken against the treatment
						if(web.size()>0){
							recdAmt_flag = (recdAmt_cost == (netAmount_cost - (couponAmount_cost + dueAmt_cost + collectedAmt_cost)));
							amountDue_flag = (dueAmt_cost == (netAmount_cost - (couponAmount_cost + recdAmt_cost + collectedAmt_cost)));
							byTrt_flag = (collectedAmt_cost == (netAmount_cost - (couponAmount_cost + recdAmt_cost + dueAmt_cost)));
						}else{
							//checking if payment has been taken against the treatment
							recdAmt_flag = (recdAmt_cost == (netAmount_cost - (couponAmount_cost + dueAmt_cost + notCollected_Amount_cost)));
							amountDue_flag = (dueAmt_cost == (netAmount_cost - (couponAmount_cost + recdAmt_cost + notCollected_Amount_cost)));
							byTrt_flag = (notCollected_Amount_cost == (netAmount_cost - (couponAmount_cost + recdAmt_cost + dueAmt_cost)));
						}
						//checking all the line items mentioned below
						Assert.assertTrue(source_flag&&treatment_flag&&treatmentStatus_flag&&unitCost_cost_flag&&netAmount_flag&&couponAmount_flag&&amountPayable_flag&&recdAmt_flag&&amountDue_flag&&byTrt_flag);
						//calculating sum of  net Amount of all the treatment present in the invoices
						totalAmount=totalAmount+netAmount_cost;
						//calculating sum of  coupon Amount of all the treatment present in the invoices
						totalCouponAmount=totalCouponAmount+couponAmount_cost;
						//calculating sum of  payable Amount of all the treatment present in the invoices
						totalAmountPayable=totalAmountPayable+amtPay_cost;
						//calculating sum of  paid Amount of all the treatment present in the invoices
						totalAmountPaid=totalAmountPaid+recdAmt_cost;
						//calculating sum of  recd Amount of all the treatment present in the invoices
						totalAmountDue=totalAmountDue+dueAmt_cost;
						//calculating sum of  total collected Amount of all the treatment present in the invoices
						totalAmountCollected=totalAmountCollected+notCollected_Amount_cost+collectedAmt_cost;
					}
				}else {
					Assert.fail();
				}
				//checking total amount of the invoice
				boolean totalAmount_flag=(totalAmount==totalAmountInvoice_cost)&&(totalAmountInvoice_cost==(couponAmountInvoice_cost+totalAmountPayableInvoice_cost));
				//checking total coupon amount of the invoice
				boolean totalCouponAmount_flag=(totalCouponAmount==couponAmountInvoice_cost)&&(couponAmountInvoice_cost==(totalAmountInvoice_cost-totalAmountPayableInvoice_cost));
				//checking total amount payable of the invoice
				boolean totalAmountPayable_flag=(totalAmountPayable==totalAmountPayableInvoice_cost)&&(totalAmountPayableInvoice_cost==(totalAmountInvoice_cost-couponAmountInvoice_cost));
				//checking total amount paid of the invoice
				boolean totalAmountPaid_flag=(totalAmountPaid==totalAmountPaidInvoice_cost)&&(totalAmountPaidInvoice_cost==(totalAmountInvoice_cost-(couponAmountInvoice_cost+totalAmountDueInvoice_cost+totalAmountCollectedInvoice_cost)));
				//checking total amount due of the invoice
				boolean totalAmountDue_flag=(totalAmountDue==totalAmountDueInvoice_cost)&&(totalAmountDueInvoice_cost==(totalAmountInvoice_cost-(couponAmountInvoice_cost+totalAmountPaidInvoice_cost+totalAmountCollectedInvoice_cost)));
				//checking total collected amount of the invoice
				boolean totalAmountCollected_flag=(totalAmountCollected==totalAmountCollectedInvoice_cost)&&(totalAmountCollectedInvoice_cost==(totalAmountInvoice_cost-(couponAmountInvoice_cost+totalAmountPaidInvoice_cost+totalAmountDueInvoice_cost)));
				//validating invoice line item mentioned below
				Assert.assertTrue(totalAmount_flag&&totalCouponAmount_flag&&totalAmountPayable_flag&&totalAmountPaid_flag&&totalAmountDue_flag&&totalAmountCollected_flag);
			}
		}
	}
	//
	public void alertMessages(){
		loginPage.waitForPageLoad();
		//fetching total invoices at the new receipt page
		if(invoiceNumber.size()>0) {
			Random randomGenerator = new Random();
			int index = randomGenerator.nextInt(invoiceNumber.size());
			//getting random invoice from new receipt page
			WebElement invoices = invoiceNumber.get(index);
			String invoice_Number = invoices.getText();
			WebElement web = loginPage.getDriver().findElement(By.xpath("//*[text()[contains(.,'" + invoice_Number + "')]]//input[@type='checkbox']"));
			//checking if only one invoice is present then it must be selected
			if(invoiceNumber.size()==1){
				WebElement webElement = loginPage.getDriver().findElement(By.xpath("//*[text()[contains(.,'"+invoice_Number+"')]]//input[@type='checkbox']"));
				//now invoice selected but there is no payment details and user try save receipt the it will show Payment not applied message
				if (paymentDetailsRow.size()==0&&webElement.isSelected()){
					saveBtn.click();
					try {
						Thread.sleep(1000);
						Assert.assertTrue(checkedWebElementDisplayed(paymentNotAppliedMsg));
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}else {
					Assert.assertTrue(webElement.isSelected());
				}
				//if invoices are more then one
			}else {
				int i = 0;
				//checking total deselected invoices at the new receipt page
				for (WebElement webElement : invoiceNumber) {
					String invoiceNumber = webElement.getText().trim();
					WebElement webElement1 = loginPage.getDriver().findElement(By.xpath("//*[text()[contains(.,'" + invoiceNumber + "')]]//input[@type='checkbox']"));
					if (!webElement1.isSelected()) {
						i++;
					}
				}
				//if all invoices are deselected then try save the receipt it will show an error message please selected the invoice validated
				if(i==invoiceNumber.size()){
					saveBtn.click();
					try {
						Thread.sleep(1000);
						loginPage.executeScript(web);
						Assert.assertTrue(checkedWebElementDisplayed(msgInvoiceNotSelected));
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					//now invoice selected but there is no payment details and user try save receipt the it will show Payment not applied message
					if (paymentDetailsRow.size()==0){
						saveBtn.click();
						try {
							Thread.sleep(1000);
							Assert.assertTrue(checkedWebElementDisplayed(paymentNotAppliedMsg));
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}else {
					Reporter.log("Invoice Selected and payment Applied at the treatment");
				}
			}
		}else{
			Reporter.log("There is no invoice");
		}
	}
	//validating the total payment receipt
	public void totalReceipt_RemainingAmountDue(){
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(totalReceiptsAmount);
		//Parsing the total payment receipt and amount due string to long
		String[] totalAmountReceipt = totalReceiptsAmount.getText().trim().split("\\.");
		String[] remainingAmount = remainingAmountDueInvoice.getText().trim().split("\\.");
		long totalAmountReceipt_cost = Long.parseLong(totalAmountReceipt[0]);
		long remainingAmount_cost = Long.parseLong(remainingAmount[0]);
		//verification of total new receipt amount and calculating the total amount from the table
		//verification of total amount due of all the invoice and  the remaining amount due at payment details
		if(totalAmountReceipt_cost==totalPaymentNewReceipt()&&totalDueInvoices()>totalAmountReceipt_cost){
			Assert.assertEquals(remainingAmount_cost,(totalAmountPayableInvoices()-(totalAmountPaidInvoices()+totalAmountReceipt_cost)));
		}else if(totalAmountReceipt_cost==totalPaymentNewReceipt()&&(totalDueInvoices()<totalAmountReceipt_cost||totalDueInvoices()==totalAmountReceipt_cost)){
			Assert.assertEquals(remainingAmount_cost,0);
		}else {
			Assert.fail();
		}
	}
	//calculating the total payment taken
	private long totalPaymentNewReceipt(){
		loginPage.waitForPageLoad();
		//checking column name of payment details
		boolean flag = checkedWebElementDisplayed(paymentDetailsHeader) && checkedWebElementDisplayed(modeColumnName) && checkedWebElementDisplayed(typeColumnName) && checkedWebElementDisplayed(amountColumnName)
				&& checkedWebElementDisplayed(statusColumnName) && checkedWebElementDisplayed(otherInfoColumnName) && checkedWebElementDisplayed(notesColumnName) && checkedWebElementDisplayed(actionColumnName);
		long totalPayment =0;
		//if payment row and  column name present
		if(paymentDetailsRow.size()>0&&flag){
			//iterating on the basis of the payments row present
			for (WebElement rowPayment:paymentDetailsRow) {
				List<WebElement> cells = rowPayment.findElements(By.tagName("td"));
				String[] amount = cells.get(2).getText().trim().split("\\.");
				long amountPayment =0;
				try{
					amountPayment = NumberFormat.getNumberInstance(Locale.US).parse(amount[0]).intValue();
				} catch (ParseException e) {
					e.printStackTrace();
				}
				totalPayment=totalPayment+amountPayment;
			}
		}
		return totalPayment;
	}
	//total amount due on invoices
	private long totalDueInvoices(){
		loginPage.waitForPageLoad();
		long totalAmountDue =0;
		for (WebElement invoices:invoiceNumber) {
			String invoiceNumber=invoices.getText().trim();
			//fetching cells on the basis of the invoice number
			List<WebElement> invoiceDetailsCells = loginPage.getDriver().findElements(By.xpath("//*[text()[contains(.,'" + invoiceNumber + "')]]/following-sibling::div"));
			//fetching total amount due for the same invoice
			String[] totalAmountDueInvoice = invoiceDetailsCells.get(4).getText().split("\\.");
			long totalAmountDueInvoice_cost=0;
			try {
				totalAmountDueInvoice_cost = NumberFormat.getNumberInstance(Locale.US).parse(totalAmountDueInvoice[0]).intValue();
			}catch (ParseException e){
				e.printStackTrace();
			}
			totalAmountDue = totalAmountDue+totalAmountDueInvoice_cost;
		}
		return totalAmountDue;
	}
	//total amount payable on invoices
	private long totalAmountPayableInvoices(){
		loginPage.waitForPageLoad();
		long totalAmountPayable =0;
		for (WebElement invoices:invoiceNumber) {
			String invoiceNumber=invoices.getText().trim();
			//fetching cells on the basis of the invoice number
			List<WebElement> invoiceDetailsCells = loginPage.getDriver().findElements(By.xpath("//*[text()[contains(.,'" + invoiceNumber + "')]]/following-sibling::div"));
			//fetching total amount due for the same invoice
			String[] totalAmountPayInvoice = invoiceDetailsCells.get(2).getText().split("\\.");
			long amtPay_cost=0;
			try {
				amtPay_cost = NumberFormat.getNumberInstance(Locale.US).parse(totalAmountPayInvoice[0]).intValue();
			}catch (ParseException e){
				e.printStackTrace();
			}
			totalAmountPayable=totalAmountPayable+amtPay_cost;
		}
		return totalAmountPayable;
	}
	//total amount paid on invoices
	private long totalAmountPaidInvoices(){
		loginPage.waitForPageLoad();
		long totalAmountPaid =0;
		for (WebElement invoices:invoiceNumber) {
			String invoiceNumber=invoices.getText().trim();
			//fetching cells on the basis of the invoice number
			List<WebElement> invoiceDetailsCells = loginPage.getDriver().findElements(By.xpath("//*[text()[contains(.,'" + invoiceNumber + "')]]/following-sibling::div"));
			//fetching total amount paid for the same invoice
			String[] totalAmountPaidInvoice = invoiceDetailsCells.get(3).getText().split("\\.");
			long totalAmountPaidInvoice_cost=0;
			try {
				totalAmountPaidInvoice_cost = NumberFormat.getNumberInstance(Locale.US).parse(totalAmountPaidInvoice[0]).intValue();
			}catch (ParseException e){
				e.printStackTrace();
			}
			//calculating sum of  paid Amount of all the treatment present in the invoices
			totalAmountPaid=totalAmountPaid+totalAmountPaidInvoice_cost;
		}
		return totalAmountPaid;
	}
	//total amount collected on invoices
	private long totalAmountCollectedInvoices(){
		loginPage.waitForPageLoad();
		long totalAmountCollected =0;
		for (WebElement invoices:invoiceNumber) {
			String invoiceNumber=invoices.getText().trim();
			//fetching cells on the basis of the invoice number
			List<WebElement> invoiceDetailsCells = loginPage.getDriver().findElements(By.xpath("//*[text()[contains(.,'" + invoiceNumber + "')]]/following-sibling::div"));
			//fetching total amount due for the same invoice
			String[] totalAmountCollectedInvoice = invoiceDetailsCells.get(5).getText().split("\\.");
			long totalAmountCollectedInvoice_cost=0;
			try {
				totalAmountCollectedInvoice_cost = NumberFormat.getNumberInstance(Locale.US).parse(totalAmountCollectedInvoice[0]).intValue();
			}catch (ParseException e){
				e.printStackTrace();
			}
			totalAmountCollected=totalAmountCollected+totalAmountCollectedInvoice_cost;
		}
		return totalAmountCollected;
	}
	//validate apply payment button in payment details and apply payment just below the all invoices
	public void checkApplyPaymentButton(){
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(applyPaymentButton);
		Assert.assertTrue(checkedWebElementDisplayed(applyPaymentButton)&&checkedWebElementDisplayed(applyPaymentBTn_invoiceFoot));
	}
	//clicking on apply button
	public void applyPayment(){
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(applyPaymentButton);
		applyPaymentButton.click();
	}
	//save the draft amount
	public void saveDraftAmount(){
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(draftButton);
		try {
			draftButton.click();
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	//validation of the payment details
	public void paymentDetailsTable(String mode,String amountValue,String bankName,String cheqAndTxnNo){
		loginPage.waitForPageLoad();
		boolean flag = checkedWebElementDisplayed(paymentDetailsHeader) && checkedWebElementDisplayed(modeColumnName) && checkedWebElementDisplayed(typeColumnName) && checkedWebElementDisplayed(amountColumnName)
				&& checkedWebElementDisplayed(statusColumnName) && checkedWebElementDisplayed(otherInfoColumnName) && checkedWebElementDisplayed(notesColumnName) && checkedWebElementDisplayed(actionColumnName);
		if(flag){
			for (WebElement row:paymentDetailsRow) {
				List<WebElement> cells = row.findElements(By.tagName("td"));
				String modePayment = cells.get(0).getText().trim();
				String modeSubType = cells.get(1).getText().trim();
				String[] amount = cells.get(2).getText().trim().split("\\.");
				String status = cells.get(3).getText().trim();
				String otherInfo = cells.get(4).getText().trim();
				String note = cells.get(5).getText().trim();
				WebElement deleteBtn = cells.get(6).findElement(By.xpath("a[contains(@data-ng-click,'deletePaymentMode')]"));
				boolean b = otherInfo.contains(bankName) && otherInfo.contains(cheqAndTxnNo);
				boolean mode_flag,modeSubType_flag,amount_flag,status_flag,otherInfo_flag,note_flag,deleteFlag;
				if(modePayment.equals(mode)) {
					switch (mode) {
						case "Cash":
							mode_flag = modePayment.equals(mode);
							modeSubType_flag = modeSubType.contains("NA");
							amount_flag = (amount[0].equals(amountValue));
							status_flag = status.equals("Collected");
							otherInfo_flag = otherInfo.contains("NA");
							note_flag = !(note.contains("NA") || note.contains("null"));
							deleteFlag = checkedWebElementDisplayed(deleteBtn);
							Assert.assertTrue(mode_flag && modeSubType_flag && amount_flag && status_flag && otherInfo_flag && note_flag && deleteFlag);
							break;
						case "Card":
							mode_flag = modePayment.equals(mode);
							modeSubType_flag = !(modeSubType.contains("NA") || modeSubType.contains("null"));
							amount_flag = (amount[0].equals(amountValue));
							status_flag = status.equals("Processed");
							otherInfo_flag = otherInfo.contains(cheqAndTxnNo);
							note_flag = !(note.contains("NA") || note.contains("null"));
							deleteFlag = checkedWebElementDisplayed(deleteBtn);
							Assert.assertTrue(mode_flag && modeSubType_flag && amount_flag && status_flag && otherInfo_flag && note_flag && deleteFlag);
							break;
						case "Wallet":
							mode_flag = modePayment.equals(mode);
							modeSubType_flag = !(modeSubType.contains("NA") || modeSubType.contains("null"));
							amount_flag = (amount[0].equals(amountValue));
							status_flag = status.equals("Processed");
							otherInfo_flag = otherInfo.contains("NA");
							note_flag = !(note.contains("NA") || note.contains("null"));
							deleteFlag = checkedWebElementDisplayed(deleteBtn);
							Assert.assertTrue(mode_flag && modeSubType_flag && amount_flag && status_flag && otherInfo_flag && note_flag && deleteFlag);
							break;
						case "Cheque":
							mode_flag = modePayment.equals(mode);
							modeSubType_flag = modeSubType.contains("NA");
							amount_flag = (amount[0].equals(amountValue));
							status_flag = status.equals("Collected");
							otherInfo_flag = b;
							note_flag = !(note.contains("NA") || note.contains("null"));
							deleteFlag = checkedWebElementDisplayed(deleteBtn);
							Assert.assertTrue(mode_flag && modeSubType_flag && amount_flag && status_flag && otherInfo_flag && note_flag && deleteFlag);
							break;
						case "NetBanking":
							mode_flag = modePayment.equals(mode);
							modeSubType_flag = modeSubType.contains("NA");
							amount_flag = (amount[0].equals(amountValue));
							status_flag = status.equals("Entered");
							otherInfo_flag = b;
							note_flag = !(note.contains("NA") || note.contains("null"));
							deleteFlag = checkedWebElementDisplayed(deleteBtn);
							Assert.assertTrue(mode_flag && modeSubType_flag && amount_flag && status_flag && otherInfo_flag && note_flag && deleteFlag);
							break;
					}
				}else {
					continue;
				}
			}
		}else{
			Assert.fail();
		}
	}
	//validating amount bottom overall amount due and overall collected
	public void totalDue_totalCollection(){
		loginPage.waitForPageLoad();
		String[] str = totalDue_TotalCollection.getText().trim().split("/");
		String[] totalInvoiceDue = str[0].trim().split("\\.");
		String[] totalCollected = str[1].trim().split("\\.");
		long totalInvoiceDueBottom=0,totalCollectedBottom=0;
		try {
			totalInvoiceDueBottom = NumberFormat.getNumberInstance(Locale.US).parse(totalInvoiceDue[0]).intValue();
			totalCollectedBottom = NumberFormat.getNumberInstance(Locale.US).parse(totalCollected[0]).intValue();
		}catch (ParseException e){
			e.printStackTrace();
		}
		Assert.assertEquals(totalInvoiceDueBottom,totalDueInvoices());
		Assert.assertEquals(totalCollectedBottom,totalAmountCollectedInvoices());
	}
//clicking on the delete button on the basis on mode and amount of the receipt
	public void deleteReceipt(String mode,String amount){
		loginPage.waitForPageLoad();
		WebElement deleteBtn = loginPage.getDriver().findElement(By.xpath("//td[contains(text(),'" + mode + "')]/following-sibling::td[contains(text(),'" + amount + "')]/following-sibling::td//span[@class='actn-icn delete']"));
		deleteBtn.click();
	}
//authenticating to delete the receipt
	public void yesDeleteReceipt(){
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(yesDeleteReceipt);
		try {
			yesDeleteReceipt.click();
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	//Cancel to delete the receipt
	public void noDeleteReceipt(){
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(noDeleteReceipt);
		try {
			noDeleteReceipt.click();
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	//verifying receipt after deleting
	public void checkDeletedReceipt(String mode,String amount){
		loginPage.waitForPageLoad();
		List<WebElement> web = loginPage.getDriver().findElements(By.xpath("//td[contains(text(),'" + mode + "')]/following-sibling::td[contains(text(),'" + amount + "')]/following-sibling::td//span[@class='actn-icn delete']"));
		Assert.assertEquals(web.size(),0);
	}
	//applying payment equal to the remaining amount
	public void addingReceiptEqualRemainingAmount(){
		loginPage.waitForPageLoad();
		String[] remainingAmount = remainingAmountDueInvoice.getText().trim().split("\\.");
		amountTextField.sendKeys(remainingAmount[0]);
		try {
			cashBtn.click();
			draftButton.click();
			Thread.sleep(2500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	//selecting all invoices
	public void selectInvoices(){
		if(invoiceNumber.size()>1) {
			for (WebElement webElement : invoiceNumber) {
				String invoiceNumber = webElement.getText().trim();
				WebElement webElement1 = loginPage.getDriver().findElement(By.xpath("//*[text()[contains(.,'" + invoiceNumber + "')]]//input[@type='checkbox']"));
				if (webElement1.isSelected()) {
					continue;
				} else {
					loginPage.executeScript(webElement1);

				}
			}
		}else {
			for (WebElement webElement : invoiceNumber) {
				String invoiceNumber = webElement.getText().trim();
				WebElement webElement1 = loginPage.getDriver().findElement(By.xpath("//*[text()[contains(.,'" + invoiceNumber + "')]]//input[@type='checkbox']"));
				Assert.assertTrue(webElement1.isSelected());
			}
		}
	}
	//validating if receipt amount is more then the invoice amount then advance receipt should be created for extra amount
	public void advanceCreatedNewReceipt(){
		loginPage.waitForPageLoad();
		String[] amount = advaceToBeCreated.getText().trim().split("\\.");
		String[] totalAmountReceipt = totalReceiptsAmount.getText().trim().split("\\.");
		long totalAmountReceipt_cost = Long.parseLong(totalAmountReceipt[0]);
		long advanceCreatedAmonut=0;
		try {
			advanceCreatedAmonut = NumberFormat.getNumberInstance(Locale.US).parse(amount[0]).intValue();
		}catch (ParseException e){
			e.printStackTrace();
		}
		Assert.assertEquals(advanceCreatedAmonut,(totalAmountReceipt_cost+totalAmountPaidInvoices())-totalAmountPayableInvoices());
	}
	private boolean checkedWebElementDisplayed(WebElement webElement) {
		return (webElement.isDisplayed());
	}
}
