package com.prm.docdashboard.pageobject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.prm.pageobjects.utils.PCDriver;

/*
 *  @author NIKHIL TYAGI*
 */

public class     MiscellaneousCallListingPage {
	private PCDriver login;
	public MiscellaneousCallListingPage(PCDriver login) {
		this.login = login;
		PageFactory.initElements(login.getDriver(), this);
	}

	// ------WebElement of Main List---------
	@FindBy(xpath = "//span[text()='Date/Time']")
	private WebElement dateOnMiscList;
	@FindBy(xpath = "//span[text()='Name']")
	private WebElement nameMainList;
	@FindBy(xpath = "//span[text()='Mobile']")
	private WebElement mobileMainList;
	@FindBy(xpath = "//span[text()='Type']")
	private WebElement typeMainList;
	@FindBy(xpath = "//span[text()='Disposition']")
	private WebElement dispositionMainList;
	@FindBy(xpath = "//span[text()='Info']")
	private WebElement infoMainList;
	@FindBy(xpath = "//span[text()='Caller']")
	private WebElement callerMainList;
	@FindBy(xpath = "//span[text()='Notes']")
	private WebElement notesMainList;
	@FindBy(xpath = "//span[contains(text(),'Call details added successfully!')]")
	private WebElement successfullMsg;

	// ------WebElement of Filter in Miscellaneous Call List---------
	@FindBy(id="type")
	private WebElement typeDrpDwn;
	@FindBy(id="disposition")
	private WebElement dispDrpDwn;
	@FindBy(id="caller Name")
	private WebElement callerNameDrpDwn;
	@FindBy(id="details")
	private WebElement detailsTxtBx;
	@FindBy(id="fromDate")
	private WebElement fromDateBtn;
	@FindBy(id="toDate")
	private WebElement toDateBtn;

	/*----Subscriber listing---*/
	@FindBy(xpath="//i[text()='Misc. Calls']")
	private WebElement miscCallsListBtn;

	/* ------WebElement of header in Miscellaneous Call List---------*/
	@FindBy(xpath = "//i[text()='Add New']")
	private WebElement addNewBtnOnMiscList;
	@FindBy(xpath="//i[text()='Subscribers']")
	private WebElement subscribersBtn;
	@FindBy(xpath="//i[text()='DashBoard']")
	private WebElement dashBoardBtn;
	@FindBy(xpath="//i[text()='Search']")
	private WebElement searchBtn;
	@FindBy(xpath="//i[text()='Reset']")
	private WebElement resetBtn;
	/* Button is for Admin
	@FindBy(xpath="//i[text()='Export']")
	private WebElement exportBtn;*/
	
	/*
	 * 
	 */
	@FindBy(xpath = "//table[@class='table']/tbody/tr")
	private List<WebElement> callRecordOnListing; 
	@FindBy(xpath="//div[@class='clearfix topPagination pagingUI']//a[@class='next']")
	private WebElement nextPageButton;
	@FindBy(xpath="//div[@class='clearfix topPagination pagingUI']//a[@class='previous']")
	private WebElement previousPageButton;
	@FindBy(xpath="//div[@class='clearfix topPagination pagingUI']//a[contains(@class,'pageNo')]")
	private List<WebElement> listOfPages;
	@FindBy(xpath = "//i[@class='ng-binding' and text()='No Record Found!']")
	private WebElement noRecordFoundMsg;
	@FindBy(xpath="//span[contains(text(),'The date range selected is more than 90 no. of day')]")
	private WebElement dateRangeMoreErrorMsg;
	@FindBy(xpath="//span[normalize-space()='From Date cannot exceed To Date.']")
	private WebElement fromDateExceedToDateMsg;

	/*--------Functions-------------*/

	/*-- Checking the All Button shown in Miscellaneous Call listing --*/
	public void checkedAllBtnInMiscellaneousCallListing() {
		login.waitForPageLoad();
		Assert.assertTrue(checkedWebElementDisplayed(searchBtn) && checkedWebElementDisplayed(resetBtn) &&
				checkedWebElementDisplayed(dashBoardBtn) && checkedWebElementDisplayed(subscribersBtn));
	}

	/*-- Click the Subscribers Button shown in Miscellaneous Call listing --*/
	public void clickOnSubscribers() {
		login.waitForPageLoad();
		login.waitForElementToBeClickable(subscribersBtn);
		subscribersBtn.click();
	}

	/*-- Click the Miscellaneous Call list Button shown in Subscriber listing --*/
	public void clickMiscsCallListBtn() {
		login.WaitTillElementIsPresent(miscCallsListBtn);
		login.waitForPageLoad();
		miscCallsListBtn.click();
	}

	/*-- Click the DashhBoard Button shown in Miscellaneous Call listing --*/
	public void clickOnDashboardBtn() {
		login.waitForPageLoad();
		login.waitForElementToBeClickable(dashBoardBtn);
		dashBoardBtn.click();
	}

	/*-- Click the Reset Button shown in Miscellaneous Call listing --*/
	public void clickOnResetBtn() {
		login.waitForPageLoad();
		login.waitForElementToBeClickable(resetBtn);
		resetBtn.click();
		login.waitForSpinnerToDisappear();
	}

	/*-- Click the Search Button shown in Miscellaneous Call listing --*/
	public void clickSearchBtn()
	{
		login.waitForPageLoad();
		searchBtn.click();
		login.waitForSpinnerToDisappear();
	}

	/*-- Checking All Head after enter new Call shown in Miscellaneous Call listing --*/
	public void checkedMainListDataName() {
		login.waitForPageLoad();
		Assert.assertTrue(checkedWebElementDisplayed(dateOnMiscList) && checkedWebElementDisplayed(nameMainList) &&
				checkedWebElementDisplayed(mobileMainList) && checkedWebElementDisplayed(typeMainList) && checkedWebElementDisplayed(dispositionMainList) &&
				checkedWebElementDisplayed(infoMainList) && checkedWebElementDisplayed(callerMainList) && checkedWebElementDisplayed(notesMainList));
	}

	/*-- Verifying the Date and Time show in Main List --*/
	public void checkDateTimeMainList(String mobno,String expectedDate,String expectedTime) {
		login.waitForPageLoad();
		login.waitForSpinnerToDisappear();
		WebElement ele = login.getDriver().findElement(By.xpath("//td[text()='"+mobno+"']/preceding-sibling::td/div"));
		String str = ele.getText();
		System.out.println(str);
		String date=str.split("\n")[0].trim();
		String time=str.split("\n")[1].trim();
		System.out.println("*******"+date);
		System.out.println("##########*"+time);
		System.out.println("%%%%%%%%"+expectedTime);
		String dateTime = date+" "+time;
		try {
			SimpleDateFormat fmt = new SimpleDateFormat("dd-mm-yy h:mm a");
			Date actualDateTime = fmt.parse(dateTime);
			Date expectedDateTime = fmt.parse(expectedDate+" "+expectedTime);

			System.out.println("***********"+actualDateTime);
			System.out.println("***********"+expectedDateTime);
			
			if (actualDateTime.equals(expectedDateTime)) {
				System.out.println("Date and Time of main list verified:- "+actualDateTime);
			}
			else
				Assert.assertFalse(true);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*-- Verifying the Name shown in Miscellaneous Call listing --*/
	public void nameInMainList(String todayDate,String mobno, String name) {
		login.waitForPageLoad();
		WebElement ele = login.getDriver().findElement(By.xpath("//span[text()='"+todayDate+"']/../../following-sibling::td[text()='"+mobno+"']/preceding-sibling::td[text()='"+name+"']"));
		String actual_name = ele.getText();
		Assert.assertEquals(actual_name, name);
	}

	/*-- Verifying the Mobile No,Type,Disposition,Info,Caller shown in Miscellaneous Call listing --*/
	public void miscellaneousCallList(String todayDate,String mobno, String misc) {
		login.waitForPageLoad();
		WebElement ele = login.getDriver().findElement(By.xpath("//span[text()='"+todayDate+"']/../../following-sibling::td[text()='"+mobno+"']/following-sibling::td[text()='"+misc+"']"));
		String actual_data = ele.getText();
		Assert.assertEquals(actual_data, misc);
	}

	/*-- Verifying the Notes shown in Miscellaneous Call listing --*/
	public void notesCallList(String todayDate,String mobno,String notes) {
		login.waitForPageLoad();
		WebElement ele = login.getDriver().findElement(By.xpath("//span[text()='"+todayDate+"']/../../following-sibling::td[text()='"+mobno+"']/following-sibling::td/div[text()='"+notes+"']"));
		String actual_data = ele.getText();
		Assert.assertEquals(actual_data, notes);
	}

	/*-- Validate the Successful Msg --*/
	public void verifySuccessfullMessage(){
		login.waitForPageLoad();
		login.waitForSpinnerToDisappear();
		login.softAssert().assertTrue(checkedWebElementDisplayed(successfullMsg));
		
	}

	/*-- Checking All options in Type DropDown filter--*/
	public void checkTypeDropDwn() {
		login.waitForPageLoad();
		boolean flag1, flag2, match = false;
		flag1 = checkedWebElementDisplayed(typeDrpDwn);
		String[] exp = {"Select Type","Inbound", "Outbound" };
		Select sel = new Select(typeDrpDwn);
		List<WebElement> options = sel.getOptions();
		for (WebElement we : options) {
			for (int i = 0; i < exp.length; i++) {
				if (flag2 = we.getText().equals(exp[i])) {
					match = flag1 && flag2;
				}
			}
			Assert.assertTrue(match);
		}
	}

	/*-- Select any one option from Type DropDown filter--*/
	public void selectType(String typeValue) {
		login.waitForSpinnerToDisappear();
		login.WaitTillElementIsPresent(typeDrpDwn);
		login.selectFromDropDownByVisibleText(typeDrpDwn, typeValue);
	}

	/*-- Checking All options in Disposition DropDown filter--*/
	public void checkDispositionDropDown() {
		login.waitForPageLoad();
		boolean flag1, flag2, match = false;
		flag1 = checkedWebElementDisplayed(dispDrpDwn);
		String[] exp = { "Select Disposition", "Busy", "Call Disconnected", "Call Me Back", "Clove Staff/Clinic",
				"Get Back To Patient", "Information Provided", "Invalid Number", "No Connect", "No Response",
				"Not Interested", "Patient Will Call", "Test Call", "Wrong Number" };
		Select sel = new Select(dispDrpDwn);
		List<WebElement> options = sel.getOptions();
		for (WebElement we : options) {
			for (int i = 0; i < exp.length; i++) {
				if (flag2 = we.getText().equals(exp[i])) {
					match = flag1 && flag2;
				}
			}
			Assert.assertTrue(match);
		}
	}

	/*-- Select any one option from Disposition DropDown filter --*/
	public void selectDisposition(String dispositionValue) {
		login.waitForSpinnerToDisappear();
		login.waitForElementToBeClickable(typeDrpDwn);
		login.selectFromDropDownByVisibleText(dispDrpDwn, dispositionValue);
	}

	/*-- Checking All options in CallerName DropDown filter --*/
	public void checkCallerNameDropDown() {
		login.waitForPageLoad();
		boolean flag1, flag2, match = false;
		flag1 = checkedWebElementDisplayed(callerNameDrpDwn);
		String[] exp = { "Select Caller Name", "A Sinha", "AlinaK", "Anu B", "ArunimaD",
				"AyushiA", "CCE", "Chirag D", "DeepaliC", "DHC 1",
				"DHC 2", "DHC 3", "DHC 4", "DHC 5","DHC 6","DivaD" };
		Select sel = new Select(callerNameDrpDwn);
		List<WebElement> options = sel.getOptions();
		for (WebElement we : options) {
			for (int i = 0; i < exp.length; i++) {
				if (flag2 = we.getText().equals(exp[i])) {
					match = flag1 && flag2;
				}
			}
			Assert.assertTrue(match);
		}
	}

	/*-- Select any one option from Caller DropDown filter--*/
	public void selectCallerName(String callerNameValue) {
		login.waitForSpinnerToDisappear();
		login.WaitTillElementIsPresent(callerNameDrpDwn);
		login.selectFromDropDownByVisibleText(callerNameDrpDwn, callerNameValue);
	}

	/*-- Checking All TextBox filter--*/
	public void checkAllTextBoxFilter() {
		login.waitForPageLoad();
		Assert.assertTrue(checkedWebElementDisplayed(detailsTxtBx) && detailsTxtBx.getAttribute("placeholder").contains("details")
				&& checkedWebElementDisplayed(fromDateBtn)&& fromDateBtn.getAttribute("placeholder").contains("from (dd-mm-yy)")
				&& checkedWebElementDisplayed(toDateBtn)&& toDateBtn.getAttribute("placeholder").contains("to (dd-mm-yy)"));
	}

	/*-- Enter data in details filter--*/
	public void addDetails(String name) {
		login.waitForPageLoad();
		detailsTxtBx.clear();
		detailsTxtBx.sendKeys(name);
	}

	/*-- Enter date in From Date filter--*/
	public void AddFromDate(String date)
	{
		login.waitForPageLoad();
		fromDateBtn.sendKeys(date);
	}

	/*-- Enter date in To Date filter--*/
	public void AddToDate(String date)
	{
		login.waitForPageLoad();
		toDateBtn.sendKeys(date);
	}

	public void verifyDataOnMiscellaneousCallListingOnFilter(String appliedFilterValue,int appliedFilterDataColumn) {
		if(listOfPages.size()>0) {
			int totalNumberOfPages=Integer.valueOf(listOfPages.get(listOfPages.size()-1).getText());
			int currentPage=1;
			while(currentPage<=totalNumberOfPages) {
				for(WebElement callRecord:callRecordOnListing) {
					List<WebElement> callDetailsList=callRecord.findElements(By.tagName("td"));
					String actualFilterDataValue=callDetailsList.get(appliedFilterDataColumn).getText().toLowerCase();
					System.out.println("******* actual displayed value on Screen"+actualFilterDataValue);
					System.out.println("******* actual search value"+appliedFilterValue);
					Assert.assertTrue(actualFilterDataValue.contains(appliedFilterValue.toLowerCase()));
					
				}
				login.waitForElementToBeClickable(nextPageButton);
				nextPageButton.click();
				currentPage++;
			}
		}
		else {
			Assert.assertTrue(noRecordFoundMsg.isDisplayed());
		}
		
	}
	
	public void verifyDataOnCallTypeFilter(String callType) {
		verifyDataOnMiscellaneousCallListingOnFilter(callType, 3);
		
	}
	
	public void verifyDataOnCallDispositionFilter(String callDisposition) {
		verifyDataOnMiscellaneousCallListingOnFilter(callDisposition, 4);
	}
	
	public void verifyDataOnCallerNameFilter(String callerName) {
		verifyDataOnMiscellaneousCallListingOnFilter(callerName, 6);
	}
	
	public void verifyDataOnMobileNumberSearchFilter(String searchedMobileNumber) {
		verifyDataOnMiscellaneousCallListingOnFilter(searchedMobileNumber, 2);
	}
	
	public void verifyDataOnNameSearchFilter(String searchedName) {
		verifyDataOnMiscellaneousCallListingOnFilter(searchedName, 1);
	}
	
	
	public void veifyDataOnDateFilter(String fromDate, String toDate) {
		if (listOfPages.size() > 0) {
			int totalNumberOfPages = Integer.valueOf(listOfPages.get(listOfPages.size() - 1).getText());
			int currentPage = 1;
			while (currentPage <= totalNumberOfPages) {
				for (WebElement callRecord : callRecordOnListing) {
					List<WebElement> callDetailsList = callRecord.findElements(By.tagName("td"));
					String dateOnCallRecord = callDetailsList.get(0).findElement(By.xpath("div/span")).getText().trim();
					try {
						SimpleDateFormat fmt = new SimpleDateFormat("dd-MM-yy");
						Date actualDateOnCallRecord = fmt.parse(dateOnCallRecord);
						Date expectedFromDate = fmt.parse(fromDate);
						Date expectedToDate = fmt.parse(toDate);
						System.out.println("actual Date"+dateOnCallRecord);
						System.out.println("actual Date"+actualDateOnCallRecord);
						System.out.println("fromDate"+expectedFromDate);
						System.out.println("to Date"+expectedToDate);
						System.out.println("compare value with from Date "+actualDateOnCallRecord.compareTo(expectedFromDate)+"compare value with to Date "+actualDateOnCallRecord.compareTo(expectedToDate));
						if (actualDateOnCallRecord.compareTo(expectedFromDate) >= 0
								&& actualDateOnCallRecord.compareTo(expectedToDate) <= 0) {
							System.out.println("Date is between FromDate and toDate");
						} else {
							System.out.println("Date is NOT between FromDate and toDate");
							Assert.assertFalse(true);
						}
					}

					catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				login.waitForElementToBeClickable(nextPageButton);
				nextPageButton.click();
				currentPage++;
			}
		} else {
			Assert.assertTrue(noRecordFoundMsg.isDisplayed());
		}

	}
	
	public void verifyDateRangeErrorMsg() {
		login.WaitTillElementIsPresent(dateRangeMoreErrorMsg);
		Assert.assertTrue(dateRangeMoreErrorMsg.isDisplayed());
		login.waitTillInvisiblityOfElement(dateRangeMoreErrorMsg);
		
	}
	
	public void verifyFromDateExceedToDateErrorMsg() {
		login.WaitTillElementIsPresent(fromDateExceedToDateMsg);
		Assert.assertTrue(fromDateExceedToDateMsg.isDisplayed());
		
	}
	
	private boolean checkedWebElementDisplayed(WebElement ele) {
		return (ele.isDisplayed());
	}

}
