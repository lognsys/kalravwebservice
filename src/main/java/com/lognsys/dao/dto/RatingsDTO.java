package com.lognsys.dao.dto;

/**
 * 
 * @author pdoshi
 * 
 * Descriptions : RatingsDTO fields are mapped with MySQL attributes 
 * in database fields.
 * 
 */
public class RatingsDTO {

	private int id;
	private double rating;
	private String rating_date;
	private int dramas_id;
	private int users_id;
	
	
	
	public RatingsDTO() {
		super();
	}

	public RatingsDTO(int id, double rating, String rating_date, int dramas_id, int users_id) {
		super();
		this.id = id;
		this.rating = rating;
		this.rating_date = rating_date;
		this.dramas_id = dramas_id;
		this.users_id = users_id;
	}

	public String getRating_date() {
		return rating_date;
	}

	public void setRating_date(String rating_date) {
		this.rating_date = rating_date;
	}

	public int getDramas_id() {
		return dramas_id;
	}

	public void setDramas_id(int dramas_id) {
		this.dramas_id = dramas_id;
	}

	public int getUsers_id() {
		return users_id;
	}

	public void setUsers_id(int users_id) {
		this.users_id = users_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	@Override
	public String toString() {
		return "RatingsDTO [id=" + id + ", rating=" + rating + ", rating_date=" + rating_date + ", dramas_id="
				+ dramas_id + ", users_id=" + users_id +  "]";
	}
	
}
