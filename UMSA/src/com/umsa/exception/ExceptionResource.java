package com.umsa.exception;

@SuppressWarnings("serial")
public class ExceptionResource extends ExceptionBase
{
	public ExceptionResource (Throwable originalException)
	{
		super(originalException);
	}
	public ExceptionResource (String localCode, String localDesc)
	{
		super(localCode, localDesc);
	}
	public ExceptionResource (String localCode, String localDesc, Throwable originalException)
	{
		super(localCode, localDesc, originalException);
	}
}
