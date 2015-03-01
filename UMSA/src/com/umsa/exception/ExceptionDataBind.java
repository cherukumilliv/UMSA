package com.umsa.exception;


@SuppressWarnings("serial")
public class ExceptionDataBind extends ExceptionBase
{
	public ExceptionDataBind (Throwable originalException)
	{
		super(originalException);
	}
	public ExceptionDataBind (String localCode, String localDesc)
	{
		super(localCode, localDesc);
	}
	public ExceptionDataBind (String localCode, String localDesc, Throwable originalException)
	{
		super(localCode, localDesc, originalException);
	}
}
