package com.lognsys.dao.jdbc.resultset;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.lognsys.dao.dto.AuditoriumsDTO;
import com.lognsys.dao.dto.DramasAuditoriumsDTO;
import com.lognsys.dao.dto.DramasDTO;
import com.lognsys.dao.dto.GroupsDTO;
import com.lognsys.dao.dto.UsersDTO;
import com.lognsys.dao.dto.UsersGroupsDTO;
import com.lognsys.util.Constants;

/**
 * <a>http://stackoverflow.com/questions/13295600/multiple-one-to-many-relations
 * -resultsetextractor
 * 
 * @author pdoshi
 *
 */
public class DramaAuditoriumResultSetExtractor implements ResultSetExtractor<List<DramasAuditoriumsDTO>> {

	@Override
	public List<DramasAuditoriumsDTO> extractData(ResultSet rs) throws SQLException, DataAccessException {

		List<DramasAuditoriumsDTO> listOfDramasAudiDTO = new ArrayList<DramasAuditoriumsDTO>();
		while (rs.next()) {
			DramasAuditoriumsDTO da = new DramasAuditoriumsDTO();

			// Union of UsersDTO and Groups object
			DramasDTO d = new DramasDTO(
					rs.getInt(Constants.DRAMA_FIELD_NAMES.dramasId.name()),
					rs.getString(Constants.DRAMA_FIELD_NAMES.imageurl.name()),
					rs.getString(Constants.DRAMA_FIELD_NAMES.title.name()),
					rs.getString(Constants.DRAMA_FIELD_NAMES.drama_length.name()),
					rs.getString(Constants.DRAMA_FIELD_NAMES.date.name()),
					rs.getString(Constants.DRAMA_FIELD_NAMES.genre.name()),
					rs.getString(Constants.DRAMA_FIELD_NAMES.star_cast.name()),
					rs.getString(Constants.DRAMA_FIELD_NAMES.description.name()),
					rs.getString(Constants.DRAMA_FIELD_NAMES.director.name()),
					rs.getString(Constants.DRAMA_FIELD_NAMES.writer.name()),
					rs.getString(Constants.DRAMA_FIELD_NAMES.music.name()),
					rs.getString(Constants.DRAMA_FIELD_NAMES.avg_rating.name()),
			rs.getString(Constants.DRAMA_FIELD_NAMES.dramas_language.name()));

			AuditoriumsDTO auditoriums = new AuditoriumsDTO(
					rs.getInt(Constants.AUDITORIUMS_FIELDNAME.auditoriumsId.name()),
					rs.getString(Constants.AUDITORIUMS_FIELDNAME.auditorium_name.name()),
					rs.getString(Constants.AUDITORIUMS_FIELDNAME.address.name()),
					rs.getString(Constants.AUDITORIUMS_FIELDNAME.lat_lon.name()));

			// users & groups
			da.setDrama(d);
			da.setAuditoriums(auditoriums);

			listOfDramasAudiDTO.add(da);
		}

		return listOfDramasAudiDTO;
	}

}
