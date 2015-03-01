package com.umsa.cache;

import java.util.HashMap;

public class RepositoryDurable
{
	private static RepositoryDurable m_resourceHandlerRep = new RepositoryDurable();
	private static HashMap<String, Object> m_repository = new HashMap<String, Object>();
	
	private RepositoryDurable()
	{}
	
	public static RepositoryDurable getInstance()
	{
		return m_resourceHandlerRep;	
	}
	
	public synchronized Object getResource(String aHandlerName)
	{
		Object handler = null;
		if(m_repository.containsKey(aHandlerName))
		{
			handler = m_repository.get(aHandlerName);
		}
		return handler;
	}
	
	public synchronized void setResource(String aHandlerName, Object aHandler)
	{
		m_repository.put(aHandlerName, aHandler);		
	}
	
	public synchronized void removeResource(String aHandlerName)
	{
		if(getResource(aHandlerName)!=null)
		m_repository.remove(aHandlerName);		
	}

}
