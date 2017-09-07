package com.lognsys.web.controller;

/**
 * Description : This is Base controller which serves the handling of 
 * login, users request and responds to appropriate view.
 * 
 * NOTE : No global variable should be defined!!!
 * 
 * Default: Spring mvc Controller is Singleton Class. 
 * 
 * @author pdoshi
 * 
 */

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.lognsys.dao.dto.DeviceDTO;
import com.lognsys.dao.dto.DramasDTO;
import com.lognsys.dao.dto.GroupsDTO;
import com.lognsys.dao.dto.RolesDTO;
import com.lognsys.dao.dto.UsersDTO;
import com.lognsys.model.Notifications;
import com.lognsys.model.Users;
import com.lognsys.service.DramaService;
import com.lognsys.service.NotificationService;
import com.lognsys.service.UserService;
import com.lognsys.util.FormValidator;
import com.lognsys.util.PushNotificationHelper;
import com.mongodb.util.JSON;

//TODO Logging required for base controller
@Controller
public class NotificationController {
	@Autowired
	private NotificationService  notificationService;
	/**
	 * 
	 * @param model
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/notificationlist", method = RequestMethod.GET)
	public String showUsers(Model model, HttpServletRequest request) throws IOException {
		notificationService.refreshNotificationList();
		
		return "notificationlist";
	}

	/**
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/sendnotification", method = RequestMethod.GET)
	public String showRegister(Model model, HttpServletRequest request) {
		// CALL database to get dramas & users
		
	List<DramasDTO> listOfDramasDTO = notificationService.getDramas();
	List<UsersDTO> listOfUsersDTO = notificationService.getUsers();
					
	// Adding data to list from DramasDTO & UsersDTO
	Hashtable<Integer, String> dramasList=new Hashtable<>();
	for (DramasDTO dramasDTO : listOfDramasDTO) {
		dramasList.put(dramasDTO.getId(), dramasDTO.getTitle());
	}	

		Hashtable<Integer, String> usersList=new Hashtable<>();
		for (UsersDTO usersDTO : listOfUsersDTO) {
			usersList.put(usersDTO.getId(), usersDTO.getRealname());
		}
		Notifications notifications = new Notifications();
		model.addAttribute("notifications", notifications);
		model.addAttribute("dramasList", dramasList);
		model.addAttribute("usersList", usersList);

		return "sendnotification";
	}

	/**
	 * 
	 * @param user
	 * @param result
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/sendnotification", method = RequestMethod.POST)
	public String saveForm(@ModelAttribute("notifications") Notifications notification, BindingResult result, ModelMap model) throws Exception {
		
		FormValidator formValidator = new FormValidator();
		formValidator.validate(notification, result);

		if (result!=null && result.hasErrors()) {
			System.out.println("Adding Errors - " + notification.toString());
			
			// CALL database to get dramas & users
			
			List<DramasDTO> listOfDramasDTO = notificationService.getDramas();
			List<UsersDTO> listOfUsersDTO = notificationService.getUsers();
			
			// Adding data to list from DramasDTO & UsersDTO
			Hashtable<Integer, String> dramasList=new Hashtable<>();
			for (DramasDTO dramasDTO : listOfDramasDTO) {
				dramasList.put(dramasDTO.getId(), dramasDTO.getTitle());
			}	

			
//			List<String> usersList = new ArrayList<String>();
			Hashtable<Integer, String> usersList=new Hashtable<>();
			for (UsersDTO usersDTO : listOfUsersDTO) {
				usersList.put(usersDTO.getId(), usersDTO.getRealname());
			}
			
			model.addAttribute("usersList", usersList);
			model.addAttribute("dramasList", dramasList);
			return "sendnotification";
		} else {
			try {
				if(notification.getUserId()!=0){
					notification.setRealname(notificationService.getUserRealnameById(notification.getUserId()));
				}
				else{
					notification.setRealname("");
				}
				if(notification.getDramaId()!=0){
						
					notification.setDramaTitle(notificationService.getDramaTitleById(notification.getDramaId()));
//					System.out.println("Adding notification getDramaTitle   " + notification.getDramaTitle());
						
				}
				else{
					notification.setDramaTitle("");
				}
//				System.out.println("Adding notification -toString  " + notification.toString());
				
				notificationService.addNotification(notification);

				String resultNotify=null;
				try {
					List<DeviceDTO> listsdeviceToken=notificationService.getDeviceToken();
				
					if(notification.getUserId()!=0){
						Users users =notificationService.getUserDetailById(notification.getUserId());
						
						for(DeviceDTO devicedto : listsdeviceToken){
							System.out.println(" DEVICE ====   "+(users.getDevice() !=null && users.getDevice().equalsIgnoreCase(devicedto.getDeviceToken())));
							
							if(users.getDevice() !=null && users.getDevice().equalsIgnoreCase(devicedto.getDeviceToken()) && notification.getUserId()==users.getId()){
								resultNotify=PushNotificationHelper.sendPushNotification(users.getDevice() ,notification);
							}
							else{
								resultNotify=PushNotificationHelper.sendPushNotification(devicedto.getDeviceToken() ,notification);
							}
						}
					}
					else{
						for(DeviceDTO devicedto : listsdeviceToken){
							System.out.println("\n DEVICE NO USER AND DRAMA====   "+devicedto.getDeviceToken());
							
							if(devicedto.getDeviceToken()!=null){
								resultNotify=PushNotificationHelper.sendPushNotification(devicedto.getDeviceToken() ,notification);
							}
							
						}
							
					}
					
						
				} catch (IOException e) {
					System.out.println(" sendNotification IOException "+e);
					
					e.printStackTrace();
				}
	
				return "notificationlist";

			 
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println(" IOException - " + e);
				
			}
		}
		return "notificationlist";
	}
	
	/**
	 * 
	 * @param model
	 * @param userIds
	 * @param userAction
	 * @return
	 */
	@RequestMapping(value = "/notificationlist", method = RequestMethod.POST)
	public String manageNotification(Model model, @RequestParam(value = "notificationIds", required = false) String notificationIds,
			@RequestParam String notifyAction) {
		System.out.println("#delete notifyAction - " +notifyAction);
		
		switch (notifyAction) {

		case "delete":
			JSONParser parser = new JSONParser();
			
			try {
				System.out.println("#manageNotification notificationIds - " +notificationIds.toString());
					Object obj = parser.parse(notificationIds);
				
				JSONArray arr = (JSONArray) obj;
				
				Integer[] notifyIDs = new Integer[arr.size()];
				if(arr.size()>0){
				for (int i = 0; i < arr.size(); i++) {
					JSONObject jsonObject = (JSONObject) arr.get(i);
					
					int id = Integer.parseInt(jsonObject.get("id").toString());
					System.out.println("manageNotification arr id "+id);
					
					notifyIDs[i] = id;
					System.out.println("manageNotification arr notifyIDs[i] "+notifyIDs[i]);
					
				}
				System.out.println("manageNotification arr notifyIDs length "+notifyIDs.length);
				
				notificationService.deleteNotification(notifyIDs);
				}	

			}  catch (Exception e) {
				System.out.println("#manageNotification IOException - " +e);
			}
			return "notificationlist";

		}

		return "dashboard";

	}
}
