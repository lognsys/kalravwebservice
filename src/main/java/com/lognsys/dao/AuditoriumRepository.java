package com.lognsys.dao;

import java.util.Date;
import java.util.List;
import com.lognsys.dao.dto.AuditoriumsDTO;
import com.lognsys.dao.dto.DramasAuditoriumsDTO;

public interface AuditoriumRepository {

	public List<AuditoriumsDTO> getAllAuditoriums();

	public List<AuditoriumsDTO> findAuditoriumBy(int drama_id,Date strDate);

	public boolean addAuditoriums(AuditoriumsDTO auditoriumsDTO);

	public List<DramasAuditoriumsDTO> getAllDramasAndAuditorium();

	public List<AuditoriumsDTO> getAuditoriumListBy(int id, int dramas_id,String date);

	public int findIDBy(String auditorium_name);
	
	public List<DramasAuditoriumsDTO> getDramasByAuditorium(String auditorium_name);

}
