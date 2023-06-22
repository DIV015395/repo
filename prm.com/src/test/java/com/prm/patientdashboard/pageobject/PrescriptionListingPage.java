package com.prm.patientdashboard.pageobject;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.prm.pageobjects.utils.PCDriver;

public class PrescriptionListingPage {
	private PCDriver login;

	public PrescriptionListingPage(PCDriver login) {
		this.login = login;
		PageFactory.initElements(login.getDriver(), this);
	}

	/* -----webElement in Main List-----------*/
	@FindBy(xpath = "//*[text()='No Record Found!']")
	private WebElement noRecordFoundMsg;
	@FindBy(xpath = "//i[text()='Add New']")
	private WebElement addNewBtn;
	@FindBy(xpath = "//div[@class='dtNClncs orlExmDateNClnc']/following-sibling::div//th[text()='Brand Name']")
	private WebElement brandNameMainList;
	@FindBy(xpath = "//div[@class='dtNClncs orlExmDateNClnc']/following-sibling::div//th[text()='Generic Name']")
	private WebElement genericNameMainList;
	@FindBy(xpath = "//div[@class='dtNClncs orlExmDateNClnc']/following-sibling::div//th[text()='Strength']")
	private WebElement strengthNameMainList;
	@FindBy(xpath = "//div[@class='dtNClncs orlExmDateNClnc']/following-sibling::div//th[text()='Duration']")
	private WebElement durationNameMainList;
	@FindBy(xpath = "//div[@class='dtNClncs orlExmDateNClnc']/following-sibling::div//th[text()='Route']")
	private WebElement routeNameMainList;
	@FindBy(xpath = "//div[@class='dtNClncs orlExmDateNClnc']/following-sibling::div//th[text()='Dosage']")
	private WebElement dosageNameMainList;
	@FindBy(xpath = "//div[@class='dtNClncs orlExmDateNClnc']/following-sibling::div//th[text()='Instruction']")
	private WebElement instructionNameMainList;
	@FindBy(xpath = "//div[@class='dtNClncs orlExmDateNClnc']/following-sibling::div//th[text()='Prescribe By']")
	private WebElement prescribeByMainList;

	/*---WebElement in View Pop up----*/
	@FindBy(xpath = "//h4[contains(@class,'modal-title')]")
	private WebElement headInView;
	@FindBy(xpath = "(//a[@class='btn ng-scope']/span[text()='View'])[1]")
	private WebElement viewBtn;
	@FindBy(xpath = "(//a[@class='btn ng-scope']/span[text()='Edit'])[1]")
	private WebElement editBtn;
	@FindBy(xpath = "//h4[contains(text(),'Patient Prescription Detail')]/following-sibling::span[contains(@class,'text-right pnameid')]")
	private WebElement dateInView;
	@FindBy(xpath = "//h4[text()='Patient Prescription Detail']")
	private WebElement headerViewPopUp;
	@FindBy(xpath = "(//div[@class='modalBtns']/following-sibling::div/span)[1]")
	private WebElement userNameInView;
	@FindBy(xpath = "//table[@class='table table-striped']//th[text()='Brand Name']")
	private WebElement brandNameInView;
	@FindBy(xpath = "//table[@class='table table-striped']//th[text()='Generic Name']")
	private WebElement genericNameInView;
	@FindBy(xpath = "//table[@class='table table-striped']//th[text()='Strength']")
	private WebElement strengthInView;
	@FindBy(xpath = "//table[@class='table table-striped']//th[text()='Duration']")
	private WebElement durationInView;
	@FindBy(xpath = "//table[@class='table table-striped']//th[text()='Route']")
	private WebElement routeInView;
	@FindBy(xpath = "//table[@class='table table-striped']//th[text()='Dosage']")
	private WebElement dosageInView;
	@FindBy(xpath = "//table[@class='table table-striped']//th[text()='Instruction']")
	private WebElement instructionInView;
	@FindBy(xpath = "//table[@class='table table-striped']//th[text()='Prescribe By']")
	private WebElement prescribeByInView;
	@FindBy(xpath = "//strong[contains(text(),'Notes')]")
	private WebElement notesInView;
	@FindBy(className = "close")
	private WebElement closeViewButton;
	
	/*---WebElement in Send Pop up----*/
	@FindBy(xpath = "//b[text()='Patient Name']")
	private WebElement patientNameInSend;
	@FindBy(xpath = "//b[text()='Patient Mobile']")
	private WebElement patientMobileInSend;
	@FindBy(xpath = "//b[text()='Patient Email']")
	private WebElement patientEmailInSend;
	@FindBy(id = "email")
	private WebElement patientEmailTxtBxInSend;
	@FindBy(xpath = "//button[contains(@ng-click,'save')]")
	private WebElement saveVitalsButton;
	@FindBy(xpath = "//button[contains(@ng-click,'cancel')]")
	private WebElement closeVitalsButton;
	@FindBy(xpath = "//span[text()='Mail has been sent to the patient !']")
	private WebElement mailSendMsg;
	
	/*-------------Functions------------*/

	/*Verifying Prescription Listing Page-----*/
	public void verifyPrescriptionListHomePage() {
		try {
			Thread.sleep(3000);
			login.waitForPageLoad();
			Assert.assertTrue(login.getTitle().contains("Prescription Listing"));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*-- Checking All head shown in Prescription main listing page-----*/
	public void checkedMainListDataName() {
		login.waitForPageLoad();
		Assert.assertTrue(checkedWebElementDisplayed(brandNameMainList)
				&& checkedWebElementDisplayed(genericNameMainList) && checkedWebElementDisplayed(strengthNameMainList)
				&& checkedWebElementDisplayed(durationNameMainList) && checkedWebElementDisplayed(dosageNameMainList)
				&& checkedWebElementDisplayed(routeNameMainList) && checkedWebElementDisplayed(instructionNameMainList)
				&& checkedWebElementDisplayed(prescribeByMainList));
	}

	/*Verifying Add New button in Prescription Listing Page-----*/
	public void verifyAddNewBtn() {
		login.waitForPageLoad();
		Assert.assertTrue(checkedWebElementDisplayed(addNewBtn));
	}

	/*Verifying Generic Name in main list*/
	public void genericNameInMainList(String brandName,String genericName) {
		login.waitForPageLoad();
		String str = login.getDriver().findElement(By.xpath("//td[text()='"+brandName+"']/../td[text()='" + genericName.toUpperCase()+ "']")).getText();
		Assert.assertTrue(str.equalsIgnoreCase(genericName));
	}

	/*Verifying BrandName,Route and Notes in main list*/
	public void prescriptionInMainList(String brandName,String prescription) {
		login.waitForPageLoad();
		String str = login.getDriver().findElement(By.xpath("//td[text()='"+brandName+"']/../td[text()='" + prescription + "']")).getText();
		Assert.assertEquals(str, prescription);
	}

	/*Verifying Strength in Main list*/
	public void strengthMainList(String brandName,String strength, String drug) {
		login.waitForPageLoad();
		String str = strength + ".00 " + drug;
		Assert.assertTrue(checkedWebElementDisplayed(
				login.getDriver().findElement(By.xpath("//td[text()='"+brandName+"']/../td[contains(text(),'" + str + "')]"))));
	}

	/*Verifying Duration in Main list*/
	public void durationMainList(String brandName,String duration, String dur) {
		login.waitForPageLoad();
		String str = duration + " " + dur;
		Assert.assertTrue(checkedWebElementDisplayed(
				login.getDriver().findElement(By.xpath("//td[text()='"+brandName+"']/../td[contains(text(),'" + str + "')]"))));
	}

	/*Verifying No record Message in Main list*/
	public void verifyNoRecordFoundMessage() {
		login.waitForPageLoad();
		login.WaitTillElementIsPresent(noRecordFoundMsg);
		try {
			Thread.sleep(3000);
			Assert.assertTrue(checkedWebElementDisplayed(noRecordFoundMsg));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*Verifying Clinic Name in Prescription listing*/
	public void verifyClinicInPrescriptionListing(String brandName,String clinic) {
		login.waitForPageLoad();
		WebElement web = login.getDriver().findElement(By.xpath("//td[text()='"+brandName+"']/../ancestor::div[@class='clearfix table-responsive']/preceding-sibling::div/div[@class='fnt_avgR dateClnc']"));
		String str = web.getText();
		Assert.assertTrue(str.contains(clinic));
	}

	/*Click on View button Shown in Main list*/
	public void clickViewMainList(String brandName) {
		login.waitForPageLoad();
		WebElement webelement = login.getDriver().findElement(By.xpath("//td[text()='"+brandName+"']/../ancestor::div[@class='clearfix table-responsive']/preceding-sibling::div//span[text()='View']"));
		Assert.assertTrue(checkedWebElementDisplayed(webelement));
		login.waitForElementToBeClickable(webelement);
		webelement.click();
	}

	/*Verifying Edit button shown in Main list*/
	public void verifyEditBtnMainList(String brandName) {
		login.waitForPageLoad();
		WebElement webelement = login.getDriver().findElement(By.xpath("//td[text()='"+brandName+"']/../ancestor::div[@class='clearfix table-responsive']/preceding-sibling::div//span[text()='Edit']"));
		Assert.assertTrue(checkedWebElementDisplayed(webelement));
	}

	/*Verifying Print button shown in Main list*/
	public void verifyPrintBtnMainList(String brandName) {
		login.waitForPageLoad();
		WebElement webelement = login.getDriver().findElement(By.xpath("//td[text()='"+brandName+"']/../ancestor::div[@class='clearfix table-responsive']/preceding-sibling::div//span[text()='Print']"));
		Assert.assertTrue(checkedWebElementDisplayed(webelement));
	}
	
	/*Click on Send button shown in Main list*/
	public void clickSendBtnMainList(String brandName) {
		login.waitForPageLoad();
		login.getDriver().findElement(By.xpath("//td[text()='"+brandName+"']/../ancestor::div[@class='clearfix table-responsive']/preceding-sibling::div//span[text()='Send']")).click();
	}
	
	/*Verifying Send button shown in Main list*/
	public void verifySendBtnMainList(String brandName) {
		login.waitForPageLoad();
		WebElement webelement = login.getDriver().findElement(By.xpath("//td[text()='"+brandName+"']/../ancestor::div[@class='clearfix table-responsive']/preceding-sibling::div//span[text()='Send']"));
		Assert.assertTrue(checkedWebElementDisplayed(webelement));
	}
	
	/*-- Checking All head shown in View Pop-----*/
	public void dataNameInSend() {
		login.waitForPageLoad();
		try {
			Thread.sleep(2000);
			Assert.assertTrue(checkedWebElementDisplayed(patientNameInSend)&&checkedWebElementDisplayed(patientMobileInSend)
					&& checkedWebElementDisplayed(patientEmailInSend));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/*-- Verify Patient Name in Patient Vitals-----*/
	public void verifyPatientNameInSend(String patientName) {
		login.waitForPageLoad();
		String str = login.getDriver().findElement(By.xpath("//b[text()='Patient Name']/../following-sibling::div")).getText();
		Assert.assertEquals(str, patientName);
	}
	
	/*-- Verify Patient Mobile in Patient Vitals-----*/
	public void verifyPatientMobileInSend(String patientMobile) {
		login.waitForPageLoad();
		String str = login.getDriver().findElement(By.xpath("//b[text()='Patient Mobile']/../following-sibling::div")).getText();
		Assert.assertEquals(str, patientMobile);
	}
	
	/*-- Add Email Id in Patient Vitals Pop up-----*/
	public void addEmailInSend(String email) {
		login.waitForPageLoad();
		patientEmailTxtBxInSend.clear();
		patientEmailTxtBxInSend.sendKeys(email);
	}
	
	/*-- Click on save button coming in Patient Vitals Pop up-----*/
	public void clickSaveBtnInSend() {
		login.waitForPageLoad();
		saveVitalsButton.click();
	}

	/*-- Verify success msg coming after click on send button-----*/
	public void successfullyMessage(){
		login.waitForPageLoad();
        try {
            Thread.sleep(2000);
            login.softAssert().assertTrue(checkedWebElementDisplayed(mailSendMsg));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

	/*-- Checking the Header in Prescription view Screen--*/
	public void verifyHeaderInView(String header) {
		login.waitForPageLoad();
		login.waitForElementToBeClickable(headInView);
		try {
			Thread.sleep(3000);
			Assert.assertTrue(headInView.getText().contains(header));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*-- Checking All head shown in View Pop-----*/
	public void dataNameInView() {
		login.waitForPageLoad();
		try {
			Thread.sleep(2000);
			Assert.assertTrue(checkedWebElementDisplayed(headerViewPopUp)&&checkedWebElementDisplayed(brandNameInView)
					&& checkedWebElementDisplayed(genericNameInView) && checkedWebElementDisplayed(strengthInView)
					&& checkedWebElementDisplayed(durationInView) && checkedWebElementDisplayed(routeInView)
					&& checkedWebElementDisplayed(dosageInView) && checkedWebElementDisplayed(instructionInView)
					&& checkedWebElementDisplayed(prescribeByInView) && checkedWebElementDisplayed(notesInView));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*-- Verify Patient Name in View Pop-----*/
	public void verifyPatientNameInView(String patientName) {
		login.waitForPageLoad();
		String str = login.getDriver().findElement(By.xpath("//span[@class='txt_medgry ng-binding']")).getText();
		String[] split = str.split(":");
		String actual = split[0].trim();
		Assert.assertEquals(actual, patientName);
	}

	/*-- Verifying the date in view Pop up-----*/
	public void verifyDateInViewPopUp() {
		login.waitForPageLoad();
		try {
			Thread.sleep(4000);
			String string = dateInView.getText().trim();
			String[] split = string.split(":");
			String actualDate = split[1].trim();
			Date dat = new Date();
			String expectedDate = new SimpleDateFormat("dd-MM-yy").format(dat);
			boolean flag = expectedDate.equals(actualDate);
			Assert.assertTrue(flag);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*-- Verifying GenericName in View-----*/
	public void checkedGenericNameInView(String brandName,String genericName) {
		login.waitForPageLoad();
		String str = login.getDriver().findElement(By.xpath("//tbody[@class='ng-scope']//td[text()='" + genericName.toUpperCase() +"']")).getText();
			Assert.assertTrue(str.equalsIgnoreCase(genericName));
	}

	/* Verifying Brand Name,Route and Instruction in View */
	public void checkedPrescriptionInView(String brandName,String prescription) {
		login.waitForPageLoad();
		String str = login.getDriver().findElement(By.xpath("//tbody[@class='ng-scope']//td[text()='" + prescription + "']")).getText();
		Assert.assertEquals(str, prescription);
	}

	/*-- Verifying Strength in View-----*/
	public void strengthInView(String brandName,String strength, String drug) {
		login.waitForPageLoad();
		String str = strength + ".00 " + drug;
		Assert.assertTrue(checkedWebElementDisplayed(
				login.getDriver().findElement(By.xpath("//tbody[@class='ng-scope']//td[text()='"+brandName+"']/../td[contains(text(),'" + str + "')]"))));

	}

	/*-- Verifying Duration in View-----*/
	public void durationInView(String brandName,String duration, String dur) {
		login.waitForPageLoad();
		String str = duration + " " + dur;
		Assert.assertTrue(checkedWebElementDisplayed(
				login.getDriver().findElement(By.xpath("//tbody[@class='ng-scope']//td[text()='"+brandName+"']/../td[contains(text(),'" + str + "')]"))));
	}

	/*-- Verifying Notes in View-----*/
	public void notesInView(String brandname, String note) {
		login.waitForPageLoad();
		WebElement webelement = login.getDriver().findElement(By.xpath("//td[contains(text(),'" + brandname
				+ "')]/../following-sibling::tr//div[@class='textDataVisualizerFull ng-binding']"));
		Assert.assertTrue(webelement.getText().trim().contains(note));
	}

	/*-- Close the view pop up-----*/
	public void closeViewPopup() {
		login.waitForPageLoad();
		login.waitForElementToBeClickable(closeViewButton);
		closeViewButton.click();
	}

	/*---Verifying deleted Prescription in main list------*/
	public void verifyPrescriptionDeletedMainList(String Prescription) {
		login.waitForPageLoad();
		try {
			Thread.sleep(2000);
			WebElement web = login.getDriver().findElement(By.xpath("//td[contains(text(),'" + Prescription + "')]/.."));
			Assert.assertTrue(web.getAttribute("class").contains("ng-scope deleted"));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*---Verifying deleted Prescription in View modal------*/
	public void verifyPrescriptionDeletedInViewMainList(String Prescription) {
		login.waitForPageLoad();
		try {
			Thread.sleep(2000);
			WebElement web = login.getDriver().findElement(By.xpath("//div[@id='prescriptionListbody']//td[text()='" + Prescription + "']/.."));
			Assert.assertTrue(web.getAttribute("class").contains("deleted"));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*Click on Delete button on Pop up---*/
	public void clickDeletePop() {
		login.waitForPageLoad();
		login.getDriver().findElement(By.xpath("//span[@class='cmnicons yes-mdl']")).click();
	}

	/*checking view and edit button after deleting prescription---*/
	public void checkBtnAfterDelete() {
		login.waitForPageLoad();
		Assert.assertTrue(checkedWebElementDisplayed(viewBtn) && checkedWebElementDisplayed(editBtn));
	}

	private boolean checkedWebElementDisplayed(WebElement webelement) {
		return (webelement.isDisplayed());
	}
}
