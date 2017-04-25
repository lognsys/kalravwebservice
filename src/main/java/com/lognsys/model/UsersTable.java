package com.lognsys.model;

public class UsersTable {

	private static enum STATUS {
		ACTIVE, INACTIVE
	}

	private int id;
	private String name;
	private String email;
	private String group;
	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	private String status;

	public UsersTable(int id, String name, String email, String group, String status) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.group = group;
		this.status = status;
	}

	public UsersTable(int id, String name, String email, String group, boolean enabled) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
