package com.umsa.dto.query;

import com.umsa.dto.view.ConsentedCompletedCourseList;
import com.umsa.interfaces.IDTO;

public class CompletedConsentedQueryDTO implements IDTO {
	private ConsentedCompletedCourseList consentedCompletedCourseList = null;
	private String courseNumber = null;
	private String subject = null;
	
	public ConsentedCompletedCourseList getConsentedCompletedCourseList() {
		return consentedCompletedCourseList;
	}
	public void setConsentedCompletedCourseList(ConsentedCompletedCourseList cccList) {
		this.consentedCompletedCourseList = cccList;
	}
	public String getCourseNumber() {
		return courseNumber;
	}
	public void setCourseNumber(String courseNumber) {
		this.courseNumber = courseNumber;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	
}
