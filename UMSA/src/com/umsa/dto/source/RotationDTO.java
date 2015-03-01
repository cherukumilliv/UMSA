package com.umsa.dto.source;

import com.umsa.interfaces.IDTO;

public class RotationDTO implements IDTO{
	private String year = null;
	DTOList  courseList = null;
	
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public DTOList getCourseList() {
		return courseList;
	}
	public void setCourseList(DTOList courseList) {
		this.courseList = courseList;
	}
	public void addCourse(IDTO courseDTO)
	{
		if(courseList == null) courseList = new DTOList();
		
		courseList.add(courseDTO);
	}
	public RotationCourseDTO getCourse(int index)
	{
		RotationCourseDTO returnValue = null;
		
		if(courseList != null) returnValue = (RotationCourseDTO) courseList.getDTO(index);
		
		return returnValue;
	}
}
