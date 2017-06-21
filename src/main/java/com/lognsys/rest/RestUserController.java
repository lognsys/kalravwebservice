package com.lognsys.rest;

import java.util.List;
import java.util.Properties;

import javax.ws.rs.Produces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lognsys.dao.dto.UsersDTO;
import com.lognsys.dao.jdbc.JdbcGroupRepository;
import com.lognsys.dao.jdbc.JdbcUserRepository;
import com.lognsys.exception.UserDataAccessException;
import com.lognsys.model.Users;
import com.lognsys.model.UsersTable;
import com.lognsys.service.UserService;
import com.lognsys.util.Constants;
import com.lognsys.util.ObjectMapper;

@Produces("application/json")
@RestController
public class RestUserController {

	@Autowired
	UserService userService;


	@Autowired
	private JdbcGroupRepository jdbcGroupRepository;

	@Autowired
	private JdbcUserRepository jdbcUserRepository;

	// Injecting resource application.properties.
	@Autowired
	@Qualifier("applicationProperties")
	private Properties applicationProperties;

	
	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getallusers/", method = RequestMethod.GET)
	public ResponseEntity<List<UsersTable>> listAllUsers() {
		List<UsersTable> users = ObjectMapper.mapToUserTable(jdbcGroupRepository.getAllUsersAndGroup());
		if (users.isEmpty()) {
			return new ResponseEntity<List<UsersTable>>(HttpStatus.NO_CONTENT);// You
																				// many
																				// decide
																				// to
																				// return
																				// HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<UsersTable>>(users, HttpStatus.OK);
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/getsingleuser/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Users> getUser(@PathVariable("id") int id) {
		System.out.println("Fetching User with id " + id);
		List<Users> users = userService.getUserWithRoleAndGroup(id);
		if (users == null) {
			System.out.println("User with id " + id + " not found");
			return new ResponseEntity<Users>(HttpStatus.NOT_FOUND);
		}
		for (Users user : users) {
			return new ResponseEntity<Users>(user, HttpStatus.OK);
		}
		return null;
	}

	/**
	 * 
	 * @param id
	 * @param realname
	 * @param username
	 * @param auth_id
	 * @param phone
	 * @param provenance
	 * @param birthdate
	 * @param enabled
	 * @param notification
	 * @param device
	 * @param address
	 * @param city
	 * @param state
	 * @param zipcode
	 * @return
	 */
	@RequestMapping(value = "/createuser/{id}/{realname}/{username}/{auth_id}/{phone}/{provenance}/{birthdate}/{enabled}/{notification}/{device}/{address}/{city}/{state}/{zipcode}/{firstname}/{lastname}/{group}/{role}", method = RequestMethod.POST)
	public ResponseEntity<UsersDTO> createUser(@PathVariable(value = "id") int id,
			@PathVariable(value = "realname") String realname, @PathVariable(value = "username") String username,
			@PathVariable(value = "auth_id") String auth_id, @PathVariable(value = "phone") String phone,
			@PathVariable(value = "provenance") String provenance, @PathVariable(value = "birthdate") String birthdate,
			@PathVariable(value = "enabled") Boolean enabled,
			@PathVariable(value = "notification") Boolean notification, @PathVariable(value = "device") String device,
			@PathVariable(value = "address") String address, @PathVariable(value = "city") String city,
			@PathVariable(value = "state") String state, @PathVariable(value = "zipcode") String zipcode) {
		int userId;
		UsersDTO usersDTO = new UsersDTO(id, realname, username, auth_id, phone, provenance, birthdate, enabled,
				notification, device, address, city, state, zipcode);
		System.out.println("Creating User toString " + usersDTO.toString());

		boolean isExists = jdbcUserRepository.isExists(usersDTO.getUsername());
		System.out.println("Creating User isExists " + isExists);
		if (isExists) {
			System.out.println(" isExists usersDTO.getUsername() " + usersDTO.getUsername());

			usersDTO = (jdbcUserRepository.findUserByUsername(usersDTO.getUsername()));
			System.out.println(" isExists usersDTO.tostring " + usersDTO.toString());

			return new ResponseEntity<UsersDTO>(usersDTO, HttpStatus.OK);
		} else {
			userId = jdbcUserRepository.addUser(usersDTO);
			usersDTO.setId(userId);
			System.out.println("Creating User userId " + userId);
			System.out.println("Creating User usersDTO tostring after add " + usersDTO.toString());
			// userService.addUser(user);
			// CREATED====201
			return new ResponseEntity<UsersDTO>(usersDTO, HttpStatus.CREATED);
		}
	}

	/**
	 * 
	 * @param id
	 * @param user
	 * @return
	 */

	@RequestMapping(value = "/updateuser/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Users> updateUser(@PathVariable("id") int id, @RequestBody Users user) {
		System.out.println("Updating User " + id);

		// Users currentUser = userService.getUserWithRoleAndGroup(id);
		List<Users> currentUsers = userService.getUserWithRoleAndGroup(id);

		if (currentUsers == null) {
			System.out.println("User with id " + id + " not found");
			return new ResponseEntity<Users>(HttpStatus.NOT_FOUND);
		}
		System.out.println("Updating User user.getCity()" + user.getCity());

		Users obj = new Users(id, user.getAuth_id(), user.getUsername(), user.getRealname(), user.getPhone(),
				user.getProvenance(), user.getBirthdate(), true, true, user.getDevice(), user.getAddress(),
				user.getCity(), user.getState(), user.getZipcode(), user.getFirstname(), user.getLastname(),
				user.getGroup(), user.getRole());
		currentUsers.add(obj);
		for (Users u : currentUsers) {
			userService.updateUser(u);
			return new ResponseEntity<Users>(u, HttpStatus.OK);
		}

		return null;
	}

	/**
	 * TODO : Please remove delete user from Controller
	 * 
	 * @param id
	 * @return
	 * @deprecated
	 */
	@RequestMapping(value = "/deleteuser/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Users> deleteUser(@PathVariable("id") int id) {
		System.out.println("Fetching & Deleting User with id " + id);

		List<Users> user = userService.getUserWithRoleAndGroup(id);

		if (user == null) {
			System.out.println("Unable to delete. User with id " + id + " not found");
			return new ResponseEntity<Users>(HttpStatus.NOT_FOUND);
		} else {
			int[] ids = new int[user.size()];
			for (int i = 0; i < user.size(); i++) {
				ids[i] = user.get(i).getId();
			}
			userService.deleteUsers(ids);
		}
		return new ResponseEntity<Users>(HttpStatus.NO_CONTENT);
	}

	/**
	 * 
	 * @param username
	 * @return
	 * @throws JsonProcessingException
	 */
	@RequestMapping(value = "/getUser/{username:.+}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getsingleuserbyusername(@PathVariable String username) throws JsonProcessingException {
		Users user = null;

		
		try {
			user = userService.getUserByUsername(username);
		} catch (UserDataAccessException ue) {

			// check if user is null
			if (ue.getMessage()
					.equals(applicationProperties.getProperty(Constants.EXCEPTIONS_MSG.exception_userempty.name()))) {
			
				
				return new ResponseEntity<String>(
						applicationProperties.getProperty(Constants.REST_MSGS.response_userempty.name()),
						HttpStatus.NOT_FOUND);
			}

			if (ue.getMessage()
					.equals(applicationProperties.getProperty(Constants.EXCEPTIONS_MSG.exception_userinvalid.name()))) {
				return new ResponseEntity<String>(
						applicationProperties.getProperty(Constants.REST_MSGS.response_userinvalid.name()),
						HttpStatus.BAD_REQUEST);
			}

		}
		
		return new ResponseEntity<Users>(user, HttpStatus.OK);

	}
}