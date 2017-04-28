package com.lognsys.util;

import java.util.ArrayList;
import java.util.List;

import com.lognsys.dao.dto.DramasAuditoriumsDTO;
import com.lognsys.dao.dto.DramasDTO;
import com.lognsys.dao.dto.DramasGroupsDTO;
import com.lognsys.dao.dto.GroupsDTO;
import com.lognsys.dao.dto.UsersDTO;
import com.lognsys.dao.dto.UsersGroupsDTO;
import com.lognsys.model.Drama;
import com.lognsys.model.DramasTable;
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

	public static Users mapToUsers(UsersDTO users) {
		// TODO: Current setting of group to null, but need to change to value
		
		//Split realname to firstname and lastname to map in Users  Object
		String[] splited = users.getRealname().split(" ");
		String firstname = splited[0]==null?"":splited[0];
		String lastname = splited[1]==null?"":splited[1] ;
		
		
		
		return new Users(users.getId(),  users.getAuth_id(), users.getUsername(),users.getRealname(), 
				users.getPhone(), users.getLocation(), users.getProvenance(), users.getBirthdate(), users.isEnabled(),
				users.isNotification(), users.getDevice(), users.getAddress(), users.getCity(),
				users.getState(), users.getZipcode(), users.getCompany_name(), firstname, lastname,"");
		
	}

	
	public static DramasDTO mapToDramasDTO(Drama dramas) {
		// TODO: Current setting of group to null, but need to change to value

		return new DramasDTO(dramas.getId(), dramas.getTitle(), dramas.getImageurl(), dramas.getDrama_length(),
				dramas.getDate(), dramas.getGenre(), dramas.getStar_cast(), dramas.getDescription(), dramas.getDirector(),
				dramas.getWriter(), dramas.getMusic(), dramas.getAvg_rating());

	}

	public static List<DramasTable> mapToDramasTable(List<DramasGroupsDTO> dramaGroups) {
		List<DramasTable> list = new ArrayList<>();
		for (DramasGroupsDTO g : dramaGroups) {
			list.add(new DramasTable(g.getDrama().getId(), g.getDrama().getTitle(),
					g.getGroups().getGroup_name(), null, null));
		}
		return list;
	}
	
	public static List<DramasTable> mapToDramasAuditoriumTable(List<DramasAuditoriumsDTO> dramaAuditorium) {
		List<DramasTable> list = new ArrayList<>();
		for (DramasAuditoriumsDTO a : dramaAuditorium) {
			list.add(new DramasTable(a.getDrama().getId(), a.getDrama().getTitle(),
					a.getAuditoriums().getAuditorium_name(), null, null));
		}
		return list;
	}
}
