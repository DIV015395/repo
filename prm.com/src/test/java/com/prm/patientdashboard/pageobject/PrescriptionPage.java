package com.prm.patientdashboard.pageobject;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.prm.pageobjects.utils.PCDriver;

public class PrescriptionPage {
	private PCDriver login;

	public PrescriptionPage(PCDriver login) {
		this.login = login;
		PageFactory.initElements(login.getDriver(), this);
	}

	/* Web Elements in Prescription Add Page */
	@FindBy(id = "brandName")
	public WebElement brandNameTxtBx;
	@FindBy(xpath = "//label[text()='Brand Name']")
	public WebElement brandName;
	@FindBy(id = "strength")
	public WebElement strengthTxtBx;
	@FindBy(id = "strength Unit")
	public WebElement strengthDrpDwn;
	@FindBy(xpath = "//label[text()='Strength']")
	public WebElement strength;
	
	@FindBy(name = "durationUnit")
	public WebElement durationUnitTxtBx;
	@FindBy(xpath = "//label[text()='Duration']")
	public WebElement duration;
	@FindBy(id = "genericName")
	public WebElement genericNameTxtBx;
		
//	@FindBy(xpath = "//select[@id='duration']")
	@FindBy(id = "duration unit")
	public WebElement durationDrpDwn;
	@FindBy(id = "route")
	public WebElement routeDrpDwn;
	@FindBy(xpath = "//label[text()='Route']")
	public WebElement route;
	@FindBy(xpath = "//label[text()='Morning']")
	public WebElement morningBtn;
	@FindBy(xpath = "//label[text()='Morning']")
	public WebElement morning;
	@FindBy(xpath = "//label[text()='Morning']/../span/input")
	public WebElement incUnitMorning;
	@FindBy(xpath = "//label[text()='Afternoon']")
	public WebElement afternoonBtn;
	@FindBy(xpath = "//label[text()='Afternoon']")
	public WebElement afternoon;
	@FindBy(xpath = "//label[text()='Afternoon']/../span/input")
	public WebElement incUnitAfternoon;
	@FindBy(xpath = "//label[text()='Night']")
	public WebElement nightBtn;
	@FindBy(xpath = "//label[text()='Night']")
	public WebElement night;
	@FindBy(xpath = "//label[text()='Night']/../span/input")
	public WebElement incUnitNight;
	@FindBy(xpath = "//label[text()='Instruction']")
	public WebElement instruction;
	@FindBy(xpath = "//label[text()='Before Food']")
	public WebElement befFoodBtn;
	@FindBy(xpath = "//label[text()='After Food']")
	public WebElement aftFoodBtn;
	@FindBy(xpath = "//label[text()='Notes']")
	public WebElement notes;
	@FindBy(id = "notes")
	public WebElement notesTxtBx;

	/*------WebEelement of input list in Prescription----*/
	@FindBy(xpath = "//th[text()='Brand Name']")
	private WebElement brandNameInputList;
	@FindBy(xpath = "//th[text()='Generic Name']")
	private WebElement genericNameInputList;
	@FindBy(xpath = "//th[text()='Strength']")
	private WebElement strengthInputList;
	@FindBy(xpath = "//th[text()='Duration']")
	private WebElement durationInputList;
	@FindBy(xpath = "//th[text()='Route']")
	private WebElement routeInputList;
	@FindBy(xpath = "//th[text()='Dosage']")
	private WebElement dosageInputList;
	@FindBy(xpath = "//th[text()='Instruction']")
	private WebElement instructionInputList;
	@FindBy(xpath = "//th[text()='Notes']")
	private WebElement notesInputList;
	@FindBy(xpath = "//th[text()='Action']")
	private WebElement actionInputList;

	/*-------------All buttons in Prescription Add Page-----------*/
	@FindBy(xpath = "//button[contains(@class,'btn-circular ui-link ng-scope')]//i[text()='save']")
	private WebElement saveBtn;
	@FindBy(xpath = "//i[text()='Reset']")
	private WebElement resetBtn;
	@FindBy(xpath = "//i[text()='Cancel']")
	private WebElement cancelBtn;
	@FindBy(xpath = "//span[@class='actn-icn delete']")
	private WebElement deleteBtn;
	@FindBy(xpath = "//span[@class='cmnicons yes-mdl']")
	private WebElement yesOnDelete;

	/*---------------------Validation Message-------*/
	@FindBy(xpath = "//label[text()='Brand Name']/following-sibling::div//div[contains(text(),'Enter Brand Name')]")
	private WebElement errorBrandMsg;
	@FindBy(xpath = "//label[text()='Duration']/following-sibling::div//div[contains(text(),'Enter Duration')]")
	private WebElement errorDurationMsg;
	@FindBy(xpath = "//input[@id='genericName']/..//div[contains(text(),'Enter Generic Name')]")
	private WebElement errorGenericMsg;
	@FindBy(xpath = "//div[contains(text(),'Select Duration Unit')]")
	private WebElement errorDurationDrpDwnMsg;
	@FindBy(xpath = "//div[contains(text(),'Select Dosage')]")
	private WebElement errorFreqMsg;
	@FindBy(xpath = "//div[contains(text(),'Select Instruction')]")
	private WebElement errorInstructionMsg;
	@FindBy(xpath = "//div[contains(text(),'Enter Duration in Digits only')]")
	private WebElement errorDurationDigitMsg;
	@FindBy(xpath = "//div[contains(text(),'Enter Drug Strength in Digits only')]")
	private WebElement errorDrugDigitMsg;

	/*-------------Functions---------------------*/

	/*-- Checking the BrandName and Generic Name-----*/
	public void checkBrandAndGenericName() {
		login.waitForPageLoad();
		Assert.assertTrue(checkedWebElementDisplayed(brandName) && checkedWebElementDisplayed(genericNameTxtBx)
				&& genericNameTxtBx.getAttribute("placeholder").contains("generic name")
				&& checkedWebElementDisplayed(brandNameTxtBx)
				&& brandNameTxtBx.getAttribute("placeholder").contains("brand name"));
	}

	/*-- Enter BrandName in Add Prescription Page----*/
	public void addBrandName(String brandName) {
		login.waitForPageLoad();
		brandNameTxtBx.clear();
		brandNameTxtBx.sendKeys(brandName);
	}
	

	/*-- Checking the Strength TextField in Add Prescription Page---*/
	public void checkStrength() {
		login.waitForPageLoad();
		Assert.assertTrue(checkedWebElementDisplayed(strength) && checkedWebElementDisplayed(strengthTxtBx)
				&& strengthTxtBx.getAttribute("placeholder").contains("strength"));
	}

	/*-- Enter Strength in Add Prescription Page----*/
	public void addStrength(String strength) {
		login.waitForPageLoad();
		strengthTxtBx.clear();
		strengthTxtBx.sendKeys(strength);
	}

	/*-- Checking the duration TextField in Add Prescription Page---*/
	public void checkDuration() {
		login.waitForPageLoad();
		Assert.assertTrue(checkedWebElementDisplayed(duration) && checkedWebElementDisplayed(durationUnitTxtBx)
				&& durationUnitTxtBx.getAttribute("placeholder").contains("duration"));
	}

	/*-- Enter Duration in Add Prescription Page----*/
	public void addDuration(String duration) {
		login.waitForPageLoad();
		durationUnitTxtBx.clear();
		durationUnitTxtBx.sendKeys(duration);

	}

	/*-- Enter GenericName in Add Prescription Page----*/
	public void addGenericName(String genericname) {
		login.waitForPageLoad();
		genericNameTxtBx.clear();
		try {
			genericNameTxtBx.sendKeys(genericname);
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*-- Checking all options present in Strength dropDown in Add Prescription Page---*/
	public void chckStrengthDropDownWebelemet() {
		login.waitForPageLoad();
		boolean flag1, flag2, match = false;
		flag1 = checkedWebElementDisplayed(strengthDrpDwn);
		String[] exp = { "Select Strength Unit", "mg", "mg/1cc", "mg/2cc", "mg/3cc", "mg/4cc", "mg/5cc", "mcg", "grams" };
		Select select = new Select(strengthDrpDwn);
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

	/*-- Select any value from Drug DropDown present in Add Prescription Page----*/
	public void selectDrug(String drugValue) {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		login.waitForPageLoad();
		login.selectFromDropDownByVisibleText(strengthDrpDwn, drugValue);
	}

	/*-- Checking all options present in Duration dropDown in Add Prescription Page---*/
	public void chckDurationDropDownWebelement() {
		login.waitForPageLoad();
		boolean flag1, flag2, match = false;
		flag1 = checkedWebElementDisplayed(durationDrpDwn);
		String[] exp = { "Select Duration Unit", "Day(s)", "Week(s)", "Month(s)", "Year(s)", "s.o.s." };
		Select select = new Select(durationDrpDwn);
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

	/*-- Select any value from Duration DropDown present in Add Prescription Page----*/
	public void selectDuration(String durationValue) {
		login.waitForPageLoad();
		try {
			Thread.sleep(2000);
			login.selectFromDropDownByVisibleText(durationDrpDwn, durationValue);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*-- Checking all options present in Route dropDown in Add Prescription Page---*/
	public void chckRouteDropDownWebelement() {
		login.waitForPageLoad();
		boolean flag1, flag2, match = false;
		flag1 = checkedWebElementDisplayed(route) && checkedWebElementDisplayed(routeDrpDwn);
		String[] exp = { "Select Route", "Injectible", "Oral", "Parenteral", "Rectal", "Topical" };
		Select select = new Select(routeDrpDwn);
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

	/*-- Select any value from Route DropDown present in Add Prescription Page----*/
	public void selectRoute(String routeValue) {
		login.waitForPageLoad();
		login.selectFromDropDownByVisibleText(routeDrpDwn, routeValue);
	}

	/*-- Checking all Checkbox like Morning,AfterNoon,Evening in Add Prescription Page---*/
	public void checkAllCheckbox() {
		login.waitForPageLoad();
		Assert.assertTrue(checkedWebElementDisplayed(morning) && checkedWebElementDisplayed(afternoon)
				&& checkedWebElementDisplayed(night));
	}

	/*-- Select Morning checkbox in Add Prescription Page----*/
	public void clickOnMorning() {
		login.waitForPageLoad();
		login.waitForElementToBeClickable(morningBtn);
		morningBtn.click();
	}

	/*-- Select Afternoon checkbox in Add Prescription Page----*/
	public void clickOnAfternoon() {
		login.waitForPageLoad();
		login.waitForElementToBeClickable(afternoonBtn);
		afternoonBtn.click();
	}

	/*-- Select Evening checkbox in Add Prescription Page----*/
	public void clickOnNight() {
		login.waitForPageLoad();
		login.waitForElementToBeClickable(nightBtn);
		nightBtn.click();
	}

	/*-- Checking Instructions in Add Prescription Page---*/
	public void checkInstruction() {
		login.waitForPageLoad();
		Assert.assertTrue(checkedWebElementDisplayed(instruction) && checkedWebElementDisplayed(befFoodBtn)
				&& checkedWebElementDisplayed(aftFoodBtn));
	}

	/*-- Select BeforeFood in Add Prescription Page----*/
	public void beforeFood() {
		login.waitForPageLoad();
		login.waitForElementToBeClickable(befFoodBtn);
		befFoodBtn.click();
	}

	/*-- Select AfterFood in Add Prescription Page----*/
	public void afterFood() {
		login.waitForPageLoad();
		login.waitForElementToBeClickable(aftFoodBtn);
		aftFoodBtn.click();
	}

	/*-- Checking Notes in Add Prescription Page---*/
	public void checkNotes() {
		login.waitForPageLoad();
		Assert.assertTrue(checkedWebElementDisplayed(notes) && checkedWebElementDisplayed(notesTxtBx)
				&& notesTxtBx.getAttribute("placeholder").contains("notes"));
	}

	/*-- Enter Notes in Add Prescription Page----*/
	public void enterNote(String notes) {
		login.waitForPageLoad();
		notesTxtBx.clear();
		notesTxtBx.sendKeys(notes);
	}

	/*-- Checking Save button in Add Prescription Page---*/
	public void checkSave() {
		login.waitForPageLoad();
		Assert.assertTrue(checkedWebElementDisplayed(saveBtn));
	}

	/*-- Click on Save button in Add Prescription Page---*/
	public void clickOnSaveBtn() {
		login.waitForPageLoad();
		login.WaitTillElementIsPresent(saveBtn);
		try {
			Thread.sleep(4000);
			saveBtn.click();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*-- Checking Cancel button in Add Prescription Page---*/
	public void checkCancel() {
		login.waitForPageLoad();
		Assert.assertTrue(checkedWebElementDisplayed(cancelBtn));
	}

	/*-- Click on Cancel button in Add Prescription Page---*/
	public void clickOnCancelBtn() {
		login.waitForElementToBeClickable(cancelBtn);
		try {
			Thread.sleep(3000);
			cancelBtn.click();
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*-- Checking Reset button in Add Prescription Page---*/
	public void checkReset() {
		login.waitForPageLoad();
		Assert.assertTrue(checkedWebElementDisplayed(resetBtn));
	}

	/*-- Click on Reset button in Add Prescription Page---*/
	public void clickOnResetBtn() {
		login.waitForElementToBeClickable(resetBtn);
		login.getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		resetBtn.click();
	}

	/*---Checking all element in Prescription Input List---*/
	public void checkedInputListDataName() {
		login.waitForPageLoad();
		try {
			Thread.sleep(6000);
			Assert.assertTrue(checkedWebElementDisplayed(brandNameInputList)
					&& checkedWebElementDisplayed(genericNameInputList) && checkedWebElementDisplayed(strengthInputList)
					&& checkedWebElementDisplayed(durationInputList) && checkedWebElementDisplayed(routeInputList)
					&& checkedWebElementDisplayed(dosageInputList) && checkedWebElementDisplayed(instructionInputList)
					&& checkedWebElementDisplayed(notesInputList) && checkedWebElementDisplayed(actionInputList));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/* Verifying Generic Name in Input list */
	public void verifyGenericNameInputListData(String genericName) {
		login.waitForPageLoad();
		String actual = login.getDriver()
				.findElement(By.xpath("//td[contains(text(),'" + genericName.toUpperCase() + "')]")).getText();
		Assert.assertTrue(actual.equalsIgnoreCase(genericName));
	}

	/* Verifying BrandName,Route and Notes in Input list */
	public void verifyPrescriptionInputListData(String Prescription) {
		login.waitForPageLoad();
		String actual = login.getDriver().findElement(By.xpath("//td[contains(text(),'" + Prescription + "')]"))
				.getText();
		Assert.assertEquals(actual, Prescription);
	}

	/* Verifying Strength in Input list */
	public void verifyStrengthInputListData(String Strength, String Drug) {
		login.waitForPageLoad();
		String str = Strength + ".00 " + Drug;
		Assert.assertTrue(checkedWebElementDisplayed(
				login.getDriver().findElement(By.xpath("//td[contains(text(),'" + str + "')]"))));
	}

	/* Verifying Duration in Input list */
	public void verifyDurationInputListData(String Duration, String dur) {
		login.waitForPageLoad();
		String exp_str = Duration + " " + dur;
		String actual_str = login.getDriver().findElement(By.xpath("//td[contains(text(),'" + exp_str + "')]"))
				.getText();
		Assert.assertEquals(actual_str, exp_str);
	}

	/* Verifying Notes in Input list */
	public void verifyNotesInputList(String brandName, String notes) {
		login.waitForPageLoad();
		try {
			Thread.sleep(2000);
			WebElement webelement = login.getDriver().findElement(By.xpath("//td[contains(text(),'" + brandName
					+ "')]/following-sibling::td//div[contains(@class,'tdvNotes')]"));
			login.WaitTillElementIsPresent(webelement);
			Assert.assertTrue(webelement.getText().contains(notes));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/* Click on Edit button shown against the given entry in Input list */
	public void clickEditInputList(String brandName) {
		try {
			Thread.sleep(4000);
			login.waitForPageLoad();
			WebElement webelement = login.getDriver().findElement(By.xpath(
					"//td[contains(text(),'" + brandName + "')]/following-sibling::td//span[@class='actn-icn edit']"));
			webelement.click();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/* Get BrandName value from TextBox in Add Prescription */
	public void getBrandName(String expected) {
		login.waitForPageLoad();
		login.waitForElementToBeClickable(brandNameTxtBx);
		JavascriptExecutor javascriptExecutor = (JavascriptExecutor) login.getDriver();
		Object obj = javascriptExecutor.executeScript("return document.getElementById('brandName').value;");
		String actual = (String) obj;
		Assert.assertEquals(actual, expected);
	}

	/* Get Strength value from TextBox in Add Prescription */
	public void getStrength(String expected) {
		login.waitForPageLoad();
		login.waitForElementToBeClickable(strengthTxtBx);
		JavascriptExecutor javascriptExecutor = (JavascriptExecutor) login.getDriver();
		Object obj = javascriptExecutor.executeScript("return document.getElementById('strength').value;");
		String actual = (String) obj;
		Assert.assertEquals(actual, expected);
	}

	/* Get Duration value from TextBox in Add Prescription */
	public void getDuration(String expected) {
		login.waitForPageLoad();
		login.waitForElementToBeClickable(durationUnitTxtBx);
		JavascriptExecutor javascriptExecutor = (JavascriptExecutor) login.getDriver();
		Object obj = javascriptExecutor.executeScript("return document.getElementsByName('durationUnit')[0].value;");
		String actual = (String) obj;
		Assert.assertEquals(actual, expected);
	}

	/* Get GenericName value from TextBox in Add Prescription */
	public void getGenericName(String expected) {
		login.waitForPageLoad();
		login.waitForElementToBeClickable(genericNameTxtBx);
		JavascriptExecutor javascriptExecutor = (JavascriptExecutor) login.getDriver();
		Object obj = javascriptExecutor.executeScript("return document.getElementById('genericName').value;");
		String actual = (String) obj;
		Assert.assertTrue(expected.equalsIgnoreCase(actual));
	}

	/* Get Drug value from DropDown in Add Prescription */
	public void getDrug(String expected) {
		login.waitForPageLoad();
		Assert.assertTrue(login.firstSelectedOption(strengthDrpDwn).contains(expected));
	}

	/* Get Duration value from DropDown in Add Prescription */
	public void getDurationDrpDwn(String expected) {
		login.waitForPageLoad();
		Assert.assertTrue(login.firstSelectedOption(durationDrpDwn).contains(expected));
	}

	/* Get Route value from DropDown in Add Prescription */
	public void getRoute(String expected) {
		login.waitForPageLoad();
		Assert.assertTrue(login.firstSelectedOption(routeDrpDwn).contains(expected));
	}

	/* Get Instruction value from TextBox in Add Prescription */
	public void getInstruction(String expected) {
		login.waitForPageLoad();
		login.waitForElementToBeClickable(instruction);
		JavascriptExecutor javascriptExecutor = (JavascriptExecutor) login.getDriver();
		Object obj = javascriptExecutor.executeScript("return document.getElementById('instruction').value;");
		String actual = (String) obj;
		Assert.assertEquals(actual, expected);
	}

	/* Get Notes value from TextBox in Add Prescription */
	public void getNotes(String expected) {
		login.waitForPageLoad();
		login.waitForElementToBeClickable(notesTxtBx);
		JavascriptExecutor javascriptExecutor = (JavascriptExecutor) login.getDriver();
		Object obj = javascriptExecutor.executeScript("return document.getElementById('notes').value;");
		String actual = (String) obj;
		Assert.assertEquals(actual, expected);
	}

	/* Click on Cancel button shown in Add Prescription */
	public void cancelInputList(String brandName) {
		login.waitForPageLoad();
		WebElement cancelBtn = login.getDriver().findElement(By.xpath(
				"//td[contains(text(),'" + brandName + "')]/following-sibling::td//span[@class='actn-icn cancel']"));
		cancelBtn.click();
	}

	/* Checking Edit and Delete button in Add Prescription */
	public void actionBtnInputList(String brandName) {
		login.waitForPageLoad();
		try {
			Thread.sleep(1000);
			WebElement deleteBtn = login.getDriver().findElement(By.xpath("//td[contains(text(),'" + brandName
					+ "')]/following-sibling::td//a[@class='btn ng-isolate-scope']"));
			WebElement editBtn = login.getDriver().findElement(
					By.xpath("//td[contains(text(),'" + brandName + "')]/following-sibling::td//a[@class='btn']"));
			Assert.assertTrue(checkedWebElementDisplayed(deleteBtn) && checkedWebElementDisplayed(editBtn));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/* Checking Delete button on Pop up--- */
	public void checkDeleteBtn(String brandName) {
		login.waitForPageLoad();
		Assert.assertTrue(checkedWebElementDisplayed(login.getDriver()
				.findElement(By.xpath("//td[text()='" + brandName + "']/..//span[@class='actn-icn delete']"))));
	}

	/* Click on Delete button on Pop up--- */
	public void clickOnDeleteBtn(String brandName) {
		login.waitForPageLoad();
		try {
			Thread.sleep(4000);
			login.getDriver()
					.findElement(By.xpath("//td[text()='" + brandName + "']/..//span[@class='actn-icn delete']"))
					.click();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/* Click on yes for Delete on Pop up--- */
	public void clickOnYesDelete() {
		login.waitForPageLoad();
		Assert.assertTrue(login.getDriver().findElement(By.xpath("//p[text()='Are you sure to delete this record?']"))
				.isDisplayed());
		login.waitForElementToBeClickable(yesOnDelete);
		yesOnDelete.click();
	}

	/* Checking Deleted Prescription entry in Input listing--- */
	public void verifyPrescriptionDeletedInputList(String Prescription) {
		login.waitForPageLoad();
		WebElement web = login.getDriver().findElement(By.xpath("//td[contains(text(),'" + Prescription + "')]/.."));
		try {
			Thread.sleep(3000);
			Assert.assertTrue(web.getAttribute("class").contains("ng-scope deleted"));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/* Checking Error brand Validation Msg--- */
	public void checkedErrorBrand(String errorMsg) {
		login.waitForPageLoad();
		Assert.assertTrue(errorBrandMsg.getText().contains(errorMsg));
	}

	/* Checking Error Duration Validation Msg--- */
	public void checkedErrorDuration(String errorMsg) {
		login.waitForPageLoad();
		Assert.assertTrue(errorDurationMsg.getText().contains(errorMsg));
	}

	/* Checking Error Duration in digit Validation Msg--- */
	public void checkedErrorDurationInDigit(String errorMsg) {
		login.waitForPageLoad();
		Assert.assertTrue(errorDurationDigitMsg.getText().contains(errorMsg));
	}

	/* Checking Error Generic Validation Msg--- */
	public void checkedErrorGeneric(String errorMsg) {
		login.waitForPageLoad();
		Assert.assertTrue(errorGenericMsg.getText().contains(errorMsg));
	}

	/* Checking Error Drug Validation Msg--- */
	public void checkedErrorDurationDrpDwn(String errorMsg) {
		login.waitForPageLoad();
		Assert.assertTrue(errorDurationDrpDwnMsg.getText().contains(errorMsg));
	}

	/* Checking Error Frequency Validation Msg--- */
	public void checkedErrorDosage(String errorMsg) {
		login.waitForPageLoad();
		Assert.assertTrue(errorFreqMsg.getText().contains(errorMsg));
	}

	/* Checking Error Instruction Validation Msg--- */
	public void checkedErrorInstruction(String errorMsg) {
		login.waitForPageLoad();
		Assert.assertTrue(errorInstructionMsg.getText().contains(errorMsg));
	}

	private boolean checkedWebElementDisplayed(WebElement webElement) {
		return (webElement.isDisplayed());
	}
}
