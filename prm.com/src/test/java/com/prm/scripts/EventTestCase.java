package com.prm.scripts;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Map;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.prm.docdashboard.pageobject.AppointmentAddPage;
import com.prm.docdashboard.pageobject.BasePage;
import com.prm.docdashboard.pageobject.AppointmentsLisitngPage;
import com.prm.docdashboard.pageobject.EventListingPage;
import com.prm.docdashboard.pageobject.DoctorDashBoard;
import com.prm.docdashboard.pageobject.EventPage;
import com.prm.docdashboard.pageobject.LoginPage;
import com.prm.pageobjects.utils.ExtentReport;
import com.prm.pageobjects.utils.ReadConfig;
import com.prm.pageobjects.utils.ReadExcelData;
import com.prm.pageobjects.utils.SheetTest;
import com.prm.pageobjects.utils.TestData;
import com.relevantcodes.extentreports.LogStatus;

import bsh.ParseException;

public class EventTestCase {

	private ThreadLocal <LoginPage> loginPage = new ThreadLocal<> ();
	private ThreadLocal<DoctorDashBoard> doctorDashboard = new ThreadLocal<> ();
	private ThreadLocal<EventPage> eventPage = new ThreadLocal<> ();
	private ThreadLocal<EventListingPage> eventListingPage = new ThreadLocal<> ();
	private ThreadLocal<AppointmentAddPage> appointmentAddPage = new ThreadLocal<> ();
	private ThreadLocal<BasePage> basePage = new ThreadLocal<> ();
	private ThreadLocal<ExtentReport> extentReport = new ThreadLocal<> ();
	private ThreadLocal<AppointmentsLisitngPage> appointmentsLisitngPage = new ThreadLocal<> ();

	
	private static final String INCILINIC_FACILITY=TestData.getInstance().getInputData("event_inclinic_facility");
	private static final String ECONSULT_FACILITY=TestData.getInstance().getInputData("event_econsult_facility");
	private static final String SCANNER_FACILITY=TestData.getInstance().getInputData("event_scanner_facility");

	/*
	 * Page Url of Add Event,Edit Event ,DoctorDashboard and Event Listing
	 */
	private static final String ADD_EVENT_PAGE_TITLE = "Add Appointment/Event";
	private static final String DOCTOR_DASHBOARD_PAGE_TITLE = "Doctor Dashboard";
	private static final String EVENTLISTING_PAGE_TITLE = "Appointment/Event Listing";

	/*
	 * // Message here which We will print extent execution report after execution
	 *
	 */
	private static final String SCRIPTS_STARTED_MSG = "@@ Test scripts has been started here @@";
	private static final String VERIFY_ALL_WEB_ELEMENT_ON_EVENT_ADD = "EventAdd #:Verifying all Mandatory WebElements in By Doctor,By Operatory ,By Room and By Scanner are present on event Add";
	private static final String CHECKING_WITHOUT_MANDATORY_FIELDS = "EventAdd  #Verifying all error Msg shown when mandatory field are not filled on Add Event ";
	private static  final String VERIFY_VALIDATION_FOR_EVENT_TIME_FIELDS="# Verifying Validation on Hours,Minutes and Duration field";
	private static final String VERIFY_BY_DOCTOR_EVENT = "#1 Add ByDoctor Event #2: Verify Event Details on  the Event Listing home page #3: check on Add Appoinment Doctor Name is coming disable in Doctor Dropdown";
	private static final String VERIFY_BY_OPERATORY_EVENT = "#1: Add ByOperatory Event #Verify Event Details on the Event Listing home Page #3: check on Add Appointment page event Added Operatory is disabled";
	private static final String VERIFY_BY_ROOM_EVENT = "#1: Add ByRoom Event #2: Verify Event Details on the Event Listing home Page #3: check on Add Appointment page event Added Room is disbaled";
	private static final String VERIFY_BY_SCANNER_EVENT = "#1: Add ByScanner Event #2 Verify Event Details on the Event Listing hoome Page #3: check on Add Appointment page event Added Scanner is disabled";
	private static final  String VERIFY_BY_DOCTOR_EVENT_UPDATE="Verify Updating Single Doctor Event to  All Doctors event and checking Add Appointment update event time slot is disabled for all doctor and previous time slot is available now.";
	private static final String VERIFY_BY_DOCTOR_EVENT_DELETED="Verify Deleting By Doctor Event and checking doctor is available after event deleted";
	private static final  String VERIFY_ADD_EVENT_FOR_ALREADY_BOOKED_APPOINTMENT_TIME="Verify Booking event for the same time for which already a booked appointment is present";
	private static final  String VERIFY_BY_OPERATORY_EVENT_UPDATE="Verify Updating By Operatory Event and checking Add Appointment update event time slot is disabled for operatory and for previous time slot operatory is available now.";
	private static final String VERIFY_BY_OPERATORY_EVENT_DELETED="Verify Deleting By Operatory Event and checking operatory is available after event deleted";
	private static final  String VERIFY_BY_ROOM_EVENT_UPDATE="Verify Updating Room Event and checking on Add Appointment room is disabled for update event time slot and room is available on previous time slot.";
	private static final String VERIFY_BY_ROOM_EVENT_DELETED="Verify Delete By Room event and checking on add appointment room is available after event deleted";
	private static final  String VERIFY_BY_SCANNER_EVENT_UPDATE="Verify Updating Single Doctor Event to  All Doctors event and checking Add Appointment update event time slot is disabled for all doctor and previous time slot is available now.";
	private static final String VERIFY_BY_SCANNER_EVENT_DELETED="Verify Delete By Scanner event and checking on add appointment scanner is available after event deleted";
	private static final String VERIFY_DOC_IF_ALREADY_EVENT_ON_SAME_TIME = "1: EventAdd #Doctor has aleady an event added for the same time verified";
	private static final String EVENT_TIME__ALREADY_PASSED = "1: EventAdd # Event Time Alreay Passed";
	private static final String VERIFY_RESET_BTN = "1: EventAdd #9:Reset Btn is working fine";
	private static final String VERIFY_CANCEL_BTN = "1: EventAdd #10:Cancel Btn is working fine";
	private static final String VERIFY_ADDING_EVENT_ON_LAST_AVAILABLE_OPERATORY = ":1 Verify Event cannot be added  on Last available  Operatory";
	private static final String VERIFY_ADDING_EVENT_ON_LAST_AVAILABLE_SCANNER = "Verify Event cannot be added on Last available Scanner";
	private static final String STATUS_OF_SCRIPTS = "@@ Test script has been completed here@@";
	private static final String VERIFY_ADDING_EVENT_FOR_A_SLOT_FOR_WHICH_TENTATIVE_APPT_ALREADY_BOOKED="Verifying add event for same time slot for which tentative appointment is present";
	// -------------------------Event listing Msg------------------------------
	private static final String VERIFY_ELEMENT_ON_EVENT_LIST = "2: Event Listing #71:All  WebElement are present on event listing home page";
	private static final String VERIFY_ADD_EVENT_FROM_EVENT_LISTING = "2: Event Listing #72:Verify Event Add redirection from Event Listing # Verify redirection to event listing after event save ";
	private static final String VERIFY_FRM_AND_TO_DATE_FILTER = "2: Event Listing #75:All event date is b/w frm and to date";
	private static final String CHECK_FRM_DATE_IS_NOT_LATER_TO_TO_DATE = "2: Event Listing #76:frm Date is not later than to Date";
	private static final String VERIFY_DOCTOR_FILTER_ON_EVENT_LIST = "2: Event Listing #77:Doctor filter is working fine";
	private static final String VERIFY_FILTER_OF_CATEGORY_TYPE = "2: Event Listing #78:Category filter is working fine";
	private static final String VERIFY_EVENT_TYPE_FILTER_ON_EVENT_LIST = "2: Event Listing #79:Event Type filter is working fine";
	private static final String VERIFY_RESET_BUTTON_FUNCTIONALITY_ON_EVENT_LISTING = "2: Verify Reset Button woking on Event Listing";

	/*
	 * Google Sheet Name where data is present
	 */
	private final String MODULE_NAME="Event";
	private final String TEST_DATA_SHEET_NAME="EventTestData";
	
	/*
	 * Google Sheet Test Data in Map
	 */
	private Map<String,String>addDoctorEventTestData=SheetTest.prepareData(MODULE_NAME, TEST_DATA_SHEET_NAME, "A2", "O2");
	private Map<String,String> updateDoctorEventTestData=null;
	private Map<String,String> addOperatoryEventTestData=null;
	private Map<String,String> updateOperatoryEventTestData=null;
	private Map<String,String> addRoomEventTestData=null;
	private Map<String,String> updateRoomEventTestData=null;
	private Map<String,String> addScannerRoomEvent=null;
	private Map<String,String> updateScannerRoomEvent=null;
	private Map<String,String> confirmedAppointmentTestData=null;
	private Map<String,String> tentativeAppointmentTestData=null;
	
	/*
	 * Create Extent Report with name Same as Class Name Launching Browser Login to Application Redirect to Doctor DashBoardPage
	 * Log in the extent report testCases Started Redirect to Doctor Dashboard after
	 * every test exceution
	 *
	 */

	
	@BeforeMethod(enabled = true)
	public void EventAddHomePage(){
		loginPage.set ( new LoginPage () );
		doctorDashboard.set ( new DoctorDashBoard ( loginPage.get()) );
		eventPage.set ( new EventPage ( loginPage.get()) );
		eventListingPage.set ( new EventListingPage ( loginPage.get()) );
		appointmentAddPage.set ( new AppointmentAddPage ( loginPage.get()) );
		basePage.set ( new BasePage ( loginPage.get()) );
		appointmentsLisitngPage.set ( new AppointmentsLisitngPage( loginPage.get()) );
		extentReport.set ( new ExtentReport (loginPage.get (),"Event Add/Listing TestCases") );
		extentReport.get ().logger = extentReport.get ().report.startTest(this.getClass().getSimpleName());
		extentReport.get ().logger.log(LogStatus.INFO, LoginPage.testCaseRunningStatusmsg);
		extentReport.get ().logger.log(LogStatus.PASS, LoginPage.browserMsg);
		String username = ReadConfig.getInstance().getUsername().toString();
		String password = ReadConfig.getInstance().getPassword().toString();
		loginPage.get().login(username, password);
		extentReport.get ().logger.log(LogStatus.PASS, LoginPage.userNameMsg);
		extentReport.get ().logger.log(LogStatus.PASS, LoginPage.passwordMsg);
		extentReport.get ().logger.log(LogStatus.PASS, LoginPage.loginBtnClickMsg);
		doctorDashboard.get ().doctorDashboardHomePage();
		extentReport.get ().logger.log(LogStatus.PASS, SCRIPTS_STARTED_MSG);
		loginPage.get().waitForPageLoad();
		doctorDashboard.get ().doctorDashboardHomePage();
		loginPage.get().waitForPageLoad();
		basePage.get().verifyPageTitle(DOCTOR_DASHBOARD_PAGE_TITLE);
	}

	/*
	 * Verify Title Duration,Date,Clinic and Notes field on Add Event
	 * Verify default Event Type and Date selected on Add Event
	 * Verify Event Type according to clinic Supported Consultation Type
	 * Verify Doctor,Operatory,Room,Scanner DropDown in By Doctor,By Operatory ,By Room and By Scanner Event
	 */

	@Test(description = VERIFY_ALL_WEB_ELEMENT_ON_EVENT_ADD)
	public void verifyAllWebElementOnEventAdd() {
		extentReport.get ().logger.log(LogStatus.PASS, VERIFY_ALL_WEB_ELEMENT_ON_EVENT_ADD);
		basePage.get().selectClinicFrmHeader(INCILINIC_FACILITY);
		doctorDashboard.get().clickonAppointmentAdd();
		loginPage.get().waitForPageLoad();
		eventPage.get().clickOnEvent();
		eventPage.get().verifyDefaultSelectedEventType();
		eventPage.get().verifySelectedDateInEvent ( eventPage.get().getCurrentDate ());
		eventPage.get().verifyDefaultSelectedClinic(INCILINIC_FACILITY);
		eventPage.get().verifyAllMandatoryWebElementOnByDoctorEvent();
		eventPage.get().selectClinicInEvent(INCILINIC_FACILITY);
		eventPage.get().verifyAllMandatoryWebElementOnByOperatoryEvent();
		eventPage.get().selectClinicInEvent(ECONSULT_FACILITY);
		eventPage.get().verifyAllMandatoryWebElementOnByRoomEvent();
		eventPage.get().selectClinicInEvent(SCANNER_FACILITY);
		eventPage.get().verifyAllMandatoryWebElementOnByScannerEvent();
		basePage.get().verifyPageTitle(ADD_EVENT_PAGE_TITLE);

	}

	/*
	 * Verify all Error Message shown on Add Event when Mandatory field are not
	 * filled with data.
	 */

	@Test(description = CHECKING_WITHOUT_MANDATORY_FIELDS)
	public void checkingWithoutMandatoryFields() {
		extentReport.get ().logger.log(LogStatus.INFO,CHECKING_WITHOUT_MANDATORY_FIELDS);
		basePage.get().selectClinicFrmHeader(INCILINIC_FACILITY);
		doctorDashboard.get().clickonAppointmentAdd();
		loginPage.get().waitForPageLoad();
		eventPage.get().clickOnEvent();
		eventPage.get().clickOnEventSaveBtn();

		eventPage.get().enterTittleErrorMsg();
		eventPage.get().verifyDefaultSelectedOptionInDoctorDrpDwn();
		eventPage.get().selectDoctorErrorMsg();
		eventPage.get().verifyDefaultSelectedOptionInCategoryDrpDwn();
		eventPage.get().selectCategoryErrorMsg();
		eventPage.get().eventDurationErrorMsg();


		eventPage.get().selectOperatoryErrorMsg();
		eventPage.get().enterTittleErrorMsg();
		eventPage.get().verifyDefaultSelectedOptionInOperatoryDrpDwn();
		eventPage.get().verifyDefaultSelectedOptionInCategoryDrpDwn();
		eventPage.get().selectCategoryErrorMsg();
		eventPage.get().eventDurationErrorMsg();


		eventPage.get().selectClinicInEvent(ECONSULT_FACILITY);
		eventPage.get().selectRoomErrorMsg();
		eventPage.get().enterTittleErrorMsg();
		eventPage.get().verifyDefaultSelectedOptionInRoomDrpDwn();
		eventPage.get().verifyDefaultSelectedOptionInCategoryDrpDwn();
		eventPage.get().selectCategoryErrorMsg();
		eventPage.get().eventDurationErrorMsg();

		eventPage.get().selectClinicInEvent(SCANNER_FACILITY);
		eventPage.get().selectScannerErrorMsg();
		eventPage.get().enterTittleErrorMsg();
		eventPage.get().verifyDefaultSelectedOptionInScannerDrpDown();
		eventPage.get().verifyDefaultSelectedOptionInCategoryDrpDwn();
		eventPage.get().selectCategoryErrorMsg();
		eventPage.get().eventDurationErrorMsg();
		basePage.get().verifyPageTitle(ADD_EVENT_PAGE_TITLE);

	}



	/*
	 * Verify the validation on Hours field should be b/w 1-12
	 * Verify the validation on Minutes field should be b/w 0-59
	 * Verify the validation on Duration field should be less minute left in current day.
	 * Verify validation event duration cannot be zero
	 */

	@Test(description = VERIFY_VALIDATION_FOR_EVENT_TIME_FIELDS)
	public void verifyHoursMinsutesAndDurationValidation() {
		extentReport.get ().logger.log(LogStatus.PASS, VERIFY_VALIDATION_FOR_EVENT_TIME_FIELDS);
		basePage.get().selectClinicFrmHeader(addDoctorEventTestData.get("event_Clinic"));
		doctorDashboard.get().clickonAppointmentAdd();
		loginPage.get().waitForPageLoad();
		eventPage.get().clickOnEvent();
		/* verify when full day event CheckBox is checked then Hours,Minutes,AM/PM and
		 * Duration fields is disabled
		 */
		eventPage.get().behaviourOfEventType();
		eventPage.get().clickOnEventResetBtn();

		eventPage.get().enterHours("30");
		loginPage.get().pressTabKey(loginPage.get().getDriver());
		eventPage.get().verifyInvalidHoursErrorMsg();
		eventPage.get().clickOnEventResetBtn();

		eventPage.get().enterMinutes("72");
		loginPage.get().pressTabKey(loginPage.get().getDriver());
		eventPage.get().verifyInvalidMintuesErrorMsg();
		eventPage.get().clickOnEventResetBtn();

		eventPage.get().enterTitleName(addDoctorEventTestData.get("event_Title"));
		eventPage.get().selectClinicInEvent(addDoctorEventTestData.get("event_Clinic"));
		eventPage.get().selectDoctorInEvent(addDoctorEventTestData.get("event_On"));
		eventPage.get().selectEventCategory(addDoctorEventTestData.get("category"));
		eventPage.get().selectDateInEvent(addDoctorEventTestData.get("event_Date"));
		eventPage.get().enterHours("3");
		eventPage.get().enterMinutes("40");
		eventPage.get().selectAmPm("PM");
		eventPage.get().enterEventDuration("00");
		eventPage.get().clickOnEventSaveBtn();
		eventPage.get().verifyDurationCannotBeZeroErrorMsg();
		eventPage.get().clearEventDuration();
		eventPage.get().enterEventDuration("3000");
		eventPage.get().clickOnEventSaveBtn();
		eventPage.get().verifyDurationMoreThanAvailableMinErrorMsg();
		basePage.get().verifyPageTitle(ADD_EVENT_PAGE_TITLE);

	}


	/*
	 * Adding By Doctor Event and then verify added Event on Event Listing
	 * Checking on Add Appointment Page Event Added Doctor is Non-Selected in DropDown for
	 * the Event Duration
	 */
	@Test(description = VERIFY_BY_DOCTOR_EVENT)
	public void verifyAddingByDoctorEvent() {
		extentReport.get ().logger.log(LogStatus.INFO, VERIFY_BY_DOCTOR_EVENT);
		basePage.get().selectClinicFrmHeader(addDoctorEventTestData.get("event_Clinic"));
		doctorDashboard.get().clickonAppointmentAdd();
		loginPage.get().waitForPageLoad();
		eventPage.get().clickOnEvent();
		eventPage.get().enterTitleName(addDoctorEventTestData.get("event_Title"));
		eventPage.get().selectClinicInEvent(addDoctorEventTestData.get("event_Clinic"));
		eventPage.get().selectDoctorInEvent(addDoctorEventTestData.get("event_On"));
		eventPage.get().selectEventCategory(addDoctorEventTestData.get("category"));
		eventPage.get().selectDateInEvent(addDoctorEventTestData.get("event_Date"));
		eventPage.get().enterHours(addDoctorEventTestData.get("event_hours"));
		eventPage.get().enterMinutes(addDoctorEventTestData.get("event_mintues"));
		eventPage.get().selectAmPm(addDoctorEventTestData.get("AM_PM"));
		eventPage.get().enterEventDuration(addDoctorEventTestData.get("event_duration"));
		eventPage.get().enterNotes(addDoctorEventTestData.get("event_notes"));
		eventPage.get().clickOnEventSaveBtn();
//		eventPage.get().verifyEventAddSuccessMsg();
		basePage.get().verifyPageTitle(DOCTOR_DASHBOARD_PAGE_TITLE);
		basePage.get().selectClinicFrmHeader(addDoctorEventTestData.get("event_Clinic"));
		doctorDashboard.get().clickOnAppListBtn();
		eventListingPage.get().clickOnEventBtn();
		eventListingPage.get().selectDoctor(addDoctorEventTestData.get("event_On"));
		eventListingPage.get().selectFrmdateInEvent(addDoctorEventTestData.get("event_Date"));
		eventListingPage.get().selectTodateInEvent(addDoctorEventTestData.get("event_Date"));
		eventListingPage.get().selectType(addDoctorEventTestData.get("event_Type"));
		eventListingPage.get().selectCategroy(addDoctorEventTestData.get("category"));
		eventListingPage.get().clickOnSearch();
		eventListingPage.get().verifyDateTimeDisplayOnEventList( addDoctorEventTestData.get("event_Title"), addDoctorEventTestData.get("event_DateTime"));
		eventListingPage.get().verifyDurationDisplayOnEventList( addDoctorEventTestData.get("event_Title"), addDoctorEventTestData.get("event_duration"));
		eventListingPage.get().verifyTitleDisplayOnEventlist(addDoctorEventTestData.get("event_Title"));
		eventListingPage.get().verifyEventTypeDisplayOnEventList( addDoctorEventTestData.get("event_Title"),"Doctor");
		eventListingPage.get().verifyCategoryDisplayOnEventList( addDoctorEventTestData.get("event_Title"), addDoctorEventTestData.get("category"));
		eventListingPage.get().verifyDoctorDisplayOnEventList( addDoctorEventTestData.get("event_Title"),addDoctorEventTestData.get("doctor_nickName"));
		eventListingPage.get().verifyClinicDisplayOnEventList(addDoctorEventTestData.get("event_Title"),addDoctorEventTestData.get("event_Clinic"));
		eventListingPage.get().verifyNotesDisplayOnEventList( addDoctorEventTestData.get("event_Title"), addDoctorEventTestData.get("event_notes"));
		eventListingPage.get().verifyEditAndDeletBtn(addDoctorEventTestData.get("event_Title"));
		basePage.get().backTODoctorDashboard();
		basePage.get().verifyPageTitle(DOCTOR_DASHBOARD_PAGE_TITLE);
		basePage.get().selectClinicFrmHeader(addDoctorEventTestData.get("event_Clinic"));
		doctorDashboard.get().clickonAppointmentAdd();
		eventPage.get().checkEventAddedDoctorIsDisable(addDoctorEventTestData.get("event_On"),
				addDoctorEventTestData.get("event_Date"),
				addDoctorEventTestData.get("event_duration"),
				addDoctorEventTestData.get("appointmentTimeSlot"));

	}

	/*
	 * Verify Adding Event for Doctor for same Time for which doctor is already
	 ** having a Event
	 */
	@Test(description = VERIFY_DOC_IF_ALREADY_EVENT_ON_SAME_TIME,dependsOnMethods = "verifyAddingByDoctorEvent")
	public void verifyDocEventIfAlreadyEventOnSameTime() {
		extentReport.get ().logger.log(LogStatus.INFO, VERIFY_DOC_IF_ALREADY_EVENT_ON_SAME_TIME);
		basePage.get().selectClinicFrmHeader(addDoctorEventTestData.get("event_Clinic"));
		doctorDashboard.get().clickonAppointmentAdd();
		loginPage.get().waitForPageLoad();
		eventPage.get().clickOnEvent();
		eventPage.get().enterTitleName(addDoctorEventTestData.get("event_Title"));
		eventPage.get().selectClinicInEvent(addDoctorEventTestData.get("event_Clinic"));
		eventPage.get().selectDoctorInEvent(addDoctorEventTestData.get("event_On"));
		eventPage.get().selectEventCategory(addDoctorEventTestData.get("category"));
		eventPage.get().selectDateInEvent(addDoctorEventTestData.get("event_Date"));
		eventPage.get().enterHours(addDoctorEventTestData.get("event_hours"));
		eventPage.get().enterMinutes(addDoctorEventTestData.get("event_mintues"));
		eventPage.get().selectAmPm(addDoctorEventTestData.get("AM_PM"));
		eventPage.get().enterEventDuration(addDoctorEventTestData.get("event_duration"));
		eventPage.get().enterNotes(addDoctorEventTestData.get("event_notes"));
		eventPage.get().clickOnEventSaveBtn();
		eventPage.get().verifyEventAlreadyBookedForSlotErrorMsg();
		basePage.get().verifyPageTitle(ADD_EVENT_PAGE_TITLE);
	}

	/*
	 * Verify Updating Single Doctor Event to  All Doctors event and then verifying the update event details on Event listing
	 * Checking on Add Appointment update event added time slot is disabled for all doctor and previous time slot is available now.
	 */
	@Test(description =VERIFY_BY_DOCTOR_EVENT_UPDATE, dependsOnMethods = "verifyAddingByDoctorEvent")
	public void verifyUpdateDoctorEvent(){
		updateDoctorEventTestData=SheetTest.prepareData(MODULE_NAME, TEST_DATA_SHEET_NAME, "A3", "O3");
		extentReport.get ().logger.log(LogStatus.INFO, VERIFY_BY_DOCTOR_EVENT_UPDATE);
		basePage.get().selectClinicFrmHeader(addDoctorEventTestData.get("event_Clinic"));
		doctorDashboard.get().clickOnAppListBtn();
		eventListingPage.get().clickOnEventBtn();
		eventListingPage.get().selectFrmdateInEvent(addDoctorEventTestData.get("event_Date"));
		eventListingPage.get().selectTodateInEvent(addDoctorEventTestData.get("event_Date"));
		eventListingPage.get().selectCategroy(addDoctorEventTestData.get("category"));
		eventListingPage.get().clickOnSearch();
		eventListingPage.get().clickOnEditBtn (addDoctorEventTestData.get("event_Title"));
		basePage.get().verifyPageTitle(ADD_EVENT_PAGE_TITLE);

		eventPage.get().verifyEnteredEventTitleInEdit ( addDoctorEventTestData.get("event_Title") );
		eventPage.get().verifySelectedDateInEvent ( addDoctorEventTestData.get("event_Date"));
		eventPage.get().verifySelectedClinicInEdit (addDoctorEventTestData.get("event_Clinic"));
		eventPage.get().verifySelectedDoctorInEdit ( addDoctorEventTestData.get("event_On") );
		eventPage.get().verifySelectedCategoryInEdit ( addDoctorEventTestData.get("category"));
		eventPage.get().verifyFullDayEventCheckboxInEdit ();
		eventPage.get().enterTitleName(updateDoctorEventTestData.get("event_Title"));
		eventPage.get().selectClinicInEvent(updateDoctorEventTestData.get("event_Clinic"));
		eventPage.get().selectDoctorInEvent(updateDoctorEventTestData.get("event_On"));
		eventPage.get().selectEventCategory( updateDoctorEventTestData.get("category"));
		eventPage.get().selectDateInEvent( updateDoctorEventTestData.get("event_Date"));
		eventPage.get().enterHours(updateDoctorEventTestData.get("event_hours"));
		eventPage.get().enterMinutes(updateDoctorEventTestData.get("event_mintues"));
		eventPage.get().selectAmPm(updateDoctorEventTestData.get("AM_PM"));
		eventPage.get().enterEventDuration(updateDoctorEventTestData.get("event_duration"));
		eventPage.get().enterNotes(updateDoctorEventTestData.get("event_notes"));
		eventPage.get().clickOnEventSaveBtn();
		basePage.get().verifyPageTitle(EVENTLISTING_PAGE_TITLE);
		basePage.get().backTODoctorDashboard ();
		basePage.get().selectClinicFrmHeader(updateDoctorEventTestData.get("event_Clinic"));
		doctorDashboard.get().clickOnAppListBtn();
		eventListingPage.get().clickOnEventBtn();
		eventListingPage.get().selectFrmdateInEvent( updateDoctorEventTestData.get("event_Date"));
		eventListingPage.get().selectTodateInEvent( updateDoctorEventTestData.get("event_Date"));
		eventListingPage.get().selectCategroy( updateDoctorEventTestData.get("category"));
		eventListingPage.get().clickOnSearch();
		eventListingPage.get().verifyDateTimeDisplayOnEventList(updateDoctorEventTestData.get("event_Title"), updateDoctorEventTestData.get("event_DateTime"));
		eventListingPage.get().verifyDurationDisplayOnEventList(updateDoctorEventTestData.get("event_Title"), updateDoctorEventTestData.get("event_duration"));
		eventListingPage.get().verifyTitleDisplayOnEventlist(updateDoctorEventTestData.get("event_Title"));
		eventListingPage.get().verifyEventTypeDisplayOnEventList(updateDoctorEventTestData.get("event_Title"),"Doctor");
		eventListingPage.get().verifyCategoryDisplayOnEventList(updateDoctorEventTestData.get("event_Title"),  updateDoctorEventTestData.get("category"));
		eventListingPage.get().verifyDoctorDisplayOnEventList(updateDoctorEventTestData.get("event_Title"), updateDoctorEventTestData.get("doctor_nickName"));
		eventListingPage.get().verifyClinicDisplayOnEventList(updateDoctorEventTestData.get("event_Title"),updateDoctorEventTestData.get("event_Clinic"));
		eventListingPage.get().verifyNotesDisplayOnEventList(updateDoctorEventTestData.get("event_Title"), updateDoctorEventTestData.get("event_notes"));
		eventListingPage.get().verifyEditAndDeletBtn(updateDoctorEventTestData.get("event_Title"));
		basePage.get().backTODoctorDashboard();
		basePage.get().selectClinicFrmHeader(updateDoctorEventTestData.get("event_Clinic"));
		doctorDashboard.get().clickonAppointmentAdd();
		/*
		 * in method checkDoctorIsSelectableInAddAppointmentDoctorDropdown we are
		 * checking the date/time for which initially Event added before edit is now
		 * free from event
		 */
		eventPage.get().checkDoctorIsSelectableInAddAppointmentDoctorDropdown(addDoctorEventTestData.get("event_On"),
				addDoctorEventTestData.get("event_Date"), addDoctorEventTestData.get("event_duration"),
				addDoctorEventTestData.get("appointmentTimeSlot"));
		/*
		 * in checkEventAddedDoctorIsDisable we are checking the event is added for
		 * updated date/time after edit
		 */
		eventPage.get().checkTimeSlotIsDisableOnAddAppt (updateDoctorEventTestData.get("event_Date"),
				updateDoctorEventTestData.get("appointmentTimeSlot"),
				updateDoctorEventTestData.get("event_duration"));

		basePage.get().backTODoctorDashboard();
		basePage.get().verifyPageTitle(DOCTOR_DASHBOARD_PAGE_TITLE);

	}
	/*
	 *Deleting a By Doctor Event Verify Deleted Event record is strike off on Event Listing
	 * Checking on Add Appointment doctor is available after event deleted.
	 */
	@Test(description = VERIFY_BY_DOCTOR_EVENT_DELETED,dependsOnMethods = "verifyUpdateDoctorEvent")
	public void verifyDeleteDoctorEvent(){
		extentReport.get ().logger.log(LogStatus.PASS, VERIFY_BY_DOCTOR_EVENT_DELETED);
		basePage.get().selectClinicFrmHeader(updateDoctorEventTestData.get("event_Clinic"));
		doctorDashboard.get().clickOnAppListBtn();
		eventListingPage.get().clickOnEventBtn();
		eventListingPage.get().selectFrmdateInEvent( updateDoctorEventTestData.get("event_Date"));
		eventListingPage.get().selectTodateInEvent( updateDoctorEventTestData.get("event_Date"));
		eventListingPage.get().selectType (updateDoctorEventTestData.get("event_Type") );
		eventListingPage.get().clickOnSearch();
		eventListingPage.get().verifyEditAndDeletBtn(updateDoctorEventTestData.get("event_Title"));
		eventListingPage.get().clickOnDeleteBtn(updateDoctorEventTestData.get("event_Title"));
		eventListingPage.get().clickOnNoBtnDeletePopUp();
		eventListingPage.get().clickOnDeleteBtn(updateDoctorEventTestData.get("event_Title"));
		eventListingPage.get().clickOnYesBtnDeletePopUp();
//		eventPage.get().verifyEventDeleteSuccessMsg ();
		eventListingPage.get().verifyAfterDelete(updateDoctorEventTestData.get("event_Title"));
		basePage.get().verifyPageTitle(EVENTLISTING_PAGE_TITLE);
		basePage.get().backTODoctorDashboard();
		basePage.get().selectClinicFrmHeader(updateDoctorEventTestData.get("event_Clinic"));
		doctorDashboard.get().clickonAppointmentAdd();
		/*
		 * *checking after doctor event is deleted ,date/time for which doctor event is added is now
		 * available
		 */
		eventPage.get().checkTimeSlotIsSelectableOnAddAppt (  updateDoctorEventTestData.get("event_Date"),
				updateDoctorEventTestData.get("appointmentTimeSlot"),
				addDoctorEventTestData.get("event_duration") );

		basePage.get().backTODoctorDashboard();
		basePage.get().verifyPageTitle(DOCTOR_DASHBOARD_PAGE_TITLE);


	}

	/*
	 * Adding a In-clinic confirmed appointment for a doctor
	 * then adding a Doctor event for the same time for which appointment is already booked
	 * Verify the error message on event save.
	 *
	 */
	@Test(description = VERIFY_ADD_EVENT_FOR_ALREADY_BOOKED_APPOINTMENT_TIME,priority = 1)
	public void verifyAddEventIfAppointmentAlreadyBookedForTime() throws ParseException, java.text.ParseException {
		confirmedAppointmentTestData=SheetTest.prepareData("Appointment", "PatientAppointment", "A7", "W7");
		extentReport.get ().logger.log ( LogStatus.INFO,VERIFY_ADD_EVENT_FOR_ALREADY_BOOKED_APPOINTMENT_TIME );
		basePage.get().selectClinicFrmHeader(confirmedAppointmentTestData.get("clinicLocation"));
		doctorDashboard.get().clickonAppointmentAdd();
		loginPage.get().waitForPageLoad();
		appointmentAddPage.get().checkAppointmentInClinic();
		appointmentAddPage.get().selectInClinic();
		appointmentAddPage.get().enterPatientName(confirmedAppointmentTestData.get("patient_name"));
		appointmentAddPage.get().enterMobileNumber(confirmedAppointmentTestData.get("patient_mobile"));
		appointmentAddPage.get().selectClinicFromDropdown(confirmedAppointmentTestData.get("clinicLocation"));
		appointmentAddPage.get().appointmentDate(confirmedAppointmentTestData.get("appointmentDate"));
		appointmentAddPage.get().selectTimeSlotFromDropdown(confirmedAppointmentTestData.get("slot"));
		appointmentAddPage.get().enterTimeDuration(confirmedAppointmentTestData.get("duration"));
		appointmentAddPage.get().selectDoctorFromDropdown(confirmedAppointmentTestData.get("doctor"));
		appointmentAddPage.get().selectReferralFromDropdown("Doctor");
		appointmentAddPage.get().clickOnSaveBtn();
		basePage.get().verifyPageTitle(DOCTOR_DASHBOARD_PAGE_TITLE);

		basePage.get().selectClinicFrmHeader(confirmedAppointmentTestData.get("clinicLocation"));
		doctorDashboard.get().clickonAppointmentAdd();
		loginPage.get().waitForPageLoad();
		eventPage.get().clickOnEvent();
		eventPage.get().enterTitleName("Doctor Having Appointment already for slot");
		eventPage.get().selectClinicInEvent(confirmedAppointmentTestData.get("clinicLocation"));
		eventPage.get().selectDoctorInEvent(confirmedAppointmentTestData.get("doctor"));
		eventPage.get().selectEventCategory(addDoctorEventTestData.get("category"));
		eventPage.get().selectDateInEvent(confirmedAppointmentTestData.get("appointmentDate"));
		eventPage.get().enterHours(loginPage.get().convert24HourFormatTo12HourFormat(confirmedAppointmentTestData.get("slot")).split(":")[0]);
		eventPage.get().enterMinutes(loginPage.get().convert24HourFormatTo12HourFormat(confirmedAppointmentTestData.get("slot")).split(":")[1]);
		eventPage.get().selectAmPm(loginPage.get().convert24HourFormatTo12HourFormat(confirmedAppointmentTestData.get("slot")).split(" ")[1].toUpperCase());
		eventPage.get().enterEventDuration(confirmedAppointmentTestData.get("duration"));
		eventPage.get().enterNotes(addDoctorEventTestData.get("event_notes"));
		eventPage.get().clickOnEventSaveBtn();
		eventPage.get().verifyEventAlreadyBookedForSlotErrorMsg();



	}


	/*
	 * Verify Adding By Operatory Event for Full Day
	 * then verify event details on event listing
	 * Check event added Operatory is Non Selectable on Add  Appointment Page.
	 */
	@Test(description = VERIFY_BY_OPERATORY_EVENT)
	public void verifyByOperatoryEvent() {
		addOperatoryEventTestData=SheetTest.prepareData(MODULE_NAME, TEST_DATA_SHEET_NAME, "A6", "O6");
		extentReport.get ().logger.log(LogStatus.INFO, VERIFY_BY_OPERATORY_EVENT);
		basePage.get().selectClinicFrmHeader(addOperatoryEventTestData.get("event_Clinic"));
		doctorDashboard.get().clickonAppointmentAdd();
		loginPage.get().waitForPageLoad();
		eventPage.get().clickOnEvent();
		eventPage.get().selectClinicInEvent(addOperatoryEventTestData.get("event_Clinic"));
		eventPage.get().selectByOperatoryEvent();
		eventPage.get().enterTitleName(addOperatoryEventTestData.get("event_Title"));
		eventPage.get().selectOperatory(addOperatoryEventTestData.get("event_On"));
		eventPage.get().selectEventCategory(addOperatoryEventTestData.get("category"));
		eventPage.get().selectDateInEvent(addOperatoryEventTestData.get("event_Date"));
		eventPage.get().clickOnFullDayCheckBox ();
		eventPage.get().enterNotes(addOperatoryEventTestData.get("event_notes"));
		eventPage.get().clickOnEventSaveBtn();
//		eventPage.get().verifyEventAddSuccessMsg ();
		basePage.get().verifyPageTitle(DOCTOR_DASHBOARD_PAGE_TITLE);
		basePage.get().selectClinicFrmHeader(addOperatoryEventTestData.get("event_Clinic"));
		doctorDashboard.get().clickOnAppListBtn();
		eventListingPage.get().clickOnEventBtn();
		eventListingPage.get().selectFrmdateInEvent(addOperatoryEventTestData.get("event_Date"));
		eventListingPage.get().selectTodateInEvent(addOperatoryEventTestData.get("event_Date"));
		eventListingPage.get().selectType(addOperatoryEventTestData.get("event_Type"));
		eventListingPage.get().clickOnSearch();
		eventListingPage.get().verifyDateTimeDisplayOnEventList(addOperatoryEventTestData.get("event_Title"),addOperatoryEventTestData.get("event_DateTime"));
		eventListingPage.get().verifyDurationDisplayOnEventList(addOperatoryEventTestData.get("event_Title"),addOperatoryEventTestData.get("event_duration"));
		eventListingPage.get().verifyTitleDisplayOnEventlist(addOperatoryEventTestData.get("event_Title"));
		eventListingPage.get().verifyEventTypeDisplayOnEventList(addOperatoryEventTestData.get("event_Title"),addOperatoryEventTestData.get("event_On"));
		eventListingPage.get().verifyCategoryDisplayOnEventList(addOperatoryEventTestData.get("event_Title"), addOperatoryEventTestData.get("category"));
		eventListingPage.get().verifyDoctorDisplayOnEventList(addOperatoryEventTestData.get("event_Title"), "NA");
		eventListingPage.get().verifyClinicDisplayOnEventList(addOperatoryEventTestData.get("event_Title"),addOperatoryEventTestData.get("event_Clinic"));
		eventListingPage.get().verifyNotesDisplayOnEventList(addOperatoryEventTestData.get("event_Title"), addOperatoryEventTestData.get("event_notes"));
		eventListingPage.get().verifyEditAndDeletBtn(addOperatoryEventTestData.get("event_Title"));
		basePage.get().backTODoctorDashboard();
		basePage.get().selectClinicFrmHeader(addOperatoryEventTestData.get("event_Clinic"));
		doctorDashboard.get().clickonAppointmentAdd();
		eventPage.get().checkEventAddedOperatoryIsDisable(addOperatoryEventTestData.get("event_On"),
				addOperatoryEventTestData.get("event_Date"),
				addOperatoryEventTestData.get("event_duration"),
				addOperatoryEventTestData.get("appointmentTimeSlot"));
		basePage.get().backTODoctorDashboard();
		loginPage.get().softAssert().assertAll();
	}

	/*
	 * Verify event cannot be added to last available Operatory in clinic Assuming
	 * that only two Operatories  is available in clinic,event will be added on first
	 * Operatories  by above @test
	 *
	 */
	@Test(description = VERIFY_ADDING_EVENT_ON_LAST_AVAILABLE_OPERATORY,dependsOnMethods = "verifyByOperatoryEvent")
	public void verifyAddingEventOnLastOpearatoryAvailable() {
		extentReport.get ().logger.log(LogStatus.INFO, VERIFY_ADDING_EVENT_ON_LAST_AVAILABLE_OPERATORY);
		basePage.get().selectClinicFrmHeader(addOperatoryEventTestData.get("event_Clinic"));
		doctorDashboard.get().clickonAppointmentAdd();
		loginPage.get().waitForPageLoad();
		eventPage.get().clickOnEvent();
		eventPage.get().selectByOperatoryEvent();
		eventPage.get().enterTitleName(addOperatoryEventTestData.get("event_Title"));
		eventPage.get().selectClinicInEvent(addOperatoryEventTestData.get("event_Clinic"));
		eventPage.get().selectOperatory("Operatory 2");
		eventPage.get().selectEventCategory(addOperatoryEventTestData.get("category"));
		eventPage.get().selectDateInEvent(addOperatoryEventTestData.get("event_Date"));
		eventPage.get().clickOnFullDayCheckBox ();
		eventPage.get().enterNotes(addOperatoryEventTestData.get("event_notes"));
		eventPage.get().clickOnEventSaveBtn();
		eventPage.get().verifyErrorOnAddingEventOnLastAvailableOperatory();
		Assert.assertTrue(basePage.get().verification().contains(ADD_EVENT_PAGE_TITLE));


	}

	/*
	 *Updating By Operatory Event and then checking on event listing
	 * Checking on Add Appointment page for the Updated date/time operatory is not available
	 * Checking on Add Appointment Operatory is available for date/time for which event was added before update.
	 */
	@Test(description = VERIFY_BY_OPERATORY_EVENT_UPDATE,dependsOnMethods = "verifyByOperatoryEvent")
	public void verifyUpdateByOperatoryEvent(){
		updateOperatoryEventTestData=SheetTest.prepareData(MODULE_NAME, TEST_DATA_SHEET_NAME, "A7", "O7");
		extentReport.get ().logger.log(LogStatus.PASS, VERIFY_BY_OPERATORY_EVENT_UPDATE);
		basePage.get().selectClinicFrmHeader(addOperatoryEventTestData.get("event_Clinic"));
		doctorDashboard.get().clickOnAppListBtn();
		eventListingPage.get().clickOnEventBtn();
		eventListingPage.get().selectFrmdateInEvent(addOperatoryEventTestData.get("event_Date"));
		eventListingPage.get().selectTodateInEvent(addOperatoryEventTestData.get("event_Date"));
		eventListingPage.get().selectType(addOperatoryEventTestData.get("event_Type"));
		eventListingPage.get().clickOnSearch();
		eventListingPage.get().clickOnEditBtn ( addOperatoryEventTestData.get("event_Title") );
		basePage.get().verifyPageTitle(ADD_EVENT_PAGE_TITLE);

		eventPage.get().verifyEnteredEventTitleInEdit ( addOperatoryEventTestData.get("event_Title"));
		eventPage.get().verifySelectedDateInEvent (  addOperatoryEventTestData.get("event_Date") );
		eventPage.get().verifySelectedClinicInEdit ( addOperatoryEventTestData.get("event_Clinic"));
		eventPage.get().verifySelectedOperatoryInEdit (  addOperatoryEventTestData.get("event_On"));
		eventPage.get().verifySelectedCategoryInEdit (  addOperatoryEventTestData.get("category") );
		eventPage.get().verifyFullDayEventCheckboxInEdit ();

		eventPage.get().enterTitleName( updateOperatoryEventTestData.get("event_Title"));
		eventPage.get().selectClinicInEvent(updateOperatoryEventTestData.get("event_Clinic"));
		eventPage.get().selectOperatory (updateOperatoryEventTestData.get("event_On") );
		eventPage.get().selectEventCategory(updateOperatoryEventTestData.get("category"));
		eventPage.get().selectDateInEvent(updateOperatoryEventTestData.get("event_Date"));
		eventPage.get().clickOnFullDayCheckBox ();
		eventPage.get().enterHours (updateOperatoryEventTestData.get("event_hours") );
		eventPage.get().enterMinutes (updateOperatoryEventTestData.get("event_mintues"));
		eventPage.get().selectAmPm (updateOperatoryEventTestData.get("AM_PM"));
		eventPage.get().enterEventDuration ( updateOperatoryEventTestData.get("event_duration"));
		eventPage.get().enterNotes (updateOperatoryEventTestData.get("event_notes") );
		eventPage.get().clickOnEventSaveBtn ();
////		eventPage.get().verifyEventUpdatedSuccessMsg ();
		basePage.get().verifyPageTitle(EVENTLISTING_PAGE_TITLE);
		basePage.get().backTODoctorDashboard ();
		basePage.get().selectClinicFrmHeader(updateOperatoryEventTestData.get("event_Clinic"));
		doctorDashboard.get().clickOnAppListBtn();
		eventListingPage.get().clickOnEventBtn();
		eventListingPage.get().verifyDateTimeDisplayOnEventList(updateOperatoryEventTestData.get("event_Title"),updateOperatoryEventTestData.get("event_DateTime"));
		eventListingPage.get().verifyDurationDisplayOnEventList(updateOperatoryEventTestData.get("event_Title"),updateOperatoryEventTestData.get("event_duration"));
		eventListingPage.get().verifyTitleDisplayOnEventlist(updateOperatoryEventTestData.get("event_Title"));
		eventListingPage.get().verifyEventTypeDisplayOnEventList(updateOperatoryEventTestData.get("event_Title"),updateOperatoryEventTestData.get("event_On"));
		eventListingPage.get().verifyCategoryDisplayOnEventList(updateOperatoryEventTestData.get("event_Title"), updateOperatoryEventTestData.get("category"));
		eventListingPage.get().verifyDoctorDisplayOnEventList(updateOperatoryEventTestData.get("event_Title"), "NA");
		eventListingPage.get().verifyClinicDisplayOnEventList(updateOperatoryEventTestData.get("event_Title"),updateOperatoryEventTestData.get("event_Clinic"));
		eventListingPage.get().verifyNotesDisplayOnEventList(updateOperatoryEventTestData.get("event_Title"),updateOperatoryEventTestData.get("event_notes"));
		eventListingPage.get().verifyEditAndDeletBtn(updateOperatoryEventTestData.get("event_Title"));
		basePage.get().backTODoctorDashboard();
		basePage.get().selectClinicFrmHeader(addOperatoryEventTestData.get("event_Clinic"));
		doctorDashboard.get().clickonAppointmentAdd();
		eventPage.get().checkOperatoryIsSelectableInAddAppt (  addOperatoryEventTestData.get("event_On"),
				addOperatoryEventTestData.get("event_Date"),
				addOperatoryEventTestData.get("event_duration"),
				addOperatoryEventTestData.get("appointmentTimeSlot"));
		basePage.get().backTODoctorDashboard();
		basePage.get().selectClinicFrmHeader(updateOperatoryEventTestData.get("event_Clinic"));
		doctorDashboard.get().clickonAppointmentAdd();
		eventPage.get().checkEventAddedOperatoryIsDisable(updateOperatoryEventTestData.get("event_On"),
				updateOperatoryEventTestData.get("event_Date"),
				updateOperatoryEventTestData.get("event_duration"),
				updateOperatoryEventTestData.get("appointmentTimeSlot"));


	}

	@Test(description = VERIFY_BY_OPERATORY_EVENT_DELETED,dependsOnMethods = "verifyUpdateByOperatoryEvent")
	public void verifyDeleteByOperatoryEvent(){
		extentReport.get ().logger.log ( LogStatus.INFO,VERIFY_BY_OPERATORY_EVENT_DELETED );
		basePage.get().selectClinicFrmHeader(updateOperatoryEventTestData.get("event_Clinic"));
		doctorDashboard.get().clickOnAppListBtn();
		eventListingPage.get().clickOnEventBtn();
		eventListingPage.get().selectFrmdateInEvent(updateOperatoryEventTestData.get("event_Date"));
		eventListingPage.get().selectTodateInEvent(updateOperatoryEventTestData.get("event_Date"));
		eventListingPage.get().selectType(updateOperatoryEventTestData.get("event_Type"));
		eventListingPage.get().selectCategroy(updateOperatoryEventTestData.get("category"));
		eventListingPage.get().clickOnSearch();
		eventListingPage.get().verifyEditAndDeletBtn(updateOperatoryEventTestData.get("event_Title"));
		eventListingPage.get().clickOnDeleteBtn(updateOperatoryEventTestData.get("event_Title"));
		eventListingPage.get().clickOnNoBtnDeletePopUp();
		eventListingPage.get().clickOnDeleteBtn(updateOperatoryEventTestData.get("event_Title"));
		eventListingPage.get().clickOnYesBtnDeletePopUp();
//		eventPage.get().verifyEventDeleteSuccessMsg ();
		basePage.get().verifyPageTitle(EVENTLISTING_PAGE_TITLE);
		eventListingPage.get().verifyAfterDelete(updateOperatoryEventTestData.get("event_Title"));
		basePage.get().backTODoctorDashboard();
		basePage.get().selectClinicFrmHeader(updateOperatoryEventTestData.get("event_Clinic"));
		doctorDashboard.get().clickonAppointmentAdd ();
		eventPage.get().checkOperatoryIsSelectableInAddAppt (updateOperatoryEventTestData.get("event_On"),
				updateOperatoryEventTestData.get("event_Date"),
				updateOperatoryEventTestData.get("event_duration"),
				updateOperatoryEventTestData.get("appointmentTimeSlot"));

	}
	/*
	 * Verify tentative appointment is present then user can add event for same time slot
	 * tentative appointment.
	 * */
	@Test(description = VERIFY_ADDING_EVENT_FOR_A_SLOT_FOR_WHICH_TENTATIVE_APPT_ALREADY_BOOKED)
	public void verifyAddEventIfTentativeAppointmentAlreadyBookedForTime() throws ParseException, java.text.ParseException {
		tentativeAppointmentTestData=SheetTest.prepareData("Appointment", "PatientAppointment", "A8", "W8");
		extentReport.get ().logger.log(LogStatus.PASS,VERIFY_ADDING_EVENT_FOR_A_SLOT_FOR_WHICH_TENTATIVE_APPT_ALREADY_BOOKED);
		basePage.get().selectClinicFrmHeader(tentativeAppointmentTestData.get("clinicLocation"));
		doctorDashboard.get().clickonAppointmentAdd();
		loginPage.get().waitForPageLoad();
		appointmentAddPage.get().checkAppointmentInClinic();
		appointmentAddPage.get().selectInClinic();
		appointmentAddPage.get().enterPatientName(tentativeAppointmentTestData.get("patient_name"));
		appointmentAddPage.get().enterMobileNumber(tentativeAppointmentTestData.get("patient_mobile"));
		appointmentAddPage.get().selectClinicFromDropdown(tentativeAppointmentTestData.get("clinicLocation"));
		appointmentAddPage.get().appointmentDate(tentativeAppointmentTestData.get("appointmentDate"));
		appointmentAddPage.get().selectTimeSlotFromDropdown(tentativeAppointmentTestData.get("slot"));
		appointmentAddPage.get().enterTimeDuration(tentativeAppointmentTestData.get("duration"));
		appointmentAddPage.get().clickOnTentative();
		appointmentAddPage.get().selectDoctorFromDropdown(tentativeAppointmentTestData.get("doctor"));
		appointmentAddPage.get().selectReferralFromDropdown("Doctor");
		appointmentAddPage.get().clickOnSaveBtn();
		basePage.get().verifyPageTitle(DOCTOR_DASHBOARD_PAGE_TITLE);

		basePage.get().selectClinicFrmHeader(tentativeAppointmentTestData.get("clinicLocation"));
		doctorDashboard.get().clickonAppointmentAdd();
		loginPage.get().waitForPageLoad();
		eventPage.get().clickOnEvent();
		eventPage.get().enterTitleName("Tentative Appointment booked for given slot");
		eventPage.get().selectClinicInEvent(tentativeAppointmentTestData.get("clinicLocation"));
		eventPage.get().selectDoctorInEvent(tentativeAppointmentTestData.get("doctor"));
		eventPage.get().selectEventCategory(addDoctorEventTestData.get("category"));
		eventPage.get().selectDateInEvent(tentativeAppointmentTestData.get("appointmentDate"));
		eventPage.get().enterHours(loginPage.get().convert24HourFormatTo12HourFormat(tentativeAppointmentTestData.get("slot")).split(":")[0]);
		eventPage.get().enterMinutes(loginPage.get().convert24HourFormatTo12HourFormat(tentativeAppointmentTestData.get("slot")).split(":")[1]);
		eventPage.get().selectAmPm(loginPage.get().convert24HourFormatTo12HourFormat(tentativeAppointmentTestData.get("slot")).split(" ")[1].toUpperCase());
		eventPage.get().enterEventDuration(tentativeAppointmentTestData.get("duration"));
		eventPage.get().enterNotes(addDoctorEventTestData.get("notes"));
		eventPage.get().clickOnEventSaveBtn();
//		eventPage.get().verifyEventAddSuccessMsg ();
		basePage.get().selectClinicFrmHeader(tentativeAppointmentTestData.get("clinicLocation"));
		doctorDashboard.get().clickonAppointmentAdd();
		eventPage.get().checkEventAddedDoctorIsDisable(tentativeAppointmentTestData.get("doctor"),
				tentativeAppointmentTestData.get("appointmentDate"),
				tentativeAppointmentTestData.get("duration"),tentativeAppointmentTestData.get("slot"));


	}


	/*
	 * Verify Adding By Room Event
	 * Verify the event details on Event
	 * Check event added Room is non selectable on Add Appointment page
	 */

	@Test(description = VERIFY_BY_ROOM_EVENT)
	public void verifyByRoomEvent() {
		addRoomEventTestData=SheetTest.prepareData(MODULE_NAME, TEST_DATA_SHEET_NAME, "A10", "O10");
		extentReport.get ().logger.log(LogStatus.PASS, VERIFY_BY_ROOM_EVENT);
		basePage.get().selectClinicFrmHeader(addRoomEventTestData.get("event_Clinic"));
		doctorDashboard.get().clickonAppointmentAdd();
		loginPage.get().waitForPageLoad();
		eventPage.get().clickOnEvent();
		eventPage.get().selectClinicInEvent(addRoomEventTestData.get("event_Clinic"));
		eventPage.get().selectByRoomEvent();
		eventPage.get().enterTitleName(addRoomEventTestData.get("event_Title"));
		eventPage.get().selectRoom(addRoomEventTestData.get("event_On"));
		eventPage.get().selectEventCategory(addRoomEventTestData.get("category"));
		eventPage.get().selectDateInEvent(addRoomEventTestData.get("event_Date"));
		eventPage.get().enterHours(addRoomEventTestData.get("event_hours"));
		eventPage.get().enterMinutes(addRoomEventTestData.get("event_mintues"));
		eventPage.get().selectAmPm(addRoomEventTestData.get("AM_PM"));
		eventPage.get().enterEventDuration(addRoomEventTestData.get("event_duration"));
		eventPage.get().enterNotes(addRoomEventTestData.get("event_DateTime"));
		eventPage.get().clickOnEventSaveBtn();
//		eventPage.get().verifyEventAddSuccessMsg ();
		basePage.get().verifyPageTitle(DOCTOR_DASHBOARD_PAGE_TITLE);
		basePage.get().selectClinicFrmHeader(addRoomEventTestData.get("event_Clinic"));
		doctorDashboard.get().clickOnAppListBtn();
		eventListingPage.get().clickOnEventBtn();
		eventListingPage.get().selectFrmdateInEvent(addRoomEventTestData.get("event_Date"));
		eventListingPage.get().selectTodateInEvent(addRoomEventTestData.get("event_Date"));
		eventListingPage.get().selectType(addRoomEventTestData.get("event_Type"));
		eventListingPage.get().selectCategroy(addRoomEventTestData.get("category"));
		eventListingPage.get().clickOnSearch();
		eventListingPage.get().verifyDateTimeDisplayOnEventList(addRoomEventTestData.get("event_Title"),addRoomEventTestData.get("event_DateTime"));
		eventListingPage.get().verifyDurationDisplayOnEventList(addRoomEventTestData.get("event_Title"), addRoomEventTestData.get("event_duration"));
		eventListingPage.get().verifyTitleDisplayOnEventlist(addRoomEventTestData.get("event_Title"));
		eventListingPage.get().verifyEventTypeDisplayOnEventList(addRoomEventTestData.get("event_Title"), addRoomEventTestData.get("event_On"));
		eventListingPage.get().verifyCategoryDisplayOnEventList(addRoomEventTestData.get("event_Title"), addRoomEventTestData.get("category"));
		eventListingPage.get().verifyDoctorDisplayOnEventList(addRoomEventTestData.get("event_Title"), "NA");
		eventListingPage.get().verifyClinicDisplayOnEventList(addRoomEventTestData.get("event_Title"),addRoomEventTestData.get("event_Clinic"));
		eventListingPage.get().verifyNotesDisplayOnEventList(addRoomEventTestData.get("event_Title"), addRoomEventTestData.get("event_DateTime"));
		eventListingPage.get().verifyEditAndDeletBtn(addRoomEventTestData.get("event_Title"));
		basePage.get().backTODoctorDashboard();
		basePage.get().selectClinicFrmHeader(addRoomEventTestData.get("event_Clinic"));
		doctorDashboard.get().clickonAppointmentAdd();
		appointmentAddPage.get().enterMobileNumber("1112211111");
		appointmentAddPage.get().selectEConsult();
		eventPage.get().checkRoomIsDisableOnAddAppointment (addRoomEventTestData.get("event_On"),
				addRoomEventTestData.get("event_Date"),
				addRoomEventTestData.get("event_duration"),
				addRoomEventTestData.get("appointmentTimeSlot"));

		loginPage.get().softAssert().assertAll();

	}

	@Test(description = VERIFY_BY_ROOM_EVENT_UPDATE,dependsOnMethods = "verifyByRoomEvent")
	public void verifyUpdateByRoomEvent(){
		updateRoomEventTestData=SheetTest.prepareData(MODULE_NAME, TEST_DATA_SHEET_NAME, "A11", "O11");
		extentReport.get ().logger.log(LogStatus.PASS, VERIFY_BY_ROOM_EVENT_UPDATE);
		basePage.get().selectClinicFrmHeader(addRoomEventTestData.get("event_Clinic"));
		doctorDashboard.get().clickOnAppListBtn();
		eventListingPage.get().clickOnEventBtn();
		eventListingPage.get().selectFrmdateInEvent(addRoomEventTestData.get("event_Date"));
		eventListingPage.get().selectTodateInEvent(addRoomEventTestData.get("event_Date"));
		eventListingPage.get().selectType(addRoomEventTestData.get("event_Type"));
		eventListingPage.get().selectCategroy(addRoomEventTestData.get("category"));
		eventListingPage.get().clickOnSearch();
		eventListingPage.get().clickOnEditBtn ( addRoomEventTestData.get("event_Title") );
		basePage.get().verifyPageTitle(ADD_EVENT_PAGE_TITLE);

		eventPage.get().verifyEnteredEventTitleInEdit ( addRoomEventTestData.get("event_Title"));
		eventPage.get().verifySelectedDateInEvent ( addRoomEventTestData.get("event_Date"));
		eventPage.get().verifySelectedClinicInEdit (addRoomEventTestData.get("event_Clinic"));
		eventPage.get().verifySelectedRoomInEdit ( addRoomEventTestData.get("event_On") );
		eventPage.get().verifySelectedCategoryInEdit ( addRoomEventTestData.get("category"));
		eventPage.get().getHours (addRoomEventTestData.get("event_hours"));
		eventPage.get().getMinutes ( addRoomEventTestData.get("event_mintues"));
		eventPage.get().getAMPM (addRoomEventTestData.get("AM_PM"));
		eventPage.get().getDurations (addRoomEventTestData.get("event_duration"));
		eventPage.get().getEventNotes ( addRoomEventTestData.get("event_DateTime") );

		eventPage.get().enterTitleName(updateRoomEventTestData.get("event_Title"));
		eventPage.get().selectClinicInEvent(updateRoomEventTestData.get("event_Clinic"));
		eventPage.get().selectRoom (updateRoomEventTestData.get("event_On"));
		eventPage.get().selectEventCategory(updateRoomEventTestData.get("category"));
		eventPage.get().selectDateInEvent(updateRoomEventTestData.get("event_Date"));
		eventPage.get().enterHours (updateRoomEventTestData.get("event_hours"));
		eventPage.get().enterMinutes (updateRoomEventTestData.get("event_mintues"));
		eventPage.get().selectAmPm (updateRoomEventTestData.get("AM_PM"));
		eventPage.get().enterEventDuration (updateRoomEventTestData.get("event_duration"));
		eventPage.get().enterNotes (updateRoomEventTestData.get("event_notes"));
		eventPage.get().clickOnEventSaveBtn ();
		basePage.get().verifyPageTitle(EVENTLISTING_PAGE_TITLE );
		basePage.get().backTODoctorDashboard ();
		basePage.get().selectClinicFrmHeader(updateRoomEventTestData.get("event_Clinic"));
		doctorDashboard.get().clickOnAppListBtn();
		eventListingPage.get().clickOnEventBtn();
		eventListingPage.get().verifyDateTimeDisplayOnEventList(updateRoomEventTestData.get("event_Title"),updateRoomEventTestData.get("event_DateTime"));
		eventListingPage.get().verifyDurationDisplayOnEventList(updateRoomEventTestData.get("event_Title"), updateRoomEventTestData.get("event_duration"));
		eventListingPage.get().verifyTitleDisplayOnEventlist(updateRoomEventTestData.get("event_Title"));
		eventListingPage.get().verifyEventTypeDisplayOnEventList(updateRoomEventTestData.get("event_Title"),updateRoomEventTestData.get("event_On"));
		eventListingPage.get().verifyCategoryDisplayOnEventList(updateRoomEventTestData.get("event_Title"), updateRoomEventTestData.get("category"));
		eventListingPage.get().verifyDoctorDisplayOnEventList( updateRoomEventTestData.get("event_Title"), "NA");
		eventListingPage.get().verifyClinicDisplayOnEventList(updateRoomEventTestData.get("event_Title"), updateRoomEventTestData.get("event_Clinic"));
		eventListingPage.get().verifyNotesDisplayOnEventList(updateRoomEventTestData.get("event_Title"), updateRoomEventTestData.get("event_notes"));
		eventListingPage.get().verifyEditAndDeletBtn(updateRoomEventTestData.get("event_Title"));
		basePage.get().backTODoctorDashboard();
		basePage.get().selectClinicFrmHeader(addRoomEventTestData.get("event_Clinic"));
		doctorDashboard.get().clickonAppointmentAdd();
		appointmentAddPage.get().enterMobileNumber("1112211111");
		appointmentAddPage.get().selectEConsult ();
		eventPage.get().checkRoomIsSelectableOnAddAppt ( addRoomEventTestData.get("event_On"),
				addRoomEventTestData.get("event_Date"),
				addRoomEventTestData.get("event_duration"),
				addRoomEventTestData.get("appointmentTimeSlot")  );
		basePage.get().backTODoctorDashboard();
		basePage.get().selectClinicFrmHeader(updateRoomEventTestData.get("event_Clinic"));
		doctorDashboard.get().clickonAppointmentAdd();
		appointmentAddPage.get().selectEConsult ();
		eventPage.get().checkRoomIsDisableOnAddAppointment (updateRoomEventTestData.get("event_On"),
				updateRoomEventTestData.get("event_Date"),
				updateRoomEventTestData.get("event_duration"),
				updateRoomEventTestData.get("appointmentTimeSlot"));


	}

	@Test(description = VERIFY_BY_ROOM_EVENT_DELETED,dependsOnMethods = "verifyUpdateByRoomEvent")
	public void verifyDeleteByRoomEvent(){
		extentReport.get ().logger.log(LogStatus.PASS, VERIFY_BY_ROOM_EVENT_DELETED);
		basePage.get().selectClinicFrmHeader(updateRoomEventTestData.get("event_Clinic"));
		doctorDashboard.get().clickOnAppListBtn();
		eventListingPage.get().clickOnEventBtn();
		eventListingPage.get().selectFrmdateInEvent(updateRoomEventTestData.get("event_Date"));
		eventListingPage.get().selectTodateInEvent(updateRoomEventTestData.get("event_Date"));
		eventListingPage.get().selectType (updateRoomEventTestData.get("event_Type"));
		eventListingPage.get().clickOnSearch();
		eventListingPage.get().verifyEditAndDeletBtn(updateRoomEventTestData.get("event_Title"));
		eventListingPage.get().clickOnDeleteBtn(updateRoomEventTestData.get("event_Title"));
		eventListingPage.get().clickOnNoBtnDeletePopUp();
		eventListingPage.get().clickOnDeleteBtn(updateRoomEventTestData.get("event_Title"));
		eventListingPage.get().clickOnYesBtnDeletePopUp();
		eventListingPage.get().verifyAfterDelete(updateRoomEventTestData.get("event_Title"));
		Assert.assertTrue(appointmentsLisitngPage.get().verifiyAppListHP());
		basePage.get().backTODoctorDashboard();
		basePage.get().selectClinicFrmHeader(updateRoomEventTestData.get("event_Clinic"));
		doctorDashboard.get().clickonAppointmentAdd();
		appointmentAddPage.get().enterMobileNumber("1112211111");
		appointmentAddPage.get().selectEConsult ();
		eventPage.get().checkRoomIsSelectableOnAddAppt (updateRoomEventTestData.get("event_On"),
				updateRoomEventTestData.get("event_Date"),
				updateRoomEventTestData.get("event_duration"),
				updateRoomEventTestData.get("appointmentTimeSlot")   );
		basePage.get().backTODoctorDashboard();
		basePage.get().verifyPageTitle(DOCTOR_DASHBOARD_PAGE_TITLE);

	}

	/*
	 ** Check when event is added for Time already Passed
	 * and error message show on save
	 */

	@Test(description = EVENT_TIME__ALREADY_PASSED)
	public void addingEventForPastTime() {
		extentReport.get ().logger.log(LogStatus.PASS, EVENT_TIME__ALREADY_PASSED);
		basePage.get().selectClinicFrmHeader(addDoctorEventTestData.get("event_Clinic"));
		doctorDashboard.get().clickonAppointmentAdd();
		loginPage.get().waitForPageLoad();
		eventPage.get().clickOnEvent();
		eventPage.get().enterTitleName(addDoctorEventTestData.get("event_Title"));
		eventPage.get().selectClinicInEvent(addDoctorEventTestData.get("event_Clinic"));
		eventPage.get().selectDoctorInEvent(addDoctorEventTestData.get("event_On"));
		eventPage.get().selectEventCategory(addDoctorEventTestData.get("category"));
		eventPage.get().enterHours("12");
		eventPage.get().enterMinutes("00");
		eventPage.get().selectAmPm("AM");
		eventPage.get().enterEventDuration("90");
		eventPage.get().clickOnEventSaveBtn();
		eventPage.get().verifyEventTimePassedErrorMessage();

		eventPage.get().clickOnFullDayCheckBox ();
		eventPage.get().clickOnEventSaveBtn();
		eventPage.get().verifyEventTimePassedErrorMessage();
		Assert.assertTrue(basePage.get().verification().contains(ADD_EVENT_PAGE_TITLE));
	}

	/*
	 ** Verify Adding By Scanner Event and then verify the event details on Event
	 * Listing Check event added Scanner is not available on Add Appointment page
	 */

	@Test(description = VERIFY_BY_SCANNER_EVENT)

	public void verifyByScannerEvent() {
		addScannerRoomEvent=SheetTest.prepareData(MODULE_NAME, TEST_DATA_SHEET_NAME, "A15", "O15");
		extentReport.get ().logger.log(LogStatus.PASS, VERIFY_BY_SCANNER_EVENT);
		basePage.get().selectClinicFrmHeader(addScannerRoomEvent.get("event_Clinic"));
		doctorDashboard.get().clickonAppointmentAdd();
		loginPage.get().waitForPageLoad();
		eventPage.get().clickOnEvent();
		eventPage.get().selectClinicInEvent(addScannerRoomEvent.get("event_Clinic"));
		eventPage.get().selectByScannerEvent();
		eventPage.get().enterTitleName(addScannerRoomEvent.get("event_Title"));
		eventPage.get().selectScanner(addScannerRoomEvent.get("event_On"));
		eventPage.get().selectEventCategory(addScannerRoomEvent.get("category"));
		eventPage.get().selectDateInEvent(addScannerRoomEvent.get("event_Date"));
		eventPage.get().clickOnFullDayCheckBox ();
		eventPage.get().enterNotes(addScannerRoomEvent.get("event_notes"));
		eventPage.get().clickOnEventSaveBtn();
		basePage.get().selectClinicFrmHeader(addScannerRoomEvent.get("event_Clinic"));
		doctorDashboard.get().clickOnAppListBtn();
		eventListingPage.get().clickOnEventBtn();
		eventListingPage.get().selectFrmdateInEvent(addScannerRoomEvent.get("event_Date"));
		eventListingPage.get().selectTodateInEvent(addScannerRoomEvent.get("event_Date"));
		eventListingPage.get().selectType(addScannerRoomEvent.get("event_Type"));
		eventListingPage.get().selectCategroy(addScannerRoomEvent.get("category"));
		eventListingPage.get().clickOnSearch();
		eventListingPage.get().verifyDateTimeDisplayOnEventList( addScannerRoomEvent.get("event_Title"),addScannerRoomEvent.get("event_DateTime"));
		eventListingPage.get().verifyDurationDisplayOnEventList( addScannerRoomEvent.get("event_Title"), addScannerRoomEvent.get("event_duration"));
		eventListingPage.get().verifyTitleDisplayOnEventlist(addScannerRoomEvent.get("event_Title"));
		eventListingPage.get().verifyEventTypeDisplayOnEventList( addScannerRoomEvent.get("event_Title"),addScannerRoomEvent.get("event_On"));
		eventListingPage.get().verifyCategoryDisplayOnEventList( addScannerRoomEvent.get("event_Title"), addScannerRoomEvent.get("category"));
		eventListingPage.get().verifyDoctorDisplayOnEventList( addScannerRoomEvent.get("event_Title"), "NA");
		eventListingPage.get().verifyClinicDisplayOnEventList(addScannerRoomEvent.get("event_Title"),addScannerRoomEvent.get("event_Clinic"));
		eventListingPage.get().verifyNotesDisplayOnEventList( addScannerRoomEvent.get("event_Title"), addScannerRoomEvent.get("event_notes"));
		eventListingPage.get().verifyEditAndDeletBtn(addScannerRoomEvent.get("event_Title"));
		basePage.get().backTODoctorDashboard();
		basePage.get().selectClinicFrmHeader(addScannerRoomEvent.get("event_Clinic"));
		doctorDashboard.get().clickonAppointmentAdd();
		eventPage.get().checkEventAddedScannerIsDisable(addScannerRoomEvent.get("event_On"),
				addScannerRoomEvent.get("event_Date"),
				addScannerRoomEvent.get("event_duration"),
				addScannerRoomEvent.get("appointmentTimeSlot"));

	}

	/*
	 * Verify event cannot be added to last available Scanner in clinic Assuming
	 * that only two Scanner is available in clinic,event will be added on first
	 * Scanner by above @test
	 *
	 */
	@Test(description = VERIFY_ADDING_EVENT_ON_LAST_AVAILABLE_SCANNER,dependsOnMethods = "verifyByScannerEvent")

	public void verifyAddingEventOnLastScannerAvailable() {
		extentReport.get ().logger.log(LogStatus.PASS, VERIFY_BY_SCANNER_EVENT_UPDATE);
		basePage.get().selectClinicFrmHeader(addScannerRoomEvent.get("event_Clinic"));
		doctorDashboard.get().clickonAppointmentAdd();
		loginPage.get().waitForPageLoad();
		eventPage.get().clickOnEvent();
		eventPage.get().selectByScannerEvent();
		eventPage.get().enterTitleName(addScannerRoomEvent.get("event_Title"));
		eventPage.get().selectClinicInEvent(addScannerRoomEvent.get("event_Clinic"));
		eventPage.get().selectScanner("Scanner 202");
		eventPage.get().selectEventCategory(addScannerRoomEvent.get("category"));
		eventPage.get().selectDateInEvent(addScannerRoomEvent.get("event_Date"));
		eventPage.get().clickOnFullDayCheckBox ();
		eventPage.get().enterNotes(addScannerRoomEvent.get("event_notes"));
		eventPage.get().clickOnEventSaveBtn();
		eventPage.get().verifyErrorOnAddingEventOnLastAvailableScanner();
		Assert.assertTrue(basePage.get().verification().contains(ADD_EVENT_PAGE_TITLE));

	}

	/*
	 * Update Scanner Event Added by @test verifyByScannerEvent
	 * Verify Updated Scanner details on Event Listing
	 * Checking in add appointment the Event is added on the Scanner
	 * Checking in the add appointment Scanner is available for the previous time slot
	 *
	 */
	@Test(description = VERIFY_BY_SCANNER_EVENT_UPDATE,dependsOnMethods = "verifyByScannerEvent")
	public void verifyUpdateByScannerEvent(){
		updateScannerRoomEvent=SheetTest.prepareData(MODULE_NAME, TEST_DATA_SHEET_NAME, "A16", "O16");
		extentReport.get ().logger.log(LogStatus.PASS, VERIFY_BY_SCANNER_EVENT_UPDATE);
		basePage.get().selectClinicFrmHeader(addScannerRoomEvent.get("event_Clinic"));
		doctorDashboard.get().clickOnAppListBtn();
		eventListingPage.get().clickOnEventBtn();
		eventListingPage.get().selectFrmdateInEvent(addScannerRoomEvent.get("event_Date"));
		eventListingPage.get().selectTodateInEvent(addScannerRoomEvent.get("event_Date"));
		eventListingPage.get().selectType(addScannerRoomEvent.get("event_Type"));
		eventListingPage.get().clickOnSearch();
		eventListingPage.get().verifyEditAndDeletBtn ( addScannerRoomEvent.get("event_Title"));
		eventListingPage.get().clickOnEditBtn ( addScannerRoomEvent.get("event_Title") );
		Assert.assertEquals ( loginPage.get().getTitle (),ADD_EVENT_PAGE_TITLE);

		eventPage.get().verifyEnteredEventTitleInEdit ( addScannerRoomEvent.get("event_Title"));
		eventPage.get().verifySelectedDateInEvent ( addScannerRoomEvent.get("event_Date"));
		eventPage.get().verifySelectedClinicInEdit (addScannerRoomEvent.get("event_Clinic"));
		eventPage.get().verifySelectedScannerInEdit ( addScannerRoomEvent.get("event_On"));
		eventPage.get().verifySelectedCategoryInEdit ( addScannerRoomEvent.get("category") );
		eventPage.get().verifyFullDayEventCheckboxInEdit ();
		eventPage.get().getEventNotes ( addScannerRoomEvent.get("event_notes"));


		eventPage.get().enterTitleName(updateScannerRoomEvent.get("event_Title"));
		eventPage.get().selectClinicInEvent(updateScannerRoomEvent.get("event_Clinic"));
		eventPage.get().selectScanner (updateScannerRoomEvent.get("event_On"));
		eventPage.get().selectEventCategory(updateScannerRoomEvent.get("category"));
		eventPage.get().selectDateInEvent(updateScannerRoomEvent.get("event_Date"));
		eventPage.get().clickOnFullDayCheckBox ();
		eventPage.get().enterHours (updateScannerRoomEvent.get("event_hours"));
		eventPage.get().enterMinutes (updateScannerRoomEvent.get("event_mintues"));
		eventPage.get().selectAmPm (updateScannerRoomEvent.get("AM_PM"));
		eventPage.get().enterEventDuration (updateScannerRoomEvent.get("event_duration") );
		eventPage.get().enterNotes (updateScannerRoomEvent.get("event_notes"));
		eventPage.get().clickOnEventSaveBtn ();
		Assert.assertEquals ( basePage.get().verification (),EVENTLISTING_PAGE_TITLE);
		basePage.get().backTODoctorDashboard ();
		basePage.get().selectClinicFrmHeader(updateScannerRoomEvent.get("event_Clinic"));
		doctorDashboard.get().clickOnAppListBtn();
		eventListingPage.get().clickOnEventBtn();
		eventListingPage.get().verifyDateTimeDisplayOnEventList(updateScannerRoomEvent.get("event_Title"),updateScannerRoomEvent.get("event_DateTime"));
		eventListingPage.get().verifyDurationDisplayOnEventList(updateScannerRoomEvent.get("event_Title"),updateScannerRoomEvent.get("event_duration"));
		eventListingPage.get().verifyTitleDisplayOnEventlist(updateScannerRoomEvent.get("event_Title"));
		eventListingPage.get().verifyEventTypeDisplayOnEventList(updateScannerRoomEvent.get("event_Title"),updateScannerRoomEvent.get("event_On"));
		eventListingPage.get().verifyCategoryDisplayOnEventList(updateScannerRoomEvent.get("event_Title"), updateScannerRoomEvent.get("category"));
		eventListingPage.get().verifyDoctorDisplayOnEventList(updateScannerRoomEvent.get("event_Title"), "NA");
		eventListingPage.get().verifyClinicDisplayOnEventList(updateScannerRoomEvent.get("event_Title"),updateScannerRoomEvent.get("event_Clinic"));
		eventListingPage.get().verifyNotesDisplayOnEventList(updateScannerRoomEvent.get("event_Title"), updateScannerRoomEvent.get("event_notes"));
		eventListingPage.get().verifyEditAndDeletBtn(updateScannerRoomEvent.get("event_Title"));
		basePage.get().backTODoctorDashboard();
		basePage.get().selectClinicFrmHeader(addScannerRoomEvent.get("event_Clinic"));
		doctorDashboard.get().clickonAppointmentAdd();
		eventPage.get().checkScannerIsSelectableOnAddAppt (addScannerRoomEvent.get("event_On"),
				addScannerRoomEvent.get("event_Date"),
				addScannerRoomEvent.get("event_duration"),
				addScannerRoomEvent.get("appointmentTimeSlot")  );
		basePage.get().backTODoctorDashboard();
		basePage.get().selectClinicFrmHeader(updateScannerRoomEvent.get("event_Clinic"));
		doctorDashboard.get().clickonAppointmentAdd();
		eventPage.get().checkEventAddedScannerIsDisable(updateScannerRoomEvent.get("event_On"),
				updateScannerRoomEvent.get("event_Date"),
				updateScannerRoomEvent.get("event_duration"),
				updateScannerRoomEvent.get("appointmentTimeSlot"));

	}



	/*
	 *Delete the Added Scanner Event
	 *Checking on Add appointment after Scanner Event is deleted Scanner is available
	 *
	 */

	@Test(description = VERIFY_BY_SCANNER_EVENT_DELETED,dependsOnMethods = "verifyUpdateByScannerEvent")
	public void verifyDeleteByScannerEvent(){
		extentReport.get ().logger.log(LogStatus.PASS, VERIFY_BY_SCANNER_EVENT_DELETED);
		basePage.get().selectClinicFrmHeader(updateScannerRoomEvent.get("event_Clinic"));
		doctorDashboard.get().clickOnAppListBtn();
		eventListingPage.get().clickOnEventBtn();
		eventListingPage.get().selectFrmdateInEvent(updateScannerRoomEvent.get("event_Date"));
		eventListingPage.get().selectTodateInEvent(updateScannerRoomEvent.get("event_Date"));
		eventListingPage.get().selectType (updateScannerRoomEvent.get("event_Type"));
		eventListingPage.get().clickOnSearch();
		eventListingPage.get().verifyEditAndDeletBtn(updateScannerRoomEvent.get("event_Title"));
		eventListingPage.get().clickOnDeleteBtn(updateScannerRoomEvent.get("event_Title"));
		eventListingPage.get().clickOnNoBtnDeletePopUp();
		eventListingPage.get().clickOnDeleteBtn(updateScannerRoomEvent.get("event_Title"));
		eventListingPage.get().clickOnYesBtnDeletePopUp();
		eventListingPage.get().verifyAfterDelete(updateScannerRoomEvent.get("event_Title"));
		basePage.get().verifyPageTitle(EVENTLISTING_PAGE_TITLE);
		basePage.get().backTODoctorDashboard();
		basePage.get().selectClinicFrmHeader(updateScannerRoomEvent.get("event_Clinic"));
		doctorDashboard.get().clickonAppointmentAdd();
		eventPage.get().checkScannerIsSelectableOnAddAppt (updateScannerRoomEvent.get("event_On"),
				updateScannerRoomEvent.get("event_Date"),
				updateScannerRoomEvent.get("event_duration"),
				updateScannerRoomEvent.get("appointmentTimeSlot"));

	}

	/*
	 * Check Cancel button working on Add Event Page and Redirection after click on cancel button
	 *Verify Reset button working on Add Event Page.
	 */
	@Test(description = VERIFY_CANCEL_BTN)
	public void verifyCancelAndResetBtnWorkingOnAddEvent() {
		extentReport.get ().logger.log(LogStatus.PASS, VERIFY_CANCEL_BTN);
		extentReport.get ().logger.log ( LogStatus.INFO,VERIFY_RESET_BTN );
		basePage.get().selectClinicFrmHeader (addDoctorEventTestData.get("event_Clinic"));
		doctorDashboard.get().clickonAppointmentAdd();
		eventPage.get().clickOnEvent ();
		eventPage.get().enterTitleName(addDoctorEventTestData.get("event_Title"));
		eventPage.get().selectClinicInEvent(addDoctorEventTestData.get("event_Clinic"));
		eventPage.get().selectDoctorInEvent(addDoctorEventTestData.get("event_On"));
		eventPage.get().selectEventCategory( addDoctorEventTestData.get("category"));
		eventPage.get().selectDateInEvent( addDoctorEventTestData.get("event_Date"));
		eventPage.get().enterHours(addDoctorEventTestData.get("event_hours"));
		eventPage.get().enterMinutes(addDoctorEventTestData.get("event_mintues"));
		eventPage.get().selectAmPm(addDoctorEventTestData.get("AM_PM"));
		eventPage.get().enterEventDuration(addDoctorEventTestData.get("event_duration"));
		eventPage.get().enterNotes ( addDoctorEventTestData.get("event_notes") );
		eventPage.get().clickOnEventResetBtn ();

		eventPage.get().verifyEnteredEventTitleInEdit ( "");
		eventPage.get().verifySelectedClinicInEdit (addDoctorEventTestData.get("event_Clinic"));
		eventPage.get().verifySelectedDoctorInEdit ( "Select Doctor" );
		eventPage.get().verifySelectedDateInEvent (eventPage.get().getCurrentDate ());
		eventPage.get().verifySelectedCategoryInEdit ( "Select Category");
		eventPage.get().getEventNotes ( "" );
		eventPage.get().getHours ( "" );
		eventPage.get().getDurations ( "" );
		eventPage.get().getMinutes ( "" );


		eventPage.get().clickOnCancelBtn();
		Assert.assertTrue(basePage.get().verification().contains(DOCTOR_DASHBOARD_PAGE_TITLE));
	}


	/*
	 * Check All the Filter and Button Present on Event Listing Check Column Header
	 * on Event Listing
	 */

	@Test(description = VERIFY_ELEMENT_ON_EVENT_LIST)
	public void verifyElementOnEventList() {
		extentReport.get ().logger.log(LogStatus.PASS, VERIFY_ELEMENT_ON_EVENT_LIST);
		doctorDashboard.get().clickOnAppListBtn();
		loginPage.get().waitForPageLoad();
		eventListingPage.get().clickOnEventBtn();
		eventListingPage.get().verifyAllMandatoryWebElement();
		basePage.get().verifyPageTitle(EVENTLISTING_PAGE_TITLE);
	}

	/*
	 * Check Add Event Button on Event Listing
	 * Check On Click Redirecting to Add Event
	 * Check on click on Cancel button redirect to event listing
	 * Add event and check redirecting to event listing
	 */
	@Test(description = VERIFY_ADD_EVENT_FROM_EVENT_LISTING)
	public void verifyAddEventFromEventListingAndRedirection () {
		extentReport.get ().logger.log(LogStatus.PASS, VERIFY_ADD_EVENT_FROM_EVENT_LISTING);
		basePage.get().selectClinicFrmHeader(addDoctorEventTestData.get("event_Clinic"));
		doctorDashboard.get().clickOnAppListBtn();
		eventListingPage.get().clickOnEventBtn();
		eventListingPage.get().clickOnAddEventBtnOnEventListing();
		basePage.get().verifyPageTitle(ADD_EVENT_PAGE_TITLE);
		eventPage.get().clickOnCancelBtn();
		basePage.get().verifyPageTitle(EVENTLISTING_PAGE_TITLE);
		eventListingPage.get().clickOnAddEventBtnOnEventListing();
		basePage.get().verifyPageTitle(ADD_EVENT_PAGE_TITLE);
		eventPage.get().enterTitleName("Doctor out of station due to seminar");
		eventPage.get().selectClinicInEvent(addDoctorEventTestData.get("event_Clinic"));
		eventPage.get().selectDoctorInEvent("Sup Head");
		eventPage.get().selectEventCategory(addDoctorEventTestData.get("category"));
		eventPage.get().selectDateInEvent(eventPage.get().getCurrentDate ());
		eventPage.get().enterHours ( "08" );
		eventPage.get().enterMinutes ( "00" );
		eventPage.get().selectAmPm ( "PM" );
		eventPage.get().enterEventDuration ( "30" );
		eventPage.get().clickOnEventSaveBtn();
//		eventPage.get().verifyEventAddSuccessMsg ();
		Assert.assertEquals (basePage.get().verification (),EVENTLISTING_PAGE_TITLE);

	}

	/*
	 *Apply date filter on Event Listing
	 * Verifying data after filter is applied
	 */

	@Test(description = VERIFY_FRM_AND_TO_DATE_FILTER,priority = 1)
	public void verifyFrmAndToDateFilter() {
		extentReport.get ().logger.log(LogStatus.PASS, VERIFY_FRM_AND_TO_DATE_FILTER);
		basePage.get().selectClinicFrmHeader(addDoctorEventTestData.get("event_Clinic"));
		doctorDashboard.get().clickOnAppListBtn();
		eventListingPage.get().clickOnEventBtn();
		eventListingPage.get().selectFrmdateInEvent(addDoctorEventTestData.get("event_Date"));
		eventListingPage.get().selectTodateInEvent(addDoctorEventTestData.get("event_Date"));
		eventListingPage.get().clickOnSearch();
		eventListingPage.get().dateFilter(addDoctorEventTestData.get("event_Date"),
				addDoctorEventTestData.get("event_Date"));
		basePage.get().verifyPageTitle(EVENTLISTING_PAGE_TITLE);
	}

	/*
	 * Checking From date cannot be greater than to date in date Filter
	 */

	@Test(description = CHECK_FRM_DATE_IS_NOT_LATER_TO_TO_DATE)
	public void checkFrmDateIsNotLaterToToDate() {
		extentReport.get ().logger.log(LogStatus.PASS, CHECK_FRM_DATE_IS_NOT_LATER_TO_TO_DATE);
		doctorDashboard.get().clickOnAppListBtn();
		eventListingPage.get().clickOnEventBtn();
		eventListingPage.get().selectFrmdateInEvent("03/30/2022");
		eventListingPage.get().selectTodateInEvent("03/22/2022");
		eventListingPage.get().clickOnSearch();
		eventListingPage.get().checkFrmDateIsNotLaterToToDate();
		basePage.get().verifyPageTitle(EVENTLISTING_PAGE_TITLE);

	}

	/*
	 * Applying Doctor Filter with date filter
	 * verifying data is filtered according to selected doctor and date
	 */
	@Test(description = VERIFY_DOCTOR_FILTER_ON_EVENT_LIST,priority = 1)
	public void verifyDoctorFilterWithDateFilterOnEventList () {
		extentReport.get ().logger.log(LogStatus.PASS, VERIFY_DOCTOR_FILTER_ON_EVENT_LIST);
		basePage.get().selectClinicFrmHeader(addDoctorEventTestData.get("event_Clinic"));
		doctorDashboard.get().clickOnAppListBtn();
		eventListingPage.get().clickOnEventBtn();
		eventListingPage.get().selectDoctor("Sup Zonehead");
		eventListingPage.get().verifyFilterDoctorInEventList("sup.zch");

		eventListingPage.get().selectFrmdateInEvent(eventPage.get().getCurrentDate ());
		eventListingPage.get().selectTodateInEvent(eventPage.get().getCurrentDate ());
		eventListingPage.get().clickOnSearch();
		eventListingPage.get().dateFilter(eventPage.get().getCurrentDate (),
				eventPage.get().getCurrentDate ());

		eventListingPage.get().verifyFilterDoctorInEventList("sup.zch");


	}

	/*
	 * Checking EventType Filter with date filter
	 * and verifying data is filtered according to selected Event Type and date
	 */

	@Test(description = VERIFY_EVENT_TYPE_FILTER_ON_EVENT_LIST,priority = 1)
	public void verifyEventTypeFilterWithDateFilter() {
		extentReport.get ().logger.log(LogStatus.PASS, VERIFY_EVENT_TYPE_FILTER_ON_EVENT_LIST);
		basePage.get().selectClinicFrmHeader(addDoctorEventTestData.get("event_Clinic"));
		doctorDashboard.get().clickOnAppListBtn();
		eventListingPage.get().clickOnEventBtn();
		eventListingPage.get().verifyOptionInEventTypeFilter();
		eventListingPage.get().selectFrmdateInEvent(addDoctorEventTestData.get("event_Date"));
		eventListingPage.get().selectTodateInEvent(addDoctorEventTestData.get("event_Date"));
		eventListingPage.get().selectType("By Doctor");
		eventListingPage.get().clickOnSearch();
		eventListingPage.get().dateFilter(addDoctorEventTestData.get("event_Date"),
				addDoctorEventTestData.get("event_Date"));
		eventListingPage.get().verifyFilterEventTypeInEventList("Doctor");

	}

	/*
	 * Checking Event Category filter along with date and event type filter
	 * verifying data is filtered according to selected Category Type ,date and event type
	 */
	@Test(description = VERIFY_FILTER_OF_CATEGORY_TYPE,priority = 1)
	public void verifyCategoryFilterWithDateAndTypeFilter () {
		extentReport.get ().logger.log(LogStatus.PASS, VERIFY_FILTER_OF_CATEGORY_TYPE);
		basePage.get().selectClinicFrmHeader(addDoctorEventTestData.get("event_Clinic"));
		doctorDashboard.get().clickOnAppListBtn();
		eventListingPage.get().clickOnEventBtn();
		eventListingPage.get().selectFrmdateInEvent(addDoctorEventTestData.get("event_Date"));
		eventListingPage.get().selectTodateInEvent(addDoctorEventTestData.get("event_Date"));
		eventListingPage.get().selectType("By Doctor");
		eventListingPage.get().selectCategroy("Lunch");
		eventListingPage.get().clickOnSearch();
		eventListingPage.get().dateFilter(addDoctorEventTestData.get("event_Date"),
				addDoctorEventTestData.get("event_Date"));
		eventListingPage.get().verifyFilterEventTypeInEventList("Doctor");
		eventListingPage.get().verifyFilterCategoryInEventList("Lunch");



	}

	/*
	 * Checking Reset button on event listing and verifying reset button working
	 */
	@Test(description = VERIFY_RESET_BUTTON_FUNCTIONALITY_ON_EVENT_LISTING)
	public void verifyResetBtnOnEventListing() {
		extentReport.get ().logger.log(LogStatus.PASS, VERIFY_RESET_BUTTON_FUNCTIONALITY_ON_EVENT_LISTING);
		doctorDashboard.get().clickOnAppListBtn();
		eventListingPage.get().clickOnEventBtn();
		eventListingPage.get().selectFrmdateInEvent ( eventPage.get().getCurrentDate () );
		eventListingPage.get().selectTodateInEvent ( eventPage.get().getCurrentDate () );
		eventListingPage.get().selectType ( "By Doctor" );
		eventListingPage.get().selectCategroy ( "In Office" );
		eventListingPage.get().verifyResetBtnWorking();
		basePage.get().verifyPageTitle(EVENTLISTING_PAGE_TITLE);

	}
	

	@AfterMethod(description = STATUS_OF_SCRIPTS, enabled = true)
	public void statusOfScripts(ITestResult result) {
		extentReport.get ().onTestFailure(result);
		extentReport.get ().logger.log(LogStatus.PASS, STATUS_OF_SCRIPTS);
		basePage.get().backTODoctorDashboard();
		basePage.get().clickOnlogOut();
		loginPage.get().close();
		extentReport.get ().report.endTest(extentReport.get ().logger);
		extentReport.get ().report.flush();
		extentReport.get ().report.close();
	}


}
