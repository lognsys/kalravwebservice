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
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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
		System.out.println("Adding result - " + result.toString());

		FormValidator formValidator = new FormValidator();
		formValidator.validate(notification, result);

		if (result.hasErrors()) {
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
					System.out.println("Adding notification getRealnamee   " + notification.getRealnamee());
					System.out.println("Adding notification -toString  " + notification.toString());
						
				}
				else{
					notification.setRealname("-");
				}
				if(notification.getDramaId()!=0){
						
					notification.setDramaTitle(notificationService.getDramaTitleById(notification.getDramaId()));
					System.out.println("Adding notification getDramaTitle   " + notification.getDramaTitle());
						
				}
				else{
					notification.setDramaTitle("-");
				}
				System.out.println("Adding notification -toString  " + notification.toString());
				System.out.println("\notification 1 - Send Http POST request");
//				notificationService.sendPost(notification);
				notificationService.addNotification(notification);
				

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
	public String manageUser(Model model, @RequestParam(value = "notificationIds", required = false) String notificationIds,
			@RequestParam String notifyAction) {
		System.out.println("#delete notifyAction - " +notifyAction);
		
		switch (notifyAction) {

		case "delete":
			JSONParser parser = new JSONParser();
			try {
				System.out.println("#delete notificationIds - " +notificationIds);
				
				Object obj = parser.parse(notificationIds);

				JSONArray arr = (JSONArray) obj;
				System.out.println("#delete arr - " +arr);
				System.out.println("#delete arr.size() - " +arr.size());
				
				String[] messages = new String[arr.size()];
				for (int i = 0; i < arr.size(); i++) {

					JSONObject jsonObject = (JSONObject) arr.get(i);
					messages[i] = jsonObject.get("message").toString();
					System.out.println("#delete messages[i] - " +messages[i]);
					
				}

				notificationService.deleteNotification(messages);

			} catch (ParseException e) {
				System.out.println("#delete ParseException - " +e);
				
				
			} catch (IOException e) {
				System.out.println("#delete IOException - " +e);
			}
			return "notificationlist";

		case "edit":

			JSONParser p = new JSONParser();
			try {
				Object obj = p.parse(notificationIds);
				System.out.println("Edit notifications notificationIds - "+notificationIds);
				
				JSONArray arr = (JSONArray) obj;
				String message = "";
				for (int i = 0; i < arr.size(); i++) {

					JSONObject jsonObject = (JSONObject) arr.get(i);
					message = jsonObject.get("message").toString();
				}
				System.out.println("Edit notifications message - "+message);

				Notifications notifications = notificationService.getNotificationByMessage(message);
				System.out.println("Edit notifications - "+notifications);

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
					model.addAttribute("notifications", notifications);
					model.addAttribute("dramasList", dramasList);
					model.addAttribute("usersList", usersList);
				
			
				return "sendnotification";
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return "notificationlist";

		}

		return "dashboard";

	}
}
