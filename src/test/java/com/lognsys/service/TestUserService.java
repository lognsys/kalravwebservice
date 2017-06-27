package com.lognsys.service;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lognsys.model.Users;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:application-context.xml", "classpath:datasource-context.xml",
		"classpath:mongo-context.xml" })
public class TestUserService {

	@Autowired
	private UserService userService;

	public void setUp() {

	}

	@Test
	public void testRefreshList() throws IOException {
		userService.refreshUserList();
	}

	@Test
	public void testAddUser() throws IOException {
		Users users = new Users();

		users.setAuth_id("123456789");

		users.setRealname("Priyank Doshi");

		users.setUsername("doshipriyank");

		users.setPhone("9867544359");

		users.setState("Maharashtra");

		users.setCity("Mumbai");

		users.setDevice("ABXCS67868");

		users.setZipcode("400067");

//		users.setCompany_name("LognSystems");

		users.setAddress("Kandivali West");

//		users.setLocation("Kandivali West Again...");

		users.setProvenance("Web");

		users.setNotification(true);
		users.setRole("Admin");
		users.setGroup("ladies");

		java.util.Date dt = new java.util.Date();
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currentTime = sdf.format(dt);
		users.setBirthdate(currentTime);
		userService.addUser(users);
	}
	
	@Test
	public void testUserService(){
		String username = "lognsystems@gmail.com";
		
		Users user = userService.getUserWithRoleAndGroup(username);
		System.out.println("Users - "+user);
		
		
	}

}
