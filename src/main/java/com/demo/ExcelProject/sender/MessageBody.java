package com.demo.ExcelProject.sender;

import com.demo.ExcelProject.Student;

class MessageBody {
	   
		public static String getEmailBody(Student s) {
			String result = s.getPassed().equals("YES") ? "PASSED " : "FAILED";
			String message = "Dear Mr/Mrs."+s.getParentName()+",\n"
					+"\t the results have been published for yhe academic year 2020 - 2021 for all the Students."
					+"your son/daughter"+ s.getStudentName()+"has scored "+s.getTotalMarks()+"and have"+result+"their secondary school."
					+"Report card and further details have been attached as an excel file below"
					+"\n\n\n Thanks & Regards"
					+ "\nController of Examination,"
					+ "\nWebsite : http.//www.myschool.com/"
					+ "\nEmail : myschool@school.com,"
					+ "\nPhone :+91 987654321";
					
			return message; 

	}
		public static String getSMSBody(Student s) {
			String message = "Dear Mr/Mrs."+s.getParentName()+","
					+"\n Result Published."
					+"Kindely chek your email and use the following OTP to open the attachment"
					+"/n"+s.getOTP();
			return message;
		}

}
