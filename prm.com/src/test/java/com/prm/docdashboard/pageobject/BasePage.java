package com.prm.docdashboard.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

/**
 * @author Ajit
 */
public class BasePage {

    private LoginPage loginPage;

    public BasePage(LoginPage loginPage) {
        this.loginPage = loginPage;
        PageFactory.initElements(loginPage.getDriver(), this);
    }

//    @FindBy(xpath = "//a[contains(@href,'home')]")
    @FindBy(id = "brand_logo")
    public WebElement logo;
    @FindBy(id = "logoutBtn")
    private WebElement logout;
    @FindBy(xpath = "//li[@id='topDropdownContainer']//input")
    private WebElement searchClinicDropdown;
    @FindBy(id = "searchBtnContainer")
    private WebElement searchBtn;
    @FindBy(id = "patientbyall")
    private WebElement searchBox;
    @FindBy(xpath = "(//ul[@class='ui-autocomplete ui-front ui-menu ui-widget ui-widget-content ui-corner-all'])[1]")
    private List<WebElement> searchType;
    @FindBy(xpath = "//div[contains(@class,'clearfix topPagination pagingUI')]//a[contains(@class,'page')]")
    private List<WebElement> totalPagePatients;
    @FindBy(xpath = "//div[contains(@class,'clearfix topPagination pagingUI')]//a[contains(@class,'next')]")
    private WebElement nextPageBtn;
    
    
    
    public void selectClinicFrmHeader(String clinic) {
    	loginPage.waitForSpinnerToDisappear();
    	loginPage.waitForElementToDisappear((By.xpath("//div[@class='ui-widget-overlay']")));
        loginPage.waitForElementVisibility(searchClinicDropdown,4000);
        searchClinicDropdown.clear();
        searchClinicDropdown.sendKeys(clinic);
        loginPage.waitForSpinnerToDisappear();
        loginPage.waitForElementVisibility(searchClinicDropdown,4000);
        loginPage.waitForPageToBecomeActive();
        WebElement element = loginPage.getDriver().findElement(By.xpath("//a[contains(text(),'" + clinic + "')]"));
        loginPage.waitForElementVisibility(element, 4000);
        loginPage.hoverOnElement(element);
        element.click();
        loginPage.waitForPageToBecomeActive();
    }

//    public void selectClinicFrmHeader(String clinic) {
//        loginPage.waitForPageLoad();
//        loginPage.waitForElementToDisappear((By.xpath("//div[contains(@class='ui-widget-overlay')]")));
//        searchClinicDropdown.clear();
//        searchClinicDropdown.sendKeys(clinic);
//        try {
//            Thread.sleep(6000);
//            WebElement element = loginPage.getDriver().findElement(By.xpath("//a[contains(text(),'" + clinic + "')]"));
//            loginPage.hoverOnElement(element);
//            element.click();
//            Thread.sleep(6000);
//        } catch (InterruptedException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }
    
    public void backToDoctorDashbrd(){
    	loginPage.waitForSpinnerToDisappear();
        loginPage.waitForElementVisibility(logo, 4000);
        loginPage.waitForModalOverlayToDisappear();
        logo.click();
    }

    public void backTODoctorDashboard() {
    	loginPage.waitForSpinnerToDisappear();
        loginPage.waitForElementVisibility(logo, 4000);
    	loginPage.waitForSpinnerToDisappearOnDoctorDashboard();
        loginPage.waitForElementVisibility(logo, 6000);
        loginPage.waitForElementToBeClickable(logo);
        logo.click();
//        try {
//            loginPage.waitForElementToBeClickable(logo);
//            Thread.sleep(4000);
//            logo.click();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    public void clickOnlogOut() {
        loginPage.waitForPageLoad();
        loginPage.waitForElementToBeClickable(logout);
        try {
            Thread.sleep(6000);
            logout.click();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
//    public void clickOnSearchBtn() {
//        loginPage.waitForPageLoad();
//        loginPage.waitForSpinnerToDisappear();
//        loginPage.waitForElementVisibility(searchBtn, 4000);
////        loginPage.waitForElementToBeClickable(searchBtn);
//        searchBtn.click();
//    }

    public void clickOnSearchBtn() {
        loginPage.waitForPageLoad();
        loginPage.waitForElementToBeClickable(searchBtn);
        try {
            Thread.sleep(4000);
            searchBtn.click();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
  
    public void enterMobileNo(String patientMobileNum) {
        loginPage.waitForPageLoad();
        loginPage.waitForElementToBeClickable(searchBox);
        searchBox.sendKeys(patientMobileNum);
    }
    
//    public void clickOnPatient(String mobile_no, String patient_name) {
//        loginPage.waitForSpinnerToDisappear();
//        WebElement patientName =   loginPage.getDriver().findElement(By.xpath("//td[contains(text(),'" + mobile_no
//                    + "')]/preceding-sibling::td//a[contains(text(),'" + patient_name + "')]"));
//        loginPage.waitForElementVisibility(patientName, 4000);
//        patientName.click();
//       }
    

    public void clickOnPatient(String mobile_no, String patient_name) {
        loginPage.waitForPageLoad();
        loginPage.waitForSpinnerToDisappear();
        loginPage.waitForElementToDisappear((By.xpath("//div[contains(@class='modal overlay show')]")));
        try {
            Thread.sleep(3000);
            loginPage.getDriver().findElement(By.xpath("//td[contains(text(),'" + mobile_no
                    + "')]/preceding-sibling::td//a[contains(text(),'" + patient_name + "')]")).click();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        loginPage.waitForSpinnerToDisappear();
    }

    public void editPatient(String patient, String mobile) {
        loginPage.waitForPageLoad();
        try {
            Thread.sleep(7000);
            loginPage.getDriver().findElement(By.xpath("//a[contains(text(),'" + patient + "')]/ancestor::td/following-sibling::td[contains(text(),'" + mobile + "')]/following-sibling::td//span[@class='actn-icn edit']")).click();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    public String verification() {
        loginPage.waitForSpinnerToDisappear();
        return loginPage.getTitle();
    }
    
    public void verifyPageTitle(String expectedTitle) {
    	loginPage.verifyPageTitle(expectedTitle);
    }
    
//    public String verification() {
//        loginPage.waitForPageLoad();
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        return loginPage.getTitle();
//    }
}
