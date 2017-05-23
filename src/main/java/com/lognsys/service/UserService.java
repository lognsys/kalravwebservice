package com.lognsys.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lognsys.dao.dto.GroupsDTO;
import com.lognsys.dao.dto.RolesDTO;
import com.lognsys.dao.dto.UsersDTO;
import com.lognsys.dao.jdbc.JdbcGroupRepository;
import com.lognsys.dao.jdbc.JdbcRolesRepository;
import com.lognsys.dao.jdbc.JdbcUserRepository;
import com.lognsys.model.Users;
import com.lognsys.model.UsersTable;
import com.lognsys.util.CommonUtilities;
import com.lognsys.util.Constants;
import com.lognsys.util.ObjectMapper;
import com.lognsys.util.WriteJSONToFile;

//TODO 2: Add logging at service level
@Service("userService")
public class UserService {

	Logger LOG = Logger.getLogger(this.getClass());

	@Autowired
	private JdbcUserRepository jdbcUserRepository;

	@Autowired
	private JdbcGroupRepository jdbcGroupRepository;

	@Autowired
	private JdbcRolesRepository jdbcRolesRepository;

	// Injecting resource sql.properties.
	@Autowired
	@Qualifier("applicationProperties")
	private Properties applicationProperties;

	/**
	 * Add user to database.. Check if user already exists in db
	 * 
	 * @return
	 */
	@Transactional
	public void addUser(Users users) {
		String username = users.getUsername();

		UsersDTO usersDTO = ObjectMapper.mapToUsersDTO(users);
		
		try {
			boolean isExists = jdbcUserRepository.isExists(username);

			if (isExists) {
				LOG.warn("User exists in database with username - " + username);

			} else {

				LOG.info("#addUser - " + "Adding USER in database with - " + username);
				int userID = jdbcUserRepository.addUser(usersDTO);
				
				LOG.info("#addUser - " + "Adding USER to corresponding GROUP - " + users.getGroup());
				jdbcUserRepository.addUserAndGroup(userID, users.getGroup());

				LOG.info("#addUser - " + "Adding USER to corresponding ROLE - " + users.getRole());
				jdbcUserRepository.addUserAndRole(userID, users.getRole());

				
				
				refreshUserList();

			}
		} catch (DataAccessException | IOException dae) {
			LOG.error(dae.getMessage());
			System.out.println(dae.getMessage());
			throw new IllegalStateException("Error : Failed to add user!");
		}
	}

	/**
	 * Synchronize users from mysql to json files.
	 *
	 * @return
	 * @throws IOException
	 */
	public void refreshUserList() throws IOException {
		List<UsersTable> users = ObjectMapper.mapToUserTable(jdbcGroupRepository.getAllUsersAndGroup());
		ResourceLoader resourceLoader = new FileSystemResourceLoader();
		Resource resource = resourceLoader
				.getResource(applicationProperties.getProperty(Constants.JSON_FILES.user_filename.name()));
		String list = CommonUtilities.convertToJSON(users);
		try {
			WriteJSONToFile.getInstance().write(resource, list);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Delete users from database
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
					refreshUserList();
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
	public void deleteUsers(String[] emailIDs) throws IOException {
		LOG.info("#deleteUser - " + "Deleting total number of users from database - " + emailIDs.length);

		for (String emailID : emailIDs) {
			try {
				boolean isDelete = jdbcUserRepository.deleteUserBy(emailID);

				if (!isDelete) {
					LOG.info("#deleteUser - " + "failed to delete user with ID - " + emailID);
				} else {
					refreshUserList();
				}
			} catch (DataAccessException dae) {

				LOG.error(dae.getMessage());
				throw new IllegalStateException("Error : Failed to delete user!");
			}
		}
	}

	/**
	 * @param user
	 */
	public void updateUser(Users users) {
		boolean isUpdated = false;
		try {
			
			UsersDTO u = ObjectMapper.mapToUsersDTO(users);
			isUpdated = jdbcUserRepository.updateUser(u);
			LOG.info("INFO: updation successful for user - " + users.getUsername());
			System.out.println("INFO: updation successful for user - " + isUpdated);
		} catch (DataAccessException dae) {
			LOG.error(dae.getMessage());
			throw new IllegalStateException("Failed user update : status - " + isUpdated);
		}
	}

	/**
	 * 
	 * This method updates json and refreshed the list of Users
	 * 
	 * @return returns the list of users from database
	 */
	public List<UsersDTO> getUsers() {

		// Refresh list after deletion of user
		try {
			refreshUserList();
		} catch (IOException e) {
			e.printStackTrace();
		}

		LOG.info("#getUsers - Get All Users from database");
		List<UsersDTO> userList;

		try {
			userList = jdbcUserRepository.getAllUsers();
		} catch (DataAccessException dae) {
			LOG.error(dae.getMessage());
			throw new IllegalStateException("Error : Failed to add user!");
		}
		return userList;
	}

	/**
	 * This is the service layer with users and its role and Group
	 * 
	 * @param userId
	 * @return
	 */
	@Transactional
	public List<Users> getUserWithRoleAndGroup(int userId) {

		try {
			List<Users> listUsers=new ArrayList<Users>();
			
			//get Users information from user table
			Users users = ObjectMapper.mapToUsers(jdbcUserRepository.findUserById(userId));
			
			//get Role information with role table
			String role = jdbcRolesRepository.getRoleBy(users.getId());
			if(role!=null ){
				users.setRole(role);
			}
			else{
				users.setRole("User");
			}
			
//			//get Group information 
			String groupName = jdbcGroupRepository.findGroupBy(users.getId());
			System.out.println("getUserWithRoleAndGroup users groupName "+groupName);
			if(groupName!=null){
				users.setGroup(groupName);
			}
			else{
				users.setGroup("None");
			}
			System.out.println("getUserWithRoleAndGroup groupName "+groupName);
			listUsers.add(users);
			return listUsers;
		} catch (DataAccessException dae) {
			System.out.println("getUserWithRoleAndGroup DataAccessException "+dae);
//			LOG.error(dae.getMessage());
//			throw new IllegalAccessError("Failed to get user from database with ID - " + userId);
		}
		return null;
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
	
	@Transactional
	public List<Users> getUserByUsername(String username) {

		try {
			List<Users> listUsers=new ArrayList<Users>();
			UsersDTO userDTo=jdbcUserRepository.findUserByUsername(username);
			
			System.out.println("getUserByUsername userDTo ====== "+userDTo.toString());
			/*
			//get Users information from user table
			Users users = ObjectMapper.mapToUsers(userDTo);
			
			
			System.out.println("getUserByUsername users ====== "+users.toString());
			listUsers.add(users);
			return listUsers;*/
		} catch (DataAccessException dae) {
			System.out.println("getUserByUsername DataAccessException "+dae);
//			LOG.error(dae.getMessage());
//			throw new IllegalAccessError("Failed to get user from database with ID - " + userId);
		}
		return null;
	}

}
