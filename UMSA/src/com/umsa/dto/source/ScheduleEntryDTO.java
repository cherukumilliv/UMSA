package com.umsa.dto.source;

import com.umsa.interfaces.IDTO;

public class ScheduleEntryDTO implements IDTO {
	private String term = null;
	private String year = null;
	private DTOList sessionList = null;
	
	
	
	public DTOList getSessionList() {
		return sessionList;
	}
	public void setSessionList(DTOList sessionList) {
		this.sessionList = sessionList;
	}
	public String getTerm() {
		return term;
	}
	public void setTerm(String term) {
		this.term = term;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	
	public void add(IDTO sessionDTO)
	{
		if(sessionList == null) sessionList = new DTOList();
		
		sessionList.add(sessionDTO);
	}
	
	public SessionDTO getSessionCourse(int index)
	{
		SessionDTO returnValue = null;
		
		if(sessionList != null) returnValue = (SessionDTO) sessionList.getDTO(index);
		
		return returnValue;
	}

	
}
