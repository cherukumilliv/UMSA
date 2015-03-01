package com.umsa.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.umsa.controller.BusinessController;
import com.umsa.dto.database.CourseDTO;
import com.umsa.interfaces.IDTO;
import com.umsa.logger.Logger;



/**
 * @version 	1.0
 * @author
 */
public class CourseAction extends Action {

	/**
	* Constructor
	*/
	public CourseAction() {

		super();

	}
	public ActionForward perform(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws IOException, ServletException {

		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward();
		// return value
		try {
			BusinessController controller = new BusinessController();
			
			ArrayList<IDTO> courseList = controller.getCourseDetails(new Logger());
			
			CourseDTO currentCourse = (CourseDTO) courseList.get(0);
			
			System.out.println("Course Name : " + currentCourse.getCourseName());
			System.out.println("Course Number : "  + currentCourse.getCourseNumber());
			
				
		}  catch (Exception e) {

			// Report the error using the appropriate name and ID.
			errors.add("name", new ActionError("id"));

		}

		// If a message is required, save the specified key(s)
		// into the request for use by the <struts:errors> tag.

		if (!errors.empty()) {
			saveErrors(request, errors);

			// Forward control to the appropriate 'failure' URI (change name as desired)
				forward = mapping.findForward("failure");

		} else {

			// Forward control to the appropriate 'success' URI (change name as desired)
			forward = mapping.findForward("success");

		}

		// Finish with
		return (forward);

	}
}

