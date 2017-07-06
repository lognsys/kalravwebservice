package com.lognsys.dao.dto;

public class RowSeatDTO {

	private int id;
	private int row_num;
	private String row_name;
	private int seat_num;
	private int auditoriums_id;
	private String seatCount;
	private String auditorium_name;

	public RowSeatDTO(int row_num, String row_name, int seat_num, int auditoriums_id) {
		super();

		this.row_num = row_num;
		this.row_name = row_name;
		this.seat_num = seat_num;
		this.auditoriums_id = auditoriums_id;

	}

	public RowSeatDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRow_num() {
		return row_num;
	}

	public void setRow_num(int row_num) {
		this.row_num = row_num;
	}

	public String getRow_name() {
		return row_name;
	}

	public void setRow_name(String row_name) {
		this.row_name = row_name;
	}

	public int getSeat_num() {
		return seat_num;
	}

	public void setSeat_num(int seat_num) {
		this.seat_num = seat_num;
	}

	public int getAuditoriums_id() {
		return auditoriums_id;
	}

	public void setAuditoriums_id(int auditoriums_id) {
		this.auditoriums_id = auditoriums_id;
	}

	public String getSeatCount() {
		return seatCount;
	}

	public void setSeatCount(String seatCount) {
		this.seatCount = seatCount;
	}

	public String getAuditorium_name() {
		return auditorium_name;
	}

	public void setAuditorium_name(String auditorium_name) {
		this.auditorium_name = auditorium_name;
	}

}
