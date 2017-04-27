package com.lognsys.dao;

import java.util.List;

import com.lognsys.dao.dto.AuditoriumsDTO;
import com.lognsys.dao.dto.DramasAuditoriumsDTO;
import com.lognsys.dao.dto.GroupsDTO;
import com.lognsys.dao.dto.UsersGroupsDTO;

public interface AuditoriumRepository {

	public List<AuditoriumsDTO> getAllAuditoriums();

	public String findAuditoriumBy(int drama_id);

	public List<DramasAuditoriumsDTO> getDramasByAuditorium(String auditoriumname);

	public boolean adddAuditorium(String audi_id);

	
	public int findIDBy(String auditoriumname);

	
//	getting List of  all getAllDramasAndAuditorium 
	public List<DramasAuditoriumsDTO> getAllDramasAndAuditorium();

}
