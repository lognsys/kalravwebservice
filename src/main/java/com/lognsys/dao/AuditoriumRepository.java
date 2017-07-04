package com.lognsys.dao;

import java.util.List;
import com.lognsys.dao.dto.AuditoriumsDTO;
import com.lognsys.dao.dto.DramasAuditoriumsDTO;

public interface AuditoriumRepository {

	public List<AuditoriumsDTO> getAllAuditoriums();

	public  List<AuditoriumsDTO>  findAuditoriumBy(int drama_id);

	public List<DramasAuditoriumsDTO> getDramasByAuditorium(String auditorium_name);

	public void addAuditoriums(AuditoriumsDTO auditoriumsDTO);

	public int findIDBy(String auditoriumname);

	public List<DramasAuditoriumsDTO> getAllDramasAndAuditorium();
	
	
//	public List<DramasAuditoriumsDTO> getAllDramasAndAuditorium();

}
