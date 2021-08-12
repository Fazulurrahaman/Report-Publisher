package com.demo.ExcelProject;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.apache.poi.hssf.record.crypto.Biff8EncryptionKey;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class ExcelCreator {

	public final String DATA_STORE = "src/main/java/com/demo/ExcelProject/Student Data/";
	public static int lockNum = 10;

	private Set<Student> studentEntries = new HashSet<>();
	private String filename;

	public Set<Student> createExcelForStudent(String filename) throws IOException {

		this.filename = filename;
		storeDataFromExcel();
		createExcelForAll();
		generateOTPForAll();
		setPasswordForAll();

		return this.studentEntries;

	}

	private void storeDataFromExcel() {
		try (Workbook workbook = new HSSFWorkbook(new FileInputStream(filename))) {
			Sheet sheet = workbook.getSheetAt(0);
			Iterator<Row> iter = sheet.rowIterator();

			iter.next();

			Row row;
			Student student;
			while (iter.hasNext()) {
				row = iter.next();
				student = new Student();

				student.setStudentName(row.getCell(0).getStringCellValue());
				student.setStudentRoll((long) row.getCell(1).getNumericCellValue());
				student.setMathMarks((int) row.getCell(2).getNumericCellValue());
				student.setScienceMarks((int) row.getCell(3).getNumericCellValue());
				student.setSocialMarks((int) row.getCell(4).getNumericCellValue());
				student.setEnglishMarks((int) row.getCell(5).getNumericCellValue());
				student.setLanguage2Marks((int) row.getCell(6).getNumericCellValue());
				student.setTotalMarks((int) row.getCell(7).getNumericCellValue());
				student.setPassed(row.getCell(8).getStringCellValue());
				student.setParentName(row.getCell(9).getStringCellValue());
				student.setParentEmail(row.getCell(10).getStringCellValue());
				student.setParentMobile((long) row.getCell(11).getNumericCellValue());

				this.studentEntries.add(student);
			}
			System.out.println("All entries stored");

		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	private void createExcelForAll() throws IOException {
		Workbook wb = null;
		FileOutputStream output = null;
		Student s;
		String destination;
		Sheet sheet;
		Row row;
		Cell cell;

		Iterator<Student> iter = studentEntries.iterator();
		while (iter.hasNext()) {
			int i = 0;
			s = iter.next();
			String fileName = s.getStudentName() + "s Results.xls";
			destination = DATA_STORE + fileName;
			s.setExcelFileLocation(destination);
			studentEntries.add(s);

			wb = new HSSFWorkbook();
			sheet = wb.createSheet(s.getStudentName());

			row = sheet.createRow(i);
			row.createCell(0).setCellValue("Name");
			row.createCell(1).setCellValue(s.getStudentName());

			row = sheet.createRow(++i);
			row.createCell(0).setCellValue("Role");
			row.createCell(1).setCellValue(s.getStudentRoll());

			row = sheet.createRow(++i);
			row.createCell(0).setCellValue("Math");
			row.createCell(1).setCellValue(s.getMathMarks());

			row = sheet.createRow(++i);
			row.createCell(0).setCellValue("Science");
			row.createCell(1).setCellValue(s.getScienceMarks());

			row = sheet.createRow(++i);
			row.createCell(0).setCellValue("Social");
			row.createCell(1).setCellValue(s.getSocialMarks());

			row = sheet.createRow(++i);
			row.createCell(0).setCellValue("English");
			row.createCell(1).setCellValue(s.getEnglishMarks());

			row = sheet.createRow(++i);
			row.createCell(0).setCellValue("Language");
			row.createCell(1).setCellValue(s.getLanguage2Marks());

			row = sheet.createRow(++i);
			row.createCell(0).setCellValue("Total");
			row.createCell(1).setCellValue(s.getTotalMarks());

			if (s.getPassed().equals("YES")) {
				row = sheet.createRow(++i);
				row.createCell(0).setCellValue("Results");
				row.createCell(1).setCellValue("PASSED");

				String lockerNumber = String.valueOf("L" + lockNum);
				s.setLockerNum(lockerNumber);
				row = sheet.createRow(++i);
				row.createCell(0).setCellValue("Locker Number");
				row.createCell(1).setCellValue(lockerNumber);

				String password = String.valueOf(s.getStudentRoll() + lockNum);

				s.setLockerPass(password);
				row = sheet.createRow(++i);
				row.createCell(0).setCellValue("Locker password");
				row.createCell(1).setCellValue(password);

				lockNum++;
			} else {
				row = sheet.createRow(++i);
				row.createCell(0).setCellValue("Result");
				row.createCell(1).setCellValue("FAILED");
			}
			CellStyle style = wb.createCellStyle();
			style.setWrapText(true);
			style.setAlignment(HorizontalAlignment.RIGHT);

			for (int j = 0; j < sheet.getPhysicalNumberOfRows(); j++) {
				cell = sheet.getRow(j).getCell(1);
				cell.setCellStyle(style);
			}
			sheet.autoSizeColumn(0);
			sheet.autoSizeColumn(1);

			output = new FileOutputStream(destination);
			wb.write(output);

		}
		System.out.println("Excel file Successfully created for all the students");

		output.close();
		wb.close();
	}

	private void generateOTPForAll() {
		Iterator<Student> iter = this.studentEntries.iterator();
		Student s;

		while (iter.hasNext()) {
			s = iter.next();

			String OTP_1 = s.getStudentName().substring(s.getStudentName().length() - 3).toUpperCase();
			String rollString = String.valueOf(s.getStudentRoll());
			String OTP_2 = rollString.substring(rollString.length() - 3);
			s.setOTP(OTP_1 + OTP_2);
		}

	}

	private void setPasswordForAll() throws IOException {
		Iterator<Student> iter = this.studentEntries.iterator();
		Student s;
		while (iter.hasNext()) {
			s = iter.next();
			HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(s.getExcelFileLocation()));

			Biff8EncryptionKey.setCurrentUserPassword(s.getOTP());
			workbook.write(new FileOutputStream(s.getExcelFileLocation()));
			workbook.close();
		}

	}

	public static void print(Set<Student> studentEntries) {
		System.out.println("\n-------------------------------Printing Data----------\n");
		Iterator<Student> iter = studentEntries.iterator();
		while (iter.hasNext()) {
			Student s = iter.next();
			System.out.printf(
					"%s\t%s\t" + "%s\t%s\t%s\t%s\t%s\t" + "%s\t%s\t" + "%s\t%s\t%s\t" + "%s\t%s\t%s\t" + "%s\n",
					// Name & roll
					s.getStudentName(), s.getStudentRoll(),
					// Marks
					s.getMathMarks(), s.getScienceMarks(), s.getSocialMarks(), s.getEnglishMarks(),
					s.getLanguage2Marks(), s.getTotalMarks(), s.getPassed(),
					// Parent Details
					s.getParentName(), s.getParentEmail(), s.getParentMobile(),
					// Locker Details
					s.getLockerNum(), s.getLockerPass(), s.getOTP(),
					// attachment
					s.getExcelFileLocation());
		}
	}
}
