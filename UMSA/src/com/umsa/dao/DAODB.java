 package com.umsa.dao;

import com.umsa.database.DataSourceFactory;
import com.umsa.database.TypeDBAbstract;
import com.umsa.database.rowset.impl.CachedRowSet;
import com.umsa.dto.database.DBResultDTO;
import com.umsa.dto.database.connection.ConnectionDTO;
import com.umsa.exception.ExceptionDAO;
import com.umsa.exception.ExceptionTransport;
import com.umsa.interfaces.IConstants;
import com.umsa.interfaces.IMapper;
import com.umsa.logger.Logger;


public class DAODB {
  

	public static void main(String[] args){
	}

	public DAODB() {
		super();
	}
	
	public synchronized DBResultDTO retrieveDataFromDatabaseUsingPS(IMapper databaseMapper,Logger logger) 
	throws ExceptionDAO{
		logger.log(Logger.INFO_LEVEL,"DAO::retrieveDataFromDatabaseUsingPS - Loading Subject Details from Database");
		DBResultDTO resultantDTO = null;
		try{				
			ConnectionDTO connectionDTO = (ConnectionDTO) databaseMapper.mapTo();
			
			CachedRowSet cachedRowSet = new CachedRowSet();
			cachedRowSet = (CachedRowSet)executeDBCall(TypeDBAbstract.MODE_QUERY,
																			  connectionDTO,
																			  cachedRowSet,logger);
			
			resultantDTO = (DBResultDTO) databaseMapper.mapFrom(cachedRowSet);
			
		}catch(ExceptionDAO exceptionDAO){
			logger.log(Logger.INFO_LEVEL,"DAO::retrieveDataFromDatabaseUsingPS - Loading  Details from Database Failed " + exceptionDAO.getLocalErrorDescription());
			exceptionDAO.setLocalErrorDescription("DAO::retrieveDataFromDatabaseUsingPS - Loading  Details Failed");
			throw exceptionDAO;		
		}catch(Exception exception){
			logger.log(Logger.INFO_LEVEL,"DAO::retrieveDataFromDatabaseUsingPS - Loading Subject  from Database Failed " );
			ExceptionDAO exceptionDAO = new ExceptionDAO(exception);
			exceptionDAO.setLocalErrorDescription("DAO::retrieveDataFromDatabaseUsingPS - Loading  Details from Database Failed");
			throw exceptionDAO;
		}
		logger.log(Logger.INFO_LEVEL,"DAO::retrieveDataFromDatabaseUsingPS - Completed Loading  Details from Database");
		return resultantDTO;
	}
		

	public synchronized Object executeDBCall(int executeMode,
			 ConnectionDTO connectionDTO,									 
			 CachedRowSet cachedRowSet,Logger logger) throws ExceptionDAO{
		Object response = null;
		try{		
			connectionDTO.setResourceName("jdbc/StudentAdvisor");
			connectionDTO.setStatementType(IConstants.TYPE_DB_PREPARED);
			connectionDTO.setOperationMode(executeMode);
			connectionDTO.setCachedRowSet(cachedRowSet);
			response = DataSourceFactory.getInstance().sendReceive(connectionDTO,logger);
		} catch (ExceptionTransport e) {
			throw new ExceptionDAO(e);
		} 	
		return response;
	}

}
