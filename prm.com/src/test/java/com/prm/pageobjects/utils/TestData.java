package com.prm.pageobjects.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.time.DateUtils;

public class TestData {
    private static final TestData instance = new TestData();
    private static Properties properties = new Properties();

    private TestData() {
    }

    public static synchronized TestData getInstance() {
        try {
            File file = new File(".\\testData.properties");
            FileInputStream fileInput = new FileInputStream(file);
            properties.load(fileInput);
            fileInput.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return instance;
    }
    public String getInputData(String mandatory_Info){
        return properties.getProperty(mandatory_Info);
    }
    
    /*Today Date is in format of "dd-MM-yy" */
    public String getTodayDate(){
        Date now = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yy");
        String date = simpleDateFormat.format(now);
        return date;
    }
    
    public String getFewMinuteBackFromCurrentDateTime() {
    	int addMinuteTime=-3;
    	Date now = new Date();
    	Date targetTime = DateUtils.addMinutes(now, addMinuteTime);
    	SimpleDateFormat simpleDateTimeFormat = new SimpleDateFormat("dd-MM-yy h:mm a");
    	String dateTime = simpleDateTimeFormat.format(targetTime);
    	return dateTime;
    }
    
    public String getFewMinuteAfterFromCurrentDateTime() {
    	int addMinuteTime=8;
    	Date now = new Date();
    	Date targetTime = DateUtils.addMinutes(now, addMinuteTime);
    	SimpleDateFormat simpleDateTimeFormat = new SimpleDateFormat("dd-MM-yy h:mm a");
    	String dateTime = simpleDateTimeFormat.format(targetTime);
    	return dateTime;
    }
}
