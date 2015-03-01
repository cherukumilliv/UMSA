package com.umsa.exception;


@SuppressWarnings("serial")
public class ExceptionMapper extends ExceptionBase
{
	public ExceptionMapper (Throwable originalException)
	{
		super(originalException);
	}
	public ExceptionMapper (String localCode, String localDesc)
	{
		super(localCode, localDesc);
	}
	public ExceptionMapper (String localCode, String localDesc, Throwable originalException)
	{
		super(localCode, localDesc, originalException);
	}
	
}
