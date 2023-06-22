package com.prm.patientdashboard.pageobject;

import com.prm.pageobjects.utils.PCDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class CovidDeclarationForm {
    private PCDriver loginPage;

    public CovidDeclarationForm(PCDriver loginPage) {
        this.loginPage = loginPage;
        PageFactory.initElements(loginPage.getDriver(), this);
    }

    //web element of covid-19 form
    @FindBy(id = "infEvaluationFormLabel")
    private WebElement headerCovidForm;
    @FindBy(id = "selectedPatientName")
    private WebElement patientName;
    @FindBy(id = "selectedPatientMobile")
    private WebElement patientId;
    @FindBy(id = "infDiseaseFormSave")
    private WebElement saveCovidFormBtn;
    @FindBy(xpath = "//button[@id='infDiseaseFormSave']/following-sibling::button/span[@class='cmnicons cncl-mdl']")
    private WebElement cancelCovidFormBtn;
    @FindBy(id = "infDiseaseFormReset")
    private WebElement resetCovidFormBtn;
    @FindBy(id = "infDiseaseFormPrint")
    private WebElement printCovidFormBtn;
    @FindBy(xpath = "//span[text()='Have you suffered from any of these in the past 2 weeks?']/preceding-sibling::span[@class='hdrCount']/span[@class='red astrick']")
    private WebElement covidQue_1;
    @FindBy(xpath = "//h4[contains(@data-question,'Have you suffered from any of these in the past 2 weeks?')]/following-sibling::div/div/label[text()='High Fever']")
    private WebElement highFever;
    @FindBy(xpath = "//h4[contains(@data-question,'Have you suffered from any of these in the past 2 weeks?')]/following-sibling::div/div/label[text()='Difficulty in Breathing']")
    private WebElement difficultyBreathing;
    @FindBy(xpath = "//h4[contains(@data-question,'Have you suffered from any of these in the past 2 weeks?')]/following-sibling::div/div/label[text()='Cough / Sore Throat']")
    private WebElement coughSoreThroat;
    @FindBy(xpath = "//h4[contains(@data-question,'Have you suffered from any of these in the past 2 weeks?')]/following-sibling::div/div/label[text()='Sneezing / Running Nose']")
    private WebElement sneezingRunningNose;
    @FindBy(xpath = "//h4[contains(@data-question,'Have you suffered from any of these in the past 2 weeks?')]/following-sibling::div/div/label[text()='None of Them']")
    private WebElement noneOfThem;
    @FindBy(xpath = "//span[text()='Have you been asked to go in quarantine during past 2 months?']/preceding-sibling::span[@class='hdrCount']/span[@class='red astrick']")
    private WebElement covidQue_2;
    @FindBy(xpath = "//h4[contains(@data-question,'Have you been asked to go in quarantine during past 2 months?')]/following-sibling::div//label[text()='Yes']")
    private WebElement yesBtnCovidQue_2;
    @FindBy(xpath = "//h4[contains(@data-question,'Have you been asked to go in quarantine during past 2 months?')]/following-sibling::div//label[text()='No']")
    private WebElement noBtnCovidQue_2;
    @FindBy(xpath = "//span[text()='Any of your family members found COVID positive in last few months?']/preceding-sibling::span[@class='hdrCount']/span[@class='red astrick']")
    private WebElement covidQue_3;
    @FindBy(xpath = "//h4[contains(@data-question,'Any of your family members found COVID positive in last few months?')]/following-sibling::div//label[text()='Yes']")
    private WebElement yesBtnCovidQue_3;
    @FindBy(xpath = "//h4[contains(@data-question,'Any of your family members found COVID positive in last few months?')]/following-sibling::div//label[text()='No']")
    private WebElement noBtnCovidQue_3;
    @FindBy(xpath = "//span[text()='Have you travelled to any other state within India in the past 2 months?']/preceding-sibling::span[@class='hdrCount']/span[@class='red astrick']")
    private WebElement covidQue_4;
    @FindBy(xpath = "//h4[contains(@data-question,'Have you travelled to any other state within India in the past 2 months?')]/following-sibling::div//label[text()='Yes']")
    private WebElement yesBtnCovidQue_4;
    @FindBy(xpath = "//h4[contains(@data-question,'Have you travelled to any other state within India in the past 2 months?')]/following-sibling::div//label[text()='No']")
    private WebElement noBtnCovidQue_4;
    @FindBy(xpath = "//span[text()='Have you lived or travelled to any foreign country in the past 2 months?']/preceding-sibling::span[@class='hdrCount']/span[@class='red astrick']")
    private WebElement covidQue_5;
    @FindBy(xpath = "//h4[contains(@data-question,'Have you lived or travelled to any foreign country in the past 2 months?')]/following-sibling::div//label[text()='Yes']")
    private WebElement yesBtnCovidQue_5;
    @FindBy(xpath = "//h4[contains(@data-question,'Have you lived or travelled to any foreign country in the past 2 months?')]/following-sibling::div//label[text()='No']")
    private WebElement noBtnCovidQue_5;
    @FindBy(xpath = "//span[text()='Have you met someone who recently returned from Corona active country?']/preceding-sibling::span[@class='hdrCount']/span[@class='red astrick']")
    private WebElement covidQue_6;
    @FindBy(xpath = "//h4[contains(@data-question,'Have you met someone who recently returned from Corona active country?')]/following-sibling::div//label[text()='Yes']")
    private WebElement yesBtnCovidQue_6;
    @FindBy(xpath = "//h4[contains(@data-question,'Have you met someone who recently returned from Corona active country?')]/following-sibling::div//label[text()='No']")
    private WebElement noBtnCovidQue_6;
    @FindBy(xpath = "//span[text()='Have you been in contact with a suspected/confirmed case of COVID-19?']/preceding-sibling::span[@class='hdrCount']/span[@class='red astrick']")
    private WebElement covidQue_7;
    @FindBy(xpath = "//h4[contains(@data-question,'Have you been in contact with a suspected/confirmed case of COVID-19?')]/following-sibling::div//label[text()='Yes']")
    private WebElement yesBtnCovidQue_7;
    @FindBy(xpath = "//h4[contains(@data-question,'Have you been in contact with a suspected/confirmed case of COVID-19?')]/following-sibling::div//label[text()='No']")
    private WebElement noBtnCovidQue_7;
    @FindBy(xpath = "//span[text()='Have you been in a gathering or a meeting with unacquainted people or where people have returned from foreign country?']/preceding-sibling::span[@class='hdrCount']/span[@class='red astrick']")
    private WebElement covidQue_8;
    @FindBy(xpath = "//h4[contains(@data-question,'Have you been in a gathering or a meeting with unacquainted people or where people have returned from foreign country?')]/following-sibling::div//label[text()='Yes']")
    private WebElement yesBtnCovidQue_8;
    @FindBy(xpath = "//h4[contains(@data-question,'Have you been in a gathering or a meeting with unacquainted people or where people have returned from foreign country?')]/following-sibling::div//label[text()='No']")
    private WebElement noBtnCovidQue_8;
    @FindBy(xpath = "//span[text()='History of any medicine intake during last 2 weeks?']/preceding-sibling::span[@class='hdrCount']/span[@class='red astrick']")
    private WebElement covidQue_9;
    @FindBy(xpath = "//h4[contains(@data-question,'History of any medicine intake during last 2 weeks?')]/following-sibling::div//label[text()='Yes']")
    private WebElement yesBtnCovidQue_9;
    @FindBy(xpath = "//h4[contains(@data-question,'History of any medicine intake during last 2 weeks?')]/following-sibling::div//label[text()='No']")
    private WebElement noBtnCovidQue_9;
    @FindBy(xpath = "//span[text()='What is Your Temperature (In Fahrenheit)?']/preceding-sibling::span[@class='hdrCount']")
    private WebElement covidQue_10;
    @FindBy(xpath = "//h4[contains(@data-question,'What is Your Temperature (In Fahrenheit)?')]/following-sibling::div/input[contains(@placeholder,'enter details')]")
    private WebElement textFieldsCovidQue_10;
    @FindBy(xpath = "//span[text()='What is your Oximeter level?']/preceding-sibling::span[@class='hdrCount']")
    private WebElement covidQue_11;
    @FindBy(xpath = "//h4[contains(@data-question,'What is your Oximeter level?')]/following-sibling::div/input[contains(@placeholder,'enter details')]")
    private WebElement textFieldsCovidQue_11;
    @FindBy(xpath = "//span[text()='Do you want to overide the patient status manually?']/preceding-sibling::span[@class='hdrCount']")
    private WebElement covidQue_12;
    @FindBy(xpath = "//span[contains(text(),'Do you want to overide the patient status manually?')]/../following-sibling::div//label[@data-desc='Low Probability']")
    private WebElement greenBtnCovidQue_12;
    @FindBy(xpath = "//span[contains(text(),'Do you want to overide the patient status manually?')]/../following-sibling::div//label[@data-desc='Medium Probability']")
    private WebElement yellowBtnCovidQue_12;
    @FindBy(xpath = "//span[contains(text(),'Do you want to overide the patient status manually?')]/../following-sibling::div//label[@data-desc='High Probability']")
    private WebElement redBtnCovidQue_12;
    @FindBy(id = "status_remarks")
    private WebElement remarksTextFields;
    @FindBy(xpath = "//textarea[@id='status_remarks']/following-sibling::span[text()='Max. 768 character(s)']")
    private WebElement maxCarMsg;
    @FindBy(xpath = "//p[contains(text(),'This field is required')]")
    private WebElement errorMsg;
    @FindBy(xpath = "//span[contains(text(),'Do you want to overide the patient status manually?')]/../following-sibling::div//input[@data-desc='Green']")
    private WebElement lowProbability;
    @FindBy(xpath = "//span[contains(text(),'Do you want to overide the patient status manually?')]/../following-sibling::div//input[@data-desc='Yellow']")
    private WebElement mediumProbability;
    @FindBy(xpath = "//span[contains(text(),'Do you want to overide the patient status manually?')]/../following-sibling::div//input[@data-desc='Red']")
    private WebElement highProbability;

    //Reusable function
    //closing the covid form modal
    public void closeCovidForm() {
        loginPage.waitForPageLoad();
        try {
            loginPage.waitForElementToBeClickable(cancelCovidFormBtn);
            cancelCovidFormBtn.click();
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void saveCovidForm() {
        loginPage.waitForPageLoad();
        loginPage.waitForElementToBeClickable(saveCovidFormBtn);
        saveCovidFormBtn.click();
    }

    //checking patient name and patient id
    public void patientNameCovidForm(String patient) {
        loginPage.waitForElementToBeClickable(headerCovidForm);
        Assert.assertTrue(headerCovidForm.getText().contains("Covid-19 Declaration") && patientName.getText().equalsIgnoreCase(patient) && !(patientId.getText().contains("NA") || patientId.getText().contains("null")));
    }

    //checking action button
    public void checkActionsNewPatient() {
        loginPage.waitForElementToBeClickable(cancelCovidFormBtn);
        Assert.assertTrue(checkedWebElementDisplayed(cancelCovidFormBtn) && checkedWebElementDisplayed(saveCovidFormBtn));
    }

    //check all mandatory question of the covid form
    public void question_1() {
        loginPage.waitForElementToBeClickable(covidQue_1);
        Assert.assertTrue(checkedWebElementDisplayed(covidQue_1) && checkedWebElementDisplayed(highFever) && checkedWebElementDisplayed(difficultyBreathing) && checkedWebElementDisplayed(coughSoreThroat) && checkedWebElementDisplayed(sneezingRunningNose) && checkedWebElementDisplayed(noneOfThem));
    }

    public void question_2() {
        loginPage.waitForElementToBeClickable(covidQue_2);
        Assert.assertTrue(checkedWebElementDisplayed(covidQue_2) && checkedWebElementDisplayed(yesBtnCovidQue_2) && checkedWebElementDisplayed(noBtnCovidQue_2));
    }

    public void question_3() {
        loginPage.waitForElementToBeClickable(covidQue_3);
        Assert.assertTrue(checkedWebElementDisplayed(covidQue_1) && checkedWebElementDisplayed(yesBtnCovidQue_3) && checkedWebElementDisplayed(noBtnCovidQue_3));
    }

    public void question_4() {
        loginPage.waitForElementToBeClickable(covidQue_4);
        Assert.assertTrue(checkedWebElementDisplayed(covidQue_4) && checkedWebElementDisplayed(yesBtnCovidQue_4) && checkedWebElementDisplayed(noBtnCovidQue_4));
    }

    public void question_5() {
        loginPage.waitForElementToBeClickable(covidQue_5);
        Assert.assertTrue(checkedWebElementDisplayed(covidQue_5) && checkedWebElementDisplayed(yesBtnCovidQue_5) && checkedWebElementDisplayed(noBtnCovidQue_5));
    }

    public void question_6() {
        loginPage.waitForElementToBeClickable(covidQue_6);
        Assert.assertTrue(checkedWebElementDisplayed(covidQue_6) && checkedWebElementDisplayed(yesBtnCovidQue_6) && checkedWebElementDisplayed(noBtnCovidQue_6));
    }

    public void question_7() {
        loginPage.waitForElementToBeClickable(covidQue_7);
        Assert.assertTrue(checkedWebElementDisplayed(covidQue_7) && checkedWebElementDisplayed(yesBtnCovidQue_7) && checkedWebElementDisplayed(noBtnCovidQue_7));
    }

    public void question_8() {
        loginPage.waitForElementToBeClickable(covidQue_8);
        Assert.assertTrue(checkedWebElementDisplayed(covidQue_8) && checkedWebElementDisplayed(yesBtnCovidQue_8) && checkedWebElementDisplayed(noBtnCovidQue_8));
    }

    public void question_9() {
        loginPage.waitForElementToBeClickable(covidQue_9);
        Assert.assertTrue(checkedWebElementDisplayed(covidQue_9) && checkedWebElementDisplayed(yesBtnCovidQue_9) && checkedWebElementDisplayed(noBtnCovidQue_9));
    }

    public void question_10() {
        loginPage.waitForElementToBeClickable(covidQue_10);
        Assert.assertTrue(checkedWebElementDisplayed(covidQue_10) && checkedWebElementDisplayed(textFieldsCovidQue_10));
    }

    public void question_11() {
        loginPage.waitForElementToBeClickable(covidQue_11);
        Assert.assertTrue(checkedWebElementDisplayed(covidQue_11) && checkedWebElementDisplayed(textFieldsCovidQue_11));
    }

    public void question_12() {
        loginPage.waitForElementToBeClickable(covidQue_12);
        Assert.assertTrue(checkedWebElementDisplayed(covidQue_12) && checkedWebElementDisplayed(greenBtnCovidQue_12) && checkedWebElementDisplayed(yellowBtnCovidQue_12) && checkedWebElementDisplayed(redBtnCovidQue_12));
    }

    public void remarksTextFields() {
        loginPage.waitForElementToBeClickable(remarksTextFields);
        Assert.assertTrue(checkedWebElementDisplayed(remarksTextFields) && checkedWebElementDisplayed(maxCarMsg));
    }

    //selecting the options
    public void yesOption(int count) {
        loginPage.waitForElementToBeClickable(yesBtnCovidQue_2);
        switch (count) {
            case 3:
                yesBtnCovidQue_3.click();
                break;
            case 2:
                yesBtnCovidQue_2.click();
                break;
            case 4:
                yesBtnCovidQue_4.click();
                break;
            case 5:
                yesBtnCovidQue_5.click();
                break;
            case 6:
                yesBtnCovidQue_6.click();
                break;
            case 7:
                yesBtnCovidQue_7.click();
                break;
            case 8:
                yesBtnCovidQue_8.click();
                break;
            case 9:
                yesBtnCovidQue_9.click();
                break;
        }
    }

    public void noOption(int count) {
        loginPage.waitForElementToBeClickable(noBtnCovidQue_2);
        switch (count) {
            case 3:
                noBtnCovidQue_3.click();
                break;
            case 2:
                noBtnCovidQue_2.click();
                break;
            case 4:
                noBtnCovidQue_4.click();
                break;
            case 5:
                noBtnCovidQue_5.click();
                break;
            case 6:
                noBtnCovidQue_6.click();
                break;
            case 7:
                noBtnCovidQue_7.click();
                break;
            case 8:
                noBtnCovidQue_8.click();
                break;
            case 9:
                noBtnCovidQue_9.click();
                break;
        }
    }

    public void noOptionSelected(int count) {
        loginPage.waitForElementToBeClickable(noBtnCovidQue_2);
        switch (count) {
            case 2:
                Assert.assertTrue(noBtnCovidQue_2.getAttribute("class").contains("active"));
                break;
            case 3:
                Assert.assertTrue(noBtnCovidQue_3.getAttribute("class").contains("active"));
                break;
            case 4:
                Assert.assertTrue(noBtnCovidQue_4.getAttribute("class").contains("active"));
                break;
            case 5:
                Assert.assertTrue(noBtnCovidQue_5.getAttribute("class").contains("active"));
                break;
            case 6:
                Assert.assertTrue(noBtnCovidQue_6.getAttribute("class").contains("active"));
                break;
            case 7:
                Assert.assertTrue(noBtnCovidQue_7.getAttribute("class").contains("active"));
                break;
            case 8:
                Assert.assertTrue(noBtnCovidQue_8.getAttribute("class").contains("active"));
                break;
            case 9:
                Assert.assertTrue(noBtnCovidQue_9.getAttribute("class").contains("active"));
                break;
        }
    }

    public void errorMsgDisplayed() {
        loginPage.waitForElementToBeClickable(errorMsg);
        Assert.assertTrue(checkedWebElementDisplayed(errorMsg));
    }

    public void selectFirstQuestion(String symptoms) {
        loginPage.waitForPageLoad();
        loginPage.waitForElementToBeClickable(highFever);
        switch (symptoms) {
            case "High Fever":
                loginPage.executeScript(highFever);
                break;
            case "Difficulty in Breathing":
                loginPage.executeScript(difficultyBreathing);
                break;
            case "Cough / Sore Throat":
                loginPage.executeScript(coughSoreThroat);
                break;
            case "Sneezing / Running Nose":
                loginPage.executeScript(sneezingRunningNose);
                break;
            case "None of Them":
                loginPage.executeScript(noneOfThem);
                break;
        }
    }

    public void selectedFirstQuestion(String symptoms) {
        loginPage.waitForPageLoad();
        loginPage.waitForElementToBeClickable(highFever);
        switch (symptoms) {
            case "High Fever":
                Assert.assertTrue(highFever.getAttribute("class").contains("active"));
                break;
            case "Difficulty in Breathing":
                Assert.assertTrue(difficultyBreathing.getAttribute("class").contains("active"));
                break;
            case "Cough / Sore Throat":
                Assert.assertTrue(coughSoreThroat.getAttribute("class").contains("active"));
                break;
            case "Sneezing / Running Nose":
                Assert.assertTrue(sneezingRunningNose.getAttribute("class").contains("active"));
                break;
            case "None of Them":
                Assert.assertTrue(noneOfThem.getAttribute("class").contains("active"));
                break;
        }
    }

    public void greenFlag() {
        Assert.assertTrue(lowProbability.isSelected());
    }

    public void patientTemperature() {
        loginPage.waitForElementToBeClickable(textFieldsCovidQue_10);
        String temperature = (String) ((JavascriptExecutor) loginPage.getDriver()).executeScript("return arguments[0].value;", textFieldsCovidQue_10);
        Assert.assertTrue(temperature.isEmpty());
    }

    public void patientOximeter() {
        loginPage.waitForElementToBeClickable(textFieldsCovidQue_11);
        String oximeter = (String) ((JavascriptExecutor) loginPage.getDriver()).executeScript("return arguments[0].value;", textFieldsCovidQue_11);
        Assert.assertTrue(oximeter.isEmpty());
    }

    public void setPatientTemperature(String temperature) {
        loginPage.waitForElementToBeClickable(textFieldsCovidQue_10);
        textFieldsCovidQue_10.clear();
        textFieldsCovidQue_10.sendKeys(temperature);
    }

    public void setPatientOximeter(String oximeter) {
        loginPage.waitForElementToBeClickable(textFieldsCovidQue_11);
        textFieldsCovidQue_11.clear();
        textFieldsCovidQue_11.sendKeys(oximeter);
    }

    public void checkPatientTemperature(String temp) {
        loginPage.waitForElementToBeClickable(textFieldsCovidQue_10);
        String temperature = (String) ((JavascriptExecutor) loginPage.getDriver()).executeScript("return arguments[0].value;", textFieldsCovidQue_10);
        Assert.assertEquals(temperature, temp);
    }

    public void checkPatientOximeter(String oximeter) {
        loginPage.waitForElementToBeClickable(textFieldsCovidQue_11);
        String ximeterLevel = (String) ((JavascriptExecutor) loginPage.getDriver()).executeScript("return arguments[0].value;", textFieldsCovidQue_11);
        Assert.assertEquals(ximeterLevel, oximeter);
    }

    private boolean checkedWebElementDisplayed(WebElement webElement) {
        return (webElement.isDisplayed());
    }
}
