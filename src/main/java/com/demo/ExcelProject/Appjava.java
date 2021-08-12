package com.demo.ExcelProject;

import java.io.IOException;
import java.util.Set;
import com.demo.ExcelProject.MailSender;
import com.demo.ExcelProject.SMSSender;
public class Appjava {

	public static void main(String[] args) throws IOException {
		ExcelCreator excelcreator = new ExcelCreator();
		Set<Student> studentEntries = excelcreator.createExcelForStudent("src/main/java/com/demo/ExcelProject/Student Data/data.xls");

		SMSSender smsSender = new SMSSender();
		smsSender.sendMessage(studentEntries);
		
		MailSender mailSender = new MailSender();
		mailSender.sendMail(studentEntries);
		
		ExcelCreator.print(studentEntries);
	}

}
