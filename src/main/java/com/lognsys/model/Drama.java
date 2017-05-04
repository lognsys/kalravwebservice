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
	private String date;
	private String imageurl;
    private String drama_length;
    private String music;
    private String avg_rating;


    private String group_name="";
    private String auditorium_name="";
    private int group_id;
    private int auditorium_id;
	
    
    
    

	public Drama(int id, String title, String genre, String star_cast, String director, String writer,
			String description, String date, String imageurl, String drama_length, String music, String avg_rating,
			String group_name, String auditorium_name, int group_id, int auditorium_id) {
		super();
		this.id = id;
		this.title = title;
		this.genre = genre;
		this.star_cast = star_cast;
		this.director = director;
		this.writer = writer;
		this.description = description;
		this.date = date;
		this.imageurl = imageurl;
		this.drama_length = drama_length;
		this.music = music;
		this.avg_rating = avg_rating;
		this.group_name = group_name;
		this.auditorium_name = auditorium_name;
		this.group_id = group_id;
		this.auditorium_id = auditorium_id;

	}

	public Drama() {
	}

	public String getGroup_name() {
		return group_name;
	}

	public void setGroup_name(String group_name) {
		this.group_name = group_name;
	}

	public String getAuditorium_name() {
		return auditorium_name;
	}

	public void setAuditorium_name(String auditorium_name) {
		this.auditorium_name = auditorium_name;
	}

	public int getGroup_id() {
		return group_id;
	}

	public void setGroup_id(int group_id) {
		this.group_id = group_id;
	}

	public String getMusic() {
		return music;
	}

	public void setMusic(String music) {
		this.music = music;
	}

	public String getAvg_rating() {
		return avg_rating;
	}

	public void setAvg_rating(String avg_rating) {
		this.avg_rating = avg_rating;
	}

	public String getDrama_length() {
		return drama_length;
	}

	public void setDrama_length(String drama_length) {
		this.drama_length = drama_length;
	}

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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
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

	

}
