package com.umsa.dto.source;

import com.umsa.interfaces.IDTO;

public class ScheduleDTO {

	private DTOList scheduleEntryList = null;

	public DTOList getScheduleEntryList() {
		return scheduleEntryList;
	}

	public void setScheduleEntryList(DTOList scheduleEntryList) {
		this.scheduleEntryList = scheduleEntryList;
	}

	
	public void add(IDTO sessionEntryDTO)
	{
		if(scheduleEntryList == null) scheduleEntryList = new DTOList();
		
		scheduleEntryList.add(sessionEntryDTO);
	}
	
	public ScheduleEntryDTO getSessionCourse(int index)
	{
		ScheduleEntryDTO returnValue = null;
		
		if(scheduleEntryList != null) returnValue = (ScheduleEntryDTO) scheduleEntryList.getDTO(index);
		
		return returnValue;
	}
	
}
