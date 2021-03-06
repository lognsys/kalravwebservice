package com.lognsys.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.activity.InvalidActivityException;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.lognsys.dao.dto.DeviceDTO;
import com.lognsys.dao.dto.GroupsDTO;
import com.lognsys.dao.dto.RolesDTO;
import com.lognsys.dao.dto.UsersDTO;
import com.lognsys.dao.jdbc.JdbcDeviceRepository;
import com.lognsys.dao.jdbc.JdbcGroupRepository;
import com.lognsys.dao.jdbc.JdbcRolesRepository;
import com.lognsys.dao.jdbc.JdbcUserRepository;
import com.lognsys.exception.UserDataAccessException;
import com.lognsys.model.Drama;
import com.lognsys.model.Users;
import com.lognsys.model.UsersTable;
import com.lognsys.util.CommonUtilities;
import com.lognsys.util.Constants;
import com.lognsys.util.ObjectMapper;
import com.lognsys.util.WriteJSONToFile;

@Service("userService")
public class UserService {

	Logger LOG = Logger.getLogger(this.getClass());

	@Autowired
	private JdbcUserRepository jdbcUserRepository;
	@Autowired
	private JdbcDeviceRepository jdbcDeviceRepository;

	@Autowired
	private JdbcGroupRepository jdbcGroupRepository;

	@Autowired
	private JdbcRolesRepository jdbcRolesRepository;

	// Injecting resource application.properties.
	@Autowired
	@Qualifier("applicationProperties")
	private Properties applicationProperties;

	
	/**
	 * Add user to database.. Check if user already exists in db
	 * 
	 * TODO : Add rollbackFor is users exists TODO : Add exception for users and
	 * roles and groups which has unique constraints
	 * 
	 * @return
	 * @throws IOException
	 */
	@Transactional(rollbackFor = IllegalArgumentException.class)
	public int addUser(Users users) throws IOException {
		String username = users.getUsername();

		// convert UserDTO -> User Object
		UsersDTO usersDTO = ObjectMapper.mapToUsersDTO(users);

		// Check if User Exists
		if (exists(users))
			throw new IllegalArgumentException("User already exists in database with username - " + username);

		// adding user into database users
		LOG.info("#addUser - " + "Adding USER in database with - " + username);

		
		int users_id = jdbcUserRepository.addUser(usersDTO);
		  System.out.println("addUser users users_id "+users_id);
		  System.out.println("addUser users users.getGroup()) "+users.getGroup());
				
		// adding user into group
		LOG.info("#addUser - " + "Adding USER to corresponding GROUP - " + users.getGroup());
		jdbcUserRepository.addUserAndGroup(users_id, users.getGroup());

	
        //Adding user to corresponding role
		LOG.info("#addUser - " + "Adding USER to corresponding ROLE - " + users.getRole());

		jdbcUserRepository.addUserAndRole(users_id, users.getRole());
		 System.out.println("addUser users  users.getDevice() "+ users.getDevice());
			
		LOG.info("#addUser - " + "Adding USER with  users_id - " + users_id);
		jdbcUserRepository.addUserAndDevice(users_id, users.getDevice());


		try {
			refreshUserList();
		} catch (IOException io) {
			LOG.fatal("UserService#addUser refresUserList - " + io.getMessage());
		}
		return users_id;
	}

	/**
	 * This method synchronizes the users from the database and
	 * loads it into Users.json file. 
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
	 * @param
	 * 
	 * @return
	 *
	 */
	public boolean deleteUsers(Integer[] ids) {
		LOG.info("#deleteUser - " + "Deleting total number of users from database - " + ids.length);

		for (int id : ids) {
			try {

				boolean isDelete = jdbcUserRepository.deleteUserBy(id);

				if (!isDelete) {
					return false;
				} else {
					refreshUserList();
				}
			} catch (DataAccessException | IOException dae) {

				LOG.error(dae.getMessage());
				throw new IllegalStateException("Error : Failed to delete user!");
			}
			
		}return true;
	}

	/**
	 * Delete users from database
	 * 
	 * @param String
	 *            emailID
	 * @return
	 * @throws IOException
	 * 
	 * 
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

			refreshUserList();

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
	 * 
	 * TODO 2 : Need to catch exception at role and group level This is the
	 * service layer with users and its role and Group
	 * 
	 * @param userId
	 * @return
	 * @throws ParseException 3
	 */
	@Transactional(rollbackFor = UserDataAccessException.class)
	public Users getUserWithRoleAndGroup(String response) throws ParseException {
		JSONParser parser = new JSONParser();
		Object obj1 = parser.parse(response);
		   
        JSONObject obj2 =new JSONObject((Map) obj1);
        System.out.println("getUserWithRoleAndGroup obj2 "+obj2);
        
        String username=(String) obj2.get("username");
        System.out.println("getUserWithRoleAndGroup username "+username);
        String device;
        
        if(obj2.get("device")!=null)
        {
        	device=(String) obj2.get("device");
        }else{
        	device=null;
        }
		
		// Throw exception on invalid paramter or empty paramter
		if (username.isEmpty() || !CommonUtilities.isValidEmail(username))
			throw new UserDataAccessException(
					applicationProperties.getProperty(Constants.EXCEPTIONS_MSG.exception_userinvalid.name()));

		Users users = null;

		try 
		{
			 System.out.println("getUserWithRoleAndGroup jdbcUserRepository.findUserByUsername(username)=== "+jdbcUserRepository.findUserByUsername(username).toString());
			    
			// get Users information from user table
			users = ObjectMapper.mapToUsers(jdbcUserRepository.findUserByUsername(username));

			// database returning empty user
			if (users == null)
				throw new UserDataAccessException(
						applicationProperties.getProperty(Constants.EXCEPTIONS_MSG.exception_notfound.name()));

		} catch (DataAccessException dae) {
			 System.out.println("getUserWithRoleAndGroup dae "+dae);
		       
			dae.printStackTrace();

			// throw exception if user is empty
			throw new UserDataAccessException(
					applicationProperties.getProperty(Constants.EXCEPTIONS_MSG.exception_notfound.name()));
		}

		// get Role information with role table
		String role = jdbcRolesRepository.getRoleBy(users.getId());
        System.out.println("\n \ng etUserWithRoleAndGroup role "+role);

		if (role != null) {
			users.setRole(role);
		} else {
			users.setRole(Constants.DEFAULT_ROLE.GUEST.toString());
		}

		// get group information
		String groupName = jdbcGroupRepository.findGroupBy(users.getId());

        System.out.println("\n \n getUserWithRoleAndGroup groupName "+groupName);
		if (groupName != null) {
			users.setGroup(groupName);
		} else {
			users.setRole(Constants.DEFAULT_GROUP.NONE.toString());
		}
		  System.out.println("\n \n getUserWithRoleAndGroup device "+device);
		  System.out.println("\n \n getUserWithRoleAndGroup users.getDevice() "+users.getDevice());
			
	if(users.getDevice()!=null && device!=null){
		System.out.println("getUserWithRoleAndGroup device "+device);
		System.out.println("getUserWithRoleAndGroup users.getDevice() "+users.getDevice());
        
		if(!(users.getDevice().equals(device))){
//			update user
			DeviceDTO deviceDTO= new DeviceDTO();
			deviceDTO.setUsers_id(users.getId());
			if(device!=null)
			deviceDTO.setDeviceToken(device);
			else
			deviceDTO.setDeviceToken(users.getDevice());

			boolean isUpdateDeviceDTO =jdbcDeviceRepository.updateDevice(deviceDTO);
			System.out.println("getUserWithRoleAndGroup isUpdateDeviceDTO "+isUpdateDeviceDTO);
			   
			boolean isUpdate=jdbcUserRepository.updateUserDevice(device,username);
			System.out.println("getUserWithRoleAndGroup isUpdate "+isUpdate);
		      
			if(isUpdate){
				users.setDevice(device);
			}
			  System.out.println("getUserWithRoleAndGroup users.getDevice  "+users.getDevice());
			  
		}
		return users;

	}
	else
		return users;

	}

	/**
	 * 
	 * TODO 1 : Need to catch exception at role and group level This is the
	 * service layer with users and its role and Group
	 * 
	 * @param userId
	 * @return
	 */
	@Transactional(rollbackFor = UserDataAccessException.class)
	public Users getUserWithRoleAndGroup(int id) {

		Users users = null;

		try {
			// get Users information from user table
			users = ObjectMapper.mapToUsers(jdbcUserRepository.findUserById(id));

			if (users == null)
				throw new UserDataAccessException(
						applicationProperties.getProperty(Constants.EXCEPTIONS_MSG.exception_notfound.name()));
		} catch (DataAccessException dae) {
			dae.printStackTrace();

			throw new UserDataAccessException(
					applicationProperties.getProperty(Constants.EXCEPTIONS_MSG.exception_notfound.name()));
		}

		// get Role information with role table
		String role = jdbcRolesRepository.getRoleBy(users.getId());
		if (role != null) {
			users.setRole(role);
		} else {
			users.setRole(Constants.DEFAULT_ROLE.GUEST.toString());
		}

		// //get Group information
		String groupName = jdbcGroupRepository.findGroupBy(users.getId());
		if (groupName != null) {
			users.setGroup(groupName);
		} else {
			users.setRole(Constants.DEFAULT_GROUP.NONE.toString());
		}

		return users;

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
					applicationProperties.getProperty(Constants.EXCEPTIONS_MSG.exception_notfound.name()), e);
		}

		return user;
	}
public boolean exists(Users users) {
		
		return jdbcUserRepository.isExists(users.getUsername());

	}

/*public Users saveFrom(Users users) throws InvalidActivityException{

    String firstName = users.getFirstname();

    if(!StringUtils.isEmpty(firstName) && "Dave".equalsIgnoreCase(firstName)) {
        throw new InvalidActivityException("Sorry Dave");
    }

   UsersDTO usersDTO = ObjectMapper.mapToUsersDTO(users);

    int id = jdbcUserRepository.addUser(usersDTO);

    users.setId(id);

    return users;

}
*/}
