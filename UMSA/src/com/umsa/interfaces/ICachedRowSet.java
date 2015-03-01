package com.umsa.interfaces;

import java.sql.*;

import com.umsa.exception.ExceptionCachedRowSet;

public interface ICachedRowSet extends IDTO
{
	boolean next();
	int size();
	Object getObject(int aIndex) throws ExceptionCachedRowSet;
	Object getObject(String aColumnName) throws ExceptionCachedRowSet;
	String getString(int aIndex) throws ExceptionCachedRowSet;
	void populate(ResultSet aResultSet) throws SQLException;
}
