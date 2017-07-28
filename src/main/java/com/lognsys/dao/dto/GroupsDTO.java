package com.lognsys.dao.dto;

public class GroupsDTO {

	private int id;
	private String group_name;
	private boolean is_subgroup;
	private int parent_id; 
	
	public GroupsDTO(int id, String group_name) {
		super();
		this.id = id;
		this.group_name = group_name;
	}

	public GroupsDTO() {
		// TODO no-arg constructor
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

	public boolean isIs_subgroup() {
		return is_subgroup;
	}

	public void setIs_subgroup(boolean is_subgroup) {
		this.is_subgroup = is_subgroup;
	}

	public int getParent_id() {
		return parent_id;
	}

	public void setParent_id(int parent_id) {
		this.parent_id = parent_id;
	}

}
