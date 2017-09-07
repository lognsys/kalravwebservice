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
	

	private String realname;
	private String dramaTitle;
	
	
	public NotificationsTable(int id, boolean notify, String message,String realname,String dramaTitle) {
		super();
		this.id = id;
		this.notify = notify;
		this.message = message;
		this.realname = realname;
		this.dramaTitle = dramaTitle;
	}

	/*

	public NotificationsTable(int id, String name, String title, String message, boolean enabled) {
		super();
		this.id = id;
		this.name = name;
		this.title = title;
		this.message = message;
		setNotify(enabled);
		this.notify = getNotify();
	}*/

	public NotificationsTable() {
		// TODO Auto-generated constructor stub
	}

	public boolean getNotify() {
		return notify;
	}

//	public void setNotify(boolean notify) {
//		this.notify = notify;
//	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public String getDramaTitle() {
		return dramaTitle;
	}
	public void setDramaTitle(String dramaTitle) {
		this.dramaTitle = dramaTitle;
	}
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
