package com.lognsys.service;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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

}
