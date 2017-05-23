package com.lognsys.model;

/**
 * 
 * Description: User class is Value Object which populates client side object.
 * This takes care of client side validation.
 * 
 * Change LOG :
 * 
 * 1) PJD - 04/05/17 Added Fields String role, String group
 * 
 * @author pdoshi
 */

public class Users {

	private int id;

	private String auth_id = "";
	private String username = "";
	private String realname = "";
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
	private String firstname = "";
	private String lastname = "";
	private String role = "";
	private String group = "";

	public Users(int id, String auth_id, String username, String realname, String phone,
			String provenance, String birthdate, boolean enabled, boolean notification, String device, String address,
			String city, String state, String zipcode, String firstname, String lastname,
			String group, String role) {
		super();
		this.id = id;
		this.auth_id = auth_id;
		this.username = username;
		this.realname = realname;
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
		this.firstname = firstname;
		this.lastname = lastname;
		this.group = group;
		this.role = role;
	}

	// for mapping UsersDTO to Users
	public Users(int id, String auth_id, String username, String realname, String phone,
			String provenance, String birthdate, boolean enabled, boolean notification, String device, String address,
			String city, String state, String zipcode, String firstname, String lastname) {
		super();
		this.id = id;
		this.auth_id = auth_id;
		this.username = username;
		this.realname = realname;
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
		this.firstname = firstname;
		this.lastname = lastname;

	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Users() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
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


	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setlastname(String lastname) {
		this.lastname = lastname;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getDevice() {
		return device;
	}

	public void setDevice(String device) {
		this.device = device;
	}

	@Override
	public String toString() {
		return "Users [id=" + id + ", auth_id=" + auth_id + ", username=" + username + ", realname=" + realname
				+ ", phone=" + phone + ", provenance=" + provenance + ", birthdate=" + birthdate + ", enabled="
				+ enabled + ", notification=" + notification + ", device=" + device + ", address=" + address + ", city="
				+ city + ", state=" + state + ", zipcode=" + zipcode + ", firstname=" + firstname + ", lastname="
				+ lastname + ", role=" + role + ", group=" + group + "]";
	}

}
