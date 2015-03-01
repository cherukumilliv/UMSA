package com.umsa.exception;


@SuppressWarnings("serial")
public class ExceptionController extends ExceptionBase
{
	public ExceptionController (Throwable originalException)
	{
		super(originalException);
	}
	public ExceptionController (String localCode, String localDesc)
	{
		super(localCode, localDesc);
	}
	public ExceptionController (String localCode, String localDesc, Throwable originalException)
	{
		super(localCode, localDesc, originalException);
	}
}
