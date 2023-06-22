package com.prm.docdashboard.pageobject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

/**
 * 
 * @author Ajit
 *
 */
public class PatientRegistrationPage {
	private LoginPage loginPage;

	public PatientRegistrationPage(LoginPage loginPage) {
		this.loginPage = loginPage;
		PageFactory.initElements(loginPage.getDriver(), this);
	}
	@FindBy(id = "name")
	private WebElement patientName;
	@FindBy(xpath = "//label[text()='Gender']")
	private WebElement gender;
	@FindBy(xpath = "//label[text()='Gender']/sup[@class='required']")
	private WebElement genderRequired;
	@FindBy(xpath = "//label[text()='Male']")
	private WebElement genderMale;
	@FindBy(xpath = "//label[text()='Female']")
	private WebElement genderFemale;
	@FindBy(xpath = "//label[text()='Other']")
	private WebElement genderOther;
	@FindBy(id = "dob")
	private WebElement DOB;
	@FindBy(id = "age")
	private WebElement age;
	@FindBy(id = "email")
	private WebElement email;
	@FindBy(id = "mobile")
	private WebElement mobile;
	@FindBy(xpath = "//label[text()='Call']/preceding-sibling::input")
	private WebElement callCheckbox;
	@FindBy(xpath = "//label[text()='SMS']/preceding-sibling::input")
	private WebElement smsCheckbox;
	@FindBy(xpath = "//label[text()='Email']/preceding-sibling::input")
	private WebElement emailCheckbox;
	@FindBy(id = "landline")
	private WebElement landline;
	@FindBy(id = "alternateName")
	private WebElement alternateName;
	@FindBy(id = "alternateContact")
	private WebElement alternateContact;
	@FindBy(id = "primaryDentist")
	private WebElement primaryDentist;
	@FindBy(id = "reachtime")
	private WebElement reachtime;
	@FindBy(xpath = "//select[@id='occupation']")
	private WebElement occupation;
	@FindBy(xpath = "//input[@id='occupation']")
	private WebElement otherOccupation;
	@FindBy(id = "address")
	private WebElement addressTextBox;
	@FindBy(id = "city")
	private WebElement city;
	@FindBy(id = "Country")
	private WebElement country;
	@FindBy(id = "State")
	private WebElement state;
	@FindBy(id = "pin")
	private WebElement pin;
	@FindBy(xpath = "//div[contains(@class,'sideNavigation')]//i[text()='Approve & Save']")
	private WebElement approveSave;
	@FindBy(xpath = "//div[contains(@class,'sideNavigation')]//i[text()='Approve & Appt+']")
	private WebElement approveAppBtn;
	@FindBy(xpath = "//div[contains(@class,'sideNavigation')]//i[text()='Reset']")
	private WebElement Reset;
	@FindBy(xpath = "//div[contains(@class,'sideNavigation')]//i[text()='Cancel']")
	private WebElement Cancel;
	@FindBy(xpath = "//div[@id='wrapper']//input")
	private List<WebElement> allWebElements;
	@FindBy(xpath = "//div[@class='error ng-binding ng-scope']")
	private List<WebElement> errorMsg;
	@FindBy(id = "guardianName")
	private WebElement guardianName;
	@FindBy(id = "guardianContact")
	private WebElement guardianContact;
	@FindBy(id = "guardianContact")
	private List<WebElement> guardianContactSet;
	@FindBy(xpath = "//span[contains(text(),'Medical History')]")
	private WebElement medicalHistory;
	@FindBy(xpath = "//div[@class='form-group col-xs-12 col-sm-6 col-md-6 col-lg-6 ng-scope']")
	private List<WebElement> medicalHistoryDomains;
	@FindBy(xpath = "//span[contains(text(),'Past History')]")
	private WebElement pastHistory;
	@FindBy(xpath = "//span[contains(text(),'Add')]")
	private WebElement addBtnPastHistory;
	@FindBy(xpath = "//a[text()='Adult']")
	private WebElement adult;
	@FindBy(xpath = "//a[text()='Pedo']")
	private WebElement pedo;
	@FindBy(xpath = "//a[text()='Mixed']")
	private WebElement mixed;
	@FindBy(xpath = "//label[@for='alltooth']")
	private WebElement allToothCheckBox;
	@FindBy(xpath = "//ul[@id='childLowerJaw']//li")
	private List<WebElement> teethslowerPedo;
	@FindBy(xpath = "//ul[@id='childUpperJaw']//li")
	private List<WebElement> teethsUpperPedo;
	@FindBy(xpath = "//ul[@id='adultUpperJaw']//li")
	private List<WebElement> teethslowerAdult;
	@FindBy(xpath = "//ul[@id='adultUpperJaw']//li")
	private List<WebElement> teethsUpperAdult;
	@FindBy(xpath = "//button[@type='submit']")
	private WebElement saveBtn;
//	@FindBy(xpath = "//table[@class='table table-striped']//div[contains(@class,'tdvTooth')]")
	@FindBy(xpath = "//span[@class='btnLabel']/../../../..//div[contains(@class,'tdvTooth')]")
	private WebElement toothNoInPasstHistory;
	@FindBy(xpath = "//table[@class='table table-striped']//div[contains(@class,'tdvNotes')]")
	private WebElement notesPastHistory;
	@FindBy(xpath = "//table[@class='table table-striped']//span[contains(@class,'actn-icn delete')]")
	private WebElement actionBtn;
//	@FindBy(xpath = "(//div[contains(text(),'Note(s)')]/following-sibling::div//textarea[@id='notes'])[2]")
	@FindBy(id = "notes")
	private WebElement notes;
	@FindBy(xpath = "(//span[text()='Delete'])[1]")
	private WebElement DeleteBtnInPastHistory;
	@FindBy(xpath = "//span[contains(text(),'Medication')]")
	private WebElement medication;
	@FindBy(xpath = "//label[contains(text(),'Are you on medication?')]/following-sibling::div//label[text()='Yes']")
	private WebElement yesOnMedication;
	@FindBy(xpath = "//label[contains(text(),'Are you on medication?')]/following-sibling::div//label[text()='No']")
	private WebElement noOnMedication;
	@FindBy(xpath = "//label[contains(text(),'Are you on medication?')]/following-sibling::div//label[text()='NA']")
	private WebElement naOnMedication;
	@FindBy(xpath = "//label[contains(text(),'Are you on medication?')]/..//label[text()='Yes']")
	private WebElement medicationCheckUpYesBtn;
	@FindBy(xpath = "//div[text()='Enter medication details']")
	private WebElement enterMedicationDetails;
	@FindBy(xpath = "//div[text()='Enter allergy(s) details']")
	private WebElement enterAllergyDetails;
	@FindBy(xpath = "//span[contains(text(),'Dental Checkup')]")
	private WebElement dentalCheckup;
	@FindBy(xpath = "//label[contains(text(),'Less than 6 month')]")
	private WebElement lessThanSixMonth;
	@FindBy(xpath = "//label[contains(text(),'More than 6 month')]")
	private WebElement moreThanSixMonth;
	@FindBy(xpath = "//label[contains(text(),'More than 1 year')]")
	private WebElement moreThanOneYr;
	@FindBy(xpath = "//span[contains(text(),'Allergies')]")
	private WebElement allergies;
	@FindBy(xpath = "//label[contains(text(),'Allergies to any medications?')]/..//label[text()='Yes']")
	private WebElement allergiesYesBtn;
	@FindBy(xpath = "//label[contains(text(),'Allergies to any medications?')]/..//label[text()='No']")
	private WebElement allergiesNoBtn;
	@FindBy(xpath = "//label[contains(text(),'Allergies to any medications?')]/..//label[text()='NA']")
	private WebElement allergiesNABtn;
	@FindBy(id = "specifyallrg")
	private WebElement allrgTxtArea;
	@FindBy(xpath = "//Span[contains(text(),'Group')]")
	private WebElement group;
	@FindBy(id = "groups")
	private WebElement groupTextfields;
	@FindBy(xpath = "//Span[contains(text(),'Patient Notes')]")
	private WebElement patientNotes;
	@FindBy(id = "patientnotes")
	private WebElement patientNotesTextFields;
	@FindBy(id = "patientInfoHistory")
	private WebElement patientHistory;
	@FindBy(xpath = "//div[@id='patientHistoryInfoList']//th[text()='Date']")
	private WebElement datePatientHistory;
	@FindBy(xpath = "//div[@id='patientHistoryInfoList']//th[text()='Created/Updated by']")
	private WebElement createdDatePatientHistory;
	@FindBy(xpath = "//div[@id='patientHistoryInfoList']//th[text()='Name']")
	private WebElement namePatientHistory;
	@FindBy(xpath = "//div[@id='patientHistoryInfoList']//th[text()='Mobile']")
	private WebElement mobliePatientHistory;
	@FindBy(xpath = "//div[@id='patientHistoryInfoList']//th[text()='Email']")
	private WebElement emailPatientHistory;
	@FindBy(xpath = "//div[@id='patientHistoryInfoList']//strong[text()='Address » ']")
	private List<WebElement> address;
	@FindBy(xpath = "//div[text()='Enter patient name']")
	private WebElement enterPatientNameMsg;
	@FindBy(xpath = "//div[text()='Select gender']")
	private WebElement selectGenderMsg;
	@FindBy(xpath = "//div[text()='Enter mobile no.']")
	private WebElement enterMobileNoMsg;
	@FindBy(xpath = "//div[text()='Enter 10 digit mobile no.']")
	private WebElement enterTenDigitMobileNoMsg;
	@FindBy(xpath = "//div[text()='Enter a valid mobile no.']")
	private WebElement enterValidMobileNoMsg;
	@FindBy(xpath = "//div[text()='Enter alternate contact name']")
	private WebElement enterAlternateContactNameMsg;
	@FindBy(xpath = "//div[text()='Enter alternate contact no.']")
	private WebElement enterAlternateContactNoMsg;
	@FindBy(xpath = "//div[text()='Enter guardian name']")
	private WebElement enterGuardianNameMsg;
	@FindBy(xpath = "//div[text()='Enter guardian contact no.']")
	private WebElement enterGuardianContactNoMsg;
	@FindBy(xpath = "//div[contains(text(),'Enter email id')]")
	private WebElement validateEnterEmailErrMsg;
	@FindBy(xpath = "//div[contains(text(),'Invalid Email!')]")
	private WebElement validateInvalidEmailErrMsg;
	@FindBy(xpath = "//span[contains(text(),'Verified!')]")
	private WebElement verifiedEmailMsg;
	//past history column name
	@FindBy(xpath = "//td[text()='Date']")
	private WebElement dateColumn;
	@FindBy(xpath = "//td[text()='Tooth No(s)']")
	private WebElement toothNoColumn;
	@FindBy(xpath = "//td[text()='Created By']")
	private WebElement createdByColumn;
	@FindBy(xpath = "//td[text()='Notes']")
	private WebElement notesColumn;
	@FindBy(xpath = "//td[text()='Action']")
	private WebElement actionColumn;
	@FindBy(id="specifymedictn")
	private WebElement medicationTextFields;
	@FindBy(id="specifyallrg")
	private WebElement alleryTextField;
	@FindBy(xpath = "//span[text()='Name and phone already exist']")
	private WebElement nameAndPhoneAlreadyExit;
	@FindBy(xpath = "//img[@class='spinner']")
	private WebElement spinnerLogo;

	public void nameAndPhoneAlreadyExit(){
		loginPage.waitForPageLoad();
		try {
			Thread.sleep(1000);
			Assert.assertTrue(checkedElementDisplayed(nameAndPhoneAlreadyExit));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public void enterMedication(String medication){
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(medicationTextFields);
		medicationTextFields.sendKeys(medication);
	}
	public void enterAllergy(String allergy){
		loginPage.WaitTillElementIsPresent(allrgTxtArea);
		loginPage.waitForElementToBeClickable(alleryTextField);
		alleryTextField.sendKeys(allergy);
	}
	public void selectGenderMsg(){
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(selectGenderMsg);
		Assert.assertTrue(checkedElementDisplayed(selectGenderMsg));
	}
	public void enterMobileNoMsg(){
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(enterMobileNoMsg);
		Assert.assertTrue(checkedElementDisplayed(enterMobileNoMsg));
	}
	public void enterTenDigitMobileNoMsg(){
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(enterTenDigitMobileNoMsg);
		Assert.assertTrue(checkedElementDisplayed(enterTenDigitMobileNoMsg));
	}
		
	public void validateEnterEmailMsg(){
		loginPage.waitForElementVisibility(validateEnterEmailErrMsg, 4000);
		loginPage.waitForElementToBeClickable(validateEnterEmailErrMsg);
		Assert.assertTrue(checkedElementDisplayed(validateEnterEmailErrMsg));
	}
	
	public void validateInvalidEmailMsg(){
		loginPage.waitForElementVisibility(validateInvalidEmailErrMsg, 4000);
		loginPage.waitForElementToBeClickable(validateInvalidEmailErrMsg);
		Assert.assertTrue(checkedElementDisplayed(validateInvalidEmailErrMsg));
	}
	
	public void verificationOfValidEmail(){
		loginPage.waitForSpinnerToDisappear();
		loginPage.waitForElementVisibility(verifiedEmailMsg, 4000);
		loginPage.waitForElementToBeClickable(verifiedEmailMsg);
		Assert.assertTrue(checkedElementDisplayed(verifiedEmailMsg));
	}
	
	public void enterValidMobileNoMsg(){
//		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(enterValidMobileNoMsg);
		Assert.assertTrue(checkedElementDisplayed(enterValidMobileNoMsg));
	}
	public  void enterAlternateContactNameMsg(){
//		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(enterAlternateContactNameMsg);
		Assert.assertTrue(checkedElementDisplayed(enterAlternateContactNameMsg));
	}
	public void enterAlternateContactNoMsg(){
//		loginPage.waitForPageLoad();
		
//		loginPage.waitForElementVisibility(enterAlternateContactNoMsg, 5000);
	loginPage.waitForElementToBeClickable(enterAlternateContactNoMsg);
		Assert.assertTrue(checkedElementDisplayed(enterAlternateContactNoMsg));
	}
	public void enterGuardianNameMsg(){
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(enterGuardianNameMsg);
		Assert.assertTrue(checkedElementDisplayed(enterGuardianNameMsg));
	}
	public void enterGuardianContactNoMsg(){
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(enterGuardianContactNoMsg);
		Assert.assertTrue(checkedElementDisplayed(enterGuardianContactNoMsg));
	}
	public void patientPastHistory() {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(patientName);
			Assert.assertTrue(checkedElementDisplayed(medicalHistory) && checkedElementDisplayed(pastHistory)
					&& checkedElementDisplayed(dentalCheckup) && checkedElementDisplayed(medication)
					&& checkedElementDisplayed(allergies) && checkedElementDisplayed(group)
					&& checkedElementDisplayed(patientNotes));
	}
	public void checkAge(){
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(patientName);
		Assert.assertTrue(checkedElementDisplayed(DOB) && (checkedElementDisplayed(age) && checkedMandatory(age)));
	}
	
	public void checkGender() {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(gender);
		Assert.assertTrue(checkedElementDisplayed(gender)&&checkedElementDisplayed(genderRequired)&&checkedElementDisplayed(genderMale)&&checkedElementDisplayed(genderFemale)&&checkedElementDisplayed(genderOther));
	}

//	public void genderMaleSelected() {
//		loginPage.waitForPageLoad();
//		Assert.assertTrue(loginPage.getDriver().findElement(By.id("gender-male")).isSelected());
//
//	}
	
	public void checkPatientName(){
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(patientName);
		Assert.assertTrue(checkedElementDisplayed(patientName) && checkedMandatory(patientName));
	}
	public void checkEmailId(){
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(email);
		Assert.assertTrue(checkedElementDisplayed(email));
	}
	public void checkMobile(){
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(patientName);
		Assert.assertTrue(checkedElementDisplayed(mobile) && checkedMandatory(mobile));
	}
	
	public void checkSmsCallEmailCheckbox(){
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(patientName);
		Assert.assertFalse(callCheckbox.isSelected() && smsCheckbox.isSelected() && emailCheckbox.isSelected());
	}
	
	public void checkLandline(){
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(landline);
		Assert.assertTrue(checkedElementDisplayed(landline));
	}
	public void checkAlternateContactName(){
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(alternateName);
		Assert.assertTrue(checkedElementDisplayed(alternateName) && checkedMandatory(alternateName));
	}
	public void checkAlternateContactNo(){
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(alternateContact);
		Assert.assertTrue(checkedElementDisplayed(alternateContact) && checkedMandatory(alternateContact));
	}
	public void checkPrimaryDentist(String doctorName){
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(primaryDentist);
		Assert.assertTrue(checkedElementDisplayed(primaryDentist)&&loginPage.firstSelectedOption(primaryDentist).equalsIgnoreCase(doctorName));
	}
	public void checkReachTime(){
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(reachtime);
		Assert.assertTrue(checkedElementDisplayed(reachtime));
	}
	public void checkOccupation(){
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(occupation);
		Assert.assertTrue(checkedElementDisplayed(occupation)&&loginPage.firstSelectedOption(occupation).contains("Select Occupation"));
	}
	
	public void checkOccupationOthers(){
		loginPage.waitForPageLoad();
		Assert.assertTrue(checkedElementDisplayed(otherOccupation)&&!otherOccupation.isEnabled());
	}
	
	public void checkAddress(){
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(addressTextBox);
		Assert.assertTrue(checkedElementDisplayed(addressTextBox)&&addressTextBox.getAttribute("placeholder").contains("» address") && checkedElementDisplayed(city)&& city.getAttribute("placeholder").contains("» city") 
				&&checkedElementDisplayed(country)&&loginPage.firstSelectedOption(country).contains("India")&& checkedElementDisplayed(state) && loginPage.firstSelectedOption(state).contains("Select State") &&checkedElementDisplayed(pin) && pin.getAttribute("placeholder").contains("» Pin"));
	}
	
	public void checkActionsButton(){
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(approveSave);
		Assert.assertTrue(checkedElementDisplayed(approveSave)&&checkedElementDisplayed(approveAppBtn)&&checkedElementDisplayed(Cancel)&checkedElementDisplayed(Reset));
	}

	public void clickOnResetBtn() {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(Reset);
		Reset.click();
	}

	public void clickOnCancelBtn() {
		loginPage.waitForSpinnerToDisappear();
		loginPage.waitForElementToBeClickable(Cancel);
		loginPage.hoverOnElement(Cancel);
		Cancel.click();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void clickOnApproveApp() {
		loginPage.waitForSpinnerToDisappear();
		loginPage.waitForElementToBeClickable(approveAppBtn);
		approveAppBtn.click();
	}

	public void print() {
		Assert.assertTrue(loginPage.findElement(By.xpath("//span[contains(@text(),'Name and phone already exist')]")).isDisplayed());
	}

	public void clickOnDeleteBtn() {
		loginPage.waitForElementToBeClickable(DeleteBtnInPastHistory);
		DeleteBtnInPastHistory.click();
	}

	public void verifyPastHistoryAfterDelete() {
		Assert.assertTrue(loginPage.getDriver()
				.findElement(By.xpath("(//tr[@data-ng-repeat='data in patient.PastMedicalHistory'])[1]"))
				.getAttribute("class").contains("strikeOff"));
	}

	public void enterNotes(String note) {
		loginPage.waitForElementToBeClickable(notes);
		notes.sendKeys(note);
	}

	public void clickOnSaveBtnOfPastHistory() {
		loginPage.waitForElementToBeClickable(saveBtn);
		saveBtn.click();
	}
	
	public void clickOnAddBtnPastHistory() {
//			loginPage.scrollPage(500);
		    loginPage.waitForSpinnerToDisappear();
			loginPage.scrollToView(addBtnPastHistory);
			loginPage.waitForPageLoad();
			addBtnPastHistory.click();
	}

//	public void clickOnAddBtnPastHistory() {
//		try {
//			Thread.sleep(3000);
//			loginPage.scrollPage(500);
//			loginPage.waitForPageLoad();
//			addBtnPastHistory.click();
//		} catch (NoSuchElementException|InterruptedException e1) {
//			e1.printStackTrace();
//		}
//	}

	public void clickOnAdult() {
		loginPage.waitForElementToBeClickable(adult);
		adult.click();
	}

	public void clickOnPedo() {
		loginPage.waitForElementToBeClickable(pedo);
		pedo.click();
	}

	public void clickOnMixed() {
		loginPage.waitForElementToBeClickable(mixed);
		mixed.click();
	}

	public void clickOnAllTeeth() {
		loginPage.waitForElementToBeClickable(allToothCheckBox);
		allToothCheckBox.click();
	}

	public void isAllTeethIsSelected() {
		loginPage.waitForElementToBeClickable(allToothCheckBox);
		Assert.assertTrue(!allToothCheckBox.isSelected());
	}

//	public void selectTeeth(String teethNo) {
//		WebElement teeth = loginPage.getDriver().findElement(By.id(teethNo));
//		loginPage.waitForElementToBeClickable(teeth);
//		teeth.click();
//	}
	
	public void selectTeeth(String[] teeths) {
		for(int i =0;i<teeths.length;i++)
		{
			String teethNo = teeths[i];
			WebElement teeth = loginPage.getDriver().findElement(By.id(teethNo));
			loginPage.waitForElementToBeClickable(teeth);
			teeth.click();
		}
		
	}
	
	public void verifyTeethIsSelected(String[] teeths) {
		for(int i =0;i<teeths.length;i++)
		{
			String teethNo = teeths[i];
			WebElement teeth = loginPage.getDriver().findElement(By.id(teethNo));
			loginPage.waitForElementToBeClickable(teeth);
			Assert.assertTrue(teeth.getAttribute("class").contains("selected"));
		}
	}

//	public void verifyTeethIsSelected(String teethNo) {
//		WebElement teeth = loginPage.getDriver().findElement(By.id(teethNo));
//		loginPage.waitForElementToBeClickable(teeth);
//		Assert.assertTrue(teeth.getAttribute("class").contains("selected"));
//	}

	public void pedoCheckInMixedAfterSelectedInPedo() {
		boolean flag;
		if (flag = teethsUpperPedo.size() == teethslowerPedo.size()) {
			for (int i = 0; i < teethslowerPedo.size(); i++) {
				flag = teethsUpperPedo.get(i).getAttribute("class").contains("selected")
						&& teethslowerPedo.get(i).getAttribute("class").contains("selected");
				Assert.assertTrue(flag);
			}
		} else {
			Assert.assertTrue(flag);
		}
	}

	public void adultCheckInMixedAfterSelectedInAdult() {
		if (teethslowerAdult.size() == teethsUpperAdult.size()) {
			for (int i = 0; i < teethslowerAdult.size(); i++) {
				boolean flag = teethslowerAdult.get(i).getAttribute("class").contains("selected")
						&& teethsUpperAdult.get(i).getAttribute("class").contains("selected");
				Assert.assertTrue(flag);
			}
		}
	}

	public void pedoCheckedAllTeethDeselected() {
		if (teethsUpperPedo.size() == teethslowerPedo.size()) {
			for (int i = 0; i < teethslowerPedo.size(); i++) {
				boolean flag = !teethsUpperPedo.get(i).getAttribute("class").contains("selected")
						&& !teethslowerPedo.get(i).getAttribute("class").contains("selected");
				Assert.assertTrue(flag);
			}
		}
	}

	public void adultCheckedAllTeethDeselected() {
		if (teethslowerAdult.size() == teethsUpperAdult.size()) {
			for (int i = 0; i < teethslowerAdult.size(); i++) {
				boolean flag = !teethslowerAdult.get(i).getAttribute("class").contains("selected")
						&& !teethsUpperAdult.get(i).getAttribute("class").contains("selected");
				Assert.assertTrue(flag);
			}
		}
	}
	
	
	public void verifySelectedTeethPastHistory(String[] teeths) {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(dateColumn);
		if(checkedElementDisplayed(dateColumn)&&checkedElementDisplayed(toothNoColumn)&&checkedElementDisplayed(createdByColumn)&&checkedElementDisplayed(notesColumn)&&checkedElementDisplayed(actionColumn)){
			String expectedTeeths = toothNoInPasstHistory.getText();
			int noOfTeeth = teeths.length;
			if(noOfTeeth > 1)
			{
				String[] expectedTeethsArr = expectedTeeths.split(",");
				Assert.assertEquals(expectedTeethsArr.length, noOfTeeth);
				for(int i = 0;i<noOfTeeth;i++)
				{
					System.out.println(expectedTeethsArr[i]);
					Assert.assertEquals(expectedTeethsArr[i], teeths[i]);
				}
			}
			else
			{
				Assert.assertEquals(expectedTeeths.length(), teeths.length);
				Assert.assertEquals(expectedTeeths, teeths[0]);
			}
			
		}else {
			Assert.fail();
		}
	}

//	public void verifySelectedTeethPastHistory(String firstSelect, String secondSelect) {
//		loginPage.waitForPageLoad();
//		loginPage.waitForElementToBeClickable(dateColumn);
//		if(checkedElementDisplayed(dateColumn)&&checkedElementDisplayed(toothNoColumn)&&checkedElementDisplayed(createdByColumn)&&checkedElementDisplayed(notesColumn)&&checkedElementDisplayed(actionColumn)){
//			String expected = firstSelect + "," + secondSelect;
//			String actual = toothNoInPasstHistory.getText();
//			Assert.assertEquals(actual, expected);
//		}else {
//			Assert.fail();
//		}
//	}
	
	

	public void verifyNotesPastHistory(String notes) {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(dateColumn);
		if(checkedElementDisplayed(dateColumn)&&checkedElementDisplayed(toothNoColumn)&&checkedElementDisplayed(createdByColumn)&&checkedElementDisplayed(notesColumn)&&checkedElementDisplayed(actionColumn)){
			String actual = notesPastHistory.getText();
			Assert.assertEquals(actual, notes);
		}else {
			Assert.fail();
		}
	}

	public void checkDeleteBtnPastHistory(){
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(actionBtn);
		Assert.assertTrue(checkedElementDisplayed(actionBtn));
	}
	public void clickMedication() {
		loginPage.waitForElementToBeClickable(medication);
		medication.click();
	}

	public void medicationAllRdioBtn() {
		loginPage.waitForElementToBeClickable(yesOnMedication);
		boolean flag = checkedElementDisplayed(yesOnMedication) && checkedElementDisplayed(noOnMedication)
				&& checkedElementDisplayed(naOnMedication);
		Assert.assertTrue(flag);
	}
	
	public void clickOnMedicationCheckUpYesBtn() {
		loginPage.waitForElementToBeClickable(medicationCheckUpYesBtn);
		medicationCheckUpYesBtn.click();
	}

	public void checkMedicationDetailsTextField() {
//		loginPage.WaitTillElementIsPresent(enterMedicationDetails);
		loginPage.waitForElementVisibility(enterMedicationDetails, 4000);
		Assert.assertTrue(checkedElementDisplayed(enterMedicationDetails));
	}

	public void clickOnDentalCheckUP() {
		loginPage.waitForElementToBeClickable(dentalCheckup);
		dentalCheckup.click();
	}

	public void dentalCheckUpAllRdioBtn() {
		loginPage.waitForElementToBeClickable(moreThanOneYr);
		boolean flag = checkedElementDisplayed(lessThanSixMonth) && checkedElementDisplayed(moreThanOneYr)
				&& checkedElementDisplayed(moreThanSixMonth);
		Assert.assertTrue(flag);
	}
	
	public void selectDentalCheckUpOptions(String duration)
	{
		loginPage.getDriver().findElement(By.xpath("//label[contains(text(),'"+duration+"')]")).click();
	}
	
	

	public void clickOnAllergies() {
		loginPage.waitForElementToBeClickable(allergies);
		allergies.click();
	}

	public void allRadioInsideAllergies() {
		loginPage.waitForElementToBeClickable(allergiesYesBtn);
		Assert.assertTrue(checkedElementDisplayed(allergiesNABtn)&&checkedElementDisplayed(allergiesNoBtn)&&checkedElementDisplayed(allergiesYesBtn));
	}

	public void checkAllergyDetailsTextField() {
		loginPage.WaitTillElementIsPresent(enterAllergyDetails);
		Assert.assertTrue(checkedElementDisplayed(enterAllergyDetails));
	}

	public void clickOnAllergiesYesBtn() {
		loginPage.waitForElementToBeClickable(allergiesYesBtn);
		allergiesYesBtn.click();
	}
	
//	public void addAllergiesDetails(String allergyDetails)
//	{
//		
//		loginPage.waitForElementToBeClickable(patientNotes);
//		allrgTxtArea.sendKeys(allergyDetails);
//	}

	public void clickOnGroup() {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(group);
		group.click();
	}

	public void enterGroup(String groupName) {
		loginPage.waitForElementToBeClickable(groupTextfields);
		groupTextfields.sendKeys(groupName);
	}
	
	public void clickOnPatientNotes() {
		    loginPage.waitForSpinnerToDisappear();
		    loginPage.WaitTillElementIsPresent(patientNotes);
			loginPage.waitForElementToBeClickable(patientNotes);
			patientNotes.click();
	}

//	public void clickOnPatientNotes() {
//		loginPage.waitForPageLoad();
//		try {
//			Thread.sleep(3000);
//			loginPage.waitForElementToBeClickable(patientNotes);
//			patientNotes.click();
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//	}

	public void enterPatientNotes(String PatientNotes) {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(patientNotesTextFields);
		patientNotesTextFields.sendKeys(PatientNotes);
	}

	public void enterPinCode(String pincode) {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(reachtime);
		pin.sendKeys(pincode);
	}

	public void enterCity(String cityName) {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(reachtime);
		city.sendKeys(cityName);
	}

	public void enterInReachTime(String reachTimeInformation) {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(reachtime);
		reachtime.sendKeys(reachTimeInformation);
	}

	public void selectOccupation(String occupationInfo) {
		loginPage.waitForPageLoad();
		loginPage.waitForElementVisibility(occupation, 5000);
		loginPage.waitForElementToBeClickable(occupation);
		loginPage.waitForSpinnerToDisappear();
		loginPage.selectFromDropDownByVisibleText(occupation, occupationInfo);
	}
	
	public void selectState(String stateName) {
		loginPage.waitForPageLoad();
		loginPage.waitForElementVisibility(state, 5000);
		loginPage.waitForElementToBeClickable(state);
		loginPage.selectFromDropDownByVisibleText(state, stateName);
	}

	public void enterAddress(String address) {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(occupation);
		addressTextBox.sendKeys(address);
	}

	public void enterGuardianName(String gurdianName) {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(guardianName);
		guardianName.clear();
		guardianName.sendKeys(gurdianName);
	}

	public void enterGuardianNo(String gurdianNo) {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(guardianContact);
		guardianContact.clear();
		guardianContact.sendKeys(gurdianNo);
	}

	public void guardianDetailsHide(){
		loginPage.waitForPageLoad();
		Assert.assertEquals(guardianContactSet.size(),0);
	}

	public void scrollDown() {
		try {
			loginPage.scrollPage(-600);
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void scrollUp() {
		try {
			loginPage.scrollPage(500);
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void clickOnApproveSave() {
		loginPage.waitForPageLoad();
		loginPage.waitForSpinnerToDisappear();
		loginPage.waitForElementVisibility(approveSave,5000);
		loginPage.moveToElementAndClick(approveSave);
		
//			loginPage.waitForElementToBeClickable(approveSave);
//			approveSave.click();
	}

//	public void clickOnApproveSave() {
//		try {
//			Thread.sleep(5000);
//			loginPage.waitForPageLoad();
//			loginPage.waitForElementToBeClickable(approveSave);
//			approveSave.click();
//			Thread.sleep(10000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//	}
	
	public void enterPatientName(String name) {
		loginPage.waitForPageLoad();
		loginPage.waitForElementVisibility(patientName,5000);
		loginPage.waitForElementToBeClickable(patientName);
			patientName.clear();
			patientName.sendKeys(name);
	}
	
	public void enterEmailAddress(String emailText) {
		loginPage.waitForPageLoad();
		loginPage.waitForElementVisibility(patientName,5000);
		loginPage.waitForElementToBeClickable(patientName);
			email.clear();
			email.sendKeys(emailText);
	}

//	public void enterPatientName(String name) {
//		loginPage.waitForPageLoad();
//		loginPage.waitForElementToBeClickable(patientName);
//		try {
//			Thread.sleep(4000);
//			patientName.clear();
//			patientName.sendKeys(name);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//	}

	public void enterAlternateMobileNO(String alterNateMoblieNum) {
		loginPage.waitForPageLoad();
		loginPage.waitForElementVisibility(alternateContact, 5000);
		loginPage.waitForElementToBeClickable(alternateContact);
		alternateContact.clear();
		alternateContact.sendKeys(alterNateMoblieNum);
	}

	public void enterMobileNO(String MoblieNum) {
		loginPage.waitForSpinnerToDisappear();
//		loginPage.waitForPageLoad();
		loginPage.waitForElementVisibility(mobile, 5000);
		loginPage.waitForElementToBeClickable(mobile);
		mobile.clear();
		mobile.sendKeys(MoblieNum);
	}

	public void enterAlterContactName(String alterNateContactName) {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(alternateName);
		alternateName.clear();
		alternateName.sendKeys(alterNateContactName);
	}

	public void enterAge(String Age) {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(age);
		age.clear();
		age.sendKeys(Age);
	}

	public void checkAgeCalculation(String dob) {
		loginPage.waitForElementToBeClickable(DOB);
		DOB.clear();
		DOB.sendKeys(dob);
		if (loginPage.getDriver() instanceof JavascriptExecutor) {
			JavascriptExecutor javascriptExecutor = (JavascriptExecutor) loginPage.getDriver();
			Object obj = javascriptExecutor.executeScript("return document.getElementById('age').value;");
			String str = (String) obj;
			Date date = new Date();
			SimpleDateFormat simple = new SimpleDateFormat("dd-MM-yyyy");
			String currentDate = simple.format(date);
			String[] split = currentDate.split("-");
			String currentYear = split[2];
			String[] year = dob.split("-");
			String dobYear = year[2];
			int actual = Integer.parseInt(currentYear) - Integer.parseInt(dobYear);
			int expected = Integer.parseInt(str);
			Assert.assertEquals(actual, expected);
		}
	}

	public void enterPatientNameMsg(){
		loginPage.waitForPageLoad();
		Assert.assertTrue(checkedElementDisplayed(enterPatientNameMsg));
	}
		
	public void selectGenderMale(String gender) {
    	loginPage.waitForPageLoad();
		loginPage.waitForElementVisibility(genderMale,5000);
		loginPage.hoverOnElement(genderMale);
		if(gender.equals("Male"))
		{
			loginPage.waitForElementToBeClickable(genderMale);
			loginPage.waitForSpinnerToDisappear();
     		loginPage.moveToElementAndClick(genderMale);
//			genderMale.click();
		}
		else
		loginPage.moveToElementAndClick(genderFemale);
//		genderFemale.click();
	}

	public void landlineMaxLength() {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(landline);
		int expected = 15;
		int actual = Integer.parseInt(landline.getAttribute("maxlength"));
		Assert.assertEquals(actual, expected);
	}

	public void byDefaultCountry(String countryName) {
		loginPage.waitForElementToBeClickable(country);
		Assert.assertTrue(loginPage.firstSelectedOption(country).contains(countryName));
	}

	public void verifyStatesAccordingToCountry(String stateName) {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(state);
		Assert.assertTrue(state.getText().contains(stateName));
	}

	public void selectCountry(String countryName) {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(country);
		try {
			Thread.sleep(3000);
			loginPage.selectFromDropDownByVisibleText(country, countryName);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void verifyCharacterLimitOfPinText() {
		loginPage.waitForElementToBeClickable(pin);
		Assert.assertTrue(pin.getAttribute("maxlength").contains("10"));
	}

	public void clickOnMedicalHistory() {
		loginPage.waitForElementToBeClickable(medicalHistory);
		medicalHistory.click();
	}

	public void selectMedicalHistory(String Disease, String level) {
		loginPage.waitForPageLoad();
		WebElement radioBtn = loginPage.getDriver()
				.findElement(By.xpath("//label[contains(text(),'" + Disease + "')]/..//label[text()='" + level + "']"));
		radioBtn.click();
	}
	
	public void selectAllMedicalHistory(String options){
		loginPage.waitForPageLoad();
		for(int i = 2; i<=medicalHistoryDomains.size();i++)
		{
		
			if(i == 3)
			{
				continue;
			}
			else
			{
			 loginPage.getDriver()
			 .findElement(By.xpath("//div[@class='form-group col-xs-12 col-sm-6 col-md-6 col-lg-6 ng-scope']["+i+"]//input/..//label[text()='"+options+"']")).click();
			}
		}
	}
	
	public void verifyEditPatientName(String patient_name) {
		loginPage.waitForPageLoad();
		loginPage.waitForSpinnerToDisappear();
		loginPage.waitForElementToBeClickable(patientName);
		JavascriptExecutor javascriptExecutor = (JavascriptExecutor) loginPage.getDriver();
		Object obj = javascriptExecutor.executeScript("return document.getElementById('name').value;");
		String actual = (String) obj;
		Assert.assertEquals(actual,patient_name);
	}
	
	public void editPatientName(String patient_name) {
		loginPage.waitForPageLoad();
		loginPage.waitForSpinnerToDisappear();
		loginPage.waitForElementToBeClickable(patientName);
		patientName.clear();
		patientName.sendKeys(patient_name);
	}
	
	public void verifyEditMobile(String patient_mobile) {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(patientName);
		JavascriptExecutor javascriptExecutor = (JavascriptExecutor) loginPage.getDriver();
		Object obj = javascriptExecutor.executeScript("return document.getElementById('mobile').value;");
		String actual = (String) obj;
		Assert.assertEquals(actual,patient_mobile);
	}
	
	
	public void editMobile(String patient_mobile) {
		loginPage.waitForPageLoad();
		loginPage.waitForSpinnerToDisappear();
		loginPage.waitForElementToBeClickable(mobile);
		mobile.clear();
		mobile.sendKeys(patient_mobile);
	}
	
	public void verifyEditEmail(String patient_email) {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(email);
		JavascriptExecutor javascriptExecutor = (JavascriptExecutor) loginPage.getDriver();
		Object obj = javascriptExecutor.executeScript("return document.getElementById('email').value;");
		String actual = (String) obj;
		Assert.assertEquals(actual,patient_email);
	}
	
	public void editEmail(String patient_email) {
		loginPage.waitForPageLoad();
		loginPage.waitForSpinnerToDisappear();
		loginPage.waitForElementToBeClickable(email);
		email.clear();
		email.sendKeys(patient_email);
	}
	

	public void editAge(String patient_age) {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(patientName);
		JavascriptExecutor javascriptExecutor = (JavascriptExecutor) loginPage.getDriver();
		Object obj = javascriptExecutor.executeScript("return document.getElementById('age').value;");
		String actual = (String) obj;
		Assert.assertEquals(actual.trim(),patient_age.trim());
	}
	public void editAlternateName(String patient_alternate_mobile) {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(patientName);
		JavascriptExecutor javascriptExecutor = (JavascriptExecutor) loginPage.getDriver();
		Object obj = javascriptExecutor.executeScript("return document.getElementById('alternateName').value;");
		String actual = (String) obj;
		Assert.assertEquals(actual,patient_alternate_mobile);
	}
	public void editAlternateMobile(String patient_alternate_mobile) {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(alternateName);
		JavascriptExecutor javascriptExecutor = (JavascriptExecutor) loginPage.getDriver();
		Object obj = javascriptExecutor.executeScript("return document.getElementById('alternateContact').value;");
		String actual = (String) obj;
		Assert.assertEquals(actual,patient_alternate_mobile);
	}
	
	public boolean checkCallCheckBoxChecked(){
		loginPage.WaitTillElementIsPresent ( callCheckbox );
		return callCheckbox.isSelected ();
	}
	public boolean checkSMSCheckBoxChecked(){
		loginPage.WaitTillElementIsPresent ( smsCheckbox );
		return  smsCheckbox.isSelected ();

	}
	public boolean checkEmailCheckBoxChecked(){
		loginPage.WaitTillElementIsPresent ( emailCheckbox );
		return  emailCheckbox.isSelected ();

	}

	public boolean checkEmailCheckBoxDisable(){
		loginPage.WaitTillElementIsPresent ( emailCheckbox );
		if(emailCheckbox.getAttribute ( "disabled" ).equals ( "true" )){
			return true;
		}
		else {
			return false;
		}
	}
	private boolean checkedMandatory(WebElement webElement) {
		return (webElement.getAttribute("class").contains("required"));
	}

	private boolean checkedElementDisplayed(WebElement webElement) {
		return (webElement.isDisplayed());
	}
}
