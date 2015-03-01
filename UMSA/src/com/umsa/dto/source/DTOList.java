package com.umsa.dto.source;

import java.util.ArrayList;

import com.umsa.interfaces.IDTO;

public class DTOList {

	ArrayList<IDTO> dtoList = null;
	
	public void add(IDTO dtoObject)
	{
		if(dtoList==null) dtoList = new ArrayList<IDTO>(1);
		
		dtoList.add(dtoObject);
	}
	
	public ArrayList<IDTO> getDTOList()
	{
		return dtoList;
	}
	
	public IDTO getDTO(int index)
	{
		IDTO returnValue = null;
		if(dtoList != null)
			returnValue = dtoList.get(index);
		return returnValue;
	}
}
