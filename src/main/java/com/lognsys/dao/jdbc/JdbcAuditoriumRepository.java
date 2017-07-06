package com.lognsys.dao.jdbc;

import java.util.Hashtable;
import java.util.List;
import java.util.Properties;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import com.lognsys.dao.AuditoriumRepository;
import com.lognsys.dao.dto.AuditoriumsDTO;
import com.lognsys.dao.dto.DramasAuditoriumsDTO;
import com.lognsys.dao.jdbc.resultset.DramaAuditoriumResultSetExtractor;
import com.lognsys.dao.jdbc.rowmapper.AuditoriumDramaIDRowMapper;
import com.lognsys.dao.jdbc.rowmapper.DramaUserIDRowMapper;
import com.lognsys.util.Constants;

@Repository
public class JdbcAuditoriumRepository implements AuditoriumRepository {

	@Autowired
	private NamedParameterJdbcTemplate namedParamJdbcTemplate;

	/**
	 * Injecting resource sql.properties.
	 */
	@Resource(name = "sqlProperties")
	private Properties sqlProperties;

	/**
	 * Add auditoriumsDTO object into database
	 * 
	 * @param auditoriumsDTO
	 */
	
	@Override
	public void addAuditoriums(AuditoriumsDTO auditoriumsDTO) {
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(auditoriumsDTO);
		namedParamJdbcTemplate.update(sqlProperties.getProperty(Constants.AUDITORIUM_QUERIES.insert_auditoriums.name()), params);
	
	}


	@Override
	public List<AuditoriumsDTO> getAllAuditoriums() {
		List<AuditoriumsDTO> listauditoriums = namedParamJdbcTemplate.query(
				sqlProperties.getProperty(Constants.AUDITORIUM_QUERIES.select_auditoriums.name()),
				new BeanPropertyRowMapper<AuditoriumsDTO>(AuditoriumsDTO.class));

		return listauditoriums;
	}

	@Override
	public  List<AuditoriumsDTO>  findAuditoriumBy(int dramas_id) {
		SqlParameterSource parameter = new MapSqlParameterSource("dramas_id", dramas_id);
		
		List<AuditoriumsDTO> listauditoriums = namedParamJdbcTemplate.query(
				sqlProperties.getProperty(Constants.AUDITORIUM_QUERIES.select_new_name_id_auditorium_by_dramaId.name()),parameter,
				new BeanPropertyRowMapper<AuditoriumsDTO>(AuditoriumsDTO.class));
		   System.out.println("#JdbcAuditoriumRepository findAuditoriumBy listauditoriums "+ listauditoriums.size());
			
		return listauditoriums;
		
	}

	
	@Override
	public List<DramasAuditoriumsDTO> getAllDramasAndAuditorium() {
		return namedParamJdbcTemplate.query(
				sqlProperties.getProperty(Constants.AUDITORIUM_QUERIES.select_dramasauditoriums_all.name()),
				new DramaAuditoriumResultSetExtractor());
	}


	@Override
	public List<AuditoriumsDTO> getAuditoriumListBy(int id,int dramas_id) {
//		SqlParameterSource parameter = new MapSqlParameterSource("id", id);
		Hashtable<String, Integer> parameter=new Hashtable<>();
		parameter.put("id",id);
		parameter.put("dramas_id",dramas_id);
		
		List<AuditoriumsDTO> listauditoriums = namedParamJdbcTemplate.query(
				sqlProperties.getProperty(Constants.AUDITORIUM_QUERIES.select_new_audi_price_time.name()),parameter,
				new BeanPropertyRowMapper<AuditoriumsDTO>(AuditoriumsDTO.class));
		   System.out.println("#JdbcAuditoriumRepository getAuditoriumListBy listauditoriums "+ listauditoriums.size());
			
		return listauditoriums;
	}




}
