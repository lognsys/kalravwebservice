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
	private String date;
	private String time;
	private int istart;
	private int iend;
	private Double price;

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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public int getIstart() {
		return istart;
	}

	public void setIstart(int istart) {
		this.istart = istart;
	}

	public int getIend() {
		return iend;
	}

	public void setIend(int iend) {
		this.iend = iend;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public AuditoriumsDTO(int id, String auditorium_name, String address, String latitude, String longitude,
			String last_edit, String date, String time, int istart, int iend, Double price) {
		super();
		this.id = id;
		this.auditorium_name = auditorium_name;
		this.address = address;
		this.latitude = latitude;
		this.longitude = longitude;
		this.last_edit = last_edit;
		this.date = date;
		this.time = time;
		this.istart = istart;
		this.iend = iend;
		this.price = price;
	}

	@Override
	public String toString() {
		return "AuditoriumsDTO [id=" + id + ", auditorium_name=" + auditorium_name + ", address=" + address
				+ ", latitude=" + latitude + ", longitude=" + longitude + ", last_edit=" + last_edit + ", date=" + date
				+ ", time=" + time + ", istart=" + istart + ", iend=" + iend + ", price=" + price + "]";
	}

}
