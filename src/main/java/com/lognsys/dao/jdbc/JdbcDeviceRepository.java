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
import com.lognsys.dao.jdbc.rowmapper.DramaUserIDRowMapper;
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
		namedParamJdbcTemplate.update(sqlProperties.getProperty(Constants.DEVICE_QUERIES.insert_devices.name()),
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteDeviceBy(Integer id)  throws DataAccessException{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public DeviceDTO findDeviceById(Integer id)  throws DataAccessException{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteDeviceBy(String deviceToken)  throws DataAccessException{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public DeviceDTO findDeviceById(String deviceToken) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Add users object into database
	 * 
	 * @param users
	 */


}