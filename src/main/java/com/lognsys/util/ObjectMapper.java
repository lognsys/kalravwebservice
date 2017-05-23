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
import com.lognsys.model.Groups;
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
				users.getPhone(), users.getProvenance(), users.getBirthdate(), users.isEnabled(),
				users.isNotification(), users.getDevice(), users.getAddress(), users.getCity(),
				users.getState(), users.getZipcode());

	}

	/**
	 * Map UsersDTO with Users object in model directory
	 * 
	 * NOTE: Sync Users.java fields with this method
	 * 
	 * @param users
	 * @return
	 */
	public static Users mapToUsers(UsersDTO users) {
		// TODO: Current setting of group to null, but need to change to value
		System.out.println(" mapToUsers users toString "+users.toString());
		
		String[] splited=null;
		String firstname=null,lastname=null;
		
		if( users.getRealname()!=null &&  users.getRealname().length()>0)
		{
			//Split realname to firstname and lastname to map in Users  Object
		
		splited = users.getRealname().split(" ");
		 firstname = splited[0]==null?"":splited[0];
		 lastname = splited[1]==null?"":splited[1] ;
		}
		
		Users newusers= new Users(users.getId(),  users.getAuth_id(), users.getUsername(),users.getRealname(), 
				users.getPhone(), users.getProvenance(), users.getBirthdate(), users.isEnabled(),
				users.isNotification(), users.getDevice(), users.getAddress(), users.getCity(),
				users.getState(), users.getZipcode(), firstname, lastname);
		System.out.println(" mapToUsers newusers toString "+newusers.toString());
		
		return newusers;
		
	}
	public static Drama mapToDramas(DramasDTO dramas) {
		// TODO: Current setting of group to null, but need to change to value
		return new Drama(dramas.getId(), dramas.getTitle(), dramas.getImageurl(), dramas.getDrama_length(),
				dramas.getDate(), dramas.getGenre(), dramas.getStar_cast(), dramas.getDescription(), dramas.getDirector(),
				dramas.getWriter(), dramas.getMusic(), dramas.getAvg_rating(),dramas.getDrama_language());

	}

	
	public static DramasDTO mapToDramasDTO(Drama dramas) {
		// TODO: Current setting of group to null, but need to change to value

		return new DramasDTO(dramas.getId(), dramas.getTitle(), dramas.getImageurl(), dramas.getDrama_length(),
				dramas.getDate(), dramas.getGenre(), dramas.getStar_cast(), dramas.getDescription(), dramas.getDirector(),
				dramas.getWriter(), dramas.getMusic(), dramas.getAvg_rating(), dramas.getDrama_language());

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
	
//	mapToGroupsDTO
	public static GroupsDTO mapToGroupsDTO(Groups groups) {
	
		return new GroupsDTO(groups.getId(), groups.getGroup_name());

	}
}
