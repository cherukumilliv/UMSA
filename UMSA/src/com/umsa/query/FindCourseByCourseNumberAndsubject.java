package com.umsa.query;

import com.umsa.cache.RepositoryDurable;
import com.umsa.dto.query.CourseQueryDTO;
import com.umsa.dto.source.CourseDTO;
import com.umsa.dto.source.DTOList;
import com.umsa.exception.ExceptionQuery;
import com.umsa.interfaces.IConstants;
import com.umsa.interfaces.ICourseQueryDTO;
import com.umsa.interfaces.IDTO;
import com.umsa.interfaces.IQuery;

public class FindCourseByCourseNumberAndsubject implements IQuery{

	@Override
	public IDTO retrieve(IDTO dto) throws ExceptionQuery {
		CourseDTO courseDTO = null;
		CourseQueryDTO qDTO = null;
		
		if(!(dto instanceof ICourseQueryDTO))
		{
			throw new ExceptionQuery(IConstants.INVALIDINPUT,"Expecting CourseQueryDTO");
		}else
		{
			qDTO = (CourseQueryDTO) dto;
		}
		DTOList courseList = (DTOList)RepositoryDurable.getInstance().getResource(IConstants.UMSA_CACHE_RESOURCE_COURSE);
		
		for(int i = 0; courseList != null && i < courseList.size() ; i++)
		{
			CourseDTO currentCourseDTO = (CourseDTO) courseList.getDTO(i);
			if(currentCourseDTO != null &&
					currentCourseDTO.getCourseNumber().equalsIgnoreCase(qDTO.getCourseNumber()) &&
					currentCourseDTO.getSubject().equalsIgnoreCase(qDTO.getSubject()))
			{
				courseDTO = currentCourseDTO;
				break;
			}
		}
		return courseDTO;
	}

}
