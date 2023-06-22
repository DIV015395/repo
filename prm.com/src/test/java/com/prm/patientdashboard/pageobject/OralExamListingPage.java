package com.prm.patientdashboard.pageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.prm.pageobjects.utils.PCDriver;
import com.prm.pageobjects.utils.TestData;

/**
 * 
 * @author Ajit Yadav
 *
 */
public class OralExamListingPage {

	private PCDriver loginPage;

	public OralExamListingPage(PCDriver loginPage) {
		this.loginPage = loginPage;
		PageFactory.initElements(loginPage.getDriver(), this);
	}

	@FindBy(xpath = "//h1[contains(@class,'pull-left')]")
	private WebElement title;
	@FindBy(xpath = "//table[@id='table_oelisting']//th[text()='Tooth No']")
	private WebElement toothNo;
	@FindBy(xpath = "//table[@id='table_oelisting']//th[text()='Tooth Site & Perio']")
	private WebElement toothSitePerio;
	@FindBy(xpath = "//table[@id='table_oelisting']//th[text()='Soft Tissue']")
	private WebElement softTissue;
	@FindBy(xpath = "//table[@id='table_oelisting']//th[text()='Hard Tissue']")
	private WebElement hardTissue;
	@FindBy(xpath = "//table[@id='table_oelisting']//th[text()='Created By']")
	private WebElement createdBy;
	@FindBy(xpath = "//table[@id='table_oelisting']//th[text()='Notes']")
	private WebElement notes;
	@FindBy(xpath = "//table[@class='table table-striped']//th[text()='Tooth No']")
	private WebElement viewToothNo;
	@FindBy(xpath = "//table[@class='table table-striped']//th[text()='Tooth Site/Perio']")
	private WebElement viewToothSitePerio;
	@FindBy(xpath = "//table[@class='table table-striped']//th[text()='Soft Tissue']")
	private WebElement viewSoftTissue;
	@FindBy(xpath = "//table[@class='table table-striped']//th[text()='Hard Tissue']")
	private WebElement viewHardTissue;
	@FindBy(xpath = "//table[@class='table table-striped']//th[text()='Created By']")
	private WebElement viewCreatedBy;
	@FindBy(xpath = "(//table[@class='table table-striped']//strong[contains(text(),'Notes')])[1]")
	private WebElement viewNotes;
	
	private static final String TODAY_DATE =TestData.getInstance().getTodayDate();

	public void checkedDataName() {
		loginPage.waitForPageLoad();
		Assert.assertTrue(checkedElementDisplayed(toothNo) && checkedElementDisplayed(toothSitePerio)
				&& checkedElementDisplayed(softTissue) && checkedElementDisplayed(hardTissue)
				&& checkedElementDisplayed(createdBy) && checkedElementDisplayed(notes));
	}

	public void checkedViewDataName() {
		loginPage.waitForPageLoad();
		Assert.assertTrue(checkedElementDisplayed(viewToothNo) && checkedElementDisplayed(viewToothSitePerio)
				&& checkedElementDisplayed(viewSoftTissue) && checkedElementDisplayed(viewHardTissue)
				&& checkedElementDisplayed(viewCreatedBy) && checkedElementDisplayed(viewNotes));
	}

	public void verifyProvisinalsSingleTeethOralExamList(String teethNo, int NoProvisinals) {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(title);
		List<WebElement> webElement1 = loginPage.getDriver().findElements(By.xpath("//span[contains(text(),'"+TODAY_DATE+"')]/../../..//span[contains(text(),'"+teethNo+"')]/../../..//td//span[@class='ng-binding ng-scope']"));
		int size = webElement1.size();
		System.out.println("The Size Is "+size);
		loginPage.visibilityOfListLocated(webElement1);
		Assert.assertTrue(size == NoProvisinals);
	}

	public void verifyProvisinalsMultipleTeethOralExamList(String teethNo1, String teethNo2, int NoProvisinals) {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(title);
		List<WebElement> webelement2 = loginPage.getDriver().findElements(By.xpath("//span[contains(text(),'"+TODAY_DATE+"')]/../../..//span[contains(text(),'" + teethNo1
				+ "," + teethNo2
				+ "')]/../../..//td//span[@class='ng-binding ng-scope']"));
		loginPage.visibilityOfListLocated(webelement2);
		Assert.assertTrue(webelement2.size() == NoProvisinals);
	}

	public void oralExamViewPopup(String teethNo, int noProvisinals) {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(viewCreatedBy);
		List<WebElement> webElements = loginPage.getDriver()
				.findElements(By.xpath("//table[@class='table table-striped']//span[contains(text(),'"+teethNo+"')]/../../..//td//span[@class='ng-binding ng-scope']"));
		Assert.assertTrue(webElements.size() == noProvisinals);
	}

	public void createdByOralExamViewPopup(String teethNo, String createdBy) {
		loginPage.waitForPageLoad();
		Assert.assertTrue(loginPage
				.getDriver().findElement(By.xpath("//table[@class='table table-striped']//span[contains(text(),'"
						+ teethNo + "')]/../../..//td[@class='ng-binding']"))
				.getText().equalsIgnoreCase(createdBy));
	}

	public void noteOralExamViewPopup(String teethNo, String notes) {
		loginPage.waitForPageLoad();
		Assert.assertTrue(loginPage.getDriver()
				.findElement(By.xpath("//table[@class='table table-striped']//span[contains(text(),'" + teethNo
						+ "')]/../../../following-sibling::tr//div[contains(@class,'textDataVisualizerFull')]"))
				.getText().contains(notes));
	}

	public void verifyNoteOralExamList(String teethNo, String notes) {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(title);
		Assert.assertTrue(loginPage.getDriver()
				.findElement(By.xpath("//span[contains(text(),'" + teethNo
						+ "')]/../../following-sibling::td/div[contains(@class,'textDataVisualizer tdvNotes')]"))
				.getText().contains(notes));
	}

	public void verifyViewBtnOnOralExamList() {
		loginPage.waitForPageLoad();
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		WebElement web = loginPage.getDriver().findElement(By.xpath("(//div[@class='fnt_avgR dateClnc'])[1]"));
		String str = web.getText();
		String[] split = str.split(" ");
		String str1 = split[0];
		loginPage.getDriver().findElement(By.xpath(
				"//span[contains(text(),'" + str1 + "')]/../following-sibling::div//span[@class='actn-icn view']"))
				.click();

	}

	public void verifyEditBtnOnOralExamList() {
		loginPage.waitForPageLoad();
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		WebElement web = loginPage.getDriver().findElement(By.xpath("(//div[@class='fnt_avgR dateClnc'])[1]"));
		String str = web.getText();
		String[] split = str.split(" ");
		String str1 = split[0];
		loginPage.getDriver().findElement(By.xpath(
				"//span[contains(text(),'" + str1 + "')]/../following-sibling::div//span[@class='actn-icn edit']"))
				.click();

	}

	public void verifyDeleteBtnOnOralExamList() {
		loginPage.waitForPageLoad();
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		WebElement web = loginPage.getDriver().findElement(By.xpath("(//div[@class='fnt_avgR dateClnc'])[1]"));
		String str = web.getText();
		String[] split = str.split(" ");
		String str1 = split[0];
		loginPage.getDriver().findElement(By.xpath(
				"//span[contains(text(),'" + str1 + "')]/../following-sibling::div//span[@class='actn-icn delete']"))
				.click();

	}

	public void verifyPrintBtnOnOralExamList() {
		loginPage.waitForPageLoad();
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		WebElement web = loginPage.getDriver().findElement(By.xpath("(//div[@class='fnt_avgR dateClnc'])[1]"));
		String str = web.getText();
		String[] split = str.split(" ");
		String str1 = split[0];
		loginPage.getDriver().findElement(By.xpath(
				"//span[contains(text(),'" + str1 + "')]/../following-sibling::div//span[@class='actn-icn print']"))
				.click();
	}

	public void verifyCreatedByInOralExamList(String teethNo, String userName) {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(title);
		Assert.assertTrue(loginPage.getDriver()
				.findElement(By.xpath(
						"//span[contains(text(),'" + teethNo + "')]/../../following-sibling::td[@class='ng-binding']"))
				.getText().equalsIgnoreCase(userName));
	}

	private boolean checkedElementDisplayed(WebElement webelement) {
		return (webelement.isDisplayed());
	}
}
