package com.umsa.dto.source;

import com.umsa.interfaces.IDTO;

public class CSBSReqDTO implements IDTO{
	private DTOList coreCourseList = null;
	private DTOList mathStatList = null;
	private DTOList otherCourseList = null;
	
	private int noOfElectives;
	private int aboveLevel;
	private String relationwithotherelectives = null;

	public DTOList getOtherCourseList() {
		return otherCourseList;
	}

	public void setOtherCourseList(DTOList otherCourseList) {
		this.otherCourseList = otherCourseList;
	}

	public DTOList getMathStatList() {
		return mathStatList;
	}

	public void setMathStatList(DTOList mathStatList) {
		this.mathStatList = mathStatList;
	}
	public int getNoOfElectives() {
		return noOfElectives;
	}

	public void setNoOfElectives(int noOfElectives) {
		this.noOfElectives = noOfElectives;
	}

	public int getAboveLevel() {
		return aboveLevel;
	}

	public void setAboveLevel(int aboveLevel) {
		this.aboveLevel = aboveLevel;
	}

	public String getRelationwithotherelectives() {
		return relationwithotherelectives;
	}

	public void setRelationwithotherelectives(String relationwithotherelectives) {
		this.relationwithotherelectives = relationwithotherelectives;
	}

	public DTOList getCoreCourseList() {
		return coreCourseList;
	}

	public void setCoreCourseList(DTOList coreCourseList) {
		this.coreCourseList = coreCourseList;
	}
	
	public void addCoreCourse(IDTO courseDTO)
	{
		if(coreCourseList == null) coreCourseList = new DTOList();
		
		coreCourseList.add(courseDTO);
	}
	
	public CourseDTO getCoreCourse(int index)
	{
		CourseDTO returnValue = null;
		
		if(coreCourseList != null) returnValue = (CourseDTO) coreCourseList.getDTO(index);
		
		return returnValue;
	}
	
	public void addMathStatCourse(IDTO courseDTO)
	{
		if(mathStatList == null) mathStatList = new DTOList();
		
		mathStatList.add(courseDTO);
	}
	
	public CourseDTO getMathStatCourse(int index)
	{
		CourseDTO returnValue = null;
		
		if(mathStatList != null) returnValue = (CourseDTO) mathStatList.getDTO(index);
		
		return returnValue;
	}
	public void addOtherCourse(IDTO courseDTO)
	{
		if(otherCourseList == null) otherCourseList = new DTOList();
		
		otherCourseList.add(courseDTO);
	}
	
	public CourseDTO getOtherCourse(int index)
	{
		CourseDTO returnValue = null;
		
		if(otherCourseList != null) returnValue = (CourseDTO) otherCourseList.getDTO(index);
		
		return returnValue;
	}
}
