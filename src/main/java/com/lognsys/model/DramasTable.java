package com.lognsys.model;

public class DramasTable {

	private int id;
	private String title;
	private String auditorium_name;
	private String group;
	private String date;

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public DramasTable(int id, String title, String auditorium_name, String group, String date) {
		super();
		this.id = id;
		this.title = title;
		this.auditorium_name = auditorium_name;
		this.group = group;
		this.date = date;
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

	public String getAuditorium_name() {
		return auditorium_name;
	}

	public void setAuditorium_name(String auditorium_name) {
		this.auditorium_name = auditorium_name;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

}
