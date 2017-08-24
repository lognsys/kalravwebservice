package com.lognsys.dao.dto;

import org.springframework.data.mongodb.core.mapping.Document;


@Document
public class BookingDTO {
	private int id;

    private String booking_date="";

    private String confirmation_no="";

    private int users_id;
    private int dramas_id;
    private int auditorium_id;
    private double price;
    private String status;


	private static enum STATUS {
		Active, Passive
	}
	public BookingDTO() {
		super();
	}
	public BookingDTO(int id, String booking_date, String confirmation_no, int users_id, int dramas_id,
			int auditorium_id, double price,String status) {
		super();
		this.id = id;
		this.booking_date = booking_date;
		this.confirmation_no = confirmation_no;
		this.users_id = users_id;
		this.dramas_id = dramas_id;
		this.auditorium_id = auditorium_id;
		this.price = price;
		this.status = status;
	}
	public BookingDTO(int id, String booking_date, String confirmation_no, int users_id, int dramas_id,
			int auditorium_id, double price,boolean enabled) {
		super();
		this.id = id;
		this.booking_date = booking_date;
		this.confirmation_no = confirmation_no;
		this.users_id = users_id;
		this.dramas_id = dramas_id;
		this.auditorium_id = auditorium_id;
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
			this.status = STATUS.Active.name();
		} else {
			this.status = STATUS.Passive.name();
		}
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
	public int getAuditorium_id() {
		return auditorium_id;
	}
	public void setAuditorium_id(int auditorium_id) {
		this.auditorium_id = auditorium_id;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "BookingDTO [id=" + id + ", booking_date=" + booking_date + ", confirmation_no=" + confirmation_no
				+ ", users_id=" + users_id + ", dramas_id=" + dramas_id + ", auditorium_id=" + auditorium_id
				+ ", price=" + price + "]";
	}
    
    
}