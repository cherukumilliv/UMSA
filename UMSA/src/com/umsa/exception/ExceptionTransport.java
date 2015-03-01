package com.umsa.exception;


@SuppressWarnings("serial")
public class ExceptionTransport extends ExceptionBase
{
	public ExceptionTransport (Throwable originalException)
	{
		super(originalException);
	}
	public ExceptionTransport (String localCode, String localDesc)
	{
		super(localCode, localDesc);
	}
	public ExceptionTransport (String localCode, String localDesc, Throwable originalException)
	{
		super(localCode, localDesc, originalException);
	}
}
