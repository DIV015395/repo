package com.prm.patientdashboard.pageobject;

import com.prm.pageobjects.utils.PCDriver;
import com.prm.pageobjects.utils.TestData;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import java.util.List;

/**
 * 
 * @author Ajit Yadav
 *
 */
public class OralExamsPage {

	private PCDriver loginPage;

	public OralExamsPage(PCDriver loginPage) {
		this.loginPage = loginPage;
		PageFactory.initElements(loginPage.getDriver(), this);
	}

	@FindBy(xpath = "//a[text()='Teeth']")
	private WebElement teeth;
	@FindBy(xpath = "//a[text()='Tissue']")
	private WebElement tissue;
	@FindBy(xpath = "//b[text()='Adult']")
	private WebElement adult;
	@FindBy(xpath = "//b[text()='Pedo']")
	private WebElement pedo;
	@FindBy(xpath = "//b[text()='Mixed']")
	private WebElement mixed;
	@FindBy(xpath = "//b[text()='Soft']")
	private WebElement soft;
	@FindBy(xpath = "//b[text()='Hard']")
	private WebElement hard;
	@FindBy(xpath = "//li[text()='Chief Complaint(s)']")
	private WebElement chiefComplaints;
	@FindBy(xpath = "//span[@alt='Add chief complaint(s)']")
	private WebElement chiefComplaintsAddBtn;
	@FindBy(id = "oralexamselectallbtn")
	private WebElement AllTeeth;
	@FindBy(xpath = "//div[contains(@class,'sideNavigation hidden-xs visible-sm visible-md visible-lg')]//i[contains(text(),'Diagnostic')]")
	private WebElement diagnostic;
	@FindBy(xpath = "//div[contains(@class,'sideNavigation hidden-xs visible-sm visible-md visible-lg')]//i[contains(text(),'Treatment')]")
	private WebElement treatment;
	@FindBy(xpath = "//div[contains(@class,'sideNavigation hidden-xs visible-sm visible-md visible-lg')]//i[contains(text(),'File')]")
	private WebElement file;
	@FindBy(css = "h1[class='btmTitle pull-left ng-binding']")
	private WebElement title;
	@FindBy(xpath = "//div[@id='adult']//li")
	private List<WebElement> adultTeeths;
	@FindBy(xpath = "//div[@id='pedo']//li")
	private List<WebElement> pedoTeeths;
	@FindBy(xpath = "//div[@id='mixed']//li")
	private List<WebElement> mixedTeeths;

	// ---------webelement in tissue------
	@FindBy(css = "li[ng-show=\"tissueCategory === 'HardTissue'\"]")
	private List<WebElement> hardProvisionals;
	@FindBy(css = "li[ng-show=\"tissueCategory === 'SoftTissue'\"]")
	private List<WebElement> softProvisionals;

	// ------------------webelements in the popup after seleting the tooth
	// image-----
	@FindBy(id = "obpopupmodalheader")
	private WebElement titlePopup;
	@FindBy(xpath = "//div[@id='toothViewPopup']//span[@class='cmnicons cncl-mdl']")
	private WebElement closeBtnOnTeethPopup;
	@FindBy(xpath = "//div[@id='confirmbox']//span[@class='cmnicons yes-mdl']")
	private WebElement yesOnMultiselect;
	@FindBy(xpath = "//ul[@id='observationpopupul']//span[@class='ng-binding']/following-sibling::span")
	private List<WebElement> removeBtn;
	@FindBy(xpath = "//div[@id='toothViewPopup']//a[text()='Tooth Site & Perio']")
	public WebElement toothSitePerio;
	@FindBy(xpath = "//span[text()='Buccal']")
	private WebElement Buccal;
	@FindBy(xpath = "//span[text()='Distal']")
	private WebElement Distal;
	@FindBy(xpath = "//span[text()='Mesial']")
	private WebElement Mesial;
	@FindBy(xpath = "//span[contains(text(),'Occlusal')]")
	private WebElement Occlusal;
	@FindBy(xpath = "//span[text()='Palatal']")
	private WebElement Palatal;
	@FindBy(xpath = "//span[text()='All Surface']")
	private WebElement AllSurface;
	@FindBy(xpath = "//span[text()='Perio']")
	private WebElement Perio;
	@FindBy(xpath = "//div[@class='content']")
	private List<WebElement> Provisinals;
	@FindBy(xpath = "//i[text()='IOPAR']")
	private WebElement IOPARBtn;
	@FindBy(id = "ioprRemark")
	private WebElement IOPARNotes;
	@FindBy(id = "toothObservationRemark")
	private WebElement toothObservationRemark;
	@FindBy(xpath = "//textarea[@id='ioprRemark']/following-sibling::span")
	private WebElement ioparWordlimitMsg;
	@FindBy(xpath = "//textarea[@id='toothObservationRemark']/following-sibling::span")
	private WebElement notesWordlimitMsg;
	@FindBy(xpath = "//div[@id='toothViewPopup']//span[@class='cmnicons sv-mdl']")
	private WebElement saveBtn;
	@FindBy(xpath = "//div[contains(@ng-click,'removetoothSitesItems')]//a[@class='remove-button']")
	private List<WebElement> provisionalRemoveBtnToothSite;

	// ------------------for soft tissue on popup--------
	@FindBy(xpath = "//div[@id='observationpopupbtn']/../../..//a[text()='Soft Tissue']")
	private WebElement softTissue;
	@FindBy(xpath = "//div[contains(@ng-click,'addToothStissueItem')]//h5[text()='Lip']")
	private WebElement lip;
	@FindBy(xpath = "//div[contains(@ng-click,'addToothStissueItem')]//h5[text()='Cheek']")
	private WebElement cheek;
	@FindBy(xpath = "//div[contains(@ng-click,'addToothStissueItem')]//h5[text()='Tongue']")
	private WebElement Tongue;
	@FindBy(xpath = "//div[contains(@ng-click,'addToothStissueItem')]//h5[text()='Floor of Mouth']")
	private WebElement FloorOfMouth;
	@FindBy(xpath = "//div[contains(@ng-click,'addToothStissueItem')]//h5[text()='Palate']")
	private WebElement Palate;
	@FindBy(xpath = "//div[contains(@ng-click,'addToothStissueItem')]//h5[text()='Gingiva']")
	private WebElement Gingiva;
	@FindBy(xpath = "//div[contains(@ng-click,'addToothStissueItem')]//h5[text()='Vestibule']")
	private WebElement Vestibule;
	@FindBy(xpath = "//div[contains(@ng-click,'addToothStissueItem')]//h5[text()='Frenum']")
	private WebElement Frenum;
	@FindBy(xpath = "//div[contains(@ng-click,'addToothStissueItem')]//h5[text()='Salivary Gland']")
	private WebElement SalivaryGland;
	@FindBy(xpath = "//div[contains(@ng-click,'addToothStissueItem')]//h5[text()='Lymph Nodes']")
	private WebElement LymphNodes;
	@FindBy(xpath = "//div[contains(@ng-click,'softDiagno.ProvisionalDiagnostic.bsId')]")
	private List<WebElement> SoftTissueProvisinals;
	@FindBy(xpath = "//div[contains(@ng-click,'removeToothSoftTissueItems')]//a[@class='remove-button']")
	private List<WebElement> provisionalRemoveBtnSoftTissue;

	// ------------------------webelement of hard tissue in popup---
	@FindBy(xpath = "//div[@id='toothViewPopup']//a[text()='Hard Tissue']")
	private WebElement HardTissueOnPopUp;
	@FindBy(xpath = "//span[text()='Mandibular Angle']")
	private WebElement MandibularAngle;
	@FindBy(xpath = "//div[@ng-click='addToothHardTissue(hTissues.id, hTissues.title)']//span[text()='Mandibular Body']")
	private WebElement MandibularBody;
	@FindBy(xpath = "//span[text()='Maxillary Tuberosity']")
	private WebElement MaxillaryTuberosity;
	@FindBy(xpath = "//span[text()='Posterior Maxilla']")
	private WebElement PosteriorMaxilla;
	@FindBy(xpath = "//span[text()='Pre-Maxilla']")
	private WebElement PreMaxilla;
	@FindBy(xpath = "//span[text()='Maxillary Sinus']")
	private WebElement MaxillarySinus;
	@FindBy(xpath = "//span[text()='Mandibular Symphysis']")
	private WebElement MandibularSymphysis;
	@FindBy(xpath = "//span[text()='TM Joint']")
	private WebElement TMJoint;
	@FindBy(xpath = "//div[contains(@ng-click,'hDiadgnos.ProvisionalDiagnostic.id')]")
	private List<WebElement> HardProvisinalsInPopup;
	@FindBy(xpath = "//div[contains(@ng-click,'removeToothHardTissueItems')]//a[@class='remove-button']")
	private List<WebElement> provisionalRemoveBtnHardTissue;

	// ---------------webelment of chief complaints on oral page-------------------
	@FindBy(xpath = "//span[@alt='Edit added chief complaint(s)']")
	private WebElement chiefComplaintEditBtn;

	// ------------------WebElement and function of teeth---------------------
	@FindBy(xpath = "//b[@class='ng-binding']")
	private WebElement AllTeethText;

	// ------------------webelement and reusable method of input list------------
	@FindBy(xpath = "//th[text()='Tooth No.']")
	private WebElement ToothNo;
	@FindBy(xpath = "//th[text()='Tooth Site & Perio']")
	private WebElement ToothSitePerioInputList;
	@FindBy(xpath = "//th[text()='Soft Tissue']")
	private WebElement SoftTissueInputList;
	@FindBy(xpath = "//th[text()='Hard Tissue']")
	private WebElement HardTissueInputList;
	@FindBy(xpath = "//th[text()='Notes']")
	private WebElement NotesInputList;
	@FindBy(xpath = "//th[text()='Action']")
	private WebElement ActionInputList;
	@FindBy(xpath = "//h5[contains(text(),'Mandibular Angle')]")
	private WebElement mandibularAngle;
	
	private static final String TODAY_DATE =TestData.getInstance().getTodayDate();

	public void clickOnchiefComplaintsAddBtn() {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(chiefComplaintsAddBtn);
		chiefComplaintsAddBtn.click();
	}
	
	public void verifyUiOralExamPage() {
		loginPage.waitForModalOverlayToDisappear();
		loginPage.waitForSpinnerToDisappear();
		loginPage.waitForElementVisibility(teeth,4000);
		Assert.assertTrue(checkedElementDisplayed(teeth) && checkedElementDisplayed(tissue)
				&& checkedElementDisplayed(adult) && checkedElementDisplayed(pedo) && checkedElementDisplayed(mixed)
				&& checkedElementDisplayed(chiefComplaints)
				&& checkedElementDisplayed(AllTeeth) && checkedElementDisplayed(diagnostic)
				&& checkedElementDisplayed(treatment) && checkedElementDisplayed(file));
	}

//	public void verifyUiOralExamPage() {
//		loginPage.waitForPageLoad();
//		loginPage.waitForElementToBeClickable(AllTeeth);
//		try {
//			Thread.sleep(2000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		Assert.assertTrue(checkedElementDisplayed(teeth) && checkedElementDisplayed(tissue)
//				&& checkedElementDisplayed(adult) && checkedElementDisplayed(pedo) && checkedElementDisplayed(mixed)
//				&& checkedElementDisplayed(chiefComplaints) && checkedElementDisplayed(chiefComplaintsAddBtn)
//				&& checkedElementDisplayed(AllTeeth) && checkedElementDisplayed(diagnostic)
//				&& checkedElementDisplayed(treatment) && checkedElementDisplayed(file));
//	}

	public void clickOnTissue() {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(tissue);
		tissue.click();
	}

	public void clickOnPedo() {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(pedo);
		pedo.click();
	}

	public void clickOnAdult() {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(adult);
		adult.click();
	}

	public void clickOnMixed() {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(mixed);
		mixed.click();
	}

	public void verifyAdultsTeeths() {
		loginPage.visibilityOfListLocated(adultTeeths);
		Assert.assertTrue(adultTeeths.size() == 32);
	}
	
	public void verifyPedoTeeths() {
		loginPage.waitForSpinnerToDisappear();
		loginPage.waitForElementVisibility(pedoTeeths.get(1), 4000);
		Assert.assertTrue(pedoTeeths.size() == 20);
	}

//	public void verifyPedoTeeths() {
//		loginPage.waitForPageLoad();
//		try {
//			Thread.sleep(2000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		Assert.assertTrue(pedoTeeths.size() == 20);
//	}
	
	public void verifyMixedTeeths() {
		loginPage.waitForSpinnerToDisappear();
		loginPage.waitForElementVisibility(mixedTeeths.get(1), 4000);
		Assert.assertTrue(mixedTeeths.size() == 52);
	}

//	public void verifyMixedTeeths() {
//		loginPage.waitForPageLoad();
//		try {
//			Thread.sleep(2000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		Assert.assertTrue(mixedTeeths.size() == 52);
//	}

	public void verifyWebElementInHardTissue() {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(soft);
		Assert.assertTrue(
				(checkedElementDisplayed(hard) && checkedElementDisplayed(soft)) == (hardProvisionals.size() == 8));
	}

	public void verifyWebElementInSoftTissue() {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(soft);
		Assert.assertTrue(
				(checkedElementDisplayed(hard) && checkedElementDisplayed(soft)) == (softProvisionals.size() == 10));
	}
	
	public void clickOnHardTissue() {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(hard);
		hard.click();
		loginPage.waitForElementVisibility(mandibularAngle,4000);
	}

//	public void clickOnHardTissue() {
//		loginPage.waitForPageLoad();
//		loginPage.waitForElementToBeClickable(hard);
//		hard.click();
//		try {
//			Thread.sleep(2000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

	public void removeToothSiteProvisionals() {
		loginPage.waitForPageLoad();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		loginPage.waitForElementToBeClickable(toothObservationRemark);
		for (int i = 0; provisionalRemoveBtnToothSite.size() > i; i++) {
			provisionalRemoveBtnToothSite.get(i).click();
		}
	}

	public void enterOralExamNotes(String note) {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(toothObservationRemark);
		toothObservationRemark.sendKeys(note);
	}

	public void clickOnSaveBtn() {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(saveBtn);
		saveBtn.click();
		loginPage.waitForElementToDisappear(By.xpath("//span[contains(text(),'Oral Exam findings added successfully!')]"));
	}

	public void selectedToothAndSiteProvisionalDisplayedBelow() {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(softTissue);
		Assert.assertTrue(provisionalRemoveBtnToothSite.size() == 1 || provisionalRemoveBtnToothSite.size() == 2
				|| provisionalRemoveBtnToothSite.size() == 3 || provisionalRemoveBtnToothSite.size() == 4
				|| provisionalRemoveBtnToothSite.size() == 5 || provisionalRemoveBtnToothSite.size() == 6
				|| provisionalRemoveBtnToothSite.size() == 7);
	}
	
	public void clickOnToothSiteAndPerioProvisinals() {
		    loginPage.waitForSpinnerToDisappear();
			loginPage.getDriver().findElement(By.xpath("(//div[@class='content'])[1]")).click();
	}

//	public void clickOnToothSiteAndPerioProvisinals() {
//		loginPage.waitForPageLoad();
//		try {
//			Thread.sleep(2000);
//			loginPage.getDriver().findElement(By.xpath("(//div[@class='content'])[1]")).click();
//		} catch (StaleElementReferenceException | InterruptedException e) {
//			loginPage.getDriver().findElement(By.xpath("(//div[@class='content'])[1]")).click();
//		}
//	}

	public void verifyProvisinalIsSelected(String name) {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(softTissue);
		Assert.assertTrue(loginPage.getDriver().findElement(By.xpath("//span[text()='" + name + "']/.."))
				.getAttribute("class").contains("selected"));
	}

	public void verifyToothObservationRemark() {
		loginPage.waitForElementToBeClickable(toothObservationRemark);
		boolean result = toothObservationRemark.isDisplayed();
		Assert.assertTrue(result
				&& (toothObservationRemark.getAttribute("maxlength").contains("768")
						&& toothObservationRemark.getAttribute("placeholder").contains("» enter notes")
						&& notesWordlimitMsg.getText().contains("768 Character(s) left")));
	}

	public void verifyIoparBtn() {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(IOPARBtn);
		Assert.assertTrue(checkedElementDisplayed(IOPARBtn));
	}

	public void verifyIOPARNotes() {
		loginPage.waitForElementVisibility(IOPARNotes,4000);
		loginPage.waitForElementToBeClickable(IOPARNotes);
		boolean result = IOPARNotes.isDisplayed();
		Assert.assertTrue(result
				&& IOPARNotes.getAttribute("placeholder").contains("» IOPAR notes")
				&& ioparWordlimitMsg.getText().contains("768 Character(s) left"));
	}

	public void clickOnIOPARBtn() {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(IOPARBtn);
		IOPARBtn.click();
	}

	public void ioparNotesDisable() {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(IOPARBtn);
		List<WebElement> web = loginPage.getDriver()
				.findElements(By.xpath("//div[@id='toothViewPopup']//span[contains(text(),'Character left')]"));
		Assert.assertTrue(web.size() == 1);
	}
	
	public void clickOnBuccal() {
		loginPage.waitForElementVisibility(Buccal,4000);
		loginPage.waitForElementToBeClickable(Buccal);
		Buccal.click();
		loginPage.waitForSpinnerToDisappear();
	}

//	public void clickOnBuccal() {
//		loginPage.waitForPageLoad();
//		try {
//			Thread.sleep(3000);
//			loginPage.waitForElementToBeClickable(Buccal);
//			Buccal.click();
//			Thread.sleep(1000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

	public void clickOnDistal() {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(Distal);
		Distal.click();
	}

	public void clickOnMesial() {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(Mesial);
		Mesial.click();
	}

	public void clickOnOcclusal() {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(Occlusal);
		Occlusal.click();
	}

	public void clickOnPalatal() {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(Palatal);
		Palatal.click();
	}

	public void clickOnAllSurface() {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(AllSurface);
		AllSurface.click();
	}

	public void clickOnPerio() {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(Perio);
		Perio.click();
	}

	public void verifyProvisinals() {
		loginPage.waitForSpinnerToDisappear();
		Assert.assertTrue(Provisinals.size() > 0);
	}

	public void removeSoftTissueProvisionals() {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(toothObservationRemark);
		for (int i = 0; provisionalRemoveBtnSoftTissue.size() > i; i++) {
			provisionalRemoveBtnSoftTissue.get(i).click();
		}
	}

	public void selectedSoftTissueProvisionalDisplayedBelow() {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(toothSitePerio);
		Assert.assertTrue(provisionalRemoveBtnSoftTissue.size() == 1 || provisionalRemoveBtnSoftTissue.size() == 2
				|| provisionalRemoveBtnSoftTissue.size() == 3 || provisionalRemoveBtnSoftTissue.size() == 4
				|| provisionalRemoveBtnSoftTissue.size() == 5 || provisionalRemoveBtnSoftTissue.size() == 6
				|| provisionalRemoveBtnSoftTissue.size() == 7 || provisionalRemoveBtnSoftTissue.size() == 8
				|| provisionalRemoveBtnSoftTissue.size() == 9 || provisionalRemoveBtnSoftTissue.size() == 10);
	}
	
	public void clickOnSoftTissueProvisinals() {
		loginPage.waitForSpinnerToDisappear();
			loginPage.getDriver()
					.findElement(By.xpath("(//div[contains(@ng-click,'softDiagno.ProvisionalDiagnostic.bsId')])[1]"))
					.click();
	}

//	public void clickOnSoftTissueProvisinals() {
//		loginPage.waitForPageLoad();
//		try {
//			Thread.sleep(2000);
//			loginPage.getDriver()
//					.findElement(By.xpath("(//div[contains(@ng-click,'softDiagno.ProvisionalDiagnostic.bsId')])[1]"))
//					.click();
//		} catch (StaleElementReferenceException | InterruptedException e) {
//			loginPage.getDriver()
//					.findElement(By.xpath("(//div[contains(@ng-click,'softDiagno.ProvisionalDiagnostic.bsId')])[1]"))
//					.click();
//		}
//	}

	public void provisionalIsSelectedOfSoftTissue(String name) {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(toothSitePerio);
		Assert.assertTrue(loginPage.getDriver()
				.findElement(By.xpath("//div[contains(@ng-click,'addToothStissueItem')]//h5[text()='" + name + "']"))
				.getAttribute("class").contains("selected"));
	}
	
	public void verifySoftProvisinals() {
		loginPage.waitForSpinnerToDisappear();
		Assert.assertTrue(SoftTissueProvisinals.size() > 0);
	}

//	public void verifySoftProvisinals() {
//		loginPage.waitForPageLoad();
//		try {
//			Thread.sleep(2000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		Assert.assertTrue(SoftTissueProvisinals.size() > 0);
//	}

	public void clickOnLip() {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(lip);
		lip.click();
	}

	public void clickOnCheek() {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(cheek);
		cheek.click();
	}

	public void clickOnTongue() {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(Tongue);
		Tongue.click();
	}

	public void clickOnFloorOfMouth() {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(FloorOfMouth);
		FloorOfMouth.click();
	}

	public void clickOnPalate() {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(Palate);
		Palate.click();
	}

	public void clickOnGingiva() {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(Gingiva);
		Gingiva.click();
	}

	public void clickOnVestibule() {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(Vestibule);
		Vestibule.click();
	}

	public void clickOnFrenum() {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(Frenum);
		Frenum.click();
	}

	public void clickOnSalivaryGland() {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(SalivaryGland);
		SalivaryGland.click();
	}

	public void clickOnLymphNodes() {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(LymphNodes);
		LymphNodes.click();
	}
	
	public void clickOnSoftTissueOnPopup() {
		loginPage.waitForElementVisibility(softTissue, 4000);
		softTissue.click();
	}


//	public void clickOnSoftTissueOnPopup() {
//		try {
//			Thread.sleep(2500);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		softTissue.click();
//	}

	public void removeHardTissueProvisionals() {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(toothObservationRemark);
		for (int i = 0; provisionalRemoveBtnHardTissue.size() > i; i++) {
			provisionalRemoveBtnHardTissue.get(i).click();
		}
	}

	public void selectedHardTissueProvisionalDisplayedBelow() {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(toothSitePerio);
		Assert.assertTrue(provisionalRemoveBtnHardTissue.size() == 1 || provisionalRemoveBtnHardTissue.size() == 2
				|| provisionalRemoveBtnHardTissue.size() == 3 || provisionalRemoveBtnHardTissue.size() == 4
				|| provisionalRemoveBtnHardTissue.size() == 5 || provisionalRemoveBtnHardTissue.size() == 6
				|| provisionalRemoveBtnHardTissue.size() == 7 || provisionalRemoveBtnHardTissue.size() == 8);
	}
	
	public void clickOnHardTissueProvisinals() {
	loginPage.waitForSpinnerToDisappear();
	loginPage.getDriver()
	.findElement(By.xpath("(//div[contains(@ng-click,'hDiadgnos.ProvisionalDiagnostic.id')])[1]"))
	.click();
	}

//	public void clickOnHardTissueProvisinals() {
//		loginPage.waitForPageLoad();
//		try {
//			Thread.sleep(1000);
//			loginPage.getDriver()
//					.findElement(By.xpath("(//div[contains(@ng-click,'hDiadgnos.ProvisionalDiagnostic.id')])[1]"))
//					.click();
//		} catch (StaleElementReferenceException | InterruptedException e) {
//			loginPage.getDriver()
//					.findElement(By.xpath("(//div[contains(@ng-click,'hDiadgnos.ProvisionalDiagnostic.id')])[1]"))
//					.click();
//		}
//	}

	public void provisionalIsSelectedOfHardTissue(String name) {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(softTissue);
		Assert.assertTrue(loginPage.getDriver().findElement(By.xpath("//span[text()='" + name + "']/.."))
				.getAttribute("class").contains("selected"));
	}

	public void clickOnMandibularAngle() {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(MandibularAngle);
		MandibularAngle.click();
	}

	public void clickOnMandibularBody() {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(MandibularBody);
		MandibularBody.click();
	}

	public void clickOnMaxillaryTuberosity() {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(MaxillaryTuberosity);
		MaxillaryTuberosity.click();
	}

	public void clickOnPosteriorMaxilla() {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(PosteriorMaxilla);
		PosteriorMaxilla.click();
	}

	public void clickOnPreMaxilla() {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(PreMaxilla);
		PreMaxilla.click();
	}

	public void clickOnMaxillarySinus() {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(MaxillarySinus);
		MaxillarySinus.click();
	}

	public void clickOnMandibularSymphysis() {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(MandibularSymphysis);
		MandibularSymphysis.click();
	}

	public void clickOnTMJoint() {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(TMJoint);
		TMJoint.click();
	}

	public void clickOnHardTissueInPopup() {
		loginPage.waitForElementVisibility(HardTissueOnPopUp,4000);
		HardTissueOnPopUp.click();
	}
	
	public void verifyHardProvisionals() {
		loginPage.waitForSpinnerToDisappear();
		Assert.assertTrue(HardProvisinalsInPopup.size() > 0);
	}

//	public void verifyHardProvisionals() {
//		loginPage.waitForPageLoad();
//		try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		Assert.assertTrue(HardProvisinalsInPopup.size() > 0);
//	}
	
	public void verifyTitleOfPopup(String typeTeeth, String tabName) {
		loginPage.waitForElementVisibility(closeBtnOnTeethPopup,4000);
		loginPage.WaitTillElementIsPresent(titlePopup);
		String title = "Oral Exam >" + typeTeeth + " > " + tabName;
		Assert.assertTrue(titlePopup.getText().contains(title));
	}

//	public void verifyTitleOfPopup(String typeTeeth, String tabName) {
//		loginPage.waitForPageLoad();
//		loginPage.WaitTillElementIsPresent(titlePopup);
//		try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		String title = "Oral Exam >" + typeTeeth + " > " + tabName;
//		Assert.assertTrue(titlePopup.getText().contains(title));
//	}

	public void clickOnTeethImage(String toothType, String teethNo) {
		loginPage.waitForPageLoad();
		String toothNO = "tooth" + teethNo;
		if (toothType.equals("Adult")) {
			loginPage.getDriver().findElement(By.xpath("//div[@id='adult']//span[contains(@class,'" + toothNO + "')]")).click();
		} else if (toothType.equals("Pedo")) {
			pedo.click();
			loginPage.waitForElementToBeClickable(mixed);
			loginPage.getDriver().findElement(By.xpath("//div[@id='pedo']//span[contains(@class,'" + toothNO + "')]")).click();
		} else if (toothType.equals("Mixed")) {
			mixed.click();
			loginPage.waitForElementToBeClickable(pedo);
			loginPage.getDriver().findElement(By.xpath("//div[@id='mixed']//a//span[contains(@data-ng-click,'" + teethNo + "')]")).click();
		}
	}

	public void selectMultipleTeeth(String toothType, String teethNo) {
		loginPage.waitForPageLoad();
		if (toothType.equals("Adult")) {
			loginPage.getDriver().findElement(By.xpath("//div[@id='adult']//span[text()='" + teethNo + "']")).click();
		} else if (toothType.equals("Pedo")) {
			pedo.click();
			loginPage.waitForElementToBeClickable(mixed);
			WebElement web = loginPage.getDriver()
					.findElement(By.xpath("//div[@id='pedo']//span[text()='" + teethNo + "']"));
			loginPage.waitForElementToBeClickable(web);
			web.click();
		} else if (toothType.equals("Mixed")) {
			mixed.click();
			WebElement web = loginPage.getDriver()
					.findElement(By.xpath("//div[@id='mixed']//span[text()='" + teethNo + "']"));
			web.click();
		}
	}

	public void selectedInRespective(String toothType, String teethNo) {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(tissue);
		if (toothType.equals("Adult")) {
			Assert.assertTrue(
					loginPage.getDriver().findElement(By.xpath("//div[@id='adult']//span[text()='" + teethNo + "']"))
							.getAttribute("class").contains("selected"));
		} else if (toothType.equals("Pedo")) {
			pedo.click();
			Assert.assertTrue(
					loginPage.getDriver().findElement(By.xpath("//div[@id='pedo']//span[text()='" + teethNo + "']"))
							.getAttribute("class").contains("selected"));
		} else if (toothType.equals("Mixed")) {
			mixed.click();
			Assert.assertTrue(
					loginPage.getDriver().findElement(By.xpath("//div[@id='mixed']//span[text()='" + teethNo + "']"))
							.getAttribute("class").contains("selected"));
		}
	}

	public void deSelectedInRespective(String toothType, String teethNo) {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(tissue);
		if (toothType.equals("Adult")) {
			Assert.assertTrue(
					!loginPage.getDriver().findElement(By.xpath("//div[@id='adult']//span[text()='" + teethNo + "']"))
							.getAttribute("class").contains("selected"));
		} else if (toothType.equals("Pedo")) {
			pedo.click();
			Assert.assertTrue(
					!loginPage.getDriver().findElement(By.xpath("//div[@id='pedo']//span[text()='" + teethNo + "']"))
							.getAttribute("class").contains("selected"));
		} else if (toothType.equals("Mixed")) {
			mixed.click();
			Assert.assertTrue(
					!loginPage.getDriver().findElement(By.xpath("//div[@id='mixed']//span[text()='" + teethNo + "']"))
							.getAttribute("class").contains("selected"));
		}
	}

	public void selectedAfterClickOnEdit(String toothType, String teethNo) {
		loginPage.waitForSpinnerToDisappear();
		loginPage.waitForElementToBeClickable(tissue);
		loginPage.waitForElementVisibility(tissue, 4000);
		if (toothType.equals("Adult")) {
			Assert.assertTrue(
					loginPage.getDriver().findElement(By.xpath("//div[@id='adult']//span[text()='" + teethNo + "']"))
							.getAttribute("class").contains("selected"));
		} else if (toothType.equals("Pedo")) {
			Assert.assertTrue(
					loginPage.getDriver().findElement(By.xpath("//div[@id='pedo']//span[text()='" + teethNo + "']"))
							.getAttribute("class").contains("selected"));
		} else if (toothType.equals("Mixed")) {
			Assert.assertTrue(
					loginPage.getDriver().findElement(By.xpath("//div[@id='mixed']//span[text()='" + teethNo + "']"))
							.getAttribute("class").contains("selected"));
		}
	}

	public void clickOnCloseBtn() {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(closeBtnOnTeethPopup);
		closeBtnOnTeethPopup.click();
	}

	public void clickOnYes() {
		loginPage.waitForSpinnerToDisappear();;
		loginPage.waitForElementVisibility(yesOnMultiselect,4000);
		yesOnMultiselect.click();
		loginPage.waitForElementToDisappear((By.xpath("//div[@id='confirmbox']//span[@class='cmnicons yes-mdl']")));
	}

	public void verifySelectedTeethOnPopup(String teeth, int no) {
		loginPage.waitForElementVisibility(closeBtnOnTeethPopup,4000);
		loginPage.waitForElementToBeClickable(closeBtnOnTeethPopup);
		Assert.assertTrue(loginPage.getDriver()
				.findElement(By.xpath("(//ul[@id='observationpopupul']//span[@class='ng-binding'])[" + no + "]"))
				.getText().contains(teeth));
	}

	public void clickOnEditOfChiefComplaint() {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(chiefComplaintEditBtn);
		chiefComplaintEditBtn.click();
	}

	public void checkedDeletedChiefComplaints(String chiefComplaint) {
		loginPage.waitForSpinnerToDisappear();;
		List<WebElement> webElement = loginPage.getDriver()
				.findElements(By.xpath("//span[text()='" + chiefComplaint + "']/following-sibling::a"));
		Assert.assertTrue(webElement.size() == 0);
	}
	
	public void removeChiefComplaintsOnOralExam(String chiefComplaint) {
		loginPage.waitForModalOverlayToDisappear();
		WebElement webElement = loginPage.getDriver()
				.findElement(By.xpath("//span[text()='" + chiefComplaint + "']/following-sibling::a"));
		loginPage.waitForElementToBeClickable(webElement);
		webElement.click();
		loginPage.waitForElementToDisappear(By.xpath("//span[contains(text(),'Chief Complaint deleted successfully')]"));
	}

//	public void removeChiefComplaintsOnOralExam(String chiefComplaint) {
//		loginPage.waitForPageLoad();
//		loginPage.waitForElementToDisappear(By.xpath("//span[contains(text(),'Chief Complaint deleted successfully')]"));
//		WebElement webElement = loginPage.getDriver()
//				.findElement(By.xpath("//span[text()='" + chiefComplaint + "']/following-sibling::a"));
//		loginPage.waitForElementToBeClickable(webElement);
//		webElement.click();
//		try {
//			Thread.sleep(5000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

	public void checkedChiefComplaintsOnOralExam(String chiefComplaint) {
		loginPage.waitForPageLoad();
		WebElement webElement = loginPage.getDriver()
				.findElement(By.xpath("//span[text()='" + chiefComplaint + "']/following-sibling::a"));
		loginPage.waitForElementToBeClickable(webElement);
		Assert.assertTrue(checkedElementDisplayed(webElement));
	}

	public void clickOnAllTeeth() {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(AllTeeth);
		AllTeeth.click();
		loginPage.waitForPageLoad();
	}

	public void allTeethButtonDeselected() {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(AllTeeth);
		Assert.assertTrue(
				!AllTeeth.getAttribute("class").contains("selected") && AllTeethText.getText().contains("All Teeth"));
	}

	public void allTeethButtonSelected() {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(AllTeeth);
		Assert.assertTrue(
				AllTeeth.getAttribute("class").contains("selected") && AllTeethText.getText().contains("Undo All"));
	}

	public void verifyInputListWebElement() {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(SoftTissueInputList);
		Assert.assertTrue(checkedElementDisplayed(ToothNo) && checkedElementDisplayed(ToothSitePerioInputList)
				&& checkedElementDisplayed(SoftTissueInputList) && checkedElementDisplayed(HardTissueInputList)
				&& checkedElementDisplayed(NotesInputList) && checkedElementDisplayed(ActionInputList));
	}

	public void verifyProvisinalsInputList(String teethNo, int NoProvisinals) {
		loginPage.waitForElementVisibility(SoftTissueInputList,4000);
		List<WebElement> webelement2 = loginPage.getDriver().findElements(By.xpath("//span[contains(text(),'"+teethNo+"')]/../../..//td//span[@class='ng-binding ng-scope']"));
		int size = webelement2.size();
		System.out.println("The Size is"+size);
		Assert.assertTrue(size== NoProvisinals);
	}

	public void provisinalsInputListOfMultipleTeeth(String teethNo1, String teethNo2, int NoProvisinals) {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(SoftTissueInputList);
		List<WebElement> webelement2 = loginPage.getDriver().findElements(By.xpath("//span[contains(text(),'" + teethNo1
				+ "," + teethNo2
				+ "')] /../../..//td//span[@class='ng-binding ng-scope']"));
		loginPage.visibilityOfListLocated(webelement2);
		Assert.assertTrue(webelement2.size() == NoProvisinals);
	}

	public void verifyNoteInputList(String teethNo, String notes) {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(SoftTissueInputList);
		Assert.assertTrue(loginPage.getDriver()
				.findElement(By.xpath("//span[contains(text(),'" + teethNo
						+ "')]/../../following-sibling::td/div[@class='textDataVisualizer tdvNotes ng-binding']"))
				.getText().contains(notes));
	}
	
	public void clickOnEditBtnTeethNo(String teethNo) {
		loginPage.scrollToView(ActionInputList);
		WebElement wb = loginPage.getDriver().findElement(By.xpath("//span[contains(text(),'" + teethNo
				+ "')]/../../following-sibling::td//a[contains(@data-ng-click,'oeEditInputList')]"));
		loginPage.scrollToView(wb);
		loginPage.waitForElementVisibility(wb,4000);
		wb.click();
	}

//	public void clickOnEditBtnTeethNo(String teethNo) {
//		try {
//			Thread.sleep(2000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		loginPage.waitForPageLoad();
//		loginPage.getDriver().findElement(By.xpath("//span[contains(text(),'" + teethNo
//				+ "')]/../../following-sibling::td//a[contains(@data-ng-click,'oeEditInputList')]")).click();
//	}

	public void verifyActionBtnOfTeethNo(String teethNo) {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(SoftTissueInputList);
		Assert.assertTrue(loginPage.getDriver()
				.findElement(By.xpath("//span[contains(text(),'" + teethNo
						+ "')]/../../following-sibling::td//a[contains(@data-ng-click,'oeEditInputList')]"))
				.isDisplayed()
				&& loginPage.getDriver()
						.findElement(By.xpath("//span[contains(text(),'" + teethNo
								+ "')]/../../following-sibling::td//a[contains(@data-ng-click,'oeSingleDelete')]"))
						.isDisplayed());
	}
	
	public void alertMsgOnNotSelectProvisional() {
		loginPage.waitForPageLoad();
		loginPage.softAssert().assertTrue((loginPage.getDriver()
				.findElement(By.cssSelector("div[class='alert ng-isolate-scope alert-danger alert-dismissable']"))
				.getText().contains("Please select a provisional diagnosis to add as Oral Exam finding(s)")));
		loginPage.waitForElementToDisappear(((By.xpath("//span[contains(text(),'Please select a provisional diagnosis to add as Oral Exam finding(s)')]"))));
	}

//	public void alertMsgOnNotSelectProvisional() {
//		loginPage.waitForPageLoad();
//		try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		loginPage.softAssert().assertTrue((loginPage.getDriver()
//				.findElement(By.cssSelector("div[class='alert ng-isolate-scope alert-danger alert-dismissable']"))
//				.getText().contains("Please select a provisional diagnosis to add as Oral Exam finding(s)")));
//	}

	public void hardcodeWait() {
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean checkedElementDisplayed(WebElement webElement) {
		return (webElement.isDisplayed());
	}
}
