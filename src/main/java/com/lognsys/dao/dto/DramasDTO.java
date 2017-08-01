package com.lognsys.dao.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Database object
 * 
 * @author monika
 *
 */

@Document(collection = "dramas")
public class DramasDTO {
	@Id
	private int id;

    private String title="";
    private String imageurl="";
    private String drama_length="";
    private String date="";
    private String genre="";
    private String star_cast="";
    private String description="";
    private String director="";
    private String writer="";
    private String music="";
    private String avg_rating="";
    private String drama_language="";
    
public DramasDTO(int id, String title, String imageurl) {
		super();
		this.id = id;
		this.title = title;
		this.imageurl = imageurl;
	}

public DramasDTO(int id, String title, String imageurl, String drama_length, String date, String genre,
			String star_cast, String description, String director, String writer, String music, String avg_rating,String drama_language) {
		super();
		this.id = id;
		this.title = title;
		this.imageurl = imageurl;
		this.drama_length = drama_length;
		this.date = date;
		this.genre = genre;
		this.star_cast = star_cast;
		this.description = description;
		this.director = director;
		this.writer = writer;
		this.music = music;
		this.avg_rating = avg_rating;
		this.drama_language=drama_language;
	}

	/*
	public DramasDTO(int id, String title, String imageurl, String drama_length, String date, String genre,
			String star_cast, String description, String director, String writer, String music, String avg_rating,
			String group_name, String auditorium_name, int group_id, int auditorium_id) {
		super();
		this.id = id;
		this.title = title;
		this.imageurl = imageurl;
		this.drama_length = drama_length;
		this.date = date;
		this.genre = genre;
		this.star_cast = star_cast;
		this.description = description;
		this.director = director;
		this.writer = writer;
		this.music = music;
		this.avg_rating = avg_rating;
		this.group_name = group_name;
		this.auditorium_name = auditorium_name;
		this.group_id = group_id;
		this.auditorium_id = auditorium_id;
	}
*/
	
public DramasDTO() {
		super();
	}

	

	public String getDrama_language() {
	return drama_language;
}

public void setDrama_language(String drama_language) {
	this.drama_language = drama_language;
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

	public String getImageurl() {
		return imageurl;
	}

	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}

	public String getDrama_length() {
		return drama_length;
	}

	public void setDrama_length(String drama_length) {
		this.drama_length = drama_length;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	@Override
	public String toString() {
		return "DramasDTO [id=" + id + ", title=" + title + ", imageurl=" + imageurl + ", drama_length=" + drama_length
				+ ", date=" + date + ", genre=" + genre + ", star_cast=" + star_cast + ", description=" + description
				+ ", director=" + director + ", writer=" + writer + ", music=" + music + ", avg_rating=" + avg_rating
				+ "]";
	}




}
