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

import com.lognsys.dao.DramaRespository;
import com.lognsys.dao.UserRespository;
import com.lognsys.dao.dto.DramasDTO;
import com.lognsys.dao.dto.UsersDTO;
import com.lognsys.util.Constants;

@Repository("dramaRepository")
public class JdbcDramaRepository implements DramaRespository {

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
	public void addDrama(DramasDTO dramas) {
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(dramas);
		namedParamJdbcTemplate.update(sqlProperties.getProperty(Constants.DRAMA_QUERIES.insert_dramas.name()), params);
	}

	/**
	 * Returns boolean true if user exists
	 * 
	 * @param username
	 *            - String
	 * 
	 */
	@Override
	public boolean isExists(String title) {

		SqlParameterSource param = new MapSqlParameterSource("title", title);
		return namedParamJdbcTemplate.queryForObject(
				sqlProperties.getProperty(Constants.DRAMA_QUERIES.select_dramas_exists.name()), param, Integer.class) > 0;
	}

	@Override
	public void updateDrama(String title) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<DramasDTO> getAllDramas() {
		List<DramasDTO> listdramas = namedParamJdbcTemplate.query(
				sqlProperties.getProperty(Constants.DRAMA_QUERIES.select_dramas.name()),
				new BeanPropertyRowMapper<DramasDTO>(DramasDTO.class));

		return listdramas;
	}

	@Override
	public boolean deleteDramaBy(Integer id) {
		SqlParameterSource parameter = new MapSqlParameterSource("id", Integer.valueOf(id));
		return namedParamJdbcTemplate.update(sqlProperties.getProperty(Constants.DRAMA_QUERIES.delete_dramas.name()),
				parameter) == 1;
	}

	@Override
	public DramasDTO findDramaById(Integer id) {
		SqlParameterSource param = new MapSqlParameterSource("id", id);
		return namedParamJdbcTemplate.queryForObject(
				sqlProperties.getProperty(Constants.DRAMA_QUERIES.select_dramas_id.name()), param,
				DramasDTO.class);
	
	}

	@Override
	public boolean deleteDramaBy(String title) {
		SqlParameterSource parameter = new MapSqlParameterSource("title",title);
		return namedParamJdbcTemplate.update(sqlProperties.getProperty(Constants.DRAMA_QUERIES.delete_dramas_title.name()),
				parameter) == 1;
	}

	@Override
	public DramasDTO findDramaById(String title) {

		SqlParameterSource param = new MapSqlParameterSource("title", title);
		return namedParamJdbcTemplate.queryForObject(
				sqlProperties.getProperty(Constants.DRAMA_QUERIES.select_dramas_title.name()), param,
				DramasDTO.class);	}

}
