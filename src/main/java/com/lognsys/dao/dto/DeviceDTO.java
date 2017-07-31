package com.lognsys.dao.dto;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class DeviceDTO {

	private int id;

	private int users_id;
	private String deviceToken="";
	
	
	public DeviceDTO() {
		
	}
	
	public DeviceDTO(int id, int users_id, String deviceToken) {
		super();
		this.id = id;
		this.users_id = users_id;
		this.deviceToken = deviceToken;
	}

	public DeviceDTO( String deviceToken) {
		this.deviceToken = deviceToken;
	}
	
	
	
	public int getUsers_id() {
		return users_id;
	}
	public void setUsers_id(int users_id) {
		this.users_id = users_id;
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

	@Override
	public String toString() {
		return "DeviceDTO [id=" + id + ", users_id=" + users_id + ", deviceToken=" + deviceToken + "]";
	}
	
	
}
