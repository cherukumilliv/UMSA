package com.umsa.exception;


@SuppressWarnings("serial")
public class ExceptionQuery extends ExceptionBase
{
	public ExceptionQuery (Throwable originalException)
	{
		super(originalException);
	}
	public ExceptionQuery (String localCode, String localDesc)
	{
		super(localCode, localDesc);
	}
	public ExceptionQuery (String localCode, String localDesc, Throwable originalException)
	{
		super(localCode, localDesc, originalException);
	}
}
