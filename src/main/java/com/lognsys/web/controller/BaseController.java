package com.lognsys.web.controller;

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
import com.lognsys.dao.dto.UsersDTO;
import com.lognsys.model.Users;
import com.lognsys.service.UserService;
import com.lognsys.util.FormValidator;
import com.lognsys.util.ObjectMapper;

//TODO Logging required for base controller
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
	 * @param error
	 * @return
	 */
	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public String dashboard(Model model, HttpServletRequest request) {

		return "dashboard";

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
	 * @param model
	 * @param userIds
	 * @param userAction
	 * @return
	 */
	@RequestMapping(value = "/userlist", method = RequestMethod.POST)
	public String manageUser(Model model, @RequestParam(value = "userIds", required = false) String userIds,
			@RequestParam String userAction) {

		switch (userAction) {

		case "delete":
			System.out.println("UserAction : "+userAction);
			JSONParser parser = new JSONParser();
			try {
				Object obj = parser.parse(userIds);

				JSONArray arr = (JSONArray) obj;
				String[] userIDs = new String[arr.size()];

				for (int i = 0; i < arr.size(); i++) {

					userIDs[i] = arr.get(i).toString();
				}

				 userService.deleteUsers(userIDs);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return "userlist";

		case "edit":

			JSONParser p = new JSONParser();
			try {
				Object obj = p.parse(userIds);
				JSONArray arr = (JSONArray) obj;
				String id = "";
				for (int i = 0; i < arr.size(); i++) {

					JSONObject jsonObject = (JSONObject)arr.get(i);
					id =jsonObject.get("id").toString();
				}
			
				UsersDTO usersDTO = userService.findByUser(Integer.parseInt(id));
				Users newUsers = ObjectMapper.mapToUsers(usersDTO);
				model.addAttribute("users", newUsers);
				return "register";
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return "userlist";

		}

		return "dashboard";

	}

	/**
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
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
