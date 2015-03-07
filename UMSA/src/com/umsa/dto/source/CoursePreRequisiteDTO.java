package com.umsa.dto.source;

import com.umsa.interfaces.IDTO;

public class CoursePreRequisiteDTO  implements IDTO {
		private DTOList courseList = null;
		// additional pre-requisite.
		private String message = null;
		
		public DTOList getCourseList() {
			return courseList;
		}

		public void setCourseList(DTOList courseList) {
			this.courseList = courseList;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		public void add(String courseNumber , String subject)
		{
			CourseDTO course = new CourseDTO();
			course.setCourseNumber(courseNumber);
			course.setSubject(subject);
			add(course);
		}
		public void add(IDTO courseDTO)
		{
			if(courseList == null) courseList = new DTOList();
			
			courseList.add(courseDTO);
		}
		
		public CourseDTO getCourse(int index)
		{
			CourseDTO returnValue = null;
			
			if(courseList != null) returnValue = (CourseDTO) courseList.getDTO(index);
			
			return returnValue;
		}

	
}
