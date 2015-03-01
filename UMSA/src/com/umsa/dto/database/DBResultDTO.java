package com.umsa.dto.database;

import java.util.ArrayList;

import com.umsa.interfaces.IDTO;

public class DBResultDTO implements IDTO{
	
	ArrayList<IDTO> resultDTOList = null;

	public ArrayList<IDTO> getResultDTOList() {
		return resultDTOList;
	}

	public void setResultDTOList(ArrayList<IDTO> resultDTOList) {
		this.resultDTOList = resultDTOList;
	}
	
	public void add(IDTO currentDTO)
	{
		if (resultDTOList == null)
			resultDTOList = new ArrayList<IDTO>(1);
		
		resultDTOList.add(currentDTO);
	}
	
}
