package com.umsa.database;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.umsa.cache.RepositoryDurable;
import com.umsa.database.rowset.impl.CachedRowSet;
import com.umsa.dto.database.connection.ConnectionDTO;
import com.umsa.exception.ExceptionResource;
import com.umsa.exception.ExceptionTransport;
import com.umsa.interfaces.IConstants;
import com.umsa.logger.Logger;

public class DataSourceFactory {

	private static DataSourceFactory dsFactory =  null;
	private static final String INITIAL_CONTEXT = "java:comp/env";
		
	synchronized public static DataSourceFactory getInstance()
	{		
		if(dsFactory == null)
		{
			dsFactory = new DataSourceFactory();			
		}
		return dsFactory;
	}
	
	public Connection getConnection(ConnectionDTO connectionDTO) throws ExceptionResource
	{
		Connection con = null;
		try {
			con = getDataSource(connectionDTO.getResourceName()).getConnection();
		} catch (SQLException sqlException) {
			// TODO Auto-generated catch block
			throw new ExceptionResource(sqlException);
		}
		return con;
	}
	
	private DataSourceFactory()
	{
	}
	
	protected DataSource getDataSource(String jndiName)
	{
		InitialContext initCtx;
		DataSource ds = null;
		try {
			if((ds=(DataSource)RepositoryDurable.getInstance().getResource(IConstants.UMSA_DB_JNDI_NAME)) == null)
			{
				initCtx = new InitialContext();
				Context envCtx = (Context) initCtx.lookup(INITIAL_CONTEXT);
				ds = (DataSource) envCtx.lookup(jndiName);
				RepositoryDurable.getInstance().setResource(IConstants.UMSA_DB_JNDI_NAME, ds);
			}
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ds;
	}
	
	public Object sendReceive(ConnectionDTO connectionDTO,Logger aLogger) throws ExceptionTransport
	{
		CachedRowSet cachedRowSet = null;
		if(connectionDTO.getStatementType() == IConstants.TYPE_DB_PREPARED)
		{
			OperationPreparedStatement opstmt = new OperationPreparedStatement(connectionDTO,aLogger);
		    cachedRowSet = (CachedRowSet) opstmt.sendReceive(connectionDTO,aLogger);			
		}
		return cachedRowSet;
	}
	
}
