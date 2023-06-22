package com.prm.pageobjects.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * 
 * @author Ajit Yadav
 *
 */
public class ReadExcelData {
	private static String stringValue;

	public static String readExcelData(String filePath, String sheetName, int rowNum, int cellNum) {
		Workbook wb;
		try {
			wb = WorkbookFactory.create(new FileInputStream(new File(filePath)));
			stringValue = wb.getSheet(sheetName).getRow(rowNum).getCell(cellNum).getStringCellValue();
		} catch (EncryptedDocumentException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return stringValue;
	}
}
