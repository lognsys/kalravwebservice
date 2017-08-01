package com.lognsys.dao;

import java.util.List;
import java.util.Properties;
import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.lognsys.dao.NotificationRespository;
import com.lognsys.dao.dto.DramasDTO;
import com.lognsys.dao.dto.NotificationsDTO;
import com.lognsys.dao.dto.UsersDTO;
import com.lognsys.dao.jdbc.JdbcNotificationsRepository;
import com.lognsys.dao.jdbc.rowmapper.NotificationByIDRowMapper;
import com.lognsys.dao.jdbc.rowmapper.UserByUserIDRowMapper;
import com.lognsys.util.Constants;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:datasource-context.xml" })
public class TestJdbcNotificationsRepository {

	@Autowired
	private JdbcNotificationsRepository jdbcNotificationsRepository;


	public void setUp() {

	}
	
	@Test
    @Transactional
    @Rollback(true)
	public void addNotifications() {
		NotificationsDTO notificationdto =new NotificationsDTO();
//		id, notify, message, userId, dramaId, realname, dramaTitle, last_edit
		notificationdto.setNotify(true);
		notificationdto.setMessage("this World");
		notificationdto.setUserId(40);
		notificationdto.setDramaId(5);
		notificationdto.setRealname("Priyank Doshi");
		notificationdto.setDramaTitle("The  kites");
		jdbcNotificationsRepository.addNotifications(notificationdto);
		Assert.notNull(notificationdto, "notificationdto Null");
		
	}

	@Test
	public void getAllNotifications() {
		List<NotificationsDTO> listnotifications = jdbcNotificationsRepository.getAllNotifications();
		Assert.notNull(listnotifications, "Check list of NotificationsDTO NOT NULL");
		Assert.notEmpty(listnotifications, "Collection not empty..list of NotificationsDTO object");
	}

	@Test
    @Transactional
    @Rollback(true)
	public void deleteNotificationsBy() {
		String message="test";
		boolean isDelete=jdbcNotificationsRepository.deleteNotificationsBy(message);
		Assert.isTrue(isDelete, "isDelete booking - " + isDelete );
		
	}

	@Test
	public void findUserByMessage() {
		String message="hihih";
		NotificationsDTO notificationsDTO =jdbcNotificationsRepository.findUserByMessage(message);
		Assert.notNull(notificationsDTO, "notificationdto findUserByMessage ");
		
	}

	@Test
    @Transactional
    @Rollback(true)
	public void deleteNotificationsById() {
		boolean isDelete=jdbcNotificationsRepository.deleteNotificationsById(34);
		Assert.isTrue(isDelete, "isDelete deleteNotificationsById - " + isDelete );
	
	}
}