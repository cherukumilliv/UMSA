package com.umsa.databind.impl;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Properties;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.Marshaller;

import org.apache.xerces.dom.ElementNSImpl;
import org.xml.sax.SAXException;

import com.umsa.exception.ExceptionDataBind;
import com.umsa.jaxb.course.Descriptions;
import com.umsa.jaxb.schedule.Schedule;
import com.umsa.jaxb.schedule.Schedule.ScheduledCourse;
import com.umsa.jaxb.schedule.Schedule.ScheduledCourse.Session;
import com.umsa.jaxb.schedule.Schedule.ScheduledCourse.Session.Course;
import com.umsa.xml.parserer.XmlDomParser;


public class TestJaxb {
	
	public static void main(String args[])
	{
		Properties jaxbProp = new Properties();
		jaxbProp.put(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		String packageStr = "com.umsa.jaxb.course";
		packageStr = "com.umsa.jaxb.schedule";
		String message = null;
		DataBindJAXB dataBindJAXB = new DataBindJAXB(packageStr, jaxbProp, true);
		
		try {
//			XmlDomParser parser = new XmlDomParser("http://comp.umsl.edu/~xml_data/Courses.xml");
			XmlDomParser parser = new XmlDomParser("http://comp.umsl.edu/~xml_data/Schedule.xml");
			System.out.println(parser.toString());
			message = parser.toString();
		} catch (SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Schedule sch = (Schedule) dataBindJAXB.decode(message);
			List<Serializable> schContent = sch.getContent();
			
			for(int i = 0 ;schContent != null && i < schContent.size() ; i++)
			{
				if(sch.getContent().get(i) instanceof JAXBElement<?>)
				{
					JAXBElement<?> schCourseJaxb =(JAXBElement<?>)sch.getContent().get(i);
					ScheduledCourse schCourse = (ScheduledCourse) schCourseJaxb.getValue();
					
					List<Serializable> schCourseContent = schCourse.getContent();
					for(int j = 0 ;schCourseContent != null && j < schCourseContent.size() ; j++)
					{
						if(schCourse.getContent().get(j) instanceof JAXBElement<?>)
						{
								JAXBElement<?> jaxElement = (JAXBElement<?>)schCourse.getContent().get(j);
								
								System.out.println("Name :" + jaxElement.getName());
								Object valObject = jaxElement.getValue();
								if(valObject instanceof String)
								{
									String value = jaxElement.getValue().toString();
									System.out.println("Value :" + value);
								}
								if(valObject instanceof Integer)
								{
									String value = jaxElement.getValue().toString();
									System.out.println("Value :" +value);
								}
								if(valObject instanceof Session)
								{
									Session schCourseSession = (Session) valObject;
									if(valObject instanceof ElementNSImpl)
									{
										ElementNSImpl elm = (ElementNSImpl) schCourseSession.getSessionName();
										if( elm.getFirstChild() != null)
										System.out.println("Session Name " + elm.getFirstChild().getNodeValue());
									}
									List<Course> schCourseList = schCourseSession.getCourse();
									for(int k = 0 ;schCourseList != null && k < schCourseList.size();k++)
									{
										Course schSessionCourse = (Course)schCourseList.get(k);
										List<Serializable> schSessionCourseContent =  schSessionCourse.getContent();
										for(int l = 0 ;schSessionCourseContent != null && l < schSessionCourseContent.size() ; l++)
										{
											if(schSessionCourseContent.get(l) instanceof JAXBElement<?>)
											{
												JAXBElement<?> schSessionCourseJAXElement = (JAXBElement<?>)schSessionCourseContent.get(l);
												if("subject".equalsIgnoreCase(schSessionCourseJAXElement.getName().getLocalPart()))
												{
													Object schSessionCourseValObject = schSessionCourseJAXElement.getValue();
													System.out.println("Subkect:schSessionCourseValObject :" + schSessionCourseValObject);
												}												
											}
										}
									}
								}
						}
					}
				}
			}
			

		} catch (ExceptionDataBind e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
