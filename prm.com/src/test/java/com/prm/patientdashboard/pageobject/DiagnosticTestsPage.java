package com.prm.patientdashboard.pageobject;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import com.prm.pageobjects.utils.PCDriver;

public class DiagnosticTestsPage {
	private PCDriver loginPage;

	public DiagnosticTestsPage(PCDriver loginPage) {
		this.loginPage = loginPage;
		PageFactory.initElements(loginPage.getDriver(), this);
	}

	/* webelement of the diagnostic add page */
	@FindBy(xpath = "//div[contains(@class,'sideNavigation hidden-xs visible-sm visible-md visible-lg')]//i[contains(text(),'Treatment')]")
	private WebElement treatmentButton;
	@FindBy(xpath = "//div[contains(@class,'sideNavigation hidden-xs visible-sm visible-md visible-lg')]//i[contains(text(),'Oral Exam')]")
	private WebElement oralExamButton;
	@FindBy(xpath = "//div[contains(@class,'sideNavigation hidden-xs visible-sm visible-md visible-lg')]//i[contains(text(),'File')]")
	private WebElement fileButton;
	@FindBy(xpath = "//button[contains(text(),'IOPAR')]")
	private WebElement ioparButton;
	@FindBy(xpath = "//button[contains(text(),'Imaging')]")
	private WebElement imagingButton;
	@FindBy(xpath = "//button[contains(text(),'Blood')]")
	private WebElement bloodButton;
	@FindBy(xpath = "//button[contains(text(),'Urine')]")
	private WebElement urineButton;
	@FindBy(xpath = "//button[contains(text(),'Biochemical')]")
	private WebElement biochemicalButton;
	@FindBy(xpath = "//button[contains(text(),'Markers')]")
	private WebElement markersButton;
	@FindBy(xpath = "//span[@class='cmnicons sv-mdl']")
	private WebElement saveButton;
	@FindBy(xpath = "//button[@type='submit']/following-sibling::button[@class='close']")
	private WebElement closeButton;
	@FindBy(id = "testRemark")
	private WebElement diagnosticsNotes;
	@FindBy(xpath = "//span[contains(@class,'notestext')]")
	private WebElement notesLimitMsg;
//	@FindBy(xpath = "//div[contains(@class,'modal-header')]/h4[contains(@class,'modal-title')]")
//	private WebElement headerDiagnosis;
	
	@FindBy(xpath = "//div[contains(@class,'modal-header')]/..//h4")
	private WebElement headerDiagnosis;

	/* IOPAR Webelements */
	@FindBy(xpath = "//a[text()='Pedo']")
	private WebElement pedo;
	@FindBy(xpath = "//a[text()='Adult']")
	private WebElement adult;
	@FindBy(xpath = "//a[text()='Mixed']")
	private WebElement mixed;
	@FindBy(xpath = "//a[text()='Adult']/..")
	private WebElement selectedAdult;
	@FindBy(xpath = "//a[text()='Pedo']/..")
	private WebElement selectedPedo;
	@FindBy(xpath = "//a[text()='Mixed']/..")
	private WebElement selectedMixed;
	@FindBy(xpath = "//div[contains(@ng-include,'adultIOPAR.shtml')]//li")
	private List<WebElement> adultTeeths;
	@FindBy(xpath = "//div[contains(@ng-include,'pedoIOPAR.shtml')]//li")
	private List<WebElement> pedoTeeths;
	@FindBy(xpath = "//div[contains(@ng-include,'mixedIOPAR.shtml')]//li")
	private List<WebElement> mixedTeeths;

	/* Imaging WebElements */
	@FindBy(xpath = "//span[@class='ng-binding' and contains(text(),'OPG')]")
	private WebElement opg;
	@FindBy(xpath = "//span[@class='ng-binding' and contains(text(),'Lateral Cephalogram')]")
	private WebElement lateralCephalogram;
	@FindBy(xpath = "//span[@class='ng-binding' and contains(text(),'CBCT')]")
	private WebElement cBCT;
	@FindBy(xpath = "//span[@class='ng-binding' and contains(text(),'CECT')]")
	private WebElement cECT;
	@FindBy(xpath = "//span[@class='ng-binding' and contains(text(),'Bitewing')]")
	private WebElement bitewing;
	@FindBy(xpath = "//span[@class='ng-binding' and contains(text(),'Occlusal')]")
	private WebElement occlusal;
	@FindBy(xpath = "//span[@class='ng-binding' and contains(text(),'USG')]")
	private WebElement uSG;
	@FindBy(xpath = "//span[@class='ng-binding' and contains(text(),'Submentovertex')]")
	private WebElement submentovertex;
	@FindBy(xpath = "//span[@class='ng-binding' and contains(text(),'PA View')]")
	private WebElement pAView;
	@FindBy(xpath = "//span[@class='ng-binding' and contains(text(),'Occipeto-mental')]")
	private WebElement occipetoMental;

	/* Blood WebElements */
	@FindBy(xpath = "//span[text()='MCH']")
	private WebElement mCH;
	@FindBy(xpath = "//span[text()='MPV']")
	private WebElement mPV;
	@FindBy(xpath = "//span[text()='MCV']")
	private WebElement mCV;
	@FindBy(xpath = "//span[text()='MCHC']")
	private WebElement mCHC;
	@FindBy(xpath = "//span[text()='BT/CT']")
	private WebElement bT_CT;
	@FindBy(xpath = "//span[text()='Hematocrit']")
	private WebElement hematocrit;
	@FindBy(xpath = "//span[text()='Hemoglobin']")
	private WebElement hemoglobin;
	@FindBy(xpath = "//span[text()='Platlet Count']")
	private WebElement platletCount;
	@FindBy(xpath = "//span[text()='PT-INR']")
	private WebElement pT_INR;
	@FindBy(xpath = "//span[text()='RBC']")
	private WebElement rBC;
	@FindBy(xpath = "//span[text()='WBC']")
	private WebElement wBC;
	@FindBy(xpath = "//span[text()='RBS']")
	private WebElement rBS;
	@FindBy(xpath = "//span[text()='TLC']")
	private WebElement tLC;
	@FindBy(xpath = "//span[text()='DLC']")
	private WebElement dLC;
	@FindBy(xpath = "//span[text()='HbA1C']")
	private WebElement hbA1C;
	@FindBy(xpath = "//span[text()='Blood Sugar-Fasting']")
	private WebElement bloodSugarFasting;
	@FindBy(xpath = "//span[text()='Blood Sugar-PP']")
	private WebElement bloodSugarPP;

	/* Urine WebElements */
	@FindBy(xpath = "//span[text()='Urine Routine']")
	private WebElement urineRoutine;
	@FindBy(xpath = "//span[text()='Urine Microscopic']")
	private WebElement urineMicroscopic;

	/* Bio chemical WebElements */
	@FindBy(xpath = "//span[text()='LFT']")
	private WebElement lFT;
	@FindBy(xpath = "//span[text()='ALP']")
	private WebElement aLP;
	@FindBy(xpath = "//span[text()='ALT']")
	private WebElement aLT;
	@FindBy(xpath = "//span[text()='AST']")
	private WebElement aSt;
	@FindBy(xpath = "//span[text()='Bilirubin']")
	private WebElement bilirubin;
	@FindBy(xpath = "//span[text()='GGT']")
	private WebElement gGT;
	@FindBy(xpath = "//span[text()='Colour']")
	private WebElement colour;
	@FindBy(xpath = "//span[text()='Density']")
	private WebElement density;
	@FindBy(xpath = "//span[text()='P.H']")
	private WebElement pH;
	@FindBy(xpath = "//span[text()='Albumin']")
	private WebElement albumin;

	/* markers WebElements */
	@FindBy(xpath = "//span[text()='C-RP']")
	private WebElement C_RP;
	@FindBy(xpath = "//span[text()='Viral Antibodies (HIV&EBV)']")
	private WebElement ViralAntibodies;
	@FindBy(xpath = "//span[text()='Biopsy']")
	private WebElement biopsy;
	@FindBy(xpath = "//span[text()='Culture & Sensitivity']")
	private WebElement culture_Sensitivity;
	@FindBy(xpath = "//span[text()='Throat Swab']")
	private WebElement throatSwab;
	@FindBy(xpath = "//span[text()='PCR']")
	private WebElement pCR;

	/* input list and alert webelement */
	@FindBy(xpath = "//span[contains(text(),'Investigation(s) updated successfully')]")
	private WebElement diagnosisAddSuccessAlertMsg;
	@FindBy(xpath = "//span[contains(text(),'Please select an Investigation')]")
	private WebElement diagnosisSelectAlertMsg;
	@FindBy(xpath = "//span[contains(text(),'Please select a Tooth')]")
	private WebElement ioparToothSelectAlertMsg;
	@FindBy(xpath = "//table[@id='diagnoInputList']//th[text()='Category']")
	private WebElement categoryDiagnosisInputList;
	@FindBy(xpath = "//table[@id='diagnoInputList']//th[text()='Investigation']")
	private WebElement investigationDiagnosisInputList;
	@FindBy(xpath = "//table[@id='diagnoInputList']//th[text()='Notes']")
	private WebElement notesDiagnosisInputList;
	@FindBy(xpath = "//table[@id='diagnoInputList']//th[text()='Action']")
	private WebElement actionDiagnosisInputList;
	@FindBy(xpath = "//span[@class='cmnicons yes-mdl']")
	private WebElement yesDeleteBtn;
	@FindBy(xpath = "//span[@class='cmnicons cncl-mdl']")
	private WebElement noDeleteBtn;
	@FindBy(xpath = "//p[contains(text(),'Do you want to delete the selected Diagnostic(s) category')]")
	private WebElement deleteCategoryMsg;
	@FindBy(xpath = "//p[contains(text(),'Do you want to delete this investigation')]")
	private WebElement deleteInvestigationMsg;
	@FindBy(xpath = "//span[contains(text(),'Investication(s) deleted successfully')]")
	private WebElement diagnosisDeleteSuccessAlertMsg;
	


	/* Checked diagnosis Added successfully message */
	public void checkedSuccessAlert() {
		    loginPage.waitForSpinnerToDisappear();
			loginPage.softAssert().assertTrue(checkedElementDisplayed(diagnosisAddSuccessAlertMsg));
			loginPage.waitForElementToDisappear((By.xpath("//span[@class='ng-binding ng-scope']")));
	}
	
	public void checkedUpdateSuccessAlert() {
	    loginPage.waitForSpinnerToDisappear();
	    WebElement wb = loginPage.getDriver().findElement((By.xpath("//span[contains(text(),'Investigation(s) added successfully!')]")));
		loginPage.softAssert().assertTrue(checkedElementDisplayed(wb));
		loginPage.waitForElementToDisappear((By.xpath("//span[@class='ng-binding ng-scope']")));
}
	
//	public void checkedSuccessAlert() {
//		loginPage.WaitTillElementIsPresent(diagnosisAddSuccessAlertMsg);
//		try {
//			Thread.sleep(1000);
//			loginPage.softAssert().assertTrue(checkedElementDisplayed(diagnosisAddSuccessAlertMsg));
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

	/* checked message when saving diagnosis without selecting any diagnosis */
	public void withoutDiagnosisAlert() {
		loginPage.WaitTillElementIsPresent(diagnosisSelectAlertMsg);
		Assert.assertTrue(checkedElementDisplayed(diagnosisSelectAlertMsg));
		loginPage.waitForElementToDisappear((By.xpath("//span[contains(text(),'Please select an Investigation')]")));
	}

	/* checked message when saving IOPAR without selecting any teeth */
	public void withoutIoparAlert() {
		    loginPage.waitForElementVisibility(ioparToothSelectAlertMsg, 4000);
			loginPage.softAssert().assertTrue(checkedElementDisplayed(ioparToothSelectAlertMsg));
			loginPage.waitForElementToDisappear((By.xpath("//span[@class='ng-binding ng-scope']")));
	}
	
//	public void withoutIoparAlert() {
//		try {
//			Thread.sleep(1500);
//			loginPage.softAssert().assertTrue(checkedElementDisplayed(ioparToothSelectAlertMsg));
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//	}

	/* clicking on the IOPAR */
//	public void clickIopar() {
//		try {
//			Thread.sleep(2000);
//			loginPage.waitForElementToBeClickable(ioparButton);
//			ioparButton.click();
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//	}
	
	public void clickIopar() {
		    loginPage.waitForSpinnerToDisappear();
		    loginPage.waitForPageToBecomeActive();
			loginPage.waitForElementVisibility(ioparButton,4000);
			ioparButton.click();
			loginPage.waitForSpinnerToDisappear();
	}

	/* clicking on the Imaging */
	public void clickImaging() {
		loginPage.waitForSpinnerToDisappear();
		loginPage.waitForPageToBecomeActive();
		loginPage.waitForElementVisibility(imagingButton,4000);
		imagingButton.click();
		loginPage.waitForSpinnerToDisappear();
	}
	
//	public void clickImaging() {
//		try {
//			Thread.sleep(3500);
//			imagingButton.click();
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

	/* clicking on the blood */
	public void clickBlood() {
		    loginPage.waitForSpinnerToDisappear();
		    loginPage.waitForPageToBecomeActive();
			loginPage.waitForElementVisibility(bloodButton,4000);
			bloodButton.click();
	}
	
	
	
	
//	public void clickBlood() {
//		try {
//			Thread.sleep(2500);
//			bloodButton.click();
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

	/* clicking on the Urine */
	public void clickUrine() {
            loginPage.waitForSpinnerToDisappear();
            loginPage.waitForPageToBecomeActive();
			loginPage.waitForElementVisibility(urineButton,4000);
			urineButton.click();
	}
	
	
//	public void clickUrine() {
//		try {
//			Thread.sleep(1500);
//			urineButton.click();
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

	/* clicking on the biochemical */
	public void clickBiochemicalButton() {
		    loginPage.waitForSpinnerToDisappear();
		    loginPage.waitForPageToBecomeActive();
			loginPage.waitForElementVisibility(biochemicalButton,4000);
			biochemicalButton.click();
	}
	
	
	
//	public void clickBiochemicalButton() {
//		try {
//			Thread.sleep(1500);
//			biochemicalButton.click();
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

	/* clicking on the Markers */
	
	public void clickMarkersButton() {
		loginPage.waitForSpinnerToDisappear();
		 loginPage.waitForPageToBecomeActive();
		loginPage.waitForElementVisibility(markersButton,4000);
			markersButton.click();
	}
	
//	public void clickMarkersButton() {
//		try {
//			Thread.sleep(1500);
//			markersButton.click();
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

	/* selecting teeth from the IOPAR popup */
	
	public void selectTeeth(String teethType, String teethNo) {
			WebElement webElement = loginPage.getDriver().findElement(By.xpath(
					"//div[contains(@ng-include,'" + teethType + "IOPAR.shtml')]//span[text()='" + teethNo + "']"));
			loginPage.waitForElementToBeClickable(webElement);
			webElement.click();
	}
//	public void selectTeeth(String teethType, String teethNo) {
//		try {
//			Thread.sleep(1500);
//			WebElement webElement = loginPage.getDriver().findElement(By.xpath(
//					"//div[contains(@ng-include,'" + teethType + "IOPAR.shtml')]//span[text()='" + teethNo + "']"));
//			loginPage.waitForElementToBeClickable(webElement);
//			webElement.click();
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//	}

	/* saving the diagnosis */
	public void saveDiagnosis() {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(saveButton);
		loginPage.executeScript(saveButton);
	}

	/* closing the diagnosis popup */
	public void closeDiagnosisPopup() {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(closeButton);
		closeButton.click();
	}

	/* clicking the pedo */
	
	public void clickPedo() {
		   loginPage.waitForElementVisibility(pedo, 4000);
			pedo.click();
			WebElement element = loginPage.getDriver().findElement((By.xpath("//li[@heading='Pedo']/../..//span[@id='55']")));
			loginPage.waitForElementVisibility(element, 4000);
	}
//	public void clickPedo() {
//		try {
//			Thread.sleep(1500);
//			loginPage.waitForElementToBeClickable(pedo);
//			pedo.click();
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//	}

	/* clicking the adult */
	public void clickAdult() {
		loginPage.waitForElementVisibility(adult, 4000);
		adult.click();
		WebElement element = loginPage.getDriver().findElement((By.xpath("(//li[@heading='Mixed']/../..//span[@class='tooth18'])[]")));
		loginPage.waitForElementVisibility(element, 4000);
	}

	/* clicking the mixed */
	public void clickMixed() {
		   loginPage.waitForElementVisibility(mixed, 4000);
		    mixed.click();
			WebElement element = loginPage.getDriver().findElement((By.xpath("(//li[@heading='Mixed']/../..//span[@class='tooth18'])[2]")));
			loginPage.waitForElementVisibility(element, 4000);	
	}
	
//	public void clickMixed() {
//		try {
//			Thread.sleep(1500);
//			loginPage.waitForElementToBeClickable(mixed);
//			mixed.click();
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//	}

	/* entering the diagnosis notes */
	public void enterDiagnosisNotes(String note) {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(diagnosticsNotes);
		diagnosticsNotes.sendKeys(note);
	}

	/* Diagnostic Notes should be shown entered in edit */
	public void verifyEnteredDiagnosisNotes() {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(diagnosticsNotes);
		Assert.assertFalse(notesLimitMsg.getText().contains("768 Character"));
	}

	/* checked pedo total teeths */
	public void checkedPedoTeeths() {
		loginPage.waitForPageLoad();
		Assert.assertTrue(pedoTeeths.size() == 20);
	}

	/* checked adult total teeths */
	public void checkedAdultTeeths() {
		loginPage.waitForPageLoad();
		Assert.assertTrue(adultTeeths.size() == 32);
	}

	/* checked mixed total teeths */
	public void checkedMixedTeeths() {
		loginPage.waitForPageLoad();
		Assert.assertTrue(mixedTeeths.size() == 52);
	}

	/* checking the oral Exam button, file upload and treatment plan */
	public void checked_Trt_oralExam_file_Btn() {
		loginPage.waitForSpinnerToDisappear();
		loginPage.waitForElementToBeClickable(fileButton);
		Assert.assertTrue(checkedElementDisplayed(oralExamButton) && checkedElementDisplayed(fileButton)
				&& checkedElementDisplayed(treatmentButton));
	}

	/* checking the diagnosis boxes appears on the Diagnosis Add page */
	public void checkedDiagnosisBox() {
		loginPage.waitForElementVisibility(ioparButton, 4000);
		Assert.assertTrue(checkedElementDisplayed(ioparButton) && checkedElementDisplayed(imagingButton)
				&& checkedElementDisplayed(bloodButton) && checkedElementDisplayed(urineButton)
				&& checkedElementDisplayed(biochemicalButton) && checkedElementDisplayed(markersButton));
	}

	/*
	 * checking the header of the popup when we open any diagnosis for selecting
	 * Particular test
	 */
	public void checkedDiagnosisHeader(String diagnosisHeader) {
		loginPage.waitForSpinnerToDisappear();
		loginPage.WaitTillElementIsPresent(headerDiagnosis);
		Assert.assertTrue(headerDiagnosis.getText().contains(diagnosisHeader));
	}

	/* checking the save button, notes fields and close button */
	public void checked_Save_Close_Notes() {
		loginPage.waitForElementVisibility(saveButton,4000);
		Assert.assertTrue(checkedElementDisplayed(saveButton) && checkedElementDisplayed(closeButton)
				&& checkedElementDisplayed(diagnosticsNotes) && checkedElementDisplayed(notesLimitMsg)
				&& diagnosticsNotes.getAttribute("maxlength").contains("768"));
	}

	/* checking the Adult selected at the iopar */
	public void checkedAdultSelected() {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(adult);
		Assert.assertTrue(selectedAdult.getAttribute("class").contains("first-nav ng-isolate-scope active")
				&& checkedElementDisplayed(adult) && checkedElementDisplayed(pedo) && checkedElementDisplayed(mixed));
	}

	/* checking the pedo selected at the iopar */
	public void checkedPedoSelected() {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(pedo);
		Assert.assertTrue(selectedPedo.getAttribute("class").contains("first-nav ng-isolate-scope active")
				&& checkedElementDisplayed(adult) && checkedElementDisplayed(pedo) && checkedElementDisplayed(mixed));
	}

	/* checking the mixed selected at the iopar */
	public void checkedMixedSelected() {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(mixed);
		Assert.assertTrue(selectedMixed.getAttribute("class").contains("first-nav ng-isolate-scope active")
				&& checkedElementDisplayed(adult) && checkedElementDisplayed(pedo) && checkedElementDisplayed(mixed));
	}

	/* checking all Imaging diagnosis */
	public void checkedImagingDiagnosis() {
		loginPage.waitForSpinnerToDisappear();;
		loginPage.waitForElementVisibility(saveButton,4000);
		Assert.assertTrue(checkedElementDisplayed(opg) && checkedElementDisplayed(lateralCephalogram)
				&& checkedElementDisplayed(cBCT) && checkedElementDisplayed(cECT) && checkedElementDisplayed(bitewing)
				&& checkedElementDisplayed(occlusal) && checkedElementDisplayed(uSG)
				&& checkedElementDisplayed(submentovertex) && checkedElementDisplayed(pAView)
				&& checkedElementDisplayed(occipetoMental));
	}

	/* checking all Blood diagnosis */
	public void checkedBloodDiagnosis() {
		loginPage.waitForSpinnerToDisappear();
		loginPage.waitForElementToBeClickable(saveButton);
		Assert.assertTrue(checkedElementDisplayed(mCH) && checkedElementDisplayed(mPV) && checkedElementDisplayed(mCV)
				&& checkedElementDisplayed(mCHC) && checkedElementDisplayed(bT_CT)
				&& checkedElementDisplayed(hematocrit) && checkedElementDisplayed(hemoglobin)
				&& checkedElementDisplayed(platletCount) && checkedElementDisplayed(pT_INR)
				&& checkedElementDisplayed(rBC) && checkedElementDisplayed(wBC) && checkedElementDisplayed(rBS)
				&& checkedElementDisplayed(tLC) && checkedElementDisplayed(dLC) && checkedElementDisplayed(hbA1C)
				&& checkedElementDisplayed(bloodSugarFasting) && checkedElementDisplayed(bloodSugarPP));
	}

	/* checking all Urine diagnosis */
	public void checkedUrineDiagnosis() {
		loginPage.waitForSpinnerToDisappear();;
		loginPage.waitForElementToBeClickable(saveButton);
		Assert.assertTrue(checkedElementDisplayed(urineRoutine) && checkedElementDisplayed(urineMicroscopic));
	}

	/* checking all Bio chemical diagnosis */
	public void checkedBioChemicalDiagnosis() {
		loginPage.waitForSpinnerToDisappear();;
		loginPage.waitForElementToBeClickable(saveButton);
		Assert.assertTrue(checkedElementDisplayed(lFT) && checkedElementDisplayed(aLP) && checkedElementDisplayed(aLT)
				&& checkedElementDisplayed(aSt) && checkedElementDisplayed(bilirubin) && checkedElementDisplayed(gGT)
				&& checkedElementDisplayed(colour) && checkedElementDisplayed(density) && checkedElementDisplayed(pH)
				&& checkedElementDisplayed(albumin));
	}

	/* checking all Markers diagnosis */
	public void checkedMarkersDiagnosis() {
		loginPage.waitForSpinnerToDisappear();;
		loginPage.waitForElementToBeClickable(saveButton);
		Assert.assertTrue(checkedElementDisplayed(C_RP) && checkedElementDisplayed(ViralAntibodies)
				&& checkedElementDisplayed(biopsy) && checkedElementDisplayed(culture_Sensitivity)
				&& checkedElementDisplayed(throatSwab) && checkedElementDisplayed(pCR));
	}

	/* checking all column name at diagnosis Add page */
	public void checkedDiagnosisInputListData() {
		try {
			Thread.sleep(2000);
			loginPage.WaitTillElementIsPresent(categoryDiagnosisInputList);
			Assert.assertTrue(checkedElementDisplayed(categoryDiagnosisInputList)
					&& checkedElementDisplayed(investigationDiagnosisInputList)
					&& checkedElementDisplayed(notesDiagnosisInputList)
					&& checkedElementDisplayed(actionDiagnosisInputList));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/* checking the category name at input list */
	public void checkedCategory(String category) {
		loginPage.waitForPageLoad();
		Assert.assertTrue(checkedElementDisplayed(loginPage.getDriver()
				.findElement(By.xpath("//table[@id='diagnoInputList']//td[text()='" + category + "']"))));
	}

	/* checking the investigation at input list */
	public void checkedInvestigation(String category, String investigation) {
		loginPage.waitForPageLoad();
		if (category.equals("IOPAR")) {
			Assert.assertTrue(checkedElementDisplayed(loginPage.getDriver().findElement(By.xpath(
					"//td[text()='" + category + "']/following-sibling::td//div[text()='" + investigation + "']"))));
		} else {
			Assert.assertTrue(checkedElementDisplayed(loginPage.getDriver().findElement(By.xpath(
					"//td[text()='" + category + "']/following-sibling::td//span[text()='" + investigation + "']"))));
		}
	}

	/* selecting the diagnosis */
	public void selectDiagnosis(String diagnosis) {		
			WebElement webElement = loginPage.getDriver().findElement(By.xpath("//span[@class ='ng-binding' and contains(text(),'"+diagnosis+"')]"));
			loginPage.waitForElementVisibility(webElement, 4000);
			webElement.click();
	}
	
//	public void selectDiagnosis(String diagnosis) {
//		try {
//			Thread.sleep(1500);
//			WebElement webElement = loginPage.getDriver().findElement(By.xpath("//span[text()='" + diagnosis + "']"));
//			loginPage.waitForElementToBeClickable(webElement);
//			webElement.click();
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//	}

	/* check Investigation is selected at the time of Diagnostic Edit */
	public void checkSelectedInvestigation(String investigation) {
	        loginPage.waitForSpinnerToDisappear();
	        loginPage.waitForElementVisibility(saveButton,4000);
			WebElement webElement = loginPage.getDriver().findElement(By.xpath("//div[contains(@data-ng-class,'selected')]/span[text()='"+investigation+"']"));
			loginPage.waitForElementToBeClickable(webElement);
			webElement.isSelected();
	}
	
	
//	public void checkSelectedInvestigation(String investigation) {
//		try {
//			Thread.sleep(1500);
//			WebElement webElement = loginPage.getDriver().findElement(By.xpath("//div[contains(@data-ng-class,'selected')]/span[text()='"+investigation+"']"));
//			loginPage.waitForElementToBeClickable(webElement);
//			webElement.isSelected();
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//	}

	/* checking the delete button with investigation */
	public void checkedDeleteWithInvestigation(String category, String investigation) {
		loginPage.waitForPageLoad();
		if (category.equals("IOPAR")) {
			Assert.assertTrue(checkedElementDisplayed(loginPage.getDriver()
					.findElement(By.xpath("//td[text()='" + category + "']/following-sibling::td//div[text()='"
							+ investigation + "']/following-sibling::div[@class='actnsVertIcns DiagDelInvest']"))));
		} else {
			Assert.assertTrue(checkedElementDisplayed(loginPage.getDriver()
					.findElement(By.xpath("//td[text()='" + category + "']/following-sibling::td//span[text()='"
							+ investigation + "']/following-sibling::div[@class='actnsVertIcns DiagDelInvest']"))));
		}
	}

	/* click deleted button with investigation */
	public void clickDeleteWithInvestigation(String category, String investigation) {
		loginPage.waitForPageLoad();
		if (category.equals("IOPAR")) {
			WebElement deleteBtn = loginPage.getDriver()
					.findElement(By.xpath("//td[text()='" + category + "']/following-sibling::td//div[text()='"
							+ investigation + "']/following-sibling::div[@class='actnsVertIcns DiagDelInvest']"));
			deleteBtn.click();
		} else {
			WebElement deleteBtn = loginPage.getDriver()
					.findElement(By.xpath("//td[text()='" + category + "']/following-sibling::td//span[text()='"
							+ investigation + "']/following-sibling::div[@class='actnsVertIcns DiagDelInvest']"));
			deleteBtn.click();
		}
	}
	
	/* checking the notes at the input listing */
	public void notesDiagnosisInputList(String category, String investigation, String notes) {
		loginPage.waitForPageLoad();
		if (category.equals("IOPAR")) {
			Assert.assertTrue(loginPage.getDriver().findElement(By.xpath("//td[text()='" + category
					+ "']/following-sibling::td//div[text()='" + investigation
					+ "']/following-sibling::div[@class='actnsVertIcns DiagDelInvest']/../following-sibling::div[contains(@class,'ioparInListNotes ')]"))
					.getText().equalsIgnoreCase(notes));
		} else {
			Assert.assertTrue(loginPage.getDriver()
					.findElement(By.xpath("//td[text()='" + category
							+ "']/following-sibling::td/div[contains(@class,'wrd-brk-wrd')]"))
					.getText().equalsIgnoreCase(notes));
		}
	}

	/* checking the Delete button at input list for diagnosis */
	public void checkedDeleteBtnInputlist(String category) {
		loginPage.waitForPageLoad();
		Assert.assertTrue(checkedElementDisplayed(loginPage.getDriver().findElement(
				By.xpath("//td[text()='" + category + "']/following-sibling::td//span[text()='Delete']"))));
	}

	/* click the Delete button at input list for diagnosis */
	public void clickDeleteBtnInputlist(String category) {
		loginPage.waitForPageLoad();
		WebElement deleteBtn = loginPage.getDriver().findElement(By.xpath("//td[text()='" + category + "']/following-sibling::td//span[text()='Delete']"));
		deleteBtn.click();
	}

	/* checking the Edit button at input list for diagnosis */
	public void checkedEditBtnInputlist(String category) {
		loginPage.waitForPageLoad();
		if (category.equals("IOPAR")) {
			Assert.assertTrue(loginPage.getDriver().findElement(By.xpath("//td[text()='" + category + "']/following-sibling::td//span[text()='Edit']/.."))
					.getAttribute("data-ng-class").contains("hide"));
		} else {
			Assert.assertTrue(checkedElementDisplayed(loginPage.getDriver().findElement(
					By.xpath("//td[text()='" + category + "']/following-sibling::td//span[text()='Edit']"))));
		}
	}

	/* click on Edit button at input list for diagnosis */
	public void clickEditBtnInputlist(String category) {
		loginPage.waitForPageLoad();
		if (category.equals("IOPAR")) {
			Assert.assertTrue(loginPage.getDriver().findElement(By.xpath("//td[text()='" + category + "']/following-sibling::td//span[text()='Edit']/.."))
					.getAttribute("data-ng-class").contains("hide"));
		} else {
			WebElement editBtn = loginPage.getDriver().findElement(
					By.xpath("//td[text()='" + category + "']/following-sibling::td//span[text()='Edit']"));
			editBtn.click();
		}
	}
	
	/* check delete Message against Investigation and from main action button*/
	public void checkMsgOnDeleteModal(String delete){
		loginPage.waitForPageLoad();
		if(delete.equals("Investigation")) 
			Assert.assertTrue(checkedElementDisplayed(deleteInvestigationMsg)); 
		else
			Assert.assertTrue(checkedElementDisplayed(deleteCategoryMsg));
	}
	
	/* click on Yes button shown after click on Delete button at input list for diagnosis */
	public void clickYesDeleteBtn() {
		loginPage.waitForPageLoad();
		yesDeleteBtn.click();
	}
	
	/* click on No button shown after click on Delete button at input list for diagnosis */
	public void clickNoDeleteBtn() {
		loginPage.waitForPageLoad();
		noDeleteBtn.click();
	}

	/* Checked diagnosis Added successfully message */
	public void checkedSuccessDeleteAlert() {
		    loginPage.WaitTillElementIsPresent(diagnosisDeleteSuccessAlertMsg);
			loginPage.softAssert().assertTrue(checkedElementDisplayed(diagnosisDeleteSuccessAlertMsg));
	}
	
//	public void checkedSuccessDeleteAlert() {
//		loginPage.WaitTillElementIsPresent(diagnosisDeleteSuccessAlertMsg);
//		try {
//			Thread.sleep(1000);
//			loginPage.softAssert().assertTrue(checkedElementDisplayed(diagnosisDeleteSuccessAlertMsg));
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
	private boolean checkedElementDisplayed(WebElement webElement) {
		return (webElement.isDisplayed());
	}

}
