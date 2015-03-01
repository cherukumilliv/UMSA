package com.umsa.database.rowset.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.umsa.exception.ExceptionCachedRowSet;
import com.umsa.interfaces.ICachedRowSet;

public class CachedRowSet implements ICachedRowSet
{
	private javax.sql.rowset.CachedRowSet m_cachedRowSet;
	public boolean next()
	{
		try
		{
			return m_cachedRowSet.next();
		}catch(SQLException aSQLException)
		{
			return false;
		}
	}	
	public int size()
	{
		return m_cachedRowSet.size();
	}
	public Object getObject(int aIndex) throws ExceptionCachedRowSet
	{
		try
		{
			return m_cachedRowSet.getObject(aIndex);
		}catch(SQLException aSQLException)
		{
			return null;
		}
	}
	public Object getObject(String aColumnName) throws ExceptionCachedRowSet
	{
		return null;
	}
	public String getString(int aIndex) throws ExceptionCachedRowSet
	{
		try
		{
			return m_cachedRowSet.getString(aIndex);
		}catch(SQLException aSQLException)
		{
			return null;
		}
	}
	public String getString(String aColumnName)throws ExceptionCachedRowSet 
	{
		try
		{
			return m_cachedRowSet.getString(aColumnName);
		}catch(SQLException aSQLException)
		{
			return null;
		}
	}
	public void populate(ResultSet aResultSet) throws SQLException 
	{
		m_cachedRowSet = new com.sun.rowset.CachedRowSetImpl();
		m_cachedRowSet.populate(aResultSet);
	}

}
