package com.lognsys.util;

import java.util.ArrayList;
import java.util.List;
import com.lognsys.dao.dto.GroupsDTO;
import com.lognsys.dao.dto.UsersDTO;
import com.lognsys.dao.dto.UsersGroupsDTO;
import com.lognsys.model.Users;
import com.lognsys.model.UsersTable;

public class ObjectMapper {

	/**
	 * 
	 * @param users
	 *            - the Users Object and group object is added to Users Tabled
	 * @return
	 */
	public static UsersTable mapToUserTable(UsersDTO users, GroupsDTO groups) {

		return new UsersTable(users.getId(), users.getRealname(), users.getUsername(), groups.getGroup_name(),
				users.isEnabled());

	}

	/**
	 * Based on the group map all the usersDTO object to Users Tables object
	 * 
	 * @param groups
	 * @return
	 */
	public static List<UsersTable> mapToUserTable(List<UsersGroupsDTO> userGroups) {
		List<UsersTable> list = new ArrayList<>();
		for (UsersGroupsDTO g : userGroups) {
			list.add(new UsersTable(g.getUser().getId(), g.getUser().getRealname(), g.getUser().getUsername(),
					g.getGroups().getGroup_name(), g.getUser().isEnabled()));
		}
		return list;
	}

	public static UsersDTO mapToUsersDTO(Users users) {
		// TODO: Current setting of group to null, but need to change to value

		return new UsersDTO(users.getId(), users.getRealname(), users.getUsername(), users.getAuth_id(),
				users.getPhone(), users.getLocation(), users.getProvenance(), users.getBirthdate(), users.isEnabled(),
				users.isNotification(), users.getDevice(), users.getAddress(), users.getCity(),
				users.getState(), users.getZipcode(), users.getCompany_name());

	}

}
