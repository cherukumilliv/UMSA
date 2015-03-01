package com.umsa.dto.database.connection;

import java.net.URL;

import com.umsa.database.rowset.impl.CachedRowSet;
import com.umsa.interfaces.IDTO;

public class ConnectionDTO implements IDTO
{
	private String m_env;
	private String m_resourceName;
	private String m_driverName;
	private String m_userId;
	private String m_password; 
	private String m_initialContextName;
	private String m_providerUrl;
	private URL m_urlSource;
	private String m_resourceOption;
	private DbParameterDTO statementParamList;	
	
	private int statementType;

	private int operationMode;
	private String statement;
	private CachedRowSet cachedRowSet;
	
	public int getStatementType() {
		return statementType;
	}

	public void setStatementType(int statementType) {
		this.statementType = statementType;
	}
	
	public DbParameterDTO getStatementParamList() {
		return statementParamList;
	}

	public void setStatementParamList(DbParameterDTO statementParamList) {
		this.statementParamList = statementParamList;
	}

	public ConnectionDTO()
	{
	}
	
	public String getPassword() 
	{
		return m_password;
	}

	public String getResourceName() 
	{
		return m_resourceName;
	}

	public String getUserId() 
	{
		return m_userId;
	}

	public void setPassword(String string) 
	{
		m_password = string;
	}

	public void setResourceName(String string) 
	{
		m_resourceName = string;
	}


	public void setUserId(String string) 
	{
		m_userId = string;
	}


	public URL getUrl() 
	{
		return m_urlSource;
	}

	public void setUrl(URL url) 
	{
		m_urlSource = url;
	}
	
	public String getResourceOption() 
	{
		return m_resourceOption;
	}

	public void setResourceOption(String string) 
	{
		m_resourceOption = string;
	}
	
	public String getInitialContextFactoryName() 
	{
		return m_initialContextName;
	}

	public String getProviderUrl() 
	{
		return m_providerUrl;
	}

	public void setInitialContextFactoryName(String string) 
	{
		m_initialContextName = string;
	}

	public void setProviderUrl(String string) 
	{
		m_providerUrl = string;
	}

	public String getDriverName() 
	{
		return m_driverName;
	}

	public void setDriverName(String string) 
	{
		m_driverName = string;
	}

	public String getEnv() 
	{
		return m_env;
	}

	public void setEnv(String string) 
	{
		m_env = string;
	}

	public int getOperationMode() {
		return operationMode;
	}

	public void setOperationMode(int operationMode) {
		this.operationMode = operationMode;
	}

	public String getStatement() {
		return statement;
	}

	public void setStatement(String statement) {
		this.statement = statement;
	}

	public CachedRowSet getCachedRowSet() {
		return cachedRowSet;
	}

	public void setCachedRowSet(CachedRowSet cachedRowSet) {
		this.cachedRowSet = cachedRowSet;
	}

}
