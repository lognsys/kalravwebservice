package com.lognsys.dao;

import java.util.Date;
import java.util.List;
import com.lognsys.dao.dto.AuditoriumsDTO;
import com.lognsys.dao.dto.BookedSeatsDTO;
import com.lognsys.dao.dto.BookingDTO;
import com.lognsys.dao.dto.DramasAuditoriumsDTO;
import com.lognsys.dao.dto.RowSeatDTO;
import com.lognsys.dao.dto.UsersDTO;
import com.lognsys.model.RowSeat;

public interface RowSeatRepository {

	/**
	 * Get All bookings
	 * 
	 * @return
	 */
	public List<RowSeatDTO> getAllRowSeat();

	/**
	 * Add booking into database
	 * 
	 * @param bookingDTO
	 */
	public int addRowSeat(RowSeatDTO rowSeatDTO);

	/**
	 * Delete booking by id
	 * @param id
	 */
	public boolean deleteRowSeatById(Integer id);
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
		 public  List<RowSeatDTO> findRowSeatByAuditoriumId(int auditoriums_id);
		
		 public  int getRowSeats(int row_num,String row_name,int auditoriums_id);

}
