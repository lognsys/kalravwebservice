package com.lognsys.model;

public class Auditoriums {

	private int id;
	private String auditorium_name;
	private String address;
	private double lat_lon;

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

	public double getLat_lon() {
		return lat_lon;
	}

	public void setLat_lon(double lat_lon) {
		this.lat_lon = lat_lon;
	}

}
