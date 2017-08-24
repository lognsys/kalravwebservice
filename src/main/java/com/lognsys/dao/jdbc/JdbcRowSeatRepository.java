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
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.lognsys.dao.RowSeatRepository;
import com.lognsys.dao.dto.RowSeatDTO;
import com.lognsys.dao.jdbc.resultset.RowSeatResultSetExtractor;
import com.lognsys.util.Constants;

@Repository
public class JdbcRowSeatRepository implements RowSeatRepository {

	// Injecting namedParamterTemplate
	@Autowired
	private NamedParameterJdbcTemplate namedParamJdbcTemplate;

	/**
	 * Injecting resource sql.properties.
	 */
	@Resource(name = "sqlProperties")
	private Properties sqlProperties;

	@Override
	public List<RowSeatDTO> getAllRowSeat() {
		return namedParamJdbcTemplate.query(sqlProperties.getProperty(Constants.ROWSEAT_QUERIES.select_rowseat_all.name()),
				new BeanPropertyRowMapper<RowSeatDTO>(RowSeatDTO.class));

	}

	@Override
	public int addRowSeat(RowSeatDTO rowSeatDTO) {
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(rowSeatDTO);
		final KeyHolder keyHolder = new GeneratedKeyHolder();
		namedParamJdbcTemplate.update(sqlProperties.getProperty(Constants.ROWSEAT_QUERIES.insert_rowseat.name()),
				params, keyHolder);
		return keyHolder.getKey().intValue();
	}

	@Override
	public boolean deleteRowSeatById(Integer id) {
		SqlParameterSource parameter = new MapSqlParameterSource("id", Integer.valueOf(id));
		return namedParamJdbcTemplate.update(sqlProperties.getProperty(Constants.ROWSEAT_QUERIES.delete_by_id.name()),
				parameter) == 1;
	
	}

	@Override
	public List<RowSeatDTO> findRowSeatByAuditoriumId(int auditoriums_id) {
SqlParameterSource parameter = new MapSqlParameterSource("auditoriums_id", Integer.valueOf(auditoriums_id));
		
		return namedParamJdbcTemplate.query(
				sqlProperties.getProperty(Constants.ROWSEAT_QUERIES.select_rowseat_by_auditoriums_id.name()), parameter,
				new RowSeatResultSetExtractor());

	}

	@Override
	public int getRowSeats(int seat_num, String row_name, int auditoriums_id) {
		Hashtable<String, Object> parameter = new Hashtable<>();
		parameter.put("seat_num",(seat_num));
		parameter.put("row_name", row_name);
		parameter.put("auditoriums_id", auditoriums_id);
	
		return namedParamJdbcTemplate.queryForObject(
				sqlProperties.getProperty(Constants.ROWSEAT_QUERIES.get_select_id.name()), parameter,
				Integer.class);
	}

}