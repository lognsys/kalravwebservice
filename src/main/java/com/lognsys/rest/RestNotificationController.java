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
public class RestNotificationController {

	@Autowired
	UserService userService;


	// Injecting resource application.properties.
	@Autowired
	@Qualifier("applicationProperties")
	private Properties applicationProperties;
	@Autowired
	private DeviceService deviceService;

	@Autowired
	private NotificationService notificationService;

	// getDevice token 
		@PostMapping("/getDeviceToken/{deviceToken}")
		public ResponseEntity getDeviceToken(@PathVariable("deviceToken") String deviceToken) {
		System.out.println("RestNotificationController getDeviceToken deviceToken = "+deviceToken);
		
		Device device=new Device();
		device.setDeviceToken(deviceToken);
//		deviceToken="chSD5yuHt-0:APA91bGKikJYyet8PB21SjSWUC8vRe3_1p4XIF50pxed2upo6b20qi81PtnGfrs6xvAGXO7RKTerKrFHYthdlUN5AcGtWaihAUuEoDRNuJJXoEAD_L4aY_7jPkXoRYAz1Jw2w_-Rey0f";
		System.out.println("RestNotificationController getDeviceToken deviceToken  == = = == = == == "+deviceToken);
		
		deviceService.addDevice(new Device(0, deviceToken));
		System.out.println("RestNotificationController getDeviceToken deviceService added = ");
		
		return new ResponseEntity(deviceService, HttpStatus.OK);
		}
	
	/**
	 * 
	 * @return
	 */
	
	@PostMapping(value = "/notify/{notifications}")
	public ResponseEntity sendNotification(@RequestBody Notifications notifications) {
		System.out.println(" sendNotification  notifications === "+notifications.toString());
		String result=null;
		try {
			String deviceToken="etRZydjLhu8:APA91bGByxWhuKozRL1mnqqmIF783qNnOqeyRp3nj1or9PfspNj1o1ZnUEInH1Fz3lx3cwaddnBs0EpyPvkBTVmA-m3Tq4fHCSrnwH0-tjSebnoPOx_7xI2BCqgTQfpsxQysy6tG2FjI";
//			String deviceToken="eY2UboGuVsc:APA91bHPlohGb1QYBwByk1JKbquUoJ8aCxaWOCQZ19J-ZNiWSH3T0zZGJBpHLm-crjlJ1wuT46MQ54To9rB_XkjDnTP50NyfX6N9phsZKUTEZwDpqXm_oTLwHmb7ktGFWrnhy8X1VbMr";
			if(notifications.getId()!=0){
				Users users =notificationService.getUserDetailById(notifications.getId());
				if(users.getDevice() !=null && users.getDevice().equals(deviceToken)){
					result=PushNotificationHelper.sendPushNotification(users.getDevice(),notifications);
				}
					
			}
			else{
				result=PushNotificationHelper.sendPushNotification(deviceToken,notifications);
					
			}
			
			System.out.println(" sendNotification result   "+result);
				
		} catch (IOException e) {
			System.out.println(" sendNotification IOException "+e);
			
			e.printStackTrace();
		}
		
		return new ResponseEntity(result,HttpStatus.OK);
	}
}