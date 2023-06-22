package com.prm.patientdashboard.pageobject;

import com.google.j2objc.annotations.Weak;
import com.prm.docdashboard.pageobject.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class CampSubscriberPage {
    private LoginPage loginPage;

    public CampSubscriberPage(LoginPage loginPage){
        this.loginPage=loginPage;
        PageFactory.initElements(loginPage.getDriver(), this);
    }
    //WebElement of camp subscriber add
    @FindBy(xpath = "//h1[text()='Camp Subscriber(s)']")
    private WebElement headerPage;
    @FindBy(xpath = "//strong[contains(text(),'Creation Date :')]/span")
    private WebElement creationDate;
    @FindBy(xpath = "//span[contains(text(),'Camp Info')]")
    private WebElement campInfo;
    @FindBy(xpath = "//li[@ng-if='listHideShow']//span[@class='cmnicons nxt-mdl']")
    private WebElement nextBtn;
    @FindBy(xpath = "//div[contains(@class,'sideNavigation')]//span[@class='cmnicons cncl']")
    private WebElement cancelBtn;
    @FindBy(xpath = "//b[text()='Type']/../following-sibling::span")
    private WebElement type;
    @FindBy(xpath = "//label[contains(text(),'Region')]")
    private WebElement regionHeader;
    @FindBy(id = "region")
    private WebElement regionDropDown;
    @FindBy(xpath = "//label[contains(text(),'Zone')]")
    private WebElement zoneHeader;
    @FindBy(id = "zone")
    private WebElement zoneDropDown;
    @FindBy(xpath = "//label[contains(text(),'Campaign')]")
    private WebElement campaignHeader;
    @FindBy(id = "campaign")
    private WebElement campaignDropDown;
    @FindBy(xpath = "//label[contains(text(),'Source')]")
    private WebElement sourceHeader;
    @FindBy(id = "source")
    private WebElement sourceDropDown;
    @FindBy(xpath = "//label[contains(text(),'Sub-Source')]")
    private WebElement subSourceHeader;
    @FindBy(id = "subSource")
    private WebElement subSourceTextField;
    @FindBy(xpath = "//div[text()='Select Region']")
    private WebElement selectRegionErr;
    @FindBy(xpath = "//div[text()='Select Zone']")
    private WebElement selectZoneErr;
    @FindBy(xpath = "//div[text()='Select Campaign']")
    private WebElement selectCampaignErr;
    @FindBy(xpath = "//div[contains(@class,'error')]")
    private WebElement selectSubscriberSourceErr;
    @FindBy(xpath = "//div[text()='Enter Sub-Source']")
    private WebElement enterSubSourceErr;
    //WebElement camp Add page
    @FindBy(xpath = "//div[contains(@class,'sideNavigation')]//span[@class='cmnicons aprsv']")
    private WebElement saveBtn;
    @FindBy(xpath = "//div[contains(@class,'sideNavigation')]//span[@class='cmnicons rst']")
    private WebElement resetBtn;
    @FindBy(xpath = "//b[text()='Region']/../following-sibling::span")
    private WebElement region;
    @FindBy(xpath = "//b[text()='Zone']/../following-sibling::span")
    private WebElement zone;
    @FindBy(xpath = "//b[text()='Campaign']/../following-sibling::span")
    private WebElement campaign;
    @FindBy(xpath = "//b[text()='Source']/../following-sibling::span")
    private WebElement source;
    @FindBy(xpath = "//b[text()='Sub-Source']/../following-sibling::span")
    private WebElement subSource;
    //WebElement New Subscriber
    @FindBy(xpath = "//span[text()='New Subscriber']")
    private WebElement newSubscriberHeader;
    @FindBy(xpath = "//label[text()='Name']")
    private WebElement headerName;
    @FindBy(id="name")
    private WebElement name;
    @FindBy(xpath = "//label[text()='Gender']")
    private WebElement headerGender;
    @FindBy(id = "campSubs-gender-male")
    private WebElement male;
    @FindBy(xpath = "//label[text()='Male']")
    private WebElement maleText;
    @FindBy(id = "campSubs-gender-female")
    private WebElement female;
    @FindBy(xpath = "//label[text()='Female']")
    private WebElement femaleText;
    @FindBy(id = "campSubs-gender-other")
    private WebElement other;
    @FindBy(xpath = "//label[text()='Other']")
    private WebElement otherText;
    @FindBy(xpath = "//label[text()='DOB']")
    private WebElement headerDob;
    @FindBy(id="subs_dob")
    private WebElement dob;
    @FindBy(xpath = "//label[text()='Age']")
    private WebElement headerAge;
    @FindBy(id = "subs_dob_age")
    private WebElement age;
    @FindBy(xpath = "//label[text()='Email']")
    private WebElement headerEmail;
    @FindBy(id = "email")
    private WebElement email;
    @FindBy(xpath = "//label[text()='Mobile']")
    private WebElement headerMobile;
    @FindBy(id = "mobile")
    private WebElement mobile;
    @FindBy(id = "callCheckBox")
    private WebElement callCheckBox;
    @FindBy(xpath="//label[text()='Call']")
    private WebElement callText;
    @FindBy(id = "smsCheckBox")
    private WebElement smsCheckBox;
    @FindBy(xpath = "//label[text()='SMS']")
    private WebElement smsText;
    @FindBy(id = "emailCheckBox")
    private WebElement emailCheckBox;
    @FindBy(xpath = "//input/following-sibling::label[text()='Email']")
    private WebElement emailText;
    @FindBy(xpath = "//i[text()='Add File(s)']/preceding-sibling::span/span[@class='cmnicons ccAdd']")
    private WebElement addFilesBtn;
    @FindBy(xpath = "//span[text()='Finding(s)']")
    private WebElement headerFindings;
    @FindBy(xpath = "//b[contains(text(),'Interest Level')] ")
    private WebElement interestLevel;
    @FindBy(id = "campSubs-intrstLevel-Low")
    private WebElement intrstLevelLow;
    @FindBy(xpath = "//label[text()='Low'] ")
    private WebElement intrstLevelLowText;
    @FindBy(id = "campSubs-intrstLevel-Medium")
    private WebElement intrstLevelMedium;
    @FindBy(xpath = "//label[text()='Medium'] ")
    private WebElement intrstLevelMediumText;
    @FindBy(id = "campSubs-intrstLevel-High")
    private WebElement intrstLevelHigh;
    @FindBy(xpath = "//label[text()='Low'] ")
    private WebElement intrstLevelHighText;
    @FindBy(xpath = "//div[@class='content']/span")
    private List<WebElement> findingsBox;
    @FindBy(xpath = "//span[text()='Membership(s)']")
    private WebElement membershipHeader;
    @FindBy(xpath = "//label[text()='Membership']")
    private WebElement membershipDrpDwnHeader;
    @FindBy(xpath = "//div[@class='multiselect-parent btn-group dropdown-multiselect']")
    private WebElement membershipDropDown;
    @FindBy(xpath = "//button[contains(text(),'Select Membership')]")
    private WebElement selectMembershipBtn;
    @FindBy(xpath = "//input[@placeholder='Search...']")
    private WebElement searchBoxMembership;
    @FindBy(xpath = "//a[contains(text(),'Dental Health Plan 2.0  (550.00)')]/span")
    private WebElement dentalHealthPlan2;
    @FindBy(xpath = "//a[contains(text(),'Dental Health Plan - 3500  (560.00)')]/span")
    private WebElement dentalHealthPlan3;
    //WebElement of payment  mode
    @FindBy(xpath = "//label[text()='Payment Modes']")
    private WebElement headerPaymentMode;
    @FindBy(xpath = "//label[text()='Cash']/preceding-sibling::input[@value='Cash']")
    private WebElement cash;
    @FindBy(xpath = "//label[text()='Cheque']/preceding-sibling::input[@value='Cheque']")
    private WebElement cheque;
    @FindBy(xpath = "//label[text()='NetBanking']/preceding-sibling::input[@value='NetBanking']")
    private WebElement netBanking;
    @FindBy(xpath = "//label[text()='Wallet']/preceding-sibling::input[@value='Wallet']")
    private WebElement wallet;
    @FindBy(xpath = "//label[text()='Card']/preceding-sibling::input[@value='Card']")
    private WebElement card;
    @FindBy(xpath = "//label[text()='Amount']")
    private WebElement amountHeader;
    @FindBy(id = "amount")
    private WebElement amount;
    @FindBy(xpath = "//label[text()='Type']")
    private WebElement subType;
    @FindBy(id = "type")
    private WebElement subTypeDropDown;
    @FindBy(xpath = "//div[contains(@class,'error')]")
    private WebElement errorMsg;
    //camp subscriber add page input listing
    @FindBy(xpath = "//th[text()='Name/Patient Id']")
    private WebElement namePatientId;
    @FindBy(xpath = "//th[text()='Mobile/Email']")
    private WebElement mobileEmail;
    @FindBy(xpath = "//th[text()='Findings']")
    private WebElement findings;
    @FindBy(xpath = "//th[text()='Membership(s)']")
    private WebElement memberships;
    @FindBy(xpath = "//th[text()='Payment Mode']")
    private WebElement paymentMode;
    @FindBy(xpath = "//th[text()='Amount']")
    private WebElement amountTabular;
    @FindBy(xpath = "//th[text()='Action']")
    private WebElement action;
    @FindBy(xpath = "//table[@id='campSubs']/tbody/tr")
    private List<WebElement> rowCampSubs;
    //WebElement of the view modal
    @FindBy(xpath = "//span[@class='cmnicons cncl-mdl']")
    private WebElement modalCloseBtn;
    @FindBy(xpath = "//h4[text()='Details']")
    private WebElement details;
    @FindBy(xpath = "//span[contains(text(),'Created Date')]/..")
    private WebElement createdDateView;
    @FindBy(xpath = "//div[contains(text(),'Personal Info')]")
    private WebElement personalInfo;
    @FindBy(xpath = "//b[text()='Name']/../following-sibling::span")
    private WebElement subNameView;
    @FindBy(xpath = "//b[text()='Mobile']/../following-sibling::span")
    private WebElement subMobileView;
    @FindBy(xpath = "//b[text()='Email']/../following-sibling::span")
    private WebElement subEmailView;
    @FindBy(xpath = "//b[text()='Gender']/../following-sibling::span")
    private WebElement subGenderView;
    @FindBy(xpath = "//b[text()='Age']/../following-sibling::span")
    private WebElement subAgeView;
    @FindBy(xpath = "//div[contains(text(),'Camp Info')]")
    private WebElement campInfoView;
    @FindBy(xpath = "//div[contains(text(),'Camp Info')]/following-sibling::div//b[text()='Type']/../following-sibling::span")
    private WebElement subTypeView;
    @FindBy(xpath = "//div[contains(text(),'Camp Info')]/following-sibling::div//b[text()='Region']/../following-sibling::span")
    private WebElement subRegionView;
    @FindBy(xpath = "//div[contains(text(),'Camp Info')]/following-sibling::div//b[text()='Zone']/../following-sibling::span")
    private WebElement subZoneView;
    @FindBy(xpath = "//div[contains(text(),'Camp Info')]/following-sibling::div//b[text()='Campaign']/../following-sibling::span")
    private WebElement subCampaignView;
    @FindBy(xpath = "//div[contains(text(),'Camp Info')]/following-sibling::div//b[text()='Source']/../following-sibling::span")
    private WebElement subSourceView;
    @FindBy(xpath = "//div[contains(text(),'Camp Info')]/following-sibling::div//b[text()='Sub-Source']/../following-sibling::span")
    private WebElement subSubSourceView;
    @FindBy(xpath = "//div[contains(text(),'Camp Info')]/following-sibling::div//b[text()='Findings']/../following-sibling::div")
    private WebElement subFindingsView;






    //Reusable functions of the camp add page
    public void headerPage(){
        loginPage.waitForElementToBeClickable(headerPage);
        Assert.assertTrue(checkedWebElementDisplayed(headerPage));
    }

    //Creation date Camp Subscriber(s)
    public void creationDate(){
        loginPage.waitForElementToBeClickable(creationDate);
        String createdDate = creationDate.getText().trim();
        LocalDate localDate=LocalDate.now();
        String date2 = localDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        Assert.assertEquals(createdDate,date2);
    }

    //Checking the next button and clicking on Next button
    public void checkedNextBtn(){
        loginPage.waitForElementToBeClickable(nextBtn);
        Assert.assertTrue(checkedWebElementDisplayed(nextBtn));
    }
    public void clickNextBtn(){
        loginPage.waitForElementToBeClickable(nextBtn);
        nextBtn.click();
    }

    //Checking the cancel button and clicking on the cancel Button
    public void checkedCancelBtn(){
        loginPage.waitForElementToBeClickable(cancelBtn);
        Assert.assertTrue(checkedWebElementDisplayed(cancelBtn));
    }
    public void clickCancelBtn(){
        loginPage.waitForElementToBeClickable(cancelBtn);
        cancelBtn.click();
    }

    //Checking the cancel button and clicking on the cancel Button
    public void checkedResetBtn(){
        loginPage.waitForElementToBeClickable(resetBtn);
        Assert.assertTrue(checkedWebElementDisplayed(resetBtn));
    }
    public void clickResetBtn(){
        loginPage.waitForElementToBeClickable(resetBtn);
        loginPage.executeScript(resetBtn);
    }

    //Checking the cancel button and clicking on the cancel Button
    public void checkedSaveBtn(){
        loginPage.waitForElementToBeClickable(saveBtn);
        Assert.assertTrue(checkedWebElementDisplayed(saveBtn));
    }
    public void clickSaveBtn(){
        loginPage.waitForElementToBeClickable(saveBtn);
        loginPage.executeScript(saveBtn);
    }

    //Checking Camp Info header
    public void headerCampInfo(){
        loginPage.waitForElementToBeClickable(campInfo);
        Assert.assertTrue(checkedWebElementDisplayed(campInfo));
    }
    public void openCloseCampInfo(){
        loginPage.waitForElementToBeClickable(campInfo);
        campInfo.click();
    }

    //Checking Type header and options selected
    public void subscriberType(){
        loginPage.waitForElementToBeClickable(type);
        Assert.assertEquals(type.getText().trim(),"Outbound");
    }

    //Checking Region header,Dropdown,options and first options selected
    public void regionDropDown() {
        loginPage.waitForElementToBeClickable(regionDropDown);
        boolean flag1, flag2, match = false;
        flag1 = checkedWebElementDisplayed(regionHeader) && checkedWebElementDisplayed(regionDropDown) && loginPage.firstSelectedOption(regionDropDown).contains("Select Region");
        String[] arr = {"Select Region", "Gujarat", "Maharashtra", "Rajasthan"};
        Select sel = new Select(regionDropDown);
        List<WebElement> options = sel.getOptions();
        for (String we : arr) {
            for (int i = 0; i < options.size(); i++) {
                if (flag2 = we.equals(options.get(i).getText().trim())){
                    match = flag1 && flag2;
                }
            }
            Assert.assertTrue(match);
        }
    }
    public void selectRegion(String region){
        loginPage.waitForElementToBeClickable(regionDropDown);
        loginPage.selectFromDropDownByVisibleText(regionDropDown,region);
    }

    //Checking the Zone
    public void zoneDropDown(){
        loginPage.waitForElementToBeClickable(zoneHeader);
        Assert.assertTrue(checkedWebElementDisplayed(zoneDropDown)&&checkedWebElementDisplayed(zoneHeader));
    }

    //Checking the campaign header,dropdown,options and first options selected
    public void campaignDropDown() {
        loginPage.waitForPageLoad();
        loginPage.waitForElementToBeClickable(campaignDropDown);
        boolean flag1, flag2, match = false;
        flag1 = checkedWebElementDisplayed(campaignDropDown) && loginPage.firstSelectedOption(campaignDropDown).contains("Select Campaign")&&checkedWebElementDisplayed(campaignHeader);
        String[] exp = {"Select Campaign", "Aatman", "Braces","CRM","CSR","Digital","Generic Dental","Implant","International Patients","Kids Campaign","Other","Practo","Pratapa Rao","Preventive Oral Health","Ritika Singh","Smile Makeover","Special Day Offer","TTYD"};
        Select select = new Select(campaignDropDown);
        List<WebElement> options = select.getOptions();
        for (WebElement we : options) {
            for (int i = 0; i < exp.length; i++) {
                if (flag2 = we.getText().trim().equals(exp[i])) {
                    match = flag1 && flag2;
                }
            }
            Assert.assertTrue(match);
        }
    }
    public void selectCampaign(String campaign){
        loginPage.waitForElementToBeClickable(campaignDropDown);
        loginPage.selectFromDropDownByVisibleText(campaignDropDown,campaign);
    }

    //Checking the source header,dropdown,options and first options selected
    public void sourceDropDown() {
        loginPage.waitForPageLoad();
        loginPage.waitForElementToBeClickable(sourceDropDown);
        boolean flag1, flag2, match = false;
        flag1 = checkedWebElementDisplayed(sourceDropDown) && loginPage.firstSelectedOption(sourceDropDown).contains("Select Source")&&checkedWebElementDisplayed(sourceHeader);
        String[] exp = {"Select Source", "Ad-Newspaper", "Ad-Radio","Branding","Camp-CSR","Camp-Market","CDOP-Corporate","CDOP-Coupon/Leaflet","CDOP-Retail","CDOP-RWA","CDOP-School","Chat","Direct Email","Existing Patient","Facebook","Google","In-App","In-Clinic","Just Dial","Nearbuy Deals","Other","Practo","SEM","SMS","Walk-In","Website","Yahoo"};
        Select select = new Select(sourceDropDown);
        List<WebElement> options = select.getOptions();
        for (WebElement we : options) {
            for (int i = 0; i < exp.length; i++) {
                if (flag2 = we.getText().trim().equals(exp[i])) {
                    match = flag1 && flag2;
                }
            }
            Assert.assertTrue(match);
        }
    }
    public void selectSource(String source){
        loginPage.waitForElementToBeClickable(sourceDropDown);
        loginPage.selectFromDropDownByVisibleText(sourceDropDown,source);
    }

    //Checking Sub-source text fields
    public void subSourceTextFields(){
        loginPage.waitForElementToBeClickable(subSourceTextField);
        Assert.assertTrue(checkedWebElementDisplayed(subSourceHeader)&&checkedWebElementDisplayed(subSourceTextField)&&subSourceTextField.getAttribute("placeholder").contains("sub source"));
    }
    public void enterSubSource(String subSource){
        loginPage.waitForElementToBeClickable(subSourceTextField);
        subSourceTextField.sendKeys(subSource);
    }

    //Mandatory error message
    public void selectRegionErr(){
        loginPage.waitForElementToBeClickable(selectRegionErr);
        Assert.assertTrue(checkedWebElementDisplayed(selectRegionErr));
    }
    public void selectZoneErr(){
        loginPage.waitForElementToBeClickable(selectZoneErr);
        Assert.assertTrue(checkedWebElementDisplayed(selectZoneErr));
    }
    public void selectCampaignErr(){
        loginPage.waitForElementToBeClickable(selectCampaignErr);
        Assert.assertTrue(checkedWebElementDisplayed(selectCampaignErr));
    }
    public void selectSubscriberSourceErr(){
        loginPage.waitForElementToBeClickable(selectSubscriberSourceErr);
        Assert.assertTrue(selectSubscriberSourceErr.getText().contains("Select Subscriber's Source"));
    }
    public void enterSubSourceErr(){
        loginPage.waitForElementToBeClickable(enterSubSourceErr);
        Assert.assertTrue(checkedWebElementDisplayed(enterSubSourceErr));
    }

    //Checking Camp info data after selection
    public void selectedRegion(String selected_Region){
        loginPage.waitForElementToBeClickable(region);
        Assert.assertEquals(region.getText().trim(),selected_Region);
    }
    public void selectedZone(String selected_Zone){
        loginPage.waitForElementToBeClickable(zone);
        Assert.assertEquals(zone.getText().trim(),selected_Zone);
    }
    public void selectedCampaign(String selected_Campaign){
        loginPage.waitForElementToBeClickable(campaign);
        Assert.assertEquals(campaign.getText().trim(),selected_Campaign);
    }
    public void selectedSource(String selected_Source){
        loginPage.waitForElementToBeClickable(source);
        Assert.assertEquals(source.getText().trim(),selected_Source);
    }
    public void selectedSubSource(String selected_SubSource){
        loginPage.waitForElementToBeClickable(subSource);
        Assert.assertEquals(subSource.getText().trim(),selected_SubSource);
    }

    //new subscriber WebElement
    public void headerNewSubscriber(){
        loginPage.waitForElementToBeClickable(newSubscriberHeader);
        Assert.assertTrue(checkedWebElementDisplayed(newSubscriberHeader));
    }

    //name WebElement and method
    public void nameTextFields(){
        loginPage.waitForElementToBeClickable(name);
        Assert.assertTrue(checkedWebElementDisplayed(name)&&checkedWebElementDisplayed(headerName)&&name.getAttribute("placeholder").contains("name"));
    }
    public void enterName(String nameSub){
        loginPage.waitForElementToBeClickable(name);
        name.clear();
        name.sendKeys(nameSub);
    }

    //Gender WebElement
    public void genderRadio(){
        loginPage.waitForPageLoad();
        Assert.assertTrue(checkedWebElementDisplayed(headerGender)&&checkedWebElementDisplayed(maleText)&&male.isSelected()&&checkedWebElementDisplayed(femaleText)&&!female.isSelected()&&checkedWebElementDisplayed(otherText)&&!other.isSelected());
    }
    public void selectFemale(){
        loginPage.waitForElementToBeClickable(female);
        female.click();
    }
    public void selectOther(){
        loginPage.waitForElementToBeClickable(other);
        other.click();
    }

    //Dob textFields
    public void dobTextFields(){
        loginPage.waitForElementToBeClickable(dob);
        Assert.assertTrue(checkedWebElementDisplayed(dob)&&checkedWebElementDisplayed(headerDob)&&dob.getAttribute("placeholder").contains("dd-mm-yyyy"));
    }
    public void enterDob(String dobSubs){
        loginPage.waitForElementToBeClickable(dob);
        dob.sendKeys(dobSubs);
    }

    //Age TextFields
    public void ageTextFields(){
        loginPage.waitForElementToBeClickable(age);
        Assert.assertTrue(checkedWebElementDisplayed(age)&&checkedWebElementDisplayed(headerAge)&&age.getAttribute("placeholder").contains("age"));
    }
    public void enterAge(String ageSubs){
        loginPage.waitForElementToBeClickable(age);
        age.sendKeys(ageSubs);
    }

    //Email WebElement
    public void emailTextFields(){
        loginPage.waitForElementToBeClickable(email);
        Assert.assertTrue(checkedWebElementDisplayed(email)&&checkedWebElementDisplayed(headerEmail)&&email.getAttribute("placeholder").contains("email"));
    }
    public void enterEmail(String emailSub){
        loginPage.waitForElementToBeClickable(email);
        email.clear();
        email.sendKeys(emailSub);
    }
    //Mobile WebElement
    public void mobileTextFields(){
        loginPage.waitForPageLoad();
        Assert.assertTrue(checkedWebElementDisplayed(mobile)&&checkedWebElementDisplayed(headerMobile)&&mobile.getAttribute("placeholder").contains("enter 10 digit mobile no"));
    }
    public void enterMobile(String mobileSub){
        loginPage.waitForElementToBeClickable(mobile);
        mobile.clear();
        mobile.sendKeys(mobileSub);
    }
    public void notifications(){
        loginPage.waitForPageLoad();
        Assert.assertTrue(callCheckBox.isSelected()&&checkedWebElementDisplayed(callText)&&smsCheckBox.isSelected()&&checkedWebElementDisplayed(smsText)
        &&!emailCheckBox.isSelected()&&checkedWebElementDisplayed(emailText));
    }
    public void notificationsSelected(){
        loginPage.waitForPageLoad();
        try {
            headerEmail.click();
            Thread.sleep(2000);
            Assert.assertTrue(callCheckBox.isSelected()&&checkedWebElementDisplayed(callText)&&smsCheckBox.isSelected()&&checkedWebElementDisplayed(smsText)
                    &&emailCheckBox.isSelected()&&checkedWebElementDisplayed(emailText));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //File upload webElement
    public void fileUploadBtn(){
        loginPage.waitForElementToBeClickable(addFilesBtn);
        Assert.assertTrue(checkedWebElementDisplayed(addFilesBtn));
    }
    public void uploadFiles(String filePath){
        loginPage.waitForElementToBeClickable(addFilesBtn);
        try {
            loginPage.uploadFile(filePath);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    //Subscriber interest level
    public void interestLevel(){
        loginPage.waitForElementToBeClickable(interestLevel);
        Assert.assertTrue(checkedWebElementDisplayed(interestLevel)&&!intrstLevelLow.isSelected()&&checkedWebElementDisplayed(intrstLevelLowText)
                &&!intrstLevelMedium.isSelected()&&checkedWebElementDisplayed(intrstLevelMediumText)
                &&!intrstLevelHigh.isSelected()&&checkedWebElementDisplayed(intrstLevelHighText));
    }
    public void lowInterest(){
        loginPage.waitForElementToBeClickable(intrstLevelLow);
        intrstLevelLow.click();
    }
    public void mediumInterest(){
        loginPage.waitForElementToBeClickable(intrstLevelMedium);
        intrstLevelMedium.click();
    }public void highInterest(){
        loginPage.waitForElementToBeClickable(intrstLevelHigh);
        intrstLevelHigh.click();
    }

    //checking Findings Header
    public void checkedFindings(){
        loginPage.waitForElementToBeClickable(headerFindings);
        Assert.assertTrue(checkedWebElementDisplayed(headerFindings));
    }
    public void findingBox(String findingsSub){
        loginPage.visibilityOfListLocated(findingsBox);
        int count = findingsBox.size();
        for (int i=0;i<count;i++){
            if(findingsBox.get(i).getText().trim().contains(findingsSub)){
                Assert.assertTrue(checkedWebElementDisplayed(findingsBox.get(i)));
            }else{
                continue;
            }
        }
    }

    //Membership WebElement
    public void headerMembership(){
        loginPage.waitForPageLoad();
        Assert.assertTrue(checkedWebElementDisplayed(membershipHeader)&&checkedWebElementDisplayed(membershipDrpDwnHeader));
    }
    public void membershipDropDown(){
        loginPage.waitForPageLoad();
        membershipDropDown.click();
        Assert.assertTrue(checkedWebElementDisplayed(membershipDropDown)&&checkedWebElementDisplayed(searchBoxMembership)&&checkedWebElementDisplayed(dentalHealthPlan2)&&checkedWebElementDisplayed(dentalHealthPlan3));
    }
    public void enterTrtSearchBox(String trt){
        loginPage.waitForPageLoad();
        searchBoxMembership.sendKeys(trt);
    }
    public  void selectMembershipTrt(){
        loginPage.waitForPageLoad();
        dentalHealthPlan2.click();
    }
    //WebElement of payment modes
    public void paymentMode(){
        loginPage.waitForPageLoad();
        Assert.assertTrue(checkedWebElementDisplayed(headerPaymentMode)&&checkedWebElementDisplayed(amountHeader));
    }
    //cash mode option validation
    public void cashMode(){
        loginPage.waitForPageLoad();
        if(!cash.isSelected()){
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            loginPage.executeScript(cash);
            JavascriptExecutor javascriptExecutor = (JavascriptExecutor) loginPage.getDriver();
            Object obj = javascriptExecutor.executeScript("return document.getElementById('1').value;");
            String str = (String) obj;
            Assert.assertTrue(!(str==null));
        }else{
            Assert.fail();
        }
    }
    //Cheque mode option validation
    public void chequeMode(){
        loginPage.waitForPageLoad();
        if(!cheque.isSelected()){
            loginPage.executeScript(cheque);
            JavascriptExecutor javascriptExecutor = (JavascriptExecutor) loginPage.getDriver();
            Object obj = javascriptExecutor.executeScript("return document.getElementById('3').value;");
            String str = (String) obj;
            Assert.assertTrue(!(str==null));
        }else{
            Assert.fail();
        }
    }
    //NetBanking mode option validation
    public void netBankingMode(){
        loginPage.waitForPageLoad();
        if(!netBanking.isSelected()){
            loginPage.executeScript(netBanking);
            JavascriptExecutor javascriptExecutor = (JavascriptExecutor) loginPage.getDriver();
            Object obj = javascriptExecutor.executeScript("return document.getElementById('4').value;");
            String str = (String) obj;
            Assert.assertTrue(!(str==null));
        }else{
            Assert.fail();
        }
    }
    //card mode option validation
    public void cardMode(){
        loginPage.waitForPageLoad();
        if(!card.isSelected()){
            loginPage.executeScript(card);
            JavascriptExecutor javascriptExecutor = (JavascriptExecutor) loginPage.getDriver();
            Object obj = javascriptExecutor.executeScript("return document.getElementById('3').value;");
            String str = (String) obj;
            Assert.assertTrue(!(str==null));
            boolean flag1, flag2, match = false;
            flag1 = checkedWebElementDisplayed(subType) && checkedWebElementDisplayed(subTypeDropDown) && loginPage.firstSelectedOption(subTypeDropDown).contains("Select Type");
            String[] arr = {"Card", "Bajaj Finance", "EMI", "Liquiloan"};
            Select sel = new Select(subTypeDropDown);
            List<WebElement> options = sel.getOptions();
            for (String we : arr) {
                for (int i = 0; i < options.size(); i++) {
                    if (flag2 = we.equals(options.get(i).getText().trim())){
                        match = flag1 && flag2;
                    }
                }
                Assert.assertTrue(match);
            }
        }else{
            Assert.fail();
        }
    }
    //Wallet mode option validation
    public void walletMode(){
        loginPage.waitForPageLoad();
        if(!wallet.isSelected()){
            loginPage.executeScript(wallet);
            JavascriptExecutor javascriptExecutor = (JavascriptExecutor) loginPage.getDriver();
            Object obj = javascriptExecutor.executeScript("return document.getElementById('6').value;");
            String str = (String) obj;
            Assert.assertTrue(!(str==null));
            boolean flag1, flag2, match = false;
            flag1 = checkedWebElementDisplayed(subType) && checkedWebElementDisplayed(subTypeDropDown) && loginPage.firstSelectedOption(subTypeDropDown).contains("Select Type");
            String[] arr = {"PayTm", "Clove Voucher", "UPI", "Clove DHP Card","Religare Insurance"};
            Select sel = new Select(subTypeDropDown);
            List<WebElement> options = sel.getOptions();
            for (String we : arr) {
                for (int i = 0; i < options.size(); i++) {
                    if (flag2 = we.equals(options.get(i).getText().trim())){
                        match = flag1 && flag2;
                    }
                }
                Assert.assertTrue(match);
            }
        }else{
            Assert.fail();
        }
    }
    //validation of mandatory fields
    public void error(String error_msg){
        loginPage.waitForPageLoad();
        Assert.assertEquals(errorMsg.getText().trim(),error_msg);
    }
    //validating data in input listing
    public void inputListColumn(){
        loginPage.waitForPageLoad();
        try {
            Thread.sleep(5000);
            Assert.assertTrue(checkedWebElementDisplayed(namePatientId)&&checkedWebElementDisplayed(mobileEmail)&&checkedWebElementDisplayed(findings)
                    &&checkedWebElementDisplayed(memberships)&&checkedWebElementDisplayed(paymentMode)&&checkedWebElementDisplayed(amountTabular)&&checkedWebElementDisplayed(action));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void nameInputList(String nameInput) {
        loginPage.waitForPageLoad();
        for (WebElement row : rowCampSubs) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            String name_PatientId = cells.get(0).getText();
            String[] split = name_PatientId.split("\\R");
            Assert.assertTrue(split[0].trim().equalsIgnoreCase(nameInput)&&split[1].trim().equalsIgnoreCase("NA"));
        }
    }
    public void mobileEmailInput(String mobileInput,String emailInput) {
        loginPage.waitForPageLoad();
        for (WebElement row : rowCampSubs) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            String mobile_Email = cells.get(1).getText();
            String[] split = mobile_Email.split("\\R");
            Assert.assertTrue(split[0].trim().equalsIgnoreCase(mobileInput)&&split[1].trim().equalsIgnoreCase(emailInput));
        }
    }
    public void findingsInput(String findingInput) {
        loginPage.waitForPageLoad();
        for (WebElement row : rowCampSubs) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            String findings = cells.get(2).getText();
            Assert.assertTrue(findings.trim().equalsIgnoreCase(findingInput));
        }
    }
    public void membershipInput(String membershipInput) {
        loginPage.waitForPageLoad();
        for (WebElement row : rowCampSubs) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            String membership = cells.get(3).getText();
            Assert.assertTrue(membership.trim().equalsIgnoreCase(membershipInput));
        }
    }
    public void paymentModeInput(String paymentModeInput) {
        loginPage.waitForPageLoad();
        for (WebElement row : rowCampSubs) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            String paymentMode = cells.get(4).getText();
            Assert.assertTrue(paymentMode.trim().equalsIgnoreCase(paymentModeInput));
        }
    }
    public void amountInput(String amountInput) {
        loginPage.waitForPageLoad();
        for (WebElement row : rowCampSubs) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            String amount = cells.get(5).getText();
            Assert.assertTrue(amount.trim().equalsIgnoreCase(amountInput));
        }
    }
    public void viewInput() {
        loginPage.waitForPageLoad();
        for (WebElement row : rowCampSubs) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            WebElement viewBtn = cells.get(6).findElement(By.xpath("div//span[@class='actn-icn view']"));
            Assert.assertTrue(checkedWebElementDisplayed(viewBtn));
        }
    }
    public void openView(String subscriber) {
        loginPage.waitForPageLoad();
        for (WebElement row : rowCampSubs) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            String subs = cells.get(0).getText().trim().split("\\R")[0].trim();
            WebElement view = loginPage.findElement(By.xpath("//span[contains(text(),'"+subs+"')]/../../following-sibling::td//span[@class='actn-icn view']"));
            System.out.println(subs+"====");
            Assert.assertTrue(checkedWebElementDisplayed(view));
            if(subs.equalsIgnoreCase(subscriber)){
                view.click();
            }else {
                Assert.fail();
            }
        }
    }
    public void editInput() {
        loginPage.waitForPageLoad();
        for (WebElement row : rowCampSubs) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            WebElement editBtn = cells.get(6).findElement(By.xpath("div//span[@class='actn-icn edit']"));
            Assert.assertTrue(checkedWebElementDisplayed(editBtn));
        }
    }

    public void deleteInput() {
        loginPage.waitForPageLoad();
        for (WebElement row : rowCampSubs) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            WebElement deleteBtn = cells.get(6).findElement(By.xpath("div//span[@class='actn-icn delete']"));
            Assert.assertTrue(checkedWebElementDisplayed(deleteBtn));
        }
    }
    public void closeViewModal(){
        loginPage.waitForPageLoad();
        loginPage.waitForElementToBeClickable(modalCloseBtn);
        modalCloseBtn.click();
    }
    //creation date and function in view modal
    //personal details
    public void headerViewModal(){
        loginPage.waitForPageLoad();
        try {
            Thread.sleep(8000);
            Assert.assertTrue(checkedWebElementDisplayed(details)&&checkedWebElementDisplayed(personalInfo)&&checkedWebElementDisplayed(campInfoView));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void creationDateView(){
        loginPage.waitForElementToBeClickable(createdDateView);
        String[] createdDate = createdDateView.getText().trim().split(":");
        LocalDate localDate=LocalDate.now();
        String date2 = localDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        Assert.assertEquals(createdDate[1].trim(),date2);
    }
    public void nameSubView(String nameView){
        loginPage.waitForPageLoad();
        Assert.assertEquals(subNameView.getText().trim(),nameView);
    }
    public void mobileSubView(String mobileView){
        loginPage.waitForPageLoad();
        Assert.assertEquals(subMobileView.getText().trim(),mobileView);
    }
    public void emailSubView(String emailView){
        loginPage.waitForPageLoad();
        Assert.assertEquals(subEmailView.getText().trim(),emailView);
    }
    public void genderSubView(String genderView){
        loginPage.waitForPageLoad();
        Assert.assertEquals(subGenderView.getText().trim(),genderView);
    }
    public void ageSubView(String ageView){
        loginPage.waitForPageLoad();
        Assert.assertEquals(subAgeView.getText().trim(),ageView);
    }
    //camp info details
    public void typeSubView(String typeView){
        loginPage.waitForPageLoad();
        Assert.assertEquals(subTypeView.getText().trim(),typeView);
    }
    public void regionSubView(String regionView){
        loginPage.waitForPageLoad();
        Assert.assertEquals(subRegionView.getText().trim(),regionView);
    }
    public void zoneSubView(String zoneView){
        loginPage.waitForPageLoad();
        Assert.assertEquals(subZoneView.getText().trim(),zoneView);
    }
    public void campaignSubView(String campaignView){
        loginPage.waitForPageLoad();
        Assert.assertEquals(subCampaignView.getText().trim(),campaignView);
    }
    public void sourceSubView(String nameView){
        loginPage.waitForPageLoad();
        Assert.assertEquals(subSourceView.getText().trim(),nameView);
    }
    public void subSourceSubView(String subSourceView){
        loginPage.waitForPageLoad();
        Assert.assertEquals(subSubSourceView.getText().trim(),subSourceView);
    }
    public void findingsSubView(String findingsView){
        loginPage.waitForPageLoad();
        Assert.assertEquals(subFindingsView.getText().trim(),findingsView);
    }
















    private boolean checkedWebElementDisplayed(WebElement webElement){
        return (webElement.isDisplayed());
    }
}
