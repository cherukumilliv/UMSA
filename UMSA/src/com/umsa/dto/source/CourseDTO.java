package com.umsa.dto.source;

import com.umsa.interfaces.IDTO;

public class CourseDTO implements IDTO{
	private String courseNumber = null;
	private String courseName = null;
	private String description = null;
	private String credit;
	private String subject = null;
	private CoursePreRequisiteDTO preReqDTO = null;
	
	private boolean consented = false;
	private boolean completed = false;
	
	
	public CoursePreRequisiteDTO getPreReqDTO() {
		return preReqDTO;
	}
	public void setPreReqDTO(CoursePreRequisiteDTO preReqDTO) {
		this.preReqDTO = preReqDTO;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCredit() {
		return credit;
	}
	public void setCredit(String credit) {
		this.credit = credit;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public boolean isConsented() {
		return consented;
	}
	public void setConsented(boolean consented) {
		this.consented = consented;
	}
	public boolean isCompleted() {
		return completed;
	}
	public void setCompleted(boolean completed) {
		this.completed = completed;
	}
}
	