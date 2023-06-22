package com.prm.scripts;

import com.prm.docdashboard.pageobject.*;
import com.prm.pageobjects.utils.ExtentReport;
import com.prm.pageobjects.utils.ReadConfig;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.annotations.Test;

public class SubscriberListingTestCase {
    LoginPage loginPage = new LoginPage();
    DoctorDashBoard doctorDashboard = new DoctorDashBoard(loginPage);
    BasePage basePage = new BasePage(loginPage);
    ExtentReport extentReport = new ExtentReport(loginPage, "AppointmentListingTestCase");
    SubscriberListingPage subscriberListingPage=new SubscriberListingPage(loginPage);
    NewSubscriberPage newSubscriberPage=new NewSubscriberPage(loginPage);


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
    public void navigateSubscriberListing() {
        extentReport.logger.log(LogStatus.PASS, NAVIGATE_SUBSCRIBER_LISTING);
        doctorDashboard.clickSubscriber();
        Assert.assertTrue(basePage.verification().contains("Subscriber Listing"));
    }

    @Test(priority = 1)
    public void defaultWebElement(){
        subscriberListingPage.headerSubscriberListing();
        subscriberListingPage.newSubscriberAddBtn();
        subscriberListingPage.appointmentAddBtn();
        subscriberListingPage.complaintAddBtn();
        subscriberListingPage.complaintsListingBtn();
        subscriberListingPage.dropCallBtn();
        subscriberListingPage.assignBtn();
        subscriberListingPage.importBtn();
        subscriberListingPage.csvFmtBtn();
        subscriberListingPage.miscCallsAddBtn();
        subscriberListingPage.miscCallsListBtn();
        subscriberListingPage.dashBoardBtn();
        subscriberListingPage.regionDropDown("Select Region");
        subscriberListingPage.assigneeDropDown("Select Assignee");
        subscriberListingPage.stageDropDown("Select Stage");
        subscriberListingPage.details();
        subscriberListingPage.fromDate();
        subscriberListingPage.toDate();
        subscriberListingPage.stage_Creation_Updation();
        subscriberListingPage.searchBtn();
        subscriberListingPage.advanceSearchBtn();
        subscriberListingPage.resetBtn();
        subscriberListingPage.openAdvanceFilter();
        subscriberListingPage.typeDropDown("Select Type");
        subscriberListingPage.sourceDropDown("Select Source");
        subscriberListingPage.subSource();
        subscriberListingPage.ageing();
        subscriberListingPage.leadTypeDropDown("Select Lead Type");
        subscriberListingPage.campaignDropDown("Select Campaign");
        subscriberListingPage.findingDropDown("Select Finding");
        subscriberListingPage.noRecordFound();
        Assert.assertTrue(basePage.verification().contains("Subscriber Listing"));
    }

    @Test(priority = 2)
    public void subscriberAddPage(){
        subscriberListingPage.clickSubscriberAddBtn();
        newSubscriberPage.nameTextField();
        newSubscriberPage.genderRadioBtn();
        newSubscriberPage.dobTextFields();
        newSubscriberPage.emailIdTextFields();
        newSubscriberPage.mobileTextFields();
        newSubscriberPage.alternateContactNo();
        newSubscriberPage.typeDropDown("Select Type");
        newSubscriberPage.stageDropDown("New");
        newSubscriberPage.campaignDropDown("Select Campaign");
        newSubscriberPage.sourceDropDown("Select Source");
        newSubscriberPage.subSourceTextFields();
        newSubscriberPage.findingsDropDown();
        newSubscriberPage.otherFindingTextFields();
        newSubscriberPage.regionDropDown("Select Region");
        newSubscriberPage.zoneDropDown("Select Zone");
        newSubscriberPage.addFilesBtn();
        newSubscriberPage.saveBtn();
        newSubscriberPage.resetBtn();
        newSubscriberPage.cancelBtn();
        Assert.assertTrue(basePage.verification().contains("New Subscriber"));
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
