package com.umsa.database;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Properties;



import com.umsa.cache.RepositoryDurable;
import com.umsa.exception.ExceptionCode;
import com.umsa.exception.ExceptionUtility;
import com.umsa.interfaces.IConstants;
import com.umsa.logger.Logger;

public class FrameworkHelper 
{

	public static String getMessageDate()
	{
		Calendar calendar = new GregorianCalendar();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd,HH:mm:ss:S");
		String parsedDate = format.format(calendar.getTime()); 
		String firstHalf = parsedDate.substring(0,8);
		String lastHalf = parsedDate.substring(8);
		parsedDate = firstHalf + "T" + lastHalf;// + sign + offsetAsStr;
		
		return parsedDate ;
	}

	public static String getProperty(String propertyId,String environment,Logger utility) throws ExceptionUtility{
		String retValue = null;
		Properties dbProps = null;
		if((dbProps = (Properties)RepositoryDurable.getInstance().getResource(IConstants.UMSA_PROPERTY_FILE_CACHE_ID + environment)) == null)
		{
			dbProps = getPropertyFile(environment,utility);
		}
		retValue = (String)dbProps.get(propertyId);
		return retValue;
	}
	
	public static Properties getPropertyFile(String environment,Logger utility) throws ExceptionUtility
	{
		Properties dbProps = null;
		try
		{
			if((dbProps = (Properties)RepositoryDurable.getInstance().getResource(IConstants.UMSA_PROPERTY_FILE_CACHE_ID + environment)) == null)
			{	
				String propertyFilename = (java.lang.String) System.getProperty("PropertiesFile");
				propertyFilename = propertyFilename + "." + environment;
				InputStream inputStream = getAsStream(propertyFilename,utility);
				dbProps = new Properties();
				dbProps.load(inputStream);
				RepositoryDurable.getInstance().setResource(IConstants.UMSA_PROPERTY_FILE_CACHE_ID + environment, dbProps);
			}
		} catch (FileNotFoundException aException) {
			throw new ExceptionUtility(ExceptionCode.ERR_UMSA_INVALID_INPUT, "LOCAL Exception @ FrameworkHelper.getPropertyFile():FileNotFoundException", aException);
		} catch (IOException aException) {
			throw new ExceptionUtility(ExceptionCode.ERR_UMSA_INVALID_INPUT, "LOCAL Exception @ FrameworkHelper.getPropertyFile():IOException", aException);
		} catch (Exception aException){
			throw new ExceptionUtility(ExceptionCode.ERR_UMSA_INVALID_INPUT, "LOCAL Exception @ FrameworkHelper.getPropertyFile()", aException);
		} finally{
			
		}
		return dbProps;
	}

	public static InputStream getAsStream(String resourceName,
            Logger logger)
            throws FileNotFoundException, ExceptionUtility
    {
        try
        {
            return getAsURL(resourceName, logger).openStream();
        }
        catch (Exception e)
        {
        	logger.log(Logger.DEBUG_LEVEL,
                    "File not found on relative path.  "
                            + "Will attempt absolute path");
            return getAsStreamFromAbsolutePath(resourceName, logger);
        }
    }

    protected static URL getAsURL(String resourceName,
            Logger logger)
            throws FileNotFoundException
    {
    	URL url = null;
        if (resourceName == null || (resourceName = resourceName.trim()).length() < 1)
        {
            throw new FileNotFoundException("resourceName not defined");
        }
        resourceName = "/" + resourceName;
        logger.log(Logger.DEBUG_LEVEL,"Trying to find resource '"+ resourceName + "'.");
        url = FrameworkHelper.class.getResource(resourceName);
        System.out.println((url!=null)?url.getFile():"Url was null");
        if (url == null)
        {
            throw new FileNotFoundException("propertiesFileName <"+ resourceName + "> not found ");
        }
        return url;
    }

    protected static InputStream getAsStreamFromAbsolutePath(
            String resourceName,
            Logger logger) throws ExceptionUtility
    {

        FileInputStream fis = null;
        try{
	        if (resourceName == null || (resourceName = resourceName.trim()).length() < 1)
	        {
	            throw new FileNotFoundException("resourceName not defined ");
	        }
	
	        if (fis == null)
	        {
	            fis = fetchFileStream(resourceName, logger);
	            if (fis == null)
	            {
	                throw new FileNotFoundException("propertiesFileName <"+ resourceName + "> not found ");
	            }
	        }
		} catch (FileNotFoundException aException) {
			throw new ExceptionUtility(ExceptionCode.ERR_UMSA_INVALID_INPUT, "LOCAL Exception @ FrameworkHelper.getAsStreamFromAbsolutePath():FileNotFoundException", aException);
		}
		return new BufferedInputStream(fis);
    }

    public static FileInputStream fetchFileStream(
            String resourceName,
            Logger logger) throws ExceptionUtility
    {
        File file = new File(resourceName);
        FileInputStream fis = null;

        try
        {
        	logger.log( Logger.DEBUG_LEVEL,"Trying to find properties resource '" + resourceName + "'.");
            fis = new FileInputStream(file);
        }
        catch (FileNotFoundException aException)
        {
        	logger.log( Logger.DEBUG_LEVEL, " resource '"+ resourceName + "' not found.");
			throw new ExceptionUtility(ExceptionCode.ERR_UMSA_INVALID_INPUT, " resource '"+ resourceName + "' not found.LOCAL Exception @ FrameworkHelper.getPropertyFile_LocalEnv():FileNotFoundException", aException);
        }
        return fis;
    }


}
