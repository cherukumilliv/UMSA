package com.umsa.query;

import com.umsa.dto.query.CourseQueryDTO;
import com.umsa.dto.source.CourseDTO;
import com.umsa.dto.source.CoursePreRequisiteDTO;
import com.umsa.exception.ExceptionQuery;
import com.umsa.interfaces.IConstants;
import com.umsa.interfaces.IDTO;
import com.umsa.interfaces.IQuery;
import com.umsa.query.processor.QueryProcessor;

public class FindPreRequisitesByCourseAndSubject implements IQuery {

	@Override
	public IDTO retrieve(IDTO dto) throws ExceptionQuery {
		CoursePreRequisiteDTO coursePreRequisiteDTO = null;
		CourseQueryDTO qDTO = null;
		
		if(!(dto instanceof CourseQueryDTO))
		{
			throw new ExceptionQuery(IConstants.INVALIDINPUT,"Expecting CourseQueryDTO");
		}else
		{
			qDTO = (CourseQueryDTO) dto;
		}
		QueryProcessor qp = new QueryProcessor();
		CourseDTO courseDTO = (CourseDTO) qp.retrieve(QueryProcessor.FIND_COURSE_BY_COURSE_NUMBER_AND_SUBJECT, qDTO);
		
		if(courseDTO != null)
		{
			coursePreRequisiteDTO = courseDTO.getPreReqDTO();
		}
		return coursePreRequisiteDTO;
	}

}
