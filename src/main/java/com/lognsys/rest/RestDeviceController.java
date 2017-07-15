package com.lognsys.rest;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import javax.ws.rs.Produces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lognsys.dao.dto.DramasDTO;
import com.lognsys.dao.dto.UsersDTO;
import com.lognsys.dao.jdbc.JdbcDeviceRepository;
import com.lognsys.dao.jdbc.JdbcGroupRepository;
import com.lognsys.dao.jdbc.JdbcUserRepository;
import com.lognsys.exception.UserDataAccessException;
import com.lognsys.model.Device;
import com.lognsys.model.Notifications;
import com.lognsys.model.Users;
import com.lognsys.model.UsersTable;
import com.lognsys.service.DeviceService;
import com.lognsys.service.DramaService;
import com.lognsys.service.NotificationService;
import com.lognsys.service.UserService;
import com.lognsys.util.Constants;
import com.lognsys.util.ObjectMapper;
import com.lognsys.util.PushNotificationHelper;

@Produces("application/json")
@RestController
public class RestDeviceController {

	@Autowired
	UserService userService;


	// Injecting resource application.properties.
	@Autowired
	@Qualifier("applicationProperties")
	private Properties applicationProperties;
	@Autowired
	private DeviceService deviceService;

	@Autowired
	private JdbcDeviceRepository jdbcDeviceRepository;

	// getDevice token 
	@RequestMapping(value = "/getDeviceToken", method = { RequestMethod.POST })
	public ResponseEntity<?> getDeviceToken(@RequestBody Device deviceToken) throws IOException {
			Device device=new Device();
			
			System.out.println("getDeviceToken =============== "+deviceToken);
			
			if(deviceToken!=null){
				boolean isExists = jdbcDeviceRepository.isExists(deviceToken.getDeviceToken());
				System.out.println("getDeviceToken isExists =============== "+isExists);
				if (isExists) {
					String str = "All Ready Exist";
					return new ResponseEntity<String>(str, HttpStatus.FOUND);
				}else {
					int id = deviceService.addDevice(deviceToken);
					deviceToken.setId(id);
				}
			}
					return new ResponseEntity<Device>(deviceToken, HttpStatus.CREATED);
			}
		
}