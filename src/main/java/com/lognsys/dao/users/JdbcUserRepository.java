package com.lognsys.dao.users;

import java.util.List;
import java.util.Properties;
import javax.annotation.Resource;
import javax.sql.DataSource;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import com.lognsys.model.Users;

@Repository
public class JdbcUserRepository implements UserRespository {

	private NamedParameterJdbcTemplate namedParamJdbcTemplate;

	@Resource(name = "sqlProperties")
	private Properties sqlProperties;

	public JdbcUserRepository(DataSource dataSource, Properties sqlProperties) {
		this.sqlProperties = sqlProperties;
		this.namedParamJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	@Override
	public void addUser(Users users) {
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(users);
		namedParamJdbcTemplate.update(sqlProperties.getProperty(USER_QUERIES.insert_users.name()), params);
	}

	@Override
	public void updateUser(Users users) {
		// TODO Auto-generated method stub

	}

	@Override
	public Users findUserByEmailId(String emailId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Users> getAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * enum contains queries related to user
	 */
	private enum USER_QUERIES {
		insert_users
	}
}
