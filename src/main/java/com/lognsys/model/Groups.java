package com.lognsys.model;

/**
 * Group POJO as value object for retrieving values from form
 * 
 * @author pdoshi
 *
 */
public class Groups {

	public Groups(int id, String group_name) {
		super();
		this.id = id;
		this.group_name = group_name;
	}

	private int id;
	private String group_name;

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

}
