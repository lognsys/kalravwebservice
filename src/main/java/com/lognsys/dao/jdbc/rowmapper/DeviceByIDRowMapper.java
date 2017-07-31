package com.lognsys.dao.jdbc.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.lognsys.dao.dto.DeviceDTO;
import com.lognsys.dao.dto.NotificationsDTO;
import com.lognsys.dao.dto.UsersDTO;

public class DeviceByIDRowMapper implements RowMapper<DeviceDTO> {
	DeviceDTO deviceDTO = new DeviceDTO();

	@Override
	public DeviceDTO mapRow(ResultSet rs, int arg1) throws SQLException {

		deviceDTO.setId(rs.getInt("id"));
		deviceDTO.setUsers_id(rs.getInt("users_id"));
		deviceDTO.setDeviceToken(rs.getString("deviceToken"));
		return deviceDTO;
	}
}
