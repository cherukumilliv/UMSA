package com.umsa.interfaces;

import com.umsa.exception.ExceptionDataBind;

public interface IDataBind extends IReceiver
{
	Object encode(Object aList) throws ExceptionDataBind;
	Object decode(Object aObj) throws ExceptionDataBind;
}
