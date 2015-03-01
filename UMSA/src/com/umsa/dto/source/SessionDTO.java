package com.umsa.dto.source;

import com.umsa.interfaces.IDTO;

public class SessionDTO implements IDTO {
	private String name = null;
	private DTOList sessionCourseList = null;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public DTOList getSessionCourseList() {
		return sessionCourseList;
	}
	public void setSessionCourseList(DTOList sessionCourseList) {
		this.sessionCourseList = sessionCourseList;
	}
	
	public void add(IDTO sessionCourseDTO)
	{
		if(sessionCourseList == null) sessionCourseList = new DTOList();
		
		sessionCourseList.add(sessionCourseDTO);
	}
	
	public SessionCourseDTO getSessionCourse(int index)
	{
		SessionCourseDTO returnValue = null;
		
		if(sessionCourseList != null) returnValue = (SessionCourseDTO) sessionCourseList.getDTO(index);
		
		return returnValue;
	}
	
	
}
