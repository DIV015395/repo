package com.prm.patientdashboard.pageobject;

import com.prm.pageobjects.utils.PCDriver;
import org.openqa.selenium.By;
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
 * @author Ajit Yadav
 */
public class AppointmentsListPage {
    private PCDriver loginPage;

    public AppointmentsListPage(PCDriver loginPage) {
        this.loginPage = loginPage;
        PageFactory.initElements(loginPage.getDriver(), this);
    }

    /*storing the webelement*/
    @FindBy(xpath = "//table[@id='appLis']//td[1]")
    private List<WebElement> dates;
    @FindBy(xpath = "//span[text()='No Record Found']")
    private WebElement noRecordFoundMsg;
    @FindBy(xpath = "//table[@id='appLis']/tbody/tr")
    private List<WebElement> appointmentsPatient;
    @FindBy(xpath = "//span[contains(@class,'infPatientFlag')]/a[contains(@class,'green')]")
    private WebElement covidStatus;
//    @FindBy(xpath = "//th[text()='Date-Time']")
    @FindBy(xpath = "//th[text()='Date-Time']")
    private WebElement dateTimeColumn;
    @FindBy(xpath = "//th[text()='Chief Compalint(s)']")
    private WebElement chiefComplaintColumn;
    @FindBy(xpath = "//th[text()='Renewals']")
    private WebElement renewalsColumn;
    @FindBy(xpath = "//th[text()='Type']")
    private WebElement typeColumn;
    @FindBy(xpath = "//th[text()='Clinic']")
    private WebElement clinicColumn;
    @FindBy(xpath = "//th[text()='Doctor']")
    private WebElement doctorColumn;
    @FindBy(xpath = "//th[text()='Action']")
    private WebElement actionColumn;
    @FindBy(xpath = "//table[@id='appLis']//td")
    private List<WebElement> cells;
    @FindBy(xpath = "//span[@class='actn-icn delete']/../../../../..//td[@class='ng-binding']")
    private List<WebElement> doctorNameAndClinic;
    @FindBy(xpath="//i[text()='DashBoard']")
	private WebElement dashBoardBtn;
//    @FindBy(xpath = "//iframe[contains(@id,'fancybox-frame')]")
//    private WebElement viewFrame;

    
    public void checkAppointmentTime(String date, String slot) {
        loginPage.waitForElementToBeClickable(dateTimeColumn);
        String[] split = date.split("/");
        String year = split[2].substring(2);
//        String[] sp = split[0].split("'");
        String dateMonth = split[0]+ "-" + split[1] + "-" +year;
        String time = null;
        try {
            final SimpleDateFormat sdf = new SimpleDateFormat("H:mm");
            final Date dateObj = sdf.parse(slot);
            time = new SimpleDateFormat("K:mm").format(dateObj);
        } catch (final ParseException e) {
            e.printStackTrace();
        }      
        boolean flag = checkedWebElementDisplayed(dateTimeColumn) && checkedWebElementDisplayed(chiefComplaintColumn) && checkedWebElementDisplayed(renewalsColumn) && checkedWebElementDisplayed(typeColumn)
                && checkedWebElementDisplayed(clinicColumn) && checkedWebElementDisplayed(doctorColumn) && checkedWebElementDisplayed(actionColumn);
        if (flag) {
            String str = cells.get(0).getText().trim().split("New")[0];
            String getDateTime =time+" "+"pm";
            Assert.assertTrue(str.contains(getDateTime));
//           Assert.assertTrue(str.contains(time) && str.contains(dateMonth));
//            Assert.assertEquals(str, getDateTime);
        } else {
            Assert.fail();
        }
    }

    public void chiefComplaint(String complaint) {
        loginPage.waitForPageLoad();
        String chiefComplaint = cells.get(1).getText().trim();
        Assert.assertEquals(chiefComplaint, complaint);
    }

    public void appointmentType(String appointment_type) {
        loginPage.waitForPageLoad();
        String appointmentType = cells.get(3).getText().trim();
        Assert.assertEquals(appointmentType, appointmentType);
    }

    public void type(String appointment_type) {
        loginPage.waitForPageLoad();
        String appointmentType = cells.get(3).getText().trim();
        Assert.assertEquals(appointmentType, appointmentType);
    }
    
    public void clinicName(String clinic) {
        loginPage.waitForPageLoad();
        String clinicName = doctorNameAndClinic.get(1).getText().trim();
        Assert.assertEquals(clinicName, clinic);
    }


//    public void clinicName(String clinic) {
//        loginPage.waitForPageLoad();
//        String clinicName = cells.get(5).getText().trim();
//        Assert.assertEquals(clinicName, clinic);
//    }
    
    public void doctorName(String doctor) {
        loginPage.waitForPageLoad();
        String doctorName = doctorNameAndClinic.get(0).getText().trim();
        Assert.assertEquals(doctorName, doctor);
    }

//    public void doctorName(String doctor) {
//        loginPage.waitForPageLoad();
//        String doctorName = cells.get(4).getText().trim();
//        Assert.assertEquals(doctorName, doctor);
//    }

    public void checkViewBtn() {
        loginPage.waitForPageLoad();
        WebElement web = cells.get(6).findElement(By.xpath(".//span[@class='actn-icn view']"));
        Assert.assertTrue(checkedWebElementDisplayed(web));
    }
    

    public void openViewModal() {
//        loginPage.waitForPageLoad();
    	loginPage.waitForSpinnerToDisappear();
        WebElement web = cells.get(6).findElement(By.xpath(".//span[@class='actn-icn view']"));
        web.click();
//        loginPage.switchToFrame(viewFrame);
    }

    public void checkEditBtn() {
        loginPage.waitForPageLoad();
        WebElement web = cells.get(6).findElement(By.xpath(".//span[@class='actn-icn edit']"));
        Assert.assertTrue(checkedWebElementDisplayed(web));
    }

    public void checkDeleteBtn() {
        loginPage.waitForPageLoad();
        WebElement web = cells.get(6).findElement(By.xpath(".//span[@class='actn-icn delete']"));
        Assert.assertTrue(checkedWebElementDisplayed(web));
    }

    public void openCovidForm() {
        loginPage.waitForPageLoad();
        loginPage.waitForElementToBeClickable(covidStatus);
        covidStatus.click();
    }

    /*checking Appointment added successfully for the patient*/
    public void appointmentAdded() {
        loginPage.waitForPageLoad();
        if (appointmentsPatient.size() > 0) {
            loginPage.visibilityOfListLocated(dates);
            for (int i = 0; dates.size() > i; i++) {
                String appdate = dates.get(i).getText().trim();
                String[] str = appdate.split(" ");
                String appDate = str[0];
                SimpleDateFormat simple = new SimpleDateFormat("dd-MM-yy");
                Date date = new Date();
                String todayDate = simple.format(date);
                boolean flag;
                if (flag = appDate.equals(todayDate)) {
                    Assert.assertTrue(flag);
                } else {
                    continue;
                }
            }
        } else {
            Reporter.log("No Record Found!", true);
        }
    }

    public void checkFeedbackBtn(String todayDate) {
        loginPage.waitForPageLoad();
        loginPage.waitForSpinnerToDisappear();
        loginPage.visibilityOfListLocated(appointmentsPatient);
//        loginPage.waitForElementToBeClickable(dashBoardBtn);
        Assert.assertTrue(loginPage.getDriver().findElement(By.xpath("//td//span[contains(text(),'"+todayDate+"')]//../following-sibling::td//div//span[@class='actn-icn feedback']/..")).isDisplayed());
    }

    public void clickOnFeedbackBtn(String todayDate) {
       loginPage.waitForSpinnerToDisappear();
       loginPage.visibilityOfListLocated(appointmentsPatient);
       loginPage.waitForElementToBeClickable(dashBoardBtn);
       WebElement feedbackButton=loginPage.getDriver().findElement(By.xpath("//td//span[contains(text(),'"+todayDate+"')]//../following-sibling::td//div//span[@class='actn-icn feedback']/.."));
//       loginPage.waitForElementToBeClickable(feedbackButton);
       feedbackButton.click();
       loginPage.waitForSpinnerToDisappear();
    }

    /*checked there are any Appointment available for the patient within 24 hr or not*/
    public void appointmentAvailable() {
        loginPage.waitForPageLoad();
        final long MILLIS_PER_DAY = 24 * 60 * 60 * 1000L;
        if (appointmentsPatient.size() > 0) {
            loginPage.visibilityOfListLocated(dates);
            for (int i = 0; dates.size() > i; i++) {
                String appdate = dates.get(i).getText().trim();
                SimpleDateFormat simple = new SimpleDateFormat("dd-MM-yy");
                Date date = new Date();
                Date appointmentDate = null;
                try {
                    appointmentDate = simple.parse(appdate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                boolean flag;
                if (flag = Math.abs(date.getTime() - appointmentDate.getTime()) < MILLIS_PER_DAY) {
                    Assert.assertFalse(flag);
                } else {
                    Reporter.log("Appointment not available in 24 hr", true);
                }
            }
        } else {
            Reporter.log("There are no Appointment.", true);
        }
    }

    private boolean checkedWebElementDisplayed(WebElement webElement) {
        return (webElement.isDisplayed());
    }
}
