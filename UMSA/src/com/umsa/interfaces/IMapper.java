package com.umsa.interfaces;

import com.umsa.exception.ExceptionMapper;

public interface IMapper {
	public Object mapFrom(Object dto) throws ExceptionMapper;	
	public Object mapTo() throws ExceptionMapper;
}
