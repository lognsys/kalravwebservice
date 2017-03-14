package com.lognsys.model;

public class Drama {

	private int drama_id;
	private int group_id;
	private String genre;
	private String star_cast;
	private String director;
	private String writer;
	private String description;
	private String date_time;
	private String place;

	public int getDrama_id() {
		return drama_id;
	}

	public void setDrama_id(int drama_id) {
		this.drama_id = drama_id;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getStar_cast() {
		return star_cast;
	}

	public void setStar_cast(String star_cast) {
		this.star_cast = star_cast;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDate_time() {
		return date_time;
	}

	public void setDate_time(String date_time) {
		this.date_time = date_time;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public int getGroup_id() {
		return group_id;
	}

	public void setGroup_id(int group_id) {
		this.group_id = group_id;
	}

}
