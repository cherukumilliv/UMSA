package com.umsa.databind;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.Properties;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.bind.Unmarshaller;

import org.xml.sax.InputSource;

import com.umsa.exception.ExceptionDataBind;


public class DefaultDataBindJAXB{
	private String m_packageName = null;
	private Properties m_marshalUnmarshalOptions = null;

	public DefaultDataBindJAXB(String i_packageName, Properties i_marshalUnmarshalOptions) {
		this.m_packageName = i_packageName;
		this.m_marshalUnmarshalOptions = i_marshalUnmarshalOptions;
	}
	public Object[] decode(String i_message) throws ExceptionDataBind
	{
		Object[] returnObjects = null;
		JAXBContext jc = null;
		try {
			// create a JAXBContext capable of handling classes generated into
			// the generated package
			//When you invoke JAXBContext.newInstance("aaa.bbb.ccc") , 
			//it tries to load a property file called jaxb.properties from each package, 
			//using the same classloader used to load the JAXBContext class itself. 
			//This classloader may be different from the classloader 
			//which was used to load your application (see the picture). 
			//In this case, you'll see the above error. This problem is often seen 
			//with application servers, J2EE containers, Ant, JUnit, and other applications 
			//that use sophisticated class loading mechanisms.

			//The solution for the situation is to pass your curent class loader. 
			
			jc = JAXBContext.newInstance( m_packageName,this.getClass().getClassLoader());  // package name
			Unmarshaller u = null;

			// create an Unmarshaller
			u = jc.createUnmarshaller();

			// unmarshal a generated instance document into a tree of Java content
			// objects composed of classes from the generated package.
			returnObjects = new Object[]{u.unmarshal( new InputSource(new StringReader(i_message)))};
            
		} 
		catch( JAXBException je ) {
			throw new ExceptionDataBind(je);
		} 
        
		return returnObjects;
	}

	/**
	 * @throws ExceptionDataBind 
	 * @see com.sbc.eia.bis.embus.service.access.IEncoder#encode(Object[])
	 */
	public String encode(Object[] i_objectArray) throws ExceptionDataBind
	{
		// create a Marshaller and marshal to a string
		StringWriter temp = new StringWriter();
		JAXBContext jc = null;
		try {
			// create a JAXBContext capable of handling classes generated into
			// the generated package
			//When you invoke JAXBContext.newInstance("aaa.bbb.ccc") , 
			//it tries to load a property file called jaxb.properties from each package, 
			//using the same classloader used to load the JAXBContext class itself. 
			//This classloader may be different from the classloader 
			//which was used to load your application (see the picture). 
			//In this case, you'll see the above error. This problem is often seen 
			//with application servers, J2EE containers, Ant, JUnit, and other applications 
			//that use sophisticated class loading mechanisms.

			//The solution for the situation is to pass your curent class loader. 


			jc = JAXBContext.newInstance( m_packageName,this.getClass().getClassLoader());  // package name
			Marshaller m = null;
			m = jc.createMarshaller();
			setupMarshaller( m, m_marshalUnmarshalOptions );         
			m.marshal( i_objectArray[0], temp );
		} 
		catch( PropertyException pe ) {
			throw new ExceptionDataBind(pe);
		}
		catch( JAXBException je ) {
			throw new ExceptionDataBind(je);
		}        
        
		return temp.toString(); 
	}

	private void setupMarshaller( Marshaller io_marshaller, Properties i_options ) throws PropertyException{
        
		if ( i_options != null ) {
			if ( i_options.containsKey( Marshaller.JAXB_FORMATTED_OUTPUT ) && i_options.getProperty(Marshaller.JAXB_FORMATTED_OUTPUT) != null )
			{
				if ( i_options.getProperty(Marshaller.JAXB_FORMATTED_OUTPUT).trim().equalsIgnoreCase("true") )
				{
					io_marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE );
				}
			}
    
			if ( i_options.containsKey( Marshaller.JAXB_ENCODING ) && i_options.getProperty(Marshaller.JAXB_ENCODING ) != null )
			{
				io_marshaller.setProperty(Marshaller.JAXB_ENCODING, i_options.getProperty(Marshaller.JAXB_ENCODING));
			}
            
			if ( i_options.containsKey( Marshaller.JAXB_NO_NAMESPACE_SCHEMA_LOCATION ) && i_options.getProperty( Marshaller.JAXB_NO_NAMESPACE_SCHEMA_LOCATION ) != null )
			{
				io_marshaller.setProperty(Marshaller.JAXB_NO_NAMESPACE_SCHEMA_LOCATION, i_options.getProperty( Marshaller.JAXB_NO_NAMESPACE_SCHEMA_LOCATION ));
			}
            
			if ( i_options.containsKey( Marshaller.JAXB_SCHEMA_LOCATION ) && i_options.getProperty( Marshaller.JAXB_SCHEMA_LOCATION ) != null )
			{
				io_marshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, i_options.getProperty(Marshaller.JAXB_SCHEMA_LOCATION));
			}
		}
	}

	public Properties getMarshalUnmarshalOptions() {
		return m_marshalUnmarshalOptions;
	}
	public void setMarshalUnmarshalOptions(Properties unmarshalOptions) {
		m_marshalUnmarshalOptions = unmarshalOptions;
	}
	public String getPackageName() {
		return m_packageName;
	}
	public void setPackageName(String name) {
		m_packageName = name;
	}
}
