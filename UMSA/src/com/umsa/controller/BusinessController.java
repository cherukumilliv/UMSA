package com.umsa.controller;

import java.util.ArrayList;

import com.umsa.dao.DAODB;
import com.umsa.dto.database.DBResultDTO;
import com.umsa.exception.ExceptionController;
import com.umsa.exception.ExceptionDAO;
import com.umsa.interfaces.IDTO;
import com.umsa.logger.Logger;
import com.umsa.mapper.database.CourseMapper;
import com.umsa.mapper.database.ProfessorMapper;
import com.umsa.mapper.database.SubjectMapper;
import com.umsa.mapper.database.TermMapper;

public class BusinessController {
	
	public ArrayList<IDTO> getCourseDetails(Logger logger) throws ExceptionController
	{
		DBResultDTO resultantDTO = null;
		DAODB dao = new DAODB();
		
		try {			
			CourseMapper databaseMapper = new CourseMapper();
			resultantDTO = dao.retrieveDataFromDatabaseUsingPS(databaseMapper,logger);
		} catch (ExceptionDAO exDAO) {
			throw new ExceptionController(exDAO);
		} 
		
		return (resultantDTO != null)?resultantDTO.getResultDTOList() : null;
	}
	
	public ArrayList<IDTO> getProfessorDetails(Logger logger) throws ExceptionController
	{
		DBResultDTO resultantDTO = null;
		DAODB dao = new DAODB();
		
		try {			
			ProfessorMapper databaseMapper = new ProfessorMapper();
			resultantDTO = dao.retrieveDataFromDatabaseUsingPS(databaseMapper,logger);
		} catch (ExceptionDAO exDAO) {
			throw new ExceptionController(exDAO);
		} 
		
		return (resultantDTO != null)?resultantDTO.getResultDTOList() : null;
	}

	public ArrayList<IDTO> getTermDetails(Logger logger) throws ExceptionController
	{
		DBResultDTO resultantDTO = null;
		DAODB dao = new DAODB();
		
		try {			
			TermMapper databaseMapper = new TermMapper();
			resultantDTO = dao.retrieveDataFromDatabaseUsingPS(databaseMapper,logger);
		} catch (ExceptionDAO exDAO) {
			throw new ExceptionController(exDAO);
		} 
		
		return (resultantDTO != null)?resultantDTO.getResultDTOList() : null;
	}
	
	public ArrayList<IDTO> getSubjectDetails(Logger logger) throws ExceptionController
	{
		DBResultDTO resultantDTO = null;
		DAODB dao = new DAODB();
		
		try {			
			SubjectMapper databaseMapper = new SubjectMapper();
			resultantDTO = dao.retrieveDataFromDatabaseUsingPS(databaseMapper,logger);
		} catch (ExceptionDAO exDAO) {
			throw new ExceptionController(exDAO);
		} 
		
		return (resultantDTO != null)?resultantDTO.getResultDTOList() : null;
	}	
}
