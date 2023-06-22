package com.prm.patientdashboard.pageobject;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import com.prm.pageobjects.utils.PCDriver;

public class WorkDoneHistoryPage {
	private PCDriver loginPage;

	public WorkDoneHistoryPage(PCDriver loginPage) {
		this.loginPage = loginPage;
		PageFactory.initElements(loginPage.getDriver(), this);
	}

	/**
	 * Storing the webelement of the work done history page
	 */
	@FindBy(xpath = "//i[text()='Print']")
	private WebElement print;
	@FindBy(xpath = "//span[contains(@class,'clnsnametxt ng-binding')]")
	private List<WebElement> treatmentStartedDate;
	@FindBy(xpath = "//span[contains(@class,'clnsnametxt ng-binding')]/following-sibling::span[contains(@class,'dis-inline')]")
	private List<WebElement> treatments;
	@FindBy(id="reason")
	private WebElement reasonDrpDwn;
	@FindBy(xpath="//span[@class='cmnicons sv-mdl']")
	private WebElement reasonSaveBtn;
	@FindBy(xpath="//i[text()='Add New']")
	private WebElement addNewBtn;
	@FindBy(xpath = "//span[@class='cmnicons labadd']")
	private WebElement labWorkOrderAddBtn;
	@FindBy(xpath = "//i[text()='Print']")
	private WebElement printBtn;
	@FindBy(xpath = "//form//a[contains(@href,'invoiceList')]")
	private WebElement invoiceListBtn;
	/**
	 * webelement of the follow modal
	 */
	@FindBy(xpath = "//h4[@class='modal-title popup-title']")
	private WebElement followUpModalHeader;
	@FindBy(xpath = "//form[@id='wdHistoryListFollowupModal']//span[@class='cmnicons sv-mdl']")
	private WebElement followUpSaveBtn;
	@FindBy(xpath = "//form[@id='wdHistoryListFollowupModal']//span[@class='cmnicons cncl-mdl']")
	private WebElement followUpModalCloseBtn;
	@FindBy(xpath = "//form[@id='wdHistoryListFollowupModal']//b[text()='Treatment Name']/../following-sibling::div")
	private WebElement treatmentFollowUpModal;
	@FindBy(xpath = "//form[@id='wdHistoryListFollowupModal']//b[text()='Source']/../following-sibling::div")
	private WebElement teethFollowUpModal;
	@FindBy(xpath = "//form[@id='wdHistoryListFollowupModal']//b[text()='Clinic Name']/../following-sibling::div")
	private WebElement clinicNameFollowUpModal;
	@FindBy(xpath = "//form[@id='wdHistoryListFollowupModal']//select[@id='Doctor']")
	private WebElement treatingDoctorFollowUp;
	@FindBy(xpath = "//form[@id='wdHistoryListFollowupModal']//select[@id='Time']")
	private WebElement timeSpentFollowup;
	@FindBy(xpath = "//label[text()='Treating Dentist']")
	private WebElement headerTreatingDentist;
	@FindBy(xpath = "//label[text()='Time Spent']")
	private WebElement headerTimeSpent;
	@FindBy(xpath = "//label[text()='Notes']")
	private WebElement headerNotesFollowup;
	@FindBy(id = "notes")
	private WebElement notesFollowUp;
	@FindBy(xpath = "//span[text()='768 characters left']")
	private WebElement maxCharLimitNotesMsg;
	@FindBy(xpath = "//textarea[@id='notes']/../..//div[text()='Enter Notes']")
	private WebElement errorMsgNote;
	/**
	 * webelement of the re-treat modal
	 */
	@FindBy(xpath = "//form[@id='retreatForm']//h4")
	private WebElement headerRetreatModal;
	@FindBy(xpath = "//form[@id='retreatForm']//span[@class='cmnicons sv-mdl']")
	private WebElement saveRetreatBtn;
	@FindBy(xpath = "//form[@id='retreatForm']//span[@class='cmnicons cncl-mdl']")
	private WebElement closeReTreatBtn;
	@FindBy(xpath = "//form[@id='retreatForm']//label[text()='Treating Dentist']")
	private WebElement HeaderTreatingDentistRetreat;
	@FindBy(xpath = "//form[@id='retreatForm']//select[@id='Doctor']")
	private WebElement treatingDentistRetreat;
	@FindBy(xpath = "//form[@id='retreatForm']//label[text()='Stage']")
	private WebElement headerStageRetreat;
	@FindBy(xpath = "//label[@for='Stage']/..//select[@data-ng-model='value']")
	private WebElement stageDrpDwnRetreat;
	@FindBy(xpath = "//form[@id='retreatForm']//label[text()='Reason']")
	private WebElement headrReasonRetreat;
	@FindBy(xpath = "//form[@id='retreatForm']//select[@id='reason']")
	private WebElement reasonDrpdwnRetreat;
	@FindBy(xpath = "//form[@id='retreatForm']//div[contains(@class,'error')]")
	private WebElement errorMssage;
	@FindBy(xpath = "//form[@id='retreatForm']//label[text()='Notes']")
	private WebElement headerNotesRetreat;
	@FindBy(xpath = "//form[@id='retreatForm']//textarea[@id='notes']")
	private WebElement notesRetreat;
	@FindBy(xpath = "//form[@id='retreatForm']//span[text()='768 characters left']")
	private WebElement maxCharMsgREtreat;

	public void notesRetreat(String notes){
		loginPage.waitForPageLoad();
		if(checkedElementDisplayed(headerNotesRetreat)&&checkedElementDisplayed(maxCharMsgREtreat)&&notesRetreat.getAttribute("placeholder").contains("notes")){
			notesRetreat.sendKeys(notes);
		}
	}

	public void errorMessage(String errorMsg){
		loginPage.waitForPageLoad();
		Assert.assertTrue(errorMssage.getText().contains(errorMsg));
	}

	public void stageDropDownFirstTime(){
		loginPage.waitForPageLoad();
		Assert.assertTrue(checkedElementDisplayed(headerStageRetreat)&&loginPage.firstSelectedOption(stageDrpDwnRetreat).contains("In-Progress"));
	}
	public void selectStage(String stage){
		loginPage.waitForPageLoad();
		loginPage.selectFromDropDownByVisibleText(stageDrpDwnRetreat,stage);
	}

	public void selectReason(String reason){
		loginPage.waitForPageLoad();
		if(checkedElementDisplayed(headrReasonRetreat)&&loginPage.firstSelectedOption(reasonDrpdwnRetreat).contains("Select Reason")){
			loginPage.selectFromDropDownByVisibleText(reasonDrpdwnRetreat,reason);
		}
	}

	public void closeBtnRetreatBtn(){
		loginPage.waitForPageLoad();
		Assert.assertTrue(checkedElementDisplayed(closeReTreatBtn));
	}

	public void saveRetreatDetails(){
		loginPage.waitForPageLoad();
		try {
			saveRetreatBtn.click();
			Thread.sleep(3500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public  void selectTreatingDentistReTreatModal(String treatingDentist){
		loginPage.waitForPageLoad();
		if(checkedElementDisplayed(HeaderTreatingDentistRetreat)&&loginPage.firstSelectedOption(treatingDentistRetreat).equalsIgnoreCase(treatingDentist)){
		}else{
			Assert.fail();
		}
	}

	public void headerReTreatModal(String header){
		loginPage.waitForPageLoad();
		Assert.assertTrue(headerRetreatModal.getText().contains(header));
	}
	public void errorMessageDisplayed(){
		loginPage.waitForPageLoad();
		Assert.assertTrue(checkedElementDisplayed(errorMsgNote));
	}
	public void notesFollowUp(String note){
		loginPage.waitForPageLoad();
		if(checkedElementDisplayed(headerNotesFollowup)&&checkedElementDisplayed(maxCharLimitNotesMsg)&&notesFollowUp.getAttribute("placeholder").contains("notes")){
			notesFollowUp.sendKeys(note);
		}
	}

	public  void selectTreatingDentistFollowUpModal(String treatingDentist){
		loginPage.waitForPageLoad();
		if(checkedElementDisplayed(headerTreatingDentist)&&loginPage.firstSelectedOption(treatingDoctorFollowUp).equalsIgnoreCase(treatingDentist)){
		}else{
			Assert.fail();
		}
	}

	public void selectSpentTime(String spentTime){
		loginPage.waitForPageLoad();
		try {
			Thread.sleep(2000);
			if(checkedElementDisplayed(headerTimeSpent)&&loginPage.firstSelectedOption(timeSpentFollowup).contains("Select Time")){
				loginPage.selectFromDropDownByVisibleText(timeSpentFollowup,spentTime);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public void headerFollowUpModal(String header){
		loginPage.waitForPageLoad();
		Assert.assertTrue(followUpModalHeader.getText().contains(header));
	}

	public void saveFollowUpDetails(){
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(followUpSaveBtn);
		try {
			followUpSaveBtn.click();
			Thread.sleep(3500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void closeFollowUp(){
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(followUpModalCloseBtn);
		followUpModalCloseBtn.click();
	}

	public void closeBtnFollowUpModal(){
		loginPage.waitForPageLoad();
		Assert.assertTrue(checkedElementDisplayed(followUpModalCloseBtn));
	}

	public void treatmentFollowUpModal(String treatment){
		loginPage.waitForPageLoad();
		Assert.assertTrue(treatmentFollowUpModal.getText().contains(treatment));
	}

	public void sourceFollowUpModal(String source){
		loginPage.waitForPageLoad();
		Assert.assertTrue(teethFollowUpModal.getText().contains(source));
	}

	public void clinicNameFollowUpModal(String clinic){
		loginPage.waitForPageLoad();
		Assert.assertTrue(clinicNameFollowUpModal.getText().contains(clinic));
	}


	public void checkedInvoiceListBtn() {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(invoiceListBtn);
		Assert.assertTrue(checkedElementDisplayed(invoiceListBtn));
	}

	public void checkedPrintBtn() {
		loginPage.WaitTillElementIsPresent(printBtn);
		Assert.assertTrue(checkedElementDisplayed(printBtn));
	}

	public void checkLabWorkOrderBtn() {
		loginPage.waitForPageLoad();
		Assert.assertTrue(checkedElementDisplayed(labWorkOrderAddBtn));
	}
	public void checkPrintButtonDisplayed() {
		loginPage.waitForPageLoad();
		Assert.assertTrue(checkedElementDisplayed(print));
	}

	public void checkDateTreatment(String treatment, String todayDate) {
		loginPage.waitForPageLoad();
		for (int i = 0; treatmentStartedDate.size() > i; i++) {
			if (treatments.get(i).getText().trim().contains(treatment)) {
				Assert.assertTrue(treatmentStartedDate.get(i).getText().trim().contains(todayDate)
						&& treatments.get(i).getText().trim().contains(treatment));
				break;
			} else {
				continue;
			}
		}
	}

	public void checkSourceNo(String treatment, String source) {
		loginPage.waitForPageLoad();
		WebElement web = loginPage.getDriver().findElement(By.xpath("//span[contains(text(),'" + treatment
				+ "')]/../..//span[text()='Source']/following-sibling::span[@class='toothNoCont ng-binding']"));
		Assert.assertTrue(web.getText().trim().contains(source));
	}

	public void checkedStatusTreatment(String treatment, String status) {
		List<WebElement> web = loginPage.getDriver().findElements(
				By.xpath("//span[contains(text(),'" + treatment + "')]/../..//span[contains(@class,'indicator_txt')]"));
		for (int i = 0; web.size() > i; i++) {
			if (web.get(i).getText().trim().contains(status)) {
				Assert.assertTrue(web.get(i).getText().trim().contains(status));
				break;
			} else {
				continue;
			}
		}
	}

	public void checkDataName(String treatment) {
		loginPage.waitForPageLoad();
		List<WebElement> dateTime = loginPage.getDriver().findElements(By.xpath(
				"//span[contains(text(),'" + treatment + "')]/../../following-sibling::div//th[text()='Date/Time']"));
		List<WebElement> trtDentist = loginPage.getDriver().findElements(By.xpath("//span[contains(text(),'" + treatment
				+ "')]/../../following-sibling::div//th[text()='Trt. Dentist']"));
		List<WebElement> clinicName = loginPage.getDriver().findElements(By.xpath(
				"//span[contains(text(),'" + treatment + "')]/../../following-sibling::div//th[text()='Clinic Name']"));
		List<WebElement> time = loginPage.getDriver().findElements(By
				.xpath("//span[contains(text(),'" + treatment + "')]/../../following-sibling::div//th[text()='Time']"));
		List<WebElement> trtStage = loginPage.getDriver().findElements(By.xpath(
				"//span[contains(text(),'" + treatment + "')]/../../following-sibling::div//th[text()='Trt. Stage']"));
		List<WebElement> notes = loginPage.getDriver().findElements(By.xpath(
				"//span[contains(text(),'" + treatment + "')]/../../following-sibling::div//span[text()='Clinical Notes']"));
		for (int i = 0; dateTime.size() > i; i++) {
			loginPage.waitForElementToBeClickable(dateTime.get(i));
			Assert.assertTrue(checkedElementDisplayed(dateTime.get(i)) && checkedElementDisplayed(trtDentist.get(i))
					&& checkedElementDisplayed(clinicName.get(i)) && checkedElementDisplayed(time.get(i))
					&& checkedElementDisplayed(trtStage.get(i)) && checkedElementDisplayed(notes.get(i)));
		}
	}

	public void modifiedDate(String treatment, String todayDate) {
		List<WebElement> web = loginPage.getDriver().findElements(By.xpath("//span[contains(text(),'"+todayDate+"')]/.."
				+ "//span[contains(text(),'"+treatment+"')]"
				+ "/../../following-sibling::div//span[@class='legendOriginal legendOrgNoMrg ng-binding']"));
		for (int i = 0; web.size() > i; i++) {
			LocalDate localDate = LocalDate.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yy");
			String formate = localDate.format(formatter);
			Assert.assertTrue(web.get(i).getText().trim().contains(formate));
		}
	}

	public void checkDoctorTreated(String treatment, String doctor) {
		loginPage.waitForPageLoad();
		List<WebElement> webElements = loginPage.getDriver().findElements(
				By.xpath("//span[contains(text(),'" + treatment + "')]/../../following-sibling::div//select[@id='Doctor']/../../../div"));
		for (int i =0; webElements.size() > i; i++) {
			Assert.assertTrue(webElements.get(i).getText().contains(doctor));
		}
	}

	public void checkedClinic(String treatment, String clinic) {
		loginPage.waitForPageLoad();
		List<WebElement> webElements = loginPage.getDriver().findElements(
				By.xpath("//span[contains(text(),'" + treatment + "')]/../../following-sibling::div/table/tbody/tr"));
		boolean flag;
		for (int i = 1; webElements.size() > i; i++) {
			flag = loginPage.getDriver().findElement(By.xpath("(//span[contains(text(),'" + treatment
					+ "')]/../../following-sibling::div/table/tbody/tr[" + i + "]//td[@class='ng-binding'])[2]"))
					.getText().trim().contains(clinic);
			Assert.assertTrue(flag);
		}
	}

	public void checkSpentTime(String treatment, String timeSpent) {
		loginPage.waitForPageLoad();
		WebElement web = loginPage.getDriver().findElement(By.xpath("//span[contains(text(),'" + treatment
				+ "')]/../../following-sibling::div//custom-combo[@data-list='TimeSpentOptions']/preceding-sibling::div"));
		Assert.assertTrue(web.getText().trim().contains(timeSpent));
	}

	public void checkStageTreatment(String treatment, String stages) {
		loginPage.waitForPageLoad();
		WebElement web = loginPage.getDriver().findElement(By.xpath("(//span[contains(text(),'" + treatment
				+ "')]/../../following-sibling::div//td[@class='ng-binding'])[3]"));
		Assert.assertTrue(web.getText().trim().contains(stages));
	}

	public void checkedRemarks(String treatment, String remarks) {
		loginPage.waitForPageLoad();
		WebElement web = loginPage.getDriver().findElement(By.xpath("//span[contains(text(),'" + treatment
				+ "')]/../../following-sibling::div//div[@class='notesWorkDone ng-binding']"));
		Assert.assertTrue(web.getText().trim().equalsIgnoreCase(remarks));
	}
	/*checking all option in Reason after click on ReTreat button from Reason Dropdown field*/
	public void checkRetreatOptionDropDwn() {
		loginPage.waitForPageLoad();
		boolean flag1,flag2, match= false;
		flag1=checkedElementDisplayed(reasonDrpDwn);
		String[] exp = { "Select Reason", "Accidentally completed the treatment on PRM.", "Not satisfied with the treatment.", "Re-infection.","Others"};
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
	
	/*Select the Reason after click on ReTreat/Reopen button from Reason Dropdown field*/
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
	
	public void clickReOpenBtn(String teethNo) {
		loginPage.waitForPageLoad();
		WebElement ele = loginPage.getDriver().findElement(By.xpath("//span[contains(text(),'"+teethNo+"')]/../following-sibling::div//span[@class='actn-icn reopen']"));
		ele.click();
	}
	
	public void clickSaveBtnOnReasonPopUp() {
		loginPage.waitForPageLoad();
		reasonSaveBtn.click();
	}
	
	public void clickAddNewBtn() {
		loginPage.waitForPageLoad();
		try {
			Thread.sleep(2000);
			addNewBtn.click();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void followUpBtn(String treatment){
		loginPage.waitForPageLoad();
		Assert.assertTrue(checkedElementDisplayed(loginPage.getDriver().findElement(By.xpath("//span[contains(text(),'"+treatment+"')]/../..//span[@class='actn-icn followup']"))));
	}
	public void followUpBtnRedCovidFlag(String treatment){
		loginPage.waitForPageLoad();
		Assert.assertFalse(checkedElementDisplayed(loginPage.getDriver().findElement(By.xpath("//span[contains(text(),'"+treatment+"')]/../..//span[@class='actn-icn followup']"))));
	}
	public void clickFollowUpBtn(String treatment){
		loginPage.waitForPageLoad();
		try {
			loginPage.getDriver().findElement(By.xpath("//span[contains(text(),'"+treatment+"')]/../..//span[@class='actn-icn followup']")).click();
			Thread.sleep(4500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void reTreatBtn(String treatment){
		loginPage.waitForPageLoad();
		Assert.assertTrue(checkedElementDisplayed(loginPage.getDriver().findElement(By.xpath("//span[contains(text(),'"+treatment+"')]/../..//span[@class='actn-icn retreat']"))));
	}

	public void clickReTreatBtn(String treatment){
		loginPage.waitForPageLoad();
		try {
			loginPage.getDriver().findElement(By.xpath("//span[contains(text(),'"+treatment+"')]/../..//span[@class='actn-icn retreat']")).click();
			Thread.sleep(2500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private boolean checkedElementDisplayed(WebElement webElement) {
		return (webElement.isDisplayed());
	}

}
