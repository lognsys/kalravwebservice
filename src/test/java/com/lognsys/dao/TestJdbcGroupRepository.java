package com.lognsys.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import com.lognsys.dao.dto.UsersGroupsDTO;
import com.lognsys.dao.jdbc.JdbcGroupRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:datasource-context.xml" })
public class TestJdbcGroupRepository {

	@Autowired
	private JdbcGroupRepository groupRepo;

	public void setUp() {

	}

	@Test
	public void testGetUsersByGroup() {
		List<UsersGroupsDTO> ug = groupRepo.getAllUsersAndGroup();

		Assert.notNull(ug, "Check list of Users NOT NULL");
		Assert.notEmpty(ug, "Collection not empty..list of Users and Group object");
	}
	
	@Test
	public void testGetAllUsersAndGroup() {
		String group_name_1 = "ladies";
		List<UsersGroupsDTO> ug1 = groupRepo.getUsersByGroup(group_name_1);
		org.junit.Assert.assertEquals("No of Users in Ladies Group", 3, ug1.size());
		
		String group_name_2 = "couple";
		List<UsersGroupsDTO> ug2 = groupRepo.getUsersByGroup(group_name_2);
		org.junit.Assert.assertEquals("No of Users in Couple Group", 4, ug2.size());
	}

	
	@Test
	public void testfindGroupBy(){
		int user_id = 15;
		
		String expected = "ladies";
		String actual = groupRepo.findGroupBy(user_id);
		assertEquals(expected, actual);
	}
}
