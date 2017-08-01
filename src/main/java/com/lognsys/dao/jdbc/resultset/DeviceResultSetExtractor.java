package com.lognsys.dao.jdbc.resultset;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import com.lognsys.dao.dto.BookingDTO;
import com.lognsys.dao.dto.DeviceDTO;
import com.lognsys.dao.dto.GroupsDTO;
import com.lognsys.dao.dto.RatingsDTO;
import com.lognsys.dao.dto.UsersDTO;
import com.lognsys.dao.dto.UsersGroupsDTO;
import com.lognsys.util.Constants;

public class DeviceResultSetExtractor implements ResultSetExtractor<List<DeviceDTO>> {
	BookingDTO bookingDTO = new BookingDTO();



	@Override
	public List<DeviceDTO> extractData(ResultSet rs) throws SQLException, DataAccessException {
		List<DeviceDTO> listOfDeviceDTO = new ArrayList<>();
		while (rs.next()) {
			// Union of UsersDTO and Groups object
			DeviceDTO ug = new DeviceDTO(rs.getInt(Constants.DEVICE_FIELD_NAMES.id.name()),
					rs.getInt(Constants.DEVICE_FIELD_NAMES.users_id.name()),
					rs.getString(Constants.DEVICE_FIELD_NAMES.deviceToken.name()));

			listOfDeviceDTO.add(ug);
		}

		return listOfDeviceDTO;
	}
}
