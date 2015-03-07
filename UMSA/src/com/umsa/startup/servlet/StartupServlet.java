package com.umsa.startup.servlet;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.umsa.cache.RepositoryDurable;
import com.umsa.controller.BusinessController;
import com.umsa.dto.source.DTOList;
import com.umsa.dto.source.ScheduleDTO;
import com.umsa.interfaces.IConstants;
import com.umsa.logger.Logger;

public class StartupServlet extends HttpServlet implements Servlet {
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public StartupServlet() {
		super();
	}

	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest arg0, HttpServletResponse arg1)
	 */
	protected void doGet(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/* (non-Java-doc)mjncxg.wdoPost(HttpServletRequest arg0, HttpServletResponse arg1)
	 */
	protected void doPost(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see javax.servlet.Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig servletConfig) throws ServletException {
		try{
			//String authPropertiesFileName = (String)servletConfig.getInitParameter("Authorization");
			BusinessController controller = new BusinessController();
			DTOList courseList = controller.getCourseDetailsUsingXML(new Logger());
			DTOList rotationList = controller.getRotationDetailsUsingXML(new Logger());
			ScheduleDTO scheduleList = controller.getScheduleDetailsUsingXML(new Logger());
			
			RepositoryDurable.getInstance().setResource(IConstants.UMSA_CACHE_RESOURCE_COURSE, courseList);
			RepositoryDurable.getInstance().setResource(IConstants.UMSA_CACHE_RESOURCE_ROTATION, rotationList);
			RepositoryDurable.getInstance().setResource(IConstants.UMSA_CACHE_RESOURCE_SCHEDULE, scheduleList);
			super.init(servletConfig);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}


}