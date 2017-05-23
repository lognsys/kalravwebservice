package com.lognsys.dao.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Database object
 * 
 * @author pdoshi
 *
 */

@Document(collection = "users")
public class UsersDTO {
	@Id
	private int id;
	private String realname = "";
	private String username = "";
	private String auth_id = "";
	private String phone = "";
	private String provenance = "";
	private String birthdate = null;
	private boolean enabled = false;
	private boolean notification = false;

	private String device = "";
	private String address = "";
	private String city = "";
	private String state = "";
	private String zipcode = "";
    
	
	public UsersDTO() {
		super();
	}

	public UsersDTO(int id, String realname, String username, String auth_id, String phone,
			String provenance, String birthdate, boolean enabled, boolean notification, String device, String address,
			String city, String state, String zipcode) {
		super();
		this.id = id;
		this.realname = realname;
		this.username = username;
		this.auth_id = auth_id;
		this.phone = phone;
		this.provenance = provenance;
		this.birthdate = birthdate;
		this.enabled = enabled;
		this.notification = notification;
		this.device = device;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zipcode = zipcode;
	}

	
	public String getAuth_id() {
		return auth_id;
	}

	public void setAuth_id(String auth_id) {
		this.auth_id = auth_id;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	
	public String getProvenance() {
		return provenance;
	}

	public void setProvenance(String provenance) {
		this.provenance = provenance;
	}

	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public boolean isNotification() {
		return notification;
	}

	public void setNotification(boolean notification) {
		this.notification = notification;
	}

	public String getDevice() {
		return device;
	}

	public void setDevice(String device) {
		this.device = device;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "UsersDTO [id=" + id + ", realname=" + realname + ", username=" + username + ", auth_id=" + auth_id
				+ ", phone=" + phone + ", provenance=" + provenance + ", birthdate=" + birthdate + ", enabled="
				+ enabled + ", notification=" + notification + ", device=" + device + ", address=" + address + ", city="
				+ city + ", state=" + state + ", zipcode=" + zipcode + "]";
	}

	

}
