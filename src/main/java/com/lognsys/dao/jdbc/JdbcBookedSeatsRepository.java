package com.lognsys.dao.jdbc;

import java.util.ArrayList;
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

import com.lognsys.dao.BookedSeatsRepository;
import com.lognsys.dao.BookingRepository;
import com.lognsys.dao.RoleRepository;
import com.lognsys.dao.dto.BookedSeatsDTO;
import com.lognsys.dao.dto.BookingDTO;
import com.lognsys.dao.dto.RolesDTO;
import com.lognsys.dao.jdbc.resultset.BookedSeatsResultSetExtractor;
import com.lognsys.dao.jdbc.resultset.BookingResultSetExtractor;
import com.lognsys.dao.jdbc.rowmapper.UserByUserIDRowMapper;
import com.lognsys.util.Constants;

@Repository
public class JdbcBookedSeatsRepository implements BookedSeatsRepository {

	// Injecting namedParamterTemplate
	@Autowired
	private NamedParameterJdbcTemplate namedParamJdbcTemplate;

	/**
	 * Injecting resource sql.properties.
	 */
	@Resource(name = "sqlProperties")
	private Properties sqlProperties;

	@Override
	public List<BookedSeatsDTO> getAllBookedSeats() {
		return namedParamJdbcTemplate.query(sqlProperties.getProperty(Constants.BOOKEDSEATS_QUERIES.select_bookedseats_all.name()),
				new BeanPropertyRowMapper<BookedSeatsDTO>(BookedSeatsDTO.class));

	}

	@Override
	public int addBookedSeats(BookedSeatsDTO bookedSeatsDTO) {
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(bookedSeatsDTO);
		final KeyHolder keyHolder = new GeneratedKeyHolder();
		namedParamJdbcTemplate.update(sqlProperties.getProperty(Constants.BOOKEDSEATS_QUERIES.insert_bookedseats.name()),
				params, keyHolder);
		return keyHolder.getKey().intValue();
	}

	@Override
	public boolean deleteBookedSeatsById(Integer id) {
		SqlParameterSource parameter = new MapSqlParameterSource("id", Integer.valueOf(id));
		return namedParamJdbcTemplate.update(sqlProperties.getProperty(Constants.BOOKEDSEATS_QUERIES.delete_bookedseats_by_Id.name()),
				parameter) == 1;
	
	}

	@Override
	public List<BookedSeatsDTO> findBookedSeatsByUserId(int users_id) {
		SqlParameterSource parameter = new MapSqlParameterSource("users_id", Integer.valueOf(users_id));
		
		return namedParamJdbcTemplate.query(
				sqlProperties.getProperty(Constants.BOOKEDSEATS_QUERIES.select_bookedseats_by_users_id.name()), parameter,
				new BookedSeatsResultSetExtractor());
	}

}
