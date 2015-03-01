package com.umsa.exception;

@SuppressWarnings("serial")
public class ExceptionBase extends Exception
{

	private Throwable m_originalException;      //Reference to original exception
	private String m_localErrorCode = "";            //Reference to description in XML message file
	private String m_localErrorDescription = "";     //Built from XML message
	@SuppressWarnings("unused")
	private String m_externalErrorCode = "";         //External System's original Error Code
	@SuppressWarnings("unused")
	private String m_externalErrorDescription = "";  //External System's original Error Description
	
   public ExceptionBase (Throwable aOriginalException)
   {
	   this("", "", aOriginalException);
   }
   
   public ExceptionBase (String aLocalCode, String aLocalDesc)
   {
	   this(aLocalCode, aLocalDesc, null);
   }
	public ExceptionBase (String aLocalCode, String aLocalDesc, 
							Throwable aOriginalException)
	{
		setOriginalException(aOriginalException);
		setLocalErrorCode(aLocalCode);
		setLocalErrorDescription(aLocalDesc);
	}
	public String getErrorCode()
	{
		if(m_localErrorCode.trim().length() == 0)
		{
			if((m_originalException != null && m_originalException instanceof ExceptionBase))
			{
				return ((ExceptionBase)m_originalException).getErrorCode();
			}
			else return "";
		}
		else return m_localErrorCode;
	}
	
	public Throwable getRootOriginalException()
	{
		if(getOriginalException() != null)
		{ 
			if(getOriginalException() instanceof ExceptionBase)
			{
				Throwable t = ((ExceptionBase)getOriginalException()).getRootOriginalException();
				if(t == null)
				return getOriginalException();
				else return t;	
			}
			else return getOriginalException();
		}else return null;
	}
	public String getMessage ()
	{
		String buff = "";
		if (m_localErrorCode.trim().length() != 0)
		   buff = buff + m_localErrorCode.toString();
	   if (m_localErrorDescription.trim().length() != 0)
		   buff = buff + ", " + m_localErrorDescription.toString();
		if (m_originalException != null)
			buff = buff + m_originalException.getLocalizedMessage();
		return buff;
	}
	protected void setOriginalException (Throwable aOriginalException)
	{
		m_originalException = aOriginalException;
	}
	public void setLocalErrorCode (String aLocalCode)
	{
		m_localErrorCode = aLocalCode;
	}
	public void setLocalErrorDescription (String aLocalDesc)
	{
		m_localErrorDescription = aLocalDesc;
	}
	public Throwable getOriginalException ()
	{
		return m_originalException;
	}
	public String getLocalErrorCode()
	{
		return m_localErrorCode;
	}
	public String getLocalErrorDescription ()
	{
		return m_localErrorDescription;
	}    
}
