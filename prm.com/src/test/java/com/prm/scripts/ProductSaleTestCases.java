package com.prm.scripts;

import com.prm.docdashboard.pageobject.*;
import com.prm.pageobjects.utils.ExtentReport;
import com.prm.pageobjects.utils.ReadConfig;
import com.prm.pageobjects.utils.SheetTest;
import com.prm.pageobjects.utils.TestData;
import com.prm.patientdashboard.pageobject.BasePatientLifeCyclePage;
import com.prm.patientdashboard.pageobject.InvoiceListingPage;
import com.prm.patientdashboard.pageobject.TreatmentPlanListingPage;
import com.prm.patientdashboard.pageobject.TreatmentPlansPage;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.IOException;
import java.lang.reflect.Method;
import java.security.GeneralSecurityException;
import java.util.Map;

public class ProductSaleTestCases {

    private LoginPage loginPage = new LoginPage();
    private DoctorDashBoard doctorDashboard = new DoctorDashBoard(loginPage);
    private ExtentReport extentReport=new ExtentReport (loginPage,"ProductSales TestCases");
    private ProductSalePage productSalePage=new ProductSalePage ( loginPage,extentReport );
    private BasePage basePage=new BasePage ( loginPage );
    private InvoiceListingPage invoiceListingPage=new InvoiceListingPage ( loginPage ,extentReport);
    private PatientRegistrationPage patientRegistrationPage=new PatientRegistrationPage ( loginPage );
    private PatientDashboardPage patientDashboardPage=new PatientDashboardPage ( loginPage );
    private TreatmentPlanListingPage treatmentPlanListingPage=new TreatmentPlanListingPage ( loginPage );
    private TreatmentPlansPage treatmentPlansPage=new TreatmentPlansPage ( loginPage );
    private BasePatientLifeCyclePage basePatientLifeCyclePage=new BasePatientLifeCyclePage ( loginPage );

    final String VERIFY_WEBELEMENT_ON_PRODUCT_LISTING="Verifying all the Field and button on product listing and product in product listing ";
    final String VERIFY_ERROR_ON_MANDATORY_FIELD="Verify the behaviour when mandatory field are not filled and verify error message shown";
    final String VERIFY_RESET_CANCEL_BUTTON_WORKING="Verify the redirection on click of cancel button and check on reset field are getting blank";
    final String VERIFY_PRODUCT_SEARCH="Verify product search by name and batch on Product listindt and matching the result according to the search";
    final String VERIFY_ADD_REMOVE_ITEM_ON_PRODUCT_LISTING="/*Verifying adding product to cart and removing some qty of added item */ Verify NetAmount,Unit COst,Coupon Value and Amt.Payables valuesremoving qty from product listing tab and then checking qty is also remove from cart */";
    final String VERIFY_ADD_REMOVE_ITEM_FROM_CART="Validating Increase /Decrease Qty of Add item and verifying the effect on Amounts";
    final String VERIFY_PRODUCT_REMOVE_HIGHLIGHT_BEFORE_EXPIRY="Verify Product with Expiry Near to Configured days is removed on product listing and Verify Product with Expiry Near to Configured days are Highlighted";
    final String VERIFY_EXISTING_PATIENT_AUTO_SUGGESTION="Verifying the Auto Suggestion when existing patient Name/Mobile Number/Patient Id is entered.";
    final String VERIFY_NEW_PATIENT_CREATION_FROM_PRODUCT_SALES="Creating New UnApproved patient from PRM and then checking patient is created by searching patient in the clinic Verifying UnApproved patient is created and checking Patient communication.";
    final String VERIFY_PRODUCT_SALES_FOR_EXISTING_PATIENT="Verifying Product Sales for Existing patient";
/*
 * Test Data From test.properties file
 */
    
    final String PRODUCT_SALE_CLINIC=TestData.getInstance().getInputData("product_sale_clinic");
    final String PATIENT_NAME=TestData.getInstance().getInputData("patient_name");
    final String PATIENT_MOBILE=TestData.getInstance().getInputData("patient_mobile");
    final String PATIENT_ID=TestData.getInstance().getInputData("patient_id");
    
    /*
     * Module and Sheet Name for getting Data from Google sheet
     */
    final String MODULE_NAME="Product Sale";
    final String GOOGLE_SHEET_NAME="ProductSaleTestData";
    final String Product_SALE_PAGE_TITLE="Product Sales";
    
    /*
     * Initalizing Map for storing Google Sheet data for the test
     */
    private Map<String ,String> productSaleTestData;
    private Map<String,String> productSalesTestData1;
   
    @BeforeClass
    public void beforeClass() throws GeneralSecurityException, IOException{
    	productSaleTestData=SheetTest.getDataFromGoogleSheet(MODULE_NAME, GOOGLE_SHEET_NAME, "A2", "J2");
    	for(String value:productSaleTestData.values()) {
    		System.out.println("********"+value);
    	}
        productSalesTestData1=SheetTest.getDataFromGoogleSheet(MODULE_NAME, GOOGLE_SHEET_NAME, "A3", "J3");
    	String username = ReadConfig.getInstance().getUsername().toString();
        String password = ReadConfig.getInstance().getPassword().toString();
        loginPage.login(username, password);
        doctorDashboard.doctorDashboardHomePage();
    }

    @BeforeMethod
    public void beforeMethod( Method method){
        extentReport.logger= extentReport.report.startTest ( method.getName () );
        basePage.backTODoctorDashboard ();
        doctorDashboard.doctorDashboardHomePage();
        extentReport.logger.log ( LogStatus.INFO,"Selecting Clinic from Global CLinic Dropdown on Doctor Dashboard" );
        basePage.selectClinicFrmHeader ( PRODUCT_SALE_CLINIC);
        extentReport.logger.log ( LogStatus.INFO,"Clicking on Product Sales button on Doctor Dashboard" );
        doctorDashboard.clickOnProductSales ();
        basePage.verifyPageTitle(Product_SALE_PAGE_TITLE);
//        Assert.assertEquals ( basePage.verification (),Product_SALE_PAGE_TITLE);
    }

    /*
    Validating all the Field and Button on Product Listing
    and validating Details show on individual product
     */
    @Test(description =VERIFY_WEBELEMENT_ON_PRODUCT_LISTING)
    public void verifyAllWebElementOnAddProductSale(){
        extentReport.logger.log ( LogStatus.INFO,"Checking productSale Tab is displayed");
        Assert.assertTrue ( productSalePage.isProductSaleTabDisplayed () );
        Assert.assertTrue ( productSalePage.verifyPatientNameField () );
        Assert.assertTrue ( productSalePage.verifyMobileNumberField () );
        Assert.assertTrue ( productSalePage.verifyPatientIdField () );
        Assert.assertTrue ( productSalePage.verifyPatientEmailIdField ());
        Assert.assertTrue ( productSalePage.checkSaveButton () );
        Assert.assertTrue ( productSalePage.checkCancelButton () );
        Assert.assertTrue ( productSalePage.checkResetButton () );
        Assert.assertTrue ( productSalePage.checkSearchProduct () );
        Assert.assertTrue (productSalePage.verifyProductListingData ());

    }

    /*
    Validating the Error Msg shown on Product Listing when mandatory field are not filled and user click on save button
     */
    @Test(description = VERIFY_ERROR_ON_MANDATORY_FIELD)
    public void verifyMandatoryFieldAndError(){
        productSalePage.clickOnSaveButton ();
        Assert.assertTrue (productSalePage.verifyEnterPatientNameErrorMsg());
        Assert.assertTrue ( productSalePage.verifyEnterPatientMobileNumberErrorMsg() );
        productSalePage.enterPatientName ( PATIENT_NAME);
        Assert.assertFalse ( productSalePage.verifyEnterPatientNameErrorMsg () );
        Assert.assertTrue ( productSalePage. verifyEnterPatientMobileNumberErrorMsg());
        productSalePage.enterMobileNumber (PATIENT_MOBILE );
        Assert.assertFalse ( productSalePage.verifyEnterPatientMobileNumberErrorMsg() );
        productSalePage.enterMobileNumber ( "KKDD837837" );
        Assert.assertTrue (productSalePage.verifyEnterValidMobileNumberErrMsg ());
        productSalePage.enterMobileNumber (PATIENT_MOBILE);
        Assert.assertFalse (productSalePage.verifyEnterValidMobileNumberErrMsg () );
        productSalePage.clickOnSaveButton ();
        productSalePage.enterPatientEmail ( "notanemail" );
        Assert.assertTrue (productSalePage.verifyEnterValidEmailErrorMsg ());
        productSalePage.enterPatientEmail ( "noemail@clovedental.info" );
        Assert.assertFalse ( productSalePage.verifyEnterValidEmailErrorMsg () );
        Assert.assertTrue ( productSalePage.verifyAddProductErrMsg() );
    }

/*
Verify the redirection on click of cancel button and checking on reset field are getting blank
 */
    @Test(description = VERIFY_RESET_CANCEL_BUTTON_WORKING)
    public void verifyResetAndCancelButtonWorking(){
        Assert.assertTrue ( productSalePage.checkCancelButton () );
        Assert.assertTrue ( productSalePage.checkResetButton () );
        productSalePage.enterPatientName ( PATIENT_NAME );
        productSalePage.enterMobileNumber ( PATIENT_MOBILE );
        productSalePage.enterPatientId ( PATIENT_ID);
        productSalePage.enterPatientEmail ( "noemail@clovedental.info" );
        productSalePage.clickOnResetButton ();
        Assert.assertEquals ( productSalePage.getPatientName (),"" );
        Assert.assertEquals ( productSalePage.getPatientMobile (),"" );
        Assert.assertEquals ( productSalePage.getPatientEmail (),"" );
        Assert.assertEquals ( productSalePage.getPatientId (),"" );
        productSalePage.clickOnCancelButton ();
        Assert.assertEquals ( basePage.verification (),"Doctor Dashboard" );

    }
/*
Verify product search by name and batch on Product listing and matching the result according to the search
 */
    @Test(description = VERIFY_PRODUCT_SEARCH)
    public void verifyProductSearch(){
        Assert.assertTrue ( productSalePage.checkSearchProduct ());
        productSalePage.enterSearchedDetails ( productSaleTestData.get("product_Name").split(" ")[0]);
        Assert.assertTrue (productSalePage.verifyProductSearchByName ( productSaleTestData.get("product_Name").split(" ")[0]));
        productSalePage.enterSearchedDetails (productSaleTestData.get("product_BatchNumber").substring(0,productSaleTestData.get("product_BatchNumber").length()-3));
        Assert.assertTrue (productSalePage.verifyProductSearchByBatchNumber (productSaleTestData.get("product_BatchNumber").substring(0,productSaleTestData.get("product_BatchNumber").length()-3)) );

    }
/*
Checking discount tag shown on item configured on product
 */
    @Test(description = "")
    public void verifyProductsAndDiscountOnProductListing(){
       Assert.assertTrue (productSalePage.verifyProductListingData ());
       Assert.assertEquals (productSalePage.verifyProductCompanyName ( productSaleTestData.get("product_Name"),productSaleTestData.get("product_BatchNumber")), productSaleTestData.get("product_Company"));
       Assert.assertTrue ( productSalePage.verifyProductName ( productSaleTestData.get("product_Name"),productSaleTestData.get("product_BatchNumber") ) );
       Assert.assertTrue ( productSalePage.verifyProductBatchNumber ( productSaleTestData.get("product_Name"),productSaleTestData.get("product_BatchNumber") ) );
       Assert.assertEquals ( productSalePage.verifyProductSize ( productSaleTestData.get("product_Name"),productSaleTestData.get("product_BatchNumber") ) , productSaleTestData.get("product_Size"));
       Assert.assertTrue( productSalePage.verifyProductOriginalPrice ( productSaleTestData.get("product_Name"),productSaleTestData.get("product_BatchNumber"),productSaleTestData.get("product_Original_Price")));
       Assert.assertTrue ( productSalePage.verifyProductPriceAfterDiscount ( productSaleTestData.get("product_Name"),productSaleTestData.get("product_BatchNumber"),5));
       Assert.assertTrue ( productSalePage. verifyQtyDiscountTagOnProduct ( productSaleTestData.get("product_Name"),productSaleTestData.get("product_BatchNumber"),1 ,5));
       productSalePage.addProductQtyToCart ( productSaleTestData.get("product_Name"),productSaleTestData.get("product_BatchNumber"),1 );
        Assert.assertTrue ( productSalePage. verifyQtyDiscountTagOnProduct ( productSaleTestData.get("product_Name"),productSaleTestData.get("product_BatchNumber"),2 ,15));
        productSalePage.addProductQtyToCart ( productSaleTestData.get("product_Name"),productSaleTestData.get("product_BatchNumber"),1 );
        Assert.assertTrue ( productSalePage.verifyProductOriginalPrice ( productSaleTestData.get("product_Name"),productSaleTestData.get("product_BatchNumber"), productSaleTestData.get("product_Original_Price")));
        Assert.assertTrue ( productSalePage.verifyProductPriceAfterDiscount ( productSaleTestData.get("product_Name"),productSaleTestData.get("product_BatchNumber"),15));
       Assert.assertTrue ( productSalePage.verifyToolTipOnProduct ( productSaleTestData.get("product_Name"),productSaleTestData.get("product_BatchNumber")) );
       Assert.assertTrue ( productSalePage.verifySpecialDiscountTagOnProduct ( productSaleTestData.get("product_Name"),productSaleTestData.get("product_BatchNumber"),productSaleTestData.get("discount_From_Date"),productSaleTestData.get("discount_To_Date") ) );
       productSalePage.clickOnEditProductTab ();
       productSalePage.clickOnDeleteButton ( productSaleTestData.get("product_Name"),productSaleTestData.get("product_BatchNumber") );
       
    }

    /*
    Verify No Product Found error msg found when there is no Product present in clinic
     */
    @Test
    public void verifyNoProductFoundErrMsg(){
        basePage.backTODoctorDashboard ();
        basePage.selectClinicFrmHeader ("Camp Clinic - Jaipur" );
        doctorDashboard.clickOnProductSales ();
        Assert.assertTrue ( productSalePage.noProductFoundErrMsg () );
    }

/*
Verifying adding product to cart and removing some qty of added item
*Verify NetAmount,Unit COst,Coupon Value and Amt.Payables values
removing qty from product listing tab and then checking qty is also remove from cart
 */
    @Test(description = VERIFY_ADD_REMOVE_ITEM_ON_PRODUCT_LISTING)
    public void verifyAddRemoveProductOnListing(){
         productSalePage.addProductQtyToCart ( productSaleTestData.get("product_Name"),productSaleTestData.get("product_BatchNumber"),2 );
         Assert.assertEquals ( productSalePage.getAddedQtyCountOnProduct ( productSaleTestData.get("product_Name"),productSaleTestData.get("product_BatchNumber") ),2 );
         Assert.assertTrue (productSalePage.verifyEditProductTab ());
         Assert.assertEquals ( productSalePage.getNumberOfProductInCart (),1 );
         Assert.assertTrue ( productSalePage.verifyAmtPayableOfProductAddedInCart() );
         productSalePage.removeProductQtyFromCart ( productSaleTestData.get("product_Name"),productSaleTestData.get("product_BatchNumber"),2 );
         Assert.assertEquals ( productSalePage.getAddedQtyCountOnProduct ( productSaleTestData.get("product_Name"),productSaleTestData.get("product_BatchNumber") ),0 );
        Assert.assertFalse (productSalePage.verifyEditProductTab ());
        Assert.assertFalse (productSalePage.verifyAmtPayableOfProductAddedInCart()  );

         productSalePage.addProductQtyToCart ( productSaleTestData.get("product_Name"),productSaleTestData.get("product_BatchNumber"),3 );
         Assert.assertEquals ( productSalePage.getNumberOfProductInCart (),1 );
         productSalePage.addProductQtyToCart (productSalesTestData1.get("product_Name"),productSalesTestData1.get("product_BatchNumber"),2 );
         Assert.assertEquals ( productSalePage.getNumberOfProductInCart (),2);
        Assert.assertTrue ( productSalePage.verifyAmtPayableOfProductAddedInCart() );
        
        productSalePage.clickOnEditProductTab ();
        Assert.assertTrue ( productSalePage.verifyColumnLabelOnProductCart () );
        Assert.assertTrue ( productSalePage.verifyDetailsOfProductInCart () );
        Assert.assertTrue ( productSalePage.verifyTotalAmountPayableOfCart () );

        productSalePage.clickOnProductSaleTab ();

        productSalePage.removeProductQtyFromCart (productSaleTestData.get("product_Name"),productSaleTestData.get("product_BatchNumber"),3 );
        Assert.assertEquals ( productSalePage.getAddedQtyCountOnProduct ( productSaleTestData.get("product_Name"),productSaleTestData.get("product_BatchNumber") ),0 );
        Assert.assertEquals ( productSalePage.getAddedQtyCountOnProduct (productSalesTestData1.get("product_Name"),productSalesTestData1.get("product_BatchNumber") ),2);
        Assert.assertEquals ( productSalePage.getNumberOfProductInCart (),1 );
        Assert.assertTrue ( productSalePage.verifyAmtPayableOfProductAddedInCart() );


         productSalePage.clickOnEditProductTab ();
         Assert.assertTrue ( productSalePage.verifyColumnLabelOnProductCart () );
         Assert.assertTrue ( productSalePage.verifyDetailsOfProductInCart () );
         Assert.assertTrue ( productSalePage.verifyTotalAmountPayableOfCart () );

        Assert.assertFalse ( productSalePage.checkAddedProductNameDisplayed( productSaleTestData.get("product_Name"),productSaleTestData.get("product_BatchNumber") ));
        Assert.assertFalse ( productSalePage.checkAddedProductBatchDisplayed( productSaleTestData.get("product_Name"),productSaleTestData.get("product_BatchNumber") ) );

        Assert.assertTrue ( productSalePage.checkAddedProductNameDisplayed(productSalesTestData1.get("product_Name"),productSalesTestData1.get("product_BatchNumber") ) );
        Assert.assertTrue ( productSalePage.checkAddedProductBatchDisplayed(productSalesTestData1.get("product_Name"),productSalesTestData1.get("product_BatchNumber") ) );


        productSalePage.clickOnProductSaleTab ();
        productSalePage.addProductQtyToCart ( productSaleTestData.get("product_Name"),productSaleTestData.get("product_BatchNumber"),3 );
        Assert.assertTrue ( productSalePage.verifyAmtPayableOfProductAddedInCart() );
        Assert.assertEquals ( productSalePage.getAddedQtyCountOnProduct ( productSaleTestData.get("product_Name"),productSaleTestData.get("product_BatchNumber") ),3 );
        Assert.assertEquals ( productSalePage.getAddedQtyCountOnProduct (productSalesTestData1.get("product_Name"),productSalesTestData1.get("product_BatchNumber") ),2 );

        productSalePage.clickOnEditProductTab ();
        Assert.assertTrue ( productSalePage.verifyColumnLabelOnProductCart () );
        Assert.assertTrue ( productSalePage.verifyDetailsOfProductInCart () );
        Assert.assertTrue ( productSalePage.verifyTotalAmountPayableOfCart () );

         Assert.assertTrue ( productSalePage.checkAddedProductNameDisplayed( productSaleTestData.get("product_Name"),productSaleTestData.get("product_BatchNumber") ) );
         Assert.assertTrue ( productSalePage.checkAddedProductBatchDisplayed( productSaleTestData.get("product_Name"),productSaleTestData.get("product_BatchNumber") ) );
         Assert.assertEquals ( productSalePage.checkAddedProductCompanyShortNameDisplayed ( productSaleTestData.get("product_Name"),productSaleTestData.get("product_BatchNumber") ),productSaleTestData.get("product_Company"));
         Assert.assertTrue ( productSalePage.checkAddedProductNameDisplayed(productSalesTestData1.get("product_Name"),productSalesTestData1.get("product_BatchNumber") ) );
         Assert.assertTrue ( productSalePage.checkAddedProductBatchDisplayed(productSalesTestData1.get("product_Name"),productSalesTestData1.get("product_BatchNumber") ) );
         Assert.assertEquals ( productSalePage.checkAddedProductCompanyShortNameDisplayed (productSalesTestData1.get("product_Name"),productSalesTestData1.get("product_BatchNumber") ),productSalesTestData1.get("product_Company"));

         productSalePage.clickOnProductSaleTab ();
         productSalePage.addProductQtyToCart (productSalesTestData1.get("product_Name"),productSalesTestData1.get("product_BatchNumber"),1 );
        Assert.assertEquals ( productSalePage.getAddedQtyCountOnProduct (productSalesTestData1.get("product_Name"),productSalesTestData1.get("product_BatchNumber") ),3 );
        Assert.assertTrue ( productSalePage.verifyAmtPayableOfProductAddedInCart() );

        productSalePage.clickOnEditProductTab ();
        Assert.assertTrue ( productSalePage.verifyDetailsOfProductInCart () );
        Assert.assertTrue ( productSalePage.verifyTotalAmountPayableOfCart () );
        Assert.assertEquals ( productSalePage.getAddedQtyOfProductOnListing (productSalesTestData1.get("product_Name"),productSalesTestData1.get("product_BatchNumber") ),3 );
        productSalePage.clickOnDeleteButton (productSaleTestData.get("product_Name"),productSaleTestData.get("product_BatchNumber"));
        productSalePage.clickOnDeleteButton ( productSalesTestData1.get("product_Name"),productSalesTestData1.get("product_BatchNumber"));

    }

    /*
    Increasing/Decreasing the item qty from cart tab and verifying Amounts values and then checking in Product Listing Page
    *Deleting added product from Cart and verifying Amount values after item remove from cart.
     */

    @Test(description = VERIFY_ADD_REMOVE_ITEM_FROM_CART)
    public void verifyAddRemoveProductOnCartTab(){
        productSalePage.addProductQtyToCart ( productSaleTestData.get("product_Name"),productSaleTestData.get("product_BatchNumber"),3 );
        Assert.assertTrue (productSalePage.verifyEditProductTab ());
        Assert.assertTrue ( productSalePage.verifyAmtPayableOfProductAddedInCart() );

        productSalePage.clickOnEditProductTab ();
        Assert.assertTrue ( productSalePage.checkAddedProductNameDisplayed( productSaleTestData.get("product_Name"),productSaleTestData.get("product_BatchNumber") ) );
        Assert.assertTrue ( productSalePage.checkAddedProductBatchDisplayed( productSaleTestData.get("product_Name"),productSaleTestData.get("product_BatchNumber") ) );
        Assert.assertTrue ( productSalePage.verifyDetailsOfProductInCart () );
        Assert.assertTrue ( productSalePage.verifyTotalAmountPayableOfCart () );
        Assert.assertEquals ( productSalePage.getAddedQtyOfProductOnListing ( productSaleTestData.get("product_Name"),productSaleTestData.get("product_BatchNumber") ),3 );

        productSalePage.increaseQtyOfAddedProduct ( productSaleTestData.get("product_Name"),productSaleTestData.get("product_BatchNumber"),2 );
        Assert.assertEquals ( productSalePage.getAddedQtyOfProductOnListing ( productSaleTestData.get("product_Name"),productSaleTestData.get("product_BatchNumber") ),5 );
        Assert.assertTrue ( productSalePage.verifyDetailsOfProductInCart () );
        Assert.assertTrue ( productSalePage.verifyTotalAmountPayableOfCart () );
        productSalePage.clickOnProductSaleTab ();
        Assert.assertEquals ( productSalePage.getAddedQtyCountOnProduct ( productSaleTestData.get("product_Name"),productSaleTestData.get("product_BatchNumber") ),5 );
        Assert.assertTrue ( productSalePage.verifyAmtPayableOfProductAddedInCart() );

        productSalePage.addProductQtyToCart (productSalesTestData1.get("product_Name"),productSalesTestData1.get("product_BatchNumber"),2 );
        productSalePage.clickOnEditProductTab ();
        Assert.assertTrue ( productSalePage.checkAddedProductNameDisplayed(productSalesTestData1.get("product_Name"),productSalesTestData1.get("product_BatchNumber") ) );
        Assert.assertEquals ( productSalePage.getAddedQtyOfProductOnListing (productSalesTestData1.get("product_Name"),productSalesTestData1.get("product_BatchNumber") ),2 );

        productSalePage.decreaseQtyOfAddedProduct ( productSaleTestData.get("product_Name"),productSaleTestData.get("product_BatchNumber"),2 );
        Assert.assertTrue ( productSalePage.checkAddedProductNameDisplayed(productSalesTestData1.get("product_Name"),productSalesTestData1.get("product_BatchNumber") ) );
        Assert.assertEquals ( productSalePage.getAddedQtyOfProductOnListing ( productSaleTestData.get("product_Name"),productSaleTestData.get("product_BatchNumber") ),3 );
        Assert.assertTrue ( productSalePage.verifyDetailsOfProductInCart () );
        Assert.assertTrue ( productSalePage.verifyTotalAmountPayableOfCart () );

        productSalePage.clickOnProductSaleTab ();
        Assert.assertEquals ( productSalePage.getAddedQtyCountOnProduct ( productSaleTestData.get("product_Name"),productSaleTestData.get("product_BatchNumber") ),3 );
        Assert.assertTrue ( productSalePage.verifyAmtPayableOfProductAddedInCart() );

        productSalePage.clickOnEditProductTab ();
        productSalePage.decreaseQtyOfAddedProduct (productSalesTestData1.get("product_Name"),productSalesTestData1.get("product_BatchNumber"),2 );
        Assert.assertTrue (productSalePage.verifyProductDeletedFromCartSucessMsg ());
        Assert.assertFalse ( productSalePage.checkAddedProductNameDisplayed(productSalesTestData1.get("product_Name"),productSalesTestData1.get("product_BatchNumber") ) );
        Assert.assertTrue ( productSalePage.verifyDetailsOfProductInCart () );
        Assert.assertTrue ( productSalePage.verifyTotalAmountPayableOfCart () );

        productSalePage.clickOnProductSaleTab ();
        Assert.assertTrue ( productSalePage.verifyAmtPayableOfProductAddedInCart() );
        Assert.assertFalse (  productSalePage.verifyProductSelectedOnListing (productSalesTestData1.get("product_Name"),productSalesTestData1.get("product_BatchNumber") ));

        productSalePage.clickOnEditProductTab ();
        productSalePage.clickOnDeleteButton ( productSaleTestData.get("product_Name"),productSaleTestData.get("product_BatchNumber") );
        Assert.assertTrue (productSalePage.verifyProductDeletedFromCartSucessMsg ());
        Assert.assertFalse ( productSalePage.verifyAmtPayableOfProductAddedInCart() );
        Assert.assertFalse (  productSalePage.verifyProductSelectedOnListing ( productSaleTestData.get("product_Name"),productSaleTestData.get("product_BatchNumber") ));

    }

    /*
    Creating New UnApproved patient from PRM and then checking patient is created by searching patient in the clinic
    Verifying UnApproved patient is created and checking Patient communication.
     */
    @Test(description =VERIFY_NEW_PATIENT_CREATION_FROM_PRODUCT_SALES )
    public void verifyNewPatientCreationThroughProductSales(){
        productSalePage.enterPatientName ( "Prashant kumar Rawat" );
        productSalePage.enterMobileNumber ( "1111111116" );
        productSalePage.enterPatientEmail ( "umesh.joshi@instantsys.com" );
        productSalePage.addProductQtyToCart ( productSalesTestData1.get("product_Name"),productSalesTestData1.get("product_BatchNumber"),1 );
        productSalePage.clickOnSaveButton ();
        basePage.verifyPageTitle("Invoice Listing");
//        invoiceListingPage.invoiceListTableColumnName();
//        invoiceListingPage.invoiceDataTable();
//        invoiceListingPage.actionsButton_InvoicesCreatedDate();
        invoiceListingPage.checkReceiptBtn ();
        basePatientLifeCyclePage.clickOnDashBoard ();
        Assert.assertEquals ( basePage.verification (),"Doctor Dashboard" );
        basePage.selectClinicFrmHeader ( PRODUCT_SALE_CLINIC);
        basePage.enterMobileNo ( "1111111116" );
        basePage.clickOnSearchBtn();
        basePage.clickOnPatient("1111111116", "Prashant kumar Rawat");
        Assert.assertEquals (  basePage.verification (),"Patient Registration");
        patientRegistrationPage.verifyEditPatientName ( "Prashant kumar Rawat" );
        patientRegistrationPage.verifyEditMobile ( "1111111116" );
        patientRegistrationPage.verifyEditEmail ( "umesh.joshi@instantsys.com" );
        Assert.assertTrue (patientRegistrationPage.checkSMSCheckBoxChecked ());
        Assert.assertFalse ( patientRegistrationPage.checkCallCheckBoxChecked () );
        Assert.assertTrue ( patientRegistrationPage.checkEmailCheckBoxChecked () );



    }

    /*
    Verifying Product Sale for existing Patient
    Verifying if patient is not present in clinic from where he/she is buying product then after product sale patient is getting mapped to that clinic or not
    Verifying if patient do not have email and user enter email from product sales>>Getting updated in patient profile.
     */
    @Test(description =VERIFY_PRODUCT_SALES_FOR_EXISTING_PATIENT)
    public void verifyProductSaleForExistingPatientWithoutEmail(){
        productSalePage.enterPatientName (PATIENT_NAME );
        productSalePage.enterMobileNumber (PATIENT_MOBILE );
        productSalePage.enterPatientEmail ( "noemail@clovedental.info" );
        productSalePage.addProductQtyToCart ( productSalesTestData1.get("product_Name"),productSalesTestData1.get("product_BatchNumber"),1 );
        productSalePage.clickOnSaveButton ();
        basePage.verifyPageTitle("Invoice Listing");
//        invoiceListingPage.invoiceListTableColumnName();
//        invoiceListingPage.invoiceDataTable();
//        invoiceListingPage.actionsButton_InvoicesCreatedDate();
        basePage.backTODoctorDashboard ();
        basePage.selectClinicFrmHeader ( PRODUCT_SALE_CLINIC);
        basePage.enterMobileNo (PATIENT_MOBILE );
        basePage.clickOnSearchBtn();
        basePage.clickOnPatient(PATIENT_MOBILE, PATIENT_NAME);
        Assert.assertEquals ( basePage.verification (),"Patient Dashboard" );
        if(patientDashboardPage.dueWarningPopup.size()>0){
            patientDashboardPage.hideDueWarningPopup();
        }else {
            basePatientLifeCyclePage.clickOnAlert();
        }
        patientDashboardPage.clickProfileEditBtn ();
        basePage.verifyPageTitle("Patient Registration");
        patientRegistrationPage.verifyEditEmail ( "noemail@clovedental.info" );
        Assert.assertTrue (patientRegistrationPage.checkSMSCheckBoxChecked ());
        Assert.assertTrue ( patientRegistrationPage.checkCallCheckBoxChecked () );
        Assert.assertTrue ( patientRegistrationPage.checkEmailCheckBoxDisable () );

    }

    /*
    Checking the Auto Suggestion when existing patient Name/Mobile Number/Patient Id is entered.
     */

    @Test(description = VERIFY_EXISTING_PATIENT_AUTO_SUGGESTION)
    public void verifyExistingPatientAutoSuggestOnProductSale (){
        productSalePage.enterPatientName (PATIENT_NAME.substring(0,PATIENT_NAME.length()-2) );
        productSalePage.autoSuggestByPatientName(PATIENT_NAME,PATIENT_MOBILE);
        Assert.assertTrue ( productSalePage.getPatientName ().equalsIgnoreCase ( PATIENT_NAME) );
        Assert.assertEquals ( productSalePage.getPatientMobile (),PATIENT_MOBILE );
        Assert.assertEquals ( productSalePage.getPatientId (),PATIENT_ID );
        Assert.assertEquals ( productSalePage.getPatientEmail (),"umesh.joshi@instantsys.com" );

        productSalePage.clickOnResetButton ();

        productSalePage.enterMobileNumber (PATIENT_MOBILE.substring(0,PATIENT_MOBILE.length()-4));
        productSalePage.autoSuggestByPatientMobile(PATIENT_MOBILE,PATIENT_NAME);
        Assert.assertTrue ( productSalePage.getPatientName ().equalsIgnoreCase ( PATIENT_NAME) );
        Assert.assertEquals ( productSalePage.getPatientMobile (),PATIENT_MOBILE );
        Assert.assertEquals ( productSalePage.getPatientId (),PATIENT_ID );
        Assert.assertEquals ( productSalePage.getPatientEmail (),"umesh.joshi@instantsys.com" );

        productSalePage.clickOnResetButton ();
        productSalePage.enterPatientId (PATIENT_ID.substring(0,PATIENT_ID.length()-1));
        productSalePage.autoSuggestByPatientId (PATIENT_ID,PATIENT_NAME,PATIENT_MOBILE);
        Assert.assertTrue ( productSalePage.getPatientName ().equalsIgnoreCase ( PATIENT_NAME) );
        Assert.assertEquals ( productSalePage.getPatientMobile (),PATIENT_MOBILE );
        Assert.assertEquals ( productSalePage.getPatientId (),PATIENT_ID );
        Assert.assertEquals ( productSalePage.getPatientEmail (),"umesh.joshi@instantsys.com" );

    }

    /*
    Verify when user have added some item to cart and user try to exit screen without creating the invoice of product added in cart
     */
    @Test(priority = 1)
    public void verifyConfirmationPopupShownOnExitingFromScreen(){
        productSalePage.enterPatientName (PATIENT_NAME);
        productSalePage.autoSuggestByPatientMobile(PATIENT_NAME,PATIENT_MOBILE);
        productSalePage.addProductQtyToCart (productSalesTestData1.get("product_Name"),productSalesTestData1.get("product_BatchNumber") ,1);
        productSalePage.clickOnCancelButton ();
        Assert.assertTrue (productSalePage.verifyConfirmationPopUpShownOnLeavingScreen ());
        productSalePage.clickOnCancelBtnOnPopUp ();
        basePage.verifyPageTitle(Product_SALE_PAGE_TITLE);
        productSalePage.clickOnCancelButton ();
        Assert.assertTrue (productSalePage.verifyConfirmationPopUpShownOnLeavingScreen ());
        productSalePage.clickOnYesBtnOnPopUp ();
        basePage.verifyPageTitle("Doctor Dashboard" );


        doctorDashboard.clickOnProductSales ();
        productSalePage.enterPatientName (PATIENT_NAME );
        productSalePage.autoSuggestByPatientMobile(PATIENT_NAME,PATIENT_MOBILE);
        productSalePage.addProductQtyToCart (productSalesTestData1.get("product_Name"),productSalesTestData1.get("product_BatchNumber") ,1);
        productSalePage.clickOnGlobalSearchBtn ();
        Assert.assertTrue (productSalePage.verifyConfirmationPopUpShownOnLeavingScreen ());
        productSalePage.clickOnYesBtnOnPopUp ();
        basePage.verifyPageTitle("Patient List" );

        basePage.backTODoctorDashboard ();
        doctorDashboard.clickOnProductSales ();
        productSalePage.enterPatientName (PATIENT_NAME);
        productSalePage.autoSuggestByPatientMobile(PATIENT_NAME,PATIENT_MOBILE);
        productSalePage.addProductQtyToCart (productSalesTestData1.get("product_Name"),productSalesTestData1.get("product_BatchNumber") ,1);
        basePage.backTODoctorDashboard ();
        Assert.assertTrue (productSalePage.verifyConfirmationPopUpShownOnLeavingScreen ());
        productSalePage.clickOnYesBtnOnPopUp ();
        basePage.verifyPageTitle("Doctor Dashboard" );


        doctorDashboard.clickOnProductSales ();
        productSalePage.enterPatientName (PATIENT_NAME);
        productSalePage.autoSuggestByPatientMobile(PATIENT_NAME,PATIENT_MOBILE);
        productSalePage.addProductQtyToCart (productSalesTestData1.get("product_Name"),productSalesTestData1.get("product_BatchNumber") ,1);
        basePage.clickOnlogOut ();
        Assert.assertTrue (productSalePage.verifyConfirmationPopUpShownOnLeavingScreen ());
        productSalePage.clickOnCancelBtnOnPopUp ();
        productSalePage.clickOnEditProductTab ();
        productSalePage.clickOnDeleteButton (productSalesTestData1.get("product_Name"),productSalesTestData1.get("product_BatchNumber"));
//        productSalePage.clickOnYesBtnOnPopUp ();
//        Assert.assertEquals ( basePage.verification (),"Clove Dental :: Login Access To Clinical View" );


    }

    /*
    Verify Product with Expiry Near to Configured days is removed on product listing
    Verify Product with Expiry Near to Configured days are Highlighted
     */
    @Test(description = VERIFY_PRODUCT_REMOVE_HIGHLIGHT_BEFORE_EXPIRY)
    public void verifyProductHighlightBeforeExpiryDate(){
        productSalePage.verifyRemoveNearExpiryProductFromListing();
        productSalePage.verifyHighlightProductInListingBeforeExpiry();
    }

/*
Verify Error Msg shown when No Qty of item is added and user click on minus button
Verify Error Msg shown when user has already the max available qty.
 */
    @Test
    public void verifyMinMaxQtyErrorOnProductListing(){
        productSalePage.removeProductQtyFromCart (productSalesTestData1.get("product_Name"),productSalesTestData1.get("product_BatchNumber"),1  );
        Assert.assertTrue (productSalePage.verifyZeroQuantityErrorMsg ());
        productSalePage.addProductQtyToCart ( productSalesTestData1.get("product_Name"),productSalesTestData1.get("product_BatchNumber"),Integer.valueOf(productSalesTestData1.get("available_Qty") ));
        Assert.assertTrue ( productSalePage.verifyMaxQtyAddedErrorMsg (productSalesTestData1.get("available_Qty") ) );
        Assert.assertTrue ( productSalePage.verifyPlusIconOnAddingMaxQtyOnListing ( productSalesTestData1.get("product_Name"),productSalesTestData1.get("product_BatchNumber") ) );
        productSalePage.clickOnEditProductTab ();
        productSalePage.increaseQtyOfAddedProduct ( productSalesTestData1.get("product_Name"),productSalesTestData1.get("product_BatchNumber"),1 );
        Assert.assertTrue ( productSalePage.verifyMaxQtyAddedErrorMsg (productSalesTestData1.get("available_Qty")) );
        Assert.assertTrue ( productSalePage.verifyPlusIconOnAddingMaxQtyOnCart ( productSalesTestData1.get("product_Name"),productSalesTestData1.get("product_BatchNumber") ) );
        productSalePage.clickOnDeleteButton ( productSalesTestData1.get("product_Name"),productSalesTestData1.get("product_BatchNumber") );
    }

    /*
    Verifying Product Sales Button Within Patient Dashboard and Redirection to Product Sale on click
     */
    @Test
    public void verifyRedirectionToProductListingFromPatientDashboardScreen(){
        basePage.backTODoctorDashboard ();
        basePage.selectClinicFrmHeader ( PRODUCT_SALE_CLINIC);
        basePage.enterMobileNo   (PATIENT_MOBILE);
        basePage.clickOnSearchBtn();
        basePage.clickOnPatient(PATIENT_MOBILE,PATIENT_NAME);
        if(patientDashboardPage.dueWarningPopup.size()>0){
            patientDashboardPage.hideDueWarningPopup();
        }else {
            basePatientLifeCyclePage.clickOnAlert();
        }
        Assert.assertEquals ( basePage.verification (),"Patient Dashboard" );
        patientDashboardPage.clickOnTreatmentPlanListBtn ();
        Assert.assertTrue ( patientDashboardPage.verifyProductSalesButton () );
        patientDashboardPage.clickOnProductSaleButton ();
        Assert.assertEquals ( basePage.verification (),Product_SALE_PAGE_TITLE);
        Assert.assertTrue (productSalePage.verifyPatientNameAndIdOnProductSale(PATIENT_NAME,PATIENT_ID) );
        Assert.assertTrue ( productSalePage.verifyLeftNavigationOnProductSale () );
        basePatientLifeCyclePage.openCloseLeftNavigator();
        basePatientLifeCyclePage.webElementOfLeftNavigator();
        basePatientLifeCyclePage.openCloseLeftNavigator();
        productSalePage.clickOnCancelButton ();
        Assert.assertEquals ( basePage.verification (),"Treatment Plan Listing" );
        basePatientLifeCyclePage.clickOnAddNewBtn ();
        Assert.assertTrue ( patientDashboardPage.verifyProductSalesButton () );
        patientDashboardPage.clickOnProductSaleButton ();
        Assert.assertEquals ( basePage.verification (),Product_SALE_PAGE_TITLE);
        Assert.assertTrue (productSalePage.verifyPatientNameAndIdOnProductSale(PATIENT_NAME,PATIENT_ID) );
        Assert.assertTrue ( productSalePage.verifyLeftNavigationOnProductSale () );
        productSalePage.clickOnCancelButton ();
        Assert.assertEquals ( basePage.verification (),"Treatment Plans" );
        basePatientLifeCyclePage.clickOnDashBoard ();
        patientDashboardPage.clickOnInvoiceList ();
        Assert.assertEquals ( basePage.verification (),"Invoice Listing" );
        Assert.assertTrue ( patientDashboardPage.verifyProductSalesButton () );
        patientDashboardPage.clickOnProductSaleButton ();
        Assert.assertEquals ( basePage.verification (),Product_SALE_PAGE_TITLE);
        Assert.assertTrue (productSalePage.verifyPatientNameAndIdOnProductSale(PATIENT_NAME,PATIENT_ID) );
        Assert.assertTrue ( productSalePage.verifyLeftNavigationOnProductSale () );
        productSalePage.clickOnCancelButton ();
        Assert.assertEquals ( basePage.verification (),"Invoice Listing" );

    }

    @AfterMethod(enabled = true)
    public void statusOfScripts( ITestResult result) {
        extentReport.onTestFailure(result);
        basePage.backTODoctorDashboard ();
    }

    @AfterClass
    public void tearDown() {
        extentReport.report.endTest(extentReport.logger);
        extentReport.report.flush();
        // extentReport.report.close();
        basePage.clickOnlogOut();
        loginPage.close();
    }





}
