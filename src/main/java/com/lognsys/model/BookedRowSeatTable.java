package com.lognsys.model;


public class BookedRowSeatTable {

	private static enum STATUS {
		Active, Passive
	}

	private int id;
	private int row_num;
	private int seat_number;
	private int auditoriums_id;
	private String status;

	public BookedRowSeatTable() {
		super();
	}

	public BookedRowSeatTable(int row_num, int seat_number, int auditoriums_id, String status) {
		super();
		this.row_num = row_num;
		this.seat_number = seat_number;
		this.auditoriums_id = auditoriums_id;
		this.status = status;
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

	public String getStatus() {
		return status;
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

	public int getSeat_number() {
		return seat_number;
	}

	public void setSeat_number(int seat_number) {
		this.seat_number = seat_number;
	}

	public int getAuditoriums_id() {
		return auditoriums_id;
	}

	public void setAuditoriums_id(int auditoriums_id) {
		this.auditoriums_id = auditoriums_id;
	}


}
