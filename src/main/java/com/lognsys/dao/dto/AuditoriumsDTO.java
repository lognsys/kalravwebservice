package com.lognsys.dao.dto;

public class AuditoriumsDTO {

	private int id;
	private String auditorium_name;
	private String address;
	private String last_edit;
	
	public AuditoriumsDTO(int id, String auditorium_name,String address,String last_edit) {
		super();
		this.id = id;
		this.auditorium_name = auditorium_name;
		this.address = address;
		this.last_edit = last_edit;
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

	public void setLast_edit(String last_edit) {
		this.last_edit = last_edit;
	}



}
