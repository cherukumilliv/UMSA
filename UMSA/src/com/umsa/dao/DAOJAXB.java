package com.umsa.dao;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.xml.bind.Marshaller;

import com.umsa.dao.dto.ResourceURLDTO;
import com.umsa.databind.impl.DataBindJAXB;
import com.umsa.dto.source.DTOList;
import com.umsa.dto.source.ScheduleDTO;
import com.umsa.exception.ExceptionDAO;
import com.umsa.interfaces.IConstants;
import com.umsa.interfaces.IMapper;
import com.umsa.jaxb.course.Descriptions;
import com.umsa.jaxb.rotation.Rotations;
import com.umsa.jaxb.schedule.Schedule;
import com.umsa.logger.Logger;
import com.umsa.xml.parserer.XmlDomParser;

public class DAOJAXB {
	
	
	public synchronized ScheduleDTO retrieveScheduleInformationfromUMSL(IMapper scheduleMapper,Logger logger) 
	throws ExceptionDAO{
		logger.log(Logger.INFO_LEVEL,"DAOJAXB::retrieveScheduleInformationfromUMSL - Pulling Schedule Details from UMSL");
		ScheduleDTO sdto = null;
		try{				
			Properties jaxbProp = new Properties();
			jaxbProp.put(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

			String xmlResponse = null;
			
			String resourceURL = getURLForResource(ResourceURLDTO.SCHEDULEURL);
			XmlDomParser parser = new XmlDomParser(resourceURL);
			xmlResponse = parser.toString();
			
			DataBindJAXB dataBindJAXB = new DataBindJAXB(IConstants.SCHEDULE_JAXB_PACKAGE, jaxbProp, false);
			Schedule schedule = (Schedule) dataBindJAXB.decode(xmlResponse);
			
			sdto = (ScheduleDTO)scheduleMapper.mapFrom(schedule);
			
		}catch(ExceptionDAO exceptionDAO){
			logger.log(Logger.INFO_LEVEL,"DAOJAXB::retrieveScheduleInformationfromUMSL - - Pulling Schedule Details from UMSL Failed " + exceptionDAO.getLocalErrorDescription());
			exceptionDAO.setLocalErrorDescription("DAOJAXB::retrieveScheduleInformationfromUMSL - - Pulling Schedule Details from UMSL Failed");
			throw exceptionDAO;		
		}catch(Exception exception){
			logger.log(Logger.INFO_LEVEL,"DAOJAXB::retrieveScheduleInformationfromUMSL - - Pulling Schedule Details from UMSL Failed " );
			ExceptionDAO exceptionDAO = new ExceptionDAO(exception);
			exceptionDAO.setLocalErrorDescription("DAOJAXB::retrieveScheduleInformationfromUMSL - - Pulling Schedule Details from UMSL Failed");
			throw exceptionDAO;
		}
		logger.log(Logger.INFO_LEVEL,"DAOJAXB::retrieveRotataionInformationfromUMSL - - Pulling Schedule Details from UMSL");
		return sdto;
	}
	
	public synchronized DTOList retrieveRotataionInformationfromUMSL(IMapper rotationMapper,Logger logger) 
	throws ExceptionDAO{
		logger.log(Logger.INFO_LEVEL,"DAOJAXB::retrieveRotataionInformationfromUMSL - Pulling Rotation Details from UMSL");
		DTOList rotationList = null;
		try{				
			Properties jaxbProp = new Properties();
			jaxbProp.put(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

			String xmlResponse = null;
			
			String resourceURL = getURLForResource(ResourceURLDTO.ROTATIONURL);
			XmlDomParser parser = new XmlDomParser(resourceURL);
			xmlResponse = parser.toString();
			
			DataBindJAXB dataBindJAXB = new DataBindJAXB(IConstants.ROTATION_JAXB_PACKAGE, jaxbProp, false);
			Rotations rotations = (Rotations) dataBindJAXB.decode(xmlResponse);
			
			rotationList = (DTOList)rotationMapper.mapFrom(rotations);
			
		}catch(ExceptionDAO exceptionDAO){
			logger.log(Logger.INFO_LEVEL,"DAOJAXB::retrieveRotataionInformationfromUMSL - - Pulling Rotation Details from UMSL Failed " + exceptionDAO.getLocalErrorDescription());
			exceptionDAO.setLocalErrorDescription("DAOJAXB::retrieveRotataionInformationfromUMSL - - Pulling Rotation Details from UMSL Failed");
			throw exceptionDAO;		
		}catch(Exception exception){
			logger.log(Logger.INFO_LEVEL,"DAOJAXB::retrieveRotataionInformationfromUMSL - - Pulling Rotation Details from UMSL Failed " );
			ExceptionDAO exceptionDAO = new ExceptionDAO(exception);
			exceptionDAO.setLocalErrorDescription("DAOJAXB::retrieveRotataionInformationfromUMSL - - Pulling Rotation Details from UMSL Failed");
			throw exceptionDAO;
		}
		logger.log(Logger.INFO_LEVEL,"DAOJAXB::retrieveRotataionInformationfromUMSL - - Pulling Rotation Details from UMSL");
		return rotationList;
	}

	public synchronized DTOList retrieveCourseInformationfromUMSL(IMapper CourseMapper,Logger logger) 
	throws ExceptionDAO{
		logger.log(Logger.INFO_LEVEL,"DAOJAXB::retrieveCourseInformationfromUMSL - Pulling Course Details from UMSL");
		DTOList courseList = null;
		try{				
			Properties jaxbProp = new Properties();
			jaxbProp.put(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

			String xmlResponse = null;
			
			String resourceURL = getURLForResource(ResourceURLDTO.COURSEURL);
			XmlDomParser parser = new XmlDomParser(resourceURL);
			xmlResponse = parser.toString();
			
			DataBindJAXB dataBindJAXB = new DataBindJAXB(IConstants.COURSE_JAXB_PACKAGE, jaxbProp, false);
			Descriptions desc = (Descriptions) dataBindJAXB.decode(xmlResponse);
			
			courseList = (DTOList)CourseMapper.mapFrom(desc);
			
		}catch(ExceptionDAO exceptionDAO){
			logger.log(Logger.INFO_LEVEL,"DAOJAXB::retrieveCourseInformationfromUMSL - - Pulling Course Details from UMSL Failed " + exceptionDAO.getLocalErrorDescription());
			exceptionDAO.setLocalErrorDescription("DAOJAXB::retrieveCourseInformationfromUMSL - - Pulling Course Details from UMSL Failed");
			throw exceptionDAO;		
		}catch(Exception exception){
			logger.log(Logger.INFO_LEVEL,"DAOJAXB::retrieveCourseInformationfromUMSL - - Pulling Course Details from UMSL Failed " );
			ExceptionDAO exceptionDAO = new ExceptionDAO(exception);
			exceptionDAO.setLocalErrorDescription("DAOJAXB::retrieveCourseInformationfromUMSL - - Pulling Course Details from UMSL Failed");
			throw exceptionDAO;
		}
		logger.log(Logger.INFO_LEVEL,"DAOJAXB::retrieveCourseInformationfromUMSL - - Pulling Course Details from UMSL");
		return courseList;
	}

	
	
	protected String getURLForResource(int resource) throws ExceptionDAO
	{
		Context initCtx;
		String returnURL = null;
		try {
			initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");
			ResourceURLDTO resourceURLDTO = (ResourceURLDTO) envCtx.lookup("bean/URLDataSource");
			switch(resource)
			{
				case ResourceURLDTO.ROTATIONURL:{
					returnURL = resourceURLDTO.getRotationXMLURL();
					break;
				}
				case ResourceURLDTO.COURSEURL:{
					returnURL = resourceURLDTO.getCourseXMLURL();
					break;
				}
				case ResourceURLDTO.BSCSURL:{
					returnURL = resourceURLDTO.getBscsXMLURL();
					break;
				}
				case ResourceURLDTO.SCHEDULEURL:{
					returnURL = resourceURLDTO.getScheduleXMLURL();
					break;
				}
			}
		} catch (NamingException e) {
			throw new ExceptionDAO(e);
		}
		return returnURL;
	}
}
