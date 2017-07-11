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

import com.lognsys.dao.dto.DramasDTO;
import com.lognsys.dao.dto.GroupsDTO;
import com.lognsys.dao.dto.NotificationsDTO;
import com.lognsys.dao.dto.RolesDTO;
import com.lognsys.dao.dto.UsersDTO;
import com.lognsys.dao.jdbc.JdbcDramaRepository;
import com.lognsys.dao.jdbc.JdbcGroupRepository;
import com.lognsys.dao.jdbc.JdbcNotificationsRepository;
import com.lognsys.dao.jdbc.JdbcRolesRepository;
import com.lognsys.dao.jdbc.JdbcUserRepository;
import com.lognsys.exception.UserDataAccessException;
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
		LOG.info("#addNotification - " + "addNotification notifyID : - " + notifyID);
		LOG.info("#addNotification - " + "addNotification notifyID : - " + notifyID);
		notifications.setId(notifyID);
		sendPost(notifications);
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
	 * @param
	 * 
	 * @return
	 */
	public void deleteUsers(int[] ids) {
		LOG.info("#deleteUser - " + "Deleting total number of users from database - " + ids.length);

		for (int id : ids) {
			try {

				boolean isDelete = jdbcUserRepository.deleteUserBy(id);

				if (!isDelete) {
					LOG.info("#deleteUser - " + "failed to delete user with ID - " + id);
				} else {
					refreshNotificationList();
				}
			} catch (DataAccessException | IOException dae) {

				LOG.error(dae.getMessage());
				throw new IllegalStateException("Error : Failed to delete user!");
			}

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
	 * 
	 * @param user
	 */
	@Transactional
	public boolean updateUser(Users users) {
		boolean isUpdated = false;
		try {

			// Convert Users POJO to UsersDTO
			UsersDTO u = ObjectMapper.mapToUsersDTO(users);

			// TODO add exception
			isUpdated = jdbcUserRepository.updateUser(u);
			isUpdated = jdbcGroupRepository.updateGroupOfUser(users.getUsername(), users.getGroup());
			isUpdated = jdbcRolesRepository.updateRoleOfUser(users.getUsername(), users.getRole());

			refreshNotificationList();

		} catch (DataAccessException dae) {
			LOG.error(dae.getMessage());
			throw new IllegalStateException("Failed user update : status - " + isUpdated);
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
		return isUpdated;

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

	/**
	 * This is the service layer with users and its role and Group
	 * 
	 * @param userId
	 * @return
	 */
	@Transactional
	public Users getUserWithRoleAndGroup(String username) {

		Users users = null;
		
		
//		try {

		
			
			// get Users information from user table
			users = ObjectMapper.mapToUsers(jdbcUserRepository.findUserByUsername(username));
		
			// get Role information with role table
			String role = jdbcRolesRepository.getRoleBy(users.getId());
			if (role != null) {
				users.setRole(role);
			} else {
				users.setRole("User");
			}

			// //get Group information
			String groupName = jdbcGroupRepository.findGroupBy(users.getId());
			if (groupName != null) {
				users.setGroup(groupName);
			} else {
				users.setGroup("None");
			}

			return users;
/*
		} catch (DataAccessException dae) {
			System.out.println("getUserWithRoleAndGroup DataAccessException " + dae);
			// LOG.error(dae.getMessage());
			// throw new IllegalAccessError("Failed to get user from database
			// with ID - " + userId);
			return users;
		}*/
	}
	/**
	 * This is the service layer with users and its role and Group
	 * 
	 * @param userId
	 * @return
	 */
	@Transactional
	public Users getUserWithRoleAndGroup(int id) {
		
		Users users = null;
		
		
		try {
			
			
			
			// get Users information from user table
			users = ObjectMapper.mapToUsers(jdbcUserRepository.findUserById(id));
			
			// get Role information with role table
			String role = jdbcRolesRepository.getRoleBy(users.getId());
			if (role != null) {
				users.setRole(role);
			} else {
				users.setRole("User");
			}
			
			// //get Group information
			String groupName = jdbcGroupRepository.findGroupBy(users.getId());
			if (groupName != null) {
				users.setGroup(groupName);
			} else {
				users.setGroup("None");
			}
			
			return users;
			
		} catch (DataAccessException dae) {
			System.out.println("getUserWithRoleAndGroup DataAccessException " + dae);
			// LOG.error(dae.getMessage());
			// throw new IllegalAccessError("Failed to get user from database
			// with ID - " + userId);
			return users;
		}
	}

	/**
	 * 
	 * @return
	 */
	public List<GroupsDTO> getAllGroups() {

		try {
			return jdbcGroupRepository.getAllGroups();
		} catch (DataAccessException dae) {
			LOG.error(dae.getMessage());
			throw new IllegalAccessError("Error: All groups cannot be retrieved");
		}

	}

	/**
	 * 
	 * @return
	 */
	public List<RolesDTO> getAllRoles() {

		try {
			return jdbcRolesRepository.getAllRoles();
		} catch (DataAccessException dae) {
			LOG.error(dae.getMessage());
			throw new IllegalAccessError("Error: All roles cannot be retrieved");
		}

	}

	/**
	 * Returns User object on success.
	 * 
	 * 
	 * @param username
	 * @return
	 */
	public Users getUserByUsername(String username) {

		Users user = null;

		// Throws user invalid on bad paramters
		if (username.trim().isEmpty() || !CommonUtilities.isValidEmail(username))
			throw new UserDataAccessException(
					applicationProperties.getProperty(Constants.EXCEPTIONS_MSG.exception_userinvalid.name()));

		// throws exception user not found
		try {
			user = ObjectMapper.mapToUsers(jdbcUserRepository.findUserByUsername(username));
		} catch (EmptyResultDataAccessException e) {
			throw new UserDataAccessException(
					applicationProperties.getProperty(Constants.EXCEPTIONS_MSG.exception_userempty.name()), e);
		}

		return user;
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
	// HTTP POST request
		public void sendPost(Notifications notifications) throws Exception {
			String urlParameters=null;
			String url = "http://localhost:8080/notify/";
			URL obj = new URL(url);
			HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

			//add reuqest header
			con.setRequestMethod("POST");
//			con.setRequestProperty("User-Agent", USER_AGENT);
			con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

			if(notifications!=null && notifications.getMessage()!=null){
				if(notifications.getUserId()!=0 && notifications.getRealnamee()!=null){
					 urlParameters = "realname="+notifications.getRealnamee()+"&message="+notifications.getMessage();
	
				}
				else if(notifications.getDramaId()!=0 && notifications.getDramaTitle()!=null){
					 urlParameters = "dramaTitle="+notifications.getDramaTitle()+"&message="+notifications.getMessage();
						
				}else if(notifications.getDramaId()>0 && notifications.getUserId()>0 && notifications.getDramaTitle()!=null && notifications.getRealnamee()!=null)
						{
					 urlParameters =  "realname="+notifications.getRealnamee()+"&dramaTitle="+notifications.getDramaTitle()+"&message="+notifications.getMessage();
				}
				else{
					 urlParameters =  "message="+notifications.getMessage();
						
				}
			}
			
			// Send post request
			con.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(urlParameters);
			wr.flush();
			wr.close();

			int responseCode = con.getResponseCode();
			System.out.println("\nSending 'POST' request to URL : " + url);
			System.out.println("Post parameters : " + urlParameters);
			System.out.println("Response Code : " + responseCode);

			BufferedReader in = new BufferedReader(
			        new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			//print result
			System.out.println(response.toString());

		}
		public Users getUserDetailById(int id) {

			return ObjectMapper.mapToUsers(jdbcUserRepository.findUserById(id));
		}


	
}
