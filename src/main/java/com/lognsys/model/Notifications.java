package com.lognsys.model;

public class Notifications {

	private int id;/*
	private int users_id;
	private int dramas_id;*/
	private boolean notify=false;
	private String message;
	
	public Notifications() {
		super();
	}


	public Notifications(int i, boolean b, String string) {
		this.id=i;
		this.notify=b;
		this.message=string;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

/*	public int getUsers_id() {
		return users_id;
	}

	public void setUsers_id(int users_id) {
		this.users_id = users_id;
	}

	public int getDramas_id() {
		return dramas_id;
	}

	public void setDramas_id(int dramas_id) {
		this.dramas_id = dramas_id;
	}
*/
	public boolean isNotify() {
		return notify;
	}

	public void setNotify(boolean notify) {
		this.notify = notify;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


}
