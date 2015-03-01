package com.umsa.dto.source;

import com.umsa.interfaces.IDTO;

public class RotationTermDTO implements IDTO {
	private String term = null;
	private String timeCode = null;
	
	public String getTerm() {
		return term;
	}
	public void setTerm(String term) {
		this.term = term;
	}
	public String getTimeCode() {
		return timeCode;
	}
	public void setTimeCode(String timeCode) {
		this.timeCode = timeCode;
	}
}
