package com.umsa.interfaces;

import com.umsa.exception.ExceptionQuery;

public interface IQuery {
		public IDTO retrieve(IDTO dto) throws ExceptionQuery;
}
