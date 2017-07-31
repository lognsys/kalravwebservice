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
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import com.lognsys.dao.DeviceRespository;
import com.lognsys.dao.DramaRespository;
import com.lognsys.dao.dto.DeviceDTO;
import com.lognsys.dao.dto.DramasDTO;
import com.lognsys.dao.dto.NotificationsDTO;
import com.lognsys.dao.jdbc.JdbcDeviceRepository;
import com.lognsys.dao.jdbc.rowmapper.DramaUserIDRowMapper;
import com.lognsys.model.Device;
import com.lognsys.util.Constants;
import com.lognsys.util.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:datasource-context.xml" })
public class TestJdbcDeviceRepository  {

	@Autowired
	private JdbcDeviceRepository jdbcDeviceRepository;
	public void setUp() {

	}

	@Test
	public void addDevice()  {
		DeviceDTO device=new DeviceDTO();
		device.setUsers_id(40);
		device.setDeviceToken("xdxgfdjgvgftfhojpomkopjerwredtrdchjkiloih");
		boolean isTrue=false;
		
		isTrue=jdbcDeviceRepository.isExists(40);
		if(!isTrue)
		{jdbcDeviceRepository.addDevice(device);
		}Assert.notNull(device, "device Null");
	}

	@Test
	public void getAllDeviceDTO(){
		List<DeviceDTO> listDeviceDTO = jdbcDeviceRepository.getAllDeviceDTO();
		Assert.notNull(listDeviceDTO, "Check list of DeviceDTO NOT NULL");
	}

	@Test
	public void deleteDeviceByUserId() {
		boolean isDelete=jdbcDeviceRepository.deleteDeviceByUserId(40);
		Assert.isTrue(isDelete, "isDelete deleteDeviceBy - " + isDelete );
		
	}

	@Test
	public void findDeviceById()  {
		DeviceDTO deviceDTO= jdbcDeviceRepository.findDeviceByUsersId(40);
		Assert.notNull(deviceDTO, "deviceDTO findDeviceById ");
		
	}



	

	/**
	 * Add users object into database
	 * 
	 * @param users
	 */


}