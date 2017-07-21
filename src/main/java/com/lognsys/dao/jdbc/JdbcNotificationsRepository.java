package com.lognsys.dao.jdbc;

import java.util.List;
import java.util.Properties;
import javax.annotation.Resource;
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
import com.lognsys.dao.NotificationRespository;
import com.lognsys.dao.dto.DramasDTO;
import com.lognsys.dao.dto.NotificationsDTO;
import com.lognsys.dao.dto.UsersDTO;
import com.lognsys.dao.jdbc.rowmapper.NotificationByIDRowMapper;
import com.lognsys.dao.jdbc.rowmapper.UserByUserIDRowMapper;
import com.lognsys.util.Constants;

@Repository("notificationsRepository")
public class JdbcNotificationsRepository implements NotificationRespository {

	@Autowired
	private NamedParameterJdbcTemplate namedParamJdbcTemplate;

	/**
	 * Injecting resource sql.properties.
	 */
	@Resource(name = "sqlProperties")
	private Properties sqlProperties;

	
	@Override
	public int addNotifications(NotificationsDTO notificationdto) {
		int notify_id = 0;
		System.out.println("#addNotification - " + "addNotification notificationdto toString : - " + notificationdto.toString());

		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(notificationdto);
		final KeyHolder keyHolder = new GeneratedKeyHolder();
		namedParamJdbcTemplate.update(sqlProperties.getProperty(Constants.NOTIFICATION_QUERIES.insert_notification.name()),
				params, keyHolder);
		notify_id= keyHolder.getKey().intValue();
		return notify_id;
		
	}

	@Override
	public List<NotificationsDTO> getAllNotifications() {
		List<NotificationsDTO> listnotifications = namedParamJdbcTemplate.query(
				sqlProperties.getProperty(Constants.NOTIFICATION_QUERIES.select_notification.name()),
				new BeanPropertyRowMapper<NotificationsDTO>(NotificationsDTO.class));
		System.out.println("JdbcNotificationsRepository-Service listnotifications : " +listnotifications);
		System.out.println("JdbcNotificationsRepository-Service listnotifications size : " +listnotifications.size());
		
		return listnotifications;
	}

	@Override
	public boolean deleteNotificationsBy(String message) {
		SqlParameterSource parameter = new MapSqlParameterSource("message",message);
		return namedParamJdbcTemplate.update(sqlProperties.getProperty(Constants.NOTIFICATION_QUERIES.delete_notification.name()),
				parameter) == 1;
	}

	@Override
	public NotificationsDTO findUserByMessage(String message) throws DataAccessException {

		SqlParameterSource parameter = new MapSqlParameterSource("message", message);

		NotificationsDTO notificationsDTO = namedParamJdbcTemplate.queryForObject(
				sqlProperties.getProperty(Constants.NOTIFICATION_QUERIES.select_notification_message.name()), parameter,
				new NotificationByIDRowMapper());

		if (notificationsDTO == null)
			throw new EmptyResultDataAccessException(1);

		return notificationsDTO;

	}

	@Override
	public boolean deleteNotificationsById(int id) {
		SqlParameterSource parameter = new MapSqlParameterSource("id",id);
		return namedParamJdbcTemplate.update(sqlProperties.getProperty(Constants.NOTIFICATION_QUERIES.delete_notification_by_id.name()),
				parameter) == 1;
	}
}