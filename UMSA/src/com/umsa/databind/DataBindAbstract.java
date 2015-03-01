package com.umsa.databind;

import com.umsa.exception.ExceptionDataBind;
import com.umsa.interfaces.IDataBind;
public abstract class DataBindAbstract implements IDataBind
{
	public abstract Object encode(Object aList) throws ExceptionDataBind;
	public abstract Object decode(Object aStrMessage) throws ExceptionDataBind;
}
