package com.umsa.exception;

@SuppressWarnings("serial")
public class ExceptionCachedRowSet extends ExceptionBase
{
	public ExceptionCachedRowSet (Throwable originalException)
	{
		super(originalException);
	}
	public ExceptionCachedRowSet (String localCode, String localDesc)
	{
		super(localCode, localDesc);
	}
	public ExceptionCachedRowSet (String localDesc)
	{
		super("", localDesc);
	}
	public ExceptionCachedRowSet (String localCode, String localDesc, Throwable originalException)
	{
		super(localCode, localDesc, originalException);
	}
}
