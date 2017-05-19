package com.lognsys.dao.jdbc.resultset;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.lognsys.dao.dto.DramasDTO;
import com.lognsys.dao.dto.DramasGroupsDTO;
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
public class DramaGroupsResultSetExtractor implements ResultSetExtractor<List<DramasGroupsDTO>> {

	@Override
	public List<DramasGroupsDTO> extractData(ResultSet rs) throws SQLException, DataAccessException {

		List<DramasGroupsDTO> listOfDramasGroupsDTO = new ArrayList<DramasGroupsDTO>();
		while (rs.next()) {
			DramasGroupsDTO dg = new DramasGroupsDTO();

			/*DramasDTO d = new DramasDTO(
					rs.getInt(Constants.DRAMA_FIELD_NAMES.dramasId.name()),
					rs.getString(Constants.DRAMA_FIELD_NAMES.title.name()),
					rs.getString(Constants.DRAMA_FIELD_NAMES.imageurl.name()),
					rs.getString(Constants.DRAMA_FIELD_NAMES.drama_length.name()),
					rs.getString(Constants.DRAMA_FIELD_NAMES.date.name()),
					rs.getString(Constants.DRAMA_FIELD_NAMES.genre.name()),
					rs.getString(Constants.DRAMA_FIELD_NAMES.star_cast.name()),
					rs.getString(Constants.DRAMA_FIELD_NAMES.description.name()),
					rs.getString(Constants.DRAMA_FIELD_NAMES.director.name()),
					rs.getString(Constants.DRAMA_FIELD_NAMES.writer.name()),
					rs.getString(Constants.DRAMA_FIELD_NAMES.music.name()),
					rs.getString(Constants.DRAMA_FIELD_NAMES.avg_rating.name()));
*/
			
			DramasDTO d = new DramasDTO(
					rs.getInt(Constants.DRAMA_FIELD_NAMES.dramasId.name()),
					rs.getString(Constants.DRAMA_FIELD_NAMES.title.name()),
					rs.getString(Constants.DRAMA_FIELD_NAMES.imageurl.name()));

//			GroupsDTO g = new GroupsDTO(rs.getInt(Constants.GROUPS_FIELDNAME.groupsId.name()),
//					rs.getString(Constants.GROUPS_FIELDNAME.group_name.name()));
			GroupsDTO g = new GroupsDTO(rs.getInt(Constants.GROUPS_FIELDNAME.groupsId.name()),
					rs.getString(Constants.GROUPS_FIELDNAME.group_name.name()));

			// drama & groups
			dg.setDrama(d);
			dg.setGroups(g);

			listOfDramasGroupsDTO.add(dg);
		}

		return listOfDramasGroupsDTO;
	}

}
