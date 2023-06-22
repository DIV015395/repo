package com.prm.patientdashboard.pageobject;

import com.prm.pageobjects.utils.PCDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import java.awt.*;
import java.util.List;

public class FilesPage {
    private PCDriver loginPage;
    public FilesPage(PCDriver loginPage) {
        this.loginPage = loginPage;
        PageFactory.initElements(loginPage.getDriver(), this);
    }
    @FindBy(xpath = "//div[@id='categoryContainer']//label[contains(text(),'Category')]")
    private WebElement categoryDopdownHeader;
    @FindBy(id="formCategory")
    private WebElement categoryDropdown;
    @FindBy(xpath = "//span[contains(@class,'ui-icon ui-icon-checkbox')]")
    private WebElement fileNameCheckbox;
    @FindBy(xpath = "//div[@id='fileNameContainer']/label[contains(text(),'File title')]")
    private WebElement fileNameHeader;
    @FindBy(xpath = "//div[@id='fileNameContainer']//input[@id='form_file_name']")
    private WebElement fileNameTextFields;
    @FindBy(xpath = "//div[@id='notesContainer']//label[text()='Notes']")
    private WebElement notesHeader;
    @FindBy(xpath = "//div[@id='notesContainer']//textarea[@id='form_file_notes']")
    private WebElement notesTextField;
    @FindBy(xpath = "//div[@id='notesContainer']//span[text()='Max. 768 character(s)']")
    private WebElement notesCharactersLimitMsg;
    @FindBy(xpath = "//button[@type='submit']")
    private WebElement saveBtn;
    @FindBy(xpath = "//button[@type='submit']//b[text()='Save']")
    private WebElement saveBtnText;
    @FindBy(xpath = "//button[@id='fileResetter']")
    private WebElement resetBtn;
    @FindBy(xpath = "//button[@id='fileResetter']//b[text()='Reset']")
    private WebElement resetBtnText;
    @FindBy(id = "cancelCTA")
    private WebElement cancelBtn;
    @FindBy(xpath = "//a[@id='cancelCTA']//b[text()='Cancel']")
    private WebElement cancelBtntext;
    @FindBy(xpath = "//label[@id='dropzoneId']/span")
    private  WebElement uploadFilesBtn;
    @FindBy(xpath = "//label[@id='dropzoneId']//b[text()=' Add file(s)']")
    private WebElement uploadFilesBtnText;
    @FindBy(xpath = "//h1[@class='h1tSpace1']")
    private WebElement headerPage;
    @FindBy(id = "urgentPatientNamePid")
    private  WebElement patietName;
    @FindBy(xpath = "//p[@for='formCategory']")
    private WebElement errorCategoryMsg;
    @FindBy(xpath = "//p[@for='form_file_name']")
    private WebElement errorFileTitle;
    @FindBy(xpath = "//div[@class='clearfix alertMessage']")
    private WebElement fileNotSelectedErrorMsg;
    @FindBy(xpath = "//div[text()='File(s) saved/updated sucessfully!']")
    private WebElement successMsg;
    public void enterTitleFile(String title){
        loginPage.waitForPageLoad();
        fileNameTextFields.clear();
        fileNameTextFields.sendKeys(title);
    }
    public void errorMsgFileNotSelected(){
        loginPage.waitForPageLoad();
        try {
            Thread.sleep(1000);
            Assert.assertTrue(fileNotSelectedErrorMsg.getText().contains("Please select a file to upload!"));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void selectCategory(String categoryDropdownValue){
        loginPage.waitForPageLoad();
        loginPage.selectFromDropDownByVisibleText(categoryDropdown,categoryDropdownValue);
    }
    public void clickSaveBtn(){
        loginPage.waitForPageLoad();
        try {
            saveBtn.click();
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void clickResetBtn(){
        loginPage.waitForPageLoad();
        resetBtn.click();
    }
    public void clickCancelBtn(){
        loginPage.waitForPageLoad();
        cancelBtn.click();
    }

    public void checkedErrorCategory(String errorMsg){
        loginPage.waitForPageLoad();
        Assert.assertTrue(errorCategoryMsg.getText().contains(errorMsg));
    }
    public void checkedErrorTitle(String errorMsg){
        loginPage.waitForPageLoad();
        Assert.assertTrue(errorFileTitle.getText().contains(errorMsg));
    }
    public void verifyPatientName(String patientName) {
        loginPage.waitForPageLoad();
        loginPage.waitForElementToBeClickable(patietName);
        String str = patietName.getText();
        String[] split = str.split(" : ");
        boolean flag = split[0].contains(patientName) && !(split[1].equals("NA") || split[1].equals("Null"));
        Assert.assertTrue(flag);
    }
    public void headerPage(String header){
        loginPage.waitForPageLoad();
        Assert.assertTrue(headerPage.getText().contains(header));
    }
    public void categoryDropDownWebelemet(){
        loginPage.waitForPageLoad();
        boolean flag1,flag2,match = false;
        flag1=checkElementDisplayed(categoryDopdownHeader)&&checkElementDisplayed(categoryDropdown);
        String[] exp = {"Select Category","Oral Images","Past Docs","X-Rays","Profile"};
        Select select = new Select(categoryDropdown);
        List<WebElement> options = select.getOptions();
        for(WebElement we:options)
        {
            for (int i=0; i<exp.length; i++){
                if (flag2=we.getText().equals(exp[i])){
                    match = flag1&&flag2;
                }
            }
            Assert.assertTrue(match);
        }
    }

    public void fileTitleWebElementCheckBoxNotSelected(){
        loginPage.waitForPageLoad();
        Assert.assertTrue(checkElementDisplayed(fileNameCheckbox)&&checkElementDisplayed(fileNameHeader)&&fileNameTextFields.getAttribute("class").contains("disabled"));
    }
    public void fileTitleWebElementCheckBoxSelected(){
        loginPage.waitForPageLoad();
        Assert.assertTrue(checkElementDisplayed(fileNameCheckbox)&&checkElementDisplayed(fileNameHeader)&&!fileNameTextFields.getAttribute("class").contains("disabled"));
    }
    public void notesWebElement(){
        loginPage.waitForPageLoad();
        Assert.assertTrue(checkElementDisplayed(notesHeader)&&checkElementDisplayed(notesCharactersLimitMsg)&&checkElementDisplayed(notesTextField)&&notesTextField.getAttribute("maxlength").contains("768"));
    }
    public void enterNotes(String notes){
        loginPage.waitForPageLoad();
        notesTextField.sendKeys(notes);
    }
    public void fileUploadBtnDisplayed(){
        loginPage.waitForPageLoad();
        Assert.assertTrue(checkElementDisplayed(uploadFilesBtn)&&checkElementDisplayed(uploadFilesBtnText));
    }
    public void singleFileUploadBtn(String path){
        loginPage.waitForPageLoad();
        loginPage.WaitTillElementIsPresent(uploadFilesBtn);
      //  uploadFilesBtn.click();
        try {
            uploadFilesBtn.click();
            Thread.sleep(2000);
            loginPage.uploadFile(path);
            Thread.sleep(2000);
        } catch (AWTException e) {
            e.printStackTrace();
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void uploadMultipleFiles(String path1,String path2){
        loginPage.waitForPageLoad();
        String path="\""+path1+"\"\""+path2+"\"";
        uploadFilesBtn.click();
        try {
            uploadFilesBtn.click();
            Thread.sleep(2000);
            loginPage.uploadFile(path);
        } catch (AWTException e) {
            e.printStackTrace();
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void saveBtnDisplayed(){
        loginPage.waitForPageLoad();
        Assert.assertTrue(checkElementDisplayed(saveBtn)&&checkElementDisplayed(saveBtnText));
    }

    public void resetBtnDisplayed(){
        loginPage.waitForPageLoad();
        Assert.assertTrue(checkElementDisplayed(resetBtn)&&checkElementDisplayed(resetBtnText));
    }
    public void cancelBtnDisplayed(){
        loginPage.waitForPageLoad();
        Assert.assertTrue(checkElementDisplayed(cancelBtn)&&checkElementDisplayed(cancelBtntext));
    }

    public void checkFileNameCheckbox(){
        loginPage.waitForPageLoad();
        fileNameCheckbox.click();
        Assert.assertTrue(!fileNameTextFields.getAttribute("class").contains("disabled"));
    }
    public void checkDeleteBtnFileAddPage(String fileName){
        loginPage.waitForPageLoad();
        Assert.assertTrue(checkElementDisplayed(loginPage.getDriver().findElement(By.xpath("//span[contains(text(),'"+fileName+"')]/following-sibling::span/a[contains(@class,'deleteFilesBtn')]"))));
    }
    
    public void checkVisibletoPatientFileAddPage(String fileName){
        loginPage.waitForPageLoad();
        Assert.assertTrue(checkElementDisplayed(loginPage.getDriver().findElement(By.xpath("//span[contains(text(),'"+fileName+"')]/following-sibling::span/label[contains(text(),'Patient Visible')]"))));
    }
    
    public void checkedDeletedFileAddPage(String fileName){
        loginPage.waitForPageLoad();
        List<WebElement> web = loginPage.getDriver().findElements(By.xpath("//span[contains(text(),'" + fileName + "')]/following-sibling::span/a[contains(@class,'deleteFilesBtn')]"));
        Assert.assertTrue(web.size()<1);
    }
    public void checkedImagefileAddPage(String fileName){
        loginPage.waitForPageLoad();
        Assert.assertTrue(checkElementDisplayed(loginPage.getDriver().findElement(By.xpath("//span[contains(text(),'"+fileName+"')]/preceding-sibling::span/img[@class='obj']"))));
    }
    public void clickDeleteBtnFileAddPage(String fileName){
        loginPage.waitForPageLoad();
        loginPage.getDriver().findElement(By.xpath("//span[contains(text(),'"+fileName+"')]/following-sibling::span/a[contains(@class,'deleteFilesBtn')]")).click();
    }
    
    public void clickVisibletoPatientBtnFileAddPage(String fileName){
        loginPage.waitForPageLoad();
        loginPage.getDriver().findElement(By.xpath("//span[contains(text(),'"+fileName+"')]/following-sibling::span/label[contains(text(),'Patient Visible')]/span[@class='checkmark']")).click();
    }
    
    public void clickVisibletoPatientBtnForEditFileAddPage(String fileName){
        loginPage.waitForPageLoad();
        loginPage.getDriver().findElement(By.xpath("//span[contains(text(),'"+fileName+"')]/following-sibling::span//span[contains(text(),'Patient Visible')]/following-sibling::span")).click();
    }
    
    public void successfullyMessage(){
        loginPage.waitForPageLoad();
        try {
            Thread.sleep(2000);
            loginPage.softAssert().assertTrue(checkElementDisplayed(successMsg));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void getFirstSelectOptions(String category){
        loginPage.waitForPageLoad();
        Select sel=new Select(categoryDropdown);
        Assert.assertTrue(sel.getFirstSelectedOption().getText().contains(category));
    }
    public void getFileName(String expected) {
        loginPage.waitForPageLoad();
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) loginPage.getDriver();
        Object obj = javascriptExecutor.executeScript("return document.getElementById('form_file_name').value;");
        String actual = (String) obj;
        Assert.assertEquals(actual, expected);
    }
    public void getFileNotes(String expected) {
        loginPage.waitForPageLoad();
        loginPage.waitForElementToBeClickable(notesTextField);
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) loginPage.getDriver();
        Object obj = javascriptExecutor.executeScript("return document.getElementById('form_file_notes').value;");
        String actual = (String) obj;
        Assert.assertEquals(actual, expected);
    }
    public void editCheckDeleteBtnFileAddPage(String fileName){
       loginPage.waitForPageLoad();
       Assert.assertTrue(checkElementDisplayed(loginPage.getDriver().findElement(By.xpath("//span[contains(text(),'"+fileName+"')]/following-sibling::span/a[@id='filesDeleteOnEditPg']"))));
    }
    public void editDeleteFileAddPage(String fileName){
        loginPage.waitForPageLoad();
        loginPage.getDriver().findElement(By.xpath("//span[contains(text(),'"+fileName+"')]/following-sibling::span/a[@id='filesDeleteOnEditPg']")).click();
    }
    public void editCheckImageFileAddPage(String fileName){
        loginPage.waitForPageLoad();
        Assert.assertTrue(checkElementDisplayed(loginPage.getDriver().findElement(By.xpath("//span[contains(text(),'"+fileName+"')]/preceding-sibling::span/img"))));
    }
    public void fileDeleted(String fileName){
        loginPage.waitForPageLoad();
        WebElement web = loginPage.getDriver().findElement(By.xpath("//span[contains(text(),'" + fileName + "')]"));
        Assert.assertTrue(web.getAttribute("class").contains("strikeOff"));
    }
    public void uploadFileNotPresent(){
        loginPage.waitForPageLoad();
        Assert.assertFalse(checkElementDisplayed(uploadFilesBtn));
    }
    private Boolean checkElementDisplayed(WebElement webElement){
        return (webElement.isDisplayed());
    }
}
