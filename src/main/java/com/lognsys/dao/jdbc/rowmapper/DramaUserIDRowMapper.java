package com.lognsys.dao.jdbc.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.lognsys.dao.dto.DramasDTO;

public class DramaUserIDRowMapper implements RowMapper<DramasDTO> {
	DramasDTO dramasDTO = new DramasDTO();

	@Override
	public DramasDTO mapRow(ResultSet rs, int arg1) throws SQLException {

		dramasDTO.setId(rs.getInt("id"));
		dramasDTO.setTitle(rs.getString("title"));
		dramasDTO.setGenre(rs.getString("genre"));
		dramasDTO.setStar_cast(rs.getString("star_cast"));
		dramasDTO.setDirector(rs.getString("director"));
		dramasDTO.setWriter(rs.getString("writer"));
		dramasDTO.setDescription(rs.getString("description"));
		dramasDTO.setDate(rs.getString("date"));
		dramasDTO.setAvg_rating(rs.getString("avg_rating"));
		dramasDTO.setImageurl(rs.getString("imageurl"));
		dramasDTO.setDrama_length(rs.getString("drama_length"));
		dramasDTO.setMusic(rs.getString("music"));
		return dramasDTO;
	}

}
