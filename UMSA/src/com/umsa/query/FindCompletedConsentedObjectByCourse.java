package com.umsa.query;

import java.util.ArrayList;

import com.umsa.dto.query.CompletedConsentedQueryDTO;
import com.umsa.dto.view.ConsentedCompletedCourseList;
import com.umsa.dto.view.ViewCourseDTO;
import com.umsa.exception.ExceptionQuery;
import com.umsa.interfaces.IConstants;
import com.umsa.interfaces.IDTO;
import com.umsa.interfaces.IQuery;

public class FindCompletedConsentedObjectByCourse implements IQuery{

	@Override
	public IDTO retrieve(IDTO dto) throws ExceptionQuery {
		ViewCourseDTO courseDTO = null;
		CompletedConsentedQueryDTO qDTO = null;
		
		if(!(dto instanceof CompletedConsentedQueryDTO))
		{
			throw new ExceptionQuery(IConstants.INVALIDINPUT,"Expecting CompletedConsentedQueryDTO");
		}else
		{
			qDTO = (CompletedConsentedQueryDTO) dto;
		}
		ConsentedCompletedCourseList consentedCompletedCourseList = qDTO.getConsentedCompletedCourseList();
		
		if(consentedCompletedCourseList != null)
		{
			ArrayList<String> completedCourseNumberList = consentedCompletedCourseList.getCompletedCourseNumberList();
			ArrayList<String> consentedNumberList = consentedCompletedCourseList.getConsentedCourseNumberList();
			boolean found = false;
			
			for(int i = 0 ; i < completedCourseNumberList.size() ;i++ )
			{
				String courseNumber = completedCourseNumberList.get(i);
				if(courseNumber.equalsIgnoreCase(qDTO.getCourseNumber()))
				{
					courseDTO = new ViewCourseDTO();
					courseDTO.setCompleted(true);
					courseDTO.setCourseNumber(courseNumber);
					found = true;
					break;
				}
				
			}
			for(int i = 0 ; !found && i < consentedNumberList.size() ;i++ )
			{
				String courseNumber = consentedNumberList.get(i);
				if(courseNumber.equalsIgnoreCase(qDTO.getCourseNumber()))
				{
					courseDTO = new ViewCourseDTO();
					courseDTO.setCompleted(true);
					courseDTO.setCourseNumber(courseNumber);
					found = true;
					break;
				}
				
			}
		}
		
		return courseDTO;
	}

}
