package com.umsa.dto.query;

import com.umsa.interfaces.IDTO;

public class RotationQueryDTO implements IDTO {
	private String year = null;

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}
	
}
