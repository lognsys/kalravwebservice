package com.lognsys.controller;

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

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.flash;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.aop.AopInvocationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.hamcrest.Matcher;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.ContextConfiguration;
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
import com.lognsys.dao.dto.UsersDTO;
import com.lognsys.model.Users;
import com.lognsys.service.UserService;
import com.lognsys.util.FormValidator;
import com.lognsys.web.controller.BaseController;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:application-context.xml", "classpath:datasource-context.xml",
"classpath:mongo-context.xml" })
@WebAppConfiguration
public class RestBaseControllerTest {

	private Logger LOG = Logger.getLogger(getClass());
	private MockMvc mockMvc;
	   
	@Autowired
	private UserService userService;



    //Add WebApplicationContext field here
 
    //The setUp() method is omitted.
	 @Test
	    public void addUserView() throws Exception {
		 Users users1=new Users();
		 Users users=new Users();
	     users.setAuth_id("1234");
	     users.setAddress("Malad");
	     users.setBirthdate("1991-05-13");
	     users.setCity("Mumbai");
	     users.setDevice("Xml1234");
	     users.setEnabled(true);
	     users.setFirstname("reena");
	     users.setGroup("JJC");
	     users.setlastname("More");
	     users.setNotification(true);
	     users.setPhone("7788998877");
	     users.setProvenance("Android");
	     users.setRealname("Mana More");
	     users.setRole("GUEST");
	     users.setState("Maharashtra");
	     users.setUsername("Maharashtra@gmail.com");
	     users.setZipcode("401107");

	        Mockito.when(this.userService.addUser(users)).thenReturn(72);
	        mockMvc.perform(post("/createuser")
	                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
	                .sessionAttr("users", new Users()))
	                .andExpect(status().isMovedTemporarily())
	                .andExpect(view().name("redirect:todo/{id}"))
	                .andExpect(redirectedUrl("/getsingleuser/"+71))
	                .andExpect(model().attribute("id", is(71)))
	                .andExpect(flash().attribute("feedbackMessage", is("Todo entry: title was added.")));
	 
	        ArgumentCaptor<UsersDTO> formObjectArgument = ArgumentCaptor.forClass(UsersDTO.class);
	        ((List) verify(userService, times(1))).add(formObjectArgument.capture());
	        verifyNoMoreInteractions(userService);
	 
	        UsersDTO formObject = formObjectArgument.getValue();
	 
	        assertThat(formObject.getUsername(), is("username"));
	        assertNull(formObject.getId());
	        assertThat(formObject.getRealname(), is("realname"));
	    }
	 
//
//	    @Test
//	    public void findById_UserEntryToModelAndRenderView() throws Exception {
//	        Users found = new Users();
//	        found.setId(66);
//	             
//	 
//	        Mockito.when(userService.getUserWithRoleAndGroup(66)).thenReturn(userService.getUserWithRoleAndGroup(66));
//	 
//	       
//	        mockMvc.perform(get("/getsingleuser/{id}", 66))
//	                .andExpect(status().isOk())
//	                .andExpect(view().name("userlist"))
//	                .andExpect(forwardedUrl("/WEB-INF/pages/userlist.jsp"));
//	 
//	        verify(userService, times(1)).getUserWithRoleAndGroup(66);
//	        verifyNoMoreInteractions(userService);
//	    }
//
//
//		private Object hasProperty(String string, Matcher<Object> matcher) {
//			// TODO Auto-generated method stub
//			return null;
//		}
//	 
}
