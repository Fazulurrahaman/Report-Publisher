package com.demo.ExcelProject;

public class Student {

	private String studentName;
	private long studentRoll;;

	private int mathMarks, scienceMarks, socialMarks, englishMarks, language2Marks, totalMarks;

	private String passed;

	private String parentName, parentEmail;
	private long parentMobile;
	private String LockerNum, LockerPass, OTP;
	private String excelFileLocation;

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public long getStudentRoll() {
		return studentRoll;
	}

	public void setStudentRoll(long studentRoll) {
		this.studentRoll = studentRoll;
	}

	public int getMathMarks() {
		return mathMarks;
	}

	public void setMathMarks(int mathMarks) {
		this.mathMarks = mathMarks;
	}

	public int getScienceMarks() {
		return scienceMarks;
	}

	public String getPassed() {
		return passed;
	}

	public void setPassed(String passed) {
		this.passed = passed;
	}

	public void setScienceMarks(int scienceMarks) {
		this.scienceMarks = scienceMarks;
	}

	public int getEnglishMarks() {
		return englishMarks;
	}

	public void setEnglishMarks(int englishMarks) {
		this.englishMarks = englishMarks;
	}

	public int getSocialMarks() {
		return socialMarks;
	}

	public void setSocialMarks(int socialMarks) {
		this.socialMarks = socialMarks;
	}

	public int getLanguage2Marks() {
		return language2Marks;
	}

	public void setLanguage2Marks(int language2Marks) {
		this.language2Marks = language2Marks;
	}

	public int getTotalMarks() {
		return totalMarks;
	}

	public void setTotalMarks(int totalMarks) {
		this.totalMarks = totalMarks;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getParentEmail() {
		return parentEmail;
	}

	public void setParentEmail(String parentEmail) {
		this.parentEmail = parentEmail;
	}

	public long getParentMobile() {
		return parentMobile;
	}

	public void setParentMobile(long parentMobile) {
		this.parentMobile = parentMobile;
	}

	public String getLockerNum() {
		return LockerNum;
	}

	public void setLockerNum(String lockerNum) {
		LockerNum = lockerNum;
	}

	public String getLockerPass() {
		return LockerPass;
	}

	public void setLockerPass(String lockerPass) {
		LockerPass = lockerPass;
	}

	public String getOTP() {
		return OTP;
	}

	public void setOTP(String oTP) {
		OTP = oTP;
	}

	public String getExcelFileLocation() {
		return excelFileLocation;
	}

	public void setExcelFileLocation(String excelFileLocation) {
		this.excelFileLocation = excelFileLocation;
	}

}
