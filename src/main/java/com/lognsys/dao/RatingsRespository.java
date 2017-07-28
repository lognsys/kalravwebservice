package com.lognsys.dao;

import java.util.List;

import com.lognsys.dao.dto.DramasDTO;
import com.lognsys.dao.dto.RatingsDTO;
import com.lognsys.dao.dto.UsersDTO;

public interface RatingsRespository {

	/**
	 * Add rating into database
	 * 
	 * @param ratings
	 */
	public int addRating(RatingsDTO ratings);
	
	
	/**
	 * Check if ratings exists by user_id
	 * @param user_id
	 * @return
	 */
	public boolean isExists(RatingsDTO ratingsDTO);
	public RatingsDTO findRatingByUserIDAndDramaID(int users_id,int dramas_id);

	/**
	 * Update rating information, enable/disable etc..
	 * 
	 * @param rating
	 */
	public int updateRating(RatingsDTO ratingsDTO);


	/**
	 * Get All rating
	 * 
	 * @return
	 */
	public List<RatingsDTO> getAllRatings();

}
