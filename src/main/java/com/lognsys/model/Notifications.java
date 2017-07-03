package com.lognsys.model;

public class Notifications {

	private int id;/*
	private int users_id;
	private int dramas_id;*/
	private boolean notify=true;
	private String message;
	private int userId;
	private String realname;
	private int dramaId;
	private String dramaTitle;
	public Notifications() {
		super();
	}
	public Notifications(int i, boolean b, String string,int dramaId,int userId,String realname,String dramaTitle) {
		this.id=i;
		this.notify=b;
		this.message=string;
		this.dramaId=dramaId;
		this.userId=userId;
		this.realname=realname;
		this.dramaTitle=dramaTitle;
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

public int getDramaId() {
		return dramaId;
	}
	public void setDramaId(int dramaId) {
		this.dramaId = dramaId;
	}
	
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public String getRealnamee() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
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
	
	public String getDramaTitle() {
		return dramaTitle;
	}
	public void setDramaTitle(String dramaTitle) {
		this.dramaTitle = dramaTitle;
	}
	@Override
	public String toString() {
		return "Notifications [id=" + id + ", notify=" + notify + ", message=" + message + ", userId=" + userId
				+ ", realname=" + realname + ", dramaId=" + dramaId + ", dramaTitle=" + dramaTitle + "]";
	}
	


}
