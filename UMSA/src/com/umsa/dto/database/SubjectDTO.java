package com.umsa.dto.database;

import com.umsa.interfaces.IDTO;

public class SubjectDTO implements IDTO{
	
	private int subjectId;
	private String subjectName = null;

	public int getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(int id) {
		this.subjectId = id;
	}
	
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	} 
	
}
