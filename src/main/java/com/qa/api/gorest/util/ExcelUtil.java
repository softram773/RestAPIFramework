package com.qa.api.gorest.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.Test;

public class ExcelUtil {
	
	public static String TESTDATA_FILE_PATH = "C:\\Users\\ramesh.biradar\\eclipse-workspace\\RestAPIFramework\\src\\test\\resources\\GorestUserData.xlsx";
	public static Workbook book;
	public static Sheet sheet;
	

	public static Object[][] getTestData(String sheetName) {
		try {
			FileInputStream file = new FileInputStream(TESTDATA_FILE_PATH); // establish connection
			try {
				book = WorkbookFactory.create(file);  // It will create local excel copy in java memory
				sheet = book.getSheet(sheetName);
			} catch (EncryptedDocumentException | IOException e) {
				e.printStackTrace();
			}   
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Object data[][] = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		for(int row = 0; row < sheet.getLastRowNum(); row++) {
			for(int column = 0; column < sheet.getRow(0).getLastCellNum(); column++) {
				data[row][column] = sheet.getRow(row + 1).getCell(column).toString();
			}
		}
		return data;
	}
//	public static void main(String[] args) {
//		getTestData("userdata");
//	}

}
