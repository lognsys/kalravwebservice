package com.lognsys.model;

public class Device {

	private int id;
	private String deviceToken;
	private int users_id;
	
	public int getUsers_id() {
		return users_id;
	}
	public void setUsers_id(int users_id) {
		this.users_id = users_id;
	}
	public Device() {
		super();
	}
	public Device(int id, String deviceToken) {
		super();
		this.id = id;
		this.deviceToken = deviceToken;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDeviceToken() {
		return deviceToken;
	}
	public void setDeviceToken(String deviceToken) {
		this.deviceToken = deviceToken;
	}
	
	
}
