package com.lognsys.service;

import java.io.IOException;
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
import com.lognsys.dao.dto.UsersDTO;
import com.lognsys.dao.jdbc.JdbcGroupRepository;
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

	// Injecting resource sql.properties.

	@Autowired
	@Qualifier("applicationProperties")
	private Properties applicationProperties;

	/**
	 * Add user to database.. Check if user already exists in db
	 * 
	 * @return
	 */
	// TODO 3: Add users and get ID which needs to be added to the group as well
	// as roles
	@Transactional
	public void addUser(Users users) {
		String username = users.getUsername();

		UsersDTO usersDTO = ObjectMapper.mapToUsersDTO(users);

		try {
			boolean isExists = jdbcUserRepository.isExists(username);

			if (isExists) {
				LOG.info("Found user in database with username - " + username);

			} else {

				LOG.info("#addUser - " + "Adding user in database with - " + username);
				System.out.println("#addUser - " + "Adding user in database with - " + username);
				 jdbcUserRepository.addUser(usersDTO);

			}
		} catch (DataAccessException dae) {
			LOG.error(dae.getMessage());
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

				if (!isDelete)
					LOG.info("#deleteUser - " + "failed to delete user with ID - " + id);

			} catch (DataAccessException dae) {

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
				} 
			} catch (DataAccessException dae) {

				LOG.error(dae.getMessage());
				throw new IllegalStateException("Error : Failed to delete user!");
			}

			//

		}
	}

	/**
	 * @param user
	 */
	public void updateUser(Users user) {

	}

	/**
	 * 
	 * This method updates json and refreshed the list of Users
	 * @return returns the list of users from database
	 */
	public List<UsersDTO> getUsers() {
		
		//Refresh list after deletion of user 
		try {
			refreshUserList();
		} catch (IOException e) {
			// TODO Auto-generated catch block
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
	 * 
	 * @param id
	 * @return
	 */
	public UsersDTO findByUser(int id) {

		try {
			return jdbcUserRepository.findUserById(id);
		} catch (DataAccessException dae) {
			LOG.error(dae.getMessage());
			throw new IllegalAccessError("Failed to get user from database with ID - " + id);
		}
	}

}
