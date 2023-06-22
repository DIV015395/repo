package com.prm.docdashboard.pageobject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.prm.pageobjects.utils.PCDriver;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Ajit
 *
 */
public class PatientDashboardPage {
	private PCDriver loginPage;

	public PatientDashboardPage(PCDriver loginPage) {
		this.loginPage = loginPage;
		PageFactory.initElements(loginPage.getDriver(), this);
	}

	@FindBy(id = "patientDasbboardALId")
	private WebElement appOnPdList;
	@FindBy(xpath = "//div[@id='profileview']//button[@class='close']")
	private WebElement closeBTn;
	@FindBy(id = "patientTimeAmPm")
	private WebElement reachTime;
	@FindBy(id = "patientOccupation")
	private WebElement patientOccupation;
	@FindBy(id = "patientAddress")
	private WebElement patientAddress;
	@FindBy(id = "patientCity")
	private WebElement patientCity;
	@FindBy(id = "patientProfileEditBtn")
	private WebElement patientProfileEditBtn;
	@FindBy(id = "patientDasbboardWDId")
	private WebElement profileViewBtn;

	/*------------Patient Profile View Section-------*/
	@FindBy(className = "patientName")
	private WebElement patientName;
	@FindBy(id = "patientPublicId")
	private WebElement patientId;
	@FindBy(id = "profileviewLabel")
	private WebElement patientDetailsHeadInView;
	@FindBy(xpath="//a[text()='Info']")
	private WebElement patientProfileInfo;
	@FindBy(id = "patientInfoHistory")
	private WebElement patientProfileHistory;
	@FindBy(xpath = "//div[contains(text(),'Patient Information')]")
	private WebElement patientInformation;
	@FindBy(xpath="//b[text()='Gender']")
	private WebElement genderLabelInView;
	@FindBy(id = "patientGender")
	private WebElement patientGenderValue;
	@FindBy(xpath="//b[text()='Date of Birth']")
	private WebElement dobLabelInView;
	@FindBy(id = "patientDOB")
	private WebElement patientDOBValue;
	@FindBy(xpath="//b[text()='Age']")
	private WebElement ageLabelInView;
	@FindBy(id = "patientAge")
	private WebElement patientAgeValue;
	@FindBy(xpath="//b[text()='Email']")
	private WebElement emailLabelInView;
	@FindBy(id = "patientEmail")
	private WebElement patientEmailValue;
	@FindBy(xpath="//b[text()='Mobile']")
	private WebElement mobileLabelInView;
	@FindBy(id = "patientMobile")
	private WebElement patientMobile;
	@FindBy(xpath="//b[text()='Landline']")
	private WebElement landlineLabelInView;
	@FindBy(id = "patientLandline")
	private WebElement patientLandlineValue;
	@FindBy(xpath="//b[text()='Alternate Contact Name']")
	private WebElement alternateNameLabelInView;
	@FindBy(id = "patientEmergencyNext")
	private WebElement patientAlternateNameValue;
	@FindBy(xpath="//b[text()='Alternate Contact no.']")
	private WebElement alternateContactLabelInView;
	@FindBy(id = "patientRelationshipContact")
	private WebElement patientAlternateContactValue;
	@FindBy(xpath="//b[text()='Primary Dentist']")
	private WebElement primaryDentistLabelInView;
	@FindBy(id = "pDentist")
	private WebElement patientDentistValue;
	@FindBy(xpath="//b[text()='Best time to reach you']")
	private WebElement bestTimeReachLabelInView;
	@FindBy(id = "patientTimeAmPm")
	private WebElement patientTimeReachValue;
	@FindBy(xpath="//b[text()='Occupation']")
	private WebElement occupationLabelInView;
	@FindBy(id = "patientOccupation")
	private WebElement patientOccupationValue;
	@FindBy(xpath = "//div[contains(text(),'Address Information')]")
	private WebElement patientAddressInformation;
	@FindBy(xpath="//b[text()='Address']")
	private WebElement addressLabelInView;
	@FindBy(id = "patientAddress")
	private WebElement patientAddressValue;
	@FindBy(xpath="//b[text()='State']")
	private WebElement stateLabelInView;
	@FindBy(id = "patientState")
	private WebElement patientStateValue;
	@FindBy(xpath="//b[text()='Country']")
	private WebElement countryLabelInView;
	@FindBy(id = "patientCountry")
	private WebElement patientCountryValue;
	@FindBy(xpath="//b[text()='City']")
	private WebElement cityLabelInView;
	@FindBy(id = "patientCity")
	private WebElement patientCityValue;
	@FindBy(xpath="//b[text()='Postal Code']")
	private WebElement postalCodeLabelInView;
	@FindBy(id = "patientPostalCode")
	private WebElement patientPostalCodeValue;
	@FindBy(xpath = "//div[contains(text(),'Past History Information')]")
	private WebElement patientPastHistoryInformation;
	@FindBy(xpath="//div[@id='pastHistory']//th[text()='Date']")
	private WebElement dateLabelInView;
	@FindBy(xpath="//div[@id='pastHistory']//th[text()='Tooth no.(s)']")
	private WebElement toothNoLabelInView;
	@FindBy(xpath="//div[@id='pastHistory']//div[contains(@class,'tdvTooth')]")
	private WebElement patientToothNoInPastHistoryValue;
	@FindBy(xpath="//div[@id='pastHistory']//th[text()='Notes']")
	private WebElement notesLabelInView;
	@FindBy(xpath="//div[@id='pastHistory']//div[contains(@class,'tdvNotes')]")
	private WebElement patientNotesInPastHistoryValue;
	@FindBy(xpath = "//div[contains(text(),'Group Information')]")
	private WebElement patientGroupInformation;
	@FindBy(id = "patientGroup")
	private WebElement patientGroupValue;
	@FindBy(xpath = "//div[contains(text(),'Patient Notes')]")
	private WebElement patientPatientNotes;
	@FindBy(id = "patientNotes")
	private WebElement patientNotesValue;

	//-------Patient History Section --------//
	@FindBy(id = "patientInfoHistory")
	private WebElement patientHistoryTab;
	@FindBy(xpath = "//table[@id='communicationHistoryTable']//th[text()='Date']")
	private WebElement dateLabelInPatientHistory;
	@FindBy(xpath = "//table[@id='communicationHistoryTable']//th[text()='Created/Updated by']")
	private WebElement createdByLabelInPatientHistory;
	@FindBy(xpath = "//table[@id='communicationHistoryTable']//th[text()='Name']")
	private WebElement nameLabelInPatientHistory;
	@FindBy(xpath = "//table[@id='communicationHistoryTable']//th[text()='Mobile']")
	private WebElement mobileLabelInPatientHistory;
	@FindBy(xpath = "//table[@id='communicationHistoryTable']//th[text()='Email']")
	private WebElement emailLabelInPatientHistory;
	@FindBy(xpath = "(//table[@id='communicationHistoryTable']//span[@class='hightLighttext'])[1]")
	private WebElement updatedPatientName;


	@FindBy(id = "patientProfilePrintBtn")
	private WebElement print;
	@FindBy(xpath = "//div[@id='pastHistory']//div[contains(@class,'tdvTooth')]")
	private WebElement teethInPastHistory;
	@FindBy(id = "patientGroup")
	private WebElement patientGroup;
	@FindBy(id = "patientNotes")
	private WebElement patientNotes;
	// ---------------chief complaint webelement and reusable method--------
	@FindBy(id = "patientDasbboardCCList")
	private WebElement chiefComplaintListBtn;

	@FindBy(id = "patientDasbboardCCId")
	private WebElement chiefComplaintAddBtn;
	@FindBy(id = "patientAppointmentCallId")
	private WebElement appAddBtn;
	@FindBy(id = "oeAddBtn")
	private WebElement oralExamAddBtn;
	@FindBy(id = "oeListBtn")
	private WebElement oralExamListBtn;
	@FindBy(id = "diagAddBtn")
	private WebElement diagnosticsTestAdd;
	@FindBy(id = "diagListBtn")
	public WebElement diagnosticsTestList;
	@FindBy(id = "patientDasbboardPreAddId")
	public WebElement prescriptionTestAdd;
	@FindBy(id="patientDasbboardPreListId")
	public WebElement prescriptionTestList;
	@FindBy(id="wonlistingaccess")
	public WebElement labWorkOrderBtn;
	@FindBy(id = "tpAddBtn")
	private WebElement TreatmentPlanAddBtn;
	@FindBy(id = "tpListBtn")
	private WebElement TreatmentPlanListBtn;
	@FindBy(id = "wdAddBtn")
	private WebElement WorkDoneAddBtn;
	@FindBy(id="wdhListBtn")
	private WebElement workDoneHistoryBtn;
	@FindBy(id="patientInvoiceCallId")
	private WebElement invoiceListBtn;
	@FindBy(id="patientRecieptCallId")
	private WebElement receiptListBtn;
	@FindBy(id="patientDasbboardFList")
	private WebElement filesListingBtn;
	@FindBy(id="patientDasbboardFId")
	private WebElement filesAddBtn;
	@FindBy(xpath = "//p[contains(text(),'I understand that this patient has amount overdue, but I want to continue with the treatment for this patient!')]")
	public WebElement amountDuePopup;
	@FindBy(xpath = "//div[@id='dueWarningView']//button[@id='dueYes']")
	public WebElement dueWarningYes;
	@FindBy(xpath = "//div[@class='modal fade in']")
	public List<WebElement> dueWarningPopup;
	@FindBy(xpath = "//a[@class='inf_flag_blck green']")
	public WebElement greenFlagEdit;
	@FindBy(id="urgentPatientNamePidPD")
	private WebElement patientNamePage;
	@FindBy(xpath = "//span[contains(text(),'Medical Problems')]/following-sibling::span")
	private WebElement medicalProblemAlert;
	@FindBy(xpath = "//span[contains(text(),'Medications')]/following-sibling::span")
	private WebElement medicationAlert;
	@FindBy(xpath = "//span[contains(text(),'Allergies')]/following-sibling::span")
	private WebElement allergyAlert;
	@FindBy(xpath = "//a[@class='inf_flag_blck red']")
	private WebElement redFlag;
	
	/*
	 * Product Sale Button WebElement within Patient Dashboard
	 */
	@FindBy(xpath = "//span[@class='cmnicons prdcts']")
	private WebElement productSaleButton;

	public void checkPatientRedFlag(){
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(redFlag);
		Assert.assertTrue(checkedWebElementDisplayed(redFlag));
	}
	public void patientNamePatientDashboard(String patient_name){
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(patientNamePage);
		String[] split = patientNamePage.getText().trim().split(":");
		String patient = split[0].trim();
		String patientId = split[1].trim();
		Assert.assertTrue(patient.equalsIgnoreCase(patient_name)&&!(patientId.contains("NA")||patientId.contains("null")));
	}
	public void openCovidForm(){
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(greenFlagEdit);
		greenFlagEdit.click();
	}
	
	public void hideDueWarningPopup() {
		loginPage.waitForSpinnerToDisappear();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if(amountDuePopup.isDisplayed()) {
			dueWarningYes.click();
		}
	}

//	public void hideDueWarningPopup() {
//		loginPage.waitForPageLoad();
//		if(amountDuePopup.isDisplayed()) {
//			try {
//				Thread.sleep(5500);
//				dueWarningYes.click();
//				//			for (WebElement ele:dueWarningYes) {
//				//				dueWarningYes.get(0).click();
//				//			}
//				Thread.sleep(3000);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}
//	}

	public void clickOnFilesAdd() {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(filesAddBtn);
		try {
			filesAddBtn.click();
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void clickOnFilesList() {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(filesListingBtn);
		try {
			filesListingBtn.click();
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void clickOnInvoiceList() {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(invoiceListBtn);
		try {
			invoiceListBtn.click();
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void clickOnReceiptList() {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(receiptListBtn);
		try {
			receiptListBtn.click();
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void clickOnWorkDoneAdd() {
		loginPage.waitForElementVisibility(WorkDoneAddBtn,4000);
		WorkDoneAddBtn.click();
		loginPage.waitForSpinnerToDisappear();
	}

//	public void clickOnWorkDoneAdd() {
//		loginPage.waitForPageLoad();
//		loginPage.waitForElementToBeClickable(WorkDoneAddBtn);
//		try {
//			WorkDoneAddBtn.click();
//			Thread.sleep(6000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

	public void clickOnWorkDoneHistory() {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(workDoneHistoryBtn);
		try {
			workDoneHistoryBtn.click();
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void clickOnTreatmentPlanListBtn() {
	    loginPage.waitForSpinnerToDisappear();
	    loginPage.waitForElementVisibility(TreatmentPlanListBtn, 4000);
		TreatmentPlanListBtn.click();
		loginPage.waitForSpinnerToDisappear();
		loginPage.waitForModalOverlayToDisappear();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}


//	public void clickOnTreatmentPlanListBtn() {
//		loginPage.waitForPageLoad();
//		loginPage.waitForElementToBeClickable(TreatmentPlanListBtn);
//		try {
//			TreatmentPlanListBtn.click();
//			Thread.sleep(3000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

	public void clickOnTreatmentPlanAddBtn() {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(TreatmentPlanAddBtn);
		try {
			TreatmentPlanAddBtn.click();
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void clickOnOralExamAdd() {
		loginPage.waitForPageLoad();
		loginPage.WaitTillElementIsPresent(oralExamAddBtn);
		oralExamAddBtn.click();
	}

	public void clickOnOralExamList() {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(oralExamListBtn);
		oralExamListBtn.click();
	}

	public void clickOnAppList() {
		loginPage.waitForSpinnerToDisappear();
//		loginPage.waitForPageToBecomeActive();
//		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(appOnPdList);
		appOnPdList.click();

	}

	public void clickOnAppAdd() {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(appAddBtn);
		try {
			Thread.sleep(2000);
			appAddBtn.click();
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	public void clickOnChiefComplaintAddBtn() {
		loginPage.waitForSpinnerToDisappear();
//		loginPage.waitForPageLoad();\loginPage
		loginPage.waitForElementVisibility(chiefComplaintAddBtn, 4000);
		loginPage.waitForElementToBeClickable(chiefComplaintAddBtn);
		chiefComplaintAddBtn.click();
		loginPage.waitForSpinnerToDisappear();
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void clickOnChiefComplaintlistBtn() {
		loginPage.waitForSpinnerToDisappear();
		loginPage.waitForElementVisibility(chiefComplaintListBtn, 4000);
		loginPage.waitForElementToBeClickable(chiefComplaintListBtn);
		chiefComplaintListBtn.click();
	}

//	public void clickOnChiefComplaintlistBtn() {
//		loginPage.waitForPageLoad();
//		loginPage.waitForElementToBeClickable(chiefComplaintListBtn);
//		try {
//			Thread.sleep(2000);
//			chiefComplaintListBtn.click();
//			Thread.sleep(6000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

	public void clickOnDiagnosticsTestAdd() {
		loginPage.waitForPageLoad();
		loginPage.WaitTillElementIsPresent(diagnosticsTestAdd);
		diagnosticsTestAdd.click();
	}

	public void clickOnDiagnosticsTestList() {
		loginPage.waitForPageLoad();
		loginPage.WaitTillElementIsPresent(diagnosticsTestList);
		diagnosticsTestList.click();
		loginPage.waitForSpinnerToDisappear();
	}

	public void clickOnPrescriptionTestAdd() {
		loginPage.waitForPageLoad();
		loginPage.WaitTillElementIsPresent(prescriptionTestAdd);
		prescriptionTestAdd.click();
	}
	public void clickOnPrescriptionTestList() {
		loginPage.waitForPageLoad();
		loginPage.WaitTillElementIsPresent(prescriptionTestList);
		prescriptionTestList.click();
	}
	public void clickOnLabWorkOrderAdd() {
		loginPage.waitForPageLoad();
		loginPage.WaitTillElementIsPresent(labWorkOrderBtn);
		labWorkOrderBtn.click();
	}
	
	
	/*---------------------reusable method-----------------------*/
	public void clickOnProfileViewBtn() {
		loginPage.waitForSpinnerToDisappear();
//		loginPage.waitForElementVisibility(profileViewBtn, 4000);
		loginPage.hoverOnElement(profileViewBtn);
		profileViewBtn.click();
	}

	public void clickProfileEditBtn() {
		loginPage.waitForSpinnerToDisappear();
		loginPage.waitForElementToBeClickable(patientProfileEditBtn);
		loginPage.hoverOnElement(patientProfileEditBtn);
		patientProfileEditBtn.click();
	}

	public void clickOnClosePopUp() {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(closeBTn);
    	closeBTn.click();
    	try {
    		Thread.sleep(2000);
    	}
    	catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/* These all are related to Patient Profile View*/
	public void verifyPatientProfileViewPopUp(String PatientName) {
		loginPage.waitForElementToBeClickable(patientName);
		Assert.assertTrue(patientName.getText().contains(PatientName));
	}

	public void checkAllDataInPatientProfileView(){
		loginPage.waitForPageLoad();
		Assert.assertTrue(checkedWebElementDisplayed(patientProfileInfo)&&checkedWebElementDisplayed(patientInformation)&&checkedWebElementDisplayed(genderLabelInView)
				&&checkedWebElementDisplayed(dobLabelInView)&&checkedWebElementDisplayed(ageLabelInView)&&checkedWebElementDisplayed(emailLabelInView)&&checkedWebElementDisplayed(mobileLabelInView)&&checkedWebElementDisplayed(primaryDentistLabelInView)
				&&checkedWebElementDisplayed(landlineLabelInView)&&checkedWebElementDisplayed(alternateContactLabelInView)&&checkedWebElementDisplayed(bestTimeReachLabelInView)&&checkedWebElementDisplayed(occupationLabelInView)
				&&checkedWebElementDisplayed(patientAddressInformation)&&checkedWebElementDisplayed(addressLabelInView)&&checkedWebElementDisplayed(stateLabelInView)&&checkedWebElementDisplayed(cityLabelInView)
				&&checkedWebElementDisplayed(countryLabelInView)&&checkedWebElementDisplayed(postalCodeLabelInView)&&checkedWebElementDisplayed(patientPastHistoryInformation)
				&&checkedWebElementDisplayed(dateLabelInView)&&checkedWebElementDisplayed(toothNoLabelInView)&&checkedWebElementDisplayed(notesLabelInView)&&checkedWebElementDisplayed(patientGroupInformation)
				&&checkedWebElementDisplayed(patientGroupValue)&&checkedWebElementDisplayed(patientPatientNotes));
	}

	public void verifyHeadInProfileView() {
		loginPage.waitForPageLoad();
		loginPage.waitForSpinnerToDisappear();
		loginPage.waitForElementVisibility(patientDetailsHeadInView,4000);
		Assert.assertTrue(checkedWebElementDisplayed(patientDetailsHeadInView));
	}

	public void verifyGenderOnPatientProfile(String gender) {
		loginPage.waitForElementToBeClickable(patientName);
		Assert.assertTrue(patientGenderValue.getText().contains(gender));
	}

	public void verifyPatientNameIDOnPatientProfile(String PatientName) {
		loginPage.waitForElementToBeClickable(patientName);
		String patientID = patientId.getText().trim();
		Assert.assertTrue(patientName.getText().contains(PatientName)&&!(patientID.contains("NA")||patientID.contains("null")||patientID.contains(" ")));
	}

	public void verifyPatientEmailOnPatientProfile(String patientEmail) {
		loginPage.waitForElementToBeClickable(patientEmailValue);
//		Assert.assertTrue(patientEmailValue.getText().contains(patientEmail));
		Assert.assertEquals(patientEmailValue.getText(),patientEmail.trim());
	}

	public void verifyPatientMoblieOnPatientProfile(String PatientMobile) {
		loginPage.waitForElementToBeClickable(patientMobile);
		Assert.assertTrue(patientMobile.getText().contains(PatientMobile));
	}

	public void verifyAgeOnPatientProfile(String PatientAge) {
		loginPage.waitForElementToBeClickable(patientAgeValue);
		loginPage.waitForElementVisibility(patientAgeValue, 4000);
		String patntAgeVal = patientAgeValue.getText().trim();
		Assert.assertEquals(patntAgeVal,PatientAge.trim());
	}

	public void verifyAlternateNameOnPatientProfile(String AlternateName) {
		loginPage.waitForElementToBeClickable(patientAlternateNameValue);
		Assert.assertTrue(patientAlternateNameValue.getText().contains(AlternateName));
	}

	public void verifyAlterNateNoOnPatientProfile(String AlternateNo) {
		loginPage.waitForElementToBeClickable(patientAlternateContactValue);
		Assert.assertTrue(patientAlternateContactValue.getText().contains(AlternateNo));
	}

	public void verifyPrimaryDentistOnPatientProfile(String primaryDentist) {
		loginPage.waitForElementToBeClickable(patientDentistValue);
		Assert.assertTrue(patientDentistValue.getText().contains(primaryDentist));
	}

	public void verifyTimeReachOnPatientProfile(String bestTime) {
		loginPage.waitForElementToBeClickable(patientTimeReachValue);
		Assert.assertTrue(patientTimeReachValue.getText().contains(bestTime));
	}

	public void verifyOccupationOnPatientProfile(String occupation) {
		loginPage.waitForElementToBeClickable(patientOccupationValue);
		Assert.assertTrue(patientOccupationValue.getText().contains(occupation));
	}

	public void verifyAddressOnPatientProfile(String address) {
		loginPage.waitForElementToBeClickable(patientAddressValue);
		Assert.assertTrue(patientAddressValue.getText().contains(address));
	}

	public void verifyStateOnPatientProfile(String state) {
		loginPage.waitForElementToBeClickable(patientStateValue);
		Assert.assertTrue(patientStateValue.getText().contains(state));
	}

	public void verifyCountryOnPatientProfile(String country) {
		loginPage.waitForElementToBeClickable(patientCountryValue);
		Assert.assertTrue(patientCountryValue.getText().contains(country));
	}

	public void verifyCityOnPatientProfile(String city) {
		loginPage.waitForElementToBeClickable(patientCityValue);
		Assert.assertTrue(patientCityValue.getText().contains(city));
	}

	public void verifyPostalCodeOnPatientProfile(String postalCode) {
		loginPage.waitForElementToBeClickable(patientPostalCodeValue);
		Assert.assertTrue(patientPostalCodeValue.getText().contains(postalCode));
		Assert.assertEquals(postalCode.trim(), patientPostalCodeValue.getText().trim());
	}
	
	public void verifyToothNoOnPatientProfile(String[] teeths) {
		loginPage.waitForPageLoad();
		String expectedTeeths = patientToothNoInPastHistoryValue.getText();
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
	}
	
	

//	public void verifyToothNoOnPatientProfile(String toothNo) {
//		loginPage.waitForElementToBeClickable(patientToothNoInPastHistoryValue);
//		Assert.assertTrue(patientToothNoInPastHistoryValue.getText().contains(toothNo));
//	}
	
	
	public void verifyNotesOnPastHistoryPatientProfile(String notes) {
		loginPage.waitForElementToBeClickable(patientNotesInPastHistoryValue);
		Assert.assertTrue(patientNotesInPastHistoryValue.getText().contains(notes));
	}

	public void verifyGroupNameOnPatientProfile(String group) {
		loginPage.waitForElementToBeClickable(patientGroup);
		Assert.assertTrue(patientGroup.getText().contains(group));
	}

	public void verifyPatientNotesOnPatientProfile(String patientNotes) {
		loginPage.waitForElementToBeClickable(patientNotesValue);
		Assert.assertTrue(patientNotesValue.getText().contains(patientNotes));
	}

	/**Patient History functions***/
	public void clickHistoryOnPatientProfile() {
		loginPage.waitForElementToBeClickable(patientHistoryTab);
		patientHistoryTab.click();
	}

	public void checkAllDataInPatientHistoryView(){
		loginPage.waitForPageLoad();
		Assert.assertTrue(checkedWebElementDisplayed(dateLabelInPatientHistory)&&checkedWebElementDisplayed(createdByLabelInPatientHistory)&&checkedWebElementDisplayed(nameLabelInPatientHistory)
				&&checkedWebElementDisplayed(mobileLabelInPatientHistory)&&checkedWebElementDisplayed(emailLabelInPatientHistory));
	}

	public void clickOnPrint() {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(print);
		loginPage.hoverOnElement(print);
		print.click();
	}

	public void selectedTeethVerificationPatientDashboard(String teeth) {
		loginPage.waitForElementToBeClickable(teethInPastHistory);
		Assert.assertTrue(teethInPastHistory.getText().contains(teeth));
	}
	
	public void medicalProblemAlertModal(String medicalProblem, String medicalStatus){
		loginPage.waitForElementToBeClickable(medicalProblemAlert);
		String medicalProblemOnAlert = medicalProblemAlert.getText();
		String medicalProbleAndStatus = medicalProblem+" "+medicalStatus;
		boolean result = medicalProblemOnAlert.contains(",");
		if(result)
		{
			String [] medicalProbAlrtArr = medicalProblemOnAlert.split(",");
			 ArrayList<String> medicalProbList = new ArrayList<String>(medicalProbAlrtArr.length);
			for(int i=0;i<medicalProbAlrtArr.length;i++)
			{
				medicalProbList.add(medicalProbAlrtArr[i].trim());
			}
			boolean isMedicalProblemPresent = medicalProbList.contains(medicalProbleAndStatus);
			Assert.assertEquals(isMedicalProblemPresent, true);
		}
		else
		{
			Assert.assertEquals(medicalProblemOnAlert.trim(), medicalProbleAndStatus);
		}
		
		
		
//		Assert.assertTrue(medicalProblemAlert.getText().contains(medication));
	}

	public void medicationAlertModal(String medication){
		loginPage.waitForElementToBeClickable(medicationAlert);
		Assert.assertTrue(medicationAlert.getText().contains(medication));
	}
	public void allergiesAlertModal(String allergies){
		loginPage.waitForElementToBeClickable(allergyAlert);
		Assert.assertTrue(allergyAlert.getText().contains(allergies));
	}
	
	public boolean verifyProductSalesButton(){
		loginPage.waitForPageLoad ();
		loginPage.WaitTillElementIsPresent ( productSaleButton );
		loginPage.waitForElementToBeClickable ( productSaleButton );
		return productSaleButton.isDisplayed ();
	}

	public void clickOnProductSaleButton(){
		loginPage.waitForPageLoad ();
		loginPage.WaitTillElementIsPresent ( productSaleButton );
		loginPage.waitForElementToBeClickable ( productSaleButton );
		productSaleButton.click ();
	}
	
	private boolean checkedWebElementDisplayed(WebElement webElement){
		return (webElement.isDisplayed());
	}
}
