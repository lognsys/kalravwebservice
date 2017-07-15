package com.lognsys.dao.dto;

public class DeviceDTO {

	private int id;
	private String deviceToken="";

	public DeviceDTO(int id, String deviceToken) {
		
		this.id = id;
		this.deviceToken = deviceToken;
	}
	public DeviceDTO( String deviceToken) {
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
	@Override
	public String toString() {
		return "DeviceDTO [id=" + id + ", deviceToken=" + deviceToken + "]";
	}
	
}
