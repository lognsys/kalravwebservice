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

    private String drama_language;


	private String group="";
	private String auditorium="";
    
    
    

	public Drama(int id, String title, String genre, String star_cast, String director, String writer,
			String description, String date, String imageurl, String drama_length, String music,
			String avg_rating,String group,String auditorium,String drama_language) {
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
		this.group = group;
		this.auditorium = auditorium;
		this.drama_language=drama_language;

	}

	public Drama(int id, String title, String genre, String star_cast, String director, String writer,
			String description, String date, String imageurl, String drama_length, String music, String avg_rating,String drama_language) {
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
		this.drama_language=drama_language;
	}

	public Drama() {
	}

	public String getDrama_language() {
		return drama_language;
	}

	public void setDrama_language(String drama_language) {
		this.drama_language = drama_language;
	}

	public String getAuditorium() {
		return auditorium;
	}

	public void setAuditorium(String auditorium) {
		this.auditorium = auditorium;
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

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

}
