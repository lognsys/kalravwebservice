package com.lognsys.dao.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class UsersGroupsDTO {

	@Id
	private int id;

	@DBRef
	private GroupsDTO groups;

	@DBRef
	private UsersDTO user;

	public UsersGroupsDTO(int id, GroupsDTO groups, UsersDTO user) {
		super();
		this.id = id;
		this.groups = groups;
		this.user = user;
	}

	public UsersGroupsDTO() {

	}

	public GroupsDTO getGroups() {
		return groups;
	}

	public void setGroups(GroupsDTO groups) {
		this.groups = groups;
	}

	public UsersDTO getUser() {
		return user;
	}

	public void setUser(UsersDTO user) {
		this.user = user;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
