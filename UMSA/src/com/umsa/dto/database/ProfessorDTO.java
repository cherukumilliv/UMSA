package com.umsa.dto.database;

import com.umsa.interfaces.IDTO;

public class ProfessorDTO implements IDTO{
	private String professorId = null;
	private String professorName = null;
	
	public String getProfessorId() {
		return professorId;
	}
	public void setProfessorId(String professorId) {
		this.professorId = professorId;
	}
	public String getProfessorName() {
		return professorName;
	}
	public void setProfessorName(String professorName) {
		this.professorName = professorName;
	}
	
	
}
