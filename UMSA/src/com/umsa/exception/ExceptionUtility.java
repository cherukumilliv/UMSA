package com.umsa.exception;


@SuppressWarnings("serial")
public class ExceptionUtility extends ExceptionBase
{
	public ExceptionUtility (Throwable originalException)
	{
		super(originalException);
	}
	public ExceptionUtility (String localCode, String localDesc)
	{
		super(localCode, localDesc);
	}
	public ExceptionUtility (String localCode, String localDesc, Throwable originalException)
	{
		super(localCode, localDesc, originalException);
	}
}
