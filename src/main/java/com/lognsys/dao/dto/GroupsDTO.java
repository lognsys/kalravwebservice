package com.lognsys.dao.dto;

public class GroupsDTO {

	private int id;
	private String group_name;
	
	public GroupsDTO(int id, String group_name) {
		super();
		this.id = id;
		this.group_name = group_name;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getGroup_name() {
		return group_name;
	}

	public void setGroup_name(String group_name) {
		this.group_name = group_name;
	}

	public enum GROUPS_FIELDNAME {
		groupsId, group_name
	}

}
