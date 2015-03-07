package com.umsa.interfaces;

public interface IConstants 
{	
	//// JAXB Package Identifiers - Start
	public static final String ROTATION_JAXB_PACKAGE = "com.umsa.jaxb.rotation";
	public static final String COURSE_JAXB_PACKAGE = "com.umsa.jaxb.course";
	public static final String SCHEDULE_JAXB_PACKAGE = "com.umsa.jaxb.schedule";
	//// JAXB Package Identifiers - Stop
	
	//// Cache Entries - Start
	public static final String UMSA_PROPERTY_FILE_CACHE_ID = "UMSA_PROPERTY_FILE_CACHE_ID";
	public static final String UMSA_CACHE_RESOURCE_COURSE = "UMSA_CACHE_RESOURCE_COURSE";
	public static final String UMSA_CACHE_RESOURCE_ROTATION = "UMSA_CACHE_RESOURCE_ROTATION";
	public static final String UMSA_CACHE_RESOURCE_SCHEDULE = "UMSA_CACHE_RESOURCE_SCHEDULE";
	//// Cache Entries - Stop
	
	//// Error Types - Start
	public static final String INVALIDINPUT = "InvalidInput";
	//// Error Types - Stop
	
	public static final String RESOURCE_NAME = "RESOURCENAME";
	public static final String RESOURCE_NAME_DATASOURCE = "RESOURCENAMEDATASOURCE";
	public static final String RESOURCE_NAME_DRIVER = "RESOURCENAMEDRIVER";
	public static final String USER_ID = "USERID";
	public static final String PASSWORD = "PASSWORD";
	public static final String DB_JDBC_OPTION = "OPTION";
	public static final String INITIAL_CONTEXT = "INITIALCONTEXT";
	public static final String PROVIDER_URL = "PROVIDERURL";
	public static final String UMSA_DB_JNDI_NAME = "UMSA_DB_JNDI_NAME";
	public static final int TYPE_DB_CALLABLE = 0;
	public static final int TYPE_DB_PREPARED = 1;

}
