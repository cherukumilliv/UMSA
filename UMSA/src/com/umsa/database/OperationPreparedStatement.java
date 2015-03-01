package com.umsa.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.umsa.dto.database.connection.ConnectionDTO;
import com.umsa.dto.database.connection.DbParameterDTO;
import com.umsa.exception.ExceptionCode;
import com.umsa.exception.ExceptionTransport;
import com.umsa.logger.Logger;

public class OperationPreparedStatement extends TypeDBAbstract
{	
	private PreparedStatement m_preparedStatement = null;
	
	public OperationPreparedStatement(){}
	
	public OperationPreparedStatement(ConnectionDTO aConnectionDTO,Logger aLogger) throws ExceptionTransport
	{
		super (aConnectionDTO,aLogger);
	}

	protected List<Boolean> sendReceiveExecute() throws ExceptionTransport
	{
		try
		{
			ArrayList<Boolean> list = new ArrayList<Boolean>(1);
			boolean isSuccess = m_preparedStatement.execute();
			list.add(new Boolean(isSuccess));
			return list;
		}
		catch(SQLException aSQLException)
		{
			throw new ExceptionTransport(ExceptionCode.ERR_UMSA_SQL_EXCEPTION, "External exception @ TypeDBPreparedStatement.sendReceiveExecute():", aSQLException);
		}
	}

	protected ResultSet sendReceiveQuery() throws ExceptionTransport
	{
		try
		{
			return m_preparedStatement.executeQuery();
		}
		catch(SQLException aSQLException)
		{
			throw new ExceptionTransport(ExceptionCode.ERR_UMSA_SQL_EXCEPTION, "External exception @ TypeDBPreparedStatement.sendReceiveQuery():", aSQLException);
		}
	}

	protected Integer sendReceiveUpdate() throws ExceptionTransport
	{
		try
		{
			int count = m_preparedStatement.executeUpdate();
			return new Integer(count);
		}
		catch(SQLException aSQLException)
		{
			throw new ExceptionTransport(ExceptionCode.ERR_UMSA_SQL_EXCEPTION, "External exception @ TypeDBPreparedStatement.sendReceiveUpdate():", aSQLException);
		}
	}

	protected Integer[] sendReceiveBatchUpdate() throws ExceptionTransport
	{
		try
		{
			int count[] = m_preparedStatement.executeBatch();
			Integer[] response = new Integer[count.length];
			for(int i=0;i<count.length;i++)
			response[i] = new Integer(count[i]);
			return response;
		}
		catch(SQLException aSQLException)
		{
			throw new ExceptionTransport(ExceptionCode.ERR_UMSA_SQL_EXCEPTION, "External exception @ TypeDBPreparedStatement.sendReceiveUpdate():", aSQLException);
		}
	}
	
	protected void createStatement(Connection connection, ConnectionDTO connectionDTO) throws ExceptionTransport
	{
		try
		{	
//			m_preparedStatement = m_connection.prepareStatement(m_statement, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			m_preparedStatement = connection.prepareStatement(connectionDTO.getStatement());
		}
		catch(SQLException aSQLException)
		{
			throw new ExceptionTransport(ExceptionCode.ERR_UMSA_SQL_EXCEPTION, "External exception @ TypeDBPreparedStatement.createStatement():", aSQLException);
		}
	}	

	protected void buildStatementParameters(DbParameterDTO[] aDbParameters) throws ExceptionTransport
	{
		try
		{
			for(int i=0; i<aDbParameters.length; i++)
			{
				DbParameterDTO dbParameterDTO = aDbParameters[i];
				List<Object> parameterList = dbParameterDTO.getParameterList();
				for(int j = 0; j < parameterList.size(); j++)
				{
					try
					{
						m_preparedStatement.setObject(j+1, parameterList.get(j));
					}catch(Exception e)
					{
						m_preparedStatement.setString(j+1, (String)parameterList.get(j));
					}
				}
				m_preparedStatement.addBatch();
			}
		}catch(ClassCastException aClassCastException)
		{
			throw new ExceptionTransport(ExceptionCode.ERR_UMSA_INVALID_INPUT, "LOCAL Exception @ TypeDBPreparedStatement.buildPreparedStatement(): Expecting an ArrayList object:", aClassCastException);
		}catch(SQLException aSQLException)
		{
			throw new ExceptionTransport(ExceptionCode.ERR_UMSA_SQL_EXCEPTION, "External exception @ TypeDBPreparedStatement.buildPreparedStatement():", aSQLException);
		}catch(Exception aException)
		{
			if (!(aException instanceof ExceptionTransport))
			throw new ExceptionTransport(ExceptionCode.ERR_UMSA_SQL_EXCEPTION, "LOCAL exception @ " + this.getClass().toString() + ".buildStatementParameters():" + aException);
			else throw (ExceptionTransport)aException;
		}
	}

	protected void buildStatementParameters(DbParameterDTO aDbParameterDTO) throws ExceptionTransport
	{
		try
		{
			List<Object> parameterList = aDbParameterDTO.getParameterList();
			for(int i = 0; i < parameterList.size(); i++)
			{
				Object obj = parameterList.get(i);
				try
				{
					m_preparedStatement.setObject(i+1, obj);
				}catch(Exception e)
				{
					m_preparedStatement.setString(i+1, (String)obj);
				}
			}
		}catch(ClassCastException aClassCastException)
		{
			throw new ExceptionTransport(ExceptionCode.ERR_UMSA_INVALID_INPUT, "LOCAL Exception @ TypeDBPreparedStatement.buildPreparedStatement(): Expecting an ArrayList object:", aClassCastException);
		}catch(SQLException aSQLException)
		{
			throw new ExceptionTransport(ExceptionCode.ERR_UMSA_SQL_EXCEPTION, "External exception @ TypeDBPreparedStatement.buildPreparedStatement():", aSQLException);
		}catch(Exception aException)
		{
			if (!(aException instanceof ExceptionTransport))
			throw new ExceptionTransport(ExceptionCode.ERR_UMSA_SQL_EXCEPTION, "LOCAL exception @ " + this.getClass().toString() + ".buildStatementParameters():" + aException);
			else throw (ExceptionTransport)aException;
		}
	}
	
	protected void closeStatement()
	{
		try
		{
			if(m_preparedStatement != null)
			{
				m_preparedStatement.close();
				m_preparedStatement = null;
			}
		}
		catch(SQLException sqle)
		{}
	}
}
