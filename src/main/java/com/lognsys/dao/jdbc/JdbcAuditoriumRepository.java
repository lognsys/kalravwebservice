package com.lognsys.dao.jdbc;

import java.util.Date;

/**
 * @author pdoshi
 * 
 */

import java.util.Hashtable;
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
import org.springframework.stereotype.Repository;
import com.lognsys.dao.AuditoriumRepository;
import com.lognsys.dao.dto.AuditoriumsDTO;
import com.lognsys.dao.dto.DramasAuditoriumsDTO;
import com.lognsys.dao.dto.RowSeatDTO;
import com.lognsys.dao.jdbc.resultset.DramaAuditoriumResultSetExtractor;
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
	public boolean addAuditoriums(AuditoriumsDTO auditoriumsDTO) throws DataAccessException {
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(auditoriumsDTO);
		return namedParamJdbcTemplate
				.update(sqlProperties.getProperty(Constants.AUDITORIUM_QUERIES.insert_auditoriums.name()), params) == 1;

	}

	@Override
	public List<AuditoriumsDTO> getAllAuditoriums() throws DataAccessException {
		List<AuditoriumsDTO> listauditoriums = namedParamJdbcTemplate.query(
				sqlProperties.getProperty(Constants.AUDITORIUM_QUERIES.select_auditoriums.name()),
				new BeanPropertyRowMapper<AuditoriumsDTO>(AuditoriumsDTO.class));

		return listauditoriums;
	}

	@Override

	public List<AuditoriumsDTO> findAuditoriumBy(int dramas_id) {
		
		Hashtable<String, Object> parameter = new Hashtable<>();
		parameter.put("dramas_id",(dramas_id));
		List<AuditoriumsDTO> listauditoriums = namedParamJdbcTemplate.query(
				sqlProperties.getProperty(Constants.AUDITORIUM_QUERIES.select_new_name_id_auditorium_by_dramaId.name()),
				parameter, new BeanPropertyRowMapper<AuditoriumsDTO>(AuditoriumsDTO.class));
		System.out.println("#JdbcAuditoriumRepository findAuditoriumBy listauditoriums " + listauditoriums.size());

		return listauditoriums;

	}

	@Override
	public List<DramasAuditoriumsDTO> getDramasByAuditorium(String auditorium_name) throws DataAccessException {

		SqlParameterSource param = new MapSqlParameterSource("auditorium_name", auditorium_name);
		return namedParamJdbcTemplate.query(
				sqlProperties.getProperty(Constants.AUDITORIUM_QUERIES.select_dramabyauditorium.name()), param,
				new DramaAuditoriumResultSetExtractor());

	}

	@Override
	public int findIDBy(String auditorium_name) throws DataAccessException {
		SqlParameterSource param = new MapSqlParameterSource("auditorium_name", auditorium_name);
		return namedParamJdbcTemplate.queryForObject(
				sqlProperties.getProperty(Constants.AUDITORIUM_QUERIES.select_auditorium_id_byname.name()), param,
				Integer.class);
	}

	
	@Override
	public List<DramasAuditoriumsDTO> getAllDramasAndAuditorium() {
		return namedParamJdbcTemplate.query(
				sqlProperties.getProperty(Constants.AUDITORIUM_QUERIES.select_dramasauditoriums_all.name()),
				new DramaAuditoriumResultSetExtractor());
	}

	@Override
	public List<AuditoriumsDTO> getAuditoriumListBy(int id, int dramas_id ,String date) {
		// SqlParameterSource parameter = new MapSqlParameterSource("id", id);
		Hashtable<String, Object> parameter = new Hashtable<>();
		parameter.put("id", id);
		parameter.put("dramas_id", dramas_id);
		parameter.put("date", date);
		System.out.println("#AuditoriumService================ dramas_id "+dramas_id);
		
		List<AuditoriumsDTO> listauditoriums = namedParamJdbcTemplate.query(
				sqlProperties.getProperty(Constants.AUDITORIUM_QUERIES.select_new_audi_price_time.name()), parameter,
				new BeanPropertyRowMapper<AuditoriumsDTO>(AuditoriumsDTO.class));
		System.out.println("#JdbcAuditoriumRepository getAuditoriumListBy listauditoriums " + listauditoriums.size());

		return listauditoriums;
	}

	// Add Row_Seat
	public boolean addRow_Seat(RowSeatDTO rowSeatDTO) {
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(rowSeatDTO);
		return namedParamJdbcTemplate.update(sqlProperties.getProperty(Constants.ROWSEAT_QUERIES.insert_rowseat.name()),
				params) == 1;
	}

}
