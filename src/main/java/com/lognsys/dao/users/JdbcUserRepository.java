package com.lognsys.dao.users;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
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
public void delete(Integer id) {
	// TODO Auto-generated method stub
	String sql = "DELETE FROM USERS WHERE id= :id";
	namedParamJdbcTemplate.update( sqlProperties.getProperty(USER_QUERIES.delete_users.name()),
			new MapSqlParameterSource("id", id));

}
	@Override
	public Users findUserByEmailId(String emailId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Users> getAllUsers() {
		 List<Users> listUsers = namedParamJdbcTemplate.query(
				 sqlProperties.getProperty(USER_QUERIES.select_users.name()),
				 new RowMapper<Users>() {
			 
		        @Override
		        public Users mapRow(ResultSet rs, int rowNum) throws SQLException {
		        	Users useritem = new Users();
		 
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
		        	useritem.setAvg_rating(rs.getInt("avg_rating"));
		        	useritem.setAuth_id(rs.getString("auth_id"));
		   		 
		            return useritem;
		        }
		 
		    });
		 
		    return listUsers;
	}

	/**
	 * enum contains queries related to user
	 */
	private enum USER_QUERIES {
		insert_users,
		select_users,
		delete_users
	}
}
