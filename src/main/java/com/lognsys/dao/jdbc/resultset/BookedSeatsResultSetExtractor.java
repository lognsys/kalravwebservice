package com.lognsys.dao.jdbc.resultset;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import com.lognsys.dao.dto.BookedSeatsDTO;
import com.lognsys.dao.dto.BookingDTO;
import com.lognsys.dao.dto.GroupsDTO;
import com.lognsys.dao.dto.RatingsDTO;
import com.lognsys.dao.dto.UsersDTO;
import com.lognsys.dao.dto.UsersGroupsDTO;
import com.lognsys.util.Constants;

public class BookedSeatsResultSetExtractor implements ResultSetExtractor<List<BookedSeatsDTO>> {
	BookedSeatsDTO bookedSeatsDTO = new BookedSeatsDTO();



	@Override
	public List<BookedSeatsDTO> extractData(ResultSet rs) throws SQLException, DataAccessException {
		List<BookedSeatsDTO> listOfbookingDTO = new ArrayList<>();
		while (rs.next()) {
			// Union of UsersDTO and Groups object
			BookedSeatsDTO ug = new BookedSeatsDTO(rs.getInt(Constants.BOOKEDSEATS_FIELD_NAMES.id.name()),
					rs.getInt(Constants.BOOKEDSEATS_FIELD_NAMES.booking_id.name()),
					rs.getInt(Constants.BOOKEDSEATS_FIELD_NAMES.row_seats_id.name()),
					rs.getInt(Constants.BOOKEDSEATS_FIELD_NAMES.refer_seat_id.name()));

			listOfbookingDTO.add(ug);
		}

		return listOfbookingDTO;
	}
}
