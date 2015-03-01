package com.umsa.mapper.database;

import com.umsa.database.rowset.impl.CachedRowSet;
import com.umsa.dto.database.DBResultDTO;
import com.umsa.dto.database.ProfessorDTO;
import com.umsa.dto.database.connection.ConnectionDTO;
import com.umsa.dto.database.connection.DbParameterDTO;
import com.umsa.exception.ExceptionCachedRowSet;
import com.umsa.exception.ExceptionMapper;
import com.umsa.interfaces.IConstants;
import com.umsa.interfaces.IMapper;

public class ProfessorMapper implements IMapper{

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
				
				String professorId = cachedRowSet.getString(1);
				String professorName = cachedRowSet.getString(2);
				
				ProfessorDTO currentDTO = new ProfessorDTO();
				currentDTO.setProfessorId(professorId);
				currentDTO.setProfessorName(professorName);
				
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
			String statement = "select * from StudentAdvisor.Professor";
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
