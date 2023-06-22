package com.prm.docdashboard.pageobject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

/**
 * 
 * @author Ajit
 *
 */
public class AppointmentSchedulerCalenderPage {
	private LoginPage loginPage;

	public AppointmentSchedulerCalenderPage(LoginPage loginPage) {
		this.loginPage = loginPage;
		PageFactory.initElements(loginPage.getDriver(), this);
	}

	@FindBy(xpath = "//div[text()='Day']")
	private WebElement dayBtn;
	@FindBy(className = "fancybox-iframe")
	private WebElement frame;
	@FindBy(className = "dhx_cal_next_button")
	private WebElement nextBtn;
	@FindBy(xpath = "//div[contains(@id,'11:00')]")
	private WebElement slotTime;
	@FindBy(xpath = "//p[contains(text(),'Appointment date/time selected has already booked!')]")
	private WebElement alreadyBookedMsg;
	@FindBy(xpath = "//span[text()='OK']")
	private WebElement okBtn;
	@FindBy(xpath = "//div[@class='fancybox-outer']/../a[@class='fancybox-item fancybox-close']")
	private WebElement closeBtn;
	@FindBy(xpath = "//div[@class='dhx_scale_bar']")
	private List<WebElement> weekDays;
	@FindBy(xpath = "//div[@class='dhx_scale_slots']")
	private List<WebElement> timeSlotOnScheduler;
	@FindBy(id = "clinicApptDoctorDDL")
	private WebElement DocDrpDwmScheduler;

	public void checkAppointment(String patient,String timeSlot){
		loginPage.waitForPageLoad();
		String str=timeSlot+": "+patient;
		Assert.assertTrue(loginPage.getDriver().findElement(By.xpath("//span[contains(text(),'"+str+"')]/../preceding-sibling::div[contains(text(),'"+timeSlot+"')]")).isDisplayed());
	}

	public void selectTimeSlot(String timeSlot) {
		loginPage.waitForElementToBeClickable(dayBtn);
		dayBtn.click();
		WebElement timeSlotsInScheduler = loginPage.getDriver().findElement(By.xpath("(//div[text()='Operatory 2']/../..//div[contains(text(),'" + timeSlot + "')])[2]"));
		loginPage.waitForElementToBeClickable(timeSlotsInScheduler);
		timeSlotsInScheduler.click();
		loginPage.switchToFrame(frame);
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebElement slot=loginPage.getDriver().findElement(By.xpath("//div[contains(@id,'"+timeSlot+"')]"));
		loginPage.waitForElementToBeClickable(slot);
		slot.click();
		loginPage.waitForPageLoad();
		if (loginPage.getTitle().contains("Appointment Scheduler Calender")) {
			loginPage.getDriver()
					.findElement(By.xpath("//p[text()='This time slot is already booked']/..//span[text()='OK']"))
					.click();
			loginPage.waitForPageLoad();
			loginPage.getDriver().findElement(By.xpath("(//span[@class='dashboardDeleteIcon'])[1]")).click();
			loginPage.waitForElementToBeClickable(timeSlotsInScheduler);
			timeSlotsInScheduler.click();
			loginPage.switchToFrame(frame);
			try {
				Thread.sleep(4000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			loginPage.waitForElementToBeClickable(slot);
			slot.click();
		}
		loginPage.switchToDefaultContent();
		loginPage.waitForPageLoad();
	}

	public void eventOnSchedular(String SelectDate) {
		loginPage.waitForPageLoad();
		Date d = new Date(SelectDate);
		SimpleDateFormat dt = new SimpleDateFormat("MMMM/dd/yyyy");
		SimpleDateFormat dt1 = new SimpleDateFormat("EEE");
		String dayString = dt1.format(d);
		String date = dt.format(d);
		String[] split = date.split("/");
		String month = split[0];
		String day = split[1];
		String monthAndDay = dayString + ", " + month + " " + Integer.parseInt(day);
		for (int i = 0; i < weekDays.size(); i++) {
			System.out.println(weekDays.get(i).getText());
			if (weekDays.get(i).getText().contains(monthAndDay)) {
				++i;
				String str1 = "(//div[contains(text(),'" + monthAndDay + "')]/../..//div[text()='11:00-13:00'])[";
				String str2 = "]";
				String xpath = str1 + i + str2;
				loginPage.waitForPageLoad();
				loginPage.getDriver().findElement(By.xpath(xpath)).click();
				loginPage.switchToFrame(frame);
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				loginPage.WaitTillElementIsPresent(slotTime);
				slotTime.click();
				loginPage.WaitTillElementIsPresent(alreadyBookedMsg);
				alreadyBookedMsg.isDisplayed();
				okBtn.click();
				loginPage.switchToDefaultContent();
				loginPage.WaitTillElementIsPresent(closeBtn);
				closeBtn.click();
			}
		}
	}

	public void timeSlotWhichIsNotAvailableOnwscheduler(String doctor, String SelectDate, String hour, String minuts,
			String amPm) {
		loginPage.waitForPageLoad();
		loginPage.WaitTillElementIsPresent(dayBtn);
		loginPage.hoverOnElement(dayBtn);
		dayBtn.click();
		loginPage.waitForPageLoad();
		loginPage.WaitTillElementIsPresent(DocDrpDwmScheduler);
		loginPage.selectFromDropDownByVisibleText(DocDrpDwmScheduler, doctor);
		try {
			Thread.sleep(3000);
			Date d = new Date(SelectDate);
			SimpleDateFormat dt = new SimpleDateFormat("MMM/dd/yyyy");
			String date = dt.format(d);
			String[] split = date.split("/");
			String finaldate = Integer.parseInt(split[1]) + " " + split[0] + " " + split[2];
			System.out.println(finaldate + " final date");
			Thread.sleep(3000);
			try {
				boolean flag = loginPage.getDriver().findElement(By.xpath("//div[contains(text(),'" + finaldate + "')]"))
						.isDisplayed();
				while (!flag) {
					if (!flag) {
						loginPage.waitForElementToBeClickable(nextBtn);
						nextBtn.click();
					} else {
						break;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			String time = hour + ":" + minuts + " " + amPm;
			SimpleDateFormat displayFormat = new SimpleDateFormat("HH:mm");
			SimpleDateFormat parseFormat = new SimpleDateFormat("hh:mm a");
			Date dat2 = null;
			String dat = displayFormat.format(parseFormat.parse(time));
			System.out.println(dat + " dat");
			dat2 = displayFormat.parse(dat);
			int slotSize = timeSlotOnScheduler.size();
			int opratory1 = slotSize / 2;
			for (int i = 0; opratory1 > i; i++) {
				String slot = timeSlotOnScheduler.get(i).getText();
				String[] split1 = slot.split("-");
				String str1 = split1[0];
				String str2 = split1[1];
				Date beforeTime = null, afterTime = null;
				beforeTime = displayFormat.parse(str1);
				afterTime = displayFormat.parse(str2);
				if (dat2.after(beforeTime) && dat2.before(afterTime)) {
					Assert.assertTrue(false);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
