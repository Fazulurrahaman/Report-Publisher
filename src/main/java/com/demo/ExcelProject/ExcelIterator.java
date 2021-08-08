package com.demo.ExcelProject;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelIterator {

	public static void main(String[] args) throws IOException {
		ExcelIterator ei = new ExcelIterator();
		ei.readExcelUsingIterator();

	}

	private void readExcelUsingIterator() throws IOException {
		Workbook excelWorkbook = new XSSFWorkbook(new FileInputStream("ExcelSample.xlsx"));
	   Iterator<Sheet> sheetIterator = excelWorkbook.iterator();
		
		while (sheetIterator.hasNext()) {
			Sheet sheet =  sheetIterator.next();
			Iterator<Row> rowIterator = sheet.iterator();
			
			while(rowIterator.hasNext()) {
				Row row =rowIterator.next();
				Iterator<Cell> cellIterator = row.iterator();
				
				while(cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					System.out.print(cell.toString()+ "\t\t");
				}
				System.out.println();
			}
			
	}
		
    excelWorkbook.close();

	}

}
