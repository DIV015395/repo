package com.prm.docdashboard.pageobject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.prm.pageobjects.utils.DatePicker;	
import com.prm.pageobjects.utils.PCDriver;

public class EventPage {
	
	private PCDriver loginPage;
	private DatePicker datepicker;
	AppointmentAddPage appointmentPage;

	public EventPage(PCDriver login) {
		this.loginPage = login;
		PageFactory.initElements(login.getDriver(), this);
		datepicker=new DatePicker(loginPage);
		appointmentPage=new AppointmentAddPage(loginPage);
		
	}
	
	/*
	 * WebElement on Event Add Page
	 */
	@FindBy(xpath="//div[@class='modal overlay']")
	private WebElement loader;
	@FindBy(xpath="//li[@id='addEvent']/a[text()='Add Event']")
	private WebElement eventFormTab;
	@FindBy(xpath="//input[@value='doctor']")
	private WebElement eventByDoctor;
	@FindBy(xpath="//label[text()='By Operatory']")
	private WebElement eventByOperatory;
	@FindBy(xpath="//label[text()='By Room']")
	private WebElement eventByRoom;
	@FindBy(xpath="//label[@for='apptType-byscanner']")
	private WebElement eventByScanner;
	@FindBy(xpath="//label[text()='Title ']//following-sibling::input[@id='title']")
	private WebElement title;
	@FindBy(xpath="//label[text()='Clinic ']/following::custom-combo[@id='clinic']//select")
	private WebElement clinicInEvent;
	@FindBy(xpath="//select[@id='doctor']")
	private WebElement doctorEvent;
	@FindBy(xpath="//label[text()='Category ']/following::custom-combo[@id='category']//select")
	private WebElement eventCategory;
	@FindBy(xpath="//label[text()='Date ']/following-sibling::input[@id='eventDate']")
	private WebElement eventDate;
	@FindBy(xpath="//select[@id='operatory']")
	private WebElement operatoryInEvent;
	@FindBy(xpath="//select[@id='room']")
	private WebElement roomInEvent;
	@FindBy(xpath="//select[@id='scanner']")
	private WebElement scannerInEvent;
	@FindBy(xpath="//label[text()='Notes']//following::textarea[@id='eventNotes']")
	private WebElement notesText;
	@FindBy(xpath="//div[@class='error ng-binding ng-scope']")
	private List<WebElement> eventErrMsg;
	@FindBy(xpath="//a[@data-ng-click='resetEvent(addAppointment.eventForm)']//span[@class='cmnicons rst']")
	private WebElement eventResetBtn;
	@FindBy(xpath="//div//ul[@data-spy='affix']//span[@class='cmnicons aprsv']")
	private WebElement eventSaveBtn;
	@FindBy(xpath="//ul[@data-spy='affix']//i[text()='Cancel']")
	private WebElement cancelBtn;
	@FindBy(className = "//span[normalize-space()='Event added successfully!']")
	private WebElement eventAddSuccessMsg;
	@FindBy(className = "//span[normalize-space()='Event updated successfully!']")
	private WebElement eventUpdateSuccessMsg;
	@FindBy(className = "//span[normalize-space()='Event deleted successfully!']")
	private WebElement eventDeleteSuccessMsg;
	@FindBy(xpath="//label[normalize-space()='Full Day Event']")
	private WebElement fulldayBtn;
	@FindBy(xpath="//input[@id='hours']")
	private WebElement hours;
	@FindBy(xpath="//input[@id='minutes']")
	private WebElement minutes;
	@FindBy(xpath="//select[@id='ampm']")
	private WebElement amPm;
	@FindBy(xpath="//div[@class='eventTyp']//input[@id='duration']")
	private WebElement eventDuration;
	@FindBy(xpath = "//div[@role='alert']")
	private WebElement alreadyAppMsg;
	@FindBy(xpath="//div[contains(text(),'Enter event duration')]")
	private WebElement invalidDuratinMsg;
	@FindBy(xpath="//div[contains(text(),'Enter event title')]")
    private WebElement tittleErrorMsg;
	@FindBy(xpath="//div[contains(text(),'Select category')]")
	private WebElement selectCategoryErrorMsg;
	@FindBy(xpath="//div[contains(text(),'Select doctor')]")
	private WebElement selectDoctorErrorMsg;
	@FindBy(xpath="//div[contains(text(),'Select operatory')]")
	private WebElement selectOperatoryErrorMsg;
	@FindBy(xpath="//div[contains(text(),'Select room')]")
	private WebElement selectRoomErrorMsg;
	@FindBy(xpath="//div[contains(text(),'Select scanner')]")
	private WebElement selectScannerErrorMsg;
	@FindBy(xpath="//div[text()='Enter event duration']")
	private WebElement eventDurationErrorMsg;
	@FindBy(xpath="//div[text()='Time slot selected for event has already passed!']")
	private WebElement timeAlreadyPassErrorMsg;
	@FindBy(xpath="//div[text()='Max limit reached for event booking by operatory for the time range!']")
	private WebElement lastOperatoryAvailableErrorMsg;
	@FindBy(xpath="//div[text()='Max limit reached for event booking by scanner for the time range!']")
	private WebElement lastScannerAvailableErrorMsg;
	@FindBy(xpath="//div[text()='Time slot selected for event is already booked!']")
	private WebElement eventAlreadyBookedForSlotErrorMsg;
	@FindBy(xpath="//div[normalize-space()='Enter Minutes between 0 - 59']")
	private WebElement invalidMinutesErrorMsg;
	@FindBy(xpath="//div[normalize-space()='Enter Hours between 1 - 12']")
	private WebElement invalidHoursErrorMsg;
	@FindBy(xpath="//div[text()='Event duration cannot be zero !']")
	private WebElement eventDurationCannotBeZeroErrorMsg;
	@FindBy(xpath="//div[text()='Duration can only be remaining minutes available for the day!']")
	private WebElement durationMoreThanAvailableMinInDayErrorMsg;
	
	public void clickOnEvent() {
		loginPage.waitForElementToBeClickable(eventFormTab);
		loginPage.waitForSpinnerToDisappear ();
		eventFormTab.click();	
		loginPage.waitForSpinnerToDisappear ();
	}
	
	
	public void selectByOperatoryEvent() {
		loginPage.WaitTillElementIsPresent ( eventByOperatory );
		loginPage.waitForElementToBeClickable(eventByOperatory);
		eventByOperatory.click();
			
	}
	
	public void selectByRoomEvent() {
		loginPage.WaitTillElementIsPresent ( eventByRoom );
		loginPage.waitForElementToBeClickable(eventByRoom);
		eventByRoom.click();
	}
	
	public void selectByScannerEvent() {
	loginPage.waitForElementToBeClickable(eventByScanner);
	eventByScanner.click();
		
	}
	
	
	public void clickOnEventSaveBtn() {
		loginPage.waitForElementToBeClickable(eventSaveBtn);
		eventSaveBtn.click();
		loginPage.waitForPageLoad();		
//		
	}

	public void verifyEventAddSuccessMsg(){
		loginPage.WaitTillElementIsPresent ( eventAddSuccessMsg );
		loginPage.softAssert ().assertTrue ( eventAddSuccessMsg.isDisplayed () );

	}
	public void verifyEventUpdatedSuccessMsg(){
		loginPage.WaitTillElementIsPresent ( eventUpdateSuccessMsg );
		loginPage.softAssert ().assertTrue ( eventUpdateSuccessMsg.isDisplayed () );
	}

	public void verifyEventDeleteSuccessMsg(){
		loginPage.WaitTillElementIsPresent ( eventDeleteSuccessMsg );
		loginPage.softAssert ().assertTrue ( eventDeleteSuccessMsg.isDisplayed () );

	}
	
	public void clickOnEventResetBtn() {
		loginPage.waitForElementToBeClickable(eventResetBtn);
		loginPage.waitForSpinnerToDisappear ();
		eventResetBtn.click();
		loginPage.waitForSpinnerToDisappear ();
		
	}
	
	/*
	Verifying By Default By doctor Event Type is selected
	 */
	public void verifyDefaultSelectedEventType() {
		loginPage.waitForElementToBeClickable(eventByDoctor);
		Assert.assertTrue (eventByDoctor.isSelected());

	}

	public void enterTitleName(String titleName) {
		loginPage.waitForElementToBeClickable (title);
		title.clear ();
		title.sendKeys(titleName);
	}

	public void selectClinicInEvent(String clinic) {
		loginPage.waitForElementToBeClickable(clinicInEvent);
		loginPage.waitForSpinnerToDisappear();
		loginPage.selectFromDropDownByVisibleText(clinicInEvent, clinic);

	}

	public void selectDoctorInEvent(String doctor) {
		loginPage.waitForElementToBeClickable( doctorEvent );
		loginPage.selectFromDropDownByVisibleText( doctorEvent, doctor.trim());
	}

	public void selectEventCategory(String category) {
		loginPage.waitForElementToBeClickable(eventCategory);
		loginPage.selectFromDropDownByVisibleText(eventCategory, category);
	}

	/*
	Select Date on Event Add/Edit Screen
	 */
	public void selectDateInEvent(String SelectDate) {
		String element_id ="eventDate";
		loginPage.waitForPageLoad();
		loginPage.WaitTillElementIsPresent( eventDate );
		datepicker.appointmentDate(SelectDate, eventDate, element_id );
	}

	
	public void selectOperatory(String operatory) {
		loginPage.waitForElementToBeClickable(operatoryInEvent);
		loginPage.selectFromDropDownByVisibleText(operatoryInEvent, operatory);
	}
	
	public void selectRoom(String room) {
		loginPage.waitForElementToBeClickable(roomInEvent);
		loginPage.selectFromDropDownByVisibleText(roomInEvent, room);
	}
	
	public void selectScanner(String scanner) {
		loginPage.waitForElementToBeClickable(scannerInEvent);
		loginPage.selectFromDropDownByVisibleText(scannerInEvent,scanner);
		
		
	}
	
	public void enterNotes(String notes) {
		loginPage.WaitTillElementIsPresent(notesText);
		notesText.sendKeys(notes);
	}

	public void clickOnFullDayCheckBox () {
		loginPage.waitForElementToBeClickable(fulldayBtn);
		fulldayBtn.click();
	}

	public void enterHours(String hour) {
		loginPage.waitForElementToBeClickable ( hours );
		hours.clear ();
		hours.sendKeys(hour);
	}

	public void enterMinutes(String eventMinutes ) {
		loginPage.waitForElementToBeClickable ( minutes );
		minutes.clear ();
		minutes.sendKeys( eventMinutes );
	}

	public void selectAmPm(String AmPm) {
		loginPage.WaitTillElementIsPresent(amPm);
		loginPage.selectFromDropDownByVisibleText(amPm, AmPm);
	}

	public void enterEventDuration(String duration) {
		loginPage.waitForElementToBeClickable ( eventDuration );
		eventDuration.clear ();
		eventDuration.sendKeys(duration);
	}
	
	public boolean verifyEventTitleTextFieldAndPlaceholder() {
		loginPage.WaitTillElementIsPresent(title);
		return title.isDisplayed() && title.getAttribute("placeholder").equals("» title");

	}
	
	public boolean verifyDurationHoursFieldAndPlaceholder() {
	    loginPage.WaitTillElementIsPresent(hours);
		return hours.isDisplayed() && hours.getAttribute("placeholder").equals("» HH");
	}

	public boolean verifyDurationMinuteFieldAndPlaceholder() {
		loginPage.WaitTillElementIsPresent( minutes );
		return minutes.isDisplayed() && minutes.getAttribute("placeholder").equals("» MM");

	}
	
	public boolean verifyEventDurationInMinsFieldAndPlaceholder() {
		loginPage.WaitTillElementIsPresent( eventDuration );
		return eventDuration.isDisplayed() && eventDuration.getAttribute("placeholder").equals("» Duration in Mins");

	}

	/*
	Check all the Fields ,dropdown shown on Add Event Screen
	 */
	private boolean verifyAllCommonWebElementOnAddEvent() {
		loginPage.waitForPageLoad();
		boolean flag = eventByDoctor.isDisplayed() && verifyEventTitleTextFieldAndPlaceholder() && clinicInEvent.isDisplayed()
				&& eventCategory.isDisplayed() && eventDate.isDisplayed() && verifyDurationHoursFieldAndPlaceholder()
				&& verifyDurationMinuteFieldAndPlaceholder() && amPm.isDisplayed() && verifyEventDurationInMinsFieldAndPlaceholder()
				&& fulldayBtn.isDisplayed() && notesText.isDisplayed() && eventSaveBtn.isDisplayed()
				&& eventResetBtn.isDisplayed() && cancelBtn.isDisplayed();
		return flag;

	}
	
	public void verifyAllMandatoryWebElementOnByDoctorEvent() {
		loginPage.waitForPageLoad();
		boolean isAllMandatoryFieldDisplayed = doctorEvent.isDisplayed() && verifyAllCommonWebElementOnAddEvent();
		Assert.assertTrue ( isAllMandatoryFieldDisplayed );
	}
	
	public void verifyAllMandatoryWebElementOnByOperatoryEvent() {
		selectByOperatoryEvent();
		loginPage.WaitTillElementIsPresent(operatoryInEvent);
		boolean isAllMandatoryFieldDisplayed=eventByOperatory.isDisplayed() && operatoryInEvent.isDisplayed() && verifyAllCommonWebElementOnAddEvent();
		Assert.assertTrue ( isAllMandatoryFieldDisplayed );
	}
	
	
	public void verifyAllMandatoryWebElementOnByRoomEvent() {
		selectByRoomEvent();
		loginPage.WaitTillElementIsPresent(roomInEvent);
		boolean isAllMandatoryFieldDisplayed=eventByRoom.isDisplayed() &&  roomInEvent.isDisplayed() && verifyAllCommonWebElementOnAddEvent();
		Assert.assertTrue ( isAllMandatoryFieldDisplayed );
	}
	
	public void verifyAllMandatoryWebElementOnByScannerEvent() {
		selectByScannerEvent();
		loginPage.WaitTillElementIsPresent(scannerInEvent);
		boolean isAllMandatoryFieldDisplayed= eventByScanner.isDisplayed() && scannerInEvent.isDisplayed() && verifyAllCommonWebElementOnAddEvent();
		Assert.assertTrue ( isAllMandatoryFieldDisplayed );
	}
	
	/*
	Check Default selected clinic in Clinic dropdown on Add Event Screen
	 */
	public void verifyDefaultSelectedClinic(String clinic) {
		loginPage.WaitTillElementIsPresent(clinicInEvent);
		WebElement byDefaultClinic = loginPage.getDriver().findElement(By.xpath("//option[@selected='selected']/../option[text()='" + clinic + "']"));
		boolean isDefaultSelectedClinicCorrect=byDefaultClinic.isSelected();
		Assert.assertTrue ( isDefaultSelectedClinicCorrect );
	}
	
	public void verifyDefaultSelectedOptionInDoctorDrpDwn() {
		loginPage.WaitTillElementIsPresent( doctorEvent );
		String defaultOptionSelectedInDoctorDrpDwn=loginPage.firstSelectedOption( doctorEvent );
		if (defaultOptionSelectedInDoctorDrpDwn.equals("Select Doctor")) {
			Assert.assertTrue(true);
		} else {
			Assert.assertTrue(false);
		}
	}

	public void verifyDefaultSelectedOptionInOperatoryDrpDwn() {
		String defaultOptionSelectedInOperatoryDrpDwn=loginPage.firstSelectedOption(operatoryInEvent);
		if (defaultOptionSelectedInOperatoryDrpDwn.equals("Select Operatory")) {
			Assert.assertTrue(true);
		} else {
			Assert.assertTrue(false);
		}
	}
	
	public void verifyDefaultSelectedOptionInRoomDrpDwn() {
		String defaultOptionSelectedInRoomDrpDwn=loginPage.firstSelectedOption(roomInEvent);
		if (defaultOptionSelectedInRoomDrpDwn.equals("Select Room")) {

			Assert.assertTrue(true);
		} else {
			Assert.assertTrue(false);
		}
	}
	
	public void verifyDefaultSelectedOptionInScannerDrpDown() {
		loginPage.WaitTillElementIsPresent ( scannerInEvent );
		String defaultOptionSelectedInScannerDrpdwn=loginPage.firstSelectedOption(scannerInEvent);
		boolean isDefaultSelectedScannerCorrect=defaultOptionSelectedInScannerDrpdwn.equals("Select Scanner");
		Assert.assertTrue(isDefaultSelectedScannerCorrect);

	}
	
	public void verifyDefaultSelectedOptionInCategoryDrpDwn() {
		loginPage.WaitTillElementIsPresent(eventCategory);
		String defaultOptionSelectedInCategoryDrpDwn = loginPage.firstSelectedOption(eventCategory);
		boolean isDefaultSelectedCategoryCorrect = defaultOptionSelectedInCategoryDrpDwn.equals("Select Category");
		Assert.assertTrue ( isDefaultSelectedCategoryCorrect );

	}

	public void behaviourOfEventType() {
		loginPage.WaitTillElementIsPresent(fulldayBtn);
		if (fulldayBtn.isDisplayed() && hours.isDisplayed() && minutes.isDisplayed() && amPm.isDisplayed()
				&& eventDuration.isDisplayed()) {
			loginPage.WaitTillElementIsPresent(fulldayBtn);
			loginPage.executeScript(fulldayBtn);
			boolean flag = hours.isEnabled() && minutes.isEnabled() && amPm.isEnabled()
					&& eventDuration.isEnabled();
			Assert.assertFalse(flag);
		}
		else{
			Assert.assertTrue ( false );
		}
	}
	
	
	public void enterTittleErrorMsg() {
		loginPage.WaitTillElementIsPresent(tittleErrorMsg);
		Assert.assertTrue(tittleErrorMsg.isDisplayed());
		
	}
	
	public void selectCategoryErrorMsg() {
		loginPage.WaitTillElementIsPresent(selectCategoryErrorMsg);
        Assert.assertTrue(selectCategoryErrorMsg.isDisplayed());		
	}


	public void eventDurationErrorMsg() {
		loginPage.WaitTillElementIsPresent(eventDurationErrorMsg);
        Assert.assertTrue(eventDurationErrorMsg.isDisplayed());
		
	}
	public void selectDoctorErrorMsg() {
		loginPage.WaitTillElementIsPresent(selectDoctorErrorMsg);
        Assert.assertTrue(selectDoctorErrorMsg.isDisplayed());
	}
	
	public void selectOperatoryErrorMsg() {
		selectByOperatoryEvent();
		loginPage.WaitTillElementIsPresent(selectOperatoryErrorMsg);
        Assert.assertTrue(selectOperatoryErrorMsg.isDisplayed());
	}
	
	public void selectRoomErrorMsg() {
		selectByRoomEvent();
		loginPage.WaitTillElementIsPresent(selectRoomErrorMsg);
        Assert.assertTrue(selectRoomErrorMsg.isDisplayed());
	}
	
	public void selectScannerErrorMsg() {
		selectByScannerEvent();
		loginPage.WaitTillElementIsPresent(selectScannerErrorMsg);
		Assert.assertTrue(selectScannerErrorMsg.isDisplayed());
		
	}

	/*
	Checking the Error Msg shown when event is booked for last available operatory in clinic for the date/time.
	 */
   public void verifyErrorOnAddingEventOnLastAvailableOperatory() {
	   loginPage.waitForElementToBeClickable(lastOperatoryAvailableErrorMsg);
	   Assert.assertTrue(lastOperatoryAvailableErrorMsg.isDisplayed());
	   
   }

	/*
     Checking the Error Msg shown when event is booked for last available scanner in clinic for the date/time.
      */
   public void verifyErrorOnAddingEventOnLastAvailableScanner() {
	   loginPage.waitForElementToBeClickable(lastScannerAvailableErrorMsg);
	   Assert.assertTrue(lastScannerAvailableErrorMsg.isDisplayed());
	   
   }
   

	public void verifyEventAlreadyBookedForSlotErrorMsg() {
		loginPage.waitForElementToBeClickable(eventAlreadyBookedForSlotErrorMsg);
		Assert.assertTrue(eventAlreadyBookedForSlotErrorMsg.isDisplayed());

	}

   public void invalidDataInDuration() {
		loginPage.WaitTillElementIsPresent(invalidDuratinMsg);
		Assert.assertTrue(invalidDuratinMsg.isDisplayed());
	}
   
   public boolean verifyInvalidHoursErrorMsg() {
	   
	   try {
		   loginPage.WaitTillElementIsPresent(invalidHoursErrorMsg);
		   return invalidHoursErrorMsg.isDisplayed();
	   }
	   
	   catch(NoSuchElementException noSuchElementException){
		   return false;
	   }
	   
   }
   
   public boolean verifyInvalidMintuesErrorMsg() {	   
	   try {
		   loginPage.WaitTillElementIsPresent( invalidMinutesErrorMsg );
		   return invalidMinutesErrorMsg.isDisplayed();
	   }
	   
	   catch(NoSuchElementException noSuchElementException){
		   return false;
	   }  
	   
   }
   
   public void verifyDurationCannotBeZeroErrorMsg() {
	   loginPage.waitForElementToBeClickable(eventDurationCannotBeZeroErrorMsg);
	   Assert.assertTrue(eventDurationCannotBeZeroErrorMsg.isDisplayed());
	   loginPage.waitTillInvisiblityOfElement(eventDurationCannotBeZeroErrorMsg);
	   
   }
   
   public void verifyDurationMoreThanAvailableMinErrorMsg() {
	   loginPage.waitForElementToBeClickable(durationMoreThanAvailableMinInDayErrorMsg);
	   Assert.assertTrue(durationMoreThanAvailableMinInDayErrorMsg.isDisplayed());
   }
   
   public void verifyEventTimePassedErrorMessage() {
	   loginPage.waitForElementToBeClickable(timeAlreadyPassErrorMsg);
	   Assert.assertTrue(timeAlreadyPassErrorMsg.isDisplayed());
	   
   }
   
   
   public void clickOnCancelBtn() {
		loginPage.waitForElementToBeClickable(cancelBtn);
		loginPage.waitForSpinnerToDisappear();
		cancelBtn.click();
		loginPage.waitForPageLoad();
	}

   
   public void clearEventDuration()
   {
	   loginPage.WaitTillElementIsPresent( eventDuration );
	   eventDuration.clear();
	   
   }

   /*
   Checking title when event is edited
    */
   public void verifyEnteredEventTitleInEdit ( String expectedEventTitle){
		loginPage.waitForElementToBeClickable ( title );
	   String actualEventTitle=getEnteredText ( "title" );
	   Assert.assertEquals ( expectedEventTitle,actualEventTitle );

	}

	/*
	Checking By Default current date is selected in Date on Add Event
	 */
	public void verifySelectedDateInEvent ( String expectedEventDate) {
		loginPage.waitForElementToBeClickable ( eventDate );
		String actualEventDate=getEnteredText ( "eventDate");
		Date d=new Date (expectedEventDate);
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat ("dd-MM-yyyy");
		expectedEventDate=simpleDateFormat.format ( d );
		Assert.assertEquals ( actualEventDate,expectedEventDate );

   }

   public void getEventNotes(String expectedEventNotes){
	   loginPage.waitForElementToBeClickable ( notesText );
	   String actualEventNotes=getEnteredText (  "eventNotes");
	   Assert.assertEquals ( expectedEventNotes,actualEventNotes );
   }

   public void verifySelectedClinicInEdit ( String clinic){
		loginPage.waitForElementToBeClickable ( clinicInEvent );
		Assert.assertEquals ( loginPage.firstSelectedOption ( clinicInEvent ),clinic );

   }

   public void verifySelectedDoctorInEdit ( String doctor){
	loginPage.waitForElementToBeClickable ( doctorEvent );
	Assert.assertEquals ( loginPage.firstSelectedOption ( doctorEvent ),doctor);

	}

	public void verifySelectedCategoryInEdit ( String category){
		loginPage.waitForElementToBeClickable ( eventCategory );
		Assert.assertEquals ( loginPage.firstSelectedOption ( eventCategory ), category);
	}

	public void getHours(String expectedHours){
		loginPage.waitForElementToBeClickable ( hours );
		String actualHours=getEnteredText ( "hours" );
		Assert.assertEquals ( actualHours,expectedHours );
	}


	public void getMinutes(String expectedMinutes ){
		loginPage.waitForElementToBeClickable ( minutes );
		String actualMinutes=getEnteredText ( "minutes" );
		Assert.assertEquals ( actualMinutes, expectedMinutes );
	}

	public void getDurations(String expectedDuration){
		loginPage.waitForElementToBeClickable ( eventDuration );
		String actualDuration =getEnteredText ( "duration" );
		Assert.assertEquals ( actualDuration, expectedDuration );
	}

	public void getAMPM(String value){
		loginPage.WaitTillElementIsPresent ( amPm );
		Assert.assertEquals ( loginPage.firstSelectedOption ( amPm ),value );

	}
	public void verifySelectedOperatoryInEdit(String expectedOperatory){
		loginPage.waitForElementToBeClickable ( operatoryInEvent );
		Assert.assertEquals ( loginPage.firstSelectedOption ( operatoryInEvent ),expectedOperatory );

	}

	public void verifySelectedRoomInEdit(String expectedRoom){
		loginPage.waitForElementToBeClickable ( roomInEvent );
		Assert.assertEquals ( loginPage.firstSelectedOption ( roomInEvent ),expectedRoom);
	}

	public void verifySelectedScannerInEdit(String expectedScanner){
		loginPage.waitForElementToBeClickable ( scannerInEvent );
		Assert.assertEquals ( loginPage.firstSelectedOption ( scannerInEvent ),expectedScanner );
	}

	public void verifyFullDayEventCheckboxInEdit(){
		loginPage.waitForElementToBeClickable ( fulldayBtn );
		WebElement element=loginPage.getDriver ().findElement ( By.xpath ( "//label[text()='Full Day Event']/preceding-sibling::input" ) );
		if(element.getAttribute ( "class" ).contains ( "ng-valid-required" )){
		Assert.assertTrue ( true );

		}
		else {
			Assert.assertFalse ( true );
		}

	}

	/*
	 * checking the time slot for which Event  is added/Update and delete is coming disable or selectable on Add Appointment Screen
	 */
   public void checkTimeSlotIsDisableOrSelectableInAddAppt(String eventDate,String eventStartTime,String eventDuration,String expectedAttributeValue) {
	   loginPage.WaitTillElementIsPresent(appointmentPage.dateAppointment);
	   appointmentPage.selectDateofAppointment(eventDate);
	   loginPage.waitForPageLoad();
	   Select slot=new Select(appointmentPage.timeSlot);
	   List<WebElement> appointmentSlots=slot.getOptions();
	   if(eventDuration.equals("All day event")) {
		   for(WebElement ele:appointmentSlots) {
			   Assert.assertEquals(ele.getAttribute(expectedAttributeValue), "true");
			   
		   }
	   }
	   else {
		   int duration = Integer.parseInt(eventDuration) / 30;
			while (duration > 0) {
				loginPage.selectFromDropDownByVisibleText(appointmentPage.timeSlot, eventStartTime);
				WebElement eventAddedTimeSlot=loginPage.getDriver().findElement(By.xpath("//option[@value='"+ eventStartTime +"']"));
				Assert.assertEquals(eventAddedTimeSlot.getAttribute(expectedAttributeValue),"true");
				eventStartTime = getSlotTime(eventStartTime);
				duration--;
			}
		   
	   }
	   
   }

	/*
	 * checking the time slot for which Event  is added is coming disable on Add Appointment Screen
	 */
   public void checkTimeSlotIsDisableOnAddAppt(String eventDate,String eventStartTime,String eventDuration){
	   checkTimeSlotIsDisableOrSelectableInAddAppt(eventDate,eventStartTime,eventDuration,"disabled");
   }

	/*
	 * checking the time slot for which Event  is deleted is coming selectable on Add Appointment Screen
	 */
   public void checkTimeSlotIsSelectableOnAddAppt(String eventDate,String eventStartTime,String eventDuration){
	   checkTimeSlotIsDisableOrSelectableInAddAppt(eventDate,eventStartTime,eventDuration,"selected");

   }

	/*
	 * checking the Doctor for which Event  is added/Update and delete is coming disable or selectable on Add Appointment Screen in doctor dropdown
	 */
   private void checkDoctorIsDisableOrSelectableInAddAppt(String eventDoctor, String eventDate, String eventDuration,String eventStartTime,String expectedAttributeValue){
	   loginPage.waitForPageLoad();
	   loginPage.WaitTillElementIsPresent(appointmentPage.dateAppointment);
	   appointmentPage.selectDateofAppointment(eventDate);
	   loginPage.waitForSpinnerToDisappear();
	   Select slot = new Select(appointmentPage.timeSlot);
	   List<WebElement> appointmentSlot = slot.getOptions();

	   if (eventDuration.equals("All day event")) {

		   for (int i = 1; i < appointmentSlot.size(); i++) {
			   loginPage.selectFromDropDownByVisibleText(appointmentPage.timeSlot, appointmentSlot.get(i).getText());
			   loginPage.waitForSpinnerToDisappear();
			   loginPage.selectFromDropDownByVisibleText ( appointmentPage.doctor, eventDoctor );
			   WebElement doctor = loginPage.getDriver ().findElement ( By.xpath ( "//select/option[contains(text(),'" + eventDoctor + "')]" ) );
			   Assert.assertTrue (doctor.getAttribute ( expectedAttributeValue ).contains ( "true" ) );

		   }

	   }

	   else {
		   int duration = Integer.parseInt(eventDuration) / 30;
		   while (duration > 0) {
			   loginPage.selectFromDropDownByVisibleText(appointmentPage.timeSlot, eventStartTime);
			   loginPage.waitForPageLoad();
			   loginPage.waitForSpinnerToDisappear();
			   loginPage.selectFromDropDownByVisibleText(appointmentPage.doctor, eventDoctor);
			   WebElement doctor=loginPage.getDriver().findElement(By.xpath("//select/option[contains(text(),'"+eventDoctor +"')]"));
			   Assert.assertTrue(doctor.getAttribute(expectedAttributeValue).contains("true"));
			   eventStartTime = getSlotTime(eventStartTime);
			   duration--;

		   }
	   }
   }


   public void checkDoctorIsSelectableInAddAppointmentDoctorDropdown(String eventDoctor, String eventDate, String eventDuration,String eventStartTime) {
		checkDoctorIsDisableOrSelectableInAddAppt ( eventDoctor,eventDate,eventDuration,eventStartTime,"selected" );

   }
   
   public void checkEventAddedDoctorIsDisable(String eventDoctor, String eventDate, String eventDuration,String eventStartTime) {
		checkDoctorIsDisableOrSelectableInAddAppt ( eventDoctor,eventDate,eventDuration,eventStartTime,"disabled" );
	}

	public void checkDisableEnableOfOperatoryOnAddAppointment ( String eventDate, String eventDuration, String eventStartTime, String expectedAttributeValue, String eventWebElement){
		loginPage.waitForPageLoad();
		loginPage.waitForSpinnerToDisappear();
		appointmentPage.selectDateofAppointment(eventDate);
		loginPage.waitForPageLoad();
		Select sel=new Select(appointmentPage.timeSlot);
		List<WebElement> appointmentSlot = sel.getOptions();
		if (eventDuration.equals("All day event")) {
			for (int i = 0; i < appointmentSlot.size(); i++) {
				loginPage.selectFromDropDownByVisibleText(appointmentPage.timeSlot, appointmentSlot.get(i).getText());
				loginPage.waitForSpinnerToDisappear();
				WebElement eventoperatory=loginPage.getDriver().findElement(By.xpath(eventWebElement));
				Assert.assertTrue(eventoperatory.getAttribute("class").contains(expectedAttributeValue));
			}
		}

		else {
			int duration = Integer.parseInt(eventDuration) / 30;
			while (duration > 0) {
				loginPage.selectFromDropDownByVisibleText(appointmentPage.timeSlot, eventStartTime);
				loginPage.waitForSpinnerToDisappear();
				WebElement eventOperatory =loginPage.getDriver().findElement(By.xpath(eventWebElement));
				Assert.assertTrue( eventOperatory.getAttribute("class").contains(expectedAttributeValue));
				eventStartTime=getSlotTime(eventStartTime);
				duration--;

			}
		}
	}

	/*
	 * checking the Operatory for which Event  is added/Update and delete is coming disable or selectable on Add Appointment Screen
	 */
	public void checkEventAddedOperatoryIsDisable(String eventAddedOperatory,String eventDate ,String eventDuration, String eventStartTime) {
		checkDisableEnableOfOperatoryOnAddAppointment ( eventDate,eventDuration,eventStartTime,"booked-event" ,getEventAddedWebElement ( eventAddedOperatory,"Operatory" ));
	}

	public void checkOperatoryIsSelectableInAddAppt(String eventAddedOperatory,String eventDate ,String eventDuration, String eventStartTime) {
		checkDisableEnableOfOperatoryOnAddAppointment ( eventDate,eventDuration,eventStartTime,"available" ,getEventAddedWebElement ( eventAddedOperatory,"Operatory" ));
	}

	/*
	 * checking the room for which Event  is added/Update and delete is coming disable or selectable on Add Appointment Screen
	 */
	public void checkRoomIsDisableOnAddAppointment ( String eventAddedRoom, String eventDate, String eventDuration, String eventStartTime) {
		checkDisableEnableOfOperatoryOnAddAppointment ( eventDate,eventDuration,eventStartTime,"booked" , getEventAddedWebElement (eventAddedRoom,"Room"));
	}

	public void checkRoomIsSelectableOnAddAppt(String eventAddedRoom, String eventDate, String eventDuration,String eventStartTime) {
		checkDisableEnableOfOperatoryOnAddAppointment ( eventDate,eventDuration,eventStartTime,"available" , getEventAddedWebElement ( eventAddedRoom,"Room" ));
	}

	/*
	 * checking the scanner for which Event  is added/Update and delete is coming disable or selectable on Add Appointment Screen
	 */
	public void checkEventAddedScannerIsDisable(String eventAddedScanner, String eventDate, String eventDuration,String eventStartTime) {
		checkDisableEnableOfOperatoryOnAddAppointment (  eventDate,eventDuration,eventStartTime,"booked-event",getEventAddedWebElement ( eventAddedScanner,"Scanner" ));
	}

	public void checkScannerIsSelectableOnAddAppt(String eventAddedScanner, String eventDate, String eventDuration,String eventStartTime){
		checkDisableEnableOfOperatoryOnAddAppointment (  eventDate,eventDuration,eventStartTime,"available",getEventAddedWebElement ( eventAddedScanner,"Scanner" ));
	}


	public String getEventAddedWebElement ( String eventAddedType,String eventType){
		String eventAddedTypeNumber=eventAddedType.split ( " " )[1];
		String webElementOnWhichEventIsAdded="";
		switch (eventType){
			case "Operatory":
				webElementOnWhichEventIsAdded="//label[@id='operatoryLabel']//following::ul//li/a[@data-value='"+eventAddedTypeNumber+"']";
				return webElementOnWhichEventIsAdded;
			case "Room":
				webElementOnWhichEventIsAdded="//label[@id='eCosultLabel']/following-sibling::ul//a[@data-value='"+eventAddedTypeNumber+"']";
				return webElementOnWhichEventIsAdded;

			case "Scanner":
				webElementOnWhichEventIsAdded="//label[@id='scannerLabel']/following-sibling::ul//a[@data-value='"+eventAddedTypeNumber+"']";
				return  webElementOnWhichEventIsAdded;

			default:
				return webElementOnWhichEventIsAdded;

		}

	}


	/*
Getting the Next Slot time for event added duration
 */
private  String getSlotTime(String eventStartTime) {
	SimpleDateFormat df = new SimpleDateFormat("HH:mm");
	try {
		Date d = df.parse(eventStartTime);
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		cal.add(Calendar.MINUTE, 30);
		eventStartTime = df.format(cal.getTime());

	} catch (Exception e) {

	}

return eventStartTime;	
	
}

/*
Common method to get the entered text from field
Passing the id of field and returning the value of entered text
 */
private String getEnteredText(String id){
	JavascriptExecutor javascriptExecutor= (JavascriptExecutor) loginPage.getDriver ();
	String text=(String)javascriptExecutor.executeScript ( "return document.getElementById('"+id+"').value;" );
	return text;
}


/*
Getting Current Date in MMMM/dd/yyyy format
 */
public String  getCurrentDate(){
	Date now = new Date();
	SimpleDateFormat dt = new SimpleDateFormat("MMMM/dd/yyyy");
	String date = dt.format(now);
	return date;

}
}
