package com.lognsys.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BaseController {

	@RequestMapping(value = "/login")
	public String showLogin(Model model, HttpServletRequest request) {
		return "login";
	}

	@RequestMapping(value = "/")
	public String redirectLogin(Model model, HttpServletRequest request) {
		return "redirect:/login";

	}

	@RequestMapping(value = "/dashboard")
	public String showDashboard(Model model, HttpServletRequest request) {
		System.out.println("Going in Dashbaord controller");
		return "dashboard";

	}

	
}
