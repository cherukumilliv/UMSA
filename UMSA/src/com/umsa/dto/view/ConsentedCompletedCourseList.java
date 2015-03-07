package com.umsa.dto.view;

import java.util.ArrayList;
import java.util.StringTokenizer;

import com.umsa.interfaces.IDTO;

public class ConsentedCompletedCourseList implements IDTO{
	
	private ArrayList<String> completedCourseNumberList = null;
	private ArrayList<String> consentedCourseNumberList = null;

	private String courseNumberCSV = null;
	
	public ArrayList<String> getConsentedCourseNumberList() {
		return consentedCourseNumberList;
	}

	public void setConsentedCourseNumberList(
			ArrayList<String> consentedCourseNumberList) {
		this.consentedCourseNumberList = consentedCourseNumberList;
	}
	
	public String getCourseNumberCSV() {
		return courseNumberCSV;
	}

	public ArrayList<String> getCompletedCourseNumberList() {
		return completedCourseNumberList;
	}

	public void setCompletedCourseNumberList(
			ArrayList<String> courseNumberList) {
		this.completedCourseNumberList = courseNumberList;
	}
	
	/*
	 * Expected input string
	 * courseNumber#CMPLTD,courseNumber#CNSNTD
	 * there is no order to CMPLTD and CNSNTD
	 */
	public void setCourseNumberCSV(String courseNumberCSV)
	{
		this.courseNumberCSV = courseNumberCSV;
		if(courseNumberCSV != null)
		{
			if(completedCourseNumberList == null)
			{
				completedCourseNumberList = new ArrayList<String>(1);
			}
			if(consentedCourseNumberList == null)
			{
				consentedCourseNumberList = new ArrayList<String>(1);
			}
			StringTokenizer coursetokenizer = new StringTokenizer(courseNumberCSV,",");
			while(coursetokenizer.hasMoreElements())
			{
				String courseStatusStr = coursetokenizer.nextToken();
				String currCourseNumber = null;
				String currCourseStatus = null;
				StringTokenizer statusTokenizer = new StringTokenizer(courseStatusStr,"#");
				
				if(statusTokenizer.hasMoreTokens())
					currCourseNumber = statusTokenizer.nextToken();
				if(statusTokenizer.hasMoreTokens())
					currCourseStatus = statusTokenizer.nextToken();
			
				if("CNSNTD".equalsIgnoreCase(currCourseStatus))
					consentedCourseNumberList.add(currCourseNumber);
				if("CMPLTD".equalsIgnoreCase(currCourseStatus))
					completedCourseNumberList.add(currCourseNumber);
			}
		}
	}
}
