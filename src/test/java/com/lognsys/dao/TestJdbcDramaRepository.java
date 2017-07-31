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

import com.lognsys.dao.dto.DramasDTO;
import com.lognsys.dao.dto.DramasGroupsDTO;
import com.lognsys.dao.jdbc.JdbcDramaRepository;
import com.lognsys.dao.jdbc.JdbcGroupRepository;
import com.lognsys.model.Drama;
import com.lognsys.util.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:datasource-context.xml" })
public class TestJdbcDramaRepository  {

	@Autowired
	private JdbcDramaRepository jdbcDramaRepository;
	@Autowired
	private JdbcGroupRepository jdbcGroupRepository;

	
	@Test
	public void addDrama() {
//		id, title, genre, star_cast, director, writer, description, auditorium_id, date, avg_rating, imageurl, drama_length, music, drama_language, last_edit
		Drama dramas=new Drama(9,"History of India","Historic", "Priyank Doshi","Priyank Doshi", "Priyank Doshi", "description", "2017-08-08","http://www.gmail.com","2 hour", "Asian music", "5","Hindi, English");
		DramasDTO dramasDTO = ObjectMapper.mapToDramasDTO(dramas);
		System.out.println("DramaService addDrama  dramasDTO "+dramasDTO);
		int dramaID =jdbcDramaRepository.addDrama(dramasDTO);
		Assert.notNull(dramasDTO, "Check list of dramasDTO NOT NULL");
			
	}
	@Test
	public void deleteDramasById() {
				boolean isDelete = jdbcDramaRepository.deleteDramaBy(7);
				System.out.println("manageDrama deleteDramas isDelete "+isDelete);
				Assert.isTrue(isDelete, "Check list of deleteDramasById ");
	}
	/*
	*//**
	 * Delete drama from database
	 * @param String title
	 * @return
	 */
	@Test
	public void deleteDramasByTitle() {
		
				String title="History of India";
				System.out.println("title "+title);
				boolean isDelete = jdbcDramaRepository.deleteDramaBy(title);
				Assert.isTrue(isDelete, "Check list of deleteDramasByTitle ");
	}
	
	@Test
	public void findByDrama() {

		DramasDTO dramasDTO= jdbcDramaRepository.findDramaById(6);
		Assert.notNull(dramasDTO, "Check list of dramasDTO NOT NULL");	
	}
	@Test
	public void getAllDramasAndGroup() {
		
			List<DramasGroupsDTO> list= jdbcGroupRepository.getAllDramasAndGroup();
			Assert.notNull(list, "Check list of DramasGroupsDTO NOT NULL");

			Assert.notEmpty(list, "Collection not empty..list of DramasGroupsDTO  object");
			
		}
}

