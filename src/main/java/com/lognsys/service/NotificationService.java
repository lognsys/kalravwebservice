package com.lognsys.service;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;
import java.util.Properties;

import javax.net.ssl.HttpsURLConnection;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lognsys.dao.dto.DeviceDTO;
import com.lognsys.dao.dto.DramasDTO;
import com.lognsys.dao.dto.GroupsDTO;
import com.lognsys.dao.dto.NotificationsDTO;
import com.lognsys.dao.dto.RolesDTO;
import com.lognsys.dao.dto.UsersDTO;
import com.lognsys.dao.jdbc.JdbcDeviceRepository;
import com.lognsys.dao.jdbc.JdbcDramaRepository;
import com.lognsys.dao.jdbc.JdbcGroupRepository;
import com.lognsys.dao.jdbc.JdbcNotificationsRepository;
import com.lognsys.dao.jdbc.JdbcRolesRepository;
import com.lognsys.dao.jdbc.JdbcUserRepository;
import com.lognsys.exception.UserDataAccessException;
import com.lognsys.model.Device;
import com.lognsys.model.Notifications;
import com.lognsys.model.NotificationsTable;
import com.lognsys.model.Users;
import com.lognsys.model.UsersTable;
import com.lognsys.util.CommonUtilities;
import com.lognsys.util.Constants;
import com.lognsys.util.ObjectMapper;
import com.lognsys.util.WriteJSONToFile;

//TODO 2: Add logging at service level
@Service("notificationService")
public class NotificationService {

	Logger LOG = Logger.getLogger(this.getClass());

	@Autowired
	private JdbcUserRepository jdbcUserRepository;

	@Autowired
	private JdbcDeviceRepository jdbcDeviceRepository;
	
	@Autowired
	private JdbcDramaRepository jdbcDramaRepository;
	@Autowired
	private JdbcGroupRepository jdbcGroupRepository;

	@Autowired
	private JdbcRolesRepository jdbcRolesRepository;
	
	@Autowired
	private JdbcNotificationsRepository jdbcNotificationsRepository;

	// Injecting resource application.properties.
	@Autowired
	@Qualifier("applicationProperties")
	private Properties applicationProperties;

	/**
	 * Add user to database.. Check if user already exists in db
	 * 
	 * @return
	 * @throws Exception 
	 */
	@Transactional
	public void addNotification(Notifications notifications) throws Exception {
//		String username = notifications.get();

		NotificationsDTO notificationDTO = ObjectMapper.mapToNotificationsDTO(notifications);
		LOG.info("#addNotification - " + "addNotification notificationDTO toString : - " + notificationDTO.toString());

		int notifyID = jdbcNotificationsRepository.addNotifications(notificationDTO);
	
		notifications.setId(notifyID);
		try {
			refreshNotificationList();
		} catch (IOException io) {
			LOG.fatal("UserService#addUser refresUserList - " + io.getMessage());
		}
	}
	public String getUserRealnameById(int id){
		UsersDTO usersDTO=jdbcUserRepository.findUserById(id);
		return usersDTO.getRealname();
	}
	public String getDramaTitleById(int id){
		DramasDTO dramasDTO=jdbcDramaRepository.findDramaById(id);
		return dramasDTO.getTitle();
	}

	/**
	 * Synchronize users with mysql database
	 *
	 * @return
	 * @throws IOException
	 */
		public void refreshNotificationList() throws IOException {
		List<NotificationsTable> notificationsTables = ObjectMapper.mapToNotificationsDTO(jdbcNotificationsRepository.getAllNotifications());

		ResourceLoader resourceLoader = new FileSystemResourceLoader();
		Resource resource = resourceLoader
				.getResource(applicationProperties.getProperty(Constants.JSON_FILES.notification_filename.name()));
		String list = CommonUtilities.convertToJSON(notificationsTables);

		try {
			WriteJSONToFile.getInstance().write(resource, list);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	/**
	 * Delete users from database
	 * 
	 * @param String
	 *            emailID
	 * @return
	 * @throws IOException
	 */
	public void deleteNotification(String[] messages) throws IOException {
		System.out.println("#deleteNotification messages.length - " +messages.length);
		
		for (String message : messages) {
			try {
				System.out.println("#deleteNotification message - " +message);
				
				boolean isDelete = jdbcNotificationsRepository.deleteNotificationsBy(message);

				if (!isDelete) {
					LOG.info("#deleteNotification - " + "failed to delete Notification with messages - " + messages);
				} else {
					refreshNotificationList();
				}
			} catch (DataAccessException dae) {

				LOG.error(dae.getMessage());
				throw new IllegalStateException("Error : Failed to delete Notification!");
			}
		}
	}


	/**
	 * 
	 * This method updates json and refreshed the list of Users
	 * 
	 * @return returns the list of users from database
	 */
	public List<UsersDTO> getUsers() {

		
		try {
			return jdbcUserRepository.getAllUsers();
		} catch (DataAccessException dae) {
			LOG.error(dae.getMessage());
			throw new IllegalStateException("Error : Failed to add user!");
		}
	
	}

	public Notifications getNotificationByMessage(String message) {

		Notifications notifications = null;

			// get Users information from user table
		notifications = ObjectMapper.mapToNotifications(jdbcNotificationsRepository.findUserByMessage(message));
	

			return notifications;

	}
	/*
	*//**
	 * @return returns the list of drama from database
	 */
	public List<DramasDTO> getDramas() {

		try {
			return jdbcDramaRepository.getAllDramas();
		} catch (DataAccessException dae) {
			LOG.error(dae.getMessage());
			throw new IllegalAccessError("Error: All roles cannot be retrieved");
		}
		
	}
			public Users getUserDetailById(int id) {

			return ObjectMapper.mapToUsers(jdbcUserRepository.findUserById(id));
		}
		public List<DeviceDTO> getDeviceToken() {
			try {
				return jdbcDeviceRepository.getAllDeviceDTO();
			} catch (DataAccessException dae) {
				LOG.error(dae.getMessage());
				throw new IllegalAccessError("Error: All roles cannot be retrieved");
			}
		}
		


	
}
