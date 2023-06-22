package com.prm.patientdashboard.pageobject;

import com.prm.pageobjects.utils.PCDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

public class FileListingPage {
    private PCDriver loginPage;

    public FileListingPage(PCDriver loginPage) {
        this.loginPage = loginPage;
        PageFactory.initElements(loginPage.getDriver(), this);
    }

    @FindBy(id = "OralImages")
    private WebElement oralImagesTab;
    @FindBy(id = "PastDocs")
    private WebElement pastDocsTab;
    @FindBy(id = "X-Rays")
    private WebElement xRaysTab;
    @FindBy(id = "Profile")
    private WebElement profileTab;
    @FindBy(xpath = "//div[contains(text(),'No record Found')]")
    private WebElement noRecordfound;
    @FindBy(id = "filesDateDDL")
    private WebElement fileDateFilter;
    @FindBy(xpath = "//span[@class='pull-left fileNamePlacement']")
    private WebElement fileName;
    @FindBy(xpath = "//div[@class='dateUpdated clearfix']/following-sibling::p")
    private WebElement notesFileListPopup;
    @FindBy(id = "closeBtn")
    private WebElement closeBtnFileListPopup;
    @FindBy(id = "zoomInOut")
    private WebElement zoomInOutFileListPopup;
    @FindBy(id = "downloadBtn")
    private WebElement downloadBtnFileListPopup;
    @FindBy(xpath = "//p[contains(text(),'Do you want to delete the selected file?')]")
    private WebElement msgDeletePopup;
    @FindBy(xpath = "//span[text()='Yes']")
    private WebElement yesBtnDeletePopup;
    @FindBy(xpath = "//span[text()='No']")
    private WebElement noBtnDeletePopup;
    @FindBy(xpath = "(//span[@id ='urgentPatientNamePid']/span)[1]")
    private  WebElement patientName;
    @FindBy(xpath = "(//span[@id ='urgentPatientNamePid']/span)[2]")
    private  WebElement patientId;

    public void checkFileAddedDate(String fileName) {
        loginPage.waitForPageLoad();
        try {
            WebElement addedFileDate = loginPage.getDriver().findElement(By.xpath("//span[contains(text(),'" + fileName + "')]/following-sibling::span[@class='pull-right']"));
            Date date1 = new SimpleDateFormat("dd-MM-yy").parse(addedFileDate.getText());
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(String.valueOf(LocalDate.now()));
            Assert.assertTrue(date.equals(date1));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    
    public void verifyPatientName(String Name) {
        loginPage.waitForPageLoad();
        loginPage.waitForElementToBeClickable(patientName);
        String str = patientName.getText();
        String str1 = patientId.getText();
        boolean flag = str.contains(Name) && !(str1.equals("NA") || str1.equals("Null"));
        Assert.assertTrue(flag);
    }

    public void multipleFilesRenameAddedDate(String fileName, int totalFile) {
        loginPage.waitForPageLoad();
        List<WebElement> addedFileDate = loginPage.getDriver().findElements(By.xpath("//span[contains(text(),'" + fileName + "')]/following-sibling::span[@class='pull-right']"));
        Assert.assertTrue(addedFileDate.size() == totalFile);
    }

    public void checkedFileEditBtn(String fileName) {
        loginPage.waitForPageLoad();
        WebElement web = loginPage.getDriver().findElement(By.xpath("//span[contains(text(),'" + fileName + "')]/../../preceding-sibling::div/a[contains(@class,'editFileButton')]"));
        Assert.assertTrue(checkElementDisplayed(web) && web.getText().contains("Edit"));
    }

    public void checkedFileVisibleToPatientIcon(String fileName) {
        loginPage.waitForPageLoad();
        WebElement web = loginPage.getDriver().findElement(By.xpath("//span[contains(text(),'"+fileName+"')]/following-sibling::span[@class='pull-right']/span[@id='fileIcon']"));
        Assert.assertTrue(checkElementDisplayed(web));
    }
    
    public void multipleFilesRenameEditBtn(String fileName, int totalFile) {
        loginPage.waitForPageLoad();
        List<WebElement> web = loginPage.getDriver().findElements(By.xpath("//span[contains(text(),'" + fileName + "')]/../../preceding-sibling::div/a[contains(@class,'editFileButton')]"));
        Assert.assertTrue(web.size() == totalFile);
    }

    public void clickFileEditBtn(String fileName) {
        loginPage.waitForPageLoad();
        try {
            loginPage.getDriver().findElement(By.xpath("//span[contains(text(),'" + fileName + "')]/../../preceding-sibling::div/a[contains(@class,'editFileButton')]")).click();
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void checkedFileDownloadBtn(String fileName) {
        loginPage.waitForPageLoad();
        WebElement web = loginPage.getDriver().findElement(By.xpath("//span[contains(text(),'" + fileName + "')]/../../preceding-sibling::div/a[contains(@class,'downloadFileButton')]"));
        Assert.assertTrue(checkElementDisplayed(web) && web.getText().contains("Download"));
    }

    public void multipleFilesRenameDownloadBtn(String fileName, int totalFile) {
        loginPage.waitForPageLoad();
        List<WebElement> web = loginPage.getDriver().findElements(By.xpath("//span[contains(text(),'" + fileName + "')]/../../preceding-sibling::div/a[contains(@class,'downloadFileButton')]"));
        Assert.assertTrue(web.size() == totalFile);
    }

    public void clickFileDownloadBtn(String fileName) {
        loginPage.waitForPageLoad();
        loginPage.getDriver().findElement(By.xpath("//span[contains(text(),'" + fileName + "')]/../../following-sibling::div/a[@class='downloadFileButton']")).click();
    }

    public void checkedFileDeleteBtn(String fileName) {
        loginPage.waitForPageLoad();
        WebElement web = loginPage.getDriver().findElement(By.xpath("//span[contains(text(),'"+fileName+"')]/../../preceding-sibling::div/a[contains(@class,'deleteFileListButton')]"));
        Assert.assertTrue(checkElementDisplayed(web) && web.getText().contains("Delete"));
    }

    public void multipleFilesRenameDeleteBtn(String fileName, int totalFile) {
        loginPage.waitForPageLoad();
        List<WebElement> web = loginPage.getDriver().findElements(By.xpath("//span[contains(text(),'" + fileName + "')]/../../preceding-sibling::div/a[contains(@class,'deleteFileListButton')]"));
        Assert.assertTrue(web.size() == totalFile);
    }

    public void clickFileDeleteBtn(String fileName) {
        loginPage.waitForPageLoad();
        loginPage.getDriver().findElement(By.xpath("//span[contains(text(),'" + fileName + "')]/../../following-sibling::div/a[@class='deleteFileListButton deleteConfirm']")).click();
    }

    public void clickPastDocsTab() {
        loginPage.waitForPageLoad();
        pastDocsTab.click();
        try {
            Thread.sleep(2500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void clickXRayTab() {
        loginPage.waitForPageLoad();
        xRaysTab.click();
        try {
            Thread.sleep(2500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void clickProfileTab() {
        loginPage.waitForPageLoad();
        profileTab.click();
        try {
            Thread.sleep(2500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void oralExamTabSelected() {
        loginPage.waitForPageLoad();
        Assert.assertTrue(checkElementDisplayed(oralImagesTab) && oralImagesTab.getAttribute("class").contains("selectedTab"));
    }

    public void oralExamTabNotSelected() {
        loginPage.waitForPageLoad();
        Assert.assertTrue(checkElementDisplayed(oralImagesTab) && !oralImagesTab.getAttribute("class").contains("selectedTab"));
    }

    public void pastDocsTabSelected() {
        loginPage.waitForPageLoad();
        Assert.assertTrue(checkElementDisplayed(pastDocsTab) && pastDocsTab.getAttribute("class").contains("selectedTab"));
    }

    public void pastDocsTabNotSelected() {
        loginPage.waitForPageLoad();
        Assert.assertTrue(checkElementDisplayed(pastDocsTab) && !pastDocsTab.getAttribute("class").contains("selectedTab"));
    }

    public void xRaysTabSelected() {
        loginPage.waitForPageLoad();
        Assert.assertTrue(checkElementDisplayed(xRaysTab) && xRaysTab.getAttribute("class").contains("selectedTab"));
    }

    public void xRaysTabNotSelected() {
        loginPage.waitForPageLoad();
        Assert.assertTrue(checkElementDisplayed(xRaysTab) && !xRaysTab.getAttribute("class").contains("selectedTab"));
    }

    public void profileTabSelected() {
        loginPage.waitForPageLoad();
        Assert.assertTrue(checkElementDisplayed(profileTab) && profileTab.getAttribute("class").contains("selectedTab"));
    }

    public void profileTabNotSelected() {
        loginPage.waitForPageLoad();
        Assert.assertTrue(checkElementDisplayed(profileTab) && !profileTab.getAttribute("class").contains("selectedTab"));
    }

    public void noRecordMessageDisplayed() {
        loginPage.waitForPageLoad();
        Assert.assertTrue(checkElementDisplayed(noRecordfound));
    }

    public void fileDateFilter() {
        loginPage.waitForPageLoad();
        boolean flag1, flag2, match = false;
        flag1 = checkElementDisplayed(fileDateFilter);
        String[] exp = {"All Dates"};
        Select select = new Select(fileDateFilter);
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

    public void checkedFileAddedDateInDropdown() {
        loginPage.waitForPageLoad();
        Select select = new Select(fileDateFilter);
        List<WebElement> options = select.getOptions();
        LocalDate localDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yy");
        String formattedString = localDate.format(formatter);
        boolean flag = false;
        for (WebElement we : options) {
            if (flag = we.getText().contains(formattedString)) {
            }
        }
        Assert.assertTrue(flag);
    }

    //functions for file list Popup
    public void openFile(String fileName) {
        loginPage.waitForPageLoad();
        loginPage.getDriver().findElement(By.xpath("//span[contains(text(),'" + fileName + "')]")).click();
    }

    public void checkHeaderFileListPopup(String fileName) {
        loginPage.waitForPageLoad();
        Assert.assertTrue(checkElementDisplayed(loginPage.getDriver().findElement(By.xpath("//h2[text()='" + fileName + "']"))));
    }

    public void checkFileAddedDateFileListPopup() {
        loginPage.waitForPageLoad();
        try {
            WebElement addedFileDate = loginPage.getDriver().findElement(By.xpath("//div[@class='dateUpdated clearfix']"));
            Date date1 = new SimpleDateFormat("dd-MM-yy").parse(addedFileDate.getText());
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(String.valueOf(LocalDate.now()));
            Assert.assertTrue(date.equals(date1));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void notesFileListPopup(String notes) {
        loginPage.waitForPageLoad();
        Assert.assertTrue(notesFileListPopup.getText().contains(notes));
    }

    public void actionsBtnFileListPopup() {
        loginPage.waitForPageLoad();
        Assert.assertTrue(checkElementDisplayed(closeBtnFileListPopup) && checkElementDisplayed(zoomInOutFileListPopup) && checkElementDisplayed(downloadBtnFileListPopup));
    }

    public void closeFileListPopup() {
        loginPage.waitForPageLoad();
        loginPage.hoverOnElement(closeBtnFileListPopup);
        closeBtnFileListPopup.click();
    }

    public void deletedFileListing(String fileName) {
    	loginPage.waitForPageLoad();
    	List < WebElement > web = loginPage.getDriver().findElements(By.xpath("//span[contains(@class,'strikeOff') and contains(text(),'"+fileName+"')]"));
    	for(int i=0;web.size()>i;i++) {
    		Assert.assertTrue(web.get(i).getAttribute("class").contains("strikeOff"));
    	}
    }

    public void deletePopupWebElement() {
        loginPage.waitForPageLoad();
        Assert.assertTrue(checkElementDisplayed(msgDeletePopup) && checkElementDisplayed(yesBtnDeletePopup) && checkElementDisplayed(noBtnDeletePopup));
    }
    public void clickNoButton(){
        loginPage.waitForPageLoad();
        loginPage.hoverOnElement(noBtnDeletePopup);
        noBtnDeletePopup.click();
    }
    public void clickYesButton(){
        loginPage.waitForPageLoad();
        try {
            loginPage.hoverOnElement(yesBtnDeletePopup);
            yesBtnDeletePopup.click();
            Thread.sleep(3500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    private Boolean checkElementDisplayed(WebElement webElement) {
        return (webElement.isDisplayed());
    }
}