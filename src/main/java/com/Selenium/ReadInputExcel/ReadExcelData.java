package com.Selenium.ReadInputExcel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ReadExcelData {

	
	/****************   TO READ SEARCH BAR DATA   ****************/
	public static String readExcelDataForSearchValue(int row,int col) throws IOException {
		FileInputStream fin = new FileInputStream(
				System.getProperty("user.dir") + "\\srcTestResources\\InputData.xlsx");

		XSSFWorkbook workbook = new XSSFWorkbook(fin);
		XSSFSheet sheet = workbook.getSheetAt(0);
		String value = sheet.getRow(row).getCell(col).getStringCellValue();
		workbook.close();
		fin.close();
		System.out.println(value);
		return value;
	}
	
	
	/****************   TO READ GIFT-CARD CREDENTIALS   ****************/
	public static String readExcelDataForGiftCard(int row,int col ) throws IOException {
		File src = new File(System.getProperty("user.dir")+"\\srcTestResources\\InputData.xlsx");
		FileInputStream fin = new FileInputStream(src);
		XSSFWorkbook workbook = new XSSFWorkbook(fin);
		XSSFSheet sheet = workbook.getSheet("cardCredentials");   
		XSSFCell cell = sheet.getRow(row).getCell(col);
        switch (cell.getCellType()) {
        case STRING :
        	String value = cell.getStringCellValue();
        	workbook.close();
                return value;

        case NUMERIC :
        	long value1 = (long)cell.getNumericCellValue();
        	workbook.close();
                return String.valueOf(value1);
                default:
        }
		workbook.close();
		fin.close();
		return null;
	}
}

