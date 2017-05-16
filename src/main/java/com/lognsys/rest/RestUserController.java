package com.lognsys.rest;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.lognsys.dao.dto.DramasDTO;
import com.lognsys.dao.dto.GroupsDTO;
import com.lognsys.dao.dto.RolesDTO;
import com.lognsys.model.Drama;
import com.lognsys.model.Users;
import com.lognsys.service.DramaService;
import com.lognsys.service.UserService;
import com.lognsys.util.FormValidator;

//@RestController
public class RestUserController {

	@Autowired
	private UserService userService;

 
 
	@PostMapping(value = "/createuser")
	public ResponseEntity saveForm(@RequestBody  Users user,UriComponentsBuilder ucBuilder,
			BindingResult result) {
		System.out.println("createuser result - " + result.toString());
		
		FormValidator formValidator = new FormValidator();
		formValidator.validate(user, result);
		System.out.println("createuser result.hasErrors() - " + result.hasErrors());
		
		if (result.hasErrors()) {
			
			System.out.println("createuser  - " + user.toString());
	
			 return new ResponseEntity<Void>(HttpStatus.CONFLICT);
	 	} 
		System.out.println("createuser  after user.toString() - " + user.toString());
		
			userService.addUser(user);
		
		 
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
   
	}
}