package com.umsa.mapper.database;

import com.umsa.database.rowset.impl.CachedRowSet;
import com.umsa.dto.database.DBResultDTO;
import com.umsa.dto.database.TermDTO;
import com.umsa.dto.database.connection.ConnectionDTO;
import com.umsa.dto.database.connection.DbParameterDTO;
import com.umsa.exception.ExceptionCachedRowSet;
import com.umsa.exception.ExceptionMapper;
import com.umsa.interfaces.IConstants;
import com.umsa.interfaces.IMapper;

public class TermMapper implements IMapper{

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
				
				int termid = Integer.parseInt(cachedRowSet.getString(1));
				String year = cachedRowSet.getString(2);
				String name = cachedRowSet.getString(3);
				
				
				TermDTO currentDTO = new TermDTO();
				currentDTO.setTermId(termid);
				currentDTO.setName(name);
				currentDTO.setYear(year);
				
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
			String statement = "select * from StudentAdvisor.Term";
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
