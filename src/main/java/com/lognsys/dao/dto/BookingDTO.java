package com.lognsys.dao.dto;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class BookingDTO {
	private int id;

    private String booking_date="";

    private String confirmation_no="";

    private int users_id;

    private int booking_seatcount;
    private int dramas_auditoriums_id;
	public BookingDTO(int id, String booking_date, String confirmation_no, int users_id, int booking_seatcount,
			int dramas_auditoriums_id) {
		super();
		this.id = id;
		this.booking_date = booking_date;
		this.confirmation_no = confirmation_no;
		this.users_id = users_id;
		this.booking_seatcount = booking_seatcount;
		this.dramas_auditoriums_id = dramas_auditoriums_id;
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
	public int getBooking_seatcount() {
		return booking_seatcount;
	}
	public void setBooking_seatcount(int booking_seatcount) {
		this.booking_seatcount = booking_seatcount;
	}
	public int getDramas_auditoriums_id() {
		return dramas_auditoriums_id;
	}
	public void setDramas_auditoriums_id(int dramas_auditoriums_id) {
		this.dramas_auditoriums_id = dramas_auditoriums_id;
	}
	@Override
	public String toString() {
		return "BookingDTO [id=" + id + ", booking_date=" + booking_date + ", confirmation_no=" + confirmation_no
				+ ", users_id=" + users_id + ", booking_seatcount=" + booking_seatcount + ", dramas_auditoriums_id="
				+ dramas_auditoriums_id + "]";
	}
  
    
}
