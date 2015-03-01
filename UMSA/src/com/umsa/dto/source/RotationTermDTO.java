package com.umsa.dto.source;

import java.util.ArrayList;

import com.umsa.interfaces.IDTO;

public class RotationTermDTO implements IDTO {
	private String term = null;
	private ArrayList<String> timeCodeList = null;
	
	public String getTerm() {
		return term;
	}
	public void setTerm(String term) {
		this.term = term;
	}
	public ArrayList<String>  getTimeCodeListt() {
		return timeCodeList;
	}
	public void setTimeCode(ArrayList<String>  timeCodeList) {
		this.timeCodeList = timeCodeList;
	}
	public void add(String timeCode)
	{
		if(timeCodeList == null) timeCodeList = new ArrayList<String>();
		
		timeCodeList.add(timeCode);
	}
	
	public String getSessionCourse(int index)
	{
		String returnValue = null;
		
		if(timeCodeList != null) returnValue = (String) timeCodeList.get(index);
		
		return returnValue;
	}

}
