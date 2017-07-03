package com.lognsys.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lognsys.dao.dto.RatingsDTO;
import com.lognsys.dao.dto.UsersDTO;
import com.lognsys.dao.jdbc.JdbcRatingsRepository;
import com.lognsys.service.RatingService;

@RestController
public class RestRatingController {

//	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(RestRatingController.class);
	
	@Autowired
	private RatingService ratingService;
	
	@Autowired
	private JdbcRatingsRepository jdbcRatingsRepository;
		
	
	/**
	 * 
	 * @param id
	 * @param rating
	 * @param rating_date
	 * @param users_id
	 * @param dramas_id
	 * @param last_edit
	 * @return
	 */
	
	@RequestMapping(value = "/ratedrama/{id}/{rating}/{rating_date}/{users_id}/{dramas_id}", method = RequestMethod.POST)
	public ResponseEntity<Boolean> createUser(@PathVariable(value = "id") int id,
			@PathVariable(value = "rating") double rating, @PathVariable(value = "rating_date") String rating_date,
			@PathVariable(value = "users_id") int users_id, @PathVariable(value = "dramas_id") int dramas_id) {

		
		try {
			RatingsDTO ratingsDTO=new RatingsDTO(id, rating, rating_date, users_id, dramas_id);
			
			System.out.println("Creating User toString " + ratingsDTO.toString());
			
			boolean isExists = jdbcRatingsRepository.isExists(ratingsDTO);
			
			System.out.println("Creating User isExists " + isExists);
			
			if (isExists) {
				System.out.println(" isExists ratingsDTO.toString() " + ratingsDTO.toString());

				ratingsDTO = (jdbcRatingsRepository.findRatingByUserIDAndDramaID(ratingsDTO.getUsers_id(),ratingsDTO.getDramas_id()));
				int Updatecount =ratingService.updateRating(ratingsDTO.getId(),ratingsDTO);
				return new ResponseEntity(ratingsDTO, HttpStatus.OK);
			} else {
				int ID = jdbcRatingsRepository.addRating(ratingsDTO);
				ratingsDTO.setId(ID);
				System.out.println("Creating User ID " + ID);
				System.out.println("Creating User usersDTO tostring after add " + ratingsDTO.toString());
				
				return new ResponseEntity("Thank You for Rating....", HttpStatus.CREATED);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
