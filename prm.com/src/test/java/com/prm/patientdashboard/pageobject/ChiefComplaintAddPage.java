package com.prm.patientdashboard.pageobject;

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
public class ChiefComplaintAddPage {
	private PCDriver loginPage;

	public ChiefComplaintAddPage(PCDriver loginPage) {
		this.loginPage = loginPage;
		PageFactory.initElements(loginPage.getDriver(), this);
	}

	/*WebElement of the chief complaint Add page*/
	@FindBy(xpath = "//div[contains(@class,'sideNavigation')]//i[contains(text(),'Oral Exam +')]")
	private WebElement oralExamBtn;
	@FindBy(xpath = "//span[contains(text(),'Chief Complaint added successfully!')]")
	private WebElement successMsg;
	@FindBy(xpath = "//span[@class='cmnicons yes-mdl']")
	private WebElement yesOnDelete;
	@FindBy(xpath = "//div[@class='modal-body ccAddEditModal']//h4[text()='Location']")
	private WebElement location;
	@FindBy(id = "intensitySecPopUp")
	private WebElement intensity;
	@FindBy(id = "period")
	private WebElement period;
	@FindBy(id = "ccRemark")
	private WebElement notes;
	@FindBy(xpath = "//span[contains(@class,'maxillary')]")
	private WebElement maxillary;
	@FindBy(xpath = "//span[contains(@class,'mandibular')]")
	private WebElement mandibular;
	@FindBy(css = "button[aria-label='Save']")
	private WebElement saveBtn;
	@FindBy(css = "button[aria-label='Close']")
	private WebElement closeBtn;
	@FindBy(xpath = "//div[@class='posteriorAntrrior']//a[contains(@data-ng-if,'Posterior')]")
	private List<WebElement> locations;
	@FindBy(className = "Severe")
	private WebElement severe;
	@FindBy(className = "Moderate")
	private WebElement moderate;
	@FindBy(className = "Mild")
	private WebElement mild;
	@FindBy(xpath = "//div[@class='spetrr']//h5[text()='Month(s)']/following-sibling::a[@class='periodScrollUp']")
	private WebElement monthSliderUp;
	@FindBy(xpath = "//div[@class='spetrr']//h5[text()='Day(s)']/following-sibling::a[@class='periodScrollUp']")
	private WebElement daySliderUp;
	@FindBy(xpath = "//div[@class='maxiMandiSectn']/following-sibling::div//span[text()='Right']")
	private WebElement right;
	@FindBy(xpath = "//div[@class='maxiMandiSectn']/following-sibling::div//span[text()='Left']")
	private WebElement left;
	@FindBy(xpath = "//div[@class='spetrr']//h5[text()='Month(s)']")
	private WebElement month;
	@FindBy(xpath = "//div[@class='spetrr']//h5[text()='Day(s)']")
	private WebElement day;
	@FindBy(xpath = "//span[contains(text(),'30')]")
	private WebElement dayInMonth;
	@FindBy(xpath = "//span[contains(text(),'12')]")
	private WebElement monthInYear;
	@FindBy(xpath = "//th[text()='Chief Complaint']")
	private WebElement chiefComplaintInputList;
	@FindBy(xpath = "//th[text()='Location']")
	private WebElement locationInputList;
	@FindBy(xpath = "//th[text()='Intensity']")
	private WebElement intensityInputList;
	@FindBy(xpath = "//th[text()='Period']")
	private WebElement periodInputList;
	@FindBy(xpath = "//th[text()='Notes']")
	private WebElement notesInputList;
	@FindBy(xpath = "//th[text()='Action']")
	private WebElement actionInputList;
	@FindBy(xpath = "//div[@class='modal-content']")
	private WebElement chiefCmpltPopUp;
	@FindBy(xpath = "//div[@class='alert ng-isolate-scope alert-success alert-dismissable']")
	private WebElement successAlert;
	

	/*checking chief complaint at the input listing*/
	public void verifyChiefComplaintsInputList(String ChiefComplaints) {
		loginPage.waitForPageLoad();
		WebElement element = loginPage.getDriver()
				.findElement(By.xpath("//td[contains(text(),'" + ChiefComplaints + "')]"));
		Assert.assertTrue(checkedElementDisplayed(element));
	}

	/*chief complaint added success message*/
	public void chiefComplaintAddSuccessMsg() {
		loginPage.waitForElementToBeClickable(successMsg);
		Assert.assertTrue(checkedElementDisplayed(successMsg));
	}

	

	/*checking every chief comlaints boxes present at chief complaints Add page*/
	public void VerifyChiefCompliantBoxes() {
		loginPage.waitForPageLoad();
		boolean flag = (checkedElementDisplayed(chiefComplaint("Pain"))
				&& checkedElementDisplayed(chiefComplaint("Sensitivity"))
				&& checkedElementDisplayed(chiefComplaint("Chewing Difficulty"))
				&& checkedElementDisplayed(chiefComplaint("Loose Teeth"))
				&& checkedElementDisplayed(chiefComplaint("Bad Breath"))
				&& checkedElementDisplayed(chiefComplaint("Bleeding Gums"))
				&& checkedElementDisplayed(chiefComplaint("Discolored Tooth"))
				&& checkedElementDisplayed(chiefComplaint("Swollen Gums"))
				&& checkedElementDisplayed(chiefComplaint("Plaque"))
				&& checkedElementDisplayed(chiefComplaint("Tooth Decay"))
				&& checkedElementDisplayed(chiefComplaint("Teeth Grinding"))
				&& checkedElementDisplayed(chiefComplaint("Other")));
		Assert.assertTrue(flag);
	}

	/*Selecting the chief complaint at chief complaint add page*/
	public void clickOnChiefComplaint(String chiefComplaint) {
		loginPage.waitForSpinnerToDisappear();
		loginPage.waitForPageToBecomeActive();
		loginPage.waitForElementVisibility(chiefComplaint(chiefComplaint), 4000);
		loginPage.waitForElementToBeClickable(chiefComplaint(chiefComplaint));
		if(chiefComplaint == "Pain" || chiefComplaint == "Sensitivity"){
			chiefComplaint(chiefComplaint).click();
			loginPage.waitForElementVisibility(chiefCmpltPopUp, 4000);	
		}
		else
		{
			chiefComplaint(chiefComplaint).click();
			loginPage.waitForSpinnerToDisappear();
		}	
	}
	
	
//	public void clickOnChiefComplaint(String chiefComplaint) {
//		loginPage.waitForPageLoad();
//		loginPage.waitForElementToBeClickable(chiefComplaint(chiefComplaint));
//		try {
//			Thread.sleep(3500);
//			chiefComplaint(chiefComplaint).click();
//			Thread.sleep(3500);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

	/*checking the oral exam button*/
	public void verifyOralExamBtn() {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(oralExamBtn);
		Assert.assertTrue(checkedElementDisplayed(oralExamBtn));
	}

	/*deleting the chief complaint from the input listing*/
	public void clickOnDeleteBtn(String chiefComplaint) {
		loginPage.waitForSpinnerToDisappear();
		loginPage.waitForPageToBecomeActive();
		loginPage.getDriver().findElement(By.xpath("//td[contains(text(),'"+chiefComplaint+"')]/following-sibling::td/a[@class='btn ng-isolate-scope']")).click();
		loginPage.waitForSpinnerToDisappear();
		Assert.assertTrue(loginPage.getDriver()
				.findElement(By.xpath("//p[text()='Do you want to delete the selected Chief Complaint?']"))
				.isDisplayed());
		loginPage.waitForElementToBeClickable(yesOnDelete);
		yesOnDelete.click();
	}
		
//	public void clickOnDeleteBtn(String chiefComplaint) {
//		loginPage.waitForPageLoad();
//		try {
//			Thread.sleep(2000);
//			loginPage.getDriver().findElement(By.xpath("//td[contains(text(),'"+chiefComplaint+"')]/following-sibling::td/a[@class='btn ng-isolate-scope']")).click();
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		loginPage.waitForPageLoad();
//		Assert.assertTrue(loginPage.getDriver()
//				.findElement(By.xpath("//p[text()='Do you want to delete the selected Chief Complaint?']"))
//				.isDisplayed());
//		loginPage.waitForElementToBeClickable(yesOnDelete);
//		yesOnDelete.click();
//	}

	/*closing the pain popup*/
	public void clickOnCloseBtn() {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(closeBtn);
		closeBtn.click();
	}

	/*saving the chief complaint */
	public void clickOnSaveBtn() {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(closeBtn);
		saveBtn.click();
		loginPage.waitForSpinnerToDisappear();
		loginPage.waitForElementToDisappear(By.xpath("//div[@class='alert ng-isolate-scope alert-success alert-dismissable']"));
	}
	
//	public void clickOnSaveBtn() {
//		loginPage.waitForPageLoad();
//		loginPage.waitForElementToBeClickable(closeBtn);
//		try {
//			saveBtn.click();
//			Thread.sleep(2000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

	/*entering the notes inside the notes text fields*/
	public void enterNote(String note) {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(notes);
		notes.sendKeys(note);
	}

	/*checking the webelemnt of the pain popup*/
	public void checkedPopupElement() {
		loginPage.waitForElementVisibility(intensity, 4000);
		loginPage.waitForElementToBeClickable(location);
		boolean flag = checkedElementDisplayed(location) && checkedElementDisplayed(intensity)
				&& checkedElementDisplayed(period) && checkedElementDisplayed(notes)
				&& (notes.getAttribute("maxlength").contains("768")) && checkedElementDisplayed(maxillary)
				&& checkedElementDisplayed(mandibular)&&checkedElementDisplayed(saveBtn)&& 
				checkedElementDisplayed(closeBtn) && checkedElementDisplayed(dayInMonth)
				&& checkedElementDisplayed(severe) && checkedElementDisplayed(moderate) && checkedElementDisplayed(mild)
		&& (locations.size() == 8) && checkedElementDisplayed(right)&& 
		checkedElementDisplayed(left) && checkedElementDisplayed(day) && checkedElementDisplayed(month);
		Assert.assertTrue(flag);
	}

	/*checking the location is multi selection*/
	public void multiSelectOfLocation() {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(location);
		for (int i = 0; locations.size() > i; i++) {
			locations.get(i).click();
			Assert.assertTrue(locations.get(i).getAttribute("class").contains("selected"));
		}
		// checking all location have multiselect
		for (int i = 0; locations.size() > i; i++) {
			Assert.assertTrue(locations.get(i).getAttribute("class").contains("selected"));
		}
	}


	public void clickOnSevere() {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(severe);
		severe.click();
	}

	public void clickOnModerate() {
		loginPage.waitForPageLoad();
		moderate.click();
	}

	public void verifySevereIsNotSelected() {
		loginPage.waitForPageLoad();
		Assert.assertTrue(!severe.getAttribute("class").contains("selected"));
	}

	/*selecting the months from the chief complaint popup */
	public void SelectMonth(int month) {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(monthSliderUp);
		for (int i = 1; month >= i; i++) {
			try {
				Thread.sleep(1000);
				monthSliderUp.click();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/*selecting the date from chief complaint popup*/
	public void SelectDays(int day) {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(daySliderUp);
		for (int i = 1; day >= i; i++) {
			try {
				Thread.sleep(1000);
				daySliderUp.click();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/*checking the column name at input listing*/
	public void checkedInputlistDataName() {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(chiefComplaintInputList);
		Assert.assertTrue(checkedElementDisplayed(chiefComplaintInputList) && checkedElementDisplayed(locationInputList)
				&& checkedElementDisplayed(intensityInputList) && checkedElementDisplayed(periodInputList)
				&& checkedElementDisplayed(notesInputList) && checkedElementDisplayed(actionInputList));
	}

	/*checking periods behavior at chief complaint add page*/
	public void periodsBehaviour(String chiefComplaint, int expectedMonth, int expectedDay, String periods) {
		loginPage.waitForPageLoad();
		loginPage.getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		if (periods.equals("NA")) {
			WebElement webelement = loginPage.getDriver().findElement(By.xpath("//td[contains(text(),'" + chiefComplaint
					+ "')]/following-sibling::td//span[@data-ng-show='input.CCdetails.ChiefcomplaintPeriod.length < 1']"));
			loginPage.waitForElementToBeClickable(webelement);
			Assert.assertTrue(webelement.getText().contains(periods));
		} else {
			List<WebElement> webelement = loginPage.getDriver().findElements(By.xpath("//td[contains(text(),'"
					+ chiefComplaint
					+ "')]/following-sibling::td//span[contains(@data-ng-class,'{\"deleted\":period.ccp_deletestatus}')]"));
			for (int i = 0; webelement.size() > i; i++) {
//				System.out.println(!webelement.get(i).getAttribute("class").contains("deleted"));
				if (!webelement.get(i).getAttribute("class").contains("deleted")) {		
					String actualString = webelement.get(i).getText();
					String expectedString = expectedMonth + " month(s) " + expectedDay + " day(s)";
					System.out.println("Actual Strinng"+actualString+" "+"Expected String"+expectedString);
//					Assert.assertTrue(actualString.equals(expectedString));
//					Assert.assertEquals(actualString, expectedString);
				}
			}
		}
	}

	/*checking the without location at input listing*/
	public void withoutLocationInputList(String chiefComplaints, String location) {
		loginPage.waitForPageLoad();
		WebElement webelement = loginPage.getDriver().findElement(By.xpath("//td[contains(text(),'" + chiefComplaints
				+ "')]/following-sibling::td//span[@data-ng-hide='input.CCdetails.Chiefcomplaintlocation.length']"));
		loginPage.waitForElementToBeClickable(webelement);
		boolean flag = webelement.getText().contains(location);
		Assert.assertTrue(flag);		
	}

	/*checking the intensity at input listing*/
	public void intensityInputList(String chiefComplaints, String intensity) {
		loginPage.waitForPageLoad();
		WebElement webelement;
		if (intensity.equals("NA")) {
			webelement = loginPage.getDriver().findElement(By.xpath("//td[contains(text(),'" + chiefComplaints
					+ "')]/following-sibling::td/span[@data-ng-show='input.CCdetails.ChiefcomplaintIntensity.length < 1']"));
			loginPage.waitForElementToBeClickable(webelement);
		} else {
			webelement = loginPage.getDriver().findElement(By.xpath("//td[contains(text(),'" + chiefComplaints
					+ "')]/following-sibling::td/span[@data-ng-repeat='intensity in input.CCdetails.ChiefcomplaintIntensity']"));
			loginPage.waitForElementToBeClickable(webelement);
		}
		Assert.assertTrue(webelement.getText().contains(intensity));
	}

	/*checking the notes in input listing*/
	public void notesInputList(String chiefComplaints, String notes) {
		   loginPage.waitForPageToBecomeActive();
			WebElement webelement = loginPage.getDriver().findElement(By.xpath("//td[contains(text(),'" + chiefComplaints
					+ "')]/following-sibling::td[contains(@class,'wrd-brk')]//div[contains(@class,'tdvNotes')]"));
			loginPage.WaitTillElementIsPresent(webelement);
			Assert.assertTrue(webelement.getText().contains(notes));
	}
		
//	public void notesInputList(String chiefComplaints, String notes) {
//		loginPage.waitForPageLoad();
//		try {
//			Thread.sleep(2000);
//			WebElement webelement = loginPage.getDriver().findElement(By.xpath("//td[contains(text(),'" + chiefComplaints
//					+ "')]/following-sibling::td[contains(@class,'wrd-brk')]//div[contains(@class,'tdvNotes')]"));
//			loginPage.WaitTillElementIsPresent(webelement);
//			Assert.assertTrue(webelement.getText().contains(notes));
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//	}
	
	public void clickEditInInputList(String chiefComplaints) {
		    loginPage.waitForSpinnerToDisappear();
			WebElement webelement = loginPage.getDriver().findElement(
			By.xpath("//td[contains(text(),'" + chiefComplaints + "')]/following-sibling::td/a[@class='btn']"));
			loginPage.waitForElementToBeClickable(webelement);
			webelement.click();
	}


//	public void clickEditInInputList(String chiefComplaints) {
//		try {
//			Thread.sleep(4000);
//			WebElement webelement = loginPage.getDriver().findElement(
//					By.xpath("//td[contains(text(),'" + chiefComplaints + "')]/following-sibling::td/a[@class='btn']"));
//			loginPage.waitForElementToBeClickable(webelement);
//			webelement.click();
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

	/*chief complaint after edit at chief complaint Add page*/
	public void chiefComplaintsInputEdit(String chiefComplaints) {
		loginPage.waitForPageLoad();
		WebElement notesInput = loginPage.getDriver().findElement(
				By.xpath("//td[contains(text(),'" + chiefComplaints + "')]/following-sibling::td//textarea"));
		WebElement limitMessageNote = loginPage.getDriver().findElement(By.xpath("//td[contains(text(),'" + chiefComplaints
				+ "')]/following-sibling::td//textarea/following-sibling::span"));
		WebElement saveBtn = loginPage.getDriver().findElement(By.xpath("//td[contains(text(),'" + chiefComplaints
				+ "')]/following-sibling::td//span[@class='actn-icn save']"));
		WebElement cancelBtn = loginPage.getDriver().findElement(By.xpath("//td[contains(text(),'" + chiefComplaints
				+ "')]/following-sibling::td//span[@class='actn-icn cancel']"));
		Assert.assertTrue(checkedElementDisplayed(notesInput) && checkedElementDisplayed(limitMessageNote)
				&& checkedElementDisplayed(saveBtn) && checkedElementDisplayed(cancelBtn));
	}

	/*cancel the edit from the input listing*/
	public void cancelInputListEdit(String chiefComplaints) {
		loginPage.waitForPageLoad();
		WebElement cancelBtn = loginPage.getDriver().findElement(By.xpath("//td[contains(text(),'" + chiefComplaints
				+ "')]/following-sibling::td//span[@class='actn-icn cancel']"));
		cancelBtn.click();
	}

	/*entering the notes after edit at input listing*/
	public void enterNotesInputList(String chiefComplaints, String notes) {
		loginPage.waitForPageLoad();
		WebElement notesInput = loginPage.getDriver().findElement(
				By.xpath("//td[contains(text(),'" + chiefComplaints + "')]/following-sibling::td//textarea"));
		loginPage.WaitTillElementIsPresent(notesInput);
		notesInput.sendKeys(notes);
	}

	/*saving after edit from the input listing*/
	public void saveInputListEdit(String chiefComplaints) {
		loginPage.waitForPageLoad();
		WebElement saveBtn = loginPage.getDriver().findElement(By.xpath("//td[contains(text(),'" + chiefComplaints
				+ "')]/following-sibling::td//span[@class='actn-icn save']"));
		saveBtn.click();
	}

	/*action button at input listing*/
	public void actionBtnInputList(String chiefComplaints) {
		loginPage.waitForPageLoad();
		WebElement deleteBtn = loginPage.getDriver().findElement(By.xpath("//td[contains(text(),'" + chiefComplaints
				+ "')]/following-sibling::td/a[@class='btn ng-isolate-scope']"));
		WebElement editBtn = loginPage.getDriver().findElement(
				By.xpath("//td[contains(text(),'" + chiefComplaints + "')]/following-sibling::td/a[@class='btn']"));
		Assert.assertTrue(checkedElementDisplayed(deleteBtn) && checkedElementDisplayed(editBtn));
	}

	/*checking chief complaint after deleteing*/
    public void deletedChiefComplaintInInputList(String chiefComplaint){
        try {
            Thread.sleep(2000);
            WebElement ele = loginPage.getDriver().findElement(By.xpath("//table[@id='ccInputListTable']//td[contains(text(),'" + chiefComplaint + "')]"));
            Assert.assertTrue(ele.getAttribute("class").contains("deleted"));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
	private boolean checkedElementDisplayed(WebElement webelement) {
		return (webelement.isDisplayed());
	}

	private WebElement chiefComplaint(String chiefComplaints) {
		WebElement chiefCmplnt = loginPage.getDriver().findElement(By.xpath("//button[contains(text(),'" + chiefComplaints + "')]"));
		loginPage.waitForElementVisibility(chiefCmplnt, 4000);
		return chiefCmplnt;
	}

}