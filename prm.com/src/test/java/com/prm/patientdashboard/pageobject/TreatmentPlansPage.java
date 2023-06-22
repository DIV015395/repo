package com.prm.patientdashboard.pageobject;

import com.prm.pageobjects.utils.PCDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;

import java.util.List;

/**
 * 
 * @author Ajit Yadav
 *
 */
public class TreatmentPlansPage {
	private PCDriver loginPage;
	private OralExamsPage OralExamsPage;

	public TreatmentPlansPage(PCDriver loginPage) {
		this.loginPage = loginPage;
		PageFactory.initElements(loginPage.getDriver(), this);
		OralExamsPage = new OralExamsPage(loginPage);
	}

	@FindBy(xpath = "//i[text()='Create']")
	private WebElement creatBtn;
	@FindBy(xpath = "//i[text()='Products+']")
	private WebElement productBtn;
	@FindBy(xpath = "//a[text()='Findings']")
	private WebElement findings;
	@FindBy(xpath = "//a[text()='New Teeth']")
	private WebElement NewTeeth;
	@FindBy(xpath = "//i[@class='ng-binding']")
	private WebElement noRecordMsg;
	@FindBy(xpath = "//b[text()='All Teeth']")
	private WebElement AllTeethBtn;
	@FindBy(xpath = "//b[text()='By Arch']")
	private WebElement ByArchBtn;
	@FindBy(xpath = "//b[text()='By Quadrant']")
	private WebElement ByQuadrantBtn;
	@FindBy(xpath = "//b[text()='By Arch']")
	private WebElement byArchBtn;
	@FindBy(xpath = "//h1[text()='Treatment Plans']")
	private WebElement header;
	@FindBy(xpath = "//div[@id='confirmbox']//button[@class='btn btn-warning confirmBtn']")
	private WebElement yesOnMultiselect;

	// ------------webelement of the popup after selected teeth image------
	@FindBy(xpath = "//h4[contains(@class,'modal-title')]/span[contains(text(),'Findings')]")
	private WebElement titleOfPopup;
	@FindBy(xpath = "//div[@id='tgrouppopupheader']//button[@aria-label='Close']")
	private WebElement closeBtn;
	@FindBy(xpath = "//a[text()='Treatment Groups']")
	private WebElement treatmentGroups;
	@FindBy(xpath = "//div[contains(@class,'searchTreatmentBox')]/input")
	private WebElement searchBox;
	@FindBy(xpath = "//button[@ng-click='treatmentSave()']")
	private WebElement saveBtn;
	@FindBy(xpath = "//div[contains(text(),'Consultations / X-Rays')]")
	private WebElement consultation_XRays;
	@FindBy(xpath = "//div[contains(text(),'Endo')]")
	private WebElement endo;;
	@FindBy(xpath = "//div[contains(text(),'Minor Oral Surgery')]")
	private WebElement minorOralSurgery;
	@FindBy(xpath = "//div[contains(text(),'Extractions')]")
	private WebElement extractions;
	@FindBy(xpath = "//div[contains(text(),'Prostho (Except Crowns)')]")
	private WebElement prosthoExceptCrowns;
	@FindBy(xpath = "//div[text()='Crowns ']")
	private WebElement crowns;
	@FindBy(xpath = "//div[contains(text(),'Implant Crowns')]")
	private WebElement implantCrowns;
	@FindBy(xpath = "//div[contains(text(),'Ortho')]")
	private WebElement ortho;
	@FindBy(xpath = "//div[contains(text(),'Perio')]")
	private WebElement perio;
	@FindBy(xpath = "//div[contains(text(),'Aligners (LMB)')]")
	private WebElement alignersLMB;
	@FindBy(xpath = "//div[contains(text(),'Aligners (Others)')]")
	private WebElement alignersOthers;
	@FindBy(xpath = "//div[contains(text(),'Pedo')]")
	private WebElement pedo;
	@FindBy(xpath = "//div[contains(text(),'Implants (AB)')]")
	private WebElement implantsAB;
	@FindBy(xpath = "//div[contains(text(),'Implants (Nobel)')]")
	private WebElement implantsNobel;
	@FindBy(xpath = "//div[contains(text(),'Implant (Others)')]")
	private WebElement implantOthers;
	@FindBy(xpath = "//div[contains(text(),'Bone Graft/ Membrane / PRF')]")
	private WebElement boneGraftAndMembranesPRF;;
	@FindBy(xpath = "//div[contains(text(),'Lasers')]")
	private WebElement lasers;
	@FindBy(xpath = "//div[contains(text(),'IP/ CDOP/ Bank')]")
	private WebElement ip_CDOP_Bank;
	@FindBy(xpath = "//div[contains(text(),'Membership')]")
	private WebElement membership;
	@FindBy(xpath = "//div[text()='Others ']")
	private WebElement others;
	@FindBy(xpath = "//div[@class='contentBx']//span[@class='ng-binding']")
	private List<WebElement> treatments;
	@FindBy(css = "span[class='glyphicon glyphicon-pencil']")
	private WebElement oralExamBtn;
	@FindBy(xpath = "//span[contains(text(),'Please select a treatment before clicking on save!')]")
	private WebElement withoutTreatmentMsg;
	@FindBy(xpath = "//label[@for='bridge']")
	private WebElement partOfBridgeChkBx;

	// ----------------webElement of oral exam popup after selecting pencil in
	// treatment popup----------------------
	@FindBy(xpath = "//div[@id='obpopupmodalheader']/../preceding-sibling::div//span[@class='cmnicons cncl-mdl']")
	private WebElement closeBtnOralExam;
	@FindBy(xpath = "//div[@id='obpopupmodalheader']/../preceding-sibling::div//span[@class='cmnicons sv-mdl']")
	private WebElement saveBtnOralExam;
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
	@FindBy(xpath = "//div[contains(@ng-click,'removeToothSoftTissueItems')]//a[@class='remove-button']")
	private List<WebElement> provisionalRemoveBtnSoftTissue;

	// -------------------------------IOPAR webelement on treatment on treatment
	// plan------------
	@FindBy(xpath = "//i[text()='IOPAR']")
	private WebElement IOPARBtn;
	@FindBy(id = "ioprRemark")
	private WebElement IOPARNotes;
	@FindBy(xpath = "//div[@class='content']")
	private List<WebElement> Provisinals;
	@FindBy(xpath = "//a[text()='Selected Treatment']")
	private WebElement selectedTreatment;

	// -----------webElement after added in input list of treatment page
	@FindBy(xpath = "//p[text()='Do you want to delete the selected treatment?']/../..//span[@class='cmnicons yes-mdl']")
	private WebElement yesForDelete;
	@FindBy(xpath = "//span[text()='Treatment deleted successfully!']")
	private WebElement deleteSuccessMessage;
	@FindBy(xpath = "//table[@id='treatmentplanedittable']//span[@class='actn-icn edit dashboardEditIcon']")
	private WebElement editBtnTreatmentInputList;
	@FindBy(xpath = "//table[@id='treatmentplanedittable']/following-sibling::div//span[@class='cmnicons sv-mdl']")
	private WebElement SaveBtnOnInputList;
	@FindBy(xpath = "//div[@id='cubeboxwrapper']//span[text()='Buccal']")
	private WebElement Buccal;

	// --------------webelement and funtions of discount
	@FindBy(xpath = "//span[contains(text(),'Please Select Coupon(s) Basis')]")
	private WebElement withoutCouponMsg;

	// -------------webelement treatment input list------------------------//
	@FindBy(xpath = "//table[@id='treatmentplanedittable']//th[text()='Source']")
	private WebElement sourceInputList;
	@FindBy(xpath = "//table[@id='treatmentplanedittable']//th[text()='Start']")
	private WebElement startInputList;
	@FindBy(xpath = "//table[@id='treatmentplanedittable']//th[text()='Treatment']")
	private WebElement treatmentInputList;
	@FindBy(xpath = "//table[@id='treatmentplanedittable']//th[text()='Type']")
	private WebElement typeInputList;
	@FindBy(xpath = "//table[@id='treatmentplanedittable']//th[text()='Unit Cost']")
	private WebElement unitCostInputList;
	@FindBy(xpath = "//table[@id='treatmentplanedittable']//th[text()='Net Amt.']")
	private WebElement netAmtInputList;
	@FindBy(xpath = "//table[@id='treatmentplanedittable']//th[text()='Coupon']")
	private WebElement couponInputList;
	@FindBy(xpath = "//table[@id='treatmentplanedittable']//th[text()='Amt. Pay.']")
	private WebElement AmtPayInputList;
	@FindBy(xpath = "//table[@id='treatmentplanedittable']//th[text()='Coupon Basis']")
	private WebElement couponBasisInputList;
	@FindBy(xpath = "//table[@id='treatmentplanedittable']//th[text()='Action']")
	private WebElement actionInputList;
	@FindBy(xpath = "//td[contains(@data-ng-class,'Treatment.tr_name_emphasis')]")
	private List<WebElement> treatmentsInputList;
	@FindBy(xpath = "//td[contains(@data-ng-class,'Treatment.tr_name_emphasis')]/preceding-sibling::td//label[@class='assign_area']")
	private List<WebElement> checkBoxInputList;
	@FindBy(xpath = "//td[contains(@data-ng-class,'Treatment.tr_name_emphasis')]/following-sibling::td//select[@ng-model='tretChildOne.Treatment.treatmenttype']")
	private List<WebElement> treatmentTypeInputList;
	@FindBy(xpath = "//td[contains(@data-ng-class,'Treatment.tr_name_emphasis')]/following-sibling::td//span[contains(@ng-class,'tretChildOne.Treatment.unit_price')]")
	private List<WebElement> unitPriceNetPriceInputlist;
	@FindBy(xpath = "//td[contains(@data-ng-class,'Treatment.tr_name_emphasis')]/following-sibling::td//select[@ng-model='tretChildOne.Treatment.discount_type']")
	private List<WebElement> discountTypeInputList;
	@FindBy(xpath = "//td[contains(@data-ng-class,'Treatment.tr_name_emphasis')]/following-sibling::td//input[@ng-model='tretChildOne.Treatment.discount']")
	private List<WebElement> discountValueInputList;
	@FindBy(xpath = "//td[contains(@data-ng-class,'Treatment.tr_name_emphasis')]/following-sibling::td//input[@ng-model='tretChildOne.Treatment.discount']")
	private List<WebElement> discountInputList;
	@FindBy(xpath = "//td[contains(@data-ng-class,'Treatment.tr_name_emphasis')]/following-sibling::td//span[contains(@ng-class,'tretChildOne.Treatment.new_fee')]")
	private List<WebElement> amountPayInputList;
	@FindBy(xpath = "//td[contains(@data-ng-class,'Treatment.tr_name_emphasis')]/following-sibling::td//select[@ng-model='tretChildOne.Treatment.remarks']")
	private List<WebElement> couponsInputList;
	@FindBy(xpath = "//td[contains(@data-ng-class,'Treatment.tr_name_emphasis')]/following-sibling::td//a[contains(@class,'trtDeleteIcn')]")
	private List<WebElement> singleTrtDeleteInputList;
	@FindBy(xpath = "//td[contains(@data-ng-class,'Treatment.tr_name_emphasis')]/..")
	private List<WebElement> treatmentsDetail;
	@FindBy(xpath = "//div[contains(@class,'modal-tooth-tissue-box ng-scope')]//span")
	private List<WebElement> provisional;
	@FindBy(xpath = "//span[text()='Treatment(s) could not be started. Please update patient Covid-19 Declaration!']")
	private WebElement covidUpdateAlert;

	public void startCheckBoxInputListNotPresent() {
		loginPage.waitForSpinnerToDisappear();
		if(checkBoxInputList.size() > 0) {
			System.out.println("Patient alreday has/have"+checkBoxInputList.size()+"old treatments");
			Reporter.log("Patient alreday has/have\"+checkBoxInputList.size()+\"old treatments");
		}
		else {
			Assert.assertEquals(checkBoxInputList.size(), 0);
		}
	}
	
	public void selectedTreamentInIputList(String fullName) {
		loginPage.waitForPageLoad();
		int j = 0;
		for (int i = 0; treatmentsInputList.size() > i; i++) {
			if (treatmentsInputList.get(i).getText().contains(fullName)) {
				if (!treatmentsDetail.get(i).getAttribute("class").contains("deleted")) {
					Assert.assertTrue(checkedWebElementDisplayed(treatmentsInputList.get(i))
							&& checkedWebElementDisplayed(treatmentTypeInputList.get(i - j))
							&& (unitPriceNetPriceInputlist.size() == treatmentsInputList.size() * 2)
							&& checkedWebElementDisplayed(discountTypeInputList.get(i - j))
							&& checkedWebElementDisplayed(discountInputList.get(i - j))
							&& checkedWebElementDisplayed(amountPayInputList.get(i))
							&& checkedWebElementDisplayed(couponsInputList.get(i - j))
							&& checkedWebElementDisplayed(singleTrtDeleteInputList.get(i - j)));
					break;
				}
			} else {
				j++;
			}
		}
	}

//	public void selectedTreamentInIputList(String fullName) {
//		loginPage.waitForPageLoad();
//		int j = 0;
//		for (int i = 0; treatmentsInputList.size() > i; i++) {
//			if (treatmentsInputList.get(i).getText().contains(fullName)) {
//				if (!treatmentsDetail.get(i).getAttribute("class").contains("deleted")) {
//					Assert.assertTrue(checkedWebElementDisplayed(treatmentsInputList.get(i))
//							&& checkedWebElementDisplayed(checkBoxInputList.get(i - j))
//							&& checkedWebElementDisplayed(treatmentTypeInputList.get(i - j))
//							&& (unitPriceNetPriceInputlist.size() == treatmentsInputList.size() * 2)
//							&& checkedWebElementDisplayed(discountTypeInputList.get(i - j))
//							&& checkedWebElementDisplayed(discountInputList.get(i - j))
//							&& checkedWebElementDisplayed(amountPayInputList.get(i))
//							&& checkedWebElementDisplayed(couponsInputList.get(i - j))
//							&& checkedWebElementDisplayed(singleTrtDeleteInputList.get(i - j)));
//					break;
//				}
//			} else {
//				j++;
//			}
//		}
//	}

	public void selectTreatmentInputList(String treatment) {
		loginPage.waitForPageLoad();
		try {
			Thread.sleep(5000);
			WebElement web = loginPage.getDriver().findElement(
					By.xpath("//*[text()[contains(.,'" + treatment + "')]]/../preceding-sibling::td//label"));
			web.click();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void setCovidUpdateAlert() {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(covidUpdateAlert);
		Assert.assertTrue(checkedWebElementDisplayed(covidUpdateAlert));
	}
	
	
	public void checkedInputListDataName() {
		    loginPage.waitForSpinnerToDisappear();
			loginPage.WaitTillElementIsPresent(sourceInputList);
			Assert.assertTrue(checkedWebElementDisplayed(sourceInputList) && checkedWebElementDisplayed(startInputList)
					&& checkedWebElementDisplayed(treatmentInputList) && checkedWebElementDisplayed(typeInputList)
					&& checkedWebElementDisplayed(unitCostInputList) && checkedWebElementDisplayed(netAmtInputList)
					&& checkedWebElementDisplayed(couponInputList) && checkedWebElementDisplayed(couponBasisInputList)
					&& checkedWebElementDisplayed(actionInputList));
	}

//	public void checkedInputListDataName() {
//		loginPage.waitForPageLoad();
//		try {
//			Thread.sleep(3000);
//			loginPage.WaitTillElementIsPresent(sourceInputList);
//			Assert.assertTrue(checkedWebElementDisplayed(sourceInputList) && checkedWebElementDisplayed(startInputList)
//					&& checkedWebElementDisplayed(treatmentInputList) && checkedWebElementDisplayed(typeInputList)
//					&& checkedWebElementDisplayed(unitCostInputList) && checkedWebElementDisplayed(netAmtInputList)
//					&& checkedWebElementDisplayed(couponInputList) && checkedWebElementDisplayed(couponBasisInputList)
//					&& checkedWebElementDisplayed(actionInputList));
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

	public void checkEditSaveInputList(String teethNo) {
		loginPage.waitForPageLoad();
		Assert.assertTrue(checkedWebElementDisplayed(loginPage.getDriver()
				.findElement(By.xpath("//table[@id='treatmentplanedittable']//span[text()='" + teethNo
						+ "']/../following-sibling::td//a[contains(@ng-click,'openTreatmentEditPopup')]")))
				&& checkedWebElementDisplayed(SaveBtnOnInputList));
	}

	public void checkDeleteInputList(String teethNo) {
		loginPage.waitForPageLoad();
		Assert.assertTrue(checkedWebElementDisplayed(loginPage.getDriver()
				.findElement(By.xpath("//table[@id='treatmentplanedittable']//span[text()='" + teethNo
						+ "']/../following-sibling::td//a[contains(@ng-click,'treatmentToothGroupDelete')]")))
				&& checkedWebElementDisplayed(SaveBtnOnInputList));
	}

	public void clickOnCreatebtn() {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(creatBtn);
		creatBtn.click();
	}

	public void verifyHeader() {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(header);
		Assert.assertTrue(checkedWebElementDisplayed(header));
	}

	public void allTeethByQuadrantAndByArchIsPresent() {
//		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(AllTeethBtn);
		Assert.assertTrue(checkedWebElementDisplayed(AllTeethBtn) && checkedWebElementDisplayed(ByArchBtn)
				&& checkedWebElementDisplayed(ByQuadrantBtn));
	}

	public void allTeethAndByQuadrantIsPresent() {
//		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(AllTeethBtn);
		Assert.assertTrue(checkedWebElementDisplayed(AllTeethBtn) && checkedWebElementDisplayed(ByQuadrantBtn));
	}

	public void verifyNoRecordMsg() {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(noRecordMsg);
		Assert.assertTrue(noRecordMsg.getText().contains("No Record Found"));
	}

	public void verifyCreateBtnIsPresent() {
//		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(creatBtn);
		Assert.assertTrue(checkedWebElementDisplayed(creatBtn));
	}

	public void verifyProductAddBtnIsPresent() {
//		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(productBtn);
		Assert.assertTrue(checkedWebElementDisplayed(productBtn));
	}

	public void clickOnYes() {
		loginPage.waitForPageLoad();
		try {
			Thread.sleep(2500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		yesOnMultiselect.click();
	}
	
	public void clickOnNewTeethBtn() {
		loginPage.waitForSpinnerToDisappear();
		loginPage.waitForElementToBeClickable(NewTeeth);
		NewTeeth.click();
	}
	
	public void clickOnFindingsBtn() {
		loginPage.waitForPageToBecomeActive();
		loginPage.waitForSpinnerToDisappear();
		loginPage.waitForElementVisibility(findings,4000);
		loginPage.waitForElementToBeClickable(findings);
		findings.click();
	}

//	public void clickOnFindingsBtn() {
//		loginPage.waitForPageLoad();
//		loginPage.waitForElementToBeClickable(findings);
//		try {
//			Thread.sleep(4000);
//			findings.click();
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

	public void clickOnTreatmentGroups() {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(treatmentGroups);
		treatmentGroups.click();
	}
	
	public void clickOnOralExamBtn() {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(oralExamBtn);
		oralExamBtn.click();
	}

//	public void clickOnOralExamBtn() {
//		loginPage.waitForPageLoad();
//		loginPage.waitForElementToBeClickable(oralExamBtn);
//		try {
//			oralExamBtn.click();
//			Thread.sleep(2000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//	}

	public void clickOnConsultationXRays() {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(consultation_XRays);
		consultation_XRays.click();
	}

	public void clickOnEndo() {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(endo);
		endo.click();
	}

	public void clickOnExtractions() {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(extractions);
		extractions.click();
	}

	public void clickOnMinorOralSurgery() {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(minorOralSurgery);
		minorOralSurgery.click();
	}

	public void clickOnProsthoExceptCrowns() {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(prosthoExceptCrowns);
		prosthoExceptCrowns.click();
	}

	public void clickOnCrowns() {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(crowns);
		crowns.click();
	}

	public void clickOnImplantCrowns() {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(implantCrowns);
		implantCrowns.click();
	}

	public void clickOnOrtho() {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(ortho);
		ortho.click();
	}

	public void clickOnPerio() {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(perio);
		perio.click();
	}

	public void clickOnAlignersLMB() {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(alignersLMB);
		alignersLMB.click();
	}

	public void clickOnAlignersOthers() {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(alignersOthers);
		alignersOthers.click();
	}

	public void clickOnPedo() {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(pedo);
		pedo.click();
	}

	public void clickOnImplantsAB() {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(implantsAB);
		implantsAB.click();
	}

	public void clickOnImplantsNobel() {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(implantsNobel);
		implantsNobel.click();
	}

	public void clickOnImplantOthers() {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(implantOthers);
		implantOthers.click();
	}

	public void clickOnBoneGraftAndMembranes() {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(boneGraftAndMembranesPRF);
		boneGraftAndMembranesPRF.click();
	}

	public void clickOnLasers() {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(lasers);
		lasers.click();
	}

	public void clickOnIpCDOPBank() {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(ip_CDOP_Bank);
		ip_CDOP_Bank.click();
	}

	public void clickOnMembership() {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(membership);
		membership.click();
	}

	public void clickOnOthers() {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(others);
		others.click();
	}

	public void clickOnSearchField(String treatName) {
		loginPage.waitForPageLoad();
		try {
			Thread.sleep(3000);
			searchBox.sendKeys(treatName);
			searchBox.click();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void verifyTreatments() {
    loginPage.waitForSpinnerToDisappear();
	loginPage.visibilityOfListLocated(treatments);
	Assert.assertTrue(treatments.size() > 0);
}
	
	

//	public void verifyTreatments() {
//		loginPage.waitForPageLoad();
//		try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		loginPage.visibilityOfListLocated(treatments);
//		Assert.assertTrue(treatments.size() > 0);
//	}

	public void checkWebElementsOfPopup() {
//		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(searchBox);
		Assert.assertTrue(checkedWebElementDisplayed(treatmentGroups) && checkedWebElementDisplayed(searchBox)
				&& checkedWebElementDisplayed(saveBtn) && checkedWebElementDisplayed(closeBtn)
				&& titleOfPopup.getText().contains("Findings"));
	}
	
	public void saveTreatment() {
		loginPage.waitForElementVisibility(saveBtn,4000);
		saveBtn.click();
		loginPage.waitForModalOverlayToDisappear();
		loginPage.waitForSpinnerToDisappear();
		loginPage.waitForElementVisibility(typeInputList, 4000);
	}
	
	public void saveTreatmentWithMessage() {
		loginPage.waitForElementVisibility(saveBtn,4000);
		saveBtn.click();
	}

//	public void saveTreatment() {
//		loginPage.waitForPageLoad();
//		loginPage.waitForElementToBeClickable(saveBtn);
//		saveBtn.click();
//		loginPage.waitForPageLoad();
//		try {
//			Thread.sleep(2000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
	public void closeTreatmentPopup() {
			closeBtn.click();
			loginPage.waitForSpinnerToDisappear();
	}

//	public void closeTreatmentPopup() {
//		try {
//			Thread.sleep(1500);
//			loginPage.waitForElementToBeClickable(closeBtn);
//			closeBtn.click();
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//	}
	
	public void verifySeletecdTeethOnPopup(String teeth) {
		loginPage.waitForSpinnerToDisappear();
		loginPage.waitForElementToBeClickable(titleOfPopup);
		Assert.assertTrue(checkedWebElementDisplayed(loginPage.getDriver()
					.findElement(By.xpath("//div[@id='tgrouppopupheader']//span[contains(text(),'" + teeth + "')]")))
					&& titleOfPopup.getText().contains("Findings"));
	}

//	public void verifySeletecdTeethOnPopup(String teeth) {
//		loginPage.waitForPageLoad();
//		try {
//			Thread.sleep(5000);
//			loginPage.waitForElementToBeClickable(titleOfPopup);
//			Assert.assertTrue(checkedWebElementDisplayed(loginPage.getDriver()
//					.findElement(By.xpath("//div[@id='tgrouppopupheader']//span[contains(text(),'" + teeth + "')]")))
//					&& titleOfPopup.getText().contains("Findings"));
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//	}

	public void deselectedTreatment(String shortNameTreatment) {
		loginPage.waitForPageLoad();
		try {
			Thread.sleep(1000);
			List<WebElement> web = loginPage.getDriver().findElements(By.xpath("//div[@class='content-trtmnt']"));
			List<WebElement> web1 = loginPage.getDriver().findElements(By.xpath("//div[@class='content-trtmnt']"));
			for (int i = 0; web.size() > i; i++) {
				if (web.get(i).getText().contains(shortNameTreatment)) {
					Assert.assertFalse(web1.get(i).getAttribute("class").contains("selected"));
				} else {
					continue;
				}
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void verifyWithoutTreatmentMsg() {
		Assert.assertTrue(checkedWebElementDisplayed(withoutTreatmentMsg));
	}

//	public void verifyWithoutTreatmentMsg() {
//		loginPage.waitForPageLoad();
//		try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		Assert.assertTrue(checkedWebElementDisplayed(withoutTreatmentMsg));
//	}

	public void clickOnSaveOralBtn() {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(saveBtnOralExam);
		saveBtnOralExam.click();
	}

	public void selectedSoftTissueProvisionalDisplayedBelow() {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(OralExamsPage.toothSitePerio);
		Assert.assertTrue(provisionalRemoveBtnSoftTissue.size() == 1 || provisionalRemoveBtnSoftTissue.size() == 2
				|| provisionalRemoveBtnSoftTissue.size() == 3 || provisionalRemoveBtnSoftTissue.size() == 4
				|| provisionalRemoveBtnSoftTissue.size() == 5 || provisionalRemoveBtnSoftTissue.size() == 6
				|| provisionalRemoveBtnSoftTissue.size() == 7 || provisionalRemoveBtnSoftTissue.size() == 8
				|| provisionalRemoveBtnSoftTissue.size() == 9 || provisionalRemoveBtnSoftTissue.size() == 10);
	}

	public void clickOnSoftTissueProvisinals() {
		loginPage.waitForPageLoad();
		try {
			Thread.sleep(2000);
			loginPage.getDriver()
					.findElement(By.xpath("(//div[contains(@ng-click,'softDiagno.ProvisionalDiagnostic.bsId')])[1]"))
					.click();
		} catch (StaleElementReferenceException | InterruptedException e) {
			loginPage.getDriver()
					.findElement(By.xpath("(//div[contains(@ng-click,'softDiagno.ProvisionalDiagnostic.bsId')])[1]"))
					.click();
		}
	}

	public void provisionalIsSelectedOfSoftTissue(String name) {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(OralExamsPage.toothSitePerio);
		Assert.assertTrue(loginPage.getDriver()
				.findElement(By.xpath("//div[contains(@ng-click,'addToothStissueItem')]//h5[text()='" + name + "']"))
				.getAttribute("class").contains("selected"));
	}

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

	public void clickOnCloseBtnOralExam() {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(closeBtnOralExam);
		closeBtnOralExam.click();
	}

	public void clickOnSelectedTreatment() {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(selectedTreatment);
		selectedTreatment.click();
	}

	public void verifyIoparBtn() {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(IOPARBtn);
		Assert.assertTrue(checkedWebElementDisplayed(IOPARBtn));
	}

	public void verifyIOPARNotes() {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(IOPARNotes);
		Assert.assertTrue(
				checkedWebElementDisplayed(IOPARNotes) && IOPARNotes.getAttribute("maxlength").contains("768"));
	}

	public void clickOnIOPARBtn() {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(IOPARBtn);
		IOPARBtn.click();
	}

	public void verifyProvisinals() {
		loginPage.waitForPageLoad();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Assert.assertTrue(Provisinals.size() > 0);
	}

	public void removeTreatmentFromSelectedTreatmentTab(String treatment) {
		loginPage.waitForPageLoad();
		WebElement web = loginPage.getDriver().findElement(By.xpath(
				"//span[contains(text(),'" + treatment + "')]/following-sibling::div//span[@class='deletebtn']"));
		loginPage.waitForElementToBeClickable(web);
		web.click();
	}
	
	public void clickOnSaveBtnTreatmentInputList() {
		loginPage.waitForElementVisibility(SaveBtnOnInputList,4000);
		loginPage.waitForElementToBeClickable(SaveBtnOnInputList);
		SaveBtnOnInputList.click();
		loginPage.waitForSpinnerToDisappear();
	}

//	public void clickOnSaveBtnTreatmentInputList() {
//		loginPage.waitForPageLoad();
//		loginPage.waitForElementToBeClickable(SaveBtnOnInputList);
//		try {
//			Thread.sleep(2500);
//			SaveBtnOnInputList.click();
//			Thread.sleep(2500);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

	public void clickOnEditBtnTreatmentInputList() {
		loginPage.waitForModalOverlayToDisappear();
		loginPage.waitForSpinnerToDisappear();
		loginPage.waitForElementVisibility(editBtnTreatmentInputList,4000);
		loginPage.waitForElementToBeClickable(editBtnTreatmentInputList);
		editBtnTreatmentInputList.click();
	}

	public void verifyDeleteSuccessMsg() {
		loginPage.waitForPageLoad();
		try {
			Thread.sleep(1000);
			Assert.assertTrue(checkedWebElementDisplayed(deleteSuccessMessage));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void clickOnYesForDelete() {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(yesForDelete);
		yesForDelete.click();
	}
	
	public void clickOnTreatments(String shortNameTreatment) {
		loginPage.waitForSpinnerToDisappear();
			List<WebElement> web = loginPage.getDriver().findElements(By.xpath("//div[@class='content-trtmnt']"));
			for (int i = 0; web.size() > i; i++) {
				if (web.get(i).getText().contains(shortNameTreatment)) {
					web.get(i).click();
				} else {
					continue;
				}
			}
	}

//	public void clickOnTreatments(String shortNameTreatment) {
//		loginPage.waitForPageLoad();
//		try {
//			Thread.sleep(1500);
//			List<WebElement> web = loginPage.getDriver().findElements(By.xpath("//div[@class='content-trtmnt']"));
//			for (int i = 0; web.size() > i; i++) {
//				if (web.get(i).getText().contains(shortNameTreatment)) {
//					web.get(i).click();
//				} else {
//					continue;
//				}
//			}
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

	public void checkTreatmentsIsSelectedAsBridgeable(String treatment) {
		loginPage.waitForPageLoad();
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebElement ele = loginPage.getDriver().findElement(By.xpath("//span[text()='" + treatment
				+ "']/../following-sibling::span[@class='price-trtmnt ng-binding bridgeTrt bridgeTrtchk']"));
		Assert.assertTrue(checkedWebElementDisplayed(ele));
	}

	public void clickPartOfBridge() {
		loginPage.waitForPageLoad();
		partOfBridgeChkBx.click();

	}

	public void deleteTreatment(String treatmentName) {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(findings);
		for (int i = 0; treatmentsInputList.size() > i; i++) {
			if (treatmentsInputList.get(i).getText().contains(treatmentName)) {
				singleTrtDeleteInputList.get(i).click();
			} else {
				continue;
			}
		}
	}

	public void verifyAfterDelete(String treatment) {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(findings);
		for (int i = 0; treatmentsInputList.size() > i; i++) {
			if (treatmentsInputList.get(i).getText().contains(treatment)) {
				Assert.assertTrue(treatmentsDetail.get(i).getAttribute("class").contains("deleted"));
				break;
			} else {
				continue;
			}
		}
	}

	public void clickOnProvisionals(String provisinals) {
		loginPage.waitForPageLoad();
		try {
			Thread.sleep(2000);
			for (int i = 0; provisional.size() > i; i++) {
				if (provisional.get(i).getText().contains(provisinals)) {
					provisional.get(i).click();
				} else {
					continue;
				}
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void verifySelectedProvisinalsIntreatmentPopup(String provisinals) {
		loginPage.waitForPageLoad();
		try {
			Thread.sleep(1000);
			WebElement web = loginPage.getDriver()
					.findElement(By.xpath("//a/span[contains(text(),'" + provisinals + "')]"));
			loginPage.waitForElementToBeClickable(web);
			Assert.assertTrue(checkedWebElementDisplayed(web));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void clickOnBuccalForMultipleTeeth() {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(Buccal);
		Buccal.click();
	}

	public void verifyToothOnFinding(String teethNo) {
		loginPage.waitForPageLoad();
		String str = "toothBind tooth" + teethNo;
		WebElement web = loginPage.getDriver().findElement(By.xpath("//div[contains(@class,'" + str + "')]"));
		loginPage.waitForElementToBeClickable(web);
		Assert.assertTrue(checkedWebElementDisplayed(web));
	}

	public void verifyTissuesInFinding(String teethNo, String Tissues) {
		loginPage.waitForPageLoad();
		String str = "toothBind tooth" + teethNo;
		WebElement web = loginPage.getDriver()
				.findElement(By.xpath("//div[contains(@class,'" + str
						+ "')]//ancestor::div[@id='softTissue_left']/following-sibling::div//span[contains(text(),'"
						+ Tissues + "')]"));
		loginPage.waitForElementToBeClickable(web);
		Assert.assertTrue(checkedWebElementDisplayed(web));
	}

	public void verifyProvisionalsInFinding(String teethNo, String provisinals) {
		loginPage.waitForPageLoad();
		String str = "toothBind tooth" + teethNo;
		WebElement web = loginPage.getDriver()
				.findElement(By.xpath("//div[contains(@class,'" + str
						+ "')]//ancestor::div[@id='softTissue_left']/following-sibling::div//span[contains(text(),'"
						+ provisinals + "')]"));
		loginPage.waitForElementToBeClickable(web);
		Assert.assertTrue(checkedWebElementDisplayed(web));
	}

	public void verifySelectBtn(String teethNo) {
		loginPage.waitForPageLoad();
		String str = "toothBind tooth" + teethNo;
		WebElement web = loginPage.getDriver().findElement(By.xpath("//div[contains(@class,'" + str
				+ "')]//ancestor::div[@id='softTissue_left']/following-sibling::div//span[contains(@class,'inputSelectionView chkB')]"));
		loginPage.waitForElementToBeClickable(web);
		Assert.assertTrue(checkedWebElementDisplayed(web));
	}

	public void selectTeethInFindings(String teethNo) {
		loginPage.waitForPageLoad();
		String str = "toothBind tooth" + teethNo;
		WebElement web = loginPage.getDriver().findElement(By.xpath("//div[contains(@class,'" + str
				+ "')]//ancestor::div[@id='softTissue_left']/following-sibling::div//b[contains(text(),'Select')]"));
		loginPage.waitForElementToBeClickable(web);
		loginPage.executeScript(web);
	}
	
	public void intiallyBothPriceSameDiscount(String fullname) {
		loginPage.waitForPageLoad();
		String netAmountPrice, amountPayablePrice;
		for (int i = 0; treatmentsInputList.size() > i; i++) {
			if (treatmentsInputList.get(i).getText().contains(fullname)) {
				Select select = new Select(treatmentTypeInputList.get(i));
				netAmountPrice = unitPriceNetPriceInputlist.get(i * 2).getText();
				amountPayablePrice = amountPayInputList.get(i).getText();
				Assert.assertTrue(select.getFirstSelectedOption().getText().contains("Recommended")
						&& netAmountPrice.equals(amountPayablePrice));
				break;
			}
		}
	}
	

//	public void intiallyBothPriceSameDiscount(String fullname) {
//		loginPage.waitForPageLoad();
//		String netAmountPrice, amountPayablePrice;
//		for (int i = 0; treatmentsInputList.size() > i; i++) {
//			if (treatmentsInputList.get(i).getText().contains(fullname)) {
//				Select select = new Select(treatmentTypeInputList.get(i));
//				netAmountPrice = unitPriceNetPriceInputlist.get(i * 2).getText();
//				amountPayablePrice = amountPayInputList.get(i).getText();
//				Select sel = new Select(discountTypeInputList.get(i));
//     			String discountValue = discountValueInputList.get(i).getText();
//				Assert.assertTrue(select.getFirstSelectedOption().getText().contains("Recommended")
//						&& netAmountPrice.equals(amountPayablePrice)
//						&& sel.getFirstSelectedOption().getText().contains("%") && discountValue.contains("0.00"));
//				break;
//			}
//		}
//	}

	public void couponBasis(String fullname) {
		loginPage.waitForPageLoad();
		for (int i = 0; treatmentsInputList.size() > i; i++) {
			if (treatmentsInputList.get(i).getText().contains(fullname)) {
				loginPage.selectFromDropDownByIndex(couponsInputList.get(i), 2);
				break;
			}
		}
	}

	public void checkFunctionalityOfPercentageDis(String fullname, float inPercentage) {
		loginPage.waitForPageLoad();
		String actualPrice, amountPayablePrice;
		float actual;
		for (int i = 0; treatmentsInputList.size() > i; i++) {
			if (treatmentsInputList.get(i).getText().contains(fullname)) {
				actualPrice = unitPriceNetPriceInputlist.get(i * 2).getText();
				amountPayablePrice = amountPayInputList.get(i).getText();
				if (actualPrice.contains(",")) {
					String[] str = actualPrice.split(",");
					String actualStr1 = str[0];
					String actualStr2 = str[1];
					int indexOfLast = actualStr2.lastIndexOf(".");
					String newString = actualStr2;
					if (indexOfLast >= 0)
						newString = actualStr2.substring(0, indexOfLast);
					String actualStr3 = actualStr1 + newString;
					actual = Float.parseFloat(actualStr3);
				} else {
					actual = Float.parseFloat(actualPrice);
				}
				float discount;
				if (amountPayablePrice.contains(",")) {
					String[] discountStr = amountPayablePrice.split(",");
					String discountStr1 = discountStr[0];
					String discountStr2 = discountStr[1];
					int indexOfLast1 = discountStr2.lastIndexOf(".");
					String newString1 = discountStr2;
					if (indexOfLast1 >= 0)
						newString1 = discountStr2.substring(0, indexOfLast1);
					String discountStr3 = discountStr1 + newString1;
					discount = Float.parseFloat(discountStr3);
				} else {
					discount = Float.parseFloat(amountPayablePrice);
				}
				float effectivePrice = (actual * inPercentage) / 100;
				float PriceOnDiscount = actual - effectivePrice;
				Assert.assertTrue(discount == PriceOnDiscount);
			}
		}
	}

	public void checkFunctionalityOfAmountDis(String fullname, float inAmount) {
		loginPage.waitForPageLoad();
		String actualPrice, amountPrice;
		float actual;
		for (int i = 0; treatmentsInputList.size() > i; i++) {
			if (treatmentsInputList.get(i).getText().contains(fullname)) {
				actualPrice = unitPriceNetPriceInputlist.get(i * 2).getText();
				amountPrice = amountPayInputList.get(i).getText();
				if (actualPrice.contains(",")) {
					String[] str = actualPrice.split(",");
					String actualStr1 = str[0];
					String actualStr2 = str[1];
					int indexOfLast = actualStr2.lastIndexOf(".");
					String newString = actualStr2;
					if (indexOfLast >= 0)
						newString = actualStr2.substring(0, indexOfLast);
					String actualStr3 = actualStr1 + newString;
					actual = Float.parseFloat(actualStr3);
				} else {
					actual = Float.parseFloat(actualPrice);
				}
				float discount;
				if (amountPrice.contains(",")) {
					String[] discountStr = amountPrice.split(",");
					String discountStr1 = discountStr[0];
					String discountStr2 = discountStr[1];
					int indexOfLast1 = discountStr2.lastIndexOf(".");
					String newString1 = discountStr2;
					if (indexOfLast1 >= 0)
						newString1 = discountStr2.substring(0, indexOfLast1);
					String discountStr3 = discountStr1 + newString1;
					discount = Float.parseFloat(discountStr3);
				} else {
					discount = Float.parseFloat(amountPrice);
				}
				float PriceOnDiscount = actual - inAmount;
				Assert.assertTrue(discount == PriceOnDiscount);
			}
		}
	}

	public void enterDiscountValue(String fullname, String discount) {
		loginPage.waitForPageLoad();
		for (int i = 0; treatmentsInputList.size() > i; i++) {
			if (treatmentsInputList.get(i).getText().contains(fullname)) {
				discountInputList.get(i).clear();
				discountInputList.get(i).sendKeys(discount);
				break;
			}
		}
	}

	public void discountInPercentage(String fullname) {
		loginPage.waitForPageLoad();
		for (int i = 0; treatmentsInputList.size() > i; i++) {
			if (treatmentsInputList.get(i).getText().contains(fullname)) {
				loginPage.selectFromDropDownByIndex(discountTypeInputList.get(i), 0);;
				break;
			}
		}
	}

	public void discountInAmount(String fullname) {
		loginPage.waitForPageLoad();
		for (int i = 0; treatmentsInputList.size() > i; i++) {
			if (treatmentsInputList.get(i).getText().contains(fullname)) {
				loginPage.selectFromDropDownByIndex(discountTypeInputList.get(i), 1);;
				break;
			}
		}
	}

	public void verifyMsgWithoutCoupon() {
		loginPage.waitForElementVisibility(withoutCouponMsg, 2000);
		Assert.assertTrue(checkedWebElementDisplayed(withoutCouponMsg));
		loginPage.waitForElementToDisappear((By.xpath("//span[contains(text(),'Please Select Coupon(s) Basis')]")));
	}

	public void clickOnByArch() {
		loginPage.waitForPageLoad();
		byArchBtn.click();
	}

	public void clickOnByQuadrant() {
		loginPage.waitForPageLoad();
		ByQuadrantBtn.click();
	}

	private boolean checkedWebElementDisplayed(WebElement webElement) {
		return (webElement.isDisplayed());
	}

}
