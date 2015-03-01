package com.umsa.controller;

import java.util.ArrayList;

import com.umsa.dao.DAODB;
import com.umsa.dao.DAOJAXB;
import com.umsa.dto.database.DBResultDTO;
import com.umsa.dto.source.DTOList;
import com.umsa.dto.source.ScheduleDTO;
import com.umsa.exception.ExceptionController;
import com.umsa.exception.ExceptionDAO;
import com.umsa.interfaces.IDTO;
import com.umsa.logger.Logger;
import com.umsa.mapper.database.CourseMapper;
import com.umsa.mapper.database.ProfessorMapper;
import com.umsa.mapper.database.SubjectMapper;
import com.umsa.mapper.database.TermMapper;
import com.umsa.mapper.jaxb.CourseJAXBMapper;
import com.umsa.mapper.jaxb.RotationJAXBMapper;
import com.umsa.mapper.jaxb.ScheduleJAXBMapper;

public class BusinessController {
	
	public ScheduleDTO getScheduleDetailsUsingXML(Logger logger) throws ExceptionController
	{
		ScheduleDTO schedule = null;
		DAOJAXB dao = new DAOJAXB();
		
		try {			
			ScheduleJAXBMapper scheduleMapper = new ScheduleJAXBMapper();
			schedule = dao.retrieveScheduleInformationfromUMSL(scheduleMapper, logger);
		} catch (ExceptionDAO exDAO) {
			throw new ExceptionController(exDAO);
		} 
		
		return schedule;
	
	}

	public DTOList getCourseDetailsUsingXML(Logger logger) throws ExceptionController
	{
		DTOList courseList = null;
		DAOJAXB dao = new DAOJAXB();
		
		try {			
			CourseJAXBMapper courseMapper = new CourseJAXBMapper();
			courseList = dao.retrieveCourseInformationfromUMSL(courseMapper, logger);
		} catch (ExceptionDAO exDAO) {
			throw new ExceptionController(exDAO);
		} 
		
		return courseList;
	}
	
	public DTOList getRotationDetailsUsingXML(Logger logger) throws ExceptionController
	{
		DTOList rotationList = null;
		DAOJAXB dao = new DAOJAXB();
		
		try {			
			RotationJAXBMapper rotationMapper = new RotationJAXBMapper();
			rotationList = dao.retrieveRotataionInformationfromUMSL(rotationMapper, logger);
		} catch (ExceptionDAO exDAO) {
			throw new ExceptionController(exDAO);
		} 
		
		return rotationList;
	}
	
	public ArrayList<IDTO> getCourseDetailsFromDatabase(Logger logger) throws ExceptionController
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
	
	public ArrayList<IDTO> getProfessorDetailsFromDatabase(Logger logger) throws ExceptionController
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

	public ArrayList<IDTO> getTermDetailsFromDatabase(Logger logger) throws ExceptionController
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
	
	public ArrayList<IDTO> getSubjectDetailsFromDatabase(Logger logger) throws ExceptionController
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
