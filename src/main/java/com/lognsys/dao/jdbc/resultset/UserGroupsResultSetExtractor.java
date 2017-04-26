package com.lognsys.dao.jdbc.resultset;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

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
public class UserGroupsResultSetExtractor implements ResultSetExtractor<List<UsersGroupsDTO>> {

	@Override
	public List<UsersGroupsDTO> extractData(ResultSet rs) throws SQLException, DataAccessException {

		List<UsersGroupsDTO> listOfUsersDTO = new ArrayList<>();
		while (rs.next()) {
			UsersGroupsDTO ug = new UsersGroupsDTO();

			// Union of UsersDTO and Groups object
			UsersDTO u = new UsersDTO(rs.getInt(Constants.USER_FIELD_NAMES.usersId.name()),
					rs.getString(Constants.USER_FIELD_NAMES.realname.name()),
					rs.getString(Constants.USER_FIELD_NAMES.username.name()),
					rs.getString(Constants.USER_FIELD_NAMES.auth_id.name()),
					rs.getString(Constants.USER_FIELD_NAMES.phone.name()),
					rs.getString(Constants.USER_FIELD_NAMES.location.name()),
					rs.getString(Constants.USER_FIELD_NAMES.provenance.name()),
					rs.getString(Constants.USER_FIELD_NAMES.birthdate.name()),
					rs.getBoolean(Constants.USER_FIELD_NAMES.enabled.name()),
					rs.getBoolean(Constants.USER_FIELD_NAMES.notification.name()),
					rs.getString(Constants.USER_FIELD_NAMES.device.name()),
					rs.getString(Constants.USER_FIELD_NAMES.address.name()),
					rs.getString(Constants.USER_FIELD_NAMES.city.name()),
					rs.getString(Constants.USER_FIELD_NAMES.state.name()),
					rs.getString(Constants.USER_FIELD_NAMES.zipcode.name()),
					rs.getString(Constants.USER_FIELD_NAMES.company_name.name()));

			GroupsDTO g = new GroupsDTO(rs.getInt(Constants.GROUPS_FIELDNAME.groupsId.name()),
					rs.getString(Constants.GROUPS_FIELDNAME.group_name.name()));

			// users & groups
			ug.setUser(u);
			ug.setGroups(g);

			listOfUsersDTO.add(ug);
		}

		return listOfUsersDTO;
	}

}
