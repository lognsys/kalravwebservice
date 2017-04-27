package com.lognsys.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.lognsys.dao.dto.UsersDTO;

@Controller
public class DramaController {

	@RequestMapping(value = "/dramalist", method = RequestMethod.GET)
	public String showDramas(Model model, HttpServletRequest request) {
		/*List<UsersDTO> listOfUsers = userService.getUsers();
		model.addAttribute("listOfUsers", listOfUsers);*/
		return "dramalist";
	}
}
