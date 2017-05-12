package com.lognsys.model;

/**
 * Group POJO as value object for retrieving values from form
 * 
 * @author pdoshi
 *
 */
public class Groups {
	private int id;
	private String group_name;
	private String sub_groups_name;

	public String getSub_groups_name() {
		return sub_groups_name;
	}


	public void setSub_groups_name(String sub_groups_name) {
		this.sub_groups_name = sub_groups_name;
	}


	


	public Groups(int id, String group_name, String sub_groups_name) {
	
		this.id = id;
		this.group_name = group_name;
		this.sub_groups_name = sub_groups_name;
	}


	public String getGroup_name() {
		return group_name;
	}

	public void setGroup_name(String group_name) {
		this.group_name = group_name;
	}

	public Groups() {
		// no-arg constructor
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	@Override
	public String toString() {
		return "Groups [id=" + id + ", group_name=" + group_name + ", sub_groups_name=" + sub_groups_name + "]";
	}

}
