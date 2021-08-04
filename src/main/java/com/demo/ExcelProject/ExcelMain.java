package com.demo.ExcelProject;

import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelMain {

	public static void main(String[] args) throws IOException {
		ExcelMain excelmain = new ExcelMain();
		excelmain.createAndSaveExcel();
	}


	private void createAndSaveExcel() throws IOException {
		Workbook xlsxWorkbook=new XSSFWorkbook();
		Sheet sheet1 = xlsxWorkbook.createSheet("Test");
		Row row1 = sheet1.createRow(0);
		row1.createCell(0).setCellValue("Header 1");
		row1.createCell(1).setCellValue("Header 2");
		row1.createCell(2).setCellValue("Header ");
		
		Row row2 = sheet1.createRow(1);
		row2.createCell(0).setCellValue("Header 1");
		row2.createCell(1).setCellValue("Header 2");
		row2.createCell(2).setCellValue("Header ");
		
		
		xlsxWorkbook.write(new FileOutputStream("ExcelSample.xlsx"));
		System.out.println("Excel Created SuccesFully");
	}

}
