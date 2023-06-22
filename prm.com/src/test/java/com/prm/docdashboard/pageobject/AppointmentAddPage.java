package com.prm.docdashboard.pageobject;

import com.prm.pageobjects.utils.DatePicker;
import com.prm.pageobjects.utils.PCDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author Ajit Yadav
 */
public class AppointmentAddPage {
    DatePicker datepicker;
    private PCDriver loginPage;
    
    public AppointmentAddPage(PCDriver loginPage) {
        this.loginPage = loginPage;
        PageFactory.initElements(loginPage.getDriver(), this);
        datepicker = new DatePicker(loginPage);
        
    }

    // WebElement on Appointment Add screen
    @FindBy(xpath = "//h1[text()='Add Appointment/Event']")
    private WebElement headerPage;
    @FindBy(xpath = "//li[@heading='Add Appointment']")
    private WebElement appointmentTab;
    @FindBy(xpath = "//label[text()='in-Clinic']")
    private WebElement inClinicAppointment;
   @FindBy(xpath = "//label[text()='e-Consult']")
    private WebElement eConsultAppointment;
   @FindBy(xpath = "//label[text()='Scan']")
   private WebElement scanAppointment;
   @FindBy(xpath = "//label[text()='e-Consult']/..//div[@class='stl-chkB-rdB']")
   private WebElement eConsultRadioBtn;
    @FindBy(css = "a[data-value='1']")
    private WebElement firstOperatory;
    @FindBy(id = "eCosultLabel")
    private WebElement rooms;
//    @FindBy(xpath = "//span[text()='Available']")
    @FindBy(xpath = "//label[@id='eCosultLabel']/..//span")
    private List<WebElement> totalRooms;
    @FindBy(id = "scannerLabel")
    private WebElement scanners;
    @FindBy(xpath = "//label[@id='scannerLabel']/..//span")
    private List<WebElement> totalScanners;
    @FindBy(xpath = "//a[contains(text(),'Go To Scheduler')]")
    private WebElement goToSchedularBtn;
    @FindBy(xpath = "//p[contains(text(),'Navigation to scheduler screen will result in loss of your selected information. Are you sure to continue?')]")
    private WebElement schedularTextMsg;
    @FindBy(xpath = "//span[@class='cmnicons yes-mdl']")
    private WebElement yesBtn;
    @FindBy(xpath = "//span[@class='cmnicons cncl-mdl']")
    private WebElement noBtn;
    @FindBy(xpath = "//label[contains(text(),'Patient ID')]/following-sibling::input[@id='patientid']")
    public WebElement patientId;
    @FindBy(xpath = "//label[contains(text(),'Patient Name')]/following-sibling::input[@id='patientname']")
    public WebElement patientName;
    @FindBy(xpath = "//label[contains(text(),'Email ID')]/following-sibling::input[@id='email']")
    public WebElement patientEmail;
    @FindBy(xpath = "//label[contains(text(),'Duration of Appt.')]/following-sibling::input[@id='duration']")
    private WebElement formDuration;
    @FindBy(xpath = "//label[contains(text(),'Mobile')]/following-sibling::input[@id='mobile']")
    public WebElement patientMobile;
//    @FindBy(xpath = "//label[contains(text(),'Clinic')]/following-sibling::custom-combo[@id='mainClinic']//select[@id='clinic']")
    @FindBy(id = "serach_option")
    private WebElement clinicDropDown;
    @FindBy(xpath = "//label[contains(text(),'Doctor')]/following-sibling::custom-combo-new[@id='mainDoctor']//select")
    protected WebElement doctor;
//    @FindBy(xpath = "//label[contains(text(),'Date of App')]/following-sibling::input[@id='date']")
    @FindBy(id = "date")
    protected WebElement dateAppointment;
    @FindBy(xpath = "//label[contains(text(),'Slot Availability')]/following-sibling::custom-combo-new[@id='timeSlot']//select")
    protected WebElement timeSlot;
    @FindBy(xpath = "//div[text()='Enter patient name']")
    private WebElement enterPatientNameMsg;
    @FindBy(xpath = "//div[text()='Select doctor']")
    private WebElement pleaseSelectDoctorMsg;
    @FindBy(id = "tentative")
    public WebElement tentativeCheckbox;
//    @FindBy(xpath = "//label[text()='Referral']/following-sibling::custom-combo//select[@id='referral']")
    @FindBy(xpath = "//label[text()='Referral']/..//div[@class='custom-combo-new']//select")
    private WebElement referral;
//    @FindBy(xpath = "//label[text()='Referral Details']/following-sibling::input[@id='refDetails']")
    @FindBy(id = "refDetails")
    private WebElement referralDetails;
//    @FindBy(xpath = "//label[text()='Source']/following-sibling::custom-combo//select[@id='source']")
    @FindBy(xpath = "//label[text()='Source']/..//div[@class='custom-combo-new']//select")
    private WebElement source;
    @FindBy(xpath = "//label[text()='Notes']/following-sibling::textarea[@id='appointment_note']")
    private WebElement notes;
    @FindBy(id = "addAppointment")
    private WebElement addAppointmentTab;
    @FindBy(xpath = "//div[text()='Enter valid email address']")
    private WebElement enterValidEmailAddressMsg;
    @FindBy(xpath = "//p[text()='Select an operatory']")
    private WebElement operatoriesDisableMsg;
    @FindBy(xpath = "//span[@class='cmnicons aprsv']")
    private WebElement saveBtn;
    @FindBy(xpath = "//span[@class='cmnicons rst']")
    private WebElement appResetBtn;
    @FindBy(xpath = "//span[@class='cmnicons cncl']")
    private WebElement cancelBtn;
    @FindBy(xpath = "cmnicons apptAdd")
    private WebElement apptBtn;
    @FindBy(xpath = "//li[contains(@id,'typeahead')]")
    private WebElement AutoSeletwb;
    @FindBy(id = "sms")
    private WebElement smsEmailCheckbox;
    @FindBy(xpath = "//span[text()='Others']")
    private WebElement mentionsOtherTextBox;
    @FindBy(xpath = "//a[@data-ng-model='patient.appointmentData.operatory']")
    private List<WebElement> operatory;
    @FindBy(xpath = "//label[text()='Chief Complaint(s)']")
    private WebElement chiefComplaints;
    @FindBy(xpath = "//span[text()='Pain']")
    private WebElement pain;
    @FindBy(xpath = "//span[text()='Sensitivity']")
    private WebElement sensitivity;
    @FindBy(xpath = "//span[text()='Swollen Gums']")
    private WebElement swollenGums;
    @FindBy(xpath = "//span[text()='Chewing Difficulty']")
    private WebElement chewingDifficulty;
    @FindBy(xpath = "//span[text()='Bad Breath']")
    private WebElement badBreath;
    @FindBy(xpath = "//span[text()='Bleeding Gums']")
    private WebElement bleedingGums;
    @FindBy(xpath = "//span[text()='Discolored Tooth']")
    private WebElement discoloredTooth;
    @FindBy(xpath = "//span[text()='Plaque']")
    private WebElement plaque;
    @FindBy(xpath = "//span[text()='Tooth Decay']")
    private WebElement toothDecay;
    @FindBy(xpath = "//span[text()='Teeth Grinding']")
    private WebElement teethGrinding;
    @FindBy(xpath = "//span[text()='Loose Teeth']")
    private WebElement looseTeeth;
    @FindBy(id = "otherchiefComplaint")
    private WebElement otherChiefComplaintBox;
    @FindBy(xpath = "//label[contains(text(),'Tentative Appt.')]")
    private WebElement tentativeBtn;
    //web elements on Covid-19 form
    @FindBy(id = "patient_infDses_flag")
    private WebElement patientCovidStatus;
    @FindBy(xpath = "//span[text()='Covid-19 Declaration']")
    private WebElement headerCovid;
    @FindBy(xpath = "//span[@class='ng-binding ng-scope']")
    private WebElement CovidFlagAlertMsg;
    @FindBy(xpath = "//div[@id='patient_infDses_flag']//span[contains(@class,'grey')]")
    private WebElement greyFlag;
    @FindBy(xpath = "//div[@id='patient_infDses_flag']//h1[text()='Not Available']")
    private WebElement notAvailableGrey;
    @FindBy(xpath = "//div[@id='patient_infDses_flag_formInitialize']")
    private WebElement covidFormBtn;
    @FindBy(xpath = "//h1[text()='Treatment Allowed']")
    private WebElement greenCovidPatient;
    @FindBy(xpath = "//span[text()='Patient Covid-19 Declaration saved successfully!']")
    private WebElement covidUpdateSuccessMsg;
    @FindBy(xpath = "//h1[text()='Treatment Not Allowed']")
    private WebElement redCovidPatient;
    @FindBy(xpath = "//div[text()='Enter mobile no.']")
    private WebElement enterMobileNoMsg;
    @FindBy(xpath = "//div[text()='Enter 10 digit mobile no.']")
    private WebElement enterTenDigitMobileNoMsg;
    @FindBy(xpath = "//div[text()='Enter a valid mobile no.']")
    private WebElement enterValidMobileNoMsg;
    @FindBy(xpath = "//label[contains(text(),'Slot Availability')]/..//..//select")
    private WebElement selectSlot;
    @FindBy(id = "@Home")
    private WebElement homeServiceType;
    @FindBy(xpath = "//label[contains(text(),'@Clinic')]")
    private WebElement clinicServiceType;
    @FindBy(id = "srvc_address")    
    private WebElement serviceAddress;
    @FindBy(xpath = "//input[@id='srvc_address']/..//div[@class='error ng-binding ng-scope']")    
    private WebElement serviceAddressErrMsg;
    @FindBy(id = "srvc_city")
    private WebElement serviceCity;
    @FindBy(xpath = "//input[@id='srvc_city']/..//div[@class='error ng-binding ng-scope']")    
    private WebElement serviceCityErrMsg;
    @FindBy(id = "srvc_pincode")
    private WebElement servicePincode;
    @FindBy(xpath = "//input[@id='srvc_pincode']/..//div[@class='error ng-binding ng-scope']")    
    private WebElement servicePincodeErrMsg;
    @FindBy(id = "state")
    private WebElement serviceState;
    @FindBy(xpath = "//select[@id='state']/../../..//div[@class='error mrgn-top5 ng-binding ng-scope']")    
    private WebElement serviceStateErrMsg;
    @FindBy(id = "service clinic")
    private WebElement serviceClinic;
    @FindBy(xpath = "//select[@id='service clinic']/../../..//div[@class='error mrgn-top5 ng-binding ng-scope']")    
    private WebElement serviceClinicErrMsg;
    @FindBy(xpath = "//div[@class='alert ng-isolate-scope alert-success alert-dismissable']//div//span")    
    private WebElement updateAppnmntTxt;
    @FindBy(xpath = "//div[@class='alert ng-binding ng-isolate-scope alert-danger alert-dismissable']")    
    private WebElement restrictUpdateAppnmntTxt;
    @FindBy(xpath = "//span[@class='glyphicon glyphicon-chevron-down customComboBtn cursor-pointer']")    
    private WebElement clinicDrpdwnBtn;
   
    //Reusable function
    public void checkAppointmentInClinic() {
      loginPage.waitForSpinnerToDisappear();
      loginPage.waitForElementVisibility(appointmentTab,4000);
      System.out.println(checkWebElementDisplayed(appointmentTab));
      System.out.println(appointmentTab.getAttribute("class").contains("active"));
      System.out.println(checkWebElementDisplayed(inClinicAppointment));
      System.out.println(eConsultAppointment.getAttribute("class").contains("selected"));
      Assert.assertTrue(checkWebElementDisplayed(appointmentTab) 
    		  && appointmentTab.getAttribute("class").contains("active") 
    		  && checkWebElementDisplayed(inClinicAppointment) 
    		  && inClinicAppointment.getAttribute("class").contains("selected"));
  } 
    
    public void checkAppointmenteConsult() {
        loginPage.waitForSpinnerToDisappear();
        loginPage.waitForElementVisibility(appointmentTab,4000);
        Assert.assertTrue(checkWebElementDisplayed(appointmentTab) 
      		  && appointmentTab.getAttribute("class").contains("active") 
      		  && checkWebElementDisplayed(eConsultAppointment) 
      		  && eConsultAppointment.getAttribute("class").contains("selected"));
    }  
    
//    public void checkAppointmentInClinic() {
////        loginPage.waitForPageLoad();
//        loginPage.waitForSpinnerToDisappear();
//        try {
//        	Thread.sleep(3000);
//        }
//        catch(Exception e) {
//        	e.printStackTrace();
//        }
//        Assert.assertTrue(checkWebElementDisplayed(appointmentTab) && appointmentTab.getAttribute("class").contains("active") && checkWebElementDisplayed(inClinicAppointment) && inClinicAppointment.getAttribute("class").contains("selected"));
//    }

    public void selectInClinic() {
        loginPage.waitForPageLoad();
        if (!inClinicAppointment.getAttribute("class").contains("selected")) {
            inClinicAppointment.click();
        }
    }
    
    
    public void selectEConsult() {
      loginPage.waitForSpinnerToDisappear();
      if (!eConsultAppointment.getAttribute("class").contains("selected")) {
      JavascriptExecutor j = (JavascriptExecutor) loginPage.getDriver();
      j.executeScript("arguments[0].click();", eConsultAppointment);
      }
      loginPage.waitForSpinnerToDisappear();
  }
    
    public void selectScan() {
        loginPage.waitForSpinnerToDisappear();
        if (!eConsultAppointment.getAttribute("class").contains("selected")) {
        JavascriptExecutor j = (JavascriptExecutor) loginPage.getDriver();
        j.executeScript("arguments[0].click();", scanAppointment);
        loginPage.waitForSpinnerToDisappear();
        }
    }
    
    
    public void selectClinicServiceType()
    {
    	clinicServiceType.click();
        loginPage.waitForElementVisibility(serviceClinic, 4000);
    }
    
    public void verifyServiceType(String serviceType){
    	switch(serviceType){
    	case "Home":
    		Assert.assertTrue(homeServiceType.isSelected());
            Assert.assertTrue(checkWebElementDisplayed(serviceAddress));
            Assert.assertTrue(checkWebElementDisplayed(serviceCity));
            Assert.assertTrue(checkWebElementDisplayed(servicePincode));
            Assert.assertTrue(checkWebElementDisplayed(serviceState));
            break;
            
    	case "Clinic":
    		selectClinicServiceType();
    	    loginPage.waitForElementVisibility(serviceClinic, 4000);
    	    Assert.assertTrue(checkWebElementDisplayed(serviceClinic));
    	    break;
    	}
    }
    
    public void validateServiceTypeMandatoryFieldsMessages(String serviceType){
    	switch(serviceType){
    	case "Home":
    		Assert.assertTrue(homeServiceType.isSelected());
            Assert.assertTrue(checkWebElementDisplayed(serviceAddressErrMsg));
            Assert.assertTrue(checkWebElementDisplayed(serviceCityErrMsg));
            Assert.assertTrue(checkWebElementDisplayed(servicePincodeErrMsg));
            Assert.assertTrue(checkWebElementDisplayed(serviceStateErrMsg));
            break;          
    	case "Clinic":
    		clinicServiceType.click();
    	    loginPage.waitForElementVisibility(serviceClinicErrMsg, 4000);
    	    Assert.assertTrue(checkWebElementDisplayed(serviceClinicErrMsg));
    	    break;
    	}
    }
    
public void addHomeServiceAddress(String address) {
	serviceAddress.sendKeys(address);
} 

public void addHomeServiceCity(String city) {
	serviceCity.sendKeys(city);
}

public void addHomeServicePincode(String pincode) {
	servicePincode.sendKeys(pincode);
}

public void selectServiceState(String state) {
	loginPage.selectFromDropDownByVisibleText(serviceState, state);
}

public void selectServiceClinic(String clinic) {
	loginPage.selectFromDropDownByVisibleText(serviceClinic, clinic);
}
    
    


//    public void selectEConsult() {
////        loginPage.waitForPageLoad();
//        loginPage.waitForSpinnerToDisappear();
//        if (!eConsultAppointment.getAttribute("class").contains("selected")) {
//        	System.out.println("Jai Hind");
////         	loginPage.waitForElementToBeClickable(eConsultAppointment);
//         	eConsultAppointment.click();
//        }
//    }

    public void enterMobileNoMsg() {
        loginPage.waitForElementToBeClickable(enterMobileNoMsg);
        Assert.assertTrue(checkWebElementDisplayed(enterMobileNoMsg));
    }

    public void enterTenDigitMobileNoMsg() {
        loginPage.waitForElementToBeClickable(enterTenDigitMobileNoMsg);
        Assert.assertTrue(checkWebElementDisplayed(enterTenDigitMobileNoMsg));
    }

    public void enterValidMobileNoMsg() {
        loginPage.waitForElementToBeClickable(enterValidMobileNoMsg);
        Assert.assertTrue(checkWebElementDisplayed(enterValidMobileNoMsg));
    }

    public void selectPatientSuggestion(String patient) {
        loginPage.waitForPageLoad();
        try {
            Thread.sleep(4000);
            WebElement web = loginPage.getDriver().findElement(By.xpath("//a[contains(text(),'" + patient + "')]"));
            loginPage.hoverOnElement(web);
            web.click();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void covidStatusUpdate() {
        loginPage.waitForPageLoad();
        loginPage.waitForElementToBeClickable(covidUpdateSuccessMsg);
        Assert.assertTrue(checkWebElementDisplayed(covidUpdateSuccessMsg));
    }

    public void patientCovidGreen() {
        loginPage.waitForPageLoad();
        loginPage.waitForElementToBeClickable(greenCovidPatient);
        Assert.assertTrue(checkWebElementDisplayed(greenCovidPatient));
    }

    public void patientCovidRed() {
        loginPage.waitForPageLoad();
        loginPage.waitForElementToBeClickable(redCovidPatient);
        Assert.assertTrue(checkWebElementDisplayed(redCovidPatient));
    }

    public void openCovidForm() {
        try {
            loginPage.waitForElementToBeClickable(covidFormBtn);
            covidFormBtn.click();
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void checkPatientCovidStatus() {
        loginPage.waitForPageLoad();
        loginPage.waitForElementToBeClickable(headerCovid);
        Assert.assertTrue(checkWebElementDisplayed(greyFlag) && checkWebElementDisplayed(notAvailableGrey) && checkWebElementDisplayed(covidFormBtn));
    }

    public void covidOption() {
        loginPage.waitForPageLoad();
        loginPage.waitForElementToBeClickable(patientCovidStatus);
        Assert.assertTrue(checkWebElementDisplayed(headerCovid) && patientCovidStatus.getText().contains("Status not known"));
    }

    public void covidAlertMsgGrey() {
        loginPage.waitForPageLoad();
        loginPage.waitForElementToBeClickable(CovidFlagAlertMsg);
        Assert.assertTrue(CovidFlagAlertMsg.getText().contains("Appointment Booking is blocked for grey status, please fill the Covid-19 Declaration to update the patient status!"));
    }

    public void covidAlertMsgRed() {
        loginPage.waitForPageLoad();
        loginPage.waitForElementToBeClickable(CovidFlagAlertMsg);
        Assert.assertTrue(CovidFlagAlertMsg.getText().contains("Appointment Booking is blocked for (red) status till"));
    }

    public void clickOnTentative() {
        loginPage.waitForPageLoad();
        loginPage.waitForElementToBeClickable(tentativeBtn);
        tentativeBtn.click();
    }

    public void verifyTentativeAfterClickOnEditBtn() {
        loginPage.waitForPageLoad();
        Assert.assertTrue(tentativeCheckbox.isSelected());
        loginPage.waitForPageLoad();
    }

    public void patientNameTextFields() {
        loginPage.waitForElementToBeClickable(patientName);
        Assert.assertTrue(checkWebElementDisplayed(patientName) && patientName.getAttribute("placeholder").contains("patient name"));
    }

    public void patientMobileTextFields() {
        loginPage.waitForElementToBeClickable(patientMobile);
        Assert.assertTrue(checkWebElementDisplayed(patientMobile) && patientMobile.getAttribute("placeholder").contains("enter 10 digit mobile no"));
    }

    public void patientIdTextFields() {
        loginPage.waitForElementToBeClickable(patientId);
        Assert.assertTrue(checkWebElementDisplayed(patientId) && patientId.getAttribute("placeholder").contains("patient id"));
    }

    public void patientEmailIdTextFields() {
        loginPage.waitForElementToBeClickable(patientEmail);
        Assert.assertTrue(checkWebElementDisplayed(patientEmail) && patientEmail.getAttribute("placeholder").contains("email"));
    }

    public void clinicDrpDwn() {
        loginPage.waitForElementToBeClickable(clinicDropDown);
        Assert.assertTrue(checkWebElementDisplayed(clinicDropDown));
    }

    public void dateCalender() {
    	  loginPage.waitForSpinnerToDisappear();
    	  loginPage.waitForElementVisibility(dateAppointment, 4000);
//        loginPage.waitForElementToBeClickable(dateAppointment);
        Assert.assertTrue(checkWebElementDisplayed(dateAppointment));
    }

    public void timeSlotDrpDwn() {
        loginPage.waitForElementToBeClickable(timeSlot);
        Assert.assertTrue(checkWebElementDisplayed(timeSlot) && defaultSlotSelection());
    }

    public void timeDurationTextFields() {
        loginPage.waitForElementToBeClickable(formDuration);
        Assert.assertTrue(checkWebElementDisplayed(formDuration) && byDefaultDuration());
    }

    public void doctorDrpDwn() {
        loginPage.waitForElementToBeClickable(doctor);
        Assert.assertTrue(checkWebElementDisplayed(doctor) && loginPage.firstSelectedOption(doctor).equalsIgnoreCase("Select Doctor"));
       		
    }

    public void referralDrpDwn() {
        loginPage.waitForElementToBeClickable(referral);
        Assert.assertTrue(checkWebElementDisplayed(referral) && loginPage.firstSelectedOption(referral).equalsIgnoreCase("Select Referral"));
    }

    public void sourceDrpDwn() {
        loginPage.waitForElementToBeClickable(source);
        Assert.assertTrue(checkWebElementDisplayed(source) && loginPage.firstSelectedOption(source).equalsIgnoreCase("Select Source"));
    }

    public void notesFields() {
        loginPage.waitForElementToBeClickable(notes);
        Assert.assertTrue(checkWebElementDisplayed(notes) && notes.getAttribute("placeholder").contains("notes"));
    }

    public void tentativeCheckBox() {
        loginPage.waitForPageLoad();
        Assert.assertTrue(!tentativeCheckbox.isSelected());
    }

    public void chiefComplaints() {
//        loginPage.waitForPageLoad();
        Assert.assertTrue(checkWebElementDisplayed(chiefComplaints) && checkWebElementDisplayed(pain) && checkWebElementDisplayed(sensitivity) && checkWebElementDisplayed(swollenGums) && checkWebElementDisplayed(chewingDifficulty) && checkWebElementDisplayed(badBreath)
                && checkWebElementDisplayed(bleedingGums) && checkWebElementDisplayed(discoloredTooth) && checkWebElementDisplayed(plaque) && checkWebElementDisplayed(toothDecay) && checkWebElementDisplayed(teethGrinding) && checkWebElementDisplayed(looseTeeth));
    }

    public void clickOnChiefComplaints(String chiefComplaint) {
        loginPage.waitForPageLoad();
        WebElement element = loginPage.getDriver().findElement(By.xpath("//span[text()='" + chiefComplaint + "']"));
        loginPage.waitForElementToBeClickable(element);
        element.click();
    }

    public void otherChiefComplaint() {
//        loginPage.waitForPageLoad();
        loginPage.waitForElementToBeClickable(otherChiefComplaintBox);
        Assert.assertTrue(checkWebElementDisplayed(otherChiefComplaintBox) && otherChiefComplaintBox.getAttribute("placeholder").contains("other chief complaint"));
    }

    public void actionsBtnAppointmentAdd() {
//        loginPage.waitForPageLoad();
        Assert.assertTrue(checkWebElementDisplayed(saveBtn) && checkWebElementDisplayed(cancelBtn) && checkWebElementDisplayed(appResetBtn));
    }
    
    public void getTextPatientName(String expected) {
            loginPage.waitForPageLoad();
            loginPage.waitForSpinnerToDisappear();
            JavascriptExecutor javascriptExecutor = (JavascriptExecutor) loginPage.getDriver();
            Object obj = javascriptExecutor.executeScript("return document.getElementById('patientname').value;");
            String actual = (String) obj;
            Assert.assertEquals(actual, expected);
    }

//    public void getTextPatientName(String expected) {
//        loginPage.waitForPageLoad();
//        try {
//            Thread.sleep(10000);
//            JavascriptExecutor javascriptExecutor = (JavascriptExecutor) loginPage.getDriver();
//            Object obj = javascriptExecutor.executeScript("return document.getElementById('patientname').value;");
//            String actual = (String) obj;
//            Assert.assertEquals(actual, expected);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }

    public void verifyMandatoryFieldsOnEdit() {
        loginPage.waitForPageLoad();
        try {
            Thread.sleep(10000);
            Assert.assertTrue(!fieldReadOnly(patientName) && !fieldReadOnly(patientId) && !fieldReadOnly(patientMobile));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void patientIDAfterAutoSelect() {
        loginPage.waitForPageLoad();
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) loginPage.getDriver();
        Object obj = javascriptExecutor.executeScript("return document.getElementById('patientid').value;");
        String actual = (String) obj;
        Assert.assertTrue(actual != null);
    }

    public void getTextPatientMobile(String expected) {
    	loginPage.waitForSpinnerToDisappear();
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) loginPage.getDriver();
        Object obj = javascriptExecutor.executeScript("return document.getElementById('mobile').value;");
        String actual = (String) obj;
        System.out.println("The Heo iS"+actual);
        Assert.assertEquals(actual, expected);
    }

    public void toVerifyAppointmentHomePage() {
        try {
            Thread.sleep(6000);
            loginPage.waitForPageLoad();
            Assert.assertTrue(loginPage.getTitle().contains("Appointment"));
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public void enterPatientName(String name) {
        loginPage.waitForSpinnerToDisappear();
        patientName.clear();
        patientName.sendKeys(name);
    }
    

//    public void enterPatientName(String name) {
//        loginPage.waitForPageLoad();
//        loginPage.waitForElementToBeClickable(patientName);
//        patientName.clear();
//        patientName.sendKeys(name);
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
//    
    public void enterMobileNumber(String mobileNum) {
        loginPage.waitForSpinnerToDisappear();
        loginPage.waitForElementVisibility(patientMobile, 4000);
        patientMobile.clear();
        patientMobile.sendKeys(mobileNum);
    }

//    public void enterMobileNumber(String mobileNum) {
//        loginPage.waitForPageLoad();
//        loginPage.waitForElementToBeClickable(patientMobile);
//        patientMobile.clear();
//        patientMobile.sendKeys(mobileNum);
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
    
    public void enterEmailAddress(String email) {
        loginPage.waitForSpinnerToDisappear();;
        patientEmail.clear();
        patientEmail.sendKeys(email);
    }

//    public void enterEmailAddress(String email) {
//        loginPage.waitForPageLoad();
//        loginPage.WaitTillElementIsPresent(patientEmail);
//        patientEmail.clear();
//        patientEmail.sendKeys(email);
//        try {
//			Thread.sleep(3000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//    }

    public void enterTimeDuration(String duration) {
        loginPage.waitForPageLoad();
        formDuration.clear();
        formDuration.sendKeys(duration);
    }

    public void selectClinicFromDropdown(String clinic) {
        loginPage.waitForElementToBeClickable(clinicDropDown);
        clinicDropDown.clear();
    	clinicDropDown.sendKeys(clinic);
    	loginPage.waitForSpinnerToDisappear();
    	WebElement centreList = loginPage.getDriver().findElement(By.xpath("//li[contains(text(),'"+clinic+"')]"));
    	loginPage.waitForElementVisibility(centreList, 4000);
    	centreList.click();
//    	loginPage.getDriver().findElement(By.xpath("//li[contains(text(),'"+clinic+"')]")).click();
//        loginPage.selectFromDropDownByVisibleText(clinicDropDown, clinic);
    }

    public void selectDateofAppointment(String Date) {
        loginPage.waitForPageLoad();
        loginPage.waitForElementToBeClickable(dateAppointment);
        datepicker.selectDateofAppCal(Date, dateAppointment);
    }

    public void appointmentDate(String Date) {
//        loginPage.waitForPageLoad();
    	loginPage.waitForSpinnerToDisappear();
        loginPage.waitForElementToBeClickable(dateAppointment);
        datepicker.appointmentDate(Date, dateAppointment,"date");
    }

    public void selectDoctorFromDropdown(String doctorName) {
        loginPage.waitForSpinnerToDisappear();
        loginPage.WaitTillElementIsPresent(doctor);
        loginPage.waitForElementToDisappear((By.xpath("//div[contains(@class='ui-widget-overlay')]")));
//        try {
//			Thread.sleep(4000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
        doctor.click();
        loginPage.selectFromDropDownByVisibleText(doctor, doctorName.trim());
    }

    public void doctorSelected(String doctor_selected) {
        loginPage.waitForPageLoad();
        Assert.assertTrue(loginPage.firstSelectedOption(doctor).contains(doctor_selected));
    }
    
    public void clinicSelected(String clinic_Selected) {
        loginPage.waitForSpinnerToDisappear();
        String selectedCentreName = loginPage.getDriver().findElement(By.xpath("//div[@class='drpdwnLabel ng-binding']")).getText();
        Assert.assertTrue(selectedCentreName.contains(clinic_Selected));
    }

//    public void clinicSelected(String clinic_Selected) {
//        loginPage.waitForPageLoad();
//        Assert.assertTrue(loginPage.firstSelectedOption(clinicDropDown).contains(clinic_Selected));
//    }
    
    public void slotSelected(String slot_Selected) {
        loginPage.waitForSpinnerToDisappear();;
        WebElement firstOption = loginPage.getDriver().findElement(By.xpath("//label[@for='slot']/..//select[@data-ng-model='value']/option[@selected='selected']"));
        Assert.assertEquals(firstOption.getText().trim(), slot_Selected);
    }
    
//    public void slotSelected(String slot_Selected) {
//        loginPage.waitForPageLoad();
//        timeSlot.click();
//        WebElement firstOption = loginPage.getDriver().findElement(By.xpath("//label[@for='slot']/..//select[@data-ng-model='value']/option[@selected='selected']"));
//        loginPage.hoverOnElement(firstOption);
//        Assert.assertEquals(firstOption.getText().trim(), slot_Selected);
//        timeSlot.click();
//    }

    public void sourceSelected(String source_Selected) {
        loginPage.waitForPageLoad();
        Assert.assertTrue(loginPage.firstSelectedOption(source).contains(source_Selected));
    }

    public void referralSelected(String referral_Selected) {
        loginPage.waitForPageLoad();
        Assert.assertTrue(loginPage.firstSelectedOption(referral).contains(referral_Selected));
    }

    public void getReferralDetails(String expected) {
        loginPage.waitForPageLoad();
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) loginPage.getDriver();
        Object obj = javascriptExecutor.executeScript("return document.getElementById('refDetails').value;");
        String actual = (String) obj;
        Assert.assertEquals(actual, expected);
    }

    public void getNotes(String expected) {
        loginPage.waitForPageLoad();
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) loginPage.getDriver();
        Object obj = javascriptExecutor.executeScript("return document.getElementById('appointment_note').value;");
        String actual = (String) obj;
        Assert.assertEquals(actual, expected);
    }

    public void selectDoctorFromDropdownByIndex(int index) {
        loginPage.waitForPageLoad();
        loginPage.WaitTillElementIsPresent(doctor);
        loginPage.selectFromDropDownByIndex(doctor, index);
    }
    
    public void selectTimeSlotFromDropdown(String timeslt) {
        loginPage.waitForSpinnerToDisappear();
        loginPage.selectFromDropDownByVisibleText(timeSlot, timeslt);
    }

//    public void selectTimeSlotFromDropdown(String timeslt) {
//        loginPage.waitForPageLoad();
//        loginPage.WaitTillElementIsPresent(timeSlot);
//        try {
//			Thread.sleep(3000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//        loginPage.selectFromDropDownByVisibleText(timeSlot, timeslt);
//    }
    
    public void enterNote(String note) {
        loginPage.waitForPageLoad();
        notes.clear();
        notes.sendKeys(note);
    }

    public void enterPatientNameMsg() {
        loginPage.WaitTillElementIsPresent(enterPatientNameMsg);
        Assert.assertTrue(checkWebElementDisplayed(enterPatientNameMsg));
    }

    public void pleaseSelectDoctorMsg() {
        loginPage.WaitTillElementIsPresent(pleaseSelectDoctorMsg);
        Assert.assertTrue(pleaseSelectDoctorMsg.isDisplayed());
    }
    
    
    public void selectReferralFromDropdown(String referralName) {
      loginPage.waitForSpinnerToDisappear();
      loginPage.waitForPageToBecomeActive();
      loginPage.waitForElementVisibility(referral, 4000);
      loginPage.selectFromDropDownByVisibleText(referral, referralName);
  }
    
//    public void selectReferralFromDropdown(String referralName) {
//       loginPage.waitForPageLoad();
//       loginPage.waitForElementToBeClickable(referral);
//        loginPage.waitForElementVisibility(referral, 4000);
//       try {
//		Thread.sleep(3000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//        loginPage.selectFromDropDownByVisibleText(referral, referralName);
//    }

    public void referralDetails() {
//        loginPage.waitForPageLoad();
        Assert.assertTrue(checkWebElementDisplayed(referralDetails) && referralDetails.getAttribute("placeholder").contains("referral details"));
    }

    public void enterReferralDetails(String referral_Details) {
        loginPage.waitForPageLoad();
        referralDetails.sendKeys(referral_Details);
    }

    public void selectSourceFromDropdown(String sourceName) {
        loginPage.waitForElementToBeClickable(source);
        loginPage.selectFromDropDownByVisibleText(source, sourceName);
    }
    
    public void clickOnSaveBtn() {
    	loginPage.waitForSpinnerToDisappear();
        loginPage.WaitTillElementIsPresent(saveBtn);
        saveBtn.click();
//       loginPage.waitForElementToDisappear((By.xpath("//div[contains(text(),'Appointment added successfully!')]")));
    }

//    public void clickOnSaveBtn() {
//    	loginPage.waitForSpinnerToDisappear();
//        loginPage.WaitTillElementIsPresent(saveBtn);
//        saveBtn.click();
//        loginPage.waitForElementToDisappear((By.xpath("//div[contains(text(),'Appointment added successfully!')]")));
//        try {
//            Thread.sleep(4000);
//            saveBtn.click();
//            Thread.sleep(4000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
    
    public void clickOnCancelBtn() {
    	loginPage.waitForSpinnerToDisappear();
    	loginPage.waitForElementVisibility(cancelBtn, 4000);
        loginPage.waitForElementToBeClickable(cancelBtn);
        cancelBtn.click();  
    }

//    public void clickOnCancelBtn() {
//        loginPage.waitForElementToBeClickable(cancelBtn);
//        try {
//            loginPage.getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//            Thread.sleep(5000);
//            cancelBtn.click();
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }

    public void clickOnAppResetBtn() {
    	loginPage.waitForSpinnerToDisappear();
//    	loginPage.waitForElementToDisappear((By.xpath("//div[contains(@class='modal overlay show')]")));
        loginPage.waitForElementToBeClickable(appResetBtn);
//        loginPage.getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        appResetBtn.click();
    }
    
    public void clickOnAddApptBtn() {
        loginPage.waitForPageLoad();
        loginPage.WaitTillElementIsPresent(apptBtn);
        try {
            Thread.sleep(4000);
            apptBtn.click();
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
//    public void clickOnAddApptBtn() {
//        loginPage.waitForPageLoad();
//        loginPage.WaitTillElementIsPresent(apptBtn);
//        try {
//            Thread.sleep(4000);
//            apptBtn.click();
//            Thread.sleep(4000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
    

    public boolean defaultSlotSelection() {
        loginPage.waitForPageLoad();
//        loginPage.clickEscapeKeyboardButton();
        loginPage.waitForElementToDisappear((By.xpath("//div[contains(@class='modal overlay show')]")));
////        loginPage.waitForElementToDisappear((By.xpath("//div[contains(@class='ui-widget-overlay')]")));
        loginPage.WaitTillElementIsPresent(timeSlot);
        String slot;
        String currentTime = datepicker.getCurrentLocalDateTimeStamp();
        String[] time = currentTime.split(":");
        String hour = time[0];
        int hr = Integer.parseInt(hour.trim());
        int nxt_slot = ++hr;
        String nxt_slotAsString = Integer.toString(nxt_slot);
        String date2 = nxt_slotAsString + ":00";
        String date1 = time[0] + ":30";
        if (currentTime.compareTo(date1) < 0) {
            slot = date1;
        } else {
            slot = date2;
        }
        loginPage.waitForElementToDisappear((By.xpath("//div[contains(@class='modal overlay show')]")));
//        loginPage.waitForElementToDisappear((By.xpath("//div[contains(@class='ui-widget-overlay')]")));
//        timeSlot.click();
        loginPage.selectFromDropDownByVisibleText(selectSlot,slot);
         WebElement slotElement = loginPage.getDriver().findElement(By.xpath("//option[contains(@value,'" + slot + "')]"));
//        loginPage.hoverOnElement(slotElement);
         loginPage.waitForElementVisibility(slotElement, 3000);
        String slotTime = slotElement.getText();
        String expected = slot.trim();
        String actual = slotTime.trim();
        return actual.equalsIgnoreCase(expected);   
    }

    public void selectingFutureDateAndVerifyStartTime(int days) {
    	 loginPage.waitForElementToDisappear((By.xpath("//div[contains(@class='modal overlay show')]")));
//       loginPage.waitForElementToDisappear((By.xpath("//div[contains(@class='ui-widget-overlay')]")));
    	String startingTime = "09:00";
    	loginPage.waitForElementToDisappear((By.xpath("//div[contains(@class='modal overlay show')]")));
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMMM/dd/yyyy");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, days);
//       datepicker.appointmentDate(simpleDateFormat.format(c.getTime()), d);
    //    timeSlot.click();
        loginPage.waitForSpinnerToDisappear();
        loginPage.selectFromDropDownByVisibleText(selectSlot,startingTime);
        WebElement firstOption = loginPage.getDriver().findElement(By.xpath("//custom-combo-new[@id='timeSlot']//option[@selected='selected']"));
//        loginPage.hoverOnElement(firstOption);
        loginPage.waitForElementVisibility(firstOption, 3000);
        Assert.assertEquals(firstOption.getText().trim(), startingTime);
    }

    public boolean byDefaultDuration() {
        loginPage.waitForPageLoad();
        loginPage.waitForElementToBeClickable(formDuration);
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) loginPage.getDriver();
        String actual = javascriptExecutor.executeScript("return angular.element(document.getElementById('duration')).scope().patient.appointmentData.slot_duration;").toString();
        return actual.equalsIgnoreCase("30");
    }

    public void autoSelectMobile(String mobileNum) {
        loginPage.WaitTillElementIsPresent(patientMobile);
        patientMobile.sendKeys(mobileNum);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        loginPage.WaitTillElementIsPresent(AutoSeletwb);
        loginPage.hoverOnElement(AutoSeletwb);
        AutoSeletwb.click();
    }

    public void autoSelectPatientName(String PatientName) {
        loginPage.WaitTillElementIsPresent(patientName);
        patientName.sendKeys(PatientName);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        loginPage.WaitTillElementIsPresent(AutoSeletwb);
        loginPage.hoverOnElement(AutoSeletwb);
        AutoSeletwb.click();
    }

    public void enterValidEmailAddressMsg() {
        loginPage.waitForPageLoad();
        loginPage.WaitTillElementIsPresent(enterValidEmailAddressMsg);
        Assert.assertTrue(checkWebElementDisplayed(enterValidEmailAddressMsg));
    }

    public void checkFirstOperatoriesIsSelected() {
        loginPage.waitForPageLoad();
        loginPage.waitForElementToBeClickable(firstOperatory);
        Assert.assertTrue(operatory.size() > 0 && firstOperatory.getAttribute("class").contains("selected"));
    }
    
    public void slectOperatory() {
        loginPage.waitForPageLoad();
        firstOperatory.click();
        Assert.assertTrue(operatory.size() > 0 && firstOperatory.getAttribute("class").contains("selected"));
    }

    public void operatories() {
//        loginPage.waitForPageLoad();
        if (checkWebElementDisplayed(rooms)) {
            Assert.assertTrue(operatory.size() > 0);
        }
    }

    public void rooms() {
//        loginPage.waitForPageLoad();
        if (checkWebElementDisplayed(rooms)) {
            Assert.assertTrue(totalRooms.size() > 0 && totalRooms.get(0).getAttribute("class").contains("selected"));
        }
    }
    
    public void scanners() {
    	 if (checkWebElementDisplayed(scanners)) {
    		 int getTotalScanners = totalScanners.size();
    		 if(getTotalScanners == 1) {
    			 Assert.assertTrue(totalScanners.get(0).getAttribute("class").contains("selected"));
    		 }
    		 else {
    			 Assert.assertTrue(totalScanners.size() > 0 && totalScanners.get(0).getAttribute("class").contains("selected"));
    		 }
         }
    }

    public void roomSelected() {
        loginPage.waitForPageLoad();
        Assert.assertTrue(totalRooms.get(0).getAttribute("class").contains("selected"));
    }

    public void checkingFirstOperatoryIsDisable() {
        loginPage.waitForPageLoad();
        loginPage.WaitTillElementIsPresent(firstOperatory);
        Assert.assertTrue(firstOperatory.getAttribute("class").contains("booked"));
    }
    
    public void checkSchedulerOptions() {
        loginPage.waitForSpinnerToDisappear();
        loginPage.waitForElementToBeClickable(goToSchedularBtn);
        goToSchedularBtn.click();
        loginPage.waitForElementVisibility(schedularTextMsg, 4000);
        Assert.assertTrue(schedularTextMsg.isDisplayed() && yesBtn.isDisplayed() && noBtn.isDisplayed());
        noBtn.click();
        loginPage.waitForPageToBecomeActive();
    }

//    public void checkSchedulerOptions() {
//        loginPage.waitForPageLoad();
//        loginPage.waitForElementToBeClickable(goToSchedularBtn);
//        goToSchedularBtn.click();
//        loginPage.waitForElementVisibility(schedularTextMsg, 4000);
////       loginPage.WaitTillElementIsPresent(schedularTextMsg);   
////        try {
////			Thread.sleep(3000);
////		} catch (InterruptedException e) {
////			e.printStackTrace();
////		}
//        Assert.assertTrue(schedularTextMsg.isDisplayed() && yesBtn.isDisplayed() && noBtn.isDisplayed());
//        noBtn.click();
//        loginPage.waitForElementToDisappear((By.xpath("//div[@class='modal-backdrop fade  in']")));
//    }
    
    public void clickOnGoToSchedulerYesBtn() {
      loginPage.waitForSpinnerToDisappear();
      goToSchedularBtn.click();
      loginPage.waitForElementVisibility(schedularTextMsg, 4000);
      loginPage.waitForElementToBeClickable(yesBtn);
      yesBtn.click();
  }


//    public void clickOnGoToSchedulerYesBtn() {
////        loginPage.waitForPageLoad();
////        loginPage.waitForElementToDisappear((By.xpath("//div[contains(@class='modal-backdrop fade  in")));
//        loginPage.waitForElementToBeClickable(goToSchedularBtn);
////        try {
////			Thread.sleep(3000);
////		} catch (InterruptedException e) {
////			e.printStackTrace();
////		}
//        goToSchedularBtn.click();
////        loginPage.WaitTillElementIsPresent(schedularTextMsg);
//        loginPage.waitForElementVisibility(schedularTextMsg, 4000);
//        loginPage.waitForElementToBeClickable(yesBtn);
//        yesBtn.click();
//    }


    public void getOperatoryErrMsg() {
        loginPage.WaitTillElementIsPresent(operatoriesDisableMsg);
        operatoriesDisableMsg.isDisplayed();
    }
    
    public void verifyAppointmentUpdateText(String text) {
    	loginPage.waitForElementVisibility(updateAppnmntTxt, 4000);
    	String getUpdateAppnmntTxt = updateAppnmntTxt.getText();
    	System.out.println(getUpdateAppnmntTxt);
    	Assert.assertEquals(text, getUpdateAppnmntTxt);
    }
    
    public void verifyrRestrictAppointmentUpdateText(String text) {
    	loginPage.waitForElementVisibility(restrictUpdateAppnmntTxt, 4000);
    	String getUpdateAppnmntTxt = restrictUpdateAppnmntTxt.getText();
    	Assert.assertEquals(text, getUpdateAppnmntTxt);
    }


    public void selectChiefComplaints(String chiefComplaints) {
        loginPage.waitForPageLoad();
        WebElement webElement = loginPage.getDriver().findElement(By.xpath("//span[text()='" + chiefComplaints + "']"));
        loginPage.WaitTillElementIsPresent(webElement);
        webElement.click();
    }

    private boolean checkWebElementDisplayed(WebElement webElement) {
        return (webElement.isDisplayed());
    }

    private boolean fieldReadOnly(WebElement webElement) {
        return (webElement.isEnabled());
    }
}
