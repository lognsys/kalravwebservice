package com.lognsys.dao.jdbc;

import java.util.List;
import java.util.Properties;
import javax.annotation.Resource;
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
import com.lognsys.dao.DramaRespository;
import com.lognsys.dao.dto.DramasDTO;
import com.lognsys.dao.jdbc.rowmapper.DramaUserIDRowMapper;
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
	public int addDrama(DramasDTO dramas)  throws DataAccessException{
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(dramas);
		final KeyHolder keyHolder = new GeneratedKeyHolder();
		namedParamJdbcTemplate.update(sqlProperties.getProperty(Constants.DRAMA_QUERIES.insert_dramas.name()),
				params, keyHolder);
		return keyHolder.getKey().intValue();
	}

	/**
	 * Returns boolean true if user exists
	 * 
	 * @param username
	 *            - String
	 * 
	 */
	@Override
	public boolean isExists(String title){

		System.out.println("isExists title - "+title);
		SqlParameterSource param = new MapSqlParameterSource("title", title);
		Integer val;
		 val = namedParamJdbcTemplate.queryForObject(
					sqlProperties.getProperty(Constants.DRAMA_QUERIES.select_dramas_exists.name()), param, Integer.class) ;
		
			System.out.println("isExists val - "+val);
			
		if(val != null && val > 0)
			return true;
		else
		return false;
	}

	/**
	 * @param title
	 * @return 
	 */
	@Override
	public boolean updateDrama(DramasDTO dramasDTO)  throws DataAccessException{
		boolean isUpdate = false;
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(dramasDTO);
		return isUpdate = namedParamJdbcTemplate.update(sqlProperties.getProperty(Constants.DRAMA_QUERIES.update_dramas.name()),
				params) == 1;
	}

	/**
	 * 
	 */
	@Override
	public List<DramasDTO> getAllDramas() throws DataAccessException{
		List<DramasDTO> listdramas = namedParamJdbcTemplate.query(
				sqlProperties.getProperty(Constants.DRAMA_QUERIES.select_all_dramas.name()),
				new BeanPropertyRowMapper<DramasDTO>(DramasDTO.class));
		return listdramas;
	}

	/**
	 * 
	 */
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

		SqlParameterSource parameter = new MapSqlParameterSource("id", Integer.valueOf(id));
		return namedParamJdbcTemplate.queryForObject(sqlProperties.getProperty(Constants.DRAMA_QUERIES.select_dramas_id.name()),
				parameter, new DramaUserIDRowMapper());
	
	
	}

	@Override
	public boolean deleteDramaBy(String title) {
		SqlParameterSource parameter = new MapSqlParameterSource("title",title);
		return namedParamJdbcTemplate.update(sqlProperties.getProperty(Constants.DRAMA_QUERIES.delete_dramas_title.name()),
				parameter) == 1;
	}

	@Override
	public DramasDTO findDramaByTitle(String title) {

		SqlParameterSource param = new MapSqlParameterSource("title", title);
		return namedParamJdbcTemplate.queryForObject(
				sqlProperties.getProperty(Constants.DRAMA_QUERIES.select_dramas_title.name()), param,
				DramasDTO.class);	}
	
	/**
	 * 
	 * @param drama_id
	 * @param group
	 */
	public void addDramaAndGroup(int dramas_id, String group_name) throws DataAccessException {

		try {
			SqlParameterSource param = new MapSqlParameterSource().addValue("dramas_id", dramas_id).addValue("group_name",
					group_name);
			namedParamJdbcTemplate.update(sqlProperties.getProperty(Constants.GROUP_QUERIES.insert_dramas_groups.name()),
					param);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * @param drama_id
	 * @param group
	 */
	public void addDramaAndAuditorium(int dramas_id, String auditorium_name,String date,String time) throws DataAccessException{

		try {
			SqlParameterSource param = new MapSqlParameterSource()
					.addValue("dramas_id", dramas_id)
					.addValue("auditorium_name",auditorium_name)
					.addValue("date",date)
					.addValue("time",time);
			namedParamJdbcTemplate.update(sqlProperties.getProperty(Constants.AUDITORIUM_QUERIES.insert_dramas_auditoriums.name()),
					param);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
