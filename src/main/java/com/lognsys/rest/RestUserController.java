package com.lognsys.rest;

import java.io.IOException;
import java.util.Properties;
import javax.ws.rs.Produces;
import org.apache.log4j.Logger;
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
import com.lognsys.dao.jdbc.JdbcGroupRepository;
import com.lognsys.dao.jdbc.JdbcUserRepository;
import com.lognsys.exception.UserDataAccessException;
import com.lognsys.model.Users;
import com.lognsys.service.UserService;
import com.lognsys.util.Constants;

@Produces("application/json")
@RestController
public class RestUserController {

	private static final Logger LOG = Logger.getLogger(RestUserController.class);

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
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/getsingleuser/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getUser(@PathVariable("id") int id) {

		Users users = null;

		try {
			users = userService.getUserWithRoleAndGroup(id);
		} catch (UserDataAccessException ue) {
			// empty result returned from database
			if (ue.getMessage()
					.equals(applicationProperties.getProperty(Constants.EXCEPTIONS_MSG.exception_userempty.name()))) {

				return new ResponseEntity<String>(
						applicationProperties.getProperty(Constants.REST_MSGS.response_userempty.name()),
						HttpStatus.NOT_FOUND);
			}
		}

		return new ResponseEntity<Users>(users, HttpStatus.OK);
	}

	/**
	 * 
	 * @param User
	 * @return UserDTO
	 */
	@RequestMapping(value = "/createuser", method = { RequestMethod.POST })
	public ResponseEntity<?> createUser(@RequestBody Users users) {

		boolean isExists = jdbcUserRepository.isExists(users.getUsername());

		if (isExists) {
			String str = applicationProperties.getProperty(Constants.REST_MSGS.response_userexists.name());
			return new ResponseEntity<String>(str, HttpStatus.FOUND);

		} else {

			try {
				int userId = userService.addUser(users);
				users.setId(userId);
			} catch (IOException e) {
				e.printStackTrace();
			}

			return new ResponseEntity<Users>(users, HttpStatus.CREATED);
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
		System.out.println(" updateuser  id  "+id);
		System.out.println(" updateuser  user  "+user.toString());
			
		userService.updateUser(user);
//		Fremont
		user.setId(id);
		return new ResponseEntity<Users>(user, HttpStatus.OK);

	}

	/**
	 * 
	 * @param username
	 * @return
	 * @throws JsonProcessingException
	 */
	@RequestMapping(value = "/getuser/{username:.+}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getSingleUserBy(@PathVariable String username) throws JsonProcessingException {
		Users user = null;

		try {
			user = userService.getUserWithRoleAndGroup(username);
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