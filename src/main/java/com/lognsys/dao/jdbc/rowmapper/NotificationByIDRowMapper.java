package com.lognsys.dao.jdbc.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.lognsys.dao.dto.NotificationsDTO;
import com.lognsys.dao.dto.UsersDTO;

public class NotificationByIDRowMapper implements RowMapper<NotificationsDTO> {
	NotificationsDTO notificationsDTO = new NotificationsDTO();

	@Override
	public NotificationsDTO mapRow(ResultSet rs, int arg1) throws SQLException {

		notificationsDTO.setId(rs.getInt("id"));
		notificationsDTO.setNotify(rs.getBoolean("notify"));
		notificationsDTO.setMessage(rs.getString("message"));
		return notificationsDTO;
	}
}
