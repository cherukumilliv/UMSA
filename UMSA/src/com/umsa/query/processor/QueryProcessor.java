package com.umsa.query.processor;

import com.umsa.dto.source.DTOList;
import com.umsa.exception.ExceptionQuery;
import com.umsa.interfaces.IDTO;
import com.umsa.interfaces.IQuery;
import com.umsa.interfaces.IQueryList;

public class QueryProcessor {
	//// Query Definitions - Start
	public static final String FIND_COURSE_BY_COURSE_NUMBER_AND_SUBJECT = "FSBCNAS";
	public static final String FIND_PRE_REQUISITES_BY_COURSE_AND_SUBJECT = "FPRBCAS";
	public static final String FIND_ROTATION_BY_YEAR = "FRBY";
	public static final String FIND_COURSES_FOR_ROTATION_TERM = "FCFRT";
	public static final String FIND_COURSE_IN_A_ROTATION = "FCIAR";
	public static final String FIND_COMPLETED_CONSENTED_OBJECT_BY_COURSE = "FCCOBC";
	public static final String SELECT_COURSES_LIST_FOR_VIEW = "SCLFV";
	//// Query Definitions - Stop
	
	public IDTO retrieve(String queryIdentifier,IDTO queryDTO) throws ExceptionQuery
	{		
		IQuery query = QueryFactory.getQuery(queryIdentifier);
		return query.retrieve(queryDTO);
	}
	
	public DTOList retrieveList(String queryIdentifier,IDTO queryDTO) throws ExceptionQuery
	{		
		IQueryList query = QueryFactory.getListQuery(queryIdentifier);
		return query.retrieveList(queryDTO);
	}
}
