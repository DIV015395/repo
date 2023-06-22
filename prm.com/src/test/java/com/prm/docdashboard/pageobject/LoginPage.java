package com.prm.docdashboard.pageobject;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.prm.pageobjects.utils.PCDriver;
import com.prm.pageobjects.utils.ReadConfig;

/**
 * 
 * @author Ajit Yadav
 *
 */
public class LoginPage extends PCDriver {

	public static final String testCaseRunningStatusmsg = "Test Case Started";
	public static final String passwordMsg = "Password Entered";
	public static final String browserMsg = "Browser Invoked";
	public static final String loginBtnClickMsg = "Login Button Clicked";
	public static final String userNameMsg = "UserName Entered";

	public LoginPage() {
		try {
			invokeBrowser(ReadConfig.getInstance().getBrowser());
		} catch (IOException e) {
			e.printStackTrace();
		}
		PageFactory.initElements(getDriver(), this);
	}

	@FindBy(id = "username_lgn")
	private WebElement unTxBx;
	@FindBy(id = "pwd_lgn")
	private WebElement pwdTxBx;
	@FindBy(id = "loginButton")
	private WebElement loginBtn;

	public void login(String username, String password) {
		waitForPageLoad();
		waitForElementToBeClickable(unTxBx);
		unTxBx.sendKeys(username);
		waitForElementToBeClickable(unTxBx);
		pwdTxBx.sendKeys(password);
		waitForElementToBeClickable(loginBtn);
		executeScript(loginBtn);
	}

}
