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
import com.umsa.jaxb.course.Descriptions.Course.Prerequisite;
import com.umsa.jaxb.course.Descriptions.Course.Prerequisite.OrChoice;
import com.umsa.jaxb.rotation.Rotations;
import com.umsa.jaxb.rotation.Rotations.RotationYear;
import com.umsa.jaxb.schedule.Schedule;
import com.umsa.jaxb.schedule.Schedule.ScheduledCourse;
import com.umsa.jaxb.schedule.Schedule.ScheduledCourse.Session;
import com.umsa.jaxb.schedule.Schedule.ScheduledCourse.Session.Course;
import com.umsa.xml.parserer.XmlDomParser;


public class TestJaxb {


	
	public static void main(String args[])
	{
		(new TestJaxb()).testRotation();
	}
	
	public void testRotation()
	{
		Properties jaxbProp = new Properties();
		jaxbProp.put(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		String packageStr = "com.umsa.jaxb.rotation";
		String message = null;
		DataBindJAXB dataBindJAXB = new DataBindJAXB(packageStr, jaxbProp, true);
		
		try {
			XmlDomParser parser = new XmlDomParser("http://comp.umsl.edu/~xml_data/Rotation.xml");
			System.out.println(parser.toString());
			message = parser.toString();
			
			Rotations rotation = (Rotations) dataBindJAXB.decode(message);
			
			List<RotationYear> ryearList = rotation.getRotationYear();
			
			for(int i = 0 ; ryearList != null && i < ryearList.size();i++)
			{
				RotationYear ryear = ryearList.get(i);
				System.out.println("year :" + ryear.getYear());
				List<RotationYear.Course> r_courseList = ryear.getCourse();
				
				for(int j = 0 ; r_courseList != null && j < r_courseList.size();j++)
				{
					RotationYear.Course r_course = r_courseList.get(j);
					System.out.println("Course Name: " + r_course.getCourseName());
					System.out.println("Course Number: " + r_course.getCourseNumber());
					System.out.println("Subject: " + r_course.getSubject());

					List<RotationYear.Course.RotationTerm> r_termList = r_course.getRotationTerm();
					for(int k = 0 ; r_termList != null && k < r_termList.size();k++)
					{
						RotationYear.Course.RotationTerm r_rterm = r_termList.get(k);
						System.out.println("term : " + r_rterm.getTerm());
						List<String> r_timecodeList = r_rterm.getTimeCode();
						for(int l = 0 ; r_timecodeList != null && l < r_timecodeList.size();l++)
						{							
							System.out.println("timecode : " + r_timecodeList.get(l));
						}
					}
				}
				
			}
			
			
		} catch (SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExceptionDataBind e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 	
	}
	public void testCourse()
	{
		Properties jaxbProp = new Properties();
		jaxbProp.put(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		String packageStr = "com.umsa.jaxb.course";
		String message = null;
		DataBindJAXB dataBindJAXB = new DataBindJAXB(packageStr, jaxbProp, true);
		
		try {
			XmlDomParser parser = new XmlDomParser("http://comp.umsl.edu/~xml_data/Courses.xml");
			System.out.println(parser.toString());
			message = parser.toString();
			
			Descriptions description = (Descriptions) dataBindJAXB.decode(message);
			List<com.umsa.jaxb.course.Descriptions.Course> courseList = description.getCourse();
			
			for(int i = 0 ; courseList != null && i < courseList.size();i++)
			{
				com.umsa.jaxb.course.Descriptions.Course course = courseList.get(i);
				System.out.println("***************");
				System.out.println("Course Name :" + course.getCourseName());
				System.out.println("Course Description :" + course.getCourseDescription());
				System.out.println("Course Credit :" + course.getCredit());
				System.out.println("Course Subject :" + course.getSubject());
				System.out.println("Course Course Number :" + course.getCourseNumber());
				
				Prerequisite prereq = course.getPrerequisite();
				if(prereq != null)
				{
					System.out.println("Pre Req :" + prereq.getAdditionalPreq());
					OrChoice rchoice = prereq.getOrChoice();
					if(rchoice != null)
					{
						List<JAXBElement<List<String>>> ndrequestJAXBList =  rchoice.getAndRequired();
						
						for(int k = 0 ; ndrequestJAXBList != null && k < ndrequestJAXBList.size(); k++)
						{
							JAXBElement<List<String>> jaxbElementList = ndrequestJAXBList.get(k);
							List<String>ndRequestList =  jaxbElementList.getValue();
							for(int l = 0 ; ndRequestList != null && l < ndRequestList.size(); l++)
							{
								String prereqStr = ndRequestList.get(l);
								System.out.println("prereq : " + prereqStr);
							}
						}
					}
				}
				System.out.println("***************");

			}
			
		} catch (SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExceptionDataBind e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void testSchedule()
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
