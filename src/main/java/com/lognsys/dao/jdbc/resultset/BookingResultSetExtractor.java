package com.lognsys.dao.jdbc.resultset;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import com.lognsys.dao.dto.BookingDTO;
import com.lognsys.dao.dto.GroupsDTO;
import com.lognsys.dao.dto.RatingsDTO;
import com.lognsys.dao.dto.UsersDTO;
import com.lognsys.dao.dto.UsersGroupsDTO;
import com.lognsys.util.Constants;

public class BookingResultSetExtractor implements ResultSetExtractor<List<BookingDTO>> {
	BookingDTO bookingDTO = new BookingDTO();



	@Override
	public List<BookingDTO> extractData(ResultSet rs) throws SQLException, DataAccessException {
		List<BookingDTO> listOfbookingDTO = new ArrayList<>();
		while (rs.next()) {
			// Union of UsersDTO and Groups object
			BookingDTO ug = new BookingDTO(rs.getInt(Constants.BOOKING_FIELD_NAMES.id.name()),
					rs.getString(Constants.BOOKING_FIELD_NAMES.booking_date.name()),
					rs.getString(Constants.BOOKING_FIELD_NAMES.confirmation_no.name()),
					rs.getInt(Constants.BOOKING_FIELD_NAMES.users_id.name()),
					rs.getInt(Constants.BOOKING_FIELD_NAMES.dramas_id.name()),
					rs.getInt(Constants.BOOKING_FIELD_NAMES.auditorium_id.name()),
					rs.getDouble(Constants.BOOKING_FIELD_NAMES.price.name()),
					rs.getString(Constants.BOOKING_FIELD_NAMES.status.name()));

			listOfbookingDTO.add(ug);
		}

		return listOfbookingDTO;
	}
}
