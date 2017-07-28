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
import org.springframework.stereotype.Repository;

import com.lognsys.dao.BookingRepository;
import com.lognsys.dao.RoleRepository;
import com.lognsys.dao.dto.BookingDTO;
import com.lognsys.dao.dto.RolesDTO;
import com.lognsys.util.Constants;

@Repository
public class JdbcBookingRepository implements BookingRepository {

	// Injecting namedParamterTemplate
	@Autowired
	private NamedParameterJdbcTemplate namedParamJdbcTemplate;

	/**
	 * Injecting resource sql.properties.
	 */
	@Resource(name = "sqlProperties")
	private Properties sqlProperties;


	@Override
	public List<BookingDTO> getAllBooking() {
		return namedParamJdbcTemplate.query(sqlProperties.getProperty(Constants.BOOKING_QUERIES.select_booking_all.name()),
				new BeanPropertyRowMapper<BookingDTO>(BookingDTO.class));

	}

	@Override
	public boolean addBooking(BookingDTO bookingDTO) {
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(bookingDTO);
		return namedParamJdbcTemplate
				.update(sqlProperties.getProperty(Constants.BOOKING_QUERIES.insert_booking.name()), params) == 1;

	}

}
