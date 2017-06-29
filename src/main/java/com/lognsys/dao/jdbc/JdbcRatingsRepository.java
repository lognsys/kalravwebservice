package com.lognsys.dao.jdbc;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import com.lognsys.dao.DramaRespository;
import com.lognsys.dao.RatingsRespository;
import com.lognsys.dao.dto.DramasDTO;
import com.lognsys.dao.dto.RatingsDTO;
import com.lognsys.dao.jdbc.rowmapper.DramaUserIDRowMapper;
import com.lognsys.model.Ratings;
import com.lognsys.util.Constants;

@Repository("ratingRepository")
public class JdbcRatingsRepository implements RatingsRespository {

	@Autowired
	private NamedParameterJdbcTemplate namedParamJdbcTemplate;

	/**
	 * Injecting resource sql.properties.
	 */
	@Resource(name = "sqlProperties")
	private Properties sqlProperties;

	
	
	/**
	 * Returns boolean true if rating exists by users
	 * 
	 * @param users_id
	 * 
	 */
	@Override
	public boolean isExists(RatingsDTO ratingsDTO) {

		Map<String ,  Integer> param = new HashMap<String, Integer>();
		param.put("users_id",ratingsDTO.getUsers_id());
		param.put("dramas_id", ratingsDTO.getDramas_id());
		return namedParamJdbcTemplate.queryForObject(
				sqlProperties.getProperty(Constants.RATING_QUERIES.select_ratings_exists.name()), param, Integer.class) > 0;
	}
	/**
	 * Add rating object into database
	 * 
	 * @param users
	 */
	@Override
	public int addRating(RatingsDTO ratings) {
		// TODO Auto-generated method stub
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(ratings);
		final KeyHolder keyHolder = new GeneratedKeyHolder();
		namedParamJdbcTemplate.update(sqlProperties.getProperty(Constants.RATING_QUERIES.insert_ratings.name()),
				params, keyHolder);
		return keyHolder.getKey().intValue();
	}

	@Override
	public int updateRating(int id, RatingsDTO ratingsDTO) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<RatingsDTO> getAllRatings() {
		// TODO Auto-generated method stub
		return null;
	}
}
