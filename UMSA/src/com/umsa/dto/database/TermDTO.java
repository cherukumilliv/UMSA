package com.umsa.dto.database;

import com.umsa.interfaces.IDTO;

public class TermDTO implements IDTO{
	private int termId;
	private String year = null;
	private String name = null;
	
	public int getTermId() {
		return termId;
	}
	public void setTermId(int termid) {
		this.termId = termid;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
