package com.umsa.interfaces;

import com.umsa.dto.source.DTOList;
import com.umsa.exception.ExceptionQuery;

public interface IQueryList extends IQuery {
	public DTOList retrieveList(IDTO dto) throws ExceptionQuery;
}
