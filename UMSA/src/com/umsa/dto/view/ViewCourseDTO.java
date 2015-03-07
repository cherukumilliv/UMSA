package com.umsa.dto.view;

import com.umsa.interfaces.IDTO;

public class ViewCourseDTO implements IDTO {
	
	private String courseNumber = null;
	private String courseName = null;
	private boolean completed = false;
	private boolean consented = false;
	private String preReqCSV = null;
	
	public boolean isCompleted() {
		return completed;
	}
	public void setCompleted(boolean completed) {
		this.completed = completed;
	}
	public boolean isConsented() {
		return consented;
	}
	public void setConsented(boolean consented) {
		this.consented = consented;
	}	
	public String getCourseNumber() {
		return courseNumber;
	}
	public void setCourseNumber(String courseNumber) {
		this.courseNumber = courseNumber;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getPreReqCSV() {
		return preReqCSV;
	}
	public void setPreReqCSV(String preReqCSV) {
		this.preReqCSV = preReqCSV;
	}

}
