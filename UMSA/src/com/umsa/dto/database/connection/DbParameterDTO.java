package com.umsa.dto.database.connection;

import java.util.ArrayList;
import java.util.List;

public class DbParameterDTO 
{
	private ArrayList<Object> parameterList = new ArrayList<Object>(5);
	
	public void setNextParameter(Object aObj)
	{
		if(aObj == null)
		aObj = "";
		parameterList.add(aObj);
	}
	
	public List<Object> getParameterList()
	{
		return parameterList;
	}
}
