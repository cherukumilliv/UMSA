package com.umsa.dto.query;

import com.umsa.interfaces.ICourseQueryDTO;

public class CourseQueryDTO implements ICourseQueryDTO{
	private String CourseNumber = null;
	private String Subject = null;
	
	public String getCourseNumber() {
		return CourseNumber;
	}
	public void setCourseNumber(String courseNumber) {
		CourseNumber = courseNumber;
	}
	public String getSubject() {
		return Subject;
	}
	public void setSubject(String subject) {
		Subject = subject;
	}
	
}
