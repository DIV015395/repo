package com.prm.docdashboard.pageobject;

import com.prm.pageobjects.utils.PCDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class SubscriberListingPage {
    private PCDriver loginPage;

    public SubscriberListingPage(PCDriver loginPage) {
        this.loginPage = loginPage;
        PageFactory.initElements(loginPage.getDriver(), this);
    }
    //storing web element of subscriber listing
    @FindBy(xpath = "//h1[text()='Subscriber Listing']")
    private WebElement headerSubscriberListing;
    @FindBy(xpath = "//span[@class='cmnicons ccAdd']/../following-sibling::i[text()='Add New']")
    private WebElement newSubscriberAddBtn;
    @FindBy(xpath = "//span[@class='cmnicons apptAdd']/../following-sibling::i[text()='Appt.+']")
    private WebElement appointmentAddBtn;
    @FindBy(xpath = "//span[@class='cmnicons dashBoardCMAdd']/../following-sibling::i[text()='Complaint+']")
    private WebElement complaintAddBtn;
    @FindBy(xpath = "//span[@class='cmnicons cpmlnt']/../following-sibling::i[text()='Complaints']")
    private WebElement complaintsListingBtn;
    @FindBy(xpath = "//span[@class='cmnicons drpCall']/../following-sibling::i[text()='Drop Call(']")
    private WebElement dropCallBtn;
    @FindBy(xpath = "//span[@class='cmnicons glyphicon fa fa-exchange icon']/../following-sibling::i[text()='Assign']")
    private WebElement assignBtn;
    @FindBy(xpath = "//span[@class='cmnicons glyphicon fa fa-sign-in icon']/../following-sibling::i[text()='Import']")
    private WebElement importBtn;
    @FindBy(xpath = "//span[@class='cmnicons csv']/../following-sibling::i[text()='CSV fmt.']")
    private WebElement csvFmtBtn;
    @FindBy(xpath = "//span[@class='cmnicons miscList']/../following-sibling::i[text()='Misc. Calls']")
    private WebElement miscCallsListBtn;
    @FindBy(xpath = "//span[@class='cmnicons miscAdd']/../following-sibling::i[text()='Misc. Call+']")
    private WebElement miscCallsAddBtn;
    @FindBy(xpath = "//span[@class='cmnicons dashB']/../following-sibling::i[text()='DashBoard']")
    private WebElement dashBoardBtn;
    @FindBy(id= "region")
    private WebElement region;
    @FindBy(id= "assignee")
    private WebElement assignee;
    @FindBy(id= "stage")
    private WebElement stage;
    @FindBy(id = "details")
    private WebElement details;
    @FindBy(id="fromDate")
    private WebElement fromDate;
    @FindBy(id="toDate")
    private WebElement toDate;
    @FindBy(id="stageRbtn")
    private WebElement stageRbtn;
    @FindBy(id="createdRbtn")
    private WebElement createdRbtn;
    @FindBy(id="updatedRbtn")
    private WebElement updatedRbtn;
    @FindBy(xpath = "//span[@class='cmnicons srch']/../following-sibling::i[text()='Search']")
    private WebElement searchBtn;
    @FindBy(className = "cmnicons advSerch selctdBtn")
    private WebElement advanceBtn;
    @FindBy(xpath = "//span[@class='cmnicons rst']/../following-sibling::i[text()='Reset']")
    private WebElement resetBtn;
    @FindBy(id = "type")
    private WebElement type;
    @FindBy(id = "source")
    private WebElement source;
    @FindBy(id = "subsource")
    private WebElement subsource;
    @FindBy(id = "age")
    private WebElement age;
    @FindBy(id = "lead type")
    private WebElement leadType;
    @FindBy(id = "campaign")
    private WebElement campaign;
    @FindBy(id = "finding")
    private WebElement finding;
    @FindBy(xpath = "//i[text()='No Record Found']")
    private WebElement noRecordFound;

    public void headerSubscriberListing(){
        loginPage.waitForPageLoad();
        Assert.assertTrue(checkedWebElementDisplayed(headerSubscriberListing));
    }
    public void newSubscriberAddBtn(){
        loginPage.waitForPageLoad();
        Assert.assertTrue(checkedWebElementDisplayed(newSubscriberAddBtn));
    }
    public void appointmentAddBtn(){
        loginPage.waitForPageLoad();
        Assert.assertTrue(checkedWebElementDisplayed(appointmentAddBtn));
    }
    public void complaintAddBtn(){
        loginPage.waitForPageLoad();
        Assert.assertTrue(checkedWebElementDisplayed(complaintAddBtn));
    }
    public void complaintsListingBtn(){
        loginPage.waitForPageLoad();
        Assert.assertTrue(checkedWebElementDisplayed(complaintsListingBtn));
    }
    public void dropCallBtn(){
        loginPage.waitForPageLoad();
        Assert.assertTrue(checkedWebElementDisplayed(dropCallBtn));
    }
    public void assignBtn(){
        loginPage.waitForPageLoad();
        Assert.assertTrue(checkedWebElementDisplayed(assignBtn));
    }
    public void importBtn(){
        loginPage.waitForPageLoad();
        Assert.assertTrue(checkedWebElementDisplayed(importBtn));
    }
    public void csvFmtBtn(){
        loginPage.waitForPageLoad();
        Assert.assertTrue(checkedWebElementDisplayed(csvFmtBtn));
    }
    public void miscCallsListBtn(){
        loginPage.waitForPageLoad();
        Assert.assertTrue(checkedWebElementDisplayed(miscCallsListBtn));
    }
    public void miscCallsAddBtn(){
        loginPage.waitForPageLoad();
        Assert.assertTrue(checkedWebElementDisplayed(miscCallsAddBtn));
    }
    public void dashBoardBtn(){
        loginPage.waitForPageLoad();
        Assert.assertTrue(checkedWebElementDisplayed(dashBoardBtn));
    }
    public void regionDropDown(String firstOption){
        loginPage.waitForPageLoad();
        loginPage.waitForElementToBeClickable(region);
        Assert.assertTrue(checkedWebElementDisplayed(region)&&loginPage.firstSelectedOption(region).contains(firstOption));
    }
    public void assigneeDropDown(String firstOption){
        loginPage.waitForPageLoad();
        loginPage.waitForElementToBeClickable(assignee);
        Assert.assertTrue(checkedWebElementDisplayed(assignee)&&loginPage.firstSelectedOption(assignee).contains(firstOption));
    }
    public void stageDropDown(String firstOption){
        loginPage.waitForPageLoad();
        loginPage.waitForElementToBeClickable(stage);
        Assert.assertTrue(checkedWebElementDisplayed(stage)&&loginPage.firstSelectedOption(stage).contains(firstOption));
    }
    public void details(){
        loginPage.waitForElementToBeClickable(details);
        Assert.assertTrue(checkedWebElementDisplayed(details)&&details.getAttribute("placeholder").contains("details"));
    }
    public void fromDate(){
        loginPage.waitForPageLoad();
        loginPage.waitForElementToBeClickable(fromDate);
        Assert.assertTrue(checkedWebElementDisplayed(fromDate)&&fromDate.getAttribute("placeholder").contains("from (dd-mm-yy)"));
    }
    public void toDate(){
        loginPage.waitForPageLoad();
        loginPage.waitForElementToBeClickable(toDate);
        Assert.assertTrue(checkedWebElementDisplayed(toDate)&&toDate.getAttribute("placeholder").contains("to (dd-mm-yy)"));
    }
    public void stage_Creation_Updation(){
        loginPage.waitForPageLoad();
        loginPage.waitForElementToBeClickable(stageRbtn);
        Assert.assertTrue(checkedWebElementDisplayed(stageRbtn)&&checkedWebElementDisplayed(createdRbtn)&&checkedWebElementDisplayed(updatedRbtn));
    }
    public void searchBtn(){
        loginPage.waitForPageLoad();
        loginPage.waitForElementToBeClickable(searchBtn);
        Assert.assertTrue(checkedWebElementDisplayed(searchBtn));
    }
    public void advanceSearchBtn(){
        loginPage.waitForPageLoad();
        try {
            Thread.sleep(5000);
            loginPage.waitForElementToBeClickable(advanceBtn);
            Assert.assertTrue(checkedWebElementDisplayed(advanceBtn));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void resetBtn(){
        loginPage.waitForPageLoad();
        loginPage.waitForElementToBeClickable(resetBtn);
        Assert.assertTrue(checkedWebElementDisplayed(resetBtn));
    }
    public void openAdvanceFilter(){
        loginPage.waitForPageLoad();
        loginPage.executeScript(advanceBtn);
    }
    public void typeDropDown(String firstOption){
        loginPage.waitForPageLoad();
        loginPage.waitForElementToBeClickable(type);
        Assert.assertTrue(checkedWebElementDisplayed(type)&&loginPage.firstSelectedOption(type).contains(firstOption));
    }
    public void sourceDropDown(String firstOption){
        loginPage.waitForPageLoad();
        loginPage.waitForElementToBeClickable(source);
        Assert.assertTrue(checkedWebElementDisplayed(source)&&loginPage.firstSelectedOption(source).contains(firstOption));
    }
    public void subSource(){
        loginPage.waitForPageLoad();
        loginPage.waitForElementToBeClickable(subsource);
        Assert.assertTrue(checkedWebElementDisplayed(subsource)&&subsource.getAttribute("placeholder").contains("sub-source"));
    }
    public void ageing(){
        loginPage.waitForPageLoad();
        loginPage.waitForElementToBeClickable(age);
        Assert.assertTrue(checkedWebElementDisplayed(age)&&age.getAttribute("placeholder").contains("age in day(s)"));
    }
    public void leadTypeDropDown(String firstOption){
        loginPage.waitForPageLoad();
        loginPage.waitForElementToBeClickable(leadType);
        Assert.assertTrue(checkedWebElementDisplayed(leadType)&&loginPage.firstSelectedOption(leadType).contains(firstOption));
    }
    public void campaignDropDown(String firstOption){
        loginPage.waitForPageLoad();
        loginPage.waitForElementToBeClickable(campaign);
        Assert.assertTrue(checkedWebElementDisplayed(campaign)&&loginPage.firstSelectedOption(campaign).contains(firstOption));
    }
    public void findingDropDown(String firstOption){
        loginPage.waitForPageLoad();
        loginPage.waitForElementToBeClickable(finding);
        Assert.assertTrue(checkedWebElementDisplayed(finding)&&loginPage.firstSelectedOption(finding).contains(firstOption));
    }
    public void noRecordFound(){
        loginPage.waitForElementToBeClickable(noRecordFound);
        Assert.assertTrue(checkedWebElementDisplayed(noRecordFound));
    }
    public void clickSubscriberAddBtn(){
        loginPage.waitForElementToBeClickable(newSubscriberAddBtn);
        newSubscriberAddBtn.click();
    }
    private boolean checkedWebElementDisplayed(WebElement webElement){
        return (webElement.isDisplayed());
    }
}
