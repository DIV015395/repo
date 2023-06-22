package com.prm.docdashboard.pageobject;

import com.prm.pageobjects.utils.ExtentReport;
import com.prm.pageobjects.utils.PCDriver;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ProductSalePage {
    private PCDriver loginPage;
    private ExtentReport extentReport;
    final public int highlight_Products_In_Listing=120;
    final public int stop_highlighting_Products_In_Listing=180;
    final public int remove_Near_expiry_product_from_listing=30;



    public ProductSalePage( PCDriver login, ExtentReport extentReport ){
        this.loginPage=login;
        this.extentReport=extentReport;
        PageFactory.initElements ( login.getDriver (),this );
    }


    @FindBy(xpath = "//div[@class='modal overlay']")
    private WebElement loader;
    @FindBy(xpath = "//span[@class='title']/span[text()='Product Sales']")
    private WebElement productSalesTab;
    @FindBy(xpath = "//span[@class='title' and text()='Edit Products']")
    private WebElement editProductSalesTab;
    @FindBy(xpath = "//span[@class='actn-icn save']")
    private WebElement saveButton;
    @FindBy(xpath = "//span[@class='actn-icn cancel']/..")
    private WebElement cancelButton;
    @FindBy(id = "searchProducts")
    private WebElement searchProducts;
    @FindBy(xpath = "//div[@class='product-srch-icn']")
    private WebElement searchProductButton;
    @FindBy(xpath = "//span[@class='glyphicon glyphicon-exclamation-sign']/following-sibling::i[text()='No Product Found!']")
    private WebElement noProductFoundMsg;
    @FindBy(id = "patientname")
    private WebElement patientName;
    @FindBy(id = "mobile")
    private WebElement patientMobile;
    @FindBy(id = "patientid")
    private WebElement patientId;
    @FindBy(id = "email")
    private WebElement patientEmailId;
    @FindBy(xpath = "//span[@class='actn-icn manualUpdate']")
    private WebElement resetButton;
    @FindBy(xpath = "//span[@class='cart-badge cart-filled']/span")
    private WebElement itemInCart;
    @FindBy(xpath = "//span[@class='cart-badge cart-filled']/span")
    private WebElement selectedItemInCart;
    @FindBy(xpath = "//div[@class='payable_sec ng-binding ng-scope']/span[text()='Amt. Payable']")
    private WebElement amtPayableLabel;
    @FindBy(xpath = "//div[@class='col-xs-5 col-sm-4 col-md-3 col-lg-2 product-spc ng-scope']")
    private List<WebElement> listOfProduct;
    @FindBy(xpath = "//div[@class='error ng-binding ng-scope' and text()='Enter patient name']")
    private WebElement enterNameErrMsg;
    @FindBy(xpath = "//div[@class='error ng-binding ng-scope' and text()='Enter mobile no.']")
    private WebElement enterMobileErrMsg;
    @FindBy(xpath = "//div[text()='Enter 10 digit mobile no.']")
    private WebElement enterTenDigitMobileNumberErrMsg;
    @FindBy(xpath = "//div[text()='Enter a valid mobile no.']")
    private WebElement enterValidMobileNumberErrMsg;
    @FindBy(xpath = "//div[text()='Enter valid email address']")
    private WebElement enterValidEmailErrMsg;
    @FindBy(xpath = "//span[text()='Please add product(s) before clicking on Save button!']")
    private WebElement addProductErrorMsg;
    @FindBy(xpath = "//span[text()='Quantity for product is zero!']")
    private WebElement productQtyZeroErrorMsg;
    @FindBy(xpath = "//div[contains(@class,'modal-inset mdleConfirmationTxtValign ng-scope')]")
    private WebElement confirmationModal;
    @FindBy(xpath = "//div[@class='modalBtns']//span[@class='cmnicons cncl-mdl']")
    private WebElement cancelBtnOnConfirmationModal;
    @FindBy(xpath = "//div[@class='modalBtns']//span[@class='cmnicons yes-mdl']")
    private WebElement yesButtonOnConfirmationModal;

    @FindBy(xpath = "//table[@class='table table-striped']//th[1]/span[text()='Product(s) - Company, Batch & Expiry']")
    private WebElement productNameColumnLabel;
    @FindBy(xpath = "//table[@class='table table-striped']//th[2][text()='Unit Cost']")
    private WebElement unitCostColumnLabel;
    @FindBy(xpath = "//table[@class='table table-striped']//th[3][text()='Qty.']")
    private WebElement qtyColumnLabel;
    @FindBy(xpath = "//table[@class='table table-striped']//th[4][text()='Net Amount']")
    private WebElement netAmountColumnLabel;
    @FindBy(xpath = "//table[@class='table table-striped']//th[5][text()='Cpn. Amount']")
    private WebElement couponColumnLabel;
    @FindBy(xpath = "//table[@class='table table-striped']//th[6][text()='Coupon Basis']")
    private WebElement couponBasisColumnLabel;
    @FindBy(xpath = "//table[@class='table table-striped']//th[7][text()='Amt. Pay.']")
    private WebElement amountPayableColumnLabel;
    @FindBy(xpath = "//table[@class='table table-striped']//th[8][text()='Action']")
    private WebElement actionColumnLabel;
    @FindBy(xpath = "//table[@class='table table-striped']/tbody/tr")
    private List<WebElement> listOfProductAddedInCart;
    @FindBy(xpath = "//span[text()='Product deleted successfully!']")
    private WebElement productDeletedSuccessMsg;
    @FindBy(xpath = "//span[contains(@ng-bind-html,'match.label | typeaheadHighlight:query')]")
    private List<WebElement> autoSuggest;
    @FindBy(xpath = "//span[contains(@ng-bind-html,'match.model.pubpid | typeaheadHighlight:query')]")
    private List<WebElement> autoSuggestByPatientId;
    @FindBy(xpath ="//span[text()='Amt. Payable']/..")
    private WebElement amtPayable;


    @FindBy(xpath = "//span[@class='left-nav']")
    private WebElement leftNavigation;
    @FindBy(xpath = "//span[@class='mdl-hdr-text ng-binding ng-scope']")
    private WebElement patientNameAndIdOnProductListing;

    @FindBy(xpath = "//a[@class='searchBtn fa fa-search cursor-pointer']")
    private WebElement globalSearchButton;
    @FindBy(xpath = "//a[@class='btn-warning dropBtn']")
    private WebElement globalClinicDrpDwnBtn;




    public boolean isProductSaleTabDisplayed(){
        waitTillLoaderDisappear ();
        extentReport.logger.log ( LogStatus.INFO,"CHecking ProductSale Tab is Displayed" );
        loginPage.waitForElementToBeClickable ( productSalesTab );
        return  productSalesTab.isDisplayed ();

    }

    public void clickOnProductSaleTab(){
        loginPage.waitForElementToBeClickable ( productSalesTab );
        extentReport.logger.log ( LogStatus.INFO,"Clicking on ProductSale Tab" );
        productSalesTab.click ();
    }

    public boolean verifyPatientNameField(){
        loginPage.waitForElementToBeClickable ( patientName );
        boolean isPlaceHolderDisplayed=patientName.getAttribute ( "placeholder" ).contains ( "» enter patient name*" );
        boolean isPatientNameFieldDisplayed=checkWebElementIsDisplayed (patientName);
        extentReport.logger.log ( LogStatus.INFO,"Checking PatientName field and placeholder in PatientName field on Product listing" );
        return isPlaceHolderDisplayed && isPatientNameFieldDisplayed;
    }

    public boolean verifyMobileNumberField (){
        loginPage.waitForElementToBeClickable ( patientMobile );
        boolean isPlaceHolderDisplayed=patientMobile.getAttribute ( "placeholder" ).contains ( "» enter patient mobile*" );
        boolean isPatientMobileNumberDisplayed=checkWebElementIsDisplayed (patientMobile);
        extentReport.logger.log ( LogStatus.INFO,"Checking MobileNumber field and placeholder in MobileNumber field on Product listing" );
        return isPatientMobileNumberDisplayed && isPlaceHolderDisplayed;
    }

    public boolean verifyPatientIdField(){
        loginPage.waitForElementToBeClickable ( patientId );
        boolean isPlaceHolderDisplayed=patientId.getAttribute ( "placeholder" ).contains ( "» enter patient id" );
        boolean isPatientMobileNumberDisplayed=checkWebElementIsDisplayed (patientMobile);
        extentReport.logger.log ( LogStatus.INFO,"Checking PatientID field and placeholder in PatientId field on Product listing" );
        return isPatientMobileNumberDisplayed && isPlaceHolderDisplayed;
    }

    public boolean verifyPatientEmailIdField(){
        loginPage.waitForElementToBeClickable ( patientEmailId );
        boolean isPlaceHolderDisplayed=patientEmailId.getAttribute ( "placeholder" ).contains ( "» enter patient email" );
        boolean isPatientEmailFieldDisplayed=checkWebElementIsDisplayed (patientEmailId);
        extentReport.logger.log ( LogStatus.INFO,"Checking PatientEmail field and placeholder in PatientEmail field on Product listing" );
        return isPlaceHolderDisplayed && isPatientEmailFieldDisplayed;
    }

    public boolean checkSaveButton(){
        loginPage.WaitTillElementIsPresent ( saveButton );
        extentReport.logger.log ( LogStatus.INFO,"clicking on Save button on Product Listing" );
        return checkWebElementIsDisplayed (saveButton);
    }
    public boolean checkCancelButton(){
        loginPage.waitForElementToBeClickable ( cancelButton );
        extentReport.logger.log ( LogStatus.INFO,"clicking on Cancel button on Product Listing" );
        return checkWebElementIsDisplayed (cancelButton);
    }

    public boolean checkResetButton(){
        loginPage.waitForElementToBeClickable ( resetButton );
        extentReport.logger.log ( LogStatus.INFO,"clicking on reset button on Product Listing" );
        return checkWebElementIsDisplayed (resetButton);
    }

    public boolean checkSearchProduct(){
        waitTillLoaderDisappear ();
        loginPage.waitForElementToBeClickable ( searchProducts );
        boolean isPlaceHolderDisplayed=searchProducts.getAttribute ( "placeholder" ).contains ( "» search by name and batch" );
        ExtentReport extentReport=new ExtentReport (loginPage,"Checking searchProducts field and placeholder of searchProducts field On Product Listing");
        return checkWebElementIsDisplayed (searchProducts) && isPlaceHolderDisplayed && searchProductButton.isDisplayed ();
    }

    public boolean noProductFoundErrMsg(){
        loginPage.waitForElementToBeClickable ( noProductFoundMsg );
        return noProductFoundMsg.isDisplayed ();

    }

    public void searchProductByNameAndBatch(String searchDetails){
        loginPage.waitForElementToBeClickable ( searchProducts );
        searchProducts.clear ();
        searchProducts.sendKeys (searchDetails);

    }
    public void clickOnSaveButton(){
        clickOnWebElement ( saveButton );
    }

    public  void clickOnCancelButton(){
        clickOnWebElement (cancelButton);
    }

    public void clickOnResetButton(){
        clickOnWebElement(resetButton);
    }

    public void enterPatientName(String name){
        enterText ( patientName,name );

    }
    public void enterMobileNumber(String mobileNumber){
        enterText ( patientMobile,mobileNumber );

    }

    public void enterPatientId(String id){

        enterText ( patientId,id );
    }

    public void enterPatientEmail(String email){
        enterText ( patientEmailId,email );
    }

    public void enterSearchedDetails(String searchDetail){
        enterText ( searchProducts,searchDetail );
    }

    public boolean verifyEnterPatientNameErrorMsg(){
        try {
            loginPage.WaitTillElementIsPresent (enterNameErrMsg );
            return enterNameErrMsg.isDisplayed ();
        }
        catch (NoSuchElementException exception){
            return false;
        }


    }

    public boolean verifyEnterPatientMobileNumberErrorMsg(){
        try{
            loginPage.WaitTillElementIsPresent ( enterMobileErrMsg );
            return enterMobileErrMsg.isDisplayed ();
        }
        catch (NoSuchElementException exception){
            return false;

        }


    }

    public boolean verifyAddProductErrMsg(){
        loginPage.WaitTillElementIsPresent ( addProductErrorMsg );
        return addProductErrorMsg.isDisplayed ();
    }

    public boolean verifyEnterTenDigitMobileNumberErrMsg(){
        loginPage.WaitTillElementIsPresent ( enterTenDigitMobileNumberErrMsg );
        return enterTenDigitMobileNumberErrMsg.isDisplayed ();
    }

    public boolean verifyEnterValidMobileNumberErrMsg(){
        try{
            loginPage.WaitTillElementIsPresent ( enterValidMobileNumberErrMsg );
            return enterValidMobileNumberErrMsg.isDisplayed ();
        }
        catch (NoSuchElementException exception){
            return  false;
        }

    }

    public boolean verifyEnterValidEmailErrorMsg(){
        try{
            loginPage.WaitTillElementIsPresent ( enterValidEmailErrMsg );
            return enterValidEmailErrMsg.isDisplayed ();
        }
        catch (NoSuchElementException exception){
            return  false;
        }
    }


    public boolean verifyProductSearchByName(String productName){
        boolean isProductShowingRelatedToSearch=false;
        productName=productName.toLowerCase ();
        loginPage.visibilityOfListLocated ( listOfProduct );
        for(WebElement product:listOfProduct){
            WebElement elements=product.findElement ( By.xpath ( "div/div[contains(@class,'product-info')]/h5" ) );
            String actualProductName= elements.getText ().trim ().toLowerCase ();
            if(actualProductName.contains ( productName )){
                isProductShowingRelatedToSearch=true;
            }
            else{
                isProductShowingRelatedToSearch=false;
            }
        }

        return isProductShowingRelatedToSearch;
    }

    public boolean verifyProductSearchByBatchNumber(String itemBatchNumber){
        boolean isProductShowingRelatedToSearch=false;
        itemBatchNumber=itemBatchNumber.toLowerCase ();
        for(WebElement product:listOfProduct){
            WebElement elements=product.findElement ( By.xpath ( "div/div[contains(@class,'product-info')]/div[contains(@class,'batch_expiry_info')]/span[1]" ) );
            String actualProductName= elements.getText ().trim ().toLowerCase ();
            if(actualProductName.contains ( itemBatchNumber )){
                isProductShowingRelatedToSearch=true;
            }
            else{
                 isProductShowingRelatedToSearch=false;
            }
        }
        return isProductShowingRelatedToSearch;

    }

    public String getPatientName(){
        String patientName=getEnteredText ( "patientname" );
        return  patientName;

    }

    public String getPatientMobile(){
        String patientMobile=getEnteredText ( "mobile" );
        return patientMobile;

    }

    public String getPatientId(){
        String patientId=getEnteredText ( "patientid" );
        return  patientId;

    }

    public String getPatientEmail(){
        String patientEmail=getEnteredText ( "email" );
        return patientEmail;

    }

    public boolean verifyProductSelectedOnListing(String productName,String batchNumber){
        WebElement element=loginPage.getDriver ().findElement ( By.xpath ( "//div/span[contains(text(),'"+batchNumber+"')]/../preceding-sibling::h5[contains(text(),'"+productName+"')]/../.." ) );
        loginPage.WaitTillElementIsPresent ( element );
        if(element.getAttribute ( "class" ).contains ( "selected" )){
            return true;
        }
        else{
            return false;
        }

    }

    public boolean verifyProductListingData (){
        loginPage.waitTillInvisiblityOfElement ( loader );
        boolean isAllDetailsCorrectOnListing=false;
        loginPage.visibilityOfListLocated ( listOfProduct );
        for(int i=0;i<listOfProduct.size ();i++){
            String name=listOfProduct.get ( i ).findElement ( By.xpath ( "//div[@class='product-info']/h5" ) ).getText ().trim ();
            String companyName=listOfProduct.get ( i ).findElement ( By.xpath ( "//div[@class='company_name ng-binding ng-scope']" ) ).getText ().trim ();
            String price=listOfProduct.get ( i ).findElement ( By.xpath ( "//span[contains(@class,'discounted_price ng-binding')]" ) ).getText ().trim ();
            WebElement defaultQtyCount=listOfProduct.get ( i ).findElement ( By.xpath ( "//span[contains(@class,'product-quantity ng-binding')]" ) );
            WebElement addedQtySign=listOfProduct.get ( i ).findElement ( By.xpath ( "//span[contains(@class,'qtyBtns pos')]" ) );
            WebElement removeQtySign=listOfProduct.get ( i ).findElement ( By.xpath ( "//span[contains(@class,'qtyBtns neg disabled')]" ) );

            boolean isNameDisplayed=!(name.equalsIgnoreCase ( "NA" ) || name.equalsIgnoreCase ( "Null" ));
            boolean isCompanyNameDisplayed=!(companyName.equalsIgnoreCase ( "NA" ) || companyName.equalsIgnoreCase ( "Null" ));
            boolean isPriceDisplayed=!(price.equalsIgnoreCase ( "NA" ) || price.equalsIgnoreCase ( "NUll" ));
            boolean isAddedQtySignDisplayed=addedQtySign.isDisplayed ();
            boolean isRemoveQtySignDisplayed=removeQtySign.isDisplayed ();
            boolean defaultQtyCountDisplayedIsZero=Integer.valueOf (defaultQtyCount.getText ())==0;

            isAllDetailsCorrectOnListing=isNameDisplayed && isCompanyNameDisplayed && isPriceDisplayed
                    && isAddedQtySignDisplayed && isRemoveQtySignDisplayed && defaultQtyCountDisplayedIsZero;
        }

        return  isAllDetailsCorrectOnListing;
    }

    public void addProductQtyToCart(String productName,String batchNumber,int qty){
        loginPage.waitTillInvisiblityOfElement ( loader );
        loginPage.visibilityOfListLocated ( listOfProduct );
        WebElement addQty=loginPage.getDriver ().findElement ( By.xpath ( "//div[contains(@class,'product-item')]//h5[contains(text(),'"+productName+"')]/following-sibling::div/span[contains(text(),'"+batchNumber+"')]/../../following-sibling::div//span[contains(@class,'qtyBtns pos')]" ) );
        for(int i=qty+1;i>0;i--){
            addQty.click ();
        }
    }

    public void removeProductQtyFromCart(String productName,String batchNumber,int qty){
        loginPage.waitTillInvisiblityOfElement ( loader );
        loginPage.visibilityOfListLocated ( listOfProduct );
        WebElement removeQty=loginPage.getDriver ().findElement ( By.xpath ( "//div[contains(@class,'product-item')]//h5[contains(text(),'"+productName+"')]/following-sibling::div/span[contains(text(),'"+batchNumber+"')]/../../following-sibling::div//span[contains(@class,'qtyBtns neg')]" ) );
        for(int i=1;i<=qty;i++){
            removeQty.click ();
        }
    }

    public String verifyProductCompanyName(String productName,String batchNumber){
        String companyName=loginPage.getDriver ().findElement ( By.xpath ( "//div[@class='product-item']//h5[contains(text(),'"+productName+"')]/following-sibling::div/span[contains(text(),'"+batchNumber+"')]/../../preceding-sibling::div[@class='company_name ng-binding ng-scope']" ) ).getText ().trim ();
        return companyName;
    }

    public String verifyProductSize(String productName,String batchNumber){
        String productSize=loginPage.getDriver ().findElement ( By.xpath ( "//div[@class='product-item']//h5[contains(text(),'"+productName+"')]/following-sibling::div/span[contains(text(),'"+batchNumber+"')]/../../div[@class='size_info ng-binding ng-scope']" ) ).getText ().trim ();
        return productSize;
    }

    public boolean verifyProductOriginalPrice(String productName,String batchNumber,String productOriginalPrice){
        double actualOriginalPrice=Double.valueOf (loginPage.getDriver ().findElement ( By.xpath ( "//div[contains(@class,'product-item')]//h5[contains(text(),'"+productName+"')]/following-sibling::div/span[contains(text(),'"+batchNumber+"')]/../../following-sibling::div//span[contains(@class,'discounted_price ng-binding disabled_orig_cost')]" ) ).getText ());
        double expectedOriginalPrice=Double.valueOf(productOriginalPrice);
        if(actualOriginalPrice==expectedOriginalPrice) {
        	return true;
        }
        else {
        	return false;
        }
    }

    public boolean verifyProductPriceAfterDiscount(String productName,String batchNumber,int discount){
        String price=loginPage.getDriver ().findElement ( By.xpath ( "//div[contains(@class,'product-item')]//h5[contains(text(),'"+productName+"')]/following-sibling::div/span[contains(text(),'"+batchNumber+"')]/../../following-sibling::div//span[contains(@class,'discounted_price ng-binding disabled_orig_cost')]" ) ).getText ();
        double originalPrice=Double.valueOf ( price );
        String actualPrice=loginPage.getDriver ().findElement ( By.xpath ( "//div[contains(@class,'product-item')]//h5[contains(text(),'"+productName+"')]/following-sibling::div/span[contains(text(),'"+batchNumber+"')]/../../following-sibling::div//span[contains(@class,'discounted_price ng-binding ng-scope')]" ) ).getText ();
        double actualDiscountPrice=Double.valueOf ( actualPrice.split ( "\n" )[0]+actualPrice.split ( "\n" )[1]);
        double expectedDiscountPrice=originalPrice-getTwoDecimalPlacesValue ((discount*originalPrice)/100);
        return actualDiscountPrice==expectedDiscountPrice;

    }

    public boolean verifyProductName(String productName,String batchNumber){
        WebElement element=loginPage.getDriver ().findElement ( By.xpath ( "//div[contains(@class,'product-item')]//div/span[contains(text(),'"+batchNumber+"')]/../preceding-sibling::h5[contains(text(),'"+productName+"')]" ) );
        loginPage.WaitTillElementIsPresent ( element );
        return element.isDisplayed ();

    }

    public boolean verifyProductBatchNumber(String productName,String batchNumber){
        WebElement element=loginPage.getDriver ().findElement ( By.xpath ( "//div[contains(@class,'product-item')]//div/h5[contains(text(),'"+productName+"')]/following-sibling::div/span[contains(text(),'"+batchNumber+"')]/.." ) );
        loginPage.WaitTillElementIsPresent ( element );
        return element.isDisplayed ();

    }

    public boolean verifyQtyDiscountTagOnProduct(String productName,String batchNumber,int offerQty,int discount){
        String discountTag;
        if(offerQty>1){
            discountTag="Buy "+offerQty+" get "+discount+"% "+"Off";
        }
        else{
            discountTag=discount+"% Off";
        }
        WebElement element=loginPage.getDriver ().findElement ( By.xpath ( "//div[@class='product-info']//h5[@title='"+productName+"']/following-sibling::div/span[contains(text(),'"+batchNumber+"')]/../../preceding-sibling::div[@class='product-image']//strong[contains(text(),'"+discountTag+"')]" ) );
        loginPage.WaitTillElementIsPresent ( element );
        if(checkWebElementIsDisplayed ( element )){
            return true;
        }
        else {
            return false;
        }
    }

    public boolean verifySpecialDiscountTagOnProduct(String productName,String batchNumber,String discountFromDate,String discountToDate){
        WebElement element=loginPage.getDriver ().findElement ( By.xpath ( "//div[contains(@class,'product-item')]//div/span[contains(text(),'"+batchNumber+"')]/../preceding-sibling::h5[contains(text(),'"+productName+"')]/.././preceding-sibling::div[@class='product-image']/div[normalize-space()='special offer']/div" ) );
        loginPage.WaitTillElementIsPresent ( element );
        Date currentDate=new Date ();
        Date fromDate =convertStringIntoDate ( discountFromDate );
        Date toDate=convertStringIntoDate ( discountToDate );
        if((currentDate.after ( fromDate) || currentDate.equals (  fromDate)) && (currentDate.before (toDate) || currentDate.equals (toDate))){
            if(checkWebElementIsDisplayed ( element )){
                return true;
            }
            else{
                return false;
            }
        }
        else{
            return true;
        }
    }

    public boolean verifyToolTipOnProduct(String productName,String batchNumber){
        WebElement element=loginPage.getDriver ().findElement ( By.xpath ( "//div[contains(@class,'product-item')]//div/span[contains(text(),'"+batchNumber+"')]/../preceding-sibling::h5[contains(text(),'"+productName+"')]/.././preceding-sibling::div/span[@class='info medical']" ) );
        loginPage.WaitTillElementIsPresent ( element );
        return checkWebElementIsDisplayed ( element );

    }

    public int getAddedQtyCountOnProduct(String productName,String batchNumber){
        int  qty=Integer.valueOf (loginPage.getDriver ().findElement ( By.xpath ( "//div[contains(@class,'product-item')]//div/h5[contains(text(),'"+productName+"')]/following-sibling::div/span[contains(text(),'"+batchNumber+"')]/../../following-sibling::div//span[@class='product-quantity ng-binding']" ) ).getText ());
        return qty;

    }

    public boolean verifyEditProductTab(){
        try{
            loginPage.WaitTillElementIsPresent ( editProductSalesTab );
            return editProductSalesTab.isDisplayed ();
        }
        catch (NoSuchElementException exception){
            return  false;
        }



    }

    public void clickOnEditProductTab(){
        loginPage.waitForElementToBeClickable ( editProductSalesTab );
        editProductSalesTab.click ();
    }

    public boolean verifyColumnLabelOnProductCart(){
        loginPage.WaitTillElementIsPresent (productNameColumnLabel  );
        boolean isAllColumnLabelPresent=checkWebElementIsDisplayed (productNameColumnLabel) &&checkWebElementIsDisplayed (unitCostColumnLabel) && checkWebElementIsDisplayed (qtyColumnLabel)
                && checkWebElementIsDisplayed (netAmountColumnLabel) && checkWebElementIsDisplayed (couponColumnLabel) && checkWebElementIsDisplayed (couponBasisColumnLabel) && checkWebElementIsDisplayed (amountPayableColumnLabel)
                && checkWebElementIsDisplayed (actionColumnLabel);
        return  isAllColumnLabelPresent;

    }

    public boolean verifyAmtPayableOfProductAddedInCart(){

        double expectedTotalAmtPayable=0.00;
        List<WebElement> listOfProductAddedInCart=loginPage.getDriver ().findElements ( By.xpath ( "//div[@class='product-item selected']" ) );

        if(listOfProductAddedInCart.size ()>0){
            loginPage.visibilityOfListLocated (listOfProductAddedInCart  );
        for(int i=0;i<listOfProductAddedInCart.size ();i++){
            String amtPayableOfSingleQty=listOfProductAddedInCart.get ( i ).findElement ( By.xpath ( "child::div[@class='product-action-btns']//span[contains(@class,'discounted_price ng-binding')]" ) ).getText ().trim ();
            amtPayableOfSingleQty=amtPayableOfSingleQty.split ( "\n" )[0]+amtPayableOfSingleQty.split ( "\n" )[1];
            int selectedQty=Integer.valueOf (listOfProductAddedInCart.get ( i ).findElement ( By.xpath ( "child::div[@class='product-action-btns']//span[contains(@class,'product-quantity ng-binding')]" ) ).getText ().trim ());
            double amtPayable=getTwoDecimalPlacesValue (Double.valueOf (amtPayableOfSingleQty) * selectedQty);
            expectedTotalAmtPayable=expectedTotalAmtPayable+amtPayable;

        }
        double actualTotalAmtPayable= getTwoDecimalPlacesValue (Double.valueOf (amtPayable.getText ().trim ().split ( "\n" )[1]));
        boolean isAmtPayableCorrectDisplayedOnListing=expectedTotalAmtPayable ==actualTotalAmtPayable;

        return isAmtPayableCorrectDisplayedOnListing;
        }
        else{
            try{
                loginPage.WaitTillElementIsPresent ( amtPayable );
                return amtPayable.isDisplayed ();
            }
            catch (NoSuchElementException noSuchElementException){
                return  false;
            }

        }

    }

    public boolean verifyZeroQuantityErrorMsg(){
        loginPage.WaitTillElementIsPresent ( productQtyZeroErrorMsg );
        return productQtyZeroErrorMsg.isDisplayed ();

    }
    public boolean verifyMaxQtyAddedErrorMsg(String maxQty ){
        WebElement errorMsg=loginPage.getDriver ().findElement ( By.xpath ( "//span[contains(text(),'Only "+maxQty+" quantity of this product is available!')]" ) );
        loginPage.WaitTillElementIsPresent ( errorMsg );
        return errorMsg.isDisplayed ();

    }

    public boolean verifyPlusIconOnAddingMaxQtyOnListing(String productName,String batch){
        WebElement plusIconOnProduct=loginPage.getDriver ().findElement ( By.xpath ( "//div[contains(@class,'product-item')]//h5[contains(text(),'"+productName+"')]/following-sibling::div/span[contains(text(),'"+batch+"')]/../../following-sibling::div//span[@class='qtyBtns pos disabled']" ) );
        loginPage.WaitTillElementIsPresent ( plusIconOnProduct );
        return plusIconOnProduct.isDisplayed ();
    }

    public boolean verifyPlusIconOnAddingMaxQtyOnCart(String productName,String batchNumber){
        WebElement plusIconOnProduct=loginPage.getDriver ().findElement ( By.xpath ( "//table[@class='table table-striped']/tbody/tr/td//span[contains(normalize-space(),'"+productName+"')]//following-sibling::span[contains(text(),'"+batchNumber+"')]/../../../following-sibling::td//span[contains(@class,'qtyBtns pos ng-scope')]" ) );
        loginPage.WaitTillElementIsPresent ( plusIconOnProduct );
        return plusIconOnProduct.isDisplayed ();
    }

    public String checkAddedProductCompanyShortNameDisplayed(String productName,String batchNumber){
        WebElement element=loginPage.getDriver ().findElement ( By.xpath ( "//table[@class='table table-striped']/tbody/tr/td//span[contains(normalize-space(),'"+productName+"')]//following-sibling::span[contains(text(),'"+batchNumber+"')]/../preceding-sibling::span[@class='company_name ng-binding ng-scope']" ) );
        String actualProductCompanyName=element.getText ().split ( " " )[0];
        return actualProductCompanyName;
    }

    public boolean checkAddedProductNameDisplayed(String productName,String batchNumber){
        try{
        WebElement element=loginPage.getDriver ().findElement ( By.xpath ( "//table[@class='table table-striped']/tbody/tr/td//span[contains(text(),'"+batchNumber+" ')]/../parent::span[contains(normalize-space(),'"+productName+"')]" ) );
        loginPage.WaitTillElementIsPresent ( element );
        return checkWebElementIsDisplayed ( element );
        }
        catch (NoSuchElementException noSuchElementException){
            return  false;
        }

    }
    public boolean checkAddedProductBatchDisplayed(String productName,String batchNumber){
        try{
        WebElement element=loginPage.getDriver ().findElement ( By.xpath ( "//table[@class='table table-striped']/tbody/tr/td//span[contains(normalize-space(),'"+productName+"')]//following-sibling::span[contains(text(),'"+batchNumber+" ')]" ) );
        loginPage.WaitTillElementIsPresent ( element );
        return checkWebElementIsDisplayed ( element );
        }
        catch (NoSuchElementException noSuchElementException){
            return false;
        }
    }

    public boolean verifyDetailsOfProductInCart(){
        boolean isAllValuesDisplayedCorrect=false;

        for(int i=0;i<listOfProductAddedInCart.size ();i++){
            WebElement productName=listOfProductAddedInCart.get ( i ).findElement ( By.xpath ( "//span[@class='company_name ng-binding ng-scope']/.." ) );
            WebElement companyName=listOfProductAddedInCart.get ( i ).findElement ( By.xpath ( "//span[@class='company_name ng-binding ng-scope']" ) );
            double unitCost= Double.valueOf (listOfProductAddedInCart.get ( i ).findElement ( By.xpath ( "td[2]" ) ).getText ().trim ());
            int qty=Integer.valueOf ( listOfProductAddedInCart.get ( i ).findElement ( By.xpath ( "td[3]//span[@class='product-quantity ng-binding']" ) ).getText ().trim () );
            double netAmount=Double.valueOf ( listOfProductAddedInCart.get ( i ).findElement ( By.xpath ( "td[4]" ) ).getText ().trim () );
            double coupon=Double.valueOf ( listOfProductAddedInCart.get ( i ).findElement ( By.xpath ( "td[5]" ) ).getText ().trim () );
            double amountPayable=Double.valueOf ( listOfProductAddedInCart.get ( i ).findElement ( By.xpath ( "td[7]" ) ).getText ().trim () );
            WebElement deleteButton=listOfProductAddedInCart.get ( i ).findElement ( By.xpath ( "td[8]//span[@class='actn-icn delete']" ) );
            String couponBasis=listOfProductAddedInCart.get ( i ).findElement ( By.xpath ( "td[6]" ) ).getText ().trim ();

            boolean isUnitCostCorrect=getTwoDecimalPlacesValue (netAmount/Double.valueOf ( qty ))==getTwoDecimalPlacesValue (unitCost);
            boolean isNetAmountCorrect=getTwoDecimalPlacesValue ((unitCost*Double.valueOf ( qty )))==getTwoDecimalPlacesValue (netAmount);
            System.out.println ("***********"+(netAmount-amountPayable));
            System.out.println ("######"+coupon);
            boolean isCouponCorrect=getTwoDecimalPlacesValue ((netAmount-amountPayable))==getTwoDecimalPlacesValue (coupon);
            boolean isAmountPayableCorrect=getTwoDecimalPlacesValue ((netAmount-coupon))==getTwoDecimalPlacesValue (amountPayable);
            boolean qtyShouldMoreThanZero=qty>0;
            boolean isDeleteButtonDisplayed=deleteButton.isDisplayed ();
            boolean isCouponBasisDisplayedCorrect=false;
            boolean productNameDisplayed=!(productName.getText ().trim ().equalsIgnoreCase ( "NA" ) || productName.getText ().trim ().equalsIgnoreCase ( "Null" ));
            boolean companyNameDisplayed=!(companyName.getText ().trim ().equalsIgnoreCase ( "NA" ) || companyName.getText ().trim ().equalsIgnoreCase ( "Null" ));


            if(coupon!=0){
                isCouponBasisDisplayedCorrect=!(couponBasis.equals ( "NA" ) || couponBasis.equals ( "Not Applicable" )) ;

            }
            else{
                isCouponBasisDisplayedCorrect=couponBasis.equals ( "Not Applicable" );
            }

            isAllValuesDisplayedCorrect=isUnitCostCorrect && isNetAmountCorrect && isCouponCorrect &&
                    isAmountPayableCorrect && qtyShouldMoreThanZero && isDeleteButtonDisplayed && isCouponBasisDisplayedCorrect && productNameDisplayed && companyNameDisplayed;



        }
        return isAllValuesDisplayedCorrect;

    }

    public double getUnitCostOfAddedProduct ( String productName, String batchNumber){
        WebElement element=loginPage.getDriver ().findElement ( By.xpath ( "//table[@class='table table-striped']/tbody/tr/td//span[contains(normalize-space(),'"+productName+"')]//following-sibling::span[contains(text(),'"+batchNumber+"')]/../../../following-sibling::td[1]" ) );
        double actualUnitCost=Double.valueOf ( element.getText () );
        return  actualUnitCost;
    }

    public int getAddedQtyOfProductOnListing ( String productName, String batchNumber){
        WebElement element=loginPage.getDriver ().findElement ( By.xpath ( "//table[@class='table table-striped']/tbody/tr/td//span[contains(normalize-space(),'"+productName+"')]//following-sibling::span[contains(text(),'"+batchNumber+"')]/../../../following-sibling::td//span[@class='product-quantity ng-binding']" ) );
        int actualQty=Integer.valueOf ( element.getText () );
        return actualQty;

    }



    public boolean verifyNetAmountOfAddedProduct ( String productName, String batchNumber){
        double expectedNetAmount=getUnitCostOfAddedProduct (productName,batchNumber  ) * Double.valueOf ( getAddedQtyOfProductOnListing ( productName,batchNumber ) );
        WebElement element=loginPage.getDriver ().findElement ( By.xpath ( "//table[@class='table table-striped']/tbody/tr/td//span[contains(normalize-space(),'"+productName+"')]//following-sibling::span[contains(text(),'"+batchNumber+"')]/../../../following-sibling::td[3]" ) );
        double actualNetAmount=Double.valueOf ( element.getText () );
        if(expectedNetAmount==actualNetAmount){
            return true;
        }
        else{
            return false;
        }

    }

    public boolean verifyCouponAmtOfAddedProduct(String productName, String batchNumber,int discountPercentage){
        double originalAmount=getUnitCostOfAddedProduct (productName,batchNumber) *Double.valueOf ( getAddedQtyOfProductOnListing ( productName,batchNumber ) );
        double expectedCouponValue=(originalAmount *(Double.valueOf (  discountPercentage)/100.00));
        WebElement element=loginPage.getDriver ().findElement ( By.xpath ( "//table[@class='table table-striped']/tbody/tr/td//span[contains(normalize-space(),'"+productName+"')]//following-sibling::span[contains(text(),'"+batchNumber+"')]/../../../following-sibling::td[4]" ) );
        double actualCouponValue=Double.valueOf ( element.getText () );

        if(actualCouponValue==expectedCouponValue){
            return true;
        }
        else{
            return false;
        }


    }
    public boolean verifyAmtPayableOfAddedProduct(String productName, String batchNumber,int discountPercentage){
        double netAmount=getUnitCostOfAddedProduct (productName,batchNumber) * Double.valueOf ( getAddedQtyOfProductOnListing ( productName,batchNumber ) );
        double couponAmount=(netAmount *(Double.valueOf (discountPercentage)/100.00));
        double expectedAmtPayable=netAmount-couponAmount;

        WebElement element=loginPage.getDriver ().findElement ( By.xpath ( "//table[@class='table table-striped']/tbody/tr/td//span[contains(normalize-space(),'"+productName+"')]//following-sibling::span[contains(text(),'"+batchNumber+"')]/../../../following-sibling::td[6]" ) );
        double actualAmountPayable=Double.valueOf (element.getText ());

        if(actualAmountPayable==expectedAmtPayable){
            return true;
        }
        else{
            return false;
        }


    }

    public boolean verifyDeleteButton(String productName,String batchNumber){
        WebElement element=loginPage.getDriver ().findElement ( By.xpath ( "//table[@class='table table-striped']/tbody/tr/td//span[contains(normalize-space(),'"+productName+"')]//following-sibling::span[contains(text(),'"+batchNumber+"')]/../../../following-sibling::td//span[@class='actn-icn delete']" ) );
        loginPage.WaitTillElementIsPresent ( element );
        loginPage.waitForElementToBeClickable ( element );
        return checkWebElementIsDisplayed ( element );
    }

    public void clickOnDeleteButton(String productName,String batchNumber) {
        WebElement element = loginPage.getDriver ().findElement ( By.xpath ( "//table[@class='table table-striped']/tbody/tr/td//span[contains(normalize-space(),'" + productName + "')]//following-sibling::span[contains(text(),'" + batchNumber + "')]/../../../following-sibling::td//span[@class='actn-icn delete']" ) );
        loginPage.WaitTillElementIsPresent ( element );
        loginPage.waitForElementToBeClickable ( element );
        element.click ();
    }

    public boolean verifyTotalAmountPayableOfCart(){
        double totalNetAmountOfCart=0.00;
        double totalCouponValueOfCart=0.00;
        for(int i=0;i<listOfProductAddedInCart.size ();i++){
            String netAmount=listOfProductAddedInCart.get ( i ).findElement ( By.xpath ( "td[4]" ) ).getText ().trim ();
            String couponAmount=listOfProductAddedInCart.get ( i ).findElement ( By.xpath ( "td[5]" ) ).getText ().trim ();
            totalNetAmountOfCart=totalNetAmountOfCart+Double.valueOf (netAmount);
            totalCouponValueOfCart=totalCouponValueOfCart+Double.valueOf ( couponAmount );

        }
        WebElement element=loginPage.getDriver ().findElement ( By.xpath ( "//table[@class='table table-striped']/tfoot/tr/td/strong[text()='Total Amount Payable:']/../following-sibling::td[1]" ));
        double actualTotalAmtPayable=Double.valueOf (element.getText ());
        double expectedTotalAmtPayable=totalNetAmountOfCart-totalCouponValueOfCart;
        
        if(actualTotalAmtPayable==expectedTotalAmtPayable){
            return true;
        }
        else{
            return false;
        }

    }

    public String getCouponBasisOfAddedProduct(String productName,String batchNumber){
        WebElement element=loginPage.getDriver ().findElement ( By.xpath ( "//table[@class='table table-striped']/tbody/tr/td//span[contains(normalize-space(),'"+productName+"')]//following-sibling::span[contains(text(),'"+batchNumber+"')]/../../../following-sibling::td[5]" ) );
        loginPage.WaitTillElementIsPresent ( element );
        String couponBasis=element.getText ().trim ();
        return couponBasis;


    }

    public void increaseQtyOfAddedProduct(String productName,String batchNumber,int qtyToBeIncreased){
        WebElement element =loginPage.getDriver ().findElement ( By.xpath ( "//table[@class='table table-striped']/tbody/tr/td//span[contains(normalize-space(),'"+productName+"')]//following-sibling::span[contains(text(),'"+batchNumber+"')]/../../../following-sibling::td//span[contains(@class,'qtyBtns pos ng-scope')]" ) );
        for(int i =1;i<=qtyToBeIncreased;i++){
            loginPage.waitForElementToBeClickable ( element );
            element.click ();
        }

    }

    public void decreaseQtyOfAddedProduct(String productName,String batchNumber,int qtyToBeDecreased){
        WebElement element =loginPage.getDriver ().findElement ( By.xpath ( "//table[@class='table table-striped']/tbody/tr/td//span[contains(normalize-space(),'"+productName+"')]//following-sibling::span[contains(text(),'"+batchNumber+"')]/../../../following-sibling::td//span[@class='qtyBtns neg ng-scope']" ) );
        for(int i =1;i<=qtyToBeDecreased;i++){
           loginPage.waitForElementToBeClickable ( element );
           element.click ();
        }

    }

    public boolean verifyProductDeletedFromCartSucessMsg(){
        loginPage.WaitTillElementIsPresent ( productDeletedSuccessMsg );
        loginPage.waitForElementToBeClickable ( productDeletedSuccessMsg );
        return productDeletedSuccessMsg.isDisplayed ();

    }

    public int getNumberOfProductInCart(){
        int numberOfProductAddedInCart=Integer.valueOf (itemInCart.getText ().trim ());
        return numberOfProductAddedInCart;


    }

    public void selectGlobalClinicFromProductSale(){

    }

    public void autoSuggestByPatientName(String patientName,String patientMobileNumber){
        patientSelectionFromAutoSuggest (autoSuggest,patientName,patientMobileNumber );


    }
    public void autoSuggestByPatientMobile(String patientMobileNumber,String patientName){
        patientSelectionFromAutoSuggest (autoSuggest, patientMobileNumber,patientName );
    }

    public void autoSuggestByPatientId(String patientId,String patientName,String patientMobileNumber){
        String patientNameAndMobile=patientName+" : "+patientMobileNumber;
        patientSelectionFromAutoSuggest (autoSuggestByPatientId, patientId,patientNameAndMobile );

    }
    private void patientSelectionFromAutoSuggest(List<WebElement> autoSuggestList,String searchString,String otherDetails){
        loginPage.waitTillInvisiblityOfElement ( loader );
       loginPage.visibilityOfListLocated ( autoSuggestList );
       for(WebElement element:autoSuggestList){
           String text=element.findElement ( By.xpath ( "strong" ) ).getText ().toLowerCase ();
           Assert.assertTrue ( searchString.toLowerCase ().contains ( text ) );
       }
        WebElement matchOtherDetails=loginPage.getDriver ().findElement ( By.xpath ( "//span[contains(text(),'"+otherDetails+"')]" ) );
        loginPage.WaitTillElementIsPresent ( matchOtherDetails );
        loginPage.waitForElementToBeClickable ( matchOtherDetails );
        loginPage.hoverOnElement ( matchOtherDetails );
        matchOtherDetails.click ();

    }


    public boolean verifyConfirmationPopUpShownOnLeavingScreen(){
        final String expectedMsgOnPopUp="Product(s) added in cart will get removed, as they are not invoiced! Click cancel(X) to stay on the page?";
        loginPage.WaitTillElementIsPresent ( confirmationModal );
        loginPage.waitForElementToBeClickable ( confirmationModal );
        String actualMsgOnPopUp=confirmationModal.getText ().trim ();
        if(actualMsgOnPopUp.contains ( expectedMsgOnPopUp ) && checkWebElementIsDisplayed ( confirmationModal ) && checkWebElementIsDisplayed ( yesButtonOnConfirmationModal ) && checkWebElementIsDisplayed (cancelBtnOnConfirmationModal )){
            return true;
        }
        else{
            return false;
        }
    }



    public void clickOnCancelBtnOnPopUp(){
        loginPage.WaitTillElementIsPresent ( confirmationModal );
        loginPage.waitForElementToBeClickable ( cancelBtnOnConfirmationModal );
        cancelBtnOnConfirmationModal.click ();

    }

    public void clickOnYesBtnOnPopUp(){
        loginPage.WaitTillElementIsPresent ( confirmationModal );
        loginPage.waitForElementToBeClickable ( yesButtonOnConfirmationModal );
        yesButtonOnConfirmationModal.click ();
        loginPage.waitForSpinnerToDisappear();
    }

    public void clickOnGlobalSearchBtn(){
        loginPage.waitForElementToBeClickable ( globalSearchButton );
        globalSearchButton.click ();

    }

    public void clickOnGlobalClinicDrpDwn(){
        loginPage.waitForElementToBeClickable ( globalClinicDrpDwnBtn );
        globalClinicDrpDwnBtn.click ();

    }

    public boolean verifyLeftNavigationOnProductSale(){
        loginPage.waitTillInvisiblityOfElement ( loader );
        loginPage.waitForElementToBeClickable ( leftNavigation );
        return leftNavigation.isDisplayed ();

    }

    public boolean verifyPatientNameAndIdOnProductSale(String patientName,String patientId){
        loginPage.waitTillInvisiblityOfElement ( loader );
        loginPage.WaitTillElementIsPresent (  patientNameAndIdOnProductListing);
        String expectedPatientNameAndId=patientName+" : "+patientId;
        String actualPatientNameAndId=patientNameAndIdOnProductListing.getText ().trim ();
        if(actualPatientNameAndId.equalsIgnoreCase ( actualPatientNameAndId )){
            return true;
        }
        else{
            return false;
        }

    }

    public void verifyRemoveNearExpiryProductFromListing(){
        loginPage.visibilityOfListLocated ( listOfProduct );
        List<WebElement> expiryDateList=loginPage.getDriver ().findElements ( By.xpath ( "//div[contains(@class,'product-item')]/div[@class='product-info']/div[contains(@class,'batch_expiry_info')]/span[2]" ) );
        Date date =getDateFromCurrentDatePlusDays (remove_Near_expiry_product_from_listing );
        for(WebElement expiry:expiryDateList){
            String expiryDate=expiry.getText ().trim ().split ( " " )[1].trim ();
            SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yy");
            SimpleDateFormat sdf2 = new SimpleDateFormat("dd-MM-yyyy");
            try {
                Date expiryD=sdf1.parse ( expiryDate );
                expiryDate=sdf2.format ( expiryD );
                expiryD=sdf2.parse ( expiryDate );
                if(expiryD.after ( date )){
                    Assert.assertTrue ( true );
                }
                else{
                    Assert.assertTrue ( false );
                }
            } catch (ParseException e) {
                throw new RuntimeException ( e );
            }


        }

    }

    /*
    .Product Expiry Date lies between (Today Date +Product Highlight Start Day) And (Today Date +Product Highlight end Days )
     */
    public void verifyHighlightProductInListingBeforeExpiry(){
        loginPage.visibilityOfListLocated ( listOfProduct );
        List<WebElement> expiryDateList=loginPage.getDriver ().findElements ( By.xpath ( "//div[contains(@class,'product-item')]/div[@class='product-info']/div[contains(@class,'batch_expiry_info')]/span[2]" ) );
        Date productHighlightDate =getDateFromCurrentDatePlusDays (highlight_Products_In_Listing);
        Date stopProductHighlightDate=getDateFromCurrentDatePlusDays (stop_highlighting_Products_In_Listing);

        for(WebElement expiry:expiryDateList){
            String expiryDate=expiry.getText ().trim ().split ( " " )[1].trim ();
            SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yy");
            SimpleDateFormat sdf2 = new SimpleDateFormat("dd-MM-yyyy");
            try {
                Date expiryD=sdf1.parse ( expiryDate );
                expiryDate=sdf2.format ( expiryD );
                expiryD=sdf2.parse ( expiryDate );
                if(expiryD.after ( productHighlightDate ) && expiryD.before ( stopProductHighlightDate )){
                    boolean isHighlighted=expiry.findElement ( By.xpath ( "../../.." ) ).getAttribute ( "class" ).contains ( "item-highlight" );
                    Assert.assertTrue ( isHighlighted );
                }
                else{
                    boolean isHighlighted=expiry.findElement ( By.xpath ( "../../.." ) ).getAttribute ( "class" ).contains ( "item-highlight" );
                    Assert.assertFalse ( isHighlighted );
                }
            } catch (ParseException e) {
                throw new RuntimeException ( e );
            }

        }
    }
    private boolean checkWebElementIsDisplayed(WebElement element){
        loginPage.WaitTillElementIsPresent (element);
        return  element.isDisplayed ();
    }

    public void clickOnWebElement(WebElement element){
        loginPage.waitForElementToBeClickable ( element );
        element.click ();
    }

    public void enterText(WebElement element,String text){
        loginPage.waitForElementToBeClickable (  element);
        element.clear ();
        element.sendKeys ( text);
    }

    public void waitTillLoaderDisappear(){
        loginPage.waitTillInvisiblityOfElement ( loader );
    }

    private String getEnteredText(String id){
        JavascriptExecutor javascriptExecutor= (JavascriptExecutor) loginPage.getDriver ();
        String text=(String)javascriptExecutor.executeScript ( "return document.getElementById('"+id+"').value;" );
        return text;
    }

    private double getTwoDecimalPlacesValue(double d){

        return Math.round ( d*100.0 )/100.0;
    }

    public Date getDateFromCurrentDatePlusDays(int days){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date ());
        calendar.add(Calendar.DATE, days);
        Date datesAfterDaysLater = getDateInFormat (calendar.getTime(),"dd-MM-yyyy");
        return datesAfterDaysLater;


    }

    public Date getDateInFormat(Date date, String format){
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String dateString = sdf.format(date);
        try {
            return sdf.parse(dateString);
        } catch (ParseException e) {
            throw new RuntimeException ( e );
        }
    }

    public Date convertStringIntoDate(String date){
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        try {
            return sdf.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException ( e );
        }
    }





}
