package com.umsa.mapper.jaxb;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

import javax.xml.bind.JAXBElement;

import org.apache.xerces.dom.ElementNSImpl;

import com.umsa.dto.source.ScheduleDTO;
import com.umsa.dto.source.ScheduleEntryDTO;
import com.umsa.dto.source.SessionCourseDTO;
import com.umsa.dto.source.SessionDTO;
import com.umsa.exception.ExceptionMapper;
import com.umsa.interfaces.IConstants;
import com.umsa.interfaces.IMapper;
import com.umsa.jaxb.schedule.Schedule;
import com.umsa.jaxb.schedule.Schedule.ScheduledCourse;
import com.umsa.jaxb.schedule.Schedule.ScheduledCourse.Session;
import com.umsa.jaxb.schedule.Schedule.ScheduledCourse.Session.Course;

public class ScheduleMapper implements IMapper{

	@Override
	public Object mapFrom(Object dto) throws ExceptionMapper {
		Schedule schedule = null;
		ScheduleDTO sdto = null;
		if(dto != null && dto instanceof Schedule)
		{
			schedule = (Schedule) dto;
		}else
		{
			throw new ExceptionMapper(IConstants.INVALIDINPUT,"Expecting Schedule");
		}
		sdto = new ScheduleDTO();
		List<Serializable> scheduleContent = schedule.getContent();

		
		for(int i = 0 ;scheduleContent != null && i < scheduleContent.size() ; i++)
		{
			ScheduleEntryDTO sedto = new ScheduleEntryDTO();
			sdto.add(sedto);
			if(scheduleContent.get(i) instanceof JAXBElement<?>)
			{
				JAXBElement<?> schCourseJaxb =(JAXBElement<?>)scheduleContent.get(i);
				ScheduledCourse schCourse = (ScheduledCourse) schCourseJaxb.getValue();
				
				List<Serializable> schCourseContent = schCourse.getContent();
				for(int j = 0 ;schCourseContent != null && j < schCourseContent.size() ; j++)
				{
					if(schCourse.getContent().get(j) instanceof JAXBElement<?>)
					{
							JAXBElement<?> jaxElement = (JAXBElement<?>)schCourse.getContent().get(j);
							setScheduleEntry(sedto,jaxElement);
					}
				}
			}
		}
		return schedule;
	}

	protected void setScheduleEntry(ScheduleEntryDTO sedto,JAXBElement<?> jaxElement)
	{
		Object valObject = jaxElement.getValue();
		if(!(valObject instanceof Session))
		{
			String name = jaxElement.getName().getLocalPart();
			if("term".equalsIgnoreCase(name))
			{
				sedto.setTerm(jaxElement.getValue().toString());
			}
			if("year".equalsIgnoreCase(name))
			{
				sedto.setYear(jaxElement.getValue().toString());
			}
		}
		else
		{
			Session schCourseSession = (Session) valObject;
			SessionDTO sessionDTO = new SessionDTO();
			sedto.add(sessionDTO);
			
			if(valObject instanceof ElementNSImpl)
			{
				ElementNSImpl elm = (ElementNSImpl) schCourseSession.getSessionName();
				if( elm.getFirstChild() != null)
					sessionDTO.setName(elm.getFirstChild().getNodeValue());
			}
			List<Course> schCourseList = schCourseSession.getCourse();
			
			for(int k = 0 ;schCourseList != null && k < schCourseList.size();k++)
			{
				Course schSessionCourse = (Course)schCourseList.get(k);
				List<Serializable> schSessionCourseContent =  schSessionCourse.getContent();
				for(int l = 0 ;schSessionCourseContent != null && l < schSessionCourseContent.size() ; l++)
				{
					SessionCourseDTO sessionCourseDTO = new SessionCourseDTO();
					sessionDTO.add(sessionCourseDTO);
					if(schSessionCourseContent.get(l) instanceof JAXBElement<?>)
					{
						JAXBElement<?> schSessionCourseJAXElement = (JAXBElement<?>)schSessionCourseContent.get(l);
						setSession(schSessionCourseJAXElement,sessionCourseDTO);
					}
				}
			}
		}

	}
	
	protected void setSession(JAXBElement<?> schSessionCourseJAXElement,SessionCourseDTO sessionCourseDTO)
	{
		Object schSessionCourseValObject = schSessionCourseJAXElement.getValue();

		if("subject".equalsIgnoreCase(schSessionCourseJAXElement.getName().getLocalPart()))
		{
			sessionCourseDTO.setSubject(schSessionCourseValObject.toString());
		}												
		if("course_number".equalsIgnoreCase(schSessionCourseJAXElement.getName().getLocalPart()))
		{
			sessionCourseDTO.setCourseNumber(new BigInteger(schSessionCourseValObject.toString()));
		}												
		if("course_name".equalsIgnoreCase(schSessionCourseJAXElement.getName().getLocalPart()))
		{
			sessionCourseDTO.setCourseName(schSessionCourseValObject.toString());
		}												
		if("catalog_number".equalsIgnoreCase(schSessionCourseJAXElement.getName().getLocalPart()))
		{
			sessionCourseDTO.setCatalogNumber(schSessionCourseValObject.toString());
		}												
		if("class_section".equalsIgnoreCase(schSessionCourseJAXElement.getName().getLocalPart()))
		{
			sessionCourseDTO.setSection(schSessionCourseValObject.toString());
		}												
		if("start_date".equalsIgnoreCase(schSessionCourseJAXElement.getName().getLocalPart()))
		{
			sessionCourseDTO.setStartDate(schSessionCourseValObject.toString());
		}												
		if("end_date".equalsIgnoreCase(schSessionCourseJAXElement.getName().getLocalPart()))
		{
			sessionCourseDTO.setEndDate(schSessionCourseValObject.toString());
		}												
		if("instruction_mode".equalsIgnoreCase(schSessionCourseJAXElement.getName().getLocalPart()))
		{
			sessionCourseDTO.setInstructionMode(schSessionCourseValObject.toString());
		}												
		if("start_time".equalsIgnoreCase(schSessionCourseJAXElement.getName().getLocalPart()))
		{
			sessionCourseDTO.setStartTime(schSessionCourseValObject.toString());
		}												
		if("end_time".equalsIgnoreCase(schSessionCourseJAXElement.getName().getLocalPart()))
		{
			sessionCourseDTO.setEndTime(schSessionCourseValObject.toString());
		}												
		if("instructor".equalsIgnoreCase(schSessionCourseJAXElement.getName().getLocalPart()))
		{
			sessionCourseDTO.setInstructor(schSessionCourseValObject.toString());
		}												
		if("room".equalsIgnoreCase(schSessionCourseJAXElement.getName().getLocalPart()))
		{
			sessionCourseDTO.setRoom(schSessionCourseValObject.toString());
		}												
		if("day_code".equalsIgnoreCase(schSessionCourseJAXElement.getName().getLocalPart()))
		{
			sessionCourseDTO.setDayCode(schSessionCourseValObject.toString());
		}												
		if("capacity".equalsIgnoreCase(schSessionCourseJAXElement.getName().getLocalPart()))
		{
			sessionCourseDTO.setCapacity(schSessionCourseValObject.toString());
		}												
		if("current_enrollment".equalsIgnoreCase(schSessionCourseJAXElement.getName().getLocalPart()))
		{
			sessionCourseDTO.setCurrentEnrollment(schSessionCourseValObject.toString());
		}												
		
	}

	public String getValueOfStrJaxElement(JAXBElement<?> jaxElement)
	{
		String returnValue = null;
		
		
		
		return returnValue;
	}

	@Override
	public Object mapTo() throws ExceptionMapper {
		// TODO Auto-generated method stub
		return null;
	}

}
