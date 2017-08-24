package com.lognsys.dao.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class BookedRowSeatsDTO {

	@Id
	private int id;

	@DBRef
	private RowSeatDTO rows;

	@DBRef
	private BookingDTO bookingDTO;

	public BookedRowSeatsDTO(int id, RowSeatDTO rows, BookingDTO bookingDTO) {
		super();
		this.id = id;
		this.rows = rows;
		this.bookingDTO = bookingDTO;
	}

	public BookedRowSeatsDTO() {

	}

	public RowSeatDTO getRowSeat() {
		return rows;
	}

	public void setRowSeat(RowSeatDTO rows) {
		this.rows = rows;
	}

	public BookingDTO getBooking() {
		return bookingDTO;
	}

	public void setBooking(BookingDTO bookingDTO) {
		this.bookingDTO = bookingDTO;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
