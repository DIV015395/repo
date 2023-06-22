package com.prm.scripts;

import com.prm.docdashboard.pageobject.*;
import com.prm.pageobjects.utils.ExtentReport;
import com.prm.pageobjects.utils.ReadConfig;
import com.prm.patientdashboard.pageobject.CampSubscriberPage;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CampSubscriberTestCase {
    LoginPage loginPage = new LoginPage();
    DoctorDashBoard doctorDashboard = new DoctorDashBoard(loginPage);
    BasePage basePage = new BasePage(loginPage);
    ExtentReport extentReport = new ExtentReport(loginPage, "AppointmentListingTestCase");
    CampSubscriberPage campSubscriberPage=new CampSubscriberPage(loginPage);

    private static final String NAVIGATE_SUBSCRIBER_LISTING = "@@ Test scripts has been started here @@";
    private static final String STATUS_OF_SCRIPTS = "@@ Test script has been completed @@";

    @BeforeClass
    public void setup() {
        extentReport.logger = extentReport.report.startTest(this.getClass().getSimpleName());
        extentReport.logger.log(LogStatus.INFO, LoginPage.testCaseRunningStatusmsg);
        extentReport.logger.log(LogStatus.PASS, LoginPage.browserMsg);
        String username = ReadConfig.getInstance().getUsername().toString();
        String password = ReadConfig.getInstance().getPassword().toString();
        loginPage.login(username, password);
        extentReport.logger.log(LogStatus.PASS, LoginPage.userNameMsg);
        extentReport.logger.log(LogStatus.PASS, LoginPage.passwordMsg);
        extentReport.logger.log(LogStatus.PASS, LoginPage.loginBtnClickMsg);
        doctorDashboard.doctorDashboardHomePage();
    }

    @BeforeMethod(enabled = true)
    public void navigateCampAddPage() {
        extentReport.logger.log(LogStatus.PASS, NAVIGATE_SUBSCRIBER_LISTING);
        doctorDashboard.clickCampSubscriberAdd();
        Assert.assertTrue(basePage.verification().contains("Camp Subscriber"));
    }

    @Test(priority = 1)
    public void campInfoPage(){
        campSubscriberPage.headerPage();
        campSubscriberPage.creationDate();
        campSubscriberPage.checkedNextBtn();
        campSubscriberPage.checkedCancelBtn();
        campSubscriberPage.headerCampInfo();
        campSubscriberPage.subscriberType();
        campSubscriberPage.regionDropDown();
        campSubscriberPage.zoneDropDown();
        campSubscriberPage.campaignDropDown();
        campSubscriberPage.sourceDropDown();
        campSubscriberPage.subSourceTextFields();
        Assert.assertTrue(basePage.verification().contains("Camp Subscriber"));
    }

    @Test(priority = 2)
    public void errorMessageCampInfo(){
        campSubscriberPage.selectCampaign("CSR");
        campSubscriberPage.selectSource("Facebook");
        campSubscriberPage.enterSubSource("Sub-source");
        campSubscriberPage.clickNextBtn();
        campSubscriberPage.selectRegionErr();
        campSubscriberPage.selectZoneErr();
        loginPage.getDriver().navigate().refresh();
        campSubscriberPage.selectRegion("Maharashtra");
        campSubscriberPage.selectSource("Facebook");
        campSubscriberPage.enterSubSource("Sub-source");
        campSubscriberPage.clickNextBtn();
        campSubscriberPage.selectCampaignErr();
        loginPage.getDriver().navigate().refresh();
        campSubscriberPage.selectRegion("Maharashtra");
        campSubscriberPage.selectCampaign("CSR");
        campSubscriberPage.enterSubSource("Sub-source");
        campSubscriberPage.clickNextBtn();
        campSubscriberPage.selectSubscriberSourceErr();
        loginPage.getDriver().navigate().refresh();
        campSubscriberPage.selectRegion("Maharashtra");
        campSubscriberPage.selectCampaign("CSR");
        campSubscriberPage.selectSource("Facebook");
        campSubscriberPage.clickNextBtn();
        campSubscriberPage.enterSubSourceErr();
        campSubscriberPage.clickCancelBtn();
        Assert.assertTrue(basePage.verification().contains("Doctor Dashboard"));
    }

    @Test(priority = 3)
    public void campSubsAddPage(){
        campSubscriberPage.selectRegion("Maharashtra");
        campSubscriberPage.selectCampaign("CSR");
        campSubscriberPage.selectSource("Facebook");
        campSubscriberPage.enterSubSource("Sub-source");
        campSubscriberPage.clickNextBtn();
        campSubscriberPage.headerPage();
        campSubscriberPage.creationDate();
        campSubscriberPage.checkedSaveBtn();
        campSubscriberPage.checkedResetBtn();
        campSubscriberPage.checkedCancelBtn();
        campSubscriberPage.headerCampInfo();
        campSubscriberPage.openCloseCampInfo();
        campSubscriberPage.subscriberType();
        campSubscriberPage.selectedRegion("Maharashtra");
        campSubscriberPage.selectedZone("Pune");
        campSubscriberPage.selectedCampaign("CSR");
        campSubscriberPage.selectedSource("Facebook");
        campSubscriberPage.selectedSubSource("Sub-source");
        campSubscriberPage.openCloseCampInfo();
        campSubscriberPage.headerNewSubscriber();
        campSubscriberPage.nameTextFields();
        campSubscriberPage.genderRadio();
        campSubscriberPage.dobTextFields();
        campSubscriberPage.ageTextFields();
        campSubscriberPage.emailTextFields();
        campSubscriberPage.mobileTextFields();
        campSubscriberPage.notifications();
        campSubscriberPage.enterEmail("dental@gmail.com");
        campSubscriberPage.notificationsSelected();
        campSubscriberPage.fileUploadBtn();
        campSubscriberPage.interestLevel();
        campSubscriberPage.checkedFindings();
        campSubscriberPage.findingBox("Braces");
        campSubscriberPage.findingBox("Fluoride application & Scaling");
        campSubscriberPage.findingBox("Filling/RCT");
        campSubscriberPage.findingBox("Implant");
        campSubscriberPage.findingBox("Prostho");
        loginPage.scrollPage(500);
        campSubscriberPage.headerMembership();
        campSubscriberPage.membershipDropDown();
        campSubscriberPage.enterTrtSearchBox("Dental Health Plan 2.0  (550.00)");
        campSubscriberPage.selectMembershipTrt();
        loginPage.scrollPage(500);
        campSubscriberPage.paymentMode();
        campSubscriberPage.cashMode();
        campSubscriberPage.chequeMode();
        campSubscriberPage.netBankingMode();
        campSubscriberPage.cardMode();
        campSubscriberPage.walletMode();
        Assert.assertTrue(basePage.verification().contains("Camp Subscriber"));
    }

    @Test(priority = 4)
    public void mandatoryFieldsSubsAdd(){
        campSubscriberPage.selectRegion("Maharashtra");
        campSubscriberPage.selectCampaign("CSR");
        campSubscriberPage.selectSource("Facebook");
        campSubscriberPage.enterSubSource("Sub-source");
        campSubscriberPage.clickNextBtn();
        campSubscriberPage.enterAge("16");
        campSubscriberPage.enterMobile("1111111111");
        campSubscriberPage.clickSaveBtn();
        campSubscriberPage.error("Enter Subscriber's Name");
        campSubscriberPage.clickResetBtn();
        campSubscriberPage.enterName("Camp Test");
        campSubscriberPage.enterMobile("1111111111");
        campSubscriberPage.clickSaveBtn();
        campSubscriberPage.error("Enter Valid Age");
        campSubscriberPage.clickResetBtn();
        campSubscriberPage.enterName("Camp Test");
        campSubscriberPage.enterMobile("1111111111");
        campSubscriberPage.enterAge("12");
        campSubscriberPage.enterEmail("abc");
        campSubscriberPage.clickSaveBtn();
        campSubscriberPage.error("Enter valid email address!");
        campSubscriberPage.clickResetBtn();
        campSubscriberPage.enterName("Camp Test");
        campSubscriberPage.enterAge("12");
        campSubscriberPage.clickSaveBtn();
        campSubscriberPage.error("Enter Mobile No.");
        campSubscriberPage.clickResetBtn();
        campSubscriberPage.enterName("Camp Test");
        campSubscriberPage.enterAge("12");
        campSubscriberPage.enterMobile("0");
        campSubscriberPage.clickSaveBtn();
        campSubscriberPage.error("Enter Valid Mobile No.");
        campSubscriberPage.clickResetBtn();
        campSubscriberPage.enterName("Camp Test");
        campSubscriberPage.enterAge("12");
        campSubscriberPage.enterMobile("1234");
        campSubscriberPage.clickSaveBtn();
        campSubscriberPage.error("Enter 10 Digit Mobile No.");
        Assert.assertTrue(basePage.verification().contains("Camp Subscriber"));
    }

    @Test(priority = 5)
    public void newSubscriber(){
        campSubscriberPage.selectRegion("Maharashtra");
        campSubscriberPage.selectCampaign("CSR");
        campSubscriberPage.selectSource("Facebook");
        campSubscriberPage.enterSubSource("Sub-source32");
        campSubscriberPage.clickNextBtn();
//        campSubscriberPage.enterName("test21345");
//        campSubscriberPage.enterAge("16");
//        campSubscriberPage.enterMobile("1111111111");
//        campSubscriberPage.clickSaveBtn();
        loginPage.scrollPage(700);
        campSubscriberPage.inputListColumn();
        campSubscriberPage.nameInputList("test21345");
        campSubscriberPage.mobileEmailInput("1111111111","NA");
        campSubscriberPage.findingsInput("NA");
        campSubscriberPage.membershipInput("NA");
        campSubscriberPage.paymentModeInput("NA");
        campSubscriberPage.amountInput("NA");
        campSubscriberPage.viewInput();
//        campSubscriberPage.editInput();
//        campSubscriberPage.deleteInput();
        campSubscriberPage.openView("test21345");
        campSubscriberPage.headerViewModal();
        campSubscriberPage.creationDateView();
        campSubscriberPage.nameSubView("test21345");
        campSubscriberPage.mobileSubView("1111111111");
        campSubscriberPage.emailSubView("NA");
        campSubscriberPage.genderSubView("Male");
        campSubscriberPage.ageSubView("16");
        campSubscriberPage.typeSubView("Outbound");
        campSubscriberPage.regionSubView("Maharashtra");
        campSubscriberPage.zoneSubView("Pune");
        campSubscriberPage.campaignSubView("CSR");
        campSubscriberPage.sourceSubView("Facebook");
        campSubscriberPage.subSourceSubView("Sub-source32");
        campSubscriberPage.findingsSubView("NA");
        campSubscriberPage.closeViewModal();
        Assert.assertTrue(basePage.verification().contains("Camp Subscriber"));
    }
    @AfterMethod(description = STATUS_OF_SCRIPTS, enabled = true)
    public void statusOfScripts(ITestResult result) {
        extentReport.onTestFailure(result);
        basePage.backTODoctorDashboard();
        extentReport.logger.log(LogStatus.PASS, STATUS_OF_SCRIPTS);
    }

    @AfterClass
    public void tearDown() {
        extentReport.report.endTest(extentReport.logger);
        extentReport.report.flush();
        // extentReport.report.close();
        basePage.clickOnlogOut();
    }
}
