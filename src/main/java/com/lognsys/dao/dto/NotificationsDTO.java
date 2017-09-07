package com.lognsys.dao.dto;

public class NotificationsDTO {

	private int id;
	private boolean notify=true;
	private String message;
	private int userId;
	private String realname;
	private int dramaId;
	private String dramaTitle;
	
	public NotificationsDTO() {
		// no-arg constructor
	}
	public NotificationsDTO(int id, boolean notify, String message,int userId,int dramaId,String realname,String dramaTitle) {
		this.id=id;
		this.notify=notify;
		this.message=message;
		this.userId=userId;
		this.dramaId=dramaId;
		this.realname=realname;
		this.dramaTitle=dramaTitle;
	}
	public NotificationsDTO(int id,boolean notify, String message) {
		super();
		this.id = id;
		this.notify = notify;
		this.message = message;
	}

	public NotificationsDTO(int id, boolean notify, String message, String realname, String dramaTitle) {
		this.id=id;
		this.notify=notify;
		this.message=message;
		this.realname=realname;
		this.dramaTitle=dramaTitle;
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
	@Override
	public String toString() {
		return "NotificationsDTO [id=" + id + ", notify=" + notify + ", message=" + message + ", userId=" + userId
				+ ", realname=" + realname + ", dramaId=" + dramaId + ", dramaTitle=" + dramaTitle + "]";
	}
	
}
