package com.lognsys.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BaseController {

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
		}

		// if (logout != null) {
		// model.addObject("msg", "You've been logged out successfully.");
		// }

		return model;

	}

	@RequestMapping(value = "/dashboard")
	public String showDashboard(Model model, HttpServletRequest request) {
		System.out.println("Going in Dashbaord controller");
		return "dashboard";
	}

	@RequestMapping(value = "/register")
	public String showRegister(Model model, HttpServletRequest request) {
		System.out.println("Going in Registration controller");
		return "register";
	}

}
