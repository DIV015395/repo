package com.prm.pageobjects.utils;

import com.google.auth.Credentials;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.ValueRange;
import com.google.gson.Gson;
import com.google.gson.JsonElement;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SheetTest {

	private static final String APPLICATION_NAME = "Google Sheets API Java Quickstart";
	private static final Gson GSON = new Gson();
	private static final List<String> SCOPES = Collections.singletonList(SheetsScopes.SPREADSHEETS_READONLY);
	private static final String SERVICE_ACCOUNT_FILE_PATH = System.getProperty("user.dir") + File.separator
			+ "src\\main\\resources\\client_secret.json";
	private static final String SPREADSHEET_ID = "1FldGLoTmmfPd7L45Ki-pAgobcZru3gflWc1F4aew8aM";
	
	

	public static Sheets getSheet() throws IOException, GeneralSecurityException {
		HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
		GoogleCredentials credentials = GoogleCredentials.fromStream(new FileInputStream(SERVICE_ACCOUNT_FILE_PATH))
				.createScoped(SCOPES);
		Sheets sheetsService = null;
		if (credentials.refreshAccessToken().getTokenValue() != null) {

			HttpCredentialsAdapter httpCredentialsAdapter = new HttpCredentialsAdapter(credentials);
			sheetsService = new Sheets.Builder(httpTransport, new GsonFactory(), httpCredentialsAdapter)
					.setApplicationName(APPLICATION_NAME).build();
		}
		return sheetsService;
	}
	
	  public static Map<String, String> prepareData(String moduleName, String sheetName, String rowRange, String colRange){
			
			Map<String, String> appnmntData = null;
			try {
				appnmntData = SheetTest.getDataFromGoogleSheet(moduleName, sheetName, rowRange, colRange);
			} catch (GeneralSecurityException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return appnmntData;
		}

	
	public static Map<String, String> getDataFromGoogleSheet(String moduleName, String sheetName, String rowRange,
			String colName) throws GeneralSecurityException, IOException {
		final String range = sheetName+"!"+rowRange+":"+colName;
		ValueRange response = getSheet().spreadsheets().values().get(SPREADSHEET_ID, range).execute();
		List<List<Object>> values = response.getValues();
		Map<String, String> mapData = new HashMap<String, String>();
		if (values == null || values.isEmpty()) {
			System.out.println("No data found.");
		} else {
			switch (moduleName) {
			case "Registration":
				for (List<Object> row : values) {

					mapData.put("patient_name", (String) row.get(1));
					mapData.put("patient_gender", (String) row.get(2));
					mapData.put("patient_age", (String) row.get(3));
					mapData.put("email", (String) row.get(4));
					mapData.put("patient_mobile", (String) row.get(5));
					mapData.put("patient_alternate_name", (String) row.get(6));
					mapData.put("patient_alternate_mobile", (String) row.get(7));
					mapData.put("reaching_time", (String) row.get(8));
					mapData.put("occupationOptn", (String) row.get(9));
					mapData.put("address", (String) row.get(10));
					mapData.put("city", (String) row.get(11));
					mapData.put("state", (String) row.get(12));
					mapData.put("pin_code", (String) row.get(13));
					mapData.put("edit_patient_name", (String) row.get(14));
					mapData.put("edit_patient_email", (String) row.get(15));
					mapData.put("edit_patient_mobile", (String) row.get(16));
				}
				break;

			case "Appointment":
				for (List<Object> row : values) {
					mapData.put("patient_name", (String) row.get(1));
					mapData.put("patient_mobile", (String) row.get(2));
					mapData.put("email", (String) row.get(3));
					mapData.put("pin_code", (String) row.get(4));
					mapData.put("clinicLocation", (String) row.get(5));
					mapData.put("appointmentDate", (String) row.get(6));
					mapData.put("slot", (String) row.get(7));
					mapData.put("duration", (String) row.get(8));
					mapData.put("doctor", (String) row.get(9));
					mapData.put("doctorNickName", (String) row.get(10));
					mapData.put("notes", (String) row.get(11));
					mapData.put("referral", (String) row.get(12));
					mapData.put("chiefComplaint", (String) row.get(13));
					mapData.put("source", (String) row.get(14));
					mapData.put("referalDetails", (String) row.get(15));
					mapData.put("serviceAddress", (String) row.get(16));
					mapData.put("serviceCity", (String) row.get(17));
					mapData.put("servicePincode", (String) row.get(18));
					mapData.put("serviceState", (String) row.get(19));
					mapData.put("serviceClinic", (String) row.get(20));
					mapData.put("updateSlot", (String) row.get(21));
					mapData.put("updateSlotDuration", (String) row.get(22));
					mapData.put("scanCenter", (String) row.get(23));
				}
				break;
				
			case "ChiefComplaint":
				for (List<Object> row : values) {
					mapData.put("patient_name", (String) row.get(1));
					mapData.put("patient_mobile", (String) row.get(2));
					mapData.put("doctor", (String) row.get(3));
					mapData.put("doctorNickName", (String) row.get(4));
					mapData.put("clinicLocation", (String) row.get(5));
					mapData.put("complaint_future_date", (String) row.get(6));
					mapData.put("another_clinicLocation", (String) row.get(7));
				}
				break;
				
			case "Treatment&Plan":
					mapData.put("patient_name",values.get(1).get(0).toString());
					mapData.put("patient_mobile",values.get(1).get(1).toString());
					mapData.put("doctor",values.get(1).get(2).toString());
					mapData.put("doctorNickName",values.get(1).get(3).toString());
					mapData.put("clinicLocation",values.get(1).get(4).toString());
					mapData.put("complaint_future_date",values.get(1).get(5).toString());
					mapData.put("another_clinicLocation",values.get(1).get(6).toString());
					mapData.put("planGroup1",values.get(1).get(7).toString());
					mapData.put("planGroup1_Plan1",values.get(1).get(8).toString());
					mapData.put("planGroup1_Plan1_FullName",values.get(1).get(9).toString());
					mapData.put("planGroup1_Plan2",values.get(2).get(8).toString());
					mapData.put("planGroup1_Plan2_FullName",values.get(2).get(9).toString());
					mapData.put("planGroup2",values.get(3).get(7).toString());
					mapData.put("planGroup2_Plan1",values.get(3).get(8).toString());
					mapData.put("planGroup2_Plan1_FullName",values.get(3).get(9).toString());
					mapData.put("planGroup2_Plan2",values.get(4).get(8).toString());
					mapData.put("planGroup2_Plan2_FullName",values.get(4).get(9).toString());
					mapData.put("planGroup3",values.get(7).get(7).toString());
					mapData.put("planGroup3_Plan1",values.get(7).get(8).toString());
					mapData.put("planGroup3_Plan1_FullName",values.get(7).get(9).toString());
					mapData.put("planGroup3_Plan2",values.get(8).get(8).toString());
					mapData.put("planGroup3_Plan2_FullName",values.get(8).get(9).toString());
					mapData.put("companyName",values.get(1).get(10).toString());
				    break;
				
			case "OralExamData":
				mapData.put("patient_name",values.get(1).get(0).toString());
				mapData.put("patient_mobile",values.get(1).get(1).toString());
				mapData.put("doctor",values.get(1).get(2).toString());
				mapData.put("doctorNickName",values.get(1).get(3).toString());
				mapData.put("clinicLocation",values.get(1).get(4).toString());
				break;
				
			case "DiagnosticData":
				mapData.put("patient_name",values.get(1).get(0).toString());
				mapData.put("patient_mobile",values.get(1).get(1).toString());
				mapData.put("doctor",values.get(1).get(2).toString());
				mapData.put("doctorNickName",values.get(1).get(3).toString());
				mapData.put("clinicLocation",values.get(1).get(4).toString());
				break;


               case "Event":
	    		for(List<Object>row:values) {
	    			mapData.put("event_Type", (String) row.get(1));
	    			mapData.put("event_Title", (String) row.get(2));
	    			mapData.put("event_Clinic", (String) row.get(3));
	    			mapData.put("event_On", (String) row.get(4));
	    			mapData.put("category", (String) row.get(5));
	    			mapData.put("event_Date", (String) row.get(6));
	    			mapData.put("event_notes", (String) row.get(7));
	    			mapData.put("event_hours", (String) row.get(8));
	    			mapData.put("event_mintues", (String) row.get(9));
	    			mapData.put("AM_PM", (String) row.get(10));
	    			mapData.put("event_duration", (String) row.get(11));
	    			mapData.put("event_DateTime", (String) row.get(12));
	    			mapData.put("appointmentTimeSlot", (String) row.get(13));
	    			mapData.put("doctor_nickName", (String) row.get(14));	
	    		}
	    		break;

	    	case "Product Sale":
	    		for(List<Object>row:values) {
	    			mapData.put("product_Name", (String) row.get(1));
	    			mapData.put("product_BatchNumber", (String) row.get(2));
	    			mapData.put("product_Size", (String) row.get(3));
	    			mapData.put("product_Company", (String) row.get(4));
	    			mapData.put("product_Original_Price", (String) row.get(5));
	    			mapData.put("discount_On_Product", (String) row.get(6));
	    			mapData.put("discount_From_Date", (String) row.get(7));
	    			mapData.put("discount_To_Date", (String) row.get(8));
	    			mapData.put("available_Qty", (String) row.get(9));
	    			
	    		}
	    		break;
	    	case "Miscellaneous Call":
	    		for(List<Object>row:values) {
	    			mapData.put("call_type", (String) row.get(0));
	    			mapData.put("call_duration", (String) row.get(1));
	    			mapData.put("call_disposition",(String) row.get(2));
	    			mapData.put("call_info", (String) row.get(3));
	    			mapData.put("call_name", (String) row.get(4));
	    			mapData.put("mobileNumber", (String) row.get(5));
	    			mapData.put("notes", (String) row.get(6));
	    			mapData.put("caller_name", (String) row.get(7));
	    			mapData.put("patientName_details", (String) row.get(8));
	    			mapData.put("patientMobile_details", (String) row.get(9));
	    			mapData.put("fromDate", (String) row.get(10));
	    			mapData.put("toDate", (String) row.get(11));
	    			
	    			
	    		}
	    		break;
	    	case "Feedback":
	    		for(List<Object> row:values) {
	    			mapData.put("treatment_shortName", (String) row.get(0));
	    			mapData.put("treatment_fullName", (String) row.get(1));
	    			mapData.put("patient_name",(String) row.get(2));
	    			mapData.put("patient_mobileNumber", (String) row.get(3));
	    			mapData.put("doctor_name", (String) row.get(4));
	    			mapData.put("doctor_nickName", (String) row.get(5));
	    			mapData.put("clinic_name", (String) row.get(6));
	    			break;
	    		}
	    		
	    	case "WorkDoneData":
				mapData.put("patient_name",values.get(1).get(0).toString());
				mapData.put("patient_mobile",values.get(1).get(1).toString());
				mapData.put("doctor",values.get(1).get(2).toString());
				mapData.put("doctorNickName",values.get(1).get(3).toString());
				mapData.put("clinicLocation",values.get(1).get(4).toString());
				mapData.put("planGroup1",values.get(1).get(7).toString());
				mapData.put("planGroup1_Plan1",values.get(1).get(8).toString());
				mapData.put("planGroup1_Plan1_FullName",values.get(1).get(9).toString());
				mapData.put("planGroup1_Plan2",values.get(2).get(8).toString());
				mapData.put("planGroup1_Plan2_FullName",values.get(2).get(9).toString());
				mapData.put("planGroup2",values.get(3).get(7).toString());
				mapData.put("planGroup2_Plan1",values.get(3).get(8).toString());
				mapData.put("planGroup2_Plan1_FullName",values.get(3).get(9).toString());
				mapData.put("planGroup2_Plan2",values.get(4).get(8).toString());
				mapData.put("planGroup2_Plan2_FullName",values.get(4).get(9).toString());
				mapData.put("planGroup3",values.get(7).get(7).toString());
				mapData.put("planGroup3_Plan1",values.get(7).get(8).toString());
				mapData.put("planGroup3_Plan1_FullName",values.get(7).get(9).toString());
				mapData.put("planGroup3_Plan2",values.get(8).get(8).toString());
				mapData.put("planGroup3_Plan2_FullName",values.get(8).get(9).toString());
				mapData.put("planGroup4",values.get(13).get(7).toString());
				mapData.put("planGroup4_Plan1",values.get(13).get(8).toString());
				mapData.put("planGroup4_Plan1_FullName",values.get(13).get(9).toString());
				mapData.put("planGroup4_Plan2",values.get(14).get(8).toString());
				mapData.put("planGroup4_Plan2_FullName",values.get(14).get(9).toString());
				mapData.put("companyName",values.get(1).get(10).toString());
				break;			
	    	
	    	case "Prescription":
				for (List<Object> row : values) {

					mapData.put("brand_name", (String) row.get(1));
					mapData.put("strength", (String) row.get(2));
					mapData.put("duration", (String) row.get(3));
					mapData.put("generic_name", (String) row.get(4));
					mapData.put("drug", (String) row.get(5));
					mapData.put("duration_dropdown", (String) row.get(6));
					mapData.put("route", (String) row.get(7));
					mapData.put("notes", (String) row.get(8));
					mapData.put("instruction", (String) row.get(9));	
				}
				break;
				
	    	case "LWO":
				for (List<Object> row : values) {

					mapData.put("lab_name", (String) row.get(2));
					mapData.put("order_type", (String) row.get(3));
					mapData.put("work_details", (String) row.get(4));
					mapData.put("other_info", (String) row.get(5));
					mapData.put("source", (String) row.get(6));
					mapData.put("item_sent", (String) row.get(7));
					mapData.put("lab_no", (String) row.get(8));
					mapData.put("revision", (String) row.get(9));
					mapData.put("reason", (String) row.get(10));	
					mapData.put("status", (String) row.get(11));
	    			mapData.put("require", (String) row.get(12));
	    			mapData.put("shade", (String) row.get(13));
	    			mapData.put("shade_option", (String) row.get(14));
	    			mapData.put("stains_internal", (String) row.get(15));
	    			mapData.put("stains_external", (String) row.get(16));
	    			mapData.put("translucency", (String) row.get(17));
	    			mapData.put("texture", (String) row.get(18));
	    			mapData.put("configurations", (String) row.get(19));
	    			mapData.put("configurations_opt", (String) row.get(20));
	    			mapData.put("surface_details", (String) row.get(21));
	    			mapData.put("notes", (String) row.get(22));
	    			mapData.put("attach_name", (String) row.get(23));
	    			mapData.put("revision_no", (String) row.get(24));
	    			mapData.put("updation_reason", (String) row.get(25));
	    			mapData.put("plan_treatment_group", (String) row.get(26));
	    			mapData.put("plan_short_name", (String) row.get(27));
	    			mapData.put("plan_Full_Name", (String) row.get(28));
				}
				break;
			}		
			return mapData;
		}
		return null;
	}	
}

































