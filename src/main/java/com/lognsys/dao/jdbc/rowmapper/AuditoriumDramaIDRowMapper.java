package com.lognsys.dao.jdbc.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.lognsys.dao.dto.AuditoriumsDTO;
import com.lognsys.dao.dto.DramasDTO;

public class AuditoriumDramaIDRowMapper implements RowMapper<AuditoriumsDTO> {
	AuditoriumsDTO auditoriumsDTO = new AuditoriumsDTO();

	@Override
	public AuditoriumsDTO mapRow(ResultSet rs, int arg1) throws SQLException {

		auditoriumsDTO.setId(rs.getInt("id"));
		auditoriumsDTO.setAuditorium_name(rs.getString("auditorium_name"));
		auditoriumsDTO.setDate(rs.getString("date"));
		auditoriumsDTO.setTime(rs.getString("time"));
		auditoriumsDTO.setPrice(rs.getDouble("price"));
		auditoriumsDTO.setIstart(rs.getInt("istart"));
		auditoriumsDTO.setIend(rs.getInt("iend"));
		return auditoriumsDTO;
	}

}
