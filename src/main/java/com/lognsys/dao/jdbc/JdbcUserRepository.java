package com.lognsys.dao.jdbc;

import java.util.Hashtable;
import java.util.List;
import java.util.Properties;
import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import com.lognsys.dao.UserRespository;
import com.lognsys.dao.dto.UsersDTO;
import com.lognsys.dao.jdbc.rowmapper.UserByUserIDRowMapper;
import com.lognsys.util.Constants;

@Repository("userRepository")
public class JdbcUserRepository implements UserRespository {

	private static final Logger LOG = Logger.getLogger(JdbcUserRepository.class);

	@Autowired
	private NamedParameterJdbcTemplate namedParamJdbcTemplate;

	/**
	 * Injecting resource sql.properties.
	 */
	@Resource(name = "sqlProperties")
	private Properties sqlProperties;

	/**
	 * Add users object into database
	 * 
	 * @param users
	 * @return Returns auto-generated value from database
	 */
	@Override
	public int addUser(UsersDTO users) throws DataAccessException {

		int user_id = 0;

		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(users);
		final KeyHolder keyHolder = new GeneratedKeyHolder();
		namedParamJdbcTemplate.update(sqlProperties.getProperty(Constants.USER_QUERIES.insert_users.name()), params,
				keyHolder);

		user_id = keyHolder.getKey().intValue();

		return user_id;
	}

	/**
	 * Returns boolean true if user exists
	 * 
	 * @param username
	 *            - String
	 * 
	 */
	@Override
	public boolean isExists(String username) throws DataAccessException {

		SqlParameterSource param = new MapSqlParameterSource("username", username);
		return namedParamJdbcTemplate.queryForObject(
				sqlProperties.getProperty(Constants.USER_QUERIES.select_users_exists.name()), param, Integer.class) > 0;
	}

	/**
	 * update users table in the database. 
	 *  
	 * @param UsersDTO 
	 */
	@Override
	public boolean updateUser(UsersDTO users) throws DataAccessException {

		boolean isUpdate = false;
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(users);
		isUpdate = namedParamJdbcTemplate.update(sqlProperties.getProperty(Constants.USER_QUERIES.update_users.name()),
				params) == 1;
	
		return isUpdate;
	}

	/**
	 * Returns Users object by id
	 * 
	 * @param id
	 *            - Integer
	 * 
	 */
	@Override
	public UsersDTO findUserById(Integer id) throws DataAccessException {

		SqlParameterSource parameter = new MapSqlParameterSource("id", Integer.valueOf(id));

		return namedParamJdbcTemplate.queryForObject(
				sqlProperties.getProperty(Constants.USER_QUERIES.select_users_id.name()), parameter,
				new UserByUserIDRowMapper());
	}

	/**
	 * Returns List<Users> from database
	 */
	@Override
	public List<UsersDTO> getAllUsers() throws DataAccessException {
		List<UsersDTO> listUsers = namedParamJdbcTemplate.query(
				sqlProperties.getProperty(Constants.USER_QUERIES.select_users.name()),
				new BeanPropertyRowMapper<UsersDTO>(UsersDTO.class));

		return listUsers;
	}

	/**
	 * delete user by user_id
	 * 
	 * @param id
	 *            - integer
	 * 
	 */
	@Override
	public boolean deleteUserBy(Integer user_id) throws DataAccessException {
		SqlParameterSource parameter = new MapSqlParameterSource("id", Integer.valueOf(user_id));
		return namedParamJdbcTemplate.update(sqlProperties.getProperty(Constants.USER_QUERIES.delete_users.name()),
				parameter) == 1;
	}

	/**
	 * delete user by emailID
	 * 
	 * @param  emailID
	 * 
	 */
	@Override
	public boolean deleteUserBy(String emailID) throws DataAccessException {
		SqlParameterSource parameter = new MapSqlParameterSource("emailID", emailID);
		return namedParamJdbcTemplate
				.update(sqlProperties.getProperty(Constants.USER_QUERIES.delete_users_email.name()), parameter) == 1;
	}

	/**
	 * Returns UsersDTO object or null
	 * 
	 * @param username
	 */
	@Override
	public UsersDTO findUserByUsername(String username) throws DataAccessException {

		SqlParameterSource parameter = new MapSqlParameterSource("username", username);

		UsersDTO usersDTO = namedParamJdbcTemplate.queryForObject(
				sqlProperties.getProperty(Constants.USER_QUERIES.select_users_username.name()), parameter,
				new UserByUserIDRowMapper());

		return usersDTO;

	}

	/**
	 * 
	 * 
	 * @param users_id
	 * @param role
	 */
	public void addUserAndRole(int users_id, String role) throws DataAccessException {

		SqlParameterSource param = new MapSqlParameterSource().addValue("users_id", users_id).addValue("role", role);
		namedParamJdbcTemplate.update(sqlProperties.getProperty(Constants.ROLES_QUERIES.insert_users_roles.name()),
				param);
		
	}

	/**
	 * 
	 * @param users_id
	 * @param role
	 */
	public void addUserAndGroup(int users_id, String group_name) throws DataAccessException {

		SqlParameterSource param = new MapSqlParameterSource().addValue("users_id", users_id).addValue("group_name",
				group_name);
		namedParamJdbcTemplate.update(sqlProperties.getProperty(Constants.GROUP_QUERIES.insert_user_groups.name()),
				param);

	}

	public void addUserAndDevice(int users_id, String device) {
		Hashtable<String, Object> parameter = new Hashtable<>();
		parameter.put("users_id",(users_id));
		parameter.put("deviceToken", device);
		  System.out.println("addUserAndDevice      parameter "+parameter+" parameter size "+parameter.size());
			
		namedParamJdbcTemplate.update(sqlProperties.getProperty(Constants.DEVICE_QUERIES.insert_user_devices.name()),
				parameter);
		
		
	}

	@Override
	public boolean updateUserDevice(String device, String username) {
		
		Hashtable<String, Object> parameter = new Hashtable<>();
		parameter.put("device",(device));
		parameter.put("username", username);
	
		boolean isUpdate = false;
		isUpdate = namedParamJdbcTemplate.update(sqlProperties.getProperty(Constants.USER_QUERIES.update_users_device.name()),
				parameter) == 1;
	
		return isUpdate;
	}

}
