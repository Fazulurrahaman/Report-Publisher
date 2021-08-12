package com.demo.ExcelProject.sender;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import com.demo.ExcelProject.Student;
public class MailSender {
    
	Properties props;
	Set<Student> studentsDetails;
	Session session;
	
	public MailSender() {
		props = new Properties();
		
	}
	
	public void sendMail(Set<Student> studentDetails) throws  IOException {
		this.studentsDetails= studentDetails;
		
	
	props.load(new FileInputStream("config.properties"));
	
	session = Session.getInstance(props, new Authenticator() {
		@Override
		protected PasswordAuthentication getPasswordAuthentication() {
			
			return new PasswordAuthentication(props.getProperty("EMAIL"), props.getProperty("PASSWORD"));
		}
		
	});
	startOperation();
	
}

	private void startOperation() {
		System.out.println("Preparing to send email");
		
		Iterator<Student> iter = this.studentsDetails.iterator();
		while (iter.hasNext()) {
			send(iter.next());
		}
		System.out.println("----------------");
	}

	private void send(Student s) {
		
		try {
			Message message = createMessage(s);
			Transport.send(message);
			System.out.printf("Message successfully sent to <%s>\n",s.getParentEmail());
			
		} catch (Exception e) {
			System.out.printf("Failed to send messssage :<%s>\t\t\t",s.getParentEmail());
			System.out.println("Problem :"+e.getMessage());
		}
	}

	private Message createMessage(Student s) {
		
		Message message = new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress(props.getProperty("EMAIL")));
			
			message.setRecipient(Message.RecipientType.TO,new InternetAddress(s.getParentEmail()));
			
			message.setSubject("Results Hve been Published for the academic year 2020 - 2021");
			
			Multipart multipart = new MimeMultipart();
			
			MimeBodyPart text1 = new MimeBodyPart();
			text1.setText(MessageBody.getEmailBody(s)
					);
			
			MimeBodyPart file = new MimeBodyPart();
			file.attachFile(s.getExcelFileLocation());
			
			multipart.addBodyPart(text1);
			multipart.addBodyPart(file);
			message.setContent(multipart);
			
			return message;
			
		} catch (Exception e) {
			System.out.printf("SOME ERROR OCCURED IN CREATING FOR %S!!!",s.getParentEmail());
			e.printStackTrace();
		}
		return message;
	}
	
	
}
