package com.lognsys.dao.dto;

import org.springframework.data.mongodb.core.mapping.Document;


@Document
public class BookedSeatsDTO {
	private int id;
    private int  booking_id;
    private int row_seats_id;
    private int refer_seat_id;
	public BookedSeatsDTO() {
		super();
	}
	public BookedSeatsDTO(int id, int booking_id, int row_seats_id, int refer_seat_id) {
		super();
		this.id = id;
		this.booking_id = booking_id;
		this.row_seats_id = row_seats_id;
		this.refer_seat_id = refer_seat_id;
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
	public int getRefer_seat_id() {
		return refer_seat_id;
	}
	public void setRefer_seat_id(int refer_seat_id) {
		this.refer_seat_id = refer_seat_id;
	}
	
}
