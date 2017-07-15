package com.lognsys.service;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lognsys.dao.dto.DeviceDTO;
import com.lognsys.dao.dto.DramasDTO;
import com.lognsys.dao.dto.UsersDTO;
import com.lognsys.dao.jdbc.JdbcDeviceRepository;
import com.lognsys.dao.jdbc.JdbcDramaRepository;
import com.lognsys.model.Device;
import com.lognsys.model.Drama;
import com.lognsys.util.ObjectMapper;


@Service("deviceService")
public class DeviceService {


	Logger LOG = Logger.getLogger(this.getClass());

	@Autowired
	private JdbcDeviceRepository jdbcDeviceRepository;
	
	@Transactional(rollbackFor = IllegalArgumentException.class)
	public int addDevice(Device device) {
		String deviceToken = device.getDeviceToken();
		
		
		System.out.println("DeviceService addDdevice  deviceToken "+deviceToken);
			// convert DeviceDTO -> Device Object
			DeviceDTO deviceDTO = ObjectMapper.mapToDeviceDTO(device);
			System.out.println("DeviceService addDdevice  deviceDTO toString ========================== "+deviceDTO.toString());
			
			boolean isExists = jdbcDeviceRepository.isExists(deviceToken);
			
			System.out.println("DeviceService addDdevice  isExists =================================== "+isExists);
			
			if (isExists) {
				throw new IllegalArgumentException("Device already exists in database with deviceToken - " + deviceToken);
			}
				int deviceId =jdbcDeviceRepository.addDevice(deviceDTO);
				System.out.println("#DeviceService - " + "Adding deviceDTO in database with deviceId ==========================- " + deviceId);
			return deviceId;	
			
		
	}
}
