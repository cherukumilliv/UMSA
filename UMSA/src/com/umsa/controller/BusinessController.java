package com.umsa.controller;

import com.umsa.dao.DAOJAXB;
import com.umsa.dto.source.DTOList;
import com.umsa.dto.source.ScheduleDTO;
import com.umsa.exception.ExceptionController;
import com.umsa.exception.ExceptionDAO;
import com.umsa.logger.Logger;
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
	
}
