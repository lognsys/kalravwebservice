package com.lognsys.dao.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class DramasGroupsDTO {

	@Id
	private int id;

	@DBRef
	private GroupsDTO groups;

	@DBRef
	private DramasDTO dramasDTO;

	public DramasGroupsDTO(int id, GroupsDTO groups, DramasDTO dramasDTO) {
		super();
		this.id = id;
		this.groups = groups;
		this.dramasDTO = dramasDTO;
	}

	public DramasGroupsDTO() {

	}

	public GroupsDTO getGroups() {
		return groups;
	}

	public void setGroups(GroupsDTO groups) {
		this.groups = groups;
	}

	public DramasDTO getDrama() {
		return dramasDTO;
	}

	public void setDrama(DramasDTO dramasDTO) {
		this.dramasDTO = dramasDTO;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
