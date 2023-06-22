package com.prm.patientdashboard.pageobject;

import java.awt.AWTException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.prm.pageobjects.utils.PCDriver;

public class LabWorkOrderEditPage {
	private PCDriver login;
	public LabWorkOrderEditPage(PCDriver login) {
		this.login=login;
		PageFactory.initElements(login.getDriver(), this);
	}

	/*Web Elements in Edit screen*/
	@FindBy(xpath="//h1[@class='pull-left ng-binding']")
	private WebElement headerOfPage;
	@FindBy(xpath="//h1[@class='pull-left']")
	private WebElement headerOfReWorkPage;

	/*Work Details Panel WebElements*/
	
	@FindBy(xpath="//span[contains(text(),'Work Details')]")
	private WebElement workDetailsPanel;
	@FindBy(xpath="//label[@for='clinicName']")
	private WebElement clinicNameInEdit;
	@FindBy(xpath="//div[contains(@class,'source_txt wrd-brk-all')]")
	private WebElement sourceInEdit;
	@FindBy(xpath="//div[contains(@class,'lab_txt')]")
	private WebElement labNoInEdit;
	@FindBy(xpath="//label[@for='Lab']")
	private WebElement labInEdit;
	@FindBy(id="Lab")
	private WebElement labDrpDwn;
	@FindBy(xpath="//label[@for='OrderType']")
	private WebElement orderTypeInEdit;
	@FindBy(xpath="//label[text()='Order Type']/following-sibling::div")
	private WebElement orderTypeTxtBxInEdit;
	@FindBy(xpath="//label[text()='Work Details']/following-sibling::div")
	private WebElement workDetailsTxtBxInEdit;
	@FindBy(xpath="//label[@for='WorkDetails']")
	private WebElement workDetailsInEdit;
	@FindBy(xpath="//label[@for='OtherInfo']")
	private WebElement otherInfoInEdit;
	@FindBy(xpath="//label[text()='Other Info']/following-sibling::div")
	private WebElement otherInfoTxtBxInEdit;
	@FindBy(xpath="//label[text()='Item(s) Send']")
	private WebElement itemSendInEdit;
	@FindBy(className="dropdown-toggle")
	private WebElement itemSendDrpDwn;
	@FindBy(id="updationReason")
	private WebElement updateReasonTxtBx;
	@FindBy(xpath="//span[text()='Add File(s)']")
	private WebElement AddfilesBtnInEdit;
	@FindBy(xpath="//span[@class='cmnicons yes-mdl']")
	private WebElement yesOnDelete;

	/*Additional Panel WebElements */
	@FindBy(xpath="//span[contains(text(),'Additional Details')]")
	private WebElement additionalDetailsPanel;
	@FindBy(xpath="//label[text()='Require']")
	private WebElement requireInEdit;
	@FindBy(id="require")
	private WebElement requireDrpDwn;
	@FindBy(xpath="//label[@for='Shade']")
	private WebElement shadeInEdit;
	@FindBy(xpath="//label[@for='classic']")
	private WebElement classicInEdit;
	@FindBy(id="shade")
	private WebElement shadeDrpDwn;
	@FindBy(xpath="//label[@for='3d']")
	private WebElement threeDInEdit;
	@FindBy(xpath="//label[@for='na']")
	private WebElement naInEdit;
	@FindBy(xpath="//label[text()='Stains Internal']")
	private WebElement stainsInternalInEdit;
	@FindBy(id="Stains Internal")
	private WebElement stainsInternalDrpDwn;
	@FindBy(xpath="//label[text()='Stains External']")
	private WebElement stainsExternalInEdit;
	@FindBy(id="Stains External")
	private WebElement stainsExternalDrpDwn;
	@FindBy(xpath="//label[text()='Translucency']")
	private WebElement translucencyInEdit;
	@FindBy(id="address")
	private WebElement translucencyTxtBx;
	@FindBy(xpath="//label[text()='Texture']")
	private WebElement textureInEdit;
	@FindBy(id="texture")
	private WebElement textureTxtBx;
	@FindBy(xpath="//label[text()='Configurations']")
	private WebElement configurationsInEdit;
	@FindBy(xpath="//label[text()='Individual']")
	private WebElement individualInEdit;
	@FindBy(xpath="//label[text()='Splinted (Joined)']")
	private WebElement splintedJoinedInEdit;
	@FindBy(xpath = "//span[text()='Buccal']")
	private WebElement Buccal;
	@FindBy(xpath = "//span[text()='Distal']")
	private WebElement Distal;
	@FindBy(xpath = "//span[text()='Mesial']")
	private WebElement Mesial;
	@FindBy(xpath = "//span[text()='Lingual']")
	private WebElement Lingual;
	@FindBy(xpath = "//div[@class='content']")
	private List<WebElement> Provisinals;
	@FindBy(xpath = "//div[contains(@ng-click,'removeSelectedConfiguration')]//a[@class='remove-button']")
	private List<WebElement> provisionalRemoveBtnToothSite;
	@FindBy(xpath="//label[text()='Notes']")
	private WebElement notesInEdit;
	@FindBy(id="crownnotes")
	private WebElement notesTxtBx;

	/*Buttons in LabWorkOrder Edit Screen*/
	@FindBy(xpath="(//i[text()='Save as Draft'])[1]")
	private WebElement saveAsDraftInEdit;
	@FindBy(xpath="(//i[text()='Submit to Lab'])[1]")
	private WebElement submittedToLabInEdit;
	@FindBy(xpath="(//i[text()='Reset'])[1]")
	private WebElement resetInEdit;
	@FindBy(xpath="(//i[text()='Cancel'])[1]")
	private WebElement cancelInEdit;

	/*--------Functions----------------*/

	/*checked LWO edit page and also work detail panel*/
	public void checkedAllFieldsInWorkDetailPanel() {
		login.waitForPageLoad();
		try {
			Thread.sleep(3000);
			Assert.assertTrue(checkedWebElementDisplayed(workDetailsPanel) && checkedWebElementDisplayed(clinicNameInEdit) &&
					checkedWebElementDisplayed(sourceInEdit) && checkedWebElementDisplayed(labNoInEdit) &&
					checkedWebElementDisplayed(labInEdit) && checkedWebElementDisplayed(orderTypeInEdit) &&
					checkedWebElementDisplayed(workDetailsInEdit) && checkedWebElementDisplayed(otherInfoInEdit) &&
					checkedWebElementDisplayed(itemSendInEdit) && checkedWebElementDisplayed(AddfilesBtnInEdit));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*Verify header of LWO edit page*/
	public void verifyHeaderOfPage(String header) {
		login.waitForPageLoad();
		login.WaitTillElementIsPresent(headerOfPage);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Assert.assertTrue(headerOfPage.getText().contains(header));
	}

	/*Verify header of LWO ReWork page*/
	public void verifyHeaderOfReWorkPage(String header) {
		login.waitForPageLoad();
		login.WaitTillElementIsPresent(headerOfReWorkPage);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Assert.assertTrue(headerOfReWorkPage.getText().contains(header));
	}

	/*Verify Work Details Panel*/
	public void detailsPanel(String deatils) {
		login.waitForPageLoad();
		Assert.assertTrue(workDetailsPanel.getText().contains(deatils));
	}

	/*checking clinic name in LWO Edit screen*/
	public void clinicName(String clinicName) {
		login.waitForPageLoad();
		String str = clinicNameInEdit.getText();
		String[] split = str.split(":");
		Assert.assertTrue(split[0].contains("Clinic Name"));
		Assert.assertEquals(split[1].trim(), clinicName);
	}

	/*checking source in LWO Edit screen */
	public void source(String source) {
		login.waitForPageLoad();
		String str = sourceInEdit.getText();
		String[] split = str.split(":");
		String actual_source=split[1].trim();
		Assert.assertTrue(split[0].contains("Source"));
		Assert.assertEquals(actual_source, source);
	}

	/*checking LWO No. in LWO Edit screen */
	public void labWorkOrderNo() {
		login.waitForPageLoad();
		String str = labNoInEdit.getText();
		String[] split = str.split("-");
		try {
			String actual_lab=split[0].trim();
			boolean flag = actual_lab.contains("Lab Work Order No.:") && !(split[1].equals("NA") || split[1].equals("Null"));
			Assert.assertTrue(flag);
		}
		catch(ArrayIndexOutOfBoundsException object1) {
		}
	}

	/*checking lab Dropdown Edit screen */
	public void checkLabDropDwn(String instanceName) {
		login.waitForPageLoad();
		boolean flag1,flag2, match= false;
		flag1=checkedWebElementDisplayed(labDrpDwn);
		if(instanceName.equals("Prm2")) {
			String[] exp = { "Select Lab", "Dentcare - Ahemedabad", "Dentcare - Jaipur", "Dentcare - Pune", "Illusion Dental", "Jaipur ceramic lab","Sculpdent - Pune","Corona Dental Lab","Dentocraft","RR Dental Lab","Real Dental Lab","Shivam Dental Lab","Raj Laboratory","Pinkcity Ceramic Dental Lab"};
			Select select = new Select(labDrpDwn);
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
		else {
			String[] exp = { "Select Lab", "Dentcare - Ahemedabad", "Dentcare - Jaipur", "Dentcare - Pune", "Illusion Dental", "Jaipur ceramic lab","Poona Dental Lab - Pune","Sculpdent - Pune","Dentocraft"};
			Select select = new Select(labDrpDwn);
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
	}

	/*Select the lab from Lab dropdown field*/
	public void selectLab(String labValue) {
		login.waitForPageLoad();
		try {
			Thread.sleep(3000);
			login.selectFromDropDownByVisibleText(labDrpDwn, labValue);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*Get Lab from lab dropdown when we redirect from lab edit screen*/
	public void getLabDrpDwn(String expectedLabName) {
		login.waitForPageLoad();
		Assert.assertTrue(login.firstSelectedOption(labDrpDwn).contains(expectedLabName));
	}

	/*checking Lab DropDown field on LWO ReWork Screen*/
	public void checkLabForReWork() {
		login.waitForPageLoad();
		WebElement ele = login.getDriver().findElement(By.xpath("//select[@disabled='disabled']"));
		Assert.assertTrue(checkedWebElementDisplayed(ele));
	}

	/*checking orderType field on Edit Screen*/
	public void checkOrderType(String orderType) {
		login.waitForPageLoad();
		login.waitForElementToBeClickable(orderTypeTxtBxInEdit);
		String ele = orderTypeTxtBxInEdit.getText();
		Assert.assertEquals(ele.trim(), orderType);
	}

	/*checking WorkDetails field on Edit Screen*/
	public void checkWorkDetails(String workDetails) {
		login.waitForPageLoad();
		login.waitForElementToBeClickable(workDetailsTxtBxInEdit);
		String ele = workDetailsTxtBxInEdit.getText();
		Assert.assertEquals(ele.trim(), workDetails);
	}

	/*checking OtherInfo field on Edit Screen*/
	public void checkOtherInfo(String otherInfo) {
		login.waitForPageLoad();
		login.waitForElementToBeClickable(otherInfoTxtBxInEdit);
		String ele = otherInfoTxtBxInEdit.getText();
		Assert.assertEquals(ele.trim(), otherInfo);
	}

	/*checking ItemsSent field on Edit Screen*/
	public void checkItemSendDrpDwn() {
		login.waitForPageLoad();
		Assert.assertTrue(checkedWebElementDisplayed(itemSendInEdit)&& checkedWebElementDisplayed(itemSendDrpDwn)
				&&itemSendDrpDwn.getAttribute("tooltip").contains("Select Item"));
	}

	/*-- Click on Item Send DropDown--*/
	public void clickOnItemSend() {
		login.waitForPageLoad();
		try {
			Thread.sleep(3000);
			itemSendDrpDwn.click();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*Select the ItemsSent from ItemsSent dropdown field*/
	public void selectItemsSend(String itemSendValue) {
		login.waitForPageLoad();
		List<WebElement> list=login.getDriver().findElements(By.xpath("//ul[contains(@class,'multi-dropdown-Width')]//li//a//span[@class='ng-binding']"));
		System.out.println(list.size());

		for(int i=0;i<list.size();i++) {
			if(list.get(i).getText().contains(itemSendValue)) {
				list.get(i).click();
				break;
			}
		}
	}

	/*Get Item Send from Item Send dropdown when we redirect from lab edit screen*/
	public void getItemsSendDrpDwn(String itemSend) {
		login.waitForPageLoad();
		List<WebElement> list=login.getDriver().findElements(By.xpath("//ul[contains(@class,'multi-dropdown-Width')]//li//a//span[@class='ng-binding']"));
		String ele;
		for(int i=0;i<list.size();i++) {

			if(list.get(i).getText().contains(itemSend)) {
				ele=list.get(i).getText();
				Assert.assertEquals(ele, itemSend);
				break;
			}
		}
	}
	
	/*Enter Updation Reason into Reason Textfields*/
	public void addUpdateReason(String reason) {
		login.waitForPageLoad();
		updateReasonTxtBx.clear();
		updateReasonTxtBx.sendKeys(reason);
	}
	
	/*Attach single file from system*/
	public void addFile(String path){
		login.waitForPageLoad();
		AddfilesBtnInEdit.click();
		try {
			AddfilesBtnInEdit.click();
			Thread.sleep(2000);
			login.uploadFile(path);
			Thread.sleep(2000);
		} catch (AWTException e) {
			e.printStackTrace();
		}catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/*Get a FileName shown on LWO Edit Screen*/
	public void getFile(String fileName) {
		login.waitForPageLoad();
		String ele = login.getDriver().findElement(By.xpath("//span[text()='"+fileName+"']")).getText();
		Assert.assertEquals(ele, fileName);
	}

	/*Delete a File shown on LWO Edit Screen*/
	public void deleteFile(String fileName) {
		login.waitForPageLoad();
		login.getDriver().findElement(By.xpath("//span[text()='"+fileName+"']/../a")).click();
	}

	/*Click on Yes after click on Delete button shown on File Name*/
	public void clickOnYesDelete() {
		login.waitForPageLoad();
		Assert.assertTrue(login.getDriver().findElement(By.xpath("//p[text()='Do you want to delete this file?']")).isDisplayed());
		login.waitForElementToBeClickable(yesOnDelete);
		yesOnDelete.click();
	}

	/*checked Additional detail panel*/
	public void checkedAdditionalDetailPanel() {
		login.waitForPageLoad();
		try {
			Thread.sleep(3000);
			Assert.assertTrue(checkedWebElementDisplayed(additionalDetailsPanel));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		additionalDetailsPanel.click();
		login.scrollPage(350);
	}

	/*checked Additional detail panels all fields*/
	public void checkedAllFieldsInAdditionalDetailPanel() {
		login.waitForPageLoad();
		try {
			Thread.sleep(3000);
			Assert.assertTrue(checkedWebElementDisplayed(requireInEdit) && checkedWebElementDisplayed(shadeInEdit) &&
					checkedWebElementDisplayed(classicInEdit) && checkedWebElementDisplayed(threeDInEdit) &&
					checkedWebElementDisplayed(naInEdit) && checkedWebElementDisplayed(stainsInternalInEdit) &&
					checkedWebElementDisplayed(stainsExternalInEdit) && checkedWebElementDisplayed(translucencyInEdit) &&
					checkedWebElementDisplayed(textureInEdit) && checkedWebElementDisplayed(configurationsInEdit)
					&& checkedWebElementDisplayed(individualInEdit) && checkedWebElementDisplayed(splintedJoinedInEdit)
					&& checkedWebElementDisplayed(notesInEdit));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*checking all option in Require Dropdown present in Additional details Panel*/
	public void checkRequireDropDwn() {
		login.waitForPageLoad();
		boolean flag1,flag2, match= false;
		flag1=checkedWebElementDisplayed(requireDrpDwn);
		String[] exp = { "Select Require", "Metal trial", "Bisque/Unglazed trial", "Final prosthesis"};
		Select select = new Select(requireDrpDwn);
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

	/*Select the Require from Require dropdown field*/
	public void selectRequire(String requireValue) {
		login.waitForPageLoad();
		try {
			Thread.sleep(2000);
			login.selectFromDropDownByVisibleText(requireDrpDwn, requireValue);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*Get Require from Require dropdown when we redirect from lab edit screen*/
	public void getRequireDrpDwn(String expectedRequire) {
		login.waitForPageLoad();
		Assert.assertTrue(login.firstSelectedOption(requireDrpDwn).contains(expectedRequire));
	}

	/*Select Shade i.e either Classic,3D or NA */
	public void selectShade(String shade) {
		login.waitForPageLoad();
		WebElement ele = login.getDriver().findElement(By.xpath("//label[text()='"+shade+"']"));
		ele.click();
	}

	/*checking Shade Option Dropdown When we Select any Shade one dropdown gets open*/
	public void checkShadeDropDwn() {
		login.waitForPageLoad();
		boolean flag1,flag2, match= false;
		flag1=checkedWebElementDisplayed(shadeDrpDwn);
		String[] exp = { "Select Shade", "A1", "A2", "A3", "A3.5", "A4","B1","B2","B3","B4","C1","C2","C3","C4","D2","D3","D4"};
		Select select = new Select(shadeDrpDwn);
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

	/*Select the Shade Option Dropdown from Shade dropdown field*/
	public void selectShadeOptionFromDrpDwn(String shadeValue) {
		login.waitForPageLoad();
		try {
			Thread.sleep(2000);
			login.selectFromDropDownByVisibleText(shadeDrpDwn, shadeValue);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*Get Shade Option from Shade option Dropdown when we redirect from lab edit screen*/
	public void getShadeOptionDrpDwn(String expectedShade) {
		login.waitForPageLoad();
		Assert.assertTrue(login.firstSelectedOption(shadeDrpDwn).contains(expectedShade));
	}

	/*checking all option in Stains Internal Dropdown present in Additional details Panel*/
	public void checkStainsInternalDropDwn() {
		login.waitForPageLoad();
		boolean flag1,flag2, match= false;
		flag1=checkedWebElementDisplayed(stainsInternalDrpDwn);
		String[] exp = { "Select Stains Internal", "White Spots"};
		Select select = new Select(stainsInternalDrpDwn);
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

	/*Select the Stains Internal from Stains Internal dropdown field*/
	public void selectStainsInternal(String stainsInternalValue) {
		login.waitForPageLoad();
		try {
			Thread.sleep(2000);
			login.selectFromDropDownByVisibleText(stainsInternalDrpDwn, stainsInternalValue);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*Get Stains Internal from Stains Internal Dropdown when we redirect from lab edit screen*/
	public void getStainsInternalDrpDwn(String expectedStainsInternal) {
		login.waitForPageLoad();
		Assert.assertTrue(login.firstSelectedOption(stainsInternalDrpDwn).contains(expectedStainsInternal));
	}

	/*checking all option in Stains External Dropdown present in Additional details Panel*/
	public void checkStainsExternalDropDwn() {
		login.waitForPageLoad();
		boolean flag1,flag2, match= false;
		flag1=checkedWebElementDisplayed(stainsExternalDrpDwn);
		String[] exp = { "Select Stains External", "Brown"};
		Select select = new Select(stainsExternalDrpDwn);
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

	/*Select the Stains External from Stains External dropdown field*/
	public void selectStainsExternal(String stainsExternalValue) {
		login.waitForPageLoad();
		try {
			Thread.sleep(2000);
			login.selectFromDropDownByVisibleText(stainsExternalDrpDwn, stainsExternalValue);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	/*Get Stains External from Stains External Dropdown when we redirect from lab edit screen*/
	public void getStainsExternalDrpDwn(String expectedStainsExternal) {
		login.waitForPageLoad();
		Assert.assertTrue(login.firstSelectedOption(stainsExternalDrpDwn).contains(expectedStainsExternal));
	}

	/*checking Translucency,texture and Notes Textfields present in Additional details Panel*/
	public void checkedTranslucencyTextureAndNotes() {
		login.waitForPageLoad();
		Assert.assertTrue(checkedWebElementDisplayed(translucencyTxtBx)&&translucencyTxtBx.getAttribute("placeholder").contains("translucency")
				&& checkedWebElementDisplayed(textureTxtBx) && textureTxtBx.getAttribute("placeholder").contains("texture")
				&& checkedWebElementDisplayed(notesTxtBx) && notesTxtBx.getAttribute("placeholder").contains("notes"));
	}

	/*Enter data into Translucency Textfields*/
	public void addTransluency(String transluency) {
		login.waitForPageLoad();
		translucencyTxtBx.clear();
		translucencyTxtBx.sendKeys(transluency);
	}

	/*Get Translucency from Translucency Textbox when we redirect from lab edit screen*/
	public void getTranslucency(String expected) {
		login.waitForPageLoad();
		login.waitForElementToBeClickable(translucencyTxtBx);
		JavascriptExecutor javascriptExecutor = (JavascriptExecutor) login.getDriver();
		Object obj = javascriptExecutor.executeScript("return document.getElementById('address').value;");
		String actual = (String) obj;
		Assert.assertEquals(actual, expected);
	}

	/*Enter data into texture Textfields*/
	public void addTexture(String texture) {
		login.waitForPageLoad();
		textureTxtBx.clear();
		textureTxtBx.sendKeys(texture);
	}

	/*Get Texture from Texture Textbox when we redirect from lab edit screen*/
	public void getTexture(String expected) {
		login.waitForPageLoad();
		login.waitForElementToBeClickable(textureTxtBx);
		JavascriptExecutor javascriptExecutor = (JavascriptExecutor) login.getDriver();
		Object obj = javascriptExecutor.executeScript("return document.getElementById('texture').value;");
		String actual = (String) obj;
		Assert.assertEquals(actual, expected);
	}

	/*Select Configurations */
	public void selectConfigurations(String configuration,String splitedOptions) {
		login.waitForPageLoad();
		if(configuration.equals("Individual"))
			individualInEdit.click();
		else
		{
			splintedJoinedInEdit.click();
			WebElement ele = login.getDriver().findElement(By.xpath("//label[text()='"+splitedOptions+"']"));
			ele.click();
		}
	}

	/*-- Click on Buccal --*/
	public void clickOnBuccal() {
		login.waitForPageLoad();
		try {
			Thread.sleep(3000);
			login.waitForElementToBeClickable(Buccal);
			Buccal.click();
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*-- Click on Distal --*/
	public void clickOnDistal() {
		login.waitForPageLoad();
		login.waitForElementToBeClickable(Distal);
		Distal.click();
	}

	/*-- Click on Mesial --*/
	public void clickOnMesial() {
		login.waitForPageLoad();
		login.waitForElementToBeClickable(Mesial);
		Mesial.click();
	}

	/*-- Click on Lingual --*/
	public void clickOnLingual() {
		login.waitForPageLoad();
		login.waitForElementToBeClickable(Lingual);
		Lingual.click();
	}

	/*-- Checking on Provisinals --*/
	public void verifyProvisinals() {
		login.waitForPageLoad();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Assert.assertTrue(Provisinals.size() > 0);
	}

	/*-- Select the Tooth Sites--*/
	public void selectedToothAndSiteProvisionalDisplayedBelow() {
		login.waitForPageLoad();
		Assert.assertTrue(provisionalRemoveBtnToothSite.size() == 1 || provisionalRemoveBtnToothSite.size() == 2
				|| provisionalRemoveBtnToothSite.size() == 3 || provisionalRemoveBtnToothSite.size() == 4
				|| provisionalRemoveBtnToothSite.size() == 5 || provisionalRemoveBtnToothSite.size() == 6
				|| provisionalRemoveBtnToothSite.size() == 7);
	}

	/*-- Click on Tooth Site and Perio Provisionals --*/
	public void clickOnToothSiteAndPerioProvisinals() {
		login.waitForPageLoad();
		try {
			Thread.sleep(2000);
			login.getDriver().findElement(By.xpath("(//div[@class='content'])[1]")).click();
		} catch (StaleElementReferenceException | InterruptedException e) {
			login.getDriver().findElement(By.xpath("(//div[@class='content'])[1]")).click();
		}
	}

	/*-- Verifying Provisinals is selected or not--*/
	public void verifyProvisinalIsSelected(String name) {
		login.waitForPageLoad();
		Assert.assertTrue(login.getDriver().findElement(By.xpath("//span[text()='" + name + "']/.."))
				.getAttribute("class").contains("selected"));
	}

	/*Enter data into Notes Textfields*/
	public void enterNotes(String notes) {
		login.waitForPageLoad();
		notesTxtBx.clear();
		notesTxtBx.sendKeys(notes);
		login.scrollPage(350);
	}

	/*Get Notes from Notes Textbox when we redirect from lab edit screen*/
	public void getNotes(String notes) {
		login.waitForPageLoad();
		login.waitForElementToBeClickable(notesTxtBx);
		JavascriptExecutor javascriptExecutor = (JavascriptExecutor) login.getDriver();
		Object obj = javascriptExecutor.executeScript("return document.getElementById('crownnotes').value;");
		String actual = (String) obj;
		Assert.assertEquals(actual, notes);
		login.scrollPage(350);
	}

	/*checking Save as Draft,Submitted to Lab,Cancel and reset button shown in LWO edit screen*/
	public void checkedActionButtonInEdit() {
		login.waitForPageLoad();
		Assert.assertTrue(checkedWebElementDisplayed(saveAsDraftInEdit) && checkedWebElementDisplayed(submittedToLabInEdit) && checkedWebElementDisplayed(cancelInEdit) && checkedWebElementDisplayed(resetInEdit));
	}

	/*Click the cancel button shown in LWO edit screen*/
	public void clickOnCancelBtn() {
		login.waitForElementToBeClickable(cancelInEdit);
		try {
			Thread.sleep(3000);
			cancelInEdit.click();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*click button save as Draft button*/
	public void clickSaveAsDraft() {
		login.waitForPageLoad();
		saveAsDraftInEdit.click();
	}

	/*click button Submitted to Lab button*/
	public void clickSubmittedToLab() {
		login.waitForPageLoad();
		submittedToLabInEdit.click();
	}

	/*Click the reset button shown in LWO edit screen*/
	public void clickOnResetBtn() {
		login.waitForElementToBeClickable(resetInEdit);
		try {
			Thread.sleep(3000);
			resetInEdit.click();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private boolean checkedWebElementDisplayed(WebElement webelement) {
		return(webelement.isDisplayed());
	}
}
