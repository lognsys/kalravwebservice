package com.lognsys.model;

public class UsersTable {

	private static enum STATUS {
		ACTIVE, INACTIVE
	}

	private int id;
	private String realname;
	private String username;
	private String group;
	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	private String status;

	public UsersTable(int id, String realname, String username, String group, String status) {
		super();
		this.id = id;
		this.realname = realname;
		this.username = username;
		this.group = group;
		this.status = status;
	}

	public UsersTable(int id, String realname, String username, String group, boolean enabled) {
		super();
		this.id = id;
		this.realname = realname;
		this.username = username;
		this.group = group;
		setStatus(enabled);
		this.status = getStatus();
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setStatus(boolean enabled) {
		if (enabled) {
			this.status = STATUS.ACTIVE.name();
		} else {
			this.status = STATUS.INACTIVE.name();
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
