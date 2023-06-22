package com.prm.pageobjects.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author Ajit Yadav
 */
public class DatePicker {
    public static boolean flag;
    private PCDriver loginPage;

    public DatePicker(PCDriver loginPage) {
        this.loginPage = loginPage;
    }

    public void selectDateofAppCal(String SelectDate, WebElement calenderWeb) {
        @SuppressWarnings("deprecation")
        Date d = new Date(SelectDate);
        Date now = new Date();
        SimpleDateFormat dt = new SimpleDateFormat("MMMM/dd/yyyy");
        String date = dt.format(d);
        String[] split = date.split("/");
        String month = split[0];
        String year = split[2];
        try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        loginPage.executeScript(calenderWeb);
//        calenderWeb.click();

     
        while (true) {
            WebElement next = loginPage.getDriver()
                    .findElement(By.xpath("//input[@id='date']//following-sibling::ul//table//i[contains(@class,'glyphicon glyphicon-chevron-right')]"));
            WebElement prev = loginPage.getDriver()
                    .findElement(By.xpath("//input[@id='date']//following-sibling::ul//table//i[@class='glyphicon glyphicon-chevron-left']"));
            if (!flag) {
                try {
                    loginPage.getDriver().findElement(By.xpath("//input[@id='date']//following-sibling::ul//table//strong[contains(text(),'"+ year +"')]")).isDisplayed();
                    System.out.println("year matches with the calender year==  "+year);
                    flag = true;
                } catch (Exception e) {
                    if (now.before(d)) {
                        loginPage.getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
                        System.out.println("year do not matched with the calender year== "+year);
                        next.click();
                    } else {
                        loginPage.WaitTillElementIsPresent(prev);
                        loginPage.waitForElementToBeClickable(prev);
                        prev.click();
                    }
                }
            } else {
                try {
                    WebElement monthele = loginPage.getDriver()
                            .findElement(By.xpath("//input[@id='date']//following-sibling::ul//table//strong[contains(text(),'"+ month +"')]"));
                    
                    if (monthele.isDisplayed()) {
                    	System.out.println("month matches with calender month == "+month);
                        String startstr = "//input[@id='date']//following-sibling::ul//table//tbody/tr//span[text()='";
                        String endstr = "']";
                        String finalpart = startstr +split[1]+ endstr;
                        WebElement dateEelment = loginPage.getDriver().findElement(By.xpath(finalpart));
                        loginPage.hoverOnElement(dateEelment);
                        loginPage.waitForElementToBeClickable(dateEelment);
                        dateEelment.click();
                        Thread.sleep(3000);
                        break;
                    }
                } catch (Exception e) {
                    if (now.before(d)) {
                    	System.out.print("Month do not match with calender month== "+month);
                        loginPage.getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
                        next.click();
                    } else {
                        loginPage.WaitTillElementIsPresent(prev);
                        loginPage.waitForElementToBeClickable(prev);
                        prev.click();
                    }
                }
            }
        }
    }

    public void appointmentDate(String SelectDate, WebElement calenderWeb, String id) {
         @SuppressWarnings("deprecation")
        Date d = new Date(SelectDate);
        Date now = new Date();
        SimpleDateFormat dt = new SimpleDateFormat("MMMM/dd/yyyy");
        String date = dt.format(d);
        String[] split = date.split("/");
        String month = split[0];
        String year = split[2];
        calenderWeb.click();
        
        while (true) {
            WebElement next = loginPage.getDriver()
                    .findElement(By.xpath("//input[@id='" + id + "']//following::ul//table//i[contains(@class,'glyphicon glyphicon-chevron-right')]"));
            WebElement prev = loginPage.getDriver()
                    .findElement(By.xpath("//input[@id='" + id + "']//following::ul//table//i[contains(@class,'glyphicon glyphicon-chevron-left')]"));
            if (!flag) {
                try {
                    loginPage.getDriver().findElement(By.xpath("//input[@id='" + id + "']//following-sibling::ul//table//strong[contains(text(),'" + year + "')]")).isDisplayed();
                    flag = true;
                } catch (Exception e) {
                    if (now.before(d)) {
                        loginPage.getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
                        next.click();
                        
                    } else {
                        loginPage.WaitTillElementIsPresent(prev);
                        loginPage.waitForElementToBeClickable(prev);
                        prev.click();
                    }
                }
            } else {
                try {
                    WebElement monthEle = loginPage.getDriver()
                            .findElement(By.xpath("//input[@id='" + id + "']//following-sibling::ul//table//strong[contains(text(),'" + month + "')]"));
                    if (monthEle.isDisplayed()) {
//                        String startStr = "//input[@id='"+ id +"']//following-sibling::ul//table//tbody/tr//span[text()='";
                    	String startStr = "//input[@id='"+ id +"']//following-sibling::ul//button[not(contains(@class,'prevDateDisabled'))]/span[text()='";
                        String endStr = "']";
                        String finalPart = startStr +split[1]+ endStr;
                        WebElement dateElement = loginPage.getDriver().findElement(By.xpath(finalPart));
                        loginPage.hoverOnElement(dateElement);
                        loginPage.waitForElementToBeClickable(dateElement);
                        dateElement.click();
                        Thread.sleep(3000);
                        break;
                    }
                } catch (Exception e) {
                    if (now.before(d)) {
                        loginPage.getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
                        next.click();
                    } else {
                        loginPage.WaitTillElementIsPresent(prev);
                        loginPage.waitForElementToBeClickable(prev);
                        prev.click();
                    }
                }
            }
        }
    }

    public String getCurrentLocalDateTimeStamp() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm"));
    }

}
