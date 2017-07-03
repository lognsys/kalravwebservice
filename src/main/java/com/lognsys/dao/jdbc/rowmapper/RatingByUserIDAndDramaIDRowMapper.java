package com.lognsys.dao.jdbc.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.lognsys.dao.dto.RatingsDTO;
import com.lognsys.dao.dto.UsersDTO;

public class RatingByUserIDAndDramaIDRowMapper implements RowMapper<RatingsDTO> {
	RatingsDTO ratingsDTO = new RatingsDTO();

	@Override
	public RatingsDTO mapRow(ResultSet rs, int arg1) throws SQLException {

		ratingsDTO.setId(rs.getInt("id"));
		ratingsDTO.setRating(rs.getDouble("rating"));
		ratingsDTO.setRating_date(rs.getString("rating_date"));
		ratingsDTO.setUsers_id(rs.getInt("users_id"));
		ratingsDTO.setDramas_id(rs.getInt("dramas_id"));
		return ratingsDTO;
	}
}
