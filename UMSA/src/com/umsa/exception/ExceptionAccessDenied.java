package com.umsa.exception;

@SuppressWarnings("serial")
public class ExceptionAccessDenied extends ExceptionBase
{
	public ExceptionAccessDenied (Throwable originalException)
	{
		super(originalException);
	}
	public ExceptionAccessDenied (String localCode, String localDesc)
	{
		super(localCode, localDesc);
	}
	public ExceptionAccessDenied (String localCode, String localDesc, Throwable originalException)
	{
		super(localCode, localDesc, originalException);
	}
}
