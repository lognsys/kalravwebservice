package com.lognsys.dao.jdbc;

import java.util.List;
import java.util.Properties;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.lognsys.dao.DeviceRespository;
import com.lognsys.dao.DramaRespository;
import com.lognsys.dao.dto.DeviceDTO;
import com.lognsys.dao.dto.DramasDTO;
import com.lognsys.dao.dto.NotificationsDTO;
import com.lognsys.dao.jdbc.rowmapper.DeviceByIDRowMapper;
import com.lognsys.dao.jdbc.rowmapper.DramaUserIDRowMapper;
import com.lognsys.model.Device;
import com.lognsys.util.Constants;

@Repository("deviceRepository")
public class JdbcDeviceRepository implements DeviceRespository {

	@Autowired
	private NamedParameterJdbcTemplate namedParamJdbcTemplate;

	/**
	 * Injecting resource sql.properties.
	 */
	@Resource(name = "sqlProperties")
	private Properties sqlProperties;

	@Override
	public int addDevice(DeviceDTO deviceDTO)  throws DataAccessException{
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(deviceDTO);
		final KeyHolder keyHolder = new GeneratedKeyHolder();
		namedParamJdbcTemplate.update(sqlProperties.getProperty(Constants.DEVICE_QUERIES.insert_user_devices.name()),
				params, keyHolder);
		return keyHolder.getKey().intValue();
	}

	@Override
	public boolean isExists(String deviceToken)  throws DataAccessException{
		System.out.println(" isExists deviceToken==================================== "+deviceToken);
		
		SqlParameterSource param = new MapSqlParameterSource("deviceToken", deviceToken);
		return namedParamJdbcTemplate.queryForObject(
				sqlProperties.getProperty(Constants.DEVICE_QUERIES.select_devices_exists.name()), param, Integer.class) > 0;
	}

	@Override
	public boolean updateDevice(DeviceDTO deviceDTO)  throws DataAccessException{
		boolean isUpdate = false;
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(deviceDTO);
		isUpdate = namedParamJdbcTemplate.update(sqlProperties.getProperty(Constants.DEVICE_QUERIES.update_devices.name()),
				params) == 1;
	
		return isUpdate;
		
	}

	@Override
	public List<DeviceDTO> getAllDeviceDTO()  throws DataAccessException{
		List<DeviceDTO> listDeviceDTO = namedParamJdbcTemplate.query(
				sqlProperties.getProperty(Constants.DEVICE_QUERIES.select_all_devices.name()),
				new BeanPropertyRowMapper<DeviceDTO>(DeviceDTO.class));
		System.out.println("JdbcNotificationsRepository-Service listDeviceDTO : " +listDeviceDTO);
		System.out.println("JdbcNotificationsRepository-Service listDeviceDTO size : " +listDeviceDTO.size());
		
		return listDeviceDTO;
	}

	@Override
	public boolean deleteDeviceByUserId(Integer users_id)  throws DataAccessException{
		SqlParameterSource parameter = new MapSqlParameterSource("users_id", Integer.valueOf(users_id));
		return namedParamJdbcTemplate.update(sqlProperties.getProperty(Constants.DEVICE_QUERIES.delete_device.name()),
				parameter) == 1;
	}

	@Override
	public DeviceDTO findDeviceByUsersId(Integer users_id)  throws DataAccessException{
		System.out.println("DeviceDTO findDeviceById  users_id "+users_id);

		SqlParameterSource parameter = new MapSqlParameterSource("users_id", Integer.valueOf(users_id));
		return namedParamJdbcTemplate.queryForObject(sqlProperties.getProperty(Constants.DEVICE_QUERIES.select_device_by_id.name()),
				parameter, new DeviceByIDRowMapper());
	
	}

	@Override
	public boolean isExists(Integer users_id) {

		SqlParameterSource param = new MapSqlParameterSource("users_id", users_id);
		return namedParamJdbcTemplate.queryForObject(
				sqlProperties.getProperty(Constants.DEVICE_QUERIES.select_devices_exists.name()), param,
				Integer.class) > 0;
	}



}