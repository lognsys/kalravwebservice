package com.lognsys.dao.dto;

/**
 * 
 * @author pdoshi
 * 
 * Descriptions : AuditoriumsDTO fields are mapped with MySQL attributes 
 * in database fields.
 * 
 */
public class AuditoriumsDTO {

	private int id;
	private String auditorium_name;
	private String address;
	private String latitude;
	private String longitude;
	private String last_edit;

	public AuditoriumsDTO() {
	}

	public AuditoriumsDTO(String auditorium_name, String address, String latitude, String longitude) {
		super();
		this.auditorium_name = auditorium_name;
		this.address = address;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAuditorium_name() {
		return auditorium_name;
	}

	public void setAuditorium_name(String auditorium_name) {
		this.auditorium_name = auditorium_name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLast_edit() {
		return last_edit;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public void setLast_edit(String last_edit) {
		this.last_edit = last_edit;
	}

}
