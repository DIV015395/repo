package com.prm.patientdashboard.pageobject;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import com.prm.pageobjects.utils.PCDriver;
import com.prm.pageobjects.utils.TestData;

public class DiagnosticListingPage {
	private PCDriver loginPage;

	public DiagnosticListingPage(PCDriver loginPage) {
		this.loginPage = loginPage;
		PageFactory.initElements(loginPage.getDriver(), this);
	}
	/*webelements*/
	@FindBy(xpath = "//table[@class='table wordbreak']//th[text()='Category']")
	private WebElement categoryDiagnosticsList;
	@FindBy(xpath = "//table[@class='table wordbreak']//th[text()='Investigation']")
	private WebElement investigation;
	@FindBy(xpath = "//table[@class='table wordbreak']//th[text()='Created By']")
	private WebElement createdByDiagnosticsList;
	@FindBy(xpath = "//table[@class='table wordbreak']//th[text()='Notes']")
	private WebElement notesDiagnosticsList;
	
	/*Web Elements present in View Pop up */
	@FindBy(xpath = "//span[contains(@class,'compHistPadR')]")
	private WebElement userName;
	@FindBy(xpath = "//div[@id='diglistviewmbody']//th[text()='Category']")
	private WebElement categoryViewPop;
	@FindBy(xpath = "//div[@id='diglistviewmbody']//th[text()='Investigation']")
	private WebElement investigationViewPop;
	@FindBy(xpath = "//div[@id='diglistviewmbody']//th[text()='Created By']")
	private WebElement createdByViewPop;
	@FindBy(xpath = "//div[@id='diglistviewmbody']//th[text()='Notes']")
	private WebElement notesViewPop;

	/*checking the column name at diagnosis listing page*/
	public void checkedDataName() {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(categoryDiagnosticsList);
		Assert.assertTrue(checkedElementDisplayed(categoryDiagnosticsList) && checkedElementDisplayed(investigation)
				&& checkedElementDisplayed(createdByDiagnosticsList) && checkedElementDisplayed(notesDiagnosticsList));
	}

	/*checking the column name at diagnosis listing page at view modal*/
	public void checkedDataNameViewPopup() {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(categoryViewPop);
		Assert.assertTrue(checkedElementDisplayed(categoryViewPop) && checkedElementDisplayed(investigationViewPop)
				&& checkedElementDisplayed(createdByViewPop) && checkedElementDisplayed(notesViewPop));
	}

	/*checking the diagnosis at diagnosis listing page*/
	public void checkedDiagnosisMainList(String diagnosis) {
		loginPage.waitForPageLoad();
		Assert.assertTrue(checkedElementDisplayed(loginPage.getDriver().findElement(By.xpath(
				"//table[@class='table wordbreak']//following-sibling::tbody//td[text()='" + diagnosis + "']"))));
	}

	/*checking the investigation name at diagnosis listing page*/
	public void investigationMainList(String diagnosis,String InvestigationName){
		loginPage.waitForPageLoad();
		if(diagnosis.equalsIgnoreCase("IOPAR")){
			Assert.assertTrue(loginPage.getDriver().findElement(By.xpath("//table[@class='table wordbreak']//following-sibling::tbody//td[@class='btmCls ng-binding' and text()='"+diagnosis+"']/following-sibling::td//div[contains(@class,'DiagnoTeethCs ioparInListTeeth')]")).getText().equalsIgnoreCase(InvestigationName));
		}else{
				Assert.assertTrue(loginPage.getDriver().findElement(By.xpath("//table[@class='table wordbreak']//following-sibling::tbody//td[@class = 'btmCls ng-binding' and text()='"+diagnosis+"']/following-sibling::td//div[contains(text(),'"+InvestigationName+"')]")).getText().equalsIgnoreCase(InvestigationName));
		}
	}

	/*checking the created by name at diagnosis listing page*/
	public void createdByMainList(String diagnosis,String createdBy){
		loginPage.waitForPageLoad();
		if(diagnosis.equalsIgnoreCase("IOPAR")){
			Assert.assertTrue(loginPage.getDriver().findElement(By.xpath("//table[@class='table wordbreak']//following-sibling::tbody//td[text()='"+diagnosis+"']/following-sibling::td//div[contains(@class,'ioparInListcrtdBy')]")).getText().equalsIgnoreCase(createdBy));
		}else{
			Assert.assertTrue(loginPage.getDriver().findElement(By.xpath("//table[@class='table wordbreak']//following-sibling::tbody//td[text()='"+diagnosis+"']/following-sibling::td//div[contains(@class,'created')]")).getText().equalsIgnoreCase(createdBy));
		}
	}

	/*checking the notes at the diagnosis listing page*/
	public void notesByMainList(String diagnosis,String notes) {
		loginPage.waitForPageLoad();
		if (diagnosis.equalsIgnoreCase("IOPAR")) {
			Assert.assertTrue(loginPage.getDriver().findElement(By.xpath("//table[@class='table wordbreak']//following-sibling::tbody//td[text()='" + diagnosis + "']/following-sibling::td//div[contains(@class,'ioparInListNotes')]")).getText().equalsIgnoreCase(notes));
		} else {
			Assert.assertTrue(loginPage.getDriver().findElement(By.xpath("//table[@class='table wordbreak']//following-sibling::tbody//td[text()='" + diagnosis + "']/following-sibling::td//div[contains(@class,'notes-xs-area DiagnoTeethCs')]")).getText().equalsIgnoreCase(notes));
		}
	}

	/*checking the patient name and patient id is not null in Diagnostic View modal*/
	public void verifyPatientNameInView(String patientName) {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(userName);
		String str = userName.getText();
		System.out.println("PatientName "+str);
		String[] split = str.split(" : ");
		boolean flag = split[0].contains(patientName) && !(split[1].trim().equals("NA") || split[1].trim().equals("Null"));
		Assert.assertTrue(flag);
	}
	
	/*checking Diagnostic Add date on View modal*/
	public void verifyDateInView() {
		    loginPage.waitForSpinnerToDisappear();
			WebElement web = loginPage.getDriver().findElement(By.xpath("(//span[contains(@class,'compViewPopUpResponsive ')])[2]"));
			String fullDate =  web.getText().split(":")[1];
			String actualDate = fullDate.trim();
			String expectedDate = TestData.getInstance().getTodayDate().trim();
			Assert.assertTrue(expectedDate.equals(actualDate));
	}
		
	/*checking the diagnosis name at diagnosis listing page at view modal*/
	public void checkDiagnosisViewPopup(String diagnosis) {
		loginPage.waitForPageLoad();
		Assert.assertTrue(checkedElementDisplayed(loginPage.getDriver()
				.findElement(By.xpath("//div[@id='diglistviewmbody']//td[text()='" + diagnosis + "']"))));
	}

	/*checking the investigation at diagnosis listing page at view modal*/
	public void investigationView(String diagnosis,String InvestigationName){
		loginPage.waitForPageLoad();
		if(diagnosis.equalsIgnoreCase("IOPAR")){
			Assert.assertTrue(loginPage.getDriver().findElement(By.xpath("//div[@id='diglistviewmbody']//td[@class='ng-binding' and text()='"+diagnosis+"']/following-sibling::td//div[contains(@class,'DiagnoTeethCs ioparInListTeeth')]")).getText().equalsIgnoreCase(InvestigationName));
		}else{	
				Assert.assertTrue(loginPage.getDriver().findElement(By.xpath("//div[@id='diglistviewmbody']//td[@class='ng-binding' and text()='"+diagnosis+"']/following-sibling::td//div[contains(text(),'"+InvestigationName+"')]")).getText().equalsIgnoreCase(InvestigationName));
			}
//			Assert.assertTrue(loginPage.getDriver().findElement(By.xpath("//div[@id='diglistviewmbody']//td[text()='"+diagnosis+"']/following-sibling::td//div[contains(@class,'investigation')]")).getText().equalsIgnoreCase(InvestigationName));
		
	}

	/*checking the created by name at diagnosis listing page at view modal*/
	public void createdByView(String diagnosis,String createdBy){
		loginPage.waitForPageLoad();
		if(diagnosis.equalsIgnoreCase("IOPAR")){
			Assert.assertTrue(loginPage.getDriver().findElement(By.xpath("//div[@id='diglistviewmbody']//td[text()='"+diagnosis+"']/following-sibling::td//div[contains(@class,'ioparInListcrtdBy')]")).getText().equalsIgnoreCase(createdBy));
		}else{
			Assert.assertTrue(loginPage.getDriver().findElement(By.xpath("//div[@id='diglistviewmbody']//td[text()='"+diagnosis+"']/following-sibling::td//div[contains(@class,'created-wrp-list')]")).getText().equalsIgnoreCase(createdBy));
		}
	}

	/*checking the notes at diagnosis listing page at view modal*/
	public void notesView(String diagnosis,String notes){
		loginPage.waitForPageLoad();
		if(diagnosis.equalsIgnoreCase("IOPAR")){
			Assert.assertTrue(loginPage.getDriver().findElement(By.xpath("//div[@id='diglistviewmbody']//td[text()='"+diagnosis+"']/following-sibling::td//div[contains(@class,'ioparInListNotes')]")).getText().equalsIgnoreCase(notes));
		}else{
			Assert.assertTrue(loginPage.getDriver().findElement(By.xpath("//div[@id='diglistviewmbody']//td[text()='"+diagnosis+"']/following-sibling::td//div[contains(@class,'notes-xs-area DiagnoTeethCs')]")).getText().equalsIgnoreCase(notes));
		}
	}

	/*checking webelement present at the webpage*/
	private boolean checkedElementDisplayed(WebElement webElement) {
		return (webElement.isDisplayed());
	}
}
