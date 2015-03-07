package com.umsa.query;

import com.umsa.cache.RepositoryDurable;
import com.umsa.dto.query.CompletedConsentedQueryDTO;
import com.umsa.dto.source.CourseDTO;
import com.umsa.dto.source.CoursePreRequisiteDTO;
import com.umsa.dto.source.DTOList;
import com.umsa.dto.view.ViewCourseDTO;
import com.umsa.exception.ExceptionQuery;
import com.umsa.interfaces.IConstants;
import com.umsa.interfaces.IDTO;
import com.umsa.interfaces.IQueryList;
import com.umsa.query.processor.QueryProcessor;

public class SelectCoursesForView implements IQueryList{

	/*
	 * The assumption before this Query is used is that
	 * the Consented/Completed Course has already been obtained
	 * and we have that information available.
	 *
	 * (non-Javadoc)
	 * @see com.umsa.interfaces.IQuery#lookup(com.umsa.interfaces.IDTO)
	 */
	@Override
	public DTOList retrieveList(IDTO dto) throws ExceptionQuery {
		DTOList courseListforView = new DTOList();
		
		CompletedConsentedQueryDTO qDTO = new CompletedConsentedQueryDTO();
		
		DTOList courseList = (DTOList)RepositoryDurable.getInstance().getResource(IConstants.UMSA_CACHE_RESOURCE_COURSE);
		
		for(int i = 0; courseList != null && i < courseList.size() ; i++)
		{
			// ViewCourseDTO is the specialized DTO for displaying the 
			// List of courses to the Student.
			ViewCourseDTO viewCourseDTO  = new ViewCourseDTO();
			CourseDTO currCourse = (CourseDTO) courseList.getDTO(i);

			qDTO.setCourseNumber(currCourse.getCourseNumber());
			viewCourseDTO.setCourseNumber(currCourse.getCourseNumber());
			viewCourseDTO.setCourseName(currCourse.getCourseName());
			
			// Here we are trying to see if there is a object 
			// that exists pertaining to the course in the course list
			// where the student has mentioned whether he/she has 
			// Consent or Completed the course.
			// This query below checks for the given course number
			// if there is consent/complete information.
			// if so returns a DTO with that information.
			ViewCourseDTO viewCourseCompletionDTO = getCourseCompletionInfo(qDTO);
			if(viewCourseCompletionDTO.isCompleted())
			{
				viewCourseDTO.setCompleted(true);
			}
			if(viewCourseCompletionDTO.isConsented())
			{
				viewCourseDTO.setConsented(true);
			}
			
			// The same we need to know for the pre-requisite course.
			// i.e., if the student has consent or completed the pre-req.
			// So, appropriate flags can be set on the screen and 
			// the courses can be shown on the screen accordingly.
			CoursePreRequisiteDTO prereqDTO =  currCourse.getPreReqDTO();
			
			DTOList prereqCourseList = prereqDTO.getCourseList();
			String preReqCSV = "";
			for(int k = 0; prereqCourseList != null && k < prereqCourseList.size() ; k++)
			{
				CourseDTO currPreReqCourse = (CourseDTO) courseList.getDTO(k);
				preReqCSV += currPreReqCourse.getCourseNumber();
				preReqCSV += "#";
				ViewCourseDTO viewPrereqCompletionDTO = getCourseCompletionInfo(qDTO);
				if(viewPrereqCompletionDTO.isCompleted())
				{
					preReqCSV += "CMPLTD";
				}
				if(viewPrereqCompletionDTO.isConsented())
				{
					preReqCSV += "CNSNTD";
				}
				if(k+1 < prereqCourseList.size()) preReqCSV += ",";		
			}
			viewCourseDTO.setPreReqCSV(preReqCSV);
			
			courseListforView.add(viewCourseDTO);
		}
		
		return courseListforView;
	}

	protected ViewCourseDTO getCourseCompletionInfo(IDTO qdto) throws ExceptionQuery
	{
		QueryProcessor qp = new QueryProcessor();
		ViewCourseDTO viewCourseCompletionDTO = (ViewCourseDTO) qp.retrieve(QueryProcessor.FIND_COMPLETED_CONSENTED_OBJECT_BY_COURSE, qdto);
		
		return viewCourseCompletionDTO;
	}

	@Override
	public IDTO retrieve(IDTO dto) throws ExceptionQuery {
		// TODO Auto-generated method stub
		return null;
	}
	
}
