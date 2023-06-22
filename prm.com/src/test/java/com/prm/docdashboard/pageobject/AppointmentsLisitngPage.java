package com.prm.docdashboard.pageobject;

import com.prm.pageobjects.utils.DatePicker;
import com.prm.pageobjects.utils.PCDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author Ajit yadav
 */
public class AppointmentsLisitngPage {
    private PCDriver loginPage;
    AppointmentAddPage appointmentPage;
    PatientDashboardPage patientDashboardPage;
    DatePicker datepicker;

    public AppointmentsLisitngPage(PCDriver loginPage) {
        this.loginPage = loginPage;
        PageFactory.initElements(loginPage.getDriver(), this);
        appointmentPage = new AppointmentAddPage(loginPage);
        patientDashboardPage = new PatientDashboardPage(loginPage);
        datepicker = new DatePicker(loginPage);
    }

    @FindBy(xpath = "//th[text()='Patient Name']")
    private WebElement patientName;
    @FindBy(xpath = "//th[text()='Patient Id']")
    private WebElement patientId;
    @FindBy(xpath = "//th[text()='Type']")
    private WebElement type;
    @FindBy(xpath = "//th[text()='Doctor']")
    private WebElement doctor;
    @FindBy(xpath = "//th[text()='Clinic']")
    private WebElement clinic;
    @FindBy(xpath = "//th[text()='Action']")
    private WebElement actionBtn;
    @FindBy(xpath = "//input[@id='serach_option']")
    public WebElement docDropDoownBtn;
    @FindBy(id = "fromDate")
    private WebElement fromDateTxt;
    @FindBy(xpath = "//button[contains(@ng-click,'fromDate')]")
    private WebElement fromDateCalenderIcon;
    @FindBy(id = "toDate")
    private WebElement toDateTxt;
    @FindBy(xpath = "//button[contains(@ng-click,'toDate')]")
    private WebElement toDateCalenderIcon;
    @FindBy(id = "type")
    private WebElement appTypeDrpDwn;
    @FindBy(xpath= "//i[text()='Search']/preceding-sibling::span")
    private WebElement searchBtn;
    @FindBy(xpath = "(//a[@class='next'])[1]")
    private WebElement nextBtn;
    @FindBy(xpath = "//body[contains(@data-ng-class,'fbDesign')]")
    private WebElement Alert;
    // .......................................
    @FindBy(xpath = "//td[contains(@class,'dateTDwdth')]")
    private List<WebElement> timeSlotApp;
    @FindBy(xpath = "//span[contains(text(),'Tentative')]")
    private List<WebElement> tentative;
    @FindBy(xpath = "//span[contains(text(),'Confirmed')]")
    private List<WebElement> Confirmed;
    @FindBy(xpath = "//div[contains(@data-ng-click,'viewApp')]//span[@class='actn-icn view']")
    private List<WebElement> viewBtn;
    @FindBy(xpath = "//div[contains(@data-ng-click,'editApp')]//span[@class='actn-icn edit']")
    private List<WebElement> editBtn;
    @FindBy(id = "dashBoardFBList")
    private List<WebElement> feedbackBtn;
    @FindBy(xpath = "//div[contains(@data-ng-click,'confirmApp')]//span[@class='actn-icn delete']")
    private List<WebElement> deleteBtn;
    @FindBy(xpath = "//div[contains(@data-ng-click,'confirmApp')]//span[@class='actn-icn noShow']")
    private List<WebElement> noShowBtn;
    @FindBy(xpath = "//table[@id='clinicApptAllListTable']//td[contains(text(),'No records found')]")
    public WebElement noRecordFoundMsg;
    @FindBy(id = "reason")
    private WebElement noShowVisibleText;
    // ......................After clicking on view view Btn Elements.....
    @FindBy(xpath = "//div[contains(text(),'Appointment')] ")
    private WebElement headerView;
    @FindBy(xpath = "//b[text()='Patient Id']/../following-sibling::div")
    private WebElement patientIdView;
    @FindBy(xpath = "//b[text()='Patient Name']/../following-sibling::div")
    private WebElement patientNameView;
    @FindBy(xpath = "//b[text()='Email Id']/../following-sibling::div")
    private WebElement emailIdView;
    @FindBy(xpath = "//b[text()='Mobile Number']/../following-sibling::div")
    private WebElement mobileNumView;
    @FindBy(xpath = "//b[text()='Clinic Name']/../following-sibling::div")
    private WebElement clinicNameView;
    @FindBy(xpath = "//b[text()='Date-Time']/../following-sibling::div")
    private WebElement DateOfAppView;
    @FindBy(xpath = "//b[text()='Duration']/../following-sibling::div")
    private WebElement durationView;
    @FindBy(xpath = "//b[text()='Doctor']/../following-sibling::div")
    private WebElement DoctorView;
    @FindBy(xpath = "//b[text()='Operatory']/../following-sibling::div")
    private WebElement operatoriesView;
    @FindBy(xpath = "//b[text()='Room']/../following-sibling::div")
    private WebElement roomsView;
    @FindBy(xpath = "//b[text()='Referral']/../following-sibling::div")
    private WebElement referralView;
    @FindBy(xpath = "//b[text()='Referral Notes']/../following-sibling::div")
    private WebElement referralDetailsView;
    @FindBy(xpath = "//b[text()='Source']/../following-sibling::div")
    private WebElement sourceView;
    @FindBy(xpath = "//b[text()='Chief Complaint']/../following-sibling::div")
    private WebElement chiefComplaintView;
    @FindBy(xpath = "//b[text()='Notes']/../following-sibling::div")
    private WebElement notesView;
    @FindBy(className = "fancybox-iframe")
    private WebElement frameView;
    @FindBy(id = "reason")
    private WebElement reasonOfDelApp;
    @FindBy(xpath = "//button[@class='close']/span[contains(@class,'cncl')]")
    private WebElement noBtn;
    @FindBy(xpath = "//button[@type='submit']")
    private WebElement yesForDeleteBtn;
    @FindBy(xpath = "//button[@type='submit']")
    private WebElement yesForNoShowBtn;
    @FindBy(xpath = "//div[@class='clearfix alertMessage'][contains(.,'Appointment deleted successfully')]")
    private WebElement deleteSuccessMsg;
    @FindBy(xpath = "//table[@id='appLis']//span[contains(@data-ng-click,'gotoPatientDashboard')]")
    private List<WebElement> patientDashboardCall;
    @FindBy(xpath = "(//a[@class='first']/i[contains(@class,'double-left')])[1]")
    private WebElement firstButton;
    @FindBy(id = "status")
    private WebElement apptStatusDropdownList;
    @FindBy(xpath = "//span[text()='New']")
    private List<WebElement> newStatus;
    @FindBy(xpath = "//span[text()='Follow On']")
    private List<WebElement> followOnStatus;
    @FindBy(xpath = "//span[text()='No Show']")
    private List<WebElement> noShowStatus;
    @FindBy(xpath = "//span[text()='Checked In']")
    private List<WebElement> checkinStatus;
    @FindBy(xpath = "//span[text()='Expired']")
    private List<WebElement> expiredStatus;
    @FindBy(xpath = "//span[text()='Repeat']")
    private List<WebElement> repeatOnStatus;
    @FindBy(xpath = "//span[@class='actn-icn noShow']")
    private List<WebElement> dashboardNoShowIcon;
    @FindBy(id = "dashboardViewIconExpired")
    private List<WebElement> dashboardIconExpired;
    @FindBy(xpath = "//span[@class='dashboardViewIconExpired']")
    private List<WebElement> expired;
    @FindBy(id = "dashboardViewIconCheckIn")
    private List<WebElement> dashboardIconCheckIn;
    @FindBy(xpath = "//h4[text()='Are you sure you want to delete this appointment?']")
    private WebElement textOnDelete;
    @FindBy(xpath = "//div[text()='Select reason']")
    private WebElement reasonErrorMsg;
//    @FindBy(xpath = "//iframe[contains(@id,'fancybox-frame')]")
//    private WebElement viewFrame;
    @FindBy(id = "tentative")
    private WebElement tentativeHeaderView;
    @FindBy(xpath = "//button[@class='close']")
    private WebElement closeViewModal;
    @FindBy(xpath = "//div[@class='clearfix topPagination pagingUI ng-scope']//a[@class='last']")
    private WebElement lastPage;


    public void openGreenFlag(String patient) {
        loginPage.waitForPageLoad();
        try {
            WebElement web = loginPage.getDriver().findElement(By.xpath("//span[contains(@data-pname,'" + patient + "')]"));
            web.click();
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void verifyErrorMessage() {
        loginPage.waitForPageLoad();
        loginPage.waitForElementToBeClickable(reasonErrorMsg);
        Assert.assertTrue(checkIfElementDisplayed(reasonErrorMsg));
    }

    public void intiateData() {
        loginPage.waitForPageLoad();
        loginPage.waitForElementToBeClickable(firstButton);
        firstButton.click();
    }

    public void enterFromDate(String SelectDate) {
//        loginPage.waitForPageLoad();
        loginPage.waitForElementToBeClickable(fromDateTxt);
  //New code start    
    String[] split_date = SelectDate.split("/");
        String year = split_date[2].substring(2);
        String select_date = split_date[1]+"-"+split_date[0]+"-"+year;
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor)loginPage.getDriver();
        javascriptExecutor.executeScript ("document.getElementById('fromDate').removeAttribute('readonly',0);"); // Enables the from date box
        fromDateTxt.clear();
        fromDateTxt.sendKeys(select_date); //Enter date in required format
// New Code End
  //    datepicker.selectDateofAppCal(SelectDate, fromDateTxt);
    }

    public void enterToDate(String SelectDate) {
        loginPage.waitForPageLoad();
        loginPage.waitForElementToBeClickable(toDateTxt);
//New code start 
        String[] split_date = SelectDate.split("/");
        String year = split_date[2].substring(2);
        String select_date = split_date[1]+"-"+split_date[0]+"-"+year;
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) loginPage.getDriver();
        javascriptExecutor.executeScript ("document.getElementById('toDate').removeAttribute('readonly',0);"); // Enables the To date box
        toDateTxt.clear();
        toDateTxt.sendKeys(select_date); //Enter date in required format
// New Code End
 //     datepicker.selectDateofAppCal(SelectDate, toDateCalenderIcon);
    }

    public void selectAppType(String AppType) {
        loginPage.waitForPageLoad();
        loginPage.waitForElementToBeClickable(appTypeDrpDwn);
        loginPage.selectFromDropDownByVisibleText(appTypeDrpDwn, AppType);
    }

    public void selectAppStatusType(String AppStatusType) {
        loginPage.waitForPageLoad();
        loginPage.waitForElementToBeClickable(apptStatusDropdownList);
        loginPage.selectFromDropDownByVisibleText(apptStatusDropdownList, AppStatusType);
    }
    
    
    
    public void clickOnSearchBtn() {
    	loginPage.waitForSpinnerToDisappear();
        loginPage.waitForElementToBeClickable(searchBtn);
        searchBtn.click();
    }

//    public void clickOnSearchBtn() {
//        loginPage.waitForPageLoad();
//        loginPage.waitForElementToBeClickable(searchBtn);
//        try {
//			Thread.sleep(5000);
//		} catch (InterruptedException e1) {
//			e1.printStackTrace();
//		}
//        searchBtn.click();
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }

    public boolean verifiyAppListHP() {
        loginPage.waitForPageLoad();
        boolean flag = loginPage.getTitle().contains("Clinic Appointments") || loginPage.getTitle().contains("Clinic Events");
        return flag;
    }

    public void selectDoctorFRmDrpDwn(String doctorName) {
        loginPage.waitForPageLoad();
        loginPage.waitForElementToBeClickable(docDropDoownBtn);
        loginPage.selectFromDropDownByVisibleText(docDropDoownBtn, doctorName);
    }

    public void verifiyAppointmentListElements() {
        loginPage.waitForPageLoad();
        boolean flag = patientName.isDisplayed() && patientId.isDisplayed() && type.isDisplayed()
                && doctor.isDisplayed() && appTypeDrpDwn.isDisplayed() && clinic.isDisplayed()
                && actionBtn.isDisplayed() && docDropDoownBtn.isDisplayed() && fromDateTxt.isDisplayed()
                && toDateTxt.isDisplayed() && searchBtn.isDisplayed();
        Assert.assertTrue(flag);
    }

    public void appointmentTypeFilter(String AppType) {
        loginPage.waitForElementToBeEnable(By.id("clinicApptAllListTable_next"));
        List<WebElement> webElement = loginPage.getDriver()
                .findElements(By.xpath("//span[contains(text(),'" + AppType + "')]"));
        boolean flag = true;
        while (flag) {
            try {
                boolean flag1;
                if (flag1 = (timeSlotApp.size() == (webElement.size() - 1))) {
                    if (!nextBtn.getAttribute("class").contains("disabled")) {
                        for (int i = 0; timeSlotApp.size() > i; i++) {
                            Assert.assertTrue(tentative.get(i + 1).isDisplayed() && viewBtn.get(i).isDisplayed()
                                    && editBtn.get(i).isDisplayed());
                        }
                        nextBtn.click();
                        loginPage.waitForElementToBeEnable(By.id("clinicApptAllListTable_next"));
                    }
                } else {
                    Assert.assertTrue(flag1);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            flag = !nextBtn.getAttribute("class").contains("disabled");
            if (!flag) {
                webElement = loginPage.getDriver().findElements(By.xpath("//span[contains(text(),'" + AppType + "')]"));
                Assert.assertTrue(timeSlotApp.size() == (webElement.size() - 1));
                for (int i = 0; timeSlotApp.size() > i; i++) {
                    Assert.assertTrue(tentative.get(i + 1).isDisplayed() && viewBtn.get(i).isDisplayed()
                            && editBtn.get(i).isDisplayed());
                }
            }
        }
    }

    public void doctorTypeFilter(String DoctorName) {
        loginPage.waitForElementToBeEnable(By.id("clinicApptAllListTable_next"));
        int appointments_Size = timeSlotApp.size();
        List<WebElement> filterDoctor = loginPage.getDriver()
                .findElements(By.xpath("//td[contains(text(),'" + DoctorName + "')]"));
        int input_Doctor_Filter = filterDoctor.size();
        boolean flag = true;
        while (flag) {
            try {
                boolean flag1;
                if (flag1 = (appointments_Size == input_Doctor_Filter)) {
                    if (!nextBtn.getAttribute("class").contains("disabled")) {
                        nextBtn.click();
                        loginPage.waitForElementToBeEnable(By.id("clinicApptAllListTable_next"));
                    }
                } else {
                    Assert.assertTrue(flag1);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            flag = !nextBtn.getAttribute("class").contains("disabled");
            if (!flag) {
                List<WebElement> filterDoctor1 = loginPage.getDriver()
                        .findElements(By.xpath("//td[contains(text(),'" + DoctorName + "')]"));
                int input_Doctor_Filter1 = filterDoctor1.size();
                Assert.assertTrue(timeSlotApp.size() == input_Doctor_Filter1);
            }
        }
    }

    public void checkingFromDateIsBeforeAfterDate(String frmDate, String toDate) {
        SimpleDateFormat dt = new SimpleDateFormat("MM/dd/yyyy");
        try {
            Date Date1 = dt.parse(frmDate);
            Date Date2 = dt.parse(toDate);
            Assert.assertTrue(Date1.before(Date2));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void dateFilter(String frmDate, String toDate) {
        loginPage.waitForPageLoad();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        loginPage.waitForElementToBeClickable(searchBtn);
        SimpleDateFormat dt = new SimpleDateFormat("MM/dd/yyyy");
        SimpleDateFormat dt1 = new SimpleDateFormat("dd-MM-yy HH:mm");
        Date date1, date2;
        try {
            date1 = dt.parse(frmDate);
            date2 = dt.parse(toDate);
            boolean flag = true;
            while (flag) {
                for (int i = 0; timeSlotApp.size() > i; i++) {
                    String Apptime = timeSlotApp.get(i).getText();
                    Date AppDate = dt1.parse(Apptime);
                    boolean flag1;
                    if (flag1 = AppDate.before(date2) && AppDate.after(date1)) {
                        Assert.assertTrue(flag1);
                    } else {
                        Assert.assertTrue(flag1);
                    }
                }
                flag = !nextBtn.getAttribute("class").contains("disabled");
                if (flag) {
                    nextBtn.click();
                } else if (timeSlotApp.size() > 0) {
                    for (int i = 0; timeSlotApp.size() > i; i++) {
                        String Apptime = timeSlotApp.get(i).getText();
                        Date AppDate = dt1.parse(Apptime);
                        Assert.assertTrue(AppDate.before(date2) && AppDate.after(date1));
                    }
                } else {
                    Reporter.log("There is no Data", true);
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void verifyBehaviourOfViewBtn() {
        loginPage.waitForPageLoad();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement view = loginPage.getDriver().findElement(
                By.xpath("(//th[text()='Action']/../../..//tbody//a[@class='listBtn viewAppointment'])[1]"));
        loginPage.waitForElementToBeClickable(view);
        view.click();
  //      loginPage.switchToFrame(frameView);
        loginPage.waitForElementToBeClickable(patientIdView);
        boolean flag;
        if (flag = patientIdView.isDisplayed() && patientNameView.isDisplayed() && emailIdView.isDisplayed()
                && mobileNumView.isDisplayed() && clinicNameView.isDisplayed()
                && DateOfAppView.isDisplayed() && durationView.isDisplayed() && DoctorView.isDisplayed() && operatoriesView.isDisplayed()
                && referralView.isDisplayed() && chiefComplaintView.isDisplayed() && sourceView.isDisplayed()
                && notesView.isDisplayed())  {
            if (patientIdView.getText() != "NA"
                    || mobileNumView.getText() != "NA"
                    || patientNameView.getText() != "NA"
                    || DateOfAppView.getText() != "NA"
                    || DoctorView.getText() != "NA"
                    || operatoriesView.getText() != "NA") {
            }
        	Assert.assertTrue(flag);
        } else {
            Assert.assertTrue(false);
        }
        loginPage.switchToDefaultContent();
        // ---------sikuli here------
        closeViewModal.click();
    }

    public void tentativeHeaderView() {
        loginPage.waitForPageLoad();
        Assert.assertTrue(checkIfElementDisplayed(tentativeHeaderView));
    }

    public void patientIdView() {
//        loginPage.waitForPageLoad();
        loginPage.waitForElementToBeClickable(headerView);
        Assert.assertTrue(checkIfElementDisplayed(headerView) && !(patientIdView.getText().contains("NA") || patientIdView.getText().contains("null")));
    }

    public void patientNameView(String patient) {
        loginPage.waitForPageLoad();
//        loginPage.waitForElementToBeClickable(patientNameView);
        loginPage.waitForElementVisibility(patientNameView,4000);
        Assert.assertEquals(patientNameView.getText().trim(), patient);
    }

    public void emailIdView(String email) {
        loginPage.waitForPageLoad();
        loginPage.waitForElementToBeClickable(emailIdView);
        Assert.assertEquals(emailIdView.getText().trim(), email.trim());
    }

    public void mobileNumView(String mobile) {
        loginPage.waitForPageLoad();
        loginPage.waitForElementToBeClickable(mobileNumView);
        Assert.assertEquals(mobileNumView.getText().trim(), mobile);
    }

    public void clinicNameView(String clinic) {
        loginPage.waitForPageLoad();
        loginPage.waitForElementToBeClickable(clinicNameView);
        Assert.assertEquals(clinicNameView.getText().trim(), clinic);
    }

    public void DateOfAppView() {
        loginPage.waitForPageLoad();
        loginPage.waitForElementToBeClickable(DateOfAppView);
        Assert.assertTrue(!DateOfAppView.getText().contains("NA") || DateOfAppView.getText().contains("null"));
    }

    public void durationView(String duration) {
        loginPage.waitForSpinnerToDisappear();;
        loginPage.waitForElementToBeClickable(durationView);
        System.out.println("Jai Ho"+durationView.getText().trim());
        System.out.println("Jai Hind"+duration);
        Assert.assertTrue(durationView.getText().trim().contains(duration));
    }

    public void doctorView(String doctor) {
        loginPage.waitForPageLoad();
        loginPage.waitForElementToBeClickable(DoctorView);
        Assert.assertEquals(DoctorView.getText().trim(), doctor);
    }

    public void operatoriesView() {
        loginPage.waitForPageLoad();
        loginPage.waitForElementToBeClickable(operatoriesView);
        Assert.assertTrue(!operatoriesView.getText().contains("NA") || operatoriesView.getText().contains("null"));
    }

    public void roomsView() {
        loginPage.waitForPageLoad();
        loginPage.waitForElementToBeClickable(roomsView);
        Assert.assertTrue(!roomsView.getText().contains("NA") || roomsView.getText().contains("null"));
    }
    
    public void roomsViewEconsult() {
        loginPage.waitForPageLoad();
        loginPage.waitForElementToBeClickable(roomsView);
        Assert.assertTrue(!roomsView.getText().contains("NA") || roomsView.getText().contains("1"));
    }

    public void referralView(String referral) {
        loginPage.waitForPageLoad();
        loginPage.waitForElementToBeClickable(referralView);
        Assert.assertEquals(referralView.getText().trim(), referral);
    }

    public void referralDetails(String referral) {
        loginPage.waitForPageLoad();
        Assert.assertEquals(referralDetailsView.getText().trim(), referral);
    }

    public void sourceView(String source) {
        loginPage.waitForPageLoad();
        loginPage.waitForElementToBeClickable(sourceView);
        Assert.assertEquals(sourceView.getText().trim(), source);
    }

    public void chiefComplaintView(String chiefComplaint) {
        loginPage.waitForPageLoad();
        loginPage.waitForElementToBeClickable(chiefComplaintView);
        Assert.assertEquals(chiefComplaintView.getText().trim(), chiefComplaint);
    }

    public void notesView(String note) {
        loginPage.waitForPageLoad();
        loginPage.waitForElementToBeClickable(notesView);
        Assert.assertEquals(notesView.getText().trim(), note);
    }
    
    public void closeViewModal() {
        loginPage.waitForElementVisibility(closeViewModal, 4000);
        closeViewModal.click();
    }

//    public void closeViewModal() {
//        loginPage.switchToDefaultContent();
//        closeViewModal.click();
//    }
    
    public void clickOnLastPage() {
    	 loginPage.waitForSpinnerToDisappear();
    	loginPage.waitForElementToDisappear((By.xpath("//div[contains(@class='modal overlay show')]")));
        loginPage.waitForElementVisibility(lastPage, 4000);
        lastPage.click();
    }

    public void confirmWithConfirmed() {
        loginPage.waitForElementToBeEnable(By.id("clinicApptAllListTable_next"));
        boolean flag = true;
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        while (flag) {
            boolean flag1, flag2;
            if (flag1 = (timeSlotApp.size() == (Confirmed.size() - 1))
                    && ((Confirmed.size() - 1) == (newStatus.size() + followOnStatus.size() + repeatOnStatus.size()))) {
                try {
                    for (int i = 0; (Confirmed.size() - 1) > i; i++) {
                        if (flag2 = (Confirmed.size() - 1) > 0) {
                            flag2 = checkIfElementDisplayed(viewBtn.get(i)) && checkIfElementDisplayed(Confirmed.get(i))
                                    && (checkIfConfirmed(deleteBtn.get(i), editBtn.get(i))
                                    || checkIfNoShow(dashboardNoShowIcon.get(i), dashboardIconExpired.get(i),
                                    dashboardIconCheckIn.get((i))));
//								flag2=(Confirmed.get(i+1).isDisplayed()&&deleteBtn.get(i).isDisplayed()&&editBtn.get(i).isDisplayed()&&viewBtn.get(i).isDisplayed()
//										||(flag2=Confirmed.get(i+1).isDisplayed()&&viewBtn.get(i).isDisplayed()&&dashboardNoShowIcon.get(i).isDisplayed()&&dashboardIconExpired.get(i).isDisplayed()
//										||(Confirmed.get(i+1).isDisplayed()&&viewBtn.get(i).isDisplayed()&&dashboardNoShowIcon.get(i).isDisplayed()&&dashboardIconCheckIn.get(i).isDisplayed())));
                            Assert.assertTrue(flag2);
                        }
                    }
                    flag = !nextBtn.getAttribute("class").contains("disabled");
                    if (flag) {
                        nextBtn.click();
                    } else {
                        if (flag1 = (timeSlotApp.size() == (Confirmed.size() - 1)) && ((Confirmed.size()
                                - 1) == (newStatus.size() + followOnStatus.size() + repeatOnStatus.size()))) {
                            for (int i = 0; (Confirmed.size() - 1) > i; i++) {
                                if (flag2 = (Confirmed.size() - 1) > 0) {
                                    System.out.println("it is on delete2");

                                    flag2 = checkIfElementDisplayed(viewBtn.get(i))
                                            && checkIfElementDisplayed(Confirmed.get(i))
                                            && (checkIfConfirmed(deleteBtn.get(i), editBtn.get(i)) || checkIfNoShow(
                                            dashboardNoShowIcon.get(i), dashboardIconExpired.get(i),
                                            dashboardIconCheckIn.get((i))));
//										flag2=(Confirmed.get(i+1).isDisplayed()&&deleteBtn.get(i).isDisplayed()&&editBtn.get(i).isDisplayed()&&viewBtn.get(i).isDisplayed()
//												||(flag2=Confirmed.get(i+1).isDisplayed()&&viewBtn.get(i).isDisplayed()&&dashboardNoShowIcon.get(i).isDisplayed()&&dashboardIconExpired.get(i).isDisplayed()
//												||(Confirmed.get(i+1).isDisplayed()&&viewBtn.get(i).isDisplayed()&&dashboardNoShowIcon.get(i).isDisplayed()&&dashboardIconCheckIn.get(i).isDisplayed())));
                                    Assert.assertTrue(flag2);
                                }
                            }
                        }
                    }
                } catch (IndexOutOfBoundsException e) {
                    e.printStackTrace();
                }
            } else {
                Assert.assertTrue(flag1);
            }
        }
    }

    public void verifyConfirmWithNoShow() {
        loginPage.waitForPageLoad();
        loginPage.waitForElementToBeEnable(By.id("clinicApptAllListTable_next"));
        boolean flag = true;
        while (flag) {
            boolean flag1;
            if ((flag1 = timeSlotApp.size() == noShowStatus.size())
                    && (noShowStatus.size() == (newStatus.size() + followOnStatus.size() + repeatOnStatus.size()))) {
                for (int i = 0; noShowStatus.size() > i; i++) {
                    Assert.assertTrue(Confirmed.get(i + 1).isDisplayed() && noShowStatus.get(i).isDisplayed()
                            && viewBtn.get(i).isDisplayed() && dashboardNoShowIcon.get(i).isDisplayed());
                }
                flag = !nextBtn.getAttribute("class").contains("disabled");
                if (flag) {
                    nextBtn.click();
                } else {
                    if ((flag1 = timeSlotApp.size() == noShowStatus.size()) && (noShowStatus
                            .size() == (newStatus.size() + followOnStatus.size() + repeatOnStatus.size()))) {
                        for (int i = 0; noShowStatus.size() > i; i++) {
                            Assert.assertTrue(Confirmed.get(i + 1).isDisplayed() && noShowStatus.get(i).isDisplayed()
                                    && viewBtn.get(i).isDisplayed() && dashboardNoShowIcon.get(i).isDisplayed());
                        }
                    } else {
                        Assert.assertTrue(flag1);
                    }
                }
            } else {
                Assert.assertTrue(flag1);
            }
        }
    }

    public void confirmWithShow() {
        loginPage.waitForPageLoad();
        loginPage.waitForElementToBeEnable(By.id("clinicApptAllListTable_next"));
        boolean flag = true;
        while (flag) {
            boolean flag1;
            if ((flag1 = timeSlotApp.size() == (Confirmed.size() - 1))
                    && ((Confirmed.size() - 1) == (newStatus.size() + followOnStatus.size() + repeatOnStatus.size()))) {
                for (int i = 0; timeSlotApp.size() > i; i++) {
                    Assert.assertTrue(Confirmed.get(i + 1).isDisplayed() && viewBtn.get(i).isDisplayed());
                }
                flag = !nextBtn.getAttribute("class").contains("disabled");
                if (flag) {
                    nextBtn.click();
                } else {
                    if ((flag1 = timeSlotApp.size() == (Confirmed.size() - 1)) && ((Confirmed.size()
                            - 1) == (newStatus.size() + followOnStatus.size() + repeatOnStatus.size()))) {
                        for (int i = 0; timeSlotApp.size() > i; i++) {
                            Assert.assertTrue(Confirmed.get(i + 1).isDisplayed() && viewBtn.get(i).isDisplayed());
                        }
                    } else {
                        Assert.assertTrue(flag1);
                    }
                }
            } else {
                Assert.assertTrue(flag1);
            }
        }
    }

    public void confirmWithCheckIn() {
        loginPage.waitForPageLoad();
        loginPage.waitForElementToBeEnable(By.id("clinicApptAllListTable_next"));
        boolean flag = true;
        while (flag) {
            boolean flag1;
            if ((flag1 = timeSlotApp.size() == checkinStatus.size())
                    && (checkinStatus.size() == (newStatus.size() + followOnStatus.size() + repeatOnStatus.size()))) {
                for (int i = 0; checkinStatus.size() > i; i++) {
                    Assert.assertTrue(Confirmed.get(i + 1).isDisplayed() && checkinStatus.get(i).isDisplayed()
                            && viewBtn.get(i).isDisplayed());
                }
                flag = !nextBtn.getAttribute("class").contains("disabled");
                if (flag) {
                    nextBtn.click();
                } else {
                    if ((flag1 = timeSlotApp.size() == checkinStatus.size()) && (checkinStatus
                            .size() == (newStatus.size() + followOnStatus.size() + repeatOnStatus.size()))) {
                        for (int i = 0; checkinStatus.size() > i; i++) {
                            Assert.assertTrue(Confirmed.get(i + 1).isDisplayed() && checkinStatus.get(i).isDisplayed()
                                    && viewBtn.get(i).isDisplayed());
                        }
                    } else {
                        Assert.assertTrue(flag1);
                    }
                }
            } else {
                Assert.assertTrue(flag1);
            }
        }
    }

    public void confirmWithExpired() {
        loginPage.waitForPageLoad();
        loginPage.waitForElementToBeEnable(By.id("clinicApptAllListTable_next"));
        boolean flag = true;
        while (flag) {
            boolean flag1;
            if ((flag1 = timeSlotApp.size() == (expiredStatus.size() - 1)) && ((expiredStatus.size()
                    - 1) == (newStatus.size() + followOnStatus.size() + repeatOnStatus.size()))) {
                for (int i = 0; (expiredStatus.size() - 1) > i; i++) {
                    Assert.assertTrue(Confirmed.get(i + 1).isDisplayed() && expiredStatus.get(i + 1).isDisplayed()
                            && viewBtn.get(i).isDisplayed() && expired.get(i).isDisplayed());
                }
                flag = !nextBtn.getAttribute("class").contains("disabled");
                if (flag) {
                    nextBtn.click();
                } else {
                    if ((flag1 = timeSlotApp.size() == (expiredStatus.size() - 1)) && ((expiredStatus.size()
                            - 1) == (newStatus.size() + followOnStatus.size() + repeatOnStatus.size()))) {
                        for (int i = 0; (expiredStatus.size() - 1) > i; i++) {
                            Assert.assertTrue(Confirmed.get(i).isDisplayed() && expiredStatus.get(i + 1).isDisplayed()
                                    && viewBtn.get(i).isDisplayed() && expired.get(i).isDisplayed());
                        }
                    } else {
                        Assert.assertTrue(flag1);
                    }
                }
            } else {
                Assert.assertTrue(flag1);
            }
        }
    }


    public void behaviourOfNoShowBtn(String reason) {
        loginPage.waitForPageLoad();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (!noRecordFoundMsg.isDisplayed()) {
            for (int i = 0; i < noShowBtn.size(); i++) {
                if (noShowBtn.get(i).isDisplayed()) {
                    noShowBtn.get(i).click();
                    loginPage.waitForPageLoad();
                    loginPage.selectFromDropDownByVisibleText(noShowVisibleText, reason);
                    yesForNoShowBtn.click();
                }
                break;
            }
        } else {
            Reporter.log("There is No NoShowBtn", true);
        }
    }

    public void noRecordFoundMsg() {
        loginPage.waitForElementToBeClickable(noRecordFoundMsg);
        Assert.assertTrue(noRecordFoundMsg.isDisplayed());
    }
    
    public void clickOnPatient(String patient) {
            loginPage.waitForPageToBecomeActive();
            WebElement element = loginPage.getDriver().findElement(By.xpath("//span[@class='dis-inline-blk ng-scope']//span[contains(text(),'"+patient+"')]"));
            loginPage.waitForElementToBeClickable(element);
            element.click();
            loginPage.waitForPageToBecomeActive();
    }

//    public void clickOnPatient(String patient) {
//        loginPage.waitForPageLoad();
//        try {
//            Thread.sleep(3000);
//            WebElement element = loginPage.getDriver().findElement(By.xpath("//span[contains(text(),'" + patient + "')]"));
//            loginPage.waitForElementToBeClickable(element);
//            element.click();
//            Thread.sleep(4000);
//        } catch (InterruptedException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }

    public void verifyWebElementDeletePopup() {
        loginPage.waitForPageLoad();
        boolean flag = checkIfElementDisplayed(textOnDelete) && checkIfElementDisplayed(yesForDeleteBtn)
                && checkIfElementDisplayed(noBtn);
        Assert.assertTrue(flag);
    }

    public void clickNoDeletePopup() {
//        loginPage.waitForPageLoad();
        loginPage.waitForElementToBeClickable(noBtn);
        noBtn.click();
    }

    public void selectReasonDelete() {
//        loginPage.waitForPageLoad();
        loginPage.waitForElementToBeClickable(reasonOfDelApp);
        loginPage.selectFromDropDownByIndex(reasonOfDelApp, 2);
    }

    public void clickYesDeletePopup() {
//        loginPage.waitForPageLoad();
        loginPage.waitForElementToBeClickable(yesForDeleteBtn);
        yesForDeleteBtn.click();
    }
    
    public void strikeOff(String patient) {
        loginPage.waitForPageToBecomeActive();
        WebElement element = loginPage.getDriver().findElement(By.xpath("//span[contains(text(),'"+patient+"')]/../.."));
        loginPage.waitForElementVisibility(element, 4000);
        Assert.assertTrue(element.getAttribute("class").contains("dis-inline-blk ng-scope deleted"));
    }
    

//    public void strikeOff(String patient) {
//        loginPage.waitForPageLoad();
//        try {
//            Thread.sleep(3500);
//        } catch (InterruptedException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        WebElement element = loginPage.getDriver().findElement(By.xpath("//span[contains(text(),'" + patient + "')]"));
//        loginPage.waitForElementToBeClickable(element);
//        Assert.assertTrue(element.getAttribute("class").contains("strikeOff"));
//    }

    public void appointmentStatus(String patient, String status) {
        loginPage.waitForSpinnerToDisappear();
        try {
            WebElement element = loginPage.getDriver().findElement(By.xpath("//span[contains(text(),'" + patient + "')]/../../../following-sibling::td//span[contains(@class,'repeat']"));
            loginPage.waitForElementToBeClickable(element);
            Assert.assertTrue(element.getText().contains(status));
        } catch (NoSuchElementException e) {
            List<WebElement> pagination = loginPage.getDriver().findElements(By.xpath("//a[@id='clinicApptAllListTable_first']/../span//a"));
            for (int i = 0; pagination.size() > i; i++) {
                nextBtn.click();
                List<WebElement> element = loginPage.getDriver().findElements(By.xpath("//span[contains(text(),'" + patient + "')]/../../../following-sibling::td//span[contains(@class,'repeat']"));
                if (element.size() > 0) {
                    element.get(0).click();
                    break;
                } else {
                    continue;
                }
            }
        }
    }

    public void appointmentType(String patient, String appointmentType) {
    	loginPage.waitForSpinnerToDisappear();
        WebElement element =  loginPage.getDriver().findElement(By.xpath("//span[@class='dis-inline-blk ng-scope']"
        		+ "//span[contains(text(),'"+patient+"')]"
        		+ "/../../../../../..//span[contains(text(),'"+appointmentType+"')]"));
        loginPage.waitForElementToBeClickable(element);
        Assert.assertTrue(element.isDisplayed());
    }

    public void patientFlag(String patient, String flag) {
        loginPage.waitForPageLoad();
        WebElement element = loginPage.getDriver().findElement(By.xpath("//span[contains(text(),'"+patient+"')]/../../following-sibling::span/span[contains(@class,'"+flag+"')]"));
        loginPage.waitForElementToBeClickable(element);
        Assert.assertTrue(element.isDisplayed());
    }
    
    public void type(String patient, String appointmentType) {
    	loginPage.waitForSpinnerToDisappear();
        WebElement element = loginPage.getDriver().findElement(By.xpath("//span[@class='dis-inline-blk ng-scope']"
        		+ "//span[contains(text(),'"+patient+"')]"
        		+ "/../../../../../..//span[contains(text(),'"+appointmentType+"')]"));
        loginPage.scrollToView(element);
        loginPage.waitForElementVisibility(element, 4000);
        loginPage.waitForElementToBeClickable(element);
        Assert.assertTrue(element.isDisplayed());
    }

//    public void type(String patient, String appointmentType) {
//        loginPage.waitForPageLoad();
//        WebElement element = loginPage.getDriver().findElement(By.xpath("//span[contains(text(),'" + patient
//                + "')]/../../..//following-sibling::td/span[contains(text(),'" + appointmentType + "')]"));
//        loginPage.waitForElementToBeClickable(element);
//        Assert.assertTrue(element.isDisplayed());
//    }
    
    public void verifyClinic(String patient, String clinic) {
        WebElement element = loginPage.getDriver().findElement(By.xpath("//span[@class='dis-inline-blk ng-scope']"
        		+ "//span[contains(text(),'"+patient+"')]"
        		+ "/../../../../../..//td[contains(text(),'"+clinic+"')]"));
        Assert.assertTrue(element.isDisplayed());
    }

//    public void verifyClinic(String patient, String clinic) {
//        loginPage.waitForPageLoad();
//        WebElement element = loginPage.getDriver().findElement(By.xpath("//span[contains(text(),'" + patient
//                + "')]/../../..//following-sibling::td[contains(text(),'" + clinic + "')]"));
//        Assert.assertTrue(element.isDisplayed());
//    }
    
    public void verifyServiceType(String patient, String servicename) {
        WebElement element = loginPage.getDriver().findElement(By.xpath("//span[@class='dis-inline-blk ng-scope']"
        		+ "//span[contains(text(),'"+patient+"')]"
        		+ "/../../../../../..//td[contains(text(),'"+servicename+"')]"));
        loginPage.waitForElementToBeClickable(element);
        Assert.assertTrue(element.isDisplayed());
    }
    
    public void verifyServiceLocation(String patient, String address) {
        WebElement element = loginPage.getDriver().findElement(By.xpath("//span[@class='dis-inline-blk ng-scope']"
        		+ "//span[contains(text(),'"+patient+"')]"
        		+ "/../../../../../..//span[contains(text(),'"+address+"')]"));
        loginPage.waitForElementToBeClickable(element);
        Assert.assertTrue(element.isDisplayed());
    }
    
    public void checkDoctorName(String patient, String doctor) {
        WebElement element = loginPage.getDriver().findElement(By.xpath("//span[@class='dis-inline-blk ng-scope']"
        		+ "//span[contains(text(),'"+patient+"')]"
        		+ "/../../../../../..//td[contains(text(),'"+doctor+"')]"));
        loginPage.waitForElementToBeClickable(element);
        Assert.assertTrue(element.isDisplayed());
    }
    
//    public void checkDoctorName(String patient, String doctor) {
//        loginPage.waitForPageLoad();
//        WebElement element = loginPage.getDriver().findElement(By.xpath("//span[@class='dis-inline-blk ng-scope']"
//        		+ "//span[contains(text(),'"+patient+"')]"
//        		+ "/../../../../../..//span[contains(text(),'"+doctor+"')]"));
//        loginPage.waitForElementToBeClickable(web);
//        Assert.assertTrue(web.isDisplayed());
//    }

//    public void checkDoctorName(String patient, String doctor) {
//        loginPage.waitForPageLoad();
//        WebElement web = loginPage.getDriver().findElement(By.xpath("//span[contains(text(),'" + patient + "')]/../../../following-sibling::td[contains(text(),'" + doctor + "')]"));
//        loginPage.waitForElementToBeClickable(web);
//        Assert.assertTrue(web.isDisplayed());
//    }
    
    public void viewButton(String patient) {
        WebElement element = loginPage.getDriver().findElement(By.xpath("//span[@class='dis-inline-blk ng-scope']"
        		+ "//span[contains(text(),'"+patient+"')]"
        		+ "/../../../../../..//span[contains(@class,'actn-icn view')]"));
        loginPage.waitForElementToBeClickable(element);
        Assert.assertTrue(element.isDisplayed());
    }

//    public void viewButton(String patient) {
//        loginPage.waitForPageLoad();
//        WebElement element = loginPage.getDriver().findElement(By.xpath("//span[contains(text(),'" + patient
//                + "')]/../../..//following-sibling::td//span[contains(@class,'actn-icn view')]"));
//        loginPage.waitForElementToBeClickable(element);
//        Assert.assertTrue(element.isDisplayed());
//    }
    
    public void openViewModal(String patient) {
        WebElement element = loginPage.getDriver().findElement(By.xpath("//span[@class='dis-inline-blk ng-scope']"
        		+ "//span[contains(text(),'"+patient+"')]"
        		+ "/../../../../../..//span[contains(@class,'actn-icn view')]"));
        element.click();
    }
    

//    public void openViewModal(String patient) {
//        loginPage.waitForPageLoad();
//        WebElement element = loginPage.getDriver().findElement(By.xpath("//span[contains(text(),'" + patient + "')]/../../..//following-sibling::td//span[contains(@class,'actn-icn view')]"));
//        loginPage.waitForElementToBeClickable(element);
//        element.click();
////      loginPage.switchToFrame(viewFrame);
//    }
    
    public void editButton(String patient) {
        loginPage.waitForSpinnerToDisappear();
        WebElement element = loginPage.getDriver().findElement(By.xpath("//span[@class='dis-inline-blk ng-scope']"
        		+ "//span[contains(text(),'"+patient+"')]"
        		+ "/../../../../../..//span[contains(@class,'actn-icn edit')]"));
        loginPage.waitForElementVisibility(element, 4000);
        Assert.assertTrue(element.isDisplayed());
    }

//    public void editButton(String patient) {
//        loginPage.waitForPageLoad();
//        WebElement element = loginPage.getDriver().findElement(By.xpath("//span[contains(text(),'" + patient
//                + "')]/../../../following-sibling::td//span[contains(@class,'actn-icn edit')]"));
//        loginPage.waitForElementToBeClickable(element);
//        Assert.assertTrue(element.isDisplayed());
//    }
    
    public void clickEditButton(String patient) {
        loginPage.waitForSpinnerToDisappear();
        WebElement element = loginPage.getDriver().findElement(By.xpath("//span[@class='dis-inline-blk ng-scope']"
        		+ "//span[contains(text(),'"+patient+"')]"
        		+ "/../../../../../..//span[contains(@class,'actn-icn edit')]"));
        loginPage.waitForElementToBeClickable(element);
        element.click();
      
    }

//    public void clickEditButton(String patient) {
//        loginPage.waitForPageLoad();
//        try {
//            Thread.sleep(1500);
//            WebElement element = loginPage.getDriver().findElement(By.xpath("//span[contains(text(),'" + patient
//                    + "')]/../../../following-sibling::td//span[contains(@class,'actn-icn edit')]"));
//            loginPage.waitForElementToBeClickable(element);
//            element.click();
//            Thread.sleep(3500);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
    
    public void deleteButton(String patient) {
        loginPage.waitForPageLoad();
        WebElement element = loginPage.getDriver().findElement(By.xpath("//span[@class='dis-inline-blk ng-scope']"
        		+ "//span[contains(text(),'"+patient+"')]"
        		+ "/../../../../../..//span[contains(@class,'actn-icn delete')]"));
        loginPage.waitForElementToBeClickable(element);
        Assert.assertTrue(element.isDisplayed());
    }

//    public void deleteButton(String patient) {
//        loginPage.waitForPageLoad();
//        WebElement element = loginPage.getDriver().findElement(By.xpath("//span[contains(text(),'" + patient
//                + "')]/../../../following-sibling::td//span[contains(@class,'actn-icn delete')]"));
//        loginPage.waitForElementToBeClickable(element);
//        Assert.assertTrue(element.isDisplayed());
//    }
    
    
    public void clickDeleteButton(String patient) {
        loginPage.waitForSpinnerToDisappear();
        WebElement element = loginPage.getDriver().findElement(By.xpath("//span[@class='dis-inline-blk ng-scope']"
        		+ "//span[contains(text(),'"+patient+"')]"
        		+ "/../../../../../..//span[contains(@class,'actn-icn delete')]"));
        loginPage.waitForElementToBeClickable(element);
        element.click();
    }


//    public void clickDeleteButton(String patient) {
//        loginPage.waitForPageLoad();
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        WebElement element = loginPage.getDriver().findElement(By.xpath("//span[contains(text(),'" + patient
//                + "')]/../../../following-sibling::td//span[contains(@class,'actn-icn delete')]"));
//        loginPage.waitForElementToBeClickable(element);
//        element.click();
//    }

    public void verifyAfterEditAtAppList(String patient, String timeSlot) {
        loginPage.waitForPageLoad();
        WebElement element = loginPage.getDriver()
                .findElement(By.xpath("//span[contains(text(),'" + patient + "')]/../../../preceding-sibling::td"));
        loginPage.waitForElementToBeClickable(element);
        Assert.assertTrue(element.getText().contains(timeSlot));
    }
    
    public void verifyAppointmentDateTimeOnListing(String patientName,String date, String time){
    	loginPage.waitForSpinnerToDisappear();
    	WebElement getDate = loginPage.getDriver()
                .findElement(By.xpath("(//span[@class='dis-inline-blk ng-scope']"
                		+ "//span[contains(text(),'"+patientName+"')]"
                		+ "/../../../../../..//span[@class='legendOriginal ng-binding'])[1]"));
    	WebElement getTime = loginPage.getDriver()
                .findElement(By.xpath("//span[@class='dis-inline-blk ng-scope']"
                		+ "//span[contains(text(),'"+patientName+"')]"
                		+ "/../../../../../..//div[@class='text-center lineHght12 dis-inline-blk ng-binding']"));
    	String getDateText = getDate.getText();
    	String getTimeText = getTime.getText();
    	String timeText = (getTimeText.split("2023")[1]).trim();	
    	try {
			String	getTimein12hrFormat = loginPage.convert24HourFormatTo12HourFormat(time);
			boolean Flag = getTimein12hrFormat.equalsIgnoreCase(timeText);
			Assert.assertEquals(true, Flag);
			System.out.println(getDateText);
//			String getddFormat = loginPage.convertFormatingDate(date);
//			Assert.assertEquals(getDateText, getddFormat);
		} catch (bsh.ParseException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}  	
    }

    public void clickFeedbackBtn(String patientName) {
        loginPage.waitForSpinnerToDisappear();
        try {
            Thread.sleep(5000);
           WebElement feedbackButton= loginPage.getDriver().findElement(By.xpath("//td//span[contains(text(),'"+patientName+"')]//../../../../../following-sibling::td//div//span[@class='actn-icn feedback']"));
           feedbackButton.click();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void checkFeedbackBtnDisable(String toDate) {
        loginPage.waitForPageLoad();
        try {
            Thread.sleep(2000);
            Assert.assertTrue(loginPage.getDriver().findElements(By.xpath("//td[contains(text(),'" + toDate + "')]/following-sibling::td//span[@id='dashBoardFBList']")).size() == 0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private boolean checkIfNoShow(WebElement noShowButton, WebElement iconExpiredButton, WebElement checkInButton) {
        return (checkIfElementDisplayed(noShowButton)
                && (checkIfElementDisplayed(iconExpiredButton) || checkIfElementDisplayed(checkInButton)));
    }

    private boolean checkIfConfirmed(WebElement deleteButton, WebElement editButton) {
        return (checkIfElementDisplayed(deleteButton) && checkIfElementDisplayed(editButton));
    }

    private boolean checkIfElementDisplayed(WebElement webElement) {
        return (webElement != null && webElement.isDisplayed());
    }


}
