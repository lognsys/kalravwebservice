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
import com.lognsys.dao.dto.RowSeatDTO;
import com.lognsys.dao.dto.UsersDTO;
import com.lognsys.dao.dto.UsersGroupsDTO;
import com.lognsys.util.Constants;

public class RowSeatResultSetExtractor implements ResultSetExtractor<List<RowSeatDTO>> {
	BookedSeatsDTO bookedSeatsDTO = new BookedSeatsDTO();



	@Override
	public List<RowSeatDTO> extractData(ResultSet rs) throws SQLException, DataAccessException {
		List<RowSeatDTO> listOfbookingDTO = new ArrayList<>();
		while (rs.next()) {
			// Union of UsersDTO and Groups object
			RowSeatDTO ug = new RowSeatDTO(rs.getInt(Constants.ROWSEAT_FIELD_NAMES.id.name()),
					rs.getInt(Constants.ROWSEAT_FIELD_NAMES.row_num.name()),
					rs.getString(Constants.ROWSEAT_FIELD_NAMES.row_name.name()),
					rs.getInt(Constants.ROWSEAT_FIELD_NAMES.seat_num.name()),
					rs.getInt(Constants.ROWSEAT_FIELD_NAMES.auditoriums_id.name()));

			listOfbookingDTO.add(ug);
		}

		return listOfbookingDTO;
	}
}
