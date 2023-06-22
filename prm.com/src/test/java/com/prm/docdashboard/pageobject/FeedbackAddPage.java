package com.prm.docdashboard.pageobject;

import com.prm.pageobjects.utils.PCDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import java.awt.*;

public class FeedbackAddPage {
    private PCDriver loginPage;

    public FeedbackAddPage(PCDriver loginPage) {
        this.loginPage = loginPage;
        PageFactory.initElements(loginPage.getDriver(), this);
    }

    /**
     * WebElement of the Add Feedback Page
     */
    @FindBy(xpath = "//h1[contains(@class,'pull-left ng-binding')]")
    private WebElement addPageHeader;
    @FindBy(xpath = "//span[@class='mdl-hdr-text ng-binding']")
    private WebElement patientName;
    @FindBy(xpath = "//div[contains(@class,'sideNavigation')]//span[@class='cmnicons aprsv']")
    private WebElement saveBtn;
    @FindBy(xpath = "//div[contains(@class,'sideNavigation')]//span[@class='cmnicons rst']")
    private WebElement resetBtn;
    @FindBy(xpath = "//div[contains(@class,'sideNavigation')]//span[@class='cmnicons cncl']")
    private WebElement cancelBtn;
    @FindBy(xpath = "//div[contains(@class,'sideNavigation')]//span[@class='cmnicons dashB']")
    private WebElement dashBoardBtn;
    @FindBy(xpath = "//div[contains(text(),'Info')]")
    private WebElement sectionTitleTop;
    @FindBy(xpath = "//div[contains(text(),'Feedback Questions')]")
    private WebElement sectionTitle;
    @FindBy(xpath = "//label[text()='Feedback Date']/following-sibling::input[@id='feedbackDt']")
    private WebElement feedBackDate;
    @FindBy(xpath = "//label[text()='Serial No.']/following-sibling::input[@id='serial']")
    private WebElement serialNumber;
    @FindBy(xpath = "//span[@class='cmnicons ccAdd']/../following-sibling::span[text()='Add File(s)']")
    private WebElement addFilesBtn;
    @FindBy(xpath = "//span[@class='pull-right']")
    private WebElement feedbackType;
    @FindBy(xpath = "//label[text()='How likely are you to recommend Clove to your friends / family?']")
    private WebElement feedbackRate;
    @FindBy(xpath = "//li[@class='mrgnSet rating_smiley ng-scope detractors']/label[text()='1']")
    private WebElement rate_1;
    @FindBy(xpath = "//li[@class='mrgnSet rating_smiley ng-scope detractors']/label[text()='2']")
    private WebElement rate_2;
    @FindBy(xpath = "//li[@class='mrgnSet rating_smiley ng-scope detractors']/label[text()='3']")
    private WebElement rate_3;
    @FindBy(xpath = "//li[@class='mrgnSet rating_smiley ng-scope detractors']/label[text()='4']")
    private WebElement rate_4;
    @FindBy(xpath = "//li[@class='mrgnSet rating_smiley ng-scope detractors']/label[text()='5']")
    private WebElement rate_5;
    @FindBy(xpath = "//li[@class='mrgnSet rating_smiley ng-scope passives']/label[text()='6']")
    private WebElement rate_6;
    @FindBy(xpath = "//li[@class='mrgnSet rating_smiley ng-scope passives']/label[text()='7']")
    private WebElement rate_7;
    @FindBy(xpath = "//li[@class='mrgnSet rating_smiley ng-scope passives']/label[text()='8']")
    private WebElement rate_8;
    @FindBy(xpath = "//li[@class='mrgnSet rating_smiley ng-scope promotors']/label[text()='9']")
    private WebElement rate_9;
    @FindBy(xpath = "//li[@class='mrgnSet rating_smiley ng-scope promotors']/label[text()='10']")
    private WebElement rate_10;
    @FindBy(xpath = "//div[text()='Not Likely']/../label[text()='1']")
    private WebElement notLikelyText;
    @FindBy(xpath = "//div[text()='Very Likely']/../label[text()='10']")
    private WebElement veryLikelyText;
    @FindBy(xpath = "//div[text()='Enter Serial No.!']")
    private WebElement mandatorySerialNumber;
    /*web element for rating one*/
    @FindBy(xpath = "//span[text()='Please answer any question to submit!']")
    private WebElement toastMessage;
    @FindBy(xpath = "//div[@id='reasons_for_rating_1']/preceding-sibling::span[text()='What can be improved on?']")
    private WebElement improvementText_1;
    @FindBy(xpath = "//div[@id='reasons_for_rating_1']//label[text()='Dentist Interaction']")
    private WebElement dentistInteraction_1;
    @FindBy(xpath = "//div[@id='reasons_for_rating_1']//label[text()='Treatment Quality']")
    private WebElement treatmentQuality_1;
    @FindBy(xpath = "//div[@id='reasons_for_rating_1']//label[text()='Prices and Offers']")
    private WebElement pricesAndOffers_1;
    @FindBy(xpath = "//div[@id='reasons_for_rating_1']//label[text()='Waiting Time']")
    private WebElement waitingTime_1;
    @FindBy(xpath = "//div[@id='reasons_for_rating_1']//label[text()='Hygiene & Cleanliness']")
    private WebElement hygieneCleanliness_1;
    @FindBy(xpath = "//div[@id='reasons_for_rating_1']/following-sibling::span/textarea[@placeholder='» Would you like to give more feedback']")
    private WebElement feedbacktextField_1;
    /*web element for rating two*/
    @FindBy(xpath = "//div[@id='reasons_for_rating_2']/preceding-sibling::span[text()='What can be improved on?']")
    private WebElement improvementText_2;
    @FindBy(xpath = "//div[@id='reasons_for_rating_2']//label[text()='Dentist Interaction']")
    private WebElement dentistInteraction_2;
    @FindBy(xpath = "//div[@id='reasons_for_rating_2']//label[text()='Treatment Quality']")
    private WebElement treatmentQuality_2;
    @FindBy(xpath = "//div[@id='reasons_for_rating_2']//label[text()='Prices and Offers']")
    private WebElement pricesAndOffers_2;
    @FindBy(xpath = "//div[@id='reasons_for_rating_2']//label[text()='Waiting Time']")
    private WebElement waitingTime_2;
    @FindBy(xpath = "//div[@id='reasons_for_rating_2']//label[text()='Hygiene & Cleanliness']")
    private WebElement hygieneCleanliness_2;
    @FindBy(xpath = "//div[@id='reasons_for_rating_2']/following-sibling::span/textarea[@placeholder='» Would you like to give more feedback']")
    private WebElement feedbacktextField_2;
    /*web element for rating three*/
    @FindBy(xpath = "//div[@id='reasons_for_rating_3']/preceding-sibling::span[text()='What can be improved on?']")
    private WebElement improvementText_3;
    @FindBy(xpath = "//div[@id='reasons_for_rating_3']//label[text()='Dentist Interaction']")
    private WebElement dentistInteraction_3;
    @FindBy(xpath = "//div[@id='reasons_for_rating_3']//label[text()='Treatment Quality']")
    private WebElement treatmentQuality_3;
    @FindBy(xpath = "//div[@id='reasons_for_rating_3']//label[text()='Prices and Offers']")
    private WebElement pricesAndOffers_3;
    @FindBy(xpath = "//div[@id='reasons_for_rating_3']//label[text()='Waiting Time']")
    private WebElement waitingTime_3;
    @FindBy(xpath = "//div[@id='reasons_for_rating_3']//label[text()='Hygiene & Cleanliness']")
    private WebElement hygieneCleanliness_3;
    @FindBy(xpath = "//div[@id='reasons_for_rating_3']/following-sibling::span/textarea[@placeholder='» Would you like to give more feedback']")
    private WebElement feedbacktextField_3;
    /*web element for rating four*/
    @FindBy(xpath = "//div[@id='reasons_for_rating_4']/preceding-sibling::span[text()='What can be improved on?']")
    private WebElement improvementText_4;
    @FindBy(xpath = "//div[@id='reasons_for_rating_4']//label[text()='Dentist Interaction']")
    private WebElement dentistInteraction_4;
    @FindBy(xpath = "//div[@id='reasons_for_rating_4']//label[text()='Treatment Quality']")
    private WebElement treatmentQuality_4;
    @FindBy(xpath = "//div[@id='reasons_for_rating_4']//label[text()='Prices and Offers']")
    private WebElement pricesAndOffers_4;
    @FindBy(xpath = "//div[@id='reasons_for_rating_4']//label[text()='Waiting Time']")
    private WebElement waitingTime_4;
    @FindBy(xpath = "//div[@id='reasons_for_rating_4']//label[text()='Hygiene & Cleanliness']")
    private WebElement hygieneCleanliness_4;
    @FindBy(xpath = "//div[@id='reasons_for_rating_4']/following-sibling::span/textarea[@placeholder='» Would you like to give more feedback']")
    private WebElement feedbacktextField_4;
    /*web element for rating five*/
    @FindBy(xpath = "//div[@id='reasons_for_rating_5']/preceding-sibling::span[text()='What can be improved on?']")
    private WebElement improvementText_5;
    @FindBy(xpath = "//div[@id='reasons_for_rating_5']//label[text()='Dentist Interaction']")
    private WebElement dentistInteraction_5;
    @FindBy(xpath = "//div[@id='reasons_for_rating_5']//label[text()='Treatment Quality']")
    private WebElement treatmentQuality_5;
    @FindBy(xpath = "//div[@id='reasons_for_rating_5']//label[text()='Prices and Offers']")
    private WebElement pricesAndOffers_5;
    @FindBy(xpath = "//div[@id='reasons_for_rating_5']//label[text()='Waiting Time']")
    private WebElement waitingTime_5;
    @FindBy(xpath = "//div[@id='reasons_for_rating_5']//label[text()='Hygiene & Cleanliness']")
    private WebElement hygieneCleanliness_5;
    @FindBy(xpath = "//div[@id='reasons_for_rating_5']/following-sibling::span/textarea[@placeholder='» Would you like to give more feedback']")
    private WebElement feedbacktextField_5;
    /*web element for rating 6*/
    @FindBy(xpath = "//div[@id='reasons_for_rating_6']/preceding-sibling::span[text()='What can be improved on?']")
    private WebElement improvementText_6;
    @FindBy(xpath = "//div[@id='reasons_for_rating_6']//label[text()='Dentist Interaction']")
    private WebElement dentistInteraction_6;
    @FindBy(xpath = "//div[@id='reasons_for_rating_6']//label[text()='Treatment Quality']")
    private WebElement treatmentQuality_6;
    @FindBy(xpath = "//div[@id='reasons_for_rating_6']//label[text()='Prices and Offers']")
    private WebElement pricesAndOffers_6;
    @FindBy(xpath = "//div[@id='reasons_for_rating_6']//label[text()='Waiting Time']")
    private WebElement waitingTime_6;
    @FindBy(xpath = "//div[@id='reasons_for_rating_6']//label[text()='Hygiene & Cleanliness']")
    private WebElement hygieneCleanliness_6;
    @FindBy(xpath = "//div[@id='reasons_for_rating_6']/following-sibling::span/textarea[@placeholder='» Would you like to give more feedback']")
    private WebElement feedbacktextField_6;
    /*web element for rating 7*/
    @FindBy(xpath = "//div[@id='reasons_for_rating_7']/preceding-sibling::span[text()='What could be done better?']")
    private WebElement improvementText_7;
    @FindBy(xpath = "//div[@id='reasons_for_rating_7']//label[text()='Dentist Interaction']")
    private WebElement dentistInteraction_7;
    @FindBy(xpath = "//div[@id='reasons_for_rating_7']//label[text()='Treatment Quality']")
    private WebElement treatmentQuality_7;
    @FindBy(xpath = "//div[@id='reasons_for_rating_7']//label[text()='Prices and Offers']")
    private WebElement rightCharges_7;
    @FindBy(xpath = "//div[@id='reasons_for_rating_7']//label[text()='Preventive Measures Explained']")
    private WebElement preventiveMeasuresExplained_7;
    @FindBy(xpath = "//div[@id='reasons_for_rating_7']//label[text()='Hygiene & Cleanliness']")
    private WebElement hygieneCleanliness_7;
    @FindBy(xpath = "//div[@id='reasons_for_rating_7']/following-sibling::span/textarea[@placeholder='» Would you like to give more feedback']")
    private WebElement feedbacktextField_7;
    /*web element for rating 8*/
    @FindBy(xpath = "//div[@id='reasons_for_rating_8']/preceding-sibling::span[text()='What could be done better?']")
    private WebElement improvementText_8;
    @FindBy(xpath = "//div[@id='reasons_for_rating_8']//label[text()='Dentist Interaction']")
    private WebElement dentistInteraction_8;
    @FindBy(xpath = "//div[@id='reasons_for_rating_8']//label[text()='Treatment Quality']")
    private WebElement treatmentQuality_8;
    @FindBy(xpath = "//div[@id='reasons_for_rating_8']//label[text()='Prices and Offers']")
    private WebElement rightCharges_8;
    @FindBy(xpath = "//div[@id='reasons_for_rating_8']//label[text()='Preventive Measures Explained']")
    private WebElement preventiveMeasuresExplained_8;
    @FindBy(xpath = "//div[@id='reasons_for_rating_8']//label[text()='Hygiene & Cleanliness']")
    private WebElement hygieneCleanliness_8;
    @FindBy(xpath = "//div[@id='reasons_for_rating_8']/following-sibling::span/textarea[@placeholder='» Would you like to give more feedback']")
    private WebElement feedbacktextField_8;
    /*Web element for rating 9*/
    @FindBy(xpath = "//div[@id='reasons_for_rating_9']/preceding-sibling::span[text()='Pleased to have served you. Would you like to give any compliments?']")
    private WebElement improvementText_9;
    @FindBy(xpath = "//div[@id='reasons_for_rating_9']/following-sibling::span/textarea[@placeholder='» Write your compliments here']")
    private WebElement feedbacktextField_9;
    /*web element for rating 10*/
    @FindBy(xpath = "//div[@id='reasons_for_rating_10']/preceding-sibling::span[text()='Pleased to have served you. Would you like to give any compliments?']")
    private WebElement improvementText_10;
    @FindBy(xpath = "//div[@id='reasons_for_rating_10']/following-sibling::span/textarea[@placeholder='» Write your compliments here']")
    private WebElement feedbacktextField_10;
    @FindBy(xpath = "//span[text()='Please upload the supporting document!']")
    private WebElement uploadFileToastMsg;
    @FindBy(xpath = "//i[@class='fa fa-times']")
    private WebElement fileRemoveBtn;
    @FindBy(xpath = "//p[text()='Do you want to delete this file?']/../following-sibling::div//span[@class='cmnicons yes-mdl']")
    private WebElement yesRemoveBtn;
    @FindBy(xpath = "//p[text()='Do you want to delete this file?']/../following-sibling::div//span[@class='cmnicons cncl-mdl']")
    private WebElement noRemoveBtn;

    public void selectDentistInteractionRate_1(){
        loginPage.waitForElementToBeClickable(dentistInteraction_1);
        loginPage.getDriver().findElement(By.xpath("//div[@id='reasons_for_rating_1']//label[text()='Dentist Interaction']/..")).click();
    }

    public void removeAttachedFile(){
        loginPage.waitForElementToBeClickable(fileRemoveBtn);
        try {
            Thread.sleep(1500);
            fileRemoveBtn.click();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void closeFileRemove(){
        loginPage.waitForElementToBeClickable(noRemoveBtn);
        noRemoveBtn.click();
    }

    public void yesFileRemove(){
        loginPage.waitForElementToBeClickable(yesRemoveBtn);
        try {
            yesRemoveBtn.click();
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /*checking the rating of the feedback*/
    public void rateCount(){
        loginPage.waitForPageLoad();
        Assert.assertTrue(checkedWebElementDisplayed(rate_1)&&checkedWebElementDisplayed(rate_2)&&checkedWebElementDisplayed(rate_3)&&checkedWebElementDisplayed(rate_4)&&checkedWebElementDisplayed(rate_5)
        &&checkedWebElementDisplayed(rate_6)&&checkedWebElementDisplayed(rate_7)&&checkedWebElementDisplayed(rate_8)&&checkedWebElementDisplayed(rate_9)&&checkedWebElementDisplayed(rate_10)&&checkedWebElementDisplayed(notLikelyText)&&checkedWebElementDisplayed(veryLikelyText));
    }

    /*Checking the rate message*/
    public void checkMessageRating(){
        loginPage.waitForPageLoad();
        Assert.assertTrue(checkedWebElementDisplayed(feedbackRate));
    }

    /*checking the header of the page*/
    public void checkFeedBackHeader(String expected){
        loginPage.waitForSpinnerToDisappear();
        Assert.assertEquals(addPageHeader.getText(), expected);
    }

    /*checking the patient name and patient id is not null*/
    public void verifyPatientName(String patient) {
        loginPage.waitForPageLoad();
        loginPage.waitForElementToBeClickable(patientName);
        String str = patientName.getText();
        String[] split = str.split(" : ");
        boolean flag = split[0].contains(patient) && !(split[1].equals("NA") || split[1].equals("Null"));
        Assert.assertTrue(flag);
    }

    /*checking save button at add feedback button*/
    public void checkSaveBtn(){
        loginPage.waitForPageLoad();
        Assert.assertTrue(checkedWebElementDisplayed(saveBtn));
    }

    public void savefeedBackAdd(){
        loginPage.waitForPageLoad();
        try {
            Thread.sleep(4000);
            saveBtn.click();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void checkResetBtn(){
        loginPage.waitForPageLoad();
        Assert.assertTrue(checkedWebElementDisplayed(resetBtn));
    }

    public void resetfeedBackAdd(){
        loginPage.waitForPageLoad();
        try {
            resetBtn.click();
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void checkCancelBtn(){
        loginPage.waitForPageLoad();
        Assert.assertTrue(checkedWebElementDisplayed(cancelBtn));
    }

    public void cancelfeedBackAdd(){
        loginPage.waitForPageLoad();
        cancelBtn.click();
    }

    public void checkDashboardBtn(){
        loginPage.waitForPageLoad();
        Assert.assertTrue(checkedWebElementDisplayed(dashBoardBtn));
    }

    public void clickDashBoard(){
        loginPage.waitForPageLoad();
        dashBoardBtn.click();
    }

    /*checking the top header text*/
    public void topSectionFeedbackHeader(){
        loginPage.waitForPageLoad();
        Assert.assertTrue(sectionTitleTop.isDisplayed());
    }

    /*checking the bottom header text*/
    public void bottomSectionFeedbackHeader(){
        loginPage.waitForPageLoad();
        Assert.assertTrue(sectionTitle.isDisplayed());
    }

    /*checking the bottom header text*/
    public void feedbackType(String feedback_Type){
        loginPage.waitForPageLoad();
        Assert.assertTrue(feedbackType.getText().trim().contains(feedback_Type));
    }

    /*checking feedback date web element displayed at feedback Add page*/
    public void feedBackDateDisplayed(){
        loginPage.waitForPageLoad();
        Assert.assertTrue(checkedWebElementDisplayed(feedBackDate));
    }

    /*checking feedback serial number web element displayed at feedback Add page*/
    public void feedBackSerialNumberDisplayed(){
        loginPage.waitForPageLoad();
        Assert.assertTrue(checkedWebElementDisplayed(serialNumber)&&serialNumber.getAttribute("placeholder").contains("serial no."));
    }

    /*checking feedback serial number web element displayed at feedback Add page*/
    public void feedBackAddFilesDisplayed(){
        loginPage.waitForPageLoad();
        Assert.assertTrue(checkedWebElementDisplayed(addFilesBtn));
    }

    public void feedbackDate(String expected){
        try {
            Thread.sleep(8000);
            JavascriptExecutor javascriptExecutor = (JavascriptExecutor) loginPage.getDriver();
            Object obj = javascriptExecutor.executeScript("return document.getElementById('feedbackDt').value;");
            String str = (String) obj;
            Assert.assertTrue(str.contains(expected));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void serialMandatoryFields(){
        loginPage.waitForPageLoad();
        Assert.assertTrue(checkedWebElementDisplayed(mandatorySerialNumber));
    }

    public void enterSerialNumber(String serialNo){
        loginPage.waitForPageLoad();
        serialNumber.clear();
        serialNumber.sendKeys(serialNo);
    }

    public void toastMsgQuestions(){
        loginPage.waitForPageLoad();
        try {
            Thread.sleep(1000);
            Assert.assertTrue(checkedWebElementDisplayed(toastMessage));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void selectRating(int rateFeedback){
        loginPage.waitForPageLoad();
        WebElement[] array = {rate_1, rate_2, rate_3, rate_4, rate_5, rate_6, rate_7, rate_8, rate_9, rate_10};
        for(int i=1;array.length>=i;i++){
            if (i==rateFeedback){
                array[i-1].click();
                break;
            }else{
                continue;
            }
        }
    }

    public void options_rating_1(){
        loginPage.waitForPageLoad();
        Assert.assertTrue(checkedWebElementDisplayed(improvementText_1)&&checkedWebElementDisplayed(dentistInteraction_1)&&checkedWebElementDisplayed(treatmentQuality_1)&&checkedWebElementDisplayed(pricesAndOffers_1)
        &&checkedWebElementDisplayed(waitingTime_1)&&checkedWebElementDisplayed(hygieneCleanliness_1)&&checkedWebElementDisplayed(feedbacktextField_1));
    }

    public void options_rating_2(){
        loginPage.waitForPageLoad();
        Assert.assertTrue(checkedWebElementDisplayed(improvementText_2)&&checkedWebElementDisplayed(dentistInteraction_2)&&checkedWebElementDisplayed(treatmentQuality_2)&&checkedWebElementDisplayed(pricesAndOffers_2)
                &&checkedWebElementDisplayed(waitingTime_2)&&checkedWebElementDisplayed(hygieneCleanliness_2)&&checkedWebElementDisplayed(feedbacktextField_2));
    }
    public void options_rating_3(){
        loginPage.waitForPageLoad();
        Assert.assertTrue(checkedWebElementDisplayed(improvementText_3)&&checkedWebElementDisplayed(dentistInteraction_3)&&checkedWebElementDisplayed(treatmentQuality_3)&&checkedWebElementDisplayed(pricesAndOffers_3)
                &&checkedWebElementDisplayed(waitingTime_3)&&checkedWebElementDisplayed(hygieneCleanliness_3)&&checkedWebElementDisplayed(feedbacktextField_3));
    }
    public void options_rating_4(){
        loginPage.waitForPageLoad();
        Assert.assertTrue(checkedWebElementDisplayed(improvementText_4)&&checkedWebElementDisplayed(dentistInteraction_4)&&checkedWebElementDisplayed(treatmentQuality_4)&&checkedWebElementDisplayed(pricesAndOffers_4)
                &&checkedWebElementDisplayed(waitingTime_4)&&checkedWebElementDisplayed(hygieneCleanliness_4)&&checkedWebElementDisplayed(feedbacktextField_4));
    }
    public void options_rating_5(){
        loginPage.waitForPageLoad();
        Assert.assertTrue(checkedWebElementDisplayed(improvementText_5)&&checkedWebElementDisplayed(dentistInteraction_5)&&checkedWebElementDisplayed(treatmentQuality_5)&&checkedWebElementDisplayed(pricesAndOffers_5)
                &&checkedWebElementDisplayed(waitingTime_5)&&checkedWebElementDisplayed(hygieneCleanliness_5)&&checkedWebElementDisplayed(feedbacktextField_5));
    }
    public void options_rating_6(){
        loginPage.waitForPageLoad();
        Assert.assertTrue(checkedWebElementDisplayed(improvementText_6)&&checkedWebElementDisplayed(dentistInteraction_6)&&checkedWebElementDisplayed(treatmentQuality_6)&&checkedWebElementDisplayed(pricesAndOffers_6)
                &&checkedWebElementDisplayed(waitingTime_6)&&checkedWebElementDisplayed(hygieneCleanliness_6)&&checkedWebElementDisplayed(feedbacktextField_6));
    }

    public void options_rating_7(){
        loginPage.waitForPageLoad();
        Assert.assertTrue(checkedWebElementDisplayed(improvementText_7)&&checkedWebElementDisplayed(dentistInteraction_7)&&checkedWebElementDisplayed(treatmentQuality_7)&&checkedWebElementDisplayed(rightCharges_7)
                &&checkedWebElementDisplayed(preventiveMeasuresExplained_7)&&checkedWebElementDisplayed(hygieneCleanliness_7)&&checkedWebElementDisplayed(feedbacktextField_7));
    }

    public void options_rating_8(){
        loginPage.waitForPageLoad();
        Assert.assertTrue(checkedWebElementDisplayed(improvementText_8)&&checkedWebElementDisplayed(dentistInteraction_8)&&checkedWebElementDisplayed(treatmentQuality_8)&&checkedWebElementDisplayed(rightCharges_8)
                &&checkedWebElementDisplayed(preventiveMeasuresExplained_8)&&checkedWebElementDisplayed(hygieneCleanliness_8)&&checkedWebElementDisplayed(feedbacktextField_8));
    }
    public void options_rating_9(){
        loginPage.waitForPageLoad();
        Assert.assertTrue(checkedWebElementDisplayed(improvementText_9)&&checkedWebElementDisplayed(feedbacktextField_9));
    }
    public void options_rating_10(){
        loginPage.waitForPageLoad();
        Assert.assertTrue(checkedWebElementDisplayed(improvementText_10)&&checkedWebElementDisplayed(feedbacktextField_10));
    }
    public void fileUploadToastMsg(){
        try {
            Thread.sleep(1000);
            loginPage.softAssert().assertTrue(checkedWebElementDisplayed(uploadFileToastMsg));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void deSelectRating(){
        loginPage.waitForPageLoad();
        WebElement[] array = {rate_1, rate_2, rate_3, rate_4, rate_5, rate_6, rate_7, rate_8, rate_9, rate_10};
        for(int i=0;array.length>i;i++){
                Assert.assertTrue(!array[i].getAttribute("class").contains("active"));
        }
    }

    public void ratingSelected(int rate){
        loginPage.waitForPageLoad();
        WebElement[] array = {rate_1, rate_2, rate_3, rate_4, rate_5, rate_6, rate_7, rate_8, rate_9, rate_10};
        for(int i=1;array.length>i;i++){
            if(i==rate){
                Assert.assertTrue(array[i-1].getAttribute("class").contains("active"));
                break;
            }
           continue;
        }
    }

    public void feedbackSerialNumber(String expected){
        try {
            Thread.sleep(8000);
            JavascriptExecutor javascriptExecutor = (JavascriptExecutor) loginPage.getDriver();
            Object obj = javascriptExecutor.executeScript("return document.getElementById('serial').value;");
            String str = (String) obj;
            Assert.assertEquals(str,expected);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void uploadFeedbackFiles(String path){
        loginPage.waitForPageLoad();
        try {
            addFilesBtn.click();
            Thread.sleep(4000);
            loginPage.uploadFile(path);
            Thread.sleep(4000);
        } catch (AWTException e) {
            e.printStackTrace();
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void addFeedbackNotes_rate_1(String notes){
        loginPage.waitForElementToBeClickable(feedbacktextField_1);
        feedbacktextField_1.sendKeys(notes);
    }

    public void addFeedbackNotes_rate_9(String notes){
        loginPage.waitForElementToBeClickable(feedbacktextField_9);
        feedbacktextField_9.sendKeys(notes);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void editedFileFeedbackListing(String fileName){
        loginPage.waitForPageLoad();
        Assert.assertTrue(checkedWebElementDisplayed(loginPage.getDriver().findElement(By.xpath("//a[contains(@href,'"+fileName+"')]"))));
    }

    /*checking WebElement present*/
    private boolean checkedWebElementDisplayed(WebElement webElement){
        return (webElement.isDisplayed());
    }
}
