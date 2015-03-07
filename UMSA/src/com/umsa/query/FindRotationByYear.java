package com.umsa.query;

import com.umsa.cache.RepositoryDurable;
import com.umsa.dto.query.CourseQueryDTO;
import com.umsa.dto.query.RotationQueryDTO;
import com.umsa.dto.source.DTOList;
import com.umsa.dto.source.RotationDTO;
import com.umsa.exception.ExceptionQuery;
import com.umsa.interfaces.IConstants;
import com.umsa.interfaces.IDTO;
import com.umsa.interfaces.IQuery;

public class FindRotationByYear implements IQuery {

	@Override
	public IDTO retrieve(IDTO dto) throws ExceptionQuery {
		RotationDTO rotationDTO = null;
		RotationQueryDTO qDTO = null;
		
		if(!(dto instanceof CourseQueryDTO))
		{
			throw new ExceptionQuery(IConstants.INVALIDINPUT,"Expecting CourseQueryDTO");
		}else
		{
			qDTO = (RotationQueryDTO) dto;
		}
		DTOList rotationList = (DTOList)RepositoryDurable.getInstance().getResource(IConstants.UMSA_CACHE_RESOURCE_ROTATION);
		
		for(int i = 0; rotationList != null && i < rotationList.size() ; i++)
		{
			RotationDTO currRotationDTO = (RotationDTO) rotationList.getDTO(i);
			if(currRotationDTO != null &&
					currRotationDTO.getYear().equalsIgnoreCase(qDTO.getYear()))
			{
				rotationDTO = currRotationDTO;
				break;
			}
		}
		return rotationDTO;
	}

}
