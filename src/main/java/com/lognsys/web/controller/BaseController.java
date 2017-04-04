package com.lognsys.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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

	@RequestMapping(method = RequestMethod.POST)
	public String submitForm(Model model, Users userslistcheck,
		
		BindingResult result) {
		System.out.println("Going in ubmitForm");
		model.addAttribute("userslistcheck", userslistcheck);
		return "success";

	}
	@RequestMapping(value = "/dashboard")
	public ModelAndView showDashboard(Model model1A, HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		model.setViewName("drama");
		System.out.println("Going in Dashbaord controller");
		/*if(userRep.getAllUsers()!=null && userRep.getAllUsers().size()>0){
			List<Users> listContact = userRep.getAllUsers();
		    model.addObject("listUsers", listContact);
		    model.setViewName("user_listitems");
			
		    return model;
				
		}*/
		return model;
		
	}
	
// monika added to  list the users in listview
	@RequestMapping(value = "/user_listitems")
	public String showGridUser(Model model, HttpServletRequest request) {
		System.out.println("Going in grid controller");
		System.out.println("Going in grid controller +userRep.getAllUsers() "+userRep.getAllUsers().size());
		
		if(userRep.getAllUsers()!=null && userRep.getAllUsers().size()>0){
			List<Users> user_listitems = userRep.getAllUsers();
			model.addAttribute("user_listitems", user_listitems);
			return  "user_listitems";
		}
		return "user_listitems";
	}
	
	   /* It deletes record for the given id in URL and redirects to /viewemp */  
    @RequestMapping(value="/deleteemp/{id}",method = RequestMethod.GET)  
    public ModelAndView delete(@PathVariable int id){  
    	System.out.println("Going in delete  ");
		
    	List<Users> list=userRep.getAllUsers();
    	for(int i=0;i<list.size();i++){
    		Users usr=list.get(i);
        	System.out.println("Going in delete usr.getId() "+usr.getId());
    		if(usr.getId()==id){
    			userRep.delete(id,usr);
        	 	return new ModelAndView("redirect:/user_listitems");  
        	}
    		     		
    	}
    	return new ModelAndView("redirect:/user_listitems");  
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
