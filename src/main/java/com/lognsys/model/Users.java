package com.lognsys.model;


/**
 * 
 * @author pdoshi
 */

public class Users {

	private int id;

	private String auth_id = "";
	private String username = "";
	private String realname = "";
	private String phone = "";
	private String location = "";
	private String provenance = "";
	private String birthdate = "";
	private boolean enabled = false;
	private boolean notification = false;
	
	private String device;

	// Monika added 30/03/17
	private String address = "";
	private String city = "";
	private String state = "";
	private String zipcode = "";
	private String company_name = "";
	private String firstname = "";
	private String lastname = "";
	private String title = "";
	
	
	public Users() {
		// TODO Auto-generated constructor stub
	}

	public Users(int id, String auth_id, String username, String realname, String phone, String location,
			String provenance, String birthdate, boolean enabled, boolean notification,
			String device, String address, String city, String state, String zipcode, String company_name,
			String firstname, String lastname, String title) {
		super();
		this.id = id;
		this.auth_id = auth_id;
		this.username = username;
		this.realname = realname;
		this.phone = phone;
		this.location = location;
		this.provenance = provenance;
		this.birthdate = birthdate;
		this.enabled = enabled;
		this.notification = notification;

		this.device = device;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zipcode = zipcode;
		this.company_name = company_name;
		this.firstname = firstname;
		this.lastname = lastname;
		this.title = title;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
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

	public String getDevice() {
		return device;
	}

	public void setDevice(String device) {
		this.device = device;
	}

}
