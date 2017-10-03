package com.lognsys.service;

import java.io.IOException;
import java.util.List;
import java.util.Properties;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lognsys.dao.dto.AuditoriumsDTO;
import com.lognsys.dao.dto.DramasDTO;
import com.lognsys.dao.dto.GroupsDTO;
import com.lognsys.dao.dto.RatingsDTO;
import com.lognsys.dao.dto.RolesDTO;
import com.lognsys.dao.dto.UsersDTO;
import com.lognsys.dao.jdbc.JdbcAuditoriumRepository;
import com.lognsys.dao.jdbc.JdbcDramaRepository;
import com.lognsys.dao.jdbc.JdbcGroupRepository;
import com.lognsys.dao.jdbc.JdbcRatingsRepository;
import com.lognsys.dao.jdbc.JdbcUserRepository;
import com.lognsys.model.Drama;
import com.lognsys.model.DramasTable;
import com.lognsys.model.Ratings;
import com.lognsys.model.Users;
import com.lognsys.model.UsersTable;
import com.lognsys.util.CommonUtilities;
import com.lognsys.util.Constants;
import com.lognsys.util.ObjectMapper;
import com.lognsys.util.WriteJSONToFile;

@Service("ratingService")
public class RatingService {

	Logger LOG = Logger.getLogger(this.getClass());

	@Autowired
	private JdbcRatingsRepository jdbcRatingsRepository;

	// Injecting resource sql.properties.

	
	/**
	 * Add rating to drama into Ratings table .. Check if rating already exists in db
	 * 
	 * @return
	 */
	public int addRating(RatingsDTO ratingsDTO) {
		int drama_id =ratingsDTO.getDramas_id();
		int ratingId=0;
		System.out.println("RatingService addRating  drama_id "+drama_id);
		
//		RatingsDTO ratingsDTO = ObjectMapper.mapToRatingsDTO(rating);

		System.out.println("RatingService addRating  exists(ratingsDTO) "+exists(ratingsDTO));
			if (exists(ratingsDTO)) {
				LOG.info("Found Rating in database with dramaTitle - " + drama_id);
			}
			ratingId=jdbcRatingsRepository.addRating(ratingsDTO);

			System.out.println("RatingService addRating  ratingId "+ratingId);
			return ratingId ;
			
	}
	
	public int updateRating(RatingsDTO ratingsDTO) {
		
		return jdbcRatingsRepository.updateRating(ratingsDTO);

	}	public boolean exists(RatingsDTO ratingsDTO) {
		
		return jdbcRatingsRepository.isExists(ratingsDTO);

	}
public List<RatingsDTO> getAllRatings() {
		
		return jdbcRatingsRepository.getAllRatings();

	}	
public RatingsDTO FindRatingByUserIdAndDramaId(RatingsDTO ratingsDTO) {
	
	return jdbcRatingsRepository.findRatingByUserIDAndDramaID(ratingsDTO.getUsers_id(),ratingsDTO.getDramas_id());

}	
}
