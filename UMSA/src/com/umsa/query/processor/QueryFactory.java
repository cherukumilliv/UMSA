package com.umsa.query.processor;

import com.umsa.interfaces.IQuery;
import com.umsa.interfaces.IQueryList;
import com.umsa.query.FindCompletedConsentedObjectByCourse;
import com.umsa.query.FindCourseByCourseNumberAndsubject;
import com.umsa.query.FindCourseInARotation;
import com.umsa.query.FindCoursesForRotationTerm;
import com.umsa.query.FindPreRequisitesByCourseAndSubject;
import com.umsa.query.FindRotationByYear;
import com.umsa.query.SelectCoursesForView;

public class QueryFactory {
	static IQuery query = null;
	static IQueryList queryList = null;
	
	@SuppressWarnings("unused")
	private void QueryFactrory(){}
	
	public synchronized static IQuery getQuery(String queryIdentifier)
	{
		if("FSBCNAS".equalsIgnoreCase(queryIdentifier))
			query = new FindCourseByCourseNumberAndsubject();
		if("FPRBCAS".equalsIgnoreCase(queryIdentifier))
			query = new FindPreRequisitesByCourseAndSubject();
		if("FRBY".equalsIgnoreCase(queryIdentifier))
			query = new FindRotationByYear();
		if("FCFRT".equalsIgnoreCase(queryIdentifier))
			query = new FindCoursesForRotationTerm();
		if("FCIAR".equalsIgnoreCase(queryIdentifier))
			query = new FindCourseInARotation();
		if("FCCOBC".equalsIgnoreCase(queryIdentifier))
			query = new FindCompletedConsentedObjectByCourse();
		
		return query;
	}
	public synchronized static IQueryList getListQuery(String queryIdentifier)
	{
		if("SCLFV".equalsIgnoreCase(queryIdentifier))
			queryList = new SelectCoursesForView();
		
		return queryList;
	}

}
