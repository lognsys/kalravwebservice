package com.lognsys.web.controller;

import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.util.List;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.lognsys.dao.dto.DramasDTO;
import com.lognsys.dao.dto.UsersDTO;
import com.lognsys.model.Drama;
import com.lognsys.model.Users;
import com.lognsys.service.DramaService;
import com.lognsys.service.UserService;
import com.lognsys.util.FormValidator;

@WebServlet("/dramadetail")
@MultipartConfig(maxFileSize = 16177215) 
@Controller
public class DramaController {
	@Autowired
	private DramaService dramaService;

	@RequestMapping(value = "/dramalist", method = RequestMethod.GET)
	public String showDramas(Model model, HttpServletRequest request) {
		/*List<UsersDTO> listOfUsers = userService.getUsers();
		model.addAttribute("listOfUsers", listOfUsers);*/
		List<DramasDTO> listOfDramas = dramaService.getDramas();
		model.addAttribute("listOfDramas", listOfDramas);
		
		return "dramalist";
	}
	@RequestMapping(value = "/dramadetail", method = RequestMethod.GET)
	public String showDramaDetail(Model model, HttpServletRequest request) {
		System.out.println("Going in dramadetail controller");
		Drama drama = new Drama();
		model.addAttribute("drama", drama);
		return "dramadetail";
	}
	
	
	
	
	
	@RequestMapping(value = "/dramadetail", method = RequestMethod.POST)
	public String saveDramaDetail(@ModelAttribute("drama") Drama dramas,BindingResult result, ModelMap model) {
	
		FormValidator formValidator = new FormValidator();
		formValidator.validate(dramas, result);

		if (result.hasErrors()) {
			System.out.println("Adding dramas - " + dramas.toString());
				
			return "dramadetail";
		} else {

		System.out.println("Adding dramas - " + dramas.toString());
		dramaService.addDrama(dramas);
		}
		
		
		return "dramadetail";
	}
}
