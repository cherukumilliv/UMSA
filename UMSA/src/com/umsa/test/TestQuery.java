package com.umsa.test;

import com.umsa.cache.RepositoryDurable;
import com.umsa.controller.BusinessController;
import com.umsa.dto.query.CompletedConsentedQueryDTO;
import com.umsa.dto.source.DTOList;
import com.umsa.dto.source.ScheduleDTO;
import com.umsa.dto.view.ConsentedCompletedCourseList;
import com.umsa.exception.ExceptionController;
import com.umsa.exception.ExceptionQuery;
import com.umsa.interfaces.IConstants;
import com.umsa.logger.Logger;
import com.umsa.query.processor.QueryProcessor;

public class TestQuery {

	public static void main(String args[])
	{
		BusinessController controller = new BusinessController();
		DTOList courseList;
		try {
			
			courseList = controller.getCourseDetailsUsingXML(new Logger());
			System.out.println("Main");
			DTOList rotationList = controller.getRotationDetailsUsingXML(new Logger());
			ScheduleDTO scheduleList = controller.getScheduleDetailsUsingXML(new Logger());
			
			RepositoryDurable.getInstance().setResource(IConstants.UMSA_CACHE_RESOURCE_COURSE, courseList);
			RepositoryDurable.getInstance().setResource(IConstants.UMSA_CACHE_RESOURCE_ROTATION, rotationList);
			RepositoryDurable.getInstance().setResource(IConstants.UMSA_CACHE_RESOURCE_SCHEDULE, scheduleList);
			
			ConsentedCompletedCourseList completedCourseList = new ConsentedCompletedCourseList();
			completedCourseList.setCourseNumberCSV("1010#CMPLTD,2750#CNSNTD,3130#CMPLTD");
			
			CompletedConsentedQueryDTO qDTO = new CompletedConsentedQueryDTO();
			qDTO.setConsentedCompletedCourseList(completedCourseList);
			qDTO.setCourseNumber("2750");
			
			QueryProcessor qp = new QueryProcessor();
			try {
				qp.retrieveList(QueryProcessor.SELECT_COURSES_LIST_FOR_VIEW, qDTO);
			} catch (ExceptionQuery e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ExceptionController e) {
		}
	}
}
