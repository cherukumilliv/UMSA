package com.umsa.mapper.jaxb;

import java.util.List;

import com.umsa.dto.source.DTOList;
import com.umsa.dto.source.RotationCourseDTO;
import com.umsa.dto.source.RotationDTO;
import com.umsa.dto.source.RotationTermDTO;
import com.umsa.exception.ExceptionMapper;
import com.umsa.interfaces.IConstants;
import com.umsa.interfaces.IMapper;
import com.umsa.jaxb.rotation.Rotations;
import com.umsa.jaxb.rotation.Rotations.RotationYear;

public class RotationJAXBMapper implements IMapper {

	@Override
	public Object mapFrom(Object dto) throws ExceptionMapper {
		Rotations r_rotation = null;
		DTOList rotationList = null;
		if(dto != null && dto instanceof Rotations)
		{
			r_rotation = (Rotations) dto;
		}else
		{
			throw new ExceptionMapper(IConstants.INVALIDINPUT,"Expecting Rotations");
		}
		rotationList = new DTOList();
				
		List<RotationYear> ryearList = r_rotation.getRotationYear();
		
		for(int i = 0 ; ryearList != null && i < ryearList.size();i++)
		{
			RotationYear r_year = ryearList.get(i);
			RotationDTO rdto = new RotationDTO();
			rotationList.add(rdto);
			
			rdto.setYear(String.valueOf(r_year.getYear()));
			
			List<RotationYear.Course> r_courseList = r_year.getCourse();
			
			for(int j = 0 ; r_courseList != null && j < r_courseList.size();j++)
			{
				RotationCourseDTO rCourseDTO = new RotationCourseDTO();
				rdto.addCourse(rCourseDTO);
				
				RotationYear.Course r_course = r_courseList.get(j);
				rCourseDTO.setCourseName(r_course.getCourseName());
				rCourseDTO.setCourseNumber(r_course.getCourseNumber());
				rCourseDTO.setSubject(r_course.getSubject());

				List<RotationYear.Course.RotationTerm> r_termList = r_course.getRotationTerm();
				for(int k = 0 ; r_termList != null && k < r_termList.size();k++)
				{
					RotationTermDTO rotationTermDTO = new RotationTermDTO();
					rCourseDTO.add(rotationTermDTO);
					RotationYear.Course.RotationTerm r_rterm = r_termList.get(k);
					
					String term = r_rterm.getTerm();
					rotationTermDTO.setTerm(term);
					
					List<String> r_timecodeList = r_rterm.getTimeCode();
					for(int l = 0 ; r_timecodeList != null && l < r_timecodeList.size();l++)
					{			
						rotationTermDTO.add(r_timecodeList.get(l));
					}
				}
			}
			
		}

		return rotationList;
	}

	@Override
	public Object mapTo() throws ExceptionMapper {
		// TODO Auto-generated method stub
		return null;
	}

}
