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
		row1.createCell(0).setCellValue("Team Leader");
		row1.createCell(1).setCellValue("Representor 1");
		row1.createCell(2).setCellValue("Representer 2 ");
		
		Row row2 = sheet1.createRow(1);
		row2.createCell(0).setCellValue("Aslam");
		row2.createCell(1).setCellValue("Zaheer");
		row2.createCell(2).setCellValue("Rahaman");
		
//		singleRowElement = new String[row.]
//		
//		String  [][] = new String [2][3];
//		 
//		for(String i []:Row) {
//
		//for (String j:i) {
			//	System.out.print(j+"   ");
			//}
			System.out.println();
		}
		
		xlsxWorkbook.write(new FileOutputStream("ExcelSample.xlsx"));
		System.out.println("Excel Created SuccesFully");
	}

}
