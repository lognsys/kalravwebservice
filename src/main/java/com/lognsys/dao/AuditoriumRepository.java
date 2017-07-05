package com.lognsys.dao;

import java.util.List;
import com.lognsys.dao.dto.AuditoriumsDTO;
import com.lognsys.dao.dto.DramasAuditoriumsDTO;

public interface AuditoriumRepository {

	public List<AuditoriumsDTO> getAllAuditoriums();

	public  List<AuditoriumsDTO>  findAuditoriumBy(int drama_id);

	public void addAuditoriums(AuditoriumsDTO auditoriumsDTO);


	public List<DramasAuditoriumsDTO> getAllDramasAndAuditorium();
	

	public  List<AuditoriumsDTO>  getAuditoriumListBy(int id,int dramas_id);

}
