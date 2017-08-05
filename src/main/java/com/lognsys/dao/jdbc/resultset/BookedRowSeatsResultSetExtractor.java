package com.lognsys.dao.jdbc.resultset;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import com.lognsys.dao.dto.BookedRowSeatsDTO;
import com.lognsys.dao.dto.BookedSeatsDTO;
import com.lognsys.dao.dto.BookingDTO;
import com.lognsys.dao.dto.DramasDTO;
import com.lognsys.dao.dto.DramasGroupsDTO;
import com.lognsys.dao.dto.GroupsDTO;
import com.lognsys.dao.dto.RatingsDTO;
import com.lognsys.dao.dto.RowSeatDTO;
import com.lognsys.dao.dto.UsersDTO;
import com.lognsys.dao.dto.UsersGroupsDTO;
import com.lognsys.util.Constants;

public class BookedRowSeatsResultSetExtractor implements ResultSetExtractor<List<BookedRowSeatsDTO>> {
	
	@Override
	public List<BookedRowSeatsDTO> extractData(ResultSet rs) throws SQLException, DataAccessException {

		List<BookedRowSeatsDTO> listOfBookedRowSeatsDTO = new ArrayList<BookedRowSeatsDTO>();
		while (rs.next()) {
			BookedRowSeatsDTO dg = new BookedRowSeatsDTO();

			
			BookingDTO d = new BookingDTO(
					rs.getString(Constants.BOOKING_FIELD_NAMES.status.name()));
			
			
			RowSeatDTO g = new RowSeatDTO(
					rs.getInt(Constants.ROWSEAT_FIELD_NAMES.row_num.name()),
					rs.getInt(Constants.ROWSEAT_FIELD_NAMES.seat_num.name()));

			// drama & groups
			dg.setBooking(d);
			dg.setRowSeat(g);

			listOfBookedRowSeatsDTO.add(dg);
		}

		return listOfBookedRowSeatsDTO;
	}

}
