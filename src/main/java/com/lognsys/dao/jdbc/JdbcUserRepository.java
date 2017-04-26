package com.lognsys.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import com.lognsys.dao.UserRespository;
import com.lognsys.dao.dto.UsersDTO;
import com.lognsys.util.Constants;

@Repository("userRepository")
public class JdbcUserRepository implements UserRespository {

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
	 */
	@Override
	public void addUser(UsersDTO users) {
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(users);
		namedParamJdbcTemplate.update(sqlProperties.getProperty(Constants.USER_QUERIES.insert_users.name()), params);
	}

	/**
	 * Returns boolean true if user exists
	 * 
	 * @param username
	 *            - String
	 * 
	 */
	@Override
	public boolean isExists(String username) {

		SqlParameterSource param = new MapSqlParameterSource("username", username);
		return namedParamJdbcTemplate.queryForObject(
				sqlProperties.getProperty(Constants.USER_QUERIES.select_users_exists.name()), param, Integer.class) > 0;
	}

	/**
	 * 
	 */
	@Override
	public void updateUser(String username) {

	}

	/**
	 * Returns Users object by id
	 * 
	 * @param id
	 *            - Integer
	 */
	@Override
	public UsersDTO findUserById(Integer id) {

		SqlParameterSource parameter = new MapSqlParameterSource("id", Integer.valueOf(id));

		UsersDTO useritem = new UsersDTO();
		namedParamJdbcTemplate.queryForObject(sqlProperties.getProperty(Constants.USER_QUERIES.select_users_id.name()),
				parameter, new RowMapper<UsersDTO>() {

					@Override
					public UsersDTO mapRow(ResultSet rs, int arg1) throws SQLException {

						useritem.setId(rs.getInt("id"));
						useritem.setUsername(rs.getString("username"));
						useritem.setRealname(rs.getString("realname"));
						useritem.setPhone(rs.getString("birthdate"));
						useritem.setEnabled(rs.getBoolean("enabled"));
						useritem.setAddress(rs.getString("address"));
						useritem.setPhone(rs.getString("phone"));
						useritem.setCity(rs.getString("city"));
						useritem.setState(rs.getString("state"));
						useritem.setZipcode(rs.getString("zipcode"));
						useritem.setAuth_id(rs.getString("auth_id"));
						return useritem;
					}
				});

		return useritem;
	}

	/**
	 * Returns List<Users> from database
	 */
	@Override
	public List<UsersDTO> getAllUsers() {
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
	 */
	@Override
	public boolean deleteUserBy(Integer user_id) {
		SqlParameterSource parameter = new MapSqlParameterSource("id", Integer.valueOf(user_id));
		return namedParamJdbcTemplate.update(sqlProperties.getProperty(Constants.USER_QUERIES.delete_users.name()),
				parameter) == 1;
	}

	/**
	 * delete user by emailID
	 * 
	 * @param id - emailID
	 */
	@Override
	public boolean deleteUserBy(String emailID) {
		SqlParameterSource parameter = new MapSqlParameterSource("emailID", emailID);
		return namedParamJdbcTemplate.update(sqlProperties.getProperty(Constants.USER_QUERIES.delete_users_email.name()),
				parameter) == 1;
	}

	@Override
	public UsersDTO findUserById(String emailID) {
		// TODO Auto-generated method stub
		return null;
	}

}
