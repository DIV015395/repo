package com.prm.docdashboard.pageobject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.prm.pageobjects.utils.DatePicker;
import com.prm.pageobjects.utils.PCDriver;

public class EventListingPage {
    private PCDriver loginPage;
    DatePicker datepicker;
    AppointmentsLisitngPage clinicAppointmentsPage;
    EventPage eventPage;

    public EventListingPage ( PCDriver loginPage) {
        this.loginPage = loginPage;
        PageFactory.initElements(loginPage.getDriver(), this);
        clinicAppointmentsPage = new AppointmentsLisitngPage(loginPage);
        eventPage=new EventPage(loginPage);
        datepicker = new DatePicker(loginPage);
    }

    @FindBy(xpath = "//button[normalize-space()='Events List']")
    private WebElement eventBtn;
    @FindBy(xpath = "//input[@id='fromDate']//following-sibling::button[@class='calenderBtn']")
    private WebElement eventFromDate;
    @FindBy(xpath = "//input[@id='toDate']//following-sibling::button[@class='calenderBtn']")
    private WebElement eventToDate;
    @FindBy(xpath="//select[@id='type']")
    private WebElement selectEventType;
    @FindBy(xpath="//select[@id='category']")
    private WebElement selEventCat;
    @FindBy(xpath="//a//i[text()='Event +']")
    private WebElement addEventBtnOnEvent;
    @FindBy(xpath = "//i[text()='Search']//preceding-sibling::span")
    private WebElement searchBtnOnEvent;
    @FindBy(xpath="//span[@class='actn-icn manualUpdate']")
    private WebElement resetBtnOnEvent;
    @FindBy(xpath = "//table[@id='eventListing']//th[text()='Date-Time']")
    private WebElement date;
    @FindBy(xpath = "//table[@id='eventListing']//th[text()='Duration']")
    private WebElement duration;
    @FindBy(xpath = "//table[@id='eventListing']//th[text()='Title']")
    private WebElement title;
    @FindBy(xpath = "//table[@id='eventListing']//th[text()='Type']")
    private WebElement eventType;
    @FindBy(xpath="//table[@id='eventListing']//th[text()='Category']")
    private WebElement category;
    @FindBy(xpath = "//table[@id='eventListing']//th[text()='Doctor']")
    private WebElement doctor;
    @FindBy(xpath = "//table[@id='eventListing']//th[text()='Clinic']")
    private WebElement clinic;
    @FindBy(xpath="//table[@id='eventListing']//th[text()='Notes']")
    private WebElement notes;
    @FindBy(xpath = "//table[@id='eventListing']//th[text()='Action']")
    private WebElement action;
    @FindBy(xpath="//i[normalize-space()='No Record Found!']")
    private WebElement noRecordFoundMsg;

    @FindBy(xpath = "//span[@class='cmnicons cncl-mdl']")
    private WebElement cancelBtnDelete;
    @FindBy(xpath = "//span[@class='cmnicons yes-mdl']")
    private WebElement yesBtnDelete;
    @FindBy(xpath = "//table[@id='eventListing']/tbody/tr/td[1]")
    private List<WebElement> timeSlotApp;
    @FindBy(xpath = "//span[text()='From date cannot be greater than to date!']")
    private WebElement msgFrmDateLater;
    @FindBy(xpath = "//div[@class='clearfix topPagination pagingUI']//a[@class='next']")
    private WebElement nextBtn;
    

    public void selectFrmdateInEvent(String SelectDate) {
        String fromDateElementId ="fromDate";
        loginPage.WaitTillElementIsPresent(eventFromDate);
        loginPage.waitForSpinnerToDisappear();
        datepicker.appointmentDate(SelectDate, eventFromDate, fromDateElementId );
    }

    public void selectTodateInEvent(String SelectDate) {
        String toDateElementId ="toDate";
        loginPage.WaitTillElementIsPresent(eventToDate);
        loginPage.waitForSpinnerToDisappear();
        datepicker.appointmentDate(SelectDate, eventToDate, toDateElementId );
    }

    public void selectType(String eventType) {
        loginPage.waitForElementToBeClickable(selectEventType);
        loginPage.selectFromDropDownByVisibleText(selectEventType, eventType);
    }

    public void verifyOptionInEventTypeFilter() {
        loginPage.waitForElementToBeClickable(selectEventType);
        Select eventTypeOption = new Select(selectEventType);
        List<WebElement> eventTypeList = eventTypeOption.getOptions();
        for (WebElement eventType : eventTypeList) {
            if (eventType.getText().equals("Select Type") || eventType.getText().equals("By Doctor") || eventType.getText().equals("By Operatory")
                    || eventType.getText().equals("By Room") || eventType.getText().equals("By Scanner")) {
            } else {
                Assert.assertTrue(false);
                break;
            }
        }

    }
    public void selectCategroy(String categoryName) {
        loginPage.waitForElementToBeClickable(selEventCat);
        loginPage.selectFromDropDownByVisibleText(selEventCat, categoryName);
    }

    public void clickOnSearch() {
        loginPage.waitForElementToBeClickable(searchBtnOnEvent);
        searchBtnOnEvent.click();
        loginPage.waitForSpinnerToDisappear();
    }

    public void clickOnEventBtn() {
        loginPage.waitForElementToBeClickable(eventBtn);
        loginPage.waitForSpinnerToDisappear();
        eventBtn.click();
        loginPage.waitForPageLoad();
        loginPage.waitForSpinnerToDisappear();
    }

    public void clickOnAddEventBtnOnEventListing() {
        loginPage.waitForElementToBeClickable(addEventBtnOnEvent);
        loginPage.executeScript(addEventBtnOnEvent);
        loginPage.waitForPageLoad();
    }

    public void clickonResetBtn() {
        loginPage.waitForElementToBeClickable(resetBtnOnEvent);
        resetBtnOnEvent.click ();
//		loginPage.executeScript(resetBtnOnEvent);
        loginPage.waitForPageLoad();
    }

    public void selectDoctor(String doctor) {
        loginPage.waitForPageLoad();
        loginPage.waitForSpinnerToDisappear();
        WebElement ele=loginPage.getDriver().findElement(By.xpath("//span[@class='glyphicon glyphicon-chevron-down customComboBtn cursor-pointer']"));
        ele.click();
        WebElement element=loginPage.getDriver().findElement(By.xpath("//ul[@class='menu fnt_cali']/li[text()='"+doctor+"']"));
        loginPage.waitForElementToBeClickable(element);
        element.click();
        loginPage.waitForPageLoad();
        loginPage.waitForSpinnerToDisappear();
    }

    public void verifyTitleDisplayOnEventlist(String titleOnEvent) {
        loginPage.waitForPageLoad();
        WebElement title = loginPage.getDriver().findElement(By.xpath("//td[contains(text(),'"+titleOnEvent+"')]"));
        loginPage.WaitTillElementIsPresent(title);
        boolean flag = title.isDisplayed();
        if (flag) {
            Assert.assertTrue(title.getText().trim().contains(titleOnEvent));
        } else {
            Assert.assertTrue(flag);
        }
    }

    /*
     * New method added
     */
    public void verifyDateTimeDisplayOnEventList(String eventTitle,String expectedDateTimeOnEventListing ) {
        loginPage.waitForPageLoad();
        loginPage.waitForSpinnerToDisappear();
        String actualDateTimeOfEvent =loginPage.getDriver().findElement(By.xpath("//tr[@data-ng-repeat='record in eventListData']//td[contains(normalize-space(),'"+eventTitle+"')]/preceding-sibling::td[2]")).getText ().trim ();
        Assert.assertTrue (actualDateTimeOfEvent.equals ( expectedDateTimeOnEventListing ));

    }

    public void verifyDurationDisplayOnEventList(String eventTitle,String expectedDurationOfEvent) {
        loginPage.waitForPageLoad();
        loginPage.waitForSpinnerToDisappear();
        String actualDurationOfEvent =loginPage.getDriver().findElement(By.xpath("//tr[@data-ng-repeat='record in eventListData']//td[contains(normalize-space(),'"+eventTitle+"')]/preceding-sibling::td[1]")).getText ().trim ();
        Assert.assertTrue (actualDurationOfEvent.contains ( expectedDurationOfEvent ));

    }

    public void verifyEventTypeDisplayOnEventList(String eventTitle,String expectedEventType ) {
        loginPage.waitForPageLoad();
        String actualTypeOfEvent=loginPage.getDriver().findElement(By.xpath("//tr[@data-ng-repeat='record in eventListData']//td[contains(normalize-space(),'"+eventTitle+"')]/following-sibling::td[1]")).getText ().trim ();
        Assert.assertTrue (actualTypeOfEvent.equalsIgnoreCase ( expectedEventType ));
    }

    public void verifyCategoryDisplayOnEventList(String eventTitle,String expectedCategoryOfEvent ) {
        loginPage.waitForPageLoad();
        String actualCategoryOfEvent =loginPage.getDriver().findElement(By.xpath("//tr[@data-ng-repeat='record in eventListData']//td[contains(normalize-space(),'"+eventTitle+"')]/following-sibling::td[2]")).getText ().trim ();
        Assert.assertTrue ( actualCategoryOfEvent.equals ( expectedCategoryOfEvent ) );

    }




    public void verifyDoctorDisplayOnEventList(String eventTitle,String expectedEventDoctor ) {
        loginPage.waitForPageLoad();
        String actualEventDoctor =loginPage.getDriver().findElement(By.xpath("//tr[@data-ng-repeat='record in eventListData']//td[contains(normalize-space(),'"+eventTitle+"')]/following-sibling::td[3]")).getText ();
        Assert.assertTrue (actualEventDoctor.equalsIgnoreCase ( expectedEventDoctor ));
    }

    public void verifyClinicDisplayOnEventList( String eventTitle, String expectedEventClinic ) {
        loginPage.waitForPageLoad();
        String actualEventClinic=loginPage.getDriver().findElement(By.xpath("//tr[@data-ng-repeat='record in eventListData']//td[contains(normalize-space(),'"+ eventTitle +"')]/following-sibling::td[4]")).getText ().trim ();
        Assert.assertTrue (actualEventClinic.equals ( expectedEventClinic ));
    }


    public void verifyNotesDisplayOnEventList(String eventTitle,String expectedEventNotes) {
        loginPage.waitForPageLoad();
        String actualEventNotes=loginPage.getDriver().findElement(By.xpath("//tr[@data-ng-repeat='record in eventListData']//td[contains(normalize-space(),'"+eventTitle+"')]/following-sibling::td[5]")).getText ().trim ();
        Assert.assertTrue (actualEventNotes.contains ( expectedEventNotes ));
    }

    public void verifyAllMandatoryWebElement() {
        loginPage.waitForPageLoad();
        boolean isAllMandatoryFieldDisplayed = eventFromDate.isDisplayed() && eventToDate.isDisplayed() && selectEventType.isDisplayed()
                && selEventCat.isDisplayed() && searchBtnOnEvent.isDisplayed() && resetBtnOnEvent.isDisplayed()
                && clinicAppointmentsPage.docDropDoownBtn.isDisplayed() && addEventBtnOnEvent.isDisplayed();
        Assert.assertTrue ( isAllMandatoryFieldDisplayed );
    }

    public void verifyEditAndDeletBtn(String eventTitle) {
        loginPage.waitForPageLoad();
        loginPage.waitForSpinnerToDisappear();
        WebElement editBtn=loginPage.getDriver().findElement(By.xpath("//td[normalize-space()='" + eventTitle + "']/following-sibling::td//span[@class='actn-icn edit']"));
        WebElement deleteBtn=loginPage.getDriver().findElement(By.xpath("//td[normalize-space()='" + eventTitle + "']/following-sibling::td//span[@class='actn-icn delete']"));
        loginPage.WaitTillElementIsPresent(deleteBtn);
        boolean isEditAndDeleteBtnDisplayed=editBtn.isDisplayed() && deleteBtn.isDisplayed();
        Assert.assertTrue ( isEditAndDeleteBtnDisplayed );
    }

    public void clickOnEditBtn ( String eventTitle) {
        loginPage.waitForSpinnerToDisappear();
        WebElement editBtn=loginPage.getDriver().findElement(By.xpath("//td[normalize-space()='"+ eventTitle + "']/following-sibling::td//span[@class='actn-icn edit']"));
        loginPage.waitForElementToBeClickable(editBtn);
        editBtn.click();
        loginPage.waitForPageLoad();
        loginPage.waitForSpinnerToDisappear();
    }

    public void clickOnDeleteBtn(String eventTitle) {
        WebElement deleteBtn=loginPage.getDriver().findElement(By.xpath("//td[normalize-space()='" + eventTitle + "']/following-sibling::td//span[@class='actn-icn delete']"));
        loginPage.waitForElementToBeClickable(deleteBtn);
        deleteBtn.click();
    }

    public void clickOnNoBtnDeletePopUp() {
        loginPage.waitForElementToBeClickable(cancelBtnDelete);
        cancelBtnDelete.click();
    }

    public void clickOnYesBtnDeletePopUp() {
        loginPage.waitForElementToBeClickable(yesBtnDelete);
        yesBtnDelete.click();
    }

    public void verifyAfterDelete(String eventTitle) {
        loginPage.waitForPageLoad();
        loginPage.waitForSpinnerToDisappear();
        WebElement title = loginPage.getDriver().findElement(By.xpath("//td[normalize-space()='"+eventTitle+"']/parent::tr"));
        loginPage.WaitTillElementIsPresent(title);
        boolean isDeletedRecordStrikeOff = title.getAttribute("class").contains("deleted");
        Assert.assertTrue ( isDeletedRecordStrikeOff );
    }

	public void dateFilter(String frmDate, String toDate) {
		SimpleDateFormat dt = new SimpleDateFormat("MM/dd/yy");
		try {
			Date Date1 = dt.parse(frmDate);
			Date Date2 = dt.parse(toDate);
			loginPage.waitForPageLoad();
			loginPage.waitForSpinnerToDisappear();

			List<WebElement> pageNumbers = loginPage.getDriver().findElements(By.xpath(
					"//div[contains(@class,'topPagination')][1]//pagination2[@data-page-no='eventCurrentPageNo']/following-sibling::div/a[contains(@class,'pageNo')]"));
			if (pageNumbers.size() > 0) {
				int lastPageNumber = Integer.valueOf(pageNumbers.get(pageNumbers.size() - 1).getText());
				int currentPage = 1;
				while (currentPage <= lastPageNumber) {
					for (int i = 0; i < timeSlotApp.size(); i++) {
						boolean flag;
						if (flag = timeSlotApp.get(i).isDisplayed()) {
							String eventTime = timeSlotApp.get(i).getText();
							SimpleDateFormat dt1 = new SimpleDateFormat("dd-MM-yy");
							Date eventDate = dt1.parse(eventTime);
							boolean samedate = DateUtils.isSameDay(Date1, eventDate)
									|| DateUtils.isSameDay(Date2, eventDate);
							boolean counter = eventDate.before(Date2) && eventDate.after(Date1);
							if (counter || samedate) {
							} else {
								Assert.assertTrue(counter);
							}
						} else {
							Assert.assertTrue(flag);
						}
					}
					nextBtn.click();
					loginPage.waitForPageLoad();
					currentPage++;
				}
			} 
			else {
				Assert.assertTrue(noRecordFoundMsg.isDisplayed());
			}
		} 
		catch (ParseException e) {
			e.printStackTrace();
		}
	}

    public void checkFrmDateIsNotLaterToToDate() {
        loginPage.waitForElementToBeClickable(msgFrmDateLater);
        Assert.assertTrue(msgFrmDateLater.isDisplayed());
        Assert.assertTrue(msgFrmDateLater.getText().contains("From date cannot be greater than to date!"));
    }

    public void verifyFilterDoctorInEventList(String doctorNickName) {
        loginPage.waitForPageLoad();
        loginPage.waitForSpinnerToDisappear();
        List<WebElement> pageNumbers=loginPage.getDriver().findElements(By.xpath("//div[contains(@class,'topPagination')][1]//pagination2[@data-page-no='eventCurrentPageNo']/following-sibling::div/a[contains(@class,'pageNo')]"));
        int lastPageNumber=Integer.valueOf(pageNumbers.get(pageNumbers.size()-1).getText());
        int currentPage=1;
        while (currentPage<=lastPageNumber) {
            loginPage.waitForPageLoad();
            loginPage.waitForElementToBeClickable(nextBtn);
            List<WebElement> allDoctor = loginPage.getDriver()
                    .findElements(By.xpath("//table[@id='eventListing']/tbody/tr/td[6]"));
            int allDoctorSize = allDoctor.size();
            List<WebElement> filterDoctor = loginPage.getDriver().findElements(By.xpath(
                    "//table[@id='eventListing']/tbody/tr/td[normalize-space()='"+doctorNickName+"']"));
            List<WebElement> allDoctorFilter = loginPage.getDriver().findElements(
                    By.xpath("//table[@id='eventListing']/tbody/tr/td[normalize-space()='All Doctors']"));
            int total = filterDoctor.size() + allDoctorFilter.size();
            boolean flag = (allDoctorSize == total);
            if (flag) {
                loginPage.waitForElementToBeClickable(nextBtn);
                nextBtn.click();
            } else {
                Assert.assertTrue(flag);
            }
            currentPage++;

        }
    }

    public void verifyFilterEventTypeInEventList(String eventType) {
        loginPage.waitForPageLoad();
        List<WebElement> pageNumbers = loginPage.getDriver().findElements(By.xpath(
                "//div[contains(@class,'topPagination')][1]//pagination2[@data-page-no='eventCurrentPageNo']/following-sibling::div/a[contains(@class,'pageNo')]"));
        int lastPageNumber = Integer.valueOf(pageNumbers.get(pageNumbers.size() - 1).getText());
        int currentPage = 1;
        while (currentPage <= lastPageNumber) {
            loginPage.waitForPageLoad();
            List<WebElement> eventTypeList = loginPage.getDriver().findElements(By.xpath("//table[@id='eventListing']/tbody/tr/td[4]"));
            for (WebElement type : eventTypeList) {
                Assert.assertTrue ( type.getText().equals(eventType) );

            }
            loginPage.waitForElementToBeClickable(nextBtn);
            nextBtn.click();
            currentPage++;

        }


    }
    public void verifyFilterCategoryInEventList(String filterCategory) {
        loginPage.waitForPageLoad();
        List<WebElement> pageNumbers=loginPage.getDriver().findElements(By.xpath("//div[contains(@class,'topPagination')][1]//pagination2[@data-page-no='eventCurrentPageNo']/following-sibling::div/a[contains(@class,'pageNo')]"));
        int lastPageNumber=Integer.valueOf(pageNumbers.get(pageNumbers.size()-1).getText());
        int currentPage=1;
        while (currentPage<=lastPageNumber) {
            loginPage.waitForPageLoad();
            List<WebElement> eventCategoryList=loginPage.getDriver().findElements(By.xpath("//table[@id='eventListing']/tbody/tr/td[5]"));
            for(WebElement eventCategory:eventCategoryList) {
                Assert.assertTrue (eventCategory.getText().equals(filterCategory));

            }
            loginPage.waitForElementToBeClickable(nextBtn);
            nextBtn.click();
            currentPage++;

        }
    }

    public void verifyResetBtnWorking() {
        loginPage.waitForPageLoad();
        clickonResetBtn();
        loginPage.waitForSpinnerToDisappear();
        boolean flag=selectEventType.getText().contains("Select Type") && selEventCat.getText().contains("Select Category");
        boolean isFromDateBlankAfterReset=getSelectedFromDateOnEventListing ().equals ( "" );
        boolean isToDateBlankAfterReset=getSelectedToDateOnEventListing ().equals ( "" );
        boolean isAllFilterValueReset=flag && isFromDateBlankAfterReset && isToDateBlankAfterReset;
        Assert.assertTrue ( isAllFilterValueReset );
    }


    public String getSelectedFromDateOnEventListing(){
        loginPage.waitForElementToBeClickable ( eventFromDate );
        JavascriptExecutor javascriptExecutor=(JavascriptExecutor) loginPage.getDriver ();
        String fromDate=(String)javascriptExecutor.executeScript ( "return document.getElementById('fromDate').value;" );
        return  fromDate;

    }

    public String getSelectedToDateOnEventListing(){
        loginPage.waitForElementToBeClickable ( eventToDate );
        JavascriptExecutor javascriptExecutor=(JavascriptExecutor) loginPage.getDriver ();
        String toDate=(String)javascriptExecutor.executeScript ( "return document.getElementById('toDate').value;" );
        return  toDate;
    }

}
