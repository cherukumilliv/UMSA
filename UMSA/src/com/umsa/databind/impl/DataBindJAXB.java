package com.umsa.databind.impl;

import java.util.Properties;

import com.umsa.databind.DataBindAbstract;
import com.umsa.databind.DefaultDataBindJAXB;
import com.umsa.exception.ExceptionCode;
import com.umsa.exception.ExceptionDataBind;


public class DataBindJAXB extends DataBindAbstract
{
	private DefaultDataBindJAXB m_encodeDecode = null;
	private boolean m_isParseResponseString = false;
	
	public DataBindJAXB(String aPackageName, Properties aMarshalUnmarshalOptions, boolean aIsParseResponseString)
	{
		m_encodeDecode = new DefaultDataBindJAXB(aPackageName, aMarshalUnmarshalOptions);
		m_isParseResponseString = aIsParseResponseString;
	}
	
	public Object encode(Object aObj) throws ExceptionDataBind
	{
		Object[] objectList = new Object[1];
		objectList[0] = aObj;
		try
		{
			return m_encodeDecode.encode(objectList);
		}catch(Exception aException)
		{
			throw new ExceptionDataBind(ExceptionCode.ERR_UMSA_XML_PARSING_EXCEPTION, "EXTERNAL Exception @ DataBindJAXB.encode():", aException);
		}
	}
	
	public Object decode(Object aStrMessage)throws ExceptionDataBind
	{
		String inputString = null;
		try
		{
			inputString = (String)aStrMessage;
		}catch(ClassCastException aClassCastException)
		{
			throw new ExceptionDataBind(ExceptionCode.ERR_UMSA_INVALID_INPUT, "LOCAL Exception @ DataBindJAXB.decode(): Expecting a String object:", aClassCastException);
		}
		try
		{
			if(m_isParseResponseString)
			{
				inputString = parseStringForBadCharacters(inputString);
			}
			return m_encodeDecode.decode(inputString)[0];	
		}catch(Exception aException)
		{
			throw new ExceptionDataBind(ExceptionCode.ERR_UMSA_XML_PARSING_EXCEPTION, "EXTERNAL Exception @ DataBindJAXB.decode():", aException);
		}
	}
	
	private String parseStringForBadCharacters(String aMessage)
	{
		char[] ca = aMessage.toCharArray();
		for(int i = 0; i < aMessage.length(); i++)
		{
			if((ca[i] < 32) || (ca[i] > 126))
			{
				ca[i] = ' ';
			}
		}
		return new String(ca);
	}
	
}
