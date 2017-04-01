package com.lognsys.web.controller;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

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

import com.lognsys.dao.users.JdbcUserRepository;
import com.lognsys.model.Drama;
import com.lognsys.model.Users;
import com.lognsys.util.FormValidator;


@Controller
public class BaseController {
	
	@Autowired
	JdbcUserRepository userRep;

	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public String showLogin(Model model, HttpServletRequest request) {
		return "login";
	}

	// Spring Security see this :
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(@RequestParam(value = "error", required = false) String error) {

		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", "Invalid username and password!");
			model.setViewName("login");
		} else {
			
			model.setViewName("dashboard");
		
			return model;
		}

		// if (logout != null) {
		// model.addObject("msg", "You've been logged out successfully.");
		// }

		return model;

	}

	@RequestMapping(value = "/dashboard")
	public ModelAndView showDashboard(Model model1A, HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		model.setViewName("drama");
		System.out.println("Going in Dashbaord controller");
		if(userRep.getAllUsers()!=null && userRep.getAllUsers().size()>0){
			List<Users> listContact = userRep.getAllUsers();
		    model.addObject("listUsers", listContact);
		    model.setViewName("user_grid");
			return model;
				
		}
		return model;
		
	}
	

	@RequestMapping(value = "/user_grid", method = RequestMethod.GET)
	public ModelAndView showGridUser(Model model, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		System.out.println("Going in grid controller");
		Users user_grid = new Users();
		if(userRep.getAllUsers()!=null && userRep.getAllUsers().size()>0){
			List<Users> listContact = userRep.getAllUsers();
			modelAndView.addObject("listUsers", listContact);
			modelAndView.setViewName("user_grid");
			return modelAndView;
				
		}
		return modelAndView;
	}
	
	
	
	@RequestMapping(value = "/drama", method = RequestMethod.GET)
	public String showDrama(Model model, HttpServletRequest request) {
		System.out.println("Going in drama controller");
		Drama drama = new Drama();
		model.addAttribute("drama", drama);
		return "drama";
	}
	
	
	@RequestMapping(value = "/register" ,  method = RequestMethod.GET)
	public String showRegister(Model model, HttpServletRequest request) {
		System.out.println("Going in Registration controller");
		Users user = new Users();
		model.addAttribute("users", user);
		return "register";
	}

	// monika added for register submit click validation 30/03/17
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String saveForm(@ModelAttribute("users") Users user, BindingResult result, ModelMap model) {
		System.out.println("Going insaveForm ");
		
		FormValidator formValidator = new FormValidator();

		formValidator.validate(user, result);
	
		if (result.hasErrors()) {
			return "register";
		} else 
		{
			
			userRep.addUser(user);
		}
		System.out.println(user.getFirstname());
		return "register";
	}
}
