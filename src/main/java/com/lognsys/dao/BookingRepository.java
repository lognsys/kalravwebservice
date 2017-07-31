package com.lognsys.dao;

import java.util.Date;
import java.util.List;
import com.lognsys.dao.dto.AuditoriumsDTO;
import com.lognsys.dao.dto.BookingDTO;
import com.lognsys.dao.dto.DramasAuditoriumsDTO;
import com.lognsys.dao.dto.UsersDTO;

public interface BookingRepository {

	/**
	 * Get All bookings
	 * 
	 * @return
	 */
	public List<BookingDTO> getAllBooking();

	/**
	 * Add booking into database
	 * 
	 * @param bookingDTO
	 */
	public boolean addBooking(BookingDTO bookingDTO);

	/**
	 * Delete booking by id
	 * @param id
	 */
	public boolean deleteBookingById(Integer id);
/*
	*//**
	 * Update booking information, etc..
	 * 
	 * @param booking
	 *//*
	 public boolean updateBooking(BookingDTO bookingDTO);*/
	 /**
		 * Update booking information, etc..
		 * 
		 * @param booking
		 */
		 public  List<BookingDTO> findBookingByUserId(int users_id);

}
