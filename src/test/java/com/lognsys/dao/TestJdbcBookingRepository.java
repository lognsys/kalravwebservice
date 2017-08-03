package com.lognsys.dao;


import static org.junit.Assert.assertEquals;

//import org.junit.Assert;

import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.lognsys.dao.BookingRepository;
import com.lognsys.dao.RoleRepository;
import com.lognsys.dao.dto.BookingDTO;
import com.lognsys.dao.dto.RolesDTO;
import com.lognsys.dao.jdbc.JdbcBookingRepository;
import com.lognsys.model.Booking;
import com.lognsys.model.Users;
import com.lognsys.util.Constants;
import com.lognsys.util.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:datasource-context.xml" })
public class TestJdbcBookingRepository  {

	// Injecting namedParamterTemplate
	@Autowired
	private JdbcBookingRepository jdbcBookingRepository;
	
	public void setUp() {

	}

	/**
	 * Add booking object into database
	 * 
	 * @param booking
	 */
	@Test
    @Transactional
    @Rollback(true)
	public void addBooking() {
		Booking booking = new Booking();

		booking.setBooking_date("2017-07-31");
		booking.setConfirmation_no("KAL001");
		booking.setUsers_id(40);
		booking.setDramas_id(4);
		booking.setAuditorium_id(1);
		booking.setPrice(100.00);
		jdbcBookingRepository.addBooking(ObjectMapper.mapToBookingDTO(booking));
		
//		Assert.isTrue(isAdded, "isAdded booking - " + isAdded );
		
	
	}
	@Test
	public void getAllBooking() {
		 List<BookingDTO> bookingDTOs=jdbcBookingRepository.getAllBooking();
		Assert.notNull(bookingDTOs, "Check list of bookings NOT NULL");
		Assert.notEmpty(bookingDTOs, "Collection not empty..list of bookings  object");
	}

	@Test
    @Transactional
    @Rollback(true)
	public void deleteBookingById() {
	boolean isDelete=jdbcBookingRepository.deleteBookingById(5);
	Assert.isTrue(isDelete, "isDelete booking - " + isDelete );
	
	}

	@Test
	public void FindBooking() {

		
		int users_id=40;
		 List<BookingDTO> booking  =jdbcBookingRepository.findBookingByUserId(users_id);
		Assert.notNull(booking, "Check list of bookings NOT NULL");

		Assert.notEmpty(booking, "Collection not empty..list of bookings  object");
	
		
	}
	
}
