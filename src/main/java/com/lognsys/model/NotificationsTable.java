package com.lognsys.model;

public class NotificationsTable {

	private static enum NOTIFY {
		Active, Passive
	}

	private int id;
	private String name;
	private String title;
	private String message;
	private boolean notify;
	
	
	
	
	public NotificationsTable(int id, boolean notify, String message) {
		super();
		this.id = id;
		this.notify = notify;
		this.message = message;
	}

	

	public NotificationsTable(int id, String name, String title, String message, boolean enabled) {
		super();
		this.id = id;
		this.name = name;
		this.title = title;
		this.message = message;
		setNotify(enabled);
		this.notify = getNotify();
	}

	public boolean getNotify() {
		return notify;
	}

//	public void setNotify(boolean notify) {
//		this.notify = notify;
//	}

	public void setNotify(boolean enabled) {
		if (enabled) {
			this.notify = NOTIFY.Active.name() != null;
		} else {
			this.notify = NOTIFY.Passive.name() != null;
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



	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}



	public String getMessage() {
		return message;
	}



	public void setMessage(String message) {
		this.message = message;
	}

	

}
