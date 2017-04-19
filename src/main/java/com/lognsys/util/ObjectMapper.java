package com.lognsys.util;

import java.util.ArrayList;
import java.util.List;

import com.lognsys.dao.dto.UsersDTO;
import com.lognsys.model.Users;
import com.lognsys.model.UsersTable;

public class ObjectMapper {

	public static UsersTable mapToUserTable(UsersDTO users) {

		return new UsersTable(users.getId(), users.getRealname(), users.getUsername(), users.isEnabled());

	}

	public static List<UsersTable> mapToUserTable(List<UsersDTO> users) {
		List<UsersTable> list = new ArrayList<>();
		for (UsersDTO u : users) {
			list.add(new UsersTable(u.getId(), u.getRealname(), u.getUsername(), u.isEnabled()));
		}
		return list;
	}

	
	public static UsersDTO mapToUsersDTO(Users users) {
		// TODO: Current setting of group to null, but need to change to value

		return new UsersDTO(users.getId(), users.getRealname(), users.getUsername(), users.getAuth_id(),
				users.getPhone(), users.getLocation(), users.getProvenance(), users.getBirthdate(), users.isEnabled(),
				users.isNotification(), users.getAvg_rating(), users.getDevice(), users.getAddress(), users.getCity(),
				users.getState(), users.getZipcode(), users.getCompany_name(), users.getTitle(), null);

	}
	
}
