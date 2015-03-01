
package com.umsa.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.umsa.database.rowset.impl.CachedRowSet;
import com.umsa.dto.database.connection.ConnectionDTO;
import com.umsa.dto.database.connection.DbParameterDTO;
import com.umsa.exception.ExceptionCode;
import com.umsa.exception.ExceptionResource;
import com.umsa.exception.ExceptionTransport;
import com.umsa.interfaces.ICachedRowSet;
import com.umsa.logger.Logger;



public abstract class TypeDBAbstract
{
	public static final int MODE_EXECUTE = 1;
	public static final int MODE_QUERY = 2;
	public static final int MODE_UPDATE = 3;
	public static final int MODE_BATCHUPDATE = 4;
	
	protected abstract void createStatement(Connection connection, ConnectionDTO connectionDTO) throws ExceptionTransport;
	protected abstract void buildStatementParameters(DbParameterDTO[] aDbParameters)throws ExceptionTransport;
	protected abstract void buildStatementParameters(DbParameterDTO aDbParameter)throws ExceptionTransport;
	protected abstract List<Boolean> sendReceiveExecute() throws ExceptionTransport;
	protected abstract ResultSet sendReceiveQuery() throws ExceptionTransport;
	protected abstract Integer sendReceiveUpdate() throws ExceptionTransport;
	protected abstract Integer[] sendReceiveBatchUpdate() throws ExceptionTransport;
	protected abstract void closeStatement();
	
	public TypeDBAbstract(){}
	
	public TypeDBAbstract(ConnectionDTO aConnectionDTO, Logger aLogger) throws ExceptionTransport
	{		
		if(aConnectionDTO.getStatement() == null || aConnectionDTO.getStatement().trim().length() == 0)
		{
			throw new ExceptionTransport(ExceptionCode.ERR_UMSA_SQL_EXCEPTION, "External exception @ TypeDBCallableStatement.TypeDBCallableStatement(): Statement is not defined");
		}
	}
		
	public Object sendReceive(ConnectionDTO connectionDTO,Logger aLogger) throws ExceptionTransport
	{
		Object response = null;
		Connection connection = null;
		try
		{
			DbParameterDTO dbParameter = connectionDTO.getStatementParamList();
		
			connection = prepareConnection(connectionDTO);
			switch(connectionDTO.getOperationMode())
			{
				case MODE_EXECUTE:
				{
					createStatement(connection,connectionDTO);
					buildStatementParameters(dbParameter);
					aLogger.log(Logger.REMOTE_CALL, "DB:TypeDBAbstract:sendReceive: MODE_EXECUTE");
					response = sendReceiveExecute();
					aLogger.log(Logger.REMOTE_RETURN, "DB:TypeDBAbstract:sendReceive: MODE_EXECUTE");
					break;
				}
				case MODE_QUERY:
				{
					createStatement(connection,connectionDTO);
					buildStatementParameters(dbParameter);
					aLogger.log(Logger.REMOTE_CALL, "DB:TypeDBAbstract:sendReceive: MODE_QUERY");
					response = sendReceiveQuery();
					aLogger.log(Logger.REMOTE_RETURN, "DB:TypeDBAbstract:sendReceive: MODE_QUERY");
					break;
				}
				case MODE_UPDATE:
				{
					createStatement(connection,connectionDTO);
					buildStatementParameters(dbParameter);
					aLogger.log(Logger.REMOTE_CALL, "DB:TypeDBAbstract:sendReceive: MODE_UPDATE");
					response = sendReceiveUpdate();
					aLogger.log(Logger.REMOTE_RETURN, "DB:TypeDBAbstract:sendReceive: MODE_UPDATE");
					break;
				}
				default:
				{
					throw new ExceptionTransport(ExceptionCode.ERR_UMSA_INVALID_INPUT, "LOCAL exception @ TransportAbstractDatabase.sendReceive(): Invalid DB operationMode");
				}
			}
			return processResult(response);
		}catch(Exception aException)
		{
			if (!(aException instanceof ExceptionTransport))
			throw new ExceptionTransport(ExceptionCode.ERR_UMSA_SQL_EXCEPTION, "Exception @ " + this.getClass().toString() + ".sendReceive():" + aException);
			else throw (ExceptionTransport)aException;		
		}
		finally
		{
			closeStatement();
			close(connection);
		}
	}

	private Connection prepareConnection(ConnectionDTO connectionDTO) throws ExceptionTransport
	{
		try
		{
			return DataSourceFactory.getInstance().getConnection(connectionDTO);
		}catch(ExceptionResource aExceptionResource)
		{
			throw new ExceptionTransport(aExceptionResource);
		}		
	}
	
	protected Object processResult(Object aResult) throws ExceptionTransport
	{
		try
		{
			if(aResult instanceof ResultSet)
			{
				return mapToCacheRowSet((ResultSet)aResult);
			}else
			{
				return aResult;
			}
		}catch(SQLException aSQLException)
		{
			throw new ExceptionTransport(ExceptionCode.ERR_UMSA_SQL_EXCEPTION, "External exception @ TransportAbstractDatabase.processResult():", aSQLException);
		}
	}	
	
	private ICachedRowSet mapToCacheRowSet(ResultSet aResultSet) throws SQLException
	{
		CachedRowSet cachedRowSet = null;
		try
		{
			cachedRowSet = new CachedRowSet();
			cachedRowSet.populate(aResultSet);
		}catch(SQLException e)
		{
			throw e;
		}finally
		{
			try
			{
				aResultSet.close();
			}catch(Exception e){}
		}
		return cachedRowSet;
	}
	
	@SuppressWarnings("unused")
	private void setAutoCommitOff(Connection connection)
	{
		try
		{
			connection.setAutoCommit(false); 
		}catch(SQLException e){}
	}

	@SuppressWarnings("unused")
	private void commitTransaction(Connection connection)
	{
		try
		{
			connection.commit(); 
		}catch(SQLException e){}
	}

	@SuppressWarnings("unused")
	private void rollbackTransaction(Connection connection)
	{
		try
		{
			connection.rollback(); 
		}catch(SQLException e){}
	}
	
	private void close(Connection connection)
	{
		try
		{
			if(connection != null)
				connection.close();
		}
		catch(SQLException sqle)
		{}
	}
	
	}
