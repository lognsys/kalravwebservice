package com.lognsys.model;

/**
 * Group POJO as value object for retrieving values from form
 * 
 * @author pdoshi
 *
 */
public class Groups {

	private String groupName;

	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

}
