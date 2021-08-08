package com.demo.ExcelProject;

import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class NewExcel {
public static void main(String[] args) throws IOException {
	NewExcel newexcel = new NewExcel();
	newexcel.getRow();
}

private void getRow() throws  IOException {
	Workbook xlsxWorkbook = new XSSFWorkbook();
	Sheet sheet = xlsxWorkbook.createSheet();
	//Row row1= sheet1.createRow(0);

 
	String row [][] =  new String [1][4];
	        row[0][0] = "Rahaman";
			row[0][1] = "Haris Infotech ";
			row[0][2] = "Java";
			row[0][3] = "fazulurrahaman61@gmail.com";
			
			for(String i[]: row) {
				for (String j :i) {
					System.out.print(j+"  ");
					
				}
				System.out.println();
			}
			
			xlsxWorkbook.write(new FileOutputStream("ExcelSample.xlsx"));
			System.out.println("Excel Created SuccesFully");
			
			xlsxWorkbook.close();
			
	}
}



