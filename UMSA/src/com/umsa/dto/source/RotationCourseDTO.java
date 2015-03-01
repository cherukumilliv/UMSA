package com.umsa.dto.source;

import com.umsa.interfaces.IDTO;

public class RotationCourseDTO extends CourseDTO implements IDTO {
		private DTOList termList = null;

		public DTOList getTermList() {
			return termList;
		}

		public void setTermList(DTOList termList) {
			this.termList = termList;
		}
		
		public void add(IDTO rotationTermDTO)
		{
			if(termList == null) termList = new DTOList();
			
			termList.add(rotationTermDTO);
		}
		
		public RotationTermDTO getSessionCourse(int index)
		{
			RotationTermDTO returnValue = null;
			
			if(termList != null) returnValue = (RotationTermDTO) termList.getDTO(index);
			
			return returnValue;
		}

}
