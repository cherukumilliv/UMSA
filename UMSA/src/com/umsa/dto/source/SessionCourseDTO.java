package com.umsa.dto.source;

import com.umsa.interfaces.IDTO;

public class SessionCourseDTO  extends CourseDTO implements IDTO{
	private String section = null;
	private String catalogNumber = null;
	private String startDate = null;
	private String endDate = null;
	private String instructionMode = null;
	private String startTime = null;
	private String endTime = null;
	private String instructor = null;
	private String room = null;
	private String dayCode = null;
	private String capacity = null;
	private String currentEnrollment = null;
		
	
	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public String getCatalogNumber() {
		return catalogNumber;
	}

	public void setCatalogNumber(String catalogNumber) {
		this.catalogNumber = catalogNumber;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getInstructionMode() {
		return instructionMode;
	}

	public void setInstructionMode(String instructionMode) {
		this.instructionMode = instructionMode;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String starttimee) {
		this.startTime = starttimee;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endtime) {
		this.endTime = endtime;
	}

	public String getInstructor() {
		return instructor;
	}

	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public String getDayCode() {
		return dayCode;
	}

	public void setDayCode(String dayCode) {
		this.dayCode = dayCode;
	}

	public String getCapacity() {
		return capacity;
	}

	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}

	public String getCurrentEnrollment() {
		return currentEnrollment;
	}

	public void setCurrentEnrollment(String currentEnrollment) {
		this.currentEnrollment = currentEnrollment;
	}
	
}
