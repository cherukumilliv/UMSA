package com.umsa.mapper.jaxb;

import java.math.BigInteger;
import java.util.List;

import javax.xml.bind.JAXBElement;

import com.umsa.dto.source.CourseDTO;
import com.umsa.dto.source.CoursePreRequisiteDTO;
import com.umsa.dto.source.DTOList;
import com.umsa.exception.ExceptionMapper;
import com.umsa.interfaces.IConstants;
import com.umsa.interfaces.IMapper;
import com.umsa.jaxb.course.Descriptions;
import com.umsa.jaxb.course.Descriptions.Course.Prerequisite;
import com.umsa.jaxb.course.Descriptions.Course.Prerequisite.OrChoice;

public class CourseJAXBMapper implements IMapper{

	@Override
	public Object mapFrom(Object dto) throws ExceptionMapper {
		Descriptions description = null;
		DTOList courseList = null;
		if(dto != null && dto instanceof Descriptions)
		{
			description = (Descriptions) dto;
		}else
		{
			throw new ExceptionMapper(IConstants.INVALIDINPUT,"Expecting Descriptions");
		}
		courseList = new DTOList();
		List<com.umsa.jaxb.course.Descriptions.Course> d_courseList = description.getCourse();
		for(int i = 0 ; d_courseList != null && i < d_courseList.size();i++)
		{
			CourseDTO course = new CourseDTO();
			CoursePreRequisiteDTO coursePreReqDTO = new CoursePreRequisiteDTO();
			course.setPreReqDTO(coursePreReqDTO);
			courseList.add(course);
			
			com.umsa.jaxb.course.Descriptions.Course d_course = d_courseList.get(i);
			
			course.setCourseName(d_course.getCourseName());
			course.setDescription(d_course.getCourseDescription());
			course.setCredit(d_course.getCredit());
			course.setSubject(d_course.getSubject());
			BigInteger crsNmbr = d_course.getCourseNumber();
			
			course.setCourseNumber(String.valueOf((crsNmbr != null)?crsNmbr:0));
			
			Prerequisite d_prereq = d_course.getPrerequisite();
			if(d_prereq != null)
			{
				coursePreReqDTO.setMessage(d_prereq.getAdditionalPreq());
				OrChoice rchoice = d_prereq.getOrChoice();
				if(rchoice != null)
				{
					List<JAXBElement<List<String>>> ndrequestJAXBList =  rchoice.getAndRequired();
					
					for(int k = 0 ; ndrequestJAXBList != null && k < ndrequestJAXBList.size(); k++)
					{
						JAXBElement<List<String>> jaxbElementList = ndrequestJAXBList.get(k);
						List<String>ndRequestList =  jaxbElementList.getValue();
						String subject = "";
						int l = 0;
						for(l = 0 ; ndRequestList != null && l < (ndRequestList.size() - 1); l++)
						{	
							subject += ndRequestList.get(l);
						}
						String courseNumber = (ndRequestList.get(l) == null)?"":ndRequestList.get(l);
						coursePreReqDTO.add(courseNumber,subject);
					}
				}
			}
		}

		
		return courseList;
	}

	@Override
	public Object mapTo() throws ExceptionMapper {
		// TODO Auto-generated method stub
		return null;
	}

}
