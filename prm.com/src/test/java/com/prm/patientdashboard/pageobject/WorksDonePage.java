package com.prm.patientdashboard.pageobject;

import java.util.List;

import org.apache.poi.util.SystemOutLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.prm.pageobjects.utils.PCDriver;

public class WorksDonePage {
	private PCDriver loginPage;

	public WorksDonePage(PCDriver loginPage) {
		this.loginPage = loginPage;
		PageFactory.initElements(loginPage.getDriver(), this);
	}

	@FindBy(xpath = "//form//a[contains(@href,'invoiceList')]")
	private WebElement invoiceListBtn;
	@FindBy(xpath = "//a//i[contains(text(),'Collect Adv')]")
	private WebElement collectAdvanceBtn;
	@FindBy(xpath = "//a//i[contains(text(),'Collect Pay')]")
	private WebElement collectPaymentBtn;
	@FindBy(xpath = "//form//a[contains(@href,'wdHistoryList')]")
	private WebElement historyBtn;
	@FindBy(xpath = "//span[contains(@class,'wdNameInfIcon ')]")
	private WebElement userName;
	@FindBy(xpath = "//span[contains(text(),'Selected treatment(s) Workdone started successfully')]")
	private WebElement successTreatmentStartMsg;
	@FindBy(xpath = "//i[text()='Add All']")
	private WebElement addAllBtn;
	@FindBy(xpath = "//i[text()='Print']")
	private WebElement printBtn;
	@FindBy(xpath = "//span[contains(@class,'clnsnametxt ng-binding')]")
	private List<WebElement> treatmentStartedDate;
	@FindBy(xpath = "//span[contains(@class,'clnsnametxt ng-binding')]/following-sibling::span[contains(@class,'dis-inline')]")
	private List<WebElement> treatments;
	@FindBy(id = "Doctor")
	private WebElement doctorDrpDwn;
	@FindBy(xpath = "//span[@class='cmnicons cncl-mdl']")
	private WebElement appointmentCloseBtn;
	@FindBy(xpath = "//span[text()='Action can not be completed as the Lab WO for 1 treatment(s) is in draft mode']")
    private WebElement ActionMsg;
	@FindBy(id="reason")
	private WebElement reasonDrpDwn;
	@FindBy(xpath="//span[@class='cmnicons sv-mdl']")
	private WebElement reasonSaveBtn;
	@FindBy(xpath = "//span[@class='cmnicons labadd']")
	private WebElement labWorkOrderAddBtn;
	@FindBy(id = "Progress")
	private WebElement progressDropDown;
	/*LWO Btn in WorkDone  */
	@FindBy(xpath="//span[@class='cmnicons labadd']")
	private WebElement lwoBtnWorkDone;
	
	public void closeAppoitmentPopup() {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(appointmentCloseBtn);
		try {
			loginPage.hoverOnElement(appointmentCloseBtn);
			appointmentCloseBtn.click();
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void checkedTreatmentStartMsgDisplayed() {
		    loginPage.waitForElementVisibility(successTreatmentStartMsg,4000);
			loginPage.softAssert().assertTrue(checkedElementDisplayed(successTreatmentStartMsg));
			loginPage.waitForElementToDisappear((By.xpath("//span[contains(text(),'Selected treatment(s) Workdone started successfully')]")));
	}

//	public void checkedTreatmentStartMsgDisplayed() {
//		try {
//			Thread.sleep(2500);
//			loginPage.softAssert().assertTrue(checkedElementDisplayed(successTreatmentStartMsg));
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//	}

	public void checkedInvoiceListBtn() {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(invoiceListBtn);
		Assert.assertTrue(checkedElementDisplayed(invoiceListBtn));
	}

	public void checkedCollectAdvanceBtn() {
		loginPage.waitForPageLoad();
		loginPage.WaitTillElementIsPresent(collectAdvanceBtn);
		Assert.assertTrue(checkedElementDisplayed(collectAdvanceBtn));
	}
	
	public void checkedCollectPaymentBtn() {
		loginPage.waitForPageLoad();
		loginPage.WaitTillElementIsPresent(collectPaymentBtn);
		Assert.assertTrue(checkedElementDisplayed(collectPaymentBtn));
	}
	
	public void checkLabWorkOrderBtn(){
		loginPage.waitForPageLoad();
		Assert.assertTrue(checkedElementDisplayed(labWorkOrderAddBtn));
	}
	public void clickInvoiceListBtn() {
		try {
			Thread.sleep(2000);
			loginPage.waitForElementToBeClickable(invoiceListBtn);
			invoiceListBtn.click();
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void checkedHistoryBtn() {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(historyBtn);
		Assert.assertTrue(checkedElementDisplayed(historyBtn));
	}

	public void checkedAddAllBtn() {
		loginPage.WaitTillElementIsPresent(addAllBtn);
		Assert.assertTrue(checkedElementDisplayed(addAllBtn));
	}

	public void checkedAddAllBtnNotPresent() {
		loginPage.waitForPageLoad();
		Assert.assertTrue(loginPage.getDriver().findElements(By.xpath("//i[text()='Add All']")).size()==0);
	}

	public void checkedPrintBtn() {
		loginPage.WaitTillElementIsPresent(printBtn);
		Assert.assertTrue(checkedElementDisplayed(printBtn));
	}

	public void checkedPrintBtnNotPresent() {
		loginPage.waitForPageLoad();
		Assert.assertTrue(loginPage.getDriver().findElement(By.xpath("//i[text()='Print']/..")).getAttribute("class").contains("hide"));
	}

	public void checkProgressDropDown(String treatment){
		loginPage.waitForPageLoad();
		Assert.assertTrue(checkedElementDisplayed(loginPage.getDriver().findElement(By.xpath("//span[contains(text(),'"+treatment+"')]/../..//select[@data-ng-model='value']"))));
	}
	
	public void clickWorkDoneHistory() {
		loginPage.waitForElementVisibility(historyBtn, 4000);
		historyBtn.click();
		loginPage.waitForSpinnerToDisappear();
}
	
	
//	public void clickWorkDoneHistory() {
//		try {
//			loginPage.waitForPageLoad();
//			loginPage.waitForElementToBeClickable(historyBtn);
//			historyBtn.click();
//			Thread.sleep(3000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//	}

	public void checkedUserName(String patientName) {
		loginPage.waitForPageLoad();
		loginPage.WaitTillElementIsPresent(userName);
		String str = userName.getText();
		String[] split = str.split(" : ");
		boolean flag = split[0].contains(patientName) && !(split[1].equals("NA") || split[1].equals("Null"));
		Assert.assertTrue(flag);
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
				Select sel = new Select(loginPage.getDriver().findElement(
						By.xpath("//span[contains(text(),'"+treatment+"')]/../..//select[@data-ng-model='value']")));
				if (status.equals("Started")) {
					Assert.assertTrue(web.get(i).getText().trim().contains(status)
							&& sel.getFirstSelectedOption().getText().trim().contains("Select Progress"));
				} else {
					Assert.assertTrue(web.get(i).getText().trim().contains(status)
							&& sel.getFirstSelectedOption().getText().trim().contains(status));
				}
				break;
			} else {
				continue;
			}
		}
	}

//	public void checkedStatusTreatment(String treatment, String status) {
//		try {
//			Thread.sleep(4000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		List<WebElement> web = loginPage.getDriver().findElements(
//				By.xpath("//span[contains(text(),'" + treatment + "')]/../..//span[contains(@class,'indicator_txt')]"));
//		for (int i = 0; web.size() > i; i++) {
//			if (web.get(i).getText().trim().contains(status)) {
//				Select sel = new Select(loginPage.getDriver().findElement(
//						By.xpath("//span[contains(text(),'"+treatment+"')]/../..//select[@data-ng-model='value']")));
//				if (status.equals("Started")) {
//					Assert.assertTrue(web.get(i).getText().trim().contains(status)
//							&& sel.getFirstSelectedOption().getText().trim().contains("Select Progress"));
//				} else {
//					Assert.assertTrue(web.get(i).getText().trim().contains(status)
//							&& sel.getFirstSelectedOption().getText().trim().contains(status));
//				}
//				break;
//			} else {
//				continue;
//			}
//		}
//	}

	public void selectStages(String treatment, String stage) {
		loginPage.waitForPageLoad();
		WebElement web = loginPage.getDriver()
				.findElement(By.xpath("//span[contains(text(),'" + treatment + "')]/../..//select[@data-ng-model='value']"));
		loginPage.selectFromDropDownByVisibleText(web, stage);
	}

	public void checkSuspededBtn(String treatment) {
		loginPage.waitForPageLoad();
		List<WebElement> web = loginPage.getDriver().findElements(
				By.xpath("//span[contains(text(),'" + treatment + "')]/../..//span[@class='actn-icn suspend']"));
		for (int i = 0; web.size() > i; i++) {
			Assert.assertTrue(checkedElementDisplayed(web.get(i)));
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
				"//span[contains(text(),'" + treatment + "')]/../../following-sibling::div//th[contains(text(),'Clinical Notes')]"));
		List<WebElement> action = loginPage.getDriver().findElements(By.xpath(
				"//span[contains(text(),'" + treatment + "')]/../../following-sibling::div//th[text()='Action']"));
		for (int i = 0; dateTime.size() > i; i++) {
			Assert.assertTrue(checkedElementDisplayed(dateTime.get(i)) && checkedElementDisplayed(trtDentist.get(i))
					&& checkedElementDisplayed(clinicName.get(i)) && checkedElementDisplayed(time.get(i))
					&& checkedElementDisplayed(trtStage.get(i)) && checkedElementDisplayed(notes.get(i))
					&& checkedElementDisplayed(action.get(i)));
		}
	}

	public void checkBox(String treatment) {
		List<WebElement> web = loginPage.getDriver().findElements(By.xpath(
				"//span[contains(text(),'" + treatment + "')]/../../following-sibling::div//label[text()='Check']"));
		for (int i = 0; web.size() > i; i++) {
			Assert.assertTrue(checkedElementDisplayed(web.get(i)));
		}
	}

	public void modifiedDate(String treatment, String todayDate) {
		List<WebElement> web = loginPage.getDriver().findElements(By.xpath("//span[contains(text(),'" + treatment
				+ "')]/../../following-sibling::div//span[@class='legendOriginal legendOrgNoMrg ng-binding']"));
		for (int i = 0; web.size() > i; i++) {
			Assert.assertTrue(web.get(i).getText().trim().contains(todayDate));
		}
	}

	public void checkDoctorTreated(String treatment, String doctor) {
		loginPage.waitForPageLoad();
		WebElement web = loginPage.getDriver().findElement(By.xpath("//span[contains(text(),'" + treatment
				+ "')]/../../following-sibling::div//select[@id='Doctor']/../../../div"));
		Assert.assertTrue(web.getText().trim().equalsIgnoreCase(doctor));
	}

	public void checkDoctorSelected(String treatment, String doctor) {
		loginPage.waitForPageLoad();
		WebElement web = loginPage.getDriver().findElement(By.xpath(
				"//span[contains(text(),'" + treatment + "')]/../../following-sibling::div//select[@id='Doctor']"));
		Select sel = new Select(web);
		Assert.assertTrue(sel.getFirstSelectedOption().getText().trim().equalsIgnoreCase(doctor));
	}

	public void selectDoctor(String treatment, String doctor) {
		loginPage.waitForPageLoad();
		WebElement web = loginPage.getDriver().findElement(By.xpath(
				"//span[contains(text(),'" + treatment + "')]/../../following-sibling::div//select[@id='Doctor']"));
		Select sel = new Select(web);
		if (sel.getFirstSelectedOption().getText().trim().equalsIgnoreCase(doctor)) {
			Assert.assertTrue(sel.getFirstSelectedOption().getText().trim().equalsIgnoreCase(doctor));
		} else {
			loginPage.selectFromDropDownByVisibleText(web, doctor);
		}
	}

	public void checkedClinic(String treatment, String clinic) {
		loginPage.waitForPageLoad();
		List<WebElement> webElements = loginPage.getDriver().findElements(
		By.xpath("//span[contains(text(),'" + treatment + "')]"));
		for (int i = 1;i<=webElements.size();i++) {
			String text = loginPage.getDriver().findElement(By.xpath("(//span[contains(text(),'"+treatment+"')])["+i+"]/../../..//td[@class='ng-binding'][1]")).getText().trim();
			Assert.assertEquals(clinic, text);
		}
	}

	public void checkTimeSelected(String treatment, String timeSlotSelected) {
		loginPage.waitForPageLoad();
		WebElement web = loginPage.getDriver().findElement(By.xpath(
				"//span[contains(text(),'" + treatment + "')]/../../following-sibling::div//select[@id='time']"));
		Select sel = new Select(web);
		Assert.assertTrue(sel.getFirstSelectedOption().getText().trim().contains(timeSlotSelected));
	}

	public void checkSpentTime(String treatment, String spentTime) {
		loginPage.waitForPageLoad();
		WebElement web = loginPage.getDriver().findElement(By.xpath("//span[contains(text(),'" + treatment
				+ "')]/../../following-sibling::div//div[contains(@ng-hide,'worklogData.timeSpentEdit')]"));
		Assert.assertTrue(web.getText().trim().contains(spentTime));
	}

	public void selectTime(String treatment, String timeSlotSelected) {
		loginPage.waitForPageLoad();
		WebElement web = loginPage.getDriver().findElement(By.xpath(
				"//span[contains(text(),'" + treatment + "')]/../../following-sibling::div//select[@id='time']"));
		loginPage.selectFromDropDownByVisibleText(web, timeSlotSelected);
	}

	public void checkedRemarks(String treatment) {
		loginPage.waitForPageLoad();
		WebElement web = loginPage.getDriver().findElement(
				By.xpath("//span[contains(text(),'" + treatment + "')]/../../following-sibling::div//textarea"));
		Assert.assertTrue(web.getAttribute("maxlength").contains("768")
				&& web.getAttribute("placeholder").contains("no remarks"));
	}

	public void enterRemarks(String treatment, String remarks) {
		loginPage.waitForPageLoad();
		WebElement web = loginPage.getDriver().findElement(
				By.xpath("//span[contains(text(),'" + treatment + "')]/../../following-sibling::div//textarea"));
		web.sendKeys(remarks);
	}

	public void checkRemarksWorkDoneAddPage(String treatment, String remarks){
		loginPage.waitForPageLoad();
		Assert.assertTrue(loginPage.getDriver().findElement(By.xpath("//span[contains(text(),'"+treatment+"')]/../../following-sibling::div//tr//div[contains(@class,'notesWorkDone')]")).getText().contains(remarks));
	}

	public void checkedAddButton(String treatment) {
		loginPage.waitForPageLoad();
		WebElement web = loginPage.getDriver().findElement(By.xpath("//span[contains(text(),'" + treatment
				+ "')]/../../following-sibling::div//span[@class='actn-icn add']"));
		Assert.assertTrue(checkedElementDisplayed(web));
	}
	public void workDoneAddBtnRedCovidPatient(String treatment) {
		loginPage.waitForPageLoad();
		List<WebElement> web = loginPage.getDriver().findElements(By.xpath("//span[contains(text(),'" + treatment
				+ "')]/../../following-sibling::div//span[@class='actn-icn add']"));
		Assert.assertEquals(web.size(),0);
	}
	public void clickOnAdd(String treatment) {
		loginPage.waitForPageLoad();
		WebElement web = loginPage.getDriver().findElement(By.xpath("//span[contains(text(),'" + treatment
				+ "')]/../../following-sibling::div//span[@class='actn-icn add']"));
		try {
			web.click();
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void checkStatus(String treatment, String status) {
		loginPage.waitForPageLoad();
		List<WebElement> webElements = loginPage.getDriver().findElements(
				By.xpath("//span[contains(text(),'" + treatment + "')]/../../following-sibling::div/table/tbody/tr"));
		for (int i = 1; webElements.size() > i; i++) {
			int j = i + 1;
			WebElement web = loginPage.getDriver().findElement(
					By.xpath("//span[contains(text(),'"+treatment+"')]/../../following-sibling::div/table/tbody/tr[" + j
							+ "]/td[6]"));
			Assert.assertTrue(web.getText().trim().contains(status));
			break;
		}
	}

	public void checkedRemarks(String treatment, String remarks) {
		loginPage.waitForPageLoad();
		WebElement web = loginPage.getDriver().findElement(By.xpath("//span[contains(text(),'" + treatment
				+ "')]/../../following-sibling::div//div[@class='notesWorkDone ng-binding']"));
		Assert.assertTrue(web.getText().trim().equalsIgnoreCase(remarks));
	}

	public void checkEdit(String treatment) {
		loginPage.waitForPageLoad();
		WebElement web = loginPage.getDriver().findElement(By.xpath("//span[contains(text(),'" + treatment
				+ "')]/../../following-sibling::div//span[@class='actn-icn edit']"));
		Assert.assertTrue(checkedElementDisplayed(web));
	}
	
	public void verifyLWOActionMessage(){
        loginPage.waitForPageLoad();
        try {
            Thread.sleep(3000);
            loginPage.softAssert().assertTrue(checkedElementDisplayed(ActionMsg));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
	
	public void clickSuspendBtn(String teethNo) {
		loginPage.waitForPageLoad();
		WebElement ele = loginPage.getDriver().findElement(By.xpath("//span[contains(text(),'"+teethNo+"')]/../following-sibling::div//span[@class='actn-icn suspend']"));
		ele.click();
	}

	/*Select the Reason after click on ReTreat button from Reason Dropdown field*/
	public void selectRequire(String reasonValue) {
		loginPage.waitForPageLoad();
		try {
			Thread.sleep(3000);
			loginPage.selectFromDropDownByVisibleText(reasonDrpDwn, reasonValue);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void clickSaveBtnOnReasonPopUp() {
		loginPage.waitForPageLoad();
		reasonSaveBtn.click();
	}
	

	public void clickLWOBtnWD() {
		loginPage.waitForPageLoad();
		loginPage.WaitTillElementIsPresent(lwoBtnWorkDone);
		try {
			Thread.sleep(7000);
			Assert.assertTrue(checkedElementDisplayed(lwoBtnWorkDone));
			lwoBtnWorkDone.click();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private boolean checkedElementDisplayed(WebElement element) {
		return (element.isDisplayed());
	}

}
