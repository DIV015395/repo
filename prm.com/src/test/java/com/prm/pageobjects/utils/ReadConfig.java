package com.prm.pageobjects.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * 
 * @author Ajit Yadav
 *
 */
public class ReadConfig {
	private static final ReadConfig instance = new ReadConfig();
	private static Properties properties = new Properties();

	private ReadConfig() {
	}

	public static synchronized ReadConfig getInstance() {
		try {
			File file = new File(".\\qa.properties");
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

	public String getUsername() {
		return properties.getProperty("username");
	}

	public String getPassword() {
		return properties.getProperty("password");
	}

	public String getApplicationUrl() {
		return properties.getProperty("url");
	}

	public String getDriverPath() {
		return properties.getProperty("driver_path");
	}

	public static Properties getProperties() {
		return properties;
	}

	public static void setProperties(Properties properties) {
		ReadConfig.properties = properties;
	}

	public String getBrowser() {
		return properties.getProperty("browser");
	}

	public String getExcelPath() {
		return properties.getProperty("excel_path");
	}

	public String getDatabaseHostUrl() {
		return properties.getProperty("db_host_url");
	}

	public String getDatabaseUsername() {
		return properties.getProperty("db_username");
	}

	public String getDatabasePassword() {
		return properties.getProperty("db_password");
	}

	public String getProperty(String property) {
		return properties.getProperty(property);
	}

	public String getAutomationReportPath() {
		return properties.getProperty("report_path");
	}

}
