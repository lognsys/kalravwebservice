package com.lognsys.model;

import java.sql.Date;

/**
 * Drama POJO as value object for retrieving values from form
 * 
 * 
 * @author pdoshi
 *
 */

public class Drama {

	private int id;
	private String title;
	private String genre;
	private String star_cast;
	private String director;
	private String writer;
	private String description;
	private int auditorium_id;
	private Date date;
	private int avg_rating;
	private String imageurl;

	public String getImageurl() {
		return imageurl;
	}

	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}

	private String place;

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

	public int getAuditorium_id() {
		return auditorium_id;
	}

	public void setAuditorium_id(int auditorium_id) {
		this.auditorium_id = auditorium_id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getAvg_rating() {
		return avg_rating;
	}

	public void setAvg_rating(int avg_rating) {
		this.avg_rating = avg_rating;
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

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

}
