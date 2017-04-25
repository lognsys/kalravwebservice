package com.lognsys.web.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.lognsys.dao.dto.UsersDTO;
import com.lognsys.model.Users;
import com.lognsys.service.UserService;
import com.lognsys.util.CommonUtilities;
import com.lognsys.util.FormValidator;

@Controller
public class BaseController {

	@Autowired
	private UserService userService;

	/**
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public String showLogin(Model model, HttpServletRequest request) {
		return "login";
	}

	/**
	 * 
	 * @param error
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(@RequestParam(value = "error", required = false) String error) {

		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", "Invalid username or password!");
			model.setViewName("login");
		} else {

			model.setViewName("dashboard");

			return model;
		}

		return model;

	}

	/**
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/userlist", method = RequestMethod.GET)
	public String showUsers(Model model, HttpServletRequest request) {
		List<UsersDTO> listOfUsers = userService.getUsers();
		model.addAttribute("listOfUsers", listOfUsers);
		return "userlist";
	}

	/**
	 * 
	 * @param userAction
	 * @param checkboxvalues
	 * @return
//	 */
//	@RequestMapping(value = "/userlist", params = "userAction", method = RequestMethod.POST)
//	public String manageUser(Model model, @RequestParam("userAction") String userAction,
//			@RequestParam(value = "checkboxname", required = false) String[] checkboxvalues) {
//
//		try {
//
//			switch (userAction) {
//
//			case "Add":
//
//				model.addAttribute("users", new Users());
//				return "register";
//
//			case "Delete":
//
//				if (null != checkboxvalues)
//					userService.deleteUsers(CommonUtilities.parseIntArray(checkboxvalues));
//
//				List<UsersDTO> listOfUsers = userService.getUsers();
//				model.addAttribute("listOfUsers", listOfUsers);
//				return "userlist";
//
//			case "Edit":
//
//				if (null != checkboxvalues) {
//					// Assumption User can select only 1 item from user list in
//					// UI.
//					int id = Integer.parseInt(checkboxvalues[0]);
//
//					UsersDTO users = userService.findByUser(id);
//					model.addAttribute("users", users);
//				}
//				return "register";
//
//			case "Cancel":
//				return "dashboard";
//
//			default:
//
//				return "dashboard";
//
//			}
//
//		} catch (Exception e) {
//			System.out.println("manage user Exception " + e);
//
//		}
//		return "dashboard";
//	}
	/**
	 * 
	 * @param userAction
	 * @param checkboxvalues
	 * @return
	 */
	@RequestMapping(value = "/userlist",  method = RequestMethod.POST)
	public String manageUser(Model model, 
			@RequestParam String[] userIds, @RequestParam String userAction) {
		
		try {
			switch (userAction) {
			
			}
		}catch (Exception e ) {
			
		}


		
		
		//System.out.println(userAction.toString());
//		try {
//			
//			switch (userAction) {
//			
//			case "Add":
//				
//				model.addAttribute("users", new Users());
//				return "register";
//				
//			case "Delete":
//				
//				if (null != checkboxvalues)
//					userService.deleteUsers(CommonUtilities.parseIntArray(checkboxvalues));
//				
//				List<UsersDTO> listOfUsers = userService.getUsers();
//				model.addAttribute("listOfUsers", listOfUsers);
//				return "userlist";
//				
//			case "Edit":
//				
//				if (null != checkboxvalues) {
//					// Assumption User can select only 1 item from user list in
//					// UI.
//					int id = Integer.parseInt(checkboxvalues[0]);
//					
//					UsersDTO users = userService.findByUser(id);
//					model.addAttribute("users", users);
//				}
//				return "register";
//				
//			case "Cancel":
//				return "dashboard";
//				
//			default:
//				
//				return "dashboard";
//				
//			}
			
//		} catch (Exception e) {
//			System.out.println("manage user Exception " + e);
//			
//		}
		return "dashboard";
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String showRegister(Model model, HttpServletRequest request) {
		System.out.println("Going in Registration controller");
		Users user = new Users();
		model.addAttribute("users", user);
		return "register";
	}

	/**
	 * 
	 * @param user
	 * @param result
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String saveForm(@ModelAttribute("users") Users user, BindingResult result, ModelMap model) {

		FormValidator formValidator = new FormValidator();

		formValidator.validate(user, result);

		if (result.hasErrors()) {
			return "register";
		} else {

			userService.addUser(user);
		}

		return "register";
	}
}
