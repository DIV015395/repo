package com.prm.patientdashboard.pageobject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.prm.pageobjects.utils.PCDriver;

public class LabWorkOrderListingPage {
	private PCDriver login;
	public LabWorkOrderListingPage(PCDriver login) {
		this.login=login;
		PageFactory.initElements(login.getDriver(),this);
	}

	/*Web Elements in LWO listing*/
	@FindBy(xpath="//i[text()='No Record Found!']")
	private WebElement noRecordMsg;
	@FindBy(xpath="//h1[contains(@class,'btmTitle')]")
	private WebElement headerOfPage;
	@FindBy(xpath="//i[text()='DashBoard']")
	private WebElement dashBoardBtn;
	@FindBy(xpath = "//span[contains(text(),'Lab Work Order updated successfully!')]")
	private WebElement successfullMsg;
	@FindBy(xpath = "//span[contains(text(),'Reorder has been created successfully')]")
	private WebElement successfullMsgForReWork;

	/*WebElements in View modal*/
	@FindBy(xpath="//h4[@class='modal-title col-sm-6 col-md-2 col-lg-2 text-left hidden-xs']")
	private WebElement headingView;
	@FindBy(xpath="//div[contains(@class,'nogap text-center hidden-sm ')]")
	private WebElement patientNameInView;
	@FindBy(xpath="//div[@class='col-xs-12 col-md-3 col-lg-3 nogap hidden-sm']")
	private WebElement labNoView;
	@FindBy(xpath="(//div[contains(@class,'text-right hidden-xs')])[2]")
	private WebElement dateInView;
	@FindBy(xpath="(//div[@class='modal-body viewInfo lwoViewStyle']/h4)[1]")
	private WebElement workDeatilHeadView;
	@FindBy(xpath="//b[text()='Lab']")
	private WebElement labView;
	@FindBy(xpath="//b[text()='Order Type']")
	private WebElement orderTypeView;
	@FindBy(xpath="//b[text()='Work Details']")
	private WebElement workDeatilView;
	@FindBy(xpath="//b[text()='Other Info']")
	private WebElement otherInfoView;
	@FindBy(xpath="//b[text()='Source']")
	private WebElement sourceView;
	@FindBy(xpath="//b[text()='Item(s) Sent']")
	private WebElement itemSentView;
	@FindBy(xpath="//h4[text()='Additional Details']")
	private WebElement additionalDeatilsHeadView;
	@FindBy(xpath="//b[text()='Require']")
	private WebElement requireView;
	@FindBy(xpath="//b[text()='Shade']")
	private WebElement shadeView;
	@FindBy(xpath="//b[text()='Stains Internal']")
	private WebElement stainsInternalView;
	@FindBy(xpath="//b[text()='Stains External']")
	private WebElement stainsExternalView;
	@FindBy(xpath="//b[text()='Translucency']")
	private WebElement translucencyView;
	@FindBy(xpath="//b[text()='Texture']")
	private WebElement textureView;
	@FindBy(xpath="//b[text()='Configurations']")
	private WebElement configurationView;
	@FindBy(xpath="//b[text()='Surface Details']")
	private WebElement surfaceDetailsView;
	@FindBy(xpath="//b[text()='Notes']")
	private WebElement notesView;
	@FindBy(xpath="//h4[text()='Notes and Attachments']")
	private WebElement notesAttachView;
	@FindBy(xpath="//h4[text()='Timeline']")
	private WebElement timelineView;
	@FindBy(xpath="(//div[text()='Draft'])[1]")
	private WebElement draftView;
	@FindBy(xpath="(//div[text()='Submitted To Lab'])[1]")
	private WebElement submittedView;
	@FindBy(xpath="(//div[text()='Treatment Completed'])[1]")
	private WebElement treatCompletedView;
	@FindBy(xpath="(//div[text()='Payment Completed'])[1]")
	private WebElement payCompletedView;
	@FindBy(xpath="(//div[text()='Payable'])[1]")
	private WebElement payableView;
	@FindBy(xpath="(//div[@class='graphPointer active draft'])[1]")
	private WebElement checkDraftActive;
	@FindBy(xpath="(//div[@class='graphPointer active submitted'])[1]")
	private WebElement checkPaymentCompletedActive;
	@FindBy(xpath="//span[@class='ng-binding ng-scope']")
	private WebElement configDataView;
	@FindBy(xpath="//h4[text()='Revision Details']")
	private WebElement revisionDetailsHeadView;
	@FindBy(xpath="//b[text()='Revision No']")
	private WebElement revisionNoHeadView;
	@FindBy(xpath="//b[text()='Revision No']/../following-sibling::div")
	private WebElement revisionNoView;
	@FindBy(xpath="//b[text()='Revision Date']")
	private WebElement revisionDateHeadView;
	@FindBy(xpath="//b[text()='Revision Date']/../following-sibling::div")
	private WebElement revisionDateView;
	@FindBy(xpath="//b[text()='Current Status']")
	private WebElement currentStatusHeadView;
	@FindBy(xpath="//b[text()='Current Status']/../following-sibling::div")
	private WebElement currentStatusView;
	@FindBy(xpath="//h4[contains(@class,'labviewhdr ng-binding ng-scope')]")
	private WebElement treatmentNameView;
	@FindBy(xpath="//span[@class='cmnicons cncl-mdl']")
	private WebElement closeViewScreen;

	/*-----------------------Functions-----------------------*/

	/*-- Checking the clinic Name in Main List --*/
	public void checkClinicNameMainList(String teethNo,String clinicName) {
		login.waitForPageLoad();
		WebElement ele = login.getDriver().findElement(By.xpath("//div[contains(text(),'"+teethNo+"')]/ancestor::div[@class='labListDetailsTable ng-scope']/preceding-sibling::div//span[@class='col-xs-12 col-sm-3 nogap ng-binding']"));
		Assert.assertTrue(checkedWebElementDisplayed(ele));
		String str = ele.getText();
		String[] split = str.split(":");
		String clinic_Name = split[1].trim();
		Assert.assertEquals(clinic_Name, clinicName);
	}

	/*-- Checking the Order Type in Main List --*/
	public void checkOrderTypeMainList(String teethNo,String orderType) {
		login.waitForPageLoad();
		WebElement ele = login.getDriver().findElement(By.xpath("//div[contains(text(),'"+teethNo+"')]/ancestor::div[@class='labListDetailsTable ng-scope']//span[text()='Order Type:']/.."));
		Assert.assertTrue(checkedWebElementDisplayed(ele));
		String str = ele.getText();
		String[] split = str.split(":");
		String order_Type = split[1].trim();
		Assert.assertEquals(order_Type, orderType);
	}

	/*-- Checking the only Date in Main List --*/
	public void checkDateMainList(String teethNo,String date) {
		login.waitForPageLoad();
		WebElement ele = login.getDriver().findElement(By.xpath("//div[contains(text(),'"+teethNo+"')]/ancestor::div[@class='labListDetailsTable ng-scope']/preceding-sibling::div//span[@class='col-xs-12 col-sm-3 nogap text-right ng-binding']"));
		String str = ele.getText();
		String[] split = str.split(":");
		String newDate = split[1].trim();
		Assert.assertEquals(newDate, date);
	}

	/*-- Checking the Lab Name in Main List --*/
	public void checkLabNameMainList(String teethNo,String labName) {
		login.waitForPageLoad();
		WebElement ele = login.getDriver().findElement(By.xpath("//div[contains(text(),'"+teethNo+"')]/ancestor::div[@class='labListDetailsTable ng-scope']/preceding-sibling::div//span[@class='col-xs-12 col-sm-6 text-center ng-binding']"));
		Assert.assertTrue(checkedWebElementDisplayed(ele));
		String[] split = ele.getText().split(":");
		String lab_Name = split[1].trim();
		Assert.assertEquals(lab_Name, labName);
	}

	/*-- Checking the LWO no. is not equals to NA or Null but except intially(When we Start LWO enable Trt) it is NA in Main List --*/
	public void checkLabWorkOrderNo(String teethNo) {
		login.waitForPageLoad();
		WebElement ele = login.getDriver().findElement(By.xpath("//div[contains(text(),'"+teethNo+"')]/ancestor::div[@class='labListDetailsTable ng-scope']//span[text()='Lab Work Order No:']/.."));
		Assert.assertTrue(checkedWebElementDisplayed(ele));
		String[] split = ele.getText().split(":");
		String lab_WNO = split[1].trim();
		if(lab_WNO.equals("NA"))
			Assert.assertEquals(lab_WNO, "NA");
		else {
			boolean flag = !(lab_WNO.equals("NA") || lab_WNO.equals("Null"));
			Assert.assertTrue(flag);
		}
	}

	/*Check for previously entry which is InActive due to LWO updated */
	public void checkInActiveEntry(String teethNo,String revision,String reason) {
		login.waitForPageLoad();
		if(reason.equals("Reworked")) {
			WebElement ele = login.getDriver().findElement(By.xpath("//span[contains(text(),'"+teethNo+"')]/../../following-sibling::td//span[contains(text(),'"+revision+"')]/ancestor::tr[@class='ng-scope labInActive']//span[@class='inactiveArrow']"));
			Assert.assertTrue(checkedWebElementDisplayed(ele));
		}
		else {
			WebElement ele = login.getDriver().findElement(By.xpath("//div[contains(text(),'"+teethNo+"')]/../following-sibling::td//span[contains(text(),'"+revision+"')]/ancestor::tr[@class='ng-scope labInActive']//span[@class='inactiveArrow']"));
			Assert.assertTrue(checkedWebElementDisplayed(ele));
		}
	}

	/*-- Checking the Date and Time show in Main List --*/
	public void checkDateTimeMainList(String teethNo,String startDateTime,String endDateTime) {
		login.waitForPageLoad();
		WebElement ele = login.getDriver().findElement(By.xpath("//div[contains(text(),'"+teethNo+"')]/ancestor::table[@class='table labTable ng-scope']//th[text()='Date/Time']"));
		WebElement ele1 = login.getDriver().findElement(By.xpath("//div[contains(text(),'"+teethNo+"')]/ancestor::tr[@class='ng-scope']//td[@class='wrd-brk-all ng-binding']/preceding-sibling::td/div[@class='text-center ng-binding']"));
		String str = ele1.getText();
		String split = str.substring(0,8);
		String split1 = str.substring(8);
		String revisionDateTime = split+" "+split1.trim();
		Assert.assertTrue(checkedWebElementDisplayed(ele));

		try {
			SimpleDateFormat fmt = new SimpleDateFormat("dd-MM-yy H:mm a");
			Date dateTime = fmt.parse(revisionDateTime);
			Date fromDate = fmt.parse(startDateTime);
			Date toDate = fmt.parse(endDateTime);
			if (dateTime.after(fromDate) && dateTime.before(toDate)) {
				System.out.println("Date and Time of main list verified:- "+revisionDateTime);
			}
			else
				Assert.assertFalse(true);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*-- Checking the Work Details in Main List --*/
	public void checkWorkDetailsMainlist(String teethNo,String workdetails) {
		login.waitForPageLoad();
		WebElement ele = login.getDriver().findElement(By.xpath("//div[contains(text(),'"+teethNo+"')]/ancestor::table[@class='table labTable ng-scope']//th[text()='Work Details']"));
		WebElement ele1 = login.getDriver().findElement(By.xpath("//div[contains(text(),'"+teethNo+"')]/ancestor::tr[@class='ng-scope']//td[text()='"+workdetails+"']"));
		String str = ele1.getText();
		Assert.assertTrue(checkedWebElementDisplayed(ele));
		Assert.assertEquals(str, workdetails);
	}

	/*-- Checking the Source in Main List --*/
	public void checkSourceMainList(String teethNo) {
		login.waitForPageLoad();
		WebElement ele = login.getDriver().findElement(By.xpath("//div[contains(text(),'"+teethNo+"')]/ancestor::table[@class='table labTable ng-scope']//th[text()='Source']"));
		WebElement ele1 = login.getDriver().findElement(By.xpath("//tr[@class='ng-scope']//td[contains(@class,'wrd-brk-all')]/div[contains(text(),'"+teethNo+"')]"));
		String str = ele1.getText();
		Assert.assertTrue(checkedWebElementDisplayed(ele));
		Assert.assertTrue(str.contains(teethNo));
	}

	/*-- Checking the Source for Suspended and retreated Teeth in Main List --*/
	public void checkSourceForSuspAndRetreatMainList(String teethNo) {
		login.waitForPageLoad();
		WebElement ele = login.getDriver().findElement(By.xpath("//div[contains(text(),'"+teethNo+"')]/ancestor::table[@class='table labTable ng-scope']//th[text()='Source']"));
		WebElement ele1 = login.getDriver().findElement(By.xpath("//tr[@class='ng-scope']//td[contains(@class,'wrd-brk-all')]//span[contains(text(),'"+teethNo+"')]"));
		String str = ele1.getText();
		Assert.assertTrue(checkedWebElementDisplayed(ele));
		if(ele1.getAttribute("class").contains("txtRed")) {
			Assert.assertTrue(ele1.getAttribute("class").contains("txtRed"));
			Assert.assertEquals(str, teethNo);
		}
		else {
			Assert.assertTrue(ele1.getAttribute("class").contains("txtGrn"));
			Assert.assertEquals(str, teethNo);
		}
	}

	/*-- Checking the Doctor Name in Main List --*/
	public void checkDoctorMainList(String teethNo,String doctor) {
		login.waitForPageLoad();
		WebElement ele = login.getDriver().findElement(By.xpath("//div[contains(text(),'"+teethNo+"')]/ancestor::table[@class='table labTable ng-scope']//th[text()='Doctor']"));
		WebElement ele1 = login.getDriver().findElement(By.xpath("//div[contains(text(),'"+teethNo+"')]/ancestor::tr[@class='ng-scope']//td[text()='"+doctor+"']"));
		String str = ele1.getText();
		Assert.assertTrue(checkedWebElementDisplayed(ele));
		Assert.assertEquals(str, doctor);
	}

	/*-- Checking the ItemSent in Main List --*/
	public void checkItemSentMainList(String teethNo,String itemsent) {
		login.waitForPageLoad();
		WebElement ele = login.getDriver().findElement(By.xpath("//div[contains(text(),'"+teethNo+"')]/ancestor::table[@class='table labTable ng-scope']//th[text()='Item(s) Sent']"));
		WebElement ele1 = login.getDriver().findElement(By.xpath("//div[contains(text(),'"+teethNo+"')]/ancestor::tr[@class='ng-scope']/td[@class='pre hidden-xs hidden-sm wrd-brk-wrd']"));
		String str = ele1.getText();
		Assert.assertTrue(checkedWebElementDisplayed(ele));
		if(str.equals("NA"))
			Assert.assertEquals(str, itemsent);
		else if(str.contains(","))
			Assert.assertTrue(str.contains(itemsent));
		else {
			String[] split = str.split(":");
			Assert.assertEquals(split[0], itemsent);
		}
	}

	/*-- Checking the Revision and Reason in Main List --*/
	public void checkRevisionReasonMainList(String teethNo,String revision,String reason) {
		login.waitForPageLoad();
		WebElement ele = login.getDriver().findElement(By.xpath("//div[contains(text(),'"+teethNo+"')]/ancestor::table[@class='table labTable ng-scope']//th[text()='Revision/Reason']"));
		WebElement ele1 = login.getDriver().findElement(By.xpath("//div[contains(text(),'"+teethNo+"')]/ancestor::tr[@class='ng-scope']//div[@class='revisionBlock text-center ng-binding']"));
		String str = ele1.getText();
		String split = str.substring(0,10);
		String split1 = str.substring(10);
		String exp_reason = split1.trim();
		Assert.assertTrue(checkedWebElementDisplayed(ele));
		Assert.assertEquals(split, revision);
		Assert.assertEquals(exp_reason, reason);
	}

	/*-- Checking the InActive Revision and Reason in Main List --*/
	public void checkInActiveRevisionReasonMainList(String teethNo,String revision,String reason) {
		login.waitForPageLoad();
		WebElement ele = login.getDriver().findElement(By.xpath("//div[contains(text(),'"+teethNo+"')]/../following-sibling::td//span[contains(text(),'"+revision+"')]/ancestor::tr[@class='ng-scope labInActive']//div[@class='revisionBlock text-center ng-binding']"));
		String str = ele.getText();
		String split = str.substring(0,10);
		String split1 = str.substring(10);
		String exp_reason = split1.trim();
		Assert.assertEquals(split, revision);
		Assert.assertEquals(exp_reason, reason);
	}

	/*-- Checking the InActive Revision and Reason For rework in Main List --*/
	public void checkInActiveRevisionReasonMainListForRework(String teethNo,String revision,String reason) {
		login.waitForPageLoad();
		WebElement ele = login.getDriver().findElement(By.xpath("//span[contains(text(),'"+teethNo+"')]/../../following-sibling::td//span[contains(text(),'"+revision+"')]/ancestor::tr[@class='ng-scope labInActive']//div[@class='revisionBlock text-center ng-binding']"));
		String str = ele.getText();
		String split = str.substring(0,10);
		String split1 = str.substring(10);
		String exp_reason = split1.trim();
		Assert.assertEquals(split, revision);
		Assert.assertEquals(exp_reason, reason);
	}

	/*-- Checking the Status and Date in Main List --*/
	public void checkStatusDateMainList(String teethNo,String status,String startDateTime,String endDateTime ) {
		login.waitForPageLoad();
		WebElement ele = login.getDriver().findElement(By.xpath("//div[contains(text(),'"+teethNo+"')]/ancestor::table[@class='table labTable ng-scope']//th[text()='Status/Date']"));
		WebElement ele1 = login.getDriver().findElement(By.xpath("//div[contains(text(),'"+teethNo+"')]/ancestor::tr[@class='ng-scope']//div[@class='text-center ng-binding']//span[text()='"+status+"']/.."));
		String str = ele1.getText();
		String[] split = str.split("\\R");
		Assert.assertEquals(split[0],status);
		String actual_DateTime = split[1];
		Assert.assertTrue(checkedWebElementDisplayed(ele));

		try {
			SimpleDateFormat fmt = new SimpleDateFormat("dd-mm-yy h:mm a");
			Date dateTime = fmt.parse(actual_DateTime);
			Date fromDate = fmt.parse(startDateTime);
			Date toDate = fmt.parse(endDateTime);
			if (dateTime.after(fromDate) && dateTime.before(toDate)) {
				System.out.println(status+" as a status Date and Time of main list verified:- "+actual_DateTime);
			}
			else
				Assert.assertFalse(true);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*-- Checking the InActive Status and Date in Main List --*/
	public void checkInActiveStatusDateMainList(String teethNo,String status,String startDateTime,String endDateTime ) {
		login.waitForPageLoad();
		WebElement ele = login.getDriver().findElement(By.xpath("//div[contains(text(),'"+teethNo+"')]/ancestor::tr[@class='ng-scope labInActive']//div[@class='text-center ng-binding']//span[text()='"+status+"']/.."));
		String str = ele.getText();
		String[] split = str.split("\\R");
		Assert.assertEquals(split[0],status);
		String actual_DateTime = split[1];

		try {
			SimpleDateFormat fmt = new SimpleDateFormat("dd-mm-yy h:mm a");
			Date dateTime = fmt.parse(actual_DateTime);
			Date fromDate = fmt.parse(startDateTime);
			Date toDate = fmt.parse(endDateTime);
			if (dateTime.after(fromDate) && dateTime.before(toDate)) {
				System.out.println(status+" as a status Date and Time of InActive main list verified:- "+actual_DateTime);
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*-- Checking the Action heading name in Main List --*/
	public void checkActionMainList(String teethNo) {
		login.waitForPageLoad();
		WebElement ele = login.getDriver().findElement(By.xpath("//div[contains(text(),'"+teethNo+"')]/ancestor::table[@class='table labTable ng-scope']//th[text()='Action']"));
		Assert.assertTrue(checkedWebElementDisplayed(ele));
	}

	/*click View button present in LWO listing*/
	public void clickOnView(String teethNo) {
		login.waitForPageLoad();
		WebElement viewBtn = login.getDriver().findElement(By.xpath("//div[contains(text(),'"+teethNo+"')]/ancestor::tr[@class='ng-scope']//span[@class='actn-icn view']"));
		Assert.assertTrue(checkedWebElementDisplayed(viewBtn));
		viewBtn.click();
	}

	/*click on View When Rework button is present in LWO listing*/
	public void clickOnViewAtTimeForRework(String teethNo) {
		login.waitForPageLoad();
		WebElement viewBtnAtTimeRework = login.getDriver().findElement(By.xpath("//span[contains(text(),'"+teethNo+"')]/ancestor::tr[@class='ng-scope']//span[@class='actn-icn view']"));
		Assert.assertTrue(checkedWebElementDisplayed(viewBtnAtTimeRework));
		viewBtnAtTimeRework.click();
	}

	/*click edit button present in LWO listing*/
	public void clickOnEdit(String teethNo) {
		login.waitForPageLoad();
		try {
			Thread.sleep(6000);
			WebElement editBtn = login.getDriver().findElement(By.xpath("//div[contains(text(),'"+teethNo+"')]/ancestor::tr[@class='ng-scope']//span[@class='actn-icn edit']"));
			Assert.assertTrue(checkedWebElementDisplayed(editBtn));
			editBtn.click();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*Check Edit button present in LWO listing*/
	public void checkEditBtnMainList(String teethNo,String status) {
		login.waitForPageLoad();
		WebElement ele = login.getDriver().findElement(By.xpath("//div[contains(text(),'"+teethNo+"')]/ancestor::tr[@class='ng-scope']//span[@class='actn-icn edit']"));
		if(status.equals("Draft")||status.equals("Submitted To Lab")||status.equals("Payment Completed"))
			Assert.assertTrue(checkedWebElementDisplayed(ele));
		else
			Assert.assertFalse(checkedWebElementDisplayed(ele));
	}

	/*Check View button for InActive LWO show in LWO listing*/
	public void checkInActiveViewBtn(String teethNo,String revision) {
		login.waitForPageLoad();
		WebElement ele = login.getDriver().findElement(By.xpath("//div[contains(text(),'"+teethNo+"')]/../following-sibling::td//span[contains(text(),'"+revision+"')]/ancestor::tr[@class='ng-scope labInActive']//span[@class='actn-icn view']"));
		Assert.assertTrue(checkedWebElementDisplayed(ele));
	}

	/*Check View button for InActive LWO in case for Rework show in LWO listing*/
	public void checkInActiveViewBtnForRework(String teethNo,String revision) {
		login.waitForPageLoad();
		WebElement ele = login.getDriver().findElement(By.xpath("//span[contains(text(),'"+teethNo+"')]/../../following-sibling::td//span[contains(text(),'"+revision+"')]/ancestor::tr[@class='ng-scope labInActive']//span[@class='actn-icn view']"));
		Assert.assertTrue(checkedWebElementDisplayed(ele));
	}

	/*click Rework button present in LWO listing*/
	public void clickOnRework(String teethNo) {
		login.waitForPageLoad();
		try {
			Thread.sleep(3000);
			WebElement reWorkBtn = login.getDriver().findElement(By.xpath("//span[contains(text(),'"+teethNo+"')]/ancestor::tr[@class='ng-scope']//span[@class='actn-icn retreat']"));
			Assert.assertTrue(checkedWebElementDisplayed(reWorkBtn));
			reWorkBtn.click();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*Check Print button present in LWO listing*/
	public void checkPrintBtn(String teethNo) {
		login.waitForPageLoad();
		WebElement printBtn = login.getDriver().findElement(By.xpath("//div[contains(text(),'"+teethNo+"')]/ancestor::tr[@class='ng-scope']//span[@class='actn-icn print']"));
		Assert.assertTrue(checkedWebElementDisplayed(printBtn));
	}

	/*----Functions in View Modal-----*/
	public void checkedViewDataName() {
		login.waitForPageLoad();
		try {
			Thread.sleep(3000);
			Assert.assertTrue(checkedWebElementDisplayed(headingView) &&checkedWebElementDisplayed(labNoView)&&
					checkedWebElementDisplayed(workDeatilHeadView) && checkedWebElementDisplayed(labView) &&
					checkedWebElementDisplayed(orderTypeView) && checkedWebElementDisplayed(workDeatilView) &&
					checkedWebElementDisplayed(otherInfoView) && checkedWebElementDisplayed(sourceView) &&
					checkedWebElementDisplayed(itemSentView) && checkedWebElementDisplayed(requireView) &&
					checkedWebElementDisplayed(additionalDeatilsHeadView) && checkedWebElementDisplayed(shadeView) &&
					checkedWebElementDisplayed(stainsInternalView) && checkedWebElementDisplayed(stainsExternalView) &&
					checkedWebElementDisplayed(translucencyView) && checkedWebElementDisplayed(textureView) &&
					checkedWebElementDisplayed(configurationView) && checkedWebElementDisplayed(surfaceDetailsView) &&
					checkedWebElementDisplayed(notesView) && checkedWebElementDisplayed(notesAttachView) &&
					checkedWebElementDisplayed(timelineView) && checkedWebElementDisplayed(draftView) &&
					checkedWebElementDisplayed(treatmentNameView) && checkedWebElementDisplayed(draftView) &&
					checkedWebElementDisplayed(submittedView) && checkedWebElementDisplayed(treatCompletedView)&&
					checkedWebElementDisplayed(payCompletedView) && checkedWebElementDisplayed(payableView));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*-- Checking the Header in LWO view Screen--*/
	public void verifyHeaderInView(String header) {
		login.waitForPageLoad();
		login.waitForElementToBeClickable(headingView);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Assert.assertTrue(headingView.getText().contains(header));
	}

	/*-- Checking the Patient Name in View --*/
	public void verifyPatientNameView(String patientName) {
		login.waitForPageLoad();
		login.waitForElementToBeClickable(patientNameInView);
		String str = patientNameInView.getText();
		String[] split = str.split(" : ");
		boolean flag = split[0].trim().contains(patientName) && !(split[1].equals("NA") || split[1].equals("Null"));
		Assert.assertTrue(flag);
	}

	/*-- Checking the LWO No in View --*/
	public void verifyLabWorkOrderNoView() {
		login.waitForPageLoad();
		String str = labNoView.getText();
		String[] split =str.split(":");
		String lab_WNO = split[1].trim();
		if(lab_WNO.equals("NA"))
			Assert.assertEquals(lab_WNO, "NA");
		else {
			boolean flag = !(lab_WNO.equals("NA") || lab_WNO.equals("Null"));
			Assert.assertTrue(flag);
		}
	}

	/*-- Checking the Date and Time in View --*/
	public void verifyDateTimeInView(String startDateTime,String endDateTime) {
		login.waitForPageLoad();
		String str = dateInView.getText();
		String split = str.substring(6);
		String exp_Date = split.trim();
		try {
			SimpleDateFormat fmt = new SimpleDateFormat("dd-mm-yy h:mm a");
			Date dateTime = fmt.parse(exp_Date);
			Date fromDate = fmt.parse(startDateTime);
			Date toDate = fmt.parse(endDateTime);
			if (dateTime.after(fromDate) && dateTime.before(toDate)) {
				System.out.println(" Date and Time In View Modal is verified "+exp_Date);
			}
			else
				Assert.assertFalse(true);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*-- Checking the Lab Name in View --*/
	public void checkLabNameViewList(String labName) {
		login.waitForPageLoad();
		WebElement ele = login.getDriver().findElement(By.xpath("//b[text()='Lab']/../following-sibling::div"));
		String actualLab = ele.getText();
		Assert.assertEquals(actualLab,labName);
	}
	
	/*-- Checking the Updation Reason in View --*/
	public void checkUpdationReasonViewList(String reason) {
		login.waitForPageLoad();
		WebElement ele = login.getDriver().findElement(By.xpath("//b[text()='Updation Reason']/../following-sibling::div"));
		String actualLab = ele.getText();
		Assert.assertEquals(actualLab,reason);
	}
	
	/*-- Checking the Order Type in View --*/
	public void checkOrderTypeViewList(String orderType) {
		login.waitForPageLoad();
		WebElement ele = login.getDriver().findElement(By.xpath("//b[text()='Order Type']/../following-sibling::div"));
		String actOrderType = ele.getText();
		Assert.assertEquals(actOrderType,orderType);
	}

	/*-- Checking the Work Details in View --*/
	public void checkWorkDetailViewList(String workDetail) {
		login.waitForPageLoad();
		WebElement ele = login.getDriver().findElement(By.xpath("//b[text()='Work Details']/../following-sibling::div"));
		String actWorkDetail = ele.getText();
		Assert.assertEquals(actWorkDetail,workDetail);
	}

	/*-- Checking the Other Info in View --*/
	public void checkOtherInfoViewList(String otherInfo) {
		login.waitForPageLoad();
		WebElement ele = login.getDriver().findElement(By.xpath("//b[text()='Other Info']/../following-sibling::div"));
		String actOtherInfo = ele.getText();
		Assert.assertEquals(actOtherInfo,otherInfo);
	}

	/*-- Checking the Source in View --*/
	public void checkSourceViewList(String source,String Reason) {
		login.waitForPageLoad();
		WebElement ele = login.getDriver().findElement(By.xpath("//b[text()='Source']/../following-sibling::div"));
		String actSource = ele.getText();
		if(actSource.contains("/"))
		{
			String[] split = actSource.split("/");
			Assert.assertEquals(split[0].trim(),source);
			Assert.assertEquals(split[1].trim(),Reason);
		}
		else
			Assert.assertEquals(actSource,source);
	}

	/*-- Checking the Source for Suspend in View --*/
	public void checkSourceForSuspendedViewList(String source,String Reason) {
		login.waitForPageLoad();
		WebElement ele = login.getDriver().findElement(By.xpath("//b[text()='Source']/../following-sibling::div//span[@class='txtRed ng-binding']"));
		String actSource = ele.getText();
		if(actSource.contains("/"))
		{
			String[] split = actSource.split("/");
			Assert.assertEquals(split[0].trim(),source);
			Assert.assertEquals(split[1].trim(),Reason);
		}
		else
			Assert.assertEquals(actSource,source);
	}

	/*-- Checking the Source For Reopen and Treatment Completed in View --*/
	public void checkSourceForReopenAndTrtCompleteViewList(String source,String Reason) {
		login.waitForPageLoad();
		WebElement ele = login.getDriver().findElement(By.xpath("//b[text()='Source']/../following-sibling::div//span[@class='txtGrn ng-binding']"));
		String actSource = ele.getText();
		if(actSource.contains("/"))
		{
			String[] split = actSource.split("/");
			Assert.assertEquals(split[0].trim(),source);
			Assert.assertEquals(split[1].trim(),Reason);
		}
		else
			Assert.assertEquals(actSource,source);
	}

	/*-- Checking the Item Sent in View --*/
	public void checkItemSentViewList(String itemSent) {
		login.waitForPageLoad();
		WebElement ele = login.getDriver().findElement(By.xpath("//b[text()='Item(s) Sent']/../following-sibling::div"));
		String actItemSent = ele.getText();
		Assert.assertTrue(checkedWebElementDisplayed(ele));
		if(actItemSent.equals("NA"))
			Assert.assertEquals(actItemSent, itemSent);
		else if(actItemSent.contains(","))
			Assert.assertTrue(actItemSent.contains(itemSent));
		else {
			String[] split = actItemSent.split(":");
			Assert.assertEquals(split[0], itemSent);
		}
	}

	/*-- Checking the Require in View --*/
	public void checkRequireViewList(String require) {
		login.waitForPageLoad();
		WebElement ele = login.getDriver().findElement(By.xpath("//b[text()='Require']/../following-sibling::div"));
		String actualLab = ele.getText();
		Assert.assertEquals(actualLab,require);
	}

	/*-- Checking the Shade in View --*/
	public void checkShadeViewList(String shade,String shadeOption) {
		login.waitForPageLoad();
		if(shade.equals("NA")) {
			WebElement ele = login.getDriver().findElement(By.xpath("(//b[text()='Shade']/../following-sibling::div)[1]"));
			String actShade = ele.getText();
			Assert.assertEquals(actShade,shade);
		}
		else if(shade.equals("NA ")) {
			WebElement ele = login.getDriver().findElement(By.xpath("(//b[text()='Shade']/../following-sibling::div)[2]"));
			String actShade = ele.getText();
			String exp_shade = shade.trim();
			Assert.assertEquals(actShade,exp_shade);
		}
		else{
			WebElement ele = login.getDriver().findElement(By.xpath("(//b[text()='Shade']/../following-sibling::div)[2]"));
			String actShade = ele.getText();
			String actual_shade = shade+" "+":"+" "+shadeOption;
			Assert.assertEquals(actShade,actual_shade);
		}
	}

	/*-- Checking the Stains Internal in View --*/
	public void checkStainsInternalViewList(String stainsInternal) {
		login.waitForPageLoad();
		WebElement ele = login.getDriver().findElement(By.xpath("//b[text()='Stains Internal']/../following-sibling::div"));
		String actStainsInternal = ele.getText();
		Assert.assertEquals(actStainsInternal,stainsInternal);
	}

	/*-- Checking the Stains External in View --*/
	public void checkStainsExternalViewList(String stainsExternal) {
		login.waitForPageLoad();
		WebElement ele = login.getDriver().findElement(By.xpath("//b[text()='Stains External']/../following-sibling::div"));
		String actStainsExternal = ele.getText();
		Assert.assertEquals(actStainsExternal,stainsExternal);
	}

	/*-- Checking the Translucency in View --*/
	public void checkTranslucencyViewList(String translucency) {
		login.waitForPageLoad();
		WebElement ele = login.getDriver().findElement(By.xpath("//b[text()='Translucency']/../following-sibling::div"));
		String actTranslucency = ele.getText();
		Assert.assertEquals(actTranslucency,translucency);
	}

	/*-- Checking the Texture in View --*/
	public void checkTextureViewList(String texture) {
		login.waitForPageLoad();
		WebElement ele = login.getDriver().findElement(By.xpath("//b[text()='Texture']/../following-sibling::div"));
		String actTexture= ele.getText();
		Assert.assertEquals(actTexture,texture);
	}

	/*-- Checking the Configuration in View --*/
	public void checkConfigurationsViewList(String configurations,String config_Option) {
		login.waitForPageLoad();
		WebElement ele = login.getDriver().findElement(By.xpath("//b[text()='Configurations']/../following-sibling::span"));
		String actConfiguration= ele.getText().trim();
		if(actConfiguration.equals("Individual")||actConfiguration.equals("NA"))
			Assert.assertEquals(actConfiguration,configurations);
		else {
			String exp_configuration = configurations+":"+" "+config_Option;
			Assert.assertEquals(actConfiguration,exp_configuration);
		}
	}

	/*-- Checking the Surface Details in View --*/
	public void checkSurfaceDetailsViewList(String surface_Details,String surface_Option) {
		login.waitForPageLoad();
		WebElement ele = login.getDriver().findElement(By.xpath("//b[text()='Surface Details']/../following-sibling::div"));
		String actSurface_Details= ele.getText().trim();
		if(actSurface_Details.equals("NA"))
			Assert.assertEquals(actSurface_Details,surface_Details);
		else
			Assert.assertTrue(actSurface_Details.contains(surface_Details+": "+surface_Option));
	}

	/*-- Checking the Notes in View --*/
	public void checkNotesViewList(String notes) {
		login.waitForPageLoad();
		WebElement ele = login.getDriver().findElement(By.xpath("//b[text()='Notes']/../following-sibling::div"));
		String actNotes= ele.getText();
		Assert.assertEquals(actNotes,notes);
	}

	/*-- Checking the Attachment in View --*/
	public void checkAttachmentViewList(String fileName) {
		login.waitForPageLoad();
		WebElement ele = login.getDriver().findElement(By.xpath("//span[text()='"+fileName+"']"));
		Assert.assertTrue(checkedWebElementDisplayed(ele));
	}

	/*-- Close the View Screen --*/
	public void clickViewScreenClose() {
		login.waitForPageLoad();
		closeViewScreen.click();
	}

	/*-- Checking the Revision Details Data in View --*/
	public void checkedRevisionDetailsDataNameView() {
		login.waitForPageLoad();
		Assert.assertTrue(checkedWebElementDisplayed(revisionDetailsHeadView) &&checkedWebElementDisplayed(revisionNoHeadView)&&
				checkedWebElementDisplayed(revisionDateHeadView) && checkedWebElementDisplayed(currentStatusHeadView));
	}

	/*-- Checking All Status whose are Enable in View --*/
	public void checkStatusEnableInView(String statusName) {
		login.waitForPageLoad();
		try {
			Thread.sleep(3000);
			WebElement ele = login.getDriver().findElement(By.xpath("(//div[text()='"+statusName+"']/..//div[@class='glyphicon glyphicon-ok'])[1]"));
			Assert.assertTrue(checkedWebElementDisplayed(ele));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*-- Verifying All Status whose are Enable in View --*/
	public void verifyStatusInView(String statusName,String startDateTime,String endDateTime) {
		login.waitForPageLoad();
		WebElement ele = login.getDriver().findElement(By.xpath("(//div[text()='"+statusName+"']/following-sibling::div[@class='graphTxtBtm ng-binding'])[1]"));
		String str = ele.getText();
		try {
			SimpleDateFormat fmt = new SimpleDateFormat("dd-mm-yy h:mm a");
			Date dateTime = fmt.parse(str);
			Date fromDate = fmt.parse(startDateTime);
			Date toDate = fmt.parse(endDateTime);
			if (dateTime.after(fromDate) && dateTime.before(toDate)) {
				System.out.println(statusName+" Date and Time In View Modal is verified:- "+str);
			}
			else
				Assert.assertFalse(true);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*-- Check Revision No. in View --*/
	public void checkRevisionNoViewList(String revisionNo) {
		login.waitForPageLoad();
		String actRevisionNo = revisionNoView.getText();
		Assert.assertEquals(actRevisionNo,revisionNo);
	}

	/*-- Check Treatment name in View --*/
	public void verifyTreatmentNameView(String treatmentName,int count) {
		login.waitForPageLoad();
		String ele = treatmentNameView.getText();
		String treatmentHead = ele.substring(0, 14);
		String actual_TreatmentName = ele.substring(17);
		Assert.assertTrue(treatmentHead.trim().contains("Treatment Name"));
		String actual_trt = actual_TreatmentName.trim();
		if(actual_trt.contains("Bridge"))
			Assert.assertEquals(actual_trt, treatmentName+" (" +count+ " unit(s) Bridge)");
		else
			Assert.assertEquals(actual_trt, treatmentName);

	}

	/*-- Check Revision Date in View --*/
	public void verifyRevisionDateInView(String startDateTime,String endDateTime) {
		login.waitForPageLoad();
		String str=revisionDateView.getText();
		try {
			SimpleDateFormat fmt = new SimpleDateFormat("dd-mm-yy h:mm a");
			Date dateTime = fmt.parse(str);
			Date fromDate = fmt.parse(startDateTime);
			Date toDate = fmt.parse(endDateTime);
			if (dateTime.after(fromDate) && dateTime.before(toDate)) {
				System.out.println("Revision Date and Time In View Modal is verified "+str);
			}
			else
				Assert.assertFalse(true);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*-- Check Current Status in View --*/
	public void checkCurrentStatusViewList(String statusName) {
		login.waitForPageLoad();
		String actRevisionNo = currentStatusView.getText();
		Assert.assertEquals(actRevisionNo,statusName);
	}

	/*-- Validate the Successfull Msg --*/
	public void verifySuccessfullMessage(){
		login.waitForPageLoad();
		try {
			Thread.sleep(3000);
			login.softAssert().assertTrue(checkedWebElementDisplayed(successfullMsg));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/*-- Validate the Successfull Msg For rework --*/
	public void verifySuccessfullMessageForRework(){
		login.waitForPageLoad();
		try {
			Thread.sleep(3000);
			login.softAssert().assertTrue(checkedWebElementDisplayed(successfullMsgForReWork));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private boolean checkedWebElementDisplayed(WebElement webelement) {
		return (webelement.isDisplayed());
	}
}
