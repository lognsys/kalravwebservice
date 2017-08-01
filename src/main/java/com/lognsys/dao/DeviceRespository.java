package com.lognsys.dao;

import java.util.List;

import com.lognsys.dao.dto.DeviceDTO;
import com.lognsys.dao.dto.DramasDTO;
import com.lognsys.dao.dto.UsersDTO;

public interface DeviceRespository {

	/**
	 * Add device into database
	 * 
	 * @param device
	 */
	public boolean addDevice(DeviceDTO deviceDTO);
	
	
	/**
	 * Check if device exists by Token
	 * @param Token
	 * @return
	 */
	public boolean isExists(String deviceToken);
	/**
	 * Check if device exists by Token
	 * @param Token
	 * @return
	 */
	public boolean isExists(Integer users_id);

	/**
	 * Update device information, enable/disable etc..
	 * 
	 * @param device
	 */
	public boolean updateDevice(DeviceDTO deviceDTO);


	/**
	 * Get All Device
	 * 
	 * @return
	 */
	public List<DeviceDTO> getAllDeviceDTO();

	/**
	 * Delete drama by id
	 * @param id
	 */
	public boolean deleteDeviceByUserId(Integer users_id);

	/**
	 * Get drama by Id
	 * 
	 * @param id
	 * @return
	 */
	public List<DeviceDTO> findDeviceByUsersId(Integer users_id);
	

}
