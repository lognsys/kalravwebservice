package com.lognsys.dao;

import java.util.Date;
import java.util.List;
import com.lognsys.dao.dto.AuditoriumsDTO;
import com.lognsys.dao.dto.BookingDTO;
import com.lognsys.dao.dto.DramasAuditoriumsDTO;

public interface BookingRepository {

	public List<BookingDTO> getAllBooking();

	
	public boolean addBooking(BookingDTO bookingDTO);

}
