package com.umsa.mapper.database;

import com.umsa.database.rowset.impl.CachedRowSet;
import com.umsa.dto.database.CourseDTO;
import com.umsa.dto.database.DBResultDTO;
import com.umsa.dto.database.connection.ConnectionDTO;
import com.umsa.dto.database.connection.DbParameterDTO;
import com.umsa.exception.ExceptionCachedRowSet;
import com.umsa.exception.ExceptionMapper;
import com.umsa.interfaces.IConstants;
import com.umsa.interfaces.IMapper;

public class CourseMapper implements IMapper{

	@Override
	public Object mapFrom(Object dto) throws ExceptionMapper {
		CachedRowSet cachedRowSet = null;
		DBResultDTO resultantDTO = new DBResultDTO();
		if(dto != null)
		{
			cachedRowSet = (CachedRowSet) dto;
		}else
		{
			throw new ExceptionMapper(IConstants.INVALIDINPUT,"Expecting CachedRowSet");
		}
		while(cachedRowSet.next())
		{
			try {
				
				String courseNumber = cachedRowSet.getString(1);
				String courseName = cachedRowSet.getString(2);
				String description  = cachedRowSet.getString(3);
				int credit = Integer.parseInt(cachedRowSet.getString(4));
				
				CourseDTO currentDTO = new CourseDTO();
				currentDTO.setCourseName(courseName);
				currentDTO.setCourseNumber(courseNumber);
				currentDTO.setCredit(credit);
				currentDTO.setDescription(description);
				
				resultantDTO.add(currentDTO);
				
			} catch (NumberFormatException nfe) {
				throw new ExceptionMapper(nfe);
			} catch (ExceptionCachedRowSet ecrs) {
				throw new ExceptionMapper(ecrs);
			}
		}
		return resultantDTO;
	}

	@Override
	public Object mapTo() throws ExceptionMapper {
		ConnectionDTO connectionDTO = null;
		
		try
		{
			String statement = "select * from StudentAdvisor.Course";
			DbParameterDTO statementParamList = new DbParameterDTO();
	
			connectionDTO = new ConnectionDTO();
			connectionDTO.setStatement(statement);
			connectionDTO.setStatementParamList(statementParamList);
		} catch(Exception ex)
		{
			throw new ExceptionMapper(ex);
		}
		return connectionDTO;
	}

	
}
