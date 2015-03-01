package com.umsa.exception;

@SuppressWarnings("serial")
public class ExceptionInvalidSession  extends ExceptionBase{
		public ExceptionInvalidSession (Throwable originalException)
		{
			super(originalException);
		}
		public ExceptionInvalidSession (String localCode, String localDesc)
		{
			super(localCode, localDesc);
		}
		public ExceptionInvalidSession (String localCode, String localDesc, Throwable originalException)
		{
			super(localCode, localDesc, originalException);
		}

}
