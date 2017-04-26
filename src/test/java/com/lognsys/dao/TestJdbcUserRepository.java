package com.lognsys.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lognsys.dao.dto.UsersDTO;
import com.lognsys.dao.jdbc.JdbcUserRepository;
import com.lognsys.model.Users;
import com.lognsys.util.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:datasource-context.xml" })
public class TestJdbcUserRepository {

	@Autowired
	private JdbcUserRepository userRepo;

	public void setUp() {

	}


	@Test
	public void testCreateUser() {
		Users users = new Users();

		users.setAuth_id("123456789");

		users.setRealname("Priyank Doshi");

		users.setUsername("doshipriyank");

		users.setPhone("9867544359");

		users.setState("Maharashtra");

		users.setCity("Mumbai");

		users.setDevice("ABXCS67868");

		users.setZipcode("400067");

		users.setCompany_name("LognSystems");

		users.setAddress("Kandivali West");

		users.setLocation("Kandivali West Again...");

		users.setProvenance("Web");

		users.setNotification(true);

		java.util.Date dt = new java.util.Date();
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currentTime = sdf.format(dt);

		users.setBirthdate(currentTime);
		
		UsersDTO usersDTO = ObjectMapper.mapToUsersDTO(users);

		userRepo.addUser(usersDTO);

	}

}