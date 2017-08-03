package com.lognsys.dao;

import java.util.Date;
import java.util.List;
import com.lognsys.dao.dto.AuditoriumsDTO;
import com.lognsys.dao.dto.BookedSeatsDTO;
import com.lognsys.dao.dto.BookingDTO;
import com.lognsys.dao.dto.DramasAuditoriumsDTO;
import com.lognsys.dao.dto.UsersDTO;

public interface BookedSeatsRepository {

	/**
	 * Get All bookings
	 * 
	 * @return
	 */
	public List<BookedSeatsDTO> getAllBookedSeats();

	/**
	 * Add booking into database
	 * 
	 * @param bookingDTO
	 */
	public int addBookedSeats(BookedSeatsDTO bookedSeatsDTO);

	/**
	 * Delete booking by id
	 * @param id
	 */
	public boolean deleteBookedSeatsById(Integer id);
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
		 public  List<BookedSeatsDTO> findBookedSeatsByUserId(int users_id);

}
