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
	public void addRating(Ratings rating) {
		int drama_id =rating.getDramas_id();
		System.out.println("DramaService addRating  drama_id "+drama_id);
		
		RatingsDTO ratingsDTO = ObjectMapper.mapToRatingsDTO(rating);
		System.out.println("DramaService addRating  ratingsDTO "+ratingsDTO);
		
		try {
			boolean isExists = jdbcRatingsRepository.isExists(ratingsDTO);
			System.out.println("DramaService addRating  isExists "+isExists);
			
			if (isExists) {
				LOG.info("Found Rating in database with dramaTitle - " + drama_id);

			} else {
				jdbcRatingsRepository.addRating(ratingsDTO);
				System.out.println("DramaService addRating  added ");
				
			}
		} catch (DataAccessException dae) {
			System.out.println("DramaService addRating  DataAccessException "+dae);
			
			throw new IllegalStateException("Error : Failed to add rating!");
		}
	}
	
	public int updateRating(RatingsDTO ratingsDTO) {
		
		return jdbcRatingsRepository.updateRating(ratingsDTO);

	}
}
