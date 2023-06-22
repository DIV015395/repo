package com.prm.patientdashboard.pageobject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.prm.pageobjects.utils.PCDriver;

/**
 * 
 * @author Ajit Yadav
 *
 */
public class ChiefComplaintListingPage {
	private PCDriver loginPage;

	private static final String FIRST = "Maxillary Right Posterior";
	private static final String SECOND = "Maxillary Right Anterior";
	private static final String THIRD = "Maxillary Left Anterior";
	private static final String FOURTH = "Maxillary Left Posterior";
	private static final String FIFTH = "Mandibular Right Posterior";
	private static final String SIXTH = "Mandibular Right Anterior";
	private static final String SEVENTH = "Mandibular Left Posterior";
	private static final String EIGHT = "Mandibular Left Anterior";

	public ChiefComplaintListingPage(PCDriver loginPage) {
		this.loginPage = loginPage;
		PageFactory.initElements(loginPage.getDriver(), this);
	}

	/*WebElement of the chief complaint listing*/

	@FindBy(xpath = "(//div[@class='fnt_avgR dateClnc']/span)[1]")
	private WebElement dateOnCCList;
	@FindBy(xpath = "(//i[text()='No Record Found!'])[1]")
	private WebElement noRecordFoundMsg;
	@FindBy(xpath = "//div[@class='dtNClncs orlExmDateNClnc']/following-sibling::div//th[text()='Chief Complaint']")
	private WebElement chiefComplaintMainList;
	@FindBy(xpath = "//div[@class='dtNClncs orlExmDateNClnc']/following-sibling::div//th[text()='Location']")
	private WebElement locationMainList;
	@FindBy(xpath = "//div[@class='dtNClncs orlExmDateNClnc']/following-sibling::div//th[text()='Intensity']")
	private WebElement intensityMainList;
	@FindBy(xpath = "//div[@class='dtNClncs orlExmDateNClnc']/following-sibling::div//th[text()='Period']")
	private WebElement periodMainList;
	@FindBy(xpath = "//div[@class='dtNClncs orlExmDateNClnc']/following-sibling::div//th[text()='Notes']")
	private WebElement notesMainList;
	@FindBy(xpath = "//div[@class='dtNClncs orlExmDateNClnc']/following-sibling::div//th[text()='Created By']")
	private WebElement createdByMainList;
	@FindBy(xpath = "//span[@class='clnsnametxt ng-binding']")
	private WebElement chiefComplaintsDates;

	/*webElement of view popup*/
	@FindBy(xpath = "//h4[contains(text(),'Chief Complaint Details')]")
	private WebElement headerViewPopup;
	@FindBy(xpath = "//strong[@class='ng-binding']")
	private WebElement userNameViewCWebElement;
	@FindBy(xpath = "//h4[contains(text(),'Chief Complaint Details')]/following-sibling::span[contains(@class,'compViewPopUpResponsive ng-binding')]")
	private WebElement dateInView;
	@FindBy(xpath = "//div[@class='modal-body viewInfo']//th[text()='Chief Complaint']")
	private WebElement chiefComplaintsInView;
	@FindBy(xpath = "//div[@class='modal-body viewInfo']//th[text()='Location']")
	private WebElement locationInView;
	@FindBy(xpath = "//div[@class='modal-body viewInfo']//th[text()='Intensity']")
	private WebElement intensityInView;
	@FindBy(xpath = "//div[@class='modal-body viewInfo']//th[text()='Period']")
	private WebElement periodsInView;
	@FindBy(xpath = "//tbody[@data-ng-repeat='input in viewList']/..//th[text()='Created By']")
	private WebElement createdByInView;
	@FindBy(xpath = "//h4[contains(text(),'Chief Complaint Details')]/..//button[@class='close']")
	private WebElement closeViewbutton;

	/*checked column name at chief complaint listing*/
	public void checkedMainlistDataName() {
//		loginPage.waitForPageLoad();
		loginPage.waitForSpinnerToDisappear();
		loginPage.waitForElementVisibility(chiefComplaintMainList, 4000);
		Assert.assertTrue(checkedElementDisplayed(chiefComplaintMainList) && checkedElementDisplayed(locationMainList)
				&& checkedElementDisplayed(intensityMainList) && checkedElementDisplayed(periodMainList)
				&& checkedElementDisplayed(notesMainList) && checkedElementDisplayed(createdByMainList));
	}

	/*checking added chief complaints at the chief complaints listing*/
	
	public void chiefComplaintInMainList(String chiefComplaints) {
		loginPage.waitForModalOverlayToDisappear();
		loginPage.waitForSpinnerToDisappear();
		loginPage.waitForPageToBecomeActive();
		loginPage.waitForElementVisibility(chiefComplaintsDates,5000);
		WebElement element = loginPage.getDriver().findElement(
				By.xpath("//th[text()='Chief Complaint']/../../following-sibling::tbody//td[@class='ng-binding']/..//td[contains(text(),'"
						+ chiefComplaints + "')]"));
		loginPage.waitForElementVisibility(element, 4000);
		loginPage.waitForElementToBeClickable(element);
		Assert.assertTrue(checkedElementDisplayed(element));
	}


//	public void chiefComplaintInMainList(String chiefComplaints) {
//		try {
//			Thread.sleep(2000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		loginPage.waitForSpinnerToDisappear();
//		loginPage.waitForPageToBecomeActive();
//		WebElement element = loginPage.getDriver().findElement(
//				By.xpath("//th[text()='Chief Complaint']/../../following-sibling::tbody//td[@class='ng-binding']/..//td[contains(text(),'"
//						+ chiefComplaints + "')]"));
//		loginPage.waitForElementVisibility(element, 4000);
//		loginPage.waitForElementToBeClickable(element);
//		Assert.assertTrue(checkedElementDisplayed(element));
//	}

	/*checking deleted chief complaint at chief complaint listing*/
	
	public void deletedChiefComplaintInMainList(String chiefComplaints) {
		loginPage.waitForModalOverlayToDisappear();
		loginPage.waitForSpinnerToDisappear();
		loginPage.waitForPageToBecomeActive();
		loginPage.waitForElementVisibility(chiefComplaintsDates,5000);
		WebElement element = loginPage.getDriver().findElement(
				By.xpath("//th[text()='Chief Complaint']/../../following-sibling::tbody//td[contains(text(),'"
						+ chiefComplaints + "')]"));
		loginPage.waitForElementToBeClickable(element);
		Assert.assertTrue(element.getAttribute("class").contains("deleted"));
	}

	/*checking intentsity at the chief complaint listing*/
	public void intensityMainList(String chiefComplaints, String intentsity) {
		WebElement weblement;
//		loginPage.waitForPageLoad();
		loginPage.waitForSpinnerToDisappear();
		if (intentsity.equals("NA")) {
			weblement = loginPage.getDriver().findElement(By
					.xpath("//div[contains(@class,'whtBox brdr_drk_gry tabularListing mrgn-btm')]//td[contains(text(),'"
							+ chiefComplaints
							+ "')]/following-sibling::td/span[contains(@data-ng-show,'cc.CCdetails.ChiefcomplaintIntensity')]"));
		} else {
			weblement = loginPage.getDriver().findElement(By
					.xpath("//div[contains(@class,'whtBox brdr_drk_gry tabularListing mrgn-btm')]//td[contains(text(),'"
							+ chiefComplaints
							+ "')]/following-sibling::td/span[contains(@data-ng-repeat,'cc.CCdetails.ChiefcomplaintIntensity')]"));
		}
		Assert.assertTrue(weblement.getText().contains(intentsity));
	}

	/*checking  the location at chief complaint listing*/
	public void locationMainList(String chiefComplaints, String location) {
		boolean flag = false;
		loginPage.waitForPageLoad();
		if (location.equals("NA")) {
			WebElement weblement = loginPage.getDriver().findElement(By
					.xpath("//div[contains(@class,'whtBox brdr_drk_gry tabularListing mrgn-btm')]//td[contains(text(),'"
							+ chiefComplaints
							+ "')]/following-sibling::td/span[contains(@data-ng-hide,'cc.CCdetails.Chiefcomplaintlocation.length')]"));
			Assert.assertTrue(weblement.getText().contains(location));
		} else {
			List<WebElement> elements = loginPage.getDriver().findElements(By
					.xpath("//div[contains(@class,'whtBox brdr_drk_gry tabularListing mrgn-btm')]//td[contains(text(),'"
							+ chiefComplaints
							+ "')]/following-sibling::td//span[contains(@data-ng-class,'ccLoc.ccl_deletestatus')]"));
			for (int i = 0; elements.size() > i; i++) {
				String string = elements.get(i).getText();
				flag = string.contains(FIRST) || string.contains(SECOND) || string.contains(THIRD)
						|| string.contains(FOURTH) || string.contains(FIFTH) || string.contains(SIXTH)
						|| string.contains(SEVENTH) || string.contains(EIGHT);
				Assert.assertTrue(flag);
			}
		}
	}

	/*checking periods at chief complaint listing*/
	public void periodsInMainList(String chiefComplaint, int expectedMonth, int expectedDay, String period) {
		boolean flag = false;
//		loginPage.waitForPageLoad();
		loginPage.waitForSpinnerToDisappear();
		loginPage.getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		if (period.equals("NA")) {
			WebElement weblement = loginPage.getDriver().findElement(By
					.xpath("//div[contains(@class,'whtBox brdr_drk_gry tabularListing mrgn-btm')]//td[contains(text(),'"
							+ chiefComplaint
							+ "')]/following-sibling::td//span[contains(@data-ng-show,'cc.CCdetails.ChiefcomplaintPeriod.length < 1')]"));
			flag = weblement.getText().contains(period);
		} else {
			List<WebElement> webelement = loginPage.getDriver().findElements(By
					.xpath("//div[contains(@class,'whtBox brdr_drk_gry tabularListing mrgn-btm')]//td[contains(text(),'"
							+ chiefComplaint
							+ "')]/following-sibling::td//span[contains(@data-ng-class,'period.ccp_deletestatus')]"));
			for (int i = 0; webelement.size() > i; i++) {
				if (!webelement.get(i).getAttribute("class").contains("deleted")) {
					String actualString = webelement.get(i).getText();
					String expectedString = expectedMonth + " month(s) " + expectedDay + " day(s)";
					flag = actualString.equals(expectedString);
					break;
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/*checking the chief complaint created by which doctor*/
	public void chiefComplaintcreatedBy(String chiefComplaint,String username){
		loginPage.waitForPageLoad();
		WebElement ele = loginPage.getDriver().findElement(By.xpath("//div[contains(@class,'whtBox brdr_drk_gry tabularListing mrgn-btm')]//td[contains(text(),'"+chiefComplaint+"')]/following-sibling::td[contains(text(),'"+username+"')]"));
		Assert.assertTrue(checkedElementDisplayed(ele));
	}

	/*checking the notes at the chief complaint listing*/
	public void notesMainList(String chiefComplaints, String note) {
		loginPage.waitForPageLoad();
		WebElement weblement = loginPage.getDriver().findElement(By.xpath("//td[contains(text(),'" + chiefComplaints
				+ "')]/following-sibling::td/div[contains(@class,'textDataVisualizer tdvNotes')]"));
		Assert.assertTrue(weblement.getText().contains(note));
	}

    /*checking the no record found message displayed*/
	public void verifyNoRecordFoundMessage() {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(noRecordFoundMsg);
		Assert.assertTrue(checkedElementDisplayed(noRecordFoundMsg));
	}

	/*checking after added the chief complaint in multiple clinic*/
	public void verifyChiefComplaintByClinicWise(String clinic) {
		loginPage.waitForPageLoad();
		List<WebElement> web = loginPage.getDriver()
				.findElements(By.xpath("(//div[@class='fnt_avgR dateClnc']/span[@class='ng-binding'])[2]"));
		for (int i = 0; web.size() > i; i++) {
			boolean flag;
			if (flag = web.get(i).getText().contains(clinic)) {
				Assert.assertTrue(flag);
			} else {
				Assert.assertTrue(!web.get(i).getText().contains("NA") || web.get(i).getText().contains("null"));
				continue;
			}
		}
	}
    /*checking the date at chief complaint listing*/
	public void verifyDateInView() {
	        loginPage.waitForSpinnerToDisappear();
			String string = dateInView.getText().trim();
			String[] split = string.split(" ");
			String actualDate = split[1];
			Date dat = new Date();
			String expectedDate = new SimpleDateFormat("dd-MM-yy").format(dat);
			boolean flag = expectedDate.equals(actualDate);
			Assert.assertTrue(flag);
	}
	
	
	
//	public void verifyDateInView() {
//		loginPage.waitForPageLoad();
//		try {
//			Thread.sleep(4000);
//			String string = dateInView.getText().trim();
//			String[] split = string.split(" ");
//			String actualDate = split[1];
//			Date dat = new Date();
//			String expectedDate = new SimpleDateFormat("dd-MM-yy").format(dat);
//			boolean flag = expectedDate.equals(actualDate);
//			Assert.assertTrue(flag);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

	/*checking the column name at view popup*/
	public void dataNameInView() {
		loginPage.waitForPageLoad();
		Assert.assertTrue(checkedElementDisplayed(chiefComplaintsInView) && checkedElementDisplayed(locationInView)
				&& checkedElementDisplayed(intensityInView) && checkedElementDisplayed(periodsInView)
				&& checkedElementDisplayed(createdByInView));
	}

	/*checking the chief complaint at the view popup*/
	public void checkedChiefComplaintInView(String chiefComplaint) {
		loginPage.waitForPageLoad();
		WebElement webelement = loginPage.getDriver().findElement(
				By.xpath("//tbody[@data-ng-repeat='input in viewList']//td[text()='" + chiefComplaint + "']"));
		Assert.assertTrue(checkedElementDisplayed(webelement));
	}

	/*checked deleted chief complaint at view popup*/
	public void deletedChiefComplaintInView(String chiefComplaint) {
		loginPage.waitForPageLoad();
		WebElement webelement = loginPage.getDriver().findElement(
				By.xpath("//tbody[@data-ng-repeat='input in viewList']//td[text()='" + chiefComplaint + "']"));
		Assert.assertTrue(webelement.getAttribute("class").contains("deleted"));
	}

	/*checked location in view popup*/
	public void locationInView(String chiefComplaint, String location) {
		loginPage.waitForPageLoad();
		boolean flag;
		if (location.equals("NA")) {
			WebElement weblement = loginPage.getDriver()
					.findElement(By.xpath("//tbody[@data-ng-repeat='input in viewList']//td[text()='" + chiefComplaint
							+ "']/following-sibling::td//span[contains(@data-ng-hide,'input.CCdetails.Chiefcomplaintlocation.length')]"));
			Assert.assertTrue(weblement.getText().contains(location));
		} else {
			List<WebElement> elements = loginPage.getDriver()
					.findElements(By.xpath("//tbody[@data-ng-repeat='input in viewList']//td[text()='" + chiefComplaint
							+ "']/following-sibling::td//span[contains(@data-ng-class,'ccLoc.ccl_deletestatus')]"));
			for (int i = 0; elements.size() > i; i++) {
				String string = elements.get(i).getText();
				flag = string.contains(FIRST) || string.contains(SECOND) || string.contains(THIRD)
						|| string.contains(FOURTH) || string.contains(FIFTH) || string.contains(SIXTH)
						|| string.contains(SEVENTH) || string.contains(EIGHT);
				Assert.assertTrue(flag);
			}
		}

	}

	/*checked intentsity in view popup*/
	public void intentsityInView(String chiefComplaint, String intentsity) {
		loginPage.waitForPageLoad();
		WebElement weblement;
		if (intentsity.equals("NA")) {
			weblement = loginPage.getDriver()
					.findElement(By.xpath("//tbody[@data-ng-repeat='input in viewList']//td[text()='" + chiefComplaint
							+ "']/following-sibling::td//span[contains(@data-ng-show,'input.CCdetails.ChiefcomplaintIntensity.length < 1')]"));
		} else {
			weblement = loginPage.getDriver()
					.findElement(By.xpath("//tbody[@data-ng-repeat='input in viewList']//td[text()='" + chiefComplaint
							+ "']/following-sibling::td//span[contains(@data-ng-repeat,'intensity in input.CCdetails.ChiefcomplaintIntensity')]"));
		}
		Assert.assertTrue(weblement.getText().contains(intentsity));
	}

	/*checking the username at the view popup*/
	public void userNameInView(String chiefComplaint, String userName) {
		loginPage.waitForPageLoad();
		WebElement weblement = loginPage.getDriver()
				.findElement(By.xpath("(//tbody[@data-ng-repeat='input in viewList']//td[text()='" + chiefComplaint
						+ "']/following-sibling::td[contains(@data-ng-class,'input.CCdetails.Chiefcomplaint.cc_deletestate')])[4]"));
		loginPage.WaitTillElementIsPresent(weblement);
		Assert.assertTrue(weblement.getText().equalsIgnoreCase(userName));
	}

	/*checking the nootes in view popup*/
	public void notesInView(String chiefComplaint, String note) {
		loginPage.waitForPageLoad();
		WebElement weblement = loginPage.getDriver()
				.findElement(By.xpath("//tbody[@data-ng-repeat='input in viewList']//td[text()='"+chiefComplaint+"']/../following-sibling::tr//div[contains(@class,'textDataVisualizerFull')]"));
		Assert.assertTrue(weblement.getText().trim().contains(note));
	}

	/*checking the periods in view popup*/
	public void periodsInView(String chiefComplaint, int expectedMonth, int expectedDay, String period) {
		boolean flag = false;
		loginPage.waitForPageLoad();
		loginPage.getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		if (period.equals("NA")) {
			WebElement weblement = loginPage.getDriver()
					.findElement(By.xpath("//tbody[@data-ng-repeat='input in viewList']//td[text()='" + chiefComplaint
							+ "']/following-sibling::td/span[contains(@data-ng-show,'input.CCdetails.ChiefcomplaintPeriod.length < 1')]"));
			flag = weblement.getText().contains(period);
		} else {
			List<WebElement> webelement = loginPage.getDriver()
					.findElements(By.xpath("//tbody[@data-ng-repeat='input in viewList']//td[text()='" + chiefComplaint
							+ "']/following-sibling::td//span[contains(@data-ng-class,'period.ccp_deletestatus')]"));
			for (int i = 0; webelement.size() > i; i++) {
				if (!webelement.get(i).getAttribute("class").contains("deleted")) {
					String actualString = webelement.get(i).getText();
					String expectedString = expectedMonth + " month(s) " + expectedDay + " day(s)";
					flag = actualString.equals(expectedString);
					break;
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/*closing the view popup*/
	public void closeViewPopup() {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(closeViewbutton);
		closeViewbutton.click();
	}

	/*checking header of the view popup*/
	public void viewChiefComplaint(){
		loginPage.waitForPageLoad();
		Assert.assertTrue(checkedElementDisplayed(headerViewPopup));
	}
	private boolean checkedElementDisplayed(WebElement webelement) {
		return (webelement.isDisplayed());
	}

}
