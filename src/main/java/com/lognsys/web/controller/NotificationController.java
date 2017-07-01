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
import com.lognsys.dao.dto.GroupsDTO;
import com.lognsys.dao.dto.RolesDTO;
import com.lognsys.model.Notifications;
import com.lognsys.model.Users;
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

		/*// CALL database to get roles & groups
		List<RolesDTO> listOfRolesDTO = userService.getAllRoles();
		List<GroupsDTO> listOfGroupsDTO = userService.getAllGroups();

		// Adding data to list from RolesDTO
		List<String> rolesList = new ArrayList<String>();
		for (RolesDTO role : listOfRolesDTO) {
			rolesList.add(role.getRole());
		}

		// Adding data to list from GroupsDTO
		List<String> groupsList = new ArrayList<String>();
		for (GroupsDTO group : listOfGroupsDTO) {
			groupsList.add(group.getGroup_name());
		}*/

		Notifications notifications = new Notifications();
		model.addAttribute("notifications", notifications);
//		model.addAttribute("rolesList", rolesList);
//		model.addAttribute("groupsList", groupsList);

		return "sendnotification";
	}

	/**
	 * 
	 * @param user
	 * @param result
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/sendnotification", method = RequestMethod.POST)
	public String saveForm(@ModelAttribute("notifications") Notifications notification, BindingResult result, ModelMap model) {
		System.out.println("Adding notification - " + notification.toString());

		FormValidator formValidator = new FormValidator();
		formValidator.validate(notification, result);

		if (result.hasErrors()) {
			System.out.println("Adding Errors - " + notification.toString());
			
//			// CALL database to get roles
//			List<RolesDTO> listOfRolesDTO = userService.getAllRoles();
//			// Adding data to list from RolesDTO
//			List<String> rolesList = new ArrayList<String>();
//			for (RolesDTO role : listOfRolesDTO) {
//				rolesList.add(role.getRole());
//			}

//			// Adding data to list from GroupsDTO
//			// CALL database to get groups
//			List<GroupsDTO> listOfGroupsDTO = userService.getAllGroups();
//			List<String> groupsList = new ArrayList<String>();
//			for (GroupsDTO group : listOfGroupsDTO) {
//				groupsList.add(group.getGroup_name());
//			}
//
//			model.addAttribute("rolesList", rolesList);
//			model.addAttribute("groupsList", groupsList);
			return "sendnotification";
		} else {
			try {
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
	public String manageUser(Model model, @RequestParam(value = "userIds", required = false) String userIds,
			@RequestParam String userAction) {

		switch (userAction) {

		case "delete":
			JSONParser parser = new JSONParser();
			try {

				Object obj = parser.parse(userIds);

				JSONArray arr = (JSONArray) obj;

				String[] messages = new String[arr.size()];
				for (int i = 0; i < arr.size(); i++) {

					JSONObject jsonObject = (JSONObject) arr.get(i);
					messages[i] = jsonObject.get("message").toString();

				}

				notificationService.deleteNotification(messages);

			} catch (ParseException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return "notificationlist";

		case "edit":

			JSONParser p = new JSONParser();
			try {
				Object obj = p.parse(userIds);
				JSONArray arr = (JSONArray) obj;
				String message = "";
				for (int i = 0; i < arr.size(); i++) {

					JSONObject jsonObject = (JSONObject) arr.get(i);
					message = jsonObject.get("message").toString();
				}
				Notifications notifications = notificationService.getNotificationByMessage(message);

				/*Users users = userService.getUserWithRoleAndGroup(Integer.parseInt(id));

				// CALL database to get roles & groups
				List<RolesDTO> listOfRolesDTO = userService.getAllRoles();
				List<GroupsDTO> listOfGroupsDTO = userService.getAllGroups();

				// Adding data to list from RolesDTO
				List<String> rolesList = new ArrayList<String>();
				for (RolesDTO role : listOfRolesDTO) {
					rolesList.add(role.getRole());
				}

				// Adding data to list from GroupsDTO
				List<String> groupsList = new ArrayList<String>();
				for (GroupsDTO group : listOfGroupsDTO) {
					groupsList.add(group.getGroup_name());
				}

				model.addAttribute("rolesList", rolesList);
				model.addAttribute("groupsList", groupsList);*/

				model.addAttribute("notifications", notifications);
				
				System.out.println("Edit notifications - "+notifications);

				return "sendnotification";
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return "notificationlist";

		}

		return "dashboard";

	}
}
