package com.lognsys.dao;

import java.util.List;

import com.lognsys.dao.dto.DramasDTO;
import com.lognsys.dao.dto.UsersDTO;

public interface DramaRespository {

	/**
	 * Add drama into database
	 * 
	 * @param dramas
	 */
	public void addDrama(DramasDTO dramas);
	
	
	/**
	 * Check if drama exists by title
	 * @param title
	 * @return
	 */
	public boolean isExists(String title);

	/**
	 * Update drama information, enable/disable etc..
	 * 
	 * @param drama
	 */
	public void updateDrama(String title);


	/**
	 * Get All dramas
	 * 
	 * @return
	 */
	public List<DramasDTO> getAllDramas();

	/**
	 * Delete drama by id
	 * @param id
	 */
	public boolean deleteDramaBy(Integer id);

	/**
	 * Get drama by Id
	 * 
	 * @param id
	 * @return
	 */
	public DramasDTO findDramaById(Integer id);
	
	/**
	 * Delete drama by title
	 * @param id
	 */
	public boolean deleteDramaBy(String title);

	/**
	 * Get drama by title
	 * 
	 * @param title
	 * @return DramasDTO
	 */
	public DramasDTO findDramaById(String title);

}
