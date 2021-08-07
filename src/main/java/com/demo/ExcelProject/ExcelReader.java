package com.demo.ExcelProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

	public static void main(String[] args) throws IOException {
		ExcelReader excelreader = new ExcelReader();
		excelreader.readExcel();

	}

	private void readExcel() throws IOException {
		
		Workbook excelWorkbook = new XSSFWorkbook(new FileInputStream("ExcelSample.xlsx"));
		int nuberOfSheets = excelWorkbook.getNumberOfSheets();
		
		for(int i=0;i<nuberOfSheets; i++) {
		org.apache.poi.ss.usermodel.Sheet sheet= excelWorkbook.getSheetAt(i);
		int rowCount =  sheet.getPhysicalNumberOfRows();
			
		for(int j=0; j<rowCount; j++) {
		Row row =  sheet.getRow(j);
		int cellCount = row.getPhysicalNumberOfCells();
		
		for(int k = 0; k < cellCount; k++) {
			System.out.print(row.getCell(k).toString() + "\t\t");
		}
		System.out.println();
		}
			
		}
	}

}
