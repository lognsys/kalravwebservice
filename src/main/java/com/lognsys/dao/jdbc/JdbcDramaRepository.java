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
		System.out.println("showDramas --> getDramas -->getAllDramas  listdramas.size()  "+listdramas.size());
		
		return listdramas;
	}

	@Override
	public boolean deleteDramaBy(Integer id) {

		System.out.println("manageDrama deleteDramas deleteDramaBy id "+id);
		SqlParameterSource parameter = new MapSqlParameterSource("id", Integer.valueOf(id));
		return namedParamJdbcTemplate.update(sqlProperties.getProperty(Constants.DRAMA_QUERIES.delete_dramas.name()),
				parameter) == 1;
	}

	@Override
	public DramasDTO findDramaById(Integer id) {
		System.out.println("manageDrama findByDrama findDramaById id "+id);
		
		/*SqlParameterSource param = new MapSqlParameterSource("id", id);
		DramasDTO dramasDTO=namedParamJdbcTemplate.queryForObject(
				sqlProperties.getProperty(Constants.DRAMA_QUERIES.select_dramas_id.name()), param,
				DramasDTO.class);
		System.out.println("manageDrama findByDrama findDramaById dramasDTO "+dramasDTO);
		System.out.println("manageDrama findByDrama findDramaById dramasDTO title "+dramasDTO.getTitle());
		System.out.println("manageDrama findByDrama findDramaById dramasDTO getGroup_name "+dramasDTO.getGroup_name());
		
		return dramasDTO
				;*/
		SqlParameterSource parameter = new MapSqlParameterSource("id", Integer.valueOf(id));

		DramasDTO dramasDTO = new DramasDTO();
		namedParamJdbcTemplate.queryForObject(sqlProperties.getProperty(Constants.DRAMA_QUERIES.select_dramas_id.name()),
				parameter, new RowMapper<DramasDTO>() {

					@Override
					public DramasDTO mapRow(ResultSet rs, int arg1) throws SQLException {

						dramasDTO.setId(rs.getInt("id"));
						dramasDTO.setTitle(rs.getString("title"));
						dramasDTO.setGenre(rs.getString("genre"));
						dramasDTO.setStar_cast(rs.getString("star_cast"));
						dramasDTO.setDirector(rs.getString("director"));
						dramasDTO.setWriter(rs.getString("writer"));
						dramasDTO.setDescription(rs.getString("description"));
						dramasDTO.setAuditorium_id(rs.getInt("auditorium_id"));
						dramasDTO.setDate(rs.getString("date"));
						dramasDTO.setAvg_rating(rs.getString("avg_rating"));
						dramasDTO.setImageurl(rs.getString("imageurl"));
						dramasDTO.setDrama_length(rs.getString("drama_length"));
						dramasDTO.setMusic(rs.getString("music"));
						dramasDTO.setGroup_id(rs.getInt("group_id"));
						dramasDTO.setGroup_name(rs.getString("group_name"));
						dramasDTO.setAuditorium_name(rs.getString("auditorium_name"));
						return dramasDTO;
					}
				});

		return dramasDTO;
	
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
