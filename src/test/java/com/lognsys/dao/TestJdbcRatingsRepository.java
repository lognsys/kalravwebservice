package com.lognsys.dao;

import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import com.lognsys.dao.BookingRepository;
import com.lognsys.dao.RoleRepository;
import com.lognsys.dao.dto.BookingDTO;
import com.lognsys.dao.dto.RatingsDTO;
import com.lognsys.dao.dto.RolesDTO;
import com.lognsys.dao.jdbc.JdbcBookingRepository;
import com.lognsys.dao.jdbc.JdbcRatingsRepository;
import com.lognsys.model.Booking;
import com.lognsys.model.Ratings;
import com.lognsys.model.Users;
import com.lognsys.util.Constants;
import com.lognsys.util.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:datasource-context.xml" })
public class TestJdbcRatingsRepository  {

	// Injecting namedParamterTemplate
		@Autowired
		private JdbcRatingsRepository jdbcRatingsRepository;
		
		public void setUp() {

		}
	
	/**
	 * Add rating object into database
	 * 
	 * @param id, rating, rating_date, users_id, dramas_id, last_edit
	 */
	@Test
	public void addRating() {
		Ratings ratings = new Ratings();

		ratings.setRating(3.3);
		ratings.setRating_date("2017-07-31");
		ratings.setUsers_id(40);
		ratings.setDramas_id(5);
		boolean isExist=jdbcRatingsRepository.isExists((ObjectMapper.mapToRatingsDTO(ratings)));
		if(!isExist){
			int id=jdbcRatingsRepository.addRating((ObjectMapper.mapToRatingsDTO(ratings)));
			Assert.notNull(id, "isAdded ratings id - " + id );
		}
		else{
			RatingsDTO rating  =jdbcRatingsRepository.findRatingByUserIDAndDramaID(ratings.getUsers_id(),ratings.getDramas_id());
			Assert.notNull(rating, "Check list of rating NOT NULL");
			rating.setRating(4.3);
			int isUpdated=jdbcRatingsRepository.updateRating(rating);
		
		}
	}

	@Test
	public void updateRating() {
		int users_id=40;
		int dramas_id=5;
		RatingsDTO rating  =jdbcRatingsRepository.findRatingByUserIDAndDramaID(users_id,dramas_id);
		Assert.notNull(rating, "Check list of rating NOT NULL");
		rating.setRating(4.3);
	
		int isUpdated=jdbcRatingsRepository.updateRating(rating);
	
	}

	@Test
	public void getAllRatings() {
		 List<RatingsDTO> ratingsDTOs=jdbcRatingsRepository.getAllRatings();
			Assert.notNull(ratingsDTOs, "Check list of ratingsDTOs NOT NULL");
			
	}
	@Test
	public void findRatingByUserIDAndDramaID() {

		int users_id=40;
		int dramas_id=5;
		 RatingsDTO rating  =jdbcRatingsRepository.findRatingByUserIDAndDramaID(users_id,dramas_id);
		Assert.notNull(rating, "Check list of rating NOT NULL");

	}
}
