package com.prm.patientdashboard.pageobject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Screen;
import org.sikuli.script.Pattern;
import org.testng.Assert;
import com.prm.pageobjects.utils.PCDriver;
import com.prm.pageobjects.utils.TestData;

/**
 * 
 * @author Ajit Yadav
 *
 */
public class BasePatientLifeCyclePage {
	private PCDriver loginPage;

	public BasePatientLifeCyclePage(PCDriver loginPage) {
		this.loginPage = loginPage;
		PageFactory.initElements(loginPage.getDriver(), this);
	}

	/*WebElements which is common at the patient dashboard*/
	@FindBy(xpath = "//div[@id='wrapper']//i[contains(text(),'No Record Found')]")
	private WebElement NoRecordFoundMsg;
	@FindBy(xpath = "//span[contains(@class,'left-nav')]")
	private WebElement openLeftNavigationBtn;
	@FindBy(xpath = "//div[contains(@class,'left-nav')]")
	private WebElement closeLeftNavigationBtn;
	@FindBy(xpath = "//b[text()='Oral Exams']")
	private WebElement oralExam;
	@FindBy(xpath = "//b[text()='Diagnostics']")
	private WebElement Diagnostics;
	@FindBy(xpath = "//b[text()='Treatments']")
	private WebElement Treatments;
	@FindBy(xpath = "//b[text()='Works Done']")
	private WebElement WorksDone;
	@FindBy(id = "prescriptionList")
	private WebElement Prescriptions;
	@FindBy(id = "filesList")
	private WebElement filesList;
	@FindBy(id = "invoicesList")
	private WebElement invoicesList;
	@FindBy(xpath = "//span[@class='cmnicons dashB']")
	private WebElement DashboardBtn;
	@FindBy(xpath = "//i[text()='Add New']")
	private WebElement AddNewBtn;
	@FindBy(xpath = "//b[text()='Add New']")
	private WebElement AddNewBtnOldUi;
	@FindBy(xpath = "//div[@class='alertwrp']")
	private WebElement alert;
	@FindBy(xpath = "//div[@id='patientAlert'][contains(@style,'right: -1px')]")
	private List <WebElement> alertBody;
	@FindBy(css = "nav[class='navmenu navmenu-default navmenu-fixed-left']")
	public WebElement leftNavigationCloseBtnOldUi;
	@FindBy(xpath = "//h1[contains(@class,'pull-left')]")
	private WebElement listPageHeader;
	@FindBy(xpath = "//h1[contains(@class,'pull-left')]")
	private WebElement addPageHeader;
	@FindBy(xpath = "//h4[contains(@class,'modal-title')]")
	private WebElement viewPopupHeader;
	@FindBy(xpath = "//span[contains(@class,'mdl-hdr-text')]")
	private WebElement userName;
	@FindBy(xpath = "//span[@class='cmnicons cncl-mdl']")
	private WebElement closeBtnViewPop;
	@FindBy(xpath = "(//div[@class='fnt_avgR dateClnc']/span)[1]")
	private WebElement getDate;


	/*checked at all listing page if there is no data*/
	public void noRecordMsgDisplayed() {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(NoRecordFoundMsg);
		Assert.assertTrue(checkedElementDisplayed(NoRecordFoundMsg));
	}

	/*closing the view modal from the listing page*/
	public void closeViewPopup() {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(closeBtnViewPop);
		closeBtnViewPop.click();
	}

	/*closing the alert popup*/
	public void clickOnAlert() {
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		loginPage.waitForPageToBecomeActive();
		loginPage.waitForSpinnerToDisappear();
		loginPage.waitForElementToDisappear((By.xpath("//div[contains(@class='ui-widget-overlay')]")));
		loginPage.waitForElementVisibility(alert, 4000);
		loginPage.waitForElementToDisappear((By.xpath("//div[@class='ui-widget-overlay']")));
		alert.click();
		loginPage.waitForElementToDisappear((By.xpath("//div[contains(@class='modal overlay show')]")));
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
//	public void clickOnAlert() {
//		loginPage.waitForPageLoad();
//		loginPage.waitForElementToDisappear(By.xpath("//div[@class='modal overlay']"));
//		try {
//			Thread.sleep(8000);
//			alert.click();
//			Thread.sleep(2000);
//		} catch (InterruptedException| NoSuchElementException e) {
//			// TODO Auto-generated catch block
//			try {
//				Thread.sleep(8000);
//			} catch (InterruptedException interruptedException) {
//				interruptedException.printStackTrace();
//			}
//		}
//	}
//	
	/*checked the dashboard button present*/
	public void dashBoardBtnVerify() {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(DashboardBtn);
		Assert.assertTrue(checkedElementDisplayed(DashboardBtn));
	}

	/*clicking on the dashboard button*/
	
	public void clickOnDashBoard() {
		loginPage.waitForElementVisibility(DashboardBtn, 4000);
		loginPage.waitForElementToBeClickable(DashboardBtn);
		loginPage.executeScript(DashboardBtn);
		loginPage.waitForModalOverlayToDisappear();
	    loginPage.waitForSpinnerToDisappear();
	}
	
	
//	public void clickOnDashBoard() {
//		loginPage.waitForPageLoad();
//		loginPage.waitForElementToBeClickable(DashboardBtn);
//		loginPage.executeScript(DashboardBtn);
//		try {
//			Thread.sleep(4000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//	}

	/*checked add new button at listing page*/
	public void verifyAddNewBtn() {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(AddNewBtn);
		Assert.assertTrue(checkedElementDisplayed(AddNewBtn));
	}

	/*checked add new button at old listing page*/
	public void verifyAddNewBtnOldUi() {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(AddNewBtnOldUi);
		Assert.assertTrue(checkedElementDisplayed(AddNewBtnOldUi));
	}

	/*clicking at the add new button*/
	public void clickOnAddNewBtn() {
		loginPage.waitForSpinnerToDisappear();
		loginPage.waitForElementToBeClickable(AddNewBtn);
		AddNewBtn.click();
		loginPage.waitForSpinnerToDisappear();
			
	
	}

	/*open the side navigation module*/
//	public void openCloseLeftNavigator() {
//		loginPage.waitForPageLoad();
//		try {
//			Thread.sleep(4000);
//			loginPage.waitForElementToBeClickable(openLeftNavigationBtn);
//			openLeftNavigationBtn.click();
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

	/*open the side navigation module*/
	
	public void openCloseLeftNavigator() {
		loginPage.waitForSpinnerToDisappear();
		loginPage.waitForPageToBecomeActive();
		loginPage.waitForElementToBeClickable(openLeftNavigationBtn);
		openLeftNavigationBtn.click();
	}
	
//	public void openCloseLeftNavigator() {
//		loginPage.waitForPageLoad();
//		try {
//			Thread.sleep(4000);
//			loginPage.waitForElementToBeClickable(openLeftNavigationBtn);
//			openLeftNavigationBtn.click();
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

	/*Close the side navigation module*/
	public void closeLeftNavigator() {
		loginPage.waitForPageLoad();
		try {
			Thread.sleep(4000);
			loginPage.waitForElementToBeClickable(closeLeftNavigationBtn);
			closeLeftNavigationBtn.click();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*checking the all the module name present at left navigation bar*/
	public void webElementOfLeftNavigator() {
//		loginPage.waitForPageLoad();
		loginPage.waitForPageToBecomeActive();;
		loginPage.waitForElementToBeClickable(oralExam);
		Assert.assertTrue(checkedElementDisplayed(oralExam) && checkedElementDisplayed(Diagnostics)
				&& checkedElementDisplayed(Treatments) && checkedElementDisplayed(WorksDone)
				&& checkedElementDisplayed(Prescriptions) && checkedElementDisplayed(filesList)
				&& checkedElementDisplayed(invoicesList));
	}

	/*closing the side navigation in the old ui*/
	public void clickOnLeftNavigatorCloseBtnOldUi() {
		loginPage.waitForPageLoad();
		Screen screen = new Screen();
		Pattern pattern = new Pattern(".\\sikuliImages\\treatmentCloseSideNavigation.png");
		try {
			screen.click(pattern);
		} catch (FindFailed  e) {
			e.printStackTrace();
		}
	}

	/*checking the header of the page at listing pages*/
	
	public void headerOnListPage(String Header) {
		loginPage.waitForPageLoad();
		loginPage.waitForSpinnerToDisappear();
//		loginPage.WaitTillElementIsPresent(listPageHeader);
		loginPage.waitForElementVisibility(listPageHeader, 4000);
		Assert.assertTrue(listPageHeader.getText().contains(Header));
	}
	
	
	
//	public void headerOnListPage(String Header) {
//		loginPage.waitForPageLoad();
//		loginPage.WaitTillElementIsPresent(listPageHeader);
//		try {
//			Thread.sleep(5000);
//			Assert.assertTrue(listPageHeader.getText().contains(Header));
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

	/*checking the header of the page at Add pages*/
	public void headerOnAddPage(String Header) {
		loginPage.waitForPageLoad();
		loginPage.waitForSpinnerToDisappear();
		loginPage.waitForElementToBeClickable(addPageHeader);
		Assert.assertTrue(addPageHeader.getText().contains(Header));
	}

	/*checking the header of the view modal*/
	public void headerViewPopup(String Header) {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(viewPopupHeader);
		Assert.assertTrue(viewPopupHeader.getText().contains(Header));
	}

	/*checking the patient name and patient id is not null*/
	public void verifyPatientName(String patientName) {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(userName);
		String str = userName.getText();
		String[] split = str.split(" : ");
		boolean flag = split[0].contains(patientName) && !(split[1].equals("NA") || split[1].equals("Null"));
		Assert.assertTrue(flag);
	}

	/*checking the dates at listing page*/
	public void verifyDates() {
		    loginPage.waitForSpinnerToDisappear();
		    loginPage.waitForElementVisibility(AddNewBtn,4000);
    		String actualDate = getDate.getText().trim();
    		System.out.println("Actual Date "+actualDate);
//			Date dat = new Date();
//			String expectedDate = new SimpleDateFormat("dd-MM-yy").format(dat);
    		String expectedDate = TestData.getInstance().getTodayDate().trim();
    		System.out.println("Expected Date "+expectedDate);
			Assert.assertTrue(expectedDate.equals(actualDate));
	}
		
//	public void verifyDates() {
//		loginPage.waitForPageLoad();
//		try {
//			Thread.sleep(4000);
//			WebElement web = loginPage.getDriver().findElement(By.xpath("(//div[@class='fnt_avgR dateClnc']/span)[1]"));
//			String actualDate = web.getText().trim();
//			Date dat = new Date();
//			String expectedDate = new SimpleDateFormat("dd-MM-yy").format(dat);
//			Assert.assertTrue(expectedDate.equals(actualDate));
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//	}
	/*validating all action button present listing page when just added the items*/
	
	public void actionBtnMainList(String clinicName) {
		loginPage.waitForModalOverlayToDisappear();
		loginPage.waitForSpinnerToDisappear();
		loginPage.waitForElementVisibility(AddNewBtn,4000);
		WebElement viewBtn = loginPage.getDriver()
				.findElement(By.xpath("//div[@class='fnt_avgR dateClnc']//span[contains(text(),'" + clinicName
						+ "')]/../following-sibling::div//span[@class='actn-icn view']"));
		WebElement editBtn = loginPage.getDriver()
				.findElement(By.xpath("//div[@class='fnt_avgR dateClnc']//span[contains(text(),'" + clinicName
						+ "')]/../following-sibling::div//span[@class='actn-icn edit']"));
		WebElement deleteBtn = loginPage.getDriver()
				.findElement(By.xpath("//div[@class='fnt_avgR dateClnc']//span[contains(text(),'" + clinicName
						+ "')]/../following-sibling::div//span[@class='actn-icn delete']"));
		WebElement printBtn = loginPage.getDriver()
				.findElement(By.xpath("//div[@class='fnt_avgR dateClnc']//span[contains(text(),'" + clinicName
						+ "')]/../following-sibling::div//span[@class='actn-icn print']"));
		Assert.assertTrue(checkedElementDisplayed(viewBtn) && checkedElementDisplayed(editBtn)
				&& checkedElementDisplayed(deleteBtn) && checkedElementDisplayed(printBtn));
	}

	/*opening the view modal at the listing  page*/
	public void clickViewBtn(String clinicName) {
		loginPage.waitForPageLoad();
		WebElement element = loginPage.getDriver()
				.findElement(By.xpath("//div[@class='fnt_avgR dateClnc']//span[contains(text(),'" + clinicName
						+ "')]/../following-sibling::div//span[@class='actn-icn view']"));
		element.click();
	}

	/*Editing the items from the listing page*/
	public void clickEditBtn(String clinicName) {
		loginPage.waitForPageLoad();
		try {
			Thread.sleep(2000);
			WebElement ele = loginPage.getDriver()
					.findElement(By.xpath("//div[@class='fnt_avgR dateClnc']//span[contains(text(),'" + clinicName
							+ "')]/../following-sibling::div//span[@class='actn-icn edit']"));
			ele.click();
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*Deleting the item from listing page*/
	public void clickDeleteBtn(String clinicName) {
		loginPage.waitForPageLoad();
		WebElement element = loginPage.getDriver()
				.findElement(By.xpath("//div[@class='fnt_avgR dateClnc']//span[contains(text(),'" + clinicName
						+ "')]/../following-sibling::div//span[@class='actn-icn delete']"));
		element.click();
	}

	/*checking webelement present at webpage*/
	private boolean checkedElementDisplayed(WebElement webelement) {
		return (webelement.isDisplayed());
	}

}
