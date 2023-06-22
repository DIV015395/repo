package com.prm.docdashboard.pageobject;

import com.prm.pageobjects.utils.PCDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class NewSubscriberPage {
    private PCDriver loginPage;

    public NewSubscriberPage(PCDriver loginPage) {
        this.loginPage = loginPage;
        PageFactory.initElements(loginPage.getDriver(), this);
    }

    @FindBy(xpath = "//h1[text()='New Subscriber']")
    private WebElement newSubscriberHeader;
    @FindBy(xpath = "//span[contains(text(),'Creation Date')]")
    private WebElement creationDate;
    @FindBy(xpath = "//div[contains(@class,'sideNavigatio')]//span[@class='cmnicons aprsv']")
    private WebElement saveBtn;
    @FindBy(xpath = "//div[contains(@class,'sideNavigatio')]//span[@class='cmnicons rst']")
    private WebElement resetBtn;
    @FindBy(xpath = "//div[contains(@class,'sideNavigatio')]//span[@class='cmnicons cncl']")
    private WebElement cancelBtn;
    @FindBy(xpath = "//label[contains(text(),'Name')]/following-sibling::div/input[@id='name']")
    private WebElement name;
    @FindBy(xpath = "//label[contains(text(),'Gender')]/following-sibling::div//input[@id='male']")
    private WebElement male;
    @FindBy(xpath = "//label[contains(text(),'Gender')]/following-sibling::div//input[@id='female']")
    private WebElement female;
    @FindBy(xpath = "//label[contains(text(),'Gender')]/following-sibling::div//input[@id='other']")
    private WebElement other;
    @FindBy(xpath = "//label[contains(text(),'Date of Birth')]/following-sibling::input[@id='dob']")
    private WebElement dob;
    @FindBy(xpath = "//label[contains(text(),'Email ID')]/following-sibling::div//input[@id='email']")
    private  WebElement email;
    @FindBy(xpath = "//label[contains(text(),'Mobile')]/following-sibling::div//input[@id='mobile']")
    private WebElement mobile;
    @FindBy(xpath = "//label[contains(text(),'Alternate Contact No.')]/following-sibling::input[@id='alternatePhone']")
    private WebElement alternatePhone;
    @FindBy(xpath = "//label[contains(text(),'Type')]/following-sibling::custom-combo//select[@id='type']")
    private WebElement type;
    @FindBy(xpath = "//label[contains(text(),'Stage')]/following-sibling::custom-combo//select[@id='stage']")
    private WebElement stage;
    @FindBy(xpath = "//label[contains(text(),'Campaign')]/following-sibling::custom-combo//select[@id='campaign']")
    private WebElement campaign;
    @FindBy(xpath = "//label[contains(text(),'Source')]/following-sibling::custom-combo//select[@id='source']")
    private WebElement source;
    @FindBy(xpath = "//label[contains(text(),'Sub-Source')]/following-sibling::input[@id='subSource']")
    private WebElement subSource;
    @FindBy(xpath = "//label[contains(text(),'Findings')]/following-sibling::div/div[@class='multiselect-parent btn-group dropdown-multiselect']")
    private WebElement findings;
    @FindBy(xpath = "//label[contains(text(),'Other Findings')]/following-sibling::input[@id='otherFindingsTxt']")
    private WebElement otherFindingsTxt;
    @FindBy(xpath = "//label[contains(text(),'Region')]/following-sibling::custom-combo//select[@id='region']")
    private WebElement region;
    @FindBy(xpath = "//label[contains(text(),'Zone')]/following-sibling::custom-combo//select[@id='zone']")
    private WebElement zone;
    @FindBy(xpath = "//span[@class='cmnicons ccAdd']")
    private WebElement addFilesBtn;

    public void nameTextField(){
        loginPage.waitForPageLoad();
        loginPage.waitForElementToBeClickable(name);
        Assert.assertTrue(checkedWebElementDisplayed(name)&&name.getAttribute("placeholder").contains("name"));
    }
    public void genderRadioBtn(){
        loginPage.waitForPageLoad();
        loginPage.waitForElementToBeClickable(male);
        Assert.assertTrue(checkedWebElementDisplayed(male)&&male.isSelected()&&checkedWebElementDisplayed(female)&&checkedWebElementDisplayed(other));
    }
    public void dobTextFields(){
        loginPage.waitForPageLoad();
        loginPage.waitForElementToBeClickable(dob);
        Assert.assertTrue(dob.getAttribute("placeholder").contains("date of birth (dd-mm-yyyy)"));
    }
    public void emailIdTextFields(){
        loginPage.waitForPageLoad();
        loginPage.waitForElementToBeClickable(email);
        Assert.assertTrue(checkedWebElementDisplayed(email)&&email.getAttribute("placeholder").contains("email"));
    }
    public void mobileTextFields(){
        loginPage.waitForPageLoad();
        loginPage.waitForElementToBeClickable(mobile);
        Assert.assertTrue(checkedWebElementDisplayed(mobile)&&mobile.getAttribute("placeholder").contains("mobile no"));
    }
    public void alternateContactNo(){
        loginPage.waitForPageLoad();
        loginPage.waitForElementToBeClickable(alternatePhone);
        Assert.assertTrue(checkedWebElementDisplayed(alternatePhone)&&alternatePhone.getAttribute("placeholder").contains("alt. contact no."));
    }
    public void typeDropDown(String firstOptions){
        loginPage.waitForPageLoad();
        loginPage.waitForElementToBeClickable(type);
        Assert.assertTrue(checkedWebElementDisplayed(type)&&loginPage.firstSelectedOption(type).contains(firstOptions));
    }
    public void stageDropDown(String firstOptions){
        loginPage.waitForPageLoad();
        loginPage.waitForElementToBeClickable(stage);
        Assert.assertTrue(checkedWebElementDisplayed(stage)&&loginPage.firstSelectedOption(stage).contains(firstOptions));
    }
    public void campaignDropDown(String firstOptions){
        loginPage.waitForPageLoad();
        loginPage.waitForElementToBeClickable(campaign);
        Assert.assertTrue(checkedWebElementDisplayed(campaign)&&loginPage.firstSelectedOption(campaign).contains(firstOptions));
    }
    public void sourceDropDown(String firstOptions){
        loginPage.waitForPageLoad();
        loginPage.waitForElementToBeClickable(source);
        Assert.assertTrue(checkedWebElementDisplayed(source)&&loginPage.firstSelectedOption(source).contains(firstOptions));
    }
    public void subSourceTextFields(){
        loginPage.waitForPageLoad();
        loginPage.waitForElementToBeClickable(subSource);
        Assert.assertTrue(checkedWebElementDisplayed(subSource)&&subSource.getAttribute("placeholder").contains("sub source"));
    }
    public void findingsDropDown(){
        loginPage.waitForPageLoad();
        loginPage.waitForElementToBeClickable(findings);
        Assert.assertTrue(checkedWebElementDisplayed(findings));
    }
    public void otherFindingTextFields(){
        loginPage.waitForPageLoad();
        loginPage.waitForElementToBeClickable(otherFindingsTxt);
        Assert.assertTrue(checkedWebElementDisplayed(otherFindingsTxt)&&otherFindingsTxt.getAttribute("placeholder").contains("other findings"));
    }
    public void regionDropDown(String firstOptions){
        loginPage.waitForPageLoad();
        loginPage.waitForElementToBeClickable(region);
        Assert.assertTrue(checkedWebElementDisplayed(region)&&loginPage.firstSelectedOption(region).contains(firstOptions));
    }
    public void zoneDropDown(String firstOptions){
        loginPage.waitForPageLoad();
        loginPage.waitForElementToBeClickable(zone);
        Assert.assertTrue(checkedWebElementDisplayed(zone)&&loginPage.firstSelectedOption(zone).contains(firstOptions));
    }
    public void addFilesBtn(){
        loginPage.waitForPageLoad();
        loginPage.waitForElementToBeClickable(addFilesBtn);
        Assert.assertTrue(checkedWebElementDisplayed(addFilesBtn));
    }
    public void saveBtn(){
        loginPage.waitForPageLoad();
        loginPage.waitForElementToBeClickable(saveBtn);
        Assert.assertTrue(checkedWebElementDisplayed(saveBtn));
    }
    public void resetBtn(){
        loginPage.waitForPageLoad();
        loginPage.waitForElementToBeClickable(resetBtn);
        Assert.assertTrue(checkedWebElementDisplayed(resetBtn));
    }
    public void cancelBtn(){
        loginPage.waitForPageLoad();
        loginPage.waitForElementToBeClickable(cancelBtn);
        Assert.assertTrue(checkedWebElementDisplayed(cancelBtn));
    }

    private boolean checkedWebElementDisplayed(WebElement webElement){
        return (webElement.isDisplayed());
    }

}
