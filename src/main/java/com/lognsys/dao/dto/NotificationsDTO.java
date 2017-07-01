package com.lognsys.dao.dto;

public class NotificationsDTO {

	private int id;
//	private int users_id;
//	private int dramas_id;
	private boolean notify=false;
	private String message;
	
	public NotificationsDTO() {
		// no-arg constructor
	}

	public NotificationsDTO(int id,boolean notify, String message) {
		super();
		this.id = id;
		this.notify = notify;
		this.message = message;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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
