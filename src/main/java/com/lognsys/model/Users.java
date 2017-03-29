package com.lognsys.model;

/**
 * 
 * 
 * @author pdoshi
 *
 */
public class Users {
	private String auth_id = "";
	private String username = "";
	private String realname = "";
	private String phone = "";
	private String location = "";
	private String provenance = "";
	private String birthdate = "";
	private boolean enabled = false;
	private boolean notification = false;
	private double avg_rÍating;

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public double getAvg_rÍating() {
		return avg_rÍating;
	}

	public void setAvg_rÍating(double avg_rÍating) {
		this.avg_rÍating = avg_rÍating;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getAuth_id() {
		return auth_id;
	}

	public void setAuth_id(String auth_id) {
		this.auth_id = auth_id;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	public boolean isNotification() {
		return notification;
	}

	public void setNotification(boolean notification) {
		this.notification = notification;
	}

	public String getProvenance() {
		return provenance;
	}

	public void setProvenance(String provenance) {
		this.provenance = provenance;
	}

}
