package com.lognsys.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lognsys.dao.dto.DeviceDTO;
import com.lognsys.dao.dto.DramasDTO;
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
	
	@Transactional
	public void addDevice(Device device) {
		String deviceToken = device.getDeviceToken();
		System.out.println("DeviceService addDdevice  dramaTitle "+deviceToken);
		DeviceDTO deviceDTO = ObjectMapper.mapToDeviceDTO(device);
		System.out.println("DeviceService addDdevice  deviceDTO "+deviceDTO);
		
		try {
			boolean isExists = jdbcDeviceRepository.isExists(deviceDTO.getDeviceToken());
			System.out.println("DeviceService addDdevice  isExists "+isExists);
			
			if (isExists) {
				LOG.info("Found deviceToken in database with deviceToken - " + deviceToken);
				deviceDTO =jdbcDeviceRepository.findDeviceById(deviceToken);
				jdbcDeviceRepository.updateDevice(deviceDTO);
			
			} else {
				int deviceId =jdbcDeviceRepository.addDevice(deviceDTO);
				System.out.println("#DeviceService - " + "Adding deviceDTO in database with deviceId - " + deviceId);
				
			}
		} catch (DataAccessException dae) {
			System.out.println("DeviceService addDevice  Exception "+dae);
			
			throw new IllegalStateException("Error : Failed to add deviceToken!");
		}
	}
}
