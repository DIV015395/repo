package com.prm.scripts;

import com.prm.docdashboard.pageobject.BasePage;
import com.prm.docdashboard.pageobject.DoctorDashBoard;
import com.prm.docdashboard.pageobject.LoginPage;
import com.prm.docdashboard.pageobject.PatientDashboardPage;
import com.prm.pageobjects.utils.ExtentReport;
import com.prm.pageobjects.utils.ReadConfig;
import com.prm.pageobjects.utils.TestData;
import com.prm.patientdashboard.pageobject.BasePatientLifeCyclePage;
import com.prm.patientdashboard.pageobject.FileListingPage;
import com.prm.patientdashboard.pageobject.FilesPage;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

public class FileTestCase {
    private LoginPage loginPage = new LoginPage();
    private DoctorDashBoard doctorDashboard = new DoctorDashBoard(loginPage);
    private BasePage basePage = new BasePage(loginPage);
    private ExtentReport extentReport = new ExtentReport(loginPage, "FileTestCase");
    private PatientDashboardPage patientDashboardPage = new PatientDashboardPage(loginPage);
    private BasePatientLifeCyclePage basePatientLifeCyclePage = new BasePatientLifeCyclePage(loginPage);
    private FilesPage filesPage=new FilesPage(loginPage);
    private FileListingPage fileListingPage=new FileListingPage(loginPage);

    //storing Input Data need to validate the Scenario
    private static final String PATIENT_NAME= TestData.getInstance().getInputData("file_patient_name");
    private static final String MOBILE_NUMBER = TestData.getInstance().getInputData("file_patient_mobile");
    private static final String CLINIC_NAME = TestData.getInstance().getInputData("file_clinic_name");

    /*-------------------Message for extent report----------------------*/
    private static final String SCRIPTS_STARTED_MSG = "@@ Test scripts has been started here @@";
    private static final String STATUS_OF_SCRIPTS = "@@ Test script has been completed @@";
    private static final String FILE_ADD_PAGE = "Checked All webElement at file add page before File upload - #fileAddPage";
    private static final String FILE_LIST_PAGE = "Checked All webElement at file listing page before File upload - #fileListPage";
    private static final String INVALID_INPUT_ERROR = "Validated All mandatory fields with error Message - #invalidInputError";
    private static final String UPLOAD_FILE = "Uploaded single file and checked at both file add and file Listing - #uploadFile";
    private static final String ADDED_MULTIPLE_FILES = "Uploaded multiples files and checked at both file add and file Listing - #addedMultipleFiles";
    private static final String CHECK_TITLE_SCENARIO = "Validated All title related scenario - #checkTitleScenerio";
    private static final String EDIT_FILE = "Validated All edit related scenario - #editFile";
    private static final String DELETED_FILE_LISTING = "Validated All delete related scenerio - #deletedFileListing";
    private static final String RESET_CANCEL_FUNCTIONALITY ="Checked functionality of reset and cancel button - #resetCancelFunctionality";

    //Launching the browser and search for Patient
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
		basePage.selectClinicFrmHeader(CLINIC_NAME);
        basePage.enterMobileNo(MOBILE_NUMBER);
        basePage.clickOnSearchBtn();
        basePage.clickOnPatient(MOBILE_NUMBER, PATIENT_NAME);
        Assert.assertTrue(basePage.verification().contains("Patient Dashboard"));
    }

    //checked reached at patient Dashboard page
    @BeforeMethod
    public void verifyPatientdashBoardHomePage() {
        extentReport.logger.log(LogStatus.PASS, SCRIPTS_STARTED_MSG);
        if(patientDashboardPage.dueWarningPopup.size()>0){
			patientDashboardPage.hideDueWarningPopup();
		}else {
			basePatientLifeCyclePage.clickOnAlert();
		}
    }

    @Test(enabled = true,priority = 1)
    //checked All fields and text when we redirects to file add page for first time
    public void fileAddPage(){
        extentReport.logger.log(LogStatus.PASS, FILE_ADD_PAGE);
        patientDashboardPage.clickOnFilesAdd();
        filesPage.headerPage("Files");
        filesPage.verifyPatientName(PATIENT_NAME);
        basePatientLifeCyclePage.openCloseLeftNavigator();
        basePatientLifeCyclePage.webElementOfLeftNavigator();
        basePatientLifeCyclePage.closeLeftNavigator();
     //   basePatientLifeCyclePage.clickOnLeftNavigatorCloseBtnOldUi();
        filesPage.categoryDropDownWebelemet();
        filesPage.fileTitleWebElementCheckBoxNotSelected();
        filesPage.checkFileNameCheckbox();
        filesPage.notesWebElement();
        filesPage.fileUploadBtnDisplayed();
        filesPage.saveBtnDisplayed();
        filesPage.resetBtnDisplayed();
        filesPage.cancelBtnDisplayed();
        basePatientLifeCyclePage.dashBoardBtnVerify();
        Assert.assertTrue(basePage.verification().contains("Files"));
    }

    @Test(enabled = true,priority = 2)
    //checked all WebElement at file listing without any file Add
    public void fileListPage(){
        extentReport.logger.log(LogStatus.PASS, FILE_LIST_PAGE);
        patientDashboardPage.clickOnFilesList();
        basePatientLifeCyclePage.openCloseLeftNavigator();
        basePatientLifeCyclePage.webElementOfLeftNavigator();
        basePatientLifeCyclePage.closeLeftNavigator();
        fileListingPage.oralExamTabSelected();
        fileListingPage.fileDateFilter();
        fileListingPage.noRecordMessageDisplayed();
        fileListingPage.pastDocsTabNotSelected();
        fileListingPage.xRaysTabNotSelected();
        fileListingPage.profileTabNotSelected();
        fileListingPage.verifyPatientName(PATIENT_NAME);
        basePatientLifeCyclePage.verifyAddNewBtnOldUi();
        basePatientLifeCyclePage.dashBoardBtnVerify();
        fileListingPage.clickPastDocsTab();
        fileListingPage.pastDocsTabSelected();
        fileListingPage.oralExamTabNotSelected();
        fileListingPage.xRaysTabNotSelected();
        fileListingPage.profileTabNotSelected();
        fileListingPage.noRecordMessageDisplayed();
        fileListingPage.clickXRayTab();
        fileListingPage.xRaysTabSelected();
        fileListingPage.oralExamTabNotSelected();
        fileListingPage.profileTabNotSelected();
        fileListingPage.pastDocsTabNotSelected();
        fileListingPage.noRecordMessageDisplayed();
        fileListingPage.clickProfileTab();
        fileListingPage.profileTabSelected();
        fileListingPage.oralExamTabNotSelected();
        fileListingPage.xRaysTabNotSelected();
        fileListingPage.pastDocsTabNotSelected();
        fileListingPage.noRecordMessageDisplayed();
        Assert.assertTrue(basePage.verification().contains("File Listing"));
    }

    @Test(enabled = true,priority = 3)
    //validating error message without inputs
    public void invalidInputError(){
        extentReport.logger.log(LogStatus.PASS, INVALID_INPUT_ERROR);
        patientDashboardPage.clickOnFilesAdd();
        filesPage.clickSaveBtn();
        filesPage.checkedErrorCategory("Select category");
        filesPage.selectCategory("Oral Images");
        filesPage.checkFileNameCheckbox();
        filesPage.clickSaveBtn();
        filesPage.checkedErrorTitle("Enter file title");
        filesPage.enterTitleFile("fileTest");
        filesPage.clickSaveBtn();
        filesPage.errorMsgFileNotSelected();
        Assert.assertTrue(basePage.verification().contains("Files"));
    }

    @Test(enabled = true,priority = 4)
    //Added single files together and validated at File listing
    public void uploadFile(){
        extentReport.logger.log(LogStatus.PASS, UPLOAD_FILE);
        patientDashboardPage.clickOnFilesAdd();
        filesPage.headerPage("Files");
        filesPage.singleFileUploadBtn(System.getProperty("user.dir")+"\\PatientFiles\\File1.jpg");
        filesPage.checkDeleteBtnFileAddPage("File1.jpg");
        filesPage.checkVisibletoPatientFileAddPage("File1.jpg");
        filesPage.checkedImagefileAddPage("File1.jpg");
        filesPage.selectCategory("Oral Images");
        filesPage.clickSaveBtn();
        filesPage.successfullyMessage();
        fileListingPage.checkFileAddedDate("File1");
        fileListingPage.checkedFileEditBtn("File1");
        fileListingPage.checkedFileDownloadBtn("File1");
        fileListingPage.checkedFileDeleteBtn("File1");
        fileListingPage.checkedFileAddedDateInDropdown();
        fileListingPage.oralExamTabSelected();
        fileListingPage.pastDocsTabNotSelected();
        fileListingPage.xRaysTabNotSelected();
        fileListingPage.profileTabNotSelected();
        fileListingPage.clickPastDocsTab();
        fileListingPage.noRecordMessageDisplayed();
        fileListingPage.clickXRayTab();
        fileListingPage.noRecordMessageDisplayed();
        fileListingPage.clickProfileTab();
        fileListingPage.noRecordMessageDisplayed();
        Assert.assertTrue(basePage.verification().contains("File Listing"));
        loginPage.softAssert().assertAll();
    }

    @Test(enabled = true,priority = 5)
    //Added multiple files together and validated at File listing
    public void addedMultipleFiles() {
        extentReport.logger.log(LogStatus.PASS, ADDED_MULTIPLE_FILES);
        patientDashboardPage.clickOnFilesAdd();
        //Added File and deleted from input list at File Add Page
        filesPage.singleFileUploadBtn(System.getProperty("user.dir")+"\\PatientFiles\\File1.jpg");
        filesPage.checkDeleteBtnFileAddPage("File1.jpg");
        filesPage.checkedImagefileAddPage("File1.jpg");
        filesPage.clickDeleteBtnFileAddPage("File1.jpg");
        filesPage.checkedDeletedFileAddPage("File1.jpg");
        //uploading multiple Files together for a patient
        filesPage.uploadMultipleFiles(System.getProperty("user.dir")+"\\PatientFiles\\File2.jpg",System.getProperty("user.dir")+"\\PatientFiles\\File3.jpg");
        filesPage.checkDeleteBtnFileAddPage("File2.jpg");
        filesPage.checkDeleteBtnFileAddPage("File3.jpg");
        filesPage.checkedImagefileAddPage("File2.jpg");
        filesPage.clickVisibletoPatientBtnFileAddPage("File2.jpg");
        filesPage.checkedImagefileAddPage("File3.jpg");
        filesPage.selectCategory("Past Docs");
        filesPage.clickSaveBtn();
        filesPage.successfullyMessage();
        fileListingPage.checkFileAddedDate("File2");
        fileListingPage.checkedFileVisibleToPatientIcon("File2");
        fileListingPage.checkFileAddedDate("File3");
        fileListingPage.checkedFileEditBtn("File2");
        fileListingPage.checkedFileEditBtn("File3");
        fileListingPage.checkedFileDownloadBtn("File2");
        fileListingPage.checkedFileDownloadBtn("File3");
        fileListingPage.checkedFileDeleteBtn("File2");
        fileListingPage.checkedFileDeleteBtn("File3");
        fileListingPage.checkedFileAddedDateInDropdown();
        fileListingPage.pastDocsTabSelected();
        fileListingPage.oralExamTabNotSelected();
        fileListingPage.xRaysTabNotSelected();
        fileListingPage.profileTabNotSelected();
        Assert.assertTrue(basePage.verification().contains("File Listing"));
        loginPage.softAssert().assertAll();
}

    @Test(enabled = true,priority = 6)
    //uploaded multiple file together and passed a title then title should be same for every File at file listing page.
    public void checkTitleScenerio() {
        extentReport.logger.log(LogStatus.PASS, CHECK_TITLE_SCENARIO);
        patientDashboardPage.clickOnFilesAdd();
        filesPage.uploadMultipleFiles(System.getProperty("user.dir")+"\\PatientFiles\\File1.jpg",System.getProperty("user.dir")+"\\PatientFiles\\File2.jpg");
     //   filesPage.singleFileUploadBtn(System.getProperty("user.dir")+"\\PatientFiles\\File1.jpg");
      //  filesPage.singleFileUploadBtn(System.getProperty("user.dir")+"\\PatientFiles\\File2.jpg");
      //  filesPage.singleFileUploadBtn(System.getProperty("user.dir")+"\\PatientFiles\\File3.jpg");
        filesPage.selectCategory("X-Rays");
        filesPage.checkFileNameCheckbox();
        filesPage.enterTitleFile("Image");
        filesPage.enterNotes("it is for testing");
        filesPage.clickSaveBtn();
        filesPage.successfullyMessage();
        fileListingPage.multipleFilesRenameAddedDate("Image",2);
        fileListingPage.multipleFilesRenameEditBtn("Image",2);
        fileListingPage.multipleFilesRenameDownloadBtn("Image",2);
        fileListingPage.multipleFilesRenameDeleteBtn("Image",2);
        Assert.assertTrue(basePage.verification().contains("File Listing"));
        loginPage.softAssert().assertAll();
    }

    @Test(enabled = true,priority = 7)
    //Edited file from listing and validated All scenario related to edit.
    public void editFile(){
        extentReport.logger.log(LogStatus.PASS, EDIT_FILE);
        patientDashboardPage.clickOnFilesAdd();
        filesPage.singleFileUploadBtn(System.getProperty("user.dir")+"\\PatientFiles\\File1.jpg");
        filesPage.selectCategory("Profile");
        filesPage.checkFileNameCheckbox();
        filesPage.enterTitleFile("Image");
        filesPage.enterNotes("it is for testing");
        filesPage.clickSaveBtn();
        filesPage.successfullyMessage();
        fileListingPage.checkedFileAddedDateInDropdown();
        fileListingPage.checkFileAddedDate("Image");
        fileListingPage.checkedFileEditBtn("Image");
        fileListingPage.checkedFileDownloadBtn("Image");
        fileListingPage.checkedFileDeleteBtn("Image");
        fileListingPage.clickFileEditBtn("Image");
        filesPage.getFirstSelectOptions("Profile");
        filesPage.getFileName("Image");
        filesPage.fileTitleWebElementCheckBoxSelected();
        filesPage.editCheckDeleteBtnFileAddPage("File1.jpg");
        filesPage.editCheckImageFileAddPage("File1.jpg");
        filesPage.enterTitleFile("Image2");
        filesPage.selectCategory("Past Docs");
        filesPage.clickVisibletoPatientBtnForEditFileAddPage("File1.jpg");
        filesPage.clickSaveBtn();
        filesPage.successfullyMessage();
  //      fileListingPage.clickPastDocsTab();
        fileListingPage.checkedFileAddedDateInDropdown();
        fileListingPage.checkFileAddedDate("Image2");
        fileListingPage.checkedFileVisibleToPatientIcon("Image2");
        fileListingPage.checkedFileEditBtn("Image2");
        fileListingPage.checkedFileDownloadBtn("Image2");
        fileListingPage.checkedFileDeleteBtn("Image2");
        fileListingPage.pastDocsTabSelected();
        fileListingPage.oralExamTabNotSelected();
        fileListingPage.xRaysTabNotSelected();
        fileListingPage.profileTabNotSelected();
        Assert.assertTrue(basePage.verification().contains("File Listing"));
        loginPage.softAssert().assertAll();
    }

    @Test(enabled = true,priority = 8)
    //Deleting file from file listing  and edited file and deleted from file Add page
    public void deletedFileListing(){
        extentReport.logger.log(LogStatus.PASS, DELETED_FILE_LISTING);
        patientDashboardPage.clickOnFilesAdd();
        filesPage.singleFileUploadBtn(System.getProperty("user.dir")+"\\PatientFiles\\File4.jpg");
        filesPage.selectCategory("Profile"); 
        filesPage.checkFileNameCheckbox();
        filesPage.enterTitleFile("Image");
        filesPage.enterNotes("it is for testing");
        filesPage.clickSaveBtn();
        filesPage.successfullyMessage();
        fileListingPage.checkedFileAddedDateInDropdown();
        fileListingPage.openFile("Image");
        fileListingPage.checkHeaderFileListPopup("Image");
        fileListingPage.checkFileAddedDateFileListPopup();
        fileListingPage.notesFileListPopup("it is for testing");
        fileListingPage.actionsBtnFileListPopup();
        fileListingPage.closeFileListPopup();
        fileListingPage.clickFileEditBtn("Image");
        filesPage.uploadFileNotPresent();
        filesPage.editDeleteFileAddPage("File4.jpg");
        filesPage.fileDeleted("File4.jpg");
        filesPage.fileUploadBtnDisplayed();
        filesPage.singleFileUploadBtn(System.getProperty("user.dir")+"\\PatientFiles\\File5.jpg");
        filesPage.enterTitleFile("New File");
        filesPage.checkDeleteBtnFileAddPage("File5.jpg");
        filesPage.checkedImagefileAddPage("File5.jpg");
        filesPage.uploadFileNotPresent();
        filesPage.clickSaveBtn();
        filesPage.successfullyMessage();
        fileListingPage.clickProfileTab();
        fileListingPage.deletedFileListing("Image");
        fileListingPage.checkFileAddedDate("New File");
        fileListingPage.checkedFileEditBtn("New File");
        fileListingPage.checkedFileDownloadBtn("New File");
        fileListingPage.checkedFileDeleteBtn("New File");
        fileListingPage.checkedFileAddedDateInDropdown();
        fileListingPage.clickFileDeleteBtn("New File");
        fileListingPage.deletePopupWebElement();
        fileListingPage.clickNoButton();
        fileListingPage.clickFileDeleteBtn("New File");
        fileListingPage.clickYesButton();
        fileListingPage.deletedFileListing("New File");
        Assert.assertTrue(basePage.verification().contains("File Listing"));
        loginPage.softAssert().assertAll();
    }

    @Test(enabled = true,priority = 9)
    //Checked functionality of reset and cancel button
    public void resetCancelFunctionality(){
        extentReport.logger.log(LogStatus.PASS, RESET_CANCEL_FUNCTIONALITY);
        patientDashboardPage.clickOnFilesAdd();
        filesPage.singleFileUploadBtn(System.getProperty("user.dir")+"\\PatientFiles\\File1.jpg");
        filesPage.checkDeleteBtnFileAddPage("File1.jpg");
        filesPage.checkedImagefileAddPage("File1.jpg");
        filesPage.selectCategory("Oral Images");
        filesPage.checkFileNameCheckbox();
        filesPage.enterTitleFile("testing-Title");
        filesPage.enterNotes("notes testing");
        filesPage.clickResetBtn();
        filesPage.getFirstSelectOptions("Select Category");
        filesPage.fileTitleWebElementCheckBoxNotSelected();
        filesPage.getFileName("");
        filesPage.getFileNotes("");
        filesPage.fileUploadBtnDisplayed();
        filesPage.checkDeleteBtnFileAddPage("File1.jpg");
        filesPage.checkedImagefileAddPage("File1.jpg");
        filesPage.clickCancelBtn();
        basePage.verification().contains("Patient Dashboard");
        basePatientLifeCyclePage.clickOnAlert();
        patientDashboardPage.clickOnFilesAdd();
        basePage.verification().contains("Files");
    }


    @AfterMethod(description = STATUS_OF_SCRIPTS, enabled = true)
    //fetching result of every scripts and clicking dashboard
    public void statusOfScripts(ITestResult result) {
        extentReport.onTestFailure(result);
        extentReport.logger.log(LogStatus.PASS, STATUS_OF_SCRIPTS);
        basePatientLifeCyclePage.clickOnDashBoard();
    }

    //logout form page
    @AfterClass
    public void tearDown() {
        extentReport.report.endTest(extentReport.logger);
        extentReport.report.flush();
        // extentReport.report.close();
        basePage.clickOnlogOut();
    }
}
