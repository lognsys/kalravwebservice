package com.lognsys.service;

import java.io.IOException;

import org.apache.jackrabbit.uuid.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lognsys.dao.dto.UsersDTO;
import com.lognsys.dao.jdbc.JdbcAuditoriumRepository;
import com.lognsys.dao.jdbc.JdbcBookingRepository;
import com.lognsys.model.Booking;
import com.lognsys.model.Users;
import com.lognsys.util.ObjectMapper;

@Service("bookingService")
public class BookingService {
	@Autowired
	private JdbcBookingRepository jdbcBookingRepository;

	/**
	 * Add user to database.. Check if user already exists in db
	 * 
	 * TODO : Add rollbackFor is users exists TODO : Add exception for users and
	 * roles and groups which has unqieu constraints
	 * 
	 * @return
	 * @throws IOException
	 */
	@Transactional(rollbackFor = IllegalArgumentException.class)
	public String addBooking() throws IOException {
	String unique=	UUID.randomUUID().toString();
		System.out.println("UUID.randomUUID().toString() "+unique);
	return unique;
	}
	
}
