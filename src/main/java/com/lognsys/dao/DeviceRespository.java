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
	public int addDevice(DeviceDTO deviceDTO);
	
	
	/**
	 * Check if device exists by Token
	 * @param Token
	 * @return
	 */
	public boolean isExists(String deviceToken);

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
	public boolean deleteDeviceBy(Integer id);

	/**
	 * Get drama by Id
	 * 
	 * @param id
	 * @return
	 */
	public DeviceDTO findDeviceById(Integer id);
	
	/**
	 * Delete drama by title
	 * @param id
	 */
	public boolean deleteDeviceBy(String deviceToken);

	/**
	 * Get drama by deviceToken
	 * 
	 * @param title
	 * @return DramasDTO
	 */
	public DeviceDTO findDeviceById(String deviceToken);

}
