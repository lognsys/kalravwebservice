package com.lognsys.dao;

import java.util.List;

import com.lognsys.dao.dto.DramasDTO;
import com.lognsys.dao.dto.NotificationsDTO;
import com.lognsys.dao.dto.UsersDTO;

public interface NotificationRespository {

	/**
	 * Add notification into database
	 * 
	 * @param dto
	 */
	public int addNotifications(NotificationsDTO dto);
	

	/**
	 * Get All notification
	 * 
	 * @return
	 */
	public List<NotificationsDTO> getAllNotifications();
	
	/**
	 * Delete notification by message
	 * @param message
	 */
	public boolean deleteNotificationsBy(String message);
	
	
	
	/**
	 * Get User by emailId
	 * 
	 * @param emailid
	 * @return NotificationsDTO
	 */
	public NotificationsDTO findUserByMessage(String message);

}
