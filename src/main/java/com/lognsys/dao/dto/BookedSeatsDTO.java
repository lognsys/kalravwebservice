package com.lognsys.dao.dto;

import org.springframework.data.mongodb.core.mapping.Document;


@Document
public class BookedSeatsDTO {
	private int id;
    private int  booking_id;
    private int row_seats_id;
    private String seat_status;
	public BookedSeatsDTO() {
		super();
	}
	public BookedSeatsDTO(int id, int booking_id, int row_seats_id, String seat_status) {
		super();
		this.id = id;
		this.booking_id = booking_id;
		this.row_seats_id = row_seats_id;
		this.seat_status = seat_status;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getBooking_id() {
		return booking_id;
	}
	public void setBooking_id(int booking_id) {
		this.booking_id = booking_id;
	}
	public int getRow_seats_id() {
		return row_seats_id;
	}
	public void setRow_seats_id(int row_seats_id) {
		this.row_seats_id = row_seats_id;
	}
	public String getSeat_status() {
		return seat_status;
	}
	public void setSeat_status(String seat_status) {
		this.seat_status = seat_status;
	}
	@Override
	public String toString() {
		return "BookedSeatsDTO [id=" + id + ", booking_id=" + booking_id + ", row_seats_id=" + row_seats_id
				+ ", seat_status=" + seat_status + "]";
	}

	
}
