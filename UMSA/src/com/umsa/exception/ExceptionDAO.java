package com.umsa.exception;


@SuppressWarnings("serial")
public class ExceptionDAO extends ExceptionBase
{
	public ExceptionDAO (Throwable originalException)
	{
		super(originalException);
	}
	public ExceptionDAO (String localCode, String localDesc)
	{
		super(localCode, localDesc);
	}
	public ExceptionDAO (String localCode, String localDesc, Throwable originalException)
	{
		super(localCode, localDesc, originalException);
	}
}
