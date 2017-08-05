package com.lognsys.model;

import org.springframework.data.mongodb.core.mapping.Document;


public class Booking {
	private int id;

    private String booking_date="";

    private String confirmation_no="";

    private int users_id;
    private int dramas_id;
    private int auditoriums_id;
    private double price;
    private String status;


	private static enum STATUS {
		Booked, Cancel
	}
	public Booking() {
		super();
	}
	public Booking(int id, String booking_date, String confirmation_no, int users_id, int dramas_id,
			int auditoriums_id, double price,String status) {
		super();
		this.id = id;
		this.booking_date = booking_date;
		this.confirmation_no = confirmation_no;
		this.users_id = users_id;
		this.dramas_id = dramas_id;
		this.auditoriums_id = auditoriums_id;
		this.price = price;
		this.status = status;
	}
	public Booking(int id, String booking_date, String confirmation_no, int users_id, int dramas_id,
			int auditoriums_id, double price,boolean enabled) {
		super();
		this.id = id;
		this.booking_date = booking_date;
		this.confirmation_no = confirmation_no;
		this.users_id = users_id;
		this.dramas_id = dramas_id;
		this.auditoriums_id = auditoriums_id;
		this.price = price;
		setStatus(enabled);
		this.status = getStatus();
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setStatus(boolean enabled) {
		if (enabled) {
			this.status = STATUS.Booked.name();
		} else {
			this.status = STATUS.Cancel.name();
		}
		setStatus(this.status);
	}
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBooking_date() {
		return booking_date;
	}
	public void setBooking_date(String booking_date) {
		this.booking_date = booking_date;
	}
	public String getConfirmation_no() {
		return confirmation_no;
	}
	public void setConfirmation_no(String confirmation_no) {
		this.confirmation_no = confirmation_no;
	}
	public int getUsers_id() {
		return users_id;
	}
	public void setUsers_id(int users_id) {
		this.users_id = users_id;
	}
	public int getDramas_id() {
		return dramas_id;
	}
	public void setDramas_id(int dramas_id) {
		this.dramas_id = dramas_id;
	}
	
	public int getAuditoriums_id() {
		return auditoriums_id;
	}
	public void setAuditoriums_id(int auditoriums_id) {
		this.auditoriums_id = auditoriums_id;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "Booking [id=" + id + ", booking_date=" + booking_date + ", confirmation_no=" + confirmation_no
				+ ", users_id=" + users_id + ", dramas_id=" + dramas_id + ", auditoriums_id=" + auditoriums_id
				+ ", price=" + price + "]";
	}
    
    
    
}
