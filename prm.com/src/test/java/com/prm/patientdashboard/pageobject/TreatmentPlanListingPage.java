package com.prm.patientdashboard.pageobject;

import com.prm.pageobjects.utils.PCDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.lang.Float;

/**
 * 
 * @author Ajit Yadav
 *
 */
public class TreatmentPlanListingPage {
	private PCDriver loginPage;

	public TreatmentPlanListingPage(PCDriver loginPage) {
		this.loginPage = loginPage;
		PageFactory.initElements(loginPage.getDriver(), this);
	}

	@FindBy(xpath = "//h1[contains(@class,'btmTitle pull-left')]")
	private WebElement headerOfPage;
	@FindBy(xpath = "//div[contains(@class,'no_record_found_dir')]//i[text()='No Record Found!']")
	private WebElement noRecordMsg;
	@FindBy(xpath = "(//div[@class='fnt_avgR dateClnc']/span)[1]")
	private WebElement DateInTreatmentList;
	@FindBy(xpath = "//table[@id='treatmentlistingtable']//th[text()='WD']")
	private WebElement wdMainList;
	@FindBy(xpath = "//table[@id='treatmentlistingtable']//th[text()='Source']")
	private WebElement sourceMainList;
	@FindBy(xpath = "//table[@id='treatmentlistingtable']//th[text()='Unit Cost']")
	private WebElement unitCostMainList;
	@FindBy(xpath = "//table[@id='treatmentlistingtable']//th[text()='Net Amt.']")
	private WebElement netAmtMainList;
	@FindBy(xpath = "//table[@id='treatmentlistingtable']//th[text()='Cpn. Value ']")
	private WebElement cpnValueMainList;
	@FindBy(xpath = "//table[@id='treatmentlistingtable']//th[text()='Amt. Pay.']")
	private WebElement amtPayMainList;
	@FindBy(xpath = "//table[@id='treatmentlistingtable']//th[text()='Created By']")
	private WebElement createdByMainList;
	@FindBy(xpath = "//table[@id='treatmentlistingtable']//th[text()='Cpn. Basis']")
	private WebElement cpnBasisByMainList;

	// -------webelement of view popup in treatment listing-
	@FindBy(id = "myModalLabel")
	private WebElement headerViewPopup;
	@FindBy(xpath = "//h4[contains(text(),'Treatment Plan View')]/..//button[@class='close']")
	private WebElement closeBtnViewPopup;

	// ------webelement in doctor list popup
	@FindBy(id = "myModalLabel")
	private WebElement headerOfDoctorLsit;
	@FindBy(xpath = "//h4[@id='myModalLabel']/preceding-sibling::div//span[@class='cmnicons sv-mdl']")
	private WebElement saveBtnInDoctorList;
	@FindBy(xpath = "//span[@class='cmnicons yes-mdl']")
	private WebElement yesBtnForDelete;
	@FindBy(xpath = "//span[contains(text(),'Treatment plan deleted successfully!')]")
	private WebElement deleteSuccessMsg;
	@FindBy(xpath = "//span[contains(text(),'Please select treatment(s) for starting Workdone')]")
	private WebElement selectTreatmentMessage;
	@FindBy(xpath = "//span[text()='Treatment(s) could not be started. Please update patient Covid-19 Declaration!']")
	private List<WebElement> covidUpdateAlert;
	@FindBy(xpath = "//span[@class='cmnicons ccAdd']")
	private WebElement addNewBtn;
	@FindBy(xpath = "//span[contains(text(),'Treatment plan copied successfully!')]")
	private WebElement copyPlanAlrtTxt;

	public void setCovidUpdateAlert(){
		loginPage.waitForPageLoad();
		try {
			Thread.sleep(100);
			Assert.assertTrue(covidUpdateAlert.size()==1||loginPage.getTitle().contains("Treatment Plan Listing"));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public void checkedDataNameMainList() {
		loginPage.waitForPageLoad();
		Assert.assertTrue(checkedWebElementDisplayed(wdMainList) && checkedWebElementDisplayed(sourceMainList)
				&& checkedWebElementDisplayed(unitCostMainList) && checkedWebElementDisplayed(netAmtMainList)
				&& checkedWebElementDisplayed(cpnValueMainList) && checkedWebElementDisplayed(amtPayMainList)
				&& checkedWebElementDisplayed(createdByMainList) && checkedWebElementDisplayed(cpnBasisByMainList));
	}

	public void verifyHeaderOfPage(String header) {
//		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(headerOfPage);
		Assert.assertTrue(headerOfPage.getText().contains(header));
	}

	public void verifyNoRecordMsg() {
		loginPage.waitForPageLoad();
		try {
			Thread.sleep(3000);
			Assert.assertTrue(checkedWebElementDisplayed(noRecordMsg));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void selectedTreatmentInTreatmentList(String fullName,String companyName) {
		loginPage.waitForPageLoad();
		WebElement web, checkBox, sourceName;
		List<WebElement> unitCostNetAmtCpnValueBasisAmtPay, creatByRemarks;
		web = loginPage.getDriver().findElement(By.xpath("//span[contains(text(),'" + fullName + "')]"));
		String string = loginPage.getDriver().findElement(By.xpath("//span[contains(text(),'"+fullName+"')]//span[contains(@class,'company_txt')]")).getText().trim();
		String[] excat_companyName = string.split(" ");
		checkBox = loginPage.getDriver()
				.findElement(By.xpath("//span[contains(text(),'" + fullName + "')]/../preceding-sibling::td//label"));
		sourceName = loginPage.getDriver().findElement(By.xpath("//span[contains(text(),'" + fullName
				+ "')]/../preceding-sibling::td[contains(@class,'wrd-brk-wrd')]"));
		unitCostNetAmtCpnValueBasisAmtPay = loginPage.getDriver()
				.findElements(By.xpath("//span[contains(text(),'" + fullName + "')]/../following-sibling::td/span"));
		creatByRemarks = loginPage.getDriver().findElements(
				By.xpath("//span[contains(text(),'" + fullName + "')]/../following-sibling::td[@class='ng-binding']"));
		Assert.assertTrue(checkedWebElementDisplayed(web) && checkedWebElementDisplayed(checkBox)
				&& checkedWebElementDisplayed(sourceName));
		Assert.assertEquals(excat_companyName[1], companyName);
		for (int i = 0; unitCostNetAmtCpnValueBasisAmtPay.size() > i; i++) {
			Assert.assertTrue(!(unitCostNetAmtCpnValueBasisAmtPay.get(i).getText() == null));
		}
		for (int j = 0; creatByRemarks.size() > j; j++) {
			Assert.assertTrue(!(creatByRemarks.get(j).getText() == null));
		}
	}

	public void afterCopyTreatmentInMainList(String fullName) {
		loginPage.waitForElementToDisappear((By.xpath("//span[contains(text(),'Treatment plan copied successfully!'])")));
		List<WebElement> web = loginPage.getDriver().findElements(By.xpath("//span[contains(text(),'" + fullName + "')]"));
		loginPage.visibilityOfListLocated(web);
		for(int i=0;i<web.size();i++) {
			boolean result = (web.size() == 2);
			Assert.assertTrue(result);
		}
	}
	
	public void verifyDatesTreatmentPlanList() {
		loginPage.waitForModalOverlayToDisappear();
		loginPage.waitForSpinnerToDisappear();
		loginPage.waitForElementToBeClickable(DateInTreatmentList);
		String str = DateInTreatmentList.getText();
		SimpleDateFormat date = new SimpleDateFormat("dd-MM-yy");
		Date dat = new Date();
		String currentDate = date.format(dat);
		Assert.assertTrue(str.contains(currentDate));
	}

//	public void verifyDatesTreatmentPlanList() {
//		loginPage.waitForPageLoad();
//		try {
//			Thread.sleep(2500);
//			loginPage.waitForElementToBeClickable(DateInTreatmentList);
//			String str = DateInTreatmentList.getText();
//			SimpleDateFormat date = new SimpleDateFormat("dd-MM-yy");
//			Date dat = new Date();
//			String currentDate = date.format(dat);
//			Assert.assertTrue(str.contains(currentDate));
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

	public void verifyCopyBtn(String currentDate) {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(DateInTreatmentList);
		WebElement web = loginPage.getDriver().findElement(By.xpath(
				"//span[text()='"+currentDate+"']/../following-sibling::div//span[@class='trtNewIcons trt_copy_icn']/following-sibling::span[text()='Copy']"));
		Assert.assertTrue(web.isDisplayed());
	}
	
	public void clickOnCopyBtn(String currentDate) {
		loginPage.waitForElementToBeClickable(DateInTreatmentList);
			WebElement web = loginPage.getDriver().findElement(By.xpath(
			"//span[text()='"+currentDate+"']/../following-sibling::div//span[@class='trtNewIcons trt_copy_icn']/following-sibling::span[text()='Copy']"));
			web.click();
			boolean isSuccMsgDisplayed = copyPlanAlrtTxt.isDisplayed();
			Assert.assertEquals(true, isSuccMsgDisplayed);
	}

//	public void clickOnCopyBtn(String currentDate) {
//		loginPage.waitForPageLoad();
//		loginPage.waitForElementToBeClickable(DateInTreatmentList);
//		try {
//			Thread.sleep(1500);
//			WebElement web = loginPage.getDriver().findElement(By.xpath(
//					"//span[text()='"+currentDate+"']/../following-sibling::div//span[@class='trtNewIcons trt_copy_icn']/following-sibling::span[text()='Copy']"));
//			web.click();
//			Thread.sleep(1500);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//	}

	public void verifyStartBtn(String currentDate) {
		loginPage.waitForSpinnerToDisappear();
		loginPage.waitForElementVisibility(addNewBtn, 3000);
		WebElement web = loginPage.getDriver().findElement(By.xpath("//span[text()='"+currentDate+"']/../..//span[@class='trtNewIcons trt_book_icn']"));
		Assert.assertTrue(web.isDisplayed());
	} 

	public void clickOnStartBtn(String currentDate) {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(DateInTreatmentList);
		try {
			Thread.sleep(3000);
			WebElement web = loginPage.getDriver().findElement(By.xpath("//span[text()='"+currentDate+"']/../following-sibling::div//span[@class='trtNewIcons trt_strt_icn']/following-sibling::span[text()='Start']"));
			web.click();
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
					
	public void checkStartBtnNotPresent(String currentDate) {
		loginPage.waitForPageLoad();
		List<WebElement> web = loginPage.getDriver().findElements(By.xpath("//span[text()='" + currentDate + "']/../following-sibling::div//span[@class='trtNewIcons trt_strt_icn']/following-sibling::span[text()='Start']"));
		Assert.assertTrue(web.size() == 0);
	}

	public void verifyMarkBtn(String currentDate) {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(DateInTreatmentList);
		WebElement web = loginPage.getDriver().findElement(
				By.xpath("//span[text()='"+currentDate+"']/../following-sibling::div//span[@class='trtNewIcons trt_book_icn']/following-sibling::span[text()='Mark']"));
		Assert.assertTrue(web.isDisplayed());
	}

	public void clickOnMarkBtn(String currentDate) {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(DateInTreatmentList);
		WebElement web = loginPage.getDriver().findElement(
				By.xpath("//span[text()='"+currentDate+"']/../following-sibling::div//span[@class='trtNewIcons trt_book_icn']/following-sibling::span[text()='Mark']"));
		web.click();
	}

	public void verifyViewBtn(String currentDate) {
		loginPage.waitForElementToBeClickable(DateInTreatmentList);
		WebElement web = loginPage.getDriver().findElement(By.xpath(
				"//span[text()='"+currentDate+"']/../../..//td[@class='wrd-brk-wrd ng-binding']/../../../../..//span[contains(text(),'View')]"));
		Assert.assertTrue(web.isDisplayed());
	}
	
	public void clickOnViewBtn(String currentDate) {
		loginPage.waitForModalOverlayToDisappear();
		loginPage.waitForElementVisibility(addNewBtn, 4000);
		WebElement web = loginPage.getDriver().findElement(By.xpath(
				"//span[text()='"+currentDate+"']/../../..//td[@class='wrd-brk-wrd ng-binding']/../../../../..//span[contains(text(),'View')]"));
    		web.click();
    		loginPage.waitForElementVisibility(closeBtnViewPopup,4000);
	}

//	public void clickOnViewBtn(String currentDate) {
//		loginPage.waitForPageLoad();
//		try {
//			Thread.sleep(2000);
//			WebElement web = loginPage.getDriver().findElement(By.xpath(
//					"//span[text()='"+currentDate+"']/../following-sibling::div//span[@class='actn-icn view']/following-sibling::span[text()='View']"));
//			web.click();
//			Thread.sleep(5000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

	public void verifyEditBtn(String currentDate) {
		loginPage.waitForElementVisibility(addNewBtn, 4000);
		WebElement web = loginPage.getDriver().findElement(By.xpath(
						"//span[text()='"+currentDate+"']/../../..//td[@class='wrd-brk-wrd ng-binding']/../../../../..//span[contains(text(),'Edit')]"));
		Assert.assertTrue(web.isDisplayed());
	}
	
	public void clickOnEditBtn(String currentDate) {
		loginPage.waitForModalOverlayToDisappear();
		loginPage.waitForElementVisibility(addNewBtn, 4000);
        loginPage.waitForElementVisibility(DateInTreatmentList,4000);
        	WebElement web = loginPage.getDriver().findElement(By.xpath(
					"//span[text()='"+currentDate+"']/../../..//td[@class='wrd-brk-wrd ng-binding']/../../../../..//span[contains(text(),'Edit')]"));
			loginPage.waitForElementToBeClickable(web);
			web.click();	
         	loginPage.waitForSpinnerToDisappear();
	}
	


//	public void clickOnEditBtn(String currentDate) {
//		loginPage.waitForPageLoad();
//		try {
//			Thread.sleep(4000);
//			loginPage.waitForElementToBeClickable(DateInTreatmentList);
//			WebElement web = loginPage.getDriver().findElement(By.xpath(
//					"//span[text()='"+currentDate+"']/../following-sibling::div//span[@class='actn-icn edit']/following-sibling::span[text()='Edit']"));
//
//			web.click();
//			Thread.sleep(4000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

	public void verifyDeleteBtn(String currentDate) {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(DateInTreatmentList);
		WebElement web = loginPage.getDriver().findElement(By.xpath(
				"//span[text()='"+currentDate+"']/../following-sibling::div//span[@class='actn-icn delete']/following-sibling::span[text()='Delete']"));
		Assert.assertTrue(web.isDisplayed());
	}

	public void clickOnDeleteBtn(String currentDate) {
		
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(DateInTreatmentList);
		WebElement web = loginPage.getDriver().findElement(By.xpath(
				"//span[text()='"+currentDate+"']/../following-sibling::div//span[@class='actn-icn delete']/following-sibling::span[text()='Delete']"));
		web.click();
	}

	public void verifyPrintBtn(String currentDate) {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(DateInTreatmentList);
		WebElement web = loginPage.getDriver().findElement(By.xpath(
				"//span[text()='"+currentDate+"']/../following-sibling::div//span[@class='actn-icn print']/following-sibling::span[text()='Print']"));
		Assert.assertTrue(web.isDisplayed());
	}

	public void clickOnPrintBtn(String currentDate) {
		loginPage.waitForModalOverlayToDisappear();
		loginPage.waitForSpinnerToDisappear();
		loginPage.waitForElementVisibility(DateInTreatmentList, 4000);
		loginPage.waitForElementToBeClickable(DateInTreatmentList);
		WebElement web = loginPage.getDriver().findElement(By.xpath(
				"//span[text()='"+currentDate+"']/../following-sibling::div//span[@class='actn-icn print']/following-sibling::span[text()='Print']"));
		web.click();
	}

	public void verifyHeaderInViewPopup(String header) {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(headerViewPopup);
		Assert.assertTrue(headerViewPopup.getText().contains(header));
	}

	public void verifyTreatmentInViewPopup(String treatment) {
		loginPage.waitForSpinnerToDisappear();
	    loginPage.waitForElementVisibility(closeBtnViewPopup,4000); 
		WebElement web = loginPage.getDriver().findElement(By.xpath("//td[contains(text(),'" + treatment + "')]"));
		Assert.assertTrue(web.isDisplayed());
	}
	
	public void clickOnCloseBtnViewPopup() {
	    loginPage.waitForElementVisibility(closeBtnViewPopup,4000); 
		loginPage.waitForElementToBeClickable(closeBtnViewPopup);
		closeBtnViewPopup.click();
		loginPage.waitForModalOverlayToDisappear();
		loginPage.waitForPageToBecomeActive();
}

//	public void clickOnCloseBtnViewPopup() {
//		try {
//			Thread.sleep(3000);
//			loginPage.waitForElementToBeClickable(closeBtnViewPopup);
//			closeBtnViewPopup.click();
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//	}

	public void verifyAfterDeleteInTreatmentList(String treatment) {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(headerOfPage);
		Assert.assertTrue(loginPage.getDriver().findElement(By.xpath("//span[contains(text(),'" + treatment + "')]/../.."))
				.getAttribute("class").contains("deleted"));
	}

	public void checkNotDeletedTreatment(String treatment) {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(headerOfPage);
		Assert.assertTrue(!loginPage.getDriver().findElement(By.xpath("//span[contains(text(),'" + treatment + "')]/../.."))
				.getAttribute("class").contains("deleted"));
	}

	public void verifyAfterDeleteInTreatmentListViewPopup(String treatment) {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(headerViewPopup);
		Assert.assertTrue(loginPage.getDriver().findElement(By.xpath("//td[contains(text(),'" + treatment + "')]/.."))
				.getAttribute("class").contains("deleted"));
	}

	public void notDeletedTreatmentInView(String treatment) {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(headerViewPopup);
		Assert.assertTrue(!loginPage.getDriver().findElement(By.xpath("//td[contains(text(),'" + treatment + "')]/.."))
				.getAttribute("class").contains("deleted"));
	}

	public void verifyAfterMarked(String treatment) {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(headerOfPage);
		Assert.assertTrue(loginPage.getDriver().findElement(By.xpath(
				"//span[contains(text(),'" + treatment + "')]/preceding-sibling::span[@popover-trigger='mouseenter']"))
				.isDisplayed());
	}

	public void couponDiscountInViewPopup(String fullname) {
		loginPage.waitForSpinnerToDisappear();
		loginPage.waitForElementVisibility(closeBtnViewPopup,4000);
		loginPage.waitForPageLoad();
		WebElement netAmount = loginPage.getDriver().findElement(By.xpath("(//td[contains(text(),'" + fullname
				+ "')]/following-sibling::td/span[contains(@ng-class,'trviews.Treatment.fee')])[1]"));
		loginPage.waitForElementToBeClickable(netAmount);
		String actualAmount = netAmount.getText();
		WebElement payAmount = loginPage.getDriver().findElement(By.xpath("(//td[contains(text(),'" + fullname
				+ "')]/following-sibling::td/span[contains(@ng-class,'trviews.Treatment.fee')])[2]"));
		loginPage.waitForElementToBeClickable(payAmount);
		String amountPay = payAmount.getText();
		WebElement couponAmount = loginPage.getDriver().findElement(By.xpath("//td[contains(text(),'" + fullname
				+ "')]/following-sibling::td/span[contains(@ng-class,'trviews.Treatment.invoiceOldDiscount')]"));
		loginPage.waitForElementToBeClickable(couponAmount);
		String amountCoupon = couponAmount.getText();
		float actual;
		if (actualAmount.contains(",")) {
			String[] str = actualAmount.split(",");
			String actualStr1 = str[0];
			String actualStr2 = str[1];
			int indexOfLast = actualStr2.lastIndexOf(".");
			String newString = actualStr2;
			if (indexOfLast >= 0)
				newString = actualStr2.substring(0, indexOfLast);
			String actualStr3 = actualStr1 + newString;
			actual = Float.parseFloat(actualStr3);
		} else {
			actual = Float.parseFloat(actualAmount);
		}
		float discount;
		if (amountCoupon.contains(",")) {
			String[] discountStr = amountCoupon.split(",");
			String discountStr1 = discountStr[0];
			String discountStr2 = discountStr[1];
			int indexOfLast1 = discountStr2.lastIndexOf(".");
			String newString1 = discountStr2;
			if (indexOfLast1 >= 0)
				newString1 = discountStr2.substring(0, indexOfLast1);
			String discountStr3 = discountStr1 + newString1;
			discount = Float.parseFloat(discountStr3);
		} else {
			discount = Float.parseFloat(amountCoupon);
		}
		float AmountPayValue;
		if (amountPay.contains(",")) {
			String[] effectivePriceStr = amountPay.split(",");
			String effectivePriceStrStr1 = effectivePriceStr[0];
			String effectivePriceStrStr2 = effectivePriceStr[1];
			int indexOfLast1 = effectivePriceStrStr2.lastIndexOf(".");
			String newString1 = effectivePriceStrStr2;
			if (indexOfLast1 >= 0)
				newString1 = effectivePriceStrStr2.substring(0, indexOfLast1);
			String effectivePriceStr3 = effectivePriceStrStr1 + newString1;
			AmountPayValue = Float.parseFloat(effectivePriceStr3);
		} else {
			AmountPayValue = Float.parseFloat(amountPay);
		}
		float effectivePrice = actual - discount;
		Assert.assertTrue(AmountPayValue == effectivePrice);
	}

	public void verifyMarkedMsg() {
		loginPage.waitForSpinnerToDisappear();
		loginPage.softAssert().assertTrue(loginPage.getDriver().findElement(By.xpath("//span[contains(text(),'Selected treatment(s) marked as patient preferred!')]")).isDisplayed());
	}

	public void selectTreatmentInTreatmentListPage(String treatment) {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(headerOfPage);
		try {
			Thread.sleep(1000);
			List<WebElement> web = loginPage.getDriver().findElements(By.xpath("//span[contains(text(),'" + treatment + "')]/../preceding-sibling::td//label"));
			for(int i=0;i<web.size();i++) {
				web.get(i).click();
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void verifyUnbookBtn(String currentDate) {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(DateInTreatmentList);
		WebElement web = loginPage.getDriver().findElement(
				By.xpath("//span[text()='"+currentDate+"']/../following-sibling::div//span[@class='trtNewIcons trt_unbook_icn']/following-sibling::span[text()='Un-Mark']"));
		Assert.assertTrue(web.isDisplayed());
	}
	
	public void checkedWithoutTreatmentStart() {
	        loginPage.waitForElementVisibility(selectTreatmentMessage,4000);
			Assert.assertTrue(checkedWebElementDisplayed(selectTreatmentMessage));
			loginPage.waitForElementToDisappear((By.xpath("//span[contains(text(),'Please select treatment(s) for starting Workdone')]")));

	}

//	public void checkedWithoutTreatmentStart() {
//		loginPage.waitForPageLoad();
//		try {
//			Thread.sleep(1500);
//			Assert.assertTrue(checkedWebElementDisplayed(selectTreatmentMessage));
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

	public void verifyDeleteSuccessMsg() {
		loginPage.waitForPageLoad();
		try {
			Thread.sleep(1500);
			Assert.assertTrue(checkedWebElementDisplayed(deleteSuccessMsg));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void clickOnYesBtnForDelete() {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(yesBtnForDelete);
		yesBtnForDelete.click();
	}

	public void clickOnSaveBtnInDoctorList() {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(saveBtnInDoctorList);
		try {
			saveBtnInDoctorList.click();
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void verifyHeaderOfDoctorListPopup() {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(headerOfDoctorLsit);
		Assert.assertTrue(headerOfDoctorLsit.getText().contains("Doctors"));
	}

	public void selectDoctor(String doctorName) {
		loginPage.waitForPageLoad();
		loginPage.waitForElementToBeClickable(headerOfDoctorLsit);
		WebElement web = loginPage.getDriver().findElement(By.xpath("//label[contains(text(),'" + doctorName + "')]"));
		web.click();
	}

	public void actionBtn() {
		   loginPage.waitForSpinnerToDisappear();
		   loginPage.waitForElementToDisappear((By.xpath("//span[contains(text(),'Treatment plan copied successfully!'])")));
			List<WebElement> web = loginPage.getDriver()
					.findElements(By.xpath("//div[@id='treatmentlistingtable']//a[contains(@class,'btn btn-wrp')]"));
			loginPage.visibilityOfListLocated(web);
			for(int i=0;i<web.size();i++){
			boolean result = web.get(i).isDisplayed();
			Assert.assertTrue(result);
		} 
	}

	public void afterCopyPlan() {
		loginPage.waitForPageLoad();
		List<WebElement> web = loginPage.getDriver()
				.findElements(By.xpath("//div[contains(@class,'fnt_avgR dateClnc')]/span[@class='ng-binding']"));
		for (int i=0;web.size()>i;i++){
			Assert.assertTrue(web.get(i).getText().contains("Plan 1")||web.get(i).getText().contains("Plan 2"));
		}
	}

	public void verifyCouponImpactInMainList(String fullname) {
		loginPage.waitForModalOverlayToDisappear();
		loginPage.waitForSpinnerToDisappear();
		loginPage.waitForElementVisibility(addNewBtn, 4000);
		WebElement netCost = loginPage.getDriver().findElement(By.xpath("(//span[contains(text(),'"+fullname+"')]/../..//span[@class='ng-binding'])[3]"));
		loginPage.waitForElementToBeClickable(netCost);
		String ActualPrice = netCost.getText();
		System.out.println("The amount Actuual is"+ActualPrice);
		WebElement couponValue = loginPage.getDriver().findElement(By.xpath("(//span[contains(text(),'" + fullname
				+ "')]/../following-sibling::td/span[contains(@ng-class,'tretListChild.Treatment.invoiceOldDiscount')])[2]"));
		loginPage.waitForElementToBeClickable(couponValue);
		String discountPrice = couponValue.getText();
		WebElement AmountPay = loginPage.getDriver().findElement(By.xpath("(//span[contains(text(),'" + fullname
				+ "')]/../following-sibling::td/span[contains(@ng-class,'tretListChild.Treatment.fee')])[1]"));
		loginPage.waitForElementToBeClickable(AmountPay);
		String effectivePrice = AmountPay.getText();
		float actual;
		if (ActualPrice.contains(",")) {
			String[] str = ActualPrice.split(",");
			String actualStr1 = str[0];
			String actualStr2 = str[1];
			int indexOfLast = actualStr2.lastIndexOf(".");
			String newString = actualStr2;
			if (indexOfLast >= 0)
				newString = actualStr2.substring(0, indexOfLast);
			String actualStr3 = actualStr1 + newString;
			actual = Float.parseFloat(actualStr3);
		} else {
			actual = Float.parseFloat(ActualPrice);
		}
		float discount;
		if (discountPrice.contains(",")) {
			String[] discountStr = discountPrice.split(",");
			String discountStr1 = discountStr[0];
			String discountStr2 = discountStr[1];
			int indexOfLast1 = discountStr2.lastIndexOf(".");
			String newString1 = discountStr2;
			if (indexOfLast1 >= 0)
				newString1 = discountStr2.substring(0, indexOfLast1);
			String discountStr3 = discountStr1 + newString1;
			discount = Float.parseFloat(discountStr3);
		} else {
			discount = Float.parseFloat(discountPrice);
		}
		float AmountPayValue;
		if (effectivePrice.contains(",")) {
			String[] effectivepriceStr = effectivePrice.split(",");
			String effectivePriceStrStr1 = effectivepriceStr[0];
			String effectivePriceStrStr2 = effectivepriceStr[1];
			int indexOfLast1 = effectivePriceStrStr2.lastIndexOf(".");
			String newString1 = effectivePriceStrStr2;
			if (indexOfLast1 >= 0)
				newString1 = effectivePriceStrStr2.substring(0, indexOfLast1);
			String effectivePriceStr3 = effectivePriceStrStr1 + newString1;
			AmountPayValue = Float.parseFloat(effectivePriceStr3);
		} else {
			AmountPayValue = Float.parseFloat(effectivePrice);
		}
		float effecttivePrice = actual - discount;
		System.out.println("The Amount Pay Value "+AmountPayValue);
		System.out.println("The Effective Value "+effecttivePrice);
		Assert.assertTrue(AmountPayValue == effecttivePrice);
	}

	private boolean checkedWebElementDisplayed(WebElement webElement) {
		return (webElement.isDisplayed());
	}
}
