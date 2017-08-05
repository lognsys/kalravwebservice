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

import com.lognsys.dao.BookingRepository;
import com.lognsys.dao.RoleRepository;
import com.lognsys.dao.dto.BookingDTO;
import com.lognsys.dao.dto.RolesDTO;
import com.lognsys.dao.jdbc.resultset.BookingResultSetExtractor;
import com.lognsys.dao.jdbc.rowmapper.UserByUserIDRowMapper;
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

	/**
	 * listing list of bookings from database
	 * 
	 * @return Returns list of bookings value from database
	 */
	@Override
	public List<BookingDTO> getAllBooking() throws DataAccessException{
		return namedParamJdbcTemplate.query(sqlProperties.getProperty(Constants.BOOKING_QUERIES.select_booking_all.name()),
				new BeanPropertyRowMapper<BookingDTO>(BookingDTO.class));
	}

	/**
	 * Add booking object into database
	 * 
	 * @param booking
	 * @return Returns auto-generated value from database
	 */
	@Override
	public int addBooking(BookingDTO bookingDTO) throws DataAccessException{
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(bookingDTO);
		final KeyHolder keyHolder = new GeneratedKeyHolder();
		namedParamJdbcTemplate.update(sqlProperties.getProperty(Constants.BOOKING_QUERIES.insert_booking.name()),
				params, keyHolder);
		return keyHolder.getKey().intValue();
	}
	/**
	 * delete booking from database by id
	 * 
	 * @param id
	 * @return Returns true
	 */
	@Override
	public boolean deleteBookingById(Integer id) throws DataAccessException{
		SqlParameterSource parameter = new MapSqlParameterSource("id", Integer.valueOf(id));
		return namedParamJdbcTemplate.update(sqlProperties.getProperty(Constants.BOOKING_QUERIES.delete_booking_by_Id.name()),
				parameter) == 1;
	}



	@Override
	public  List<BookingDTO> findBookingByUserId(int users_id) throws DataAccessException{
		SqlParameterSource parameter = new MapSqlParameterSource("users_id", Integer.valueOf(users_id));
	
		return namedParamJdbcTemplate.query(
				sqlProperties.getProperty(Constants.BOOKING_QUERIES.select_booking_by_users_id.name()), parameter,
				new BookingResultSetExtractor());
	}

}
