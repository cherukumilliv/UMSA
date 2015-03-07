package com.umsa.dto.dao;

import com.umsa.interfaces.IDTO;

public class ResourceURLDTO implements IDTO {
	public static final int ROTATIONURL = 0;
	public static final int SCHEDULEURL = 1;
	public static final int COURSEURL = 2;
	public static final int BSCSURL = 3;
	
	private String rotationXMLURL = null;
	private String courseXMLURL = null;
	private String scheduleXMLURL = null;
	private String bscsXMLURL = null;
	
	public String getRotationXMLURL() {
		return rotationXMLURL;
	}
	public void setRotationXMLURL(String rotationXMLURL) {
		this.rotationXMLURL = rotationXMLURL;
	}
	public String getCourseXMLURL() {
		return courseXMLURL;
	}
	public void setCourseXMLURL(String courseXMLURL) {
		this.courseXMLURL = courseXMLURL;
	}
	public String getScheduleXMLURL() {
		return scheduleXMLURL;
	}
	public void setScheduleXMLURL(String scheduleXMLURL) {
		this.scheduleXMLURL = scheduleXMLURL;
	}
	public String getBscsXMLURL() {
		return bscsXMLURL;
	}
	public void setBscsXMLURL(String bscsXMLURL) {
		this.bscsXMLURL = bscsXMLURL;
	}
	
}
