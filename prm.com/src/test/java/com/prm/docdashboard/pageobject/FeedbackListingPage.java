package com.prm.docdashboard.pageobject;

import com.prm.pageobjects.utils.PCDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import java.util.List;

public class FeedbackListingPage {
    private PCDriver loginPage;

    public FeedbackListingPage(PCDriver loginPage) {
        this.loginPage = loginPage;
        PageFactory.initElements(loginPage.getDriver(), this);
    }
    @FindBy(xpath = "//h1[@class='btmTitle pull-left headTxt ng-binding']")
    private WebElement headerFeedBackListing;
    @FindBy(xpath = "//span[@class='cmnicons dashB']")
    private WebElement doctorDashBoardBtn;
    @FindBy(id="Type")
    private WebElement feedBackType;
    @FindBy(id="Search By")
    private WebElement searchBy;
    @FindBy(id="Doctor")
    private WebElement doctorDropDown;
    @FindBy(id="details")
    private WebElement details;
    @FindBy(id="fromDate")
    private WebElement fromDate;
    @FindBy(id="toDate")
    private WebElement toDate;
    @FindBy(xpath = "//label[text()='Created']")
    private WebElement created;
    @FindBy(xpath = "//label[text()='Created']")
    private WebElement submitted;
    @FindBy(xpath = "//span[@class='cmnicons srch']/../following-sibling::i[text()='Search']")
    private WebElement searchBtn;
    @FindBy(xpath = "//span[@class='cmnicons rst']/../following-sibling::i[text()='Reset']")
    private WebElement resetBtn;
    @FindBy(xpath = "//i[text()='No Record Found!']")
    private WebElement noRecordFound;
    @FindBy(xpath = "//span[text()='Sub./Cr. Date']")
    private WebElement sub_Created_date;
    @FindBy(xpath = "//span[text()='Type']")
    private WebElement type;
    @FindBy(xpath = "//span[text()='Patient Name / Id']")
    private WebElement patient_Name_Id;
    @FindBy(xpath = "//span[text()='Mobile']")
    private WebElement mobile;
    @FindBy(xpath = "//span[text()='Email']")
    private WebElement email;
    @FindBy(xpath = "//span[text()='Submitted By']")
    private WebElement submittedBy;
    @FindBy(xpath = "//span[text()='Attachment']")
    private WebElement attachment;
    @FindBy(xpath = "//span[text()='Action']")
    private WebElement action;
    /*feedback view modal web element*/
    @FindBy(xpath = "//h4[contains(@class,'compViewPopUpResponsive')]")
    private WebElement headerFeedbackViewModal;
    @FindBy(xpath = "//span[contains(@class,'compViewPopUpResponsive text-left')]")
    private WebElement patientName_PatientId;
    @FindBy(xpath = "//span[contains(@class,'cmnicons cncl-mdl')]")
    private WebElement closeBtnInView;
    @FindBy(xpath = "//h4[text()='Feedback Details']")
    private WebElement feedbackDetailsInView;
    @FindBy(xpath = "//b[text()='Facility']/../following-sibling::div")
    private WebElement clinicNameInView;
    @FindBy(xpath = "//b[text()='Email']/../following-sibling::div")
    private WebElement emailIdInView;
    @FindBy(xpath = "//b[text()='Phone']/../following-sibling::div")
    private WebElement mobileInView;
    @FindBy(xpath = "//b[text()='Address']/../following-sibling::div")
    private WebElement addressInView;
    @FindBy(xpath = "//b[text()='Submission Date']/../following-sibling::div")
    private WebElement submissionDateInView;
    @FindBy(xpath = "//b[text()='Serial No']/../following-sibling::div")
    private WebElement serialNoInView;
    @FindBy(xpath = "//h4[contains(text(),'Feedback Questions')]/span[contains(text(),'Feedback Type:')]/strong")
    private WebElement feedbackTypeInViewModal;
    @FindBy(xpath = "//b[text()='How likely are you to recommend Clove to your friends / family?']")
    private WebElement ratingHeaderInView;
    @FindBy(xpath = "//strong[contains(text(),'Rating:')]/..")
    private WebElement ratingInView;
    @FindBy(xpath = "//strong[contains(text(),'Reasons:')]/..")
    private WebElement reasonInView;
    @FindBy(xpath = "//strong[contains(text(),'Remarks:')]/..")
    private WebElement remarkInView;

    public void clickOnDashboard(){
        loginPage.waitForPageLoad();
        loginPage.waitForSpinnerToDisappear();
        doctorDashBoardBtn.click();
        loginPage.waitForSpinnerToDisappear();
        
    }
    public void headerFeedbackView(String header){
        loginPage.waitForPageLoad();
        Assert.assertEquals(headerFeedbackViewModal.getText().trim(),header);
    }

    public void patientNameInView(String patientName){
        loginPage.waitForPageLoad();
        String str = patientName_PatientId.getText();
        String[] split = str.split(":");
        Assert.assertTrue(split[0].trim().contains(patientName)&&(!split[1].contains("")||!split[1].contains("NA")));
    }

    public void checkFeedbackDetailsHeader(){
        loginPage.waitForPageLoad();
        Assert.assertTrue(checkedWebElementDisplayed(feedbackDetailsInView)&&checkedWebElementDisplayed(ratingHeaderInView));
    }

    public void clinicNameFeedbackView(String clinic){
        loginPage.waitForPageLoad();
        Assert.assertEquals(clinicNameInView.getText().trim(),clinic);
    }

    public void emailIdFeedbackView(String email){
        loginPage.waitForPageLoad();
        Assert.assertEquals(emailIdInView.getText().trim(),email);
    }

    public void addressFeedbackView(String address){
        loginPage.waitForPageLoad();
        Assert.assertEquals(addressInView.getText().trim(),address);
    }

    public void submittionDateFeedbackView(String submitDate){
        loginPage.waitForPageLoad();
        Assert.assertEquals(submissionDateInView.getText().trim(),submitDate);
    }

    public void serialNoFeedbackView(String serialNo){
        loginPage.waitForPageLoad();
        Assert.assertEquals(serialNoInView.getText().trim(),serialNo);
    }

    public void feedbackTypeInView(String feedBackType){
        loginPage.waitForPageLoad();
        Assert.assertEquals(feedbackTypeInViewModal.getText(),feedBackType);
    }

    public void mobileNoFeedbackView(String mobile){
        loginPage.waitForPageLoad();
        Assert.assertEquals(mobileInView.getText().trim(),mobile);
    }

    public void ratingFeedbackViewModal(String rating){
        loginPage.waitForPageLoad();
        Assert.assertTrue(ratingInView.getText().contains(rating));
    }

    public void reasonFeedbackViewModal(String reason){
        loginPage.waitForPageLoad();
        Assert.assertTrue(reasonInView.getText().contains(reason));
    }

    public void remarkFeedbackViewModal(String remark){
        loginPage.waitForPageLoad();
        Assert.assertTrue(remarkInView.getText().contains(remark));
    }

    public void closeFeedBackViewModal(){
        loginPage.waitForPageLoad();
        loginPage.waitForSpinnerToDisappear();
        closeBtnInView.click();
        loginPage.waitForSpinnerToDisappear();
        
    }

    public void feedbackListingColumnName() {
    	loginPage.waitForSpinnerToDisappear();
            Assert.assertTrue(checkedWebElementDisplayed(sub_Created_date) && checkedWebElementDisplayed(type) && checkedWebElementDisplayed(patient_Name_Id) && checkedWebElementDisplayed(mobile)
                    && checkedWebElementDisplayed(email) && checkedWebElementDisplayed(submittedBy) && checkedWebElementDisplayed(attachment) && checkedWebElementDisplayed(action));
     
    }

    public  void patientName_PatientId(String patientName){
        loginPage.waitForPageLoad();
        String str = loginPage.getDriver().findElement(By.xpath("//span[contains(text(),'"+patientName+"')]/..")).getText();
        String[] split = str.split("\\R");
        Assert.assertTrue(split[0].trim().contains(patientName)&&!split[1].contains("NA"));
    }

    public void feedbackType(String patientName,String feedbackType){
        loginPage.waitForPageLoad();
        List<WebElement> web = loginPage.getDriver().findElements(By.xpath("//span[contains(text(),'" + patientName + "')]/../../preceding-sibling::td"));
        for(int i=0;web.size()>i;i++){
            if(i==1){
                Assert.assertEquals(web.get(i).getText(),feedbackType);
                break;
            }else{
                continue;
            }
        }
    }

    public void mobileNumber(String patientName,String mobile){
        loginPage.waitForPageLoad();
        List<WebElement> web = loginPage.getDriver().findElements(By.xpath("//span[contains(text(),'" + patientName + "')]/../../following-sibling::td"));
        for(int i=0;web.size()>i;i++){
            if(i==0){
                Assert.assertEquals(web.get(i).getText(),mobile);
                break;
            }else{
                continue;
            }
        }
    }

    public void emailId(String patientName,String email){
        loginPage.waitForPageLoad();
        List<WebElement> web = loginPage.getDriver().findElements(By.xpath("//span[contains(text(),'" + patientName + "')]/../../following-sibling::td"));
        for(int i=0;web.size()>i;i++){
            if(i==1){
                Assert.assertEquals(web.get(i).getText(),email);
                break;
            }else{
                continue;
            }
        }
    }

    public void submittedBy(String patientName,String submittedBy){
        loginPage.waitForPageLoad();
        List<WebElement> web = loginPage.getDriver().findElements(By.xpath("//span[contains(text(),'" + patientName + "')]/../../following-sibling::td"));
        for(int i=2;web.size()>i;i++){
            if(i==1){
                Assert.assertEquals(web.get(i).getText(),submittedBy);
                break;
            }else{
                continue;
            }
        }
    }

    public void fileAttached(String patientName,String filename){
        loginPage.waitForPageLoad();
        WebElement web = loginPage.getDriver().findElement(By.xpath(" //span[contains(text(),'" + patientName + "')]/../../following-sibling::td/a[contains(@href,'" + filename + "')]"));
        Assert.assertTrue(checkedWebElementDisplayed(web));
    }

    public void feedbackViewBtn(String patientName){
        loginPage.waitForPageLoad();
        WebElement web=loginPage.getDriver().findElement(By.xpath(" //span[contains(text(),'" + patientName + "')]/../../following-sibling::td//span[@class='actn-icn view']"));
        Assert.assertTrue(checkedWebElementDisplayed(web));
    }

    public void clickFeedbackViewBtn(String patientName){
        loginPage.waitForPageLoad();
        try {
            WebElement web=loginPage.getDriver().findElement(By.xpath(" //span[contains(text(),'" + patientName + "')]/../../following-sibling::td//span[@class='actn-icn view']"));
            web.click();
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void feedbackEditBtn(String patientName){
        loginPage.waitForPageLoad();
        WebElement web=loginPage.getDriver().findElement(By.xpath(" //span[contains(text(),'" + patientName + "')]/../../following-sibling::td//span[@class='actn-icn edit']"));
        Assert.assertTrue(checkedWebElementDisplayed(web));
    }

    public void clickFeedbackEditBtn(String patientName){
        loginPage.waitForPageLoad();
        try {
            WebElement web=loginPage.getDriver().findElement(By.xpath(" //span[contains(text(),'" + patientName + "')]/../../following-sibling::td//span[@class='actn-icn edit']"));
            web.click();
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void feedbackDate(String patientName,String feedbackDate){
        loginPage.waitForPageLoad();
        List<WebElement> web = loginPage.getDriver().findElements(By.xpath("//span[contains(text(),'" + patientName + "')]/../../preceding-sibling::td"));
        for(int i=0;web.size()>i;i++){
            if(i==0){
                Assert.assertTrue(web.get(i).getText().contains(feedbackDate));
                break;
            }else{
                continue;
            }
        }
    }

    public void feedbackListingHeader(String header){
        loginPage.waitForPageLoad();
        Assert.assertEquals(headerFeedBackListing.getText().trim(),header);
    }

    public void feedbackTypeDropDown(String defaultFeedbackType) {
        loginPage.waitForPageLoad();
        boolean flag1, flag2, match = false;
        flag1 = checkedWebElementDisplayed(feedBackType) && loginPage.firstSelectedOption(feedBackType).contains(defaultFeedbackType);
        String[] exp = {"Select Type", "First Visit", "Patient Feedback","Post Treatment"};
        Select select = new Select(feedBackType);
        List<WebElement> options = select.getOptions();
        for (WebElement we : options) {
            for (int i = 0; i < exp.length; i++) {
                if (flag2 = we.getText().equals(exp[i])) {
                    match = flag1 && flag2;
                }
            }
            Assert.assertTrue(match);
        }
    }

    public void feedbackSearchType(String defaultFeedbackType) {
        loginPage.waitForPageLoad();
        boolean flag1, flag2, match = false;
        flag1 = checkedWebElementDisplayed(searchBy) && loginPage.firstSelectedOption(searchBy).contains(defaultFeedbackType);
        String[] exp = {"Select Search By", "Doctors", "Patient"};
        Select select = new Select(searchBy);
        List<WebElement> options = select.getOptions();
        for (WebElement we : options) {
            for (int i = 0; i < exp.length; i++) {
                if (flag2 = we.getText().equals(exp[i])) {
                    match = flag1 && flag2;
                }
            }
            Assert.assertTrue(match);
        }
    }

    public void doctorDropDown(String firtstOption){
        loginPage.waitForPageLoad();
        Assert.assertTrue(checkedWebElementDisplayed(doctorDropDown));
    }

    public void detailsFields(){
        loginPage.waitForPageLoad();
        Assert.assertTrue(checkedWebElementDisplayed(details)&&details.getAttribute("placeholder").contains("» name/email/mobile"));
    }

    public void dateFilter(){
        loginPage.waitForPageLoad();
        Assert.assertTrue(checkedWebElementDisplayed(fromDate)&&fromDate.getAttribute("placeholder").contains("» from (dd-mm-yy)")&&checkedWebElementDisplayed(toDate)&&toDate.getAttribute("placeholder").contains("» to (dd-mm-yy)"));
    }

    public void createdRadioBtn(){
        loginPage.waitForPageLoad();
        Assert.assertTrue(checkedWebElementDisplayed(created)&&!created.isSelected());
    }
    public void submittedRadioBtn(){
        loginPage.waitForPageLoad();
        Assert.assertTrue(checkedWebElementDisplayed(submitted)&&!submitted.isSelected());
    }
    public void searchBtn(){
        Assert.assertTrue(checkedWebElementDisplayed(searchBtn));
    }
    public void resetBtn(){
        Assert.assertTrue(checkedWebElementDisplayed(resetBtn));
    }
    public void noRecordFoundDisplayed(){
        Assert.assertTrue(checkedWebElementDisplayed(noRecordFound));
    }
    private boolean checkedWebElementDisplayed(WebElement webElement){
        return (webElement.isDisplayed());
    }
}
